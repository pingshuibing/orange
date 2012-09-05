<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">



    <div class="container_12 content">
        <div class="grid_12" style="margin-bottom:40px;">
            <h1>Find Solar Panels</h1>
        </div>
        <div class="clear">
        </div>
        <div class="grid_3">
            <table style="width: 100%">
                <tr><th colspan="2">LOCATION</th>
                </tr>
                <tr>
                    <td  style="width:120px;">
                        Postcode
                    </td>
                    <td>
                        <input id="txtPostcode" type="text" />
                    </td>
                </tr>
                <tr>
                    <th colspan="2">PRICE</th>
                </tr>
                <tr>
                    <td>
                        Minimum
                    </td>
                    <td>
                        <input id="txtMinimumPrice" type="text" style="display:none;" />
                        <select id="drpMinimumPrice"></select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum
                    </td>
                    <td>
                        <input id="txtMaximumPrice" type="text" style="display:none;"/>
                        <select id="drpMaximumPrice"></select>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">
                        EFFICIENCY
                    </th>
                </tr>
                <tr>
                    <td>Minimum</td>
                    <td>
                        <input id="txtMinimumEfficiency" type="text" style="display:none;" />
                        <select id="drpMinimumEfficiency"></select>
                    </td>
                </tr>
                <tr>
                    <td>Maximum</td>
                    <td>
                        <input id="txtMaximumEfficiency" type="text" style="display:none;" />
                        <select id="drpMaximumEfficiency"></select>
                    </td>
                </tr>
            </table>
            <input type="submit" id="btnSubmit" value="Find Solar Panels" style="float: right" />
        </div>
        <div class="grid_9">
            <div id="divLoading" style="display:none; text-align:center;">
                <h5>PROCESSING...</h5>
                <img src="images/ajax-loader.gif" />
            </div>
            <div id="divNoResults">
                <h2>Please use the options on the right to search for components</h2>
            </div>
            <div id="divResults" style="display: none;">
                <h3>YOUR RESULTS:</h3>
            </div>
        </div>
        <div class="clear">
        </div>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderJquery" runat="Server">
    <script src="js/orange/SunCalculatorUrlBuilder.js" type="text/javascript"></script>
    <script src="js/libs/jExpand.js" type="text/javascript"></script>
    
    <script type="text/javascript">
        fillSelect(1000, 40000, 1000, $('#drpMinimumPrice'));
        fillSelect(1000, 40000, 1000, $('#drpMaximumPrice'));
        fillSelect(50, 500, 50, $('#drpMinimumEfficiency'));
        fillSelect(50, 500, 50, $('#drpMaximumEfficiency'));


        $(document).ready(function ()
        {
          

            $('#btnSubmit').click(function (e)
            {
                e.preventDefault();

                //build the url to connect with servlet
                //uncomment the following lines to connect with real url
                var url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = "http://solarpowercalc.appspot.com";

                url.ComponentName = 'panel';
                url.Postcode = $('#txtPostcode').val();
                url.MinimumPrice = $('#txtMinimumPrice').val();
                url.MaximumPrice = $('#txtMaximumPrice').val();
                url.MinimumCapacity = $('#txtMinimumEfficiency').val();
                url.MaximumCapacity = $('#txtMaximumEfficiency').val();
                //alert(url.toString());

                //create a dummy url
                url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = "http://solarpowercalc.appspot.com/panel";

                $('#divLoading').toggle();


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

        function fillSelect(min, max, interval,$element)
        {
            $element.append(
                    $('<option>').attr('value', '').text('None')
                );
            for (i = min; i <= max; i += interval)
            {
                $element.append(
                    $('<option>').attr('value', i).text(i)
                );
            }
        }

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
            $thead.append($('<td>').text('NAME'));
            $thead.append($('<td>').text('MANUFACTURER'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (KW)'));
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

                //create row element for main elements
                var $row = $('<tr>').attr('class','row-main');

                //create html cell and append into row
                $row.append(createCell(readValueFromXml($xmlRow, 'name')));
                $row.append(createCell(readValueFromXml($xmlRow, 'manufacturer')));
                var price = readValueFromXml($xmlRow, 'price')
                $row.append(createCell(roundNumber(price, 2)));
                var capacity = readValueFromXml($xmlRow, 'capacity')
                $row.append(createCell(roundNumber(capacity, 2)));

                $table.append($row);

                //create row for details elements
                $row = $('<tr>').attr('class','row-details');
                $row.append(
                    $('<td>').attr({ 'colspan': '4' }).html
                    (
                        '<h5>Additional Information for ' + readValueFromXml($xmlRow, 'name') + '</h5>' +
                        'VOLTAGE: ' + readValueFromXml($xmlRow, 'voltage') + '</br>'+
                        'DIMENSIONS: ' + readValueFromXml($xmlRow, 'dimensions') + '</br>'+
                        'EFFIENCY DECREASE: ' + readValueFromXml($xmlRow, 'efficiencyDecrease') + '</br>'+
                        'OPERATING CURRENT: ' + readValueFromXml($xmlRow, 'operatingCurrent') + '</br>'+
                        'WARRANTY: ' + readValueFromXml($xmlRow, 'warranty') + '</br>'+
                        readValueFromXml($xmlRow, 'description')

                    )
                );
                $table.append($row);


            });

            //append the table into divResults
            $('#divResults').append($table);
            $table.jExpand();

            //hide and show respective divs
            $('#divLoading').toggle();
            $('#divResults').slideDown();
            $('#divNoResults').hide();

        }

        //creates a td element with a given value
        function createCell(dataValue)
        {
            return $('<td>').text(dataValue);
        }

        function errorWhileRetrievingUrl(errorInfo)
        {
            alert("there was an error trying to retrieve the url: " + url.toString());
        }

        function readValueFromXml($xmlRow, propertyName)
        {
            return $xmlRow.find(propertyName).text();
        }

        function roundNumber(num, dec)
        {
            var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
            return result;
        }

      
    </script>
</asp:Content>
