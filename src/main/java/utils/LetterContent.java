package utils;

public class LetterContent {

    public static String compose(int count) {
        int dozens = count % 100;
        int units = dozens % 10;
        if (dozens > 10 && dozens < 20) return "Найдено " + count + " писем";
        if (units > 1 && units < 5) return "Найдено " + count + " письма";
        if (units == 1) return "Найдено " + count + " письмо";
        return "Найдено " + count + " писем";
    }
}
