<%@ page import="models.InterestRate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
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
        HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
        String creditValue = "";
        String interestRateAmount = "";
        String interestPercent = "";
        String interestConstPay = "";
        String interestRateType = "";
        if (request.getParameter("action") != null) {
            creditValue = request.getParameter("creditValue");
            interestRateAmount = request.getParameter("interestRateAmount");
            interestPercent = request.getParameter("interestPercent");
            interestConstPay = request.getParameter("interestConstPay");
            interestRateType = request.getParameter("interestRateType");
        }

    %>
</head>
<body>
<div class="container">
    <div class="starter-template">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6">
                <form action="scheduler" method="post">
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
                                out.print("<span class=\"text-danger\">" + errors.get("interestRateAmount") + "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="interestPercent">Oprocentowanie</label>
                        <input class="form-control" type="text" name="interestPercent" id="interestPercent"
                               value="<% out.print(interestPercent); %>"/>
                        <%
                            if (errors != null && errors.containsKey("interestPercent")) {
                                out.print("<span class=\"text-danger\">" + errors.get("interestPercent") + "</span>");
                            }
                        %>
                    </div>


                    <div class="form-group">
                        <label for="interestConstPay">Opłata stała</label>
                        <input class="form-control" type="text" name="interestConstPay" id="interestConstPay"
                               value="<% out.print(interestConstPay); %>"/>
                        <%
                            if (errors != null && errors.containsKey("interestConstPay")) {
                                out.print("<span class=\"text-danger\">" + errors.get("interestConstPay") + "</span>");
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
                                out.print("<span class=\"text-danger\">" + errors.get("interestRateType") + "</span>");
                            }
                        %>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary" name="action" value="online">Wygeneruj online
                        </button>
                        <button type="submit" class="btn btn-primary" name="action" value="pdf">Wygeneruj do PDF
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>