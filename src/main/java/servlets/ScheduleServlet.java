package servlets;

import helpers.ConstRateScheduler;
import helpers.DescRateScheduler;
import helpers.IRateScheduler;
import helpers.RateScheduler;
import models.RateScheduler.RateSchedulerDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        RateSchedulerDetails rateSchedulerDetails = (RateSchedulerDetails) request.getAttribute("rateSchedulerDetails");

        IRateScheduler rateScheduler = rateSchedulerDetails.getInterestRateType().equals("desc") ? new DescRateScheduler() : new ConstRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);

        request.setAttribute("interestRateList", rateScheduler.getInterestRateList());
        session.setAttribute("interestRateList", rateScheduler.getInterestRateList());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
