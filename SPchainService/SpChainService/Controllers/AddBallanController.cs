using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SpChainService.Controllers
{
    public class AddBallanController : BaseController
    {
        SPData.SupplychainEntities DMSentities;

        public AddBallanController()
        {
            DMSentities = new SPData.SupplychainEntities();
            DMSentities.Configuration.LazyLoadingEnabled = false;
            DMSentities.Configuration.ProxyCreationEnabled = false;
        }

        //lây ds theo ngày
        [HttpGet]
        public JsonResult getListByDate(int page, DateTime fromdate, DateTime todate)
        {
            page -= 1;
            fromdate = fromdate.AddHours(-fromdate.Hour);
            todate = todate.AddHours(23 - todate.Hour);

            var list = DMSentities.BallanceSums.Where(x => x.SaleDate >= fromdate && x.SaleDate < todate).OrderBy(x => x.ID).Skip(page * 20).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        //lấy ds theo saleman
        [HttpGet]
        public JsonResult getListBySale(int page, string SaleID)
        {
            page -= 1;
            Guid d = Guid.Parse(SaleID);
            var list = DMSentities.BallanceSums.Where(x => x.SaleMan==d).OrderBy(x => x.ID).Skip(page * 20).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        //lấy danh sách theo sale và ngày
        [HttpGet]
        public JsonResult getListBySaleDate(int page, string SaleID, DateTime fromdate, DateTime todate)
        {
            page -= 1;
            Guid d = Guid.Parse(SaleID);

            fromdate = fromdate.AddHours(-fromdate.Hour);
            todate = todate.AddHours(23 - todate.Hour);

            var list = DMSentities.BallanceSums.Where(x => x.SaleMan == d && x.SaleDate >= fromdate && x.SaleDate < todate)
                .OrderBy(x => x.SaleDate).Skip(page * 20).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getBallanBySaleToday(int page, string SaleID, DateTime todate)
        {
            page -= 1;
            Guid d = Guid.Parse(SaleID);
            todate = todate.AddHours(- todate.Hour);
            DateTime entodate = todate.AddHours(23);

            var list = DMSentities.BallanceSums.Where(x => x.SaleMan == d && x.SaleDate >= todate && x.SaleDate < entodate)
                .OrderBy(x => x.SaleDate).Skip(page * 20).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        public JsonResult getItemById(string id)
        {
            Guid d = Guid.Parse(id);
            var person = DMSentities.BallanceSums.Where(x => x.ID == d).FirstOrDefault();
            return Json(person, JsonRequestBehavior.AllowGet);
        }
        
        [HttpPut]
        public JsonResult update(SPData.BallanceSum ballanceSum)
        {
            var mycust = DMSentities.BallanceSums.Where(x => x.ID == ballanceSum.ID).First();
            mycust.InputDailyAmount = ballanceSum.InputDailyAmount;
            mycust.SaleDate = ballanceSum.SaleDate;
            mycust.SaleMan = ballanceSum.SaleMan;
            mycust.TotalBallance = ballanceSum.TotalBallance;

            int n = DMSentities.SaveChanges();
            return Json(n, JsonRequestBehavior.AllowGet);
        }

        [HttpPut]
        public JsonResult syncBallancing(SPData.BallanceSum ballanceSum)
        {
            if (ballanceSum.ID == null || ballanceSum.ID.Equals(Guid.Empty))
            {
                saveNewBallan(ballanceSum);
                return Json(ballanceSum, JsonRequestBehavior.AllowGet);
            }

            var mycust = DMSentities.BallanceSums.Where(x => x.ID == ballanceSum.ID).First();
            String today = DateTime.Now.ToShortDateString();
            String old = mycust.SaleDate.GetValueOrDefault().ToShortDateString();

            if (today != old)
            {
                saveNewBallan(ballanceSum);
                return Json(ballanceSum, JsonRequestBehavior.AllowGet);
            }

            mycust.InputDailyAmount += ballanceSum.InputDailyAmount;
            mycust.SaleMan = ballanceSum.SaleMan;
            mycust.TotalBallance += ballanceSum.TotalBallance;

            int n = DMSentities.SaveChanges();
            return Json(mycust, JsonRequestBehavior.AllowGet);
        }

        private void saveNewBallan(SPData.BallanceSum ballanceSum)
        {
            ballanceSum.ID = Guid.NewGuid();
            ballanceSum.SaleDate = DateTime.Now;
            DMSentities.BallanceSums.Add(ballanceSum);
            DMSentities.SaveChanges();
        }
    }
}