//package mk.finki.ukim.mk.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.model.Event;
//import mk.finki.ukim.mk.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Objects;
//
//@WebServlet(name = "EventListenServlet", urlPatterns = {"/events"})
//public class EventListServlet extends HttpServlet {
//
//    private final EventService eventService;
//    private final SpringTemplateEngine templateEngine;
//
//    public EventListServlet(EventService eventService, SpringTemplateEngine templateEngine) {
//        this.eventService = eventService;
//        this.templateEngine = templateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String searchName = req.getParameter("searchName");
//        String minimalRating = req.getParameter("minimalRating");
//
//
//        List<Event> eventList = eventService.filterEvents(searchName, minimalRating);
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("events", eventList);
//        templateEngine.process("listEvents.html", context, resp.getWriter());
//
//    }
//}
