package no.jostein.util;

public enum ColorCode {
    //Color end string, color reset
    RESET("\033[0m"),
   // RED
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m");   // YELLOW

    private final String code;

    ColorCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}