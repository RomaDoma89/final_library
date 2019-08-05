<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/> " type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
    <title>Tom menu</title>
</head>
<body>
<header>
    <nav class="dws-menu">
        <ul>
            <li><a href="allBooks"><i class="fa fa-home"></i>Всі книги</a></li>
            <li><a href="allReaders"><i class="fa fa-home"></i>Всі читачі</a></li>
            <li><a href="#"></i>Пошук за параметром</a>
                <ul>
                    <li><a href="availableBookForm">Знайти чи доступна книга</a>
                    </li>
                    <li><a href="booksByAuthorForm">Вивести всі книжки по автору</a>
                    </li>
                    <li><a href="readerStatisticForm">Інформація про користувача</a>
                    </li>
                    <li><a href="getCountBookByPeriodForm">Дістати список книг виданих по періоду</a></li>
                    <li><a href="getBookStatistic">Статистика по певній книзі(к-ть використань)</a>
                    </li>
                    <li><a href="getPopularBookForm">Дістати по популярності</a>
                    </li>
                    <li><a href="getBlackList">Отримати "чорний список" користувачів</a></li>
                    <li><a href="generalStatisticForm">Статистика по читачам</a>
                    </li>
                    <li><a href="readerAvgForm">Ортимати середній вік читачів</a>
                    </li>
                    <li><a href="getCopiesInfoForm">Вивести дані по назві книжки, кількість екземплярів з інформацією про
                        них</a></li>

                </ul>
            </li>
            <li><a href="#"></i>Додати книгу</a>
            </li>
            <li><a href="#"></i>Оновити інформацію</a></li>
            <li><a href="error"></i>Видалити книгу</a></li>
        </ul>
    </nav>
</header>


</body>
</html>
