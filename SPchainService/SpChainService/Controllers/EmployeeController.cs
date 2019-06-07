using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SpChainService.Controllers
{
    public class EmployeeController : Controller
    {
        SPData.SupplychainEntities DMSentities;

        public EmployeeController()
        {
            DMSentities = new SPData.SupplychainEntities();
            DMSentities.Configuration.LazyLoadingEnabled = false;
            DMSentities.Configuration.ProxyCreationEnabled = false;
        }
        //ds nhân viên
        [HttpGet]
        public JsonResult getList(int page)
        {
            page -= 1;
            var list = DMSentities.Employees.OrderBy(x => x.EmployeeName).Skip(page * 20).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getItemById(string id)
        {
            Guid d = Guid.Parse(id);
            var person = DMSentities.Employees.Where(x => x.EmployeeId == d).FirstOrDefault();
            return Json(person, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult save(SPData.Employee employee)
        {
            employee.EmployeeId = Guid.NewGuid();
            DMSentities.Employees.Add(employee);
            int n = DMSentities.SaveChanges();

            return Json(n, JsonRequestBehavior.AllowGet);
        }

        [HttpPut]
        public JsonResult update(SPData.Employee employee)
        {
            var mycust = DMSentities.Employees.Where(x => x.EmployeeId == employee.EmployeeId).First();
            mycust.Address = employee.Address;
            mycust.EmployeeName = employee.EmployeeName;
            mycust.VehicleDefault = employee.VehicleDefault;
            mycust.Tell = employee.Tell;

            int n = DMSentities.SaveChanges();
            return Json(n, JsonRequestBehavior.AllowGet);
        }
    }
}