using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Text;


namespace GUI.OrangeServiceProxy
{
    public class UrlBuilder
    {
        public string GoogleAppsEngineBaseUrl { get; set; }
        public string ComponentName { get; set; }
        public string MinimumPrice { get; set; }
        public string MaximumPrice { get; set; }
        public string MinimumCapacity { get; set; }
        public string MaximumCapacity { get; set; }
        public string Postcode { get; set; }


        public UrlBuilder()
        {
            this.GoogleAppsEngineBaseUrl = string.Empty;
            this.ComponentName = string.Empty;
            this.MinimumPrice = string.Empty;
            this.MaximumPrice = string.Empty;
            this.MinimumCapacity = string.Empty;
            this.MaximumCapacity = string.Empty;
            this.Postcode = string.Empty;
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            
            sb.Append(GoogleAppsEngineBaseUrl); //e.g. htttp://baseurl

            
            if (ComponentName != string.Empty)
            {
                //add componentName
                sb.Append(string.Format("/{0}",ComponentName)); //e.g. htttp://baseurl/panel

                //add price
                if (MinimumPrice != string.Empty || MaximumPrice != string.Empty)
                {
                    sb.Append(string.Format("/price/{0}/{1}", MinimumPrice, MaximumPrice)); //e.g. htttp://baseurl/panel/price/100/200
                }

                //add postcode
                if (Postcode != string.Empty)
                {
                    sb.Append(string.Format("/postcode/{0}", Postcode));//e.g. htttp://baseurl/panel/postcode/4121
                }

                //add capacity
                if (MinimumCapacity != string.Empty || MaximumCapacity != string.Empty)
                {
                    sb.Append(string.Format("/capacity/{0}/{1}", MinimumCapacity, MaximumCapacity)); //e.g. htttp://baseurl/panel/capacity/100/200
                }

            }
            else 
            {
                return sb.ToString(); //e.g. htttp://baseurl
            }

            return sb.ToString();    
        }
        
    }
}
