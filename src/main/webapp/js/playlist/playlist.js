
//heart 좋아요 클릭시! 하트 뿅

$(document).ready(function(){

		var $likeBtn =$('.icon.heart');

        $likeBtn.click(function(){
        $likeBtn.toggleClass('active');

        if($likeBtn.hasClass('active')){          
           $(this).find('img').attr({
              'src': 'https://cdn-icons-png.flaticon.com/512/803/803087.png',
               alt:'찜하기 완료'
                });
          
          
         }else{
            $(this).find('i').removeClass('fas').addClass('far')
           $(this).find('img').attr({
              'src': 'https://cdn-icons-png.flaticon.com/512/812/812327.png',
              alt:"찜하기"
           })
         }
     })
 });


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

