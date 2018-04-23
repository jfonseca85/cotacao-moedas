package com.shangrila.microservices.currency.exchange.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.shangrila.microservices.currency.exchange.exception.CotacaoMoedaInternalServerErrorException;

public class LocalDateUtil {
	/***
	 * Verifca se um dia da Semana e Sabado ou Domingo
	 * Caso for Domingo subtrai 2 dias, neste caso o proximo dia vai ser sexta feira
	 * Nos demais dias sempre subtraira um dia
	 ***/
    public static int diasASubtrair(LocalDate ld) {
        DayOfWeek d = ld.getDayOfWeek();
//        if (d == DayOfWeek.SATURDAY) return 1;
        if (d == DayOfWeek.SUNDAY) return 2;
		return 1;
    }
    
	/***
	 * Verifca se um dia da Semana e Sabado ou Domingo
	 * Caso for Domingo subtrai 2 dias, neste caso o proximo dia vai ser sexta feira
	 * Caso for sabado subtrai 1 dia
	 * Nos demais casos nao ira subtrair
	 ***/
    public static int finalSemana(LocalDate ld) {
        DayOfWeek d = ld.getDayOfWeek();
        if (d == DayOfWeek.SATURDAY) return 1;
        if (d == DayOfWeek.SUNDAY) return 2;
		return 0;
    }
    
	/***
	 * Retorna True Caso for Domingo
	 * False nos demais casos
    ***/
    public static Boolean isDomingo(LocalDate ld) {
        DayOfWeek d = ld.getDayOfWeek();
        if (d == DayOfWeek.SUNDAY) return true;
		return false;
    }
	/***
	 * Retorna True Caso for Sabado
	 * False nos demais casos
    ***/
    public static Boolean isSabado(LocalDate ld) {
        DayOfWeek d = ld.getDayOfWeek();
        if (d == DayOfWeek.SATURDAY) return true;
		return false;
    }
	/***
	 * Realiza a conversao de String para LocalDate
    ***/
    public static LocalDate parseStringForLocalDate(String value, String formatter) {
    	try {
    		DateTimeFormatter DateTimeformatter = DateTimeFormatter.ofPattern(formatter);
    		return  LocalDate.parse(value, DateTimeformatter);
		} catch (NullPointerException | IllegalArgumentException | DateTimeParseException  e) {
			throw new CotacaoMoedaInternalServerErrorException(" Internal Erro : "+e.getMessage());
		}
    }
	/***
	 * Realiza a conversao de LocalDate para String
    ***/
    public static String parseLocalDateForString(LocalDate value, String formatter) {
    	try {
    		DateTimeFormatter DateTimeformatter = DateTimeFormatter.ofPattern(formatter);
    		return DateTimeformatter.format(value);
		} catch (NullPointerException | IllegalArgumentException | DateTimeParseException  e) {
			throw new CotacaoMoedaInternalServerErrorException(" Internal Erro : "+e.getMessage());
		}
    }
    
}
