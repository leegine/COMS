head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TradedProductDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link TradedProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TradedProductRow}インスタンスへ関連付けることができます。 
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
 * @@see TradedProductPK 
 * @@see TradedProductRow 
 */
public class TradedProductDao extends DataAccessObject {


  /** 
   * この{@@link TradedProductDao}に関連する型指定のRowオブジェクト 
   */
    private TradedProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TradedProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link TradedProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TradedProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TradedProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TradedProductRow )
                return new TradedProductDao( (TradedProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TradedProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TradedProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TradedProductRow}オブジェクト 
    */
    protected TradedProductDao( TradedProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TradedProductRow}オブジェクトを取得します。
   */
    public TradedProductRow getTradedProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link TradedProductRow}オブジェクトから{@@link TradedProductDao}オブジェクトを作成します。 
   * これは実際の{@@link TradedProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TradedProductDao}取得のために指定の{@@link TradedProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TradedProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TradedProductDao forRow( TradedProductRow row ) throws java.lang.IllegalArgumentException {
        return (TradedProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TradedProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TradedProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TradedProductPK}やデータベースレコードとして挿入される{@@link TradedProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TradedProductRow.TYPE );
    }


  /** 
   * {@@link TradedProductRow}を一意に特定する{@@link TradedProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TradedProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TradedProductParams}オブジェクトの主キーとして利用可能な{@@link TradedProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TradedProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TradedProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TradedProductRow}オブジェクトを検索します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradedProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradedProductRow findRowByPk( long p_tradedProductId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradedProductPK pk = new TradedProductPK( p_tradedProductId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTradedProductPKオブジェクトから{@@link TradedProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTradedProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradedProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradedProductRow findRowByPk( TradedProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TradedProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static TradedProductDao findDaoByPk( long p_tradedProductId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradedProductPK pk = new TradedProductPK( p_tradedProductId );
        TradedProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TradedProductPK)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static TradedProductDao findDaoByPk( TradedProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TradedProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link TradedProductDao}に紐付く{@@link TradedProductRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link TradedProductDao}と外部キーの関係にある{@@link MarketRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public MarketRow fetchMarketRowViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        Row row = MarketDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MarketRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MarketRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


  /** 
   * この{@@link TradedProductDao}に紐付く{@@link TradedProductRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link TradedProductDao}と外部キーの関係にある{@@link ProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(TradedProductRow)}を使用してください。 
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
   * この{@@link TradedProductDao}に関連する{@@link TradedProductRow}の外部キーがある{@@link TradedProductCalendarRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link TradedProductDao}に関連する{@@link TradedProductRow}の外部キーがある{@@link TradedProductCalendarRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTradedProductCalendarRowsByTradedProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findRowsByTradedProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductCalendarRowsByTradedProductId()}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public List fetchTradedProductCalendarDaosByTradedProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findDaosByTradedProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductCalendarRowsByTradedProductId()}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public List fetchTradedProductCalendarDaosTradedProductId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductCalendarDaosByTradedProductId();
    }


  /** 
   * この{@@link TradedProductDao}に関連する{@@link TradedProductRow}の外部キーがある{@@link TradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link TradedProductDao}に関連する{@@link TradedProductRow}の外部キーがある{@@link TradedProductUpdqRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTradedProductUpdqRowsByTradedProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findRowsByTradedProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductUpdqRowsByTradedProductId()}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public List fetchTradedProductUpdqDaosByTradedProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findDaosByTradedProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductUpdqRowsByTradedProductId()}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public List fetchTradedProductUpdqDaosTradedProductId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductUpdqDaosByTradedProductId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Market
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByMarketId(MarketRow)}を使用してください。 
   */
    public static List findRowsByMarketId( MarketDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( dao.getMarketRow() );
    }


  /** 
   * {@@link MarketRow}と外部キーの関係にある{@@link TradedProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link TradedProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link TradedProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link TradedProductRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TradedProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TradedProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradedProductRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
    }


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
   * {@@link ProductRow}と外部キーの関係にある{@@link TradedProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link TradedProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link TradedProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link TradedProductRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TradedProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TradedProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradedProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(TradedProductRow)}を使用してください。 
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
   * p_tradedProductId, and にて指定の値から一意の{@@link TradedProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * 
   * @@return 引数指定のp_tradedProductId, and の値と一致する{@@link TradedProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TradedProductRow findRowByTradedProductId( long p_tradedProductId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TradedProductRow.TYPE,
            "traded_product_id=?",
            null,
            new Object[] { new Long(p_tradedProductId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TradedProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TradedProductDao.findRowsByTradedProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTradedProductId(long)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static TradedProductDao findDaoByTradedProductId( long p_tradedProductId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradedProductId( p_tradedProductId ) );
    }


  /** 
   * p_productId, p_marketId, and にて指定の値から一意の{@@link TradedProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 引数指定のp_productId, p_marketId, and の値と一致する{@@link TradedProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TradedProductRow findRowByProductIdMarketId( long p_productId, long p_marketId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TradedProductRow.TYPE,
            "product_id=? and market_id=?",
            null,
            new Object[] { new Long(p_productId), new Long(p_marketId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TradedProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TradedProductDao.findRowsByProductIdMarketId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdMarketId(long, long)}および{@@link #forRow(TradedProductRow)}を使用してください。 
   */
    public static TradedProductDao findDaoByProductIdMarketId( long p_productId, long p_marketId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdMarketId( p_productId, p_marketId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
