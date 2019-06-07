using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SpChainService.Controllers
{
    public class UsInfoController : Controller
    {
        SPData.SupplychainEntities DMSentities;

        public UsInfoController()
        {
            DMSentities = new SPData.SupplychainEntities();
            DMSentities.Configuration.LazyLoadingEnabled = false;
            DMSentities.Configuration.ProxyCreationEnabled = false;
        }

        //g
        [HttpPost]
        public JsonResult login(string usn, string pwd)
        {
            var list = DMSentities.UserInfoes.Where(x => x.UserName == usn && x.PassWord==pwd).First();
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        
        //g
        [HttpPost]
        public JsonResult getUsById(string id)
        {
            Guid usid = Guid.Parse(id);
            var list = DMSentities.UserInfoes.Where(x => x.UserID == usid).First();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult save(SPData.UserInfo userInfo)
        {
            userInfo.UserID = Guid.NewGuid();
            DMSentities.UserInfoes.Add(userInfo);
            var person = DMSentities.SaveChanges();
            return Json(person, JsonRequestBehavior.AllowGet);
        }

        [HttpPut]
        public JsonResult update(SPData.UserInfo userInfo)
        {
            var list = DMSentities.UserInfoes.Where(x => x.UserName == userInfo.UserName && x.PassWord == userInfo.PassWord).First();
            list.PassWord = userInfo.PassWord;
            list.Role = userInfo.Role;
            var person = DMSentities.SaveChanges();
            return Json(person, JsonRequestBehavior.AllowGet);
        }


    }
}