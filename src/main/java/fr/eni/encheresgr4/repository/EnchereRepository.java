package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.Enchere;
import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class EnchereRepository implements CrudInterface<Enchere>{

    @Autowired
    private ArticleVenduRepository articleVenduRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Enchere findOneById(int id) {
        return null;
    }

    @Override
    public List<Enchere> findAll() {
        String sql = "select * from enchere order by date_enchere asc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Enchere.class));
    }

    @Override
    public int save(Enchere enchere) {

        // récupération de l'utilisateur
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();
        currentUser = utilisateurRepository.findOneById(currentUser.getNo_utilisateur());
        // récupération du dernier enchere
        List<Enchere> encheres = this.findAllByArticle(enchere.getNo_article().getNo_article());
        Enchere enchereExistant = encheres.isEmpty() ? null : encheres.get(0);

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("date_enchere", LocalDateTime.now())
            .addValue("montant_enchere", enchere.getMontant_enchere())
            .addValue("no_article", enchere.getNo_article().getNo_article())
            .addValue("no_utilisateur", enchere.getNo_utilisateur().getNo_utilisateur())
        ;

        // retire le montant
        String sql = "UPDATE utilisateurs " +
                "SET credit = ? " +
                "WHERE no_utilisateur = ?;";
        int nouveauMontantRetirer = currentUser.getCredit() - enchere.getMontant_enchere();
        jdbcTemplate.update(sql, nouveauMontantRetirer, currentUser.getNo_utilisateur());

        // rebourse le montant
        if (null != enchereExistant){
            sql = "UPDATE utilisateurs " +
                    "SET credit = ? " +
                    "WHERE no_utilisateur = ?;";
            int nouveauMontantRemb = enchereExistant.getNo_utilisateur().getCredit() + enchereExistant.getMontant_enchere();
            jdbcTemplate.update(sql, nouveauMontantRemb, enchereExistant.getNo_utilisateur().getNo_utilisateur());
        }

        // ajout

        sql = "insert into encheres (date_enchere, montant_enchere, no_article, no_utilisateur) " +
                "values (:date_enchere, :montant_enchere, :no_article, :no_utilisateur);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_article"});
        // recup l'id auto générer
        int id = (int) keyHolder.getKey();
        return keyHolder.getKey().intValue();
    }

    @Override
    public void delete(Enchere enchere) {

    }

    public List<Enchere> findAllByArticle(int id) {
        String sql = "select * from encheres WHERE no_article = ? order by date_enchere desc";
        return jdbcTemplate.query(sql, new rowMapper(articleVenduRepository, utilisateurRepository), id);
    }

    // ROW MAPPER

    static class rowMapper implements RowMapper<Enchere> {

        private ArticleVenduRepository articleVenduRepository;
        private UtilisateurRepository utilisateurRepository;

        public rowMapper(ArticleVenduRepository articleVenduRepository, UtilisateurRepository utilisateurRepository) {
            this.articleVenduRepository = articleVenduRepository;
            this.utilisateurRepository = utilisateurRepository;
        }

        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {

            Enchere enchere = new Enchere();
            enchere.setMontant_enchere(rs.getInt("montant_enchere"));
            enchere.setDate_enchere(OffsetDateTime.parse(rs.getString("date_enchere").replace( " " , "T")).toLocalDateTime());
            enchere.setNo_article(articleVenduRepository.findOneById(rs.getInt("no_article")));
            enchere.setNo_utilisateur(utilisateurRepository.findOneById(rs.getInt("no_utilisateur")));
            return enchere;
        }
    }
}
