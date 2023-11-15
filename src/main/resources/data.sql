insert into roles (id, name)
values (1, 'admin'),
       (2, 'user');

insert into users (id, email,is_activated, full_name, password)
values (1, 'ccasini0@hibu.com', 'Carter Casini',true, '$2a$10$OBKOGCzJu8YmLGxWYJfNlu4RlRkakWnWVNd/ZYw9BvCdbRQVFqdSW')
     , (2, 'jarmiger1@seesaa.net', 'Josey Armiger',false, '$2a$10$b7/jEOwAvfc6.wU8vvh7.eKbtK4DtvM6yddZk97VAYJ59V4SgO0du');


insert into user_roles (user_id, role_id)
values (1, 1),
       (2, 2);
