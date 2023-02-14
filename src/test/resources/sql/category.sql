INSERT INTO categorygroup (category_group_id, created_at, last_modified_at, name)
VALUES (1, '2023-02-09 07:40:05', '2023-02-09 07:40:06', '게시글');

INSERT INTO subcategory (sub_category_id, created_at, last_modified_at, name, category_group_id)
VALUES (1, '2023-02-09 07:40:44', '2023-02-09 07:40:45', '자유게시판', 1);
INSERT INTO concretecategory (concrete_category_id, created_at, last_modified_at, name,
                               sub_category_id)
VALUES (1, '2023-02-09 07:41:03', '2023-02-09 07:41:04', '컴퓨터', 1);
