ALTER TABLE JG_CONSTRUCT ADD (STARTTIME TIMESTAMP(6));
ALTER TABLE JG_CONSTRUCT ADD (REGION_ID NUMBER(19));
ALTER TABLE JG_CONSTRUCT ADD CONSTRAINT FK_O9AKYFIX59VLQJVANEQM8WON3 foreign key (REGION_ID) references JG_REGION (DBID)