package com.lm.myagenda.services;

import com.lm.myagenda.models.*;
import com.lm.myagenda.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DBService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    ProfessionalRepository professionalRepository;

    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    public String currentDayPlusDays(int i){
        String datayyyymmdd = LocalDateTime.from(new Date().toInstant().atZone(ZoneId.of("GMT-3"))).plusDays(i).toString().substring(0, 10);
		System.out.println("data yyyy-mm-dd: "+datayyyymmdd); //data yyyy-mm-dd: 2022-12-10
        return datayyyymmdd;
    }
    
    public void instantiateTestDatabase() throws ParseException{
        
        DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;
        DateTimeFormatter dtfPatternLocalZone = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.systemDefault());
        
        long now = System.currentTimeMillis()/1000;
        Instant instantNow = Instant.ofEpochSecond(now);
        Instant instantNow2= Instant.now();
        System.out.println(dtf.format(instantNow));  //2022-12-09T10:42:08Z
        System.out.println(dtf.format(instantNow2)); //2022-12-09T10:42:08.394563045Z

        Instant instant = Instant.parse(currentDayPlusDays(0)+"T10:45:00-03:00");

        String data = "1992-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate1 = LocalDate.parse(data,formatter);
        LocalDate birthdate2 = birthdate1.plusDays(80);
        LocalDate birthdate3 = birthdate1.plusDays(360);
        LocalDate birthdate4 = birthdate1.plusDays(120);

        //Item de servi??o
        Item item1 = new Item(null, "Enfermagem", "vacina????o Gripe", "vacina tipo", new BigDecimal("1.96"), true);
        Item item2 = new Item(null, "Coleta sanguinea", "Coleta colesterol Total", "coleta", new BigDecimal("1.10"), true);
        Item item3 = new Item(null, "Enfermagem", "Aferi????o", "Procedimento normal", new BigDecimal("1.05"), false);
        Item item4 = new Item(null, "Geral", "Acolhimento", "primeiro atendimento", new BigDecimal("0.01"), true);
        Item item5 = new Item(null, "Coleta sanguinea", "Coleta Glicemia", "descri????o coleta sanguinea", new BigDecimal("0.52"), true);
        Item item6 = new Item(null, "Geral", "Consulta", "Consulta com especialista", new BigDecimal("0.30"), true);
        itemRepository.saveAll(Arrays.asList(item1,item2,item3,item4,item5,item6));

        //Professional (employee)
        Professional emp1 = new Professional(null, "nomeEmpregado1","11891612069","mat1101","Tec de enfermagem", "enf@email.com","descricao","statusATIVO",null, LocalDate.now().minusDays(10));
        Professional emp2 = new Professional(null, "nomeEmpregado2","25455258044","mat2022","tec de enfermagem", "enf2@email.com","descricao","statusATIVO",null, LocalDate.now().minusDays(500));
        Professional emp3 = new Professional(null, "nomeEmpregado3","32611952078","mat3330","tec de enfermagem", "enf3@email.com","descricao","statusATIVO",null, LocalDate.now().minusYears(10));
        Professional emp4 = new Professional(null, "nomeEmpregado4","41211698084","mat4144","tec de enfermagem", "enf4@email.com","descricao","statusATIVO",null, LocalDate.now().minusMonths(12));
        Professional emp5 = new Professional(null, "nomeEmpregado5","55096693066","mat5505","tec de enfermagem", "enf5@email.com","descricao","statusATIVO",null, LocalDate.parse("2013-09-28"));
        professionalRepository.saveAll(Arrays.asList(emp1,emp2,emp3,emp4,emp5));

        //Person
        Person p1 = new Person(null, "jose severino da silva Neto jr", null, "14031195036", "123123412341231", "jose@email.com", "masculino", birthdate1, "0000001234", "area","anota????o", "url");
        p1.setRegisterDate(instantNow);
        Person p2 = new Person(null, "??gatha Rodrigues Gomes", null, "23397241049", "223123412341232", "agatha@email.com", "masculino", birthdate2, "0000001234", "area","anota????o", "url");
        p2.setRegisterDate(instantNow);
        Person p3 = new Person(null, "Julieta Gon??alves Araujo", null, "38406257008", "323123412341233", "Julieta@email.com", "masculino", birthdate3, "0000001234", "area","anota????o", "url");
        p3.setRegisterDate(instantNow);
        Person p4 = new Person(null, "Laura Azevedo Severina", null, "44232636021", "423123412341234", "Laura@email.com", "feminino", birthdate4, "0000001234", "area","anota????o", "url");
        p4.setRegisterDate(instantNow);
        Person p5 = new Person(null, "Nicolash Barbosa Souza", null, "57627251036", "523123412341234", "Nicolash@email.com", "feminino", birthdate4, "0000001234", "area","anota????o", "url");
        p5.setRegisterDate(instantNow);
        Person p6 = new Person(null, "Caio Almeida Souza gomes", null, "67210502009", "623123412341234", "Caio@email.com", "feminino", birthdate4, "0000001234", "area","anota????o", "url");
        p6.setRegisterDate(instantNow);

        //Address
        Address end1 = new Address(null, "avenida principal teste","10","Complemento","Cidade Universit??ria","Recife","PE","Brasil", "11222111", "observacao", "Residencial", p1);
        Address end2 = new Address(null, "rua segundaria teste","2","Complemento","Cidade Universit??ria","Joao Pessoa","PB","Brasil", "11222111", "observacao", "Residencial", p2);
        Address end3 = new Address(null, "travessa terceira teste","300","Complemento","Bairro","Jaboatao","PE","Brasil", "11222111", "observacao", "Residencial", p3);
        Address end4 = new Address(null, "via 4 teste","4000","Complemento","bairro","Caruau","PE","Brasil", "11222111", "observacao", "Resid??ncia Principal", p4);
        Address end5 = new Address(null, "avenida 5","50","Complemento","Cidade Universit??ria","Recife","PE","Brasil", "11222111", "observacao", "Residencial", p4);
        Address end6 = new Address(null, "rua segundaria teste","2","Complemento","Cidade Universit??ria","Joao Pessoa","PB","Brasil", "11222111", "observacao", "Residencial", p5);
        Address end7 = new Address(null, "rua segundaria teste","2","Complemento","Cidade Universit??ria","Joao Pessoa","PB","Brasil", "11222111", "observacao", "Residencial", p6);

        //Phone
        Phone tel1 = new Phone(null,"11","999999999","telefone pessoal","pessoal",p1);
        Phone tel3 = new Phone(null,"90","900000000","telefone apenas ws","pessoal",p3);

        personRepository.saveAllAndFlush(Arrays.asList(p1, p2, p3, p4, p5, p6));
        addressRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5, end6, end7));
        phoneRepository.saveAll(Arrays.asList(tel1, tel3));

        //Agenda
        Agenda agenda1 = new Agenda(null,"Agenda A funcionario2","agenda do profissional 1","ativa","grupoAgenda",emp2);
        agendaRepository.save(agenda1);
        Agenda agenda2 = new Agenda(null,"Agenda B funcionario3","agenda do profissional 2","ativa","grupoAgenda",emp3);
        agendaRepository.save(agenda2);
        Agenda agenda3 = new Agenda(null,"Agenda C funcionario4",emp4.getNome(),"ativa","Dentista",emp4);
        agendaRepository.saveAndFlush(agenda3);
        Agenda agenda4 = new Agenda(null,"Agenda D funcionario5", emp5.getNome(),"ativa","M??dico",emp5);
        agendaRepository.save(agenda4);

        // Adding agenda for the professional
        emp2.setAgenda(agenda1);
        emp3.setAgenda(agenda2);
        emp4.setAgenda(agenda3);
        emp5.setAgenda(agenda4);
        professionalRepository.saveAll(Arrays.asList(emp2,emp3,emp4,emp5));

        //Atendimentos
        Attendance a1 = new Attendance(null, "descricao atendimento1", "Atendimento pendente de confirma????o", instantNow.plus(4,ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES), dtfPatternLocalZone.format(instantNow.plus(4,ChronoUnit.DAYS)), dtfPatternLocalZone.format(instantNow.plus(4,ChronoUnit.DAYS).plus(14,ChronoUnit.MINUTES).plus(59, ChronoUnit.SECONDS)), "observacao", instantNow.toString(), p1);
        a1.getProfessionais().addAll(Arrays.asList(emp1,emp3));
        a1.setAgenda(agenda1);
        Attendance a2 = new Attendance(null, "descricao atendimento2", "Atendimento confirmado", instant.plus(2,ChronoUnit.DAYS), dtfPatternLocalZone.format(instant.plus(2,ChronoUnit.DAYS)), dtfPatternLocalZone.format(instant.plus(2,ChronoUnit.DAYS).plus(14,ChronoUnit.MINUTES).plus(59, ChronoUnit.SECONDS)), null, instantNow.toString(), p2);
        a2.getProfessionais().addAll(Arrays.asList(emp2,emp4));
        a2.setAgenda(agenda2);
        Attendance a3 = new Attendance(null, "descricao atendimento3", "Atendimento suspenso", instant.plus(5, ChronoUnit.DAYS), dtfPatternLocalZone.format(instant.plus(5, ChronoUnit.DAYS)), dtfPatternLocalZone.format(instant.plus(5, ChronoUnit.DAYS).plus(899,ChronoUnit.SECONDS)), null, instantNow.toString(), p3);  //899 segundos = 14min:59seg
        a3.getProfessionais().addAll(Arrays.asList(emp1));
        a3.setAgenda(agenda2);
        Attendance a4 = new Attendance(null, "descricao atendimento4", "Atendimento confirmado", instant.plus(5,ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES), dtfPatternLocalZone.format(instant.plus(5, ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES)), dtfPatternLocalZone.format(instant.plus(5, ChronoUnit.DAYS).plus(29, ChronoUnit.MINUTES)), null, instantNow.toString(), p4);
        a4.getProfessionais().addAll(Arrays.asList(emp2));
        a4.setAgenda(agenda2);
        Attendance a5 = new Attendance(null, "descricao atendimento5", "Atendimento confirmado", instant.plus(5,ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES), dtfPatternLocalZone.format(instant.plus(5, ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES)), dtfPatternLocalZone.format(instant.plus(5, ChronoUnit.DAYS).plus(29, ChronoUnit.MINUTES)), null, instantNow.toString(), p2);
        a5.getProfessionais().addAll(Arrays.asList(emp3));
        a5.setAgenda(agenda3);
        Attendance a6 = new Attendance(null, "descricao atendimento6", "Atendimento confirmado", instant.plus(5,ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES), dtfPatternLocalZone.format(instant.plus(5, ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES)), dtfPatternLocalZone.format(instant.plus(5, ChronoUnit.DAYS).plus(29, ChronoUnit.MINUTES)), "observacao a6", instantNow.toString(), p3);
        a6.setAgenda(agenda3);
        Attendance a7 = new Attendance(null, "descricao atendimento7", "Atendimento confirmado", instant.minus(3,ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES), dtfPatternLocalZone.format(instant.minus(3, ChronoUnit.DAYS).plus(15, ChronoUnit.MINUTES)), dtfPatternLocalZone.format(instant.minus(3, ChronoUnit.DAYS).plus(29, ChronoUnit.MINUTES)), "observacao a6", instant.minus(10,ChronoUnit.DAYS).toString(), p3);
        a7.setAgenda(agenda3);

        attendanceRepository.saveAllAndFlush(Arrays.asList(a1,a2,a3,a4,a5,a6,a7));

        //Order - Ordem de servico (lista de itens no atendimento)
        Order order1 = new Order(null,a1,Arrays.asList(item1));
        Order order2 = new Order(null,a2,Arrays.asList(item2,item3));
        Order order3 = new Order(null,a3,Arrays.asList(item1,item2));
        Order order4 = new Order(null,a4,Arrays.asList(item3,item5,item4));
        Order order5 = new Order(null,a6,Arrays.asList(item3));
        Order order6 = new Order(null,a5,Arrays.asList(item6,item6,item6,item6,item6,item6));
        orderRepository.save(order1);
        orderRepository.saveAll(Arrays.asList(order2,order3,order4,order5,order6));

        //Events
        List<Event>eventos = new ArrayList<>();

        Event event1 = new Event();
        event1.setTitle(p1.getName()+" cpf:"+p1.getCpf());
        event1.setDateUTC(instantNow.plus(2,ChronoUnit.DAYS));              
        event1.setStart(dtfPatternLocalZone.format(instantNow.plus(2,ChronoUnit.DAYS)));
        event1.setEnd(dtfPatternLocalZone.format(instantNow.plus(2,ChronoUnit.DAYS).plus(14,ChronoUnit.MINUTES).plus(59, ChronoUnit.SECONDS)));        
        event1.setAttendanceId(a1.getId());
        event1.setPersonBirthDate(p1.getBirthdate().toString());
        event1.setPersonCPF(p1.getCpf());
        event1.setPersonPhone("11911111111");
        eventos.add(event1);                
        
        Event event2 = new Event();
        event2.setTitle(p2.getName()+" cpf:"+p2.getCpf());
        event2.setDateUTC(a2.getDateInUTC());
        event2.setStart(a2.getStartTime());
        event2.setEnd(a2.getEndTime());
        event2.setDisplay("block");
        event2.setAttendanceId(a2.getId());
        event2.setPersonBirthDate(p2.getBirthdate().toString());
        event2.setPersonCPF(p2.getCpf());
        event2.setPersonPhone("22922222222");
        eventos.add(event2);                  
        
        Event event3 = new Event(null, null, p3.getName(), a3.getDateInUTC(), a3.getStartTime(), a3.getEndTime(), null, null, null, false, "block", null, a3.getId(),p3.getCpf(),"33933333333", p3.getBirthdate().toString());
        eventos.add(event3);        
        Event event4 = new Event(null, null, p4.getName(), a4.getDateInUTC(), a4.getStartTime(), a4.getEndTime(), null, null, null, false, "block", "descri????o test", a4.getId(), p4.getCpf(), "44944444444", p4.getBirthdate().toString());
        eventos.add(event4);

        eventRepository.saveAll(eventos);

    }
    
}
