package com.green;

import com.green.chatbot.Answer;
import com.green.chatbot.AnswerRepository;
import com.green.chatbot.ChatBotIntention;
import com.green.chatbot.ChatBotIntentionRepository;
import com.green.domain.entity.*;
import com.green.security.MyRole;
import com.green.utils.MyFileUtils;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class ConvenienceStoreApplicationTests {

    @Autowired
    private PasswordEncoder pe;

    @Value("${file.location.temp}")
    private String locationTemp;

    @Value("${file.location.upload}")
    private String locationUpload;

    @Autowired
    ImagesEntityRepository imgRepo;

    @Autowired
    EmployeesEntityRepository empRepo;

    @Autowired
    AddressEntityRepository addrRepo;

    @Autowired
    TeamEntityRepository teamRepo;

    @Autowired
    DepartmentEntityRepository depRepo;


   // @Test
    void 관리자생성테스트() {


        //사원엔티티 저장
        empRepo.save(EmployeesEntity.builder()
                .email("admin@test.com")
                .pass(pe.encode("1234"))
                .name("admin")
                .hireDate(LocalDate.parse("2023-01-09"))
                .phone("010-1234-5678")
//              .role(MyRole.ADMIN)
                .build());

    }
    //@Test
    void 팀데이터추가() {
    	teamRepo.save(TeamEntity.builder()
    			.dep(depRepo.findById(4L).get())//부서넘버
    			.name("오프라인사업팀")
    			.build());
    }
    //@Test
    void 부서데이터저장() {
    	depRepo.save(DepartmentEntity.builder()
    			.name("인사담당부")
    			.build());
    }

	////////////////////////챗봇관련//////////////////////////////
	@Autowired
	AnswerRepository answer;

	@Autowired
	ChatBotIntentionRepository intention;

	//@Test
	void 의도() {
		intention.save(ChatBotIntention.builder()
				.name("전화")
				.answer(answer.findById(1L).get())
				.build());
	}


	//@Test
	void 답변() {

	}

	//@Test
	void 안녕의도() {
		intention.save(ChatBotIntention.builder()
				.name("안녕")
				.answer(answer.save(Answer.builder()
						.keyword("전화")
						.content("문의한 사원의 전화번호 입니다.")
						.build()))
				.build());
		;
	}
	//@Test
	void 기타의도() {
		intention.save(ChatBotIntention.builder()
				.name("기타")
				.answer(answer.save(Answer.builder()
						.keyword("기타")
						.content("질문이 명확하지 않습니다</br>정확하게 입력 해 주세요!")
						.build()))
				.build());
		;
	}

	@Autowired
	DepartmentEntityRepository dept;
	/*
	//@Test
	void contextLoads() {
		//회사
		DepartmentEntity d1=dept.save(DepartmentEntity.builder().name("greengames").depth(0).build());

		//1차 : 부
		String[] s={"인사지원부","영업부","개발부","디자인부","고객관리부","경영지원부"};
		Arrays.asList(s).forEach((dname)->{
			dept.save(DepartmentEntity.builder().name((String)dname).depth(1)
					.parent(d1)//상위부서
					.build());
		});

	}*/
	/*
	//@Test
	void 부서등록() {

		String[] s={"인사지원부","영업부","개발부","디자인부","고객관리부","경영지원부"};
		for(int i=0;i<s.length; i++) {
			String[] dept2=null;
			//2차 : 팀
			if(i==0) dept2= new String[]{"인사지원팀","인력개발팀"};
			else if(i==1) dept2= new String[]{"국내영업팀","해외영업팀"};
			else if(i==2) dept2= new String[]{"서버","클라이언트","플랫폼","게임개발A","게임개발B"};
			else if(i==3) dept2= new String[]{"일러스트","3D모델링","UI/UX디자인"};
			else if(i==4) dept2= new String[]{"CS"};
			else if(i==5) dept2= new String[]{"마케팅","재무","회계"};

			String parentName=s[i];

			Arrays.asList(dept2).forEach((dname)->{
				dept.save(DepartmentEntity.builder().dname((String)dname).depth(2)
						.parent(dept.findByDname(parentName).get())//상위부서
						.build());
			});
		}

	}
*/
	/*
	@Autowired
	EmployeesEntityRepository member;
	@Autowired
	PasswordEncoder pe;

	//@Test
	void 사원테스트() {
		List<DepartmentEntity> list=dept.findAllByDepth(2);
		IntStream.rangeClosed(1, list.size()).forEach(i->{
			member.save(MemberEntity.builder()
					.name("지지"+i)
					.id("gg"+i)
					.email("gg"+i+"@ggames.shop")
					.pass(pe.encode("1234"))
					.phone(String.format("010-1111-11%02d", i))
					.hireDate(LocalDate.now().minusDays(i))
					.dept(list.get(i-1))//해당
					.build());
		});


	}

	//@Test
	void 사원테스트2() {


		member.save(MemberEntity.builder()
				.name("박기태")
				.id("gitae")
				.email("gitae@ggames.shop")
				.pass(pe.encode("1234"))
				.phone(String.format("010-1234-5678"))
				.hireDate(LocalDate.now().minusDays(10))
				.dept(dept.findAllByDepthAndDname(2, "게임개발A").get())//해당
				.build());



	}
*/
	
}
