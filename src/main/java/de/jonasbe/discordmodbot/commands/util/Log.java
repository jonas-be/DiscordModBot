package de.jonasbe.discordmodbot.commands.util;

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
    }

    public static void audit(String msg) {
        System.out.println(getPrefix("AUDIT") + msg);
    }

}
