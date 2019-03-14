<%@ page import="models.InterestRate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="models.RateSchedulerDetails" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Rate Scheduler</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <style type="text/css">
        body {
            padding-top: 5rem;
        }

        .starter-template {
            padding: 3rem 1.5rem;
            text-align: center;
        }
    </style>
    <%
        ArrayList<InterestRate> interestRateList = (ArrayList<InterestRate>) request.getAttribute("interestRateList");
        RateSchedulerDetails rateSchedulerDetails = (RateSchedulerDetails) request.getAttribute("rateSchedulerDetails");
        String creditValue = "";
        String interestRateAmount = "";
        String interestPercent = "";
        String interestConstPay = "";
        String interestRateType = "";
        if (rateSchedulerDetails != null) {
            creditValue = String.valueOf(rateSchedulerDetails.getCreditValue());
            interestRateAmount = String.valueOf(rateSchedulerDetails.getInterestRateAmount());
            interestPercent = String.valueOf(rateSchedulerDetails.getInterestPercent());
            interestConstPay = String.valueOf(rateSchedulerDetails.getInterestConstPay());
            interestRateType = String.valueOf(rateSchedulerDetails.getInterestRateType());
        }

    %>
</head>
<body>
<div class="container">
    <div class="starter-template">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-12">
                <div class="row">
                    <strong>Wnioskowana kwota kredytu:&nbsp;</strong> <% out.print(creditValue); %>
                </div>
                <div class="row">
                    <strong>Ilość rat:&nbsp;</strong> <% out.println(interestRateAmount); %>
                </div>
                <div class="row">
                    <strong>Oprocentowanie:&nbsp;</strong> <% out.println(interestPercent + "%"); %>
                </div>
                <div class="row">
                    <strong>Opłaty stałe:&nbsp;</strong> <% out.println(interestConstPay); %>
                </div>
                <div class="row">
                    <strong>Rodzaj
                        rat:&nbsp;</strong> <% out.println(interestRateType.equals("desc") ? "malejące" : "stałe"); %>
                </div>
                <table class="table text-center mt-3">
                    <thead>
                    <tr>
                        <th>Nr raty</th>
                        <th>Kwota Kapitału</th>
                        <th>Kwota odsetek</th>
                        <th>Opłaty stałe</th>
                        <th>Całkowita kwota raty</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        int id = 1;
                        for (InterestRate interestRate : interestRateList) {
                            out.println("<tr>");
                            out.println("<td>" + id + "</td>");
                            out.println("<td>" + String.format("%10.2f", interestRate.getCapitalValue()) + "</td>");
                            out.println("<td>" + String.format("%10.2f", interestRate.getTaxValue()) + "</td>");
                            out.println("<td>" + String.format("%10.2f", interestRate.getConstPayValue()) + "</td>");
                            out.println("<td>" + String.format("%10.2f", interestRate.getTotalRateValue()) + "</td>");
                            out.println("</tr>");
                            id++;
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <a href="/" type="button" class="btn btn-primary">Wróć</a>
        </div>
    </div>
</div>
</body>
</html>