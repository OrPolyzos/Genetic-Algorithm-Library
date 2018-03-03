package view;

import convex_hull.Convex_Hull_Problem;

import javax.swing.*;
import java.awt.*;

public class JActionsPanel extends JPanel {

    public JActionsPanel() {
        super();
        this.setLayout(new FlowLayout());
        JButton newProblem = new JButton("New Problem");
        this.add(newProblem);
        newProblem.addActionListener(event -> {

            SwingWorker<String, Object> worker = new SwingWorker<String, Object>() {
                @Override
                protected String doInBackground() throws Exception {
                    Canvas.getInstance().getCh_jInputPanel().toggleEverything();
                    new Convex_Hull_Problem().initializeProblem();
                    return null;
                }

                @Override
                protected void done() {
                    Canvas.getInstance().getCh_jInputPanel().toggleEverything();
                }
            };
            worker.execute();
        });
    }

}
