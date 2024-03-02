--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-03-02 18:43:37

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16403)
-- Name: age; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.age (
    ageid integer NOT NULL,
    agename text NOT NULL
);


ALTER TABLE public.age OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16533)
-- Name: appointments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appointments (
    userid integer,
    dogid integer,
    "appointmentDate" date
);


ALTER TABLE public.appointments OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16539)
-- Name: datesbooked; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.datesbooked (
    "posterID" integer,
    "dogID" integer,
    date date,
    "userID" integer
);


ALTER TABLE public.datesbooked OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16408)
-- Name: dog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dog (
    dogname character varying(50) NOT NULL,
    adopted boolean DEFAULT false NOT NULL,
    biography character varying(1000) NOT NULL,
    imagepath character varying(1000) NOT NULL,
    ageid integer NOT NULL,
    energylevelid integer NOT NULL,
    sizeid integer NOT NULL,
    sexid integer NOT NULL,
    posterid integer NOT NULL,
    dogid integer NOT NULL
);


ALTER TABLE public.dog OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16414)
-- Name: dog_dogid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dog_dogid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.dog_dogid_seq OWNER TO postgres;

--
-- TOC entry 4951 (class 0 OID 0)
-- Dependencies: 217
-- Name: dog_dogid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dog_dogid_seq OWNED BY public.dog.dogid;


--
-- TOC entry 218 (class 1259 OID 16415)
-- Name: dogtag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dogtag (
    dogid integer,
    tagid integer
);


ALTER TABLE public.dogtag OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16418)
-- Name: energylevel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.energylevel (
    energylevelid integer NOT NULL,
    enegrylevelname text NOT NULL
);


ALTER TABLE public.energylevel OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16423)
-- Name: idealdogs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.idealdogs (
    dogid integer NOT NULL,
    ageid integer NOT NULL,
    energylevelid integer NOT NULL,
    sexid integer NOT NULL,
    sizeid integer NOT NULL,
    dogname text NOT NULL
);


ALTER TABLE public.idealdogs OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16428)
-- Name: idealdogtag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.idealdogtag (
    idealdogid integer,
    tagid integer
);


ALTER TABLE public.idealdogtag OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16431)
-- Name: poster; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.poster (
    poster_id integer NOT NULL,
    displayname character varying(255),
    score integer
);


ALTER TABLE public.poster OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16434)
-- Name: poster_poster_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.poster_poster_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.poster_poster_id_seq OWNER TO postgres;

--
-- TOC entry 4952 (class 0 OID 0)
-- Dependencies: 223
-- Name: poster_poster_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.poster_poster_id_seq OWNED BY public.poster.poster_id;


--
-- TOC entry 224 (class 1259 OID 16435)
-- Name: sex; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sex (
    sexid integer NOT NULL,
    sexname text NOT NULL
);


ALTER TABLE public.sex OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16440)
-- Name: size; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.size (
    sizeid integer NOT NULL,
    sizename text NOT NULL
);


ALTER TABLE public.size OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16445)
-- Name: tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tags (
    preference integer,
    tagname character varying(20),
    tagid integer NOT NULL
);


ALTER TABLE public.tags OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16448)
-- Name: tags_tagid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tags_tagid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tags_tagid_seq OWNER TO postgres;

--
-- TOC entry 4953 (class 0 OID 0)
-- Dependencies: 227
-- Name: tags_tagid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tags_tagid_seq OWNED BY public.tags.tagid;


--
-- TOC entry 228 (class 1259 OID 16449)
-- Name: userdogs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userdogs (
    userid integer,
    dogid integer
);


ALTER TABLE public.userdogs OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16452)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(50),
    userpassword character varying(50),
    email character varying(100),
    userid integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16455)
-- Name: users_userid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_userid_seq OWNER TO postgres;

--
-- TOC entry 4954 (class 0 OID 0)
-- Dependencies: 230
-- Name: users_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;


--
-- TOC entry 4744 (class 2604 OID 16456)
-- Name: dog dogid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dog ALTER COLUMN dogid SET DEFAULT nextval('public.dog_dogid_seq'::regclass);


--
-- TOC entry 4745 (class 2604 OID 16457)
-- Name: poster poster_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.poster ALTER COLUMN poster_id SET DEFAULT nextval('public.poster_poster_id_seq'::regclass);


--
-- TOC entry 4746 (class 2604 OID 16458)
-- Name: tags tagid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags ALTER COLUMN tagid SET DEFAULT nextval('public.tags_tagid_seq'::regclass);


