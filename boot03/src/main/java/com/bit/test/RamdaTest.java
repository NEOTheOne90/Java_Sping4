package com.bit.test;

interface MyInter{
	void prn();
}

//인터페이스에서 선언된 메소드 사용 방법 - 1
class MyClass implements MyInter{
	@Override
	public void prn() {
		System.out.println("테스트...1");
	}
}

public class RamdaTest {
	public static void main(String[] args) {
		//인터페이스에서 선언된 메소드 사용 방법 - 2
		MyInter mi = new MyInter() {
			@Override
			public void prn() {
				System.out.println("테스트...2");
			}
		};
		mi.prn();
		//인터페이스에서 선언된 메소드 사용 방법 - 3
		MyInter mi2 = () -> System.out.println("테스트...3");
		mi2.prn();
	}
}







