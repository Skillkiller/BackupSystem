package de.skillkiller.backupsystem.commands;

import de.skillkiller.backupsystem.util.Message;
import de.skillkiller.backupsystem.util.Settings;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * Created by Skillkiller on 30.08.2017.
 */
public class stop implements Command {
    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) {
        Settings settings = new Settings();
        if(event.getMessage().getChannel().getId().equals("333707981155729410")) {
            event.getMessage().getChannel().sendMessage("Der Bot fährt sich nun herunter!\nBefehl von "  + event.getAuthor().getName()).complete();

            event.getJDA().shutdown();
            System.exit(1);
        } else {
            Message.sendError(event.getChannel(), ":x: Fehler", "Deine Rechte sind nicht aussreichend genug für den Befehl!");
        }
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getPermission() {
        return 0;
    }
}
