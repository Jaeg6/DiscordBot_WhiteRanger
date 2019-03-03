package Commands;

import Main.WhiteRanger;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Random;

public class MemberJoin extends ListenerAdapter {
    String[] messages = {"0 has joined.",
            "Who is this.... 0 fellow...",
            "Whomst've'd'nt is 0?"};



    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        Random r = new Random();
        int number = r.nextInt(messages.length);
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0xFFFFFF);
        join.setDescription(messages[number].replaceAll("0", event.getMember().getAsMention()));

        if(WhiteRanger.botChannelId ==  null)
            event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
        else
            event.getGuild().getTextChannelById(WhiteRanger.botChannelId).sendMessage(join.build()).queue();
    }
}
