package com.wrgoff.ai_agent.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

	@Autowired
    private MockMvc mockMvc;

//	@MockitoBean 
	@MockitoBean(answers = Answers.RETURNS_DEEP_STUBS)
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
		String mockedResponse = "Mocked AI Response";
		try
		{
			
			String actualResponse = mockMvc.perform(get("/openAiChat")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			System.out.println(actualResponse);
			
			// 1. Prepare the Mocking Chain
			ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
			ChatClient.CallResponseSpec responseSpec = mock(ChatClient.CallResponseSpec.class);
	        
			// 2. Stub the fluent API calls
			when(openAiClient.prompt(anyString())).thenReturn(requestSpec);
			when(requestSpec.call()).thenReturn(responseSpec);
			when(responseSpec.content()).thenReturn(mockedResponse);
	
			// 3. Execute your logic (e.g., calling a controller or service)
			String result = openAiClient.prompt("Hello").call().content();
	
			// 4. Assert results
			assertEquals("Mocked AI Response", result);
			verify(openAiClient).prompt("Hello");
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to get expected result for Testing of Hello <name>!");
		}
	}
	
	private void assertEquals(String string, String result) {
		// TODO Auto-generated method stub
		
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

