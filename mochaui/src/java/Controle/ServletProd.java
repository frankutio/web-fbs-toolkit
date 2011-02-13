package Controle;

import Entidades.Diretorios;
import Entidades.Produto;
import Entidades.Usuario;
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

public class ServletProd extends HttpServlet {

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

        String proximaPagina ="";

        Diretorios dir =new Diretorios();


        if(operacao.equals("login")){

            Usuario usr = new Usuario();

            usr.setNome("Frank");
            usr.setSenha("123456");
            usr.setLogin("frankutio");

            String login = request.getParameter("usr");
            String senha = request.getParameter("senha");

            // Verifica se o usuario existe
            if(usr.getLogin().equals(login)){

                if(usr.getSenha().equals(senha)){
              
                    request.getSession().setAttribute("Login", 1);

                    request.getSession().setAttribute("Usuario", usr);
                }

                else{
                    request.getSession().removeAttribute("Login");
                    request.getSession().removeAttribute("Usuario");

                    request.setAttribute("MsgErro", "Senha Não confere!");

                    proximaPagina ="/login.jsp";
                }

                proximaPagina = "/gerProdutos/cmsProduto.jsp";

            }

            else{
                request.getSession().removeAttribute("Login");
                request.setAttribute("MsgErro", "Login Não confere!");

                proximaPagina ="/login.jsp";
            }

        }
        

        else if(operacao.equals("cadProd")){

            // Construindo a Classe do Produto

            Produto prod = new Produto();           
            

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

                    if(fi.isFormField()){

                        if(fi.getFieldName().equals("nome")){
                                prod.setNome(fi.getString());                                
                            }

                        if(fi.getFieldName().equals("valor")){
                                prod.setValor(fi.getString());
                        }

                        if(fi.getFieldName().equals("descricao")){
                                prod.setDescricao(fi.getString());
                        }

                        

                    }

                    else{

                    // Tratamento do contexto

                    // Define a pasta e diretório do album do produto

                       java.io.File f = new java.io.File(dir.getPastaProdutoFrank() + prod.getNome());

                       f.mkdir();

                       String nome = fi.getName().toString();
                       String nomeArquivo = nome.substring(nome.lastIndexOf("\\")+1);                    

                       String diretorio =dir.getPastaProdutoFrank() + prod.getNome() + "/";
                       
                        // Cria um objeto file com nome do arquivo
                        // A pasta deve oferecer acesso de escrita para Conteiner
                       File uploadedFile = new File(diretorio + nomeArquivo);

                        // Grava arquivo na pasta especificada
                       fi.write(uploadedFile);                        

                        // Grava o endereço do arquivo

                        prod.setFoto(diretorio + nomeArquivo);

                        // Tudo certo, cria a sessão com os dados do produto

                        request.getSession().setAttribute("Produto", prod);


                       proximaPagina = "/index.jsp";
                    }


                }
            } catch (Exception ex) {

                request.setAttribute("Error", ex.getMessage());

                proximaPagina = "/gerProdutos/cmsProduto.jsp";

                System.out.println(new Date() + " - Exception: " + ex.getMessage());
            }
        }

        }


        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);


    }    

}
