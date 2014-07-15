<%-- 
    Document   : index
    Created on : 14/07/2014, 19:16:15
    Author     : Júlio
--%>

<%@page import="br.com.entidades.Usuario"%>
<%@page import="br.com.entidades.Resultado"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controladores.ResultadoBean"%>
<%@page import="br.com.controladores.UsuarioBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    //verificação da identidade do usuário
    Boolean logado = false;
    String idUsuario = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-vote-no-livro")) {
                logado = true;
                idUsuario = cookie.getValue();
                break;
            }
        }
    }

    //caso não haja um cookie setado com a chave "user-vote-no-livro" para o usuário, ele é redirecionado para o início;
    if (!logado) {
        response.sendRedirect("index.jsp");
    }

    //verifica se o usuário já informou nome e e-mail, ou seja, se já completou o processo de avaliação;
    UsuarioBean ub = new UsuarioBean();
    if (!idUsuario.isEmpty() && !ub.usuarioJaInformouNomeEEmail(Long.parseLong(idUsuario))) {
        //se os dados de nome e e-mail não constam na base ainda, significa que a página está recebendo os dados via POST para serem persistidos;
        if (request.getParameter("email") != null) {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            //persiste os dados vindos pelo POST e finaliza o processo de avaliação;
            ub.alterarUsuario(Long.parseLong(idUsuario), nome, email);
        } else {
            //caso o usuário o usuário digite a URL da página de resultado diretamente na barra de navegação más não tenha;
            //finalizado o processo de avaliação, ele é direcionado para a página inicial;
            response.sendRedirect("index.jsp");
        }
    }

    //carrega a classe usuário com os dados do usuário logado, através do valor do cookie (id) setado;
    Usuario u = ub.getUsuario(Long.parseLong(idUsuario.isEmpty() ? "0" : idUsuario));
    String nomeUsuario = "";
    if (u != null) {
        nomeUsuario = u.getNome();
    }

    //gera uma lista de livros ordenada de forma decrescente em relação ao número de votos;
    ResultadoBean rb = new ResultadoBean();
    List<Resultado> listaResultado = rb.getResultado();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
        <link rel="stylesheet" type="text/css" href="css/home.css"/>
        <title>Vote no Livro</title>
    </head>
    <header>
        <h1>Vote no Livro</h1>
    </header>
    <!--    <nav>
            <ul>
                <li class="selected"><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Products</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Support</a></li>
                <li><a href="#">View Cart</a></li>
            </ul>
        </nav>-->
    <section id="intro"></section>
    <div id="content">
        <article class="stream">
            <header>
                <h2>Resultado</h2>
            </header>
            <br />
            <section class="messages">

                <br/>
                <h4><% out.println(nomeUsuario); %>, seus votos foram computados com sucesso.</h4>
                <br/>
                <div class="col-unica-centralizada-media">
                    <form action="" method="post">
                        <button value="Nova Votação" type="submit" onclick="del_cookie('user-vote-no-livro');">Nova Votação</button>
                        <input type="hidden" id="novaVotacao" name="novaVotacao" value="true" />
                    </form>
                    <table class="tabela">
                        <tr class="linha">
                            <td class="coluna">Posição</td>
                            <td class="coluna">Capa</td>
                            <td class="coluna">Livro</td>
                            <td class="coluna">Votos</td>
                        </tr>
                        <%
                            int contador = 1;
                            for (Resultado r : listaResultado) {%>
                        <tr class="linha">
                            <td class="coluna"><% out.println(contador); %></td>
                            <td class="coluna"><img src="<% out.println(r.getLivro().getUrlImg()); %>" alt="<% out.println(r.getLivro().getDescricao()); %>" title="<% out.println(r.getLivro().getDescricao()); %>" class="imagem"/></td>
                            <td class="coluna"><% out.println(r.getLivro().getDescricao()); %></td>
                            <td class="coluna"><% out.println(r.getVotos()); %></td>
                        </tr>
                        <%
                                contador++;
                            }%>
                    </table>
                </div>

                <!-- this is a sample search script from twitter.com/goodies . feel free to put your own stream scripts here -->

                <script src="http://widgets.twimg.com/j/2/widget.js"></script>
                <script>
                            new TWTR.Widget({
                                version: 2,
                                type: 'search',
                                search: 'html5 or css3',
                                interval: 6000,
                                title: 'HTML5 / CSS3',
                                subject: 'Awesomeness',
                                width: 670,
                                height: 2200,
                                theme: {
                                    shell: {
                                        background: '#82d9fd',
                                        color: '#ffffff'
                                    },
                                    tweets: {
                                        background: '#ffffff',
                                        color: '#444444',
                                        links: '#1bbf5f'
                                    }
                                },
                                features: {
                                    scrollbar: false,
                                    loop: true,
                                    live: true,
                                    hashtags: true,
                                    timestamp: true,
                                    avatars: true,
                                    behavior: 'default'
                                }
                            }).render().start();
                </script>

            </section>
        </article>

    </div>



    <footer class="footer">
        <label>Júlio Reis - 2014</label>
    </footer>
    <!-- Free template created by http://freehtml5templates.com -->
</body>
<script type="text/javascript">
    function del_cookie(name) {
        document.cookie = name +
                '=; expires=Thu, 01-Jan-70 00:00:01 GMT;';
    }
</script>
</html>
