package org.ibs.test.config;

import org.ibs.test.convert.StringToCalendarConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;

@Configuration
public class ConverterConfig {

    @Autowired
    void conversionService(@Qualifier(value = "defaultConversionService") GenericConversionService gcs) {
        gcs.addConverter(new StringToCalendarConverter());
    }
}
