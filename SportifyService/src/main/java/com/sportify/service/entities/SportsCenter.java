package com.sportify.service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name = "sports_centers")
public class SportsCenter extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "description")
    private String description;
    
    @Column(name = "open_door",nullable = true)
    private String open_door;

    @Column(name = "image")
    private String image;


    @OneToMany(mappedBy = "sportsCenter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SportsField> sportsFields = new ArrayList<>();
}

