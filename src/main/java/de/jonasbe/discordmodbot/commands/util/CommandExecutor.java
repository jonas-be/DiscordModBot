package de.jonasbe.discordmodbot.commands.util;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandExecutor {
    void onCommand(MessageReceivedEvent event, String[] args);
}
