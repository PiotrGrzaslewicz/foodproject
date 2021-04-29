<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Kontakt</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>

<%@ include file="jspf/header.jsp"%>

<form action="/contact" method="get">

<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi ut turpis vel est tristique suscipit.
Donec volutpat dapibus neque, egestas interdum nunc rutrum placerat. Ut ornare, leo a consequat eleifend,
risus nunc laoreet tellus, sit amet semper odio odio vitae sapien. Etiam ac quam ac lorem interdum porttitor
ut venenatis neque. Fusce molestie, purus et tincidunt maximus, massa arcu cursus ante, id commodo diam massa ac velit.
Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Donec ornare,
    risus eu fringilla condimentum, nibh nunc bibendum sapien, a elementum quam justo a quam.</p>

</form>

<%@ include file="jspf/footer.jsp"%>

</body>
</html>
