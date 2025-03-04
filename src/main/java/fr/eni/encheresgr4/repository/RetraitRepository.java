package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RetraitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Retrait> findAll() {
        String sql = "select no_article, rue, code_postal, ville from retraits";
        return jdbcTemplate.query(sql, new RetraitRepository.rowMapper());
    }

    public Retrait findOneById(int id) {
        try {
            String sql = "select no_article, rue, code_postal, ville from retraits WHERE no_article = ?";
            return jdbcTemplate.queryForObject(sql, new RetraitRepository.rowMapper(), id);
        }catch (Exception e) {
            return null;
        }
    }

    public void save(int idArticle, ArticleVendu articleVendu) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_article", idArticle)
                .addValue("rue", articleVendu.getLieuRetrait().getRue())
                .addValue("code_postal", articleVendu.getLieuRetrait().getCode_postal())
                .addValue("ville", articleVendu.getLieuRetrait().getVille())
                ;
        if (articleVendu.getNo_article() == 0){
            // ajout
            String sql = "INSERT INTO public.retraits(no_article, rue, code_postal, ville)" +
                    "VALUES (:no_article, :rue, :code_postal, :ville);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_article"});
            // recup l'id auto générer
//            System.out.println(keyHolder.getKey());
        }else{
            // modif
            String sql =    "UPDATE retraits " +
                    "SET rue=:rue, code_postal=:code_postal, ville=:ville " +
                    " WHERE no_article = :no_article;";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_article"});
        }
    }

    static class rowMapper implements RowMapper<Retrait> {

        public rowMapper() {
        }

        @Override
        public Retrait mapRow(ResultSet rs, int rowNum) throws SQLException {
            Retrait retrait = new Retrait();
            retrait.setNo_article(rs.getInt("no_article"));
            retrait.setRue(rs.getString("rue"));
            retrait.setCode_postal(rs.getString("code_postal"));
            retrait.setVille(rs.getString("ville"));
            return retrait;
        }
    }
}
