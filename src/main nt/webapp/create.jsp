<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Добавление нового клиента</title>
  <style>
    .error { color: red; }
    .success { color: green; }
  </style>
</head>
<body>
<h1>Добавление нового клиента</h1>

<c:if test="${not empty error}">
  <p class="error">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/create" method="post">
  <h2>Информация о клиенте</h2>
  <div>
    <label>Наименование клиента:</label>
    <input type="text" name="clientName" required maxlength="100">
  </div>

  <div>
    <label>Тип клиента:</label>
    <select name="type" required>
      <option value="">Выберите тип</option>
      <option value="Юридическое лицо">Юридическое лицо</option>
      <option value="Физическое лицо">Физическое лицо</option>
    </select>
  </div>

  <h2>Адресные данные</h2>
  <div>
    <label>IP-адрес:</label>
    <input type="text" name="ip" required placeholder="192.168.0.1">
  </div>

  <div>
    <label>MAC-адрес:</label>
    <input type="text" name="mac" required placeholder="00-1A-2B-3C-4D-5E">
  </div>

  <div>
    <label>Модель устройства:</label>
    <input type="text" name="model" maxlength="100">
  </div>

  <div>
    <label>Адрес места нахождения:</label>
    <input type="text" name="address" required maxlength="200">
  </div>

  <button type="submit">Создать</button>
  <a href="${pageContext.request.contextPath}/view">Отмена</a>
</form>
</body>
</html>