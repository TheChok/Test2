package com.edu.car.mycar05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.edu.car.mycar06.Car;

@Configuration("appCtx.xml")
class CarTest {

	@Autowired
	Car car;

	@Test
	void test() {
		// fail("Not yet implemented");
	
		System.out.println(car.getTireBrand());
	}

}
