import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch extends JFrame {

    private boolean running = false;
    private int count = 0;
    private JLabel countLabel;

    public Stopwatch() {
        setTitle("Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 100);
        setLayout(new FlowLayout());

        countLabel = new JLabel("0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(countLabel);

        JButton startPauseButton = new JButton("Start");
        startPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!running) {
                    startPauseButton.setText("Pause");
                    startTimer();
                } else {
                    startPauseButton.setText("Resume");
                    stopTimer();
                }
            }
        });
        add(startPauseButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startPauseButton.setText("Start");
                count = 0;
                countLabel.setText("0");
                stopTimer();
            }
        });
        add(clearButton);
    }

    private void startTimer() {
        running = true;
        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1000);
                    count++;
                    SwingUtilities.invokeLater(() -> {
                        countLabel.setText(Integer.toString(count));
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void stopTimer() {
        running = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Stopwatch().setVisible(true);
        });
    }
}
