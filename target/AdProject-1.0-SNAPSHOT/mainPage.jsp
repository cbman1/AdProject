<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>AdBoard</title>
<%--    <script src="${pageContext.request.contextPath}/js/script.js"></script>--%>
</head>
<body>
<div style="position: fixed; left:5%">
    <a href="${pageContext.request.contextPath}/mainPage"><button>Main</button></a>
</div>
<div>
    <c:if test="${empty sessionScope.user}">
    <div style="position:fixed; left:77%">
        <a href="${pageContext.request.contextPath}/signUp"><button>Sign Up</button></a>
    </div>
    <div style="position:fixed; left:85%">
        <a href="${pageContext.request.contextPath}/signIn"><button>Sign In</button></a>
    </div>
    </c:if>

    <c:if test="${!empty sessionScope.user}">
        <div style="position:fixed; left:73%">
            <a href="${pageContext.request.contextPath}/addAdvert"><button>Add Advert</button></a>
        </div>
        <div style="position:fixed; left:65%">
            <a href="${pageContext.request.contextPath}/profile?id=${sessionScope.user.id}"><button>${sessionScope.user.firstName} ${sessionScope.user.lastName}</button></a>
        </div>
        <div style="position:fixed; left:80%">
            <a href="${pageContext.request.contextPath}/favourites"><button>Favourites</button></a>
        </div>
        <div style="position:fixed; left:87.5%">
            <a href="${pageContext.request.contextPath}/logout"><button>Logout</button></a>
        </div>
    </c:if>
