package net.hawkelele.quickinfopanel.provider;

import net.hawkelele.quickinfopanel.gui.panel.AlternateDimensionPanel;
import net.hawkelele.quickinfopanel.gui.panel.Panel;

public class PanelProvider {
    private static final Panel panel = new Panel();
    private static final AlternateDimensionPanel alternateDimensionPanel = new AlternateDimensionPanel();

    public static Panel getPanel() {
        return panel;
    }

    public static AlternateDimensionPanel getAlternateDimensionPanel() {
        return alternateDimensionPanel;
    }
}
