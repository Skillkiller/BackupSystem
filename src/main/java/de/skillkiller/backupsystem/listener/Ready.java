package de.skillkiller.backupsystem.listener;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Map;

/**
 * Created by Skillkiller on 09.09.2017.
 */
public class Ready extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Es werden folgende Bots überwacht:");

        for (Map.Entry map: UserOnlineStatusUpdateListener.targetHashMap.entrySet() ) {
            System.out.println(" - " + event.getJDA().getUserById(map.getKey().toString()).getName());
        }
    }
}
