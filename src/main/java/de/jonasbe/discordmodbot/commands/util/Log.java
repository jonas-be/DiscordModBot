package de.jonasbe.discordmodbot.commands.util;

import de.jonasbe.discordmodbot.Main;
import net.dv8tion.jda.api.entities.TextChannel;

public class Log {

    private static String getPrefix(String type) {
        return "[" + type + "]: ";
    }



    public static void error(String msg) {
        System.out.println(getPrefix("ERROR") + msg);
    }

    public static void warn(String msg) {
        System.out.println(getPrefix("WARN") + msg);
    }

    public static void info(String msg) {
        System.out.println(getPrefix("INFO") + msg);
        log("audit", "[INFO]:\n" + msg);
    }

    public static void audit(String msg) {
        System.out.println(getPrefix("AUDIT") + msg);
        log("audit", "[AUDIT]:\n" + msg);
    }

    private static void log(String channel, String msg) {

        String channelIdDefaultLog = (String) Main.getProps().get("log");
        String channelId = (String) Main.getProps().get(channel);

        try {
            TextChannel textChannel = Main.getJDA().getTextChannelById(channelId);
            textChannel.sendMessage(msg).queue();
        } catch (IllegalArgumentException illegalArgumentException) {
            try {
                TextChannel textChannel = Main.getJDA().getTextChannelById(channelIdDefaultLog);
                textChannel.sendMessage(msg).queue();
            } catch (IllegalArgumentException illegalArgumentException2) {
                throw new RuntimeException("Default LOG channel not found");
            }


        }




    }

}
