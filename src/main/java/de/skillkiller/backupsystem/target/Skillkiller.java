package de.skillkiller.backupsystem.target;

import java.io.IOException;

/**
 * Created by Skillkiller on 19.08.2017.
 */
public class Skillkiller implements Target {
    @Override
    public String getInformChannel() {
        return "324538494729060355";
    }

    @Override
    public String getCommandChannel() {
        return "308153679716941825";
    }

    @Override
    public String getCommand() {
        return "-online?";
    }

    @Override
    public Process startProcess() throws IOException {
        return Runtime.getRuntime().exec("cmd.exe /c time /T");
    }
}
