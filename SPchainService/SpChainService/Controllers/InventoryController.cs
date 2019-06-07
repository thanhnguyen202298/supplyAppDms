using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SpChainService.Controllers
{
    public class InventoryController : Controller
    {
        SPData.SupplychainEntities DMSentities;
        DateTime nowend;
        DateTime nowstart;

        public InventoryController()
        {
            DMSentities = new SPData.SupplychainEntities();
            DMSentities.Configuration.LazyLoadingEnabled = false;
            DMSentities.Configuration.ProxyCreationEnabled = false;
            DMSentities.Configuration.AutoDetectChangesEnabled = false;

            DateTime now = DateTime.Now;
            nowend = now.AddHours(23 - now.Hour);
            nowstart = now.AddHours(1 - now.Hour);
        }

        //ds khách hàng
        [HttpGet]
        public JsonResult getList(int page, string customercode, string custgroup)
        {
            page -= 1;
            var pridlass = new PriceListController();
            var pl = pridlass.getListByUser(customercode, custgroup);
            var list = DMSentities.Inventories.OrderBy(x => x.ItemName).Skip(page * 20).ToList();
            var m =  from i in list
                   join p in pl on i.ItemID equals p.ItemID
                   select new { i.Color,i.Image,i.ItemID,i.ItemName,i.Onhand,i.Volume, p.UnitPrice};

            return Json(m, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getAllProduct(int page)
        {
            page -= 1;
            var list = DMSentities.Inventories.OrderBy(x => x.ItemName).Skip(page * 20).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getItemById(string id)
        {
            Guid d = Guid.Parse(id);

            var pl = DMSentities.PriceLists.Where(y => y.FromDate < nowend && y.ToDate > nowstart && y.ItemID == d).OrderBy(x => x.TabGroupAll);
            var person = from p in pl
                         join c in DMSentities.Customers on p.Code equals c.CustAccount + "" into pc
                         from x in pc.DefaultIfEmpty()
                         select new
                         {
                             p.ItemID,
                             p.TabGroupAll,
                             p.Code,
                             p.UnitPrice,
                             x.CustName
                         };

            return Json(person, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult save(SPData.Inventory inventory)
        {
            inventory.ItemID = Guid.NewGuid();
            DMSentities.Inventories.Add(inventory);
            int n = DMSentities.SaveChanges();

            return Json(n, JsonRequestBehavior.AllowGet);
        }

        [HttpPut]
        public JsonResult update(SPData.Inventory inventory)
        {
            var mycust = DMSentities.Inventories.Where(x => x.ItemID == inventory.ItemID).First();
            mycust.Image = inventory.Image;
            mycust.ItemName = inventory.ItemName;
            mycust.Onhand = inventory.Onhand;
            mycust.Volume = inventory.Volume;
            mycust.Color = inventory.Color;

            int n = DMSentities.SaveChanges();
            return Json(n, JsonRequestBehavior.AllowGet);
        }
    }
}