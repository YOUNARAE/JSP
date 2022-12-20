package kr.or.ddit.designpattern.adapterpattern;

public class Client {
	private Target target;
	
	public static void main(String[] args) {
		Client client = new Client();
//		client.target = new OtherConcrete(); 
		//부모타입에 자식타입 객체를 할당함
		client.target = new Adapter(new Adaptee()); //샤오미 돼지코를 삼성 돼지코로 바꿈
		client.target.request();
	}
}
