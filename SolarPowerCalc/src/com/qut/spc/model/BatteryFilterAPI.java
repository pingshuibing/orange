package com.qut.spc.model;

import java.util.List;

public interface BatteryFilterAPI extends ComponentFilterAPI {
	List<Battery> search() throws Exception;
}
