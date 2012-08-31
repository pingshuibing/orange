<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    
    <div class="container_12 content">
        <div class="grid_12">
            <h1>Find Solar Panels</h1>
        </div>
        <div class="clear"></div>
        <div class="grid_3">
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
            <input type="submit" id="btnSubmit" value="Find Solar Panels" 
                style="float: right" />
        </div>
        <div class="grid_9">
            <div id="divNoResults">
                <h2>Please use the options on the right to search for components</h2>
            </div>
            <div id="divResults" style="display: none;">
                <h2>Your results are shown below</h2>
            </div>
        </div>
        <div class="clear"></div>
        
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderJquery" runat="Server">
    <script src="js/orange/SunCalculatorUrlBuilder.js" type="text/javascript"></script>
    <script src="js/libs/jExpand.js" type="text/javascript"></script>
    <script src="js/libs/sprintf-0.6.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function ()
        {
            var template;

           // $('#txtPostcode').text($.load('PanelTableTemplate.htm'));




            $('#btnSubmit').click(function (e)
            {
                e.preventDefault();

                alert(sprintf('%2$s %1$s', 'soto', 'alan'));



                //build the url to connect with servlet
                //uncomment the following lines to connect with real url


                var url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = "http://googleAppsBaseUrl";
                url.GoogleAppsEngineBaseUrl = "http://solarpowercalc.appspot.com";


                url.ComponentName = 'panel';
                url.Postcode = $('#txtPostcode').val();
                url.MinimumPrice = $('#txtMinimumPrice').val();
                url.MaximumPrice = $('#txtMaximumPrice').val();
                url.MinimumCapacity = $('#txtMinimumEfficiency').val();
                url.MaximumCapacity = $('#txtMaximumEfficiency').val();
                alert(url.toString());


                //create a dummy url
                url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = "http://solarpowercalc.appspot.com/panel/capacity/0/20000";


                //make ajax call
                $.ajax({
                    type: 'POST',
                    url: 'proxy.aspx',
                    dataType: 'xml',
                    data: { servletCallUrl: url.toString() }
                }).done(
                bindPanelXmlToTable
                ).fail(
                errorWhileRetrievingUrl
                );
            });


        });

        function bindPanelXmlToTable(xml)
        {
            //remove any existing table, if exists
            $('#tblResults_wrapper').remove();
            
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblResults' });

            //create header row
            var $theader = $('<thead>');
            
            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('Name'));
            $thead.append($('<td>').text('Manufacturer'));
            $thead.append($('<td>').text('Price'));
            $thead.append($('<td>').text('Capacity'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));
            
            //iterating through every panel in the xml
            $(xml).find("panel").each(function ()
            {

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element
                var $row = $('<tr>');

                //create html cell and append into row
                $row.append(createCell($xmlRow, 'name'));
                $row.append(createCell($xmlRow, 'manufacturer'));
                $row.append(createCell($xmlRow, 'price'));
                $row.append(createCell($xmlRow, 'capacity'));
                $table.append($row);

                

            });
            
            //append the table into divResults
            $('#divResults').append($table);
            $table.jExpand();
          
            //hide and show respective divs
            $('#divResults').slideDown();
            $('#divNoResults').hide();

        }

        //read data values from xml row and creates a td element
        function createCell($xmlRow, propertyName)
        {
            var dataValue = $xmlRow.find(propertyName).text();
            return $('<td>').text(dataValue);
        }

        function errorWhileRetrievingUrl(errorInfo)
        {
            alert("there was an error trying to retrieve the url: " + url.toString());
        }

        

      
    </script>
</asp:Content>
