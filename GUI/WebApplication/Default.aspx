<<<<<<< HEAD
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
