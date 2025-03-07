package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utilisateurId")
    private long utilisateurId;

    private String nom;
    private String prenom;
    private int age;
    private String genre;
    private String email;
    private String password;

    @OneToMany(mappedBy = "utilisateur")
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "utilisateur")
    private List<Programme> programmes;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Utilisateur_Pathologie> pathologies;



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
    public void setEvaluations(List<Evaluation> evaluations) { this.evaluations = evaluations;}
    public void setProgrammes(List<Programme> programmes) { this.programmes = programmes;}
    public void setPathologies(List<Utilisateur_Pathologie> pathologies) { this.pathologies = pathologies;}

    public long getUtilisateurId() { return utilisateurId;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public int getAge() {return age;}
    public String getGenre() {return genre;}
    public List<Evaluation> getEvaluations() {return evaluations;}
    public List<Programme> getProgrammes() {return programmes;}
    public List<Utilisateur_Pathologie> getPathologies() {return pathologies;}
}
