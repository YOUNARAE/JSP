/**
 * 
 */
//폼태그의 모든 입력 데티어의 이름과 값을 콘솔에 로그로 출력할 수 있는 함수.
//ex) $("form").log().serializeObject(); 트리구조로 사용할 수 있어서 필요하면 시리얼라이즈 함수까지 붙여서 사용할 수 있어야함
//폼태그의
$.fn.serializeObject=function(){
	if(this.prop('tagName')!='FORM')
		throw Error("form 태그 외에는 사용할 수 없음");
	let fd = new FormData(this[0]);
	let nameSet = new Set();
	for(let key of fd.keys()){
		nameSet.add(key);
	}
	let data = {};
	for(let name of nameSet){
		let values = fd.getAll(name);
		if(values.length>1){
			data[name] = values; //연상배열구조로 사용할 거라서 data("writer")이런 방식이 아닌 []로 사용한다
		} else {
			data[name] = values[0];
		}
	}
	return data;
}

$.fn.log=function(){
	let data = this.serializeObject();
	for(let prop in data){
		console.log(prop + " : " + data[prop]);
	} //오브 우측에 오는 피 연산자가 반복이 될 수 있을 때에 가능하다, 그냥 객체일 때는 in을 써서 property 하나하나에 접근을 해야한다.
	return this;
}

$.fn.sessionTimer=function(timeout, msgObj){
	if(!timeout)
		throw Error("세션 타임아웃 값이 없음.");
	
	const SPEED = 100;
	const TIMEOUT = timeout;  //기준값이 있어야해서 상수화해놓았다
	const timerArea = this;
	
// 	event propagation : bubbling 방식
	let msgArea = null;
	if(msgObj){
		msgArea = $(msgObj.msgAreaSelector).on("click", msgObj.btnSelector, function(event){ //디센던트 구조가 있을 땐 이벤트 타겟은 ".controlBtn"이 된다
			// 		console.log(this.id + "," + $(this).prop("id"));//html엘리먼트일 때
			if(this.id=="YES"){
				//타이머는 다시 120으로 바뀌어야한다	
				jobClear();
				timerInit();
				$.ajax({
					method : "head" 
				});
			}
			msgArea.hide();
		}).hide();		
	}
	
	let jobClear = function(){
		let timerJob = timerArea.data("timerJob");
		if(timerJob)
			clearInterval(timerJob);//클리어할값을 파라미터로 넘긴다
		let msgJob = msgArea.data('msgJob');
		if(msgJob)
			clearTimeout(msgJob);
	}
	
	let timerInit = function(){
		//지연작업
		if(msgObj){
			let msgJob = setTimeout(() => {
				msgArea.show();
			}, (TIMEOUT-60) * SPEED);	
			msgArea.data('msgJob', msgJob);			
		}
		
		let timer = TIMEOUT;
		let timerJob = setInterval(() => { 
			if(timer==1) {
				clearInterval(timerJob);
				location.reload();
			}else 
				timerArea.html( timeFormat(--timer) );		
		}, SPEED);
		timerArea.data("timerJob", timerJob);
	}
	
	timerInit();
	
	let timeFormat = function(time){
		let min = Math.trunc( time / 60 ); //동적타입에서 연산에 메인은 연산자이다. <->정적타입은 연산에 메인은 메인문자열
		let sec = time % 60;
		return min + ":" + sec;
	}
	
	return this;
}
