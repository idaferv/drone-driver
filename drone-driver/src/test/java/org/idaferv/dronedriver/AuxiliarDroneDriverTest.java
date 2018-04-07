package org.idaferv.dronedriver;

import static org.junit.Assert.*;

import org.idaferv.dronedriver.AdjacencyDirection;
import org.idaferv.dronedriver.AuxiliarDroneDriver;
import org.junit.Test;

public class AuxiliarDroneDriverTest {
	
	private static final Integer ORIGIN_URB_ID = 13;
	private static final Integer UP_URB_ID_RESULT = 18;
	private static final Integer DOWN_URB_ID_RESULT = 8;
	private static final Integer RIGHT_URB_ID_RESULT = 14;
	private static final Integer LEFT_URB_ID_RESULT = 12;
	
	@Test
	public void testObtenerAdyacenteArriba() {
		
		// Given
		AuxiliarDroneDriver auxDriver = new AuxiliarDroneDriver();
		
		// When
		Integer adjacentUrbId = auxDriver.getAdjacentUrbanization(ORIGIN_URB_ID, AdjacencyDirection.UP);
		
		// Then
		assertEquals(UP_URB_ID_RESULT, adjacentUrbId);
	}

	
	@Test
	public void testObtenerAdyacenteAbajo() {
		
		// Given
		AuxiliarDroneDriver auxDriver = new AuxiliarDroneDriver();
		
		// When
		Integer adjacentUrbId = auxDriver.getAdjacentUrbanization(ORIGIN_URB_ID, AdjacencyDirection.DOWN);
		
		// Then
		assertEquals(DOWN_URB_ID_RESULT, adjacentUrbId);
	}

	@Test
	public void testObtenerAdyacenteDerecha() {
		
		// Given
		AuxiliarDroneDriver auxDriver = new AuxiliarDroneDriver();
		
		// When
		Integer adjacentUrbId = auxDriver.getAdjacentUrbanization(ORIGIN_URB_ID, AdjacencyDirection.RIGHT);
		
		// Then
		assertEquals(RIGHT_URB_ID_RESULT, adjacentUrbId);
	}

	@Test
	public void testObtenerAdyacenteIzquierda() {
		
		// Given
		AuxiliarDroneDriver auxDriver = new AuxiliarDroneDriver();
		
		// When
		Integer adjacentUrbId = auxDriver.getAdjacentUrbanization(ORIGIN_URB_ID, AdjacencyDirection.LEFT);
		
		// Then
		assertEquals(LEFT_URB_ID_RESULT, adjacentUrbId);
	}

}
