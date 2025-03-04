package fr.eni.encheresgr4.model;

import jakarta.validation.constraints.NotBlank;

public class Categorie {

    private int no_categorie;

    @NotBlank(message = "Veuillez saisir une categorie")
    private String libelle;

    public Categorie(int noCategorie, String libelle) {
        this.no_categorie = noCategorie;
        this.libelle = libelle;
    }

    public Categorie() {
    }

    public int getNo_categorie() {
        return no_categorie;
    }

    public void setNo_categorie(int no_categorie) {
        this.no_categorie = no_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "noCategorie='" + no_categorie + '\'' +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
