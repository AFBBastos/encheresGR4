package fr.eni.encheresgr4.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public class Utilisateur implements UserDetails {

    private int no_utilisateur;

    @NotBlank(message = "Veuillez saisir un pseudo")
    private String pseudo;

    @NotBlank(message = "Veuillez saisir un nom")
    private String nom;

    @NotBlank(message = "Veuillez saisir un prénom")
    private String prenom;

    @NotBlank(message = "Veuillez saisir une adresse mail")
    @Email(message = "Veuillez saisir une adresse valide")
    @Size(max = 100, message = "L'adresse mail ne doit pas dépasser 100 caractères")
    private String email;

    @Size(max = 10, message = "Le numéro de téléphone dois comporter exactement 10 chiffres")
    private String telephone;

    @NotBlank(message = "Veuillez saisir une rue")
    private String rue;

    @NotBlank(message = "Veuillez saisir un code postal")
    @Size(min = 5, max = 5, message = "Le code postal dois comporter exactement 5 chiffres")
    private String code_postal;

    @NotBlank(message = "Veuillez saisir une ville")
    private String ville;

    @NotBlank(message = "Veuillez saisir un mot de passe")
    private String mot_de_passe;

    private int credit = 0;
    private boolean administrateur = false;

    public Utilisateur(int noUtilisateur,
                       String pseudo,
                       String nom,
                       String prenom,
                       String email,
                       String telephone,
                       String rue,
                       String code_postal,
                       String ville,
                       String mot_de_passe,
                       int credit,
                       boolean administrateur) {
        this.no_utilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.mot_de_passe = mot_de_passe;
        this.credit = credit;
        this.administrateur = administrateur;
    }

    public Utilisateur() {
    }

    public Utilisateur(int i, String pseudo, String lastName, String name, String email, String telephone, String rue, String codePostal, String ville, String motDePasse) {
    }

    public int getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(int no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "noUtilisateur=" + no_utilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                ", motDePasse='" + mot_de_passe + '\'' +
                ", credit=" + credit +
                ", administrateur='" + administrateur + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.mot_de_passe;
    }

    @Override
    public String getUsername() {
        return this.pseudo;
    }
}