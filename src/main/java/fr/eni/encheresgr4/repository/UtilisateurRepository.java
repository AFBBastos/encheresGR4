package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtilisateurRepository implements CrudInterface<Utilisateur> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Utilisateur findByPseudo(String pseudo) {
        String sql = "SELECT * FROM utilisateurs WHERE pseudo = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class), pseudo);
    }

    @Override
    public Utilisateur findOneById(int id) {
        String sql = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class), id);
    }

    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }

    @Override
    public void save(Utilisateur utilisateur) {

    }

    @Override
    public void delete(Utilisateur utilisateur) {

    }
}
