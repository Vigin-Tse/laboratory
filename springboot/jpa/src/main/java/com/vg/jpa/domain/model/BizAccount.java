package com.vg.jpa.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "biz_account")
public class BizAccount  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "account_balance")
	private Double accountBalance;

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
