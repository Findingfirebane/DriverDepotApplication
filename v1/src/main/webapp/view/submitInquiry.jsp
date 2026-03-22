<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submit Inquiry - DriverDepot</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f9fafb;
            color: #1e293b;
        }

        header {
            background-color: #ffffff;
            border-bottom: 1px solid #e2e8f0;
            padding: 16px 32px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        header h1 {
            font-size: 22px;
            color: #475569;
            font-weight: 700;
        }

        header a {
            font-size: 14px;
            color: #475569;
            text-decoration: none;
        }

        header a:hover {
            text-decoration: underline;
        }

        .container {
            max-width: 600px;
            margin: 48px auto;
            padding: 0 16px;
        }

        .card {
            background-color: #ffffff;
            border: 1px solid #e2e8f0;
            border-radius: 12px;
            padding: 40px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.06);
        }

        .card h2 {
            font-size: 20px;
            color: #1e293b;
            margin-bottom: 8px;
        }

        .card p.subtitle {
            font-size: 14px;
            color: #64748b;
            margin-bottom: 32px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-size: 14px;
            font-weight: 600;
            color: #374151;
            margin-bottom: 6px;
        }

        input[type="text"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 10px 14px;
            border: 1px solid #d1d5db;
            border-radius: 8px;
            font-size: 14px;
            font-family: Arial, sans-serif;
            color: #1e293b;
            background-color: #ffffff;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        textarea:focus {
            outline: none;
            border-color: #475569;
        }

        input.readonly-field {
            background-color: #f1f5f9;
            color: #94a3b8;
            cursor: not-allowed;
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        .btn-submit {
            width: 100%;
            padding: 12px;
            background-color: #475569;
            color: #ffffff;
            border: none;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            margin-top: 8px;
        }

        .btn-submit:hover {
            background-color: #334155;
        }

        .alert-success {
            background-color: #f0fdf4;
            border: 1px solid #bbf7d0;
            color: #166534;
            padding: 14px 16px;
            border-radius: 8px;
            margin-bottom: 24px;
            font-size: 14px;
        }

        .alert-error {
            background-color: #fef2f2;
            border: 1px solid #fecaca;
            color: #991b1b;
            padding: 14px 16px;
            border-radius: 8px;
            margin-bottom: 24px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<header>
    <h1>DriverDepot</h1>
    <a href="${pageContext.request.contextPath}/notifications">Staff Portal</a>
</header>

<div class="container">
    <div class="card">
        <h2>Request More Information</h2>
        <p class="subtitle">Fill in your details below and a member of our team will be in touch.</p>

        <% if ("true".equals(request.getParameter("success"))) { %>
            <div class="alert-success">
                Your inquiry has been submitted successfully. We will be in touch soon!
            </div>
        <% } %>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert-error">
                ${error}
            </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/submitInquiry" method="post">

            <div class="form-group">
                <label for="vehicle">Vehicle</label>
                <input type="text"
                       id="vehicle"
                       name="vehicle"
                       value="${vehicle}"
                       class="readonly-field"
                       readonly />
            </div>

            <div class="form-group">
                <label for="customerName">Your Name</label>
                <input type="text"
                       id="customerName"
                       name="customerName"
                       placeholder="Enter your full name"
                       required />
            </div>

            <div class="form-group">
                <label for="customerEmail">Your Email</label>
                <input type="email"
                       id="customerEmail"
                       name="customerEmail"
                       placeholder="Enter your email address"
                       required />
            </div>

            <div class="form-group">
                <label for="message">Message <span style="font-weight:400; color:#94a3b8;">(optional)</span></label>
                <textarea id="message"
                          name="message"
                          placeholder="Any additional details you would like to share..."></textarea>
            </div>

            <button type="submit" class="btn-submit">Submit Inquiry</button>

        </form>
    </div>
</div>

</body>
</html>