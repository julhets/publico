<%-- 
    Document   : index
    Created on : 14/07/2014, 19:16:15
    Author     : Júlio
--%>
<%@page import="br.com.controladores.LivroBean"%>
<%@page import="br.com.controladores.UsuarioBean"%>
<%@page import="br.com.entidades.Avaliacao"%>
<%@page import="java.util.List"%>
<%@page import="br.com.entidades.Livro"%>
<%@page import="br.com.controladores.AvaliacaoBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    //povoar livros na base de dados assim que o sistema é implantado;
    LivroBean lb = new LivroBean();
    lb.povoarBaseDeLivros();

    //rotina para identificar o usuário através do cookie, caso um cookie já tenha sido setado;
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
    UsuarioBean ub = new UsuarioBean();
    //verifica se o usuário já completou o processo de avaliação de livros;
    //se sim, ele só terá acesso à pág. de resultado.
    if (logado && ub.usuarioJaInformouNomeEEmail(Long.parseLong(idUsuario))) {
        response.sendRedirect("resultado.jsp");
    }
    //fim da rotina de identificação;

    //rotina para persistir a avaliação do usuário;
    String idLivroSelecionado = request.getParameter("idLivroSelecionado");
    if (idLivroSelecionado != null) {
        //se é a primeira vez que o usuário acessa a aplicação, cria um registro de usuário e seta um cookie com o id do registro criado;
        if (!logado || (!ub.usuarioValido(Long.parseLong(idUsuario)))) {
            idUsuario = String.valueOf(ub.inserirNovoUsuario());
            Cookie mycookie = new Cookie("user-vote-no-livro", idUsuario);
            int ano = 60 * 60 * 24 * 365;
            mycookie.setMaxAge(ano);
            response.addCookie(mycookie);
        }
        //registra a avaliação do usuário;
        AvaliacaoBean ab = new AvaliacaoBean();
        ab.getAvaliacao().setLivro1(lb.getLivro(Long.parseLong(request.getParameter("idLivro1").trim())));
        ab.getAvaliacao().setLivro2(lb.getLivro(Long.parseLong(request.getParameter("idLivro2").trim())));
        ab.getAvaliacao().setAvaliacao(Integer.parseInt(idLivroSelecionado) == ab.getAvaliacao().getLivro1().getIdLivro() ? 1 : 2);
        ab.getAvaliacao().setUsuario(ub.getUsuario(Long.parseLong(idUsuario)));
        ab.gravarAvaliacao();
    }
    //fim da rotina de persistência;

    //seleciona o par de livros a ser avaliado;
    List<Livro> parLivrosAvaliacao = lb.getParLivrosAvaliacao(idUsuario.isEmpty() ? 0 : Long.parseLong(idUsuario));
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

    <!--    <section id="intro"></section>-->
    <div id="content">
        <article class="stream">
            <header>
                <h2><% out.println(!parLivrosAvaliacao.isEmpty() ? "Qual dos dois livros abaixo você prefere?" : "Informe seu Nome e E-Mail:"); %></h2>
            </header>
            <br />
            <section class="messages">

                <% if (!parLivrosAvaliacao.isEmpty()) { %>
                <br/>
                <div>
                    <form action="" method="post" accept-charset="UTF-8">
                        <% Livro l = parLivrosAvaliacao.get(0); %>
                        <div class="col-left">
                            <input type="image" name="<% out.println(l.getDescricao());%>" src="<% out.println(l.getUrlImg());%>" class="imagem" onclick="document.getElementById('idLivroSelecionado').value = <% out.println(l.getIdLivro());%>"/>
                            <h3><% out.println(l.getDescricao());%></h3>
                            <input type="hidden" name="idLivro1" value="<% out.println(l.getIdLivro());%>" />
                        </div>
                        <% l = parLivrosAvaliacao.get(1); %>
                        <div class="col-right">
                            <input type="image" name="<% out.println(l.getDescricao());%>" src="<% out.println(l.getUrlImg());%>" class="imagem" onclick="document.getElementById('idLivroSelecionado').value = <% out.println(l.getIdLivro());%>"/>
                            <h3><% out.println(l.getDescricao());%></h3>
                            <input type="hidden" name="idLivro2" value="<% out.println(l.getIdLivro());%>" />
                        </div>
                        <input type="hidden" id="idLivroSelecionado" name="idLivroSelecionado" />
                    </form>
                </div>
                <div style="clear: both;"></div>
                <br/>
                <br/>
                <h4>* Após você avaliar todas as combinações de 5 livros e informar seu nome e e-mail, você verá os resultados coletados até aqui</h4>
                <% } %>

                <% if (parLivrosAvaliacao.isEmpty()) {%>

                <%
                    if (!ub.usuarioJaInformouNomeEEmail(Long.parseLong(idUsuario))) {
                %>

                <br/>
                <div class="col-unica-centralizada-pequena">
                    <form action="resultado.jsp" method="post" accept-charset="UTF-8">
                        <label for="nome">Nome *</label><br/><input type="text" id="nome" name="nome" required/><br/>
                        <label for="email">Email *</label><br/><input type="email" id="email" name="email" required/><br/><br/>
                        <button value="Enviar" type="submit">Enviar</button>
                    </form>
                </div>
                <% } %>

                <% }%>

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
</html>
