package de.skillkiller.backupsystem.util;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;

/**
 * Created by Skillkiller on 18.08.2017.
 */
public class Message {

    static final String FOOTER = "BackupSystem";
    static final String FOOTERURL = "https://www.iconfinder.com/icons/763475/download/png/128";

    //Info Message
    public static void sendInfo(TextChannel channel, String message) {
        channel.sendMessage(new EmbedBuilder().setColor(Color.ORANGE).setFooter(FOOTER, FOOTERURL).setDescription(message).build()).queue();
    }

    public static void sendInfo(TextChannel channel, String title, String message) {
        channel.sendMessage(new EmbedBuilder().setColor(Color.ORANGE).setFooter(FOOTER, FOOTERURL).setDescription(message).setTitle(title).build()).queue();
    }

    public static void sendInfo(TextChannel channel, EmbedBuilder embedBuilder) {
        channel.sendMessage(embedBuilder.setColor(Color.ORANGE).setFooter(FOOTER, FOOTERURL).build()).queue();
    }

    //Error Message
    public static void sendError(TextChannel channel, String message) {
        channel.sendMessage(new EmbedBuilder().setColor(Color.RED).setFooter(FOOTER, FOOTERURL).setDescription(message).build()).queue();
    }

    public static void sendError(TextChannel channel, String title, String message) {
        channel.sendMessage(new EmbedBuilder().setColor(Color.RED).setFooter(FOOTER, FOOTERURL).setDescription(message).setTitle(title).build()).queue();
    }

    public static void sendError(TextChannel channel, EmbedBuilder embedBuilder) {
        channel.sendMessage(embedBuilder.setColor(Color.RED).setFooter(FOOTER, FOOTERURL).build()).queue();
    }

    //Success Message
    public static void sendSuccess(TextChannel channel, String message) {
        channel.sendMessage(new EmbedBuilder().setColor(Color.GREEN).setFooter(FOOTER, FOOTERURL).setDescription(message).build()).queue();
    }

    public static void sendSuccess(TextChannel channel, String title, String message) {
        channel.sendMessage(new EmbedBuilder().setColor(Color.GREEN).setFooter(FOOTER, FOOTERURL).setDescription(message).setTitle(title).build()).queue();
    }

    public static void sendSuccess(TextChannel channel, EmbedBuilder embedBuilder) {
        channel.sendMessage(embedBuilder.setColor(Color.GREEN).setFooter(FOOTER, FOOTERURL).build()).queue();
    }

    public static void sendRaw(TextChannel channel, String message) {
        channel.sendMessage(message).queue();
    }
}
