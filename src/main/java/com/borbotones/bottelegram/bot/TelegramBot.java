package com.borbotones.bottelegram.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.apikey}")
    private String key;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return key;
    }

    @Value("${server.port}")
    int serverPort;


    @Override
    public void onUpdateReceived(Update update) {

        String messageText = update.getMessage().getText();
        String chatId = String.valueOf(update.getMessage().getChatId());

        SendMessage sendMessage = new SendMessage();
        if(messageText.equals("/start")){
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            row.add("/start");
            row.add("/status");
            keyboard.add(row);
            row = new KeyboardRow();
            row.add("/example1");
            row.add("/example2");
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);

            sendMessage.setChatId(chatId);
            //sendMessage.setText("keyboard");
            sendMessage.setReplyMarkup(keyboardMarkup);

        }else if(messageText.equals("/status")){
            sendMessage.setChatId(chatId);
            sendMessage.setText("Running on port: "+serverPort);
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String error){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId("1327918934");
        sendMessage.setText(error);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
