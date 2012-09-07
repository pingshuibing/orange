/* Author:ALAN
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
            var baseUrl = "";
            var parameters = "";

            baseUrl += this.GoogleAppsEngineBaseUrl;

            if (this.ComponentName != "")
            {
                baseUrl += "/" + this.ComponentName;
            }

            if (this.MinimumPrice != "" || this.MaximumPrice != "")
            {
                parameters += "&priceMin=" + this.MinimumPrice + "&priceMax=" + this.MaximumPrice;
            }

            if (this.Postcode != "")
            {
                parameters += "&postcode=" + this.Postcode;
            }

            if (this.MinimumCapacity != "" || this.MaximumCapacity != "")
            {
                parameters += "&capacityMin=" + this.MinimumCapacity + "&capacityMax=" + this.MaximumCapacity;
            }

            if (parameters.indexOf("&") == 0)
            {
                parameters = parameters.replace(parameters.charAt(0), "");
            }


            completeUrl = baseUrl + "?" + parameters;

        }

        return completeUrl;
    }


}
