package org.idaferv.dronedriver;

import org.springframework.stereotype.Service;

/**
 * Auxiliar drone driver service.
 * 
 * The only reason to be separated from DroneDrive is to leave in DroneDriver
 * the function getUrbanizations alone which is the goal of the exercise.
 * 
 * It contains the functions supposed to be already developed.
 * 
 * @author idaferv
 *
 */

@Service
public class AuxiliarDroneDriver {

	public Integer getUrbanizationId(double xCoordinate, double yCoordinate) {
		// Only valid for ranges 1 and 2
		return 13;
	}

	public Integer getAdjacentUrbanization(Integer originUrbanizationId, AdjacencyDirection adjacencyDirection) {

		// Only valid for ranges 1 and 2
		// returning an Integer for simplicity
		switch (adjacencyDirection) {
			case UP:
				return originUrbanizationId + 5;
			case DOWN:
				return originUrbanizationId - 5;
			case RIGHT:
				return originUrbanizationId + 1;
			case LEFT:
				return originUrbanizationId - 1;
			default:
				return null;
		}
	}

}
