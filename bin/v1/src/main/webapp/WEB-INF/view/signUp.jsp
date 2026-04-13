<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Signup Test</title>
</head>
<body>

<h2>Signup Form</h2>

<form action="signUp" method="post">
    
    <label>Username:</label><br>
    <input type="text" name="username" required><br><br>
    
    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>
    
    <label>Password:</label><br>
    <input type="password" name="password" required><br><br>
    
    <label>Role:</label><br>
    <select name="role">
        <option value="1">User</option>
        <option value="2">Admin</option>
    </select><br><br>
    
    <input type="submit" value="Sign Up">

</form>

</body>
</html>