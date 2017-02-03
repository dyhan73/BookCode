package com.ensoa.order.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateTypeConverter  implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}
}
