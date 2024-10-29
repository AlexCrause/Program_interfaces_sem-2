package org.example.server.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    ServerController serverController;
    JButton btnStart, btnStop;
    JTextArea log;
    boolean work;

    public ServerWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (work){
                    serverController.appendLog("Сервер уже был запущен");
                } else {
                    work = true;
                    serverController.appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!work){
                    serverController.appendLog("Сервер уже был остановлен");
                } else {
                    work = false;
                    while (!serverController.clients.isEmpty()){
                        serverController.disconnectUser(serverController.clients.get(serverController.clients.size()-1));
                    }
                    serverController.appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public boolean isWork() {
        return work;
    }
}
