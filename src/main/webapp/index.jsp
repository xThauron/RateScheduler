<%@ page import="models.InterestRate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<InterestRate> list = (ArrayList<InterestRate>) request.getAttribute("interestRateList");
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errrors");
    String creditValue = "";
    String interestRateAmount = "";
    String interest = "";
    String interestConstPay = "";
    String interestRateType = "";
    if (list != null) {
        creditValue = request.getParameter("creditValue");
        interestRateAmount = request.getParameter("interestRateAmount");
        interest = request.getParameter("interest");
        interestConstPay = request.getParameter("interestConstPay");
        interestRateType = request.getParameter("interestRateType");
    }

%>
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
</head>
<body>
<div class="container">
    <div class="starter-template">
        <div class="row h-100 justify-content-center align-items-center">
            <div class="col-md-6">
                <form action="schedule" method="post">
                    <h2>Harmonogram spłat</h2>

                    <div class="form-group mt-4">
                        <label for="creditValue">Wnioskowana kwota kredytu</label>
                        <input class="form-control" type="text" name="creditValue" id="creditValue"
                               value="<% out.print(creditValue); %>"/>
                        <%
                            if (errors != null && errors.containsKey("creditValue")) {
                                out.print("<span class='text-danger'>" + errors.get("creditValue") + "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="interestRateAmount">Ilość rat</label>
                        <input class="form-control" type="text" name="interestRateAmount" id="interestRateAmount"
                               value="<% out.print(interestRateAmount); %>"/>
                        <%
                            if (errors != null && errors.containsKey("interestRateAmount")) {
                                out.print("<span class='text-danger'>" + errors.get("interestRateAmount") + "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="interest">Oprocentowanie</label>
                        <input class="form-control" type="text" name="interest" id="interest"
                               value="<% out.print(interest); %>"/>
                        <%
                            if (errors != null && errors.containsKey("interest")) {
                                out.print("<span class='text-danger'>" + errors.get("interest") + "</span>");
                            }
                        %>
                    </div>


                    <div class="form-group">
                        <label for="interestConstPay">Opłata stała</label>
                        <input class="form-control" type="text" name="interestConstPay" id="interestConstPay"
                               value="<% out.print(interestConstPay); %>"/>
                        <%
                            if (errors != null && errors.containsKey("interestConstPay")) {
                                out.print("<span class='text-danger'>" + errors.get("interestConstPay") + "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="interestRateType">Rodzaj rat</label>
                        <select class="form-control" id="interestRateType" name="interestRateType">
                            <option value="desc" <% if (interestRateType.equals("desc")) out.print("selected"); %>>
                                malejąca
                            </option>
                            <option value="const" <% if (interestRateType.equals("const")) out.print("selected"); %>>
                                stała
                            </option>
                        </select>
                        <%
                            if (errors != null && errors.containsKey("interestRateType")) {
                                out.print("<span class='text-danger'>" + errors.get("interestRateType") + "</span>");
                            }
                        %>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Przelicz harmonogram spłat</button>
                    </div>
                </form>
            </div>
            <%
                if (list != null) {
            %>
            <div class="col-md-6">
                <table class="table text-center">
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
                        for (InterestRate interestRate : list) {
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
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>