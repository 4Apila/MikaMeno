package backend.mikameno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MikamenoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MikamenoApplication.class, args);
	}

	/* @Bean
	public CommandLineRunner kovakoodi(TapahtumaRepository tr, KayttajaRepository kr) {
		return (args) -> {

			//String kayttajatunnus, String salasanaHash, vapaaehtoinen: string rooli
			kr.save(new Kayttaja("admin", "$2a$10$/IWA0v20JnmRyacyF5EiYes1j3qSlRgMNFU66rsjNu4FI8kzKqPye", "ADMIN")); //admin admin
			kr.save(new Kayttaja("johanna", "$2a$10$QMzuzJ1SxPKDsqDud.srtOWkumOx0ZyGmVE81p2eF1xKlRIKV93uq", "ADMIN"));
			kr.save(new Kayttaja("user", "$2a$10$yWF76fZnXUeH..Vzh.T/6OdKzpqF2dkCCPzrkKC2cCXesM0CQQu5m")); // user user
			kr.save(new Kayttaja("minna", "$2a$10$YR2S9kEJflfClppjMT0OeeWMcg4b2szEPJv4mZy93j18DnRZbmc6G"));
			kr.save(new Kayttaja("miro", "$2a$10$ZugkQsxHmQDSAKAJQgSrHehS4WRyVsZOGqo7zX3c5P0DodEfh7Ka6"));
			kr.save(new Kayttaja("antti", "$2a$10$cwZvQFmFTDHOLnStgurJxO8YEcOxqFLd1LbIA.6JWyrj7Qoe0hGcq"));
			kr.save(new Kayttaja("lera", "$2a$10$dz.3ePqJAOHbXS4QWHh8zuRHlKQ0wznsNgooDvMrcNJQmoWaksjMe"));
			kr.save(new Kayttaja("bardia", "$2a$10$XsFiT6q1lMXX.xbKSLBK2.pk9pUGVGSTmG51UNADRRgxQHYGxlgki"));
			kr.save(new Kayttaja("esko", "$2a$10$Ka0onTnIhgtbJ22MCDp9../QbSQJq/QUPBXQsxV/Y0tTuxpQ8d.JS"));

			//Sting nimi, LocalDateTime alkupv, LocalDateTime loppupv,  String paikka, String kuvaus
			tr.save(new Tapahtuma("Halloween",LocalDateTime.parse("2026-11-30T16:00:00"),LocalDateTime.parse("2026-11-30T23:30:00"),"Aapelinkatu","Halloweenin viettoa, kurpitsan kaiverrusta ja kauhuleffa",5, kr.findByKayttajatunnus("johanna")));
			tr.save(new Tapahtuma("Kirjamessut",LocalDateTime.parse("2026-11-23T10:00:00"),LocalDateTime.parse("2026-11-25T21:00:00"),"Messukeskus","Helsingin kirjamessut", kr.findByKayttajatunnus("antti")));
			tr.save(new Tapahtuma("Kirjakerho: viides vuodenaika",LocalDateTime.parse("2026-12-02T18:15:00"),LocalDateTime.parse("2026-12-02T21:15:00"),"Köydenpunojankatu","Keskustelua kirjasta Viides vuodenaika. Kirjailija N. K. Jemisin",8, kr.findByKayttajatunnus("lera")));
			tr.save(new Tapahtuma("Piparileivonnaiset",LocalDateTime.parse("2026-12-06T10:00:00"),LocalDateTime.parse("2026-12-06T18:00:00"),"Aapelinkatu","Perinteinen piparkakkuleivonta. OPM eli oma purkki mukaan!",10, kr.findByKayttajatunnus("miro")));
			tr.save(new Tapahtuma("City of Dreams",LocalDateTime.parse("2026-10-26T18:15:00"),LocalDateTime.parse("2026-10-26T21:00:00"),"Aapelinkatu","Session #51: Let the sleeping dragon lie ",7, kr.findByKayttajatunnus("miro")));
			tr.save(new Tapahtuma("City of Dreams ANNIVERSARY",LocalDateTime.parse("2026-11-07T17:10:00"),LocalDateTime.parse("2026-11-08T02:00:00"),"Ilmoitetaan myöhemmin","Session #52: The Anniversary Edition",8, kr.findByKayttajatunnus("miro")));
		};
	} */

}
