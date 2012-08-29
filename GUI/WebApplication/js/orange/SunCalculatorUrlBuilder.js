﻿/* Author:ALAN
url builder for connection with google apps
*/
function UrlBuilder()
{
    this.GoogleAppsEngineBaseUrl = "";
    this.ComponentName = "";
    this.MinimumPrice = "";
    this.MaximumPrice = "";
    this.MinimumCapacity = "";
    this.MaximumCapacity = "";
    this.Postcode = "";

    this.toString = function ()
    {
        var completeUrl = "";

        if (this.GoogleAppsEngineBaseUrl != "")
        {
            completeUrl += this.GoogleAppsEngineBaseUrl;

            if (this.ComponentName != "")
            {
                completeUrl += "/" + this.ComponentName;
            }

            if (this.MinimumPrice != "" || this.MaximumPrice != "")
            {
                completeUrl += "/price/" + this.MinimumPrice + "/" + this.MaximumPrice;
            }

            if (this.Postcode != "")
            {
                completeUrl += "/postcode/" + this.Postcode;
            }

            if (this.MinimumCapacity != "" || this.MaximumCapacity != "")
            {
                completeUrl += "/capacity/" + this.MinimumCapacity + "/" + this.MaximumCapacity;
            }
        }

        return completeUrl;
    }


}