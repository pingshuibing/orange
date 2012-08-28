using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using GUI.OrangeServiceProxy;


namespace NunitTests
{
    [TestFixture]
    public class UrlBuilder_Tests
    {
        [Test]
        public void Add_returns_3()
        {
            UrlBuilder url = new UrlBuilder();
            Assert.AreEqual(34,url.addnumbers(3,4));
        }

    }
}
