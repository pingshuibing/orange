package com.qut.spc.controller;


import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.marshalling.SolarComponentUnmarshaller;
import com.qut.spc.model.ComponentContainer;
import com.qut.spc.model.InverterContainer;
import com.qut.spc.model.PanelContainer;

@Path("/populate/")
public class DBPopulator {

	
	private SolarComponentUnmarshaller um;

	String inputPanel=" <panels> <panel> <manufacturer>UNI-SOLAR</manufacturer> <capacity>128</capacity> <model>PVL 128</model> <dimensions>5486*394*4</dimensions> <voltage>24</voltage> <operatingCurrent>4.13</operatingCurrent> <peakPower>100W-150W</peakPower> <weight>7.7</weight> <warranty>20</warranty> <price>525</price> </panel> <panel> <manufacturer>UNI-SOLAR</manufacturer> <capacity>136</capacity> <model>PVL 136</model> <dimensions>5486*394*4</dimensions> <voltage>24</voltage> <operatingCurrent>4.13</operatingCurrent> <peakPower>100W-151W</peakPower> <weight>7.8</weight> <warranty>20</warranty> <price>550</price> </panel> <panel> <manufacturer>SUNGEAR</manufacturer> <capacity>10</capacity> <model>FLX 10</model> <dimensions>555*375*20</dimensions> <voltage>12</voltage> <operatingCurrent>0.6</operatingCurrent> <peakPower>Under 50W</peakPower> <weight>0.6</weight> <warranty>20</warranty> <price>199</price> </panel> <panel> <manufacturer>SOLAR-E</manufacturer> <capacity>20</capacity> <model>SE20M</model> <dimensions>638*304*35</dimensions> <voltage>12</voltage> <operatingCurrent>1.14</operatingCurrent> <peakPower>Under 50W</peakPower> <weight>2.5</weight> <warranty>12</warranty> <price>80</price> </panel> <panel> <manufacturer>SOLAR-E</manufacturer> <capacity>40</capacity> <model>SE40M</model> <dimensions>638*554*35</dimensions> <voltage>12</voltage> <operatingCurrent>2.29</operatingCurrent> <peakPower>Under 50W</peakPower> <weight>4.34</weight> <warranty>12</warranty> <price>150</price> </panel> <panel> <manufacturer>SOLAR-E</manufacturer> <capacity>55</capacity> <model>SE55W</model> <dimensions>816*554*35</dimensions> <voltage>12</voltage> <operatingCurrent>3.16</operatingCurrent> <peakPower>50W-100W</peakPower> <weight>5.5</weight> <warranty>12</warranty> <price>190</price> </panel> <panel> <manufacturer>SOLAR-E</manufacturer> <capacity>195</capacity> <model>CMS195M</model> <dimensions>1580*808*46</dimensions> <voltage>24</voltage> <operatingCurrent>5.73</operatingCurrent> <peakPower>150W-200W</peakPower> <weight>16</weight> <warranty>25</warranty> <price>370</price> </panel> <panel> <manufacturer>K-Solar</manufacturer> <capacity>80</capacity> <model>KW80M</model> <dimensions>1068*541*38</dimensions> <voltage>12</voltage> <operatingCurrent>4.97</operatingCurrent> <peakPower>50W-100W</peakPower> <weight>7</weight> <warranty>25</warranty> <price>220</price> </panel> <panel> <manufacturer>K-Solar</manufacturer> <capacity>130</capacity> <model>KWT130P</model> <dimensions>1491*671*38</dimensions> <voltage>12</voltage> <operatingCurrent>7.68</operatingCurrent> <peakPower>100W-150W</peakPower> <weight>14</weight> <warranty>25</warranty> <price>320</price> </panel> <panel> <manufacturer>K-Solar</manufacturer> <capacity>250</capacity> <model>1642*979*38</model> <dimensions>8.23</dimensions> <voltage>Over200W</voltage> <operatingCurrent>19</operatingCurrent> <peakPower>25</peakPower> <weight>490</weight> </panel> <panel> <manufacturer>SHARP</manufacturer> <capacity>80</capacity> <model>NE-080T1J</model> <dimensions>1214*545*35</dimensions> <voltage>12</voltage> <operatingCurrent>4.63</operatingCurrent> <peakPower>50W-100W</peakPower> <weight>8.5</weight> <warranty>25</warranty> <price>490</price> </panel> <panel> <manufacturer>LG</manufacturer> <capacity>215</capacity> <model>1649*993*42</model> <dimensions>35</dimensions> <voltage>7.68</voltage> <operatingCurrent>Over200W</operatingCurrent> <peakPower>19.5</peakPower> <weight>25</weight> <warranty>450</warranty> </panel> <panel> <manufacturer>LG</manufacturer> <capacity>225</capacity> <model>1649*993*42</model> <dimensions>36</dimensions> <voltage>7.76</voltage> <operatingCurrent>Over200W</operatingCurrent> <peakPower>19.5</peakPower> <weight>25</weight> <warranty>470</warranty> </panel> <panel> <manufacturer>AUSSIE BATTERIES &amp; SOLAR</manufacturer> <capacity>60</capacity> <model>60W</model> <dimensions>840*541*35</dimensions> <voltage>12</voltage> <operatingCurrent>3.5</operatingCurrent> <peakPower>50W-100W</peakPower> <weight>5.5</weight> <warranty>25</warranty> <price>175</price> </panel> <panel> <manufacturer>AUSSIE BATTERIES &amp; SOLAR</manufacturer> <capacity>40</capacity> <model>40W</model> <dimensions>630*540*35</dimensions> <voltage>12</voltage> <operatingCurrent>2.3</operatingCurrent> <peakPower>Under 50W</peakPower> <weight>4.4</weight> <warranty>25</warranty> <price>149</price> </panel> <panel> <manufacturer>AUSSIE BATTERIES &amp; SOLAR</manufacturer> <capacity>80</capacity> <model>80W</model> <dimensions>1195*541*35</dimensions> <voltage>12</voltage> <operatingCurrent>4.45</operatingCurrent> <peakPower>50W-100W</peakPower> <weight>7.9</weight> <warranty>25</warranty> <price>219</price> </panel> <panel> <manufacturer>AUSSIE BATTERIES &amp; SOLAR</manufacturer> <capacity>100</capacity> <model>100W</model> <dimensions>1325*540*40</dimensions> <voltage>12</voltage> <operatingCurrent>4.98</operatingCurrent> <peakPower>50W-100W</peakPower> <weight>8.9</weight> <warranty>25</warranty> <price>259</price> </panel> <panel> <manufacturer>AUSSIE BATTERIES &amp; SOLAR</manufacturer> <capacity>120</capacity> <model>120W</model> <dimensions>840*1070*35</dimensions> <voltage>12</voltage> <operatingCurrent>6.67</operatingCurrent> <peakPower>100W-150W</peakPower> <weight>15.3</weight> <warranty>25</warranty> <price>279</price> </panel> <panel> <manufacturer>AUSSIE BATTERIES &amp; SOLAR</manufacturer> <capacity>140</capacity> <model>140W</model> <dimensions>1482*676*35</dimensions> <voltage>12</voltage> <operatingCurrent>7.76</operatingCurrent> <peakPower>100-150W</peakPower> <weight>11.7</weight> <warranty>25</warranty> <price>309</price> </panel> <panel> <manufacturer>AUSSIE BATTERIES &amp; SOLAR</manufacturer> <capacity>200</capacity> <model>200W</model> <dimensions>1195*1064*45</dimensions> <voltage>12</voltage> <operatingCurrent>10.93</operatingCurrent> <peakPower>150-200W</peakPower> <weight>15</weight> <warranty>25</warranty> <price>429</price> </panel> </panels> ";
	String inputBattery="";
	String inputInverter="";
	public DBPopulator(){
		um = new SolarComponentUnmarshaller();		
	}
	public DBPopulator(SolarComponentUnmarshaller um){
		this.um=um;
	}
	
	
	@GET
	@Produces("text/html")
	@Path("/")
	public String populateDB(@QueryParam("panel") @DefaultValue("") String panels, @QueryParam("inverter") @DefaultValue("") String inverters, @QueryParam("battery") @DefaultValue("") String batteries) throws InvalidArgumentException{
		try{
			String ret="";
			if(!panels.equals("")){
				ret+="Panels: \n";
				ret+= unmarshal(PanelContainer.class,inputPanel)+"\n";
			}
			
			if(!inverters.equals("")){
				ret+="Inverters: \n";
				ret+= unmarshal(InverterContainer.class,inputInverter)+"\n";
			}
			
			if(!batteries.equals("")){
				ret+="Batteries:\n";
				ret+= unmarshal(InverterContainer.class,inputBattery)+"\n";
			}
			if(!ret.isEmpty())
				return "Database populated with: "+ret;
			return "Database populated with nothing";
		}catch(Exception e){
			e.printStackTrace();
			throw new InvalidArgumentException(e.getMessage());
		}

	}
	private String unmarshal(Class<? extends ComponentContainer> class1, String xml) {
		try{
			ComponentContainer p=um.unmarshall(class1, xml);
			p.save();

			return p.getList().toString();
		}catch(Exception e){
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
}
