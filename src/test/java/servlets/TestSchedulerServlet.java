package servlets;

import models.RateSchedulerDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TestSchedulerServlet extends Mockito {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void should_forward_to_online_rate_scheduler_when_correct_data_and_action_online() throws IOException, ServletException {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(1337.5, 12, 25.1, 100.5, "const");

        when(request.getParameter("action")).thenReturn("online");
        when(request.getAttribute("rateSchedulerDetails")).thenReturn(rateSchedulerDetails);
        when(request.getRequestDispatcher("/scheduler.jsp")).thenReturn(requestDispatcher);

        new SchedulerServlet().doPost(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void should_forward_to_pdf_rate_scheduler_when_correct_data_and_action_pdf() throws IOException, ServletException {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(3500, 23, 13.5, 0, "desc");

        when(request.getParameter("action")).thenReturn("pdf");
        when(request.getAttribute("rateSchedulerDetails")).thenReturn(rateSchedulerDetails);
        when(request.getRequestDispatcher("/pdf")).thenReturn(requestDispatcher);

        new SchedulerServlet().doPost(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void should_forward_to_main_page_when_correct_data_but_another_action_for_example_loveJava() throws IOException, ServletException {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(10000, 15, 3.5, 123, "desc");

        when(request.getParameter("action")).thenReturn("loveJava");
        when(request.getAttribute("rateSchedulerDetails")).thenReturn(rateSchedulerDetails);

        new SchedulerServlet().doPost(request, response);

        verify(response).sendRedirect("/");
    }
}