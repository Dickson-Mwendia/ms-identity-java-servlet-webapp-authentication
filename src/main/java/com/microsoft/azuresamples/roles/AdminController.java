// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.azuresamples.roles;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class defines a page for showing the Privileged Admin user page to demonstrate role based access
 * This is here only for sample demonstration purposes.
 */
@WebServlet(name = "AdminController", urlPatterns = {"/privileged_admin"})
public class AdminController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	   @Override
	    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
	        
	            req.setAttribute("bodyContent", "auth/privilegedAdmin.jsp");
	            final RequestDispatcher view = req.getRequestDispatcher("index.jsp");
	            view.forward(req, resp);	        
	   }
	   
}