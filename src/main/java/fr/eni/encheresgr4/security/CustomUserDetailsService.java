package fr.eni.encheresgr4.security;

import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public CustomUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
//        System.out.println("-----------loadUserByUsername-----------");
//        return new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "$2a$10$svoHqpiTs2crtaNSCDgmk.rzYxaGH.357BimFaLqZd2Ok6vmHXpqa", 200, true);


        Utilisateur utilisateur = utilisateurRepository.findByPseudo(pseudo);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur pas trouvé");
        }
        return utilisateur;
    }
}
