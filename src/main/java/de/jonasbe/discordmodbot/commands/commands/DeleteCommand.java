package de.jonasbe.discordmodbot.commands.commands;

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

        try {
            Log.audit(e.getMember().getUser().getName() + " deleted:");
            for (Message m : e.getChannel().getHistory().retrievePast(Integer.parseInt(args[0]) + 1).complete()) {
                m.delete().queue();
                Log.audit("   -> " + m.getContentRaw());
            }

        } catch (NumberFormatException Ignored) {}

    }

}
