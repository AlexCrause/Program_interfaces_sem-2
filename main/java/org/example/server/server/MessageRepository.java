package org.example.server.server;

/**
 * Интерфейс для работы с репозиторием сообщений.
 */
public interface MessageRepository {
    void saveInLog(String message);
    String readLog();
}
