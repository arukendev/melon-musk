
function deleteplaylist(n){
	let ok= confirm("삭제하시겠습니까?");
	if(ok){
	location.href='DeletePlaylistC?pl_id='+n
	}else{
	alert('삭제를 취소하였습니다.')	
	}
	
};

function deleteplmusic(n){
	location.href='DeletePlMusicC?pl_id='+n
}

function img(n){
	if(n==null){
		n='https://img.freepik.com/free-vector/realistic-question-box-mockup_23-2149489472.jpg?w=1380&t=st=1674105523~exp=1674106123~hmac=d456c089cf50ec912aab4ef03df38e3a85e7205fc5c96890913ef5b0b695f714';
	}
}




var target = document.querySelectorAll('.btn_open');
var btnPopClose = document.querySelectorAll('.pop_wrap .btn_close');
var targetID;

// 팝업 열기
for(var i = 0; i < target.length; i++){
  target[i].addEventListener('click', function(){
    targetID = this.getAttribute('href');
    document.querySelector(targetID).style.display = 'block';
  });
}

// 팝업 닫기
for(var j = 0; j < target.length; j++){
  btnPopClose[j].addEventListener('click', function(){
    this.parentNode.parentNode.style.display = 'none';
  });
}