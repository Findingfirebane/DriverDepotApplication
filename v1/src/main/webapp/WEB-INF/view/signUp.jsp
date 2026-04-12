<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account - DriverDepot</title>

<style>
body{
font-family:Arial;
background:#f8fafc;
display:flex;
justify-content:center;
align-items:center;
height:100vh;
}

.card{
background:white;
padding:40px;
border-radius:16px;
width:400px;
box-shadow:0 6px 18px rgba(0,0,0,0.08);
}

h2{
margin-bottom:20px;
color:#334155;
}

label{
font-size:14px;
font-weight:600;
color:#334155;
}

input,select{
width:100%;
padding:10px;
margin-top:6px;
margin-bottom:18px;
border-radius:8px;
border:1px solid #cbd5e1;
}

button{
width:100%;
padding:12px;
background:#475569;
color:white;
border:none;
border-radius:8px;
font-size:15px;
font-weight:600;
cursor:pointer;
}

button:hover{
background:#334155;
}

.message{
margin-bottom:15px;
padding:10px;
border-radius:8px;
font-size:14px;
}

.error{
background:#fef2f2;
border:1px solid #fecaca;
color:#991b1b;
}

.success{
background:#f0fdf4;
border:1px solid #bbf7d0;
color:#166534;
}

.back{
display:block;
margin-top:15px;
text-align:center;
font-size:14px;
text-decoration:none;
color:#475569;
}
</style>
</head>

<body>

<div class="card">

<h2>Create Account 🚗</h2>

<% if(request.getAttribute("error") != null){ %>
<div class="message error">
<%= request.getAttribute("error") %>
</div>
<% } %>

<form action="<%=request.getContextPath()%>/signUp" method="post">

<label>Username</label>
<input type="text" name="username" required>

<label>Email</label>
<input type="email" name="email" required>

<label>Password</label>
<input type="password" name="password" required>

<label>Role</label>
<select name="role">
<option value="1">Customer</option>
<option value="2">Staff</option>
</select>

<button type="submit">Sign Up</button>

</form>

<a class="back" href="<%=request.getContextPath()%>/searchVehicles">
← Back to Dashboard
</a>

</div>

</body>
</html>