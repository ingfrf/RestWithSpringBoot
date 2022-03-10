INSERT INTO `permission` (`description`) VALUES
	('ADMIN'),
	('MANAGER'),
	('COMMON_USER');

INSERT INTO `users` (`user_name`, `full_name`, `password`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`) VALUES
	('leandro', 'Leandro Costa', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', b'1', b'1', b'1', b'1'),
	('flavio', 'Flavio Costa', '$2a$16$h4yDQCYTy62R6xrtFDWONeMH3Lim4WQuU/aj8hxW.dJJoeyvtEkhK', b'1', b'1', b'1', b'1');

INSERT INTO `user_permission` (`id_user`, `id_permission`) VALUES
	(1, 1),
	(2, 1),
	(1, 2);
