/**
 * 
 */
package com.qut.spc.calculations;

import com.qut.spc.api.TotalCostCalculationAPI;

/**
 * @author Zuojun Chen
 *
 */
public class TotalCostCalculator implements TotalCostCalculationAPI {

	/* (non-Javadoc)
	 * @see com.qut.spc.api.TotalCostCalculationAPI#getSystemTotalCost(double, int, double, int, double)
	 */
	@Override
	public double getSystemTotalCost(double panelPrice, int panelsQuantity,
			double batterPrice, int batteriesQuantity, double intverterPrice)  throws IllegalArgumentException{
		double total = (panelPrice*panelsQuantity) + (batterPrice*batteriesQuantity) + panelsQuantity;
		return total;
	}

}
