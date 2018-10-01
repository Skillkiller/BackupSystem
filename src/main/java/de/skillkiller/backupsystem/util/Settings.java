package de.skillkiller.backupsystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Skillkiller on 19.08.2017.
 */
public class Settings {


    private File f;
    private Properties p;


    public Settings() {
        this.f = new File(System.getProperty("user.dir") + "/BackupBot/config.txt");
        if(!f.exists()) {
            new File(f.getAbsolutePath().replace(f.getName(), "")).mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.p = new Properties();
        try {
            p.load(new FileInputStream(this.f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return p.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        return p.getProperty(key, defaultValue);
    }

    public void set(String key, String value) {
        p.setProperty(key, value);
    }

    public String getSet(String key, String defaultValue) throws IOException {
        if(get(key) == null) {
            set(key, defaultValue);
            saveFile();
            return null;
        } else {
            return get(key);
        }


    }

    public Properties getProperties() {
        return p;
    }

    public void saveFile() throws IOException {
        p.store(new FileOutputStream(f), f.getName());
    }

}
