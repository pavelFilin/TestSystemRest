<!DOCTYPE html>
<html lang="en">
<head>
    <#--<meta name="_csrf" content="${_csrf.token}"/>-->
    <#--<meta name="_csrf_header" content="${_csrf.headerName}"/>-->
    <meta charset="UTF-8">
    <title>Test System</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<div class="container-fluid" id="content">
    <div class="card bg-light mx-auto" style="max-width: 400px; min-height: 400px">
        <article class="card-body mx-auto">
            <h5>${message?if_exists}</h5>
            <h4 class="card-title mt-3 text-center">Sing in</h4>
            <form method="post" action="/rest/login">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <input maxlength="50" name="username" class="form-control" placeholder="nickname" type="text">
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input maxlength="50" class="form-control" placeholder="password" type="password" name="password">
                </div>
                <div class="form-group">
                    <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                    <button type="submit" class="btn btn-primary btn-block">sing in</button>
                </div>
                <p class="text-center"><a href="/app/rest/registration">Sing up</a></p>
                <#--<p class="text-center">Забыли пароль? <a href="/requestresetpassword">Востановить</a></p>-->
            </form>
        </article>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>