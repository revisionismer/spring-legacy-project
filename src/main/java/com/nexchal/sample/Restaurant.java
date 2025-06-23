package com.nexchal.sample;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@RequiredArgsConstructor
public class Restaurant {
	
	private final Chef chef;
	
}
