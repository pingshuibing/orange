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

                var min = 0; var max = 0;
                if (this.MinimumPrice <= this.MaximumPrice)
                {
                    min = this.MinimumPrice;
                    max = this.MaximumPrice;
                }
                else
                {
                    min = this.MaximumPrice;
                    max = this.MinimumPrice;
                }

                parameters += "&priceMin=" + min + "&priceMax=" + max;
            }

            if (this.Postcode != "")
            {
                parameters += "&postcode=" + this.Postcode;
            }

            if (this.MinimumCapacity != "" || this.MaximumCapacity != "")
            {
                var min = 0; var max = 0;
                if (this.MinimumCapacity <= this.MaximumCapacity)
                {
                    min = this.MinimumCapacity;
                    max = this.MaximumCapacity;
                }
                else
                {
                    min = this.MaximumCapacity;
                    max = this.MinimumCapacity;
                }

                parameters += "&capacityMin=" + min + "&capacityMax=" + max;
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
