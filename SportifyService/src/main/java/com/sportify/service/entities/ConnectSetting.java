package com.sportify.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "connect_settings")
public class ConnectSetting extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "week_day")
	private String weekDay;

	@Column(name = "from_time")
	private int fromTime;

	@Column(name = "to_time")
	private int toTime;

	@Column(name = "age_max")
	private int ageMax;

	@Column(name = "age_min")
	private int ageMin;

	@Column(name = "gender_find")
	private String genderFind;

	@Column(name = "status")
	private int status;


}
