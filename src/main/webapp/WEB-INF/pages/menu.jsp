<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/resources/style.css"/> " type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
    <title>Tom menu</title>
</head>
<body>
<header>
    <nav class="dws-menu">
        <ul>
            <li><a href="allBooks"><i class="fa fa-home"></i>Всі книги</a></li>
            <li><a href="#"></i>Пошук за параметром</a>
                <ul>
                    <li><a href="availableBookForm">Знайти чи доступна книга</a>
                    </li>
                    <li><a href="booksByAuthorForm">Вивести всі книжки по автору</a>
                    </li>
                    <li><a href="userStatisticForm">Інформація про користувача</a>
                    </li>
                    <li><a href="inputGetByPeriod">Дістати список книг виданих по періоду</a></li>
                    <li><a href="inputBookStat">Статистика по певній книзі(к-ть використань)</a>
                    </li>
                    <li><a href="inputGetPopular">Дістати по популярності</a>
                    </li>
                    <li><a href="getBlackList">Отримати "чорний список" користувачів</a></li>
                    <li><a href="#">Статистика по читачам</a>
                        <ul>
                            <li><a href="readerRegistration">Середній час читання</a></li>
                            <li><a href="averageAgeOfReader">Середній вік </a></li>
                            <li><a href="appealStatistic">Кількість звернень </a></li>
                        </ul>
                    </li>
                    <li><a href="inputAvgAge">Ортимати середній вік читачів</a>
                    </li>
                    <li><a href="inputInfo">Вивести дані по назві книжки, кількість екземплярів з інформацією про них</a></li>

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
