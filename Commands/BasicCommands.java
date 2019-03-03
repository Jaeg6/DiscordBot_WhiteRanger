/**
 * Holds commands such as bot information, help command, and a testing command
 * @author Jaeg6
 */

package Commands;

import Main.WhiteRanger;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BasicCommands extends ListenerAdapter {
    private EmbedBuilder noRangerRole =  new EmbedBuilder();

    private EmbedBuilder getNoRangerRole(){
        noRangerRole.setColor(0xFFFFFF);
        noRangerRole.addField("Ranger Command Role Not Found", "You must have the \'Ranger Command\' role to perform this command.", false);
        return noRangerRole;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        System.out.println("Event occurred from: " + event.getAuthor() + "message: " + event.getMessage());

        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(event.getAuthor().isBot())
            System.out.println("Bot message");

        else {
            //Random testing command
            if (args[0].equals(WhiteRanger.prefix + "ping")) {
                System.out.println("Command successful.");
                event.getChannel().sendMessage("Pong!").queue();
            }

            //Bot information command
            if (args[0].equals(WhiteRanger.prefix + "info")) {
                EmbedBuilder info = new EmbedBuilder();
                info.setTitle("White Ranger Bot information");
                info.addField("Creator", "Jaeg#0666", false);
                info.setDescription("A bot that does absolutely nothing useful and probably shouldn't exist");
                info.setColor(0xFFFFFF);

                event.getChannel().sendMessage(info.build()).queue();
                info.clear();
            }

            //I'm xyz command
            if (args[0].equalsIgnoreCase("im") || args[0].equalsIgnoreCase("I\'m")) {
                String str = "";
                for (int i = 1; i < args.length; i++) {
                    if (i == args.length - 1)
                        str += args[i];
                    else
                        str += args[i] + " ";
                }
                event.getChannel().sendMessage("Hi " + str + ", I'm dad!").queue();
            }

            //Help command
            if (args[0].equals(WhiteRanger.prefix + "help")) {
                EmbedBuilder help = new EmbedBuilder();
                help.setTitle("White Ranger Help");
                help.addField("+ping", "Just a test command.", false);
                help.addField("+info", "Information about the bot.", false);
                help.addField("+clear [amt]", "Clears past messages in current channel. (Temporarily disabled)", false);
                help.addField("+setwelcomechannel", "Set the channel where you wish my welcome message to appear when someone joins the server.", false);
                help.setColor(0xFFFFFF);

                event.getChannel().sendMessage(help.build()).queue();
                help.clear();
            }

            if (args[0].equals(WhiteRanger.prefix + "setwelcomechannel")) {
                WhiteRanger.botChannelId = event.getChannel().getId();

                EmbedBuilder chanSet = new EmbedBuilder();
                chanSet.addField("Bot Welcome Channel Set!", "I will only post my welcome message in this channel.", false);
                chanSet.setColor(0xFFFFFF);

                event.getChannel().sendMessage(chanSet.build()).queue();
                chanSet.clear();
            }
        }

    }

    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event){
        System.out.println("Message deleted: " + event.getMessageId());
    }
}