package de.skillkiller.backupsystem.commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * Created by Skillkiller on 17.08.2017.
 */
public interface Command {

    void action(String[] args, GuildMessageReceivedEvent event);

    String getHelp();

    String getDescription();

    int getPermission();

}
