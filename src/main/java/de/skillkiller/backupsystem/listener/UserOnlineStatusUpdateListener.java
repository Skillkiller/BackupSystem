package de.skillkiller.backupsystem.listener;

import de.skillkiller.backupsystem.util.Message;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skillkiller on 18.08.2017.
 */
public class UserOnlineStatusUpdateListener extends ListenerAdapter implements Runnable{

    static UserOnlineStatusUpdateEvent pevent = null;
    static String commandChannel = "308153679716941825";
    static String informChannel = "324538494729060355";

    @Override
    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {

        pevent = event;

        ArrayList<String> target = new ArrayList<>();
        target.add("98719514908188672");
        target.add("272336949841362944");
        target.add("323587299617275904");

        User user = event.getUser();
        if (target.contains(user.getId())) {
            if (user.getMutualGuilds().get(0).getMember(user).getOnlineStatus() == OnlineStatus.OFFLINE) {
                Message.sendInfo(event.getJDA().getTextChannelById(informChannel), "Target offline", "Der User " + user.getName() + " ging gerade offline...");
                //Tests werden ausgeführt
                new Thread(new UserOnlineStatusUpdateListener()).start();

            }

        }


    }

    @Override
    public void run() {
        net.dv8tion.jda.core.entities.Message message = pevent.getJDA().getTextChannelById(commandChannel).sendMessage("-ping").complete();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<net.dv8tion.jda.core.entities.Message> history = new MessageHistory(pevent.getJDA().getTextChannelById(commandChannel)).retrievePast(10).complete();

        boolean found, search;
        search = true;
        found = false;
        int i = 0;
        while (search && i <= history.size() && !found) {
            if (history.get(0).getAuthor().getId().equals("272336949841362944")) {
                found = true;
            }
            if (history.get(0).getId().equals(message.getId())) {
                search = false;
            }
            i++;
        }

        if(found) {
            Message.sendInfo(pevent.getJDA().getTextChannelById(informChannel), "Master Bot noch online !");
        } else {
            Message.sendInfo(pevent.getJDA().getTextChannelById(informChannel), "Master Bot offline :x:");
        }

    }
}
