package com.paypal.bfs.test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EmployeeservApplicationTest {
    @Autowired
    MockMvc mvc;

    @Test
    void contextLoads(){
    }

    @Test
    void testGetEmployeeById() throws Exception{
        String createjson = "{\"first_name\":\"Nitin\",\"last_name\":\"Developer\",\"address\":{\"address_line1\":\"siddharth vihar\",\"city\":\"Ghaziabad\",\"state\":\"UP\",\"country\":\"India\",\"zip_code\":\"201014\"}}";
        String responsejson = "{\"id\":1,\"first_name\":\"Nitin\",\"last_name\":\"Developer\",\"address\":{\"address_line1\":\"siddharth vihar\",\"city\":\"Ghaziabad\",\"state\":\"UP\",\"country\":\"India\",\"zip_code\":\"201014\"}}";

        mvc.perform(post("v1/bfs/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createjson)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
        mvc.perform(get("/v1/bfs/employees/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(responsejson)));
    }


}