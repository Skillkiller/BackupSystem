package de.skillkiller.backupsystem.commands;

import de.skillkiller.backupsystem.listener.UserOnlineStatusUpdateListener;
import de.skillkiller.backupsystem.target.Target;
import de.skillkiller.backupsystem.util.Message;
import de.skillkiller.backupsystem.util.Settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

/**
 * Created by Skillkiller on 30.08.2017.
 */
public class targetCMD implements Command {
    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) {
        Settings settings = new Settings();
        try {
            if (event.getAuthor().getId().equals(settings.getSet("botowner", "98719514908188672"))) {
                if(args.length == 1) {
                    if (!UserOnlineStatusUpdateListener.targetHashMap.containsKey(args[0])) {
                        Message.sendError(event.getChannel(), ":x: Falsche ID", "Die angegebene ID ist kein Target");
                        return;
                    } else {
                        Target target = UserOnlineStatusUpdateListener.targetHashMap.get(args[0]);
                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.addField("InformChannel", event.getJDA().getTextChannelById(target.getInformChannel()).getAsMention(), false);
                        embedBuilder.addField("CommandChannel", event.getJDA().getTextChannelById(target.getCommandChannel()).getAsMention(), false);
                        embedBuilder.addField("Command", "``" + target.getCommand() + "``", false);
                        embedBuilder.addField("Start Command", "``" + target.startProcessCMD() + "``", false);
                        Message.sendInfo(event.getChannel(), embedBuilder);
                    }
                }


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
