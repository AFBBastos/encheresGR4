package fr.eni.encheresgr4.model;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class ArticleVendu {

    private int no_article;

    @NotBlank(message = "Veuillez saisir un nom")
    private String nom_article;

    @NotBlank(message = "Veuillez saisir une description")
    private String description;

    @NotBlank(message = "Veuillez selectionner une date de début")
    private LocalDateTime date_debut_encheres;

    @NotBlank(message = "Veuillez selectionner une date de fin")
    private LocalDateTime date_fin_encheres;

    @NotBlank(message = "Veuillez saisir un prix initial")
    private int prix_initial;

    @NotBlank(message = "Veuillez saisir le prix initial de vente")
    private int prix_vente;

    private String etat_vente = "En cours";
    private String image;


    private Categorie no_categorie;
    private Retrait lieuRetrait;
    private Utilisateur no_utilisateur;

    public ArticleVendu(int no_article,
                        String nom_article,
                        String description,
                        LocalDateTime date_debut_encheres,
                        LocalDateTime date_fin_encheres,
                        int prix_initial,
                        int prix_vente,
                        String etat_vente,
                        Categorie no_categorie,
                        Utilisateur no_utilisateur,
                        String image) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.etat_vente = etat_vente;
        this.no_categorie = no_categorie;
        this.no_utilisateur = no_utilisateur;
        this.image = image;
    }

    public ArticleVendu(int no_article,
                        String nom_article,
                        String description,
                        LocalDateTime date_debut_encheres,
                        LocalDateTime date_fin_encheres,
                        int prix_initial,
                        int prix_vente,
                        String etat_vente,
                        Categorie no_categorie,
                        Retrait lieuRetrait,
                        String image) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.etat_vente = etat_vente;
        this.no_categorie = no_categorie;
        this.lieuRetrait = lieuRetrait;
        this.image = image;
    }

    public ArticleVendu() {
    }

    public int getNo_article() {
        return no_article;
    }

    public void setNo_article(int no_article) {
        this.no_article = no_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate_debut_encheres() {
        return date_debut_encheres;
    }

    public void setDate_debut_encheres(LocalDateTime date_debut_encheres) {
        this.date_debut_encheres = date_debut_encheres;
    }

    public LocalDateTime getDate_fin_encheres() {
        return date_fin_encheres;
    }

    public void setDate_fin_encheres(LocalDateTime date_fin_encheres) {
        this.date_fin_encheres = date_fin_encheres;
    }

    public int getPrix_initial() {
        return prix_initial;
    }

    public void setPrix_initial(int prix_initial) {
        this.prix_initial = prix_initial;
    }

    public int getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(int prix_vente) {
        this.prix_vente = prix_vente;
    }

    public String getEtat_vente() {
        return etat_vente;
    }

    public void setEtat_vente(String etat_vente) {
        this.etat_vente = etat_vente;
    }

    public Categorie getNo_categorie() {
        return no_categorie;
    }

    public void setNo_categorie(Categorie no_categorie) {
        this.no_categorie = no_categorie;
    }

    public Retrait getLieuRetrait() {
        return lieuRetrait;
    }

    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public Utilisateur getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(Utilisateur no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ArticleVendu{" +
                "no_article=" + no_article +
                ", nom_article='" + nom_article + '\'' +
                ", description='" + description + '\'' +
                ", date_debut_encheres=" + date_debut_encheres +
                ", date_fin_encheres=" + date_fin_encheres +
                ", prix_initial=" + prix_initial +
                ", prix_vente=" + prix_vente +
                ", etat_vente='" + etat_vente + '\'' +
                ", no_categorie=" + no_categorie +
                ", lieuRetrait=" + lieuRetrait +
                ", no_utilisateur=" + no_utilisateur +
                '}';
    }
}
