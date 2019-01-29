head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TradedProductCalendarDao.java;


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
 * {@@link TradedProductCalendarDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TradedProductCalendarRow}インスタンスへ関連付けることができます。 
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
 * @@see TradedProductCalendarPK 
 * @@see TradedProductCalendarRow 
 */
public class TradedProductCalendarDao extends DataAccessObject {


  /** 
   * この{@@link TradedProductCalendarDao}に関連する型指定のRowオブジェクト 
   */
    private TradedProductCalendarRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TradedProductCalendarRow}と仮定される{@@link DataAccessObject}から新たに{@@link TradedProductCalendarDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TradedProductCalendarDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TradedProductCalendarRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TradedProductCalendarRow )
                return new TradedProductCalendarDao( (TradedProductCalendarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TradedProductCalendarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TradedProductCalendarRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TradedProductCalendarRow}オブジェクト 
    */
    protected TradedProductCalendarDao( TradedProductCalendarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TradedProductCalendarRow}オブジェクトを取得します。
   */
    public TradedProductCalendarRow getTradedProductCalendarRow() {
        return row;
    }


  /** 
   * 指定の{@@link TradedProductCalendarRow}オブジェクトから{@@link TradedProductCalendarDao}オブジェクトを作成します。 
   * これは実際の{@@link TradedProductCalendarRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TradedProductCalendarDao}取得のために指定の{@@link TradedProductCalendarRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TradedProductCalendarDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TradedProductCalendarDao forRow( TradedProductCalendarRow row ) throws java.lang.IllegalArgumentException {
        return (TradedProductCalendarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TradedProductCalendarRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TradedProductCalendarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TradedProductCalendarPK}やデータベースレコードとして挿入される{@@link TradedProductCalendarParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TradedProductCalendarRow.TYPE );
    }


  /** 
   * {@@link TradedProductCalendarRow}を一意に特定する{@@link TradedProductCalendarPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TradedProductCalendarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TradedProductCalendarParams}オブジェクトの主キーとして利用可能な{@@link TradedProductCalendarPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TradedProductCalendarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TradedProductCalendarRow}オブジェクトを検索します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_tradeDate 検索対象であるp_tradeDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradedProductCalendarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradedProductCalendarRow findRowByPk( long p_tradedProductId, java.sql.Timestamp p_tradeDate ) throws DataFindException, DataQueryException, DataNetworkException {
        TradedProductCalendarPK pk = new TradedProductCalendarPK( p_tradedProductId, p_tradeDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTradedProductCalendarPKオブジェクトから{@@link TradedProductCalendarRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTradedProductCalendarPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradedProductCalendarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradedProductCalendarRow findRowByPk( TradedProductCalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TradedProductCalendarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,java.sql.Timestamp)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static TradedProductCalendarDao findDaoByPk( long p_tradedProductId, java.sql.Timestamp p_tradeDate ) throws DataFindException, DataQueryException, DataNetworkException {
        TradedProductCalendarPK pk = new TradedProductCalendarPK( p_tradedProductId, p_tradeDate );
        TradedProductCalendarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TradedProductCalendarPK)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static TradedProductCalendarDao findDaoByPk( TradedProductCalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TradedProductCalendarRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link TradedProductCalendarDao}に紐付く{@@link TradedProductCalendarRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link TradedProductCalendarDao}と外部キーの関係にある{@@link MarketRow} 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


  /** 
   * この{@@link TradedProductCalendarDao}に紐付く{@@link TradedProductCalendarRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link TradedProductCalendarDao}と外部キーの関係にある{@@link ProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public ProductDao fetchProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        ProductPK pk = new ProductPK( row.getProductId() );
        DataAccessObject dao = ProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof ProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (ProductDao) dao;
    }


  /** 
   * この{@@link TradedProductCalendarDao}に紐付く{@@link TradedProductCalendarRow}内で外部キーの関係をもつ{@@link TradedProductRow}を検索します。 
   * 
   * @@return {@@link TradedProductCalendarDao}と外部キーの関係にある{@@link TradedProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public TradedProductRow fetchTradedProductRowViaTradedProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        TradedProductPK pk = new TradedProductPK( row.getTradedProductId() );
        Row row = TradedProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof TradedProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (TradedProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductRowViaTradedProductId()}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public TradedProductDao fetchTradedProductDaoViaTradedProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        TradedProductPK pk = new TradedProductPK( row.getTradedProductId() );
        DataAccessObject dao = TradedProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TradedProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TradedProductDao) dao;
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
   * {@@link MarketRow}と外部キーの関係にある{@@link TradedProductCalendarRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link TradedProductCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link TradedProductCalendarRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link TradedProductCalendarRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TradedProductCalendarRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TradedProductCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradedProductCalendarRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
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
   * {@@link ProductRow}と外部キーの関係にある{@@link TradedProductCalendarRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link TradedProductCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link TradedProductCalendarRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link TradedProductCalendarRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TradedProductCalendarRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TradedProductCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradedProductCalendarRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for TradedProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByTradedProductId(TradedProductRow)}を使用してください。 
   */
    public static List findRowsByTradedProductId( TradedProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( dao.getTradedProductRow() );
    }


  /** 
   * {@@link TradedProductRow}と外部キーの関係にある{@@link TradedProductCalendarRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link TradedProductRow}オブジェクト 
   * @@return 指定の{@@link TradedProductRow}に外部キーを持つ{@@link TradedProductCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( row.getTradedProductId() );
    }


  /** 
   * {@@link TradedProductPK}と外部キーの関係にある{@@link TradedProductCalendarRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link TradedProductPK}オブジェクト 
   * @@return {@@link TradedProductPK}と外部キーが一致する値を持つ{@@link TradedProductCalendarRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( pk.traded_product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TradedProductCalendarRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TradedProductCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( long p_tradedProductId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradedProductCalendarRow.TYPE,
            "traded_product_id=?",
            null,
            new Object[] { new Long(p_tradedProductId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for TradedProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByTradedProductId(TradedProductRow)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(TradedProductRow)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(TradedProductPK)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( pk.traded_product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(long)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( long p_tradedProductId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( p_tradedProductId ) );
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
   * p_tradedProductId, p_tradeDate, and にて指定の値から一意の{@@link TradedProductCalendarRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_tradeDate 検索対象であるp_tradeDateフィールドの値
   * 
   * @@return 引数指定のp_tradedProductId, p_tradeDate, and の値と一致する{@@link TradedProductCalendarRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TradedProductCalendarRow findRowByTradedProductIdTradeDate( long p_tradedProductId, java.sql.Timestamp p_tradeDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TradedProductCalendarRow.TYPE,
            "traded_product_id=? and trade_date=?",
            null,
            new Object[] { new Long(p_tradedProductId), p_tradeDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TradedProductCalendarRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TradedProductCalendarDao.findRowsByTradedProductIdTradeDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTradedProductIdTradeDate(long, java.sql.Timestamp)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static TradedProductCalendarDao findDaoByTradedProductIdTradeDate( long p_tradedProductId, java.sql.Timestamp p_tradeDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradedProductIdTradeDate( p_tradedProductId, p_tradeDate ) );
    }


  /** 
   * p_productId, p_marketId, p_tradeDate, and にて指定の値から一意の{@@link TradedProductCalendarRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_tradeDate 検索対象であるp_tradeDateフィールドの値
   * 
   * @@return 引数指定のp_productId, p_marketId, p_tradeDate, and の値と一致する{@@link TradedProductCalendarRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TradedProductCalendarRow findRowByProductIdMarketIdTradeDate( long p_productId, long p_marketId, java.sql.Timestamp p_tradeDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TradedProductCalendarRow.TYPE,
            "product_id=? and market_id=? and trade_date=?",
            null,
            new Object[] { new Long(p_productId), new Long(p_marketId), p_tradeDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TradedProductCalendarRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TradedProductCalendarDao.findRowsByProductIdMarketIdTradeDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdMarketIdTradeDate(long, long, java.sql.Timestamp)}および{@@link #forRow(TradedProductCalendarRow)}を使用してください。 
   */
    public static TradedProductCalendarDao findDaoByProductIdMarketIdTradeDate( long p_productId, long p_marketId, java.sql.Timestamp p_tradeDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdMarketIdTradeDate( p_productId, p_marketId, p_tradeDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
