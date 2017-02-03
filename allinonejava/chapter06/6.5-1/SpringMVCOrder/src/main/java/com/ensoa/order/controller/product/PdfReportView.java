package com.ensoa.order.controller.product;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.ensoa.order.model.ProductModel;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReportView extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<ProductModel> products = (List<ProductModel>)model.get("products");
		String font = "font/NanumMyeongjo.ttf";
		BaseFont bf = BaseFont.createFont(font, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font titleFont = new Font(bf, 16);
		Font bodyFont = new Font(bf, 12);
		Paragraph title = new Paragraph("제품 목록", titleFont);
		title.setAlignment("center");
		document.add(title);
		Table table = new Table(3);
		table.addCell(new Paragraph("제품명", bodyFont));
		table.addCell(new Paragraph("가격", bodyFont));
		table.addCell(new Paragraph("제품 설명", bodyFont));
		for(ProductModel product : products) {
			table.addCell(new Paragraph(product.getName(), bodyFont));
			table.addCell(new Paragraph(String.valueOf(product.getPrice()), bodyFont));
			table.addCell(new Paragraph(product.getDescription(), bodyFont));
		}
		document.add(table);
	}
}
