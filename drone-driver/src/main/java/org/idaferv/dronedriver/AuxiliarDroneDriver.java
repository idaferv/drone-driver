package org.idaferv.dronedriver;

import org.springframework.stereotype.Service;

@Service
public class AuxiliarDroneDriver {
	
	public Integer getUrbanizationId(double xCoordinate, double yCoordinate) {	
		// Only valid for ranges 1 and 2
		return 13;
	}
	
	public Integer getAdjacentUrbanization(Integer originUrbanizationId, AdjacencyDirection adjacencyDirection) {
		
		// Only valid for ranges 1 and 2
		// De primeras es irrelevante el valor de identificadorUrbanizaciónOrigen porque siempre va a ser 13
		switch (adjacencyDirection) {
			case UP : 
				return originUrbanizationId + 5;
			case DOWN :
				return originUrbanizationId - 5;
			case RIGHT :
				return originUrbanizationId + 1;
			case LEFT :
				return originUrbanizationId - 1;
			default :
				return null;
		}
	}

}
