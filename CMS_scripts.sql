INSERT INTO `CMS`.`user` (`id`,`created_by`, `created_date`,`last_modified_by`, `last_modified_date`,`enabled`, `firstname`,
`lastame`, `last_password_reset_date`, `password`, `status`, `email`, `username`)
VALUES (1, 'Admin',CURRENT_TIMESTAMP,'Admin',CURRENT_TIMESTAMP,1, 'siva', 'kumar', CURRENT_TIMESTAMP, 'test', 'ACTIVE', 'admin@gmail.com', 'admin');

INSERT INTO cms.Authority (authority_id, authority_name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO cms.user_authorities(user_id, authority_id)
VALUES (1, 1);



