package utils;

public class LetterContent {

    public static String compose(long count) {
        return "Найдено " + count + " " + pluralize(count, new String[]{"письмо", "письма", "писем"});
    }

    private static String pluralize(long number, String[] words) {
        long dozens = number % 100;
        long units = dozens % 10;
        if (dozens > 10 && dozens < 20) {
            return words[2];
        } else if (units > 1 && units < 5) {
            return words[1];
        } else if (units == 1) {
            return words[0];
        } else {
            return words[2];
        }
    }
}
