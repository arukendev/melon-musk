--테이블 삭제
drop table artist cascade constraint purge;

--테이블 안에 모든 항목 삭제
delete artist;

--테이블 컬럼 추가
--ALTER TABLE 테이블명 ADD 컬럼명 number(10) not null;

--테이블 조회
select * from auth;

--아티스트
create table artist(
	ar_id number(10) primary key,
	ar_name varchar2(200 char) not null,
	ar_member_id varchar2(100 char) not null,
	ar_member_name varchar2(100 char) not null,
	ar_img varchar2(400 char) not null,
	ar_type varchar2(10 char) not null,
	ar_company varchar2(50 char) not null,
	ar_debut varchar2(50 char) not null,
	ar_birth varchar2(10 char) not null,
	ar_info varchar2(4000 char) not null
);

--앨범

create table album(
	al_id number(10) primary key,
	al_ar_id varchar2(200 char) not null,
	al_ar_name varchar2(200 char) not null,
	al_name varchar2(200 char) not null,
	al_img varchar2(400 char) not null,
	al_type varchar2(10 char) not null,
	al_date varchar2(10 char) not null,
	al_genre varchar2(50 char) not null,
	al_publisher varchar2(200 char) not null,
	al_agency varchar2(200 char) not null,
	al_info varchar2(4000 char) not null
);

--음악

create table music(
	mu_id number(10) primary key,
	mu_ar_id varchar2(200 char) not null,
	mu_ar_name varchar2(200 char) not null,
	mu_al_id number(10) not null,
	mu_al_name varchar2(200 char) not null,
	mu_al_img varchar2(400 char) not null,
	mu_date varchar2(10 char) not null,
	mu_name varchar2(100 char) not null,
	mu_genre varchar2(50 char) not null,
	mu_lyrics varchar2(4000 char) not null,
	mu_link varchar2(50 char) not null
);


--계정

create table auth(
	au_id varchar2(10 char) primary key,
	au_pw varchar2(15 char) not null,
	au_name varchar2(10 char) not null,
	au_interest varchar2(100 char) not null,
	au_introduce varchar2(100 char) not null,
	au_img varchar2(500 char) not null
);

insert into auth values(
'admin',
'Admin123',
'관리자',
'k-pop,j-pop,메탈,힙합',
'관리자입니다.'
);

insert into auth values(
'aa',
'aa',
'test',
'k-pop,j-pop,메탈,힙합',
'test입니다.'
);

--리뷰

create table review(
	re_id number(10) primary key,
	re_name varchar2(30 char) not null,
	re_img varchar2(200 char) not null,
	re_text varchar2(2000 char) not null,
	re_view number(10) not null,
	re_like number(10) not null,
	re_date date not null
);

create sequence review_seq;

insert into review values(
1,
'뉴진스 신곡 리뷰',
'https://img.gqkorea.co.kr/gq/2022/10/style_634e7c680d723.jpg',
'너무 이쁘다...',
0,
0,
sysdate
);

alter table review add re_date date not null;

delete review;

--플레이리스트

create table playlist(
	pl_id number(10) primary key,
	pl_name varchar2(20 char) not null,
	pl_view number(10) not null,
	pl_like number(10) not null,
	pl_date date not null
);

insert into playlist values(
1,
'admin의 플레이리스트',
0,
0
);

--컬럼 추가
ALTER TABLE playlist ADD pl_date date ;

--추가된 컬럼 내용 업데이트
update playlist set pl_date = sysdate where pl_id=1;

--플레이리스트 pk 
create sequence playlist_seq;
--플레이리스트 가데이터 입력
insert into playlist values(playlist_seq.nextval,'admin9의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin8의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin7의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin6의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin7의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin10의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin11의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin12의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin13의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin14의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin15의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'admin16의 플레이리스트',0,0,sysdate);
insert into playlist values(playlist_seq.nextval,'test의 플레이리스트',0,0,sysdate);



create table playlist_music(
	pm_id number(10) primary key,
	pm_pl_id number(10) not null,
	pm_mu_id number(10) not null
);

insert into playlist_music values(
1,
1,
1
);

insert into playlist_music values(
2,
1,
2
);

insert into playlist_music values(
3,
1,
3
);




-- admin의 플레이리스트 조회 (이미지, 노래제목, 가수명)
select al_img, mu_name, ar_name
from playlist, playlist_music, album, music, artist
where pm_pl_id = pl_id
and pm_mu_id = mu_id
and mu_ar_id = ar_id
and mu_al_id = al_id
--and pl_id = 1;


-- 플레이리스트 등록을 위한 모든 노래 정보 조회 (이미지, 노래제목, 가수명)
select al_img, mu_name, ar_name from album, music, artist where mu_ar_id = mu_id
and al_ar_id = ar_id
and mu_al_id = al_id;





--댓글

create comment {
	co_id number(10) primary key,
	co_au_id number(10) not null,
	co_re_id number(10) not null,
	co_text varchar2(500 char) not null
}



insert into artist values(
1,
'뉴진스',
'https://img.gqkorea.co.kr/gq/2022/10/style_634e7c680d723.jpg'
);

insert into artist values(
2,
'르세라핌',
'https://file.mk.co.kr/meet/neds/2022/04/image_readtop_2022_329493_16498081425007863.jpg'
);
insert into artist values(
3,
'트와이스',
'https://cdnimg.melon.co.kr/cm2/artistcrop/images/009/05/701/905701_20220817134029_500.jpg'
);
insert into artist values(
4,
'test1',
'https://cdnimg.melon.co.kr/cm2/artistcrop/images/009/05/701/905701_20220817134029_500.jpg'
);
insert into artist values(
5,
'test2',
'https://cdnimg.melon.co.kr/cm2/artistcrop/images/009/05/701/905701_20220817134029_500.jpg'
);
insert into artist values(
6,
'test3',
'https://cdnimg.melon.co.kr/cm2/artistcrop/images/009/05/701/905701_20220817134029_500.jpg'
);
insert into artist values(
7,
'test4',
'https://cdnimg.melon.co.kr/cm2/artistcrop/images/009/05/701/905701_20220817134029_500.jpg'
);