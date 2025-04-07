<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<form method="post" action="update">
  <input type="hidden" name="clientid" value="${client.clientid}" />
  Имя клиента: <input type="text" name="client_name" value="${client.client_name}" /><br>
  Тип клиента:
  <select name="type">
    <option ${client.type == 'Юридическое лицо' ? 'selected' : ''}>Юридическое лицо</option>
    <option ${client.type == 'Физическое лицо' ? 'selected' : ''}>Физическое лицо</option>
  </select><br>

  <h3>Адреса:</h3>
  <div id="addresses">
    <c:forEach var="address" items="${client.addresses}" varStatus="loop">
      <div class="address">
        IP: <input type="text" name="ip" value="${address.ip}" /><br>
        MAC: <input type="text" name="mac" value="${address.mac}" /><br>
        Модель: <input type="text" name="model" value="${address.model}" /><br>
        Адрес: <input type="text" name="address" value="${address.address}" /><br>
        <hr>
      </div>
    </c:forEach>
  </div>

  <input type="submit" value="Сохранить изменения" />
</form>