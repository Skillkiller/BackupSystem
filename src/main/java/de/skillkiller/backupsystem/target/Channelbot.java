package de.skillkiller.backupsystem.target;

import java.io.IOException;

/**
 * Created by Skillkiller on 19.08.2017.
 */
public class Channelbot implements Target {
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
        return "/autochannel";
    }

    @Override
    public String startProcessCMD() throws IOException {
        return "./start.sh";
    }
}
