package com.otmanel.jpaTablePerClass.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.LazyGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Personne {
	//@GeneratedValue(strategy=GenerationType.IDENTITY)on enleve cela pour que sa marche et ds jpa test a ns de gerer les id
	//@Id @Column(length=36) 	private String id; version  guid
	//@GeneratedValue(strategy=GenerationType.AUTO) par defaut equivaut a sequence
	@TableGenerator(name="ma_sequence", 
			table="mes_sequences",
			pkColumnName="nom_sequence",
			valueColumnName="valeur_courante",
			pkColumnValue="sequence_personne",
			initialValue=0,
			allocationSize=1000
			/*schema="create table mes_sequences ("
				      +" nom_sequence varchar(64) not null,"
				      +"  valeur_courante bigint,"
				       +" primary key (nom_sequence)"
				       +") engine=InnoDB"*/
	)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="ma_sequence")
	@Id 				 	private int id;
							private String nom;
							private String prneom;
}
