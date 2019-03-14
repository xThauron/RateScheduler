package filters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TestSchedulerFilter extends Mockito {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void should_forward_to_next_chain_when_validation_correct() throws IOException, ServletException {
        when(request.getParameter("creditValue")).thenReturn("6000");
        when(request.getParameter("interestRateAmount")).thenReturn("12");
        when(request.getParameter("interestPercent")).thenReturn("25");
        when(request.getParameter("interestConstPay")).thenReturn("0");
        when(request.getParameter("interestRateType")).thenReturn("desc");

        new SchedulerFilter().doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void should_forward_to_index_with_errors_when_validation_failed() throws IOException, ServletException {
        when(request.getParameter("creditValue")).thenReturn("-1");
        when(request.getParameter("interestRateAmount")).thenReturn("0");
        when(request.getParameter("interestPercent")).thenReturn("-2");
        when(request.getParameter("interestConstPay")).thenReturn("-1");
        when(request.getParameter("interestRateType")).thenReturn("loveJava");
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);

        new SchedulerFilter().doFilter(request, response, filterChain);

        verify(requestDispatcher).forward(request, response);
    }

}