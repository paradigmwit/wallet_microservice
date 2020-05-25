CREATE USER dev_leovegas;
CREATE DATABASE leovegas;
GRANT ALL PRIVILEGES ON DATABASE leovegas TO dev_leovegas;


-- SEQUENCE: public.wallet_wallet_id_seq

-- DROP SEQUENCE public.wallet_wallet_id_seq;

CREATE SEQUENCE public.wallet_wallet_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.wallet_wallet_id_seq
    OWNER TO dev_leovegas;

-- SEQUENCE: public.player_player_id_seq

-- DROP SEQUENCE public.player_player_id_seq;

CREATE SEQUENCE public.player_player_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.player_player_id_seq
    OWNER TO dev_leovegas;


-- Table: public.wallet

-- DROP TABLE public.wallet;

CREATE TABLE public.wallet
(
    wallet_id bigint NOT NULL DEFAULT nextval('wallet_wallet_id_seq'::regclass),
    balance double precision,
    currency_code character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT wallet_pkey PRIMARY KEY (wallet_id)
)

TABLESPACE pg_default;

ALTER TABLE public.wallet
    OWNER to dev_leovegas;


CREATE TABLE public.player
(
    player_id bigint NOT NULL,
    player_name character varying(255) COLLATE pg_catalog."default",
    wallet_id bigint,
    CONSTRAINT player_pkey PRIMARY KEY (player_id),
    CONSTRAINT fk_wallet FOREIGN KEY (wallet_id)
        REFERENCES public.wallet (wallet_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.player
    OWNER to dev_leovegas;


-- Table: public.transactions

-- DROP TABLE public.transactions;

CREATE TABLE public.transactions
(
    transaction_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    amount bigint,
    currency_code character varying(255) COLLATE pg_catalog."default",
    transaction_date timestamp without time zone,
    transaction_type character varying(255) COLLATE pg_catalog."default",
    player_id bigint,
    CONSTRAINT transactions_pkey PRIMARY KEY (transaction_id),
    CONSTRAINT fk2dsix5w1yprp8ab1wehyc7e64 FOREIGN KEY (player_id)
        REFERENCES public.player (player_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.transactions
    OWNER to dev_leovegas;


--insert statements here