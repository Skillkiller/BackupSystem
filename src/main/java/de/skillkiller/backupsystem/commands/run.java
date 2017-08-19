package de.skillkiller.backupsystem.commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Skillkiller on 19.08.2017.
 */
public class run implements Command {
    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) {
        if (!event.getAuthor().getId().equals("98719514908188672") || args.length <= 0) {
            return;
        }

        String s;
        String cmd;
        String output = "";

        cmd = String.join(" ", args);

        //event.getMessage().editMessage("❯_ `" + cmd + "`").queue();

        try {
            Process p = Runtime.getRuntime().exec(cmd);

            BufferedReader Input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader Error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while ((s = Input.readLine()) != null) {
                output = output + s + "\n";
            }

            while ((s = Error.readLine()) != null) {
                output = output + s + "\n";
            }

            if (output == "") {
                event.getChannel().sendMessage("```!Kein Output!```").queue();
            } else if (output.length() < 500) {
                event.getChannel().sendMessage("```" + output + "```").queue();
            } else {
                //log.finer("Output zu groß");

                //String link = PostRequest.sendtoPastebin("Output: " + cmd, output, "1");

                event.getChannel().sendMessage("```Output zu groß!```").queue();
            }

        } catch (Exception ex) {
            //String link = PostRequest.sendtoPastebin("Error: " + cmd, ex.getMessage().toString(), "1");
            event.getChannel().sendMessage("Es gab einen Error!").queue();
        }
    }

    @Override
    public String getHelp() {
        return "``.run [BEFEHL]``";
    }

    @Override
    public String getDescription() {
        return "Sendet seinen Befehl in die Konsole vom Host System";
    }

    @Override
    public int getPermission() {
        return 5;
    }
}