--
-- TOC entry 4747 (class 2604 OID 16459)
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);


--
-- TOC entry 4928 (class 0 OID 16403)
-- Dependencies: 215
-- Data for Name: age; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.age (ageid, agename) FROM stdin;
0	Puppy 0 to 1
1	Adolescent 1 to 2
2	Adult 2 and 6
3	Mature Adult 6 to 9
4	Senior 9 +
\.


--
-- TOC entry 4944 (class 0 OID 16533)
-- Dependencies: 231
-- Data for Name: appointments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.appointments (userid, dogid, "appointmentDate") FROM stdin;
\.


--
-- TOC entry 4945 (class 0 OID 16539)
-- Dependencies: 232
-- Data for Name: datesbooked; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.datesbooked ("posterID", "dogID", date, "userID") FROM stdin;
\N	2	\N	\N
\N	2	\N	\N
1	3	2024-03-02	4
1	3	2024-03-02	4
1	3	2024-03-02	4
2	3	2024-03-02	4
\.


--
-- TOC entry 4929 (class 0 OID 16408)
-- Dependencies: 216
-- Data for Name: dog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dog (dogname, adopted, biography, imagepath, ageid, energylevelid, sizeid, sexid, posterid, dogid) FROM stdin;
Lucy	f	Intelligent and curious, Lucy always keeps an eye out for treats and enjoys learning new tricks.	file:src/guilayout/images/dog0.jpg	2	2	1	0	1	41
Daisy	f	Affectionate and playful, Daisy loves chasing butterflies and snuggling on the couch.	file:src/guilayout/images/dog5.jpg	1	1	0	1	2	42
Milo	f	Courageous and friendly, Milo is a great watchdog and enjoys being the center of attention.	file:src/guilayout/images/dog6.jpg	3	0	2	1	3	43
Luna	f	Adventurous and energetic, Luna is always up for a game of fetch or a run in the park.	file:src/guilayout/images/dog7.jpg	0	2	1	0	4	44
Rocky	f	Charming and easygoing, Rocky loves lounging on the porch and getting belly rubs.	file:src/guilayout/images/dog8.jpg	4	1	0	0	1	45
Bella	f	Growing up on the wrong side of the tracks, Bellas had to learn how to make it on the streets before learning to love.	file:src/guilayout/images/dog9.jpg	2	0	2	1	2	46
Cooper	f	Cooper is a retired helicopter pilot. He fought in Vietnam so he gets cool discounts sometimes. Full of stories from his past.	file:src/guilayout/images/dog10.jpg	1	2	1	0	3	47
Sadie	f	Silly and playful, you wouldnt be able to tell shes an ex crime syndicate leader just by looking at her.	file:src/guilayout/images/dog11.jpg	3	1	0	0	4	48
Buddy	f	Playful and friendly, Buddy loves fetching balls and going for long walks.	file:src/guilayout/images/dog1.jpg	1	0	1	0	1	37
Max	f	Max is a loyal companion who enjoys cuddling and playing in the backyard.	file:src/guilayout/images/dog2.jpg	3	2	0	1	2	38
Bailey	f	Energetic and adventurous, Bailey loves exploring new places and meeting new friends.	file:src/guilayout/images/dog3.jpg	0	1	2	0	3	39
Charlie	f	Sweet and gentle, Charlie is great with kids and enjoys relaxing in the sunshine.	file:src/guilayout/images/dog4.jpg	4	0	2	1	4	40
Charles	f	Charles is involved in some shady stuff. Do not ask questions you do not want to know the answers to.	file:src/guilayout/images/dog12.jpg	0	0	1	1	1	49
Chuckles	f	 Lazy and old Chuckles is the perfect lap dog for anyone who does not like to move.	file:src/guilayout/images/dog13.jpg	2	1	2	0	2	50
Sandy	f	 Sandy looks like she knows something you do not... but that is what makes her so loveable!	file:src/guilayout/images/dog14.jpg	4	2	0	1	3	51
Kai	f	Kai is a serious business owner. Dont mess with him, cause knows big names and he is not afraid to sue you.	file:src/guilayout/images/dog15.jpg	1	1	2	1	4	52
\.


