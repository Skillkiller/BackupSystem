package de.skillkiller.backupsystem.commands;

import de.skillkiller.backupsystem.util.Message;
import de.skillkiller.backupsystem.util.Settings;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

/**
 * Created by Skillkiller on 30.08.2017.
 */
public class stop implements Command {
    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) {
        Settings settings = new Settings();
        try {
            if(event.getAuthor().getId().equals(settings.getSet("botowner", "98719514908188672"))) {
                Message.sendSuccess(event.getChannel(), "Der Bot fährt sich nun herunter!");
                event.getJDA().shutdown();
            } else {
                Message.sendError(event.getChannel(), ":x: Fehler", "Deine Rechte sind nicht aussreichend genug für den Befehl!");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
