PGDMP                      |           finaldb4    16.2    16.2 _    {           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            |           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            }           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ~           1262    34114    finaldb4    DATABASE     �   CREATE DATABASE finaldb4 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE finaldb4;
                postgres    false                        3079    34115 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false                       0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            �            1259    34125    age    TABLE     S   CREATE TABLE public.age (
    ageid integer NOT NULL,
    agename text NOT NULL
);
    DROP TABLE public.age;
       public         heap    postgres    false            �            1259    34130 
   attributes    TABLE     h   CREATE TABLE public.attributes (
    attributetype integer NOT NULL,
    attributename text NOT NULL
);
    DROP TABLE public.attributes;
       public         heap    postgres    false            �            1259    34135    datesbooked    TABLE     �   CREATE TABLE public.datesbooked (
    userid integer NOT NULL,
    posterid integer NOT NULL,
    dogid integer NOT NULL,
    date date NOT NULL
);
    DROP TABLE public.datesbooked;
       public         heap    postgres    false            �            1259    34138    dog    TABLE     �  CREATE TABLE public.dog (
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
    DROP TABLE public.dog;
       public         heap    postgres    false            �            1259    34144    dog_dogid_seq    SEQUENCE     �   CREATE SEQUENCE public.dog_dogid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.dog_dogid_seq;
       public          postgres    false    219            �           0    0    dog_dogid_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.dog_dogid_seq OWNED BY public.dog.dogid;
          public          postgres    false    220            �            1259    34145    dogtag    TABLE     E   CREATE TABLE public.dogtag (
    dogid integer,
    tagid integer
);
    DROP TABLE public.dogtag;
       public         heap    postgres    false            �            1259    34148    energylevel    TABLE     k   CREATE TABLE public.energylevel (
    energylevelid integer NOT NULL,
    enegrylevelname text NOT NULL
);
    DROP TABLE public.energylevel;
       public         heap    postgres    false            �            1259    34153    poster    TABLE       CREATE TABLE public.poster (
    poster_id integer NOT NULL,
    displayname character varying(255),
    score double precision,
    phone character varying(255),
    email character varying(255),
    balance double precision DEFAULT 0,
    numberofratings integer
);
    DROP TABLE public.poster;
       public         heap    postgres    false            �            1259    34159    poster_poster_id_seq    SEQUENCE     �   CREATE SEQUENCE public.poster_poster_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.poster_poster_id_seq;
       public          postgres    false    223            �           0    0    poster_poster_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.poster_poster_id_seq OWNED BY public.poster.poster_id;
          public          postgres    false    224            �            1259    34160    sex    TABLE     S   CREATE TABLE public.sex (
    sexid integer NOT NULL,
    sexname text NOT NULL
);
    DROP TABLE public.sex;
       public         heap    postgres    false            �            1259    34165    size    TABLE     V   CREATE TABLE public.size (
    sizeid integer NOT NULL,
    sizename text NOT NULL
);
    DROP TABLE public.size;
       public         heap    postgres    false            �            1259    34170    tags    TABLE     t   CREATE TABLE public.tags (
    preference integer,
    tagname character varying(20),
    tagid integer NOT NULL
);
    DROP TABLE public.tags;
       public         heap    postgres    false            �            1259    34173    tags_tagid_seq    SEQUENCE     �   CREATE SEQUENCE public.tags_tagid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.tags_tagid_seq;
       public          postgres    false    227            �           0    0    tags_tagid_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.tags_tagid_seq OWNED BY public.tags.tagid;
          public          postgres    false    228            �            1259    34174    userattributepreferences    TABLE     �   CREATE TABLE public.userattributepreferences (
    userid integer NOT NULL,
    attributetype integer NOT NULL,
    attributeid integer NOT NULL
);
 ,   DROP TABLE public.userattributepreferences;
       public         heap    postgres    false            �            1259    34177    userdogs    TABLE     H   CREATE TABLE public.userdogs (
    userid integer,
    dogid integer
);
    DROP TABLE public.userdogs;
       public         heap    postgres    false            �            1259    34180    userpasseddogs    TABLE     N   CREATE TABLE public.userpasseddogs (
    userid integer,
    dogid integer
);
 "   DROP TABLE public.userpasseddogs;
       public         heap    postgres    false            �            1259    34183    userpayments    TABLE     �   CREATE TABLE public.userpayments (
    userid integer,
    paymentamount double precision,
    daysbetweenpayment integer,
    dogid integer,
    lastpaymentdate character varying NOT NULL,
    posterid integer
);
     DROP TABLE public.userpayments;
       public         heap    postgres    false            �            1259    34188    userpostersrated    TABLE     S   CREATE TABLE public.userpostersrated (
    userid integer,
    posterid integer
);
 $   DROP TABLE public.userpostersrated;
       public         heap    postgres    false            �           0    0    TABLE userpostersrated    COMMENT     U   COMMENT ON TABLE public.userpostersrated IS 'posters that a user has rated already';
          public          postgres    false    233            �            1259    34191    users    TABLE     �   CREATE TABLE public.users (
    username character varying(50),
    userpassword character varying(50),
    userid integer NOT NULL,
    balance double precision DEFAULT 0,
    email character varying(50)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    34195    users_userid_seq    SEQUENCE     �   CREATE SEQUENCE public.users_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.users_userid_seq;
       public          postgres    false    234            �           0    0    users_userid_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;
          public          postgres    false    235            �            1259    34196    usertagpreferences    TABLE     R   CREATE TABLE public.usertagpreferences (
    userid integer,
    tagid integer
);
 &   DROP TABLE public.usertagpreferences;
       public         heap    postgres    false            �           2604    34199 	   dog dogid    DEFAULT     f   ALTER TABLE ONLY public.dog ALTER COLUMN dogid SET DEFAULT nextval('public.dog_dogid_seq'::regclass);
 8   ALTER TABLE public.dog ALTER COLUMN dogid DROP DEFAULT;
       public          postgres    false    220    219            �           2604    34200    poster poster_id    DEFAULT     t   ALTER TABLE ONLY public.poster ALTER COLUMN poster_id SET DEFAULT nextval('public.poster_poster_id_seq'::regclass);
 ?   ALTER TABLE public.poster ALTER COLUMN poster_id DROP DEFAULT;
       public          postgres    false    224    223            �           2604    34201 
   tags tagid    DEFAULT     h   ALTER TABLE ONLY public.tags ALTER COLUMN tagid SET DEFAULT nextval('public.tags_tagid_seq'::regclass);
 9   ALTER TABLE public.tags ALTER COLUMN tagid DROP DEFAULT;
       public          postgres    false    228    227            �           2604    34202    users userid    DEFAULT     l   ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);
 ;   ALTER TABLE public.users ALTER COLUMN userid DROP DEFAULT;
       public          postgres    false    235    234            d          0    34125    age 
   TABLE DATA           -   COPY public.age (ageid, agename) FROM stdin;
    public          postgres    false    216   �j       e          0    34130 
   attributes 
   TABLE DATA           B   COPY public.attributes (attributetype, attributename) FROM stdin;
    public          postgres    false    217   6k       f          0    34135    datesbooked 
   TABLE DATA           D   COPY public.datesbooked (userid, posterid, dogid, date) FROM stdin;
    public          postgres    false    218   tk       g          0    34138    dog 
   TABLE DATA           {   COPY public.dog (dogname, adopted, biography, imagepath, ageid, energylevelid, sizeid, sexid, posterid, dogid) FROM stdin;
    public          postgres    false    219   �k       i          0    34145    dogtag 
   TABLE DATA           .   COPY public.dogtag (dogid, tagid) FROM stdin;
    public          postgres    false    221   3�       j          0    34148    energylevel 
   TABLE DATA           E   COPY public.energylevel (energylevelid, enegrylevelname) FROM stdin;
    public          postgres    false    222   �m      k          0    34153    poster 
   TABLE DATA           g   COPY public.poster (poster_id, displayname, score, phone, email, balance, numberofratings) FROM stdin;
    public          postgres    false    223   �m      m          0    34160    sex 
   TABLE DATA           -   COPY public.sex (sexid, sexname) FROM stdin;
    public          postgres    false    225   Es      n          0    34165    size 
   TABLE DATA           0   COPY public.size (sizeid, sizename) FROM stdin;
    public          postgres    false    226   os      o          0    34170    tags 
   TABLE DATA           :   COPY public.tags (preference, tagname, tagid) FROM stdin;
    public          postgres    false    227   �s      q          0    34174    userattributepreferences 
   TABLE DATA           V   COPY public.userattributepreferences (userid, attributetype, attributeid) FROM stdin;
    public          postgres    false    229   �u      r          0    34177    userdogs 
   TABLE DATA           1   COPY public.userdogs (userid, dogid) FROM stdin;
    public          postgres    false    230   4v      s          0    34180    userpasseddogs 
   TABLE DATA           7   COPY public.userpasseddogs (userid, dogid) FROM stdin;
    public          postgres    false    231   \v      t          0    34183    userpayments 
   TABLE DATA           s   COPY public.userpayments (userid, paymentamount, daysbetweenpayment, dogid, lastpaymentdate, posterid) FROM stdin;
    public          postgres    false    232   yv      u          0    34188    userpostersrated 
   TABLE DATA           <   COPY public.userpostersrated (userid, posterid) FROM stdin;
    public          postgres    false    233   �v      v          0    34191    users 
   TABLE DATA           O   COPY public.users (username, userpassword, userid, balance, email) FROM stdin;
    public          postgres    false    234   �v      x          0    34196    usertagpreferences 
   TABLE DATA           ;   COPY public.usertagpreferences (userid, tagid) FROM stdin;
    public          postgres    false    236   �v      �           0    0    dog_dogid_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.dog_dogid_seq', 1000, true);
          public          postgres    false    220            �           0    0    poster_poster_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.poster_poster_id_seq', 1, false);
          public          postgres    false    224            �           0    0    tags_tagid_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.tags_tagid_seq', 48, true);
          public          postgres    false    228            �           0    0    users_userid_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.users_userid_seq', 1, true);
          public          postgres    false    235            �           2606    34204    age age_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.age
    ADD CONSTRAINT age_pkey PRIMARY KEY (ageid);
 6   ALTER TABLE ONLY public.age DROP CONSTRAINT age_pkey;
       public            postgres    false    216            �           2606    34206    attributes attributes_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.attributes
    ADD CONSTRAINT attributes_pkey PRIMARY KEY (attributetype);
 D   ALTER TABLE ONLY public.attributes DROP CONSTRAINT attributes_pkey;
       public            postgres    false    217            �           2606    34208    poster displayname 
   CONSTRAINT     T   ALTER TABLE ONLY public.poster
    ADD CONSTRAINT displayname UNIQUE (displayname);
 <   ALTER TABLE ONLY public.poster DROP CONSTRAINT displayname;
       public            postgres    false    223            �           2606    34210    dog dog_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT dog_pkey PRIMARY KEY (dogid);
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT dog_pkey;
       public            postgres    false    219            �           2606    34212    datesbooked dogdatepairs 
   CONSTRAINT     d   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT dogdatepairs UNIQUE (posterid, dogid, date);
 B   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT dogdatepairs;
       public            postgres    false    218    218    218            �           2606    34214    dogtag dogtagpair 
   CONSTRAINT     T   ALTER TABLE ONLY public.dogtag
    ADD CONSTRAINT dogtagpair UNIQUE (dogid, tagid);
 ;   ALTER TABLE ONLY public.dogtag DROP CONSTRAINT dogtagpair;
       public            postgres    false    221    221            �           2606    34216    userpayments doguserpaymentpair 
   CONSTRAINT     c   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT doguserpaymentpair UNIQUE (userid, dogid);
 I   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT doguserpaymentpair;
       public            postgres    false    232    232            �           2606    34218    energylevel energylevel_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.energylevel
    ADD CONSTRAINT energylevel_pkey PRIMARY KEY (energylevelid);
 F   ALTER TABLE ONLY public.energylevel DROP CONSTRAINT energylevel_pkey;
       public            postgres    false    222            �           2606    34220    poster poster_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.poster
    ADD CONSTRAINT poster_pkey PRIMARY KEY (poster_id);
 <   ALTER TABLE ONLY public.poster DROP CONSTRAINT poster_pkey;
       public            postgres    false    223            �           2606    34222    sex sex_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.sex
    ADD CONSTRAINT sex_pkey PRIMARY KEY (sexid);
 6   ALTER TABLE ONLY public.sex DROP CONSTRAINT sex_pkey;
       public            postgres    false    225            �           2606    34224    size size_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.size
    ADD CONSTRAINT size_pkey PRIMARY KEY (sizeid);
 8   ALTER TABLE ONLY public.size DROP CONSTRAINT size_pkey;
       public            postgres    false    226            �           2606    34226    tags tags_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (tagid);
 8   ALTER TABLE ONLY public.tags DROP CONSTRAINT tags_pkey;
       public            postgres    false    227            �           2606    34228    userpasseddogs user dog pair 
   CONSTRAINT     z   ALTER TABLE ONLY public.userpasseddogs
    ADD CONSTRAINT "user dog pair" UNIQUE (userid, dogid) INCLUDE (userid, dogid);
 H   ALTER TABLE ONLY public.userpasseddogs DROP CONSTRAINT "user dog pair";
       public            postgres    false    231    231            �           2606    34230 !   userpostersrated user poster pair 
   CONSTRAINT     �   ALTER TABLE ONLY public.userpostersrated
    ADD CONSTRAINT "user poster pair" UNIQUE (userid, posterid) INCLUDE (userid, posterid);
 M   ALTER TABLE ONLY public.userpostersrated DROP CONSTRAINT "user poster pair";
       public            postgres    false    233    233            �           2606    34232 +   userattributepreferences userattributepairs 
   CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT userattributepairs UNIQUE (userid, attributetype, attributeid);
 U   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT userattributepairs;
       public            postgres    false    229    229    229            �           2606    34234    datesbooked userdatepairs 
   CONSTRAINT     m   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT userdatepairs UNIQUE (userid, posterid, dogid, date);
 C   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT userdatepairs;
       public            postgres    false    218    218    218    218            �           2606    34236    userdogs userdogpair 
   CONSTRAINT     X   ALTER TABLE ONLY public.userdogs
    ADD CONSTRAINT userdogpair UNIQUE (userid, dogid);
 >   ALTER TABLE ONLY public.userdogs DROP CONSTRAINT userdogpair;
       public            postgres    false    230    230            �           2606    34238    users username 
   CONSTRAINT     M   ALTER TABLE ONLY public.users
    ADD CONSTRAINT username UNIQUE (username);
 8   ALTER TABLE ONLY public.users DROP CONSTRAINT username;
       public            postgres    false    234            �           2606    34240    users users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    234            �           2606    34242    usertagpreferences usertagpair 
   CONSTRAINT     b   ALTER TABLE ONLY public.usertagpreferences
    ADD CONSTRAINT usertagpair UNIQUE (userid, tagid);
 H   ALTER TABLE ONLY public.usertagpreferences DROP CONSTRAINT usertagpair;
       public            postgres    false    236    236            �           2606    34243 	   dog ageid    FK CONSTRAINT     q   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT ageid FOREIGN KEY (ageid) REFERENCES public.age(ageid) NOT VALID;
 3   ALTER TABLE ONLY public.dog DROP CONSTRAINT ageid;
       public          postgres    false    4764    216    219            �           2606    34248 &   userattributepreferences attributetype    FK CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT attributetype FOREIGN KEY (attributetype) REFERENCES public.attributes(attributetype) NOT VALID;
 P   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT attributetype;
       public          postgres    false    4766    217    229            �           2606    34253    userpasseddogs dogid    FK CONSTRAINT     r   ALTER TABLE ONLY public.userpasseddogs
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid);
 >   ALTER TABLE ONLY public.userpasseddogs DROP CONSTRAINT dogid;
       public          postgres    false    219    4772    231            �           2606    34258    datesbooked dogid    FK CONSTRAINT     o   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid);
 ;   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT dogid;
       public          postgres    false    218    219    4772            �           2606    34263    userpayments dogid    FK CONSTRAINT     z   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid) NOT VALID;
 <   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT dogid;
       public          postgres    false    219    232    4772            �           2606    34268    dog energyid    FK CONSTRAINT     �   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT energyid FOREIGN KEY (energylevelid) REFERENCES public.energylevel(energylevelid) NOT VALID;
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT energyid;
       public          postgres    false    4776    219    222            �           2606    34273    dog posterid    FK CONSTRAINT     ~   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id) NOT VALID;
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT posterid;
       public          postgres    false    223    219    4780            �           2606    34278    datesbooked posterid    FK CONSTRAINT     |   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id);
 >   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT posterid;
       public          postgres    false    4780    223    218            �           2606    34283    userpayments posterid    FK CONSTRAINT     �   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id) NOT VALID;
 ?   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT posterid;
       public          postgres    false    4780    223    232            �           2606    34288 	   dog sexid    FK CONSTRAINT     q   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT sexid FOREIGN KEY (sexid) REFERENCES public.sex(sexid) NOT VALID;
 3   ALTER TABLE ONLY public.dog DROP CONSTRAINT sexid;
       public          postgres    false    219    4782    225            �           2606    34293 
   dog sizeid    FK CONSTRAINT     u   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT sizeid FOREIGN KEY (sizeid) REFERENCES public.size(sizeid) NOT VALID;
 4   ALTER TABLE ONLY public.dog DROP CONSTRAINT sizeid;
       public          postgres    false    4784    226    219            �           2606    34298 
   tags tagid    FK CONSTRAINT     s   ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tagid FOREIGN KEY (tagid) REFERENCES public.tags(tagid) NOT VALID;
 4   ALTER TABLE ONLY public.tags DROP CONSTRAINT tagid;
       public          postgres    false    227    227    4786            �           2606    34303    usertagpreferences tagid    FK CONSTRAINT     w   ALTER TABLE ONLY public.usertagpreferences
    ADD CONSTRAINT tagid FOREIGN KEY (tagid) REFERENCES public.tags(tagid);
 B   ALTER TABLE ONLY public.usertagpreferences DROP CONSTRAINT tagid;
       public          postgres    false    4786    227    236            �           2606    34308    userpasseddogs userid    FK CONSTRAINT     w   ALTER TABLE ONLY public.userpasseddogs
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid);
 ?   ALTER TABLE ONLY public.userpasseddogs DROP CONSTRAINT userid;
       public          postgres    false    4800    231    234            �           2606    34313    userattributepreferences userid    FK CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 I   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT userid;
       public          postgres    false    234    4800    229            �           2606    34318    usertagpreferences userid    FK CONSTRAINT     �   ALTER TABLE ONLY public.usertagpreferences
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 C   ALTER TABLE ONLY public.usertagpreferences DROP CONSTRAINT userid;
       public          postgres    false    234    236    4800            �           2606    34323    datesbooked userid    FK CONSTRAINT     t   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid);
 <   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT userid;
       public          postgres    false    234    4800    218            �           2606    34328    userpayments userid    FK CONSTRAINT        ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 =   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT userid;
       public          postgres    false    234    232    4800            d   Y   x�3�(-(�T0P(�W0�2�tL��I-NN�+Q0�q�JsJ��R̸�9}KJ�R �f U�\&���y��E
�
�\1z\\\ ���      e   .   x�3�LLO�2�,N��2�L�K-J��I-K��2�,άJ����� �5
�      f      x������ � �      g      x��ے�H�%���
�ی�T���_�I��RWI]u$ʹY�!��$*I�CJe=�g��;_r�7�L%/ 	He�w%IG ��/˗j�������c�8z���Ѫ}���դ�����*o�&�Q9[,��.�yY�F����������(����j	W�˦�������WE=��5���,�𦚏���s^���ŪY��;�1��b>.F F��r�,���$��3��fT7�X�?�^�T����嚾"��?�/W c9�N�r9I��c�,G�jV�pu[N������5�4�X�W9����n�;������J^���?����r�ln
�)}9*A�ѲX���ftS����\�x:�U]�|���3J7{�)(��ߓn�Z�����G����bZ������h9j���sa榼)�r��w�����c��_.���i�t�HӇ����j�����36-V����L�]���Z����r���?�	
�� _��|Y���K{b�x?���Ws��I�0���g�\�\U��e3�]��5��Y\7��f5��,��?-W�����伜�GV �O|��s],�y�w=��B�Y9}�4����X����C~wGϩ�V7�����@�,W>̂%`"����m'�������X{��� LƸ�ê��&9?�Y�CswW���~�7�jch����|L��X�'w^|YL�e�STճ
Vڲ���3x+�*8x�5�ZQ�����|yO����Zɧ���3��f��,�z��"�*�W��z	��Sg���Y���̄^��1?T�����0�Ŀ��V�uuSLK�ep��˲j���}��7}��h<m�q�`���z �af�$���V�Ie�6-a����)iFX��LCJ� `��_\E��|�9��p��/�'�{�tT��/�|�����{(Pc�n��QÅRsp7>���q>��&pO��~�}:��]0-��.�|zϺ��Њe\�Z�o[�P�T���~��P3?Q����jQ�_x������p���@�����5�UT�9�jI��~oU����1��M��|���{[�7%��e��� E���\w��^1�%�x��R���oc
38�5�o@��Q���&��FUx������"���"Q��R��U��R<���x�?Vw�ݛe�E�M�V�:W��ws�s������|�R��/*}B�Y�_N�{|(�����K�y���!`0�J:�IE.py�ʚ�sY��n���4�Gzb�p@O�y�v�α�f
Z�Y婎A���*��1��x��++�zQ.�(IX~f7��=����?SJoN���y7sǢ�hǼ�՜֓r*��K0��OiWÍ�9�������,������6�?Ç:Qڲ5M����&�g6O0���0e���l��Nyv��n�Q�\��*�����*_S�<��5{~=;Ԣ�&����4x�1���T��v
�U��t���uQT�)���䀙�æ[�RcU3�Ѽ�{��$ӑ�"�
~�}>Ju�D��j�]	�{]�L��]�u�'!�/W�tJ�Ԣ��/�$Sc>��qS�R
3_.����͑�&Kށ`#lͭ��5G�}4�s���9�?0v���?c�U?W�8I�v�l���H��6�����7k&.5<��;�5M��);�U}_�^�i���9-7)�����z���G;[�3RzlZ�iX޿�GO���9lݦPS7�G��*�#eBw};-<��0�q�,+8fy�Kx@�-���A��U�$�1p�������'j��eu���6V9����3�����I������QҺ�׶�;��?�P���jE�boj�c���3�mx�wxꌫj�J���������'��j�_XNl��i� ���S)6��+!���!�,��P�f����-z�����dL��%��M-2Lf���+e]~��C���fDH����4H�e*�q���C�W8g֔)�e��=�.-�]�R��+�T��1�p��X"�Ӥ�]���|m������)��ʱT��^=#s(�]�6&ڥY���Ӱ&M]���sg�͜Tss3ō�ώ�5��0�~T+�%/.�-���Ie��ͮD��Z�V�kCc/ܵp��$9<Ɋl��	z���̹G{6_��)� V�*z���êT6�
�~m�}<g�-���3�~��ףZ6("����(o�E�!�z�g�]�����9�m�&�>V�	Yn|aL&��7�̕w�ĺ�*x�`֡M:��Vw�,�=_��� �vU(��(�������M�٣���<9��5�;t6�T
�nd���Pr����X (�Lh]�(qY��<�q#I6Z �z?�͘ �
w��V��$����H���֭�j��}4����iz�5R���[���
U�ZC�|�.���@�ݡ�ʧE�'.��ք�Hf5i�	��]��a��l7�C/��Jd��K��D�^Ds􄯫�V�TH��Bc	&�`��d�ͥ6 ��HRc>ݡS7B���
T�1k�R����U�q��+��'��6�]���l�פڕ��o�%<�a!c��͚9�<��h_MVH���-y?N�U�Jz�*�.�`y�1��8L�'�J� ~Z��7z~�XE�&�5�i�\Ņ�"g��&�Z�׸J+\@�{�W�4�����S'"Z*0ɨX�R�:a���%� Z�?��3e���-��/�uFm��KP ��ARm��`)#��	XV`i���R�*�U�XAp��\��i���>���Ki4AK�m����<#+�F�o�����f|OR9�6m�����Q�G�������)0�K����� l��~�3�B�9OX%��R�6F[�,�zЛ�Fݴ�Ԯ�x&�o��;sJ��D��g�
>:ʛ����;�YFI�8_�7j1��}���6��sh����2�����4*���D�Uv`���W���U�ӧNؚt��
J���Se�/��8|_��~4�-<�;8n�����=�j㏢Ɉ����L���Zc|�u1�	�NA�=��;{�oe6v:�R�/(�K-��W`���]��=k�Q/�0�8�уk��@�[}hӡ��Տ�0�q��������QmN��u.�?�r��>mؾ1�������5{F�:�39��A�o*P��~��<���Wܺ5Y�5�N���=�O�f�A
8�^�� 7�/�ౠ�B���<�z���⡂B��}aĒ�RVZ8[�d���N�y����U�̬e����=�I�}��_OE��A����sF�H��B5Wl���*ȼw�Dצ
�0S(�i&J����+�,��W9�����f�};��qK�#����h�� �����;.[88ipw�a��^S���b�����C��=��<��?kh��t���y�x{va��N�b><L�|%s�����ƕl
����$��(�ޅ/U��_W�J�ķX��R�c�zΧ����b������̽�̩�X�r�(QW�+��Hz%�"��S�}~?@����}�b�ZXAɕ���;J��K[!�͢X�ë^�X+8/��bY(M��](7�>�[�0��«�k~nh�[�s>n��hdU,��G0qh1�~v��Tӄ�ٲ��m�n�	�кgp�����Q�D�a��*�l�k/g�>toI��
[�ٕ�����~�}.0�ȁ����Ac��
^e��r��SU�:��X�R���ؠ���鑿�>ab�d�-�����W���	lM#>��{ޖ3ԛ�$g�_l�񩺏̊D�l�ES�V'gTϛ��i{���{��PLi�;?��0����(�Աu}�[d���*�C������k�ׂ���jS�:��}�w0aƅA.����n'�1ޔ�g6�tV��0�
��zY��F��5Z�S��O�!��X�p7�Ѓ9� ��}����&B�hm݀���0���p�`��+�[:�R)�l˹Y|Y�z;Tzl�ѕ�l�c�C���=��L��0����]�8��Z�7��H�3/3�☩��B	�EQ�ʍG���b�;�q�Lo�j�c����'�S�OB����vt��������    a��5o���܉��MS�3���̬{"��e�ԋ|\(��ݑ���s�)5�wJ̅Wa��n���x&�?Q��m�����YP�$|{�P����o00D��(_�Dg�`���苎ד���Pe��W9� ����I��- �?�������"��o+���]�V��N��*����|}����F�ڐ�k�%SZ$�x�K���s `�խ�Zވ�/�n�;'匢�S<Z�Íh�����:�x���݂A[�֟�[Mr��<���{׌�g�^lwڕRz�-7���b�m�;%$׸��G�	��si3_� �؊$�?�[ũe���Q�<�r�bc������ʄ�U�zo�e�������l�Y:�g�ws�ߦ �:� �p�`�n�BsEW���j���ʉ[1�0��|�CU�KK�<3�: c頪uJE�֓��6?(,������8t-��]���@OߡRw��,0)d4y)���0��MI�n�~���:���:�:�=�]E��%�Q��R�-�k/�Vw�}ֱ�:�����N&�h�b��lzd>.�a�e6m���Y�c�;+ԸU���}�q>�j)`~�}ə2��z�6w��>������"��a�����	T�f�O�s"����(��]�m�+y��xK�����j��q��*ݿ8t[���0"u<[�U�Y�hK��tνVH�8�li�2�W��ڃB�I_��Lu��>�N���ѥ�V�� ��2�v�����)g{�jD��1��U�o-4|N�):>�=�3��j���iyMp��	�}~���Ihb�
«8�>�3U�BO� +���*�n�H�63�!c�,m,��?�r��ȵ�Yg�~d9���a�o�)�D;O��09��Ya�D�J��U��'v$%��Hf�J{�K�j��'B����Xn��s�b�#餸��o�`7Xi��N��B	�wB� �
	:�q]o����Ed.򇗠��آ X��]�T$a�<φ���L����߷"m;!���<��8��pt-o�/{ZgR���˫ر;�7���c~S��V���8^�g?[9�q⽩�3��!�3$����p�������"3_��q��];��7�m�T�Wq��\N���JG:��� )�<'�Q���C��ؾ*#�U�;Si�uf��s;#�ChC>Xq��_tڟ��M]r�	(�3�����ZM��^dE�c�Py�\�7s��K�'�Y�Ϻ�e��+<����X[�����Ҹ�Ә��'`1�m�:�'*1�|���q��Ѥj��r�)1�'U�N.��$���O�:�/��<����ቶ�Mr�Jy�aYo8�<��e'c��NNb�L˝ �Sk |�V� ��ipX���{��-tcܥ�i�����d&~��Pap/�C$I�} ��¤4y���-/mU9���d�'��h!8pl8��O�L�X|��}3?!adS�{W|)7 .06(1
