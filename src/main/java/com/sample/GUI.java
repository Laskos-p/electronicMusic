package com.sample;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class GUI {
    private JLabel label;
    private JFrame frame;
    private ButtonGroup buttonGroup;
    private String selected;


    public GUI() {
        frame = new JFrame();
        frame.setSize(500, 500);

        label = new JLabel();
//            label.setBounds(100, 100, 100, 30);
        frame.add(label);

        buttonGroup = new ButtonGroup();

        frame.setLayout(new GridLayout(0, 1));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setLabelText(String text) {
        label.setText(text);
        refresh();
    }

    public void setAnswerOptions(String[] options) {
        for (AbstractButton button: Collections.list(buttonGroup.getElements())) {
            buttonGroup.remove(button);
            frame.remove(button);
        }
//            buttonGroup = new ButtonGroup();
//            System.out.println(options.length);
        for (String option: options) {
            JRadioButton button = new JRadioButton(option);
//                button.setBounds(100, 150, 100, 30);
            buttonGroup.add(button);
            frame.add(button);
            button.setActionCommand(option);
            button.addActionListener(e -> setSelected());
        }
        refresh();
    }

    public void setSelected() {
        selected = buttonGroup.getSelection().getActionCommand();
    }

    public String getSelected() {
    	this.setSelected();
        return selected;
    }

    private void refresh() {
        frame.revalidate();
        frame.repaint();
    }
}
