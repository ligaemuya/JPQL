package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션을 받아오지않으면 실제 DB에 저장되지않음
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {


            /*
            //Criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();*/
       /* TypedQuery<Member> q = em.createQuery("select m from Member m where m.id = '10'", Member.class);
            Member singleResult = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());
//            TypedQuery<String> q2 = em.createQuery("select m.username from Member m", String.class);
//            Query q3 = em.createQuery("select m.username, m.age from Member m");

            Member result = q.getSingleResult();
            //Spring Data JPA ->
            System.out.println("result = " + result);*/


//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
//            List<Team> membersTeam = em.createQuery("select t from Member m join m.team t", Team.class).getResultList();
//
//            Member findMember = members.get(0);
//            findMember.setAge(20);
//
//            em.createQuery("select o.address from Order o", Address.class).getResultList();
//            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO (m.username, m.age) from Member m", MemberDTO.class).getResultList();
//
//            MemberDTO memberDTO = resultList.get(0);
//            MemberDTO memberDTO2 = resultList.get(0);
//            memberDTO2.setName("user1");
//            System.out.println("memberDTO = " + memberDTO.getName());
//            System.out.println("memberDTO = " + memberDTO.getAge());

            Team team = new Team();
            em.persist(team);
            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);
            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);




            em.flush();
            em.clear();
            String query = "select t.members.size From Team t";
            String query2 = "select t.members From Team t";
            Integer result = em.createQuery(query, Integer.class).getSingleResult();
            Collection result2 = em.createQuery(query2, Collection.class).getResultList();
            System.out.println("result = " + result);
            System.out.println("result2 = " + result2);


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            //code
            em.close();
        }
        emf.close();
    }

    private static void logic(Member m, Member m1) {
        System.out.println("m == m2: " + (m1 instanceof Member));
        System.out.println("m == m2: " + (m instanceof Member));
    }
}
