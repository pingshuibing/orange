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

        <div class="grid_7">
            <h4>Please select a component from the list below. You can also narrow your results by using the filters on the left.</h4>
            <p>Don't have that component? <br /><input type="submit" id="btnManualInput" value="Input Details Manually" /></p>

        </div>

        <!-- start: user selection area -->
        <div class="grid_2" >
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
        <!-- end: user selection area -->
        
        
        <div class="prefix_7 grid_3 suffix_2">
            <input type="submit" id="btnNext" style="float: right" value="Next" />
            <input type="submit" id="btnPrevious" style="float: right" value="Previous" />
        </div>
        <div class="clear">
        </div>
        
        <div id="divResultsPanel" class="grid_12">
            <h3>YOUR SYSTEM EFFICIENCY IS</h3>
            <h2>54</h2>
            <p>WATTS / MONTH</p>
        </div>
        <div class="clear"></div>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderJquery" runat="Server">
</asp:Content>
