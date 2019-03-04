package Commands;

import Main.WhiteRanger;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Random;

public class MemberJoin extends ListenerAdapter {
    String[] messages = {"[member] has joined.",
            "Who is this.... [member] fellow...",
            "Whomst've'd'nt is [member]?"};

    private static boolean activated = true;


    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        EmbedBuilder act = new EmbedBuilder();

        if (args[0].equals(WhiteRanger.prefix + "welcome")) {
            if(args.length < 2){
                act.addField("Missing argument",
                        "Please specify whether you wish to toggle welcome on or off",
                        false);
            }

            else if (args[1].equals("on")) {
                activated = true;
                act.addField("Welcome message enabled", "I will welcome new members that join the server.", false);
            }
            else if (args[1].equals("off")) {
                activated = false;
                act.addField("Welcome message disabled",
                        "I will longer welcome new members that join the server.",
                        false);
            }

            else
                act.addField("Unknown argument", "Please enter either on or off", false);

            act.setColor(0xFFD700);
            event.getChannel().sendMessage(act.build()).queue();
            act.clear();

        }
    }

    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        Random r = new Random();
        int number = r.nextInt(messages.length);
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0xFFFFFF);
        join.setDescription(messages[number].replace("[member]", event.getMember().getAsMention()));

        if(activated) {
            if (WhiteRanger.botChannelId == null)
                event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
            else
                event.getGuild().getTextChannelById(WhiteRanger.botChannelId).sendMessage(join.build()).queue();
        }
    }
}
