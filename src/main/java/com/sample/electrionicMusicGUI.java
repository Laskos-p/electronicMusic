package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class electrionicMusicGUI {

	public static void main(String[] args) {
        try {
            // Create gui
            GUI gui = new GUI();
            GUIState guiState = new GUIState(gui);

            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
            kSession.insert(guiState);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class GUIState {
        private String question;
        private String[] answerOptions;
        private GUI gui;

        public GUIState(GUI gui) {
            this.gui = gui;
        }

        public void setQuestion(String question) {
            this.question = question;
            gui.setLabelText(question);
        }

        public String getQuestion() {
            return question;
        }

        public void setAnswerOptions(String[] options) {
            gui.setAnswerOptions(options);
        }

        public String getAnswer() {
            return gui.getSelected();
        }
    }

    public static class GUI {
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
            return selected;
        }

        private void refresh() {
            frame.revalidate();
            frame.repaint();
        }
    }
}
