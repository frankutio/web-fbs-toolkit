package Controle;

import Entidades.Arquivo;
import Entidades.Diretorios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.File;
import java.util.Date;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class del extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       processRequest(request, response);
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       processRequest(request, response);
   }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        //RECEBE O TIPO DE OPERACAO A REALIZAR
        String operacao = request.getParameter("operacao");

        //LOG PARA TESTE
        System.out.println("Controle Acionado com Operacao: " + operacao);

        String proximaPagina = "/apaga.jsp";

        String arquivo = request.getParameter("arquivo");

        Diretorios dir = new Diretorios();

        String diretorio = dir.getPastaUploadFrank();

        // Recebe parametro do formulario em questão

        if(operacao.equals("pagar")){

            request.setAttribute("img","O arquivo que vc apagou foi: "+arquivo);

            java.io.File f = new java.io.File(diretorio+arquivo);
            f.delete();
           

        }

        else if(operacao.equals("upaload")){

            // seta a foto padrão

            String foto="padrao.png";

        // Verifica se a requisicao e do tipo multipart
        if (ServletFileUpload.isMultipartContent(request)) {

            // Fabrica para arquivos baseados em disco
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Manipulador de upload de arquivos
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Recebe lista de campos do formulario
                List<FileItem> itens = upload.parseRequest(request);

                for (FileItem fi : itens) {

                    // Tratamento do contexto
                       String nome = fi.getName().toString();
                       String nomeArquivo = nome.substring(nome.lastIndexOf("\\")+1);

                    
                        // Cria um objeto file com nome do arquivo
                        // A pasta deve oferecer acesso de escrita para Conteiner
                       File uploadedFile = new File(diretorio + nomeArquivo);
                        
                        // Grava arquivo na pasta especificada
                       fi.write(uploadedFile);                     

                       

                        String foto1 = "/arquivos/" + nomeArquivo;

                        // Grava o nome do arquivo no perfil do usuario

                        Arquivo arq = new Arquivo();

                        arq.setContexto(foto1);

                        request.setAttribute("upOk", arq);


                       proximaPagina = "/apaga.jsp";


                }
            } catch (Exception ex) {
                

                request.setAttribute("upOk", null);
                request.setAttribute("Erro", ex.getMessage());

                proximaPagina = "/apaga.jsp";

                System.out.println(new Date() + " - Exception: " + ex.getMessage());
            }
        }

        }



        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);

    } 

    

}