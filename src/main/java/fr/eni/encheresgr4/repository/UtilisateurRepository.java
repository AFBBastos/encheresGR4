package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
