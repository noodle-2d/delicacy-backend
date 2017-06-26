<?xml version="1.0" encoding="UTF-8"?>
<!--
CAUTION: Do not modify this file unless you know what you are doing.
         Unexpected results may occur if the code is changed deliberately.
-->
<dbmodel>
<schema name="public" protected="true" fill-color="#e1e1e1" sql-disabled="true">
</schema>

<database name="new_database">
</database>

<table name="books">
	<schema name="public"/>
	<position x="181" y="196"/>
	<column name="product_id">
		<type name="bigserial"/>
		<comment><![CDATA[ID Книги]]></comment>
	</column>
	<column name="type" not-null="true">
		<type name="varchar" length="1000"/>
		<comment><![CDATA[Категория книги]]></comment>
	</column>
	<column name="publisher">
		<type name="bigserial"/>
		<comment><![CDATA[Издатель]]></comment>
	</column>
	<column name="publisher_local">
		<type name="bigserial"/>
		<comment><![CDATA[Издатель в России]]></comment>
	</column>
	<column name="ISBN">
		<type name="varchar" length="1000"/>
		<comment><![CDATA[ISBN-кодировка книги]]></comment>
	</column>
	<column name="synopsis_text">
		<type name="text"/>
		<comment><![CDATA[Описание]]></comment>
	</column>
	<column name="format">
		<type name="varchar" length="1000"/>
		<comment><![CDATA[формат]]></comment>
	</column>
	<column name="pages_count">
		<type name="bigint"/>
		<comment><![CDATA[Количество страниц]]></comment>
	</column>
	<constraint name="pk_id_Book" type="pk-constr" factor="10" table="public.books">
		<columns names="product_id" ref-type="src-columns"/>
		<comment><![CDATA[Id_Book]]></comment>
	</constraint>
	<constraint name="un_ISBN" type="uq-constr" factor="10" table="public.books">
		<columns names="ISBN" ref-type="src-columns"/>
	</constraint>
</table>

<table name="Orders">
	<schema name="public"/>
	<position x="1052" y="76"/>
	<column name="order_id">
		<type name="bigserial"/>
	</column>
	<column name="user_id">
		<type name="bigserial"/>
	</column>
	<column name="quantity" not-null="true">
		<type name="bigint"/>
	</column>
	<column name="date_of_creation" not-null="true">
		<type name="date"/>
	</column>
	<column name="status" not-null="true">
		<type name="varchar" length="1000"/>
	</column>
	<column name="note">
		<type name="text"/>
	</column>
	<constraint name="pk_order_id" type="pk-constr" factor="10" table="public.&quot;Orders&quot;">
		<columns names="order_id" ref-type="src-columns"/>
	</constraint>
</table>

<table name="Users">
	<schema name="public"/>
	<position x="793" y="23"/>
	<column name="user_id">
		<type name="bigserial"/>
	</column>
	<column name="login">
		<type name="varchar" length="1000"/>
	</column>
	<column name="password_hash">
		<type name="varchar" length="1000"/>
	</column>
	<column name="name">
		<type name="varchar" length="1000"/>
	</column>
	<column name="surname">
		<type name="varchar" length="1000"/>
	</column>
	<column name="phone_number">
		<type name="varchar" length="1000"/>
	</column>
	<column name="ip_address" not-null="true">
		<type name="varchar" length="1000"/>
	</column>
	<column name="session_key" not-null="true">
		<type name="varchar" length="1000"/>
	</column>
	<column name="last_visited_date" not-null="true">
		<type name="date"/>
	</column>
	<column name="role" not-null="true">
		<type name="varchar" length="1000"/>
	</column>
	<column name="registration_date">
		<type name="date"/>
	</column>
	<constraint name="pk_user_id" type="pk-constr" factor="10" table="public.&quot;Users&quot;">
		<columns names="user_id" ref-type="src-columns"/>
	</constraint>
</table>

<table name="Order_Items">
	<schema name="public"/>
	<position x="919" y="294"/>
	<column name="order_item_id">
		<type name="bigserial"/>
	</column>
	<column name="order_id">
		<type name="bigserial"/>
	</column>
	<column name="product_id">
		<type name="bigserial"/>
	</column>
	<column name="amount">
		<type name="bigint"/>
	</column>
	<constraint name="pk_order_item_id" type="pk-constr" factor="10" table="public.&quot;Order_Items&quot;">
		<columns names="order_item_id" ref-type="src-columns"/>
	</constraint>
