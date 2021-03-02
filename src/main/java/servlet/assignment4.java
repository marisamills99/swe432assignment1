 
 
// Import Java Libraries
import java.io.*;
import java.util.*;
 
//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
// twoButtons class
// CONSTRUCTOR: no constructor specified (default)
//
// ***************  PUBLIC OPERATIONS  **********************************
// public void doPost ()  --> prints a blank HTML page
// public void doGet ()  --> prints a blank HTML page
// private void PrintHead (PrintWriter out) --> Prints the HTML head section
// private void PrintBody (PrintWriter out) --> Prints the HTML body with
//              the form. Fields are blank.
// private void PrintBody (PrintWriter out, String lhs, String rhs, String rslt)
//              Prints the HTML body with the form.
//              Fields are filled from the parameters.
// private void PrintTail (PrintWriter out) --> Prints the HTML bottom
//***********************************************************************
@WebServlet(
        name = "assignment4",
        urlPatterns = {"/assignment4"}
    )
public class assignment4 extends HttpServlet
{
 
// Location of servlet.
static String Domain  = "cs.gmu.edu:8443";
static String Path    = "/offutt/servlet/";
static String Servlet = "assignment4";
 
// Button labels
static String Operation1 = "Add to List";
static String Operation2 = "Print list";
 
// Other strings.
static String Style ="src/main/java/servlet/assignment4.java";
 
// my list
List<String> array = new ArrayList<String>();
/** *****************************************************
 *  Overrides HttpServlet's doPost().
 *  Converts the values in the form, performs the operation
 *  indicated by the submit button, and sends the results
 *  back to the client.
********************************************************* */
public void doPost (HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
{
   //Float rslt   = new Float(0.0);
   Float lhsVal = new Float(0.0);
   Float rhsVal = new Float(0.0);
   String operation = request.getParameter("Operation");
   String lhsStr = request.getParameter("LHS");
   String rhsStr = request.getParameter("RHS");
   String rslt = new String("");
   String newStr = request.getParameter("newstr");
 
   if (operation.equals(Operation1))
   {
      //rslt = new Float(lhsVal.floatValue() + rhsVal.floatValue());
      array.add(newStr);
   }
   else if (operation.equals(Operation2))
   {
      //rslt = new Float(lhsVal.floatValue() - rhsVal.floatValue());
      for (var y=0; y<array.length; y++){
        rslt = rslt.concat(newStr);
        rslt = rslt.concat(", ");
    }

   }
 
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out, lhsStr, rhsStr, rslt);
   PrintTail(out);
}  // End doPost
 
/** *****************************************************
 *  Overrides HttpServlet's doGet().
 *  Prints an HTML page with a blank form.
********************************************************* */
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out);
   PrintTail(out);
} // End doGet
 
/** *****************************************************
 *  Prints the <head> of the HTML page, no <body>.
********************************************************* */
private void PrintHead (PrintWriter out)
{
   out.println("<html>");
   out.println("");
 
   out.println("<head>");
   out.println("<title>Assignment four</title>");
   out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"./style.css\">");
   out.println("</head>");
   out.println("");
} // End PrintHead
 
/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String lhs, String rhs, String rslt)
{
   out.println("<body>");
   out.println("<div class=\"header\"><h1>ASSIGNMENT 4 swe432</h1></div>");
   out.print  ("<form method=\"post\"");
   out.println(" action=\"https://cs.gmu.edu:8443/offutt/servlet/formHandler\">");
   out.println("");
   out.println(" <table>");
   out.println("  <tr>");
   out.println("   <td>Add word here:");
   out.println("   <td><input type=\"text\" name=\"newstr\" value=\"" + lhs + "\" size=5>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result Sorted List:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=10>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result Reverse Sorted List:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=10>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result Random with removal:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=10>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result Random without removal:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=10>");
   out.println("  </tr>");
   out.println(" </table>");
   out.println(" <br>");
   out.println(" <br>");
   out.println(" <input type=\"submit\" value=\"" + Operation1 + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Operation2 + "\" name=\"Operation\">");
   out.println(" <input type=\"reset\" value=\"Reset\" name=\"reset\">");
   out.println("</form>");
   out.println("");
   out.println("</body>");
} // End PrintBody
 
/** *****************************************************
 *  Overloads PrintBody (out,lhs,rhs,rslt) to print a page
 *  with blanks in the form fields.
********************************************************* */
private void PrintBody (PrintWriter out)
{
   PrintBody(out, "", "", "");
}
 
/** *****************************************************
 *  Prints the bottom of the HTML page.
********************************************************* */
private void PrintTail (PrintWriter out)
{
   out.println("");
   out.println("</html>");
} // End PrintTail
 
}  // End assignment4

