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


        event.getChannel().sendMessageEmbeds(getCommandHelp("delete", event.getGuild())).queue();
    }

    public MessageEmbed getCommandHelp(String commandString, Guild guild) {

        EmbedBuilder builder = new EmbedBuilder();


        builder.setColor(Color.PINK);

        builder.setTitle("HELP:\t " + Main.getProps().getProperty(commandString + "HelpTitle"));

        builder.addField(new Field("Description",
                Main.getProps().getProperty(commandString + "HelpDescription"), false));

        StringBuilder whitelistedRoles = new StringBuilder();
        for (Role role: RoleUtil.getRolesByString(commandString + "Permission", guild)) {
            whitelistedRoles.append("- " + role.getName() + "\n");
        }

        builder.addField(new Field("Permission", whitelistedRoles.toString(), false));


        builder.setFooter("[Version]: " + Main.getProps().getProperty("version"));

        return builder.build();
    }
}
