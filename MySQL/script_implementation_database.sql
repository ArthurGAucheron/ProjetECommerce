insert into categories (nom_categorie,photo,description) values ('Téléphonie','','');
insert into categories (nom_categorie,photo,description) values ('Livre','','');
insert into categories (nom_categorie,photo,description) values ('Jeux vidéo','','');
insert into categories (nom_categorie,photo,description) values ('Musique','','');

select * from categories;

insert into clients(nom_client,adresse,email,tel,password_client) values ('Jean','2 rue du cul de sac 8600 Poitiers','jean@gmail.com','0549902329','jean');

select * from clients;

DELETE FROM clients where id_client=?;

insert into produits (designation,description,prix,quantite,selectionne,photo,categorie_id) values ('Iphone 6', 'Téléphone portable haute technologie',899.99,15,true,'imagetest.jpg',7);
insert into produits (designation,description,prix,quantite,selectionne,photo,categorie_id) values ('Iphone 7', 'Téléphone portable haute technologie de 2015',1000.99,10,true,'imagetest.jpg',7);
insert into produits (designation,description,prix,quantite,selectionne,photo,categorie_id) values ('Iphone 8', 'Téléphone portable haute technologie de 2016',300.99,150,true,'imagetest.jpg',7);

insert into produits (designation,description,prix,quantite,selectionne,photo,categorie_id) values ("La communauté de l'aneau", 'Tome 1 du seigneur des anneaux',10.5,15,true,'tome1.jpg',6);
insert into produits (designation,description,prix,quantite,selectionne,photo,categorie_id) values ("Les deux Tours", 'Tome 2 du seigneur des anneaux',13.5,21,true,'tome2.jpg',6);
insert into produits (designation,description,prix,quantite,selectionne,photo,categorie_id) values ("Le retour du rois", 'Tome 3 du seigneur des anneaux',15.33,21,true,'tome3.jpg',6);

select * from produits;

select id_categorie from categories where nom_categorie='Livre';

select id_client from clients where email='arthurgaucheron@hotmail.fr';

select * from panier;

select * from lignes_commandes;

insert into panier()value();

SELECT id_commande FROM commandes ORDER BY id_commande DESC;

select * from commandes where client_id=8;
select * from lignes_commandes where commande_id=19;
