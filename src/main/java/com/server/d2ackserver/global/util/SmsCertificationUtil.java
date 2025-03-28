package com.server.d2ackserver.global.util;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsCertificationUtil {

    @Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String apiSecret;

    @Value("${coolsms.fromnumber}")
    private String fromNumber;

    DefaultMessageService messageService;

    @PostConstruct
    public void ㅇinit(){
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr"); // 메시지 서비스 초기화
    }

    public void sendSMS(String to, String certificationCode){
        Message message = new Message();
        message.setFrom(fromNumber);
        message.setTo(to);
        message.setText("본인확인 인증번호는 " + certificationCode + "입니다.");

        this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }
}
