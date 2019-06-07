using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SpChainService.Controllers
{
    public class PriceListController : Controller
    {
        SPData.SupplychainEntities DMSentities;
        DateTime nowend;
        DateTime nowstart;

        public PriceListController()
        {
            DMSentities = new SPData.SupplychainEntities();
            DMSentities.Configuration.LazyLoadingEnabled = false;
            DMSentities.Configuration.ProxyCreationEnabled = false;
            DMSentities.Configuration.AutoDetectChangesEnabled = false;

            DateTime now = DateTime.Now;
            nowend = now.AddHours(23 - now.Hour);
            nowstart = now.AddHours(1 - now.Hour);
        }

        //ds giá

        public List<SPData.PriceList> getListByUser(string customercode, string custgroup)
        {
            var plcust = DMSentities.PriceLists.Where(x => x.FromDate < nowend && x.ToDate > nowstart && x.Code == customercode).OrderBy(x => x.ItemID).ToList();
            var plgr = DMSentities.PriceLists.Where(x => x.FromDate < nowend && x.ToDate > nowstart && x.Code == custgroup).OrderBy(x => x.ItemID).AsParallel();
            var all = DMSentities.PriceLists.Where(x => x.FromDate < nowend && x.ToDate > nowstart && x.TabGroupAll == "Tất cả").OrderBy(x => x.TabGroupAll).AsParallel();

            foreach (var p in plgr)
            {
                if (!(plcust.Where(x => x.ItemID == p.ItemID).Count() > 0))
                    plcust.Add(p);
            }

            foreach (var p in all)
            {
                if (!(plcust.Where(x => x.ItemID == p.ItemID).Count() > 0))
                    plcust.Add(p);
            }

            return plcust;
        }
        
        [HttpGet]
        public JsonResult getListBycustId(string id)
        {
            var list = DMSentities.PriceLists.Where(x => x.FromDate < nowend && x.ToDate > nowstart && (x.Code == id)).OrderBy(x => x.ItemID).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getListAll()
        {
            var list = DMSentities.PriceLists.Where(x => x.FromDate < nowend && x.ToDate > nowstart && x.TabGroupAll == "Tất cả").OrderBy(x => x.ItemID).ToList();

            return Json(list, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getItemById(string id)
        {
            Guid d = Guid.Parse(id);
            var person = DMSentities.PriceLists.Where(x => x.ItemID == d).FirstOrDefault();
            return Json(person, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult save(SPData.PriceList price)
        {
            price.ID = Guid.NewGuid();
            DMSentities.PriceLists.Add(price);
            int n = DMSentities.SaveChanges();

            return Json(n, JsonRequestBehavior.AllowGet);
        }

        [HttpPut]
        public JsonResult update(SPData.PriceList price)
        {
            var mycust = DMSentities.PriceLists.Where(x => x.ID == price.ID).First();
            mycust.Code = price.Code;
            mycust.TabGroupAll = price.TabGroupAll;
            mycust.ItemID = price.ItemID;
            mycust.FromDate = price.FromDate;
            mycust.ToDate = price.ToDate;
            mycust.UnitPrice = price.UnitPrice;

            int n = DMSentities.SaveChanges();
            return Json(n, JsonRequestBehavior.AllowGet);
        }
    }
}