package com.skr.ai.firstproject.advisor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;


public class CustomTokenCountAdvisor implements StreamAdvisor, CallAdvisor {

    Logger logger= LoggerFactory.getLogger(CustomTokenCountAdvisor.class);

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        this.logger.info("My token print advisor is called : ");
        this.logger.info("Request : "+chatClientRequest.prompt().getContents());
        ChatClientResponse response=callAdvisorChain.nextCall(chatClientRequest);
        this.logger.info("Response received from the model: ");
        this.logger.info("Response : "+response
                .chatResponse()
                .getResults()
                .get(0)
                .getOutput()
                .getText());
        this.logger.info("Total Token Consumed :"+response
                .chatResponse()
                .getMetadata()
                .getUsage()
                .getTotalTokens());
        return response;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return null;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
