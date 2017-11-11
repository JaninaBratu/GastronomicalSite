
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/taglib.jsp"%>


<form commandName="message" action="/edit-comment/${message.id}.html">
<form:label path="message">New comment</form:label>
<input name="message" type="text" value="${message.message}"/>

 <div class="modal-footer">
        <input type="submit" class="btn btn-primary" value="Save" />
 </div>
</form>