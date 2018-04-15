package org.idaferv.dronedriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Drone driver service.
 * 
 * It provides functions like {@link #getUrbanizations(double, double, int)}
 * that help a drone overfly urbanizations searching for how many pools are.
 * 
 * @author idaferv
 *
 */
@Service
public class DroneDriver {

	private static final Logger LOG = LoggerFactory.getLogger(DroneDriver.class);

	@Autowired
	private AuxiliarDroneDriver auxDriver;

	/**
	 * This function returns the urbanization ids within a given range from an
	 * starting point (x and y coordinates).
	 * 
	 * @param xCoordinate
	 *            x coordinate for starting point.
	 * @param yCoordinate
	 *            y coordinate for starting point.
	 * @param range
	 *            given distance to look for urbanizations.
	 * @return
	 */
	public List<Integer> getUrbanizations(double xCoordinate, double yCoordinate, int range) {

		LOG.info("Getting urbanizations for xCoordinate [{}], yCoordinate [{}] and range [{}]", xCoordinate,
				yCoordinate, range);
		List<Integer> urbanizations = new ArrayList<Integer>();

		Integer originUrbId = auxDriver.getUrbanizationId(xCoordinate, yCoordinate);
		LOG.debug("UrbanizationId for given coordinates: {}", originUrbId);

		// Adding main row urbanization ids.
		List<Integer> originRow = getRowIds(originUrbId, range);
		urbanizations.addAll(originRow);

		// Adding up and down rows within range
		Integer originIdUp = originUrbId;
		Integer originIdDown = originUrbId;
		for (int i = 1; i <= range; i++) {
			// Getting upper row origin urbanization id
			originIdUp = auxDriver.getAdjacentUrbanization(originIdUp, AdjacencyDirection.UP);
			// Getting upper row urbanization ids.
			urbanizations.addAll(getRowIds(originIdUp, range));
			// Getting down row origin urbanization id
			originIdDown = auxDriver.getAdjacentUrbanization(originIdDown, AdjacencyDirection.DOWN);
			// Getting down row urbanization ids.
			urbanizations.addAll(getRowIds(originIdDown, range));
		}

		Collections.sort(urbanizations);

		LOG.info("Urbanization ids for xCoordinate [{}], yCoordinate [{}] and range [{}] are: {}", xCoordinate,
				yCoordinate, range, urbanizations);
		return urbanizations;
	}

	/**
	 * Returns all urbanization ids in a row given the center/origin urbanization id and a
	 * range.
	 * 
	 * @param originUrbId
	 *            urbanization id from the origin/center of the row.
	 * @param range
	 *            distance to look for.
	 * @return
	 */
	private List<Integer> getRowIds(Integer originUrbId, int range) {
		LOG.debug("Getting row urbanization ids for originUrbanization [{}] and range [{}]", originUrbId, range);
		List<Integer> rowIds = new ArrayList<Integer>();
		rowIds.add(originUrbId);
		Integer idLeft = originUrbId;
		Integer idRight = originUrbId;
		for (int i = 1; i <= range; i++) {
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
