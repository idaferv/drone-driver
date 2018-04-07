package org.idaferv.dronedriver;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.idaferv.dronedriver.DroneDriver;
import org.junit.Test;

public class DroneDriverTest {

	private static final double X_COORDINATE = 38.56889;
	private static final double Y_COORDINATE = 40.511107;

	private static final List<Integer> range1Result = Arrays.asList(7, 8, 9, 12, 13, 14, 17, 18, 19);
	private static final List<Integer> range2Result = Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 13, 15, 16, 20, 21, 22, 23, 24,25);

	@Test
	public void getUrbanizationsRange1() {

		// Given
		int range = 1;
		DroneDriver droneDriver = new DroneDriver();

		// When
		List<Integer> urbs = droneDriver.getUrbanizations(X_COORDINATE, Y_COORDINATE, range);

		// Then
		assertEquals(range1Result, urbs);
	}

	@Test
	public void getUrbanizationsRange2() {

		// Given
		int range = 2;
		DroneDriver droneDriver = new DroneDriver();

		// When
		List<Integer> urbs = droneDriver.getUrbanizations(X_COORDINATE, Y_COORDINATE, range);

		// Then
		assertEquals(range2Result, urbs);

	}

}
