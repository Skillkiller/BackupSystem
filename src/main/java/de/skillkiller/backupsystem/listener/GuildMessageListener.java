package de.skillkiller.backupsystem.listener;

import de.skillkiller.backupsystem.core.CommandParser;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static de.skillkiller.backupsystem.util.Static.PREFIX;

/**
 * Created by Skillkiller on 17.08.2017.
 */
public class GuildMessageListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // Parse & Handle Command
        if (event.getMessage().getContent().startsWith(PREFIX) &&
                !event.getAuthor().equals(event.getJDA().getSelfUser())) {
            CommandParser.handleCommand(CommandParser.parse(event));
        }
    }
}
