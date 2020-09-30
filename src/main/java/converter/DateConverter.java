package converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<Date, Long> {
    @Override
    public Long convertToDatabaseColumn(Date date) {
        return date.getTime();
    }

    @Override
    public Date convertToEntityAttribute(Long timestamp) {
        return new Date(timestamp);
    }
}
