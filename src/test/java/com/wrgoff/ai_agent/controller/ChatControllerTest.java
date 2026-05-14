package com.wrgoff.ai_agent.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wrgoff.utils.DateUtils;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@MockitoBean 
    @Qualifier("openAiClient")
    private ChatClient openAiClient;

    @MockitoBean 
    @Qualifier("googleAiClient")
    private ChatClient googleGenAiClient;
  
    /**
     * Test for Base Hello get method.
     */
	@Test
	public void testHello() {
		
		String expectedResponse = "Hello Bill";
		try {
			String actualResponse = mockMvc.perform(get("/hello/Bill")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(actualResponse);
			
			if(expectedResponse.equalsIgnoreCase(actualResponse))
				System.out.println("Got expected Response: " + expectedResponse);
			else
				System.out.println("Did NOT get expected Response\n	<" + 
						expectedResponse + ">\n got \n	<" + actualResponse + ">" );
			
			assertTrue(expectedResponse.equalsIgnoreCase(actualResponse.trim()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to get expected result for Testing of Hello <name>!");
		}
	}
	
	
	/**
	 * Test for Base get method that is used to call a caned request to Open AI.
	 */
	@Test
	public void testOpenAiChat()
	{	
		//TODO
	}
	
	/**
	 * Test for POST method to get an dynamic request from Open AI.
	 */
	@Test
	public void testAskOpenAi()
	{	
		//TODO
	}
	
	/**
	 * Test for Base get method that is used to call a caned request to Google Gen AI.
	 */
	@Test
	public void testGoogleAiChat()
	{	
		//TODO
	}
	
	/**
	 * Test for POST method to get an dynamic request from Google Gen AI.
	 */
	@Test
	public void testAskGoogleAi()
	{	
		//TODO
	}
}

