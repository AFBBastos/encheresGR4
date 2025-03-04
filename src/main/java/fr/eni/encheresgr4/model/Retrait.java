package fr.eni.encheresgr4.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Retrait {

    @NotBlank(message = "Veuillez saisir une rue")
    private String rue;

    @NotBlank(message = "Veuillez saisir un code postal")
    @Size(min = 5, max = 5, message = "Le code postal dois comporter exactement 5 chiffres")
    private String code_postal;

    @NotBlank(message = "Veuillez saisir une ville")
    private String ville;

    private int no_article;

    public Retrait(String rue, String code_postal, String ville, int no_article) {
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.no_article = no_article;
    }

    public Retrait() {
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Retrait{" +
                "rue='" + rue + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
