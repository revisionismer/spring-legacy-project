package com.nexchal.sample.web.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SampleDTOList {

	private List<SampleDTO> list = new ArrayList<>();
}
