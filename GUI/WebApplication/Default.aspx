<<<<<<< HEAD
﻿<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
      
    <div class="container_12 content">
        <div class="grid_12" style="margin-bottom:40px;">
            <h1 id="componentName">FIND SOLAR PANELS</h1>
            <input type="text" id="txtComponentName" style="display:none" />
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
                    <th colspan="2">PRICE (AUD)</th>
                </tr>
                <tr>
                    <td>
                        Minimum
                    </td>
                    <td>
                        <select id="txtMinimumPrice"></select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum
                    </td>
                    <td>
                        <select id="txtMaximumPrice"></select>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">
                        EFFICIENCY (KW)
                    </th>
                </tr>
                <tr>
                    <td>Minimum</td>
                    <td>
                        <select id="txtMinimumEfficiency"></select>
                    </td>
                </tr>
                <tr>
                    <td>Maximum</td>
                    <td>
                        <select id="txtMaximumEfficiency"></select>
                    </td>
                </tr>
            </table>
            <input type="submit" id="btnSubmit" style="float: right" />
        </div>
        <div class="grid_9">
            <div id="divLoading" style="display:none; text-align:center;">
                <h5>PROCESSING...</h5>
                <img src="images/ajax-loader.gif" />
            </div>
            <div id="divNoResults">
                <h2>Please use the options on the left to search for components</h2>
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
        //script to make ajax calls and deal with form


        $(document).ready(function ()
        {
            var globalVars = new OrangeGlobalVars();


            $('#btnSubmit').click(function (e)
            {
                e.preventDefault();

                //check which component are we working with
                var componentName = $('#txtComponentName').val();

                //build the url to connect with servlet
                var url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
                url.ComponentName = componentName;
                url.Postcode = $('#txtPostcode').val();
                url.MinimumPrice = $('#txtMinimumPrice').val();
                url.MaximumPrice = $('#txtMaximumPrice').val();
                url.MinimumCapacity = $('#txtMinimumEfficiency').val();
                url.MaximumCapacity = $('#txtMaximumEfficiency').val();

                //create a dummy url
                //url = new UrlBuilder();
                //url.GoogleAppsEngineBaseUrl = "http://solarpowercalc.appspot.com/panel";

                //hide or show a 'please wait' message
                $('#divLoading').toggle();


                //make ajax call
                $.ajax({
                    type: 'POST',
                    url: 'proxy.aspx',
                    dataType: 'xml',
                    data: { servletCallUrl: url.toString() }
                }).done(
                bindXmlToTable
                ).fail(
                errorWhileRetrievingUrl
                );


            });


        });

     

        function bindXmlToTable(xml)
        {
            //remove any existing results table, if exists
            $('#tblResults').remove();
                       
            var componentName = $('#txtComponentName').val();
            var $table;

            if (componentName == 'panel')
            {
                $table = createPanelTable(xml);
            }
            else if (componentName == 'inverter')
            {
                $table = createInverterTable(xml);
            }
            else if (componentName == 'battery')
            {
                $table = createBatteryTable(xml);
            }

            //append the table into divResults
            $('#divResults').append($table);
            $table.jExpand();

            //hide and show respective divs
            $('#divLoading').toggle();
            $('#divResults').slideDown();
            $('#divNoResults').hide();

        }

        

        
    </script>
    
    <script type="text/javascript">
        //create a panel table element from xml data
        function createPanelTable(xml)
        {
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MODEL'));
            $thead.append($('<td>').text('MANUFACTURER'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (KW)'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every panel in the xml
            $(xml).find("panel").each(function ()
            {
                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'row_' + id });

                //create html cell and append into row
                $row.append(createCell(readValueFromXml($xmlRow, 'model')));
                $row.append(createCell(readValueFromXml($xmlRow, 'manufacturer')));
                var price = readValueFromXml($xmlRow, 'price')
                $row.append(createCell(roundNumber(price, 2)));
                var capacity = readValueFromXml($xmlRow, 'capacity')
                $row.append(createCell(roundNumber(capacity, 2)));

                $table.append($row);

                //create row for details elements
                $row = $('<tr>').attr('class', 'row-details');
                $row.append(
                    $('<td>').attr({ 'colspan': '4' }).html
                    (
                        '<h5>Additional Information for ' + readValueFromXml($xmlRow, 'model') + '</h5>' +
                        '<ul>'+
                        '<li>POSTCODE: ' + readValueFromXml($xmlRow, 'postcode')  + '</li>' +
                        '<li>IDENTIFIER: ' + readValueFromXml($xmlRow, 'id')  + '</li>' +
                        '<li>VOLTAGE: ' + readValueFromXml($xmlRow, 'voltage')  + '</li>' +
                        '<li>DIMENSIONS: ' + readValueFromXml($xmlRow, 'dimensions')  + '</li>' +
                        '<li>EFFIENCY DECREASE: ' + readValueFromXml($xmlRow, 'efficiencyDecrease')  + '</li>' +
                        '<li>OPERATING CURRENT: ' + readValueFromXml($xmlRow, 'operatingCurrent')  + '</li>' +
                        '<li>WARRANTY: ' + readValueFromXml($xmlRow, 'warranty')  + '</li>' +
                        '<li>DESCRIPTION: '+ readValueFromXml($xmlRow, 'description')+'</li>'+
                        '</ul>'

                    )
                );
                $table.append($row);


            });

            return $table;
        }
    </script>

    <script type="text/javascript">
        //create a inverter table element from xml data
        function createInverterTable(xml)
        {
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MODEL'));
            $thead.append($('<td>').text('MANUFACTURER'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (KW)'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every panel in the xml
            $(xml).find("inverter").each(function ()
            {
                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'row_' + id });

                //create html cell and append into row
                $row.append(createCell(readValueFromXml($xmlRow, 'model')));
                $row.append(createCell(readValueFromXml($xmlRow, 'manufacturer')));
                var price = readValueFromXml($xmlRow, 'price')
                $row.append(createCell(roundNumber(price, 2)));
                var capacity = readValueFromXml($xmlRow, 'capacity')
                $row.append(createCell(roundNumber(capacity, 2)));

                $table.append($row);

                //create row for details elements
                $row = $('<tr>').attr('class', 'row-details');
                $row.append(
                    $('<td>').attr({ 'colspan': '4' }).html
                    (
                        '<h5>Additional Information for ' + readValueFromXml($xmlRow, 'name') + '</h5>' +
                        '<ul>' +
                        '<li>POSTCODE: ' + readValueFromXml($xmlRow, 'postcode') + '</li>' +
                        '<li>IDENTIFIER: ' + readValueFromXml($xmlRow, 'id') + '</li>' +
                        '<li>VOLTAGE: ' + readValueFromXml($xmlRow, 'voltage') + '</li>' +
                        '<li>DIMENSIONS: ' + readValueFromXml($xmlRow, 'dimensions') + '</li>' +
                        '<li>EFFIENCY DECREASE: ' + readValueFromXml($xmlRow, 'efficiencyDecrease') + '</li>' +
                        '<li>WARRANTY: ' + readValueFromXml($xmlRow, 'warranty') + '</li>' +
                        '<li>BATTERY VOLTAGE RANGE: ' + readValueFromXml($xmlRow, 'batteryVoltageRange') + '</li>' +
                        '<li>OUTPUT VOLTAGE: ' + readValueFromXml($xmlRow, 'outputVoltage') + '</li>' +
                        '<li>OUTPUT FREQUENCY: ' + readValueFromXml($xmlRow, 'outputFrequency') + '</li>' +
                        '<li>DESCRIPTION: ' + readValueFromXml($xmlRow, 'description') + '</li>' +
                        '</ul>' 
                    )
                );
                $table.append($row);


            });

            return $table;
        }
    </script>

    <script type="text/javascript">
        //create a battery table element from xml data
        function createBatteryTable(xml)
        {
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MODEL'));
            $thead.append($('<td>').text('MANUFACTURER'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (KW)'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every panel in the xml
            $(xml).find("battery").each(function ()
            {
                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'row_' + id });

                //create html cell and append into row
                $row.append(createCell(readValueFromXml($xmlRow, 'model')));
                $row.append(createCell(readValueFromXml($xmlRow, 'manufacturer')));
                var price = readValueFromXml($xmlRow, 'price')
                $row.append(createCell(roundNumber(price, 2)));
                var capacity = readValueFromXml($xmlRow, 'capacity')
                $row.append(createCell(roundNumber(capacity, 2)));

                $table.append($row);

                //create row for details elements
                $row = $('<tr>').attr('class', 'row-details');
                $row.append(
                    $('<td>').attr({ 'colspan': '4' }).html
                    (
                        '<h5>Additional Information for ' + readValueFromXml($xmlRow, 'name') + '</h5>' +
                        '<ul>' +
                        '<li>IDENTIFIER: ' + readValueFromXml($xmlRow, 'id') + '</li>' +
                        '<li>POSTCODE: ' + readValueFromXml($xmlRow, 'postcode') + '</li>' +
                        '<li>VOLTAGE: ' + readValueFromXml($xmlRow, 'voltage') + '</li>' +
                        '<li>DIMENSIONS: ' + readValueFromXml($xmlRow, 'dimensions') + '</li>' +
                        '<li>EFFIENCY DECREASE: ' + readValueFromXml($xmlRow, 'efficiencyDecrease') + '</li>' +
                        '<li>WARRANTY: ' + readValueFromXml($xmlRow, 'warranty') + '</li>' +
                        '<li>DESCRIPTION: '+ readValueFromXml($xmlRow, 'description') + '</li>' +
                        '</ul>' 
                    )
                );
                $table.append($row);


            });

            return $table;
        }
    </script>

    <script type="text/javascript">
        //script to deal with link names
        $(document).ready(function ()
        {
            resetForm('panel', 'SOLAR PANELS');
            
            $('#linkFindBatteries').click(function (e)
            {
                e.preventDefault();
                resetForm('battery', 'BATTERIES');
            });

            $('#linkFindInverters').click(function (e)
            {
                e.preventDefault();
                resetForm('inverter', 'INVERTERS');
            });
            $('#linkFindSolarPanels').click(function (e)
            {
                e.preventDefault();
                resetForm('panel', 'SOLAR PANELS');
            });

        });

        function resetForm(componentName,labelName)
        {
            //hide divResults
            $('#divNoResults').show();
            $('#divResults').hide();
            $('#componentName').text('FIND ' + labelName);
            $('#btnSubmit').val('FIND ' + labelName);

            //reset dropdowns
            fillSelect(100, 5000, 100, $('#txtMinimumPrice'));
            fillSelect(100, 5000, 100, $('#txtMaximumPrice'));
            fillSelect(50, 500, 50, $('#txtMinimumEfficiency'));
            fillSelect(50, 500, 50, $('#txtMaximumEfficiency'));

            //set up componentName
            $('#txtComponentName').val(componentName);

        }

        function fillSelect(min, max, interval, $element)
        {
            $element.find('option').remove().end();

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
        
    </script>

    <script type="text/javascript">
        //helper functions

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
=======
﻿<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

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
    <script src="js/jquery.dataTables.js" type="text/javascript"></script>
    <link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        $(document).ready(function ()
        {
            $('#btnSubmit').click(function (e)
            {
                e.preventDefault();

                //build the url to connect with servlet
                //uncomment the following lines to connect with real url
                /*
                var url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = "http://googleAppsBaseUrl";
                url.ComponentName = "panel";
                url.MinimumPrice = $('#txtSolarPanelMinimumPrice').text();
                url.MaximumPrice = $('#txtSolarPanelMaximumPrice').text();
                */

                //create a dummy url
                var url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = "http://localhost:50681/WebApplication/TestXml.xml";

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
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblResults' });

            //create header row
            var $theader = $('<thead>');
            
            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('ID'));
            $thead.append($('<td>').text('Name'));
            $thead.append($('<td>').text('Model'));
            $thead.append($('<td>').text('Manufacturer'));
            $thead.append($('<td>').text('Price'));
            $thead.append($('<td>').text('Company Website'));
            $thead.append($('<td>').text('Capacity'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));
            
            //iterating through every panel in the xml
            $(xml).find("panel").each(function ()
            {
                //read data from the xml                            
                var id = $(this).find("id").text();
                var name = $(this).find("name").text();
                var model = $(this).find("model").text();
                var manufacturer = $(this).find("manufacturer").text();
                var price = $(this).find("price").text();
                var companyWebsite = $(this).find("companyWebsite").text();
                var capacity = $(this).find("capacity").text();

                //create row element
                var $row = $('<tr>');

                //append data into td and then into row
                $row.append($('<td>').text(id));
                $row.append($('<td>').text(name));
                $row.append($('<td>').text(model));
                $row.append($('<td>').text(manufacturer));
                $row.append($('<td>').text(price));
                $row.append($('<td>').text(companyWebsite));
                $row.append($('<td>').text(capacity));

                $table.append($row);

            });
            
            //append the table into divResults
            $('#divResults').append($table);

            //add dataTable sorting functions
            $table.dataTable();
          
            //hide and show respective divs
            $('#divResults').slideDown();
            $('#divNoResults').hide();

        }


        function errorWhileRetrievingUrl(errorInfo)
        {
            alert("there was an error trying to retrieve the url: " + url.toString());
        }

        

      
    </script>
</asp:Content>
>>>>>>> origin/GUI
