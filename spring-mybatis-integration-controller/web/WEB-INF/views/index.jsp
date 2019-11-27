<%--
  User: Sam
  Date: 2019/11/7
  Time: 9:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
    <table>
        <tr>
            <td>编号</td>
            <td>名称</td>
            <td>邮箱</td>
            <td>日期</td>
        </tr>
        <tbody id="tbody">

        </tbody>
    </table>

    <a class="pageNumber" id="first"  href="#" >首页</a>
    <a class="pageNumber" id="prev" href="#">上一页</a>
    <a class="pageNumber" id="next"href="#">下一页</a>
    <a class="pageNumber" id="last" href="#">尾页</a>

    <ul id="navigatepageNums">

    </ul>
<br>
<br>
<a href="/uploadView" >上传文件</a>
<br>
<a href="/download?fileName=1.png" >下载图片</a>
<br>
<form method="post" action="/insert" id="userForm">
    <input type="hidden" name="pageNum" value="${param.pageNum}"/>
    姓名：<input type="text" name="username" value="${userInfo.username}"/> <span id="username" style="color: red;"></span><br/>
    <fmt:formatDate value="${userInfo.date}" pattern="yyyy-MM-dd" var="userInfoDate"/>
    日期：<input type="text" name="date" value="${userInfoDate}"/>yyyy-MM-dd <span id="date" style="color: red;"></span><br/>
    邮箱：<input type="text" name="email" value="${userInfo.email}"/><span id="email" style="color: red;"></span><br/>
    手机：<input type="text" name="phone" value="${userInfo.phone}"/><span id="phone" style="color: red;"></span><br/>
    密码：<input type="text" name="password" value="${userInfo.password}"/><span id="password" style="color: red;"></span><br/>
    <input type="button" id="SubmitBtn" value="提交" />
</form>

<script type="text/javascript" src="/static/js/common/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(function () {

        var page = {};
        var pageSize = 2;
        getEmps(1,pageSize);
        function  getEmps(pageNum,pageSize) {
            $.ajax({
                url:"/index",
                type:"post",
                dataType:"json",
                data:{"pageNum":pageNum,"pageSize":pageSize},
                success:function (pageInfo) {
                    var userInfos = "";
                    $.each(pageInfo.list,function (index,userInfo) {
                        userInfos+="<tr>";
                        userInfos+="<td>"+userInfo.id+"</td>";
                        userInfos+="<td>"+userInfo.username+"</td>";
                        userInfos+="<td>"+userInfo.email+"</td>";
                        userInfos+="<td>"+userInfo.date+"</td>";
                        userInfos+="</tr>";

                    })//emp
                    $("#navigatepageNums").empty();
                    $.each(pageInfo.navigatepageNums,function (index,num) {
                        var pageNumbers=$("<li class='pageNumber'>"+num+"</li>");
                        pageNumbers.appendTo("#navigatepageNums");
                        pageNumbers.data("value",num);
                    })//number
                    $("#first").data("value",1);
                    $("#prev").data("value",pageInfo.prePage);
                    $("#next").data("value",pageInfo.nextPage);
                    $("#last").data("value",pageInfo.pages);

                    $(".pageNumber").on("click",function () {
                        getEmps($(this).data("value"),pageSize);
                    })
                    page=pageInfo;
                    $("#tbody").html(userInfos);
                }
            })//ajax end
        }
        $("#SubmitBtn").click(function () {
            $.ajax({
                url:"/insert",
                type:"post",
                dataType:"json",
                data:$("#userForm").serialize(),
                success:function (data) {
                    if (data.code=="200"){
                        alert(data.msg)
                        location.href = "/index?pageNum="+data.data;
                    }else{
                        $("span").text("");
                        $.each(data.data,function (index,value) {
                            $("#"+value.field).text(value.defaultMessage)
                        })//emp
                        alert(data.msg);
                    }
                }
            });
        });
    })//end
</script>
</body>
</html>