</table>

<table name="attributes">
	<schema name="public"/>
	<position x="458" y="242"/>
	<column name="product_id">
		<type name="bigserial"/>
		<comment><![CDATA[ID Книги]]></comment>
	</column>
	<column name="type" not-null="true">
		<type name="varchar" length="1000"/>
	</column>
	<column name="height">
		<type name="bigint"/>
	</column>
	<column name="manufacturer">
		<type name="bigint"/>
	</column>
	<column name="series">
		<type name="bigint" length="1000"/>
	</column>
	<column name="material">
		<type name="varchar" length="1000"/>
	</column>
	<column name="description">
		<type name="text"/>
	</column>
	<constraint name="pk_id_attributes" type="pk-constr" factor="10" table="public.attributes">
		<columns names="product_id" ref-type="src-columns"/>
		<comment><![CDATA[Id_Book]]></comment>
	</constraint>
</table>

<table name="products">
	<schema name="public"/>
	<position x="303" y="8"/>
	<column name="product_id">
		<type name="bigserial"/>
		<comment><![CDATA[ID Книги]]></comment>
	</column>
	<column name="title" not-null="true">
		<type name="varchar"/>
		<comment><![CDATA[Название книги]]></comment>
	</column>
	<column name="price">
		<type name="money"/>
	</column>
	<column name="remainder">
		<type name="bigint"/>
	</column>
	<constraint name="pk_product_id_products" type="pk-constr" factor="10" table="public.products">
		<columns names="product_id" ref-type="src-columns"/>
		<comment><![CDATA[Id_Book]]></comment>
	</constraint>
	<constraint name="un_title" type="uq-constr" factor="10" table="public.products">
		<columns names="title" ref-type="src-columns"/>
	</constraint>
</table>

<table name="subjects">
	<schema name="public"/>
	<position x="190" y="540"/>
	<column name="subject_id">
		<type name="bigserial"/>
	</column>
	<column name="name" not-null="true">
		<type name="varchar" length="1000"/>
	</column>
	<column name="description">
		<type name="text"/>
	</column>
	<constraint name="pk_subject_id" type="pk-constr" factor="10" table="public.subjects">
		<columns names="subject_id" ref-type="src-columns"/>
	</constraint>
</table>

<table name="products_authors">
	<schema name="public"/>
	<position x="36" y="416"/>
	<column name="product_id">
		<type name="bigserial"/>
	</column>
	<column name="subject_id">
		<type name="bigserial"/>
	</column>
</table>

<table name="products_artists">
	<schema name="public"/>
	<position x="381" y="414"/>
	<column name="product_id">
		<type name="bigserial"/>
	</column>
	<column name="subject_id">
		<type name="bigserial"/>
	</column>
</table>

<table name="products_tags">
	<schema name="public"/>
	<position x="54" y="10"/>
	<column name="product_id">
		<type name="bigserial"/>
	</column>
	<column name="tag_id">
		<type name="bigserial"/>
	</column>
	<constraint name="pk_product_id_tag_id_products_tags" type="pk-constr" factor="10" table="public.products_tags">
		<columns names="product_id,tag_id" ref-type="src-columns"/>
	</constraint>
</table>

<table name="tags">
	<schema name="public"/>
	<position x="35" y="140"/>
	<column name="tag_id">
		<type name="serial"/>
	</column>
	<column name="title" not-null="true">
		<type name="varchar" length="1000"/>
	</column>
	<constraint name="pk_tag_id" type="pk-constr" factor="10" table="public.tags">
		<columns names="tag_id" ref-type="src-columns"/>
	</constraint>
</table>

<table name="pictures">
	<schema name="public"/>
	<position x="570" y="16"/>
	<column name="picture_id">
		<type name="bigserial"/>
	</column>
	<column name="product_id">
		<type name="bigserial"/>
	</column>
	<column name="show_order">
		<type name="varchar" length="1000"/>
	</column>
	<constraint name="pk_picture_id" type="pk-constr" factor="10" table="public.pictures">
		<columns names="picture_id" ref-type="src-columns"/>
	</constraint>
</table>

