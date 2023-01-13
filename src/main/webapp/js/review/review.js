function write(au_id){
	if(au_id==""){
		alert('리뷰를 작성하려면 로그인 해주세요.');
		location.href="ReviewLikeC";
	} else{
		location.href="ReviewRegC";
	}
}




function like(no, writer, au_id) {
	if(au_id==""){
		alert('좋아요 하시려면 로그인 해주세요.');
		location.href="ReviewLikeC";
	} else {
		if(au_id==writer){
			alert('본인 글에 좋아요 할 수 없습니다. 양심 좀...');
		} else{
			location.href="ReviewLikeC?no="+no+"&wr="+writer;
		}
	}
	
}

function update(no, writer, au_id) {
	if(au_id!=writer){
		alert('본인 글만 수정할 수 있습니다.');
	} else{
		location.href="ReviewUpdateC?no="+no;
	}
}

function deleteReview(no, writer, au_id) {
	if(au_id!=writer){
		alert('본인 글만 삭제할 수 있습니다.');
	} else{
		location.href="ReviewDelC?no="+no;
	}
}
