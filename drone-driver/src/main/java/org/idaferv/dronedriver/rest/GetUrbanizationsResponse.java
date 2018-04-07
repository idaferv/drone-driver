package org.idaferv.dronedriver.rest;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GetUrbanizationsResponse {
	
	private List<Integer> urbanizations;

	public List<Integer> getUrbanizations() {
		return urbanizations;
	}

	public void setUrbanizations(List<Integer> urbanizations) {
		this.urbanizations = urbanizations;
	}
	
	@Override
	public String toString() {
		return "{'urbanizations':" + urbanizations + "}";
	}


}
