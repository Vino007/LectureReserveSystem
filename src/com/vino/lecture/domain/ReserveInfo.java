package com.vino.lecture.domain;

import org.springframework.stereotype.Component;

@Component
public class ReserveInfo {
	private long id;
	private String name;
	private String username;
	private long lectureId;
	private int attence;
	public int getAttence() {
		return attence;
	}

	public void setAttence(int attence) {
		this.attence = attence;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getLectureId() {
		return lectureId;
	}
	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}
	
	
}
