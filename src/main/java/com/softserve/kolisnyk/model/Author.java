package com.softserve.kolisnyk.model;

import java.util.Date;

public class Author {

  private int id;
  private String name;
  private String surName;
  private Date dateBorn;
  private Date dateDeath;

  public Author() {
  }

  public Author(int id, String name, String surName, Date dateBorn, Date dateDeath) {
    this.id = id;
    this.name = name;
    this.surName = surName;
    this.dateBorn = dateBorn;
    this.dateDeath = dateDeath;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurName() {
    return surName;
  }

  public void setSurName(String surName) {
    this.surName = surName;
  }

  public Date getDateBorn() {
    return dateBorn;
  }

  public void setDateBorn(Date dateBorn) {
    this.dateBorn = dateBorn;
  }

  public Date getDateDeath() {
    return dateDeath;
  }

  public void setDateDeath(Date dateDeath) {
    this.dateDeath = dateDeath;
  }
}
