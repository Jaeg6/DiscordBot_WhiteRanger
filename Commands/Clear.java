/**
 * Command used to clear a specified amount of messages from current channel
 * @author Jaeg6
 */

package Commands;

import Main.WhiteRanger;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public class Clear extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equals(WhiteRanger.prefix + "clear")) {
            List<Message> messages;
            try {
                if (args.length < 2) {
                    EmbedBuilder noArgument = new EmbedBuilder();
                    noArgument.addField("Missing argument", "Please specify how many items you wish to clear", false);
                    noArgument.setColor(0xFFFFFF);

                    event.getChannel().sendMessage(noArgument.build()).queue();
                    noArgument.clear();
                } else {
                    int clearAmt = Integer.parseInt(args[1]);

                    messages = event.getChannel().getHistory().retrievePast(clearAmt).complete();
                    event.getChannel().deleteMessages(messages).queue();
                }
            }
            catch (IllegalArgumentException e){
                EmbedBuilder clearError = new EmbedBuilder();
                clearError.addField("Error clearing messages", "You can only clear 1 to 100 messages", false);
                clearError.setColor(0xFFFFFF);
                event.getChannel().sendMessage(clearError.build()).queue();
                clearError.clear();
            }
        }


    }
}
