<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Records</title>
</head>
<body>
    <h1>Welcome ${u1.name}</h1>
    <p>Max User ID: ${u1.id}</p>

    <hr>    

    <h2>All User Records:</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date of Birth</th>
            <th>Gender</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Login ID</th>
            <th>Password</th>
        </tr>
        <c:forEach var="user" items="${allRecords}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.dateOfBirth}</td>
                <td>${user.gender}</td>
                <td>${user.address}</td>
                <td>${user.city}</td>
                <td>${user.state}</td>
                <td>${user.loginId}</td>
                <td>${user.password}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
