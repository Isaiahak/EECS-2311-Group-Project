PGDMP                      |           thebestoneyet    16.2    16.2 Z    <           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            =           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            >           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    18277    thebestoneyet    DATABASE     �   CREATE DATABASE thebestoneyet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE thebestoneyet;
                postgres    false                        3079    18278 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            @           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
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
       public          postgres    false    219            A           0    0    dog_dogid_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.dog_dogid_seq OWNED BY public.dog.dogid;
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
       public         heap    postgres    false            �            1259    18321    poster    TABLE     �   CREATE TABLE public.poster (
    poster_id integer NOT NULL,
    displayname character varying(255),
    score integer,
    phone character varying(255),
    email character varying(255),
    balance double precision DEFAULT 0
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
       public          postgres    false    223            B           0    0    poster_poster_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.poster_poster_id_seq OWNED BY public.poster.poster_id;
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
       public          postgres    false    227            C           0    0    tags_tagid_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.tags_tagid_seq OWNED BY public.tags.tagid;
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
       public         heap    postgres    false            �            1259    18356    users    TABLE     �   CREATE TABLE public.users (
    username character varying(50),
    userpassword character varying(50),
    email character varying(100),
    userid integer NOT NULL,
    balance double precision DEFAULT 0
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
       public          postgres    false    233            D           0    0    users_userid_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;
          public          postgres    false    234            �            1259    18361    usertagpreferences    TABLE     R   CREATE TABLE public.usertagpreferences (
    userid integer,
    tagid integer
);
 &   DROP TABLE public.usertagpreferences;
       public         heap    postgres    false            [           2604    18364 	   dog dogid    DEFAULT     f   ALTER TABLE ONLY public.dog ALTER COLUMN dogid SET DEFAULT nextval('public.dog_dogid_seq'::regclass);
 8   ALTER TABLE public.dog ALTER COLUMN dogid DROP DEFAULT;
       public          postgres    false    220    219            \           2604    18365    poster poster_id    DEFAULT     t   ALTER TABLE ONLY public.poster ALTER COLUMN poster_id SET DEFAULT nextval('public.poster_poster_id_seq'::regclass);
 ?   ALTER TABLE public.poster ALTER COLUMN poster_id DROP DEFAULT;
       public          postgres    false    224    223            ^           2604    18366 
   tags tagid    DEFAULT     h   ALTER TABLE ONLY public.tags ALTER COLUMN tagid SET DEFAULT nextval('public.tags_tagid_seq'::regclass);
 9   ALTER TABLE public.tags ALTER COLUMN tagid DROP DEFAULT;
       public          postgres    false    228    227            _           2604    18367    users userid    DEFAULT     l   ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);
 ;   ALTER TABLE public.users ALTER COLUMN userid DROP DEFAULT;
       public          postgres    false    234    233            &          0    18288    age 
   TABLE DATA           -   COPY public.age (ageid, agename) FROM stdin;
    public          postgres    false    216    e       '          0    18293 
   attributes 
   TABLE DATA           B   COPY public.attributes (attributetype, attributename) FROM stdin;
    public          postgres    false    217   ie       (          0    18298    datesbooked 
   TABLE DATA           D   COPY public.datesbooked (userid, posterid, dogid, date) FROM stdin;
    public          postgres    false    218   �e       )          0    18301    dog 
   TABLE DATA           {   COPY public.dog (dogname, adopted, biography, imagepath, ageid, energylevelid, sizeid, sexid, posterid, dogid) FROM stdin;
    public          postgres    false    219   �e       +          0    18308    dogtag 
   TABLE DATA           .   COPY public.dogtag (dogid, tagid) FROM stdin;
    public          postgres    false    221   ��       ,          0    18311    energylevel 
   TABLE DATA           E   COPY public.energylevel (energylevelid, enegrylevelname) FROM stdin;
    public          postgres    false    222   �m      -          0    18321    poster 
   TABLE DATA           V   COPY public.poster (poster_id, displayname, score, phone, email, balance) FROM stdin;
    public          postgres    false    223   �m      /          0    18328    sex 
   TABLE DATA           -   COPY public.sex (sexid, sexname) FROM stdin;
    public          postgres    false    225   �p      0          0    18333    size 
   TABLE DATA           0   COPY public.size (sizeid, sizename) FROM stdin;
    public          postgres    false    226   �p      1          0    18338    tags 
   TABLE DATA           :   COPY public.tags (preference, tagname, tagid) FROM stdin;
    public          postgres    false    227   �p      3          0    18342    userattributepreferences 
   TABLE DATA           V   COPY public.userattributepreferences (userid, attributetype, attributeid) FROM stdin;
    public          postgres    false    229   (s      4          0    18345    userdogs 
   TABLE DATA           1   COPY public.userdogs (userid, dogid) FROM stdin;
    public          postgres    false    230   as      5          0    18348    userpasseddogs 
   TABLE DATA           7   COPY public.userpasseddogs (userid, dogid) FROM stdin;
    public          postgres    false    231   ~s      6          0    18351    userpayments 
   TABLE DATA           s   COPY public.userpayments (userid, paymentamount, daysbetweenpayment, dogid, lastpaymentdate, posterid) FROM stdin;
    public          postgres    false    232   �s      7          0    18356    users 
   TABLE DATA           O   COPY public.users (username, userpassword, email, userid, balance) FROM stdin;
    public          postgres    false    233   �s      9          0    18361    usertagpreferences 
   TABLE DATA           ;   COPY public.usertagpreferences (userid, tagid) FROM stdin;
    public          postgres    false    235   �s      E           0    0    dog_dogid_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.dog_dogid_seq', 1000, true);
          public          postgres    false    220            F           0    0    poster_poster_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.poster_poster_id_seq', 1, false);
          public          postgres    false    224            G           0    0    tags_tagid_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.tags_tagid_seq', 48, true);
          public          postgres    false    228            H           0    0    users_userid_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.users_userid_seq', 12, true);
          public          postgres    false    234            b           2606    18369    age age_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.age
    ADD CONSTRAINT age_pkey PRIMARY KEY (ageid);
 6   ALTER TABLE ONLY public.age DROP CONSTRAINT age_pkey;
       public            postgres    false    216            d           2606    18371    attributes attributes_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.attributes
    ADD CONSTRAINT attributes_pkey PRIMARY KEY (attributetype);
 D   ALTER TABLE ONLY public.attributes DROP CONSTRAINT attributes_pkey;
       public            postgres    false    217            p           2606    18373    poster displayname 
   CONSTRAINT     T   ALTER TABLE ONLY public.poster
    ADD CONSTRAINT displayname UNIQUE (displayname);
 <   ALTER TABLE ONLY public.poster DROP CONSTRAINT displayname;
       public            postgres    false    223            j           2606    18375    dog dog_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT dog_pkey PRIMARY KEY (dogid);
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT dog_pkey;
       public            postgres    false    219            f           2606    18377    datesbooked dogdatepairs 
   CONSTRAINT     d   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT dogdatepairs UNIQUE (posterid, dogid, date);
 B   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT dogdatepairs;
       public            postgres    false    218    218    218            l           2606    18379    dogtag dogtagpair 
   CONSTRAINT     T   ALTER TABLE ONLY public.dogtag
    ADD CONSTRAINT dogtagpair UNIQUE (dogid, tagid);
 ;   ALTER TABLE ONLY public.dogtag DROP CONSTRAINT dogtagpair;
       public            postgres    false    221    221            ~           2606    18381    userpayments doguserpaymentpair 
   CONSTRAINT     c   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT doguserpaymentpair UNIQUE (userid, dogid);
 I   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT doguserpaymentpair;
       public            postgres    false    232    232            n           2606    18383    energylevel energylevel_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.energylevel
    ADD CONSTRAINT energylevel_pkey PRIMARY KEY (energylevelid);
 F   ALTER TABLE ONLY public.energylevel DROP CONSTRAINT energylevel_pkey;
       public            postgres    false    222            r           2606    18387    poster poster_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.poster
    ADD CONSTRAINT poster_pkey PRIMARY KEY (poster_id);
 <   ALTER TABLE ONLY public.poster DROP CONSTRAINT poster_pkey;
       public            postgres    false    223            t           2606    18389    sex sex_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.sex
    ADD CONSTRAINT sex_pkey PRIMARY KEY (sexid);
 6   ALTER TABLE ONLY public.sex DROP CONSTRAINT sex_pkey;
       public            postgres    false    225            v           2606    18391    size size_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.size
    ADD CONSTRAINT size_pkey PRIMARY KEY (sizeid);
 8   ALTER TABLE ONLY public.size DROP CONSTRAINT size_pkey;
       public            postgres    false    226            x           2606    18393    tags tags_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (tagid);
 8   ALTER TABLE ONLY public.tags DROP CONSTRAINT tags_pkey;
       public            postgres    false    227            z           2606    18395 +   userattributepreferences userattributepairs 
   CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT userattributepairs UNIQUE (userid, attributetype, attributeid);
 U   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT userattributepairs;
       public            postgres    false    229    229    229            h           2606    18397    datesbooked userdatepairs 
   CONSTRAINT     m   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT userdatepairs UNIQUE (userid, posterid, dogid, date);
 C   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT userdatepairs;
       public            postgres    false    218    218    218    218            |           2606    18399    userdogs userdogpair 
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
       public          postgres    false    216    4706    219            �           2606    18411 &   userattributepreferences attributetype    FK CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT attributetype FOREIGN KEY (attributetype) REFERENCES public.attributes(attributetype) NOT VALID;
 P   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT attributetype;
       public          postgres    false    4708    217    229            �           2606    18421    userpasseddogs dogid    FK CONSTRAINT     r   ALTER TABLE ONLY public.userpasseddogs
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid);
 >   ALTER TABLE ONLY public.userpasseddogs DROP CONSTRAINT dogid;
       public          postgres    false    4714    231    219            �           2606    18426    datesbooked dogid    FK CONSTRAINT     o   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid);
 ;   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT dogid;
       public          postgres    false    219    4714    218            �           2606    18431    userpayments dogid    FK CONSTRAINT     z   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT dogid FOREIGN KEY (dogid) REFERENCES public.dog(dogid) NOT VALID;
 <   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT dogid;
       public          postgres    false    219    232    4714            �           2606    18436    dog energyid    FK CONSTRAINT     �   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT energyid FOREIGN KEY (energylevelid) REFERENCES public.energylevel(energylevelid) NOT VALID;
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT energyid;
       public          postgres    false    222    4718    219            �           2606    18441    dog posterid    FK CONSTRAINT     ~   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id) NOT VALID;
 6   ALTER TABLE ONLY public.dog DROP CONSTRAINT posterid;
       public          postgres    false    4722    219    223            �           2606    18446    datesbooked posterid    FK CONSTRAINT     |   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id);
 >   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT posterid;
       public          postgres    false    223    218    4722            �           2606    18451    userpayments posterid    FK CONSTRAINT     �   ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT posterid FOREIGN KEY (posterid) REFERENCES public.poster(poster_id) NOT VALID;
 ?   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT posterid;
       public          postgres    false    232    223    4722            �           2606    18456 	   dog sexid    FK CONSTRAINT     q   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT sexid FOREIGN KEY (sexid) REFERENCES public.sex(sexid) NOT VALID;
 3   ALTER TABLE ONLY public.dog DROP CONSTRAINT sexid;
       public          postgres    false    4724    225    219            �           2606    18461 
   dog sizeid    FK CONSTRAINT     u   ALTER TABLE ONLY public.dog
    ADD CONSTRAINT sizeid FOREIGN KEY (sizeid) REFERENCES public.size(sizeid) NOT VALID;
 4   ALTER TABLE ONLY public.dog DROP CONSTRAINT sizeid;
       public          postgres    false    4726    226    219            �           2606    18466 
   tags tagid    FK CONSTRAINT     s   ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tagid FOREIGN KEY (tagid) REFERENCES public.tags(tagid) NOT VALID;
 4   ALTER TABLE ONLY public.tags DROP CONSTRAINT tagid;
       public          postgres    false    227    227    4728            �           2606    18471    usertagpreferences tagid    FK CONSTRAINT     w   ALTER TABLE ONLY public.usertagpreferences
    ADD CONSTRAINT tagid FOREIGN KEY (tagid) REFERENCES public.tags(tagid);
 B   ALTER TABLE ONLY public.usertagpreferences DROP CONSTRAINT tagid;
       public          postgres    false    227    235    4728            �           2606    18476    userpasseddogs userid    FK CONSTRAINT     w   ALTER TABLE ONLY public.userpasseddogs
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid);
 ?   ALTER TABLE ONLY public.userpasseddogs DROP CONSTRAINT userid;
       public          postgres    false    231    4738    233            �           2606    18481    userattributepreferences userid    FK CONSTRAINT     �   ALTER TABLE ONLY public.userattributepreferences
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 I   ALTER TABLE ONLY public.userattributepreferences DROP CONSTRAINT userid;
       public          postgres    false    229    233    4738            �           2606    18486    usertagpreferences userid    FK CONSTRAINT     �   ALTER TABLE ONLY public.usertagpreferences
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 C   ALTER TABLE ONLY public.usertagpreferences DROP CONSTRAINT userid;
       public          postgres    false    4738    233    235            �           2606    18491    datesbooked userid    FK CONSTRAINT     t   ALTER TABLE ONLY public.datesbooked
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid);
 <   ALTER TABLE ONLY public.datesbooked DROP CONSTRAINT userid;
       public          postgres    false    218    4738    233            �           2606    18496    userpayments userid    FK CONSTRAINT        ALTER TABLE ONLY public.userpayments
    ADD CONSTRAINT userid FOREIGN KEY (userid) REFERENCES public.users(userid) NOT VALID;
 =   ALTER TABLE ONLY public.userpayments DROP CONSTRAINT userid;
       public          postgres    false    232    4738    233            &   Y   x�3�(-(�T0P(�W0�2�tL��I-NN�+Q0�q�JsJ��R̸�9}KJ�R �f U�\&���y��E
�
�\1z\\\ ���      '   .   x�3�LLO�2�,N��2�L�K-J��I-K��2�,άJ����� �5
�      (      x������ � �      )      x��ۖ�H�6z�z
�n�1�Z
����5�rW�]v�����c��D�J@,��κڏ��o?ɞ��P� �Hv�> �	L�"f��7���X���U�����rs���T�珣l9���㴞�=��衬���������m>�n��hS�6�|>�f9�W�Q�u4^�|T=.'�8��y�M������O>��ey_,�F�f4��?�L�y�����?��~��7�Q,�����Iy������ƃ��O|#�g�2���|��͈޼e�*��=|l��Ѥ�������uV,�	nf �z3*���Z���1*�Ѳ|�/�Y2��^L�ES�	_^���C���0zS�i����n��!����m�b��,�h����V�|5[���eY<V�|�Ӝ�(L����0_��Lڸ�L�y�g����Iܸ�?�/�u�? �){���K�}A�.��nv[�ge9��7Ζ0U�7�~�nņ�� �4�6���>y����O�g�8;����K5�������5,�G��%���hV�")�8S�7p;�X�n�G�l9�a�֛�F�&�j#%��Ó�-���{�Gx�� A+ɻ�X򳌦^Q�L>��h]��M�t�b	�}��}�g ��M�Z�d�˼RK�����s7�[^T�4-��Kq7*�o�Tï�l�R���%l�i�?�e��Wf�=ۋ|^N���D����E�K��n������ع6�|>}����g��'pW�%���C��g ����wr��m��R~,��দ�"_N��n��=�~���u�?zaY��*����~��/@�ܕ��rYn�q>�a��$�M�	����<M4nPX�r���bQ/����zK�L�7��f8�Ú��hp����_�������K�&����p���|ܬXX�_��ެq��}��&Y�����j�	�4��U��r^�iyMr��� ؎�k6��g_�u�&�7��\�'j��{DQ*� �(�
�����F�.����]�����h���+&�&o�/z�,ˊ&k�(���n+ܡ?�~]�?�n+��r��ot���Yg5�sR-��n���0�s��z�ξ�s^��ly��|���Q��?E>UsC"�Zm���t7L=����L�4r�����<�$|�N�R Oy����8�2��r��y���'��"g��a��rRT�_�I�-Nӥ޿ �4���#���r����e �*��P�t�`�e+��1(U4�F��~}"�;��@E��=��Wr(�`?_���5����p$�7'���t
�d���_�%�^�΁e�%��k��E����!nz���r���6��%��k2[ϋ��?C'�aK+�fż����7y������0AP�0�)�E����	�S4�6�:�ي��Zf�\�H��r��M��/�@|^�e����/l?��פ��nQ��/¿OKXT���gA/�Ƅ9�s잉��F���y�/������LxJa��*e�>�NP�OQe�,A�`�RИ�gH���n߈� g��G����x6#Zʪ^�"����o�Y�
�	�O��5^�c\~�j/��7�=��ve��6��,w~�
�C�V�x��C] 3U��&}��$��J�O�.�����B��>�lR4��O_л����F� װ�`Wsv��;���_�&(.-Ư��1�b�_p�p:�9�R#�zO�M�}�,�6�Y8G>l�b\*MFo�6��� �sU�P�F��,���:~�}*=d�*���3����' &��Y^A8�x�e�/�c�6����/��#ڌg/�Y~!7t}wG'2�ղϞփ��V;�D�!����=F+5W�3��D 3�K���ͺ��c�G������ks2�Ys�GM"/�`�i��	�Z���]�4��P5V����O�;~�\�OZ��nKx�4Y�
�η�L���/��Y���f1t~���_�N�.����W�|�K��1����d��A-�;"���c����.qϓ����q�G�p�T0G\)��藟�F����e�ͺ������!��U�÷hT���yfJ?."��E��'^��*C�.c��J�y���;��,�(~��cy����um�a���G�:�uU�=��L`ˁ���f����W����#_�x���<���x�,��6����,�P��d,�F��jᱬ��hd�h��%5��L>�A�L`��&���q��vK��`�����ŕ�K�aBO�m��7"U�<�~k&�����~MS`�k���r��^�y�ړ���D��R.岙�Y'���f@%=��˭��x�t��A VB�����jv[��I,��ZK�����͌@c<�[1�6�h��`#�D-
�V��K;��5l�ҝue�*��61?O��_Ը��ȋ��XN^�-��L
�'����a9Z_���8ޤ�|ce��LG9���a���lV����8[���ޥ>:��ڞc:��業d�1��>����4�<�y�U���5?2p�P�(G�?���l�)��Ƥjǯ�-�%*��r���m�z:l���Cd��M��r5�d�Us"�j�7�:~��FV����n��Oi��['�����3�;��ޭ+ة?���"~X�d}Nr���l��"Wu���&s�F�
*5���`�����n"�/���mpy����Q��Ub���� �v1�Mдq���R���7�
s6猾�0�̕z�Ϯ�Ƌ�7����ʱƩu�&7�ٺj�#[#���gz�Y�i�H��7:O��8sc� l(7��-�{���y}�������@ai���GN�|�C���`Z�=8�ʔ�7�2Z�8Ѡ��.�mW��~	��r<�����>-P�V��3�P.s�(Ƽ�Gw��"H۾�V`̣k:�-��T4}�@ݳ,|�Z鍗:�ʊ\zUϒ4"<h��_��d�V9�k�dr��Vl1�(,[��@fy1G�β5�l�X��F�>+�ɾ`�I�dK4��%��g�7����K���߶=Oa3<�PW�hN�n��Bf������v��F%�Ⱦ3>;ֆc+�.�_4������a���:P�v����7���<�JM%��D=u��H�sh'�G�]�;�Ī70|����}0l)��������IF���N{|hR�lJ��9.t����`�*���4O�*3��i{^��fQb�)_�Թ�)���� HՆVc�y���G"��(BE7~༇��/�6n���x�Bb{�½�)�q(����CuNwr�>����9�*�L���+���(CP:���@T�.�5P6�+��������0)�;ؕ`M��bn/V/�T��?p��!��Z+�
d���S�u�w-(�A�0FG�U7�sOr��"��+?v��`�P"����yq��Q�F��2qXpڔ�C g����G �s��J����X��%���;�j�2b;���E���L�Z=ܼ	����7�#1X��f�(�?���o���H�S(#<������~���NRv���Ā
Q��O���W_P�){!��|�rC�04��ѣFD�*��d(��Un�u5�{�]�}�^Ȗ_2XD�le�B�i��<�:H�V�0���oA!y���۠�D�q�	qs�1oH�Sy"��\��	�?���������wGs��ّ�A��K�e`��_d���¸�`K�ʾ��U�����լi��
M0�z��l��#A~�9"���yS.�X4���?Jb�'6aR��M�9���mFYSy�m��f��$��Q2%_�D7va#���,/A��2k�6U�EN��m�j'Ci�fE.���Tg�XEӻ���F�!�ز�ʷ�{]ψO<��y[�R������#�Sa����R�|&�J�[O5��e��z��V�S�7A�͗kJ�㫭���g��>�`�m
�tC�rZp`oNf��q����T_1A��F�gupR���k���T�D~�MS�,�Ҧ�/�+�����R䰞b��x|�=���R�LZK�m�h��X�Kp�����x���ւV`�R�CVT��W��)    H+4�����k(����b����f����m蹫�+z��mv��Ѿi9���V�/�1�4sV,�a:���L���j���k�y����:(!����J�x��(�%�T�n�
���o5���Ųv�
씮zu��8$]�-�:����! ��v�����@%n(����������@_��9�8C����B�����i��h�ط�}����
}��R���7��_��<O�N�����y�-緂1|4y�f{�D�~���t��a�9d3�lJ`"�$��!���k��!GcvkeVWEV-�6�W�'��%&�/4	�&�_���ei�:3أ�z���O2<�&�����d��p�X����[~^|F���c,�|�"2=�9(PW:G4a�J��XGPhM��I��w��E�Hk�
I���j���/W�޺����I�#��Eb�`09�L#���hp0��(��%j.&V"fd��'�Y���#�l������zŐ�AW����b�(k*i�7�.VS���ko��y�p-���uV�N�I�D!YICo�G�×Nr���F��u���A�U~&�����m����)��8�.�f�P�A6��\���7��|,k���"�~�*����8'����������Χ�H�㰃�)���u}{�azR�'|��O?�!��B؞��L�F�pXr�
cUF��h�͊&$k��2�]Q���H���B3X��uVP���K���t|�:�@��������
#}Ϻ�\Wgȹ�����o����>Nz Vt7��k���b��@m&��jӪ�%n���.��
C�
�-�T��Z�
�^�,À�]��!�J���u���ߒG@vʦ�A�ì�/TXxV�)ų���K�	}�O�9�	��������l�_��AS�m_�q]/F��M��1�gPSX3��mn&Yn+��Z^׷z��=�h�p���i��!�?��v��7�1
s6����@�]��9?�Q� Y^�Kp� Lx���]�a�nZ�!��7�	8�GUI[�=.Lsh��O�\�ßdΤڔ�!pw��|&����b;̶��*[�|���S3����?`�H��*&9��}0Y	/0�
�y� �a�UTȣ��b$ɏf���%қ8t>d��v��c6,V8�x��`R8߮#(�����
m�Ar�o�W��Cٸӷp�s�#�2�][ie���p�,�m{���K�1%�M/V{�[pe#�*�D��;��Vd}?���r�D�:2j�/�*,u�Q��V�"��U���mШ��Y5c�5����}�T!�z�����ʿ�IU�V�c]���-6��_�W�������J~�Ҩ�J;��|���/�0I���|���� �@��ĩ��QZ�I<h�&Y��0[TIE�TIRW(!b��	��\z��g@��_�?dw�Qb������{f�җ�<�K�y]��Y��8�Q�Վ6�Zp����.G��� ģ���G��V�f'Af4w���A_�u�dU݁���D$dl+�%��V����#!�� ����p�@wΏpzm���`o�ZCr#�KD	X�%o����E���(��DFy�e����X�Fqǩ��fjo��V�x��(���<%�WI�g�K��Ѻĳ�.���(��C�2��Ah��Dߘ���e��.aݾ.��l�V�f�&u���s~��e��qQcyL��
�A�4@�P�x�����h	�˕C��rN�j���_�sé���+���l�E����l��&���eM��C��3>�е��i�9�'�u3C�~�f�UӍ�=T^�LE�3������,Ι�^j���JL#�s�����/� �������8��>v�f�:̬]ϣg�����_Å"ƆB���I&���I6÷x<ی�h+_0�L
�+�P����S�x�ڹ�x��4�l��Ǭ��IjX�d̦��X0��/�:#o�dҙ40"�T�%\�1�9���bAϽ�Ba�0z�0���8g[U׎�\(8#��W0G\:�F��(�J=5s/<:�w^gT���n�����8&�������l{H�ޚ�����=p9�7ݏ���(����w���p�D��t�jtm�4^��k�˗�����驕������;*>���	D�<�r�ǶF�;�(!�z>��n��.0�ك>ZPD�����G���6����b\i���~�N��������d�����m:�	�M���m�s��oϷ��"�c)񦑘ϙ�Q�����w@Ŧ��	�m��w�SMFWݳ���Gy��˜Z�્�\ի��T���-M��j'?xIvѢD��Y��'�L���4�`EEI5�{8��Aa@`���4���T2ɷ}!����_uM4UK2�+�p^�/P{;��OB|��qyg�$�æ��\X�܎����7�ˋ� �mc-R&C������i���V��y�*e���+��LN�a'�����N�*��L^���RyZ������򉜷�r��Z�9���"vi����F!b����n��f��gi�{eJ�fJ$\��
�sE����ҵl:k�:���bDq��b��}I��4V�\CRvLx��<f�s�;������Z��5R��;�p>�@�>hÞ�}�;^����\#R8�ž2��OT�͎,RC�Ej��@A䯑�����
��N�b��:(f�^����Y$f ��=�O�!)�:��s�˧̑��A�Q�0����P���3Dd�.�����2=ۄ��������kr�؍��E�o���FY�H���K��G#H���J����PaQ;���5. l����Fό�����U ����x�J��k�T+��R��ؽ�s22���D�~@"r6��^�^��b�hL���y�a$|���f_	/��2��p����s��Z��}�更���Z�`��6T�I!)(��U`�ۥ�06������#�+1�������+�5�D�w��aq��"��@�<��^�U�m���J����:���Wu�S31������L��Ao�'�{e^",�9~$˜�S�]�4"����r<,��F�B�Mj�,ٹ颯�<~C�$	�vǢ�>��b�y{����#�����'��ԹD���mC����Ղ{P2�U��ʿ�lG���~`۔���s���|Ҋ���A*a�K7��6��5��?
sB���5���^֤�xVQ�s4�IEM�M�%��|Gu������ǔ7�S��v���S�F1��a{>/��x�0��ÌR`K0�0�_�s���fJ�����j�߾> �b�o!5"��PQ;`,/.\u���Pt!�?��"Hㅽ� R����]o\G	n9�9$���3(n�4&0�������׷�ž�?4�Բ��m�)ķ��U�.-G?�.E��1�8k��X�SC8��e�tq�`Q78������ì���Aџ���n����bW�y�Y{��>C�^��<�*!?����Mo
�I��ut��F�C��g1������Y��������pJ��Ef
;꜒L�l�h
�a�󡀓Z�����:O�*]�<���c1��d�ϐ�z�Q�:�tE�����I^�	�>���c��ȪCGR��|�|Z�����A�|gZm�ߎ�c��r�B��^�TX����ĳ3J���;���Yt�y�����Ӊ��5	�G���}�D-��U3���s#3��p>���;4��E�¤�3��ȓML���۽LWe�55ٓ�)�R��cJ��e4`Vs�ݦ����,���+5-�&�_�ƊZ��4)��`�i#�$U;�����F@`����Ek����wށkQPkmyuͨ�h�=F%	lO��8���|��5~�IcG���m�����`�X�.�Jil���'���tp\۰.�������rm:t֠�A���HhX�(<����(6�{@����1T��L�;p��$7%��ŧ-10��m���F?hI�qN���D���G��KV�&F^��Fp
�c ��    �B�4=3�8���Ɠb:le��U^���R46WeP9���S��}�@@J
"�z����J{�h���%�� �䒛�䅍��W5WD蘣�Q�.���Xs/6��i#W1���ǖR�L �Ŝh�z��m�bAŀ߹�,Azb'�ݝ�j W�����F#�Md�5o�{@_�����<�p~������h����=<��iy�:��z{��sQ��O�H�f�.Qʊ8t��cP���^�j=���t�3^��<���T�W����-[T[jyp �	z��$N� �?bDI&�b������HI�1{�R� �iC�f����_�I@;��|Ȇ��n�M9��a�)U�l��hp�:.�>
S��v�_0��/x��h�e``.���q����+=	wz��&u��缅�؊��Xo��j;x��tx�/�G&���y(�@#��k�Kҷ�o'�VHf[��/���)����Q<ܪ��UK ��)[�T�������*L��T��$u�V�-أZ4����(,6`8�����Ɓ��y$�'k�BKJ4f��\���Je���1��g�F�=�7*K)2ݰ��ФGe�3����$�>�C`�R���"�r����`?+��g�8��<��4���`C&hu�%�E���l\DrI�Xy:�]'Ȥ���v��8;$�q��y�ځ�,u��ؚZ�� �9��6����tՖ��z�Ed����(�_yM����ň\�5�c|1�2Mu�T&�u�c\$��7������7I����	�`��P�vB30���Xr�/?�z�mj��<5J�Z�.IC,�����v�������ع.׆���jk[�4�{O�T����U	�Mc.%�����s$�9�vf�S�-ZF
���� �E�0��h�*rs#��&�fs{��<���� �i*v*2-lz �́����ٚ:�v����z��uƤ��PI�zSC��-�$��@� 1R"��ܝ�C����,������4��9A'LΤ�e��i�`g�ΠiR<O%nF2�\ߑ7&�:>�gه���ړ��4��na��هk�.=}��1w�X��4f����S�81�n����y�����_��p�e�� � ����B��&[AG`T�>.����)G�M�|���U����v ��X����ܴAm7��k��c�S}�<�6L&�/�J�K��UO�F��B;)J.�����s(�<7v��iuY����5��˃'WM��[4�Rv_.���D�'|�k_)��u�6#����@�,��:���jL��6#�}g`�)�U5���憎9�~#D�|/�{G0�ǁ��"R2v'<��;����y,��]��uN��S0r-�s�x(��<`�bE��iFK�~U*��G��x*��
ՏgK����^�L��Qy�<��Ƣn��7�̂z���"��c\n:�E���U�����q�`&��e��m��f���U���	�1*�����P3��7h�G�6D��yɐTx�F��ˑ��ģ �����kP,9⅚&t�1�##ó|
��:�Rz\�tx���$��&�������U�Z��vX��a/�JssF�DQ��K�G��24*��ZD��Z�#�f�!��e�?+��%Xh��q�Ƙ$�"�2�V�֥��9:�Pp6neξ��t_Oh��
�Xaذ���g�}cUȷC�����r钤&v�$v��<��o�f��q I��Z��p���^�ݟn���{��z���6"��E��ܬ�&2o+F�[�s�&�>�I�&�4*�'%��q��$�����'��r_n�������c&����\�x�vk��dHʎKd�ʇ<+��.��gl���&�5�K��}�]:3�ǜT�#W��yH����Me�'�˴i�l�����j;g4|>8� $��nh
��|�� 4��u���_�!H���_`u�b�������fl_���c$��}�{��G.�;�D�T�C�c�����S4��|Y6_
��(�;l)]/�%��h�����r�j�1��9.�vǿgV�������=�]�Ԥ�x��2d��Y����󊪝0'�l'>��ph�P@~�)��f����/�{*E��}.%n�Xd�f�U�\�NN�/q	�������D����
��#�ߥ&R���@�@����l��6e���c�5XI0SL�Ǹ9=���F���v���]� �Օ����m�V
��:���p_�D'K�]'L��O���D�6%���z�>�ӳ��n=�WA�@8��l?�6�������� v�g�&�y�s�z�p�����6�%����f��m9��!xL�D4�\b�X��\����z'���v�V�M{Z��Áe.�H\��\�^ �{ޔT��U�+ա���{��ɜ�}� v��`��'x��!�\Z�3�]:�C�8i��/X�Tkn"�x���j�<v��'�4�@5���0܃��nQ*X�:h����aD�b�`Y��#���U!�:)Bf!��Bv͎d������K�Ŭ��f����
��(�s���t1�J
۽s���Jmt��w~�P���V�~�=�i�����=��":��@��}*�M�,�|
C���+�-��h�����0��T��B��TxOa�VZ�yS�����K�$�jeD!Pa,[M��o����S'�O���L�xa
��ejM|3<�Lb_X={B�.p:ъK�pR�����I���Ԇ�>�$�k�"�TE0�16��7�s�T���òe�YBB�3^$���2�m;<؛z�K����Ka�E�e{l�m��AF�^�����F�g�'�.���-���n�$�>�':�/�#̋�M��sz��-X���mW/��pS�B��s/Z�\��b6DR,��LF�rOo�oPי��>C^��}���X�5Mos�������1v%·z�"�_��� v-s���aZ���h��QMɪ�iTSqpQ=��R�yE����_#��u`����/�ۼ�0��:���e��YH8~�9?���'�V�}U4��$C�WT����yX�o�Jp�,/��&������q--�/������J@zzn�3�2�'l����Qs��v4��#��������jUv�5\]��}��{r��1D�0�K�)�h�q��}�l3��� zD����tּ��F�2u>��M٪t����y�ɲ�y�Ü���hs#ـ��*Č������r	��FH]�Kx�1�.��m��xX.�����YY��_�X~�H\	��X�+P����V��d{qIKB��-�gW�!>	�W�M��拃�r��H}���ݘ[W�ޙ[5Q�U(�"t�9�|�l����׾�bLL".��JHP?r�����|mx���/������~m��|E>�z-M�zqeǇM�y�l�7��(̙�X��4�Q'�j\� �7^�e�/-|+5�@�Ե�sr��M�3�j,��)��
��⏅悠7��f���g�#�JbV)�d:{�+�}q>�v��\t��~�+9R_�6��鑒N¹�7���M}ă�@���ǁN��ʤ�������V�Fq_������˨��\�i�W��$ӗ�?w�\�������-��N�"��-b#E��7%�'��2P�p���RsC��c�{y�Wl���̷\;�3�\�:
�v����L舢5z��<p���y�mڱ":���?�͈��FY�i .&�Tu6�e�)�#`vo(����D�UuΠӑ��|J1M����<��\��>h��;���c���ǞJ|�0z��Dr�u*�uŪD�����|��I�Lf*ZD2��Z~Lک�͌�È��� #�kj9�TƷ�3�w㻮Y|߫�q"҆��-|Wp�us�/W�vߥ;92�+�����Y�]��L�$B�H��9��u�-�mƗ^�v��%�1>���	I|���$��T�bx�pG�%y�Lt�WDL��#D���6�eq�w��k��Ul�Y��&0r~���]�>_ytP�c,r'd@�Չ������C�^�d��^%Fڥuה���x�x[    1�d�x��l��Q0���D���\���K���pw��\�1
�2�(2hB8�Պ�����sg��R��1�o� ���%d|�hTNȓ��+<+70����J���P?~oa~��`{\�@Z��]��Z��gO1�	�d��E輮��Dz��|�kZ�%ه�\��fk:�θYF�p��K"i�[l��T��)]�O����,�Vg�qgD�İ�CEm��J'�ׄ���i7��kr㲭�&,=6�:��wD5C��q�m$:�F.�]���q |�]�dT9=�iH�e�:DӅ&�5��"nʯ�x=^8>}XxZ�N��bK�Ҵ$���Y�(2U	b�
�s�fŏ\f�z*��a���M���&x�Vl��l������+��K��'�w�cQ0���;I��ɸ�y�r��R��Imhή�.4��DU۪z!�������l�W�����l�������sl��zP�5xd*��	���.Tq/��$�3����{�&(����T�I�G1���j��������0�F�zv�m[��E�����e4ͺ�������B#H:{�%o6�,�����`��Y�l���`b� ���>����y�/
2���4�XN5�A�e?'�p����i����+Q,�� ����5U)*ɿ=_OI�I��Ue�aS��Hd�q��OY�~|�1[n4��14<�2C�Ǜ���������z|O�/���Y�n�E�k<�h)�_�0��>��w&���G#q�����Jʹ�����q���U����=�ky��Z�Y�t�Vp�S:���]w����Q���}�!d��L%tR��в��{�3Ҟa`�3ж~ �A+�I��Y�����D`�ؒ�x��$�×+(lyO���`�Qń����Ȕ9�a���4g�G�,g�;I��f�*0�1�)y^�-g�{��d��sF�r��HK�ϳz�aAe�}j��Hd7>��y��ں�7�{�6����/j��ɫb<�'l*l�0H ?��|�<x�}{��,���O�H�h� 5�UJA��H���P�fpG�/��9�"�3c������:�bx���k�\F�t�LV�%�4W��m`M�V��6G��G7���4�c��n8v���<���cx˖�*V�.o5�yMAݰ�����=��E]7�E�}@�{�S�΁ҋc+��KL�� _��7��S|���A���"��H���Rr�������ʱ�"	�
$��	Z^=�Y?����~(T,�ȃ}'�3:au�Y���8���~�-	���WM�aqR��=�� ��7���}p66���:8~�D���dЖ
'PU�?���&MC>W��ð�ݕܵ����g�����Y4�6=	�D6]���30��a��?���e����q��\u �-�+��0��[�-����,3z�dvҩi�E��ЖQ��2[qow�rj��3r~F���3�8�sX��m_����K� (-�t��T��YQx6��1w���i�ӑ�Zݏ\��-���S�;um 
*�a+����)᭛qK>V0"��\��0,�ߟe9�Tu�5�z�}���o��pj���9lc)��5�����	��ZI89ګ�X���Y��[�����yS�k<^[���i�[��舁���Y�!.�be��Z��Ub̋���Z^���6Ur��������x%h�+�=�?�H��(�����u���&H}�W�$VD1Q�}�>��*H�9I�YQQ|�pg��J�ZC��6c��!���&)u>����]ű���$�y���Q5��ޏ]XPc���O�sфҝ��p)s�]���ϳ��u6�5�'�9�����C ��k )\[ �p�Ş�q��Q���po��ۚ�LT�A��ӕ���X�	p��m��V@!�'�ƅs���ܪ��ZS������J�����gH�(�	RO��@k���|��B���m������B���m��p�X�Vot? |�ך=�"�,m�v�a�B�]����H�pG Ng��ȳ3ٸV"��NMO����Z�g���_��.{L�Ǳ���>��d��]	: �Ѽ^ :QO�j`�
>���b�(�h%���poK�'�Y�_��pΪ�{_�R��&ᣋ\ר���V�d�j��˒j�UA��lT"j��$�]�`�=�P���\M�ѓb5��Hp�7N��0n��e���H�py]�n7=�Z��V3�3�:����Ş`�R^q᷼�<�Qb2�p�0�d��)�ζ�[��_��3��,�=��ި��e�D��)��I1/'�(��C�A҆gV�_7�[��Y�O�m������ �M��F	[c���c����q�c�g��cy�-��[���O,l��5�mF�� 
�e���v�o�j�O,�-�)�i��U���3�{/ڽ��9Oo��;����LNe�X�����zz��"�,��GU!X�`����7l2F}���mv����_%CcV�G�n��k�V��S*�o7�ơ�-P�5�:U�������(��ķ0��c�l��(x@�3���6���F�9@�=.V�rI����$h8��%�]2d6Xg�NP>ј�`J��/R�l�WH!����H�}�����z��n������e0V��㍊������hCh��I��k?����D��)�(j����^���$�IaiW-/��wu��8_�b@��KF�i�.|!��]k�&1��2��fJQ�w���P=�q')�U���rR�Δ���#����>��Hs���Hoxwe��%���8ߊ�i�Eơ����GC�ΟW�����Y�6S�PIub�����
{�ioLfm ��t�Dlq����s1���:c"�vI�����ԙR���g��?�>S�]�u������b��	�p?Hx~CH��iKȸ�E���i�|(�8nC3plQ��t˻i� ��u�B���4:[eӬ��&�Ӳ&�T�sn%p��\��]��^�Mڱ��3�|o�e�]C�D��缮+������ <��e 3p���}�_���}�]�	�0a��
N�c��/�x��UZ8c��E���O6��{+�No��^���nO����Ѫ������[�xӟ��A���o�o	�b���`�c,f�A�V�E�0U��w6��Ӊ�Wu��)�����ys�,�W�k���#ys��=�Ǿr \�]v�զ�����_��U���N�0G`���z��|_n����*�k���� �B2�����B��yt������v�#9����i���Bu���|��lJO�
u�g�mO���z6� p����X�YRWraOKԐ2��)Z���:+���jb��<�-�wt�%i�'J҆�M�nl���V`�f2tރm�f����G�.���#��@F "緂��:lJy�O���>��pFb�S��@<'0�v��lL���0�$�)��8��S�\��84x�yz�����Ne��>k��Q����)%�lJ�w\#�/Z�#�G�8-ux�d��O��s� �Ijb�p/�Df���l\��X�a�A�L� ƛk��*MEW��ῷJ��)���G�	<W���¢�A��ٶ8ym�mةh4�DS�e��ip yeYbuLb92{S`{����k6�d�5=�?�z��.z����U=���+�=Z�@"�<��@���Nr��a$���'��T�Ѿ��Z����&{��&�|ߛ�������?"�#-��]�:<JZ�cFZ�j=�1t/r>#�B�Lp�#�U!t���E��P�J����*8n���L���5�;�*i*�&r,�4;��j�����:������*��V�bc���yS|�B-�X��6H���mDM@p	m�M�������';A�J-w�YFɚ�q��-��nk��`�s\,�r\a6p�g+�=�m�\o^���Ip�Z�������ZH>V-��Q7�,Sa�̗LBN+8���'�*�[`:tx�+00�N�G�:���~/�
�fI�-_F�/TV��,�������;r����/'�� f�qB� �0&H��    ɘ�(�j��l?lZX�ĕv?��y+����]������;Qh��	�U�	��:�m�MO�](�pW��wT���`-�#�2=UN����^ׂ��Pm8cB)�9�+2�PQX!b��Jp&"4[�б.R�I��(�i�����IX�?F�O�Ȓ��2�y#}��0��Ӗ��0������V�8���h!)�P�cw�m �b�e������jX���jX��-�y�X�0��1��^j�*^�5�]&��6�Y��x��Yڑ"5~��������"��PG�3��\Sxݛr �l�@�����0h[©`#��ޒx������9��ї6}^�-[َ ��tFuPӞn�<��1�L�$�X�i�&7A��26T	��፡VM�| u�����2w0�l�'����1_��zh�`�+�X�nk�<�6`�B��3&�vz��{x�&�U������
����n�;`q�
|�����N2���p�z���OF�@Q
9���Mo8�2 Of���۹M�+�j��@���}i�2[R��LW�o�v5�Lhi�ŕ8��"C��
����X�V[v5��:�}\Z(�;Ѓ9�*��1����I�\]��聃��YRAl&�n�;-�'zh���n�Sc�,!�%<�P)ߞ���_�!�R}�51�Q�c
����u���^��?}� ��;&w��$��`Zcf%�����q�F5p��� ���#���X-&M��	s��ѦN%�#�H�ٰ��DF�� ��bi8�"�\�U�z��� ��x�|ma�lZcE�\bO�>��.`ErX^��
)��\U0)ޕ���%h'�H��#' ��>'�5��q��0}��5�r��)lUZ�xUi�3fQgg�F�G�1.���S��:��ʃ����h�C�_A;Jxĺ���^�<dɈ�H��M��Ѻ��[�j��8�3��:�Q�	�&T�g=2���vӺ!~ѝ =u Ů�zNo$y�>$��>W�؄�q�'x2��ƫ�o{��P�w<�JQk�W�9���P]��c��d����{��A��|'���W��eh!���1W���J�����֡�O�R������7sab/t��H�+��1�Qr��1������Ώx�ʂ�7}��H�s�s"L-{�W��>@��w3�Û��CxD�·��Ox�Nt��e,{������J5|ZWz��Lє��ɥr���l�{�b�n �U����jjR�qyc�9��2�o�yx�l����K�)�(m�"2��M _��%�K�����ɒơ�Y�$p�du��׫7��_��rw\ܔڥ��B,�3#��Ib�i�#�ž��f�*��L".��5����?��XE��{F�UN�"�2Q��m�ѿ�^�q�c�|Щ�#=.�w�#]��������2[8.3r���#У�����
��]f+�0��;��V/�����Z�>��	"�I�-3�MTM���xWO�E�ح�Qw�`F-'��^Ng]��'����D������{�]3�C�(WEAO�VM>DAa�T͊L�lqx� Xn\H�a�5-�����u6�c��AW,+Zw���#�p[>l
�K6�5Q���rRa�����}l"?��;��_PǑ��7��{�n�3�14	���缯��_t%:�9*�4�k���k���H�n�坶(�7��s^ehHC�,M�c�B��5[B��y���p��&���|B�(�E� Qa���=Λ@�r�R����6�V�4��d��f�W�A9�F��m�H	)	z�2R���D$�	=���P��&!�;$��HR�pJ(�T���nz0te����I`�QqG`>.�8^d^�`p���9�<��1�:����$õ�ִ^/p .���fg^��#%�e���3#��b$���6�!UHA����S�J�d�Y
z�����.RN������pN�$5o.L�Ӻ�S}�{��ǰ��z:�6J�)����$_���R!^|Rq��Ϲ�{���V�Q�9��~/��ɂ��>b�-NRpka�u�~jF�`#�ޞ��ʕ�_�ډHHC�uZ�<`r�625��m͝};:�:}�
"�0iCr�5_$�+���"4�&Ԉ)t=�Yq��ͤG���H#C�0���| 
)y�TO P4��p�.6���q�drc6�죉܅�l��<�F�]���!��^��@FY�u���q21t��.��J�U��W~Sᥞ[�|��Y?8zӗ'G��I�=��'"�3`�p��)��]�������O�~`��eH' �{&��HTt
�
��hp8�(��DT��7Y
�-����|˼I�g/����o�/��el"�hc	�y��/��%_isI�ϵ�lЎ�������衽�IH�`�82�)��nHc�`��a����-꯸7�]m��m6:�{�٦�!z�������,�e�@��{�H%$U�)�:�&p��}��FY���;%�nH�e->8�"r^��D�T(�6�G_Ѓx@�;]�A�}�ŀ��!g1g�A|��KNv �I��`D���\��o(��f�ZZ��K��+���Ѓ��a=cz�ԏy~�B��� �:G,q*�����M�P��g��qpMV����s���~�p��BAIxE{���T1�7���`i�p���O��:��jF�0袷O玼�S�_+vA8���t�?���Z,x�"�R��{>Ru31�����o�_�@�8��n�ph	o���|�o;`���7�W���i��$�� �� A{ˋTTU1�U�BE�ܼ`IUle~����\,��-]�P�;�Q�.���3�6�A/��n���n�!���8,�H	�{�<��8��J9:K;2�g�=*B�u>�w*vz����l����v_�w�̡��Y�J;�n�L���eɌ{�6Za�`0�k��V���6�PD���b5+�tV�w<���FNCd����Ԫ�o����W+�S�%Ok��R:�[#�'�[-��zp���f�}���B���|�i^#鬺�fp&N���N�8�2��);�罥�xG��K��h ����!�\�+R8ɖ��i�pMd�Jp��l���k%t��E[pc�����i���;IK���E�th]�{�%����j��1�cCX�^�yр�)�[1v��X`�_@I�;��5'�pM�K�ǆ~�"��&�huȷ"��4�a��x*��/XE*︌T�&���p�+��=��}�r��t��Fl��k��P9č�C�����m�b3jж�JI��s>�I���z�q�d���otU�7-Ylrvٓ��f��|��{�i�FƂ��� p~��Ǣm���zj�̻���M5R�!XS�Χ,~\?�z���}����P#�i|[�� R���P����]8�nP]��k�0Q��!���j�3��]?~j��S���)#��5~�Y���?�����[����C��X�uk��,4��X[��$>��">*2��C8�kX%bCἆ#W'����&��;d�׵K�l�!�O�� ؂LF��uH���42��G�f��<�+��aj-�*��)"�'�I"�r��k����������sD��sD*��:�aA5p��=��<AN̜�5���1#�5�kq�˫��%*i�/L�1��`����,*ݥ@�?��Q�����򪇐d�}WZ�ԩ�N�06�6sD�jr<=�˜p}+΃S�:�3�s.|�2,��X?Pq�&�\�Wط�Do�'a΋}#5�A�HH���S���t���	Ec��n�a�9��k�k��������(�e��[,�a�;����R������_n-�v�����8ί�酝�����bo�F`�f����w��
��$"	m��G�
�N��
e)�����o\�e[N��i�%�x��T���D���	�x_?��&՚�mWI�$ћ�en�0�(��FQ�5 Q�{�!��>����mD�N�&��Ů�n@rx=�}�{}{��WӘ����Bϑ|;�)��~�DC���缦ڤ��{�������Ԋ��    ��[���ЛޢSI�˘遉6ƾa��BF�vH�d�=�ѳ>ȱHPr2y\�X�8�7����$㏲��ې�S�,�jӏ��,+	q�1��<o����X��(���زv����΅"�Ou��Z9v����t�Q��ShA ���b�����0:n���Q�_k��L\�-�@t�����l�ZK:���U�d�Q�|dy�ؗ�yF�V�$�(wOf���f9�ٮh�ė5�SY��b�։�Z����|�$��fND�|�m���^���U/_��q�B�k����z�j4�{8/��	�W�E��i�$r��_n�/�����'I;��ˤ�*�F�=���TQ�\Q{�f�e&�Mbk�� �7��F�Ua�f��A��ӂɟM?���aA�T�ov� `�juX]��e�Ihxe_K��.��@��� ��Pꮋ�R"Q�~��MsT�rM%���j"��*I�k.M����9�CU���x���Y��y#��9Q�g����_��Z�.-*�[5�v��\rE���9X����Ֆ��L�M�ЗV* ��t_Ȍ~}w7�U���Ox=�s�$�o70�F�W�.OIM9
Sr˲�ղ�:�$ARw�#<߀�}���S~kX�=�)�m��bʾ��hx�`7�J$������}]5,v��&���2,��*��\�z����x�j]��s�q@+M��E�C������v���^�y�I�7a?�N�u�&儃�7}�~6��φ�L�o�aJS���*Ty�:���a�z+�p��c�dz-�]y0g2�[%���l�ref��F���ɦx2���3�6�ܸ��d{��w��66�ˑ)�lcfɧVG���#����DGX<F�Fn��4�0�R�ݯ�������~N�43�M������/�s�%����_(�mc7���L�ȆĬ����b���!�4����'9�`�1�M�=��7�n<N����HORmؿ$&�9�6d��� j�Ȗo���9jfZ��gc)���aFj�AkT?i6��*��h���'��_�n\��Vۄ_7�TL꽁����ȋ\�7�Ռ	����$�����p�u��쑛8?��pbcx�7d�[��󰕦�}���`D�����
��q�}��e#�R"�i���'��߾�a�d�`rĎb�AvW5J�OA85M���q�����"|�+�,�&�!ˠ"�7AY��N_Q���2�,��e8����v70=��u@;0��Nh��v�L�&�`"R-�m�:syp�*��$����3^D�x�Cv1 ��c��L�1ˑ�ww���?3f���9�zvd5	��4�G�PJZ&�#��D�:��������p�2���ha�,�N�\�V�Q��.�ԈPJ|^�T�ȓ��z��4��"�c#�B�g��D�����s��aɨ���"I�G`�F�hR<_�"�>�66v]��^�"�&2�7��'��1�h'a�Ŏ��EK	/1�IB�E^�|��h�z�-��`&&<j*��à:2`��2%��e�����S,�q��U��Z������ς�y LF����\���$b�DQ��n����v�E�gRw��n�֧¨㦿+A�@�{��<�3o���dބ�Xxc�������q������g���Λrǘ.:�o{��]$"f%lYU�xE����߁g1;v�Æ4B�uE�＂�$�!_<�,�9�Y͊���J&��Aa�|�*L8��YR�W�6�\�e
k����l\�pG��r;��?u��=���1f�2�)�>M��7��l6�	�9ݣH}+��W��61ˡP�J���Ú�3T�����:GV|p�;�zZH{e22��)B��(��!�&�<�I��m��0W1����S���u��g��V�ݴ���tHLj|i�wIj����B{�Odp�Uu�c����.���9���Z�b*#}?yHq�n�ѣ��4�#q�j7#�SEI��&
\�][� �zUw8P�Q	>���r<FD����c1����N���x����PjW+�Wԕ.����X.5����Bb��+�P��їV/���r���|ʋ?��ʶ�gx���s����BӒ{�,G�V�2#\y�c�#�@��*�7�I�e���|��%ʙ�ѵ9j��Z	�we��|�C����������`�"�C�^q�..O�$:0���[�]ot�^5қ:@��͹���V6�d�C_A�| M�U���̰��I��%����TҔcp]��f��>naMP~
3Vd� l兮�j=fH���yp�(��8PTj���+*`��p�Wv
I\"ƚ�4X��kf����'���3�Q2�wz2�-�"�B������k����@ҭJ���C�6�QHF-�Ic'�-�cK��ĞC�	+ǁ�o:���Ǘ��e��IHTQ������(�C��{8����1Ss�V%�����dv�h��qa�Q��~��T�p�´�Y�YCҟ�q��F�g���NLx=����9*ɟC/�]I.o�R� 1<\	p�E���l���YS7X��Yo,��L�r:���J(V��n������ט��S��5��T}'��K�P0&>hLX������޳7R���vM���r*E�ʩPWF����j�e�f�	I���4�h���)+r��۠*��r$}C��
���B����R��ބ�Q�������67�ًt������+��۬Ujv���bU�R���� 7�B�5b��=�D��-��M�!�ŕ<�f���u6��n\#P�f�x9� ��3���E=j^5Fs����t��cnu��9;�/>���hyV���^Kh�l���~�?bA.�ٽ�-��1u>��L1,���F�X`#׀!p	}�rF�U;�c�1S�����_`���y���::Ko:!�!�YxH��툂G4 U�{̾%�Cxm-�m��\.��|�_��򕋂4t�1[lࣣ��P�CnyΣׁ��j��`b�S�[�	|"C�h)t�Y"�u�S�;��*ڮ	�����_n��.�"T�f>�Q1��G	#(�W�y��<W1�l��C��C]���]�3�l���9�C@�*HR\D��j>gZ���������x���f'�:{m���M�`����ې\i���7l���o�u%��c
�T����-�d��\����*ۡ�?�C}c�*~�ޠwB�w�)*��1�Y�<#��������8�շ�-�'�	���t�������S��A�V��J�>����p6fO6�if%����6��x��֞pm�yKm�D8�,��e�ћ�0F�0�U=ӹ'�M�X�!��������Y��
�p�����m�fE u����ʈ!8��܍g <�`�a�����af㚢���qi��(UQ�`����{��?��1�|��5�|�t�@� ��Z�l]}�u3G�"��!cI��~qM��'���h
�����I��E���r���e��N����u~��/���BV�~���<Ɯ�N>��9I�3�J�b*4��?�jH�\J��Ă��
4�|y��^{��ɂ�0�,p�?�x�4/��+�eC@�<��m��]"���[�i�t?�8pO(p~�ɟ:*n6Z]��9��6nNl�5n7�¡��g�����G�4A��6p�"�m��H���ѫ�G���� �,�,�I���eMX����s�{9Ҫ�Y)��)V�)g�ld��/�V��Rh�7���놰���`ꩱT���B��M�����M�
�Sg���?�E;6��E|cO�⁼Eʗ��N`
=�gp��`� �Z^~�j^`C�S����_�+���ľp�m�賄��n��VS���ǡ~W�����B�Hs�&�K����}OoFq�����͵C,a���yl���M_Re$�Y`]�}��<Ġ����V�i�E�	�{Kj���:lL�ay5^r%l�e��g�d�@H{pKF��~��?=�Ք"3&?��λ�����hj�\��3�!Tyl�zG�z��J�IX>`DQ�<��-Ř�� 1�L��&Gӛ���#�YX���Q�b��0�fV��?��O�]    5A��a]E{z]xO�7��,��q�I<��]_N���6�\��CM��_b��e��}I܌�����Zwؑ���\���"t^ͳ[8��.�o{����-}�������wɊE�����[�c��Jwb�FF`W\,�����"�O�5=j�*�v��W�E�L
 y&$��js#��_��6p��!3�"����_�7���M�F�%v�"���]��t�X��� ��(��[��_�citgh�}ud_���%�<��%�X���bʟf�	*�\`��*����@���W����'a �:D@��]�5�1�c/�Yv�t�reiHj��4R����]�W�&�Z���j
