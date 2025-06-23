package com.nexchal.sample.web.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter @Setter
@ToString
public class SampleTodoDTO {

	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
}
