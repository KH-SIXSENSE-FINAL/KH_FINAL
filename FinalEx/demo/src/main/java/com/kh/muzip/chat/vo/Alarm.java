package com.kh.muzip.chat.vo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alarm {

	@Id
	private String alarmNo;
	
	private String receiverNo;
	private String senderNo;
	private String alarmKind;
	private String alarmMessage;
	private Date createDate;
	private String checkStatus;
	private String status;
}
