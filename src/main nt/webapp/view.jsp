<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Список клиентов</title>
  <style>
    table { border-collapse: collapse; width: 100%; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
    .success { color: green; }
    .error { color: red; }
  </style>
</head>
<body>
<h1>Список клиентов</h1>

<c:if test="${not empty successMessage}">
  <p class="success">${successMessage}</p>
  <c:remove var="successMessage" scope="session"/>
</c:if>

<c:if test="${not empty errorMessage}">
  <p class="error">${errorMessage}</p>
  <c:remove var="errorMessage" scope="session"/>
</c:if>

<form action="${pageContext.request.contextPath}/view" method="get">
  <div>
    <label>Тип клиента:</label>
    <select name="typeFilter">
      <option value="">Все типы</option>
      <option value="Юридическое лицо" ${param.typeFilter == 'Юридическое лицо' ? 'selected' : ''}>
        Юридическое лицо
      </option>
      <option value="Физическое лицо" ${param.typeFilter == 'Физическое лицо' ? 'selected' : ''}>
        Физическое лицо
      </option>
    </select>
  </div>

  <div>
    <label>Поиск:</label>
    <input type="text" name="searchText" value="${param.searchText}">
    <button type="submit">Применить</button>
    <a href="${pageContext.request.contextPath}/view">Сбросить</a>
  </div>
</form>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Наименование</th>
    <th>Тип</th>
    <th>Дата добавления</th>
    <th>Действия</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${clients}" var="client">
    <tr>
      <td>${client.clientId}</td>
      <td>${client.clientName}</td>
      <td>${client.type}</td>
      <td><fmt:formatDate value="${client.added}" pattern="dd.MM.yyyy"/></td>
      <td>
        <a href="${pageContext.request.contextPath}/update?id=${client.clientId}">Редактировать</a>
        <a href="${pageContext.request.contextPath}/delete?id=${client.clientId}"
           onclick="return confirm('Вы уверены, что хотите удалить этого клиента?')">Удалить</a>
      </td>
    </tr>
    <c:forEach items="${client.addresses}" var="address">
      <tr>
        <td colspan="5">
          IP: ${address.ip}, MAC: ${address.mac},
          Модель: ${address.model}, Адрес: ${address.address}
        </td>
      </tr>
    </c:forEach>
  </c:forEach>
  </tbody>
</table>

<div>
  <a href="${pageContext.request.contextPath}/create">Добавить нового клиента</a>
</div>
</body>
</html>