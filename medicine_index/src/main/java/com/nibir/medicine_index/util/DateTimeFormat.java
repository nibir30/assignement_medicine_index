package com.nibir.medicine_index.util;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@UtilityClass
public class DateTimeFormat {

    private static final DateTimeFormatter formatterDdMmYyyy = DateTimeFormatter
            .ofPattern("dd-MM-uuuu", Locale.ENGLISH)
            .withResolverStyle(ResolverStyle.STRICT);
    private static final SimpleDateFormat dmyDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    private static final SimpleDateFormat eDmyHmsAzDateTimeFormat = new SimpleDateFormat("EEEE dd MMMM yyyy hh:mm:ss a z", Locale.ENGLISH);

    private static final SimpleDateFormat E_MDY_AT_HM_A_DATE_TIME_FORMATTER = new SimpleDateFormat("EE MMM dd yyyy 'at' hh:mm a", Locale.ENGLISH);
    private static final SimpleDateFormat E_MD_AT_HM_A_DATE_TIME_FORMATTER = new SimpleDateFormat("EE MMM dd 'at' hh:mm a", Locale.ENGLISH);
    private static final SimpleDateFormat AT_HM_A_DATE_TIME_FORMATTER = new SimpleDateFormat(" 'at' hh:mm a", Locale.ENGLISH);

    // public Supplier<Timestamp> currentSqlTimestamp = () -> new Timestamp(System.currentTimeMillis());
    public Consumer<String> validateDDMMYYYY = (date) -> LocalDate.parse(date, formatterDdMmYyyy);
    public Function<String, LocalDate> strDmyToDmyLocalDate = (dmyString) -> LocalDate.parse(dmyString, formatterDdMmYyyy);

    //------------------------------------
    public Supplier<String> currentEDmyHmsAzDateTime = () -> eDmyHmsAzDateTimeFormat.format(new Date());
    public Supplier<LocalDateTime> getCurrentLocalDateTime = LocalDateTime::now;
}