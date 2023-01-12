
function like(no, writer, au_id) {
	if(au_id==writer){
		alert('본인 글에 좋아요 할 수 없습니다. 양심 좀...');
	} else{
		location.href="ReviewLikeC?no="+no+"&wr="+writer;
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
		location.href="ReviewDeleteC?no="+no;
	}
}
