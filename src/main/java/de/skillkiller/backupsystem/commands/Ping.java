package de.skillkiller.backupsystem.commands;

import de.skillkiller.backupsystem.util.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * Created by Skillkiller on 18.08.2017.
 */
public class Ping implements Command {
    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) {
        Message.sendSuccess(event.getChannel(), "Antwort", "Der Ping beträgt: ``" + event.getJDA().getPing() + "ms``");
    }

    @Override
    public String getHelp() {
        return "``./ping``";
    }

    @Override
    public String getDescription() {
        return "Gibt den Ping vom Bot zurück.";
    }

    @Override
    public int getPermission() {
        return 0;
    }
}
