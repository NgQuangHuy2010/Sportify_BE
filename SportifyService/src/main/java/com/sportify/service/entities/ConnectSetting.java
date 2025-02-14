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
	private Integer fromTime;

	@Column(name = "to_time")
	private Integer toTime;

	@Column(name = "age_max")
	private Integer ageMax;

	@Column(name = "age_min")
	private Integer ageMin;

	@Column(name = "gender_find")
	private String genderFind;

	@Column(name = "status")
	private Integer status;
	
	//Relationship:
    @OneToOne()
	@JoinColumn(name = "user_id")
	private UserProfile userProfile;


}
