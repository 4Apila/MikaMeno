package backend.mikameno;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import backend.mikameno.model.Tapahtuma;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTapahtumatHeraa() throws Exception {
        mockMvc.perform(get("/api/tapahtumat"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getTapahtumatToimii() throws Exception {
        mockMvc.perform(get("/api/tapahtumat"))
                .andExpect(status().isOk());
    }

    @Test
    void luoJaPoistaTapahtumaToimii() throws Exception {
        String uusiTapahtuma = """
                {
                    "nimi": "Testi",
                    "alkupv": "2042-01-30T00:00:00",
                    "loppupv": "2042-01-31T23:59:00",
                    "paikka": "VsCode",
                    "kuvaus": "Tapahtuma REST-testien testaamiseen"
                }
                """;

        String testaus = mockMvc.perform(post("/api/tapahtumat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(uusiTapahtuma))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println("Luotu tapahtuma: " + testaus);

        ObjectMapper mapper = new ObjectMapper();
        Tapahtuma luotuTapahtuma = mapper.readValue(testaus, Tapahtuma.class);
        Long id = luotuTapahtuma.getId();

        mockMvc.perform(delete("/api/tapahtuma/" + id)).andExpect(status().isOk());
    }
}
