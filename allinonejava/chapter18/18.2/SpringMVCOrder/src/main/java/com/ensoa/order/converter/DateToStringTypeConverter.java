package com.ensoa.order.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateToStringTypeConverter  implements Converter<Date, String> {
	@Override
	public String convert(Date source) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(source);
	}
}
