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
	if(au_id==""){
		alert('리뷰를 수정하려면 로그인 해주세요.');
		location.href="ReviewLikeC";
	} else {
		if(au_id=='admin'){
			alert('관리자님은 게시글 삭제 권한만 있습니다.');
		} else{
			if(au_id!=writer){
				alert('본인 글만 수정할 수 있습니다.');
			} else{
				location.href="ReviewUpdateC?no="+no;
			}
		}
	}
}

function deleteReview(no, writer, au_id) {
	if(au_id==""){
		alert('리뷰를 삭제하려면 로그인 해주세요.');
		location.href="LoginC";
	} else {
		if((au_id!=writer)&&(au_id!='admin')){
			alert('본인 글만 삭제할 수 있습니다.');
		} else{
			const really = confirm('정말 삭제하겠습니까?');
			if(really){
			location.href="ReviewDelC?no="+no;
			}
		}
	}
}

function report(no, au_id){
	if(au_id==""){
		alert('신고하려면 로그인 해주세요.');
		location.href="LoginC";
	} else {
		if(au_id=='admin') {
		alert('관리자님????????????');
		} else{
			const really = confirm('정말 신고하겠습니까?');
			if(really){
			location.href="ReviewReportC?no="+no;
			}
		}	
	}
}

function commentDel(re_id, reco_id){
	const really = confirm('댓글을 삭제하시겠습니까?');
	if(really){
		location.href="ReviewCommentC?no="+re_id+"&commentId="+reco_id;
	}
}

function adminClickedButton(){
	alert('관리자님??????????????????');
}