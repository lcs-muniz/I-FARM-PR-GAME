package com.fazenda.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateUtils {
    public static Timestamp toTimestamp(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt);
    }
    
    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
