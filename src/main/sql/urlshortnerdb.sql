/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  NATCRI
 * Created: 26 mar 2024
 */

CREATE SEQUENCE public.urls_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.urls
(
    id bigint NOT NULL DEFAULT nextval('public.urls_id_seq'::regclass),
    longurl text NOT NULL,
    CONSTRAINT urls_pkey PRIMARY KEY (id)
);
