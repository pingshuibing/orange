<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Calculate.aspx.cs" Inherits="Calculate" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div class="container_12 content">
        <div class="grid_12">
            <h1>Energy Efficiency</h1>
        </div>
        <div class="clear">
        </div>
        <div id="divWelcomePanel" class="grid_12">
            <h3>THIS WIZARD WILL ALLOW YOU TO ACCURATELY CALCULATE ENERGY EFFICIENCY FOR YOUR SOLAR PANEL SYSTEM</h3>
            <input type="submit" id="btnSubmit" style="float: right" value="I AM READY, START NOW" />
        </div>
        <div class="clear">
        </div>
        <h3 class="grid_12" id="txtCaptionTitle">STEP 1 OF 3: ABOUT YOUR SOLAR PANEL </h3>
        <div class="clear">
        </div>
        <!-- start: filter results area -->
        <div class="grid_3">
            <table style="width: 100%">
                <h3>Filter Options</h3>
                <tr>
                    <th colspan="2">LOCATION</th>
                </tr>
                <tr>
                    <td style="width: 120px;">
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
                    <th colspan="2">EFFICIENCY (W) </th>
                </tr>
                <tr>
                    <td>
                        Minimum
                    </td>
                    <td>
                        <select id="txtMinimumEfficiency"></select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum
                    </td>
                    <td>
                        <select id="txtMaximumEfficiency"></select>
                    </td>
                </tr>
            </table>
            <input type="submit" id="btnFilterResults" style="float: right" />
        </div>
        <!-- end: filter results area -->
        <!-- start: wizard steps -->
        <div class="grid_7">
            <div id="divStepOne">
                <h5>PLEASE CHOOSE A SOLAR PANEL</h5>
                <h4>Please select a component from the list below. You can also narrow your results by using the filters on the left.</h4>
                <p>Don't have that component?<br />
                <a href="#"><strong>Input Details Manually</strong></a> </p>
            </div>
            <div id="divStepTwo">
                <h5>PLEASE CHOOSE AN INVERTER</h5>
            </div>
            <div id="divStepThree">
                <h5>PLEASE CHOOSE A TIME SPAN</h5>
            </div>
            <input type="submit" id="btnNext" style="float: right" value="Next" />
            <input type="submit" id="btnPrevious" style="float: right" value="Previous" />
        </div>
        <!-- end: wizard steps -->
        <!-- start: Your System -->
        <div class="grid_2">
            <h3>Your System</h3>
            <h4>Solar Panel</h4>
            <p>SOLAR-E 6542FG</p>
            <h4>Inverter</h4>
            <p>SOLAR-E 6542FG</p>
            <h4>Timespan</h4>
            <p>months</p>
        </div>
        <div class="clear">
        </div>
        <!-- end: Your System -->
        <div id="divResultsPanel" class="grid_12">
            <h3>YOUR SYSTEM EFFICIENCY IS</h3>
            <h2>54</h2>
            <p>WATTS / MONTH</p>
        </div>
        <div class="clear">
        </div>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderJquery" runat="Server">
    <script src="js/orange/GlobalVars.js" type="text/javascript"></script>
    <script src="js/orange/HelperFunctions.js" type="text/javascript"></script>
    <script src="js/orange/SunCalculatorUrlBuilder.js" type="text/javascript"></script>
    <script src="js/libs/jExpand.js" type="text/javascript"></script>
    <!-- start: script to initialize form -->
    <script type="text/javascript">
        $(document).ready(function () {
            //reset dropdowns
            var helper = new HelperFunctions();
            helper.fillSelect(100, 1000, 100, $('#txtMinimumPrice'));
            helper.fillSelect(100, 1000, 100, $('#txtMaximumPrice'));
            helper.fillSelect(50, 300, 50, $('#txtMinimumEfficiency'));
            helper.fillSelect(50, 300, 50, $('#txtMaximumEfficiency'));

            //create variables to make subsequent ajax calls
            var globalVars = new OrangeGlobalVars();
            var urlBuilder = new UrlBuilder();

            //set variables to fill panel table
            urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
            urlBuilder.ComponentName = 'panel'            
            
            //make ajax call to fil panel table
            $.ajax({
                type: 'POST',
                url: 'proxy.aspx',
                dataType: 'xml',
                data: { servletCallUrl: urlBuilder.toString() }
            }).done(createPanelTable).fail(genericAjaxErrorHandler);

            //set variables to fill inverter table
            urlBuilder = new UrlBuilder();
            urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
            urlBuilder.ComponentName = 'inverter'

            //make ajax call to fil inverter table
            $.ajax({
                type: 'POST',
                url: 'proxy.aspx',
                dataType: 'xml',
                data: { servletCallUrl: urlBuilder.toString() }
            }).done(createInverterTable).fail(genericAjaxErrorHandler);

        });
    </script>
    <!-- end: script to initialize form -->

    <!-- start: generic ajax error handler -->
    <script type="text/javascript">
        function genericAjaxErrorHandler() {
            alert('an error has occurred');
        }
    </script>
    <!-- end: generic ajax error handler -->

    <!-- start: script to fill  solar panel tables -->
    <script type="text/javascript">
        function createPanelTable(xml)
        {
            //remove any existing panel tables
            $('#tblPanelResults').remove();

            //create table element
            var $table = $('<table>').attr({ 'id': 'tblPanelResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');

            $thead.append($('<td>').text('MANUFACTURER'));
            $thead.append($('<td>').text('MODEL'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (KW)'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every panel in the xml
            $(xml).find("panel").each(function () {
                var helper = new HelperFunctions();
                
                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'row_' + id });

                //create html cell and append into row
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'model')));
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'manufacturer')));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                $table.append($row);

                //create row for details elements
                var $rowDetails = $('<tr>').attr('class', 'row-details');
                $rowDetails.append(
                    $('<td>').attr({ 'colspan': '4' }).html(
                        '<h5>Additional Information for ' + helper.readValueFromXml($xmlRow, 'model') + '</h5>'+
                        '<ul>' +
                        '<li>POSTCODE: ' + helper.readValueFromXml($xmlRow, 'postcode') + '</li>' +
                        '<li>IDENTIFIER: ' + helper.readValueFromXml($xmlRow, 'id') + '</li>' +
                        '<li>VOLTAGE: ' + helper.readValueFromXml($xmlRow, 'voltage') + '</li>' +
                        '<li>DIMENSIONS: ' + helper.readValueFromXml($xmlRow, 'dimensions') + '</li>' +
                        '<li>EFFIENCY DECREASE: ' + helper.readValueFromXml($xmlRow, 'efficiencyDecrease') + '</li>' +
                        '<li>OPERATING CURRENT: ' + helper.readValueFromXml($xmlRow, 'operatingCurrent') + '</li>' +
                        '<li>WARRANTY: ' + helper.readValueFromXml($xmlRow, 'warranty') + '</li>' +
                        '<li>DESCRIPTION: ' + helper.readValueFromXml($xmlRow, 'description') + '</li>' +
                        '</ul>'
                    )
                );

                $table.append($rowDetails);

            });


            //append the table into divStepOne
            $('#divStepOne').append($table);
            $table.jExpand();
        }
        
        
    
    </script>
    <!-- end: script to fill  solar panel tables -->

    <!-- start: script to fill inverter table -->
    <script type="text/javascript">
        function createInverterTable(xml) {
            //remove any existing inverter tables
            $('#tblInverterResults').remove();
            
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblInverterResults' });

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
            $(xml).find("inverter").each(function () {
                var helper = new HelperFunctions();

                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'row_' + id });

                //create html cell and append into row
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'model')));
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'manufacturer')));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                $table.append($row);

                //create row for details elements
                $row = $('<tr>').attr('class', 'row-details');
                $row.append(
                    $('<td>').attr({ 'colspan': '4' }).html
                    (
                        '<h5>Additional Information for ' + helper.readValueFromXml($xmlRow, 'name') + '</h5>' +
                        '<ul>' +
                        '<li>POSTCODE: ' + helper.readValueFromXml($xmlRow, 'postcode') + '</li>' +
                        '<li>IDENTIFIER: ' + helper.readValueFromXml($xmlRow, 'id') + '</li>' +
                        '<li>VOLTAGE: ' + helper.readValueFromXml($xmlRow, 'voltage') + '</li>' +
                        '<li>DIMENSIONS: ' + helper.readValueFromXml($xmlRow, 'dimensions') + '</li>' +
                        '<li>EFFIENCY DECREASE: ' + helper.readValueFromXml($xmlRow, 'efficiencyDecrease') + '</li>' +
                        '<li>WARRANTY: ' + helper.readValueFromXml($xmlRow, 'warranty') + '</li>' +
                        '<li>BATTERY VOLTAGE RANGE: ' + helper.readValueFromXml($xmlRow, 'batteryVoltageRange') + '</li>' +
                        '<li>OUTPUT VOLTAGE: ' + helper.readValueFromXml($xmlRow, 'outputVoltage') + '</li>' +
                        '<li>OUTPUT FREQUENCY: ' + helper.readValueFromXml($xmlRow, 'outputFrequency') + '</li>' +
                        '<li>DESCRIPTION: ' + helper.readValueFromXml($xmlRow, 'description') + '</li>' +
                        '</ul>'
                    )
                );
                $table.append($row);


            });

            //append the table into divStepOne
            $('#divStepTwo').append($table);
            $table.jExpand();
               
        }
    </script>
    <!-- start: script to fill inverter table -->

    

</asp:Content>
