var count = 0;
function likeUpdated(like,id){
        		alert(like, id);
				location.href='ReviewLikeC?likes='+like+'&id='+id;
    		};


function pushLike(a, id){
	
	if(a==''){
		alert('로그인 해주세요.');
	} else {
		count++;
		if(count%2 == 1){
		alert('추천 했습니다.');
		document.getElementById('likeNumber').innerText=parseInt(document.getElementById('likeNumber').innerText,10)+1;
		
		} 
		else{
			alert('추천 취소');
			document.getElementById('likeNumber').innerText=parseInt(document.getElementById('likeNumber').innerText,10)-1;
		}
		window.onbeforeunload=likeUpdated(document.getElementById('likeNumber',id).innerText); 
			
		
	}
}

