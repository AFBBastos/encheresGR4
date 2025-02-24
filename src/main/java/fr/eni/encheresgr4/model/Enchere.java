package fr.eni.encheresgr4.model;

import java.util.Date;

public class Enchere {
    private Date dateEnchere;
    private int montant_enchere;

    private Utilisateur utilisateur;
    private ArticleVendu articleVendu;

    public Enchere(Date dateEnchere, int montant_enchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
        this.dateEnchere = dateEnchere;
        this.montant_enchere = montant_enchere;
        this.utilisateur = utilisateur;
        this.articleVendu = articleVendu;
    }

    public Enchere() {
    }

    public Date getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(Date dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontant_enchere() {
        return montant_enchere;
    }

    public void setMontant_enchere(int montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "dateEnchere=" + dateEnchere +
                ", montant_enchere=" + montant_enchere +
                '}';
    }
}
