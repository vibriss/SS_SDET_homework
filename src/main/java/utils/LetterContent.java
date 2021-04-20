package utils;

public class LetterContent {

    public static String compose(long count) {
        return "Найдено " + count + " " + pluralize(count, new String[]{"письмо", "письма", "писем"});
    }

    private static String pluralize(long number, String[] words) {
        long dozens = number % 100;
        long units = dozens % 10;
        int i = 2;
        if (dozens < 10 || dozens > 20) {
            if (units > 1 && units < 5) {
                i = 1;
            } else if (units == 1) {
                i = 0;
            }
        }
        return words[i];
    }
}
