package org.idaferv.dronedriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DroneDriver {
	
	private AuxiliarDroneDriver auxDriver = new AuxiliarDroneDriver();
	
	public List<Integer> getUrbanizations(double xCoordinate, double yCoordinate, int range) {

		List<Integer> urbanizations = new ArrayList<Integer>();
		
		Integer originUrbId = auxDriver.getUrbanizationId(xCoordinate, yCoordinate);
		
		urbanizations.add(originUrbId);
		
		List<Integer> upRowUrbIds = getRowIds(originUrbId, range, AdjacencyDirection.UP);
		urbanizations.addAll(upRowUrbIds);
		List<Integer> downRowIds = getRowIds(originUrbId, range, AdjacencyDirection.DOWN);
		urbanizations.addAll(downRowIds);
		List<Integer> leftColumnIds = getColumnIds(originUrbId, range, AdjacencyDirection.LEFT);
		urbanizations.addAll(leftColumnIds);
		List<Integer> rightColumnIds = getColumnIds(originUrbId, range, AdjacencyDirection.RIGHT);
		urbanizations.addAll(rightColumnIds);
		
		Collections.sort(urbanizations);
		
		return urbanizations;
	}

	private List<Integer> getRowIds(Integer originUrbId, int range, AdjacencyDirection adjacencyDirection) {
		List<Integer> rowIds = new ArrayList<Integer>();
		Integer idOrigin = findOrigin(adjacencyDirection, range, originUrbId);
		Integer idLeft = idOrigin;
		Integer idRight = idOrigin;
		rowIds.add(idOrigin);
		for (int i=1;i<=range;i++) {
			idRight = auxDriver.getAdjacentUrbanization(idRight, AdjacencyDirection.RIGHT);
			rowIds.add(idRight);
			idLeft = auxDriver.getAdjacentUrbanization(idLeft, AdjacencyDirection.LEFT);
			rowIds.add(idLeft);
		}
		return rowIds;
	}
	
	private List<Integer> getColumnIds(Integer urbOriginid, int rango, AdjacencyDirection adjacencyDirection) {
		List<Integer> columnIds = new ArrayList<Integer>();
		Integer idOrigin = findOrigin(adjacencyDirection, rango, urbOriginid);
		Integer idUp = idOrigin;
		Integer idDown = idOrigin;
		columnIds.add(idOrigin);
		for (int i=1;i<rango;i++) {
			idDown = auxDriver.getAdjacentUrbanization(idDown, AdjacencyDirection.UP);
			columnIds.add(idDown);
			idUp = auxDriver.getAdjacentUrbanization(idUp, AdjacencyDirection.DOWN);
			columnIds.add(idUp);
		}
		return columnIds;
		
	}

	private Integer findOrigin(AdjacencyDirection adjacencyDirection, int rango, Integer urbOriginid) {
		Integer originId = urbOriginid;
		for (int i=1;i<=rango;i++) {
			originId = auxDriver.getAdjacentUrbanization(originId, adjacencyDirection);
		}
		return originId;
	}


}
