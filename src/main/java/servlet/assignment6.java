import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
 
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
        name = "assignment6",
        urlPatterns = {"/assignment6"}
    )
public class assignment6 extends HttpServlet
{
 
// Location of servlet.
static String Domain  = "cs.gmu.edu:8443";
static String Path    = "/offutt/servlet/";
static String Servlet = "assignment6";
 
// Button labels
static String Operation1 = "Add to List";
static String Operation2 = "Print list";
static String ResultSorted = "Sorted List";
static String ResultRevSorted = "Reverse Sorted List";
static String Random = "Random without replacement";
static String Randomreplace = "Random with replacement";
//required element 3
static String RemoveDuplicate = "Remove Duplicate Item";
//required element 1
//removes any string that contains < or > sign
static String VandS = "Validate and Sanitize Items that contain < or > signs";
static String Reset = "Reset Result";
 
// Other strings.
static String Style ="https://mason.gmu.edu/~mmills20/style.css";
//file
static String RESOURCE_FILE = "entries.txt";
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
      rslt = String.join(", ", array);
 
   }
   else if (operation.equals(ResultSorted))
   {
      //rslt = new Float(lhsVal.floatValue() - rhsVal.floatValue());
      Collections.sort(array);
      rslt = String.join(", ", array);
 
   }
   else if (operation.equals(ResultRevSorted))
   {
      //rslt = new Float(lhsVal.floatValue() - rhsVal.floatValue());
      Collections.sort(array);
      Collections.reverse(array);
      rslt = String.join(", ", array);
 
   }
   else if (operation.equals(Randomreplace))
   {
      //shuffle array and get top element
      Collections.shuffle(array);
      rslt = array.get(0);
 
   }
   else if (operation.equals(Random))
   {
      //shuffle array and get top element then remove it
      Collections.shuffle(array);
      rslt = array.get(0);
      array.remove(0);
   }
   else if (operation.equals(RemoveDuplicate))
   {
      //removing duplicated string
      List<String> newArr = array.stream().distinct().collect(Collectors.toList());
      array = newArr;
	Collections.sort(array);      
      rslt = String.join(", ", array);
   }
   else if (operation.equals(VandS)) 
   {
      List<String> newArr = array;
      newArr.removeIf(x -> x.contains("<"));
      newArr.removeIf(x -> x.contains(">"));
      array = newArr;
      rslt = String.join(", ", array);
   }
   else if (operation.equals(Reset))
   {
      array.clear();
      rslt = String.join("", array);
   }
 
   
 
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintWriter entriesPrintWriter =
          new PrintWriter(new FileWriter(RESOURCE_FILE, true), true);
       entriesPrintWriter.println(rslt+VALUE_SEPARATOR);
       entriesPrintWriter.close();
   PrintHead(out);
   PrintBody(out, newStr, rhsStr, rslt);
   printResponseBody(out, RESOURCE_FILE);
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
   out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"" + Style + "\">");
   out.println("</head>");
   out.println("");
} // End PrintHead
 
/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String newStr, String rhs, String rslt)
{
   out.println("<body>");
   out.println("<div class=\"header\"><h1>SWE 432 ASSIGNMENT 4 </h1></div>");
   out.print  ("<form method=\"post\"");
   out.println(" action=\"/assignment6\">");
   out.println("");
   out.println(" <table>");
   out.println("  <tr>");
   out.println("   <td>Add word here:");
   out.println("   <td><input type=\"text\" name=\"newstr\" value=\"" + newStr + "\" size=5>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=100>");
   out.println("  </tr>");
   out.println(" </table>");
   out.println(" <br>");
   out.println(" <br>");
   out.println(" <input type=\"submit\" value=\"" + Operation1 + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Operation2 + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + ResultSorted + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + ResultRevSorted + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Random + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Randomreplace + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + RemoveDuplicate + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + VandS + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Reset + "\" name=\"Operation\">");
   out.println("</form>");
   out.println("");
   //collab summary
   out.println("<p>");
   out.println("Group Members: Marisa Mills, Ximena Perez, Saman Fatima");
   out.println("</p>");
   out.println("<p>");   
 
   out.println("</body>");
} // End PrintBody
 


private void printResponseBody (PrintWriter out, String resourcePath){
    out.println("<body>");
    out.println("<p>");
    out.println(
    "A simple example that shows entries from a plain file");
    out.println("</p>");
    out.println("");
    out.println(" <table>");

    try {
        out.println("  <tr>");
        out.println("   <th>Name</th>");
        out.println("   <th>Age</th>");
        out.println("   <th>Color</th>");
        out.println("  </tr>");
        File file = new File(resourcePath);
        if(!file.exists()){
          out.println("  <tr>");
          out.println("   <td>No entries persisted yet.</td>");
          out.println("  </tr>");
          return;
        }

        BufferedReader bufferedReader =
          new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          String []  entry= line.split(,);
          //out.println("  <tr>");
          for(String value: entry){
              out.println("   <p>"+value+"</p>");
          }
          //out.println("  </tr>");
        }
        bufferedReader.close();
      } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     out.println(" </table>");
     out.println("");
     out.println("</body>");
  }


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
 
}  // End assignment6