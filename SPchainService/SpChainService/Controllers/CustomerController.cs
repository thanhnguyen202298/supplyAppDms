using System;
using System.Linq;
using System.Web.Mvc;

namespace SpChainService.Controllers
{
    public class CustomerController : Controller
    {

        SPData.SupplychainEntities DMSentities;

        public CustomerController()
        {
            DMSentities = new SPData.SupplychainEntities();
            DMSentities.Configuration.LazyLoadingEnabled = false;
            DMSentities.Configuration.ProxyCreationEnabled = false;
        }

        //ds khách hàng
        [HttpGet]
        public JsonResult getList(int page)
        {
            page -= 1;
            var list = DMSentities.Customers.OrderBy(x=>x.CustName).Skip(page*20).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        //ds khách hàng thuộc tuyến
        [HttpGet]
        public JsonResult getListOwner(int page, string saleId)
        {
            page -= 1;
            Guid d = Guid.Parse(saleId);
            var list = DMSentities.Customers.Where(x => x.SaleMan == d).OrderBy(x => x.CustName).Skip(page * 20).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getItemById(string id)
        {
            Guid d = Guid.Parse(id);
            var person = DMSentities.Customers.Where(x => x.CustAccount == d).FirstOrDefault();
            return Json(person, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult save(SPData.Customer customer)
        {
            customer.CustAccount = Guid.NewGuid();
            DMSentities.Customers.Add(customer);
            int n = DMSentities.SaveChanges();

            return Json(n,JsonRequestBehavior.AllowGet);
        }

        [HttpPut]
        public JsonResult update(SPData.Customer customer)
        {
            var mycust = DMSentities.Customers.Where(x => x.CustAccount == customer.CustAccount).First();
            mycust.Address = customer.Address;
            mycust.CustGroup = customer.CustGroup;
            mycust.CustName = customer.CustName;
            mycust.Tell = customer.Tell;
            mycust.SaleMan = customer.SaleMan;

            int n = DMSentities.SaveChanges();
            return Json(n, JsonRequestBehavior.AllowGet);
        }
    }
}