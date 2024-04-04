INSERT INTO o100(owner_gov_id, owner_name, owner_nif, owner_cell_number, owner_email)
VALUES(123, "Jose Carlos", 515233112, "910903012", "jc@iol.pt"),
(555, "Pedro Matos", 512333111, "960939212", "pm@bc.com"),
(1000, "Jonas Sabimbi", 555111222, "980901232", "jonas@ag.ag");

INSERT INTO b100(bovine_prefix, bovine_code, breed, color, gender, birth_date, bovine_status, last_known_owner_id)
VALUES 
("PT", 1, "ANGUS", "PRETO", "FEMEA", '2015-02-12', "VIVO", 1),
("PT", 2, "CRUZADO ANGUS", "PRETO", "FEMEA", '2014-01-12', "VIVO", 1),
("PT", 3, "ANGUS", "PRETO", "MACHO", '2002-11-18', "VIVO", 1),
("PT", 4, "CRUZADO ANGUS", "PRETO", "MACHO", '2018-02-11', "VIVO", 1);

INSERT INTO b100(bovine_prefix, bovine_code, breed, color, gender, birth_date, bovine_status, mothers_code, fathers_code, last_known_owner_id)
VALUES 
("PT", 50, "CRUZADO ANGUS", "PRETO", "FEMEA", '2020-02-12', "VIVO", 1, 3, 1),
("PT", 60, "ANGUS", "PRETO", "MACHO", '2021-06-02', "VIVO", 2, 3, 1),
("PT", 70, "ANGUS", "CINZENTO", "FEMEA", '2022-02-12', "VIVO", 1, 3, 1),
("PT", 88, "ANGUS", "VERMELHO", "MACHO", '2021-06-19', "VIVO", 1, 4, 1),
("PT", 99, "ANGUS", "PRETO", "FEMEA", '2020-02-12', "VIVO", 2, 4, 1);
