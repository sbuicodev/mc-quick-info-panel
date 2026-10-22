package net.hawkelele.quickinfopanel.providers.client;

public final class OverlayMessageStatusProvider {
    private static boolean displaying = false;

    private OverlayMessageStatusProvider() {
    }

    public static boolean isDisplaying() {
        return displaying;
    }

    public static void setDisplaying(boolean status) {
        displaying = status;
    }

    public static void reset() {
        displaying = false;
    }
}
