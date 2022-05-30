package de.jonasbe.discordmodbot.commands.commands;

import de.jonasbe.discordmodbot.commands.util.CommandExecutor;
import de.jonasbe.discordmodbot.commands.util.Log;
import de.jonasbe.discordmodbot.commands.util.RoleUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class BanCommand implements CommandExecutor {

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        try {

            // The Member can't be kicked because one of the Roles is not allowed to get kicked
            if (RoleUtil.hasOneOfTheseRoles(event.getMessage().getMentionedMembers().get(0),
                    "banNotRole", event.getGuild())) {
                Log.info(String.format(
                        "\"%s\" try to ban \"%s\". But \"%s\" can't be banned because, one of his Roles is not allowed to get banned.",
                        event.getMember().getUser().getAsTag(),
                        event.getMessage().getMentionedMembers().get(0).getUser().getAsTag(),
                        event.getMessage().getMentionedMembers().get(0).getUser().getAsTag()));
                return;
            }

            // ban member
            event.getMessage().getMentionedMembers().get(0).ban(0).queue();
            Log.audit(String.format("\"%s\" banned \"%s\".",
                    event.getMember().getUser().getAsTag(),
                    event.getMessage().getMentionedMembers().get(0).getUser().getAsTag()));


            // No one mentioned
        } catch (IndexOutOfBoundsException ignored) {
            Log.info(String.format("\"%s\" try to ban someone. But nobody was mentioned.",
                    event.getMember().getUser().getAsTag()));
        }
    }
}
