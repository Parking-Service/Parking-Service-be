package jh.ParkingService.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jh.ParkingService.domain.user.User;
import jh.ParkingService.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "userDataServlet", urlPatterns = "/user/register")
public class UserDataServlet extends HttpServlet {

    @Autowired
    private UserServiceImpl userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);

        User user = objectMapper.readValue(messageBody, User.class);

        //json파일 내용 print
//        System.out.println("user.getEmail() = " + user.getEmail());
//        System.out.println("user.getLoginType() = " + user.getLogintype());
//        System.out.println("user.getNickname() = " + user.getNickname());
//        System.out.println("user.getUid() = " + user.getUid());

        response.getWriter().write("ok");

        //db에 저장
        userService.join(user);

    }
}
