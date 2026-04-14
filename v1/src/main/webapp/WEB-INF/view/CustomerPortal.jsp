<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="app.model.Notification" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notifications - DriverDepot Staff Portal</title>
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

        /* ---------- SIDEBAR ---------- */
        .layout {
            display: flex;
            min-height: 100vh;
        }

        .sidebar {
            width: 240px;
            background-color: #ffffff;
            border-right: 1px solid #e2e8f0;
            display: flex;
            flex-direction: column;
            padding: 24px 16px;
        }

        .sidebar h1 {
            font-size: 20px;
            color: #475569;
            font-weight: 700;
            margin-bottom: 4px;
        }

        .sidebar p.portal-label {
            font-size: 12px;
            color: #94a3b8;
            margin-bottom: 32px;
        }

        .sidebar a {
            display: block;
            padding: 10px 14px;
            border-radius: 8px;
            font-size: 14px;
            color: #475569;
            text-decoration: none;
            margin-bottom: 4px;
        }

        .sidebar a:hover {
            background-color: #f1f5f9;
        }

        .sidebar a.active {
            background-color: #f1f5f9;
            color: #1e293b;
            font-weight: 600;
        }

        .sidebar a.customer-link {
            margin-top: auto;
            color: #94a3b8;
            font-size: 13px;
        }

        /* ---------- MAIN CONTENT ---------- */
        .main {
            flex: 1;
            padding: 40px;
        }

        .page-header {
            margin-bottom: 32px;
        }

        .page-header h2 {
            font-size: 22px;
            color: #1e293b;
            margin-bottom: 4px;
        }

        .page-header p {
            font-size: 14px;
            color: #64748b;
        }

        /* ---------- CARD ---------- */
        .card {
            background-color: #ffffff;
            border: 1px solid #e2e8f0;
            border-radius: 12px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.06);
            overflow: hidden;
        }

        .card-header {
            padding: 20px 24px;
            border-bottom: 1px solid #e2e8f0;
        }

        .card-header h3 {
            font-size: 16px;
            color: #1e293b;
        }

        .card-header p {
            font-size: 13px;
            color: #64748b;
            margin-top: 2px;
        }

        /* ---------- TABLE ---------- */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            background-color: #f8fafc;
        }

        thead th {
            padding: 12px 24px;
            text-align: left;
            font-size: 13px;
            font-weight: 600;
            color: #475569;
            border-bottom: 1px solid #e2e8f0;
        }

        tbody tr {
            border-bottom: 1px solid #f1f5f9;
        }

        tbody tr:hover {
            background-color: #f8fafc;
        }

        tbody tr:last-child {
            border-bottom: none;
        }

        tbody td {
            padding: 14px 24px;
            font-size: 14px;
            color: #374151;
            vertical-align: top;
        }

        td.message-cell {
            max-width: 280px;
            color: #64748b;
            font-size: 13px;
        }

        td.date-cell {
            color: #94a3b8;
            font-size: 13px;
            white-space: nowrap;
        }

        /* ---------- DELETE BUTTON ---------- */
        .btn-delete {
            padding: 6px 14px;
            background-color: #ffffff;
            color: #dc2626;
            border: 1px solid #fca5a5;
            border-radius: 6px;
            font-size: 13px;
            cursor: pointer;
        }

        .btn-delete:hover {
            background-color: #fef2f2;
        }

        /* ---------- EMPTY STATE ---------- */
        .empty-state {
            padding: 48px 24px;
            text-align: center;
            color: #94a3b8;
            font-size: 14px;
        }

        /* ---------- ALERTS ---------- */
        .alert-error {
            background-color: #fef2f2;
            border: 1px solid #fecaca;
            color: #991b1b;
            padding: 14px 16px;
            border-radius: 8px;
            margin-bottom: 24px;
            font-size: 14px;
        }

        .badge {
            display: inline-block;
            background-color: #f1f5f9;
            color: #475569;
            font-size: 12px;
            font-weight: 600;
            padding: 2px 8px;
            border-radius: 99px;
            margin-left: 8px;
        }
    </style>
</head>
<body>

<div class="layout">

    <!-- Sidebar -->
    <aside class="sidebar">
        <h1>DriverDepot</h1>
        <p class="portal-label">Customer Portal</p>

        <a href="${pageContext.request.contextPath}/notificationsByUser" class="active">
            Dashboard
        </a>
        <a href="${pageContext.request.contextPath}/searchVehicles" class="active">
            Vehicle Search
        </a>
        <a href="${pageContext.request.contextPath}/submitInquiry" class="active">
            Submit Inquiry
        </a>

        <a href="${pageContext.request.contextPath}/searchVehicles" class="customer-link">
            ← Search for a car
        </a>
    </aside>

    <!-- Main Content -->
    <main class="main">

        <div class="page-header">
            <h2>Customer Portal
                <%
                    List<Notification> notifications =
                        (List<Notification>) request.getAttribute("notifications");
                    if (notifications != null) {
                %>
                    <span class="badge"><%= notifications.size() %></span>
                <% } %>
            </h2>
            <p>Manage your inqueries.</p>
        </div>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert-error">${error}</div>
        <% } %>

        <div class="card">
            <div class="card-header">
                <h3>Pending Inquiries</h3>
                <p>Click Delete once you have followed up with the customer.</p>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Customer</th>
                        <th>Vehicle</th>
                        <th>Email</th>
                        <th>Message</th>
                        <th>Submitted</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    if (notifications == null || notifications.isEmpty()) {
                %>
                    <tr>
                        <td colspan="6" class="empty-state">
                            No pending inquiries. Check back later.
                        </td>
                    </tr>
                <%
                    } else {
                        for (Notification n : notifications) {
                %>
                    <tr>
                        <td><strong><%= n.getCustomerName() %></strong></td>
                        <td><%= n.getVehicle() %></td>
                        <td><%= n.getCustomerEmail() %></td>
                        <td class="message-cell"><%= n.getMessage() != null ? n.getMessage() : "-" %></td>
                        <td class="date-cell"><%= n.getSubmittedAt() %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/notifications"
                                  method="post"
                                  style="margin:0;">
                                <input type="hidden" name="id" value="<%= n.getId() %>" />
                                <button type="submit" class="btn-delete">Delete</button>
                            </form>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>

    </main>
</div>

</body>
</html>