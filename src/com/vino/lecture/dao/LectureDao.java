package com.vino.lecture.dao;


import org.springframework.stereotype.Repository;
import com.vino.lecture.domain.LectureInfo;

@Repository("lectureDao")
public class LectureDao extends CommonDaoImpl<LectureInfo> {
	
}
