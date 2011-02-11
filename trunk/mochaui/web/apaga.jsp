<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>JSP Page</title>
    </head>

    <body>
        <h1>Apagando arquivos do servidor</h1>

        <form name="formDel" action="servletDel" method="post">
            <input type="hidden" name="operacao" value="pagar" />

            Nome do arquivo a ser apagado: <input type="text" name="arquivo" />
            <br />
            <br />

            <input type="submit" value="Apagar" />

        </form>

        ${img}

        <br />
        <br />
        <br />

        <fieldset>
            <legend>Upload de Arquivos</legend>

            <form name="formUp" action="servletDel?operacao=upaload" method="post" enctype="multipart/form-data">

                Arquivo: <input type="file" name="foto" />

                <br />
                <br />
                <input type="submit" value="Upload" />
            </form>

            <br />
            <br />

            <fieldset>
                <legend>Arquivo</legend>

                

                <c:if test="${upOk != null}">
                    <a href="${upOk.contexto}">Baixar</a>
                    ${upOk.contexto}
                </c:if>

                <c:if test="${Erro != null}">
                    Ops! parace que houve um erro ao tentar carregar o arquivo!<br />
                    Erro: ${Erro}
                </c:if>


            </fieldset>
        </fieldset>
    </body>
    
</html>
