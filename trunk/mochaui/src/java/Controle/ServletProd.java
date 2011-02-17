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

        Diretorios dir = new Diretorios();


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

                    proximaPagina = "/gerProdutos/cmsProduto.jsp";
                }

                else{
                    request.getSession().removeAttribute("Login");
                    request.getSession().removeAttribute("Usuario");

                    request.setAttribute("MsgErro", "Senha Não confere!");

                    proximaPagina ="/login.jsp";
                }                

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
            Produto produto = new Produto();

            // Prepara a Servlet para trabalhar com upload

            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Manipulador de upload de arquivos
            ServletFileUpload upload = new ServletFileUpload(factory);

            if (ServletFileUpload.isMultipartContent(request)) {

            try {
                    // Recebe lista de campos do formulario
                    List<FileItem> itens = upload.parseRequest(request);

                    for (FileItem fi : itens) {

                        if (fi.isFormField()) {
                           // Recebe os campos q nao sao file

                          if(fi.getFieldName().equals("nome")){
                                prod.setNome(fi.getString());
                          }

                          if(fi.getFieldName().equals("valor")){
                                prod.setValor(fi.getString());
                          }

                          if(fi.getFieldName().equals("descricao")){
                                prod.setDescricao(fi.getString());
                          }

                        } else {

                        // Cria a pasta no perfil do produto
                        java.io.File f = new java.io.File(dir.getPastaProdutoMir() + prod.getNome());
                        f.mkdir();

                        // Seta o diretorio em uma variavel pra uso posterior
                        String diretorio = dir.getPastaProdutoMir() + prod.getNome() + "/";


                        //`Retira o nome do arquivo (Usado para corrigir bug do IE)
                        String nome = fi.getName().toString();
                        String arquivo = nome.substring(nome.lastIndexOf("\\")+1);

                            
                        // Cria um objeto file com nome do arquivo
                        // A pasta deve oferecer acesso de escrita para Conteiner
                        File uploadedFile = new File(diretorio + arquivo);
                        // Grava arquivo na pasta especificada
                        fi.write(uploadedFile);

                        // Grava o nome do arquivo no perfil do usuario
                        produto.setArquivo(arquivo);
                        prod.setFoto("/mochaui/produtos/" + prod.getNome() + "/" + arquivo);

                        }
                    }

                    
                } catch (Exception ex) {

                    request.setAttribute("Error", ex.getMessage());

                    proximaPagina = "/gerProdutos/cmsProduto.jsp";

                    System.out.println(new Date() + " - Exception: " + ex.getMessage());


                }
            }

            //RECUPERA PARAMENTRO DESCRICAO
                try{

                    produto.setNome(prod.getNome());
                    produto.setDescricao(prod.getDescricao());
                    produto.setValor(prod.getValor());

                    produto.setFoto(prod.getFoto());



                    // Tudo certo, cria a sessão com os dados do produto

                    request.getSession().setAttribute("Produto", produto);


                   proximaPagina = "/index.jsp";


                }catch(Exception e){
                    System.out.println(new Date() + " Erro: " + e.getMessage());
                }


        }

        else if(operacao.equals("listProd")){

            proximaPagina="/produtos/produto.jsp";

        }


        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);


    }    

}
