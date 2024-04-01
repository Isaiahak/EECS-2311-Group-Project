PGDMP  .    0                |           thebestoneyet    16.2    16.2 _    E           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            F           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            G           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            H           1262    18277    thebestoneyet    DATABASE     �   CREATE DATABASE thebestoneyet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE thebestoneyet;
                postgres    false                        3079    18278 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            I           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            �            1259    18288    age    TABLE     S   CREATE TABLE public.age (
    ageid integer NOT NULL,
    agename text NOT NULL
);
    DROP TABLE public.age;
       public         heap    postgres    false            �            1259    18293 
   attributes    TABLE     h   CREATE TABLE public.attributes (
    attributetype integer NOT NULL,
    attributename text NOT NULL
);
    DROP TABLE public.attributes;
       public         heap    postgres    false            �            1259    18298    datesbooked    TABLE     �   CREATE TABLE public.datesbooked (
    userid integer NOT NULL,
    posterid integer NOT NULL,
    dogid integer NOT NULL,
    date date NOT NULL
);
    DROP TABLE public.datesbooked;
       public         heap    postgres    false            �            1259    18301    dog    TABLE     �  CREATE TABLE public.dog (
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
       public         heap    postgres    false            �            1259    18307    dog_dogid_seq    SEQUENCE     �   CREATE SEQUENCE public.dog_dogid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.dog_dogid_seq;
       public          postgres    false    219            J           0    0    dog_dogid_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.dog_dogid_seq OWNED BY public.dog.dogid;
          public          postgres    false    220            �            1259    18308    dogtag    TABLE     E   CREATE TABLE public.dogtag (
    dogid integer,
    tagid integer
);
    DROP TABLE public.dogtag;
       public         heap    postgres    false            �            1259    18311    energylevel    TABLE     k   CREATE TABLE public.energylevel (
    energylevelid integer NOT NULL,
    enegrylevelname text NOT NULL
);
    DROP TABLE public.energylevel;
       public         heap    postgres    false            �            1259    18321    poster    TABLE       CREATE TABLE public.poster (
    poster_id integer NOT NULL,
    displayname character varying(255),
    score double precision,
    phone character varying(255),
    email character varying(255),
    balance double precision DEFAULT 0,
    numberofratings integer
);
    DROP TABLE public.poster;
       public         heap    postgres    false            �            1259    18327    poster_poster_id_seq    SEQUENCE     �   CREATE SEQUENCE public.poster_poster_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.poster_poster_id_seq;
       public          postgres    false    223            K           0    0    poster_poster_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.poster_poster_id_seq OWNED BY public.poster.poster_id;
          public          postgres    false    224            �            1259    18328    sex    TABLE     S   CREATE TABLE public.sex (
    sexid integer NOT NULL,
    sexname text NOT NULL
);
    DROP TABLE public.sex;
       public         heap    postgres    false            �            1259    18333    size    TABLE     V   CREATE TABLE public.size (
    sizeid integer NOT NULL,
    sizename text NOT NULL
);
    DROP TABLE public.size;
       public         heap    postgres    false            �            1259    18338    tags    TABLE     t   CREATE TABLE public.tags (
    preference integer,
    tagname character varying(20),
    tagid integer NOT NULL
);
    DROP TABLE public.tags;
       public         heap    postgres    false            �            1259    18341    tags_tagid_seq    SEQUENCE     �   CREATE SEQUENCE public.tags_tagid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.tags_tagid_seq;
       public          postgres    false    227            L           0    0    tags_tagid_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.tags_tagid_seq OWNED BY public.tags.tagid;
          public          postgres    false    228            �            1259    18342    userattributepreferences    TABLE     �   CREATE TABLE public.userattributepreferences (
    userid integer NOT NULL,
    attributetype integer NOT NULL,
    attributeid integer NOT NULL
);
 ,   DROP TABLE public.userattributepreferences;
       public         heap    postgres    false            �            1259    18345    userdogs    TABLE     H   CREATE TABLE public.userdogs (
    userid integer,
    dogid integer
);
    DROP TABLE public.userdogs;
       public         heap    postgres    false            �            1259    18348    userpasseddogs    TABLE     N   CREATE TABLE public.userpasseddogs (
    userid integer,
    dogid integer
);
 "   DROP TABLE public.userpasseddogs;
       public         heap    postgres    false            �            1259    18351    userpayments    TABLE     �   CREATE TABLE public.userpayments (
    userid integer,
    paymentamount double precision,
    daysbetweenpayment integer,
    dogid integer,
    lastpaymentdate character varying NOT NULL,
    posterid integer
);
     DROP TABLE public.userpayments;
       public         heap    postgres    false            �            1259    18551    userpostersrated    TABLE     S   CREATE TABLE public.userpostersrated (
    userid integer,
    posterid integer
);
 $   DROP TABLE public.userpostersrated;
       public         heap    postgres    false            M           0    0    TABLE userpostersrated    COMMENT     U   COMMENT ON TABLE public.userpostersrated IS 'posters that a user has rated already';
          public          postgres    false    236            �            1259    18356    users    TABLE     �   CREATE TABLE public.users (
    username character varying(50),
    userpassword character varying(50),
    userid integer NOT NULL,
    balance double precision DEFAULT 0,
    email character varying(50)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    18360    users_userid_seq    SEQUENCE     �   CREATE SEQUENCE public.users_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.users_userid_seq;
       public          postgres    false    233            N           0    0    users_userid_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;
          public          postgres    false    234            �            1259    18361    usertagpreferences    TABLE     R   CREATE TABLE public.usertagpreferences (
    userid integer,
    tagid integer
);
 &   DROP TABLE public.usertagpreferences;
       public         heap    postgres    false            _           2604    18364 	   dog dogid    DEFAULT     f   ALTER TABLE ONLY public.dog ALTER COLUMN dogid SET DEFAULT nextval('public.dog_dogid_seq'::regclass);
 8   ALTER TABLE public.dog ALTER COLUMN dogid DROP DEFAULT;
       public          postgres    false    220    219            `           2604    18365    poster poster_id    DEFAULT     t   ALTER TABLE ONLY public.poster ALTER COLUMN poster_id SET DEFAULT nextval('public.poster_poster_id_seq'::regclass);
 ?   ALTER TABLE public.poster ALTER COLUMN poster_id DROP DEFAULT;
       public          postgres    false    224    223            b           2604    18366 
   tags tagid    DEFAULT     h   ALTER TABLE ONLY public.tags ALTER COLUMN tagid SET DEFAULT nextval('public.tags_tagid_seq'::regclass);
 9   ALTER TABLE public.tags ALTER COLUMN tagid DROP DEFAULT;
       public          postgres    false    228    227            c           2604    18367    users userid    DEFAULT     l   ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);
 ;   ALTER TABLE public.users ALTER COLUMN userid DROP DEFAULT;
       public          postgres    false    234    233            .          0    18288    age 
   TABLE DATA           -   COPY public.age (ageid, agename) FROM stdin;
    public          postgres    false    216   �j       /          0    18293 
   attributes 
   TABLE DATA           B   COPY public.attributes (attributetype, attributename) FROM stdin;
    public          postgres    false    217   Kk       0          0    18298    datesbooked 
   TABLE DATA           D   COPY public.datesbooked (userid, posterid, dogid, date) FROM stdin;
    public          postgres    false    218   �k       1          0    18301    dog 
   TABLE DATA           {   COPY public.dog (dogname, adopted, biography, imagepath, ageid, energylevelid, sizeid, sexid, posterid, dogid) FROM stdin;
    public          postgres    false    219   �k       3          0    18308    dogtag 
   TABLE DATA           .   COPY public.dogtag (dogid, tagid) FROM stdin;
    public          postgres    false    221   I�       4          0    18311    energylevel 
   TABLE DATA           E   COPY public.energylevel (energylevelid, enegrylevelname) FROM stdin;
    public          postgres    false    222   �m      5          0    18321    poster 
   TABLE DATA           g   COPY public.poster (poster_id, displayname, score, phone, email, balance, numberofratings) FROM stdin;
    public          postgres    false    223   �m      7          0    18328    sex 
   TABLE DATA           -   COPY public.sex (sexid, sexname) FROM stdin;
    public          postgres    false    225   [s      8          0    18333    size 
   TABLE DATA           0   COPY public.size (sizeid, sizename) FROM stdin;
    public          postgres    false    226   �s      9          0    18338    tags 
   TABLE DATA           :   COPY public.tags (preference, tagname, tagid) FROM stdin;
    public          postgres    false    227   �s      ;          0    18342    userattributepreferences 
   TABLE DATA           V   COPY public.userattributepreferences (userid, attributetype, attributeid) FROM stdin;
    public          postgres    false    229   v      <          0    18345    userdogs 
   TABLE DATA           1   COPY public.userdogs (userid, dogid) FROM stdin;
    public          postgres    false    230   v      =          0    18348    userpasseddogs 
   TABLE DATA           7   COPY public.userpasseddogs (userid, dogid) FROM stdin;
    public          postgres    false    231   ;v      >          0    18351    userpayments 
   TABLE DATA           s   COPY public.userpayments (userid, paymentamount, daysbetweenpayment, dogid, lastpaymentdate, posterid) FROM stdin;
    public          postgres    false    232   Xv      B          0    18551    userpostersrated 
   TABLE DATA           <   COPY public.userpostersrated (userid, posterid) FROM stdin;
    public          postgres    false    236   uv      ?          0    18356    users 
   TABLE DATA           O   COPY public.users (username, userpassword, userid, balance, email) FROM stdin;
    public          postgres    false    233   �v      A          0    18361    usertagpreferences 
   TABLE DATA           ;   COPY public.usertagpreferences (userid, tagid) FROM stdin;
    public          postgres    false    235   �v      O           0    0    dog_dogid_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.dog_dogid_seq', 1000, true);
          public          postgres    false    220            P           0    0    poster_poster_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.poster_poster_id_seq', 1, false);
          public          postgres    false    224            Q           0    0    tags_tagid_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.tags_tagid_seq', 48, true);
          public          postgres    false    228            R           0    0    users_userid_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.users_userid_seq', 1, false);
          public          postgres    false    234            f           2606    18369    age age_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.age
    ADD CONSTRAINT age_pkey PRIMARY KEY (ageid);
 6   ALTER TABLE ONLY public.age DROP CONSTRAINT age_pkey;
       public            postgres    false    216            h           2606    18371    attributes attributes_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.attributes
    ADD CONSTRAINT attributes_pkey PRIMARY KEY (attributetype);
 D   ALTER TABLE ONLY public.attributes DROP CONSTRAINT attributes_pkey;
       public            postgres    false    217            t           2606    18373    poster displayname 
   CONSTRAINT     T   ALTER TABLE ONLY public.poster
    ADD CONSTRAINT displayname UNIQUE (displayname);
 <   ALTER TABLE ONLY public.poster DROP CONSTRAINT displayname;
       public            postgres    false    223            n           2606    18375    dog dog_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT dog_pkey PRIMARY KEY (dogid);
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT dog_pkey;
       public            postgres    false    219            j           2606    18377    datesbooked dogdatepairs 
   CONSTRAINT     d   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT dogdatepairs UNIQUE (posterid, dogid, date);
 B   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT dogdatepairs;
       public            postgres    false    218    218    218            p           2606    18379    dogtag dogtagpair 
   CONSTRAINT     T   ALTER TABLE ONLY public.dogtag
    ADD CONSTRAINT dogtagpair UNIQUE (dogid, tagid);
 ;   ALTER TABLE ONLY public.dogtag DROP CONSTRAINT dogtagpair;
       public            postgres    false    221    221            �           2606    18381    userpayments doguserpaymentpair 
   CONSTRAINT     c   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT doguserpaymentpair UNIQUE (userid, dogid);
 I   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT doguserpaymentpair;
       public            postgres    false    232    232            r           2606    18383    energylevel energylevel_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.energylevel
    ADD CONSTRAINT energylevel_pkey PRIMARY KEY (energylevelid);
 F   ALTER TABLE ONLY public.energylevel DROP CONSTRAINT energylevel_pkey;
       public            postgres    false    222            v           2606    18387    poster poster_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.poster
    ADD CONSTRAINT poster_pkey PRIMARY KEY (poster_id);
 <   ALTER TABLE ONLY public.poster DROP CONSTRAINT poster_pkey;
       public            postgres    false    223            x           2606    18389    sex sex_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.sex
    ADD CONSTRAINT sex_pkey PRIMARY KEY (sexid);
 6   ALTER TABLE ONLY public.sex DROP CONSTRAINT sex_pkey;
       public            postgres    false    225            z           2606    18391    size size_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.size
    ADD CONSTRAINT size_pkey PRIMARY KEY (sizeid);
 8   ALTER TABLE ONLY public.size DROP CONSTRAINT size_pkey;
       public            postgres    false    226            |           2606    18393    tags tags_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (tagid);
 8   ALTER TABLE ONLY public.tags DROP CONSTRAINT tags_pkey;
       public            postgres    false    227            �           2606    18543    userpasseddogs user dog pair 
   CONSTRAINT     z   ALTER TABLE ONLY public.userpasseddogs
    ADD CONSTRAINT "user dog pair" UNIQUE (userid, dogid) INCLUDE (userid, dogid);
 H   ALTER TABLE ONLY public.userpasseddogs DROP CONSTRAINT "user dog pair";
       public            postgres    false    231    231            �           2606    18555 !   userpostersrated user poster pair 
   CONSTRAINT     �   ALTER TABLE ONLY public.userpostersrated
    ADD CONSTRAINT "user poster pair" UNIQUE (userid, posterid) INCLUDE (userid, posterid);
 M   ALTER TABLE ONLY public.userpostersrated DROP CONSTRAINT "user poster pair";
       public            postgres    false    236    236            ~           2606    18395 +   userattributepreferences userattributepairs 
   CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT userattributepairs UNIQUE (userid, attributetype, attributeid);
 U   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT userattributepairs;
       public            postgres    false    229    229    229            l           2606    18397    datesbooked userdatepairs 
   CONSTRAINT     m   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT userdatepairs UNIQUE (userid, posterid, dogid, date);
 C   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT userdatepairs;
       public            postgres    false    218    218    218    218            �           2606    18399    userdogs userdogpair 
   CONSTRAINT     X   ALTER TABLE ONLY public.userdogs
    ADD CONSTRAINT userdogpair UNIQUE (userid, dogid);
 >   ALTER TABLE ONLY public.userdogs DROP CONSTRAINT userdogpair;
       public            postgres    false    230    230            �           2606    18401    users username 
   CONSTRAINT     M   ALTER TABLE ONLY public.users
    ADD CONSTRAINT username UNIQUE (username);
 8   ALTER TABLE ONLY public.users DROP CONSTRAINT username;
       public            postgres    false    233            �           2606    18403    users users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    233            �           2606    18405    usertagpreferences usertagpair 
   CONSTRAINT     b   ALTER TABLE ONLY public.usertagpreferences
    ADD CONSTRAINT usertagpair UNIQUE (userid, tagid);
 H   ALTER TABLE ONLY public.usertagpreferences DROP CONSTRAINT usertagpair;
       public            postgres    false    235    235            �           2606    18406 	   dog ageid    FK CONSTRAINT     q   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT ageid FOREIGN KEY (ageid) REFERENCES public.age(ageid) NOT VALID;
 3   ALTER TABLE ONLY public.dog DROP CONSTRAINT ageid;
       public          postgres    false    219    4710    216            �           2606    18411 &   userattributepreferences attributetype    FK CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT attributetype FOREIGN KEY (attributetype) REFERENCES public.attributes(attributetype) NOT VALID;
 P   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT attributetype;
       public          postgres    false    229    4712    217            �           2606    18421    userpasseddogs dogid    FK CONSTRAINT     r   ALTER TABLE ONLY public.userpasseddogs
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid);
 >   ALTER TABLE ONLY public.userpasseddogs DROP CONSTRAINT dogid;
       public          postgres    false    231    4718    219            �           2606    18426    datesbooked dogid    FK CONSTRAINT     o   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid);
 ;   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT dogid;
       public          postgres    false    219    4718    218            �           2606    18431    userpayments dogid    FK CONSTRAINT     z   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid) NOT VALID;
 <   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT dogid;
       public          postgres    false    232    4718    219            �           2606    18436    dog energyid    FK CONSTRAINT     �   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT energyid FOREIGN KEY (energylevelid) REFERENCES public.energylevel(energylevelid) NOT VALID;
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT energyid;
       public          postgres    false    219    4722    222            �           2606    18441    dog posterid    FK CONSTRAINT     ~   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id) NOT VALID;
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT posterid;
       public          postgres    false    219    4726    223            �           2606    18446    datesbooked posterid    FK CONSTRAINT     |   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id);
 >   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT posterid;
       public          postgres    false    218    4726    223            �           2606    18451    userpayments posterid    FK CONSTRAINT     �   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id) NOT VALID;
 ?   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT posterid;
       public          postgres    false    223    4726    232            �           2606    18456 	   dog sexid    FK CONSTRAINT     q   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT sexid FOREIGN KEY (sexid) REFERENCES public.sex(sexid) NOT VALID;
 3   ALTER TABLE ONLY public.dog DROP CONSTRAINT sexid;
       public          postgres    false    4728    225    219            �           2606    18461 
   dog sizeid    FK CONSTRAINT     u   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT sizeid FOREIGN KEY (sizeid) REFERENCES public.size(sizeid) NOT VALID;
 4   ALTER TABLE ONLY public.dog DROP CONSTRAINT sizeid;
       public          postgres    false    226    219    4730            �           2606    18466 
   tags tagid    FK CONSTRAINT     s   ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tagid FOREIGN KEY (tagid) REFERENCES public.tags(tagid) NOT VALID;
 4   ALTER TABLE ONLY public.tags DROP CONSTRAINT tagid;
       public          postgres    false    4732    227    227            �           2606    18471    usertagpreferences tagid    FK CONSTRAINT     w   ALTER TABLE ONLY public.usertagpreferences
    ADD CONSTRAINT tagid FOREIGN KEY (tagid) REFERENCES public.tags(tagid);
 B   ALTER TABLE ONLY public.usertagpreferences DROP CONSTRAINT tagid;
       public          postgres    false    227    4732    235            �           2606    18476    userpasseddogs userid    FK CONSTRAINT     w   ALTER TABLE ONLY public.userpasseddogs
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid);
 ?   ALTER TABLE ONLY public.userpasseddogs DROP CONSTRAINT userid;
       public          postgres    false    233    231    4744            �           2606    18481    userattributepreferences userid    FK CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 I   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT userid;
       public          postgres    false    233    4744    229            �           2606    18486    usertagpreferences userid    FK CONSTRAINT     �   ALTER TABLE ONLY public.usertagpreferences
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 C   ALTER TABLE ONLY public.usertagpreferences DROP CONSTRAINT userid;
       public          postgres    false    235    4744    233            �           2606    18491    datesbooked userid    FK CONSTRAINT     t   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid);
 <   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT userid;
       public          postgres    false    233    4744    218            �           2606    18496    userpayments userid    FK CONSTRAINT        ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 =   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT userid;
       public          postgres    false    4744    233    232            .   Y   x�3�(-(�T0P(�W0�2�tL��I-NN�+Q0�q�JsJ��R̸�9}KJ�R �f U�\&���y��E
�
�\1z\\\ ���      /   .   x�3�LLO�2�,N��2�L�K-J��I-K��2�,άJ����� �5
�      0      x������ � �      1      x��ے�F�-�\�
��Z��k�tHV�j[j�X����b��p�7A�T~ڟq��|ə���d�xI@r�Z&�*��̙�2���}������?���v��Fi��h5)Fu1�}�ʛ��jT�ˢ��j^֫�"/�b����r5���at[-��z�ԓ�>>^���G����]���T��8����X5�r~G?_^���Ĩ�OS.��t4��5|&�ߌ�f^���ѫ�j�Rbҿ\�W� |3�����
d,ǣ�i^.G i5z���hR͊�n�i��z9���������)g�]Q��Mu'���X�]�+q��'W���X���M3�/G%H5Z�rY܌n�U1^����OǼ�����~B�f�8e��>)�vA������zt[�o�/�Lk]��a���f>�>fnʛb�*W�xw��:�?�K�����N7�4}�i���0yz���8c�b�_;�T�D��+x��躸+��̏�#���*�	��U�ϗ5L��'f����A��y�0�)�TS�/x����U�X�]6sx�%^�ߘ�u�hV���{��r5�.��M��9|d��4�'nq1�Ų��xףy+4���G~HS�_��U^N_>�ww���iu���Y�Bp��,�W&�{�?��v����(����{`Y�a�d��9��1L�j��Up?4wwe>_��G�.�qS�6恶�]>�Ǵȋ�x�q���ŴZ<EU=�`�-��z?������7N[C�%?NN1�Η��i�)��|:�o>�jl���R����)�yz�� ��ʡ1�qV�/��X��L���C���|7� #�@��9lZW7ŴT[�X�,���Q��j�^p���>�������
��PfH"��l�TVo�滜����f����4�4VK ��U�}ȧ��
W��~B�GIGE	_��ȗ/�)h��5��5\(5w��#H��I���h����[Ч�?*�Ӣ��Y�ϧ��K��X��%���
uL��j�gqJ�yU0����-�����7�>I+W�M���?F���a����qVKZE�{��&|n�I5m�����ۢ�)Q/��X(J<��ՠ(��Y-ae���ϐ���7xS��9�|:�@���6)L6��[�=��4W)��D�:=�rL��5��Q?h~ǋ������,-2nz��չ�,��+�c��x���_��c�
�~Q���b�rZ��C��I���\������`��hV�IN*r��V��ˢ��v����7���<�;�z��#�3gp��6S���*Ou�:D@/�W������^Y֋rY�@I��3��Ũ���D''��R�x+p�����;=�E;�u�洞�sPh^���-~J�n���ȁw� ��d�E ����	,�>ԉҖ�i�݇_7�?�y�����@��)Cu?g�uʳs�w��*�J$W��&��V��2������)�!��6Y�P�6'��_�Q�_7���SX�ʮ0��5����ZL�u'̜7�6݊���I���s,'��|�T������Q�C&�tV�J���"o`����R�[<	��x�*�S���}�'��l���R��rY~ʧl���4q�X��`kn���9���y��e��a���[������z�Iڴche㤠�F�����ľY3ip��!����i<M1�1����b�N����h�iH��`����֋7(<�����c�
N#����%>Bx����a�6��
��?r�W�)���i���\=��KgY�1˓_��o��j���%���������=�>Q�//���&���qm�����|�H|� ����������	<�1�z��V+�`�{Ss8��<��n����Sg\USV"��� >FX=?(�T���rb[O�ؕϞJ�y�(x]	�Ȕ1xe���Z4x��4hѣ��n$�`��P(�lj�a2��.�/^)��K����U4#Z@�vW�A�,SI�3��z?�9��Lqh/���!viY�j��<]��"����_��y'��$��k����-��H�=T��Z���C��R�1�.���M/%眆�0i�2�g/�;�m漠���)n��xv���C�����ZY-yqnYt�LO*�~nv%b��r�z\�{ᮅ[P7'��IVd{�N�[m=7`�=ڳ��6xMq��@U��V���V��k���9�m��v�A��G�ղAy���'�Gy�,
AԫG8�������	n�5���ZL�r�c2�־�g��%�mU���m�����3f��������BYg�E1����4��n����ɹ/��ޡ��Y�(�R�v#�TM���� �w�A��`b@��G�ˊ��ـ��I������n���8�V�#x��b� ����FR���n�W�O��������H�#��z^�ނoV�Z�j���S1��j��U>-j=	�cpQ�&|D2�IkM��� u��g�Yz%W"�~]b8&�/�"��'|]-'�jx�b@�K�0�{�� #o.�0�D�����*�GT�j�Y{�����2�kpXf^�@$>	0�f�I�*��f>���(`��&ծ''x�,���{�l����yVG�j�B�� o��~2�
�P�T�u1��)t�a*`?�Wb��2�����*j4ٜ�N���*.d9�54yֺ��UZ��K~�
�aT��,�:�R�IF�������	��\,�Ъ�u�)�� l��3�h�g�]����j�K��%M��K�|P�JU��r��
�;��jgM�����=\J�	Zrm{�Ol��h��Y�5:ox#�p|\7�{��	�i���J=R��?<7O�	\��dt`���a��y�*�0ϖ��	0��g1՛�޴4ꦽ�v=�3i�_�ؙS���&:��?+W��Q�ܔ��P�ޱ��20J����"�Q�����#4��L��@�֏��ōX?�Qپ�'*��� �ͼ��Z�>u��֤��XPPZ�P�*�|��g������qm���q��x7�o$�9P�MF�0�o�fBe<�k���	FxHu
���-���x+��ө��}A�FXj���;����Y��zA���I��X�����C���~�y���?������jsZU�s��)��Ӧ_�i����<�g�_��/��3��y���	��~S��_}��w���@n��֭�b�yw����7+�*P���
�|�? �R�(6��׫��j��#�������'�0�t�������gf-��|��NR�7���T�<�09g��<Q�,Ts�����{Ktm�ph 3�b�f��L�LѺ�����{0���ZM`�ܷ�K��?2q��6���+)���c�u���w����5�X�^*f�);�x>9���@�|�����VZO�p�1����ۣ��t
s��aڦ�+����W�)T��n���XPz�TMN~]5+��V`٢7J�_0�I�9�h3?3�qhL�c62 3�f3��c�ʩ�D]�L��"�D�p~O���� H�c�1��ja$W2�~,�(�
/m�T7�b	�z�c�༐3��e�49Gw	�Pܬ�D7l��c�V�����5o�O��if�l�U����ġ�����r(PMf�溶�
��'�B��U>~V�G�%�e�,�񮽜%��y�%��+l�fW2oF�a�u��x#��BJ�v*x�	Z����OU}�L�caK%v�b�^��6�G�6���Dj�y��;*k7D_���'�5���/g�y[�Po��D����Ƨ�>2+}�IMqZ��Q=o����#��i@�C1�M����À�?�Q��c������OU��l�S'��v�y;�զu���ޯ`�\Row��>L�c�)��l���-av
���"��^�k�f���~AH_�\�nh�sPA %�2-;M���ں��a8/���N�W��t0�R�ٖs���B�
v�0��6�+/F�0�J�8Y(z.M�0a*a�޻�q��Fo�%��g^f/�1S_�΋��7��+�2w��4<�W���/�p!���5OON����͛���yg9c5�    8k0�Å�Q���g�K�Y�D S�����P�_�#B���Sj�����v���L<�����@��o볠|I���@�s�9��``��3P����*�S�,��;��'s����?��r,����0/[@�)~ �W�?����#D4��V�����45*��5�U{�Χ��>ñ��^�!װK��H(	�F�!� ��[�6��_��dwN�Eݧx�p��$C'u�/��I����?o����y0;���?������+���'[n?3�:��"wJH�q9��n����f��A��I�
��S���
-��y4��n��fM;%�QGK�	ҫ0��T�j5��QWt�Z�t�Ϧ��@�MA:u�A���\� �抮a�r[��b�az��oUM.-���8� ,�����I(�[O�o������Z�J�е@�6P�vG{=}�J݅S�����������7%��)��
�ಟ�8��\v	�|�G��K��#��ȋjZݡ�Y�����bz;���9������D܆1�ٴq��f��=�P�V}�^����lԫ���	��%g��
(�5�� �=��Vx(�
>����:�Ŋ��^$P}T���>Ή|b6�������amW�`��,c�K+��/p�"��/T�1p趺�=*aD�x��
#,�ZіZ�{�*�Nq���*e�"0-����>s���}L�����KU�R`Ae�[����9WS���Ո0ic�����Zh<���St|�{g&�#�<a)��,�v���`;8?���&H�Wq�}(g�҅.�6AV`A�U`ݢ�4mf8C�t%X>�X894��Q!�k�����r�-QÜ��S^;�v���`rࣳ�L���E��QO�HJi;��̕�֗��vO�
=W������7F�Iqr�ߒ�:n��Ý.ׅA�t��ހk>��\�/A�ѱEA�|�-1>���H�ly�#b{������oE�vB�I�yFWq�+��Z� �T_��4Τ���W�cw*;nhA��<"���8/q��3�~�r\!2��{S�	fJ�C6gH��Y72��I�ǩ�SEf���$��v8�o2ۄ�������^˕�t���AR(yN��߿�B��}UF.«�w��|��-l�vF�+�,І|"����?#,����P: g��z	���Q�Ȋ4�\��D��n�v��O��$�u�5ʔ�Wx����8#zs�qw�1!QO�b"۶uOTb$�����ѣI�&��TSbvO�&|�\6�5I���O�:�/��<����ቶ�Mr�Jy�aYo8�<��e'c��NNb�L˝ �Sk |�V� ��ipX���{��-tcܥ�i�����d&~��Pap/�C$I��6?�Ii��ݷ[^ڪrK9ɼ�\k�Bp��p�-U�ȱ��f~B�Ȧ����\n \`lP b���U1�Tl�E�>���
�Z�Oq���s�e�uԁNO�i��X�u�~���:��7?���*�V�0�J��[�y���yj>bmr|2�JC���5v��0��F|���
'~�f�^w�F^:�6�j�t�ؓ*����!�4���քmY��D�������$N�İ�(�^xӛ� Is2��߾��M}s���z`g�o y�(���X��W0�u�١P;�twjN�h��mivދuۘ�Ժ��g��5�K�Veh�@��L�����Y���-d�;��#k�\j�4)E�X�����%m>z��䰘�΋�	9*4�2�}x��{s7ч�d��t�&.��A���\O�>�q���>�8sb�����ޛ���ͽ�g�:K����m�>Ȯ�ІD�b��J�������S]e��Xd�����UI�ՕWYlh�D� ���HL��#�Y����?��^�n����(%���+�|	���rN�l=o��[�t�}D�իvLSm�VH$G�bmi�A`l�B��vBc�f�ߔخ�����+x`*�^x�d �7�K*/
�D*�u�!	���I��k�j�Az	�NT,��d���WH�C��N�4��JZ����4`j�;t4xT�nu�8��#�,$n'� ��gjcQ��\�sm��%X)t:�7Np���+fڄ�1Zm\b��_�+,?����bR�)�߱%;����ݪ9C���]PQ�I��4�K�B]U���XJ�}������lL��0x
~`��y<�"�#"��P{H�cu��߅9W'"K��h��"�bD^g�`ͳՄ>A���r<��ZI~`��$�S�����T���Y_�Ci_��"���}���ǆ�Uހc�$��a��(��C B�j_�n� -��+/:C}���n��z���Hv;���dGTP��S�����e�?��ʚ��}*�$����MG�I�
n�'��:6���p��0�L����L��5�rOˁ?t�`�l�p��'D���$O	_�lƒ��1c#[�M�R�&����R�rZ�6��?�4%��\ɹ !B[e��1o����:� SƲm�h"���K����3��Ǵ�zQq>Ibk"5�8���	�`t�^�UE�)�O(lBMGts��p��g�e1z�}B�W/�Q�Oo2}h�1DA;��5ƼMq�WHYEg֘oU@����3	7�s�lw�lB��	��s�W�uJǇ���`��Vs�°�����D삒$4q@��?�Y� "�ٷ��@*e��JڵL����/fJ�5�\gl��{��?��wX��.{`仢�����i�G��x
��;���#ȜϏ�Ҵsl�0d�(k���S���Ն�G{Ձ���뢯�m;E��#V�}ި~���v� ��$]b������{�8�w��&n��H����s���"��㇆�Z`Q���Ny}�����`RP��uqɬ��V(s��K���޶�7-�jV��+f,�Da�.��T���C>�F����pq7�z�b�3_YTG\cA2��O�M5�4k>pn؅)ۋ&�U2[��(�Ȩ�["��t=�+R�}�-�Vo��Ȃ+�lL葮>�5�6ȼ�b�uN�X�l����Z���8�Ɣ��z	�oC���"���=��Y��6-��'œ�=���Ԧ[�a�׃�CH�}߷�(�k�� �'�����^�cOcgv����I�.<u_�2[������I��J��nZ4���Q�
�_�����r#�9n�i	3�G��<��M�k�vZ.Ԝ<�L�Ad��	l_C+�V�
�K���a�����k��1L|�ao0E�qm��.�"2<��o!�'{����h�v|���ȥ:�&h�
4����4�ב�����w=QI��N��b!)��L���j`�cDנ؇AkR:U �5ҍ\���^L^쐖Q���|��z_����A���~����j�p��kZ��K�nd�/W/W�bA"4sը'�@7R��1�����@ip�ݖ� ����rs�k��6���|6�)m/V��(��~kȚƗ� ��,�!�t_]A�uT��!E��>{�z�
zO��<�	�P�������fGB�f�=��I=i����0����sA���s�
&OT*��T����|�B��<& 䏼���m���z〲8��@��PP�%���9��'�H��ņ���O(��4LLl/IE�+�����f�ЛEP���0��*�<p,�0�~ �n���q�:=u����NX�Gvnf��Z�lI�O�����@-iL��M�
Q��c�����7�r�k�딚T ~�嵸�jB�7�'���mt��q��l��JD�����W|�?� I�qyzb��Pa�"��5�>>�l�
��Ԧ�͐&�K^�Ԗ�S])6�ZmVZ;���ޛ��`��uK��FmSڐ(�D$��n2��`44�{�����6��� ��hN����)X���)\X��Ey�+�|�����Wb٘�]D}�t5 ��*�o�\C�VK�pK܇��}^#���'��Y4�n��h�wD�ž���D�ċ��ٕ���U���Qb&.`����ۏ/zJ��
���$�� ����o��<L��M8���@    "��7*��0!%;QId���j�����2%�b �`J�n�/aQ(!��8�^OT��r�N�`���鲬w�t��nA9v��r��L��5�~*f �!M���4�e�y���q�9(-� �Դ�]7��lZp�y����Nnz��������H�VV�6>ɼ�t�*��羄�߫��tAlMH��;�&��K��S��R�w"L���U�a��*1q���,�	�c��\$ޱܻ�i� ;��Z֍�1���9Q��*;�{��B��X]z?���=�Ƭt���,���P._"�����jEpVw����@?�@w�I��^cu���k+����]:V 6Ϊ`5RXWeo���|1��n�1�[U3���Β�{�e�T�Z}=���*�[Hb�0�:�`:ۭ5H4/o��n
�HU���2g�G�<A%b�FW�h��`���h���U�?��tkOL�f�~�X�P�D�<&��#3x���~R�Fv�(���$�X�)��7o�k$�M�]v ���:Ik� �'�3-��z��$�C�ԚC�4������I�m��gH}��B�Ti����X����t�I�zTQ�E��κ9׈>��I�O�<^)��$>Q�-�[	3,/N��Qp��G��W�󇪉c�h��
g\k�m��ӳ�(����]����V+��`��)T!E6��^�5�Eg�[6�L�|�t{�����@��)�g���y��7C=Nw��xbL��"���6�04�i�
� 5�;�OLN�����Ǘ��5�����"�ݣ����x�̱=�Ǟ�V_c�ӛc՘�%O��HЭ���P�f����"M��X^�*�o8"�&�萸iA't�t�f�
����X;"���!yOc�$.l����0mV���@��^�/_v�ڠ�.Ȅ�
�W8���5ڛ���iBĦ���_p
d�SьE×�@%�	��F&me#ʃ��1Q����1�b��EAY��Ir��o@3���$�L�}$��7^F�H���e����t>{ɔ���Me�w���%�iL'PV'Nzo�i�é�>��4�g��z�}�>:����u�͕ػap`�eU�/��`3��|{-МB�%V��Al��̙����'�}C�㞾�N?O,iNG�i�l:�>2c��73<z�rB��˙�"4G��r���Xkqy�\��R׬��[$G]0_��D����a��oe ��;��C�]�**w�׻Q?���#
��OM�h�GFKTU����ݡax��P�6"�9��_��7&���v��{�&3(�T!��]Kay��S_����zt�/��H�ڼd����s৚؇�r薵5�h/0_3Bc�&�$לڂʖ�n��6W5����)U��\'���b�e?J�L��=H������-�~e� ��/�Lc��`����u"T�𑐵V='��0�����#��		�A�G׍�<�n�Ǭy�N�L�Gdc��}����w��Qմgjv���oϨ�c Xr �mQޔk���*lIu����	�-!�^��r��C笣��@�ޏ��,�7褫˧��+���*0��N����D�wh!�d�є�Ge��ʬ��Y������X�ӿɧ��8��	����
C�k�%wq�?j�f��ڎ%3y�1|K�%�*&K7$�#%���Bų��y�m�WV 4D{�f��l��~��`�"x��[�iڢA!���LKX���+�lLf��}'���"I�?å4�RE���?<�LM�Gr]^ ��5��j���� ��j��VŮ���z���x� >:Sw����'!	�����@�`�.�3\��bݓ�֢�� ���ܵ�CXI�$8�Ld�SA�{��'�:�����/��o��@~a��I�'��������, ��|$�[�bx?,�93���w��wp�ǣվ=�TI����>��r����ƺZl&P꒯ �"H�9	�P�lV�Y
�X�h�Ad��k�-T�۩86E�N(�@�A#�{��-���#���g�v%�Y��"������ �+��%SޠL��	R離ޠB��>%�I�΢EAj̀P���kY�W�c��9 ����޲�z�u�D�����ۂk ��mA�T_ף�$Xp������"����%I�&8�zS�*��{c�7�Zrì������~?���2��n5�Z������.L�3���m�o�@r����%[q
}��C���pe�X۩:Խ�l��N�����/5�:�N{��;��ݴ��{��PLI��;����"B��s�bSh�P|��>���E+�q��&Cd<���Z���Kpn���|w�6��m�"�/"�1���.{�}UnEt�}%��8b!7U
d���v����gꉏT���E�Kf�dj�rr��'FR�(&|����֡�̨�f�%���Q���B�
F�!��j�vкs�*#"�+l:������C�A��`��������$�é'P�����-1�:���Z#ǁ���pG�N���#��ֱ� -z%�т0�X{�ht8
ʇ�`�6�i�0�=����>��]�'��f\p�[*�=>]����9Zu%�gG��T�9��&���U�oz�[r�.t�"���8:������n�ξq@O��{S-���H����e%p�YMnϫ �5�彦�D�XÃ�i�SUaeJ*�芄���#&.z�����XL-bS!���{���9v��w�ިg9Q��D��<���H�^��r!� �����~R\cA�3�6�c���C�)�.#Q�LA͸�"��t3��-@5x: J��a��b�բ�C^O��K���9u�7nu9�`V�%ɴ�`������:�P��O�2�(���63 ��-b}�ϲ�b�f0o��3=�>�7�*����jSy��m���>�b��QN0f����E��'����Xx����}	J�#�g��5C�x�`�׶ �f���MPu�
��'�_p���XhgO�1��.`eN����7)�׏6d��đ`:�`	�s2 �����$�n���c9���Z�-�ƚ��V2w��4�O��}�86���jR����5S��h#'�%��n�-�(?���g��Z��z+MZ�_��Ʃ�[>�m����$��L����S�%Nʜ����{�Y6b4ا4K����P3B�|�C	f
������R�o�	Wv߮�Y��0�Dx�5��bE �����vS>��7�������@��G=,`�����
���"O����eJ��p���%5&�	���5	M0c�c'yO�Ƒ��j�
��D�O�]C�(��s-�ݩC����H�4���{?�^7�t���_�k:�,�Tj2y.|��d�	^u����&��6�tKR���LN����Nb��^?h%�H�d����bg�a�,�S�{ބY6��7A!��dn���E�b�C��xƨ��m��}�v�EKG���ȍ�˅<�����Z�u���*K�e���=,�)
��zr6��N�ڮx+��ʷ����Yo�ݡ����ތxz�?�Tg��VG��4K�KɃ���7�t86��(�a@��:���0f���Z�#?����T)	w����� 7&�<������OgB?��-�c��4VLE��CC�N��h��YO��z:�sw����O�т�^��/�� ���[��
�/�ڤn�:��I����M�O3�HUӛo3�����Y���o��a��P��9���D�)�bd&�2��Z�/E�Wu�>�&ȕR`t��V+��V���7�usp/늞�ƒ��(�
4�9��F~P:�^UT�J��"o��w+F�N`,�!��
���*s.6	O���IS�9��O�ydB�C��L4Yh��-vke�[�$�#�EF};��2�n�m�@nm���s��Ũ�ȭ}Uf]K���-�������B-y�.b����?v����q���.��w��:2��/�`��� 1`�@����8ifpn��j�#�(��v_$u�@ݠBȼ�^��T�P��    ��m�kX̣��F��c��&d��M�f�Б�cL�׊v��v���vL���h��C�&b+8�'}=b�=k�׾�$u�˳�e��I�0�
�#�����M��&��hI?���e�b���#�I���������/���ʜ:��/�	s�S'�ƈٝ�~�Ű��K4:tZ_��a�d=(/�/���~YQ�vP�l��+е�c��*0T��^UϊnQ�����A���1q�ҸJ]��s�՛���L�j�R� ��}�\�Mr�vQ���6{-��Ǉ�ou��;F��n��
������彰qy	��������Ш�t�Q�/�������Og�i����Td��(�M�\�]͇��B<���:lt�[֊���%�>�}E6�G�?\��1?~AC'r�)E!���'�jo�Ϙ'E$�p'�-���ҸA��)��D�=�QN
�=��@�F�20�9��m ��K�+��5,mߢ�xI��vb$����*\�ak��	~�?��D�SXBj�֢������E�7����6v�8I Q�c�b8��Q�.M��na����$i��x��� ���k �,���R�d�Ub@�n���Zz�6�'l0�ȗ�m'�1z�؁���+>�णɅ;^V״Q���U���������'_`$E�D�I-U�5 ����'�B�խB\�`� ����Gв�1�!R��oܔ��Ҋt2 ��{�!E`T�*Y�"���SbY��A�0��Ө0��E)�+���$���b亻�-D��=E+^ ���|s�NR��:#/��:����W� [���xt��S볷;.����B��@zo�<�XIWg�u���7p4 L&�C�GC��j�}�z�O����o��﮺ֹ����a�����I�a8�S%w��S�[�
�e�:�(�FrG��������`�C�2�^�f��-�NI�r��S7���f��v���[�����_�s s�?UIl���c�� �?B1<{�^5���f0E}�3C���7��5�٢JQ�y�@��v=�f7�u�Td���k��6"'A�;�{�p�e�}+��fwI?�ډa$���)-L=��]��Y�Ld-���FQ3m���Ci�����7� aL�Ȕ�����a���jL����3|&5����	�v�'+#����|��+���vC��>�F"c��]����	}�"�ӆ_����g֓D����O�E;n�h��~ �QM�Prf��A(^�.:�:Mf"�v:L4�m��/���� ���Rd$1}�����dtC�T�{�x���R�&���Ф�����t|�\���'�d&�`��d���:����x%L����q�7t�r�/��ϡ�w���	���A+o� �ؙ3<�%xH�.<~f�P�,�0��]��o4���v�Nl�Y����5��P=��I�y:��{rǰ6j^:��m�F�3+,�ax��X��tɐ8f�k�2̢k>�IAq���m+��c��K�LG��c�M�דA�#M�Ϊ,!��_\���ڀ]|^���jC�(���g��S�@�q�M��a��Y쎣�l��3p��D����v�+��	��BFF��:G��ͦz8:������"�Ɋ�,�}'�wxڈ�Fׇ+9�r���b39B��� ���OO��<&��T�B$���u��ť��P�?���=U'��d+ʙ���9?�b.J��E3�"��Dl֨e�3��mD��Of#����p�0���
�I�\5.��z3a�H"��Q���鯍�0姢��$�	��a�f�Q�͗\x�{s
�3�)0�N)09�Q@k��2��4��h����>pt�[�͡2�b;��������c!Z�^�Aq�)>g]����47�'Fc1�~U�a��N�A�N�*�R�d���j��$a�q�*����R�+}��8P-�?����i�3s2��E
�,�����q����c���N����q�:,l��%&WpǪ��)���l�$�ޫ2���1(���~����d`q���,h�@��8\�:?j%�<&BŁ��{��oD�p�"ՙ�}qA|bh{5rm��c���z+�u{~���Yۻ1V��L8���F�՚(�/�	�9lZ]����*&�ڍ$������"�}&��q� ���F�x�˂���u�zuk]ջ$A>�9���^���x�X�W㽠6ڇ�!-8}��,�l�������o�����ZxJ�N��5#9���NIn"�7��n�4�G:�Ke�h��\/#�V�yz�%AH��V~��5z%����3=@~G��|���R>��h�v��B�kh��Z�+\N�|�[7U���f\�ώ��u��#�6r�0L [QNRO��aQ�Q����W��s��a���F�e컋�R����"`k��[����Kw�iEW�i�� Ec�$ޏE��c���(�A�G&t�z�V��#��koO�.>��#8_"P	F`���������(�v��-��8�m}	�;ج�ˁi�8vL&j�"Sa1p��ss�l�c.M�Mr�=��O�Ok�	�}3���nw;�,ǹ��J��j�_�3t���@����"�d0u{`f�ř�nt�]jz�[�B4!ޝ֛EL��MB�ҩ�
���:��e3�L҆��4v�T3����5>�è)C�42W���_Ϸ��W4��LS�?1>���Ԩݮ�Dʼ�`T�9�uڊ�3��N��^T��{L�7N�Pd7�0��d�*�Pڅ+ܻȻ�P:+��#<�H���@t�K�]�a��5��w��$�d_�}㓠�����:V�A��pRT.�Ǳ	��wԸ�
��E����p�N��I�L�h~PmG޻�����n��);�Z�n�R)��T^��,��Gm��Z�ы0$�O_����7�f	���k^���&[l��U�1��mX@;�~	g|?_|��V���1zH����Z_i<������I��A���J��y�����p4+'�ZW ��7��"t}�Z�'��K�W���<gz��ˠ�������1�.��AΘ��P���Gc���s��iЎ�� j�}��M���A���n�Xǲ��"xʔ�0Ark��r����/l�of��,���`P7M�W�9���n@l
�=*]���g���y˪->�� [�ųZ"zus�03����2�%w���5F���O�0'��p��Yރj���1�(0�A���hj�(	�<��K�Jv����ϧB�NX3�c�5�ҏ{6C�Ϝ��N�k�U�'_�� P��͑f�0���L����+<��ѹ]x�6�s�&v��V_�Ҁ�ϼ_�ͪZg���ӳ�H��@�{��٤�~�~�wl�����Yl��m�҆����J��Qq��k���A�f$����^s;o�����Vՙ�S_SJ��P�sB!�1~�0F�t]�n�K�8l"�D1��2�Բ���3n�j@�)$����vҮ؀���h>��.k�B�K�q��c�dRaQ#��\/L�����5GDH�۴�5�`5������w����	��E@����?~���L�uu3�];ʩ�*��{׌)~/_I�=�ʡ��%y����-��A�#�hmhߗ�B��bs��*q�׬|l+Д�ʗXF�"��sei�74(�iiD;
��؆J���E�]� ;�&ea	Ǹ:�"�^yc�x�M_�6��,G��U�
W�����~,������t�.}[r���}�S]�@83HkM��kMɩ4Yh�=*�M`f�#�S���}s~�Q�Jj� ��v�T-/���Q��V� �U�������4צ�׼�f+��7�?������.Tk<��{O�D����o_4�����B�0H��}����b�+bʰ0H-s�+f�_�̽�G�V�u�)������z�y:H���)j�NwZ��VS?ދ�Nq�W�����h�
4�VuH��'�-�thyPЂ�7/�%o����j����P��=��p���K���i΀�?�����P��G��k6�    
N��uǭ�P�&�[W-߰���B�g�ЂAq*p��+��{���λ�`� �[�nle�R�72g�1_�(�7��a���p�
'�^��^�k�Z�q���eyzڜ�_��f��m��O��ʧ���2��Q�"ɉ��ebw��
{��6i`��J���W�[|k����&��,2�,��9�^�f���@W!�,���`����q��7�p<���x8�6ɭR/��lK��BN"�2�Uݮ���3G���B�N���xDlP,w��ɼ_VTӁ/�'�#����wl�1{2�
�X���(t;f����Չ��mr�sqwHM��7���4���"a����[�N+.�V���&��V-�1%���JХ�y>!g/�P�v_���d���s��@<��m���eQ4u[mDysE��jAt��9:AbK��r?"��r�9DXݑ�&QD5�&��g�8�.U���8ָ���05�7���sc�1Ѓ/(�ƅ2�Qi����Z,H4s{���n@mT��Y43k>���-�����%>C�#i�_Z����V7�h=��2�n��C��� !��C��D�6��P�뤥ypl ��h9�LP�&R�z��r"k�rF�ѣ@!���sTIwģ�j�c8@��vU拯��n�HU�^!�"	��,(w��wlf�h���HJ��F��J�3�_zsL3�W�au��|en�R�H�Ib@a���Fa���۸����nI~�<���:TF��8d� N�\�GGysCv�Z�NV�lx�ś/�FиC��1{uê�/թ���" �N>��U�KF�(6��-@.��;Lg�9��m��jh�$��s����VN��A�����|_x���3�J{e���/.�&msn�^H�{���"���~W�]���=��Xxo�e��<8��.@G�ЛH���_�i�~�>
Ǆ_С�@_wI�AZS���X~�������+�R1T�q��T��B�7�����O�+I}���7�
��dF�l�7���ඨ:��m�C����n�v"��A'��G�6vÉBt��P�N�ٍ��lTM>_��obw8��\��4R�C>��-�(xɗ�F Wj̍b{D�!Ӵ T[��)��/I�ܲ9L|�}U�8��{�Mj鑤�;��>�ɰ���L�T<s_���×E����;���P�{|�I��;�D��	��<&biJ�Z����ہ-�F�Q�L���)1e��5*ɤc�P�h��އ��S��a(aO �pJٙ�.���'t��u�Q��<�/']�wl��Әo��q�:~�Q�fkp��U\k=��G�����ܳ�����f�9�1��6�:�9XT�)w�u���n�P�7����v� 
�!$�*��%��0s�e=�S&��8��/���������h͔��������jB(�aVuWwH�M�|6��Ɓ�j���&�;$[t^Y+�����,��6�?h�p��cTM�i���S�uK-{�����ss�����ȷ��a�!���v�j|�c�$��ڞ���4笉�lP�Bǿ�8=է�BP�Ql���2��ܔH�o�Yx��٫-)	�EZ.	2�=��i#�CD\
����{A�,���@6*g>v0_��W�
蓣}�O��W[z]z����߶Z�\������{�N�S��#�x_���}	3�/�n������JPS�0���|%)����7RgE�,s'e��MT�3%�װ�Ɋ�o#�!��X���=��.8o��06�
�8?��&JZ����t�u�.â�AU	�u�ņ�2s��_��:�~�{D�Z��l�n��t�yQx��=�&޿a���?T��v�ۯ��u��?���eh�.�F�����獁%�t�t��
͙��y���E�H���7��)�bZ �/�("� P(}���{�������u>�L�l�����`��ß�X�<:��{پ��_8��tx�=���u=rX���Ad����̤�������p����� �,6��#pf�	|�k���LH��ЅM�KŴ����?��w�4j�O�7Z���o4��F@"Cg{?ŏ�hҪٰ���0ޑ%̹������<��4�V��K�c�;yFlf��B�yC����Dh5;��J'r@����M;Z���{�궝�L�:� ��+#_`J��ƜAN��ޑ���cĭ��tZǱ��
�d�9�U�H(�*�Ш+t�E��
�e����A���Q3�H��┤����d�w� :8�+3��э�/r41�rxa�ev8&��$ԓ�b�!z��e�ޞ5�X��F0C��2o�*J�#�ڵ����̑���T������{�,T���F�!���S���լ]� N���j\0���Ͱ��bVu:���{5_!�<1J��%+�2��	�
/�Sӧ�+7���F�>F�0�xRe��9!��� ��"#�xl�+�جmĕ�Ց4���m	5�q����v���7Fھ�c5Ŕ��v��O�g5�9TqI���������86��,J�I)pب�1�f4�R�@��e��!�'�_'Uq]�ٰ�?t����e����!��&��VDO_gscuoO80{w720A��[lDBz?6ؑ�x���p�[gp���٬ �U�)J����(U��-UW�҅���S�t�)*�6S����r�p*{�/i x��}���aCC 䚃�{���0��.tS�^�O�Dd)��������B#s� �X��|N�|��S�|�G���ٮĕ��{��YL��^��n^|cO�6��m�怙_"�z������p�[Qxx���44�r�u�y�y�W���j�(`'����eQ���::��|LO�^�c(��c"5q��Q`�јz���~{u����o�pM�J���l)

c�(f�|gJ�n��3 Uz"��0���A�Ƈ7�8�&Q��fB��	���%]�����T���٩�l�6wG՚%�1��q"�pgպ���^E?Y��24	U�1�'�*ToOԳr�o�J%�+�I��
mA;-�m�ZIR��Yۤ8��:m�f���Q�.Fâ8��;��;�YS�ΚAS�Y��>L��y��mO�H�"�{��Hh|�hTX�&,!��s$[h���-V~8�f���˄ʱ���[sK�2z� _[`���TN�W�V{cNw4q���[Ԏ�/��.��1�?�^�3��� �W7���*�"w2�~,�J�����O��9E��7����o2�~(��u��׭c����.?�[^�_+��(��B�V��O�3����˯��2u��r�Z�)��6�f�<	a0�Vz�c��D'$�s̡��m��h@]?�`pN��nd~���"2�^M��|���Q�ho�9ַ���9Ba�`��[���4z�Vђ�'v\����q��<}*��L�G΄���B�2���Aۼ>t�?�"��R���P�Z��W����حuz6QV	��-a"��r6������ΑB�@%Mpv�Hf�\n����o%}I7�GK^��`�����j��_�	ʷx�*e+��+&�o�M��*�hG��b�O��C�r�y�҇lޮ8}b'*��0L���)[�|�K�ޱ�=uk]a���X+!U�Ea��H]ՇC'k}8��!1�2\����Ҟ���(�j�~������F���X�	ݯ�����CV�h� 4.�OS�9�ooz�'iN��o��/"������ʌ{�^ҵ���t�T��a�)��9����U���c,�0��/�e1�-�u�����Ql1	m�W�I�ow`���)��!H�#d�l:�䟏���q~�:B�}{D��"Tw`�)Roo�}���Ӓg(`{g���u�W����f�'x�@v3L؛���c����ޜxSAiN���Z*P��X8�}�w�>IO׾@�8i�sl��}:Z��-K�Dci,�_m}�:�`��Gx�:8Fgज!権F�\�c�Q�8*��˼��qݐ9�c�'Th���f>l�n@oO�h@�f3 ���Q��LPx�|D-ܫ    ��O��;������D��	Jc��(�(�W�6��V�C �>�7����j�=1̿}��p��0�u��qꒀ����]^�@}��2�Ō+�'7���Rrw����)0�{��fz�kU�U�B��o��-�O�	D�-��b)��g	�$0$Ȉ�7�#n�}$���%�t.5����κ_��}"�{h�~n�(���U\��%$��z����a�����v�[�f��$��yo��r����zUpCu�2'�� �$�F2��3��Q-g����&��#��z�{�'d!;��F��:ޟd��Ie�O�/�Z(ˉ�E�7A�iܣch����N�ٓ��(�)�١ߊo�~u�5��G}��t�Q�x6&��&�!���������w�j�������i��a��H��{Q*���mYLo��.i��@�0�F��u5~z棻��(r�[Sj�hm3���|���n�!m�B9���R&���w�����qxPT�;h�q�ng�L?{�騧�ʀOC��d�S����o=�i�C�:�Fi��Z�R�-b����<Vw�F�e�	��}.�U�S���_��4����JW��Y�� 3��U�x2Ǆ������Cf��pȎ��Q�nƐW�|��TY^��������<G��$T�?�5�K�����Ħ6�-��a�J��I�AL��E<z�lQ�bJX�>���L��~"[mhջKY���/������0�^�~]]��A��	�P� 0B*"�m�%j�[S�:�y1�r�8i�_��n�� '��}ճ �yuO�_�ل�X=G�P����2۰�)��Lz����3l�KF�u��uQ����퓪h+���D�\Л�SW��)c�.v���ZCRG���P�µ�z�篕^�"��#e��F�"���z"�%�!!G���DQ����Y�}?-�$���b�S�ϋ�r�,��T7�Lv<:� ~X�"���WM�,ӱ��+��Y��foŗݩ��+w���_�,�6pm<o����|T?��jNN ~�6�M�h,�vx� @yA9=�[|�k$�HY��uU��\c�N%�͍� �¸݉#�^�MLH\F�^fڎ���������v.ԫ-����^�}3�[��K�T�� <�Xv�Y�ޡ��6���'�AZK��a�QI���@���%,�uh��O)5��;8i��[Wj��y=�n��$�[��!�K�f�{�#�a��Ra	c�(	����ns��Ś� R� �jd(�_��GihK \�\�GGysC�GM�123k����c�<�X53+Ol�?T������֬�;�'�C�6Z1��/����MYٻ�3]�ذ�`���C�	�B���=���0g�d�g���O�CqQK�T G�c;\ �*�&9ձ�.ǞtW���ABW�0_�mC����>������ʱ��$y��n���
�C
��^�T �ILq\:�3�}9+�})5J{���Lt%�,)�%����1{��2	K����BwB�������x`<� {L��E,����$u״G6�2\$R�⤎��*���P�N"�~R$M?�>��w�~2����=!�"��5T�/}��E�4�M��/n� pjČ�G�8��ܘ���t2:�H	�����g���mQ˛A�R��O�#��rI�p�T��T˶ӄ5t钀w�����'>��\�[:ZN7p���Y{�89x���b�F���]���4��*b�,�{��St	/z��F��.��F[/��᠎$��\o$typ-�X�������D+B��꒡�X�ВL��\Ґi��ND�M�U���J��E��	�����a��Z9��R�-��H�m����+�����k8G������q����x�Y�f(l��3�HNLN�w�Ңeq���7�x�b��k�����ځ������B%NV��-���B���/C� n�}|d��=�H�!�$j���ȲH1�0��kN����ŰIk�=�nt8�.��9���(f-z^5+
}l1�HZp���	�B_B��	
�]�%�l�5��A�x�N��%~ˮB�n���,�ǻ,F��<K8a'ά�P1� #�SH�NH�?�*\x{�K
���j݃�|Q~�Ԧ�� �i���*�֦�YN���ޞ�c�M�u`�:<�s�WHj8@T]B�K���*�������RX��͸plLf�������R�)��;w
��9wJ���i���˔�V���Ug8+���PVR�F;��Px��� � ܱ�y��;(O��0��]��#̅^��"�8Q�_J�ʸaiJ*�n��ǋo�홚gS�jٳ�YdY���xVa轣r�w�g�8���t&ZKA(a��ݲ��x��F�B�&=�9B��N�$�C�ܞ�$������[R���{�%AT�&q�x�fA�W�f'<{��x�ϔ�l��~�v'Xj��=������W [���e���k�]�i���^Ǉ޵,���\���fj<z��w�?�Wl�d��0%��?f�渿��n0�����U��}����"_.A��ß
Invlb$�ZC�y>�$�� R%�B���(��5��fH�s%�����f�}e�D�A�
��n8�*R�����W��C��L�u���*oZ�G��D�%����tقq3�`c��S�`�o߳�C�J�^\i��s�Q��l;�D�]�;����Qb:r�KR@Z��d�̲
U�7J���=ʑ�o�F
�%q�Zw�q��#K�{�ǱO��(<��P��~��y2�9:��b���@-�B=%Ia��T���+X	d^�E?y�S�c�p.0���e�$`�U.�9�j�9�n�M�)����k]�����Pn3�&սm�}�"�Մ���'>qv:�^��;T��84Z�M�*L�s�yn��pjTו��N�*8�m�k��q�Q��}��h�,�j1-T ֞8���A%�����T���\:^X)�]i�C�zKA�.8����'7I$��7�qo�����[&v�zKt_2[舽�T�f��|�4"��X7'�{8��!46��8$������5�@�")��8^��/��$L�Q��=<�3�u~�M����$���򤡉�K�H�͞9_��n��5H�U3��
�w��M�?5��=Tw`�߾�E$�n&؍I��;�3���p�QF���z�$f#��b"7!���� >�)m�uyD�I�cl����蟿d���6I"T�@�/�����Mx�7��cr�f���&\��9Y�X��!c����I⽮Pm�i]��e);ռ�9�)�va���0�$�Ɂ)�8kCx��H2�����Q�j�����\��<�u}R�9S�����e4xo���і��񖙎q��Ӂ��t.������yrO�}g,h`#\!�_�ŢX�<ث:h��J��V��Ts!M� U�AMsUq g>�**k�J��?.s��T*Q�2E�'�� y=�I��9�gNf�����B��6b��c��©�F#
h��E�`nK 6�r`Qy���S�1�ҐY�Z�T�j�P�³_���D��W���g���%H��9I�,����%{����VO3���oS�¶˶����b�&޻�޲�6���H�P�8ѩ��2[���fi�J;���| �1����5c��
�)�~S����_%J�7�,y`/�HN�30�2_wk����p��yL�Y�L��4���وu�� q$i����y8Ґ9lm�j"�Ri�Ɏ�@Q6d����}�����X���������lIw����D�A�}��Ո�@E�#=�67d�CGv�qz2��&}k�C�]��Z�0�qGF�kl�@@�:	
p ��Eq���
�$a��������d��,�KF�Y@�,ݩ�ᩦ�2׽5
t]���:�0�A3�o��1E��1�j A3_��5es|���p�L����
S⓲��VѰF�J�,h�wi�'��"�'�����J/*�����C���m�W��#�l��HF�ѓe��IF));�F�03l>�
3�р惛^���R�    \��v��$�T�=_N���'ڡ!D�3�j�l	��M|�}\�f�|�։֓CE����Zw)���B�W���&.�tr	�?��B��Yt�����r�#�<�M���OywGU��: ������Y�-��1�U����zԑ��r�'s���G��C��U'~�� ��f�B&���=$��	7���֢�k����6��߅��%��M���y3���7ul,I��8]0=d N�Jo��M�v�Mm���$Y�4�$z�e���#d�B�
 �W�0�吁�܅�~l�b�4]��Çd'�u��b.���C�9�Ί܇��؆.�nӤ��)�T�}�4��bʭ.���tשع�n�MDd{a�jFc��gq���h X�A�Nh*V�H���{��پ��L���,k7�!�,��#�D�)x���v�����=IkG
-n�e��o*��A��O,=���R�ޏK0���u��G���9��>'�0Q2>�� ���`=r�)��s ؒ�mQ��O뀸;d��E�n�Ī{��pM�5`�.�`Um�pl�a(w�$yg~�,3guȝ�� ����@�ק������3c�y&��Em��Bci���{�Cc!�M�E��gB��{S� ��zh�G�ЄQ�z<-f�Q&6��I�W�jJ�j��-�t;	��8Ru��I=�^4d��G0�ѩE˿l����B���)%,���B�6M�A��	y��-a��������y�?��(\$�լ;w��O����Z;��T��	Wv���:��uJ?�9̌eIv��(;�:��I��]:�%��v���F%�]�Nc=W�$c��6q�H�)���_�Jk*��pж�-%S�/�C[y��⥂����O����޷�,�����#�-���[sK�z��Yr�Q�w��Y"��@�3�`;�1��Yb��s�=l	�v�=G�L��K1��'��k�u���Q=L¶���91����X��J];i�/��u�É/��#�l�6��4�w��q�h�!�D=���U����sG�0�/Վ;��E���^Z3������랡�u�,����D{��I͸>'�����g�r8uQ���E�a��-������^�3�y����i���Z������؈��c�,�aQB���L���O���+��v����zBNO�~�︚���r6G��$6d!ק&a��Ŧ�BJ�����I�ms�����
��9��w�,�<��d�DK�F>���%L	5
�+���}�In�&�m����%^a�mt=����F��%��E�tjcS7\$�p%�%S>��(�Eٿk�A�t��e�҂>�D� ���x�ͻ3@34^��+���9D��e|5�R�$�A�U���B8S�ǢZ�m��i����,C�z����$�%S��E߈��i���R����h�e�Bq�D�-lo94�+�.U���h��$t4��"�Hx?Ls���Hї�<@D�]`|���\�'��-5�i��̎��5�H_�?O��H�*��7x4Q��V}^�Z�C}g�B�/>�,mx�L�Iy�O�UA݃�O��B�&SE���(6�S�?�N'��uD�KBv��S��mIq�(Yo�u���:�+�k�[���ޛ��7cD08ۜ�=�67�1Ջ�	0:�͆a�c�b�@�;����s�$�o���2�x4`�o��,��Eþ�i`9[��l�ˀ{��^ֻ��T�ؑ���H�������7�wo��ij������S�������%�Ũ~^tq�+n���J��!�A�ģ8���O�+������Q6��#��������Q�0��pIE�[pu�	A���=�c����X���6��wX������a��t��Y��;U�v�$'������1$ʕ�&XF�T5��|@��D؂�ѪK��_���%|羝�,3��_
rɱ�}�;�<O=�L��W8��y�Ӫo��碗���إv;��'Hޅ���������g�C��R'޻r����3�
c7J�~)��h}K(�����!ڽ�O 33R��	���2�ӛ!0�m�IX��6� �'����<G��Y爈}�&��$ҍ�^ص�|��G�����ذ�J�ƾ�|�i���l|����+ܖ���p8�5�)�f]�U��P�Ȭڈ|��%����F&��fw
����f?��>��d��õD�d/��YI�#^��Jm�~���
a��$s�Oh�[�^��ł6Q3�ꦞ`+�� ��|�2�<�1ҵ9q��:i����=�עc-�jVL����0Aj̈́b}JQ���zM�ў�))�-!A+�(�� ��=[�t�s��	8�[$��jT5��+}���_��K�|l-����)�>Ғx+b-�`��fIo��+��::Qˤ�̝j�����Sd~¯c�Y�Ń֘�p���i����}�������MD�yo��3�i��Zn�Y���f�e�M��чK�T�\m:)�_��X��qWRs����̜��1�Er=g�R���i���v�ox���&��-sBu?K��΀�nΐ��bM�v`6�'�`4�NS��&#@?N�Kf�N�^�w��SP��쎪K��_\CR���'�9O..h��"�-u�F�:׽��D�Q��cL�|���>_Ra$���	���gE�9�^]_��ǗD�H�n�TifbTH�C�h�@��4��N�\!�u_C�沴*7N����/f޲�t
��]:E
g��*j��� 6����Y*)T��U	��IU\���Eo���m�C���-�j<��Ά���!�R�S�>��w�C�'�A*ܖ['mR>0|3��ڞda|6v7Z�+c��qH\��9�?Ҋ���B�`����w|H�����)~`��v�>��^'V�ݢ���-��\]G�� 18MS4'��sf/�[4�|@|7�9:���}��,_��Al�*K��C��E�ÕFTL���hMʭ��#\w�v�I!�Ċ�y��Dz��D�G˴�
�Ċ��a�Q��Ө�U��������gO�=�E��?�r��|�Z'���94�pG���(�*�����b����D/�!ʲ��_��ԇ	%��rVxz)��2�廕V�˅&(���F����;���,���4��H_���p5�޿����
p�P4.��Cш/;"�/զ�� �YLR�>�1���Je�^�*�\����U*|����u��랒�0�=��-�y
A��
�?Yh�g\=N��٥�"�IC?&:p��У�M!&�#"s��t���p��x������t�����]�J��QL�����&bIp1�R��WoH��Eg�Q{s�����_iw���Dǩ�J�&H{&��֙h���[%�ϴ c匎*��t]�^a�	A����(1��9&ә�`�����.?4cm~f"���d�_W��T��HA��kgow'\����f▪��a�T�q���'����ɜ�q�br���w~���6� =qD�(Sb�H�Q([�.�5�;�β�"�n�XK�٧��}���|ÈN��c�ٳ*��bc�����7��(�Fd�SN�X��\�'y�B�ӏ�l��Ra����'��Q+� p�V�-��N�GL�oA´��ݴ�0�bv�q�E��s3�f��B;?:�����ɾ̟$�lG�Ѹ���b{|�>��Y�'��t?ͼ$b���	1����{6XJ���6�J�� �����Uww[\�W�k�x?�	������*�ז���(����Q`b3D���E꽆�����>�hX��R4B�s��b�m��,}{���]
�7�_�-쁼KMHH��*}����>E_3Mn��F���|lp���� �(9qV��c��D�~�8>�>n����[���B+�������3�)f�
w���Țǵ����0�����k�y'U�C���(�tb_��j����6C��$�7���68�kʒ��q�'�q����4C�fJ�@���dj*�Mo���3>Mǆ    nbS�l��O��(X���Ql`f��l�L��>����DY"�j,+���A!�:	���6(�Qa)$��Z�"���{_�=#UN�{2c�Qf�ͫ�(yt8̳�؞Գ�YI��+����)�ZXd@8���NΏ}��f�KC��V����7B����@_ �L�����4�&Z�ojQd�>��o�$���1�&���?�<���mRY���jY���j`l����N�m\ۀ��B���.	�^�ڂ)�0�u�m�*[����8)m��c�Tu�<�MޡI��s�y���B?.L�uVˊj+���R��5�2�Ϊ��{��
g��QEcC�]H����օ��x|�0�X.���!�Kz�䄶$��Wp�F���i�{�����?��Tɛ�r�2�$z����$�Y��S�Z&\�H��4+�ۧ�� �:��4�]f1Ɋ�2�B�︦�/.�VҲ���8 �6<�g��6�G����ˡ�@U` F1L�g��ŷ���V�Gt�lk�;�ԧ���S�"֓��s�f�ʔ^�e���4y�PD
=�s�y?5s�:|�[E����]�r��}6��ywRXK?���LO��Q�U��`(�HE��ؑ�>w��,� (@ʃƒ���w9���AU鋡�(�Xq�~��'g�0~���	ho�CM�<�$��x��s5n�N�۔�v޸�x����0G��*R)�L���Q���r�{�z�ˠ��f��R��.K^[Cw�����������%M���E�A��By޻�#NLgB�V�Z��Ul��T25Xݟ`��Q�L��b��߃^X[V�8�Q�i�+�}j�����V��t��,���̷Q`����Û�r��"i#�Zʪ�ޱ�B	�}q�L�PA��e]���7��c�����*r�u~�$<�d5u�\�G�����?9�D!�V�F߭n؏�+�����&|�9�#�v�p�]��E?B�A��<����g<
�'Z����Τ��m5xmd�4__7�~��~�嶾E�S��x�`��D�n.���#
�#��ELW����_�W��#O�~d�,�$q���5Ϊ2��n�9t;`���w!Q��-��BU6;(��ß@I�c0L&��j:e�4���Ö�M�.��I>��B
Ų:�r5z�ǻv�ƉrD����K���L�f�XJ{�Qv���"���Tq�v��utw�J^��UZX���aF�qk���|��ؗ8A�4�1ڜ�J����/C�.��0[�����^S?�\�ռ�	�Z��W�aZyB� ļ����"	��;��I���=�?t�	�bv"�D�"�^�t^)�z ��V|��@��f�7���3�\0����o���p
�z�����/7�9y��ӎ�W �0�C��J��@�,v��ց;��H���/� �J��`�����
�)��QC��詡6u������:mDz�B��DsD���:��ܑƚ��gCbi�uEҐ�ay쮎�R���ʛ�o�2)TpP꤉�9zh�����^Øx��@�&U^�<�x(aĽ�S�7$o�ƶD�5lP��a�md��;X��x�p �'x�\�2�Q�L(E�����0�#��T�1���P�<\_d��߬�b[��Ukd��o�4�u�� �A_'�MJ��T����b׬�����h��I� Ձ�k�G?M�%�Μ�X�]Ʊ�E�ܺ��q���]��Ph�h0}�](���&�4��� ~��Ͽ7��� �QEs	>���b0��ʾ��yN��_��
��钰bX�e����;9b^��H[D�O}��8z��V�.e�G�{#���\�<N�<E:N�7��X�Fd%O���@{�R���XGǩmt�Q�w�Y�ov�B���ڕ@_{Ɍ�4:΢�9h�2�-H/���J[_��E	���w=Yֹ����i±��sG6G>��27l�lO|~�+�����v�n��f�'<J��*:.���Hˁ�%�Ƅ�2C�F�8���긊>��gL�KF�G+��>�.?�y3�tt���O|�q��T�|M#��9߱(��I�����=y��;��6Q�]I�yh��"�7G����ɹ(��J��y�6$%���'(ڰ�p�h�q��>���w��#�dяS0G�����/2�o_����;�"e�F��FA<Hc{�t�������^Q�9dn��Z��9�t�Go֓�������/ I��E'��δ1����<qn��2z��cM5?|5 j~�cP�ڑ��L!8�7,/��_��$/�~�J�u�����`�5a$�lLl4P#�O�V��K�����1V�pb��,I�M�,�k�D���<�41j:�;�a�5�D5ʮ#ba����ӘH�e���E΄&eA}��'��׋����i��V���+:d��_��\��Wk/��	�T��.lÂg�n���v-DM��f%7�!���G��4��U��� �9̴��ّ�NW�LR�$�/�;�_��[����"�S���a=yXx`�'�z{�_�v�eR����Z/�{T��I �i?����H'6sVFWSj����yo��H��W��p�~�5��8�����eU���#@XE|�!-��"����d�w�q[����m�؜�����K��"`��YɃ�j�=Q�"h��S�N�*�(+��=x4.��!��dΛ$Ml�*��j��z8x��/�{���9��)��v�s���N�>S�1�����;�00}��P��w��tԃ̀*�&�(�6��sZ7K\/�?!��e� ��g����A�<�c:sҁ�.�MU���	�;�U��Xk�)�iZ�"�N�X��I�i`IYlCg*z�b��Pc
��Ba������j��p�pC_��A��ܢ�(죛�|}�ĚN���=�yϔ{��þ��i�05"A���f?�������_j�����v���0S?A�Ǳ�O����cvD��)h|�Fr'R��"�NKX3o�ZB��g���L�:~����to-��ߺ�Ӽ�����<����!����EGW5B��9rzxR����9͖�Ħ!u�mi�����̶¬�r/��g�Q�ſ�|��$w�,��T�R/�$��"~:�8��܉{��_��Rd���?��p�*w�s'�VY��l��١g#�o�q��&(J;Y)"i��h��$�jۼ��Q��c��Z%6��BI�)��������Q��<�N��r9%�&UDow����Q�z�nη���q/gR$q�0��Vet���?�1��$�8{��F�;�"oz�m�+z��������@/������u�R��@6�D�h�A��a������X��6aq��a�<O�p0+�^^߭L���,~�i�������|7���G�o�m����x<l���i�^ʯ�K�
��@�#4���߸.�&��_�`������^9)	�g�#8��j�����	�zу��^o1/j^v�x;���[�A,�z��t��a�a]��zf�A쟰�a�7��rd��;Yq�y����HS.n�"�59�Ȣl*���$�����ޭt�M�x�j׉a0�{p�
���t��7ೡ�r� y����M��E�0��k	'1|~�	2�� 5'�2F�+�2�(�b7�=���D)�~��H�Ȝ3���߳��Rr?��
�:N1�$����U��U��2�^Ջ�Mr���0mz�Ln�ݛ'��y\bY�Ê6"^^�`�*�a�ˉj�����1��N���K�>E���1]������i�T����Y6�gfӥ��/��q�7��
�/t���g��9�']��V�)K��������骓�릃�+���kťRH�{�4�!���0�$��$2�Tx�����l��5�%9_y�Y`��M� !�x��iS8j2}-��z�1���(���:������e���a�f�v���''�q1�x�smk�l`x3��u,��6��8@	�;�ؤ��%�z�����n�2vA����
o̟*�厝��L���A k  �펵V'��Z�k���2���ThK���(N���#��F��_�0R��$�pd�ҁ-���y���i4�8�sh�2�i	��L�%�"�gE��w��r{����a�M��ͫ�:�tZf}<�$��RO]GУw�P�u�^WV�&~Ͳ�ͶAj�a���+uꜺ��뵀����T[��oWp&�.X��0scm�}��D���n>y��F��s��D���z��n�,e.gy��'�:�l�J�<X:5���9�`B(
2tb�V�5j����~j�9N,���Tf�$bf�����ˬ���Z(���R�2�nm|�:b���UItM�/IT����A��av���ڈL��*�+�����/]ތ����ʢ���@7�(�H�����p�R���R�?�	&#����ԫW2�[�Gkni�ޠ�Z���������~b�^n|e{��aB	�) ��?� �'�%����Ѥͨ=��m|�b$���淜I���������৾��&?p��q]r�{� ��6�|s����?R�:C<jLUeY�|�o}7��j����A��1�Zn߆{���ݢd��{Ա�#	9L��ڷ���v�4$��.w�D�K�x� �]�6�������S���V�	֑�|��X������BG[Kba	\�HWgѷ�ŪI�C�F�����O�'���'|V�n α�������Y	�3�e��F��;]_�� �
1E^D׳u����a/!��D*�$2�u�����q�F�kh�Nlr!���W|	25�a�r{�%�o�ՙ�8��Mͪҋ�A�Z��X1�N�.c���$�cRWq�1�[h�����a��l(u{����mą�]���*(`{z��oQ�0�l�      3      x�<�[���
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
Oh��0����e�KE{d����ԝ���[����6���'�R3����>`#yh���U��n�r�����x�)<���D�0O��U`�ʥ�(��h� `�ϲ9_����g/�_�C������=�R<�{{�L[_ރ���Kd������~w��.6�̋>�a�)��Ѓ� �g�.��P<���`/4ē4�]>�z-X�P�Q�[���<�y�}K��t����*�9]/�؈C3rq�6�I��.����C�A]�]tZzIC�q[l����^��+/︋G�#����H�{�#����ȂO��נ|.�I�:`��$F9(>��zv��Ir�<���dă�x	c��c�%�=b��侍�m�4��]UF�s}U���_�V���oY�w���?f�Akl�1��"<Zr��.C������!�j��ʣ#a��x�G������ ��&�G8�`��nP�HL�r}��}&��|C�O�RM_����3�?��ȁ�0��xH�V�A�m�U�~���f �3Za�����Yyǰ˓r�~����y����τ�`����0'�C�����AV�#�2��?�uc�Pҥ[���i*+�7�����+���+��*�ÃI�'��`_��~G��~,�&�Ov��@O�χ�/|�#���@<���>�Y�y���w$�m�t��K9��z�Y%�+U@��dj�o ���W4Ć�����۸����/¸UݰW�ؐ��u@[L������;"�Q�z�Ǹ��Q.)ǅ��l·� ;��m���f����0����	 F��:]k��S?%��b�A�k�ea��ۆc�$��7���R���`��k�H{{p��G(�(O`��k2�6hd޶�����d_����v��I�>��<�Q��ȀE0W�0������Xޖ)s�ߡY�,�˰����{����X(����U�2�4��Lʌ���a�ϵ`�Q�'��tM~�&)��g��!���G܆���.�n����N���}��c{w�f��'iq���ٰA͸v,�'k���iJ��Q��p����#t_�_`2I|D��K�rܺ	H��p��a�������;���K�^��Ez��nj�u�2��Ѱš�* �_�^�`����a��`�0�rN {��q���%����k���&����L�y6��,�fS�����(P�e=��;���zO��}�|݊�Ðۼ���Gx�R�="F���d>�=��2�y,.v=2�}� ��h� 6�[�-g�DN�x����<��T���},�#�����'ƒ3�[�{���O���x$�=����D�1Y�H��.lc����@n8�����R�Ͳ!�_üm�"<�3>��w�;��K��4���,x��.��an(K���_U��u�w���#B��Gf�؍@w�d��1</�{4ߗk�a��a��7��N�/�b��G7y_�n��H�G�ރ:��K�Ľ����)eq��a�ap����^C�_/����"��#�o�*�*AF)|%�sJ�t۬Or�?U�n ���dGlx�V`ұ{��y���C��#7oLn˜����_���(+���t��c�@B<"ꦺ��<�lHǲ?8>#�FG9�ཆ,2քc!��9��b���O�,�0U)x��bϪq�|�c������r���G��x#���{d�m8����(�=ık�U����n]����'�n������+]�������������\�      4   >   x�3���,H-�2��I���2�KMO-IL�I�2���OI-J,I�2�t�K-Jd&s��qqq �U       5   g  x�}Vۊ�F}���^�~yK`w!��.	�ղ;��F���|}N{3ny��)��r�TI��i�2�����RM�QL(ÜҢ�8�uģw�<N����O��ll�#���Ǐ퐉#�8�`Fs�ب��<���vw\�Ԭ��ty�<�1vq&��X������*��SC �+�GvQI�t`>p��޴�Z�,~;�9�qZ���x�47,��7�!u�o�|=�Ӻ 	�9gxμ��T�)-�$~M�5v���Ȧv$�X��*ɔ�r����Trj/���1�^h4�����0�vށu��SiIZ���!�R
���c�����A�K*=YΧ9�/���]$��`QE䢔��u;������/��>�)�$�!��P�-Ts P���Õ]����^ZT@����ê�^���ڵ�.�A
E<V����º���TI�;�����/i�����Y$ﭷZ��Sd͹��<4}�oEZv�+֘�Se1��|�rCN8�M�7�9"�B��d�zyng?�K�=K�KK+k�m�n!�R����6�)�Ѻ����m hfD���b�Y*n=:��kLZ$�~� ��eT��M!8�<}k@�O?��|{*	�Y��0���{klw�ڏ����[1�|x6Q;��\ک+��{DW�%Ϥ-����UKa��4C�ב�oP;0�[��*^�xdSˍ���^.�VB
U��cG�A�禰m>�=h�m`F*�55��%�7z=c��G!�. F�A�i���nډF�����d�+�a�����k��-J��L�@F0o8�:���6w�xL��ʆ��pL!J�\�"u��b�s�ðܴ�e2OiN+��BVc
��w���CU�\�W6�.خ�����h����x`�`�P��,��yI��b�����(�#+t����	�-�"R�+B���y���B�~��~���p�$V���a���a̒%A�A�>�	D�I�mQ��Û��|���ڹCN\��W��@Q��V��?_�;@_������W��*��.lƔ�i�n��8�0(�ōS[Tn4�%N��zmY���E8%�Q`��S�.ō�C�A��[� �F�!��jJ+���RQ�!������I�=�i��Za����9A#@���L���vkP� ��1��t���r�a�1��Ex��z�,Ն�!�1���l��yMy*ǎ68t4g��zhT/��F���9�L��Zf�Qb������4�p��.����F�	�*�~ו�T{���%⯻��PX��[q��q��+��U�u��@��i��y]�x;�s<q�^�����x ��CZ��om��ehV����-,5.��֏�	y�t�)����f����!��Uq��et��w���~��      7      x�3��M�I�2�tK�1b���� :��      8   9   x�3��̫�2��M���2��MM�,��2��(���K�/-�2��I,JO����� _��      9   #  x�]��R�0��ڧ����k��P
L��6L���M������Ҽ}���@o����$eK���}�c��-]�e	�f��0DNINLR�8�Q�&!�A�Z|D�G�YK>ireݑ�8��.�h>���-�0w�مls� Ws\;x��dT��k��-��8�{*�7u���
`\��Nll̒2��>��(_����;�Ҟ٨�qNbQ��Z�M�	�X��i���'�J�W���;-䑣�8�`��0E^L1ò�+������NU�ߪm69���J��.S�����@Kظ�=OjD9�<_�_�$�k��U�������po'Ӈ�*�߇'��h�����V��aU��bU�}І�*P>H�F��V�1����N�AC`�]��H�2M�k}��3���n�����_��&�W��x��W� ��_@��p� �d=��j0�G=��ІN�^�� ��XSg�u�8�,�e��\�y��S�']�����(O��[�fM�fs�I���@��N�ӳ��8v�X�:³=����������V`      ;      x������ � �      <      x������ � �      =      x������ � �      >      x������ � �      B      x�3��4��24�45 ��\1z\\\ &\      ?      x������ � �      A      x������ � �     