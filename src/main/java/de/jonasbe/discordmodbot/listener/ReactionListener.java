package de.jonasbe.discordmodbot.listener;

import java.util.Properties;

import de.jonasbe.discordmodbot.Main;
import de.jonasbe.discordmodbot.commands.util.Log;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;

public class ReactionListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent e) {
        Properties props = Main.getProps();

        MessageReaction.ReactionEmote emote = e.getReaction().getReactionEmote();
        Message m = e.getChannel().retrieveMessageById(props.getProperty("reactRoleMsgId")).complete();

        if (e.getMember().getUser()
                .isBot()) {
            return;
        }

        if (e.getMessageId().toString().equals(props.getProperty("reactRoleMsgId"))) {
            clanRoleReaction(props, emote, m, e);
        } else if (e.getMessageId().toString().equals(props.getProperty("reactLivePingMsgId"))) {
            giveLivePingRole(e, props);
        }
    }

    private void clanRoleReaction(Properties props, MessageReaction.ReactionEmote emote, Message m, MessageReactionAddEvent e) {
        switch (emote.getName()) {
            case "🟥":
                System.out.println("red");
                giveNewClanRole(e, props, "reactRoleIdRed");
                break;
            case "🟨":
                System.out.println("yellow");
                giveNewClanRole(e, props, "reactRoleIdYellow");
                break;
            case "🟦":
                System.out.println("blue");
                giveNewClanRole(e, props, "reactRoleIdBlue");
                break;
            case "🟩":
                System.out.println("green");
                giveNewClanRole(e, props, "reactRoleIdGreen");
                break;
        }
        m.clearReactions().queue();
        m.addReaction("🟥").queue();
        m.addReaction("🟨").queue();
        m.addReaction("🟦").queue();
        m.addReaction("🟩").queue();
    }

    private void giveLivePingRole(MessageReactionAddEvent e, Properties props) {
        Message m = e.getChannel().retrieveMessageById(props.getProperty("reactLivePingMsgId")).complete();
        Role role = e.getGuild().getRoleById(props.getProperty("reactLivePingRoleId"));

        boolean toggledOn = false;
        if (e.getMember().getRoles().contains(role)) {
            e.getGuild().removeRoleFromMember(e.getMember(), role).queue();
        } else {
            e.getGuild().addRoleToMember(e.getMember(), role).queue();
            toggledOn = true;
        }

        m.clearReactions().queue();
        m.addReaction("🔔").queue();

        Log.audit(String.format("%s hat den Live ping %s ",
                e.getMember().getEffectiveName(),
                toggledOn ? "aktiviert" : "deaktiviert"));
    }

    private void giveNewClanRole(MessageReactionAddEvent e, Properties props, String key) {
        String oldRole = null;
        String newRole;
        String[] roles = {
                props.getProperty("reactRoleIdRed"),
                props.getProperty("reactRoleIdYellow"),
                props.getProperty("reactRoleIdBlue"),
                props.getProperty("reactRoleIdGreen"),
                props.getProperty("joinRoles")
        };

        for (String role : roles) {
            Role roleTmp = e.getGuild().getRoleById(role);
            if (e.getMember().getRoles().contains(roleTmp)) {
                e.getGuild().removeRoleFromMember(e.getMember(), roleTmp).queue();
                oldRole = roleTmp.getName();
            }
        }

        e.getGuild().addRoleToMember(e.getMember(), e.getGuild().getRoleById(props.getProperty(key))).queue();
        newRole = e.getGuild().getRoleById(props.getProperty(key)).getName();

        Log.audit(String.format("%s changed his role from %s to %s",
                e.getMember().getEffectiveName(),
                oldRole,
                newRole));
    }
}
