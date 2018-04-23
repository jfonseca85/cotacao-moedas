package com.shangrila.microservices.currency.conversion.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LocalDateUtil {
	
    public static int fimDeSemana(LocalDate ld) {
        DayOfWeek d = ld.getDayOfWeek();
        if (d == DayOfWeek.SATURDAY) return 1;
        if (d == DayOfWeek.SUNDAY) return 2;
		return 1;
    }
    
    public static Boolean isDomingo(LocalDate ld) {
        DayOfWeek d = ld.getDayOfWeek();
        if (d == DayOfWeek.SUNDAY) return true;
		return false;
    }
    
}
