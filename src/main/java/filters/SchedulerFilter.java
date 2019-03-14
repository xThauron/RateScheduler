package filters;

import jdk.nashorn.internal.ir.RuntimeNode;
import models.RateSchedulerDetails;
import validators.SchedulerValidator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/scheduler")
public class SchedulerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");

        String creditValueParam = request.getParameter("creditValue");
        String interestRateAmountParam = request.getParameter("interestRateAmount");
        String interestPercentParam = request.getParameter("interestPercent");
        String interestConstPayParam = request.getParameter("interestConstPay");
        String interestRateTypeParam = request.getParameter("interestRateType");

        SchedulerValidator schedulerValidator = new SchedulerValidator(creditValueParam, interestRateAmountParam, interestPercentParam, interestConstPayParam, interestRateTypeParam);
        schedulerValidator.validate();

        if (schedulerValidator.isValidated()) {
            RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(
                    Double.parseDouble(creditValueParam),
                    Integer.parseInt(interestRateAmountParam),
                    Double.parseDouble(interestPercentParam),
                    Double.parseDouble(interestConstPayParam),
                    interestRateTypeParam
            );
            request.setAttribute("rateSchedulerDetails", rateSchedulerDetails);
            chain.doFilter(request, response);
        } else {
            request.setAttribute("errors", schedulerValidator.getErrors());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
