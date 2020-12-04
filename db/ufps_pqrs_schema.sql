--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.18
-- Dumped by pg_dump version 12.3

-- Started on 2020-12-03 19:17:46

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 194 (class 1259 OID 19851474)
-- Name: archivo; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.archivo (
    id integer NOT NULL,
    nombre_por_usuario character varying(255) NOT NULL,
    nombre_en_servidor character varying(255) NOT NULL,
    fecha_registro timestamp without time zone NOT NULL
);


--
-- TOC entry 193 (class 1259 OID 19851472)
-- Name: archivo_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.archivo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 193
-- Name: archivo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.archivo_id_seq OWNED BY public.archivo.id;


--
-- TOC entry 196 (class 1259 OID 19851485)
-- Name: departamento; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.departamento (
    id integer NOT NULL,
    codigo character varying(2) NOT NULL,
    nombre character varying(60) NOT NULL
);


--
-- TOC entry 195 (class 1259 OID 19851483)
-- Name: departamento_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.departamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 195
-- Name: departamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.departamento_id_seq OWNED BY public.departamento.id;


--
-- TOC entry 202 (class 1259 OID 19851531)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.funcionario (
    id integer NOT NULL,
    fk_tipo_identificacion integer NOT NULL,
    codigo_interno integer NOT NULL,
    identificacion character varying(15) NOT NULL,
    nombre_completo character varying(60) NOT NULL,
    cargo character varying(35),
    celular character varying(15),
    correo character varying(30) NOT NULL,
    contrasena character varying(150) NOT NULL,
    es_administrador boolean DEFAULT false NOT NULL,
    fecha_registro timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    fecha_ultimo_ingreso timestamp without time zone
);


--
-- TOC entry 201 (class 1259 OID 19851529)
-- Name: funcionario_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.funcionario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2242 (class 0 OID 0)
-- Dependencies: 201
-- Name: funcionario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.funcionario_id_seq OWNED BY public.funcionario.id;


--
-- TOC entry 190 (class 1259 OID 19851451)
-- Name: motivo_solicitud; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.motivo_solicitud (
    id integer NOT NULL,
    fk_tipo_solicitud integer NOT NULL,
    descripcion character varying(255) NOT NULL,
    habilitar_entrada boolean DEFAULT false NOT NULL,
    habilitado boolean DEFAULT true NOT NULL
);


--
-- TOC entry 189 (class 1259 OID 19851449)
-- Name: motivo_solicitud_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.motivo_solicitud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2243 (class 0 OID 0)
-- Dependencies: 189
-- Name: motivo_solicitud_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.motivo_solicitud_id_seq OWNED BY public.motivo_solicitud.id;


--
-- TOC entry 198 (class 1259 OID 19851493)
-- Name: municipio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.municipio (
    id integer NOT NULL,
    fk_departamento integer NOT NULL,
    codigo character varying(3) NOT NULL,
    nombre character varying(60) NOT NULL
);


--
-- TOC entry 197 (class 1259 OID 19851491)
-- Name: municipio_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.municipio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2244 (class 0 OID 0)
-- Dependencies: 197
-- Name: municipio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.municipio_id_seq OWNED BY public.municipio.id;


--
-- TOC entry 204 (class 1259 OID 19851549)
-- Name: solicitud; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.solicitud (
    id integer NOT NULL,
    fk_motivo_solicitud integer NOT NULL,
    fk_funcionario integer NOT NULL,
    fk_usuario integer NOT NULL,
    otro_motivo character varying(255),
    descripcion character varying(2000) NOT NULL,
    respuesta character varying(2000),
    respondida boolean DEFAULT false NOT NULL,
    fecha_registro timestamp without time zone NOT NULL,
    fecha_respuesta timestamp without time zone
);


--
-- TOC entry 206 (class 1259 OID 19851576)
-- Name: solicitud_archivos; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.solicitud_archivos (
    id integer NOT NULL,
    fk_solicitud integer NOT NULL,
    fk_archivo integer NOT NULL,
    es_respuesta boolean DEFAULT false NOT NULL
);


--
-- TOC entry 205 (class 1259 OID 19851574)
-- Name: solicitud_archivos_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.solicitud_archivos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 205
-- Name: solicitud_archivos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.solicitud_archivos_id_seq OWNED BY public.solicitud_archivos.id;


--
-- TOC entry 203 (class 1259 OID 19851547)
-- Name: solicitud_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.solicitud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 203
-- Name: solicitud_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.solicitud_id_seq OWNED BY public.solicitud.id;


--
-- TOC entry 192 (class 1259 OID 19851466)
-- Name: tipo_identificacion; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tipo_identificacion (
    id integer NOT NULL,
    abreviatura character varying(4) NOT NULL,
    descripcion character varying(255) NOT NULL,
    habilitado boolean NOT NULL
);


