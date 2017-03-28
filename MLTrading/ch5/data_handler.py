import mysql.connector as mdb

class DataHandler():
    def __init__(self):
        self.conn = mdb.connect(user='mltdev', password='asdfasdf', host='localhost', database='mltdb')

    def beginTrans(self):
        self.conn.autocommit = False

    def endTrans(self):
        try:
            self.conn.commit()
            self.conn.autocommit = True
        except:
            print("Fatal Error in commit !!!")
            self.conn.rollback()


    def openSql(self, sql):
        try:
            cursor = self.conn.cursor()
            cursor.execute(sql)
            rows = cursor.fetchall()
            cursor.close()
            return rows
        except:
            #print("Unexpected error in ExecSQL:", sys.args[0])
            raise

    def execSql(self, sql, db_commit=True):
        try:
            cursor = self.conn.cursor()
            cursor.execute(sql)
            if db_commit:
                self.conn.commit()
            cursor.close()
            return

        except Exception as error:
            print(">>> Unexpected error in ExecSQL: ", error)
            print("--- %s ---" % (sql))
            #raise
