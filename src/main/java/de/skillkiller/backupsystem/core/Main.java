package de.skillkiller.backupsystem.core;

import de.skillkiller.backupsystem.commands.Ping;
import de.skillkiller.backupsystem.commands.run;
import de.skillkiller.backupsystem.commands.stop;
import de.skillkiller.backupsystem.commands.targetCMD;
import de.skillkiller.backupsystem.listener.GuildMessageListener;
import de.skillkiller.backupsystem.listener.Ready;
import de.skillkiller.backupsystem.listener.UserOnlineStatusUpdateListener;
import de.skillkiller.backupsystem.target.Channelbot;
import de.skillkiller.backupsystem.target.Knecht;
import de.skillkiller.backupsystem.target.zekroBot;
import de.skillkiller.backupsystem.util.Settings;
import de.skillkiller.backupsystem.util.Static;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Skillkiller on 17.08.2017.
 */
public class Main {

    private static JDABuilder builder;

    public static void main(String[] args) {
        Settings settings = new Settings();
        builder = new JDABuilder(AccountType.BOT);
        registerCommands();
        registerListeners();
        registerTargets();
        try {
            builder.setToken(settings.getSet("token", "Token angeben!"))
                    .setGame(Game.of("Watching Targets | " + Static.version))
                    .setStatus(OnlineStatus.IDLE);
            JDA jda = builder.buildBlocking();
            setStatus(jda);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    private static void registerListeners() {
        builder.addEventListener(new GuildMessageListener());
        builder.addEventListener(new UserOnlineStatusUpdateListener());
        builder.addEventListener(new Ready());
    }

    private static void registerCommands() {
        CommandParser.register("ping", new Ping());
        CommandParser.register("run", new run());
        CommandParser.register("stop", new stop());
        CommandParser.register("target", new targetCMD());
    }

    private static void registerTargets() {
        UserOnlineStatusUpdateListener.targetHashMap.put("272336949841362944", new zekroBot());
        UserOnlineStatusUpdateListener.targetHashMap.put("323587299617275904", new Knecht());
        UserOnlineStatusUpdateListener.targetHashMap.put("328643704619401217", new Channelbot());
    }


    public static void setStatus(JDA jda) {
        boolean allRight = true;
        int offline = 0;

        for (Map.Entry map: UserOnlineStatusUpdateListener.targetHashMap.entrySet() ) {
            User user = jda.getUserById(map.getKey().toString());
            if( user.getMutualGuilds().get(0).getMember(user).getOnlineStatus() == OnlineStatus.OFFLINE) {
                allRight = false;
                offline++;
            }
        }

        if(allRight) {
            jda.getPresence().setStatus(OnlineStatus.IDLE);
            jda.getPresence().setGame(Game.of("Watching Targets | " + Static.version));
        } else {
            jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
            jda.getPresence().setGame(Game.of("Exception: " + offline + (offline > 1 ? " Bots " : " Bot ") + " offline | " + Static.version));
        }
    }
}
