INSERT INTO users(uuid, name, email, password) VALUES('965aab64-2d27-4391-ad95-3fc3736d1045', 'Aluno', 'aluno@email.com', '123456');

INSERT INTO course(uuid, name, category) VALUES('518b543f-bcbb-4480-9660-28613260f2b4', 'Spring Boot', 'Programação');
INSERT INTO course(uuid, name, category) VALUES('ff30d51a-7f1d-43e8-951a-6349d47f0e80','HTML 5', 'Front-end');

INSERT INTO topic(uuid, tittle, message, creation_date, status, author_uuid, course_uuid) VALUES('df5ea228-d2c6-46ff-96d6-169e53053196','Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 1, '965aab64-2d27-4391-ad95-3fc3736d1045', '518b543f-bcbb-4480-9660-28613260f2b4');
INSERT INTO topic(uuid, tittle, message, creation_date, status, author_uuid, course_uuid) VALUES('024034ce-6db2-4520-a87b-84fb2f070e37','Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 1, '965aab64-2d27-4391-ad95-3fc3736d1045', '518b543f-bcbb-4480-9660-28613260f2b4');
INSERT INTO topic(uuid, tittle, message, creation_date, status, author_uuid, course_uuid) VALUES('ba22cc4a-6c29-4aa0-934e-e6ac1ad51b1c','Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 1, '965aab64-2d27-4391-ad95-3fc3736d1045', 'ff30d51a-7f1d-43e8-951a-6349d47f0e80');
INSERT INTO topic(uuid, tittle, message, creation_date, status, author_uuid, course_uuid) VALUES('3532ffe9-b4a1-41c3-9273-b844f3abaa3a','Dúvida 4', 'Erro ao rodar o projeto', '2019-08-05 18:00:00', 1, '965aab64-2d27-4391-ad95-3fc3736d1045', '518b543f-bcbb-4480-9660-28613260f2b4');
INSERT INTO topic(uuid, tittle, message, creation_date, status, author_uuid, course_uuid) VALUES('25f16352-297d-4aed-afe2-c0977faf5ee6','Dúvida 5', 'Erro ao criar classe', '2019-05-09 18:00:00', 1, '965aab64-2d27-4391-ad95-3fc3736d1045', '518b543f-bcbb-4480-9660-28613260f2b4');
