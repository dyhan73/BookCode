package com.ensoa.order.controller.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.ensoa.order.model.ProductModel;

public class ExcelReportView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<ProductModel> products = (List<ProductModel>)model.get("products");
		int rows = 0;
		HSSFCellStyle style = getTitleStyle(workbook);
		HSSFSheet sheet = workbook.createSheet("Products");
		HSSFRow row = sheet.createRow(rows++);
		HSSFCell cell = row.createCell(1);
		cell.setCellValue("제품 목록");
		cell.setCellStyle(style);
		row = sheet.createRow(rows++);
		row = sheet.createRow(rows++);
		style = getHeaderStyle(workbook);
		cell = row.createCell(0);
		cell.setCellValue("제품명");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("가격");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("제품 설명");	
		cell.setCellStyle(style);
		style = getNormalStyle(workbook);
		for(ProductModel product : products) {
			row = sheet.createRow(rows++);
			cell = createCell(style, row, 0);
			cell.setCellValue(product.getName());
			cell = createCell(style, row, 1);
			cell.setCellValue(product.getPrice());
			cell = createCell(style, row, 2);
			cell.setCellValue(product.getDescription());
		}
	}
	private HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont font = workbook.createFont();
		font.setFontName("나눔명조");
		font.setFontHeightInPoints((short)12);
		font.setColor(HSSFColor.WHITE.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}
	private HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont font = workbook.createFont();
		font.setFontName("나눔명조");
		font.setFontHeightInPoints((short)10);
		font.setColor(HSSFColor.WHITE.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}
	private HSSFCellStyle getNormalStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont font = workbook.createFont();
		font.setFontName("나눔명조");
		font.setFontHeightInPoints((short)10);
		font.setColor(HSSFColor.BLACK.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		style.setFont(font);
		return style;
	}
	private HSSFCell createCell(HSSFCellStyle style, HSSFRow row, int column) {
		HSSFCell cell = row.createCell(column);
		cell.setCellStyle(style);
		return cell;
	}
}
