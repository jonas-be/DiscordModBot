package de.jonasbe.discordmodbot.commands.commands;

import de.jonasbe.discordmodbot.commands.util.CommandExecutor;
import de.jonasbe.discordmodbot.commands.util.Log;
import de.jonasbe.discordmodbot.commands.util.RoleUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class KickCommand implements CommandExecutor {

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        try {

            // The Member can't be kicked because one of the Roles is not allowed to get kicked
            if (RoleUtil.hasOneOfTheseRoles(event.getMessage().getMentionedMembers().get(0),
                    "kickNotRole", event.getGuild())) {
                Log.info(String.format(
                        "\"%s\" try to kick \"%s\". But \"%s\" can't be kicked because, one of his Roles is not allowed to get kicked.",
                        event.getMember().getUser().getAsTag(),
                        event.getMessage().getMentionedMembers().get(0).getUser().getAsTag(),
                        event.getMessage().getMentionedMembers().get(0).getUser().getAsTag()));
                return;
            }

            // kick member
            event.getMessage().getMentionedMembers().get(0).kick().queue();
            Log.audit(String.format("\"%s\" kicked \"%s\".",
                    event.getMember().getUser().getAsTag(),
                    event.getMessage().getMentionedMembers().get(0).getUser().getAsTag()));


            // No one mentioned
        } catch (IndexOutOfBoundsException ignored) {
            Log.info(String.format("\"%s\" try to kick someone. But nobody was mentioned.",
                    event.getMember().getUser().getAsTag()));
        }
    }
}
