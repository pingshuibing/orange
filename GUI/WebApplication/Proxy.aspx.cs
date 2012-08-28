using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml;
using System.Net;
using System.IO;
using System.Text;

public partial class Proxy : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        //read all values from collection f paramters
        StringBuilder strParameters = new StringBuilder();
        foreach (string key in Request.QueryString.Keys)
        {
            strParameters.Append(string.Format("{0}={1}&",key,Request.QueryString[key]));
            
        }

        //set url to call google apps engine
        string url = "http://orange.alansoto.com/TestXml.xml";

        //combine the google apps engine and paramters list to do a full get
        string fullUrl = string.Format("{0}?{1}", url, strParameters.ToString());

        //make call to external url and display results
        WebResponse result = null;
        string output = "";

        WebRequest req = WebRequest.Create(fullUrl);
        result = req.GetResponse();
        Stream ReceiveStream = result.GetResponseStream();
        Encoding encode = System.Text.Encoding.GetEncoding("utf-8");
        StreamReader sr = new StreamReader(ReceiveStream, encode);
        Char[] read = new Char[256];
        int count = sr.Read(read, 0, read.Length);
        while (count > 0)
        {
            String str = new String(read, 0, count);
            output += str;
            count = sr.Read(read, 0, read.Length);
        }


        if (result != null)
        {
            result.Close();
        }

        Response.Write(output);

    }
}