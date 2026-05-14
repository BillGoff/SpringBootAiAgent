package com.wrgoff.ai_agent.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is the chat configuration for the multiple AI Chat Clients.
 * @author wrgoff
 * @since 14 May 2026
 */
@Configuration
class ChatConfiguration {

	/**
	 * This method is used to create the Bean that will be injected into the 
	 * Controller constructor so we can use it to send an AI chat to Open AI.
	 * @param chatModel OpenAiChatModel to use to create the client.
	 * @return ChatClient that will be used to send a request to Open AI
	 */
    @Bean("openAiClient")
	ChatClient openAiChatModel(OpenAiChatModel chatModel)
	{
		return ChatClient.create(chatModel);
	}
    
	/**
	 * This method is used to create the Bean that will be injected into the 
	 * Controller constructor so we can use it to send an AI chat to Google.
	 * @param chatModel GoogleGenAiChatModel used to create the client.
	 * @return ChatClient that will be used to send a request to Google AI.
	 */
	@Bean("googleAiClient")
	ChatClient googleAiChatModel(GoogleGenAiChatModel chatModel)
	{
		return ChatClient.create(chatModel);
	}
}