--
-- TOC entry 4931 (class 0 OID 16415)
-- Dependencies: 218
-- Data for Name: dogtag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dogtag (dogid, tagid) FROM stdin;
1	1
1	2
1	4
1	5
1	14
2	1
2	2
2	4
2	5
2	13
3	1
3	2
3	4
3	5
3	27
4	2
4	5
4	14
4	22
4	37
5	1
5	5
5	14
5	33
5	40
6	1
6	5
6	14
6	33
6	43
7	1
7	5
7	14
7	33
7	46
8	1
8	5
8	14
8	33
8	40
9	1
9	5
9	14
9	33
9	43
10	1
10	5
10	14
10	33
10	46
11	1
11	5
11	14
11	33
11	40
12	1
12	5
12	14
12	33
12	37
13	1
13	5
13	14
13	33
13	40
14	1
14	5
14	14
14	33
14	46
15	1
15	5
15	14
15	33
15	37
16	1
16	5
16	14
16	28
16	44
2	3
2	\N
\.


--
-- TOC entry 4932 (class 0 OID 16418)
-- Dependencies: 219
-- Data for Name: energylevel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.energylevel (energylevelid, enegrylevelname) FROM stdin;
0	Lazy
1	Moderate
2	Energetic
\.


--
-- TOC entry 4933 (class 0 OID 16423)
-- Dependencies: 220
-- Data for Name: idealdogs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.idealdogs (dogid, ageid, energylevelid, sexid, sizeid, dogname) FROM stdin;
1	0	0	0	0	idealDog
2	0	0	0	0	idealDog
4	0	0	0	0	idealDog
5	0	0	0	0	idealDog
6	0	0	0	0	idealDog
\.


--
-- TOC entry 4934 (class 0 OID 16428)
-- Dependencies: 221
-- Data for Name: idealdogtag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.idealdogtag (idealdogid, tagid) FROM stdin;
\.


--
-- TOC entry 4935 (class 0 OID 16431)
-- Dependencies: 222
-- Data for Name: poster; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.poster (poster_id, displayname, score) FROM stdin;
1	doglover123	5
2	happy-dog-man	8
3	im_just_dogging_around	3
4	itsrainingdogs82	7
\.


--
-- TOC entry 4937 (class 0 OID 16435)
-- Dependencies: 224
-- Data for Name: sex; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sex (sexid, sexname) FROM stdin;
0	Male
1	Female
\.


--
-- TOC entry 4938 (class 0 OID 16440)
-- Dependencies: 225
-- Data for Name: size; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.size (sizeid, sizename) FROM stdin;
0	Small
1	Medium
2	Large
\.


--
-- TOC entry 4939 (class 0 OID 16445)
-- Dependencies: 226
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tags (preference, tagname, tagid) FROM stdin;
0	Friendly	1
0	Adventurous	2
0	Skittish	3
0	Good with Kids	4
0	Loyal	5
0	Intelligent	6
0	Aggressive	7
0	Special Needs	8
0	Couch Potato	9
0	Athletic	10
0	Hypoallergenic	11
0	Vocal	12
0	Calm	13
0	Playful	14
0	Protective	15
0	Shy	16
0	Smart	17
0	Independent	18
0	Affectionate	19
0	Aggressive	20
0	Trainable	21
0	Sociable	22
0	Quiet	23
0	Noisy	24
0	Easygoing	25
0	Anxious	26
0	Curious	27
0	Gentle	28
0	Stubborn	29
0	Well-behaved	30
0	High-maintenance	31
0	Low-maintenance	32
0	Good with other pets	33
0	Allergic	34
0	Lap dog	35
0	Water lover	36
0	Hypoallergenic	37
0	Therapy dog	38
0	Service dog	39
0	Good on a leash	40
0	Escape artist	41
0	House trained	42
0	Mellow	43
0	Frisbee enthusiast	44
0	High prey drive	45
0	Treat motivated	46
0	Vocal	47
0	Loves car rides	48
\.


--
-- TOC entry 4941 (class 0 OID 16449)
-- Dependencies: 228
-- Data for Name: userdogs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.userdogs (userid, dogid) FROM stdin;
\.


--
-- TOC entry 4942 (class 0 OID 16452)
-- Dependencies: 229
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (username, userpassword, email, userid) FROM stdin;
katya	123	\N	1
user	124	\N	2
edson	1234	\N	4
yu	yu	\N	5
hey	tester	\N	6
\.


--
-- TOC entry 4955 (class 0 OID 0)
-- Dependencies: 217
-- Name: dog_dogid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dog_dogid_seq', 52, true);


--
-- TOC entry 4956 (class 0 OID 0)
-- Dependencies: 223
-- Name: poster_poster_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.poster_poster_id_seq', 4, true);


--
-- TOC entry 4957 (class 0 OID 0)
-- Dependencies: 227
-- Name: tags_tagid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tags_tagid_seq', 48, true);


