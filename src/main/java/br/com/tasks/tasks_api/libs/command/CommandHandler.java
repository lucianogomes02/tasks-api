package br.com.tasks.tasks_api.libs.command;

import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    public void handle(Command command) {
        command.execute();
    }
}
