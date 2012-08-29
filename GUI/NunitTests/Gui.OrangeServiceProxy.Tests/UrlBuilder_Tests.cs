using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using GUI.OrangeServiceProxy;


namespace GUI.OrangeServiceProxy.Tests
{
    [TestFixture]
    public class UrlBuilder_Tests
    {
        [Test]
        public void HasProperty_GoogleAppsEngineBaseUrl()
        {
            UrlBuilder url = new UrlBuilder();
            Assert.That(url, Has.Property("GoogleAppsEngineBaseUrl"));
        }

        [Test]
        public void HasProperty_ComponentName()
        {
            UrlBuilder url = new UrlBuilder();
            Assert.That(url, Has.Property("ComponentName"));
        }

        [Test]
        public void HasProperty_MinimumPrice()
        {
            UrlBuilder url = new UrlBuilder();
            Assert.That(url, Has.Property("MinimumPrice"));
        }

        [Test]
        public void HasProperty_MaximumPrice()
        {
            UrlBuilder url = new UrlBuilder();
            Assert.That(url, Has.Property("MaximumPrice"));
        }

        [Test]
        public void HasProperty_Postcode()
        {
            UrlBuilder url = new UrlBuilder();
            Assert.That(url, Has.Property("Postcode"));
        }

        [Test]
        public void HasProperty_MinimumCapacity()
        {
            UrlBuilder url = new UrlBuilder();
            Assert.That(url, Has.Property("MinimumCapacity"));
        }

        [Test]
        public void HasProperty_MaximumCapacity()
        {
            UrlBuilder url = new UrlBuilder();
            Assert.That(url, Has.Property("MinimumCapacity"));
        }


        
        [Test]
        public void ToString_EmptyParameters_Returns_GoogleAppsBaseUrl()
        {
            UrlBuilder url = new UrlBuilder();
            url.GoogleAppsEngineBaseUrl = "http://baseurl";
            Assert.AreEqual(url.GoogleAppsEngineBaseUrl, url.ToString());
        }



        [Test]
        public void ToString_ComponentName_and_Price()
        {
            UrlBuilder url = new UrlBuilder();
            url.GoogleAppsEngineBaseUrl = "http://baseurl";
            url.ComponentName = "panel";
            url.MinimumPrice = "100";
            url.MaximumPrice = "200";
            Assert.AreEqual("http://baseurl/panel/price/100/200", url.ToString());
        }

        [Test]
        public void ToString_ComponentName_and_Postcode()
        {
            UrlBuilder url = new UrlBuilder();
            url.GoogleAppsEngineBaseUrl = "http://baseurl";
            url.ComponentName = "panel";
            url.Postcode = "4121";
            Assert.AreEqual("http://baseurl/panel/postcode/4121", url.ToString());
        }

        [Test]
        public void ToString_ComponentName_and_Capacity()
        {
            UrlBuilder url = new UrlBuilder();
            url.GoogleAppsEngineBaseUrl = "http://baseurl";
            url.ComponentName = "panel";
            url.MinimumCapacity = "200";
            url.MaximumCapacity = "500";
            Assert.AreEqual("http://baseurl/panel/capacity/200/500", url.ToString());
        }

        [Test]
        public void ToString_ComponentName_and_Capacity_and_Price()
        {
            UrlBuilder url = new UrlBuilder();
            url.GoogleAppsEngineBaseUrl = "http://baseurl";
            url.ComponentName = "panel";
            url.MinimumCapacity = "200";
            url.MaximumCapacity = "500";
            url.MinimumPrice = "2000";
            url.MaximumPrice = "3000";
            Assert.AreEqual("http://baseurl/panel/price/2000/3000/capacity/200/500", url.ToString());
        }

        [Test]
        public void ToString_ComponentName_and_Postcode_and_Capacity_and_Price()
        {
            UrlBuilder url = new UrlBuilder();
            url.GoogleAppsEngineBaseUrl = "http://baseurl";
            url.ComponentName = "panel";
            url.MinimumCapacity = "200";
            url.MaximumCapacity = "500";
            url.Postcode = "4121";
            url.MinimumPrice = "2000";
            url.MaximumPrice = "3000";
            Assert.AreEqual("http://baseurl/panel/price/2000/3000/postcode/4121/capacity/200/500", url.ToString());
        }

        [Test]
        public void ToString_ComponentName_and_Postcode_and_Price()
        {
            UrlBuilder url = new UrlBuilder();
            url.GoogleAppsEngineBaseUrl = "http://baseurl";
            url.ComponentName = "panel";
            url.Postcode = "4121";
            url.MinimumPrice = "2000";
            url.MaximumPrice = "3000";
            Assert.AreEqual("http://baseurl/panel/price/2000/3000/postcode/4121", url.ToString());
        }

        

    }
}
