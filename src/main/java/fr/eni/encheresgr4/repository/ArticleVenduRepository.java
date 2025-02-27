package fr.eni.encheresgr4.repository;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Utilisateur;
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
public class ArticleVenduRepository implements CrudInterface<ArticleVendu> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public ArticleVendu findOneById(int id) {
        try {
            String sql = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, etat_vente from articles_vendus WHERE no_article = ?";
            return jdbcTemplate.queryForObject(sql, new rowMapper(categorieRepository), id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ArticleVendu> findAll() {
        String sql = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, etat_vente from articles_vendus";
        return jdbcTemplate.query(sql, new rowMapper(categorieRepository));
    }

    @Override
    public void save(ArticleVendu articleVendu) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_article", articleVendu.getNo_article())
                .addValue("nom_article", articleVendu.getNom_article())
                .addValue("description", articleVendu.getDescription())
                .addValue("date_debut_encheres", articleVendu.getDate_debut_encheres())
                .addValue("date_fin_encheres", articleVendu.getDate_fin_encheres())
                .addValue("prix_initial", articleVendu.getPrix_initial())
                .addValue("prix_vente", articleVendu.getPrix_vente())
                .addValue("etat_vente", articleVendu.getEtat_vente())
                .addValue("no_categorie", articleVendu.getNo_categorie().getNo_categorie())
                .addValue("no_utilisateur", articleVendu.getNo_utilisateur().getNo_utilisateur())
                ;
        if (articleVendu.getNo_article() == 0){
            // ajout
            String sql =   "INSERT INTO public.articles_vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente)" +
                    "VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie, :etat_vente);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_article"});
            // recup l'id auto générer
//            System.out.println(keyHolder.getKey());
        }else{
            // modif
            String sql =    "UPDATE articles_vendus " +
                    "SET nom_article=:nom_article, description=:description, date_debut_encheres=:date_debut_encheres, date_fin_encheres=:date_fin_encheres, prix_initial=:prix_initial, prix_vente=:prix_vente, no_utilisateur=:no_utilisateur, no_categorie=:no_categorie, etat_vente=:etat_vente " +
                    "WHERE no_article = :no_article;";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_article"});
        }
    }

    @Override
    public void delete(ArticleVendu articleVendu) {

    }

    static class rowMapper implements RowMapper<ArticleVendu> {

        private CategorieRepository categorieRepository;

        public rowMapper(CategorieRepository categorieRepository) {
            this.categorieRepository = categorieRepository;
        }

        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleVendu articleVendu = new ArticleVendu();
            articleVendu.setNo_article(rs.getInt("no_article"));
            articleVendu.setNom_article(rs.getString("nom_article"));
            articleVendu.setDescription(rs.getString("description"));
//            articleVendu.setDate_debut_encheres(rs.getTimestamp("date_debut_encheres"));
//            articleVendu.setDate_fin_encheres(rs.getTimestamp("date_fin_encheres"));
            articleVendu.setPrix_initial(rs.getInt("prix_initial"));
            articleVendu.setPrix_vente(rs.getInt("prix_vente"));
            articleVendu.setEtat_vente(rs.getString("etat_vente"));
            articleVendu.setNo_categorie(categorieRepository.findOneById(rs.getInt("no_categorie")));
            // TODO A CHANGER AVEC LE REPO USER
            articleVendu.setNo_utilisateur(new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true));
            return articleVendu;
        }
    }

}
