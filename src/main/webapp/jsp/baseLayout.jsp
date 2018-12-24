<%@ taglib uri = "http://tiles.apache.org/tags-tiles" prefix = "tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset = UTF-8">
        <title>
            <tiles:insertAttribute name = "title" ignore="true" />
        </title>
        <link rel="stylesheet" href="css/main.css">
    </head>

    <body>
        <table width="1024px" cellpadding="0" cellspacing="0">
            <tr>
                <td class="lmn_footer">
                    <tiles:insertAttribute name = "header" />
                </td>
            </tr>  
            <tr>
                <td class="lmn_menu" >
                    <tiles:insertAttribute name = "menu" />
                </td>
            </tr>  
            <tr>
                <td class="lmn_flower">
                    <tiles:insertAttribute name = "body" />
                </td>
            </tr>  
            <tr>
                <td class="lmn_footer">
                    <tiles:insertAttribute name = "footer" />
                </td>
            </tr>  
        </table>
    </body>
</html>
