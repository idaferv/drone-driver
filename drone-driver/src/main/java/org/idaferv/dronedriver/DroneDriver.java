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
		
		// Adding main row urbanization ids.
		List<Integer> originRow = getRowIds(originUrbId, range);
		urbanizations.addAll(originRow);
		
		// Adding up and down rows
		Integer originIdUp = originUrbId;
		Integer originIdDown = originUrbId;
		for (int i=1;i<=range;i++) {
			originIdUp = auxDriver.getAdjacentUrbanization(originIdUp, AdjacencyDirection.UP);
			urbanizations.addAll(getRowIds(originIdUp, range));
			originIdDown = auxDriver.getAdjacentUrbanization(originIdDown, AdjacencyDirection.DOWN);
			urbanizations.addAll(getRowIds(originIdDown, range));		
		}
		
		Collections.sort(urbanizations);
		
		LOG.info("Urbanization ids for xCoordinate [{}], yCoordinate [{}] and range [{}] are: {}", xCoordinate, yCoordinate, range, urbanizations);		
		return urbanizations;
	}

	private List<Integer> getRowIds(Integer originUrbId, int range) {
		LOG.debug("Getting row urbanization ids for originUrbanization [{}] and range [{}]",originUrbId, range);
		List<Integer> rowIds = new ArrayList<Integer>();
		rowIds.add(originUrbId);
		Integer idLeft = originUrbId;
		Integer idRight = originUrbId;
		for (int i=1;i<=range;i++) {
			idRight = auxDriver.getAdjacentUrbanization(idRight, AdjacencyDirection.RIGHT);
			rowIds.add(idRight);
			idLeft = auxDriver.getAdjacentUrbanization(idLeft, AdjacencyDirection.LEFT);
			rowIds.add(idLeft);
		}
		Collections.sort(rowIds);
		LOG.debug("Row urbanization ids: {}", rowIds);
		return rowIds;
	}

}
