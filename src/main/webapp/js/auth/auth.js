function goMyPl(){
	 let f = document.createElement('form');
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'PlaylistC');
    document.body.appendChild(f);
    f.submit();
}