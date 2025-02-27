package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Utilisateur findByPseudo(String pseudo) {
        String sql = "SELECT * FROM utilisateurs WHERE pseudo = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class), pseudo);
    }

}