--
-- TOC entry 191 (class 1259 OID 19851464)
-- Name: tipo_identificacion_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tipo_identificacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 191
-- Name: tipo_identificacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tipo_identificacion_id_seq OWNED BY public.tipo_identificacion.id;


--
-- TOC entry 188 (class 1259 OID 19851443)
-- Name: tipo_solicitud; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tipo_solicitud (
    id integer NOT NULL,
    descripcion character varying(255) NOT NULL,
    habilitado boolean NOT NULL
);


--
-- TOC entry 187 (class 1259 OID 19851441)
-- Name: tipo_solicitud_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tipo_solicitud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2248 (class 0 OID 0)
-- Dependencies: 187
-- Name: tipo_solicitud_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tipo_solicitud_id_seq OWNED BY public.tipo_solicitud.id;


--
-- TOC entry 186 (class 1259 OID 19851435)
-- Name: tipo_usuario; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tipo_usuario (
    id integer NOT NULL,
    descripcion character varying(50) NOT NULL,
    habilitado boolean NOT NULL
);


--
-- TOC entry 185 (class 1259 OID 19851433)
-- Name: tipo_usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tipo_usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2249 (class 0 OID 0)
-- Dependencies: 185
-- Name: tipo_usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tipo_usuario_id_seq OWNED BY public.tipo_usuario.id;


--
-- TOC entry 200 (class 1259 OID 19851506)
-- Name: usuario; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    fk_tipo_usuario integer NOT NULL,
    fk_tipo_identificacion integer,
    fk_municipio integer NOT NULL,
    codigo_interno integer,
    identificacion character varying(15) NOT NULL,
    primer_nombre character varying(15) NOT NULL,
    segundo_nombre character varying(15),
    primer_apellido character varying(15) NOT NULL,
    segundo_apellido character varying(15),
    telefono_fijo character varying(7),
    celular character varying(15),
    correo character varying(30) NOT NULL,
    direccion character varying(30) NOT NULL,
    barrio character varying(30),
    fecha_registro timestamp without time zone NOT NULL
);


--
-- TOC entry 199 (class 1259 OID 19851504)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 199
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 2069 (class 2604 OID 19851477)
-- Name: archivo id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.archivo ALTER COLUMN id SET DEFAULT nextval('public.archivo_id_seq'::regclass);


--
-- TOC entry 2070 (class 2604 OID 19851488)
-- Name: departamento id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.departamento ALTER COLUMN id SET DEFAULT nextval('public.departamento_id_seq'::regclass);


--
-- TOC entry 2073 (class 2604 OID 19851534)
-- Name: funcionario id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id SET DEFAULT nextval('public.funcionario_id_seq'::regclass);


--
-- TOC entry 2065 (class 2604 OID 19851454)
-- Name: motivo_solicitud id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.motivo_solicitud ALTER COLUMN id SET DEFAULT nextval('public.motivo_solicitud_id_seq'::regclass);


--
-- TOC entry 2071 (class 2604 OID 19851496)
-- Name: municipio id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.municipio ALTER COLUMN id SET DEFAULT nextval('public.municipio_id_seq'::regclass);


--
-- TOC entry 2075 (class 2604 OID 19851552)
-- Name: solicitud id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud ALTER COLUMN id SET DEFAULT nextval('public.solicitud_id_seq'::regclass);


--
-- TOC entry 2077 (class 2604 OID 19851579)
-- Name: solicitud_archivos id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud_archivos ALTER COLUMN id SET DEFAULT nextval('public.solicitud_archivos_id_seq'::regclass);


--
-- TOC entry 2068 (class 2604 OID 19851469)
-- Name: tipo_identificacion id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_identificacion ALTER COLUMN id SET DEFAULT nextval('public.tipo_identificacion_id_seq'::regclass);


--
-- TOC entry 2064 (class 2604 OID 19851446)
-- Name: tipo_solicitud id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_solicitud ALTER COLUMN id SET DEFAULT nextval('public.tipo_solicitud_id_seq'::regclass);


--
-- TOC entry 2063 (class 2604 OID 19851438)
-- Name: tipo_usuario id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_usuario ALTER COLUMN id SET DEFAULT nextval('public.tipo_usuario_id_seq'::regclass);


--
-- TOC entry 2072 (class 2604 OID 19851509)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 2088 (class 2606 OID 19851482)
-- Name: archivo archivo_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.archivo
    ADD CONSTRAINT archivo_pk PRIMARY KEY (id);


--
-- TOC entry 2090 (class 2606 OID 19851490)
-- Name: departamento departamento_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_pk PRIMARY KEY (id);


--
-- TOC entry 2098 (class 2606 OID 19851537)
-- Name: funcionario funcionario_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pk PRIMARY KEY (id);


--
-- TOC entry 2100 (class 2606 OID 19851541)
-- Name: funcionario funcionario_uk_correo; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_uk_correo UNIQUE (correo);


