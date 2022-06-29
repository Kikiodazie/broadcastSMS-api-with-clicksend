package com.example.broadcastsmsapiwithclicksend.api;

import ClickSend.Api.SmsApi;
import ClickSend.Api.SmsCampaignApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsCampaign;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import com.example.broadcastsmsapiwithclicksend.model.MessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BroadcastSMSRestController {
    @Autowired
    private ApiClient clickSendConfig;

    @PostMapping("/api/sms1000")
    public ResponseEntity<String> sendSMStoUpto1000Numbers(@RequestBody MessageDetails messageDetails){

        SmsApi smsApi = new SmsApi(clickSendConfig);

        SmsMessage smsMessage=new SmsMessage();
        smsMessage.body(messageDetails.getMessageBody());
        smsMessage.listId(messageDetails.getContactListOfMultipleNumbers());
        smsMessage.source(messageDetails.getSendingSource());

        List<SmsMessage> smsMessageList= List.of(smsMessage);

        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String response = smsApi.smsSendPost(smsMessages);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Exception when calling SmsApi#smsSendPost", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/api/sms20000")
    public ResponseEntity<String> sendSMSupTo20000Numbers(@RequestBody MessageDetails messageDetails){
        SmsCampaignApi smsCampaignApi = new SmsCampaignApi(clickSendConfig);
        SmsCampaign campaign = new SmsCampaign(); // SmsCampaign | SmsCampaign model

        campaign.listId(messageDetails.getContactListOfMultipleNumbers());
        campaign.name(messageDetails.getSmsCampaignName());
        campaign.body(messageDetails.getMessageBody());

        try {
            String response = smsCampaignApi.smsCampaignsSendPost(campaign);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Exception when calling SmsCampaignApi#smsCampaignsSendPost", HttpStatus.BAD_REQUEST);
    }



}
