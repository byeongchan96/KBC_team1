insert  user (user_id, user_pw, email, age, gender, movie_cate, joined_at)
Values('test1', '1234', 'test1@bitc.com', 10, 'M', '', now());

insert  board (id, title, visit_cnt, user_id, category, content, warning, created_at)
values (1, '제목1', 1,  1, '공포', '내용1', '성인', now()),
       (2, '제목2', 2,  1, '공포', '내용2', '성인', now()),
       (3, '제목3', 3,  1, '코미', '내용3', '전체', now()),
       (4, '제목4', 4,  1, '힐링', '내용4', '전체', now()),
       (5, '제목5', 5,  1, '애니', '내용5', '유아', now());