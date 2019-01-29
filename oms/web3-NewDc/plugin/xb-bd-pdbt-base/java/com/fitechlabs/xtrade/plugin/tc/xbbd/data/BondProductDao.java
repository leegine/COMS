head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.58.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondProductDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BondProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BondProductRow}インスタンスへ関連付けることができます。 
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
 * @@see BondProductPK 
 * @@see BondProductRow 
 */
public class BondProductDao extends DataAccessObject {


  /** 
   * この{@@link BondProductDao}に関連する型指定のRowオブジェクト 
   */
    private BondProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BondProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link BondProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BondProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BondProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondProductRow )
                return new BondProductDao( (BondProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BondProductRow}オブジェクト 
    */
    protected BondProductDao( BondProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BondProductRow}オブジェクトを取得します。
   */
    public BondProductRow getBondProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link BondProductRow}オブジェクトから{@@link BondProductDao}オブジェクトを作成します。 
   * これは実際の{@@link BondProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BondProductDao}取得のために指定の{@@link BondProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BondProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BondProductDao forRow( BondProductRow row ) throws java.lang.IllegalArgumentException {
        return (BondProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BondProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BondProductPK}やデータベースレコードとして挿入される{@@link BondProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondProductRow.TYPE );
    }


  /** 
   * {@@link BondProductRow}を一意に特定する{@@link BondProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BondProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BondProductParams}オブジェクトの主キーとして利用可能な{@@link BondProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BondProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BondProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BondProductRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondProductPK pk = new BondProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBondProductPKオブジェクトから{@@link BondProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBondProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondProductRow findRowByPk( BondProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static BondProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondProductPK pk = new BondProductPK( p_productId );
        BondProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BondProductPK)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static BondProductDao findDaoByPk( BondProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link BondProductDao}に紐付く{@@link BondProductRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link BondProductDao}と外部キーの関係にある{@@link ProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(BondProductRow)}を使用してください。 
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
   * この{@@link BondProductDao}に関連する{@@link BondProductRow}の外部キーがある{@@link BondTradedProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link BondProductDao}に関連する{@@link BondProductRow}の外部キーがある{@@link BondTradedProductRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchBondTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return BondTradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondTradedProductRowsByProductId()}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public List fetchBondTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return BondTradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondTradedProductRowsByProductId()}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public List fetchBondTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchBondTradedProductDaosByProductId();
    }


  /** 
   * この{@@link BondProductDao}に関連する{@@link BondProductRow}の外部キーがある{@@link BondTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link BondProductDao}に関連する{@@link BondProductRow}の外部キーがある{@@link BondTradedProductUpdqRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchBondTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return BondTradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondTradedProductUpdqRowsByProductId()}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public List fetchBondTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return BondTradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondTradedProductUpdqRowsByProductId()}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public List fetchBondTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchBondTradedProductUpdqDaosByProductId();
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
   * {@@link ProductRow}と外部キーの関係にある{@@link BondProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link BondProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link BondProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link BondProductRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(BondProductRow)}を使用してください。 
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
   * p_productId, and にて指定の値から一意の{@@link BondProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のp_productId, and の値と一致する{@@link BondProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductId(long)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static BondProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }


  /** 
   * p_institutionCode, p_productCode, p_productIssueCode, and にて指定の値から一意の{@@link BondProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_productIssueCode 検索対象であるp_productIssueCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productCode, p_productIssueCode, and の値と一致する{@@link BondProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondProductRow findRowByInstitutionCodeProductCodeProductIssueCode( String p_institutionCode, String p_productCode, String p_productIssueCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondProductRow.TYPE,
            "institution_code=? and product_code=? and product_issue_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_productIssueCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondProductDao.findRowsByInstitutionCodeProductCodeProductIssueCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductCodeProductIssueCode(String, String, String)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static BondProductDao findDaoByInstitutionCodeProductCodeProductIssueCode( String p_institutionCode, String p_productCode, String p_productIssueCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeProductIssueCode( p_institutionCode, p_productCode, p_productIssueCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_bondType, and にて指定の値に一致する{@@link BondProductRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_bondType 検索対象であるp_bondTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_bondType, and の値と一致する{@@link BondProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBondType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum p_bondType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondProductRow.TYPE,
            "institution_code=? and bond_type=?",
            null,
            new Object[] { p_institutionCode, p_bondType } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBondType(String, com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBondType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum p_bondType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBondType( p_institutionCode, p_bondType ) );
    }


  /** 
   * p_institutionCode, p_tradeHandleDiv, p_tradeType, and にて指定の値に一致する{@@link BondProductRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_tradeHandleDiv 検索対象であるp_tradeHandleDivフィールドの値
   * @@param p_tradeType 検索対象であるp_tradeTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_tradeHandleDiv, p_tradeType, and の値と一致する{@@link BondProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeTradeHandleDivTradeType( String p_institutionCode, String p_tradeHandleDiv, String p_tradeType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondProductRow.TYPE,
            "institution_code=? and trade_handle_div=? and trade_type=?",
            null,
            new Object[] { p_institutionCode, p_tradeHandleDiv, p_tradeType } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeTradeHandleDivTradeType(String, String, String)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeTradeHandleDivTradeType( String p_institutionCode, String p_tradeHandleDiv, String p_tradeType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeTradeHandleDivTradeType( p_institutionCode, p_tradeHandleDiv, p_tradeType ) );
    }


  /** 
   * p_institutionCode, p_currencyCode, and にて指定の値に一致する{@@link BondProductRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_currencyCode 検索対象であるp_currencyCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_currencyCode, and の値と一致する{@@link BondProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondProductRow.TYPE,
            "institution_code=? and currency_code=?",
            null,
            new Object[] { p_institutionCode, p_currencyCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeCurrencyCode(String, String)}および{@@link #forRow(BondProductRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeCurrencyCode( p_institutionCode, p_currencyCode ) );
    }

}
@
