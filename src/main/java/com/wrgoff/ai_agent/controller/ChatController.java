package com.wrgoff.ai_agent.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wrgoff.utils.DateUtils;

/**
 * My Spring AI Chat Controller.
 * @author wrgoff
 * @since 13 May 2026
 */
@RestController
public class ChatController {

	private static final Logger logger = LogManager.getLogger(ChatController.class);

	
	private final ChatClient openAiClient;
	private final ChatClient googleGenAiClient;

	/**
	 * Constructor auto wired for multiple chat clients.
	 * @param openAiClient Open AI Chat Client.
	 * @param googleGenAiClient Google Gen AI Chat Client.
	 */
    public ChatController(
    		@Qualifier("openAiClient") ChatClient openAiClient,
    		@Qualifier("googleAiClient") ChatClient googleGenAiClient)
    {
        this.openAiClient = openAiClient;
        this.googleGenAiClient = googleGenAiClient;
    }
   
    /**
     * This is just a test end point used to say hello to a user.
     * @return String just hello and the person's name.
     */
	@GetMapping("/hello/{user}")
	public String hello(@PathVariable("user") String name)
	{	
		String temp = "Base hello was reached!  user: " + name;

		System.out.println(temp);
				
		logger.debug(temp);
		return "Hello " + name;
	}

	/**
	 * This get end point is used to just send a request to the Open AI Client to tell us a joke.
	 * @return String the joke returned from Open AI Chat Client.
	 */
	@GetMapping("/openAiChat")
	public String openAiChat()
	{	
		Date startDate = DateUtils.rightNowDate();
		boolean failed = false;
		
		try
		{
			String response = openAiClient.prompt("Tell me a joke.").call().content();
			return response;
		}
		catch(Exception e)
		{
			failed = true;
			e.printStackTrace();
			return(e.getMessage());
		}
		finally
		{
			StringBuilder msg = new StringBuilder("It took " + DateUtils.computeDiff(startDate,
					DateUtils.rightNowDate()));
			if (failed)
				msg.append(" to fail to ");
			else
				msg.append(" to successfully ");
		
			msg.append("get a response back from Open AI Chat.");
			
			logger.info(msg.toString());
		}	
	}
	
	/**
	 * This get end point is used to send a request to Google Gen Ai Chat to tell us 5 famous pirates. 
	 * @return String the response back from Google Gen AI.
	 */
	@GetMapping("/googleAiChat")
	public String googleAiChat()
	{	
		Date startDate = DateUtils.rightNowDate();
		boolean failed = false;
		
		try
		{
			String response = googleGenAiClient.prompt("Generate the names of 5 famous pirates.").call().content();
			
			System.out.println(response);
			return response;
		}
		catch(Exception e)
		{
			failed = true;
			e.printStackTrace();
			return(e.getMessage());
		}
		finally
		{
			StringBuilder msg = new StringBuilder("It took " + DateUtils.computeDiff(startDate,
					DateUtils.rightNowDate()));
			if (failed)
				msg.append(" to fail to ");
			else
				msg.append(" to successfully ");
		
			msg.append("get a response back from Open AI Chat.");
			
			logger.info(msg.toString());
		}	
	}
}
