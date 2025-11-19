package pe.edu.upc.noctuwell;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.edu.upc.noctuwell.entities.*;
import pe.edu.upc.noctuwell.repositories.*;
import pe.edu.upc.noctuwell.services.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class NoctuwellApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoctuwellApplication.class, args);
    }

    @Bean
    public CommandLineRunner startConfiguration(
            UserService userService,
            AuthorityService authorityService,
            PatientRepository patientRepository,
            SpecialistRepository specialistRepository,
            TypeSpecialistRepository typeSpecialistRepository,
            PlanRepository planRepository,
            HistoryRepository historyRepository,
            AppointmentRepository appointmentRepository,
            DiagnosisRepository diagnosisRepository,
            ScheduleRepository scheduleRepository,
            MessageRepository messageRepository,
            ReviewRepository reviewRepository
    ) {
        return args -> {

            // === ROLES ===
            Authority authority1 = authorityService.addAuthority(new Authority("ROLE_ADMIN"));
            Authority authority2 = authorityService.addAuthority(new Authority("ROLE_SPECIALIST"));
            Authority authority3 = authorityService.addAuthority(new Authority("ROLE_PATIENT"));

            // === USERS ===
            User admin = userService.addUser(new User(null, "emma", "pipoishere?", true, List.of(authority1, authority2)));
            User esp1User = userService.addUser(new User(null, "esp1", "esp1", true, List.of(authority2)));
            User esp2User = userService.addUser(new User(null, "esp2", "esp2", true, List.of(authority2)));
            User pac1User = userService.addUser(new User(null, "paciente1", "paciente1", true, List.of(authority3)));
            User pac2User = userService.addUser(new User(null, "paciente2", "paciente2", true, List.of(authority3)));
            User pac3User = userService.addUser(new User(null, "paciente3", "paciente3", true, List.of(authority3)));

// === TYPE SPECIALISTS ===
            TypeSpecialist cardio = new TypeSpecialist(null, "Cardiólogo", "Especialista en enfermedades del corazón", null);
            TypeSpecialist nutri = new TypeSpecialist(null, "Nutricionista", "Especialista en nutrición y dietética", null);

            typeSpecialistRepository.saveAll(List.of(cardio, nutri));

// === SPECIALISTS ===
            Specialist esp1 = new Specialist(null, "Carlos", "Ramírez", "987654321", "CMP12345",
                    "Cardiólogo con 10 años de experiencia", 10, esp1User, cardio, null, null, null, null, null);

            Specialist esp2 = new Specialist(null, "Lucía", "Gómez", "986541237", "CMP67890",
                    "Nutricionista deportiva", 6, esp2User, nutri, null, null, null, null, null);
            Specialist esp3 = new Specialist(null, "Daniela", "Mendez", "955333444", "CMP34345",
                    "Psicóloga ocupacional", 10, esp2User, nutri, null, null, null, null, null);

            cardio.setSpecialists(List.of(esp1));
            nutri.setSpecialists(List.of(esp2, esp3));

            specialistRepository.saveAll(List.of(esp1, esp2, esp3));


            // === PLANS ===
            Plan basic = new Plan(null, "Plan Básico", "Consultas generales y acceso limitado", 29.99, null);
            Plan premium = new Plan(null, "Plan Premium", "Consultas ilimitadas y atención prioritaria", 59.99, null);

            planRepository.saveAll(List.of(basic, premium));

// === PATIENTS ===
            Patient p1 = new Patient(null, "María", "Fernández", "F", 60, 165, "999888777",
                    LocalDate.of(1998, 5, 20), pac1User, basic, null, null, null, null);
            Patient p2 = new Patient(null, "Jorge", "Pérez", "M", 75, 178, "988777666",
                    LocalDate.of(1995, 3, 10), pac2User, premium, null, null, null, null);
            Patient p3 = new Patient(null, "Lucia", "Mendoza", "F", 55, 159, "911222333",
                    LocalDate.of(2001, 3, 19), pac3User, premium, null, null, null, null);

            patientRepository.saveAll(List.of(p1, p2, p3));


            // === HISTORIES ===
            History h1 = new History(null, "Sin antecedentes graves", "Ninguna", "Ninguno", p1);
            History h2 = new History(null, "Hipertensión controlada", "Polvo", "Losartán", p2);

            // === APPOINTMENTS ===
            Appointment a1 = new Appointment(null,
                    java.time.LocalDate.of(2025, 10, 1), java.time.LocalTime.of(9, 30),
                    "Control de ansiedad", "AGENDADO", p1, esp1, null, null);
            Appointment a2 = new Appointment(null,
                    java.time.LocalDate.of(2025, 10, 2), java.time.LocalTime.of(10, 0),
                    "Evaluación inicial", "COMPLETO", p2, esp1, null, null);
            Appointment a3 = new Appointment(null,
                    java.time.LocalDate.of(2025, 10, 3), java.time.LocalTime.of(15, 0),
                    "Seguimiento de tratamiento", "AGENDADO", p3, esp2, null, null);

            Appointment a4 = new Appointment(null,
                    java.time.LocalDate.of(2025, 10, 4), java.time.LocalTime.of(11, 0),
                    "Terapia familiar", "AGENDADO", p1, esp2, null, null);

            Appointment a5 = new Appointment(null,
                    java.time.LocalDate.of(2025, 10, 5), java.time.LocalTime.of(14, 30),
                    "Revisión médica", "COMPLETO", p2, esp3, null, null);

            Appointment a6 = new Appointment(null,
                    java.time.LocalDate.of(2025, 10, 6), java.time.LocalTime.of(16, 0),
                    "Consulta nutricional", "COMPLETO", p1, esp3, null, null);


            // === DIAGNOSES ===
            Diagnosis d1 = new Diagnosis(null, "Presión arterial estable", "Cardiología", "Continuar con chequeos regulares", LocalDate.now(), a1, esp1);
            Diagnosis d2 = new Diagnosis(null, "Peso saludable", "Nutrición", "Mantener dieta balanceada", LocalDate.now(), a2, esp2);
            Diagnosis d3 = new Diagnosis(null, "Peso saludable", "Nutrición", "Mantener dieta balanceada", LocalDate.now(), a2, esp2);
            Diagnosis d4 = new Diagnosis(null, "Peso saludable", "Nutrición", "Mantener dieta balanceada", LocalDate.now(), a2, esp2);
            Diagnosis d5 = new Diagnosis(null, "Peso saludable", "Nutrición", "Mantener dieta balanceada", LocalDate.now(), a2, esp2);
            Diagnosis d6 = new Diagnosis(null, "Peso saludable", "Nutrición", "Mantener dieta balanceada", LocalDate.now(), a2, esp2);

            a1.setDiagnoses(List.of(d1));
            a2.setDiagnoses(List.of(d2));
            a3.setDiagnoses(List.of(d3));
            a4.setDiagnoses(List.of(d4));
            a5.setDiagnoses(List.of(d5));
            a6.setDiagnoses(List.of(d6));

            // === SCHEDULES ===
            Schedule s1 = new Schedule(null, "Lunes", java.sql.Time.valueOf("08:00:00"), java.sql.Time.valueOf("12:00:00"), esp1);
            Schedule s2 = new Schedule(null, "Martes", java.sql.Time.valueOf("09:00:00"), java.sql.Time.valueOf("13:00:00"), esp2);

            // === MESSAGES ===
            Message m1 = new Message(null, "Buenos días doctor, tengo dudas sobre mi cita.", LocalDateTime.now(), a1, p1, esp1);
            Message m2 = new Message(null, "Claro, revisemos su historial en la próxima consulta.", LocalDateTime.now(), a1, p1, esp1);

            a1.setMessages(List.of(m1, m2));

            // === REVIEWS ===
            Review r1 = new Review(null, "Excelente atención, muy profesional.", LocalDate.now(), 5L, p1, esp1);
            Review r2 = new Review(null, "Muy amable y atenta.", LocalDate.now(), 4L, p2, esp2);

            // === RELACIONES FINALES ===
            p1.setAppointments(List.of(a1, a4, a6));
            p2.setAppointments(List.of(a2, a5));
            p3.setAppointments(List.of(a3));

            esp1.setAppointments(List.of(a1, a2));
            esp2.setAppointments(List.of(a3, a4));
            esp3.setAppointments(List.of(a5, a6));

            historyRepository.saveAll(List.of(h1, h2));
            appointmentRepository.saveAll(List.of(a1, a2, a3, a4, a5, a6));
            diagnosisRepository.saveAll(List.of(d1, d2, d3, d4, d5, d6));
            scheduleRepository.saveAll(List.of(s1, s2));
            messageRepository.saveAll(List.of(m1, m2));
            reviewRepository.saveAll(List.of(r1, r2));
        };
    }

}
