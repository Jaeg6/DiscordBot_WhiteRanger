/**
 * Holds commands such as bot information, help command, and a testing/maintenance commands
 * @author Jaeg6
 */

package Commands;

import Main.WhiteRanger;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class BasicCommands extends ListenerAdapter {

    /**
     * Variable to store author of message event's name
     */
    private String authorName;

    /**
     * Variable to store ping amt
     */
    private long ping = 0;

    /**
     * Variable to store message event to delete
     */
    public static String msg;

    /**
     * Just a quick way to build an embed to indicate a disabled command
     */
    private EmbedBuilder commandDisabled =  new EmbedBuilder();
    private EmbedBuilder getCommandDisabled(){
        commandDisabled.setColor(0xFFFFFF);
        commandDisabled.setTitle("Command temporarily disabled.");
        return commandDisabled;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        System.out.println("Event occurred from: " + event.getAuthor() + "message: " + event.getMessage());

        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (event.getAuthor().isBot())
            System.out.println("Bot message");

        else {
            //Random testing command
            if (args[0].equals(WhiteRanger.prefix + "ping")) {

                //Delete user's message performing the command
                msg = event.getChannel().getLatestMessageId();
                event.getChannel().deleteMessageById(msg).queue();

                ping = event.getJDA().getPing();
                EmbedBuilder pingEm = new EmbedBuilder();
                pingEm.setColor(0xFFFFFF);
                pingEm.addField("Pong!", ping + "ms", false);

                event.getChannel().sendMessage(pingEm.build()).queue();

            }

            //Bot information command
            if (args[0].equals(WhiteRanger.prefix + "info")) {

                //Delete user's message performing the command
                msg = event.getChannel().getLatestMessageId();
                event.getChannel().deleteMessageById(msg).queue();

                EmbedBuilder info = new EmbedBuilder();
                info.setTitle("White Ranger Bot information");
                info.addField("Creator", "Jaeg#0666", false);
                info.setDescription("A bot that does absolutely nothing useful and probably shouldn't exist");
                info.addField("Current Version", WhiteRanger.VERSION, false);
                info.setColor(0xFFFFFF);

                event.getChannel().sendMessage(info.build()).queue();
                info.clear();
            }

            //I'm xyz command
            if (args[0].equalsIgnoreCase("im") || args[0].equalsIgnoreCase("I\'m")) {
                System.out.println(args.length);
                if (args.length > 1) {
                    if (args.length == 2 && args[1].equalsIgnoreCase("dad")) {
                        authorName = event.getAuthor().getName();
                        event.getChannel().sendMessage("No you're not, you're " + authorName + "!").queue();
                    } else {
                        String str = "";
                        for (int i = 1; i < args.length; i++) {
                            if (i == args.length - 1)
                                str += args[i];
                            else
                                str += args[i] + " ";
                        }
                        event.getChannel().sendMessage("Hi " + str + ", I'm dad!").queue();
                    }
                }
            }

            //Help command
            if (args[0].equals(WhiteRanger.prefix + "help")) {
                EmbedBuilder help = new EmbedBuilder();
                help.setTitle("White Ranger Help");

                try {
                    if (args.length < 2 || Integer.parseInt(args[1]) == 1) {

                        //Delete user's message performing the command
                        msg = event.getChannel().getLatestMessageId();
                        event.getChannel().deleteMessageById(msg).queue();

                        help.setDescription("Page 1/2");

                        help.addField("help [page number]",
                                "Returns information about my commands at a specified page number.",
                                false);

                        help.addField("Command prefix: " + WhiteRanger.prefix,
                                "e.g. w!info will run the info command",
                                false);

                        help.addField("ping",
                                "Returns my current ping.",
                                false);

                        help.addField("info",
                                "Information about the bot.",
                                false);

                        help.addField("clear [amt]",
                                "Clears past messages in current channel. (Temporarily disabled)",
                                false);

                        help.addField("setwelcomechannel",
                                "Set the channel where you wish my welcome message to appear when someone joins the server.",
                                false);

                    }

                    else if (Integer.parseInt(args[1]) == 2) {

                        //Delete user's message performing the command
                        msg = event.getChannel().getLatestMessageId();
                        event.getChannel().deleteMessageById(msg).queue();

                        help.setDescription("Page 2/2");

                        help.addField("welcome [on/off]",
                                "Toggles whether or not I will welcome new incoming members to the server. This is on by defualt.",
                                false);

                        help.addField("changelog",
                                "View changes made in my current version.",
                                false);
                    }


                    help.setColor(0xFFFFFF);

                    event.getChannel().sendMessage(help.build()).queue();
                    help.clear();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            //Set channel where White Ranger will welcome new members command
            if (args[0].equals(WhiteRanger.prefix + "setwelcomechannel")) {

                //Delete user's message performing the command
                msg = event.getChannel().getLatestMessageId();
                event.getChannel().deleteMessageById(msg).queue();

                WhiteRanger.botChannelId = event.getChannel().getId();

                EmbedBuilder chanSet = new EmbedBuilder();
                chanSet.addField("Bot Welcome Channel Set!", "I will only post my welcome message in this channel.", false);
                chanSet.setColor(0xFFFFFF);

                event.getChannel().sendMessage(chanSet.build()).queue();
                chanSet.clear();
            }

            //Bot change log
            if (args[0].equals(WhiteRanger.prefix + "changelog")) {

                //Delete user's message performing the command
                msg = event.getChannel().getLatestMessageId();
                event.getChannel().deleteMessageById(msg).queue();

                EmbedBuilder changeLog = new EmbedBuilder();
                changeLog.setColor(0xFFFFFF);
                changeLog.setTitle("White Ranger Change Log -- 3/4/19");
                changeLog.addField("Version", WhiteRanger.VERSION, false);
                changeLog.addField("Changes",
                        "- Command messages get deleted when command is executed" +
                                "\n\n- Other small fixes were made.",
                        false);

                event.getChannel().sendMessage(changeLog.build()).queue();
            }


            //Temp clear command redirect
            if (args[0].equals(WhiteRanger.prefix + "clear")) {

                //Delete user's message performing the command
                msg = event.getChannel().getLatestMessageId();
                event.getChannel().deleteMessageById(msg).queue();

                event.getChannel().sendMessage(getCommandDisabled().build()).queue();
            }

        }
    }


    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event){
        System.out.println("Message deleted: " + event.getMessageId());
    }

}