--
-- TOC entry 2102 (class 2606 OID 19851539)
-- Name: funcionario funcionario_uk_identificacion; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_uk_identificacion UNIQUE (fk_tipo_identificacion, identificacion);


--
-- TOC entry 2084 (class 2606 OID 19851458)
-- Name: motivo_solicitud motivo_solicitud_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.motivo_solicitud
    ADD CONSTRAINT motivo_solicitud_pk PRIMARY KEY (id);


--
-- TOC entry 2092 (class 2606 OID 19851498)
-- Name: municipio municipio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_pk PRIMARY KEY (id);


--
-- TOC entry 2106 (class 2606 OID 19851582)
-- Name: solicitud_archivos solicitud_archivos_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud_archivos
    ADD CONSTRAINT solicitud_archivos_pk PRIMARY KEY (id);


--
-- TOC entry 2104 (class 2606 OID 19851558)
-- Name: solicitud solicitud_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_pk PRIMARY KEY (id);


--
-- TOC entry 2086 (class 2606 OID 19851471)
-- Name: tipo_identificacion tipo_identificacion_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_identificacion
    ADD CONSTRAINT tipo_identificacion_pk PRIMARY KEY (id);


--
-- TOC entry 2082 (class 2606 OID 19851448)
-- Name: tipo_solicitud tipo_solicitud_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_solicitud
    ADD CONSTRAINT tipo_solicitud_pk PRIMARY KEY (id);


--
-- TOC entry 2080 (class 2606 OID 19851440)
-- Name: tipo_usuario tipo_usuario_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_usuario
    ADD CONSTRAINT tipo_usuario_pk PRIMARY KEY (id);


--
-- TOC entry 2094 (class 2606 OID 19851511)
-- Name: usuario usuario_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (id);


--
-- TOC entry 2096 (class 2606 OID 19851513)
-- Name: usuario usuario_uk_correo; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_uk_correo UNIQUE (correo);


--
-- TOC entry 2112 (class 2606 OID 19851542)
-- Name: funcionario funcionario_fk_tipo_identificacion; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_fk_tipo_identificacion FOREIGN KEY (fk_tipo_identificacion) REFERENCES public.tipo_identificacion(id);


--
-- TOC entry 2107 (class 2606 OID 19851459)
-- Name: motivo_solicitud motivo_solicitud_fk_tipo_solicitud; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.motivo_solicitud
    ADD CONSTRAINT motivo_solicitud_fk_tipo_solicitud FOREIGN KEY (fk_tipo_solicitud) REFERENCES public.tipo_solicitud(id);


--
-- TOC entry 2108 (class 2606 OID 19851499)
-- Name: municipio municipio_fk_departamento; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_fk_departamento FOREIGN KEY (fk_departamento) REFERENCES public.departamento(id);


--
-- TOC entry 2117 (class 2606 OID 19851588)
-- Name: solicitud_archivos solicitud_archivos_fk_archivo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud_archivos
    ADD CONSTRAINT solicitud_archivos_fk_archivo FOREIGN KEY (fk_archivo) REFERENCES public.archivo(id);


--
-- TOC entry 2116 (class 2606 OID 19851583)
-- Name: solicitud_archivos solicitud_archivos_fk_solicitud; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud_archivos
    ADD CONSTRAINT solicitud_archivos_fk_solicitud FOREIGN KEY (fk_solicitud) REFERENCES public.solicitud(id);


--
-- TOC entry 2114 (class 2606 OID 19851564)
-- Name: solicitud solicitud_fk_funcionario; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_fk_funcionario FOREIGN KEY (fk_funcionario) REFERENCES public.funcionario(id);


--
-- TOC entry 2113 (class 2606 OID 19851559)
-- Name: solicitud solicitud_fk_motivo_solicitud; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_fk_motivo_solicitud FOREIGN KEY (fk_motivo_solicitud) REFERENCES public.motivo_solicitud(id);


--
-- TOC entry 2115 (class 2606 OID 19851569)
-- Name: solicitud solicitud_fk_usuario; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_fk_usuario FOREIGN KEY (fk_usuario) REFERENCES public.usuario(id);


--
-- TOC entry 2110 (class 2606 OID 19851519)
-- Name: usuario usuario_fk_municipio; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_fk_municipio FOREIGN KEY (fk_municipio) REFERENCES public.municipio(id);


--
-- TOC entry 2111 (class 2606 OID 19851524)
-- Name: usuario usuario_fk_tipo_identificacion; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_fk_tipo_identificacion FOREIGN KEY (fk_tipo_identificacion) REFERENCES public.tipo_identificacion(id);


--
-- TOC entry 2109 (class 2606 OID 19851514)
-- Name: usuario usuario_fk_tipo_usuario; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_fk_tipo_usuario FOREIGN KEY (fk_tipo_usuario) REFERENCES public.tipo_usuario(id);


-- Completed on 2020-12-03 19:17:47

--
-- PostgreSQL database dump complete
--