���8�6W�Q��6d�D��)��D�4Y�@��:X��&7�����,��?e���켘:׸tZ�<*��� ��L�@3t�A�E���� ��3L�s?�b
�9s��gw� �ҋ��
�gU����r�O�c��f��z�B��:(��R;��8�qӶ��<K[?���:���}�T�f8�B�^꼒4ϖ�H���L�v�4X�W�g�^��n!�;��ɵ���P'��v-"͡p~,�e��&�9�(08��e��;m�M��aנ����c���{~ۗ�`)�yϺ��q�_�+;Y�����n)��:���Ƕ��s8?���	�'��RIW�N)�S�w\L'��C��!(���d|1�H����C5�=M`2��$��4fu�Z�پR:���-��L�+�c?v>�a��?t񍀷�������1�[�)����jM����菟::��I�e���b��*����P�)�v�����?Y]&Md�c��:�Ud��k��,f�h��7�OX��� z��JӻA���0�S��}_� ɞ>�j��W��X�-��ƵdX�8F�zF�/�+er�.��/�Êb&�A�N�YA�!UMh� -K�����R��Ad/�4� <�Bl�L}.��1�^���HW0�l���s<e�e�N�ݹ���f匃XQ*N�hJQەb?��h�?e;�(+b[q�%����<�y���[�rq�8o�;����(�86���S�("�ȥsk<E����b�;'�BU�$��,�{�š���mWd�_��*5���a*9�e)������[ʳ��p=%)�ɮ�p�q&0P=���2{K��d7�eO5�����`��Qp�����$�H�.�vMI���q0H�Ϳ�\���\�|��޷��C}�mvp�^���u�Mf�N=p�W�DEhQW[	r|������]����樤�yf(�[4���.+Kw���-�߱l4+B��a��>�Q=2X�>5wY�KOP�-pj�I½�,��R:��'+g{�i�j;]@�=E��c���� b�/.�7=u�!�����6�#Q�a��::"�?��N��::i�����j�G�E��5�~i�˷�X�f�o�=2C��G.#�|�ɖ7E��nŢ�K;�@l���ň�l��E4��@4�r�Cۗ�˚B�N����Ϻ�t+T��I������N���r\ �F�d��6b�;��*��jl9(*�d��=� ��X;ΫU	v�:����\X�c�٥�m�(d����:(�Q,T��\�	����.�{Q�j�.ߧ�!�J%AUT��f��g� R���y]��k|�|q��jB�{ǀe%���lT��/<��"1-)���kj]������C�jN�=���7q�긎��\��B��,�������,����b����mU���PN�v�+�v*��zt� �p��X?��ž"��֛b�Zͤ"⹰��[�0�������ȯ�֧��;-|h衯�m��$6&���2�E%o�R<�B�x��7��E�<
�Q�����D��a;�ʪ���{��pvG�*��t^����ʪ2�
P��5�����6��P��HJIw]����m���AŸ�[����nS�(��i��f1��RvXq}P���q�Z�n�^�v]���Rl֚1�ӣjYr��O8$-���!0c�����|Byؽ��լ\�]�w<�3	N�l� [��Q�+�d�u�i���β'Te���
5��`�;#	�VF����,:��}��n���ˬO}Q���$LEk��~�rz����Y9I� �v�nU�B��s�A��ł(6{WѴǉp~�=���t���ǯ���@~�Ď�ڧ6������k�A <��6ݰY:-#��7��2�t�;�;@�!�T���'��|�?ڦ� �7����L5�Ǉy�����C�Fbg���h�Φd�kX�8WNC:gX�@�Ld��Y���t7� MQ�*{�s�w�90vtg/�~� ��au�O�%���:�
?�oU�?��Q����⎺Y���n�)&&��$����'�>s�6�
�j�13�DL*Ӯ�'r��k�����&����U�wXU�G���
N�b��]������R�O\�
����G��u��pMU�
&1�e�Cǡ���P�3`��� �y�^��*�'�U���Y;P��-�H�@�C|�I�͗ThA���4O��p���-l��/(VMt��r��N��@	�g���ZiN
D2���z:�X5�}���X�
TOw�rﺊ,�3*��u>g��Vf����DM��颤�u��KB���p~.�
��+\�M⟎ѵ�T����oۋ��}r���|�TE�D�{|�3Ef_��o�q�)m#��j���i�k�d�Y8��)��n���ק���bX��2Z��2�tE�UJa���,zD�:t�� �!7v`e��4~h�*}R ��X`�ܖ�Td���iN��}����F��i�.%��5������Ʋy���A`�R�8�'K�5A!4��v�hx��V�i꼒��W*Pu�j��)Ŧ,v���#��ȍo�u��}+�Ec'���^뱎��}�O���O��
�#�&������'"{�����s=z�R2q��4]���^KS��ln*�!������N£U��a=J�~�z|_{LK�����h!�..E��'ӑ�)R�n�SDhϰ�pr¦W�@��%ɎGV%y��J��ɴ`��/�!�5B21��+i����.���sH�?t�8z�X��p$���'�?_QpIm `R�7X"��Lӛ!��g�]�a�&`��-�v
�~'%�@/�HY��3�u���xB�^S���4O�EW�cߍ7��o��f�&�M��;o�%�.z��T|�'d�=W�uBm���i��+ۖ��w���+���U]��-tޔ���������%��0X�n>+^x:F2��뭪4R�'̂ah8���51�!��dF��}i�-š�꺙�+�L
��"qށ����.SM��YPb�#�5�#��:���ez~%�!��v)O�Ʌl�as�&�\��j����u �_����܋p�b�fDHb���D]�Dv� �&<�MM��cZ�~ԋ��v��J��M�,4�'����Ǟ�G���s8�'��� ��Aq�h#�;�������v�E��T��,@�z:]@��CD(�b��rK�ÁŒ���,�HFlB�����6ȵ�Mo\��l��u'��m��)����r}Gq��e�VH���]�֞H=��KoOO��^�|�bSڞta�X�/Ж�[M��6w�{�\0�Z� עĜ��[���YF�e�ѥn�`_Bն\]{������w'��&���N(2OEV�$���3#2O|f�D��dp������7m/��t{��K�(*�J��^g�|�TRU�VH�i
�ƚ�e+��˜g��I,�U`��'K'A�<��>�t�.���(&�p��=��Uc�|�*����~���K
�$K|�y����&{�k%&���������p���Q�� =�c$ԫ_����@~�}�����wt:��	,=1�srd}䝠�<|�NK;��2�(�4��#@ď���z�	�ͳ:+���Z��A�k��Z��Ϯw�lke!��g�)Q?q~���TЛ�tp%a�f��^�³�hʥΏ�Qt=3\�&q��#kcB̉�-��y`8�&�Q��(�γ��K0-    ahҋb>no�\<��(�L�������Q�t��"���G�<͋t;O��_��7f���3D%�;;D�^�	}+�&p��o55�×o2|E7�U�J����{p�����߂>�0 _��ƕ;4��H�ž�������bE8wz�Vp����ypC��K�\��u���[��!dM���gMb;�F�7�Vcvhl g�{�r
k�p"H��9���,���;I
�E��H�8l�%�=��<�Ϸp ð��#t���n���B7|�J7s��ҍhS�*u��W�0W�y�t����4W�κ�W�B�J�0����2�J��� ��P�#k���.�S��e����V
Ud���[��z> ɷ�ճ�+p��^ϡs�Ds:�.�VvzC���������<��KyJFο��R�76V��;g��h��ii����0v>������
���n䌊 �@0;���`��j$�1�X������n��pEG�*��j���e�����P�U��Q�F�N[�ٚK�"�1`�*l����8&��򪉻�A�a':]�b\�_�G<� E�������e�m������{��&�LR5N����j6�al)�X�2������H�I����(&�@���sϙV��o�`i�~�}�)�� ����	@����F@�Ѷ��L�ţ�8��&k��h(����!�{{����͒����p��e�)Z����L��L��wJ�������]�$p�'*,I"��ܓ��h=�W�:���YW�I�a�ctԅx3�~�^��t	X7
�%�òB�_�ub�1��?�B���$�l�ʉ��5�I�1�\1��\e�]�p�f�.^0,�'���:œ�W�,Ņ�(���<-����e<�N�eZKSZ�E�z��9��o:���; ��|�_B���LY���������/0�6���Ƨ4��(����l{j;�F!��jI�v|}�5z��jI�(ǔ�29i$΋��U�7��9��Es?���h�4_��[t\�n�������ܢ1wslYjs\�K-n>�78�4���BR>M�h^ZE�1���Y%���n&�&J����#/�L��)S�|�!K�谉���nїQ��#`2�{M���g�gY��������\7��F�Zcn㍥��S�:��t�����f3e(0��2F������~84�0�������b��,��&(z���mlkK��X���e!�km����Ox�-��q�8�*<�0�"��Y�����G4�.\�Ԝ�WO��*O.nF��E���J������4���Fn��
n���V��]c��/����g�rbN�&�'J��-5ADs+��3���ۑ*9RWW+�j*Q��ɤ�ũ�J�7	�vm�w�ٛ�D=��L|�D0xUzF陻^�d<_P8��7����J_��L->��e9�A��iN~���Qh�1��L���Ɣ���$�m}�j�����"'n�3�z�r�$�<ŋQg	���X��X�R��3>R1=�p���p��b#͟�B�������x1>u4w?|�vFXj �&2�Ek��#=Ig���p?r���e�&ؐ���~?:7L����w��*J�)��O��o�ҥ1p
Dj�2q�<�UuC��ty�F�c�0�:u����Ѵ֑Q�Τ4���������B(u�y�b���ێZ�o�UM|�C)Kz�F���p�[6`�<>W7Y���Ä������)ڒ[>�Cf��z2v.O���L�(�P�!]��m����r?����r�'8a�r�����H|��8�G ���جWEW��o�"j/��dͲ�j��%�0���q�k�%���z�l�P�^�p<�A-Q��Ï��7�pۉo��h���-��V�^&ެr�	|�'�����;Nʧ!���렀}� �5˵��7��Y��=	ͲoK9�/�a��-��5���xxvx�]��u��u���{Ӓ;W�j���['���e�3b3h;�D/k��H�Zը����iKk����W�ѷ��2�,>I?Wմ�`��h�/D�G� �G��r������oxk��!P3��jl"���34�v�ɞ t��D\b^gzvY������49�v֛c���W������+�ak+�㢒L�ڣ
��3]�s���`� k�7)��4�O������khJ�Ľպ�1�7��mC�����@�(�H�b�"���D���[[�8q>�^��@}mS�DD/�E��̐B����M���$ah�
!�E�	�A,ڄ�Cg�:ߍ���~,>;v�;�����������wǓ���v��������^��0�D���h��NKgͶ+g�l���Y�C�=�,no�__ǇS�aw�Qڊ��G �	8'��=X�,�Ȟ���u�v��ŝ) �P��NE3$W��2�0"��o��lxȹN�@�8����
\ӷP&{&��uc[� �0��S���rCN�V<}��S@M^��4��WuĘ�r�&H�� U�$,����dW�g���h�U��,�&t�p�;�����?غ|���I�⩢f��f�] ��x S���^g)�4f��W-���1��T��g�:(V˯8?_��e�F��ʪv�r����zRb*uayE�3d�'���0���}[P��u�ÚJ��
0��WK%� �{cL���[�����	H��t1 �z ��z���l�_�c�~P�3�ؤI�,�F@��� �.`(���p���ƶ.���r,ַgA_�&�U�e�<�Z�X�cN{om�������5�X��5�򩋨��wZ�'�����a"�<�muͺ�`���eq�m�m�A�T-k5%����b|^+��k�5s]2OMV&>2�_O���XM-:Z��ih��8�͆R�'�}�/n�@1�=���і&g���D���,3���6K�?E�T��*�̊��B25U���.);��f��Bç=�DV�Jp�CFS�x2�����+�XE�Zew����:����^��A��g �xNn=��:�2J�EYB	_�)�ø��g�Ɲ�
�k�X���8�>D��K����H�7jb�]��wym�'{�R��U�0�d�~�=�]���v�XZ��� ��7���Dxo0E�f��~!��0�nSƁV	s��|1V�E�13V���"e�Oe�](����70�~��4ƀ5Q�2ǫ����	�)C �i:ǚ��Խ��n���)znH����8�L����|�,Uh;<KX7uaj�C�,n��K>�v8&붞	|�~�$�!��q��r�	Q��؈eNj	�w����W����t��p�Z��EmR� ���c�4˱�?W�yEc�Rк�x���?%_�dC8p�2%�n����b��b��#01Y��a�%>5�ͦb�J�"�9���ʉ��U��+��Sp�6��$!������N�r '��=i4L�����>��Q�����\��o�� ��A,�`���o�I?�jw���e�W�[P��۫/p��%��ժ/ֶd�����-@)A���H�Y�m�j���5��5��7�*V߾���y�s�O���#�����9�n��B^��3o���g�2$@��s�oH"q^������|h�>I`��a��ʦ΋���2z7B��׈���J@�H�l�A�w�#�z��lK�X&:Y8ݓ/�btR�@�2��9>J�Z���?�!_��k�h��0q�!�>��7�tsЋ������}�:��K}�ڢ�I7{�\�:Q��Q�gt%�i�:w"�`S��4�5�	��4d�AOC"~���]�_ȡ�Xπe�h���X7y���F�C[��,�z�(b(�!�C��<)AT\��J;hzB�Z�|d.�r����熒C��=c&Y�H��.��QZJ~�~L�M��q�]��w�jN��x1��hx�¿q-_�����"�i�G�F� K�n��z班x�P ��Du\.���dr��7ё_gp���i�j��)��[*&w^݁�oLv���F�Uq��lOkv�L0� �  9⌫_d���M>g�C�lk	��~V��Lj��G�y�2��V�(8�z	�
-5.y������j�d�!�"ڳU��٬�3C�5�Y�)P\���֫(����]0�O�Q0��V@}����Y��Sm��v���燞�#Luö�ǒ��u��H���X�	�딻�թ�#�װ���d����Lc���,���
{S�v��^�F�擶�H3�Y�T|\.Q�_�7 ��=�2��kf�w-7y���x��i��0�fI%� �+)�
�.�N��R�����VV��
��M�#�{1 G|�C&�t�rb*U�Ku�\׵h~�%��{���A���F �bL�\Ȋ�X�p�Y���5�?ݿ:�l5���z��5�j��Wi99<���My�%��5�e]Nj��>2�&�M�t���h��p�ml}d�I�8`mDZ>��O�'����'\ }�����a���R^�b�!cb�ƙ���9�~����_}j��A/?�^�e��'�_��o����\���b����ɻ/��7�o&�4-8��b2��x��r���WD�=�����8�Ԙ~��?��-�LRs���2W�?�L0���4�ŨԀ3���ۼ�a���k�2�:�(�&R�Y���	]�����+7�g��y�?�!�ثZ�      +      x�<�[���
E��f)���a��(X c�1f��9��|$�ݏD�I���h)�����&ɯ�>rZj�����#��ka�/~t�OտN�vK��6϶mU�����MP}�ˎ�,�����g��*fW���l�͈�ۢճ����7�#u�ׯ�&]��\}���}b��&]�p�[]��E\
������lҪ���D���UD��m��������J|��#������l��U5����Z��ʏ��V��Pغ� �w��N���|���s�k��7Z��ܮ�� �+�	n7�����E����j�v��ve���}����s���o4���wA/6?���F0���t�I�Q|���?���[���	Eo�]~R�5���G���]�I���]W���O�9zC���'��ٴ�K���w��]zEk0���V�!O��7�� t��v��K>j�w����ib~���s,ھ��􂏶]Qs:LS�M_��6�W_����TM�X�����n������@��v�wt�.�@'4����ݮ]�M)�э>����l�ME7�6�h�f�����:m�������tږN�Ѿf�F;D<4Q�ďh���}J��ѝ�#�B�)V��t���NCH���A�R�C��#�����?���������M�7	�����8�������n&���;����w3�=6���d�)5�5E����&�w@S~�N�-'��4��ސ�OKw��wٚ4�ߓ��ïy�R�)��|����4�bM톢\
��N�w��[�}��Y�?���������M0�Y��[>�u�4�x�.3�rm8��_��7��Qi������_}ҭΣ�]+�Ϥ�}�kA_�����V���`�Q,
?z�<�w_�Ӎhu�:���ֿ��-s�P'�ڧ�siv��H����t��~�L�^��5�M�Iˏ�̯+���)<Z��m��Mj��̭>�⻰��״��U*��=�K'��t��;�����G��&f���قgPk�	�n�`���(��?�h�)8rtՃXA�W�u<�ڰ�w��i�s;�t��`&T.� �E
��G�����Q_M�L�>�,�\������Ԃ(]��]H����Ղ�Ot/�(�A:x25m2�Y��b>�]u{�1 ��wP���Aw�����t�����w���*�+�g]��k:1�u�ه�[s͠�v)��e0Qt�0�f�A�_k(�:G0��ҁja@��������(L7�Y�G/?M�8(���+ }6x��`���`�����w5-~��ЁWA��p���
}�==�����ˑ<�N��~������@�o?^M��&��dXH�~����+L{[�.Z������؊�t��b��ҍ�6��=y6-(���D�I�1<$���c�g�;�;��,��~4��ݮ�C.�wg��/Tt���f��~�jEG�n��I������W=���0��[�if2�Lz]�PLz�Gۋ�`����<
�N������tK&����7�V���7��ϛ'T���sɳ)���,y�%Ͽ�Rzb>(��}���~����_��8I�H��GU��9����n���	����'g��L:]k�Q7��/�#=+y~�������]�_J�)���/���4�|�V�?�k2)�S.A��D��	A���ަ��ɫ_؊�f1�,��7���SO�b��&��G~�����!g��d�����7�}��W[���Y�����Y����ޢ3O��(���x],z�G����bi䣉����ֿ!����D�GHP�m���H���^t��~���ꕑVk��	�} 2(�D����C�xj}���s�'�Фt��F���\������X<&�7^]��}�>?�*�i��+�]<&[����B�]��Y�������X����l��R</��Y����a;�dm��B�(R#N(�J܉y���x�s�by�x����������G{���ݢo/����ER���Ôtѧs��2cS4�(F�l���m��ѯk��.��7.��b�ۿ/�P����4�~Wj�M/��'��I��+/fɋU�ży�v�xm_�|���6�M��o���\�h\,�,&ϋ9�G'�a��|�����3�E_�i6m���$�?oӤ�3��=������k�3v��]=�e�1O��s��b�b���մ�F�^̠�ţx�ƺ�ɋ���+���o�S8�ǣ�.&�j
M�
=�].�����*y�D_M�1�B�kb>�'�_!��Cz����y��^��~����D6���򢫷�ψ���ϋ�見g��/zx���_`�uS�Ŕx��u�8���.fċ��x7���}�-�]D!^$����^Ufj�y&o�i7����K!҉��=7�^�f��t�͗�M��<�7������̓�5_)+��5����f=�iMn��,(�|O�ͫ��m:`�.��w�i)�_���w�l�p7���c���Mq
�]�Ć��f��y�n:��m�e�R>��)2l���A�6}t�ڴI��n���)����n�{ަ[~t�6V`�����xlnz�恼�V�N����R�ɴ��׮��MO�<N7k�ۯ��M7�L�ww�ͫ��3��@贛o#~af���7Ť/0���
��C��y����m6���P,t�;�����o�x[�;�+�Ov/�n&͛��̓u��6������ۛ����n���n���ν�����l�j[m��tZ�:ڡ_���#ʡw~�u�f����İ��K������=t��c���u萇U��{j���(���&4?�B��=̦O���C��8>���k��×��C�)v�o0:��C�>�gR��w�.���u�l��&��|�G��yz轇թ��0�><O�����=�����0�h7C/۞~�V��E��C7��ؕ�Ͽ�I�i���w�;�?a���V}�W�d�������<��.��S:��-ܳҏ�9������_��t�4�(���0��wb���)X�=~c<�?����V�P4	�&�@=���~��\O?�}��z��=��_F������9x��ɧ�ۏ��Lh����z��ы�/��=ρ>��_�^��厾|	��׷?,^���av�3�#����������eI���K\n�˃���d���zq�P�'ց��2��Z��D�I��
?O�n/���W��c��k.3��#����so/�^�����,��%.S�K��|}��T/��/]���.o��稷碗����ez�J�*MQܦ�?|\�n?�.ϰ�z���2�l��T���P�/`O6/��K?������=�z�����Is4(�������x陗^�6r�n��9襗^��\z��iw{���zY����^|�s���˺�e������{�a_u�N��~ħW]�N���evx����Au�W����b�}!�9����O�{�����f�o���_���O�?�f��,��äc-�����3T��
V���ᓖ��B={u�}�LM@5��:~ ~�~ӊ�ݲn@~S��1���T;�8	��i�N9EI��2~��6J�1B|E��H�B�`�k�=�e�K�C��O �/�A��HW�����4,����"~tzL�ܸ�
~�孿�����*�4"m�T��-ύ��,�r(LYTyx�oy��:�R��G�:5���m����`�3H-��W�""��'��w��ǈ�~G̱���<��S�رHF����x��9�ڱz�~ǚ���2�\�L8��z}��z�j��,�w�4,���b_�	؉��������İ�7���{�!���f	��x0�Ǘ��$5Re�=�n�L<�E�"v���c����k���DrD������<��?�L�P��q���@��	�n�1=��`:�rhXJ����4��+DT��x<��Ȥ��`<h�Ϝ�7nJ� !�� J�qB�� }+t��� [�E=#�N.�@����	qi���)U|�(�M��(����A�Fy:�4'��*I�҃;��:P��g�zFz����^��'    �N4Xb"��c��Vj{�
Qۋ���q�u��j�ч��8�Þ> ��ʠ�>|Ұ�
3zؤ��}��ǵ�.��z��U�6��Z8 <�Ҹ�>+�4���l(��"`{��i��N���9�I}_j6UN%���vhN�����Ҧ�Ogϧ���4�z���H$$�S�1�M��8YPB)Zv�	@�r�~
Z��'�?6��Wz�i����ڤ��O�Ì;�0H���:�"fp������ڱ45k;�s�@�&_��OT�H�J�	//�]o�"�˦�[�e��2s[=�~B�PNM��)7,����~TЮ6���x:�Ra�[������Sz���D/!m�E��Ԃi	���.�
I�q|����! �"u�WT�m�s�J�qE&O�iwn��t��+� �OgVHX*���z)�Mv��i�k�W('��<?�Yy�������������4l�8?�aw����`փ8ݎ�vF>���J�C�5-=�~��}D���p�5"�
�?�`>�.�٥�L��� k�B�J�Rx�y�	*f���e��ƌ�c?M�_h��3�!v�p�v��y�� 	,h
A6UΧ��9C�N�:-��Q"���}q�{�~ǋzxl�c��U���`AL�0�g��W�P�h�J�#���=E���(թ:������k�P�L��]����;���Mg�j*؆G�-9����dY��Э�C���*r�YA{�Ԍ �A�v�(un'��I�Ea�����vմ��}-}kN�j�HNg�@��u�~������hg�i�������%h�.�rB��-�lC�"��=%}l��ҴW��	tT�P�n��ﯹ<?�wB��qeЧ����* job�(ӏfev��ۂe,X�,'���P�-SC�T3nV��"��QW�����=A��k���`6�R�aY=��	�T�G�^Q��JC�TNMJe+��Qa�����!����m9�4DG5�V��a�斋_"���'�:���>X+�ak���r^�,-*%�?{��k��>��|){�p�f��*XĿ��)_�*=�U�h�l�Bơ��VZ}f�������O�z���JS�}avZtCANCT��ʇ[���)/`y��%�	?�z&[3_>j,V�������h�-���%����Y¹8�.�L�W��V�R�%��w��\�S�e��t (;yم�'mmj��O�,d2�m�~]<2���N��X=_��;�tP{o�[�E/��s�\n*�u=��"�`:o�)S����}a:���}o,����O��N.+-W�KM�G�H�f}^ˑD<�/H[�ӅV5�^+r���b�~���t㟰_c�H,,T�<�z�"J��v�O�f�B��Z9`,W����帱x��тA/4���|�^�Ѕ}!�3��J4�c%0N��j�`�|���U�mj?�Lh7���K���l���S��&f�Y�>B��-�R>���E�������l�i��fT֥y�[M5�`h��I�Yt@W����� �����3�+���sA~��\���-W���"��8-��w�!�k�L��������r��ʖ���ŚI�P\m'6�i�b>�|{��,�h_�5�4��/b=C�\��8�\�[Ne������(�|mX�6�ǚ�$��_Z��;D-1`cCLl���H'#b����������,��&݈ۉ��5A�X��V*�,��_2��ل�A�%���a�I?��}l�!�e�Q��I�� �|5;�k�%��/"�0�Z��u���d���~;Q��q��dʮ�PW���5�k������~D�5m�����}��m�W�Aa����cX<I�R���C�;Gڎ&@�0T��Ȑ�
q�΀���Z
}c?��1y�߮�of!�͚�v�m��
ӆX5�2-SfQX�r��4�Lm�R��! mWb�`=C�Лz����Ķ�Nk���tE�C�Ti7�_ﶣ��4$�g;����ػ��`��\)�H-:�q���~�]�͚M��<�j��.��|��H��в/�q~p�4^^��@gM��?��i� �!8�Y#���^�k,��B�x����Kx�VqA�8n�$ǅ��
�����]�8\��k�*�����89�P'��+���8���I_��~��{(M����A)�q5{*�NiQwAt�BƱ|�:����8�Z>P��[�|J#�������.��ugY���K��c�qM�8�9�P'?��x�J7��~����Cز��S�ϰ�|�:Q�Kh� ���+�6�
�BG�yM��/Á�'��p�ڹr�/��+����(�J��S�7��
0�!�ݒiэ|i�ax�?����7����j����:5��!�|廮U
�kf	vP�������!�����O
%�R%�v��M���~�H����o� Q����__.�S��{�����<0=S����~=��/�~x}Ÿ.�\�*�Ӈ�;�M��^|	�c�u �N`.)F�����rE]'���@��d��ü�f4��
��k?݇p`�����:�=��r�bp�a���z�,�,*�J�R��u��ڟﲒ�u�����q�8'>�_?�]_8�����r}������b	�Q_�/�e����LC�_3z�'�ŕ+���p}	���iG\ٸ��\_"�����qMc�2��D&NO���c�wQ��"t�f���d?^7(�0��0�@��wg��1��i�A'x�1JL�}0��ق�Bg�����g��߰r&;�G��ʲ�a���R�y0~�s3�ɏ�̲���P;Ә���<'�i���a�$.�bҴh���4��x>�-�i��X"h��pH���L~�<��͖K3�俘�b�dJ�#p�LMHѳ0�\Eӟ����a�����Jګ�K��X��4+��4װԅ�zGS��|m��U\��s#��o�j�O�-O�l,���1�L�H:\h�ƅX^�e�zpA���4�M��a��_jPn��q���"��E�����&��u��lP�.�߬-?s��<l6e�x�:+d����]~&v�o��0,������}m��dLa�S��]،�!���)\q
I�B����sN[�o�P�	�~�@���'9S�!��i�8�M0�G��fCe�{�%]z��XMA)Ӱ��0]���xI���༌��x&Z�s��4%�镆ٓL�6E6�Д*PC�0ӫ5+M�!B��b�C��4f
G��G�b�(�c�&��s8��.т�#V���0fOC��A�V��2��-�E*�6T��3��Ӧ D�R߼�8���c�S�=Cˆ���f,���d���Tk��hSPb3���K�l�e39X�v��a�^,���Q�16�܆�y[o�j��LC�h	�^��F$ˎβ!�\��X��/�N��f4��8>����c:  p�	Sŵ��-v��޹��u�׊;@���B&��/����c|N?��u���?�M;y��ɐ#�r
��i14/M���lνL7Y�(�:�0�&ЕM�q��v��r��a��g�6��t,{��^&�ɛ�Z���lo�C��Ӭo����͙�t2���(��/�����%�{������A�j2T��īNKa�8�Mgg��:X��gV����ֽ�tf�{��f�����h깙^r,1�͆ʩr��Ac���fr�8���`澛��8���M��2�ʹM��M>55K%��;"N�B��Sh�|����N����~+o�2�_���Ӏ]�MƦ�l6��M�ȗO\�|Y�Ӻ�Zo:�M�b���\^���.�oY)S�MǗ�O�+A���Qt�V�T��M�p߶�6��|�?��xӑ��y���
1nMG�yl
�gӑM��|������YooE�O�z������h��ŝMgU�z80o�$zS ��٬tA8�Ң,���'^�����GP��5|ʺ?� �$���C5a	�z/��{����Tv;���锥����>���n�p�	��k����"LQ�L	w��0!f8
�CL8S_/���bY�"n�[
��!J���Rc:2kF0�x�CocM-ȟ�
���B3lX;    ��pH2��e:�6�A�m�P5���Д~f��J��CX�;
P���)p&L�,Or�a�,�XSؓ�ܗ$$盢V��)�*��:f�cL��ÙO�����5[�qWm���)�U���ӴH�eo'u_S�4��9  h��d�s�8`���uN��ꋔ�����&i�(J�U�UMO��ka��i�ʾs����ä��f��H
�g�.%�9}�J��t}'�������G���M�pT�K��������e2��(MC�n�y�T��y���o�|�j�{QSR��jÆ���+A�$#}qJ>_7�Yta�]?�w�#p�|�"e)�{u�7�_�`�:�ٜ�iQ��e���>sz�̗w�W�|Iw}]���H��$]�Ig� ږ���+RI38�[K��ܕ1f"�Ĳ��բ�[��X�0!")`%K�4�/o�$!ymK���NsN��(e�b=��� C?����t|Ig�u�`�iZ�	Zw�q�֝d3�&HD'�q_��#�v���r������7=D�N2Ns �4�u�B�bt)*r�h�t���@ɫ���)v`���M�Y�<ݓ,*��SҐ�ξ����n���Lӌ�S(�$�4���;͇��Ez=�QR�����^>��O��ͦR(qj�6rZR�-�C�Cu���d�|*G@ �)��m��i�f�튁Dp�a��{�i'�)�I����'�w�*q��p
����`�{��\8�N��B�P�p�/'��0��%?{2��i��)�w��C�C.�Á��i�B$�в�<)t�'�$aS����f3��yQ�����#��w��S�m3�shQSu��p��p��f;D)�/���a<��Jwh��\j��r�����:�D�i5�^2W��6M(��}�X�w,W?�z�_���N)d?��1��F�L�4+`�ڊ�/mVJ���~�P���O�T����J�w�)�r��;��\D�-:Y n�P7�}+�,���`�Ӵ�S�4��$a"�R♫�zʆ�C��~#Z~>Z�'�jH��Ӭ4�Z��a=��$��J�Ԁ����M�c�);c0W�A;E��ݱ#�K��}����ٷW���"ٴ�vе=�Ok3N�H�M��暈���|��_-E
���0,^
�wٍ�+��>l�̇�lX�yiԷ�2B�B�ꋁ��i��f�lM,���0-Sɵ�϶�n���
S�`�$9l:4�K���� -J�
;"8؎ۑ�|�ӄ���̶�t�J��P�'����
���T*�l�8Y'�i'�ئ�ؐM�3��`�`	-嫋�[��&
}�����E�Y���)�v�ȝfml�2�|�خi����4�5}���(D�K��ċ����L٫O�e�ټ�l�s6N3<"-ʹ�I}Aَ%f��a(qh�ٺ���O�s;�l���l��9�K����me;Xm�<���!h��)��g�"D3������4�4�~_6;����:fv�bi�~[�0O�oS�.A��=��
CA�)���E�,B�t�R�)����R�i$?I���yq0����`	--R�P'�5\�};E�b����q�P-:��,,�=��"^�����y�	��q
�mV�����4��fJH�1��Q���W���)�u��E7`�4�/��Y6��O�"??�C�nc�GN��S�k�P�L]�<�J�Eٖ���jS�n��:�"��"��'�=5��,
���	�ְ�xX�<�U�]���x�La�H��&p��S��4%%n-�?��N�ͨ��	Ǐ�V�О[�Y�UB=/��nq襇���D����{�u���p\��
�;���tn�0�0s�<o?��s���폘sON��iI7��\mƉ9�8|h5�$L��'��!�o��z�ok!?W\���&���NSe߇�a���f��9��5�O�.�h�n���A��ڬ�Ixj���X��i���:�R6����prb"�i�L$�:S���������,סAp�;̓9M�9��65h(Գ{ʄM�9�m�����:X�E�rBN̕�L�C�aܲ(�4-K˸B����l>�a�$-9�c�u1�&�!Ӥ�Inį����w�lN��͞���%L�"\�Q��3t��pa�L�Zi���/�q�:K����4�\6���9f)k�z��A�u��u\y�#�{�L��3Ԃ��C�	1��/QN�<���6��NS^N�bN��Sm����^5~o31z0ҟL�ަI`+l���^6��[,����3�cw1q�!�5�|	�bZ��)��2Lo&�q��ɺ�|�Z6띡�aI�ʩ��z�������3��2`S�[S�x@�A���6���emF�ڹ����ڬ����a�I,���J��Vc���݊��za���ʼ�!f�Y<兩+u�w*M-�y[�,��a�g�`NaG�,5ኹ-�oٮ������3vG�߲�����0#&e��=����nw]�lYt`܍ۻ���~�1&z5~�w�돗��&����xǫ~�t����3u��ߵ��nLx(R�J��.tA{�>����/��IA�冽T(h�ͺ��!�m��\<������'�(G�ΐF��E��7F�&jK-4��nL6)��tx�0�!���#ӫ��ϾM�S�ml�ܑ��+J���p-�ø�߰
�v��H���L/�֞��`C�Tə��ﶀ��;쾣�����n�)� l)�n��F��l��Pn
���p��V	�Z?_u�z�x�sF�+8������j*а�����=n�FⰵT
�9�{������m��S�#�̒T�;��%.���.tN�їE� R��ٵ�����l���A�!2�KI󣳺i�xe/�U�j��u]�T�g+&�Y7�{�6{�i���B]%˞`�n��B L)e�����6'e�Q�a��(�,�-u�=!�2ܒ�sӲ^�T�qD�%&G�G�*n�ی�n>*3�C�K�8����``��:&{�N��#2�?��G�~0i_���"42�W��0�q��MwTux�N��7�A�x�K	�����8J!�7�[�6�Xn*:�1t:N	c��a��`�ަ	퇂;�6*K�T�5�(����E���I��JͿ��lj�2�qE�b�/p�+3�sIٳ|҄�'���o��"�XR�0F;1���91+�BC�c���M�Xb���td:LM�Oc=�W�h\��y��ߨ�`3���Q	�d�8]�R6�g?]'��T��@��"rH�	�:/�g��}#��n,V1�66����]�q���/H%���݆�g�I�p����o/c�A�M잡��8��I���񤊬�3D(MS�4p�P�v.f|���qxfogd�[�o�0z(��ƾE�]��^�ok�p����Ͷ��R<i�^�!�[�T0<�s�(k�>Ġ�G6�+��&�)����W���r82g����aV�$a!+Y�4���b�#L�|��"q���N�pdy�2�mt�r�M��77/~;�m�c[��q������86�3�p�I�T��:��A��҄qc:�5൭^�w�p��鋤s���򂒼w�|w2nƞ�N3��~�	?)�J������ӆd Hw/���>�M��l��.N��aO�<�	���1`�� ��b#Q*��[��2}�
ݞ�?�2��3A�p�0�g��v-�nX�Z��[�;@�VJ�1�i��Y�ȷ��cG����ұ�̡M���4�F�'�5)��)��RX�|�~&�$�,`S�{L:|���b)��qV�LU�<ڬ�=�q$d�y6��@�Ƙ�(�S���,��M���k����Clb�9s��4�W�25Q�A�iXҭ��΀��}}!�،3t�:+�pF2�LJ�l�3]�i��Nô�X<���}N��(���U�fP��S^Oҍ��%����Ծ��ܧͺ;	�S��l(u˚5DN�{IG9������IJa?ؔi��f��t5�|���	�Ħ?U	��nZ���L�^��p�2�[ES��� B�S���/�A�S�>=���D���0q*1��I�Qq�&�[��nF�;P����Փ4)Y�5�o(�=��� ?j��s    ��4��9��}9M(�&�cB%C�$̢��YM�Ѧ�e���D��1-�B{�Coڐn\�_�|��6�X4��r1�|mpG�BQj�R�0��<:O���d�:��4ܠ9D.���f���,Ҟ��w�P�`�o4�D�iG���͞.5�h�
}w�b4@H���!	;Z,X�u��W��J��dhF��l�7Q�!�1�:�
i[vtP�aߚn�b�M��]����C�b�G4L�f�3v¶��[�":k@#�/a*P�v:�qK�P���%�[8�Ŧ�.���EG�ɿ ��07K�}yݥva}G.^���n�R�Xm��ӽ�C�a�)��>���f�6]St��9a궾W�W���\g\,Y.���U��۾�8C�cb�qb�Ma�Z�(�i��7Ì�͆,Օ��z��c�e��Ňv����n(�譝PS�RP:
t�7�Pl�ݰW��+R��]�HQI�>�f=q�^�]�Hf�����lͺ�
���z�R���o��]�`��ݙ����I6��y�6��]��P�/�nӲ�a�$��a���w�p�p6��j;�7�f �!���St�O���j6�=x����M�C`�	qH=�1C����҃NY=�.+�藊�}��єL�Ю,
�h��ۅAa|!�U`f�v��B�t�*����ƶ�o'��B�m�C� �'ɸ����sA�HF-�{��3H���`�d�}d�eI�+�虷���� �/̜��ݘ�$�!�/ b��xe/؂q4��9��i+0L�٬d�@b����!�/��9̮��ʎe�d��8%���t�(�/D����0'J-�,�����|a�����$�0u� �vg��'$��~!�/�]�8�=��{�q�����)t3�-l�f�{F��pn	۲��T%�ĉ���ps�f4�+k�l����ۄ�ab����lFL{�[H� �b��͒ǎ+�/D���0e�`~���af�`��x"�`ЩN3¹��o�m�|��n1�7�d����J�u���X��R�p��,_��o��FX2%}/40��PRI�}�=vWсH!���25����nwyP_�B��l����إeKei�Cڜ���!B/D�QV�M��E}�\��]_]MB����&�!�.�ۅ�K���x}O��O���\���o!�.��9L�"Ԃ��P��PzP��	Ckֽ��o�3\�k�J)d���u'�v�D��L�w��"ý�azk�Q�n�"�B�[ �k���t���o^�V"��%��_׮w�$v}�u��0?d�����en
�-L��yl:ՍW4e��,KRe��i���8��
s�?�X6ʆJ�.���vsA"iQ�/�H�_o)��n)���{����LAr�.4,Q(B6���4I��O�I��K7��?�Ϣ,zR�F��z3�x�MD��Q7��~Zt��AY5J�7"����+Ktk04P�W�g�S#-�'�ԅ9��u��c��2a4,��f�Tʨ]�*{r��lj�Z\�,����䟂f�K/��i'S�]��K�vN��N�ui�F��Ӣg�x�2�pw�'QV�<��2��?������u�c);�5K�v���M�+��P\��uZ���:-/��r�&Y/r[�.֌�l{���i�m�m�mN�yt
����Ԏ�U>�్��X��ύ�[�x���9����1#NcI�j׆��Z�k/�����]�Ɗ����N��/���aJ�
˴�	<��7�c�>��@B�G�YZ�:��R�6�rj�C_��D �s�g�[WkkX;*a�)$v`�����B11��[��Tg�Ҳn(��N!��8*�����~�)�0�%��ֽ`Z�g���>���(����ZLMh��
�G-5H��1
^
`P.e���٦M�V4=���?�[�]��N
.L���J�Uj�����	�|�)4I6UR)>�Q���-�T�:��/�2��TY�k�Il��=��ϡ�ߒ=?�ہzi.M�2��#���}�(3��:��p��0�Ӎ�=����=�J3>!TU�Zp픎�sl��|%SI%��g�3����e�0��,C�g��P�`�>I�0�����n��"aG²���Ԣ�Rf�v(��)?��0�4sc�AL��	�0E)��p��k�P�tsm�B���<��4t/CSc�Q�a���>,21Ϳ�B��iNG6�yl��8gb��t�m؀��3ã��U	^L��)z�2��QJ�|QTD���"*^�´(���!�L�)�V��D|���&�(-�)X��utr���،c���h6���mlJĢveD�oCk�ѹ[X;I��).�7��% ������8¹�x��1E<��q��Rn��0.��%Q��H�ń��ve��+�Paz0U����ؔ�d���xL�6�������P�*�=���s�����Sd��1-6Cy=w���<i:*�g8�n�!���1R�#&Z��s�И&}l6d}���I�瑲�dY<�,-�S�جd}���1E6®l˴W��_�±΄�H!�l(�����=��(�W�Q�����=0�B	���^ٳ4��}o����T�	X2D��qz!,�[)��٬���J�����}��I���{�'E4�X�f\>&Jf�n��ʡD��t��8�}_��ǘ�cL��i��4cc
fL�)2�;�	��}hb��E�M��t�H]�(gD,���f=�Ó�S�d��1KR&+Mz<O���Dj�F�|lF+0���9:���+t�Bb����,T���^��<KTc
�L��z�	��CR�4��P�k�޶���hLwCO��͆�Vw�Y�9���#1(�T�'��X��
���f�l*�vn���*������ӽ��.Xǧ?��SL6v��|�M�Of:I:����XX6��wv��*��3�?�f�L�)�2�r��1��<+"i�a��y���f�Hð��I��PN32���׼��Je=���jN��� "A��3�b����~��ċi�F,�!�J�B�|̘�qB�R�J��CӉM��"�č)�1E9&�D�e\����[�
f��4c�y$�Vi`ڙw0�)h1��Y��@S�c��:Ҁ)�B>�T��h&'��Mӛ-�&�z����|V�N}�ʔ�e��4�p> 1ݓ=,6˨��?黓����d�|��׶��*���#Q��h�EfzLQ�)(1�(�|�K��I��JG��������&|L�:�`��NV��O�CӅ`7o�%��@�4��e�8��������e�R�Mnj�	ko_O�IB9� "�n�z\����C9(>�6�|E���r�)�n�U��v7XO�8�;�S�paҡAi޷�{�S����%���(=��������P��a�\{)����V�iz�fS��Bh�4�R�2V�;'����J�	�D#�X�;�'��S�E�����}����&�L�����4 m�"�g�"K��~2M7���L�vO3L6�n>�IS�a(Yx��e��W��=Fe
�Lw|o6��nh�1�z�K4u=%�9�8�-�s����}��"j2�K"Y�ᐥ!��bb��0�t��:u�-_eP��$�-h������n�����K�i���e��Y�r*��?�^XȆ��9T��L7�G�BV�>���㠵X5v��\a[9OY�/�M�� 7fO�N�HML,��*R3I��SذiM��cR�41e�M{��;&K��"�5O�x^���oj�Z���fC��Wv`}[.?�-g:�I��
�;dS�� ��c����ͦ��Y���&�Q���=���2�����X���) 4������*E�����$�3E}�lC�|��Ai:qq �C�r���Q���e�2�t,{Q��9@�@&���1�۩�I%��R�N�O�8R�Ov5IM��:�֒`Jm�`�f��Py�6��r��i�nB��4�c
ބ%�*@K��Z'��`�c��o��C�9a��K��$�l*$���*�$��6�o|R+�9S�d�h6aDtia����S4%n�Zp��]��1�@6�z��N�L�曁8J,4�+5�ZPW$DS��W��;�G�'�aL�*6� -	YA�    dS�Q�o*q��Ah&��9)�whN-�n���8�qg���G��.�C���:�[;�i?��י�|"4�,㤝����m%M8_�9��"��y�eٜ�yČ�7z��L��͞�|&֝���w�uL��ְ�tKs��YB������!���a���`������!��ș�ZȦ&TxzQ.�E�^��_j�ô�T�U���y��lg�n���@���1-�?	��CR�ue�Ҍ��^�)Գ������#nB�l@���(PرL�|L��E�q��mg�:��M�	KYA5��Amlu3*n�#�b
�D�`?�J)4�z��d[�,)��37D8�˙�7��`��	��c������˼yӄ�)D3�m�\�g���Hק*�MY��#GZwT2:����S�f�����HiȺ����b=Ӵ�i�fڽӔ����ҳXZV���'�S���LŘ�`LS&6�t�?��EZ�R�5_����"��c�h�f����g�l���QMZ�C^���1�lAK����.��lX����\d��K����u�Q j�#���|����%��wq�t��r�k_W�.�����M��L�)N4Ɋ��%�$OLS#´hj6�EZd��ۊ;M�3b��+2phQ�u4ڌ;�.����iNE�CI?�R��|�d4��*/=�:վ>�/k�y���4�)�4�|�Y>e)�T�s@� ����y
E��)�����~��)�%�������/�w�rk�r3pؑ-X�d�Lhj�E�)E��B�0�ϴ�*��H��8��nZbD�ś�v���lSI6-�e .�9�t��^�����R7|�����0hlX����d<vTjXJ�ꢏX�G��(+[��_z�(/q�ͮ�S�`S%U(/y�q�O�ʥ��˫����g��>a5e�IS]J�O����߰�Q��� 4+��q�����HR��@%H*����O���Qul�c;�x��Q��X����I�ċ���f45��r� 
SJ���@�]�Z��}����q�h����%�c�}���`�;�����)�Ih�,_��R�RJ��A�d�q��Nt*P`�%��LY�'�E��2ac��܏��Ԣ�1��4"fX��rXW�ț�&�������9[�J��4�OU
C6�z!�}�fӸ0)��2]�E�Y�{ͧ*짹B���r��f��Dx"���Bi�hbVК�]�a�ϔ��l��$�Q:�Y"K�f��v�w	Ӭ�����n*>�ձw����Ri��T���t�c�/��N?��*��@^���Ƹ�zf�{�.;�gqu���,��,�/��o	&�,w�.��.�}�}������e��"�c	�����K]C6^�n�)��>)z�Y<e�~��k��l�"K�ΡW�P��T��>y�.�-bP�g�SSА�6�-h��	��@0��3<S�f���X�K�b�d�M�P�x�'�i-Ӫ�A���f�U���e7c�Eu��f�4Td�d���-Jo��<O{�Y˽�K�#�*Յ�����Ht]S��I]l.]��<�e�2�c�P9T��I}lO��%��̱XB���^]����}����_��h��%�qn��X�b�L���Q	'���a����,�%�MK�ES'j~=��{a�څ��vt(h_��377b�0�̽��`S�HA����K�a���wt8�w��2�b�+q�%԰*��K0b��t	Cl���4a����$�YK�"{e]�iD��l']�F,a�%��̩XB�d�İ��)��JL��*��V(�4+u��D�2aVF�4|E�P����p���L�X�a��oN+=��B�Z ��MD[0j����<���^Y�tKCKƋ?��^�����;D7�DWny�����L�X�C��Uҡ��*�cI�8�1ۋٻ`��X�1]�
�M�걣R�R��iI8�k5&����p��c,�L�X�1���e�l�2%CqS2U�c���x�c��U����Xt�kŝ� R,��e��bG��X��4��@6��?�و��|e;Z\�~�zL��pH_D#�e�4Rߴ�x]���K�b����Yf,2B6�g�nJ��a@�Ri+�;�SW2N�o
���Y��Laj�Q\��i���"n��&��#{�-�����#��$cP:��=�D>6#"���r��2�$L����DT��B������K+̫�f7[�P���l�i"�L��N�e��2�d	�,��n���QJ�e�Cv	�,��0�h�����.��h�2��Xib��G`�I J%��Bi"���e��MY�,wϮt�،Jn���c��	Ӥ��)��4U�ދ�{����p�2�#�+��`���}����t\�X@K�"C��R峷���Og�)�옰K��~)����|G+�%�Y��*T�cEVJ[�	[�JC�Ңh���I�����k�|�o?��PUi�����'���[�쑄Ю����2�c	�l6��i��4Q�ק��76��ń�0�S)��a}�a��}癴i��]؂>g}���|�(��.s6���rlL�}�α�1�"�4���rU$ʩ���;b_.g!"˭�K�c�U,�0��S7.��3��Í��f��:��x�-Lٌ�p]D�b	�,�H6{�ǳz�tj���~�"���SK�9$�B��CZf�|!���o�nX�ܐ͸(�v��h�*e�v�ql"���Z�;�!�1���[���r�!��2�������{�=5�s[v_��.��n���YS�2�8��*�Iɲ�/�G�P-��З�=�auv�\,1�d�elVJ�ؑ�4Q��/�/��N�R���,��[[o��F����
�MK�ʦi���O.漤H�|��7<Ji�i�������F���a�b(Qj�:�倳��uLZ�n����l���ʡr�7��s�mf9Иz�Y���/*&�,��%4��g���j-����(��ľ�8lFk�kn�f�2���Dxc�Z,��.��XHKֵ��f�(ˤ��RiM�:i�N�Ҏ�u�"ֱ�Ab�e��3Y���r{m��`Ci�Zԣ��,�����Dؕ-X�7�Y���r�n�J}����E�ف��Oj3#ؾ�fl�Wk��i�t)3nX��o����|�%��H\��0U�of�,s_6ӌS�T&ˍ�)S��>u��Rl���-QJâ�&L�%�3�v""��H�9*�]h��˾]O��:�S1�%���b�JT/|;��EX6���8���	��*���b��I���ö���!`S���C�d	�D�>��'l��Yf�� /�V�%�~���8=�]�K�d	�,��>�R���~`v�f���]�ؾql�X�f�����>�7�q;���oQ��f�%x呅C��Xi�w�j�]�|�H�����?�Hg��UA��ɺ��v��C���PbaYy���������n��"�q�p5��3cb�2RzSY>Ɉ�����E؂��\e8�"�I�94��AS b�ǲ��?�
zl6-
˦n���(BQ�����J�o!�E��n6,�+�/���,��,����Tr������V80����]��MM�Md��)����]������.�����^�������yo�Z>�,z�n/p�����F�6g� ��^���2Kf	��mYi��ci8������xq�����s���w�.ѕ%��̘Y�7����-�d�%B�ݐ������HƠ�]c��T
�,��%r�L�Y�"K %���S����Uҭ��-����˼���n]�C��c���h٬�Mq��~�*��*��DO�PL���ԗ��u�����a�ہXJ�Ҵ(�/-8M�ny]b.�=���r�6�1u3�SF��Jy���u3��Lh[���u��,��MSze�6tjݝT�d���DY6㒹x ����"��&��\��v��_|e�,�q��X#ɦ�x쪔q4a��	���8)��w[-{�up]QiY@7х�T��s3d����� 49LO�w����l��^p}W F���",Ҟ�1�ݭKLd�ڳ��ٌ;�5s�x�2?h�������
�(ڕ!�θ~L���K    �%RXV���h�2��R�A����nҰhx4�R������bs��~�K0f��d��~��s	�\nx�ȼ��db��.,*%��~��lFM����b��\dY�L�/���=���+m��|�sK-��"��V9i��)��a��6��4�����n�Q�O�,��6(�cy3��l�eՖ78*$YB��4 �H�xeK�2�m��'�����m�M�������yN�\�z�-���-�'pt?��x��^&��räӐ���?##���"\o��9]�x�?��fSA�g����0�2c���h�,J��eӲ���4�%���W�Jx��B�P���0��
�I��r	���Pz��C�>_�O.�N6MuC���&����B%�c5`��r��D����v�a��P.1����{������� `K-�&{Gks{�H�f�ذ�dZ���ԃ��0���5xI_�#�i.��m/��^�č��8 h�%�r�7R�ؓ�e��86�M�cy閍�<�eH�<8�eZ�e:MX[l-��=��=ʓ}��6-{��Ӌ�l��! g�R�x�URY�LeW�(ЅMV�Y.w�^ /��e��*5�:�u<��-x��P�{����t��#�����XҘK(�����z�_+|�U��x=�kwb���j�T�w��~�K�T�g/����T�q t.w�^�i.��^�@�X�֓þ#[�q�/�	.�;h/a�K���w"Y�����ɠ��
:4(c�K��f�l6e�qH�'J�Ɠ~���
�@��'\��^���Xe$j�F)R�^�E\�A���˦���3��ͤӢ�(e��[�BrU�Vw�#Xt�]����.w��L��E��Ut�e�6-og3�@�h�e�����ԕ������-�P�Խ�M���1��1a��;a�A=筳1�Ȳ!��r{�Q7�J�;2��2�e���xA��K�g�����x�F��\�Kd)J-	vl(gJ�ز��?/��v�`\N���\�H�iǽ�3���������3�͖�����l:X1ٚ�c1��D�6�VH�����H�dɦ~����K�_ ���].^.3W6��&�t	C�DeO1�o$K���_O��m��	�;ocj,�/��v�VLN�)!Ɔ��#%�+n��2��8�&��o%K��H�V�BDʅ��D�L�T��N��B]�u��d�Y��Э��JcXN��>�<2���7���S�B9�0U@���瑽����17�����f�����pAW(�K0�%��{I�]@7(R�w��w�P}���40i�r#�?��l=�@�^;Q�w/	�SU�_}a^��H]F�M��Tߦr��	J�˅��\X��E�\�=4�T7(��g�')7܋[��KH���2:���%t����!pV�� t5�ztG�ౄ�\��a�t ��B�\!\F��1.f���@�|�?.�石�Kg�4`�9�n�`�As��p8�&g�ͅ�F�[�ȯ���]=h ���q7�|u|����ns85�V�d�+|��eJ5�P���f_H�x*�i2��ΟA"Mu���7��gl��1�Q�p.+c���nI�a��P*��-�rE�\(�Q2Ɉ�>�'�xp�Qe�M�D�\h�Q�rI;�5��Y0@��Q�r	Q�ĭ\aH.�ɂ��ˍs�D�\����J�� Wa)�X��,&�p�O�sB�N�Y(WB<�� ����\�IԬ���{U��>*eZ_�(k��(VY�����?t�v�By\BG�J�K���P��8)��e�^B=.Tǅո�"W�?�UʫB}�Jɳ״�x\rWG��W"C.��,�[r%av��R�?�)��2��Z�wx?�@.ӏ<��§\��K�Ǖ��U�Z0sw(M'2��k@�m�/�h�KL�s,��] �q�N6e��ٵ~$�^�Z/\ǅ�XП�.c���Ԣ�K;J���ZU�?�A��Wq�����R��%Qv���m�r��t��&j��\_�sC�X/�c1H=�ʭ�a�嚬��%B����s��XP��Y�Rݠ��iqIg�L�R���e'�c,T�����HFz�� �bA=�˯�򣌧X�c�1�KFk\x���b?5���q���?B�|�j/4���I!WH���ব�`�戞�a\���p	¸d�^�Q*�/��A�)�k�W��X��dGX�$!�bA��Ă<ϓ�5�"?��O|�0Fu�5�2.AW�T��qa@.���Z���O����g9�Ņ��p�R�Yp��`8���S�׋�v:����#M�(��|<��}�1I���7����,lB4.�ǂ�W����΄�;dYo��Ulq	�X�+�ޏ��D`</���� Z�BR,��m{y�������q���`��5d�Ăx�ēʻ��t��*~m������$�q��f�gB�jrR�$s��~���J�S��T��U�g�����)2J�;��YM�v��v	�#muLTe.�$�^R{/Tɵm(�+��Be\H�^��ŀr�X�������o/��]��(Y|L&����� g\ǂ��h�עI.��(���X�W��.w�a{;�'
�BO\x�uq%Zd����,�f�k@��;�帰�廝��1��]m�ۢ����(�\BJ�J��?o���DKF M���x;�1�t�2s>
��4��Q�~�����ahK�szl$��2��Y�A|	Yp5V5_�xB��Ъ�ȅ3Y�n�)UɅH���`�0���e�D?�4��u�C�	_���r�#����@=��V.�#~|�A�$�ȗ��Kx� �t�A�-��e�c	�B�\ao�ĥ�Uc�/'�D�\���HY������O���L	��9���k������\Dv4�̅Y��&[�q"rr!Q.|�XRg2)5��2p,�h��x�@��+L��/�Ȃtn��{<0ا<)����yb�u2�=A������~���㣃t��2u�S��y���d^PvN�D�r��걝`X.,cI�=˥��W�~�1�ς�����,�@y��E��\�V.�*c���k~���u\{ĵq`UF�)g����ԑ��� ��_u' cpb@�\2����<&�����E�\8��^����Y2it��?W�F�e/�zAL�9��{�u�A���3��s�X0�jp8�e�u���`�O�xu�@'M�>�!�گD�,P��x�u�I��,w��|˔a���ҿs_�f��umZx�Kt]�jg�_�+UQ�ل6���\��,�x��g朼¿\BhR�l���s	���B�t�Kx�%�g<�@��v���A/43`�E�� d����H�S�<l���&]�w��_R�/��T=�+s>��}��xJ���Ю���t�_������Y�S��DlD΍ֹ��}�n�9�Ϭ\|��l)ڣl������dj�����������l�>Z9�Gҍѹ@��[�����j��%���E׌�@�!爍�%g/)�u��Ă!���O�n�in�7"��}�in��-�F�L�2�2M��Gx5�@.i��c����Y|쏪R.��z�e�����h�^US�t��`�A���jp�ۻ�q�r�9:�n����6���v��?�T6@������x:���?z�����q#�����c�E�t�1�=>�����n��>��w�;��2Sv܊c)�rr�of��������u^��5�kl9���W�5ӎN������Ij�ɬ^�#�� 5�����a�E{��ը����R�a>�F:�IY���g|v�����Fk�h�[����{.?0����.�<�T�%uc�|ꆺ��GG8)�K}��bt��q�Y�0���(�[��-�gL���u2؈��t#�n��-���sĹ���iVlkF�30Yf ��K�n�4�ӭ���t6�j@h@է���z�?�jA�� �����r�d𬙵�Yf?o@;!>7j�NyR�䚳��ڹ�QSE`��d��w�wbw�ߒ����/pS~\U�4��G⑧�N���Ơv��w"xn)�w��[�@l���J�U-Wy�S�/�W��Ll�7��    �;�5�l�j��|���I���������n�����0���|����̷Ԃ��͖Չ:Y0�e��G֍\�_���
�N���7��߲�o�Ȃ��k<('���Z�W��b�.7��F�ܲ��R���{ʒ��P.��a�2R5(o���,�ВS3R�F9h��Y0HU���B�,�]�Hq&#1i��\�uڜ$�(����ҏt˯s��b��Y�g���d�;9����[���T�qK�17d����~�Q2]9�o��-Rf`�t���pK�]𽺭n���%ɶ7���r��YP�%y�F%��RN�� �)��0E��ȊY1&�Q^���y����t,e�bq����_������W��O1`�>=�=��=y,�]�5�1��8�Ky%�?i��<_��Y-�Vn�*��n �������b��m�����H|7���*χSs(�Qp�H���ܲ`oÍn��.��n�w{J,����j�'h5���Y������lמՇg�+g���s%����BFFٙ�2a�C�'R�V��2�5,��daW�˱[��	�;�1�C!�!�S����_ùf������:/�[$�-qv$.�<b%Z���.^��-��r�P`��)�k���5��>��%��2qo<ƝP�;�ƨ~e�w���a�y�~��i� ��ƃ�#���+��[�ȂO���~�h �bw��{�7nq+�����D��D3[���P�@{��v����T�e��Ir
�3��H�ɍ���PW+PF�Z�dj�AӗF�r��gF��m�čָ%�.��e��c?Y�����dn�n�0n4�-�䖫{˶U)?B#iԸ�@�v�a�s�Hnq*S�z���2��=�N#G����� Y����i��8`�E�L�̠P�� ˌ�N5d�޲��.���[8�-��F��Rwo�,���{�'l@�T��-�d��g�½�ɼ�1#�^��-(�l2��M��*�����q˖ ��e ld$�9�T�Lv?5<�ז�����o��[.�*e���D&�-K�F��3�uZг��+�t��9f�� 7d�^3��T��i:NҽV���}�v��-���L8�Lg�����(��2�`����12y�r1���S��{���]�rc`n��r��r�hna(���sO�Qn���@:�N0��m�UL��p�0�[h�-�v e'�iY�cn	���Q���P;*f�H�B����E�LV/��+{chn�'w2gGh@U׺QV��I�6'a(�8�Q��|���v4Ά�O��r�ZT��Źŉܒao1'�$��O�r�g<��L#+Y ɂNb76��brմ�>�|�8��%���.{^�q1���wr��XKg��y�G���,�8�۱���e��H�Q���Ad�(��j`݇mY�J9���^]c�5+/>J��QŎym���A��b� r�7��,;���:)?5�.����A��-�v���קn�̀}%�r�.�����In�%wx�[��-�uA����R\o�� )�r6���x�s�pd��cḱ|UH��*5��>f���!���`�>0@S���~��)V^��rP�37�eA�`�ftRz�N#��]� ϕ?:,�ϲ� a�A)�iϲ`�L׾D`[�Rf������,/(�i^l�9���qB u12ho͂�O�'w8���+W;��-��^(vN�Kna"j�Ƀ�wr˗�Ë��&x[���Y2��r�z����m�#Tn��-��u;A��a-��������Rko�ȂMX�z@ͧ�TUw��*#�@�l:�Ob���)W^.�՚���-��T��vٶ�mu�6�š��v�V���so_Jd���))+��L h���L��l[�g�5N���[�-�%s�w4���u{.X�����p�󣰓[��|��2]\yi�s�`�q$�бa[�H�Q�@�a��2�5S3�A���z��(��:ȟ+p/>0��Q~��ן�ܑN�L��܂K0l��h�t��4:֍��b�T�2i��p�[�ȍ ���ܘ�;y�cx�b��ؿ�A��P׸�H�?V\�5��q���;ʔ���r�mrK�����R��k׶3��z�9y�rcMn�ȍ<����aOn	�2�^rD�ܘ�Q>�`��j��������_nɽ�ؘ����A������G�-x,�Oj�Es�z���!��#|cr>�N��}'\�-|�6|���<~��,j >>��`�26�,͂LI���ǹq<�0�i>N���ձ�l9��`9�u�T��&{���,��d�
#tcnn�2h5x�HO`�^'`P�焅.3v�07�L���f�f���4��PP<7"f�I�ۗ/&��ѱ�f���:��w�6#��,j1aj�p9c�|5�lP7���-����3k��D<΍�(ɻ�d��q�Rn�ɍ��1*��[����$5R=�7���N���s#en��}�#��O���R�Ï;��2#��{͵[��&�_5�/�[z���[��-�x�k�[a��9�)���}�"����3��Ru�8r�2���(���N#��S�8���aF��-j�'s#Pn9�Tݔ�V���++e����S��:�q�_^@�d}��b�MY�7dA�T��(�CJ��?�瑫�����)��F��Bfnȍn���e��=����$s�F���5Sàz�ތ4[��q��#�����l�?���jt��T�����}��#��l�`Y<�4�l�����#��r�F4�;ˏ�]� =���̌�AI͐���y'������}�R*[��ɼ?O���=���y�^:�݄uן��U��y�����yp6� ��r��L����3�w�=�|��~��fN2��Γ(�)����5~�=��I4��S�r��(z�A�,Ȩ�;:n���y��sk�H�П�`N�1��2��elK����c����-���,���,:)��5۹��Qٹ��|�[!c�P>���>U�Zu�x��m������� oc;��x��N����8��qÏ��>����Ǣi6��(�ƹ��n�'T������'�rqM���^��?�ϗ���#wz���tu)ǃ���JCN�E =���˂z`��]�� �$tU�f������y�&~eAM��t�\H��)��Šj���Y�I�!�[��2��5� �N�� ���V�����xIΏ��t]= ɮ^e��cWk�Z)�ò�L`b8݄���`T)��IJ�ۙ�t�9Qr�)��A����e�����.�8�Dؕ�2�WCj���ƍ�Ǥ6|�Fc�WZ���ms�����r�)X�`?H�m�f{6�'|��l<"��� ���U�nD��Ho�Ĝt�Yt���r_�� ����G���ͫ�1���D���]�tM�����^ù���S6�G���k����l�������H5��!\h������.'M�R��3-�:�AF� �1����ӕ����x�t��#���@=X��c^�[�b��ݏ �	4�_�SW�ޜp��H�i���!��%W�u��1τd�
��$���-�j���]H��#g$͞Ec��}���&�+�6�T7�՞#=|LX\�����)_'�8[^��yԌ�4��K�ͪ��N��G����G����9z*0�mkN6m���+X�G���ѱ�(�Y��x�A���Hs4�7=�}f�{@�S֊l��C=�>�d���Gk'�vܓ�W����Gt���
Bp��Ϭr)}s������(�?��	����~�����A�$gA݌nW�$=_W��G<�4P74���$[�Ab="bQ5���^=X�Q�����z0$M��/��y�, c�b%���-8,تP7�nLګ�̜t.�һ1wd�w�3Ҹd����f��I�̃{$|?	řV���k��2]�E��܆|19"ft�$e��!�� �2dg,Ju��e[?��RN�&�t�t�M��sy�#��ȗ̃��/��������9�Ӎ�z0S�4�Q*/��>;(�c<y��d=Ҵ    �փ7{�<���\��,_�l��k(~�Q);�L�uq�m��QZ���G���ZeM�$�Z�j�1Ucnp����Ǎ�VGr�)Џ��G6�#�zA=e�j�NI������5u�A��K�8�� c��=�"��A9���3̄��1:_�=F�f',�#�y��*�S��t�~ry#�~���,ȽʫD�G�ح�I�>�͙F��c�1!4}5��7��MbiAS���-cxSI��X�I�|�I�c.>.2����+�$�Rv��"S���w�"�2�^`�<8�'�9~kt�ڋ�X��� ����h$u�/�P�~�%C/8)s��0l,�[ưԇ�#���A5�����*jC�r���y��3r͈7��P�;�T)k3��`�Lи�)����i���3?��'�<c�U'մ��y�EO��U�E��z�L�h��Ј].RN� �@?ȧ�˛�i��UK��jO���֜Kp~��<�q<cRN�Z��Tу8Zϝ�|���fz��<IT~�0?�s����3!e{5[�u/�,��t7e�آG�͓��GB�T5�O����Ӻ��w��\�/�:�`>u�"e��g�iuM_ I�����Ghk�L�4u���#�2i~�%/H����S�0��Z���v�vƠ��b�.�ӥ?	���/��*�̑%*@�A=bxFyH�w���\?����N�@��#<g�R3��4�nPv��`���H�tH�4�
ԕ�FY&E�8�'9�zhAZy���<	�y�B�S54��C-�=�\)���-2a3����(�y�?�sD�#���3��j�̄��P�I���ɤN��X��|�j�x�<^�������<;择y�?�G�̓���v,�3IY}�_^���۵d�s'���/��q���y�@���W��|Ia��~D�<an�2S��k=Օ����G�H������r�O��#1xtK�2|�����<Bc�P<O�3��>f��c�<�Y����3.���~��7�d�'UxA�~�%ގ��B�3�g��3�D7���{.TM#���څ�<�p�')O��o���|����#=����j2y���/�����`��c�r�MR��O	����S�叿�+e~�Q@ra��)��b<�gr���kMo�x�c葘<�#�� 3栿���-ng�bc~���n�y�8S7I,3/�R�Q4O��U�C��� �k���<;_Ҷ/o�h��#����<�w�� ���S~J�:�A�Mg�����-�/b~a>#Gm-۟�B�2��+^hAmjR��ЂM�8W�ӂ������/6�I��*[�A`�)z;�D"d�7�0�'�*k��g11����o�ȏL���S8��;A�˓��G����Y�wv|d$��(��9�R��UNr�-B��>~߅�<�R�+�.����ʄ���P�//�	��`S�N�IzU�頿~��c���Co�z���<�.���  `O�0�5��#^�A�����k�J��;/�8��w/�Ǉ |����Ԭ�3���f�P~�e?r��.�d`vJ�\�-��HA~��<U���a7���K�&'���-�V�����b�͋�%��_i�oX�G6� Y���ǐ�N�̷#��>\����a?~�e/���,/�x��`ٟ�Tҽ�z�����z�O:��1]�z��.��bYP3 y��V�5��Ӡ��N���6���?bT|�*e��҆� �i��tu���X��J�~p�N��)�<I3~�?�����f,���;��6�`�֚MNjW����;bȘU���1��t�J�j��/�蘷'U׾\�,Ƀ%Y�S���0դK	���\[��'�����&Ї!*����@���I�� 5T+]X��d�q�s�uf@���t���j�_;����4�_��;k]Pʂ��&�ky�<x���+m��3�`�:����rp}��(E�g�k����	e����3��x$?8��<?~�/���'�#����<W�z����[���S�M4ɋ$y�#/"d�tu�s���Z�U�]wA��z���@u��������蠱�%i�Q�n�ŖL�%=�j�]E��\uͻ��I/�z�c2H���/f�E���_L�n�E����Ooe��{Wʋox�o���)�:�68�K���Ņ��i:s�/�t�ƫ�<��΢fbe���~q)c��s��(-���1(	�`�"��<��l3�]���̵o��C�9���v'�{�O��b�%a�ך� Wx�v�:Fw�v\��G�:��!���rt��k֯�^�t=�ו]�Y?��})'�n���WxƋx��kH�Ƌ�W��&_��#]Иtu,�?��L �A^����R\�5���ն�u�Y�a�U95ш��"	4�uv譾�_��jv�W, |��I����Of�ȕZb-^Y�o�x_y�/�_,�@�"��C��.��7���]Q4�����H��d����}%z�DT��z�/U��}F�%�m�i�|E���y_�+<b�j�ć�Rv_i�/�]$�š�\Z��wܼ,�︾c�7�k�7{��x���M7��#}��9����j^�����E.���g�[���W"혧��"���/���]��/bݕ����}��.b�<��]a��5V����A�[��3�� ��|�r^n���jjP�
�-����$;JB犤�ɣ��.��LF����T��u�g2L�+�bt� 5v�p�|���0FY����[Pe�2^ܸTu����F�&`2�|�ni�M�4?�m��i����*��]츋R�:���祿�W�Ƃt�<��u��Nn�_�c��b�T5�����m���gX��1y�3�u*�uA�q���1u�1����=��L�WZ�+��m�^��v��ߌ�ZB� ��+�Ew��1y���Ve]#
�2Rʺƞ��w���Q2������`2������y��Ho!��6]О�Uw�QN�^��^�*#�#ߝ�{��ERWO-B\�褺;�F���\w�T���v��
A��q'eb�Ku`��φ�{�p�9&�z��(p;�
YP��S�����Wt�j]��h��T��`��tRZY�"P^��H�	e����S��2�4͠�F��~�Хyh_��1@Dʂz�q�e����q�$2'!�\����vgB7_v�������x�8��=�\�1��7��#|s1�R��(����(7sʏ��j���T}TO��g��ר��3E�w�c}q�bI�����5�b��O�RM=0#�Q��+6�V��ǫ�A��E���+�W���,���.r�G�*�.k�.�4�������_q#J5M�����W�����&��M��+%uj�5�k������`��z���}�.0�J���w�� �y=2u��E,��{��;Z`��z�ur9s����$2�Uu�5"������
�e���4�`�?�~Zi����Zl�����~T�'�G���=\��+vc����U-��4��Ρ e���ݐ�"���2 ۀ��Pwyb1��T���� �3/��՛���!��{;u�Ïa����:3��ڂ:2���J�]Pwwzş^Q2:�`�V���g�/�����B�KMU9i�|M/�H��eڊ�޾Q^(?Ju�yQWS1s!��=gꦺ�]�Ɛ7��ݮ��ە��W|ʀ��f�!�3�>ED�,�$x=��;��像�\��sE��BC��Si����3�3�	yE�,���}��x�]U,����QeĎs���C(�#e'4U�2Sm���-t���`�`��xk��@s��?ef��F��+)xA�e���r��q�=	� O]�1��+�dW�c�����`�Z������H���c��L����W��.@���զ�O��fb���r|]�uE�." ^�(���Q�l,U�^�43�gFHgv1�-㐋�򯏕�"ˇ�eO�L<U#�R�L!$�A��"dy�^��H�4u9+���N C�G��}�ɃO3��,[�b�1��u�k,�?,'#�� �����lM�Mƞ�f 3aWY�+I"~e��_��c �  ������h`^���X��e#@K�	���c�Z_�P�)�ם��L�L��Z\�+�����'Y�H�ɧ� 5&�e�������c��	)���dl�+�d�_�vċ�x��bYF	>&�u{R#&�<��8�W�ɂ�27�ְ�s����i�$��R��t����q1f�	����~��G���<3�J��Uf6,p)�r=���Ey�����$�S{��F ˋߘ��	`�7�uq��S�
ay�@����=�"6�ľ�c�~�}���<0(��ulǣ��Jˋ�X�)k��3��#<��2���Px���Yy�üh���z��U���_��ބ���>�xU�j��Q��UF�L���%��q�&?x�`04U���Ж/�b��07�י�Mj;|l'��S�����P�,��w�m��@^�UU3P��c��̤/4�Fh˂��s�z#��2����%~e/�=���,X��~ۓ�Q^���t���bYp��~�.P5 �<����Wy�$���w��x�W˂�
�g�c��U^*��T5�P55�]W��y��׳�t"�wA��F���l_a�O�����
�ye��W�gy�׈AyE�����=�������M��1���tz=��h��qY��Ռ�"K^a$�ڳ \v��K�����ۉ��W��x*�| 9�n)9�!��h��1�I	���?���}Q/e�սN���C�q�W�rx_|�ԩꁧ-%�Zo=���_�\��X���=E��H_�� �P��5i���W��+�Eg�D�I7~��d<K?:�yd��TV�l/�w� ���W��+��En�I,�T���A���	��)�
y"��YBǂ��ˉ��y<|�=�QN���-��cl�<^T�+�)��̵���E}�Rx<��F��~�q����ϲ�8����Kg4ċ�x�u�����?�ׂ�/6��S���wQ�뤔�	�
E(�/�E���G9R6��U=C���,w��B7�n���}��������^T�+���1��)�u����'�b�x���J+.t�*3��" ]�+�/j�E	,8���{�Ƣ�:�*}���	����MЁ������sYkJ�eN��0���$�ߕ��",�6*��H��vjԞݏ�I�����. FZ�T7�~��é.Ws�q�i6^������=���nsp�������S�?�s�7���:�����������j      ,   >   x�3���,H-�2��I���2�KMO-IL�I�2���OI-J,I�2�t�K-Jd&s��qqq �U       -   z  x�mTɮ1<7��Oޗ[N�"�	�1p�hlB���&R ��8LUuw�bq��0W�K�N5� �r�,&�{ַ]�����M> [	�_�D��TwD-�r��6���9S�Y],	u1�9�%�\�B%q�Pr��1�wl�<\p�'��\�":��3�I54�cH�X��I�0�ݑNc����q���K�ڢ�J=���¯��AWdO3Z�۷��Z��D~�8o"(�T�U�����8�M
²��?>?Ô��b��)OV���.�s�%������4�)*^���p���$F;J/8*��#�\C��׺�^N��v��F��F��nk�n�b��pT/*�M���
j��4��Ll�YT�
�L>��V����ؚ%z�Q2+��R	)�h��XZY�֑�^j4R�WB��9�i�T1l�?d�p�B�=
2����q7�9]�2�ܺv��J� �P/��\��Yq3��0� ��K^�)�[,����a��:��<�+UH�M�\R2���*%����<�MO+�ł���y�{�]�8�':�|*dTx���Fr�^	}�4o�c�)�g��Kw䜿�FC�A�=_�P��t�kў8��z���E���Z��)��~      /      x�3��M�I�2�tK�1b���� :��      0   9   x�3��̫�2��M���2��MM�,��2��(���K�/-�2��I,JO����� _��      1   #  x�]��R�0��ڧ����k��P
L��6L���M������Ҽ}���@o����$eK���}�c��-]�e	�f��0DNINLR�8�Q�&!�A�Z|D�G�YK>ireݑ�8��.�h>���-�0w�مls� Ws\;x��dT��k��-��8�{*�7u���
`\��Nll̒2��>��(_����;�Ҟ٨�qNbQ��Z�M�	�X��i���'�J�W���;-䑣�8�`��0E^L1ò�+������NU�ߪm69���J��.S�����@Kظ�=OjD9�<_�_�$�k��U�������po'Ӈ�*�߇'��h�����V��aU��bU�}І�*P>H�F��V�1����N�AC`�]��H�2M�k}��3���n�����_��&�W��x��W� ��_@��p� �d=��j0�G=��ІN�^�� ��XSg�u�8�,�e��\�y��S�']�����(O��[�fM�fs�I���@��N�ӳ��8v�X�:³=����������V`      3   )   x�34�4�4�24�4�Q� ʈ�BC(#eT���� ��      4      x������ � �      5      x������ � �      6      x������ � �      7      x�+,�,,���44�4������ 0%�      9      x�34�4������ 
~�     