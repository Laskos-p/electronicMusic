package com.sample;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class GUI {
    private JLabel label;
    private JFrame frame;
    private ButtonGroup buttonGroup;
    private String selected;
    private JPanel panel;

    public GUI() {
        frame = new JFrame();
        frame.setSize(800, 500);

        frame.setLayout(new GridBagLayout());

        label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.add(panel);
        panel.add(label);

        buttonGroup = new ButtonGroup();

        frame.setLocationRelativeTo(null);
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
            panel.remove(button);
        }

            buttonGroup = new ButtonGroup();
        for (String option: options) {
            JRadioButton button = new JRadioButton(option);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setBackground(Color.LIGHT_GRAY);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.GRAY);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.LIGHT_GRAY);
                }
            });
            buttonGroup.add(button);
            panel.add(button);
            button.setActionCommand(option);
            button.addActionListener(e -> setSelected());
        }
        refresh();
    }

    public void setSelected() {
        selected = buttonGroup.getSelection().getActionCommand();
    }

    public String getSelected() {
    	if (buttonGroup.getSelection() != null) {
            return buttonGroup.getSelection().getActionCommand();
        }
        return null;
    }

    private void refresh() {
        frame.revalidate();
        frame.repaint();
    }
}
