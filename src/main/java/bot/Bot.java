package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends TelegramLongPollingBot {

    private final static String BOT_NAME = "bruno_bot";
    private final static String BOT_TOKEN = "570680109:AAFP1wHK1KAptytbkR4DNG6YKUxoepnc0bg";

    private static ArrayList command = new ArrayList<>();
    private Random random = new Random();
    private  Weather weather;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();

        //////////////////////
        // Переробити !
        command.add("/me");
        command.add("/c2");
        command.add("/c3");
        command.add("/c4");
        command.add("/c5");
        command.add("/c6");
        command.add("/c7");
        command.add("/c8");
        command.add("/c9");
        command.add("/c10");

        //////////////////////

        try {
             api.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message , String text) {

        SendMessage sendMessage =   new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public String getComand() {
        StringBuilder string_command = new StringBuilder();

        for (int i = 0 ; i < command.size(); i++) {
            string_command.append(command.get(i) + "\n");
        }
        return string_command.toString();
    }

    public void firstJoin(Message message) {
        sendMsg(message , "Привіт . \n Список команд : \n " + getComand());
    }

    public void onUpdateReceived(Update update) {
        weather = new Weather();
        Message message = update.getMessage();
        if(message != null && message.hasText()) {
            sendMsg(message , " тест");
            switch (message.getText()) {
                case "/help":
                    firstJoin(message);
                    break;
                case "/random":
                    sendMsg(message, String.valueOf(random.nextInt(100)));
                    break;
                case "/weather" :
                   sendMsg(message , weather.getWeather());
                    break;
            }
        }
    }


    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}
