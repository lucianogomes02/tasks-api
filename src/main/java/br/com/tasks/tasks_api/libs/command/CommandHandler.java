package br.com.tasks.tasks_api.libs.command;

import org.springframework.stereotype.Service;

@Service
public class CommandHandler {
    public void handle(Command command) {
        command.execute();
    }
}
