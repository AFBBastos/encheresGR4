package fr.eni.encheresgr4.model;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class Enchere {

    private Date date_enchere;

    @NotBlank(message = "Veuillez saisir un montant")
    private int montant_enchere;

    private Utilisateur no_utilisateur;
    private ArticleVendu no_article;

    public Enchere() {
    }

    public Enchere(Date date_enchere, int montant_enchere, Utilisateur no_utilisateur, ArticleVendu no_article) {
        this.date_enchere = date_enchere;
        this.montant_enchere = montant_enchere;
        this.no_utilisateur = no_utilisateur;
        this.no_article = no_article;
    }

    public Date getDate_enchere() {
        return date_enchere;
    }

    public void setDate_enchere(Date date_enchere) {
        this.date_enchere = date_enchere;
    }

    public int getMontant_enchere() {
        return montant_enchere;
    }

    public void setMontant_enchere(int montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    public Utilisateur getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(Utilisateur no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public ArticleVendu getNo_article() {
        return no_article;
    }

    public void setNo_article(ArticleVendu no_article) {
        this.no_article = no_article;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "dateEnchere=" + date_enchere +
                ", montant_enchere=" + montant_enchere +
                '}';
    }
}
