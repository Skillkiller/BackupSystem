package de.skillkiller.backupsystem.target;

import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;

import java.io.IOException;

/**
 * Created by Skillkiller on 18.08.2017.
 */
public class zekroBot implements Target {

    UserOnlineStatusUpdateEvent event;
    String targetID;

    @Override
    public String getInformChannel() {
        return "333707981155729410";
    }

    @Override
    public String getCommandChannel() {
        return "308153679716941825";
    }

    @Override
    public String getCommand() {
        return "-ping";
    }

    @Override
    public Process startProcess() throws IOException {
        return Runtime.getRuntime().exec("cmd.exe /c help");
    }


}
