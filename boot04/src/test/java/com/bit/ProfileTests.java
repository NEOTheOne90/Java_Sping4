package com.bit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Member;
import com.bit.domain.Profile;
import com.bit.persistence.MemberRepository;
import com.bit.persistence.ProfileRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@Log
@Commit
@SpringBootTest
public class ProfileTests {
	@Autowired
	MemberRepository mrepo;
	@Autowired
	ProfileRepository prepo;
	
	@Test
	public void testInsertMember() {
		IntStream.range(1, 101).forEach(i->{
			Member member = new Member();
			member.setUid("user"+i);
			member.setUpwd("pw"+i);
			member.setUname("사용자"+i);
			mrepo.save(member);
		});
	}
	
	@Test
	public void testInsertProfile() {
		Member member = new Member();
		member.setUid("user1");
		for (int i = 1; i < 5; i++) {
			Profile profile = new Profile();
			profile.setFname("face"+i+".jpg");
			if(i==1)
				profile.setCurrent(true);
			profile.setMember(member);
			prepo.save(profile);
		}
	}
	
	@Test
	public void testFetchJoin1() {
		List<Object[]> results = mrepo.getMemberWithProfileCount("user1");
		results.forEach(arr->System.out.println(Arrays.toString(arr)));
	}
	
	@Test
	public void testFetchJoin2() {
		List<Object[]> results = mrepo.getMemberWithProfile("user1");
		results.forEach(arr->System.out.println(Arrays.toString(arr)));
	}
	
}