<constraint name="fk_product_id_books" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.products" table="public.books">
	<columns names="product_id" ref-type="src-columns"/>
	<columns names="product_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_publisher_books" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.subjects" table="public.books">
	<columns names="publisher" ref-type="src-columns"/>
	<columns names="subject_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_publisher_local_books" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.subjects" table="public.books">
	<columns names="publisher_local" ref-type="src-columns"/>
	<columns names="subject_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_user_id" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.&quot;Users&quot;" table="public.&quot;Orders&quot;">
	<columns names="user_id" ref-type="src-columns"/>
	<columns names="user_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_order_id" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.&quot;Orders&quot;" table="public.&quot;Order_Items&quot;">
	<columns names="order_item_id" ref-type="src-columns"/>
	<columns names="order_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_product_id_order_items" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.products" table="public.&quot;Order_Items&quot;">
	<columns names="product_id" ref-type="src-columns"/>
	<columns names="product_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_product_id_attributes" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.products" table="public.attributes">
	<columns names="product_id" ref-type="src-columns"/>
	<columns names="product_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_product_id" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.books" table="public.products_authors">
	<columns names="product_id" ref-type="src-columns"/>
	<columns names="product_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_subject_id" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.subjects" table="public.products_authors">
	<columns names="subject_id" ref-type="src-columns"/>
	<columns names="subject_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_product_id" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.books" table="public.products_artists">
	<columns names="product_id" ref-type="src-columns"/>
	<columns names="product_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_subject_id" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.subjects" table="public.products_artists">
	<columns names="subject_id" ref-type="src-columns"/>
	<columns names="subject_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_product_id" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.products" table="public.products_tags">
	<columns names="product_id" ref-type="src-columns"/>
	<columns names="product_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_tag_id" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.tags" table="public.products_tags">
	<columns names="tag_id" ref-type="src-columns"/>
	<columns names="tag_id" ref-type="dst-columns"/>
</constraint>
<constraint name="fk_product_id_pictures" type="fk-constr"	 comparison-type="MATCH FULL"
	 upd-action="NO ACTION" del-action="NO ACTION" ref-table="public.products" table="public.pictures">
	<columns names="product_id" ref-type="src-columns"/>
	<columns names="product_id" ref-type="dst-columns"/>
</constraint>
<relationship name="&quot;rel_Orders_Users&quot;" type="relfk"
	 src-table="public.&quot;Orders&quot;"
	 dst-table="public.&quot;Users&quot;"
	 src-required="true" dst-required="true"/>

<relationship name="rel_products_authors_books" type="relfk"
	 src-table="public.products_authors"
	 dst-table="public.books"
	 src-required="true" dst-required="true"/>

<relationship name="rel_products_authors_subjects" type="relfk"
	 src-table="public.products_authors"
	 dst-table="public.subjects"
	 src-required="true" dst-required="true"/>

<relationship name="rel_products_artists_books" type="relfk"
	 src-table="public.products_artists"
	 dst-table="public.books"
	 src-required="true" dst-required="true"/>

<relationship name="rel_products_artists_subjects" type="relfk"
	 src-table="public.products_artists"
	 dst-table="public.subjects"
	 src-required="true" dst-required="true"/>

<relationship name="rel_products_tags_products" type="relfk"
	 src-table="public.products_tags"
	 dst-table="public.products"
	 src-required="true" dst-required="true"/>

<relationship name="rel_products_tags_tags" type="relfk"
	 src-table="public.products_tags"
	 dst-table="public.tags"
	 src-required="true" dst-required="true"/>

<relationship name="&quot;rel_Order_Items_Orders&quot;" type="relfk"
	 src-table="public.&quot;Order_Items&quot;"
	 dst-table="public.&quot;Orders&quot;"
	 src-required="true" dst-required="true"/>

<relationship name="&quot;rel_Order_Items_products&quot;" type="relfk"
	 src-table="public.&quot;Order_Items&quot;"
	 dst-table="public.products"
	 src-required="true" dst-required="true"/>

<relationship name="rel_books_products" type="relfk"
	 src-table="public.books"
	 dst-table="public.products"
	 src-required="true" dst-required="true"/>

<relationship name="rel_attributes_products" type="relfk"
	 src-table="public.attributes"
	 dst-table="public.products"
	 src-required="true" dst-required="true"/>

<relationship name="rel_pictures_products" type="relfk"
	 src-table="public.pictures"
	 dst-table="public.products"
	 src-required="true" dst-required="true"/>

<relationship name="rel_books_subjects" type="relfk"
	 src-table="public.books"
	 dst-table="public.subjects"
	 src-required="true" dst-required="true"/>

</dbmodel>
