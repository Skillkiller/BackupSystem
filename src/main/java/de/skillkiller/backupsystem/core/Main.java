package de.skillkiller.backupsystem.core;

import de.skillkiller.backupsystem.commands.Ping;
import de.skillkiller.backupsystem.commands.run;
import de.skillkiller.backupsystem.commands.stop;
import de.skillkiller.backupsystem.listener.GuildMessageListener;
import de.skillkiller.backupsystem.listener.UserOnlineStatusUpdateListener;
import de.skillkiller.backupsystem.target.Channelbot;
import de.skillkiller.backupsystem.util.Settings;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.IOException;

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
                    .setAutoReconnect(true)
                    .setGame(Game.of("Testing System"))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB);
            builder.buildBlocking();
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
    }

    private static void registerCommands() {
        CommandParser.register("ping", new Ping());
        CommandParser.register("run", new run());
        CommandParser.register("stop", new stop());
    }

    private static void registerTargets() {
        //UserOnlineStatusUpdateListener.targetHashMap.put("272336949841362944", new zekroBot());
        //UserOnlineStatusUpdateListener.targetHashMap.put("323587299617275904", new Knecht());
        UserOnlineStatusUpdateListener.targetHashMap.put("328643704619401217", new Channelbot());
    }
}
