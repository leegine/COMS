head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.39.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeProductDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link EqtypeProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EqtypeProductRow}インスタンスへ関連付けることができます。 
 * クライアントコードにおいて必要とされる共通のデータオペレーションを実装しています。 
 * <p> 
 *     <li> 新しいレコードに対し一意の主キー値またはオブジェクトを作成 </li> 
 *     <li> 外部キーからレコードを検索 </li> 
 *     <li> 外部キーの関係にあるオブジェクトを検索 </li> 
 *     <li> インデックスを持つ既存のデータベーススキーマにクエリを実行 </li> 
 * <p> 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeProductPK 
 * @@see EqtypeProductRow 
 */
public class EqtypeProductDao extends DataAccessObject {


  /** 
   * この{@@link EqtypeProductDao}に関連する型指定のRowオブジェクト 
   */
    private EqtypeProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EqtypeProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link EqtypeProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EqtypeProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EqtypeProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeProductRow )
                return new EqtypeProductDao( (EqtypeProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EqtypeProductRow}オブジェクト 
    */
    protected EqtypeProductDao( EqtypeProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EqtypeProductRow}オブジェクトを取得します。
   */
    public EqtypeProductRow getEqtypeProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link EqtypeProductRow}オブジェクトから{@@link EqtypeProductDao}オブジェクトを作成します。 
   * これは実際の{@@link EqtypeProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EqtypeProductDao}取得のために指定の{@@link EqtypeProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EqtypeProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EqtypeProductDao forRow( EqtypeProductRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EqtypeProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EqtypeProductPK}やデータベースレコードとして挿入される{@@link EqtypeProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeProductRow.TYPE );
    }


  /** 
   * {@@link EqtypeProductRow}を一意に特定する{@@link EqtypeProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EqtypeProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EqtypeProductParams}オブジェクトの主キーとして利用可能な{@@link EqtypeProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EqtypeProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new EqtypeProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EqtypeProductRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductPK pk = new EqtypeProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEqtypeProductPKオブジェクトから{@@link EqtypeProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEqtypeProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeProductRow findRowByPk( EqtypeProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public static EqtypeProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductPK pk = new EqtypeProductPK( p_productId );
        EqtypeProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EqtypeProductPK)}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public static EqtypeProductDao findDaoByPk( EqtypeProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeProductDao}に紐付く{@@link EqtypeProductRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link EqtypeProductDao}と外部キーの関係にある{@@link ProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public ProductRow fetchProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        ProductPK pk = new ProductPK( row.getProductId() );
        Row row = ProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof ProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (ProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public ProductDao fetchProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        ProductPK pk = new ProductPK( row.getProductId() );
        DataAccessObject dao = ProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof ProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (ProductDao) dao;
    }


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeProductDao}に関連する{@@link EqtypeProductRow}の外部キーがある{@@link EqtypeTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link EqtypeProductDao}に関連する{@@link EqtypeProductRow}の外部キーがある{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchEqtypeTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return EqtypeTradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeTradedProductUpdqRowsByProductId()}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public List fetchEqtypeTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return EqtypeTradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeTradedProductUpdqRowsByProductId()}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public List fetchEqtypeTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchEqtypeTradedProductUpdqDaosByProductId();
    }


  /** 
   * この{@@link EqtypeProductDao}に関連する{@@link EqtypeProductRow}の外部キーがある{@@link EqtypeTradedProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link EqtypeProductDao}に関連する{@@link EqtypeProductRow}の外部キーがある{@@link EqtypeTradedProductRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchEqtypeTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return EqtypeTradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeTradedProductRowsByProductId()}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public List fetchEqtypeTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return EqtypeTradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeTradedProductRowsByProductId()}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public List fetchEqtypeTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchEqtypeTradedProductDaosByProductId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Product
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(ProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( ProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getProductRow() );
    }


  /** 
   * {@@link ProductRow}と外部キーの関係にある{@@link EqtypeProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link EqtypeProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link EqtypeProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link EqtypeProductRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_productId, and にて指定の値から一意の{@@link EqtypeProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のp_productId, and の値と一致する{@@link EqtypeProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductId(long)}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public static EqtypeProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }


  /** 
   * p_institutionCode, p_productCode, and にて指定の値から一意の{@@link EqtypeProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productCode, and の値と一致する{@@link EqtypeProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeProductRow findRowByInstitutionCodeProductCode( String p_institutionCode, String p_productCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeProductRow.TYPE,
            "institution_code=? and product_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeProductDao.findRowsByInstitutionCodeProductCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductCode(String, String)}および{@@link #forRow(EqtypeProductRow)}を使用してください。 
   */
    public static EqtypeProductDao findDaoByInstitutionCodeProductCode( String p_institutionCode, String p_productCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCode( p_institutionCode, p_productCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
