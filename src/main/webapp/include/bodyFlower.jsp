<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table>
    <tr>
        <td colspan="3" >&nbsp;</td>
    </tr>
    <tr>
        <td colspan="3" >
          <div class="lmn_bodyTitle">Flower Name:<s:property value="FlowerDescription.flower.title" /></div>
        </td>
    </tr>
    <tr>
        <td class="lmn_flower">
            <img class="lmn_flower" width="400" src="<s:property value="FlowerDescription.flower.imgUrl" />">
        </td>
        <td>
             &nbsp;
        </td>
        <td class="lmn_flower">
            <p class="lmn_flower"><s:property escapeHtml="false" value="FlowerDescription.description" /></p>
        </td>
    </tr>
    <tr>
        <td colspan="3" >
          &nbsp;
        </td>
    </tr>
</table>
    
    
     