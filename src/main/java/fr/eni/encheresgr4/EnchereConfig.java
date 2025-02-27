package fr.eni.encheresgr4;

import fr.eni.encheresgr4.repository.UtilisateurRepository;
import fr.eni.encheresgr4.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnchereConfig {

    @Bean
    public CustomUserDetailsService userDetailsService(UtilisateurRepository utilisateurRepository) {
        return new CustomUserDetailsService(utilisateurRepository);
    }

}
