package com.example.myapplication;

public class Book {
    private String document;
    private String liv_emp;
    private String nb_chaise;

    private String     nb_etud;
    private String    nb_etud_existe ;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getLiv_emp() {
        return liv_emp;
    }

    public void setLiv_emp(String liv_emp) {
        this.liv_emp = liv_emp;
    }

    public String getNb_chaise() {
        return nb_chaise;
    }

    public void setNb_chaise(String nb_chaise) {
        this.nb_chaise = nb_chaise;
    }

    public String getNb_etud() {
        return nb_etud;
    }

    public void setNb_etud(String nb_etud) {
        this.nb_etud = nb_etud;
    }

    public String getNb_etud_existe() {
        return nb_etud_existe;
    }

    public void setNb_etud_existe(String nb_etud_existe) {
        this.nb_etud_existe = nb_etud_existe;
    }

    public Book() {
    }

    public Book(String document, String liv_emp, String nb_chaise, String nb_etud, String nb_etud_existe, String temp) {
        this.document = document;
        this.liv_emp = liv_emp;
        this.nb_chaise = nb_chaise;
        this.nb_etud = nb_etud;
        this.nb_etud_existe = nb_etud_existe;
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    private String temp;
}
