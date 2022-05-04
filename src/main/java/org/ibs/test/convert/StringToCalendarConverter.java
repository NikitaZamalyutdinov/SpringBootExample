package org.ibs.test.convert;

import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StringToCalendarConverter implements Converter<String, Calendar> {
    @SneakyThrows
    @Override
    public Calendar convert(@NonNull String source) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        calendar.setTime(sdf.parse(source));
        return calendar;
    }
}
