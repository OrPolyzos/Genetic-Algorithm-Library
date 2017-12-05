package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JStatsPanel extends JPanel {

    private List<JComponent> componentList;

    public JStatsPanel(List<JComponent> componentList){
        super(new BorderLayout());
        this.componentList = componentList;
        for (JComponent jComponent : componentList){
            this.add(jComponent);
        }
    }
}
