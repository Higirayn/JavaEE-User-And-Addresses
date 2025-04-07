<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<form method="post" action="create">
  Имя клиента: <input type="text" name="client_name" /><br>
  Тип клиента:
  <select name="type">
    <option>Юридическое лицо</option>
    <option>Физическое лицо</option>
  </select><br>
  IP-адрес: <input type="text" name="ip" /><br>
  MAC-адрес: <input type="text" name="mac" /><br>
  Модель устройства: <input type="text" name="model" /><br>
  Адрес: <input type="text" name="address" /><br>
  <input type="submit" value="Создать клиента" />
</form>

<%-- Вывод ошибок --%>
<c:if test="${not empty errors}">
  <ul>
    <c:forEach var="error" items="${errors}">
      <li style="color:red">${error}</li>
    </c:forEach>
  </ul>
</c:if>