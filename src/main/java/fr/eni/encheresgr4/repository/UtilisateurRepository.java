package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Utilisateur findByPseudo(String pseudo) {
        String sql = "SELECT * FROM utilisateurs WHERE pseudo = ?";
        Utilisateur user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class), pseudo);
        if (user != null) {
            return user;
        }
        return null;
    }

    public int create(Utilisateur utilisateur) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pseudo", utilisateur.getPseudo());
        params.addValue("nom", utilisateur.getNom());
        params.addValue("prenom", utilisateur.getPrenom());
        params.addValue("email", utilisateur.getEmail());
        params.addValue("telephone", utilisateur.getTelephone());
        params.addValue("rue", utilisateur.getRue());
        params.addValue("code_postal", utilisateur.getCode_postal());
        params.addValue("ville", utilisateur.getVille());
        params.addValue("credit", utilisateur.getCredit());
        params.addValue("administrateur", utilisateur.isAdministrateur());
        params.addValue("mot_de_passe", utilisateur.getMot_de_passe());

        String sql = "insert into utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur, mot_de_passe) " +
                "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :credit, :administrateur, :mot_de_passe)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,params, keyHolder, new String[]{"no_utilisateur"});
        return (keyHolder.getKey()) == null ? null : keyHolder.getKey().intValue();
    }



}
