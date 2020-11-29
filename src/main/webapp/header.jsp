<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <h1>Contact Tracing Service</h1>
    <nav>
        <ul>
            <li ${param.page eq 'Home'?'id="current"':""}>
                <a href="Controller?command=Home">Home</a>
            </li>

            <li ${param.page eq 'Register'?'id="current"':""}>
                <a href="Controller?command=Register">Register</a>
            </li>
            <c:if test="${not empty user}">
                <c:if test="${user.role=='admin'}">
                    <li ${param.page eq 'Overview'?'id="current"':""}>
                        <a href="Controller?command=Overview">Overview</a>
                    </li>
                </c:if>
                <li ${param.page eq 'Register Test'?'id="current"':""}>
                    <a href="Controller?command=RegisterPositivePage">Register Test</a>
                </li>
                <li ${param.page eq 'Contacts'?'id="current"':""}>
                    <a href="Controller?command=Contacts">Contacts</a>
                </li>
                <li ${param.page eq 'Search'?'id="current"':""} id="searchnav">
                    <a href="Controller?command=Search">Search</a>
                </li>
                <li ${param.page eq 'Signout'?'id="current"':""}>
                    <a href="Controller?command=SignOut">Sign out</a>
                </li>
            </c:if>
        </ul>
    </nav>
    <h2>${param.page}</h2>
</header>