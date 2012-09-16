package com.qut.spc.postcode;

import java.util.HashMap;
import java.util.Map;

public class PostcodeUtil {
	/**
	 * 2000 - NSW
	 * 2900 - ACT
	 * 3000 - VIC
	 * 4000 - QLD
	 * 5000 - SA
	 * 6000 - WA
	 * 7000 - TAS
	 * 0800 - NT
	 */
	private static Map<int[], String> postcodeMapping = new HashMap<int[], String>() {
		{
			put(new int[] { 1000, 2599 }, "2000");
			put(new int[] { 2619, 2898 }, "2000");
			put(new int[] { 2921, 2999 }, "2000");

			put(new int[] { 2600, 2618 }, "2900");
			put(new int[] { 2900, 2920 }, "2900");
			put(new int[] { 200, 299 },   "2900");

			put(new int[] { 3000, 3999 }, "3000");
			put(new int[] { 8000, 8999 }, "3000");

			put(new int[] { 4000, 4999 }, "4000");
			put(new int[] { 9000, 9999 }, "4000");

			put(new int[] { 5000, 5799 }, "5000");
			put(new int[] { 5800, 5999 }, "5000");

			put(new int[] { 6000, 6797 }, "6000");
			put(new int[] { 6800, 6999 }, "6000");

			put(new int[] { 7000, 7799 }, "7000");
			put(new int[] { 7800, 7999 }, "7000");

			put(new int[] { 800, 899 },   "0800");
			put(new int[] { 900, 999 },   "0800");

		}
	};

	public static String transformPostcode(String postcode)
			throws IllegalArgumentException {

		if (validatePostcode(postcode)) {
			int p = Integer.parseInt(postcode);
			for (int[] range : postcodeMapping.keySet()) {
				if (p <= range[1] && p >= range[0]) {
					return postcodeMapping.get(range);
				}
			}
		}
		throw new IllegalArgumentException("Postcode couldn't be recognized");
	}

	public static boolean validatePostcode(String postcode)
			throws IllegalArgumentException {

		if (postcode.isEmpty())
			return true;
		
		try {
			Integer.parseInt(postcode);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Postcodes should only contain numbers. " + postcode);
		}
		if (postcode.length() != 4) {
			throw new IllegalArgumentException(
					"Postcodes should consist of four numbers" + postcode);
		}
		return true;
	}

}
