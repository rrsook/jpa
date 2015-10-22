package jpa.study;

import jpa.study.constants.EQUIPMENT_TYPE;
import jpa.study.constants.GRADE;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Date;

/**
 * Created by pillsoonpark on 2015. 10. 22..
 */
public class PcRoom {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    public static void main(String[] args) {
        initialize();


        PcRoom pcRoom = new PcRoom();
        int customerId = 0;

        try {
            tx.begin();
            em.flush();

            // get customers
            List<Customer> customers = em.createQuery("select c from Customer c", Customer.class).getResultList();

            customerId = customers.get(0).getId();

            try {
                pcRoom.checkin(customerId);

                pcRoom.checkout(customerId);

            } catch (Exception e) {
                printLog(e.getMessage());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }

    private static void initialize() {
        emf = Persistence.createEntityManagerFactory("jpastudy");
        em = emf.createEntityManager();
        tx = em.getTransaction();

        registEquipment();
        registCustomer();
    }



    private void checkout(int customerId) {
        Customer customer = em.find(Customer.class, customerId);

        long paidAmount = 0;

        printLog("결제한 금액은 %d원 입니다.", paidAmount);

    }

    private void checkin(int customerId) {
        CheckIn checkIn = new CheckIn();
        Customer customer = em.find(Customer.class, customerId);
        checkIn.setCustomer(customer);
        checkIn.setStartTime(new Date());

        Equipment equipment = null;
        try {
            equipment = getIdleEquipment(EQUIPMENT_TYPE.PC);
        } catch (Exception e) {
            printLog(e.getMessage());
        }



        checkIn.setEquipment(equipment);

        em.persist(checkIn);
    }

    private Equipment getIdleEquipment(EQUIPMENT_TYPE type) throws Exception {
        String sql = "select c from Equipment c where is_idle = true";
        List<Equipment> equipments = em.createQuery(sql, Equipment.class).getResultList();

        if (equipments.size() == 0) {
            registEquipment();
        }

        return equipments.get(0);
    }

    private static int registCustomer() {
        Customer customer = new Customer();
        customer.setName("유랴");
        customer.setGrade(GRADE.NEW);
        customer.setAge(20);
        customer.setRegistTime(new Date());

        em.persist(customer);
        printLog("%s 님이 고객으로 등록되었습니다.", customer.getName());

        return customer.getId();
    }

    private static void registEquipment() {
        Equipment equipment = new Equipment();
        equipment.setType(EQUIPMENT_TYPE.PC);
        equipment.setPrice(1500);
        equipment.setIsIdle(true);

        em.persist(equipment);
    }

    private static void printLog(String template, Object... obj) {
        System.out.println(String.format(template, obj));
    }
}
