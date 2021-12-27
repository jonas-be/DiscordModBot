package de.jonasbe.discordmodbot.commands.util;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.utils.tuple.Pair;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandHandler extends ListenerAdapter {

    private static final String prefix = "!";
    private final HashMap<String, Pair<CommandExecutor, String>> commandMap = new HashMap<>();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String content = event.getMessage().getContentRaw();
        if (!content.toLowerCase().startsWith(prefix)) {
            return;
        }
        if (content.length() <= prefix.length()) {
            return;
        }

        String command = content.substring(prefix.length()).toLowerCase().split(" ")[0];
        if (!commandMap.containsKey(command)) {
            return;
        }

        String[] args = new String[0];
        if (content.split(" ", 2).length > 1) {
            args = content.substring(prefix.length() + command.length() + 1).split(" ");
        }

        // Check Permission

        if (RoleUtil.checkPermission(
                event.getMember(), commandMap.get(command).getRight(), event.getGuild())) {
            commandMap.get(command).getLeft().onCommand(event, args);
        } else {
            event.getChannel().sendMessage("No Permission!").queue();
        }
    }

    public void registerCommand(String[] commands, CommandExecutor executor) {
        for (String command : commands) {
            commandMap.put(command.toLowerCase(), Pair.of(executor, "-"));
        }
    }

    public void registerCommand(String command, CommandExecutor executor) {
        commandMap.put(command.toLowerCase(), Pair.of(executor, "-"));
    }

    /**
     * With Permission
     */
    public void registerCommand(String[] commands, CommandExecutor executor, String configKey) {
        for (String command : commands) {
            commandMap.put(command.toLowerCase(), Pair.of(executor, configKey));
        }
    }

    public void registerCommand(String command, CommandExecutor executor, String configKey) {
        commandMap.put(command.toLowerCase(), Pair.of(executor, configKey));
    }
}
