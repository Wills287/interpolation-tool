package wills.interpolation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DataControllerIT {

    private final String exampleInput = "37.454012,95.071431,73.199394,59.865848,nan\n"
            + "15.599452,5.808361,86.617615,60.111501,70.807258\n"
            + "2.058449,96.990985,nan,21.233911,18.182497\n"
            + "nan,30.424224,52.475643,43.194502,29.122914\n"
            + "61.185289,13.949386,29.214465,nan,45.606998\n";

    private final String exampleOutput = "37.454012,95.071431,73.199394,59.865848,65.336553\n"
            + "15.599452,5.808361,86.617615,60.111501,70.807258\n"
            + "2.058449,96.990985,64.3295385,21.233911,18.182497\n"
            + "31.222654,30.424224,52.475643,43.194502,29.122914\n"
            + "61.185289,13.949386,29.214465,39.338655,45.606998\n";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldProcessDataSuccessfully() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .content(exampleInput)
        )
                .andExpect(
                        status().isOk()
                )
                .andReturn();

        String output = mvcResult.getResponse().getContentAsString();

        assertEquals(exampleOutput, output);
    }
}
