using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SpChainService.Models
{
    public class PriceInvent
    {
        public System.Guid ItemID { get; set; }
        public string ItemName { get; set; }
        public string Color { get; set; }
        public Nullable<int> Volume { get; set; }
        public Nullable<bool> Onhand { get; set; }
        public string Image { get; set; }
        public double UnitPrice { get; set; }
    }
}