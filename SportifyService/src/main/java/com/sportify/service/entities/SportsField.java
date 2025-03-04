package com.sportify.service.entities;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "sports_fields")
public class SportsField extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type; // Loại sân (bóng đá, cầu lông, tennis,...)

    @Column(name = "size")
    private String size;

    @Column(name = "price_per_hour")
    private Double pricePerHour;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @Column(name = "max_players", nullable = true)
    private Integer maxPlayers; // Số người chơi tối đa

    @Column(name = "sub_players", nullable = true)
    private Integer subPlayers; // Số người dự bị
    
    @Column(name = "start_time", nullable = true)
    private LocalTime startTime; // Giờ bắt đầu mặc định

    @Column(name = "end_time", nullable = true)
    private LocalTime endTime; // Giờ kết thúc mặc định
    @Column(name = "date", nullable = true)
    private Date date; // Giờ kết thúc mặc định
    
    @ManyToOne
    @JoinColumn(name = "sports_center_id", nullable = false)
    private SportsCenter sportsCenter;
    
    @OneToMany(mappedBy = "sportsField", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();
}

