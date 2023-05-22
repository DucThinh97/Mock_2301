package com.vti.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OderListStatusConvert implements AttributeConverter<OderList.Status, String> {

    @Override
    public String convertToDatabaseColumn(OderList.Status status) {
        if (status == null) {
            return null;
        }

        return status.getValue();
    }

    @Override
    public OderList.Status convertToEntityAttribute(String sqlValue) {
        if (sqlValue == null) {
            return null;
        }

        return OderList.Status.toEnum(sqlValue);
    }

}