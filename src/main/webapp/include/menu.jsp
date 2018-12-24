<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- Size menu: <s:property value="menu.size" /> -->
<table>
    <tr>
        <s:iterator value="menu.menuItems" >
            <td><a class="lmn_menu" href="<s:property value="url" />"><s:property value="text" /></a> | </td> 
        </s:iterator>
    </tr>
</table>