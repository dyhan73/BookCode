package com.ensoa.order.formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;


public class DateFormatter implements Formatter<Date> {
	private String format;
	
	
	public DateFormatter() {
		
	}
	public DateFormatter(String format) {
		this.format = format;
	}

	@Override
	public String print(Date date, Locale locale) {
		return getDateFormat(locale).format(date);
	}

	@Override
	public Date parse(String date, Locale locale) throws ParseException {
		return getDateFormat(locale).parse(date);
	}

	private DateFormat getDateFormat(Locale locale) {
		if(StringUtils.hasText(this.format))
			return new SimpleDateFormat(this.format, locale);
		else 
			return SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, locale);
	}
	public void setFormat(String format) {
		this.format = format;
	}
}
