package org.example.server;

import org.example.server.client.ClientController;
import org.example.server.client.ClientGUI;
import org.example.server.server.ServerController;
import org.example.server.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        //создание объектов сервера и создание связи между ними
        ServerWindow serverWindow = new ServerWindow();
        ServerController serverController = new ServerController();
        serverController.setServerView(serverWindow);
        serverWindow.setServerController(serverController);

        //создание объектов клиента1 и создание связи между ними
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClient(clientController1);
        clientController1.setServer(serverController);

        //создание объектов клиента2 и создание связи между ними
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClient(clientController2);
        clientController2.setServer(serverController);
    }
}
