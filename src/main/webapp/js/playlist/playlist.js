
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