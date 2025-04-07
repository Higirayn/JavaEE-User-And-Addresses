<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Редактирование клиента</title>
  <style>
    .error { color: red; }
  </style>
</head>
<body>
<h1>Редактирование клиента</h1>

<c:if test="${not empty error}">
  <p class="error">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/update" method="post">
  <input type="hidden" name="clientId" value="${client.clientId}">

  <h2>Информация о клиенте</h2>
  <div>
    <label>Наименование клиента:</label>
    <input type="text" name="clientName" value="${client.clientName}" required maxlength="100">
  </div>

  <div>
    <label>Тип клиента:</label>
    <select name="type" required>
      <option value="Юридическое лицо" ${client.type == 'Юридическое лицо' ? 'selected' : ''}>
        Юридическое лицо
      </option>
      <option value="Физическое лицо" ${client.type == 'Физическое лицо' ? 'selected' : ''}>
        Физическое лицо
      </option>
    </select>
  </div>

  <h2>Адресные данные</h2>
  <c:forEach items="${client.addresses}" var="address" varStatus="loop">
    <input type="hidden" name="addresses[${loop.index}].id" value="${address.id}">

    <div>
      <label>IP-адрес:</label>
      <input type="text" name="addresses[${loop.index}].ip" value="${address.ip}" required>
    </div>

    <div>
      <label>MAC-адрес:</label>
      <input type="text" name="addresses[${loop.index}].mac" value="${address.mac}" required>
    </div>

    <div>
      <label>Модель устройства:</label>
      <input type="text" name="addresses[${loop.index}].model" value="${address.model}">
    </div>

    <div>
      <label>Адрес места нахождения:</label>
      <input type="text" name="addresses[${loop.index}].address" value="${address.address}" required>
    </div>
  </c:forEach>

  <button type="submit">Сохранить</button>
  <a href="${pageContext.request.contextPath}/view">Отмена</a>
</form>
</body>
</html>