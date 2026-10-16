package backend.mikameno;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.mikameno.model.Kayttaja;
import backend.mikameno.model.Tapahtuma;
import backend.mikameno.repository.TapahtumaRepository;

@SpringBootApplication
public class MikamenoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MikamenoApplication.class, args);
	}

	@Bean
	public CommandLineRunner kovakoodi(TapahtumaRepository tr) {
		return (args) -> {
			//LocalDateTime alkupv, LocalDateTime loppupv,  String paikka, String kuvaus
			tr.save(new Tapahtuma("Halloween",LocalDateTime.parse("2026-11-30T16:00:00"),LocalDateTime.parse("2026-11-30T23:30:00"),"Aapelinkatu","Halloweenin viettoa, kurpitsan kaiverrusta ja kauhuleffa",5));
			tr.save(new Tapahtuma("Kirjamessut",LocalDateTime.parse("2026-11-23T10:00:00"),LocalDateTime.parse("2026-11-25T21:00:00"),"Messukeskus","Helsingin kirjamessut"));
			tr.save(new Tapahtuma("Kirjakerho: viides vuodenaika",LocalDateTime.parse("2026-12-02T18:15:00"),LocalDateTime.parse("2026-12-02T21:15:00"),"Köydenpunojankatu","Keskustelua kirjasta Viides vuodenaika. Kirjailija N. K. Jemisin",8));
			tr.save(new Tapahtuma("Piparileivonnaiset",LocalDateTime.parse("2026-12-06T10:00:00"),LocalDateTime.parse("2026-12-06T18:00:00"),"Aapelinkatu","Perinteinen piparkakkuleivonta. OPM eli oma purkki mukaan!",10));
		};
	}

}
