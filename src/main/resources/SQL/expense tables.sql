/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  fatal1t
 * Created: Sep 4, 2016
 */
CREATE SEQUENCE expitemsids
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 89
  CACHE 1;

  -- Table: user_expens
DROP TABLE user_expens;

/*
    @Id
    private Long id;
    private Long userId;
    private String expName;
    private Long currId;
    //private Long expItems;
    @ManyToOne
    @JoinColumn(name = "idExp")
    private List<ExpenseItem> expItems; */
CREATE TABLE user_expens
(
  id_exp bigint NOT NULL DEFAULT nextval('expsids'::regclass),
  id_user bigint,
  expense_name character varying(100),
  expense_place character varying(100),
  expense_price double precision,
  expense_curr bigint,
  expense_cat bigint,
  expense_date timestamp without time zone,
  CONSTRAINT user_expens_pkey PRIMARY KEY (id_exp),
  CONSTRAINT user_expens_id_user_fkey FOREIGN KEY (id_user)
      REFERENCES fin_users (id_user) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT user_expens_id_curr FOREIGN KEY (expense_curr) REFERENCES currency (id_currency),
      CONSTRAINT user_expens_id_cat FOREIGN KEY (expense_cat) REFERENCES user_cats (id_cat)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_expens
  OWNER TO admin;

CREATE TABLE expens_items
(
/*
    private Long id;
    private Long itemName;
    private Double itemPrice;
    private Long itemDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Expense")
    private Expense expense;
    private Double itemWeight;
    private Double pricePerWeight;*/
	id_exp_item bigint NOT NULL DEFAULT nextval('expitemsids'),
	id_exp bigint,
	exp_item_mane varchar(200),
	exp_item_price double precision, 
	exp_item_desc varchar(500),
	exp_item_weight double precision,
	exp_item_weight_price double precision,
	CONSTRAINT expens_items_pk PRIMARY KEY (id_exp_item),
	CONSTRAINT expens_items_id_item FOREIGN KEY (id_exp) REFERENCES user_expens (id_exp)
)
