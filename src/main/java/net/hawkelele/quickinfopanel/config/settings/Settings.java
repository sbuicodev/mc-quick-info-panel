package net.hawkelele.quickinfopanel.config.settings;

import java.io.Serializable;

public abstract class Settings implements Cloneable, Serializable {
    @Override
    public Settings clone() {
        try {
            return (Settings) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
