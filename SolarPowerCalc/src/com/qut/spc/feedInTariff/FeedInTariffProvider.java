package com.qut.spc.feedInTariff;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.qut.spc.postcode.PostcodeUtil;

public class FeedInTariffProvider {

	private static Map<String,double[]> locationToFeedInTariffIntensity=new HashMap<String, double[]>(){
		{
			put("4000",new double[]{0.44,1.0}); //QLD
			put("5000",new double[]{0.44,1.0}); //SA
			put("2000",new double[]{0.103,1.0}); //NSW
			put("3000",new double[]{0.58,1.0}); //VIC --faked
			put("2900",new double[]{0.33,1.0}); //ACT -- faked
			put("6000",new double[]{0.44,1.0}); //WA
			put("0800",new double[]{0.2177,1.0}); //NT
			
		}};
		
		public static double getFeedInTariffByPostcode(String location) throws IllegalArgumentException {
			if(PostcodeUtil.validatePostcode(location)){
				String postcode =PostcodeUtil.transformPostcode(location);
				if(locationToFeedInTariffIntensity.containsKey(postcode))
					return locationToFeedInTariffIntensity.get(postcode)[0];			
			}
			
			return average(locationToFeedInTariffIntensity.values(),0);
		}
	
		private static double average(Collection<double[]> values,int index) {
			double sum=0;
			for(double[] d:values){
				if(index<d.length&&index>=0)
					sum+=d[index];
			}
			return sum/values.size();
		}

		public static double getFeedInTariff(String location) {
			if(PostcodeUtil.validatePostcode(location)){
				String postcode=PostcodeUtil.transformPostcode(location);
				if(locationToFeedInTariffIntensity.containsKey(postcode))
					return locationToFeedInTariffIntensity.get(location)[1];
			}
			return average(locationToFeedInTariffIntensity.values(),1);
		}
}
