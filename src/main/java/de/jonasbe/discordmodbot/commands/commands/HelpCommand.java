package de.jonasbe.discordmodbot.commands.commands;

import java.awt.Color;

import de.jonasbe.discordmodbot.Main;
import de.jonasbe.discordmodbot.commands.util.CommandExecutor;
import de.jonasbe.discordmodbot.commands.util.RoleUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand implements CommandExecutor {

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {

        event.getChannel().sendMessageEmbeds(
                getGeneralHelp(),
                getCommandHelp("help", event.getGuild(), false),
                getCommandHelp("delete", event.getGuild(), false),
                getCommandHelp("kick", event.getGuild(), false),
                getCommandHelp("ban", event.getGuild(), false)


        ).queue();
    }

    public MessageEmbed getCommandHelp(String commandString, Guild guild, boolean showVersion) {

        EmbedBuilder builder = new EmbedBuilder();

        builder.setColor(Color.PINK);

        // Title (Command)
        builder.setTitle(Main.getProps().getProperty(commandString + "HelpTitle"));

        // Description
        builder.addField(new Field("Description",
                Main.getProps().getProperty(commandString + "HelpDescription"), false));

        // Permission
        StringBuilder whitelistedRoles = new StringBuilder();
        for (Role role : RoleUtil.getRolesByString(commandString + "Permission", guild)) {
            whitelistedRoles.append("- " + role.getName() + "\n");
        }

        if (!whitelistedRoles.toString().isEmpty()) {
            builder.addField(new Field("Permission", whitelistedRoles.toString(), false));
        } else {
            builder.addField(new Field("Permission", "- everybody", false));

        }


        // Sub title (Version)
        if (showVersion) {
            builder.setFooter("[Version]: " + Main.getProps().getProperty("version"));
        }

        return builder.build();
    }

    public MessageEmbed getGeneralHelp() {

        EmbedBuilder builder = new EmbedBuilder();

        builder.setColor(Color.ORANGE);

        // Title (Command)
        builder.setTitle("HELP");

        // Description
        builder.addField(new Field("Description",
                Main.getProps().getProperty("HelpDescription"), false));

        // Coded by
        builder.addField(new Field("*Coded by Jonas*",
                "", false));

        // Sub title (Version)
        builder.setFooter("[Version]: " + Main.getProps().getProperty("version"));

        return builder.build();
    }
}
