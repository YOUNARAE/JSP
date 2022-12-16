/**
 * 
 */
//폼태그의 모든 입력 데티어의 이름과 값을 콘솔에 로그로 출력할 수 있는 함수.
//ex) $("form").log().serializeObject(); 트리구조로 사용할 수 있어서 필요하면 시리얼라이즈 함수까지 붙여서 사용할 수 있어야함
//폼태그의
$.fn.log=function(){
	let data = this.serializeObject();
	for(let prop in data){
		console.log(prop + " : " + data[prop]);
	} //오브 우측에 오는 피 연산자가 반복이 될 수 있을 때에 가능하다, 그냥 객체일 때는 in을 써서 property 하나하나에 접근을 해야한다.
	return this;
}

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
