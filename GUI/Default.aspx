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
                        <input id="Text1" type="text" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum Price
                    </td>
                    <td>
                        <input id="Text2" type="text" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Minimum Efficiency
                    </td>
                    <td>
                        <input id="Text3" type="text" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum Efficiency
                    </td>
                    <td>
                        <input id="Text4" type="text" />
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
        <div class="clear">
        </div>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderJquery" runat="Server">
    <script type="text/javascript">
        $(document).ready(function ()
        {
            $('#btnFindSolarPanels').click(function (e)
            {
                e.preventDefault();
                $('#divNoResults').hide();
                $('#divResults').slideDown();


            });
        });
    </script>
</asp:Content>
