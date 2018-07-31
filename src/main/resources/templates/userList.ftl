<!DOCTYPE html>

<html lang="en">

<body>
<#list userList as user>

City: ${user.username}! <br>
Q:Why I like? <br>
A:${user.age}!

</#list>
</body>

</html>