package net.hawkelele.quickinfopanel.config.settings;

import org.apache.commons.lang3.StringUtils;

public class GeneralSettings extends Settings {
    public boolean displayPanel = true;
    public boolean displayAlternateDimensionInfo = true;
    public Position position;

    public static class Position {
        public String code = "";

        public int x;
        public int y;
        public boolean centered;
        public boolean moveWithInventory;
        public boolean hideWithActionbar;
        public boolean invertLines;
        public boolean invertedX;
        public boolean invertedY;

        public static Position preset(String code) {
            try {
                return ((PositionPresets) PositionPresets.class.getField(StringUtils.upperCase(StringUtils.replace(code, "-", "_"))).get(null)).position;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return PositionPresets.DEFAULT.position;
            }
        }

        public Position(String code, int x, int y, boolean centered, boolean moveWithInventory, boolean hideWithActionbar, boolean invertLines, boolean invertedX, boolean invertedY) {
            this.code = code;
            this.x = x;
            this.y = y;
            this.centered = centered;
            this.moveWithInventory = moveWithInventory;
            this.hideWithActionbar = hideWithActionbar;
            this.invertLines = invertLines;
            this.invertedX = invertedX;
            this.invertedY = invertedY;
        }


        public Position(int x, int y, boolean centered, boolean moveWithInventory, boolean hideWithActionbar, boolean invertLines, boolean invertedX, boolean invertedY) {
            this("custom", x, y, centered, moveWithInventory, hideWithActionbar, invertLines, invertedX, invertedY);
        }

    }
}
