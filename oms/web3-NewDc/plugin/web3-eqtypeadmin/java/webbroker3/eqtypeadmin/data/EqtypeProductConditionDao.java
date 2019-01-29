head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EqtypeProductConditionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.eqtypeadmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link EqtypeProductConditionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EqtypeProductConditionRow}インスタンスへ関連付けることができます。 
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
 * @@see EqtypeProductConditionPK 
 * @@see EqtypeProductConditionRow 
 */
public class EqtypeProductConditionDao extends DataAccessObject {


  /** 
   * この{@@link EqtypeProductConditionDao}に関連する型指定のRowオブジェクト 
   */
    private EqtypeProductConditionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EqtypeProductConditionRow}と仮定される{@@link DataAccessObject}から新たに{@@link EqtypeProductConditionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EqtypeProductConditionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EqtypeProductConditionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeProductConditionRow )
                return new EqtypeProductConditionDao( (EqtypeProductConditionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeProductConditionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeProductConditionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EqtypeProductConditionRow}オブジェクト 
    */
    protected EqtypeProductConditionDao( EqtypeProductConditionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EqtypeProductConditionRow}オブジェクトを取得します。
   */
    public EqtypeProductConditionRow getEqtypeProductConditionRow() {
        return row;
    }


  /** 
   * 指定の{@@link EqtypeProductConditionRow}オブジェクトから{@@link EqtypeProductConditionDao}オブジェクトを作成します。 
   * これは実際の{@@link EqtypeProductConditionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EqtypeProductConditionDao}取得のために指定の{@@link EqtypeProductConditionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EqtypeProductConditionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EqtypeProductConditionDao forRow( EqtypeProductConditionRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeProductConditionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeProductConditionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EqtypeProductConditionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EqtypeProductConditionPK}やデータベースレコードとして挿入される{@@link EqtypeProductConditionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeProductConditionRow.TYPE );
    }


  /** 
   * {@@link EqtypeProductConditionRow}を一意に特定する{@@link EqtypeProductConditionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EqtypeProductConditionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EqtypeProductConditionParams}オブジェクトの主キーとして利用可能な{@@link EqtypeProductConditionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EqtypeProductConditionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new EqtypeProductConditionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EqtypeProductConditionRow}オブジェクトを検索します。 
   * 
   * @@param p_eqtypeProductConditionId 検索対象であるp_eqtypeProductConditionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeProductConditionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeProductConditionRow findRowByPk( long p_eqtypeProductConditionId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductConditionPK pk = new EqtypeProductConditionPK( p_eqtypeProductConditionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEqtypeProductConditionPKオブジェクトから{@@link EqtypeProductConditionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEqtypeProductConditionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeProductConditionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeProductConditionRow findRowByPk( EqtypeProductConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeProductConditionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static EqtypeProductConditionDao findDaoByPk( long p_eqtypeProductConditionId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductConditionPK pk = new EqtypeProductConditionPK( p_eqtypeProductConditionId );
        EqtypeProductConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EqtypeProductConditionPK)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static EqtypeProductConditionDao findDaoByPk( EqtypeProductConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeProductConditionDao}に紐付く{@@link EqtypeProductConditionRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link EqtypeProductConditionDao}と外部キーの関係にある{@@link ProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
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
   * {@@link ProductRow}と外部キーの関係にある{@@link EqtypeProductConditionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link EqtypeProductConditionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link EqtypeProductConditionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link EqtypeProductConditionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeProductConditionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeProductConditionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
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
   * p_eqtypeProductConditionId, and にて指定の値から一意の{@@link EqtypeProductConditionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_eqtypeProductConditionId 検索対象であるp_eqtypeProductConditionIdフィールドの値
   * 
   * @@return 引数指定のp_eqtypeProductConditionId, and の値と一致する{@@link EqtypeProductConditionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeProductConditionRow findRowByEqtypeProductConditionId( long p_eqtypeProductConditionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "eqtype_product_condition_id=?",
            null,
            new Object[] { new Long(p_eqtypeProductConditionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeProductConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeProductConditionDao.findRowsByEqtypeProductConditionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByEqtypeProductConditionId(long)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static EqtypeProductConditionDao findDaoByEqtypeProductConditionId( long p_eqtypeProductConditionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByEqtypeProductConditionId( p_eqtypeProductConditionId ) );
    }


  /** 
   * p_institutionCode, p_productCode, p_marketCode, p_largeItemDiv, p_smallItemDiv, p_deleteFlg, and にて指定の値から一意の{@@link EqtypeProductConditionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_largeItemDiv 検索対象であるp_largeItemDivフィールドの値
   * @@param p_smallItemDiv 検索対象であるp_smallItemDivフィールドの値
   * @@param p_deleteFlg 検索対象であるp_deleteFlgフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productCode, p_marketCode, p_largeItemDiv, p_smallItemDiv, p_deleteFlg, and の値と一致する{@@link EqtypeProductConditionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeProductConditionRow findRowByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg( String p_institutionCode, String p_productCode, String p_marketCode, String p_largeItemDiv, String p_smallItemDiv, String p_deleteFlg ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "institution_code=? and product_code=? and market_code=? and large_item_div=? and small_item_div=? and delete_flg=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_marketCode, p_largeItemDiv, p_smallItemDiv, p_deleteFlg } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeProductConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeProductConditionDao.findRowsByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg(String, String, String, String, String, String)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static EqtypeProductConditionDao findDaoByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg( String p_institutionCode, String p_productCode, String p_marketCode, String p_largeItemDiv, String p_smallItemDiv, String p_deleteFlg ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg( p_institutionCode, p_productCode, p_marketCode, p_largeItemDiv, p_smallItemDiv, p_deleteFlg ) );
    }


  /** 
   * p_productId, p_marketId, p_largeItemDiv, p_smallItemDiv, p_deleteFlg, and にて指定の値から一意の{@@link EqtypeProductConditionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_largeItemDiv 検索対象であるp_largeItemDivフィールドの値
   * @@param p_smallItemDiv 検索対象であるp_smallItemDivフィールドの値
   * @@param p_deleteFlg 検索対象であるp_deleteFlgフィールドの値
   * 
   * @@return 引数指定のp_productId, p_marketId, p_largeItemDiv, p_smallItemDiv, p_deleteFlg, and の値と一致する{@@link EqtypeProductConditionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeProductConditionRow findRowByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg( long p_productId, long p_marketId, String p_largeItemDiv, String p_smallItemDiv, String p_deleteFlg ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "product_id=? and market_id=? and large_item_div=? and small_item_div=? and delete_flg=?",
            null,
            new Object[] { new Long(p_productId), new Long(p_marketId), p_largeItemDiv, p_smallItemDiv, p_deleteFlg } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeProductConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeProductConditionDao.findRowsByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg(long, long, String, String, String)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static EqtypeProductConditionDao findDaoByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg( long p_productId, long p_marketId, String p_largeItemDiv, String p_smallItemDiv, String p_deleteFlg ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg( p_productId, p_marketId, p_largeItemDiv, p_smallItemDiv, p_deleteFlg ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productCode, p_deleteFlg, p_marketCode, and にて指定の値に一致する{@@link EqtypeProductConditionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_deleteFlg 検索対象であるp_deleteFlgフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productCode, p_deleteFlg, p_marketCode, and の値と一致する{@@link EqtypeProductConditionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeProductCodeDeleteFlgMarketCode( String p_institutionCode, String p_productCode, String p_deleteFlg, String p_marketCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "institution_code=? and product_code=? and delete_flg=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_deleteFlg, p_marketCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeProductCodeDeleteFlgMarketCode(String, String, String, String)}および{@@link #forRow(EqtypeProductConditionRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeProductCodeDeleteFlgMarketCode( String p_institutionCode, String p_productCode, String p_deleteFlg, String p_marketCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductCodeDeleteFlgMarketCode( p_institutionCode, p_productCode, p_deleteFlg, p_marketCode ) );
    }

}
@
