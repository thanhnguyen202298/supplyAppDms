using System;
using System.Linq;
using System.Web.Mvc;

namespace SpChainService.Controllers
{
    public class SaleLineController : Controller
    {
        SPData.SupplychainEntities DMSentities;

        public SaleLineController()
        {
            DMSentities = new SPData.SupplychainEntities();
            DMSentities.Configuration.LazyLoadingEnabled = false;
            DMSentities.Configuration.ProxyCreationEnabled = false;
        }

        //ds khách hàng
        
        public JsonResult getDetails(string saleid)
        {
            Guid d = Guid.Parse(saleid);
            var list = DMSentities.SaleLines.Where(x=>x.SaleID==d).OrderBy(x => x.LineNum).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        
        public JsonResult getItemById(string id)
        {
            Guid d = Guid.Parse(id);
            var person = DMSentities.SaleLines.Where(x => x.ItemID == d).FirstOrDefault();
            return Json(person, JsonRequestBehavior.AllowGet);
        }

        
        public JsonResult getItemByOrder(string id,string saleid)
        {
            Guid d = Guid.Parse(id);
            Guid orderid = Guid.Parse(saleid);
            var person = DMSentities.SaleLines.Where(x => x.ItemID == d && x.SaleID == orderid).FirstOrDefault();
            return Json(person, JsonRequestBehavior.AllowGet);
        }
        
    }
}