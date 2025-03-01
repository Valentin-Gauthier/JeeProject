package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // precise que MySQL support l'auto incrementation
    @Column(name = "utilisateurId")
    private long utilisateurId;
    private String nom;
    private String prenom;
    private int age;
    private String genre;
    private String email;
    private String password;

    // CONSTRUCTOR
    public Utilisateur() {}

    public Utilisateur(String nom, String prenom, int age, String genre,String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.age = age;
        this.genre = genre;
    }

    //GETTERS & SETTERS
    public void setUtilisateurId(long id) { this.utilisateurId = id;}
    public void setNom(String nom) {this.nom = nom;}
    public  void setPrenom(String prenom) {this.prenom = prenom;}
    public void setEmail(String email) { this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setAge(int age) {this.age = age;}
    public void setGenre(String  genre) {this.genre = genre;}

    public long getUtilisateurId() { return utilisateurId;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public int getAge() {return age;}
    public String getGenre() {return genre;}
}
