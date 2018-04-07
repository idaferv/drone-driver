package org.idaferv.dronedriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneDriver {
	
	private static final Logger LOG = LoggerFactory.getLogger(DroneDriver.class);

	@Autowired
	private AuxiliarDroneDriver auxDriver;
	
	public List<Integer> getUrbanizations(double xCoordinate, double yCoordinate, int range) {

		LOG.info("Getting urbanizations for xCoordinate [{}], yCoordinate [{}] and range [{}]", xCoordinate, yCoordinate, range);
		List<Integer> urbanizations = new ArrayList<Integer>();
		
		Integer originUrbId = auxDriver.getUrbanizationId(xCoordinate, yCoordinate);
		LOG.debug("UrbanizationId for given coordinates: {}", originUrbId);
		
		urbanizations.add(originUrbId);
		
		List<Integer> upRowUrbIds = getRowIds(originUrbId, range, AdjacencyDirection.UP);
		LOG.debug("Up row urbanization ids: {}", upRowUrbIds);
		urbanizations.addAll(upRowUrbIds);
		
		List<Integer> downRowIds = getRowIds(originUrbId, range, AdjacencyDirection.DOWN);
		LOG.debug("Down row urbanization ids: {}", downRowIds);
		urbanizations.addAll(downRowIds);
		
		List<Integer> leftColumnIds = getColumnIds(originUrbId, range, AdjacencyDirection.LEFT);
		LOG.debug("Left column urbanization ids: {}", leftColumnIds);
		urbanizations.addAll(leftColumnIds);
		
		List<Integer> rightColumnIds = getColumnIds(originUrbId, range, AdjacencyDirection.RIGHT);
		LOG.debug("Right column urbanization ids: {}", rightColumnIds);
		urbanizations.addAll(rightColumnIds);
		
		Collections.sort(urbanizations);
		
		LOG.info("Urbanization ids for xCoordinate [{}], yCoordinate [{}] and range [{}] are: {}", xCoordinate, yCoordinate, range, urbanizations);		
		return urbanizations;
	}

	private List<Integer> getRowIds(Integer originUrbId, int range, AdjacencyDirection adjacencyDirection) {
		LOG.debug("Getting row urbanization ids for adjacencyDirection [{}], range [{}] and originUrbanization [{}]", adjacencyDirection, range, originUrbId);
		List<Integer> rowIds = new ArrayList<Integer>();
		Integer idOrigin = findOrigin(originUrbId, range, adjacencyDirection);
		Integer idLeft = idOrigin;
		Integer idRight = idOrigin;
		rowIds.add(idOrigin);
		for (int i=1;i<=range;i++) {
			idRight = auxDriver.getAdjacentUrbanization(idRight, AdjacencyDirection.RIGHT);
			rowIds.add(idRight);
			idLeft = auxDriver.getAdjacentUrbanization(idLeft, AdjacencyDirection.LEFT);
			rowIds.add(idLeft);
		}
		LOG.debug("Row urbanization ids: {}", rowIds);
		return rowIds;
	}
	
	private List<Integer> getColumnIds(Integer originUrbId, int range, AdjacencyDirection adjacencyDirection) {
		LOG.debug("Getting column urbanization ids for adjacencyDirection [{}], range [{}] and originUrbanization [{}]",adjacencyDirection, range, originUrbId);
		List<Integer> columnIds = new ArrayList<Integer>();
		Integer idOrigin = findOrigin(originUrbId, range, adjacencyDirection);
		Integer idUp = idOrigin;
		Integer idDown = idOrigin;
		columnIds.add(idOrigin);
		for (int i=1;i<range;i++) {
			idDown = auxDriver.getAdjacentUrbanization(idDown, AdjacencyDirection.UP);
			columnIds.add(idDown);
			idUp = auxDriver.getAdjacentUrbanization(idUp, AdjacencyDirection.DOWN);
			columnIds.add(idUp);
		}
		LOG.debug("Column urbanization ids: {}", columnIds);
		return columnIds;
		
	}

	private Integer findOrigin(Integer originUrbId, int range, AdjacencyDirection adjacencyDirection) {
		LOG.debug("Finding origin for adjacencyDirection [{}], range [{}] and originUrbanization [{}]", adjacencyDirection, range, originUrbId);
		Integer originId = originUrbId;
		for (int i=1;i<=range;i++) {
			originId = auxDriver.getAdjacentUrbanization(originId, adjacencyDirection);
		}
		LOG.debug("Origin urbanization found: {}", originId);
		return originId;
	}


}
