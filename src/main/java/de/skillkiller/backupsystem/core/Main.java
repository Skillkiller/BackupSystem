package de.skillkiller.backupsystem.core;

import de.skillkiller.backupsystem.commands.Ping;
import de.skillkiller.backupsystem.listener.GuildMessageListener;
import de.skillkiller.backupsystem.listener.UserOnlineStatusUpdateListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * Created by Skillkiller on 17.08.2017.
 */
public class Main {

    private static JDABuilder builder;

    public static void main(String[] args) {
        builder = new JDABuilder(AccountType.BOT)
                //.setToken("MzM0NjcwMjA1MDkzNTQzOTM4.DHZ5ZQ.MXC5Fu1FYsFHbW4WxTuYuzp77jM")
                .setToken("MzQ3ODg2MDI3NTk3MTUyMjU3.DHe5oQ.k0LVehQcXT1TVfwpQ9wxO7jEiH4")
                .setAutoReconnect(true)
                .setGame(Game.of("Testing System"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB);

        registerCommands();
        registerListeners();

        try {
            builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }
    }

    private static void registerListeners() {
        builder.addEventListener(new GuildMessageListener());
        builder.addEventListener(new UserOnlineStatusUpdateListener());
    }

    private static void registerCommands() {
        CommandParser.register("ping", new Ping());
    }
}
