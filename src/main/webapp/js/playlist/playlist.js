
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

