import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.List;

public class Commands extends ListenerAdapter {
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
            if (args[0].equals(Main.prefix + "ping")) {
                System.out.println("Command successful.");
                event.getChannel().sendMessage("Pong!").queue();
            }

            //Bot information command
            if (args[0].equals(Main.prefix + "info")){
                EmbedBuilder info = new EmbedBuilder();
                info.setTitle("White Ranger Bot information");
                info.addField("Creator", "Jaeg#0666", false);
                info.setDescription("A bot that does absolutely nothing useful and probably shouldn't exist");
                info.setColor(0xFFFFFF);

                event.getChannel().sendMessage(info.build()).queue();
                info.clear();
            }

            //Clear command
            /**if (args[0].equals(Main.prefix + "clear"))
                if (args.length < 2)
                    System.out.println("Error clearing. Not enough arguments");
                else {
                    List<Message> messages;
                    try {
                        messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                        event.getChannel().deleteMessages(messages).queue();
                    } catch (IllegalArgumentException e) {
                        EmbedBuilder clearError = new EmbedBuilder();
                        clearError.addField("Error clearing messages", "You can only clear 1 to 100 messages", false);
                        clearError.setColor(0xFFFFFF);

                        event.getChannel().sendMessage(clearError.build()).queue();
                        clearError.clear();
                    }
                }
            **/
            //I'm xyz command
            if (args[0].equalsIgnoreCase("im") || args[0].equalsIgnoreCase("I\'m")) {
                String str = "";
                for (int i = 1; i < args.length; i++) {
                    if(i == args.length-1)
                        str += args[i];
                    else
                        str += args[i] + " ";
                }
                event.getChannel().sendMessage("Hi " + str + ", I'm dad!").queue();
            }

            //Help command
            if(args[0].equals(Main.prefix + "help")){
                EmbedBuilder help = new EmbedBuilder();
                help.setTitle("White Ranger Help");
                help.addField("+ping", "Just a test command.", false);
                help.addField("+info", "Information about the bot.", false);
                help.addField("+clear [amt]", "Clears past messages in current channel. (Temporarily disabled)", false);

                event.getChannel().sendMessage(help.build()).queue();
                help.clear();
            }
        }
    }

    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event){
        System.out.println("Message deleted: " + event.getMessageId());
    }
}
