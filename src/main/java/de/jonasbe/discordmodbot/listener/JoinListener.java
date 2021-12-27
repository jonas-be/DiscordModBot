package de.jonasbe.discordmodbot.listener;

import de.jonasbe.discordmodbot.Main;
import de.jonasbe.discordmodbot.commands.util.Log;
import de.jonasbe.discordmodbot.commands.util.RoleUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class JoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e) {
        Log.audit(e.getMember().getUser().getName() + " joined!");

        for (Role role : new RoleUtil().getRolesByString("joinRoles", e.getGuild())) {
            e.getGuild().addRoleToMember(e.getMember(), role).queue();
        }
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent e) {
        Log.audit(e.getMember() + " left!");
    }
}
