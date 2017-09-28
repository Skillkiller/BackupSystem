package de.skillkiller.backupsystem.target;

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
        return "358364792614027265";
    }

    @Override
    public String getCommand() {
        return "/autochannel";
    }

    @Override
    public String startProcessCMD() {
        return "./start.sh";
    }
}
