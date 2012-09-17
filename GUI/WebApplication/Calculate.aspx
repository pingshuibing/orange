<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Calculate.aspx.cs" Inherits="Calculate" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div class="container_12 content">
        <div class="grid_12">
            <h1>CALCULATE your system fitness</h1>
        </div>
        <div class="clear"></div>

        <div id="divWelcomePanel" class="grid_12">
            <h3>THIS WIZARD WILL ALLOW YOU TO ACCURATELY CALCULATE ENERGY EFFICIENCY FOR YOUR SOLAR PANEL SYSTEM</h3>
            <input type="submit" id="btnStartWizard" style="float: right" 
                value="I AM READY, START NOW" />
        </div>
        <div class="clear"></div>
                

        <!-- start: Your System -->
        <div class="grid_12" id="divUserSelection">
            <h3>Your System</h3>
            <input type="text" id="currentComponent" style="display:none;" />
            <table>
                <tr>
                    <td>
                        <h4>Solar Panel</h4>
                        <p id="selectionSolarPanel"></p>
                    </td>
                    <td>
                        <h4>Inverter</h4>
                        <p id="selectionInverter"></p>
                    </td>
                    <td>
                        <h4>Battery</h4>
                        <p id="selectionBattery">months</p>
                    </td>
                </tr>
            </table>
        </div>
        <div class="clear"></div>
        <!-- end: Your System -->

        <!-- start: title caption -->
        <h3 class="grid_12" id="txtCaptionTitle"></h3>
        <div class="clear"></div>
        <!-- end: title caption -->


        <!-- start: filter results area -->
        <div class="grid_3">
            <div id="divFilterResults">
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
            </div>.
        </div>
        <!-- end: filter results area -->

        <!-- start: wizard steps -->
        <div class="grid_9">
            <div id="divStepOne">
                <h4>Please select a component from the list below. You can also narrow your results by using the filters on the left.</h4>
                <p>Don't have that component?<br />
                <a href="#" id="linkManualInpsut"><strong>Input Details Manually</strong></a> </p>
            </div>
            <div id="divStepTwo">
            </div>
            <div id="divStepThree">
            </div>
        </div>
        <div class="clear"></div>
        <!-- end: wizard steps -->



        

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
    
      <!-- start: steps set up -->
    <script type="text/javascript">
        function StepZero() {
            //scroll to beggining of the page
            window.scrollTo(0, 0);

            //reset dropdowns
            var helper = new HelperFunctions();
            helper.fillSelect(100, 1000, 100, $('#txtMinimumPrice'));
            helper.fillSelect(100, 1000, 100, $('#txtMaximumPrice'));
            helper.fillSelect(50, 300, 50, $('#txtMinimumEfficiency'));
            helper.fillSelect(50, 300, 50, $('#txtMaximumEfficiency'));

            //reset 'Your System' area
            $('#selectionSolarPanel').text('NOT SELECTED');
            $('#selectionInverter').text('NOT SELECTED');
            

            //clear textbox for currentComponent
            $('#currentComponent').val('');

            //hide and display appropriate tabs
            /*
            $('#divWelcomePanel').show();
            $('#divUserSelection').hide();
            $('#divStepOne').hide();
            $('#divStepTwo').hide();
            $('#divStepThree').hide();
            $('#divResultsPanel').hide();
            $('#txtCaptionTitle').hide();
            $('#divFilterResults').hide();
            $('#btnNext').hide();
            $('#btnPrevious').hide();
            */
        }

        function StepOne() {
            //scroll to beggining of the page
            $('html, body').animate({
                scrollTop: $("#divUserSelection").offset().top
            }, 2000);

            //set up textbox for currentComponent
            $('#currentComponent').val('panel');

            //set up caption
            $('#txtCaptionTitle').text('STEP 1 OF 3: SELECT A SOLAR PANEL');

            /*
            //hide and display appropriate tabs
            $('#divWelcomePanel').hide();
            $('#divUserSelection').show();
            $('#divStepOne').show();
            $('#divStepTwo').hide();
            $('#divStepThree').hide();
            $('#divResultsPanel').hide();
            $('#txtCaptionTitle').show();
            $('#divFilterResults').show();
            $('#btnNext').show();
            $('#btnPrevious').hide();
            */
        }

        function StepTwo() {
            //scroll to beggining of the page
            $('html, body').animate({
                scrollTop: $("#divUserSelection").offset().top
            }, 2000);

            $('#currentComponent').val('inverter');

            //set up text
            $('#txtCaptionTitle').text('STEP 2 OF 3: SELECT AN INVERTER');

            /*
            //hide and display appropriate tabs
            $('#divWelcomePanel').hide();
            $('#divUserSelection').show();
            $('#divStepOne').hide();
            $('#divStepTwo').show();
            $('#divStepThree').hide();
            $('#divResultsPanel').hide();
            $('#txtCaptionTitle').text('STEP 2 OF 3: SELECT AN INVERTER').show();
            $('#divFilterResults').show();
            $('#btnNext').show();
            $('#btnPrevious').show();
            */
        }

        function StepThree() {
            //scroll to beggining of the page
            $('html, body').animate({
                scrollTop: $("#divUserSelection").offset().top
            }, 2000);

            $('#currentComponent').val('timespan');

            //set up caption
            $('#txtCaptionTitle').text('STEP 3 OF 3: SELECT A BATTERY');

            /*
            //hide and display appropriate tabs
            $('#divWelcomePanel').hide();
            $('#divUserSelection').show();
            $('#divStepOne').hide();
            $('#divStepTwo').hide();
            $('#divStepThree').show();
            $('#divResultsPanel').hide();
            $('#txtCaptionTitle').text('STEP 3 OF 3: SELECT A TIMESPAN').show();
            $('#divFilterResults').hide();
            $('#btnNext').show();
            $('#btnPrevious').show();
            */
        }

        
    </script>
    <!-- end: steps set up -->

    <!-- start: script to initialize form -->
    <script type="text/javascript">
        //create global variables
        var globalVars = new OrangeGlobalVars();

        $(document).ready(function () {
            //reset form
            StepZero();


            //create variables to make subsequent ajax calls
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

            //set variables to fill battery table
            urlBuilder = new UrlBuilder();
            urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
            urlBuilder.ComponentName = 'battery'

            //make ajax call to fil inverter table
            $.ajax({
                type: 'POST',
                url: 'proxy.aspx',
                dataType: 'xml',
                data: { servletCallUrl: urlBuilder.toString() }
            }).done(createBatteryTable).fail(genericAjaxErrorHandler);

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

            $thead.append($('<td>').text('MANUFACTURER / MODEL'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $thead.append($('<td>').text('ACTION'));
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
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'panel_row_' + id });

                //create html cells and append into row
                $row.append(
                    helper.createCell(
                        '<strong>' + helper.readValueFromXml($xmlRow, 'manufacturer') + '</strong><br />' + helper.readValueFromXml($xmlRow, 'model')
                    )
                );
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                //create cell for the call to action
                var $callToAction = $('<input>').attr({ 'id': helper.readValueFromXml($xmlRow, 'id'), 'class': 'panel-select', 'type':'submit' }).val('Select');
                $row.append($('<td>').append($callToAction));



                //append all main row to table
                $table.append($row);

                //create row for details elements
                var $rowDetails = $('<tr>').attr('class', 'row-details');
                $rowDetails.append(
                    $('<td>').attr({ 'colspan': '4' }).html(
                        '<h5>Additional Information for ' + helper.readValueFromXml($xmlRow, 'model') + '</h5>' +
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

                //append details row to table
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
            $thead.append($('<td>').text('MANUFACTURER / MODEL'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $thead.append($('<td>').text('ACTION'));
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
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'inverter_row_' + id });

                //create html cells and append into row
                $row.append(helper.createCell(
                    '<strong>'+helper.readValueFromXml($xmlRow, 'manufacturer')+'</strong><br />' +helper.readValueFromXml($xmlRow, 'model')
                ));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                //create cell for call to action
                var $callToAction = $('<input>').attr({ 'id': helper.readValueFromXml($xmlRow, 'id'), 'class': 'inverter-select', 'type': 'submit' }).val('Select');
                $row.append($('<td>').append($callToAction));

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

    <!-- start: script to fill battery table -->
    <script type="text/javascript">
        function createBatteryTable(xml) {
            //remove any existing inverter tables
            $('#tblBatteryResults').remove();

            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblBatteryResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MANUFACTURER / MODEL'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $thead.append($('<td>').text('ACTION'));
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
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'inverter_row_' + id });

                //create html cells and append into row
                $row.append(helper.createCell(
                    '<strong>' + helper.readValueFromXml($xmlRow, 'manufacturer') + '</strong><br />' + helper.readValueFromXml($xmlRow, 'model')
                ));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                //create cell for call to action
                var $callToAction = $('<input>').attr({ 'id': helper.readValueFromXml($xmlRow, 'id'), 'class': 'inverter-select', 'type': 'submit' }).val('Select');
                $row.append($('<td>').append($callToAction));

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
            $('#divStepThree').append($table);
            $table.jExpand();

        }
    </script>
    <!-- end: script to fill battery table -->
    
    <!-- start: btnStartWizard -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#btnStartWizard').click(function (e) 
            {
                e.preventDefault();
                StepOne();
            });
        });
    </script>
    <!-- end: btnStartWizard -->

    <!-- start: solar panel selection -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#divStepOne').on('click', '.panel-select', function (e) {
                e.preventDefault();
                $('#selectionSolarPanel').text($(this).attr('id'));
                StepTwo();
            });
        });
    </script>
    <!-- end: solar panel selection -->

    <!-- start: inverter selection -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#divStepTwo').on('click', '.inverter-select', function (e) {
                e.preventDefault();
                $('#selectionInverter').text($(this).attr('id'));
                StepThree();
            });
        });
    </script>
    <!-- end: inverter selection -->



</asp:Content>