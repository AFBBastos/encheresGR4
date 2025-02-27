package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategorieRepository implements CrudInterface<Categorie> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Categorie findOneById(int id) {
        try {
            String sql = "select no_categorie, libelle from categories WHERE no_categorie = ?";
            return jdbcTemplate.queryForObject(sql, new CategorieRepository.rowMapper(), id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Categorie> findAll() {
        String sql = "select * from categories";
        return jdbcTemplate.query(sql, new CategorieRepository.rowMapper());
    }

    @Override
    public void save(Categorie categorie) {

    }

    @Override
    public void delete(Categorie categorie) {

    }

    static class rowMapper implements RowMapper<Categorie> {

        @Override
        public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
        }
    }
}
