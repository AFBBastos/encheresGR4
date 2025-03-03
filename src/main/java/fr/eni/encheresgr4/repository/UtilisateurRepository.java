package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UtilisateurRepository implements CrudInterface<Utilisateur> {

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

    @Override
    public Utilisateur findOneById(int id) {
        String sql = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class), id);
    }

    @Override
    public List<Utilisateur> findAll() {
        String sql = "SELECT * FROM utilisateurs";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Utilisateur.class));
    }

    @Override
    public int save(Utilisateur utilisateur) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_utilisateur", utilisateur.getNo_utilisateur())
                .addValue("pseudo", utilisateur.getPseudo())
                .addValue("nom", utilisateur.getNom())
                .addValue("prenom", utilisateur.getPrenom())
                .addValue("email", utilisateur.getEmail())
                .addValue("telephone", utilisateur.getTelephone())
                .addValue("rue", utilisateur.getRue())
                .addValue("code_postal", utilisateur.getCode_postal())
                .addValue("ville", utilisateur.getVille())
                .addValue("credit", utilisateur.getCredit())
                .addValue("administrateur", utilisateur.isAdministrateur());

        if (utilisateur.getNo_utilisateur() == 0) {
            //ajout
            String sql = "insert into utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur) " +
                    "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :credit, :administrateur)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_utilisateur"});
            return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
        } else {
            //modif
            String sql = "UPDATE utilisateurs " +
                    "SET pseudo=:pseudo, nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, code_postal=:code_postal, ville=:ville, credit=:credit, administrateur=:administrateur " +
                    "WHERE no_utilisateur = :no_utilisateur;";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_utilisateur"});
            return (keyHolder.getKey()) == null ? 0 : keyHolder.getKey().intValue();
        }
    }

    public int saveWithPassword(Utilisateur utilisateur) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_utilisateur", utilisateur.getNo_utilisateur())
                .addValue("pseudo", utilisateur.getPseudo())
                .addValue("nom", utilisateur.getNom())
                .addValue("prenom", utilisateur.getPrenom())
                .addValue("email", utilisateur.getEmail())
                .addValue("telephone", utilisateur.getTelephone())
                .addValue("rue", utilisateur.getRue())
                .addValue("code_postal", utilisateur.getCode_postal())
                .addValue("ville", utilisateur.getVille())
                .addValue("credit", utilisateur.getCredit())
                .addValue("administrateur", utilisateur.isAdministrateur());

        if (utilisateur.getNo_utilisateur() == 0) {
            //ajout
            String password = new BCryptPasswordEncoder().encode(utilisateur.getPassword());
            params.addValue("mot_de_passe", password);
            String sql = "insert into utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur, mot_de_passe) " +
                    "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :credit, :administrateur, :mot_de_passe)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_utilisateur"});
            return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
        } else {
            //modif
            String sql = "UPDATE utilisateurs " +
                    "SET pseudo=:pseudo, nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, code_postal=:code_postal, ville=:ville, credit=:credit, administrateur=:administrateur, mot_de_passe=:mot_de_passe " +
                    "WHERE no_utilisateur = :no_utilisateur;";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_utilisateur"});
            return (keyHolder.getKey()) == null ? 0 : keyHolder.getKey().intValue();
        }
    }

    @Override
    public void delete(Utilisateur utilisateur) {
        String sql = "DELETE FROM utilisateurs WHERE no_utilisateur = ?";
    }

    //ROW MAPPER
    static class rowMapper implements RowMapper<Utilisateur> {

        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {

            Utilisateur user = new Utilisateur();
            user.setNo_utilisateur(rs.getInt("no_utilisateur"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setEmail(rs.getString("email"));
            user.setTelephone(rs.getString("telephone"));
            user.setRue(rs.getString("rue"));
            user.setCode_postal(rs.getString("code_postal"));
            user.setVille(rs.getString("ville"));
            user.setMot_de_passe(rs.getString("mot_de_passe"));
            user.setCredit(rs.getInt("credit"));
            user.setAdministrateur(false);
            return user;
        }
    }
}
