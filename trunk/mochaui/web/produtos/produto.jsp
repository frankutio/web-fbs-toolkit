<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Cadastro de Produtos</title>
    </head>

    <body>
        <h1>Lista de produtos</h1>

        <br />
        <br />

        <table>
            <tr>
                <td rowspan="2"><img src="${Produto.foto}" alt="${Produto.nome}" /></td>
                <td>Nome: <strong>${Produto.nome}</strong></td>
            </tr>
            <tr>
                <td>Valor: <strong>${Produto.valor}</strong></td>
            </tr>
        </table>

        <br />
        <br />

        <strong>${Produto.descricao}</strong>
    </body>
</html>
