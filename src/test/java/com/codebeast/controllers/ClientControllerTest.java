package com.codebeast.controllers;

import com.codebeast.Application;
import com.codebeast.domain.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ClientControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getView() throws Exception {
        mockMvc.perform(get("/clients")
                .contentType(contentType))
                .andExpect(status().isOk());
    }


    @Test
    public void createClientWithValidName() throws Exception {
        mockMvc.perform(post("/clients/create")
                .content(this.json(Client
                        .builder()
                        .name("ValidClientName")
                        .build()))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void createClientWithNoName() throws Exception {
        mockMvc.perform(post("/clients/create")
                .content(this.json(Client
                        .builder()
                        .build()))
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createClientWithNameLessThan3() throws Exception {
        mockMvc.perform(post("/clients/create")
                .content(this.json(Client
                        .builder()
                        .name("ab")
                        .build()))
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createClientWithNameMoreThan100() throws Exception {
        mockMvc.perform(post("/clients/create")
                .content(this.json(Client
                        .builder()
                        .name("0123456789 0123456789 0123456789 0123456789 0123456789 0123456789 0123456789 0123456789 0123456789 0123456789 0123456789")
                        .build()))
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

    @SuppressWarnings("unchecked")
    private String json(Object o) throws IOException {
        final MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}