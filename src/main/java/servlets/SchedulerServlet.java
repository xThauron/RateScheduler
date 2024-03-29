package servlets;

import helpers.ConstRateScheduler;
import helpers.DescRateScheduler;
import helpers.IRateScheduler;
import models.RateSchedulerDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/scheduler")
public class SchedulerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RateSchedulerDetails rateSchedulerDetails = (RateSchedulerDetails) request.getAttribute("rateSchedulerDetails");

        IRateScheduler rateScheduler = rateSchedulerDetails.getInterestRateType().equals("desc") ? new DescRateScheduler() : new ConstRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);
        request.setAttribute("interestRateList", rateScheduler.getInterestRateList());

        if (request.getParameter("action").equals("pdf")) {
            request.getRequestDispatcher("/pdf").forward(request, response);
        } else if (request.getParameter("action").equals("online")) {
            request.getRequestDispatcher("/scheduler.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }
}
