package net.hawkelele.quickinfopanel;

public class Utils {
    public static String truncate(String input, int max, int remainder) {
        return input.length() > max
                ? input.substring(0, max - 2 - remainder)
                + "…"
                + input.substring(input.length() - remainder)
                : input;

    }
}
