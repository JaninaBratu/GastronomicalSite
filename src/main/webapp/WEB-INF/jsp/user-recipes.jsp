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
			<label for="title" class="col-sm-2 control-label">Title:</label>
			<div class="col-sm-10">
				<form:input path="title" cssClass="form-control" />
				<form:errors path="title"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">Content:</label>
			<div class="col-sm-10">
				<form:input path="content" cssClass="form-control" />
				<form:errors path="content"/>
			</div>
		</div>

          <div class="form-group">
              <label for="content" class="col-sm-2 control-label">Category:</label>
              <div class="col-sm-10">
                  <form:select class="custom-select" path="category">
                      <c:forEach items="${categories}" var="cat">
                          <form:option value="${cat.id}">${cat.categoryType}</form:option>
                      </c:forEach>
                  </form:select>
              </div>
          </div>

          <div class="form-group">
              <label for="content" class="col-sm-2 control-label">Rating:</label>
              <div class="col-sm-10">
                  <form:select class="custom-select" path="rating">
                      <c:forEach items="${ratings}" var="rat">
                          <form:option value="${rat.id}">${rat.ratingValue}</form:option>
                      </c:forEach>
                  </form:select>
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

</div>

<!-- Tab panes -->
<div class="tab-content">
    <c:forEach items="${user.recipes}" var="recipe">
        <div role="tabpanel" class="tab-pane active" id="recipe_${recipe.id}">
            <a  href='<spring:url value="/recipe-details/${recipe.id}.html" />'>${recipe.title}</a>
        </div>
    </c:forEach>
</div>

<form>
    <input type="submit" name="sortAsc" value ="true">Ascending</input>
    <input type="submit" name="sortAsc" value="false">Descending</input>
</form>