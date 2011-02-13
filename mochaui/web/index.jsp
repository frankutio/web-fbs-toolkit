<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>JSP Page</title>
    </head>

    <body>
       
        <h1>Menu</h1>
        <ul>
            <li><a href="/mochaui/apaga.jsp">Upload</a></li>
            <li><a href="/mochaui/gerProdutos/cmsProduto.jsp">Cadastro de Produto</a></li>
            <li><a href="/mochaui/produtos?operacao=listProd">Listar produtos</a></li>
        </ul>

        ${Produto.foto}
        
    </body>

</html>
