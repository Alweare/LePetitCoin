package fr.eni.enchere.controllers.converters;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateTimeFinConverter implements Converter<String, LocalDateTime>{

	@Override
	public LocalDateTime convert(String timeStamp) {
		if (timeStamp.isEmpty() || timeStamp.isBlank()) {
			return null;
		}
		return LocalDateTime.parse(timeStamp);
	}

}