��M��[*6�V��|�O�fi�Χ8��ى9�2�:�@�����4�s�ҺI��v��Lś��MA+o�^���P}ٻ�>s���X��̮��%#v�=<cǲb�ĺ������ݶ��ο�Z/>��ʬk�s"�m��5a[@���;Q�!�~j��	�+1,)����&2HҜL���nSߜ�Bq���G�Y��_%
#d:V%��Lh]~q(��dݝZ�S 4�ls[���b�6���n���}��ҾU��%Ph�3�"E+d�b֮�s��Σ���-�Z"MJQ0,�7yI��^�?9,f��"mB�
«Lxo����M��3�06����MsO����l�W�Ąph�gWYh#|1@^%x���@ӽq��������w/Їκ��ɫ,6l~̕�M`��V�'&���,����ĺ
/�6���p0����K����rN�l=<��[c��z+��yt,0m�UȗF�Y¾l�GA�c�B��v�f�lޔؕ��\���+x`*���08�d�7�K�*
�D*�q�!�������keY�4	�N�,��F����|����%v�,�X,|�����<*� ��G8�������C�3���`�k�j�6���:����8�J����-.������	��
���l1��p��ؒ��Il�n՜��h�/�v�dA\ɿ�_W��j�	Rf��/����Vr"b?0K�<�Kq$
����<�=�����׽������Sl>���@�����jB���xm9^d�$?��XM�P�$��#�jK�?�i}(��[D��rF��cCe�B���!�n�A�yB݊���8��(�׫��])Io�J�͈�s�ۅ���&`ێ6r�v(I {=��6�|��8E����粦�[�˺9X#G?R�Qdr��{�	j%���m��o��9<�?����d��5�wM���r��)#[tS��	x�-�S!��$�i��Ȗ$ӡ��I�=x���V.� ��Hm$Wr�[����v�[E�4���*�=Ȕ���S������Rc5y����:h���{CT�Or��H)��r|oB6��WlUQ8i�e*_���\n�,��r	4��j����K$��ӛL��`QЎ$�AA�1oS��RVљ5�W�ae�e�L�M�?����&�g7A^�%��\��yj���E�}*�T���0��}-�&�`�M�g�Od���d��ء�*vlW�v�N�=��kvRm�(��L�޻���7������(���Ebl�V��pF�(�
m��x '��@�4�[7D�"�Z15����ku��^5p��h�;�A�H��^_6������29:I3��6�ʛ
B��<��Ƿ���;<"q�f��"�� �>��V��7�;�7pT�<���a�b�W0)�����dVEj+�)�D
�%��Eyo;����U5����X��s��t*���!u#���}���h=L1ә�,*�������ҦH�587$���E���
v��
O��$�-�kV���>���V��$d�G��.��5�4ȼ_b�uN�X�l����Z���8�Ɣ��z	�oC���"��=��Y�ԯ6-��'œT�=`>��[�a�׃�C�Q}߷�(�k���'�����^o�Ncgv����I�.<u_�2)������I��J��nZ4���Q�
��_�����r#�9n�i	3�G��<��M�k�vZ.Ԝ<�L�AdY�	l_C+�V�
�K���a�����k��1L|�ao0E�qm��.�"2<�g!�'[kF��].�c�w�f\���7AkU� ���l�H����Է���e��Juu�fI	p�e�}�T  ��>Z��Щh���n�Ojb�b�0�kD%��2�}��ښ�C��������ưw���U �D�9�FV��r�rU-$B3�Q�z�
t#թs����mI 
�.7w��hm�Y1�n�g��Ҷ�*�
�CC�4��Q�e�)���
R��(2q)D��^��/�����1Ot*�p�43x0�źّ�0�yO0iRO���#%��9�\E�.}�\�����
x��*�w?_���rF�	 �#�u�ee{��8�,�)<�����q	c8"�c����I�Ҥ|�/ ����<}�2Q�Њ0�~�ǹY'��DԽ�$�i��"�<L��@�[�8v\e�NO����=��^���Z���#�V6���Sa�Fo0PK@'ƻC��BT����u�z���y�����:�&��~y-.�����M��#A�h��4D$-�6����c6��_�_UORv[T'ƺ+n�>�������pO��IM�h����L3�Z��R������v����7Y��.[k
r���ޫ!1M�Hz�g�$����hh� -m�m�G�����&�F��S����S�D�>�
�(��W Z=��$���0�Ēi���e�j@2[U��_�����ᖸ�ǼF�4|�O8��h�VG�?6�.	�}��%��(��C��+����zE��L\,���_��8A�K�0n!4�mC��v��Ûp��c��DHaoTFw`BJv�2�� -|U���yeJ    $�Ź���p�K	ڭ�%,
%d�G��i��_N�����:���Q�-(�.::�uY	P���/Ō�|1��R�f��65p;N4բ�Y �����~H�A�.���T���M��q=���iB��
�������Qz�җ0�{u��.��		w�A�d#y)�vj�S��N��9�֠J=�~W%&c"��cB�%0!Rc챞�d�;ր{7"0�d�U˺q;5>'ʺbTek�\�^��C�W����Cߙ�n#�E�y��K�y��|W����57����,I��k�N4<�h%�AQ�@�K'�
��P�F
��3֙/��Ս7�Z�jb��Y{������U�����ݪ2���$&
c�C���Z��D��81���Te�?(s�T"�lt���X&i�+����jJ~�A�^�[{b73�;�z��$��1�ɘ�����b��#D1}�%�20N	^�yS]#���芰s iח��HZS�?I��Ĭ��'�����ʥ��͔����Hl�T>C�{�*�J;���'��'��Cx �O�ԣ�ʐ/�s v�Tw���F�	�M h}����bH�$%�o��J�ayq*5��"�#p>B]�r�?TM�@��@k_U8�ZC�h/���-D�ׅ��*��߶Za�S�O�
)�IL.𢭁.:+x����`��WH�wI[K�����{~�_�}7��t�;�'�D0
.�	��&Cá�a���Rc��������Oy�||�;Xc�:�.�=*��
�q�7��[�	�h51�:�9V��[�Tz�ݪ��5Y`֚��-�,��5�2���#�j���NkBwi��� X!����u��!����4fH��v�x�fU��4x�%���`���B�Lx��{��j��P���o���&DlJy9��@8�X4|�Qb�`��od�v
v1�<�+�?�+�(6�^]�����$�/�4�l�O"͔�G"�|�e܀$�8=P:��O糗L�_�h�T�[��:XB��tEau��&�F=���ӛN�aA�x�7�������_W�\�����X�P5����6C�������)�X�a59��a=�Ϝ�߸�J}�7D:������Ē�tp���ŦQv�#3��~3���('�_���-Bs�H*'�૵�'�u�(u͚�Er�#�MM�OV{�V����l�;$(ص��rw^8p�����0?��k��Դ��f}dT�DUm 
��
�g��n� b�����Z,pc�� Zl�)��o2��K?����7�9��{��GG�h�b9��K&���9~��}�*�nY[��f��5#4�k�Jrͩ-�l9Q����}asU�
]��RU��u����^����[؃D_� �Z?��2�wP�B�b�4���x]�X�� BeA	Yk�s��s۱�G�82h��pفDzt�(ʣ�|̚װ��A�TzD6V��?`�8�y��UM{�f�	+����*p=�%"���M��9�X�$QgQK���P�!��5z(�n�<�1t�:�?D���/o�bz�N��|:����S�ᴙ!	�QOT�"N�M9~TVX���E�?X�5<��|���ËΚ0�^ذ��0t�FZrW����`V(ʠ�X2�W÷]�b�tC�9Ry�+T<k˛ۖ{U`@C�'jv��+��Ia�,�W��o阦-B�k�Ǵ��((�r��dfh�w�ޱ�*�T�3\Jc-U���ó���z$��"�^�|Q�+Hr9�iU캁H�z����1Р3uW�	�xB�К��D
����P8��X-�=�Nh-������]K84���H���D&�(8���1�~��3�;��( ���A��+/	��;�4~r ��]��f	�XW�#�ߚ@����i�ϙ�O]������=����Jz?�w��񥗋�صF7��b3��P�|�mA��Ih����f���RHǒG�5"��_Kl�蠲�N��)�0tBY�����mQ��_7M?;�+��d��D���_�����e�O�z�T�"��)�Nu-
Rc�*}�]�ڼR#�&�����t���í�%�G|}��\�lz���%��;DǬ�~h���,&?�.Ir5��ԛ\Px���k�9גfU��T������v�9u�Ւ����?ta"����o�Ox���� �X�.يS�=�E��+���N�q���f{4t���8$}���Yt���i��m���ӘǇbJ����Y�xD?�j���#�B� ���kV�il�.Z1 ��X�4"sp����
��\�s{`m�结��m��|����0�gp��r�(�S�+���R ����S��>SO|����8P-�_2�l Sӗ�C��fp81���@1�,�>�dF�5.��-.�8�ڕ��V0�!V���֝�V�a�]a�С]�l�:����t`H,_05�$ɸN=���D<�o�!�A(m�9E�;zwr LA`(���h�+�ȍ��u��sD��aPP>T�dа�N#���蹦�)����:�>�4��R����:�<���Ѫ+)G8;jm���Y���0��l8W���|�Ѓ�8ܒCv�C����vHv��fvKt��x�gޛj	f�Fb@G/+��P̚h�p{^��!,�5�� ���N[��
+SR	GW$���1q�됬E�4�bj�
���|>ϱ�_\�{�F=ˉ�W'҄��`FR��ė��O_8�d������������o�M�v�2d
�h�%ٖO���]o� ���Y� Qj� ��u{��z��]_�,ͩC�q��y��-I�;�u =�X�'�ց-���� }j�D��>����o�c~�mk7�y�%���1��pVi�7�V���>h�m`4��؍r�1��}�/�=�T���+�������KP�9O�8������{3��p�7;�L�n��sW`�>���;���B;{R���Anv+s�0``��I1�~�!+|�%�Ӂ�HX��I���$Iu3O_�q�}W��h�6�<<(����D��|�4��Kű��tU����N���t�@s9�-�}un�G����?�Š��׻Xi�����4N����qB~���s2妾~O�8)s.��3��dو}�`��`,Qw lˏC�i�%�)T���bK��'\�}��g-�X�}h��Ŋ@|S+K��|i	n���Z=	���P��zX�VwQ�Z�;E�H�}˔:��r=?KjLDcakz��%�����y��X�n��B�ŉ�/���R��0
4 �z��D8%��|o�`]I����5���Kq��6ڝTE	�9��4�e�c�usM'.���H�c>�A�Hu�$�����2a���T�����6ImB�{U9��T:;)��K�����D�}�mV��z��e�	_��ZH}�-�Yf�Лބ�H��9���{�]i쎡U���ӆz�K��������^Qu�� ,�j�+kQ�ֽ'7¬lt�G�=�mt�|�\�~���5X�$�;�j�⭴6+�
�ˇg��v�b�����3b0���x��9�t[���,1.�R��>�@���Ǣ܇A�̱�Es�АrL���b{�D&��ΐ���॑rR�fz�00�L�0��L�Ɗé��`h��I�5���"�N�����D�}�3�r�@��3�'pi�}���PA�ŀ����!R�Y�"��
�if��jz��VD��$�-p�e����ݠ2��!P���:�u8Ӫ0%p�Y�a&��+�(%�����YA
����j�Y�ۊ����n�e]���X�Xe]�&4G\���OJի��i�P�6�ne��	�e8��q�Re��&LhI8�2i����:O�L(�d��O��'M4��;�c-�A���q��5�b�w��m�m�m��{��z�rӣG�9В"*aa�y�ɓPKD��˂�D�Ķ鏝�":v\d.I�b�]>����p��9-n:Йz�M#�3K�O�f�Vi�;2�)+n��RRG
*����u	N    %� u�K_���e���s�8F�iB�j��n>�9�ty�h'l�[p�i��>���8dl"��S����#�۳F}��NRw��=KZf�a����0lN���[hR!�Ø��M^����?��<<���*��R�Ϭ̩Co�r�0�=ubi����y鷚/��W�DH���eZ@fNփJ��u>��Tv+2�
�m[zG�~���R�JS��+
����Ul$��:�}�4&n�W���޳����t[�j� ����r+�E	E:�d�\����3�aK���!��[}���+��@�x����	��>G8Fy/l�ުp��������̶t�Q�/�������Og�i����Td�Y:�M�\�]͇��B<���:lt�[֊���%�>�W6xY�?\��1?~AC'r�)E!���[�j��Ϙ'Ev(�p'�-���ҸA�M��D�=�QN
�=����2�9��m ��K�+��5,�ߢ�xI�ضvb$����*\�ak��
~�?��ѳSXBj�֢M���ᛋ�o�}8�|��؍��$�DŎ��"�	�4���-���0��}.�9S���ؙ��kP�b��GbHm0(�e\W�)7p�4���y=a�9�Nܐs�G뱁�hϻ��
N:�\��euM��zX�j[��_��;x�FR�Nd��RE\A+hZ|F+�,X�*����L��|-+#"%V���MIۑ�` �H'B:?�'RFիbN)2�m�g1%�)x��;8�
��9�Z�"���okyAһ�,F�H<�B�߳�S���/����u��ŷyQ��Y�E&����؊�����CtȝZ���q�'-��D�{S��QF�J�:3 �C�����`"0�-8d�����|:��~�Dxwu���u�D~h�O��ɝ*�;Ξ��ݪT�/���9Ea6�;j|��G�E~G��Bהq�B�0[��o�tJʖ�ǝ�O��	�/�G0�z~��eh�
�ۻ� 9������Z_�]�y�,$�G�!�g`�ի������yf���o���4[�C)J9�h�=��(ռ��,+��y*�~cM�����A�d"(y'}S���4���r����O�vb�&�D�fJSw�s3h�;YKA$l7R�Lh���P�`%�Gq���!@S82��>��{�7|���z�1z%ĥ���Bj��%En��OVF��Ǜ�fO\>�����}!�Dƪl���K���yl���T�A_��'�,�%��v�f� =�� ,���2��j.�P�"�]t�5t��*DJ�t�h��D,_�E-�7�|�+�ȶ�bbw)S��e�����t�#?�MQ��I����:���2��OT�L,�������ut�[}�#�J�,!�;���o���P_xI�C���a�ɕ�V� ���3gx�K�]$x�̘�BY.a������h�M�'쥝�Ƽ�ɋ%vpk�-|�<zlg��t�����4��2t�z�&�gVX���L����!������E�|0���2'��2W��ƺe.�F�^��Ǩ���'��G��UXBnc6��������R�?�2�PY��m����d�|o�
ϳ�G�S�2�08f��/7 �86o���W�S�	����|�u�����qt0{��=d{E���Y��Nx��㍮Vr�>05�fr�
�G�ŝ#��^/yLD�	��B�H����V��ǀZ�����S�q2jض����[�S��(�ĸXD0�z �z�M�f�Z�;���Fԛ�d6�L�,���{�P��z�U��yk�1��$L%�o����S~.�N0KB� 
�kF����|Ʌ�|�7�@9Ӝ���#����!��Mû�V!�m�G繥�^*�)��s�l���\��<�%���sV���\Ks�xb4�W�v��tj��4�R�*�J�;�/��)[AְǨҋ��(�҇9π�b���ʙ���93'��?�X�0͢]On�����:�)=�T�,�����8�~Zbrw��.�R���@��fJB�J+c��4��b������k�N7?�o͂f�Ѽ�õʡ�VR�c"T8��7��F�.R����'���%�6�8�~����"_���=�P�m���[���˄O���kZ����2��æ�E�-L�a�bq��Hr�z�:�,�^u��q� ����x�˂���u�zuk]ջ$A>�9���^���x�X�W㽠6ڇ�!-8}��,�l���o���o�����ZxJ�N��5#9���NIn"�7嗍>�4�G:�Ke�h��\/#�V�yz�%AH��V~��5z%����3=@~G��|���R>��h�v��B�kh��Z�+\N�|�[7U���f\�ώ��u��#�6r�0L [QNRO��qQ�Q����W��s��a���F�i컋�R����"`k��[����Kw�iEW�i�� Ec�$��E��c���(�A�G&t�z�W��#��koO�.>��#8_"P	F`�����$
��]yh'I�9*p.v��8w�Y���2q�LԠE��b���=�>����\����{2�_
0����n����v�Y�s]��J��4��g�`���kiIEv�`������3�讻����R�hB�;�7��
���L#�S!6z��u��fx����i즩f���k|�QS��	hd�P�׿�o�'��h^���
b|ė��Q�]��y��� s�/.��1R?f���(�<"��a���o�"��n2aj�[�U?����<������r|<�3�A��H	D�tN�56�N]��z��JbJ�u�7>	Zz =�L�cu�7�'E�2~��
}G�����qZP4^�����PL���d���v�+	��/��ڜ���趺!�2�K���j�b�G�F�u�C����ߊzc�i� Ѿʹ���lo�Ŗi_��Yچ���p�����zju�����	=_i���ƃ�M�	M����{�TϘW��J�@
G�r�uR�|�+B��ޫ�x�
�Ԁxeݽ o��s�'9!����������b=�䌉N������~4f)Q,<�h��_�ާ�ڤ��z��(馏u,�-��LI
$�6��(�_?����fV��;nF u��x5��K:�Ħ ߣҵ�
q�}������
�%X<�%�W7��Q3�PY0�.�[r7�Y_��Qc�޿���N9<�{PMq�8&&B?"��M�/a�gw	YɎ�����T��	k�~�[�q�fH��s�B��q�
���s j�;�9�,�����S�~{��ޞ":�O�ft�������KU�����YU�74xz��Ҹ�"|�G8��ُ�O��m?���!��^�|����mX���T�5�T�@d��=pk��	��?��	R���7��b�	|���ѩ�)��R��9��?�Y��?�.F7��%D6h��eFj�P�t��m5����ji;iWl@�͍r4�h}�5|!������1R2)���w	���Zl��F�#"��m��g���RF���;�F�n���" �E��?�v�`�a&ֺ����ThW�k�?��o�ܞn�P��VP\Jі�� ���6
���x!�n�9�[L�8�kV>�hJ�b�K,#�j�����4x���4"��L}lC�I}��?��.{aНu����c�b�y����}��G�v��M�*C����a�{?���b��M]�A���-��Ztž�ש.�a ������F����T�,���&03����)��Ǿ9?�(i%5cNb�z����a
����D+L��*�~��M����k��k^e	�D����G�c�WwS�5�A콂'D���e�
�/�WHS�}!�C$G�>���V��1eX�����3�/_�^�#j+����|�R�\�|=�<
$�GK�5V�;�~v���E
O�8ӫP��t�CV�:�����C��<�
hAΛ�͂�7en�s5ʅ[(��PT8�����4g@��}Q[p(}�#��5�^    �J�:���C�T�ӭ��o���Q��vh��8�Z����w���λ�`� �[�nle�R�72g�1_�(�7��a���p�
'�^��^�k�Z�q���eyzڜ�_��f��m��O��ʧ���2��Q�"ɉ��ebw��
{��6i`��J���W�[|k����&��,2�,��9�^�f���@W!�,���`����y��7�p<���x8�6ɭR/��lK��BN"�2�Uݮ���3G���B�N���xDlP,w��ɼ�VTӁ/�'�#����wl�1{2�
�X���(t;f����Չ��mr�sqwHM��7���4���"a����[�N+.�V���&��V-�1%���JХ�y>!g/�P�v_���d���s��@<��m���eQ4u[mDysE��jAt��9:AbK��r?"��r�9DXݑ�&QD5�&��g�8�.U���8ָ���05�7���sc�1Ѓ/(�ƅ2�Qi����Z,H4s{���n@mT��Y43k>���-�����%>C�#i�_Z����V7�h=��2�n��C��� !��C��D�6��P�뤥ypl ��h9�LP�&R�z��r"k�rF�ѣ@!���sTIwģ�j�c8@��vU�o��n�HU�^!�"	��,(w��wlf�h���HJ��F��J�3�_zsL3�W�au��|en�R�H�Ib@a���Fa���۸����nI~�<���:TF��8d� N�\�GGysCv�Z�NV�lx�ś/�FиC��1{uê�/թ���" �N>��U�KF�(6��-@.��;Lg�9��m��jh�$��s����VN��A�����|_x���3�J{e���/.�&msn�^H�{���"���~W�]���=��Xxo�e��<8��.@G�ЛH����i�~�>
Ǆ_С�@_wI�AZS���X~�������+�R1T�q��R��B�7�����O�+I}���7�
��dF�l�7���ඨ:��m�C����n�v"��A'��G�6vÉBt��P�N�ٍ��lTM>_��obw8��\��4R�c>��-�(xɗ�G Wj̍b{D�!Ӵ T[��)��/I�ܲ9L|�}U�8��{�Mj鑤�;��>�ɰ���L�T<s_���×E����;���P�{|�I��+�D��	��<&biJ�Z����ہ-�F�Q�L���)1e��5*ɤc�P�h������S��a(aO �pJٙ�.���'t��u�Q��<�/']�wl��Әo��q�:~�Q�fkp��U\k=��G�����ܳ�����f�9�1��6�:�9XT���:�>��4���=�x��F)��k����zD�ƾ3�\fY��ԆI�������s8�)�}��>Z3%g�C`!����r�U��R�aG�%��|�q`���������W�J���r:����9��U�kZj��f�RK��*�u=��ܥ&��+�dkG���/�������T0	 ������3�9k".�����/(NO�i��~������my,7%�z^�f�jKFJB�@��K�̃0E��~�������)���^P.})'��ʙ�������h_n�j�ՖBׅ� �㷭V8Wx�=��+�������!�W���z_���WC7���}�`%��P���f����GO^ӈ���F����F�&*ƙ��k��dE����Af��P��P��]{`U�V@%��zH�m��:i�a�蠪��:�bCE�9��/C}{��="q���6	M�l:Ѽ(�M�O�_0q����
};��7��ߺQ�b�׍24ts#i��^������]_	�X��Pt���T�<i���Z$�Tћ��3-ΗS�m(���|���x�ps�:�}�t�n��Of0[��/[,[N���=�l_��/��O:�����V�:�9,j�q� 2ˆBphf�{��o��pl8mP�NzD@�8��>��Y��a?taS�R1-c�����7���:M������+�M&���Й����G��%��j6l��7�wd	s�3p��9�~/ͨ���R���N���Y�c�P�~x=Z���g��҉P`7�}L<W�d1� }���_��-;��z"D'/K<v��΂��EڜV���˺(�ŴPh&�r��>��m�P<E�*JRr5X����m�w�s98~)3������21�px���/8&��Y��I���BG>�$}βvo5�<�
V#��P3v��Uq�����'
3��@\%�&��z��+��7��������:���a
S�Z����H����f�c=�����x�c:~�O��{5_!s:�&��%�g2� 	��,�SӍ�����F��
DP,�xRe�V�8���A����ۨc�ج�Õ���4��zli#�q����v��"7Fr��#Ŕ��v��O�g��S1�H�������׽mV�%�$O�eZT�	3fs�] DԲ�����.�lK�:S_���%���D�����ŷ��W����{�
��G��$"!��Ǿ���w��s� �)��&�DW}�(�DN}���A�&F���bC���JT@l���i���d��nK��*���v��R�pÆ���5���``~�]H�R/(k��2�	��/�	����D&�f�X��|N�|���|�Gdt��ٮ���{��YL��^�ŞU|cO����mg��M"�z������p�{Qxx���44�i/�y�y?V���j���z(`4���1TQ����:�7��HJO�^5R(��R"5q��Q`9��`���~{����o�pM�J���R(

c�(f�|gJ�n��&3.�}TH�#���ؠ-T��Uh��U]y��`����19��R���q�T��j�Z��TQ�K3��*��И��8�D8��j]��`���,RW�O����ǋ��ד�Ω�'��8ٷ1�g���$-fC�)�����^�$��㫿�@��_��	3��g�(H��
V���qu��)T�� ���,�r� &�<�ؒ�+*�H��*_.��	KHnbI�.�۶X5��`�U�*�Zއ�@T�zV������E��x����Ɯ�.h�D�����_REY$I9�$$%��g
;�A��n&0�DRE�d��\ޕ�y�]ٟ�s���olE_��d��T�����o[���5�l7v�V&ޯ�˅(�觟�'gjvY�_Gue���T5�S��:l��6y�<`ȭ ��(1�p4H��C��u�р�~4������(�4�)�Ed潚���,_����P�o�1P���	��~6�2��ig���%]O����I%�(�y�T�Ǚ���	87�he֫ǃ�y}�>60�Eƫ��.PI�ܵ���n-�ݱ[�t&��nw[�C֑e&�a�*��#�Z�
w���d���6�c�K��nv�Ƴ�'� c�]�0>_�m�o�T�V('W0L��3��*SEG\�b�O�\C�r�y���lޮ�kb'*��/L��S�L����c�{�ֺ��%��VB���L�n���N��o�I?�e�nG���=C	�vQ�#�����M	~ �M؆&c�K$tWv�r�S�T]^��fи�$L�����x��9x��}?�,��WvS$�+��1zIW�`�mA�7�M����Z�WeX!8��1��Z�l�����춨?ԉN�p<F��$�IM-&��=p�BW�d,Z�@ a�������9��|���4�m��sv��&EP=pѦH-��`P(�=`.oOK���D$����_f�˚�����40aoBԃ�}���zs"�M�?��91�F�j�@E�c��-�~}����_�8i�sl��}:Z��-K�Dci,��m}�:�`��G4x�:8Fgज!権��\�c�Q�8*��˼��qݐ9�c�'Th���f>l��7oO��f@�f����Q��LP�T�|D-ܫ��O��;������D��	Jc_�(�    (�W�6��V�C �>�7���F5Ϟ�߾hf8�Y� ��8u����_t�.�C��>\L�bƕbMW�[)�;N\E�����Q^3����Ԫj!|�7M����'k�����A��LL��Q�6	d�ӛ�ѓ�n	��&��V����]hu�د�y�>�=4�?�C��T�*�\���{��i��0�NYTr;孊b3At��s����|�^KAc�*�!�:�D���ae�l#R_����*�x@T��s�z�P}=ʃ=�����@#i�b�O2�Ӥ2�'xݗJ-����"雠p�d�Q�14{��u'�����l�����o�7Q��-�T���bc��ƨ[<Z~͎���r��#�����j�Y}��� дo��|$��Ɖ�(�����,�7EU���@�Do�M�?���]��a�í)�u����i>^�}��n��X��zV)���лcI��8<�
*��uٸ@��l��=�t�See@����iY2ɩ{������4ۡT�.�4�~/P��1Aoyj�;X#ͲqԄ��>���*������/os�g��E��f�,�l����*y<�c��N����!3��j8d�f�(a7cȫe>gv�,/}\��v�Lwj#�Z*�ƚϥU�n�\zbS��L�0m��Q�$�� &�Z�"=a�(�[1%�I�c,&Y]?��6��ݥ��|��[Gm�H
S�y�qI��.��F-�@�<�����r[n��ޔ���j�D���A3N���7m��[<�5��{s��, e^ݓa�g6�6V�6����-�̶�e��(���%���Qy�|]��8't��*��
�,44�樃��Utt��"��1~�֐�Q�,�5T�pm����[e�׾�4�HY�Q��k@���H	nH�Q��6Q�*�Ǩd�x?N4I����BωwO�7Ս>��@�0�����|��U�(�t����&m�0#s��[�ew�F���ʝj����"KC���D�����!G'Տ�Ť���߱�n�+���'P^PN��o�	$R��w]Ul-ט�SIbsc$��0nw���h�Q�����g����v>i����j�}a�y�Ac�M�F&�R%��� �� �h�w袭�����	t��R5xX&AT��-+P��|	p��SJ͸�N"�֕�3� �C^O���=I����eH�Ҳ�^�}ر��TX�&J�76���E��\��f����;H���#ƶ�a�Q�W)W��Q����Q��D��̚#�����F5V����U``D0;zG�5k������{��VL���`��@SV��y�L*6,"X`~�Fj��o��rOvAg.̙ ����K��P\�R0�Q����ʱINu�˱'�Uc+x�Е�!�Wb�-�E/���n��rl!|I@�A�[c����¶��%@jS����{_��u_
ǆCG��DE�k6D�u���G�%����1{��2���{��"D��1!k��x��$�X����I�i�l�5d�H,�n�I){Ul%[�t�D���H�~��ԇ�8�b:��7:0�-zB8E�k�$ _���iN�5_���Ԉ����?pt+��1���dt��.p����̩8�$��7�&�H�_�9F���D����m�	j��%�>|)B[�+�O |��-�^�t��n�D3'b��qr���?D���0)��/Ii�-U��Yޏ����^�����E�]����^�AHd׹�H���Z������IgR�V����%C��^�%���#��!�R�;��h�2���#
�����(@���]7<ث��"u�r|��[
s�<ۘ�o�WH�a_�p�N�w)��`-���>�-���P�<�gL�,����E7�����3n�/������X[Wk�ѵ���J����[��:s5_��A�����P.,{4��C�I����e�b�a,�����ǋa���{���pz]
esH�Q�Z�:�jV��bV�����X���c"\�Kdټk(������-���K��]��!ܶuY�_�wY�;y,*�p�N�YK�bHAFN�����	~PU��2�ޗX-/պ���L�M+�A ӂ-�U­M���1;�=-��[�*��:�ux�� #���p����\����7T"a�-�ǥ����q�ؘ̼'%1�륨S�tw���s�,h�+��}�)C��?@��pV|_���ƍ*vXc��^O�A|@�c;8��x�)vP� ��a�����G��9E�7p�0��F�q��8�T(zݐŏ�s�35Φ�ղg۳Ȳ&S���{G�8�t5�Pp����L���P��z�e[��}덠��Mz\s�H��I܇��=qI����Ƀ����c��K��hM�0�^7̂H�<�Nx����)k����D�N��4*iz�3hwC�� �����o9'0��bӢ�U����k#Xfi��J����x
��/,^�~2��b�BKaJ��̰�qA��`6�G!6}۫R��"��OE>8�\�6C�?�����H���V�|8I"<6A�J(��{%�Q`ik,S͐"�J�#C��P�ʨ��|hU�p\U�&k�z��+t����b�8%�aU�"�ЏP�"K>K�-���fn�ƌ��j��߾g6������b�����!�v(�,��w�'>v����t䆗���=� �e��o����{�#5<ߪ�HK�f��j���G���L�c�Z7Q�x|�4��E�d"s,tΓ����=A�Z$�<zJ�°���3�W�ȼ�~��6�F�.\`�W��qI�6�\@s`�rL�
S��ݷ׺\��+�?��fJM�{����E*�	M���|��4*t�i���w� <qh����U�.��d�܂{�Ԩ�+��Up&pۀ����-�F�C��Y�bZ� �=qjG��J�'6!+P�*@�ٹt��R����V���@]pfǑ�n�H��?Lo�3������C�L����d��{i�$���^iD��0n*N��p̯C hl�qH��1����k��8ERЙq��M_��I��3�O�hQ{x�g�����_!I2�IC�*v���=s�1\���k�:�f0�޿�&6	�����P݁A�~���h��`7&�����x�oá�w�F���)���HgX��܄|�s��8��9���&���W����e�cO  �$�PE�7*yp � zg@7��� �W�4�m�yzۛp���dc��{����_$���B���uy��z���T�� ��"؅�_��\t'����"�w��G!�,PG�j[s1�����I��L��.�lЖ��q�eo�G[F��[f:�q7[L��ҹЊ�wC��S�-�=����9��p�4�~/�b�fH�`��Y��*=�NZ	�S1̅4�T�5�U��E�T�����+ѓ��̉R�De�5�xN ���&�F攟9��r�F��ڈ���;���HX(�}nXM��-�ؠȁE��>O�[4� JCf�jR}��C!��~E��v^)�B�!zۗ ��$a�P�;���q�v[=��C��M)Z�.�R$�.��q�x�{�����#yC���D��w�lن����*�lG��x�Tz�8L�)N0*�����($?���$J��a�<���n$���P���5RC��A��<��,P��Cw�z��lĺhl�8���D\��<i�6�6}5��m�4��dGm�(2��F~�>U�]ob,��B�����e��;P�Y�	q� ׾��jDu�"��i��ġ#;�8=�A��5�!��.�y�eX�����56B  ]�����8��e�Y�0C����̂�����A}��
w���%#�,�q��T��T�c���� ���BrbV�ߠ��7FK��"j�O5���/�ܚ�9���w8&�@�i�)�IY�n�hX�M�?^4]����sl�ݓ�dag�i�^���v�`�!__�ӫ���Q�^m$���ɲQ�$����f�D�6U���h@��M�}B��c�Qr;�P`*�;_N���'ڡ!D�3    �j�l	��M|�}Z�f�|�։֓CE����Zw)���B�W���&.�tr	�?��B��Yt�����r�#�<�M���OywGU��: ������Y�-��1�U����zԑ��r�'s���G��C��U'~����f�B&���=$��	7���֢�k����6��߅��%��M���y3���7ul,I��8]0=d N�Jo��M�v�Mm���$Y�4�$z�e���#d�B�
 �W�0�吁�܅�~n�b�4]��Çd'�u��b.���C��9�Ί܇��؆.�nӤ��)�T�}�4��bʭ.���tשع�n�MDd{a�jFc�7�gq���h X�A�Nh*V�H�_�{��پ��L���,k7�!�,��#�D�)x���v�����=IkG
-n�u���*��A��O,=���R���K0���u��G���9��>'�0Q2>�� ���`=r�)��s ؒ�mQ��O뀸;d��E�n�Ī{��pM�5`�.�`Um�pl�a(w�$yg~�,3guȝ�� ����@�ק������3c�y&��Em��Bci���{�Cc!�M�E��gB��{S� ��zh�G�ЄQ�z<-f�Q&6��I�W�jJ�j��-�t;	��8Ru��I=�^4d��'0�ѩE˿l����B���)%,���B�6M�A��	y��-a��������y�?��(\$�լ;w��O����Z;��T��	Wv���:��uJ?�9̌eIv��(;�:��I��]:�%��v���F%�]�Nc=W�$c��6q�H�)���_�Jk*��pж�-%S�/�C[y��⥂����O����޷�,�����#�-�����%����,9�(�;��,~j���H������,�?1��9���L;鞣C���å��m�5�����i�&a[W���jwVh�Io�����Wq�:��ė��v6oVI�;zϸ�V���i�UDK��zKd�#F���jǝ�Ԣ�Xw/���y�T��u�PȺu�N�gm�=��̤f\���Q�d�3G9��(AS�"�0D�N��z��G/�˿�V�zF��TcC�}C��|wWlDyt�1B���(�p�xh&a���h	^���e;K݆�t=!�'@?�҆w\MăC~9��TQ����S�0T�bS]!��\�T�Ѷ���X���BLڜP�;f�e�^2���g#����������>�$7O�[|���0�6��Vw�i��ŒV�"G:����	p���Ò)l|����5��W:��[iA�p�t�U�l<����/L���i����2����)_� �*asw����sQ-�6��4z	��r���P=���C�咩t��oD��4�
�r��v��Z4�2{��J"����{�*B�~4{W:XP�[$���9��h���~ "��.0>��d.܈������д\�fGz�j$�/֟��w�O��<�(�>T_ֻ�P�Y�P�2K0SdEޏ�iUP�`x�S=���T���2�����OŶӉ2y���TChe[R�,J�[�[0p�����
�薥8{��&/����6'q��vL��Gc��i�a��ؠ*P�N�)�0ya��'I�eC��L2X�[�,�(lѰ�p�C��.���2��b�����;'vd���9�C� Ɓ��0u3��M��E�p��;���j�� ts8�b�l1��]\���pī�c�k8�(4�!��ʡ�<ss�?��(,;5y@w)�a@*\R��\�|Bj��@���p@c� V�a��7����,����{X����nV�D�N��]0��,�t9G�h�r��	VA�Q��U�);1� y��u��f�~߹og,�DGe�\r,p���:�S3� ��(�D^��[��襡�:"v���x�	�wa�� `a}(��Y�� ����I������up�� &�����v�_J�9Eߒ��n�G04DH�v�����̌T1�D��4����f�e[3�G���%��	-nGf.�a�D��9"b߸��n�C$�tc ��v�#���󑤭}��$6lĮR���-_bZ�+6�'i�
���88N$F�w@��YW|â%��2�6"�<z�<��!��I��ٝ�$e6Ǭ��σk��8���p-�-��}V���Wb�Rۮ_�9�B�.:���Z������j��M�̭��'��n#��0_��1�d�tmN�m�BZp,cqO��X˪���|6L��B3�X�RAT~�^��j���fJ�nK�E��:� �)�k|ϖ!]|��"j��	c�UM��J�A_�(�7@����*ۃG˨.|�y
�O�$^ÊXK>�8�Y��s�J���N�2)4s�Z�$id�����Xc�k�5��\���p�?{_�&�;�qqh�[����r�x��[�m��k�Yv�mS8�y��4�-W�N����x*V�nܕԜl�663'ç:BLu�\�Y�Tb�s�(����A��(o��v˜P��R�3���3�?�X�Ņ���<����+2��Џ��Y�ӻ���6���<9��j�*�א�m�����GΓ����v�Bݭ����uo;5QC�sT�)���m�ϗT�!�~��5�Y�x�F��W�ר��e �*��8U������%�'�5�-M��*W�y����,m�ʍl�c�����2���c�N���Y�����A�x1�ͥ�v{e�J
�er�F��}R�87|�[�u��;u��⾳�(�q���������e���P��yj�
����I���L�a��'Y_�ݍ����jm��q�"��0��_6�?n��*���, n�y�جk��=�Ob�׉��F��k;gKx�V����!@N��	.���K���*�a�N1E�Ƥq�!:˗mf۪���y�~tQ9�p�S��"Z�r�h��������E��B'�"x�"�#���<�����2m��0�b�W�yԅ��4�}�/����=mfh��ÓllQ�+돦?*��VƉ�ebq�'��g8J�J�q�bg��:<?<�s�����W��>�aBɁ�l���^
���g�n�B�r��	J<j@���i��)�:K~��<Mj'���/\M��O��o�;*��~�P4�ˎE�S�i<6m��D@��l�}�RYC��WE�
?�px�
���@{y��F���d=�wO�e�x�Bj����O��C��|Apv�hR�P���ܷ;��kS���&È�܂j=�����5��0���|���9]*�%�oף��p�"��@5E��XR\�~=��[ ��z�Y�|��\��"�-�W�]�z�&�q��R�IR���	(��ufli�V��3-�X9����y�]W�WXgB�h��)Jgb��t&#X48%h���X�߀��)�1���!�i�:R�������	WfA���@�����|�*�{o�q}�I`;�e2�fܩX��3���5����#�EO�8ʔ5R�}�V#�a�΢���H��>֒q�i�{?�j��/�1��n�X<g`��J����q<��+4
��:�'9���I�P���c0[Cs:��T��}����@d���z�U|�c��������G�0mlf7�#L���|�r������B��$��Ώ����k5w�/�'�:�c4�l���~�O����)-�O3/�Xy�?�ꄘ|u��=,����4�J�� �����Uww[\�W�k�x?�	������*�ז���(����Q`b3D���E꽆�����>�hX��R4B�s��b�m��,}{���]
�7�_�-쁼KMHH��*}�#���>E_3Mn��F���|lp���� �(9qV��c��D�~�8>�>n����[���B+�������3�)f�
w���Țǵ����0�����W���N�և��Qj��>����O�K�m���I�n�mp�ה%�O��b1�ri�
͔��R"���2T����I{g|���ĦF��O���5P*�v{����̪�ق    #�y}Y�)�Չ�D�5(�XV�牃BhuXYwmPF��RH@��E������{F�$���d���,�W�Q��p�gz�=�g��T�W�-C_Sĵ��04�p>���$��F{�����P����wB����@_ �L�����4�&Z�ojQd�>��/�$���1�&���?�<���mRY���jY���j`l����N�m\ۀ��B���.	�^�ڂ)�0�u�m�*[����8)m��c�Tu�<�MޡI��s�y���B?.L�uVˊj+���R��5�2�Ϊ��{��g��QEcC�]H����օ��x|�0�X.���!�Kz�䄶$��Wp�F���i�{�����?��Tɛ�r�2�$z����$�Y��S�Z&\�H��4+�ۧ�� �:��4�]f1Ɋ�2�B�︦�/.�VҲ���8 �6<�g��6�G����ˡ�@U` F1L��������V�Gt�lk�;�ԧ���S�"֓��s�f�ʔ^�e���4y�PD
=�s�y�4s�:|�[E��T%n34�'���o�����+[nܸ���W8�N
Kc�Ǒ]���qf�rU*/��H;���n���E Hyp��XR��}�.�C:��a�6�l/��ć�yI�	�,)����jܜ�Է)c��q3�(�) )a�R�U4�RN�V��۹+�X�����A��f��R��.K^[Cw�����������%M���E�A��By޻�#NLgB�V�Z��Ul��T25Xݟ`��Q�L��b��߃^X[V�8�Q�i�+�}j�����V��t��,���̷Q`����Û�r��"i#�Zʪ�ޱ�B	�}q�L�PA��e]���7��c�����*r�u~�$<�d5u�\�G�����?9�D!�V�F߭n؏�+�����&|�9�#�v�p�]��E?B�A��<��_��x�O�p���I���j��Ȉi��n�~M��m}�ħΥ�H��ى��\H�=�G�G6��� ���-����G�z��;X@I��=k�Ue����s�v����"B�2�[���,lv8 P�g��?����`�Lz��t��i�3��-S!��]�E�|╅�eu��j�'�w���3�ŗ:�i������<�"2��`EP�K!�������;<��:�ꕼ�	ի���-��Ì ��t��M�4�Y�/q��i�b�9U�F���_�&]V�a��B̓�]��$.~��y�V�*C�Vô8�A�y�k��EvwJ��6=�{��2�#��D�TEt�"��<R�� 8��h���*�No�'�g�"`ҩ�-�	ނ���o�d����_n�s���5��� �saȇđ�����Y,��q�w�K��)�%L_�A\�W��׳rSzw�����SCm�(��_Uuڈ���!��2�<t��#�5SEφ��@ꊤ!����]G�<�ᙕ7;>���MeR���I�s�� ���_��1�f�� �M���yn�P{ͧ*oH�8`�m�B	jؠ�Ö�Ȯ�w�Fo�8�-�@�O�4��eH���<P�D�̃7aG�ɩ4�cfC3��&y2���b�YWŶrϫ���?ߠi���A~��N@'��
;�Vя�ŮY-�g���ў�G�VA�3�:�~��Kb�9ű���c�;�F	�u_�䋣����к�`�`�PU-,�%LDi��A�8��7nL+��)���|����`xl�}O+�2;���vK�%aİZ;�2!wrļ�	ɑ,��v��J�q��ŭ]�(���F
���ty��y�t�Do�g�P��J�?����9�������S���=�&�ֳD��l�>���+��4��1it�E�s,��e�[�^��=����L�&N��z��s.'ecӄcS�l�|J�en�Xٞ$���Wb��q���ݚ�e;��;Nx�N�	Ut\F߯���K>�	Ce����q0+��q}Z�Ϙ�>�@�Vڭ}�]~�f���Kg���'��٩��<�F�1s�c+P�M���+�7>��{�z�w��m�����	��<Eo�e97�sQ֡�v1���mHJ��uOP�a���f3���}�E9���G:ɢ�`��7X1_d�߾\W��$wE�Z�:Q��x�����d��Y{�۽��s��f��|	s��$�ެ'���#�C��q^@�`g�N�Ýic$wh=�y�,�&8we�Iǚj~�j@���Ǡ��#)��
�Bp�nX^�?��zI^��R����S]��k�H����h�&F>���*m!�!�Mc������Y��VYvט�X��	xPib�t�iw*:�k��j�]G��a1K�1��� 'ዜ	Mʂ�0MO����;�ӂw�}�Wt��+���;�$���^n�.���]؆φ����s�Z���9�Jn:BC <�f�ifm�X��A�s�i�#���R��,I0%^�w
�hm�N���E ��<o��z����OV��`;"����ˤ����^���~�@��~J1=��Nl欌���|��S��Z���﯈�!���k��?p ϯ�;�˪^�9G���&� CZ|�9DB��_�,�t�$D�5����9��-����!D��/F�����{��E�,ɧ���3T*08QV��{�h\�70�CN�ɜ7I��U�:�.���p�g_@�F�?�s�;R�=����ϝ�|4��3c~��������M�W@�n������Q2v�D����ڸ6�i�,q�`���Ö��pn�9��#2��@cD���qH�J��{6U]Z*&���V��W`����iy�8:�F`��'���%e����݊��B�)|����r�����J���-|8�S�r����n���5Z 0k:�w���=S�A��.�V����L�e/�������2B���:��7R^���.�L�I���>]P
O`r��}⧠�;ɝHa��;Q,a�l�,k	��2�%>{3��<������u��P~�Z�vL���W��� fr;��gGd�J�]�%j����I���f�4[�?R���U���f&brr0�
��˽�=GA���Ur��1�$R�SI�s�p��v����`s'�e#�ǗJ!��bO/�<��A/x��MO̝�Ze�?w�f���@����O��(�d�L���r���]^���m�n G%6�ij���
%��4o�.3? ��OG�;�V��� �T��Y��F����9ߊ�Vǽ�I�X�E�4Z���S���@:x�D��/��1�Њ��ݶ����s�J��W*r[,P�Tڮ&�fF�y K���U؄A�!�c�� ���v`��ڄ�EH.�y�<�����{y}�2�0~��Ŧ�O��{s��PBnM��)�'���ͳ?��za(�&.���*�c`a��ln��� �}��mھ#S/�zm�$�����hF����f&�E"r�{�ż�y����H��nK��S����{�i�u)S�-��}߈bˑz�d���Ն�6�#M��%���� #�B�	_�4bj,��c��2z�b�17��ͫ]'������+03��E߀φ��E��9�R�+7�/���N�%���m�M�&�쮃ԜP��4� ���\|�,Z ��@x��	��R �#s�3[$~��K��(�*hD�X8����V�FW���t{U/�6��Ckô��2��vo�T��q�e�+ڈxyՂ������.'�V��F�$;ed�8.y��V�t�cs��NPi0��gٌ��M��[Bо�:Ǚ��k+�w�@ЍjԞ���h�t%r[��,�Bp�S�/��N���Ү8�~�!d�Ja ��r�`�2�T�(�0��p2�L�DhR�	���>�Y�� ��|�g��6�����O�M����L��Q�hȯCL��߯s��kx�>^P3���b���K�r�1�F螜�ŀ�ε��#�������e�d��H{� %4h�,c��c��f�I��ʆ?J�����'+o�+�1��;vNN|�21X�o�5�^�;�vX��ka���c��VS�I,� j  �^3�8][���ox~1�H�+�P���J�<h���y޳���<�`ϡynȔ�%L�#2�H���9 &�Af$��9bJ�i��61B7�6�h�i���P�pwK=1tA���Bi�MzI\YњT�5�6����u����ԩs겊��r��S1l�{�]����`���̍�	���{�毻��A`�1��B@�v��ỻ������m;K� �O��+a�`����昃	�(�Љ[�֨-����I
�8�XZjS�����:O$�/���ok��#H}�@����QBW%�5��$QĪ�-���As��j#2Ep:�t_�l�&�"�ty3ZN
&*�~�z�݌�4@#��.�&^�E�JE�i��LF� -Q�W�d��*�>���ȽA5�2$I��-ɿ��~���2��<V�ÄS 8�_�A�O K<=4+>�I�Q{Z����5�H,11�o9��=Y�s�:lvd����K���}��uyȩ�a���� �������H��e0U�ey�����x8g���m�o[���hj5�}�1���Wv�����QǮ�$�0���jߒ��N�Ґl��u}�/�㱧��wy0���2����N=*[&XG
��bA؊���`m-��%pA"]�E���&u=E>�F�>!��ޞ�YM��,8��n����&f%(��Q����t}`V�3(�Mx]�V�U�g����hO��Ȥ��S�_ǭͯ��a:�ɅD2�\]�%������홗8��Vgdb�\Z75�.H/��j�/bŬ:��ŷN˯�8�I]Yĕǔn���L�چ�B��\��Y"z6(�bwA6���\���U�F�{pA����m��жT�	K��oQ��pl�      i      x�<�[���
E��f)���a��(���&�p����o�O�lѩ��D-ZW���hU�O���_�O|��_�����u����U-�m��/�����ǍΈ��_���_�N�Z�NF[|���VE���_$�������}��W-�n}�-��4:���nQ���/���$;u���_m�uNu��0������wګ�o��hq>�f=�բMfuFv��Cݦ�*���v7fw�>q[����bwE�/�W��j�N����������}-:m��:�Uy�N�q�ng���_�U�d[|�p��k���_/޾kn���#~�:5;����u|2�C�Z�2Ƚ��zjNf�G��yKz׎֎>��Y-��X$26j���S�}at�o��}����+3ɛ4vrr��(�U��E�2�>��N�d%���y��Ll��yp7��;�g���?��@~��P�;��h��OJ&=��"y䟊@�I�r�8Ⱦ�]Ȣ�*����â��������f�����-�??�j��dW����A�n�eq8JK��If�����g���16��oZs��X(6��t3vߕ8�'�@�RRHJvM?}M?����C<l�����;	ol{,���*oK�WK�}F���4�b})�ۊ�b��F_�å��n�4��Qr������i|�;�O"�~�7�͉+�ŏ��=��O��0�C�Ĺ>9�=0��g�I!��ڷ�קt��}�ӑg��O�"�o�cNƟَ��?$���5�E]�Dc��N+0aК=.M<u�(4�&.���G&��'?7����_Z���!���W�1q���f�,d�o=��;��K�}�����wN�k�/-'�̖�M�h�W�lPz��*0@QTQ�M
~��y�����h�ek��1��5�%rb]T2(���X�E=T~��C+���7�VLL0���.J���pWz��s2�M�n��r��?DW���l��D_}����%%��������,ԉ���e��|�õD�5"[n�=���i��z(;)5��'I�C���ɯ遇�f�@���fD���W�dz������34�s��cd�Q����������w��G.���G�`�x8]k��"��c20F����ޘ�;ȜT2�]�nO��`���b"���8�����?-
i���>��10p���q�Ob\L&�	xSҶQ���3gh��~w�����H�_���wY�ؖ���#-.p�e0�}r�F�t퓉]������n�)m������տ��AA�&}��y��cc���K�0�}�o����H�^�Mh�M���}�O��E�"���|g��KK��ʡ}!�[�G�����������w�dƘ�L2���ԑ�N%`���ڟxX���|�����l�d�J��dpJ�.��yg�t�~���[?q�����oK<"����H������l���$�_�6������1�}�O29w�&]q�?���Y��E1OL
��Ǡ��KF��/�S&��O�Y�2��|�����.�Q��fRh��wP��`���E���'�ج��LV~�l�-'��}X(�Q���'�^��0����������Ӳ�����ĤZb���e����D�-��v��<+����6î�P��[]	e�����������"�Z�Ȼ䡝H����id�n\�l�+���*o�r��:S��+�If��$���#P�`��L_����ȍޢ�V~�0�P�1-�e���gbH&y�=�ƨx�t'�+V]��s��f/�a�xӹM�\�?s��bU����ӽ��W=e,���)j��;��mYj��e����'��1ǫM�����jś��ղ-ڲE����-8��vrG/Z[N��C/���b�V����~.���Nly�jF�>s���b�����j�]�V�Z���_g,&e�i�Dl��N�%�}��~�ۈO�ˡA&�����]
��p���b�X,�V��'G���b^�X}�ԯ�mͰ�X-n�Ż���,E�I�I&?�Z�<��C~q����7���Vk[�_������bѳxӶ�]W�_X<���b�X}/n��ͽX4-f`�A -_V�/-��O&ٕ�C~1�Z��[��X� -�_I3��������e� �PX�0��œz��9Z��>�3����D���$�"�O��L�Z��ba�X�,n���ż�Ձ���
Y-�ū��-5�'y���O&]���� �z Y��''}��	�w�����'����գK���m�h����.���f��͛��Gm\r�z`�b�=3ۼ�޸��u6��O��?6ӯ�S�$��){R;��O���m�oZ����(MbQh��x3�����>����R�D�,�>Gݼ���'x嶹�7+�ͽ��@�-d���*�J+>�K�͜e��߬v/+6��������m�Y[l�%���������}��،�w؛�E�{ZP���߼��,6�����{���O2��?���U���6N�y���q��A}1�������l�o�͒b3�l�c3f��7N���+�͋��˱O���X�Q�3��K�Bj~�;,7#D�N�i�k1Q���^�F���ɢd�Z����'���|�����0l��ú�$i{�j
�����>�lCV���aL8�'�Ü�e����w`�g�kި�Z�����)!��п��jQ���>�D������a�r�tz�~X4<�o��9��>8T�Ʈ���5�����{�aq�����W��n�9L�_tnrp���^�����$8�������b��`����F�0�ޫ���఺>g?}���좼�>̹^xxN��>��aq"�Кh�Ge�}x�|@o���*�0s:��aQ~���gƀ��V̖�a�I�B���e,�8���徿����e,���/�ty�|��t{,��M�L�.���K�����"� M�T���P���IE����T�s���W︬!..qY�_^t]^�]V�{�rG_������2�\F�ˤ�r�_F���4���\n����|b -Ey�u7��7-��` ���/S���2v\ܗ/������̨Z"(��2��ߓB�o�������r�_<��)��8��=�n1�e~�{]n�������2�\��\&���\>�\����$Ə�}~�@�".�����e�t�BzYuߞt]�A�׻����5���~$eD���{� ���Y��E�GYe4��kc��DeU�13,��01���β}H?n~����Z�,������������k�H�88��A~���)�7=%�Pa�3ei�zz�i�z�A�S+��f����&��
>q��o�~a�1j��r�r!��7��.��W�_�?���/w������!9��Bi��E�M�?���0u��*K�a�w�W�S��j��������쩲�~���i�W~���W�Y����W��W�^���f���fT�����~�S���Ϣ??���v�)��n��^K�3�pX��ļrnH�p����;4��?��~0̢ر�~��+�������x��p=���]�o?���������z�^�w�gJ˩	�/��'c�'�$�������LS�	�|�w��xk��'<ǣT<��*,�bm?MM�6��nPu/���,�g��'��k��;B"F<�a�^*�_�L<�N�v�F�´Tz~�}���1�Cz贏 1Ҏ�9�8�TzX(���;�8��0$FY��9t�Q6��]�q$wb�gTO���t���J�ˋ��x,�kyJ�b�'������ 1�ӱ�������3��|�q�#�F�y���3Ks���f���L+�����^o�뵿�q��\�1'�1��s�v��k���&ˎL������G&+�!i���R}H?���f�ylie,"yO��̋1�ۀ��'�C�YJ���]-4���a:�O��0,���ZE?8$Y4�ER���iz
���Ӈ��T���)ZN����J�*�)���gH��5aEMR�1�K�տ#�m3e[@����2�ƚ�Ӂx�۲8����(����UVZt����Lמ�g��9�O���$}H2>�4RZ�&��    � ]cHi�<`}i�
���{�����4�YMyVs�����i���wAC�ǐ�1��y!X��&��1�G��0�ŤRM�4�q�Sfi�Oȿ��1�z`�p=��HMG�)Uj����>�i3��ZK`��z�3����V<^Ŗjf�O�pQ �cH2:�hZ�g�� tƐ�>3�{ %$0�3�o��[et4�T�W����gAL�� �G$�H�YJ��ݴp�z�[���LSx���ÉBH�||I�;�F�a�4eY!���8�� |>�3��1����@��L��H���D��'!��bɀ;2$�4pG�����gh���������j7�e�x"�s�Xd����,"$eƲ{d^�S�X�`q}���塗^ڥ��0<Cb�K������4c{[3	+RUFȟ���|��Ҡ�Q�5�E@Ƚ5�4&|f�ϓ�Q�W���씆�樢��ĵ�}^��pJ>u��A�r�j����ҧB:�H���[0�ĬW�|uh��&˒N��_�ː�2��4Ѐ!��'|�!�e�r!G����Sr_&��l��H_ $/��Z��p�g�L�(�%�d��!#��(����Ɂ4@%-ћS´C~��O����@yf]����Kdi���:x-C^ʐ�B��
%o�!=}�@i����\� ��V���I�Ʃ�K�\r����u:��I��2VH�L��	+�6�S�M^$�|���mg��H���2?q��OSON]8]L��ty�,��4pQ��vf�/׮��R?�k{���<�2\R%w9k��r����	�:qJ�Npo��m墡�k�0C~yCe��һ�/ݷ��r9"/��`�OK�!����>Mi3C��}Z���E�w�z�.��J��wR����NY�該o�����[N��-�ohȧ����r}P�,g�(_�{�ʭt�r��g3��4LS}e��(�����
XKZ._�4�C��/ޔ7P���N^N���|��p}P��{�rl/������3�iA(=�ӑ��pK*�Q��嫂�_�f(G��QR���CZ}Z�4�0m�m�/��}��(/�[�r,/���|�������u�d���w��_�Ԓ��O����r�^���!RXڙ5k�*�[4d�Z@W){��m
Y����Tv�,l9BCb)$�X�K.  ��Ɓx��5����A��'��Һ��!=�<��-�As�����| ,��E����%;�"t?���х�S��\鴋��w7�se(CC�QC�E;��-e+�fī�w�Oiտ,Z�˭�4�4�<�⧅Y�b���ɰ�6��r�]��z�������ZV��zyŘC���R�Su�_���*��D�� -%���*�t�_�ג���&R�rg�B|�f��_.������ 8���$�hH6�H!)��x��LK']����]!K(
�{g�o�wp�Y��!�/���v�u�훮��l�@�C���9�ݎzۡsC�kSa��X:QG���փ$a9-��ݥ��������k�S��W���S�R�<-�������)�!k�K&�N����@��g1�Wѳx���Շ�o�����C�,�������	E)���u���5�^�Wp;vm��[��z��+�vh܎`�P>�k%�<k~�}����M����T ���nkz��� ����HhT$~������:�5�M5`ﻳ�o����D���An���c�C�vL��y?S����/��Q�a�z��?RA~ji�AփW�ZT�T��M:v_���8=NS�/���q=��>�P�&5�UQ*���U��%�sWyN����m�q"{\�J�BY�v/G�S�t�v���=��ᵸԪ!}�!�i"J=�{��k�/Gɓ�����_���!|���Bgin�U̿�:�=z���q$<�O��x|su,���tXN֐�5�NxSC�U�9���u�����)-��ְ��HSe�^�4A��'��Q��GG>����L��rp<�A:��y���h*I�&4������5�y��d���~�W|�`�?NX�g�$�Obҷ�=;�F���	 �ei�rX UZ���qp�?�E�����6�/S������_G��s�����xW��_��(}��%�HA�4���ץ��%�u�zu���_�o��R���ծ#��!�k�R�J%�k�|�����q����2�� 6�va�LY���&l��ì���J_ӕ@�2�8�#��E�SWy_C*ؐ6�����.�q/^y��^_���j��h��n���vu��(x}�t�dr�]�Z��8U�����6aBz��/��m��-���A��^�;_�����VX�u---ͱu��WZ��0M8�^wy�4��?��f��F�Q�,B�|)-����#�������?�C��?��?�	��u�Ǜ[��ʼ��x5Z<����_�e�5|]3%�M�`�-��b!9%�a�RZN-���D� ��_�?<nJkH�^ִ��&���5tuiS��5�{atfq��I���9C���!���_��8��ʫ���������������k�HC)p����O]n0�߲��������31LXxhG.�l��~�	�!��s���ږ�v���?����,�]	�?�cnO�o�V�^-�����������ɸ M S��,S�J^B�� �R�ª8��4p���)�kB%k�*ZjȀߵ��3Q&�RCej���y���U����gegJ ���Au��(����w��+PVe]a]�*de��/��)\ ,5,5��-	2l�AB^ؐi]>S�4���Ad���|�?�ԙ?L�7�����ߦ�6�P��f�$8��[���gѷ��{0��,���\���64��C�L�kS��4����J�����h��n <��Oyi4�P#rЦ4�>��t|�f\�E�,�h,k\^]��m��Ͷݴ-��b^d�L �ϔ�F⧹0T�up�e{�l{�g��	d��i<R�V�?7M�l�hЏXkH�}5���4U�ĵ��v��rަA��zn��D���x=S}�^,yiFZ�n�d�N
y��Y!S�f���Uyq�,ܥt�9��38�3��\a��l��?sx�	Mg!�Ӱ�tYWh9���^ ��/A]�"��z1������qX��� �|��}�����/b��I���ɣ`���)�x	�_M��I=�>���6����	��%q�ң3���6�M�aS�|�:e����:ɘe��*���>,fEɰCsY/�>}L�	�����Ez���S��Ms"4��Ɋ{��@�Ϧ,�)���0oZ\%7�ᅦ�>7�A��y����;K�b6_��h��w� 6e~�JS(S���615�`:�t�i,�i(�it`�������$N��)�!_)��}���y0,���{�=t�4����Z��c�$�)ͫ�/a�'���)0k��JY�Ҽ�ᴔ��A���_��P/���aY�>S�p��濔�����3����1���,���Y���pr��'ch�e	�0͛�sE�X5*44+4�qM�U[��A7_4�vM�Mi_��i����i�D���O�)t7�X-�9��~fS�HmT�)�j)h�����x��zJ�j�����L���eT5L�p ���rh������>�Bh8,����Ȁ�ղ=��C����J��Т��t-I��O�8BC�k+I�A������t�LG�tv�.�!sM�M	^S.Ք�(�� i�L���B��W�ϧq��gD򖻁.����*9[��t,M��ث,�i�O�c���X]�קW�^�p��ч���g�C�]���Cc2�K���l�Y#�rC�>hJ˚ҹ�J�yyD���z��r�/�����B�̶' S/L��Ҧ d����V�pp��%e ��Y9#������X�U��������)�j�hʹ�F B�`о�P-'��;�)Q���%0єm����+}��X��+�*���ě���N+�HDS�ԔZ5�l4j�P��ࠎ����U�T�U)�YӬ�b85�єn5�R�*`[��n���J�1\Q��)=    �Wa��W�T_lyUS��4NR���Yij
���+�)>BMT�XG�6O%p .&�r�(��O��%�2��.����f��w^\�%/���o:u��E�J��Q�'K%�:�0G�tP��S�����P�嚺/�(��m��_�15�Z�	s����
����J��ū7�V-�ǋ�+�r:\��Y:`*_%���uE]Lų������Wk��r�.&���XP� P�8N��n�4��a�6�Ji��_��� ��>L���������ˍ�4a_a��L�}�|��kJ�"kk�*n{��8P� OT�jt9疅5�w��TZ<��#������{j�1uzc?M�Y(���K���9�.���N����^��.z��e��UV�j��P/�u��Jt���E�*S�B7��}�q_�\'���\׋���._fө���2?MXX��g�}��*��J����Ժ�Ѡ���8���q��Q�&��H�,�R8ꞅ�5ƫ�<�����2�SC �	��*X��$�Y�R]Y���R�~&�]�vP޾zں�v���	�� ���/�)�kJ�j*�r�C��������h ��>��� R����8�y�R���+@��lG���������޹u���ݝ��6��EB��̢����#)\Sv�ܾ�5J���	*�)b/;�5�0󸂌�ұȲJ�;m׏��������W�^�p8]t/�G��I��w?E*�ij�WZ�c�e�z�<�i|�)��B��w�6[�3��4�����{`1��O����;L�r���&o���ƑQRf�ă��`t�/�6C��%���&a�&̬I��)	����^�)"n��������A]��Ѩ��+��gb^_	R}3�iקr�����[�5o
���Yz�{u�,�DgT4UA���㛥��&IW�Z��|E>~m6����W�+�)�&F���z\~	ĺ��[���9M�q�n|�{iX��I���RPG���8W>��ך���z�W@�T�Qܙ5���������ä�)�
@Η��O])�+۫�*�i5��ֽ8�W�_h�C�a��
;��)Ô5�)���@�:���)��c��r֔yE�������/�a�2e^5L�9��Y�C��/�$U5t3�E¬PG��F�"K]���i��i�y��)��a
����eH��u��y���k|���D��|�t�I�nM#q�4q �9eV7�Q��:��⼎�2�P����śn�h.ik����&R��bj߭�=����f���g1�)����a#}�����Γ�q���w]�^�l���+��|��c�)��;�Sz��p���{}]u��������,�&���[ؼ��W3vW�@>%5kb0��E٩n[E�_[u����c��6��}<�7��z��������A����(��\d��oP�0-�2-ա�oK����0��b`	?�C���B�y`�	�0^XP�5_%Ϣ kd늉���p�0�`^!{k����4E���YӬk�bS�А.q+1y^�S�p�f�Gؽ�ap�0Ŭ�-Ҭ���'϶��`iW�[p�+E��G.��������]e��N��ܜ�g���E�ك|+�T�Y��Zv��3���0^%Z�Y�����x{�l�ڶض	�3a80�4L���϶���܂���ư4�Jk���9t��0�Wg���,�Ͳ�4��W�5e%�M�!g���V]K߿�5 .��h�F^�����i\����^vk�!1��
���� ⁆}��h�Ԯ�z�ei��V0p9�=��`bl-�A�b��d�����`7��C��><4� "��{ }Ň�
�ܬ���E��	���M��YY&8/7��v7ec��[��[����`_a����L2��-�����0���-�(��e�v�X}����c�M���pW5`�=Qno�[l��\�L�	�����kYZ�\vvz���5��ng!S�!�|Y����4Mp�_2�BV��2u��#�����{�1,�F�뢻ꮗO�ro4�B	?�b��4e���<��l����S�r�4���gb�S��P�Z���zGyv�A��p^$0Lu������4u����Fk1u�9�sL�o���;5�{Z̷����0���[a����КB
S�xǔup��Dߛ��Կ�I-�(-EUz�D�p+5R�IUyl����=]�Lma�
���Kp.,ߡ�*m�|�B7U"i=C����:�W�aĭ0\V.+�=-����
�p����]�5�Bn4wk��F�p�Z���8�2�ù�?�	�v�R����&6p���FMڥ�.G����������n(]*��E�	�
����-�ɷKZ��8@(_��Pe-�.9T�6_��E���&S6��BG���|��Zk*dY5!5H-J���C�R�ճi���p*N���mFߊ�al���`}��9�ʹ��]�(�ܚ�|SCi��A'x
��%�ʾS���ƛ�x[�����Nno[��4�.<y.���Vp�����.qi_0���r�t{[���1�p+7�o�̭0�W��
a�v�3�`V'ܸ-�s�i�oQ���������o��
�\���:@X�xJ�zG�,u�s�<i����)9���a|L�S aa�x���+ o:�`aȯ�F%����p��8]�����99��m����m��9��2:W�F�j�4|�ıY>g�~rD��B�up��H��Uro��9�ۺ�x`ط�zY�x.��dZ���7u�K�B�YC�RO3aM�(}�ӧ��1RB?0���0�2/��Ҭ��G�����?���&��~y*ǫI��i�T�ԱHg2���ӹ�D���E�BΗ5�R�AX�T
�*~���� �+ܩ��ø��!��8_A$0�����&+�,�Ƒ>�ҵ�[�5�<b�}!���p�:�,���������k�LE:YH'w�6���! �Pj꨹��l���;F&�e��տ;�5P�/r�Yx>�'��pZbȰ���i����r��Nx�N*���q��mu����@����j/�c�������g���Er���څ;�a��`��w�y�p?u֘*��}��
9j��g/�a�d�PWn���p^��0��,B�$Uj8��~Z�տ���R<5J�/��|�V����֐�c�X9j�f{A��0�W�l�*�
K��������n�G�L=�����OH�h�L�{�櫪�A���L�XһS_@?k�;�����B���kȥueR������e~��d�>z{ƺ�q˿����H��5yfqq}P.U�W�+���2��-��]a|0,�:�IF-�n�"R�B�Y�F��Ђ��f1>���u��a�|uW��嚿p��i"�{K�3|u�j�aڜ�\�8�BN��	u�Й ����FMZt�,�C��;g��7�X;��^`��=.��b��y�ѼA]T��}S,�ϊ�?S*�`	;��0ǂ�_A<04�n^�3���!��c�ք�Y.��@Y�u�/.�YNZH5k`�bGs7-��Q`i��o���.��5��WY�SaHGk�X�Ww �B��	ia�v�2$�S���ټ��B)�@˫�`�{���r<_�$��rV�x������z�=�^O�05�4��/���&�S�@Rڷ�P�ș��Rò����Oiea��0`X�u�g��9���j���c-�8f]��4�B�Fa=w���ٗ�JKb�i!�-$������5�vP����*��.sgÀ����SZL+�Oia�}G�Kz��K<Xl$ �L�T	�t��LIoa��0"�i�<��Aaǵ��:k�OW�Y�;��{~3o=��f�.dȅ{(�\�0ZYȗw`�F'ӛ�X�\�|�pa�1,�4o�b?aX@iũn�Ԝ�V��gᮋt��j�2R�v̗X�J�Ա�	Z�������?A%��/���)�͝�~�`7���س�ya]�����l;����c�����~�����z}���#`�Yi�a�aV�b} ��� Sܱ��.#+�W��Q��<l�ߐ?�����}l    2�)��9�zS{��2��1��1d�����R�<ݴ�Y�
��w���,c09sd���y�B��%�[B�L�T�p+I
���q^�N1��,d؅��°gA������9����������?ǻ�c�~��ol-\U�����Tw�4�`�Ȁ%��+|�T94W*96�}�tq��ʅ{J��p?I򴤑x�ỂA�Ht����y��A�����g-�ve�)���tXu�6�!�.K, L�;̺�Ѕ�8t�T �����Pv9����@d!�8�05Mi���J��K���9uTb��}���t��茄\���~�ӂ6:�'nT�]�*ӊ�GM��W���u�&�Y��Iwa�y}D�&)��aU��(C��>��TvC��a���Й&�,-��?$�s����$4��+��Q�]�9&��ʟY�˛>w�$k�z����)ۄ������9p�1H����[i6��};��i�/��5�@G��FIk��|�\g��H��ei�*3�8�L�w!)`!��N ������v��w��e���;@C���-5/�ߑJ@é�T׎�~�Gݫ_�/tv������$i/��P���Wji�)��]��7���5pN>Kd������U'��ڦX�x):��F:˿>Ed��= I�>�����+��71M�goB�*��o���n��Fl@���k|7I�^���� �p?Ҡni�����2�H������&_��4&\=��L2�rZɴ	=gHⶥ~��=r�R���Oe�|5�m��4�[���0�/k� U�8����+k"����,W�iG��Kc�5�ii���[��q�R�`�qh��g�ơrXzh9M�)-���P^�~ϗR	� r)��iJe��<��g��*�RZ���<ʰ-]���z�?e�{�4+u4jy�,:gk�m��Xێ�C�)hJL�M)������ظcS�G8�ϱ��1��R�Ytťy�c_o�k��Kx��Ͻ/]wm�����#Ӎ�u��7�l����n�2�.g 6R&��Խ��aU��`x�a��
Hh|)q��Om0���i��4�Z�E��7���?�����~��L� _��*�:tz~�meb���ėl�����U�#���|Ӌ�J� o�c�0�Ѿ���i����̹�[��Q�Bу�}��O8��ļ0�o)7�l�p�bfM�,��7��"e�T�R�N3t/I��3�@�`~�ˎ�y��F��S��Oit�^2���#_�L���\��Y\����?+�<���]^�,Y�н$7���2�yS�JA�оO��gT�5g�.�Y�p�C]_©3�,�쥔�4j)���2�Ҙhi\��̰�e�H���c��!x��D�*��vLx�=��S�3�Y�,�x3n����e��p���g����Jd��2� d�����������0K�~�Y�:+��m\���2�(7�Lw�l��h��+�]gmO	ϐ�����e�@;���7�퍳���ĞX'�k�a�TG���2k�alo�C���?�Ԡ��A׾v0�_�ڰ�U�������+S��a�+�n��=/���PB���)KÚ�{^��;�u%|�j������Z]ʴK#��ĸ��᳟B}g�,ݿ��o<7�l�*�~2FJS�r�����R�\J�Kw�L��L#��$��i����н�gb�e*���O�mU�VLc���eJ�C�򺮴��_j
w������\�,��5P��I(�4�Y��T�i�՗���sR?SӔ@��$�}d~,彥���ܖRݰ�**�Ylj��0��s{���p\tʆ|���8����M�?rS�9S��Z4���v����QRf[�lKw��t�����!��r,]�pe)�,a�@K�l�ye�>�Ur���t?�/���d�Ʉ��rؒ�hi8�4��/��Tz�|�o_G�ҖP�R�Z;���/eJXK(p��W��dJ��ҍ&J���m����F�<+����+S�i?�,u��o�ԏ���R�R�ZB^K��L6�l��40�"�7]T�@#�2-�SgE�T�������r��l���(��a�߲�^�ҡ2���O������i80�ޛ>	r�.��d��K���XK���Q��8jiT5
x ΃WUܪ��d3���8�ʵ��g���H���z�\���Ʋ��7r�iH5Raj���c����c�8�g�`��P�V�ᢖ�j-��L�C�KM>=�ǆ\�4R[��f����w5-e�5�裔O�b�]κ�g�����:�LkW��Aw�L	lT�4��/�],Sb\J�#�,ơ��q��-ᱥ\&a��G�)U}��_���)�-ɖ��P�JS݋�K.8jI4�4�Z��!5�&&@�N'䲡�����xJ�ԕ�x�m�
�|�U��*f 2��4I!�e��m� &�Uh�}�Vyǔ�|�m��҄>�!%[.�������yni��t��tG˔���q��ya]�,���	QN�	��n^��dK��t�t�4�Z]ﬢ\m�o��	$8��������)��[k��X�ڪ��r�`��n���Ԣ��d|I&/�ԫ�b��S}7�Lb�%�h�U��3�s���k�1���k�˷\a�#fC_�Ń��l�u�*w�lB=X�i2M�}n�E8i�;hb��L�w M�<Z�%�i�\H�C�,5/o��*5�g���3yB�K�u��(,>m5��veyq�:$�:����t	t�i�C���Jz&�^�n�-e�a�E����H}ҩ�Q��xl�R�!N�d�O�1U��i���|���4駎�\��r�_.�φ������,�dy���ƄkPW�x�]Lw'̈́8��lKÿ���8q	�.�ԥ;n��〗g�R��XmC�4k��^>؋픩C�7.�^��0oZ���\�Ûk�%���/ޖSYt�X>��:�����3o_ʹ�gC=e�B>���o(�p醝)Q�!M�y}A��� ��۷j�Oc��o�^��	.�4�����"�v��d�%t�tWΔ˖n�IJ��J�JK���<:��	�L�(��tą�FB��01^*�*�J��U�L��lۅ��x�Z����ץ�G˥�e�|u>؂Ů��Eg,�\��O-iX���F�K�ʥ����� si<�dOO���o���x.�5p�"���a��ht�,NW���RV\�mhBhkI1��}lñE�����s�~�tKt7��Pp�ڨr�R�a������U�����-������{F
\��'�4k��*��RX�4���׊��[z��}|�y�6��i�9�/��2+4�9���+��[��t�[��gC��A�H�ńn �q0>z��7R�Γ��b�����)��@Әs)�.eˑ�+�v��@������r(@S��K(&׼�E>������K��L��5(�@��d0��/�xK�ɥt9RӔ�%L�����Hh`U��м�o��L�ɵ�J��AS�\�Yh-�A�2��m�逆�#��o�Y˕����rh�O1#�5��vR���D4R&R��~�2/^jk������>��s�uI.i-ݱ3���1��@Xɳ[V���>���a��hm/-��?`hїK��lw��������,OS��#��ۘ<����n������8��NΥ�����+Iq�u�\v�aK�Mc�a���`~~uc7
%��)�4K�����R�Zr���������Z�k0�jh�*��|�x}>ȟ�b��o��]4e�%�7(���	��<1��])R��=î�1��riк����hI��P���R]J���P�����a*���=�[��Z����% �	��aV?/��eϥ�;���ב�MKӝL8�c%��	o�r� U�A�0�>�Kt�nHaj��t �-�Դ����M�x~�!�J�ʥҼ��e0��c��u�����<u�:f��L���neD;R���|@l�y='(�ie8: ��@��*��q���f�ZL��O�w�V�5B|o���B��J���Z�!-ƫ�Ô�߱[�6+�H�)���@�z+Hl� ���ٴ�G�-h_ڇ&�t�)C��:�0�9ԟ�R�*-ԕ/�qy�eӗ�hy�����v\�C�3_^�e�����0    ���t��S5�} n�m;�wζӶ�8ܼǊ��{�c��7�7�U�c���̯5]���w��/���µ����)-��*tG�r�Ά�4�փ��e乆|&�TX�1e����P94�+!��/�{6����VR���q/�}Y�v���v��.�%ݍT�O�e\/l%���2��Y$05����1�՜Z�;��Re�:��&z1�6?��6�L eY���x0^����$���g��%%e���6���6$=Zz����<ZYey�e'���b�O^�����)Xs?d���C���[tY��<��v���n���/5L�)�c��Ʃ���,���%ޞ���}��ed�r��rcςWF�+6-�ա{��|)����c{���\���?o���஢�^����r�� u%����5�9���8�԰r���W�[��QǞ��K�[ˮ$���x�u뚚h�9�����Ξ��B�:˄v�&+�r_O�,���̶���lW�
z\I����5�_�i���tН�Q�g؝:;x�� ���aT�\�2�a[0 �|k���B�m2�.�ە����z�����pZ���~����G�nqa��ޠ�Z	Sc�h��Ҁu�l�঎^����
�[I�kH�Re���̏�84�hw<��
ώ�g�Ա��]��ck���nǞ����ks��إ�Ά%a�-@1��R��*:��FG�ymܵ��ٓ){o��Q��+7�$���pjjQ�}#9s��3��A�Fhv����'�weE���W�R�A����
+4�.kRvg��'�l��2�ʨv��&ה0��4�*�:��룆u:��a�r"r<�# S��We١���j�����Rv�	����Et��0i��D���Z�vC(+~�eM���et�N����`�����{�(��ʳp:�ť땁�� u%����5hș9���W����nb�;�'���m���v�ӕ����W�(�*_�Jڶ=O�_��b�r�J�`���xe��2�y���;}���?%%�S��g:�9F��'neZleZơk���IeP����pL|5�ij��������.��Dh?��bh?4�WZ��,�P�<h�kz�e|�rӆ>}w.%O�|�fYZ�!�����W�6�� J	��N:`�/j��xW�Z����xpT
)�w<v�N:��$��1	I�2�O�X7T]a�ܷ��RW�+	�%Y��y��ya�z���=e��,`�ӒXF�#�'���Y��!�J�!喖*�Sz�~�H�+wE-Cڕ��Jf `^
��4I˥�B�s|������>��yO:A��ג�fJ�۳�$�������t5��^���t�.����,pw�9�c�2?���� I�,�+Y���-p�[�Y���ף8�1�0���wW�Zn�ZF�#e9��*�|G
9Mdi�M0`���:~«X�h��cԕ����KO��W������ú�d�5LS�T�E��~J�9��4�NK����(p�Ǖ��������R��������������뤐�@�~e_n��`>�re�F��4�{X����.�攅�iaZ�7�R�k�cz�?-��\^�e;׿:�U�̚�PVOK]HȺ+ѕ�>RХ�h������1������^�W����kB7�pனeh�Z�/ɯ��Q%�v��i�W��ʣLS�s�r����$^��R
\����k�r�B`<[��ލX�LtgJ�+�~eP��>[#�Oi����J�J�U�I_7��+��5p�q�ޕl���������C��q�V�X�+�JR��'O���۵�T0�7gt	���`�b��tn ߯d�BN����*���M�q) }����n/O��о|,W��8_>������6eJ���;N/�=UKn]I�#u-'(�J��4رۭ[����i^z�}]�]�k��+7r��f�)�C��roՒ�׀��{7mm��̗�����~���W��+C�5,$��k}�ӕ1�ʽV��x��B�11ͲX�����0�\�45dw��Z��*�2GM�W�_I�k�Z'��אB�����bi�x�CSZ���rWF�+�y}m_(n'{z�:�Q�P�jߔX���J>��m�u�r�J�&�}��MlI�#�E?E�/ �R���U�B��E�� �����G�K�ǹ�ꩭ[�����8E���+J���a\�o}p�zߺ�;�T��>�]�=�7W�kȗ�~�&`�٩
d?����e�M��kI�k��a�@Wn��@q����ʭV� �A���P�*&,M]ι1�d��F6�\�okI�+�˕l;R���N�#2�J�[�Wk���1W�+c̕�8�߉��j��B���G���?`��R����ɹ,;L,��چ��帕�2Z\ǆ?S���S9,��q4'�GsW�2`Mw�|\=��*J���B"�f�`[�*8
̍?���j��JN͹�;�6<]��i�,�J�JSZp�0,K�+�o%��<�������BW0�J"]��NH�˱�x)����o"ҕL���@���v4ͯm�)���j8Ճ�)��������2F]I�k�,tRנ}��c��K�+�}�s���s�醀W;F���d땛���Uy!��ܺ�hײԕ���8p%#�$ᕴ���WƔ+I~e������Γ�V�#�I�|:������:,§+��D��,�� �)�J��N���d��rؒxWƓ+Yu%7����u�34\�d�5,���S�ʺ���}�hp%w��V������kɫ���25�e�?MҔ`V_�fJ����)����	te��r؆Ф̣�|4��D?ޮC�{����J>�!��n�p�{_����n�{+��5l��*S\y��������2b_�߆�+��z_m��:��֒���D��Z̨r�o`Z��r��3W�#KྗPc�����p�:3���D�䘷i�WD�C'���ˈp��2X�2�ے,�0���}���a�a%|x[Fs[��Zj�sĭ��yͲlj���g�Zvi��i*���RUi�]���K>�r�X�
�X����  ��4�;�s	{,<[^�/�s�-`�.�tQz&i��c�H+���I'�v����{��`���'\�Ҽm!,����M�W�Ų��ڒ����'��.�>X�yn<��.	qT�,L���ul{�r��~��BH@�|���R;n�M��~9�{l��t�y��x�wԱ��>�ހ8ܒ6�d�-is�h��{�e%j�}<���e8�妫��SI!��O����*ê��c�{e����%k�a
Ceh/e��}FO�)&t���2�ےJ��tuɍ[���WSK�\�|�K��Y��iV ǬX�h��,�¡EX/_��K���O����z��L�������pP�-Ir�}^R���
�HN��I�#�O��T��`q��� �25�,ʮ�y0|:)�=m��ޭK�ے	�����L�\�������,u����##��$��-���8�`�^�
Kꙇ���֤�<�xَ�u,pl��BO�x���g�ok�!��'�g}=���k|�Z�[��
��G�w��pjQ֜���/Qnɐ[n�Jʼ�j�u�`sK�����T�%qn��B��<�Һ��W�)��sa��ox��fȝS߲�l����4+��}����� $= '�!��ޔ���-��w��p�&0,���58�N�M�{]�ᖌ�e�8` S�����&珞t7`���kCiI%������y뽌G*L��R����~L10�E�^ GeyvK��r[����yn�H<�v{l�ϱ]~���X���Mb�4�e$:R���{`�aq�t�nw��2k	M^�'�u���j�Q�[���JW�:�Q���.C�-9z�4���J�<�6Q@�ma���.w�%�IW��Θe�-c�-	q@C��5���#�e�9�6x\�Eb8�R�i�r�uK�2��°�}_6����6	�V��l��%Mn�0���!�H�L��[�.9i˽Z���6Љ��pa�r�tg�e�8 �g���j0��b[�ƺ$�a����j��I:��Q[�R��B]    ��T}��n7Q��4�BƖQߖԲeع�x���i�Foo*�V�nK��r��%�l���r�S��Z����Wz���G噭p��NcT�_�[�|���JH%�5tw��U�`Kn&C�4/T��'��20`�4��T�������tI*[R���uI*k*�)SKSi��:��'4���n�U��Q���.i_��NIM-̛��~�eX��E���j��r�0���ғtp����L�����p0�,>5�;����+�E8����+�k��"��2�TY�+�l��c:���2Z���̦�K��r��%�C��U�w�L�Ho��lv�2��2��2��rcT��ܲ��nnڀ�����){k�m���2�J!,�a�
S��c�Ku�j��86J�Zl���u���rԆ���p<K�`V�M1��.˫!�+�f��T�����,2
w�Iap-78m���IQ�jk�����o�̲����֐����-b��,U�~</��|��)Papr΅˙n9$�mZmI�Z����H�z�[zV�uvI�ZFd#�Ej�J���y4��ؖ5���i��\�6�e$�N�y�ٖ;�����i�|�=W�E�ĭ�)p�E�8�6ۂ����F)��p��T
ؒ��@�}�T۳�ݘ(������R���ؖ�o{u4�h�<�mJ�;�.	^K.גĵdl-c�-�_ˈm�|5U:g��(Jr��
/giVzyZ�#�8lK���w�B�U!���.���Y����4�<OS�p+��t�m��Z�Ԭ�n����Z��x����������䆡�b>=���5�)S��rT��ղ]G�]_����嶥KR`^{�1�H�ML`h�]Xq��F_����?���,:4m�i>*�﹖3p�#]Fa[�Q�����n5�f��,���k�3��'-�a\/�w��2��ٖT��k������lhf�W9��0�<,$����;��G�6t����P�-����ס�z��ږt.RG0�{�19j/ݞXnk�
��h-9�c�հ�@=��w���]L�d1@i��q���y�c|�qm��)�����vV.=���Y�&�:��~���+�C���Sۙ�;�\��S����`��z´��X7K�Z�� -B�'�SB�r��%�j��j�}eey-ï-ï�Ғ���etQ�r�
XY�R��z����v�v[ӆ���پ�f��e�����(�V�����6f_S	}��ߎ]7�;aTB�����l/�I7�ar}�~�5<�ɰx1|����׷�ۇ.L�O��+R��G]O0a�>��>YG�T�(32��̡��6������:'壍��R>�H�<�m =�C�d��`k� ����Yv�ۻ�,o�����[���}�e�pi�t���l�O��`�]���>A�b	�O*����-��G	��K�.���}	�����	����p�K�F�O�!�T���5�e�����}��~R��z��"Q��U����Vy
���v�Cq���L��/�f̱ԑ>n�����5(��g���ՏYe�/o�ܣ]f|<�U�$$�e��;�ܦ��JsW���A�F�W޼q�t�3"���3r؇8�����i�!�}X_�W�P�o��o�r����{��]�}�1��8��d�]|٪�D�!�}	��%ۇ�����C6ه5���?e�LWyK#Q�;�G�a�}��~�e�W~�*T��O�Ofц\���>��>Q�����D�O��O"І>��j�r�ŲG��T�8�!����d-�������r<ߺ�c���n�$�м>ܯO�����A��p�>��4����K˱|����}� �f ��˶mه�I>�%[,4�&�7[��y<�C��O�0��dW@�C9��^7����7j��>|�O�� �`�K����#��>Ht�Ď��iq3_ѽ>��>��⧊0�ĢgIB��\�m�g�1#�&����~ɒ-4���{ ҫ��\U�_���2����ML���:en <�I?YGc׺|-;&m"�u�ig'-�g��`�`ӱ{4�Q��GۿpE6
�Dm��	������;�k�5�\�v�(m���������	�X��[Wx���8������>���4���Iۈ`^�s���/7� �B�����6��p���a���y��p]F�ib>��w�b���0���Mʙ��Ґ�L:��m��S��1�}�霦����py���m$����|X>?Bç����s3|�/��3ޟn}��s��Q����{-�K�l^o&�)nw�vR[��(og��s���ζ���س��PS�z*#Q�'�;e��Ԑ��<C[� ����l]��-r�7�7�1.G���l�E7:��-�&�d[�� l~����K�"�m�6��-JB�^}k<����4�WD��-<��Vۈfm���|����KI�[��}��m[��@�G�CחFt�-:��-��s�|��hg[.������nt����6�b���{�̻���3��n� bm>?�4�\�#f3�:����>���XDa��i�֑r��Q���w`i~�2��\9=+��_��̽ӿ�m�J�n�@���������������W���<��4�G:�^9e0��u�-���g�����u�&���դ��t#�mԳ���}��ˢ�v �t�#��z�'���.k��\j�^md4mx���d���C�f\��RNB���"����G�}.�W�����?�Ex �x�]�������o�hG-ʿҳV���	<ꖺ�rҍ+���? G�a���'�y�؂�ma�ҁ��NɳXRӆ�n^�؁�j]�>�����֠̈-��?�T9�r�]�e <��p�0U�8t��-y8������^<����b�n�����m��5T�E��EJ�=�%Wݘl�!a1|�A59(�٬AnK���m'�q�vxh1g0I)�T@�����\�r��;�~_��-$��-u?u����FAL��[��+m�0�����@����)]���ܗ�uc�����68MNi9���C!�\�rP)*v`*�^`������<�C��@�&�o�[c ��C�kH�6�	ж�۶��U�����m��ŋ��=��7�G~q߂�5̔,Ҕ��pm3.�Z��Dȷ�������-��Ə۸p[(�-4[(?�� ��'a^�k@�g�`7"�F�ۉ�a&ݵ�Lk��1>��Z �(_m�=̯W1hi3�r0�����r��[-���[���R�<C�cl���umX����B2��+�@m�~���|���r�,P���P��_�~�g`�m�\~t�pz��A�"�$���R��ۉ��e�,���-p���J͋��%i�	��1�)���I�6�F��HyEh���m�ٶ�`�t��{ fv	���=x�̞��$F��B�H9��Sf8��~�!�l�`��px�<�����M�g6�3�RMCN1_7Rݖ`uˬ��Zۂ������(;l�V7��N��.+e���.Yk�����f�]�i�-s)3I��-��F�u-y@O� D�-0��,p-��U^�Cv�r�4o#>�6��ٿ����n�K[��/��n��nT-���Ibӝ�[�ҍ��Q�(f[\�HP�x2.�%-X��/�&�I���=/{��t�V�pMH�%���s�"�ݴ�/��n���Gۂ�5d�L�e�a�mYK#}��%��RM��P\^���Xi�m�F�0/L����'�io�m���U�dW,��mc�5��E��Xr�5Q���Y.���2��f������2��)�Є�y���{��u����C�I�N�w�Tl�-��Ə��������F��vU�������%NݫXf"'��Ơk��P:�5[���kא[ʹF��R�FJ�`ޚ�����U���8�S~�ߟ���z�4�;t��[���}���oQ_&/�!�� ������r�<(?+�g�{k�t������/��#m�N�0/�@��ܖ�tKO��-k�	�y�~^ڑ�b	����,��o��2���e-�L�,�    ���w����f��9�7���v�r].B������?��n�P7��m�ҥ�4�:�Y���\�3���ż�r�n!�bI�P*_U�r�m��v�q[��j�+�!������
T�(Yh07�f�]��n��b�E�|>�4s^Ĵ2���[�������u[��-���ۈs�`x!%ߕ�]|Q��V}�K��k���bq��3DQk��?�� �E9��{lP��p�Z�n[��Zh�W��wca̶�hёr0��v��lK_K�!t��m����?���{~)����wi��6�ܖ�to�g���U�\�W�xL=���!=uwfSҔv��LR�D�n�+uZ�U3iJ7"]�]���b��d{��y�ݙ|�#��aR�?6�Ғ6�虳����m4�x/�\��]���װSf <�
m���S��q?v]�q�;m��)א���w�r]�(����miN��g�x@.��YۓW��(��V^u��d��Rn*���V�҉6�On+j�նԤ�9�2��l[�Gy(s	o��m��������a�>��`-�;0R���/�>ry��j����[�t	���/Zy���m�"��{�K��az���d%ݲ��/ݘtR��B�m��-r�/�!=�]n����޶l�n�^��������m��▒]���A[��hu����&��m��8�X>�	���*�R�L��x���]^�+��Jmc�md�H�yu�3��A2�v�n[��H_�U�=t�A�I�a��/w,�����Sy��=v\��M�l�2��_6��{�4VYQ*?��-�{ȡ��:w�� �n��'���c��W֘��nK���b����Q���!7�'�k[�ԍ���k�I�/�����uW��Z'����ۉӶ�O\�
��_����,.���!�_벣6�R���Nf:�]�@_-����Zlő��k����W����I��=��eG�sJ��4��V�c7��O��A�9>���E��e���;P��������x�r[�ҍ\�1����f�a�@�0@U�`��D2�nqآK�,�v�¯R>�o;����:�]���y���6^]�_ʾGQ�vxu]f(��1���~�?ÇIz`�{t�6Z\Cz��t8rQ=�����"�m�6��Ʒ�Җn$�X2Z�Lr��X�r�q[��-�+-��yZ4{��4��Jh��w�dm��L�UWR��_Wx��y���\����t�s^�����8g�4씓�ap�~�^@I���Sz�)W"����)�ux��`1('ijyݖo#�&��C�\��*�(�<`Q����,���/�y}��Tq��U�S�R7�	�d9��a���]>�5�,��5a�'��d�B���֐��T]��ֻ�;g��SC�p�t�\�W*�C+<��n�DNk�֪�>L����6�F\"���h��E�a1���� �W��q����&v��ɦ�A�W
����+��>�P�RE��"�[$�}���V8q�d+]�h��Vb���jQ��P�w�D�%�Y	tV8f%�Z���\�.ܴn/X�S�����Oީ�SUz�/�XS������JD�J���J����Ð�#]7R�j(j%oh�H9%�]�J��B5kx	 �/vK��ݰ�Jl��L+�EKܵ�F+����4��ϰ�Ϧ���F�Bz���z�-%!��m|a�U�o��V��*𦤊Sf8�Zäˉ���0X�`i�Z���^�b^�� 4U-�$R�_�Z�,�m�WXoi����Mk��6���ḁ��������^Z|���J҆n��Xa�Aՠ/����|P��_��k��i]���对/�Za�U⫥J�T����x�`���z��֐�䍼`-UEw�����-�Z��a~���/Rm]>t���zQ>׹[��)�Ù0}_�q�JK4�Ba�r�ӰXC�/�d;-���(��P5U�"���뙍���גY�Y	�Vh��)������}����}b/ r[%�i!��A����7�m@LQ4��e��r(J��y�	�V�u��Nk���}6 �i����)�j�n����Q9�|�+!�
[���
��0�*\��F+L�d��dK#���P>��{ �R�Q0�ETk�aC:+4�����/HXg�M�p���6�7c��UB�U�|�i������� -q�J"��-ʔ�?�_@�h�J�B�/���뒓�d(-�H?� �R�{h��ƣ�^���2�&��(/ݕĦ�Ħ]f��vk���`��������Vҋ6�Ԧ!
E-&�X����\X�r���J��-�V��&�-J��/����Ƶ �]rZa�Y��U�e%mi	�Vb�2[`kC#=�ue|&�
���k�Cw��/$\���Z	�V¾����r6^F9�2C���
����,�%.\	�V��Fb���\!K���Ӄ�1���.�ZWҊ��Q79P��~��I��#���|+q�
Ŭ�M���~�;L/�Ӟ��SO^W���&��V�6��I�s���J��Bx+����Pآ��
t��.�WG�J��Vb��[���)��������V�� �[C߄B�ER~������%��0�̖_>ђ��$'P�#�	HSw�v@�+e���8����s]fX-"��[IG��j2�~���^��H�.�T�z�3�b��Z�+��N̫<\COD��]�H/�����(��o%�g �_�x�68�����ZѕXr%�h�%�y��"�]a�5�I��i/�י4�RS���/�haՕ�q��pR>W�rеu~!/��"��1]��\%`\t���ʄNx�r���t�̾e���Z��UR��]�{%0)��V���m��W�y�MI��̾~��R\���;���[����+�(I��C�)�J�ц�hv��p�lWu�e�p�T='e�f�.b\C�A��Zz�����x��ӕ��k�nv��k%5h�$ڐn����Lj�.�^���1S���+^\�e�l�P[�af�*��>�\a�Ez���9W�MWHr�W�Mʍ��K�π�����5\7Bn ��.����,��>�;���/{��.0�T �}�s��+�IK��Jv�� ������N�Jj�¹Kէ
,u=Q�S�C⭩E�-�����I:Z�ϕ�t%_h ���b~x��i^�`?��2'Si���g��^-Zn>�ȗ1�a0 }E>/	����������e�!
^CdS�}̻����ˌC��}%^C_?)I��dRf� ߕ8vK�C7/i]��� ���`|~�����6���<�i ����a%��P|�?�]I[%�t�;�����]eQ���L��P�e�H�hV �%w��m��O�4�����Y>����+!�J`�(d��W��݂��-��J�ђ�4��cHJG�>���!A�
+������T�u{{�v���U�z��W(z1T�[5=aB�+�� �B�+�Oc��GK�ֵ�C�5?��J����z�#�Ŏ��	�9�~Z|i���Fz�ٮ�<l�ŮD�k�	�AH\eʟ�	}���]�y�Mq����+a�J�ђ��DċT���`0�����!P]	l���\���p�2Z�D�bZb�R_!V��u�R�ܒ���+4� ����lK0�jg� {iC��<�Ea�% ��ةy���\��_���w�I2Ҿl�}��%�]	^W�5|���
t��pt��������
}/n��7�~؁H������ð\ �큏�W2���w�{�4Y���n�2��	�Vk[(��`���4Xe� �]�I�w`�4�G|����������I��Zh��6uiwʂP~)@�T�!l���P��<���n��ð ���I/�j@�{��&�E�ܓ�l
��Ҕu@BՒ��PK��(^���Z\I#9����^D���+q�J�Ն�0�-B�e��W�x��WI���d��gQ�O��+����91�E�kxA�� �+��*�Qo�!��9��ኒ�6��r�6�\����
ɮĴ���R[��n������ٞ��!�H.�Bы!�r�V��<T93�	�^��\6e?��W�uK].�ͅ�Q^��   C�nQ��\�x�G�d[m�	)_���.�L�WMP�:f� y��%����*���f1�J��r5�8���s����_P�
ӯ�U-�����+��}�����>> �����M�n�%v<�M�kr���6��2g�Ǐ�R��䪅�W!�UX{%�^R��C��������
��W"�ĩ%h^L6�/0Y���͏ �zb^ɆZ��u�!�+�|�%"^�4Ž���/�{a1�0���>m�	��˜Q�(w%Aj��4��,@^ë�6D�����U��=$eN,{{����/����%�jø��GX�#�ݑ�4@b�7YNR�t����p�3���Ŀ#�ޑg�Ⱥz͋I��g�5p9����Y�@��b\�ޛ����{��c1U5��3��#��Al���&�I/���s[V����΍�d�6�R�SÁ��C������`F����x�x�q� 3���fNe�AHL�a)�2�<�0x]��d1��{�[�i�ݗ`��h!�nR�����ԁ�:����t����sI>'����� $6R7�{;�m�i�]��';���B��D�Y�-^���(��,��+W���ҟr���n��Rފ��<ZZZznK׹H,���夏�9ṇ�'�9�,r��HS�����S.k�Iz�#�A*<���U�Iw��]�ă�x0�c��<f�����x]� ��H�k��'puH��������@�x��:3�Ɠ���6u�\7M�������0���-��HO �YP�@|��d���k��e�:��u,���w��.V^�PNUSդ�	,����@�`����KI�,����ȷz�OX�]��}:��/��Ow?ǵj 86d��A`��u&�d��[�ǳ]5�Rb���9�m��C�{BZ�Un�ҷrU�A�=��1�W�Rz��);�Y�$�	��$I�ߵa�~A/P��1��,�G��HܧF������7�$�'����{����"�`�ܯ���E��5�5>�^�D��C�k�o�t}^O�7�?�E"�RN}�z�`Fw/�"�I��#8^C_���L#=4�#�T��'9��0P�)Lǟ��A.l�)�:���y��m8,T�"-].��y���Ý�%���2�	�w��;Ȉ'�ă�P�K:��:��{�������d��#+����w����8g�<�e|=8�S]�� �����{$�=	�w��r9)�﷜��-�^,9<`�V8�ܕ��ܣ����F1<h��*���K���:
qw0�|��O]�C�cG��A�w#�4<L^n}���z�r=��Q�v������~�N�+�����h!g��;<�Gf��4�6���wx��|Z�tSK����d݃u����p�V�K��6�#��4{ɋ�m�����5�s�K���lÆ��	0�[c��'��n/�g��,z��ox9H�Ƅ������2*˨��ȃ��@2y�H��+�R�����o�r�yH���v'0��ū��$W�|���VAD���ʕ�x6k�`í���nS�����\��e�#����X�'��TK��)�I;Ma��H�C7���0@ߦ��}����OnՕ4�j��8M���I�#��A�k������hx��f�^�rRjbj�A_�gNʜ���*��r0m��?��A���ԗ��HM��H�m�p�t�#�^$-��ޔ�=xs�,:�Cz�?��M�8hq���W˽^J?{����G2چ����Q�p^U�PL����������IY)s�mp
Oh��0����e�KE{d����ԝ���[����6���'�R3����>`#yh���U��n�r�����x�)<���D�0O��U`�ʥ�(��h� `�ϲ9_����g/�_�C������=�R<�{{�L[_ރ���Kd������~w��.6�̋>�a�)��Ѓ� �g�.��P<���`/4ē4�]>�z-X�P�Q�[���<�y�}K��t����*�9]/�؈C3rq�6�I��.����C�A]�]tZzIC�q[l����^��+/︋G�#����H�{�#����ȂO��נ|.�I�:`��$F9(>��zv��Ir�<���dă�x	c��c�%�=b��侍�m�4��]UF�s}U���_�V���oY�w���?f�Akl�1��"<Zr��.C������!�j��ʣ#a��x�G������ ��&�G8�`��nP�HL�r}��}&��|C�O�RM_����3�?��ȁ�0��xH�V�A�m�U�~���f �3Za�����Yyǰ˓r�~����y����τ�`����0'�C�����AV�#�2��?�uc�Pҥ[���i*+�7�����+���+��*�ÃI�'��`_��~G��~,�&�Ov��@O�χ�/|�#���@<���>�Y�y���w$�m�t��K9��z�Y%�+U@��dj�o ���W4Ć�����۸����/¸UݰW�ؐ��u@[L������;"�Q�z�Ǹ��Q.)ǅ��l·� ;��m���f����0����	 F��:]k��S?%��b�A�k�ea��ۆc�$��7���R���`��k�H{{p��G(�(O`��k2�6hd޶�����d_����v��I�>��<�Q��ȀE0W�0������Xޖ)s�ߡY�,�˰����{����X(����U�2�4��Lʌ���a�ϵ`�Q�'��tM~�&)��g��!���G܆���.�n����N���}��c{w�f��'iq���ٰA͸v,�'k���iJ��Q��p����#t_�_`2I|D��K�rܺ	H��p��a�������;���K�^��Ez��nj�u�2��Ѱš�* �_�^�`����a��`�0�rN {��q���%����k���&����L�y6��,�fS�����(P�e=��;���zO��}�|݊�Ðۼ���Gx�R�="F���d>�=��2�y,.v=2�}� ��h� 6�[�-g�DN�x����<��T���},�#�����'ƒ3�[�{���O���x$�=����D�1Y�H��.lc����@n8�����R�Ͳ!�_üm�"<�3>��w�;��K��4���,x��.��an(K���_U��u�w���#B��Gf�؍@w�d��1</�{4ߗk�a��a��7��N�/�b��G7y_�n��H�G�ރ:��K�Ľ����)eq��a�ap����^C�_/����"��#�o�*�*AF)|%�sJ�t۬Or�?U�n ���dGlx�V`ұ{��y���C��#7oLn˜����_���(+���t��c�@B<"ꦺ��<�lHǲ?8>#�FG9�ཆ,2քc!��9��b���O�,�0U)x��bϪq�|�c������r���G��x#���{d�m8����(�=ık�U����n]����'�n������+]�������������\�      j   >   x�3���,H-�2��I���2�KMO-IL�I�2���OI-J,I�2�t�K-Jd&s��qqq �U       k   g  x�}Vۊ�F}���^�~yK`w!��.	�ղ;��F���|}N{3ny��)��r�TI��i�2�����RM�QL(ÜҢ�8�uģw�<N����O��ll�#���Ǐ퐉#�8�`Fs�ب��<���vw\�Ԭ��ty�<�1vq&��X������*��SC �+�GvQI�t`>p��޴�Z�,~;�9�qZ���x�47,��7�!u�o�|=�Ӻ 	�9gxμ��T�)-�$~M�5v���Ȧv$�X��*ɔ�r����Trj/���1�^h4�����0�vށu��SiIZ���!�R
���c�����A�K*=YΧ9�/���]$��`QE䢔��u;������/��>�)�$�!��P�-Ts P���Õ]����^ZT@����ê�^���ڵ�.�A
E<V����º���TI�;�����/i�����Y$ﭷZ��Sd͹��<4}�oEZv�+֘�Se1��|�rCN8�M�7�9"�B��d�zyng?�K�=K�KK+k�m�n!�R����6�)�Ѻ����m hfD���b�Y*n=:��kLZ$�~� ��eT��M!8�<}k@�O?��|{*	�Y��0���{klw�ڏ����[1�|x6Q;��\ک+��{DW�%Ϥ-����UKa��4C�ב�oP;0�[��*^�xdSˍ���^.�VB
U��cG�A�禰m>�=h�m`F*�55��%�7z=c��G!�. F�A�i���nډF�����d�+�a�����k��-J��L�@F0o8�:���6w�xL��ʆ��pL!J�\�"u��b�s�ðܴ�e2OiN+��BVc
��w���CU�\�W6�.خ�����h����x`�`�P��,��yI��b�����(�#+t����	�-�"R�+B���y���B�~��~���p�$V���a���a̒%A�A�>�	D�I�mQ��Û��|���ڹCN\��W��@Q��V��?_�;@_������W��*��.lƔ�i�n��8�0(�ōS[Tn4�%N��zmY���E8%�Q`��S�.ō�C�A��[� �F�!��jJ+���RQ�!������I�=�i��Za����9A#@���L���vkP� ��1��t���r�a�1��Ex��z�,Ն�!�1���l��yMy*ǎ68t4g��zhT/��F���9�L��Zf�Qb������4�p��.����F�	�*�~ו�T{���%⯻��PX��[q��q��+��U�u��@��i��y]�x;�s<q�^�����x ��CZ��om��ehV����-,5.��֏�	y�t�)����f����!��Uq��et��w���~��      m      x�3��M�I�2�tK�1b���� :��      n   9   x�3��̫�2��M���2��MM�,��2��(���K�/-�2��I,JO����� _��      o   #  x�]��R�0��ڧ����k��P
L��6L���M������Ҽ}���@o����$eK���}�c��-]�e	�f��0DNINLR�8�Q�&!�A�Z|D�G�YK>ireݑ�8��.�h>���-�0w�مls� Ws\;x��dT��k��-��8�{*�7u���
`\��Nll̒2��>��(_����;�Ҟ٨�qNbQ��Z�M�	�X��i���'�J�W���;-䑣�8�`��0E^L1ò�+������NU�ߪm69���J��.S�����@Kظ�=OjD9�<_�_�$�k��U�������po'Ӈ�*�߇'��h�����V��aU��bU�}І�*P>H�F��V�1����N�AC`�]��H�2M�k}��3���n�����_��&�W��x��W� ��_@��p� �d=��j0�G=��ІN�^�� ��XSg�u�8�,�e��\�y��S�']�����(O��[�fM�fs�I���@��N�ӳ��8v�X�:³=����������V`      q   9   x�-ʱ  �:�1������[QQ�Q�hܣ��6��n4�����j�������`      r      x�3�4�2�44������� .;      s      x������ � �      t      x������ � �      u      x�3��4��24�45 ��\1z\\\ &\      v      x�+I-.�,���1~\1z\\\ L�b      x      x������ � �     