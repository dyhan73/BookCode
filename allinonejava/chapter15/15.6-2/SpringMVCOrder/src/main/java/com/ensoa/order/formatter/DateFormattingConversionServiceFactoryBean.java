package com.ensoa.order.formatter;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class DateFormattingConversionServiceFactoryBean extends
		FormattingConversionServiceFactoryBean {
	protected void installFormatters(FormatterRegistry registry) {
		registry.addFormatterForFieldAnnotation(new DateFormatAnnotationFormatterFactory());
	}
}
