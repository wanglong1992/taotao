<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2018/5/7
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
    String callback = request.getParameter("callback");
    if (null != callback && !"".equals(callback)) {
//        out.print(callback+"({\"abc\":123})");
        out.print(callback + "({\"name\":\"zhangsan\"})");
    } else {
        out.print("{\"abc\": 123}");
    }
%>