--
-- TOC entry 4958 (class 0 OID 0)
-- Dependencies: 230
-- Name: users_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_userid_seq', 6, true);


--
-- TOC entry 4749 (class 2606 OID 16461)
-- Name: age age_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.age
    ADD CONSTRAINT age_pkey PRIMARY KEY (ageid);


--
-- TOC entry 4761 (class 2606 OID 16463)
-- Name: poster displayname; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.poster
    ADD CONSTRAINT displayname UNIQUE (displayname);


--
-- TOC entry 4751 (class 2606 OID 16465)
-- Name: dog dog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dog
    ADD CONSTRAINT dog_pkey PRIMARY KEY (dogid);


--
-- TOC entry 4753 (class 2606 OID 16467)
-- Name: dogtag dogtagpair; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dogtag
    ADD CONSTRAINT dogtagpair UNIQUE (dogid, tagid);


--
-- TOC entry 4755 (class 2606 OID 16469)
-- Name: energylevel energylevel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.energylevel
    ADD CONSTRAINT energylevel_pkey PRIMARY KEY (energylevelid);


--
-- TOC entry 4757 (class 2606 OID 16471)
-- Name: idealdogs idealdogs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.idealdogs
    ADD CONSTRAINT idealdogs_pkey PRIMARY KEY (dogid);


--
-- TOC entry 4759 (class 2606 OID 16473)
-- Name: idealdogtag idealdogtagpair; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.idealdogtag
    ADD CONSTRAINT idealdogtagpair UNIQUE (idealdogid, tagid);


--
-- TOC entry 4763 (class 2606 OID 16475)
-- Name: poster poster_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.poster
    ADD CONSTRAINT poster_pkey PRIMARY KEY (poster_id);


--
-- TOC entry 4765 (class 2606 OID 16477)
-- Name: sex sex_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sex
    ADD CONSTRAINT sex_pkey PRIMARY KEY (sexid);


--
-- TOC entry 4767 (class 2606 OID 16479)
-- Name: size size_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.size
    ADD CONSTRAINT size_pkey PRIMARY KEY (sizeid);


--
-- TOC entry 4769 (class 2606 OID 16481)
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (tagid);


--
-- TOC entry 4771 (class 2606 OID 16483)
-- Name: userdogs userdogpair; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userdogs
    ADD CONSTRAINT userdogpair UNIQUE (userid, dogid);


--
-- TOC entry 4773 (class 2606 OID 16485)
-- Name: users username; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT username UNIQUE (username);


--
-- TOC entry 4775 (class 2606 OID 16487)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- TOC entry 4776 (class 2606 OID 16488)
-- Name: dog ageid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dog
    ADD CONSTRAINT ageid FOREIGN KEY (ageid) REFERENCES public.age(ageid) NOT VALID;


--
-- TOC entry 4781 (class 2606 OID 16493)
-- Name: idealdogs dogid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.idealdogs
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.users(userid) NOT VALID;


--
-- TOC entry 4777 (class 2606 OID 16498)
-- Name: dog energyid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dog
    ADD CONSTRAINT energyid FOREIGN KEY (energylevelid) REFERENCES public.energylevel(energylevelid) NOT VALID;


--
-- TOC entry 4782 (class 2606 OID 16503)
-- Name: idealdogtag idealdogid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.idealdogtag
    ADD CONSTRAINT idealdogid FOREIGN KEY (idealdogid) REFERENCES public.idealdogs(dogid) NOT VALID;


--
-- TOC entry 4778 (class 2606 OID 16508)
-- Name: dog posterid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dog
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id) NOT VALID;


--
-- TOC entry 4779 (class 2606 OID 16513)
-- Name: dog sexid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dog
    ADD CONSTRAINT sexid FOREIGN KEY (sexid) REFERENCES public.sex(sexid) NOT VALID;


--
-- TOC entry 4780 (class 2606 OID 16518)
-- Name: dog sizeid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dog
    ADD CONSTRAINT sizeid FOREIGN KEY (sizeid) REFERENCES public.size(sizeid) NOT VALID;


--
-- TOC entry 4784 (class 2606 OID 16523)
-- Name: tags tagid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tagid FOREIGN KEY (tagid) REFERENCES public.tags(tagid) NOT VALID;


--
-- TOC entry 4783 (class 2606 OID 16528)
-- Name: idealdogtag tagid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.idealdogtag
    ADD CONSTRAINT tagid FOREIGN KEY (tagid) REFERENCES public.tags(tagid);


-- Completed on 2024-03-02 18:43:37

--
-- PostgreSQL database dump complete
--

