<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../layout/taglib.jsp"%>

<h1>${user.name}</h1>

<div>
	Welcome to my recipes
</div>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  New Recipe
</button>

<br><br>

<form:form commandName="recipe" cssClass="form-horizontal recipeForm">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">New Recipe</h4>
      </div>
      <div class="modal-body">
        
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Title:</label>
			<div class="col-sm-10">
				<form:input path="title" cssClass="form-control" />
				<form:errors path="title"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="url" class="col-sm-2 control-label">Content:</label>
			<div class="col-sm-10">
				<form:input path="content" cssClass="form-control" />
				<form:errors path="content"/>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="submit" class="btn btn-primary" value="Save" />
      </div>
    </div>
  </div>
</div>
</form:form>

<br><br>
<!-- Tab panes -->
  <div class="tab-content">
    <c:forEach items="${user.recipes}" var="recipe">
   		<div role="tabpanel" class="tab-pane active" id="recipe_${recipe.id}">
	   		<h1>${recipe.title}</h1>
			<p>
			<a href='<spring:url value="/recipe/remove/${recipe.id}.html"/>' class="btn btn-danger triggerRemove">remove recipe</a>
			
			${recipe.content}</p>
		
		</div>
     </c:forEach>
  </div>
  