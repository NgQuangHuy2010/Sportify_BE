package com.sportify.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "connect_settings")
public class ConnectSetting extends AbstractEntity {

	private static final long serialVersionUID = 979959609007334257L;

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
	
	//Relationship:
    @OneToOne()
	@JoinColumn(name = "user_id")
	private UserProfile userProfile;


}
