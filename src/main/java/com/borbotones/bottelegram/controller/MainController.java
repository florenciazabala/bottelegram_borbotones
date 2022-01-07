package com.borbotones.bottelegram.controller;

import com.borbotones.bottelegram.bot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@RestController
public class MainController {

    @Autowired
    TelegramBot telegramBot;

    @GetMapping("/{message}")
    public void botInitializer(@PathVariable("message")String messaje){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
            telegramBot.sendMessage(messaje);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
