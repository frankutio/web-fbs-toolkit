<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Cadastro de Produtos</title>
    </head>

    <body>
        <h1>Cadastro de produtos</h1>

        <br />
        <br />

        <form name="cadPro" action="produtos?operacao=cadProd" method="post" enctype="multipart/form-data">           

            Nome do produto <input type="text" name="nome" /> <br /><br />

            Valor do produto <input type="text" name="valor" /> <br /><br />

            Descrição do produto:<br />

            <textarea name="descricao" rows="3" cols="60"></textarea> <br /><br />

            Foto do produto <input type="file" name="foto" /> <br /><br />

            <dir align="center">
                <input type="submit" name="ok" value="Cadastrar" />
            </dir>
        </form>
        
        <br />
        <br />

        <c:if test="${Error != null}">
            Ops! parace que houve um erro ao tentar carregar o arquivo!<br />
            Erro: ${Error}
        </c:if>
    </body>
</html>
