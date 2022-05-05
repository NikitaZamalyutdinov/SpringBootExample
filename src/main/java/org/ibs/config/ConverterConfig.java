package org.ibs.config;

import org.ibs.utils.convert.StringToCalendarConverter;
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
