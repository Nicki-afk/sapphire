package gyber.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getRequestDispatcher("/register/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        // need fields : 

        // email 
        // nick_name
        // paasswd
        // repeat passwd



        // need functions : 

        // email --> validation( front-end ) --> check email on database on exists or no ( back-end ) 
        // nickName --> validation( front-end ) --> check nickName on database on exists or no ( back-end )
        // passwd --> check security , lenght , chars ( front-end )  
        // repet passwd --> check idenitify passwords ( front-end )
        // send all data to server -- > processing and save data in database ( back-end )
        // SSR --> forward to page jsp 



    }


    
    
}
