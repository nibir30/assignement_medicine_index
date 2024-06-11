package com.nibir.medicine_index.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class NullHandlerUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String removeNull(String inputStr) {
        if (inputStr == null) {
            inputStr = "";
        } else if (inputStr.equals("null")) {
            inputStr = "";
        }
        return inputStr;
    }

    public static String removeNull(Long input) {
        String inputStr = "";
        if (input == null) {
            inputStr = "";
        }
        return input + "";
    }

    public static String removeEmptyStr(String inputStr) {
        if (inputStr != null && (inputStr.equals("") || inputStr.equals("null"))) {
            inputStr = null;
        }
        return inputStr;
    }

    public static String removeStringNull(String inputStr) {
        if (inputStr == null || inputStr.equalsIgnoreCase("null")) {
            inputStr = null;
        }
        return inputStr;
    }

    public static String removeNullAndReturnString(Object a) {
        if (a == null) {
            return "";
        }
        if (a == "null") {
            return "";
        }
        return String.valueOf(a);
    }

    public static Integer removeNullAndReturnInteger(Object a) {
        if (a == null) {
            return 0;
        }
        if (a == "null") {
            return 0;
        }
        if (a.equals("")) {
            return 0;
        }
        return Integer.valueOf(String.valueOf(a));
    }

    public static Long removeNullAndReturnLong(Object a) {
        if (a == null) {
            return 0L;
        }
        if (a == "null") {
            return 0L;
        }
        if (a.equals("")) {
            return 0L;
        }
        return Long.valueOf(String.valueOf(a));
    }

    public static Long returnLong(Object a) {
        if (a == null || a == "null" || a.equals("")) {
            return null;
        }
        return Long.valueOf(String.valueOf(a));
    }

    public static Integer returnInteger(Object a) {
        if (a == null || a == "null" || a.equals("")) {
            return null;
        }
        return Integer.valueOf(String.valueOf(a));
    }

    public static Double returnDouble(Object a) {
        if (a == null || a == "null" || a.equals("")) {
            return null;
        }
        return Double.valueOf(String.valueOf(a));
    }

    public static String returnString(Object a) {
        if (a == null || a == "null" || a.equals("")) {
            return null;
        }
        return String.valueOf(a);
    }

    public static String nvl(String input1, String input2) {
        if (!StringUtils.hasText(input1)) {
            return input2;
        }
        return input1;
    }

    public static Long nvl(Long input1, Long input2) {
        if (input1 == null) {
            return input2;
        }
        return input1;
    }

    public static Integer nvl(Integer input1, Integer input2) {
        if (input1 == null) {
            return input2;
        }
        return input1;
    }

    public static LocalDate nvl(LocalDate input1, LocalDate input2) {
        if (input1 == null) {
            return input2;
        }
        return input1;
    }

    public static LocalDateTime nvl(LocalDateTime input1, LocalDateTime input2) {
        if (input1 == null) {
            return input2;
        }
        return input1;
    }

    public static BigDecimal nvl(BigDecimal input1, BigDecimal input2) {
        if (input1 == null) {
            return input2;
        }
        return input1;
    }

    public static Function<Object, Boolean> isNull = (obj) -> {
        if (null == obj) {
            return true;
        } else if ("null".equals(obj)) {
            return true;
        } else return "".equals(obj);
    };
}
