<jsp:useBean id="msalAuth" scope="session"
	class="com.microsoft.azuresamples.roles.MsalAuthSession" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="card">
	<h5 class="card-header bg-primary">
		<%
			out.println(msalAuth.getAuthenticated() ? "You're signed in!" : "You're not signed in.");
		%>
	</h5>
	<div class="card-body">
		<p class="card-text">
			<%
				if (msalAuth.getAuthenticated()) {
			%>
		
		<h2>Integrating Azure AD V2 into an Java servlet Web app and
			using Azure AD app roles for authorization.</h2>
		<p>This sample shows how to build a Java servlet Web app that uses
			Azure AD app roles for authorization.</p>
		<br />
		<p>
			Click here to get your <a class="btn btn-success"
				href="<c:url value="./token_details"></c:url>">ID Token Details</a>
		</p>
		<br> <br>
		<h3 style="color: blue">Try the following pair of Azure App Role
			driven pages</h3>

		<table>
			<tr height=20px></tr>
			<tr>
				<td>Click here to visit the <a class="btn btn-primary"
					href="<c:url value="./privileged_admin"></c:url>">Admin Page</a></td>
			</tr>
			<tr>
				<td>Click here to visit the <a class="btn btn-primary"
					href="<c:url value="./regular_user"></c:url>">User Page</a></td>
			</tr>

		</table>
		<br />
		<%
			} else {
		%>
		Use the button on the top right to sign in. Attempts to get your <a
			href="<c:url value="./token_details"></c:url>">ID Token Details</a>
		will result in a 401 error.
		<%
			}
		%>
		</p>
	</div>
</div>
