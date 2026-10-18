package backend.mikameno;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import backend.mikameno.repository.*;

@SpringBootTest
public class SmokeTest {
    
    @Autowired
    private KayttajaRepository kr;

    @Autowired
    private TapahtumaRepository tr;

    @Autowired
    public SmokeTest(KayttajaRepository kr, TapahtumaRepository tr) {
        this.kr = kr;
        this.tr = tr;
    }

    @Test
    public void repositorytLatautuvat() throws Exception {
        assertThat(kr).isNotNull();
        assertThat(tr).isNotNull();
    }
}
