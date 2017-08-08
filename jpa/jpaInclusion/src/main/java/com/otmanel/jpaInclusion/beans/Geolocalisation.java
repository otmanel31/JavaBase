package com.otmanel.jpaInclusion.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Geolocalisation {
	private double longitude;
	private double latitude;
}
