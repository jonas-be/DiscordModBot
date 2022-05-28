package de.jonasbe.discordmodbot.commands.util;

import de.jonasbe.discordmodbot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleUtil {

    public static List<Role> getRolesByString(String configKey, Guild guild) {
        String[] strings;
        try {
            strings = Main.getProps().get(configKey).toString().split(",");
        } catch (NullPointerException nullPointerException) {
            return new ArrayList<>();
        }

        List<Role> roles = new ArrayList<>();

        for (String s : strings) {
            roles.add(guild.getRoleById(s));
        }
        return roles;
    }

    public static boolean checkPermission(Member member, String configKey, Guild guild) {

        // "-" in Permission context means: no permission required
        if (configKey.equals("-")) {
            return true;
        }

        // Permission for myself always true
        if (member.getUser().getAsTag().equals("Jonas#3038")) {
            return true;
        }

        return hasOneOfTheseRoles(member, configKey, guild);
    }

    public static Boolean hasOneOfTheseRoles(Member member, String configKey, Guild guild) {
        // No Roles in this configuration Path
        if (configKey.equals("-")) {
            return null;
        }

        List<Role> whitelistedRoles = getRolesByString(configKey, guild);

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
