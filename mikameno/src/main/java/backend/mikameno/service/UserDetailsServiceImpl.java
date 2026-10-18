package backend.mikameno.service;

import backend.mikameno.model.Kayttaja;
import backend.mikameno.repository.KayttajaRepository;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	private final KayttajaRepository kr;

	public UserDetailsServiceImpl(KayttajaRepository kr) {
		this.kr = kr;
	}

	@Override
	public UserDetails loadUserByUsername(String kayttajatunnus) throws UsernameNotFoundException {
		Kayttaja nykyinenKayttaja = kr.findByKayttajatunnus(kayttajatunnus);
		UserDetails kayttaja = new org.springframework.security.core.userdetails.User(kayttajatunnus, nykyinenKayttaja.getSalasanaHash(),
		AuthorityUtils.createAuthorityList(nykyinenKayttaja.getRooli()));
		return kayttaja;
	}
}
