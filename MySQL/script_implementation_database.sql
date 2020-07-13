insert into categories (nom_categorie,photo,description) values ('Téléphonie','','');
insert into categories (nom_categorie,photo,description) values ('Livre','','');
insert into categories (nom_categorie,photo,description) values ('Jeux vidéo','','');
insert into categories (nom_categorie,photo,description) values ('Musique','','');

select * from categories;

insert into clients(nom_client,adresse,email,tel,password_client) values ('Jean','2 rue du cul de sac 8600 Poitiers','jean@gmail.com','0549902329','jean');

select * from clients;

DELETE FROM clients where id_client=?;