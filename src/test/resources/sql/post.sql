INSERT INTO posts.post (post_id, category_group_id, coment_count, concrete_category_id, content, created_at, deleted,
                        last_modified_at, member_id, sub_category_id)
VALUES (1, 1, 2, 1, 'Java Spring 백엔드 커리큘럼 한 번 봐주세요', '2023-01-15 03:16:41', 'FALSE', null, 1, 1);

INSERT INTO posts.postimage (post_image_id, created_at, deleted, image_url, last_modified_at, post_id)
VALUES (1, '2023-02-09 07:43:15', 'FALSE',
        'https://cdn.inflearn.com/public/files/pages/da35da48-52a5-4ec6-b8d3-0389a47610ec/logo1.png',
        '2023-02-09 07:43:41', 1);

