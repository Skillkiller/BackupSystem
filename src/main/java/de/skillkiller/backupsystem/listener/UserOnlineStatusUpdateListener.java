package de.skillkiller.backupsystem.listener;

import de.skillkiller.backupsystem.target.Target;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Skillkiller on 18.08.2017.
 */
public class UserOnlineStatusUpdateListener extends ListenerAdapter implements Runnable{

    public static HashMap<String, Target> targetHashMap = new HashMap<>();
    private UserOnlineStatusUpdateEvent event;
    private User user;
    private Target target;

    @Override
    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {
        this.event = event;
        user = event.getUser();
        if (targetHashMap.containsKey(user.getId())) {
            if (user.getMutualGuilds().get(0).getMember(user).getOnlineStatus() == OnlineStatus.OFFLINE) {
                target = targetHashMap.get(user.getId());
                new Thread(this).start();
            }
        }
    }

    @Override
    public void run() {
        Message message = event.getJDA().getTextChannelById(target.getCommandChannel()).sendMessage(target.getCommand()).complete();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Message> history = new MessageHistory(event.getJDA().getTextChannelById(target.getCommandChannel())).retrievePast(10).complete();

        boolean found, search;
        search = true;
        found = false;
        int i = 0;
        while (search && i <= history.size() && !found) {
            if (history.get(0).getAuthor().getId().equals(user.getId())) {
                found = true;
            }
            if (history.get(0).getId().equals(message.getId())) {
                search = false;
            }
            i++;
        }

        if(found) {
            de.skillkiller.backupsystem.util.Message.sendInfo(event.getJDA().getTextChannelById(target.getInformChannel()), user.getAsMention() + " ist noch Online");
            de.skillkiller.backupsystem.util.Message.sendInfo(event.getJDA().getTextChannelById(target.getInformChannel()), "Aber der Online Status hat sich verändert... Bitte im Blick behalten @here");
        } else {
            de.skillkiller.backupsystem.util.Message.sendError(event.getJDA().getTextChannelById(target.getCommandChannel()), "Keine Antwort innerhalb des Zeitintervalls erhalten!");
            de.skillkiller.backupsystem.util.Message.sendInfo(event.getJDA().getTextChannelById(target.getInformChannel()), user.getAsMention() + " ging gerade Offline!");

            java.util.Scanner s = null;
            if(target.startProcessCMD().equals("")) {
                de.skillkiller.backupsystem.util.Message.sendInfo(event.getJDA().getTextChannelById(target.getInformChannel()), "Kein Startbefehl angegeben!");
                return;
            }
            try {
                s = new java.util.Scanner(Runtime.getRuntime().exec(target.startProcessCMD()).getInputStream()).useDelimiter("\\A");
                String outout = s.next();
                if(outout.length() > 2000) {
                    outout = outout.substring(0, 1500);
                }
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.addField("Befehl wird ausgeführt:", "```" + target.startProcessCMD() + "```", false);
                embedBuilder.addField("**Konsole**:", "```" + outout + "```", false);
                de.skillkiller.backupsystem.util.Message.sendInfo(event.getJDA().getTextChannelById(target.getInformChannel()), embedBuilder);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
