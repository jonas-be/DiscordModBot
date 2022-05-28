package de.jonasbe.discordmodbot.commands.commands;

import java.util.ArrayList;
import java.util.List;

import de.jonasbe.discordmodbot.commands.util.CommandExecutor;
import de.jonasbe.discordmodbot.commands.util.Log;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DeleteCommand implements CommandExecutor {

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {

        if (args.length != 1 || args[0].isEmpty()) {
            return;
        }

        List<Message> messages;
        try {
            messages = e.getChannel().getHistory().retrievePast(Integer.parseInt(args[0]) + 1).complete();

        } catch (NumberFormatException Ignored) {
            // Input not a valid number
            Log.info(String.format("\"%s\" try to delete something. But the input was a invalid number.",
                    e.getMember().getUser().getAsTag()));
            return;

        } catch (IllegalArgumentException illegalArgumentException) {
            // Input to high
            Log.info(String.format("\"%s\" try to delete something. But the input was to high.",
                    e.getMember().getUser().getAsTag()));
            return;
        }


        e.getTextChannel().purgeMessages(messages);

        String deleted = "";
        for (Message m : messages) {
            deleted = deleted + " -> " + m.getContentRaw() + "\n";
        }
        Log.audit(String.format("\"%s\" deleted: \n%s",
                e.getMember().getUser().getAsTag(),
                deleted));

    }

}
