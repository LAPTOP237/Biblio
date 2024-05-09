/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 *
 * @author LINJOUOM ALAIN P
 */
public class DateUtils {
    
    public static Date getNext(String inputDate, int jour) throws ParseException {
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);
        LocalDate nextDay = date.plusDays(jour);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(nextDay.format(DateTimeFormatter.ISO_DATE));
    }
    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    
}
