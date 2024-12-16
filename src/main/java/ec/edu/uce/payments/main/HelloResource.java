package ec.edu.uce.notifications;

import ec.edu.uce.classes.*;
import ec.edu.uce.jpa.Student;
import ec.edu.uce.jpa.StudentService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;


@Path("/hello-world")
public class HelloResource {

    @Inject
    @QualifierNotification("email")
    Notificable emailNotification;

    @Inject
    @QualifierNotification("sms")
    Notificable smsNotification;

    @Inject
    @QualifierNotification("push")
    Notificable pushNotificiacion;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Produces("text/plain")
    @Path("/email")
    public String emailNotification() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentUnity");
        EntityManager em = emf.createEntityManager();

        StudentService studentService = new StudentService(em);
        studentService.createStudent(new Student(1, "Juan"));

        return emailNotification.sendNofication("example.com", "customer@example2.com", "Email Message");

    }

    @GET
    @Produces("text/plain")
    @Path("/sms")
    public String SMSNotification() {
        return smsNotification.sendNofication("example.com", "customer@example2.com", "SMS Message");

    }

    @GET
    @Produces("text/plain")
    @Path("/push")
    public String pushNotification() {
        return pushNotificiacion.sendNofication("example.com", "customer@example2.com", "Push Message");

    }

}

