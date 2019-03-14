package servlets;

import com.itextpdf.io.source.ByteArrayOutputStream;
import helpers.ConstRateScheduler;
import helpers.DescRateScheduler;
import helpers.IRateScheduler;
import helpers.PdfHelper;
import models.InterestRate;
import models.RateSchedulerDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@WebServlet("/pdf")

public class PdfServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<InterestRate> interestRateList = (ArrayList<InterestRate>) request.getAttribute("interestRateList");

        ByteArrayOutputStream byteArrayOutputStream = PdfHelper.generate(interestRateList);
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        response.setContentLength(byteArrayOutputStream.size());
        OutputStream os = response.getOutputStream();
        byteArrayOutputStream.writeTo(os);
        os.flush();
        os.close();
    }
}
