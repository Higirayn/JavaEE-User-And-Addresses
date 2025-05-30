# Система управления пользователями и адресами

Простое веб-приложение для работы с базой пользователей и их адресами. Разработано как учебный проект на Java EE.

## Что это?

Это серверное приложение, которое позволяет:
- Добавлять новых пользователей
- Редактировать существующие записи
- Удалять пользователей
- Привязывать к пользователям несколько адресов
- Искать пользователей по разным критериям

## Технологии в проекте

**Серверная часть:**
- Java EE 8
- Сервлеты
- Hibernate для работы с базой

**Клиентская часть:**
- Простые JSP-страницы
- Bootstrap для базового оформления

**Инфраструктура:**
- WildFly 26 в качестве сервера приложений
- MySQL для хранения данных

## Как запустить?

1. **Установите WildFly**  
   Качаем версию 26.0.1 с [официального сайта](https://www.wildfly.org/downloads/) и распаковываем куда-нибудь.

2. **Настройка сервера**  
   В папке WildFly создаем директорию `modules` и копируем туда наши модули из проекта.

3. **База данных**  
   Нужно заменить стандартный файл конфигурации `standalone.xml` на наш и поправить в нем настройки подключения к MySQL:

   ```xml
   <datasource jndi-name="java:/OurDatabase" pool-name="OurDB">
       <connection-url>jdbc:mysql://localhost:3306/user_db</connection-url>
       <driver>mysql</driver>
       <security>
           <user-name>db_user</user-name>
           <password>secure_password</password>
       </security>
   </datasource>
