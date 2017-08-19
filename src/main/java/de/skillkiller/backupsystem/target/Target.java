package de.skillkiller.backupsystem.target;

import java.io.IOException;

/**
 * Created by Skillkiller on 18.08.2017.
 */
public interface Target{

    String getInformChannel();
    String getCommandChannel();
    String getCommand();
    Process startProcess() throws IOException;


}
