create database e_commerce;

create table utilisateurs(id_utilisateur int  NOT NULL AUTO_INCREMENT, nom_utilisateur varchar(50), password varchar(50), actived boolean,
						  constraint pk_utilisateur primary key (id_utilisateur));

create table categories( id_categorie int NOT NULL AUTO_INCREMENT, nom_categorie varchar (50), photo varchar(100), description varchar (250),
						constraint pk_categorie primary key (id_categorie));

create table produits( id_produit int NOT NULL AUTO_INCREMENT, designation varchar(50), description varchar(250), prix double, quantite int, selectionne boolean, photo varchar(100), categorie_id int,
						constraint pk_produit primary key (id_produit),
                        constraint fk_categorie foreign key (categorie_id) references categories(id_categorie));
                        
create table clients (id_client int NOT NULL AUTO_INCREMENT, nom_client varchar(50), adresse varchar (100), email varchar(50), tel varchar(20), password_client varchar(50), 
					  constraint pk_client primary key (id_client));
                      
create table commandes(id_commande int NOT NULL AUTO_INCREMENT, date_commande date, client_id int,
						constraint pk_commande primary key (id_commande),
                        constraint fk_client foreign key (client_id) references clients(id_client));
                        
<<<<<<< HEAD
create table lignes_commandes (id_ligne_commande int NOT NULL AUTO_INCREMENT, quantite int, prix double, produit_id int, commande_id int,
							   constraint pk_ligne_commande primary key (id_ligne_commande),
                               constraint fk_commande_ligne foreign key (commande_id) references commandes(id_commande),
                               constraint fk_commande_produit foreign key (produit_id) references produits(id_produit) on delete cascade on update cascade);
                        
create table panier(id_panier int not null auto_increment, ligne_id int,
					constraint pk_panier primary key (id_panier),
                    constraint fk_panier foreign key (ligne_id) references lignes_commandes(id_ligne_commande));
                    

=======
create table panier(id_panier int not null auto_increment, 
					constraint pk_panier primary key (id_panier));
                        
create table lignes_commandes (id_ligne_commande int NOT NULL AUTO_INCREMENT, quantite int, prix double, produit_id int, commande_id int, panier_id int,
							   constraint pk_ligne_commande primary key (id_ligne_commande),
                               constraint fk_commande_ligne foreign key (commande_id) references commandes(id_commande),
                               constraint fk_commande_produit foreign key (produit_id) references produits(id_produit) on delete cascade on update cascade,
                               constraint fk_commande_panier foreign key (panier_id) references panier(id_panier));
>>>>>>> origin
