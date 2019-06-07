using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SpChainService.Controllers
{
    public class SaleOrderController : BaseController
    {
        SPData.SupplychainEntities DMSentities;

        public SaleOrderController()
        {
            DMSentities = new SPData.SupplychainEntities();
            DMSentities.Configuration.LazyLoadingEnabled = false;
            DMSentities.Configuration.ProxyCreationEnabled = false;
        }

        //ds khách hàng
        [HttpGet]
        public JsonResult getList(int page, string man, string cust)
        {
            page -= 1;
            Guid manid = Guid.Parse(man);
            if (cust == null)
            {
                var list = DMSentities.SaleOrders.Where(x => x.SalesMan == manid).OrderByDescending(x => x.LotId).Skip(page * 20).ToList();
                return Json(list, JsonRequestBehavior.AllowGet);
            }
            var list2 = DMSentities.SaleOrders.Where(x => x.SalesMan == manid && x.CustAccount +""==cust).OrderByDescending(x => x.LotId).Skip(page * 20).ToList();
            return Json(list2, JsonRequestBehavior.AllowGet);

        }

        [HttpGet]
        public JsonResult getOrderById(string id)
        {
            Guid d = Guid.Parse(id);
            var person = DMSentities.SaleOrders.Where(x => x.SaleID == d).FirstOrDefault();
            return Json(person, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult save(SPData.SaleOrder saleOrder)
        {
            saleOrder.SaleID = Guid.NewGuid();
            saleOrder.CreateDateTime = DateTime.Now;
            foreach (SPData.SaleLine sl in saleOrder.SaleLines)
            {
                sl.LotID = Guid.NewGuid();
                sl.LineNum = Guid.NewGuid();
            }

            DMSentities.SaleOrders.Add(saleOrder);
            int n = DMSentities.SaveChanges();
            return Json(n, JsonRequestBehavior.AllowGet);
        }

        [HttpPut]
        public JsonResult update(SPData.SaleOrder saleOrder)
        {
            var mycust = DMSentities.SaleOrders.Where(x => x.SaleID == saleOrder.SaleID).First();
            mycust.Description = saleOrder.Description;
            //mycust.SalesMan = saleOrder.SalesMan;
            mycust.SaleStatus = saleOrder.SaleStatus;
            if (!mycust.SaleLines.Equals(saleOrder.SaleLines))
            {
                mycust.SaleLines.Clear();
                mycust.SaleLines.Concat(saleOrder.SaleLines);
            }

            int n = DMSentities.SaveChanges();
            return Json(n, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getDetails(string saleid)
        {
            Guid d = Guid.Parse(saleid);
            var list = DMSentities.SaleLines.Where(x => x.SaleID == d).OrderBy(x => x.ItemID).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }
    }
}