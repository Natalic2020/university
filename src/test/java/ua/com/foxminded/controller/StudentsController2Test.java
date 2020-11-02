package ua.com.foxminded.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ua.com.foxminded.config.MySpringMvcDispatcherSerlvetIntitializer;
import ua.com.foxminded.config.TestWebConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestWebConfig.class   , MySpringMvcDispatcherSerlvetIntitializer.class})
@TestInstance(Lifecycle.PER_CLASS)
@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
@Disabled
class StudentsController2Test {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testController() throws Exception {
        final ResultActions actual =  mockMvc.perform(get("/students"));
//        final ResultActions actual2 =  mockMvc.perform(get("/students")).andDo(print()).andExpect(view().name("students.html"));
        actual.andExpect(status().isOk());
    }


}
