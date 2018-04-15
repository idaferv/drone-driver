package org.idaferv.dronedriver.rest;

import java.util.List;

import org.idaferv.dronedriver.DroneDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Service that provide access to getUrbanizations function from
 * DroneDriver.
 * 
 * @author idaferv
 *
 */
@RestController
public class DroneDriverRestController {

	private static final Logger LOG = LoggerFactory.getLogger(DroneDriverRestController.class);

	@Autowired
	private DroneDriver droneDriver;

	@Autowired
	private GetUrbanizationsResponse getUrbanizationsResponse;

	@RequestMapping(value = "/getUrbanizations", method = { RequestMethod.GET })
	public GetUrbanizationsResponse getUrbanizations(@ModelAttribute GetUrbanizationsRequest input) {

		LOG.info(
				"Request received at getUrbanizations. Input parameters are: xCoordinate [{}], yCoordinate [{}] and range [{}]",
				input.getxCoordinate(), input.getyCoordinate(), input.getRange());

		List<Integer> urbanizations = droneDriver.getUrbanizations(input.getxCoordinate(), input.getyCoordinate(),
				input.getRange());
		getUrbanizationsResponse.setUrbanizations(urbanizations);

		LOG.info("Response to request received at getUrbanizations is: {}", getUrbanizationsResponse);

		return getUrbanizationsResponse;
	}
}
