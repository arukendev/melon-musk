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
		if((au_id=='admin')&&(writer!='admin')){
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

function report(no, writer, au_id){
	if(au_id==""){
		alert('신고하려면 로그인 해주세요.');
		location.href="LoginC";
	} else {
		if(writer=='admin'){
			alert('공지는 신고할 수 없습니다.');
		} else{
			if(au_id=='admin') {
			alert('관리자님????????????');
			} else if(au_id==writer){
				const really = confirm('본인 글을 정말 신고하겠습니까?');
				if(really){
				location.href="ReviewReportC?no="+no;
				}
				} else {
				const really = confirm('정말 신고하겠습니까?');
				if(really){
				location.href="ReviewReportC?no="+no;
				}
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

function calcChars(){
	document.getElementById('chars').value=document.getElementById('reviewReg_row_textInput').value.length;
}
function calcChars2(){
	document.getElementById('chars').value=document.getElementById('reviewUpdate_row_textInput').value.length;
}




/* validCheck -----------------------------------------------------------------------------------------*/

function isEmpty(input){
	return !input.value;
}

function isNotType(input){
	return (input.value.indexOf(".jpg") == -1)&&(input.value.indexOf(".jpeg") == -1)&&(input.value.indexOf(".gif") == -1)&&(input.value.indexOf(".png") == -1)&&(input.value.indexOf(".JPG") == -1)&&(input.value.indexOf(".GIF") == -1)&&(input.value.indexOf(".PNG") == -1)&&(input.value.indexOf(".JPEG") == -1);
}

function moreThan(input, length){
	return input.value.length > length;
}



/* valueCheck -------------------------------------------------------------------------------- */

function reviewCall(){
	const name = document.getElementById("reviewReg_row_nameInput");
	const img = document.getElementById("reviewReg_row_imgInput");
	const text = document.getElementById("reviewReg_row_textInput");
	
	if(isEmpty(name)){
		alert('제목은 필수 입력 사항입니다.');
		return false;
	}
	
	if(moreThan(name, 100)){
		alert('제목은 100자를 초과할 수 없습니다.');
		return false;
	}
	
	
	if(isEmpty(text)){
		alert('내용은 필수 입력 사항입니다.');
		return false;
	}
	
	if(moreThan(text, 1000)){
		alert('내용은 1000자를 초과할 수 없습니다.');
		return false;
	}
	if(isNotType(img)){
		if(isEmpty(img)){
			return true;
		} else{
		alert('이미지는 jpg, png, gif 확장자 파일만 업로드 가능합니다.');
		return false;
		}
	}
	
	if(moreThan(img, 200)){
		alert('첨부 이미지 파일명 허용 길이 초과');
		return false;
	}
	
}

function reviewCommentCall(){
	const comment = document.getElementById("reviewDetail_comment_txt");
	
	if(isEmpty(comment)){
		alert('내용은 필수 입력 사항입니다.');
		return false;
	}
}

/*-----------------------------------------------------------------------------------*/
if (
  location.href.includes("ReviewLike") ||
  location.href.includes("ReviewComment")
) {
  location.href = "ReviewDetailC?no=" + getParam('no');
}


function getParam(paramName) {

    var params = location.search.substr(location.search.indexOf("?") + 1);

    var sval = "";

    params = params.split("&");

    for (var i = 0; i < params.length; i++) {

        temp = params[i].split("=");

        if ([temp[0]] == paramName) { sval = temp[1]; }

    }

    return sval;

}