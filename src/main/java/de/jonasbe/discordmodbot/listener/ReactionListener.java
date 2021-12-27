package de.jonasbe.discordmodbot.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import org.jetbrains.annotations.NotNull;

public class ReactionListener extends ListenerAdapter {

//    @Override
//    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent e) {
//        Member reactor = e.getMember();
//
//        MessageReaction.ReactionEmote emote = e.getReaction().getReactionEmote();
//
//        System.out.println(reactor + " : " + emote.getName() + " | " + emote.getAsCodepoints());
//
//        switch (emote.getAsCodepoints()) {
//            case "U+1f602": // Equals 😂
//                Role joyRole = e.getGuild().getRolesByName("*joy*", true).get(0);
//
//                e.getGuild().addRoleToMember(reactor, joyRole).queue();
//                break;
//        }
//
//        if (emote.getAsCodepoints().equals("U+1f602")) {
//            System.out.printf("Equals 😂");
//
//
//        }
//
//    }
}
