package de.jonasbe.discordmodbot;

import de.jonasbe.discordmodbot.commands.commands.DeleteCommand;
import de.jonasbe.discordmodbot.commands.commands.HelpCommand;
import de.jonasbe.discordmodbot.commands.commands.KickCommand;
import de.jonasbe.discordmodbot.commands.util.CommandHandler;
import de.jonasbe.discordmodbot.commands.util.Log;
import de.jonasbe.discordmodbot.listener.JoinListener;
import de.jonasbe.discordmodbot.listener.ReactionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {

    private static JDA jda;
    private static CommandHandler commandHandler;

    private static Properties confProps;

    private static String cmdPrefix = "!";


    public static void main(String[] args) {
        try {
            commandHandler = new CommandHandler();

            jda =
                    JDABuilder
                            .create(args[0],
                                    GatewayIntent.GUILD_MEMBERS,
                                    GatewayIntent.GUILD_MESSAGE_REACTIONS,
                                    GatewayIntent.GUILD_MESSAGES)


                            // Add Listeners
                            .addEventListeners(new JoinListener())
                            .addEventListeners(new ReactionListener())

                            .addEventListeners(commandHandler)

                            .build().awaitReady();

        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }

        // Set activity (like "playing Something")
        jda.getPresence().setActivity(Activity.streaming("SirF4wke2", "https://www.twitch.tv/sirf4wke2"));

        commandHandler.registerCommand(new String[]{"del", "delete"}, new DeleteCommand(), "deletePermission");
        commandHandler.registerCommand("help", new HelpCommand());
        commandHandler.registerCommand("kick", new KickCommand(), "kickPermission");
        commandHandler.registerCommand("ban", new KickCommand(), "banPermission");


        loadProps();


    }

    public static void loadProps() {
        System.out.println("Loading Config...");

        String rootPath = "";
        String appConfigPath = rootPath + "conf.properties";

        confProps = new Properties();

        try {
            confProps.load(new FileInputStream(appConfigPath));

            Log.info("Loaded Version: " + confProps.get("version"));

        } catch (FileNotFoundException notFoundException) {
            Log.error("ConfigFile can not loaded!");

        } catch (IOException e) {
            Log.error(e.getStackTrace().toString());
        }
    }

    public static Properties getProps() {
        return confProps;
    }

    public static JDA getJDA() {
        return jda;
    }
}