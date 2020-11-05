INSERT INTO uni.rooms (id_room, name_room ) values ('026621cc-73a6-40ba-8ea7-86628f4cb802', 'room 1')
INSERT INTO uni.rooms (id_room, name_room ) values ('bb5c2e8a-b2b1-4442-ad88-ab9904385ea0', 'room 2')
INSERT INTO uni.subjects (id_subject, name_subject ) values ('c4320591-215a-4b11-958e-9864c184ec2a', 'Maths')
INSERT INTO uni.subjects (id_subject, name_subject ) values ('78c98984-bcfc-42f5-b988-3b2a33806756', 'Art')
INSERT INTO uni.groups (id_group, name_group ) values ('0c149265-57c0-4942-a1e5-06c8b6983a23', 'gr-1')
INSERT INTO uni.groups (id_group, name_group ) values ('012c2fe7-e87a-47fa-bad2-29c96369bd9e', 'gr-2')
INSERT INTO uni.time_slots (id_time_slot, serial_number, start_time, finish_time ) values ('d789bb56-b534-402b-8baa-38547218761c', 1, '08:00:00', '09:30:00')
INSERT INTO uni.time_slots (id_time_slot, serial_number, start_time, finish_time ) values ('f56a66ef-7b9f-4d2d-91b1-e1fb58cddd91', 2, '09:50:00', '11:20:00')
INSERT INTO uni.periods (id_period, start_date, finish_date ) values ('a3158159-e04d-4cfd-99dc-f12e24a57824', '2019-01-01', '2019-12-31')
INSERT INTO uni.periods (id_period, start_date, finish_date ) values ('d7dd32c0-017b-4561-bbb9-acd81d47dd5a', '2020-01-01', '2020-12-31')
INSERT INTO uni.persons (id_person, first_name, last_name) values ('69c97b95-8ce5-4923-8d39-4c17bd5389d0', 'Yashwant', 'Chavan')
INSERT INTO uni.persons (id_person, first_name, last_name) values ('f1ae8915-0fc8-405f-b8bd-508c5dc4890f', 'Mahesh', 'Patil')
INSERT INTO uni.persons (id_person, first_name, last_name) values ('eb144b62-3d34-4187-a162-73f0e9ef5b68', 'Vishal', 'Naik')
INSERT INTO uni.teachers (id_person, degree, department, permanent, salary ) values ('69c97b95-8ce5-4923-8d39-4c17bd5389d0', 'PROFESSOR', 'ARCHITECTURE', true, 1000)
INSERT INTO uni.teachers (id_person, degree, department, permanent, salary ) values ('f1ae8915-0fc8-405f-b8bd-508c5dc4890f', 'DOCENT', 'INFORMATICS', true, 10)
INSERT INTO uni.teachers (id_person, degree, department, permanent, salary ) values ('eb144b62-3d34-4187-a162-73f0e9ef5b68', 'PROFESSOR', 'INFORMATICS', false, 5)
INSERT INTO uni.persons (id_person, first_name, last_name) values ('f17e5b3a-5963-4098-a2ff-26b497701e70', 'Nata', 'Svitlychna')
INSERT INTO uni.persons (id_person, first_name, last_name) values ('b68266d6-3494-4909-b8df-807d40299ff7', 'Katja', 'Loza')
INSERT INTO uni.persons (id_person, first_name, last_name) values ('f3b78e81-c346-4f9a-a7b2-4acfd74a3a74', 'Nina', 'Ivanov')
INSERT INTO uni.students ( id_person, study_status, start_of_study, citizenship , grants , id_group ) values ('f17e5b3a-5963-4098-a2ff-26b497701e70', 'ACCEPTED', '2019-01-01', 'Ukraine', 100, '0c149265-57c0-4942-a1e5-06c8b6983a23')
INSERT INTO uni.students ( id_person, study_status, start_of_study, citizenship , grants , id_group ) values ('b68266d6-3494-4909-b8df-807d40299ff7', 'FINISHED', '2019-01-01', 'Russia', 100, '0c149265-57c0-4942-a1e5-06c8b6983a23')
INSERT INTO uni.students ( id_person, study_status, start_of_study, citizenship , grants , id_group ) values ('f3b78e81-c346-4f9a-a7b2-4acfd74a3a74', 'STUDYING', '2019-01-01', 'German', 88, '012c2fe7-e87a-47fa-bad2-29c96369bd9e')
INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, day_of_week, id_person) values ('5e9330ba-162f-45ea-b9de-605ae734f585', '0c149265-57c0-4942-a1e5-06c8b6983a23', 'c4320591-215a-4b11-958e-9864c184ec2a', '026621cc-73a6-40ba-8ea7-86628f4cb802', 'd789bb56-b534-402b-8baa-38547218761c', 'MONDAY' , '69c97b95-8ce5-4923-8d39-4c17bd5389d0')
INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, day_of_week, id_person) values ('5dc17867-2006-4540-aeb1-2922bee3104c', '012c2fe7-e87a-47fa-bad2-29c96369bd9e', 'c4320591-215a-4b11-958e-9864c184ec2a', '026621cc-73a6-40ba-8ea7-86628f4cb802', 'f56a66ef-7b9f-4d2d-91b1-e1fb58cddd91', 'MONDAY' , '69c97b95-8ce5-4923-8d39-4c17bd5389d0')
INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, day_of_week, id_person) values ('a8ee0b44-e269-424f-8c33-74d145aaf8d1', '0c149265-57c0-4942-a1e5-06c8b6983a23', 'c4320591-215a-4b11-958e-9864c184ec2a', '026621cc-73a6-40ba-8ea7-86628f4cb802', 'f56a66ef-7b9f-4d2d-91b1-e1fb58cddd91', 'FRIDAY' , '69c97b95-8ce5-4923-8d39-4c17bd5389d0')
INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, day_of_week, id_person) values ('92f6742f-ab90-4975-9f50-bbf4a3a99c74', '012c2fe7-e87a-47fa-bad2-29c96369bd9e', '78c98984-bcfc-42f5-b988-3b2a33806756', 'bb5c2e8a-b2b1-4442-ad88-ab9904385ea0', 'f56a66ef-7b9f-4d2d-91b1-e1fb58cddd91', 'MONDAY' , 'eb144b62-3d34-4187-a162-73f0e9ef5b68')
INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, day_of_week, id_person) values ('c38685d8-5c63-46af-8584-6001273a775a', '012c2fe7-e87a-47fa-bad2-29c96369bd9e', '78c98984-bcfc-42f5-b988-3b2a33806756', 'bb5c2e8a-b2b1-4442-ad88-ab9904385ea0', 'f56a66ef-7b9f-4d2d-91b1-e1fb58cddd91', 'WENDNESDAY' ,'eb144b62-3d34-4187-a162-73f0e9ef5b68')