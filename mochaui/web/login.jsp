<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Página de Login</title>
    </head>

    <body>
        <h1>Login</h1>

        <br />
        <br />

      <c:if test="${Login == null}">

        <form name="formLog" action="/produtos?operacao=login" method="post">

            Usuário: <input type="text" name="usr" />
            <br />
            <br />

            Senha: <input type="password" name="senha" />

            <br />
            <br />

            <input type="submit" name="ok" value="Entrar" />
        </form>

        <br />
        <br />

        <c:if test="${MsgErro != null}">
            <span style="color: red;">${MsgErro}</span>
        </c:if>
      </c:if>


      <c:if test="${Login != null}">
          Você já esta logado! <a href="/gerProduto/cmsProduto.jsp">Clique aqui</a>
      </c:if>


            
    </body>
</html>
