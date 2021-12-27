package de.jonasbe.discordmodbot.commands.util;

import de.jonasbe.discordmodbot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleUtil {

    public static List<Role> getRolesByString(String configKey, Guild guild) {
        String[] strings = Main.getProps().get(configKey).toString().split(",");

        List<Role> roles = new ArrayList<>();

        for (String s : strings) {
            roles.add(guild.getRoleById(s));
        }
        return roles;
    }

    public static boolean checkPermission(Member member, String configKey, Guild guild) {
        if (configKey.equals("-")) {
            return true;
        }

        List<Role> whitelistedRoles = getRolesByString(configKey, guild);

        // Permission for myself always true
        if (member.getUser().equals(Main.getJDA().getUserById("398876120696619008"))) {
            return true;
        }

        try {
            for (Role role : whitelistedRoles) {
                if (member.getRoles().contains(role)) {
                    return true;
                }
            }
        } catch (NullPointerException nullPointerException) {
            return false;

        }

        return false;
    }
}
