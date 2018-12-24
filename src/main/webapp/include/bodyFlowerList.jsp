<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table>
    <tr>
        <td colspan="2" >&nbsp;</td>
    </tr>
    <tr>
        <td colspan="2" >
          <div class="lmn_bodyTitle">Flower List</div>
        </td>
    </tr>
    <s:iterator value="flowerList.items" >
        <tr>
            <td><a class="lmn_body" href='flowerDescription.action?flowerType=<s:property value="id" />'><img src="<s:property value="imgUrl" />" width="150" ></a></td>
            <td><a class="lmn_body" href='flowerDescription.action?flowerType=<s:property value="id" />'><s:property value="title" /></a></td> 
        </tr>
    </s:iterator>
</table>