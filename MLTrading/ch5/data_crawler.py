# coding: utf-8

from datetime import datetime

import requests
import bs4
from pandas_datareader import data as web

from data_model import StockCode
from data_handler import DataHandler
from data_writer import DataWriter
from services import services
from stock_common import convertMarketType


class DataCrawler:
    def __init__(self, wait_sec=5):
        self.wait_sec = wait_sec
        self.dbwriter = services.get('dbwriter')
        self.dbhandler = services.get('dbhandler')

    def downloadCode(self, market_type):
        url = 'http://datamall.koscom.co.kr/servlet/infoService/SearchIssue'
        html = requests.post(url, data={'flag':'SEARCH', 'marketDisabled': 'null', 'marketBit':market_type})
        return html.content

    def parseCodeHTML(self, html, market_type):
        soup = bs4.BeautifulSoup(html)
        options = soup.findAll('option')

        codes = StockCode()

        for a_option in options:
            #print(a_tr)
            if len(a_option) == 0:
                continue
            if a_option.text.endswith('(폐지)'):
                continue

            code = a_option.text[1:7]
            company = a_option.text[8:]
            full_code = a_option.get('value')

            codes.add(market_type, code, full_code, company)

        return codes


    def parseCodeHTML2(self, html, market_type):
        soup = bs4.BeautifulSoup(html)
        table = soup.find('table', {'id':'tbl1'})
        trs = table.findAll('tr')

        codes = StockCode()

        for a_tr in trs:
            #print(a_tr)
            cols = a_tr.findAll('td')
            if len(cols) == 0:
                continue

            #print(cols)
            code = cols[0].text[1:]
            company = cols[1].text.replace(";", "")
            if company.endswith('(폐지)'):
                continue
            full_code = cols[2].text

            codes.add(market_type, code, full_code, company)

        return codes

    def updateAllCodes(self):
        for market_type in [1, 2]: #['kospiVal', 'kosdaqVal']:
            html = self.downloadCode(market_type)
            codes = self.parseCodeHTML(html, market_type)
            self.dbwriter.updateCodeToDB(codes)


    def downloadStockData(self, market_type, code, year1, month1, date1, year2, month2, date2):
        def makeCode(market_type, code):
            if market_type == 1:
                return "%s.KS" % (code)

            return "%s.KQ" % (code)

        start = datetime(year1, month1, date1)
        end = datetime(year2, month2, date2)
        try:
            df = web.DataReader(makeCode(market_type, code), "yahoo", start, end)
            return df
        except:
            print("!!! Fatal Error Occurred")
            return None


    def getDataCount(self, code):
        sql = "select code from prices where code='%s'" % (code)
        rows = self.dbhandler.openSql(sql)
        return len(rows)


    def updateAllStockData(self, market_type, year1, month1, date1, year2, month2, date2, start_index=1):
        print("Start Downloading Stock Data : %s , %s%s%s ~ %s%s%s"
              % (market_type, year1, month1, date1, year2, month2, date2))

        sql = "select * from codes"
        sql += " where market_type=%s" % (market_type)
        if start_index > 1:
            sql += " and id>%s" % (start_index)

        rows = self.dbhandler.openSql(sql)

        self.dbhandler.beginTrans()

        index = 1
        for a_row in rows:
            #print(a_row)
            code = a_row[2]
            company = a_row[5]

            data_count = self.getDataCount(code)
            if data_count == 0:

                print("... %s of %s : Downloading %s data " % (index, len(rows), company))

                df_data = self.downloadStockData(market_type, code, year1, month1, date1, year2, month2, date2)
                if df_data is not None:
                    df_data_indexed = df_data.reset_index()
                    self.dbwriter.updatePriceToDB(code, df_data_indexed)

            index += 1
            #return

        self.dbhandler.endTrans()

        print("Done!!!")


if __name__ == "__main__":
    services.register('dbhandler', DataHandler())
    services.register('dbwriter', DataWriter())

    crawler = DataCrawler()
    #html_codes = crawler.downloadCode('2')
    #print(html_codes.__class__)
    #crawler.parseCodeHTML(html_codes, '2')

    #crawler.updateAllCodes()
    crawler.updateAllStockData(2, 2010, 1, 1, 2015, 12, 1, start_index=1)
