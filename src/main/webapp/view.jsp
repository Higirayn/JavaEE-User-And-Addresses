<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Список клиентов</title>
</head>
<body>

<form method="get" action="view">
  Тип клиента:
  <select name="type">
    <option value="">Все</option>
    <option value="Юридическое лицо">Юридическое лицо</option>
    <option value="Физическое лицо">Физическое лицо</option>
  </select>
  Поиск: <input type="text" name="search" />
  <input type="submit" value="Поиск" />
</form>

<table border="1">
  <tr>
    <th>ID</th>
    <th>Имя</th>
    <th>Тип</th>
    <th>Дата</th>
    <th>IP</th>
    <th>MAC</th>
    <th>Адрес</th>
    <th>Действия</th>
  </tr>

  <c:choose>
    <c:when test="${empty clients}">
      <tr>
        <td colspan="8">Нет данных для отображения</td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="client" items="${clients}">
        <c:choose>
          <c:when test="${empty client.addresses}">
            <tr>
              <td>${client.clientid}</td>
              <td>${client.client_name}</td>
              <td>${client.type}</td>
              <td>${client.added}</td>
              <td>—</td>
              <td>—</td>
              <td>—</td>
              <td>
                <a href="update?id=${client.clientid}">Редактировать</a> |
                <a href="delete?id=${client.clientid}" onclick="return confirm('Удалить клиента?')">Удалить</a>
              </td>
            </tr>
          </c:when>
          <c:otherwise>
            <c:forEach var="address" items="${client.addresses}">
              <tr>
                <td>${client.clientid}</td>
                <td>${client.client_name}</td>
                <td>${client.type}</td>
                <td>${client.added}</td>
                <td>${address.ip}</td>
                <td>${address.mac}</td>
                <td>${address.address}</td>
                <td>
                  <a href="update?id=${client.clientid}">Редактировать</a> |
                  <a href="delete?id=${client.clientid}" onclick="return confirm('Удалить клиента?')">Удалить</a>
                </td>
              </tr>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</table>

</body>
</html>