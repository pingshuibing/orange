<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div class="container_12 white">
        <div class="grid_4 alpha">
            <h2>About You</h2>
            <table style="width: 100%">
                <tr>
                    <td>
                        Postcode
                    </td>
                    <td>
                        <input id="txtPostcode" type="text" />
                    </td>
                </tr>
            </table>
            <h2>Find Solar Panels</h2>
            <table style="width: 100%">
                <tr>
                    <td>
                        Minimum Price
                    </td>
                    <td>
                        <input id="txtMinimumPrice" type="text" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum Price
                    </td>
                    <td>
                        <input id="txtMaximumPrice" type="text" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Minimum Efficiency
                    </td>
                    <td>
                        <input id="txtMinimumEfficiency" type="text" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum Efficiency
                    </td>
                    <td>
                        <input id="txtMaximumEfficiency" type="text" />
                    </td>
                </tr>
            </table>
            <input type="submit" id="btnFindSolarPanels" value="Find Solar Panels" style="float: right" />
        </div>
        <div class="grid_8 omega">
            <div id="divNoResults">
                <h2>Please use the options on the right to search for components</h2>
            </div>
            <div id="divResults" style="display:none;">
                <h2>Table of results will appear here</h2>

            </div>
        </div>
        <div class="clear"></div>
    </div>

</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderJquery" runat="Server">
    <script src="js/orange/SunCalculatorUrlBuilder.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function ()
        {
            $('#btnFindSolarPanels').click(function (e)
            {
                
                
                var url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = "http://googleAppsBaseUrl";
                url.ComponentName = "panel";
                url.MinimumPrice = $('#txtSolarPanelMinimumPrice').text();
                url.MaximumPrice = $('#txtSolarPanelMaximumPrice').text();


                e.preventDefault();
                $.ajax({
                    type: 'GET',
                    url: 'proxy.aspx',
                    dataType: 'xml',
                    data: {urlToQuery : url.toString()},
                    success: function (data)
                    {
                        var str = '';
                        $(data).find("panel").each(function ()
                        {
                            str += '<p>';
                            str += $(this).find("id").text() + '</br>';
                            str += $(this).find("name").text() + '</br>';
                            str += $(this).find("model").text() + '</br>';
                            str += $(this).find("manufacturer").text() + '</br>';

                            str += $(this).find("price").text() + '</br>';
                            str += $(this).find("companyWebsite").text() + '</br>';
                            str += $(this).find("capacity").text() + '</br>';
                            
                            
                            str += '</p>';

                        });

                        $('#divResults').html(str);
                        $('#divResults').slideDown();
                        $('#divNoResults').hide();
                    },
                    error: function(data){
                        alert("there was an error trying to retrieve the url: "+ url.toString());
                    }
                });
            });
        });
    </script>
</asp:Content>
