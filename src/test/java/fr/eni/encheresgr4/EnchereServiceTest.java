package fr.eni.encheresgr4;

import fr.eni.encheresgr4.exceptions.EnchereException;
import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Categorie;
import fr.eni.encheresgr4.model.Enchere;
import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.repository.EnchereRepository;
import fr.eni.encheresgr4.service.EnchereService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EnchereServiceTest {

    @MockitoBean
    private EnchereRepository enchereRepository;

    @Autowired
    private EnchereService enchereService;

    @Test
    public void testSaveGood() {
        // Création d'un utilisateur de test
        Utilisateur vendeur = new Utilisateur(1, "UserTest", "User", "TEST", "usertest@gmail.com", "0123456789", "5 rue des tests", "14000", "Caen", "azerty", 1000, false);
        Utilisateur acheteur = new Utilisateur(2, "UserTest2", "User", "TEST", "usertest2@gmail.com", "0123456789", "5 rue des tests", "14000", "Caen", "azerty", 1000, false);

        // Création d'un article vendu
        ArticleVendu articleVendu = new ArticleVendu(1, "PS4", "Une ps4", LocalDateTime.now(), LocalDateTime.now(), 100, 0, "En cours", new Categorie(1, "Informatique"), vendeur,"");

        // Création d'un enchere
        Enchere enchere = new Enchere(LocalDateTime.now(), 400, acheteur, articleVendu);

        // Simulation de la connexion
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        acheteur,
                        acheteur.getPassword(),
                        acheteur.getAuthorities()
                )
        );
        int result = enchereService.save(enchere);
        assertEquals(articleVendu.getNo_article(), result);
    }

    @Test
    @DisplayName("Ne peut pas encherir sur sa propre vente")
    public void testSavePropreVente() {
        // Création d'un utilisateur de test
        Utilisateur vendeur = new Utilisateur(1, "UserTest", "User", "TEST", "usertest@gmail.com", "0123456789", "5 rue des tests", "14000", "Caen", "azerty", 1000, false);

        // Création d'un article vendu
        ArticleVendu articleVendu = new ArticleVendu(1, "PS4", "Une ps4", LocalDateTime.now(), LocalDateTime.now(), 100, 0, "En cours", new Categorie(1, "Informatique"), vendeur,"");

        // Création d'une enchère
        Enchere enchere = new Enchere(LocalDateTime.now(), 400, vendeur, articleVendu);

        // Simulation de la connexion
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        vendeur,
                        vendeur.getPassword(),
                        vendeur.getAuthorities()
                )
        );

        // Vérification que l'exception est bien lancée lorsque l'utilisateur tente d'enchérir sur son propre article
        EnchereException thrown = assertThrows(EnchereException.class, () -> {
            enchereService.save(enchere);
        });

        // Vérification du message d'exception
        assertEquals("Vous ne pouvez pas encherir sur votre propre vente", thrown.getMessage());
    }

    @Test
    @DisplayName("pas assez de crédit")
    public void testSavePasMonnais() {
        // Création d'un utilisateur de test
        Utilisateur vendeur = new Utilisateur(1, "UserTest", "User", "TEST", "usertest@gmail.com", "0123456789", "5 rue des tests", "14000", "Caen", "azerty", 1000, false);
        Utilisateur acheteur = new Utilisateur(2, "UserTest2", "User", "TEST", "usertest2@gmail.com", "0123456789", "5 rue des tests", "14000", "Caen", "azerty", 1000, false);

        // Création d'un article vendu
        ArticleVendu articleVendu = new ArticleVendu(1, "PS4", "Une ps4", LocalDateTime.now(), LocalDateTime.now(), 100, 0, "En cours", new Categorie(1, "Informatique"), vendeur, "");

        // Création d'une enchère
        Enchere enchere = new Enchere(LocalDateTime.now(), 2100, acheteur, articleVendu);

        // Simulation de la connexion
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        acheteur,
                        acheteur.getPassword(),
                        acheteur.getAuthorities()
                )
        );

        // Vérification que l'exception est bien lancée lorsque l'utilisateur tente d'enchérir sur son propre article
        EnchereException thrown = assertThrows(EnchereException.class, () -> {
            enchereService.save(enchere);
        });

        // Vérification du message d'exception
        assertEquals("Vous n'avez pas assez de crédit", thrown.getMessage());
    }

    @Test
    @DisplayName("mettre un montant supèrieur à")
    public void testSaveInferieur() {
        // Création d'un utilisateur de test
        Utilisateur vendeur = new Utilisateur(1, "UserTest", "User", "TEST", "usertest@gmail.com", "0123456789", "5 rue des tests", "14000", "Caen", "azerty", 1000, false);
        Utilisateur acheteur = new Utilisateur(2, "UserTest2", "User", "TEST", "usertest2@gmail.com", "0123456789", "5 rue des tests", "14000", "Caen", "azerty", 1000, false);

        // Création d'un article vendu
        ArticleVendu articleVendu = new ArticleVendu(1, "PS4", "Une ps4", LocalDateTime.now(), LocalDateTime.now(), 100, 0, "En cours", new Categorie(1, "Informatique"), vendeur,"");

        // Création d'une enchère
        Enchere enchere = new Enchere(LocalDateTime.now(), 50, vendeur, articleVendu);

        // Simulation de la connexion
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        acheteur,
                        acheteur.getPassword(),
                        acheteur.getAuthorities()
                )
        );

        // Vérification que l'exception est bien lancée lorsque l'utilisateur tente d'enchérir sur son propre article
        EnchereException thrown = assertThrows(EnchereException.class, () -> {
            enchereService.save(enchere);
        });

        // Vérification du message d'exception
        assertEquals("Vous devez mettre un montant supèrieur à "+100, thrown.getMessage());
    }
}
