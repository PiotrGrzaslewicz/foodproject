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

<form action="/about" method="get">

    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi ut turpis vel est tristique suscipit.
        Donec volutpat dapibus neque, egestas interdum nunc rutrum placerat. Ut ornare, leo a consequat eleifend,
        risus nunc laoreet tellus, sit amet semper odio odio vitae sapien. Etiam ac quam ac lorem interdum porttitor
        ut venenatis neque. Fusce molestie, purus et tincidunt maximus, massa arcu cursus ante, id commodo diam massa ac velit.
        Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Donec ornare,
        risus eu fringilla condimentum, nibh nunc bibendum sapien, a elementum quam justo a quam.</p>

    <p>Fusce feugiat vestibulum sagittis. Integer vehicula vitae sapien sed rhoncus. Vestibulum eget suscipit felis.
        Phasellus malesuada enim quis nunc laoreet tempus. Sed tincidunt nec mi ultrices aliquam. Donec eu leo molestie,
        efficitur sapien eget, bibendum orci. Suspendisse posuere tincidunt magna, ut euismod nibh laoreet sit amet.
        Vestibulum iaculis, eros sit amet convallis consequat, nibh massa sodales mi, ac mollis massa augue at mi.
        Sed erat risus, suscipit luctus odio eleifend, mollis dapibus nunc. Aenean consequat rutrum tortor id pellentesque.
        Ut commodo dolor facilisis ligula fringilla placerat. Integer urna risus, viverra quis lacus eget,
        commodo gravida quam. Donec ac condimentum leo, ac aliquam nisl. Pellentesque tincidunt nibh massa,
        ut viverra dui dictum at.</p>

    <p>Maecenas cursus sem eu ante euismod vulputate. Pellentesque at magna dignissim, ullamcorper leo pharetra,
        fringilla libero. Maecenas ac luctus urna. Morbi gravida dolor in sem porta, eu ullamcorper nulla lobortis.
        Nullam posuere nunc tellus, sed suscipit risus mattis ac. Phasellus malesuada laoreet varius. Maecenas
        commodo non magna vitae tristique. Maecenas semper porta nisl a ultrices. Curabitur venenatis nisi nunc,
        a iaculis nisi hendrerit ut. Cras enim eros, tincidunt eget pulvinar ut, luctus luctus nisl. Suspendisse
        potenti. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
        Phasellus ac accumsan augue.</p>

    <p>Sed iaculis nisi magna, at vehicula massa vestibulum a. Maecenas sed lacus fringilla, ornare erat vitae,
        pharetra odio. Integer in tempus lacus, ac aliquam purus. Aenean consequat pharetra ante, at fermentum sem
        feugiat in. Duis vestibulum ante eu aliquet aliquet. Morbi lobortis augue vitae magna finibus aliquet et id mi.
        Nam vel sodales purus. Aliquam ut semper neque, non sollicitudin libero.</p>

    <p>Nullam ac tortor ut ligula condimentum tempus bibendum et turpis. Sed a nisi quis ipsum maximus
        dignissim elementum eu ex. Sed volutpat nisl risus, vestibulum tempus mi dignissim id. Sed nec orci
        eu massa accumsan commodo eget et dui. Integer pretium risus non augue convallis, nec posuere augue varius.
        Sed sagittis tellus ac ligula gravida, vitae egestas orci placerat.
        Quisque at velit eu tellus imperdiet dignissim.</p>

</form>

<%@ include file="jspf/footer.jsp"%>

</body>
</html>
