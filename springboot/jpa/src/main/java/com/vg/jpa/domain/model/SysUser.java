package com.vg.jpa.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_user")
public class SysUser  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "dept_id")
	private Integer deptId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "age")
	private Integer age;

	@Column(name = "version")
	private Integer version;

	@Column(name = "creator")
	private Integer creator;

	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "modifier")
	private Integer modifier;

	@Column(name = "modify_time")
	private java.util.Date modifyTime;
}
