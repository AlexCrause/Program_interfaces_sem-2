package org.example.server.server;

import org.example.server.client.ClientController;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер сервера, отвечающий за управление клиентами и обработку сообщений.
 */
public class ServerController {
    List<ClientController> clients;
    private FileMessageRepository fileMessageRepository;
    ServerWindow serverWindow;
    ClientController clientController;

    public ServerController() {
        clients = new ArrayList<>();
        fileMessageRepository = new FileMessageRepository();
        clientController = new ClientController();
    }

    public boolean connectUser(ClientController client) {
        if (serverWindow.isWork() == false) {
            return false;
        }
        clients.add(client);
        return true;
    }

    public void disconnectUser(ClientController client){
        clients.remove(client);
        if (client != null){
            client.disconnectedFromServer();
        }
    }

    public void message(String text){
        if (serverWindow.isWork() == false){
            return;
        }
        text += "";
        appendLog(text);
        answerAll(text);
        fileMessageRepository.saveInLog(text);
    }

    void appendLog(String text){
        serverWindow.log.append(text + "\n");
    }

    void answerAll(String text){
        for (ClientController client: clients){
            client.answerFromServer(text);
        }
    }

    public String getHistory() {
        return fileMessageRepository.readLog();
    }

    public void setServerView(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }
}