</div>
<div>
    <form>
        <div style="position:fixed; left: 34%; top: 8%">
        <label for="searchInput"></label>
            <input id="searchInput" type="text" name="searchInput" placeholder="Write the advert name" value=""/>
        </div>
        <div style="position: fixed; left: 48.5%; top: 8%;">
            <label for="city"></label><select name="city" id="city">
            <option value="" selected disabled hidden>Выберите город</option>
            <option value="Abakan">Абакан</option>
            <option value="Almetyevsk">Альметьевск</option>
            <option value="Anadyr">Анадырь</option>
            <option value="Anapa">Анапа</option>
            <option value="Arkhangelsk">Архангельск</option>
            <option value="Astrakhan">Астрахань</option>
            <option value="Barnaul">Барнаул</option>
            <option value="Belgorod">Белгород</option>
            <option value="Beslan">Беслан</option>
            <option value="Biysk">Бийск</option>
            <option value="Birobidzhan">Биробиджан</option>
            <option value="Blagoveshchensk">Благовещенск</option>
            <option value="Bologoye">Бологое</option>
            <option value="Bryansk">Брянск</option>
            <option value="Veliky Novgorod">Великий Новгород</option>
            <option value="Veliky Ustyug">Великий Устюг</option>
            <option value="Vladivostok">Владивосток</option>
            <option value="Vladikavkaz">Владикавказ</option>
            <option value="Vladimir">Владимир</option>
            <option value="Volgograd">Волгоград</option>
            <option value="Vologda">Вологда</option>
            <option value="Vorkuta">Воркута</option>
            <option value="Voronezh">Воронеж</option>
            <option value="Gdov">Гдов</option>
            <option value="Gelendzhik">Геленджик</option>
            <option value="Gorno-Altaysk">Горно-Алтайск</option>
            <option value="Grozny">Грозный</option>
            <option value="Gudermes">Гудермес</option>
            <option value="Gus-Khrustalny">Гусь-Хрустальный</option>
            <option value="Dzerzhinsk">Дзержинск</option>
            <option value="Dmitrov">Дмитров</option>
            <option value="Dubna">Дубна</option>
            <option value="Yeysk">Ейск</option>
            <option value="Yekaterinburg">Екатеринбург</option>
            <option value="Yelabuga">Елабуга</option>
            <option value="Yelets">Елец</option>
            <option value="Yessentuki">Ессентуки</option>
            <option value="Zlatoust">Златоуст</option>
            <option value="Ivanovo">Иваново</option>
            <option value="Izhevsk">Ижевск</option>
            <option value="Irkutsk">Иркутск</option>
            <option value="Yoshkar-Ola">Йошкар-Ола</option>
            <option value="Kazan">Казань</option>
            <option value="Kaliningrad">Калининград</option>
            <option value="Kaluga">Калуга</option>
            <option value="Kemerovo">Кемерово</option>
            <option value="Kislovodsk">Кисловодск</option>
            <option value="Komsomolsk-on-Amur">Комсомольск-на-Амуре</option>
            <option value="Kotlas">Котлас</option>
            <option value="Krasnodar">Краснодар</option>
            <option value="Krasnoyarsk">Красноярск</option>
            <option value="Kurgan">Курган</option>
            <option value=Kursk">Курск</option>
            <option value="Kyzyl">Кызыл</option>
            <option value="Leninogorsk">Лениногорск</option>
            <option value="Lensk">Ленск</option>
            <option value="Lipetsk">Липецк</option>
            <option value="Luga">Луга</option>
            <option value="Lyuban">Любань</option>
            <option value="Lyubertsy">Люберцы</option>
            <option value="Magadan">Магадан</option>
            <option value="Maykop">Майкоп</option>
            <option value="Makhachkala">Махачкала</option>
            <option value="Miass">Миасс</option>
            <option value="Mineralnye Vody">Минеральные Воды</option>
            <option value="Mirny">Мирный</option>
            <option value="Moscow">Москва</option>
            <option value="Murmansk">Мурманск</option>
            <option value="Murom">Муром</option>
            <option value="Mytishchi">Мытищи</option>
            <option value="Naberezhnye Chelny">Набережные Челны</option>
            <option value="Nadym">Надым</option>
            <option value="Nalchik">Нальчик</option>
            <option value="Nazran">Назрань</option>
            <option value="Naryan-Mar">Нарьян-Мар</option>
            <option value="Nakhodka">Находка</option>
            <option value="Nizhnevartovsk">Нижневартовск</option>
            <option value="Nizhnekamsk">Нижнекамск</option>
            <option value="Nizhny Novgorod">Нижний Новгород</option>
            <option value="Nizhny Tagil">Нижний Тагил</option>
            <option value="Novokuznetsk">Новокузнецк</option>
            <option value="Novosibirsk">Новосибирск</option>
            <option value="Novy Urengoy">Новый Уренгой</option>
            <option value="Norilsk">Норильск</option>
            <option value="Obninsk">Обнинск</option>
            <option value="Oktyabrsky">Октябрьский</option>
            <option value="Omsk">Омск</option>
            <option value="Orenburg">Оренбург</option>
            <option value="Orekhovo-Zuyevo">Орехово-Зуево</option>
            <option value="Oryol">Орёл</option>
            <option value="Penza">Пенза</option>
            <option value="Perm">Пермь</option>
            <option value="Petrozavodsk">Петрозаводск</option>
            <option value="Petropavlovsk-Kamchatsky">Петропавловск-Камчатский</option>
            <option value="Podolsk">Подольск</option>
            <option value="Pskov">Псков</option>
            <option value="Pyatigorsk">Пятигорск</option>
            <option value="Rostov-on-Don">Ростов-на-Дону</option>
            <option value="Rybinsk">Рыбинск</option>
            <option value="Ryazan">Рязань</option>
            <option value="Salekhard">Салехард</option>
            <option value="Samara">Самара</option>
            <option value="Saint Petersburg">Санкт-Петербург</option>
            <option value="Saransk">Саранск</option>
            <option value="Saratov">Саратов</option>
            <option value="Severodvinsk">Северодвинск</option>
            <option value="Smolensk">Смоленск</option>
            <option value="Sol-Iletsk">Соль-Илецк</option>
            <option value="Sochi">Сочи</option>
            <option value="Stavropol">Ставрополь</option>
            <option value="Surgut">Сургут</option>
            <option value="Syktyvkar">Сыктывкар</option>
            <option value="Tambov">Тамбов</option>
            <option value="Tver">Тверь</option>
            <option value="Tobolsk">Тобольск</option>
            <option value="Tolyatti">Тольятти</option>
            <option value="Tomsk">Томск</option>
            <option value="Tuapse">Туапсе</option>
            <option value="Tula">Тула</option>
            <option value="Tynda">Тында</option>
            <option value="Tyumen">Тюмень</option>
            <option value="Ulan-Ude">Улан-Уде</option>
            <option value="Ulyanovsk">Ульяновск</option>
            <option value="Ufa">Уфа</option>
            <option value="Khabarovsk">Хабаровск</option>
            <option value="Khanty-Mansiysk">Ханты-Мансийск</option>
            <option value="Chebarkul">Чебаркуль</option>
            <option value="Cheboksary">Чебоксары</option>
            <option value="Chelyabinsk">Челябинск</option>
            <option value="Cherepovets">Череповец</option>
            <option value="Cherkessk">Черкесск</option>
            <option value="Chistopol">Чистополь</option>
            <option value="Chita">Чита</option>
            <option value="Shadrinsk">Шадринск</option>
            <option value="Shatura">Шатура</option>
            <option value="Shuya">Шуя</option>
            <option value="Elista">Элиста</option>
            <option value="Engels">Энгельс</option>
            <option value="Yuzhno-Sakhalinsk">Южно-Сахалинск</option>
            <option value="Yakutsk">Якутск</option>
            <option value="Yaroslavl">Ярославль</option>
        </select>
        </div>
        <div style="position: fixed; left: 65%; top: 8%">
        <button type="submit">Search</button>
        </div>
    </form>

    <div style="position: fixed; left:35%; top:20%">
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Category</th>
                <th>Price</th>
                <th>Go to ad</th>
            </tr>
            <c:if test="${!empty requestScope.listBySearch}">
                <c:forEach items="${requestScope.listBySearch}" var="advert">
                    <tr>
                        <td>${advert.name}</td>
                        <td>${advert.description}</td>
                        <td>${advert.category}</td>
                        <td>${advert.price}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/advert">
                                <input type="hidden" name="id" value="${advert.id}">
                                <input type="submit" value="go">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

            <c:if test="${!empty requestScope.listByCity}">
                <c:forEach items="${requestScope.listByCity}" var="advert">
                    <tr>
                        <td>${advert.name}</td>
                        <td>${advert.description}</td>
                        <td>${advert.category}</td>
                        <td>${advert.price}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/advert">
                                <input type="hidden" name="id" value="${advert.id}">
                                <input type="submit" value="go">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>


            <c:if test="${empty requestScope.listBySearch && empty requestScope.listByCity}">
                        <c:forEach items="${requestScope.adverts}" var="advert">
            <tr>
                <td>${advert.name}</td>
                <td>${advert.description}</td>
                <td>${advert.category}</td>
                <td>${advert.price}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/advert">
                        <input type="hidden" name="id" value="${advert.id}">
                        <input type="submit" value="go">
                    </form>
                </td>
            </tr>
            </c:forEach>
            </c:if>

        </table>
    </div>
</div>
</body>

</html>