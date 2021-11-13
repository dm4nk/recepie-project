package com.dm4nk.recipeproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    Recipe recipe;
    @Lob
    String recipeNotes;
}
