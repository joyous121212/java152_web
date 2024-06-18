<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Lab 5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous" />
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="My Page" scope="page" />
        <%@ include file="../fragments/header.jspf" %>
        
        <main>
            <div class="mt-2 card">
                <div card="card-header">
                    <h2>내 정보</h2>
                </div>
                <div class="card-body">
                    <form>
                        <div class="mt-2">
                            <label for="id" class="form-label">아이디</label>
                            <input id="id" class="form-control" type="text"
                                name="userid" value="${user.userid}" readonly/>
                        </div>
                        <div class="mt-2">
                            <label for="password" class="form-label">비밀번호</label>
                            <input id="password" class="form-control" type="password"
                                name="password" value="${user.password}" readonly/>
                        </div>
                        <div class="mt-2">
                            <label for="email" class="form-label">이메일</label>
                            <input id="email" class="form-control" type="email"
                                name="email" value="${user.email}" readonly/>
                        </div>
                        <div class="mt-2">
                            <label for="points" class="form-label">포인트</label>
                            <input id="points" class="form-control" type="text"
                                value="${user.points}" readonly/>
                        </div>
                        <div class="card-footer"></div>
                    </form>
                </div>
            </div>
        </main>
        
    </div>
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
</body>
</html>