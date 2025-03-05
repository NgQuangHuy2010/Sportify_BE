package com.sportify.service.entities;

import java.sql.Date;
import java.time.LocalDate;
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
@EqualsAndHashCode(callSuper = false)
@Table(name = "time_slot_sport")
public class TimeSlotSport extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "start_time", nullable = true)
	private String startTime;
	
	@Column(name = "end_time", nullable = true)
	private String endTime;
	
	@Column(name = "max_players", nullable = true)
	private Integer maxPlayers; 

	@Column(name = "sub_players", nullable = true)
	private Integer subPlayers;
	
	@Column(name = "date", nullable = true)
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "sports_field_id", nullable = false)
	private SportsField sportsField;

}
