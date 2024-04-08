INSERT INTO o100(ownerGovId, ownerName, ownerNIF, ownerCellNumber, ownerEmail)
VALUES(123, "Jose Carlos", 515233112, "910903012", "jc@iol.pt"),
(555, "Pedro Matos", 512333111, "960939212", "pm@bc.com"),
(1000, "Jonas Sabimbi", 555111222, "980901232", "jonas@ag.ag");

INSERT INTO b100(bovineCode, bovineBreed, bovineColor, bovineGender, bovineBirthDate, bovineStatus, lastKnownOwnerId)
VALUES 
("PT1", "ANGUS", "PRETO", "FEMEA", '2015-02-12', "VIVO", 1),
("PT2", "CRUZADO ANGUS", "PRETO", "FEMEA", '2014-01-12', "VIVO", 1),
("PT3", "ANGUS", "PRETO", "MACHO", '2002-11-18', "VIVO", 1),
("PT4", "CRUZADO ANGUS", "PRETO", "MACHO", '2018-02-11', "VIVO", 1);

INSERT INTO b100(bovineCode, bovineBreed, bovineColor, bovineGender, bovineBirthDate, bovineStatus, mothersCode, fathersCode, lastKnownOwnerNIF)
VALUES 
("PT50", "CRUZADO ANGUS", "PRETO", "FEMEA", '2020-02-12', "VIVO", "PT1", "PT3", 515233112),
("PT60", "ANGUS", "PRETO", "MACHO", '2021-06-02', "VIVO", "PT2", "PT3", 515233112),
("PT70", "ANGUS", "CINZENTO", "FEMEA", '2022-02-12', "VIVO", "PT1", "PT3", 515233112),
("PT88", "ANGUS", "VERMELHO", "MACHO", '2021-06-19', "VIVO", "PT1", "PT4", 515233112),
("PT99", "ANGUS", "PRETO", "FEMEA", '2020-02-12', "VIVO", "PT2", "PT4", 515233112);
