package de.jonasbe.discordmodbot.commands.util;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandExecutor {
    void onCommand(MessageReceivedEvent event, String[] args);
}
