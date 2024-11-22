package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;

public class HistoryAttachmentDocument {

    // Id записи XRecID
    private Long id;

    //Действие:
    //«0» – Просмотр теневой копии
    //«1» – Экспорт без блокировки
    //«2» – Блокировка
    //«3» – Импорт с разблокировкой
    //«A» – Изменение прав доступа
    //«B» – Изменение видимости версии
    //«C» – Создание
    //«D» – Удаление
    //«E» – Изменение
    //«F» – Шифрование сертификатом
    //«G» – Подписание
    //«H» – Удаление версии
    //«I» – Импорт без разблокировки
    //«J» – Изменение стадии жизненного цикла
    //«K» – Изменение карточки
    //«L» – Шифрование паролем и сертификатом
    //«M» – Изменение состояния версии
    //«N» – Отключение шифрования
    //«O» – Изменение пароля
    //«P» – Шифрование паролем
    //«Q» – Восстановление из локальной копии
    //«R» – Просмотр
    //«S» – Отправка по почте
    //«T» – Изменение хранилища
    //«U» – Разблокировка
    //«V» – Создание версии
    //«W» – Изменение вида документа
    //«X» – Экспорт с блокировкой
    //«Y» – Изменение типа карточки
    //«Z» – Копирование
    private String action;

    // Дата действия
    private Instant dateAction;

    // Пользователь сделавший изменение/действие
    private String author;

    //Подробности. Значения основных полей (ИД записи, Имя, Заголовок)
    private String detail;

}
