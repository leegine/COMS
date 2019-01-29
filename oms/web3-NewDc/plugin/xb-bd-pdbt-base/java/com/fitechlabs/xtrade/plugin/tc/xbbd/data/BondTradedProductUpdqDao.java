head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.57.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondTradedProductUpdqDao.java;


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
 * {@@link BondTradedProductUpdqDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BondTradedProductUpdqRow}インスタンスへ関連付けることができます。 
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
 * @@see BondTradedProductUpdqPK 
 * @@see BondTradedProductUpdqRow 
 */
public class BondTradedProductUpdqDao extends DataAccessObject {


  /** 
   * この{@@link BondTradedProductUpdqDao}に関連する型指定のRowオブジェクト 
   */
    private BondTradedProductUpdqRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BondTradedProductUpdqRow}と仮定される{@@link DataAccessObject}から新たに{@@link BondTradedProductUpdqDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BondTradedProductUpdqDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BondTradedProductUpdqRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondTradedProductUpdqRow )
                return new BondTradedProductUpdqDao( (BondTradedProductUpdqRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondTradedProductUpdqRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondTradedProductUpdqRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BondTradedProductUpdqRow}オブジェクト 
    */
    protected BondTradedProductUpdqDao( BondTradedProductUpdqRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BondTradedProductUpdqRow}オブジェクトを取得します。
   */
    public BondTradedProductUpdqRow getBondTradedProductUpdqRow() {
        return row;
    }


  /** 
   * 指定の{@@link BondTradedProductUpdqRow}オブジェクトから{@@link BondTradedProductUpdqDao}オブジェクトを作成します。 
   * これは実際の{@@link BondTradedProductUpdqRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BondTradedProductUpdqDao}取得のために指定の{@@link BondTradedProductUpdqRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BondTradedProductUpdqDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BondTradedProductUpdqDao forRow( BondTradedProductUpdqRow row ) throws java.lang.IllegalArgumentException {
        return (BondTradedProductUpdqDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondTradedProductUpdqRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BondTradedProductUpdqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BondTradedProductUpdqPK}やデータベースレコードとして挿入される{@@link BondTradedProductUpdqParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondTradedProductUpdqRow.TYPE );
    }


  /** 
   * {@@link BondTradedProductUpdqRow}を一意に特定する{@@link BondTradedProductUpdqPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BondTradedProductUpdqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BondTradedProductUpdqParams}オブジェクトの主キーとして利用可能な{@@link BondTradedProductUpdqPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BondTradedProductUpdqPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BondTradedProductUpdqRow}オブジェクトを検索します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_validForBizDate 検索対象であるp_validForBizDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondTradedProductUpdqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondTradedProductUpdqRow findRowByPk( long p_tradedProductId, String p_validForBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondTradedProductUpdqPK pk = new BondTradedProductUpdqPK( p_tradedProductId, p_validForBizDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBondTradedProductUpdqPKオブジェクトから{@@link BondTradedProductUpdqRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBondTradedProductUpdqPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondTradedProductUpdqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondTradedProductUpdqRow findRowByPk( BondTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondTradedProductUpdqRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static BondTradedProductUpdqDao findDaoByPk( long p_tradedProductId, String p_validForBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondTradedProductUpdqPK pk = new BondTradedProductUpdqPK( p_tradedProductId, p_validForBizDate );
        BondTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BondTradedProductUpdqPK)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static BondTradedProductUpdqDao findDaoByPk( BondTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link BondTradedProductUpdqDao}に紐付く{@@link BondTradedProductUpdqRow}内で外部キーの関係をもつ{@@link TradedProductRow}を検索します。 
   * 
   * @@return {@@link BondTradedProductUpdqDao}と外部キーの関係にある{@@link TradedProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchTradedProductRowViaTradedProductId()}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public TradedProductDao fetchTradedProductDaoViaTradedProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        TradedProductPK pk = new TradedProductPK( row.getTradedProductId() );
        DataAccessObject dao = TradedProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TradedProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TradedProductDao) dao;
    }


  /** 
   * この{@@link BondTradedProductUpdqDao}に紐付く{@@link BondTradedProductUpdqRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link BondTradedProductUpdqDao}と外部キーの関係にある{@@link MarketRow} 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


  /** 
   * この{@@link BondTradedProductUpdqDao}に紐付く{@@link BondTradedProductUpdqRow}内で外部キーの関係をもつ{@@link BondProductRow}を検索します。 
   * 
   * @@return {@@link BondTradedProductUpdqDao}と外部キーの関係にある{@@link BondProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public BondProductRow fetchBondProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        Row row = BondProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondProductRowViaProductId()}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public BondProductDao fetchBondProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        DataAccessObject dao = BondProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondProductDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

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
   * {@@link TradedProductRow}と外部キーの関係にある{@@link BondTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link TradedProductRow}オブジェクト 
   * @@return 指定の{@@link TradedProductRow}に外部キーを持つ{@@link BondTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( row.getTradedProductId() );
    }


  /** 
   * {@@link TradedProductPK}と外部キーの関係にある{@@link BondTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link TradedProductPK}オブジェクト 
   * @@return {@@link TradedProductPK}と外部キーが一致する値を持つ{@@link BondTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( pk.traded_product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( long p_tradedProductId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondTradedProductUpdqRow.TYPE,
            "traded_product_id=?",
            null,
            new Object[] { new Long(p_tradedProductId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for TradedProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByTradedProductId(TradedProductRow)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(TradedProductRow)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(TradedProductPK)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( pk.traded_product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(long)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( long p_tradedProductId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( p_tradedProductId ) );
    }


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
   * {@@link MarketRow}と外部キーの関係にある{@@link BondTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link BondTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link BondTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link BondTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondTradedProductUpdqRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(BondProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( BondProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getBondProductRow() );
    }


  /** 
   * {@@link BondProductRow}と外部キーの関係にある{@@link BondTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BondProductRow}オブジェクト 
   * @@return 指定の{@@link BondProductRow}に外部キーを持つ{@@link BondTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link BondProductPK}と外部キーの関係にある{@@link BondTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BondProductPK}オブジェクト 
   * @@return {@@link BondProductPK}と外部キーが一致する値を持つ{@@link BondTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondTradedProductUpdqRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(BondProductRow)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(BondProductRow)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(BondProductPK)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
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
   * p_tradedProductId, p_validForBizDate, and にて指定の値から一意の{@@link BondTradedProductUpdqRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_validForBizDate 検索対象であるp_validForBizDateフィールドの値
   * 
   * @@return 引数指定のp_tradedProductId, p_validForBizDate, and の値と一致する{@@link BondTradedProductUpdqRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondTradedProductUpdqRow findRowByTradedProductIdValidForBizDate( long p_tradedProductId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondTradedProductUpdqRow.TYPE,
            "traded_product_id=? and valid_for_biz_date=?",
            null,
            new Object[] { new Long(p_tradedProductId), p_validForBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondTradedProductUpdqDao.findRowsByTradedProductIdValidForBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTradedProductIdValidForBizDate(long, String)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static BondTradedProductUpdqDao findDaoByTradedProductIdValidForBizDate( long p_tradedProductId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradedProductIdValidForBizDate( p_tradedProductId, p_validForBizDate ) );
    }


  /** 
   * p_productId, p_marketId, p_validForBizDate, and にて指定の値から一意の{@@link BondTradedProductUpdqRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_validForBizDate 検索対象であるp_validForBizDateフィールドの値
   * 
   * @@return 引数指定のp_productId, p_marketId, p_validForBizDate, and の値と一致する{@@link BondTradedProductUpdqRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondTradedProductUpdqRow findRowByProductIdMarketIdValidForBizDate( long p_productId, long p_marketId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondTradedProductUpdqRow.TYPE,
            "product_id=? and market_id=? and valid_for_biz_date=?",
            null,
            new Object[] { new Long(p_productId), new Long(p_marketId), p_validForBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondTradedProductUpdqDao.findRowsByProductIdMarketIdValidForBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdMarketIdValidForBizDate(long, long, String)}および{@@link #forRow(BondTradedProductUpdqRow)}を使用してください。 
   */
    public static BondTradedProductUpdqDao findDaoByProductIdMarketIdValidForBizDate( long p_productId, long p_marketId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdMarketIdValidForBizDate( p_productId, p_marketId, p_validForBizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
