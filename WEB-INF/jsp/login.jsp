<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0; 
            margin: 0;
            padding: 0;
        }
        .container {
            width: 350px; 
            margin: 100px auto; 
            background-color: #fff; 
            border: 1px solid #ddd; 
            border-radius: 5px; 
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
            padding: 20px; 
        }
        .container h1 {
            text-align: center; 
            margin-bottom: 20px; 
        }
        label {
            display: block; 
            margin-bottom: 5px; 
        }
        input[type="text"], input[type="password"] {
            width: calc(100% - 22px); 
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 10px; 
            box-sizing: border-box; 
        }
        input[type="checkbox"] {
            margin-right: 5px; 
        }
        button[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 17px;
        }
        button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <form action="validate.htm" method="post">
        <label for="loginId">Login ID:</label>
        <input type="text" id="loginId" name="loginId" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <input type="checkbox" id="showPasswordCheckbox">
        <label for="showPasswordCheckbox">Show Password</label>
        
        <button type="submit">Login</button>
    </form>
</div>

<script>
    document.getElementById("showPasswordCheckbox").addEventListener("change", function () {
        var passwordField = document.getElementById("password");
        if (passwordField.type === "password") {
            passwordField.type = "text";
        } else {
            passwordField.type = "password";
        }
    });
</script>
</body>
</html>
