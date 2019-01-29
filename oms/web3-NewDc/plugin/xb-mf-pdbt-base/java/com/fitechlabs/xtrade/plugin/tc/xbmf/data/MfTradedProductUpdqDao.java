head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.11.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MfTradedProductUpdqDao.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MfTradedProductUpdqDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MfTradedProductUpdqRow}インスタンスへ関連付けることができます。 
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
 * @@see MfTradedProductUpdqPK 
 * @@see MfTradedProductUpdqRow 
 */
public class MfTradedProductUpdqDao extends DataAccessObject {


  /** 
   * この{@@link MfTradedProductUpdqDao}に関連する型指定のRowオブジェクト 
   */
    private MfTradedProductUpdqRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MfTradedProductUpdqRow}と仮定される{@@link DataAccessObject}から新たに{@@link MfTradedProductUpdqDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MfTradedProductUpdqDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MfTradedProductUpdqRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfTradedProductUpdqRow )
                return new MfTradedProductUpdqDao( (MfTradedProductUpdqRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfTradedProductUpdqRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfTradedProductUpdqRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MfTradedProductUpdqRow}オブジェクト 
    */
    protected MfTradedProductUpdqDao( MfTradedProductUpdqRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MfTradedProductUpdqRow}オブジェクトを取得します。
   */
    public MfTradedProductUpdqRow getMfTradedProductUpdqRow() {
        return row;
    }


  /** 
   * 指定の{@@link MfTradedProductUpdqRow}オブジェクトから{@@link MfTradedProductUpdqDao}オブジェクトを作成します。 
   * これは実際の{@@link MfTradedProductUpdqRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MfTradedProductUpdqDao}取得のために指定の{@@link MfTradedProductUpdqRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MfTradedProductUpdqDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MfTradedProductUpdqDao forRow( MfTradedProductUpdqRow row ) throws java.lang.IllegalArgumentException {
        return (MfTradedProductUpdqDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfTradedProductUpdqRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MfTradedProductUpdqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MfTradedProductUpdqPK}やデータベースレコードとして挿入される{@@link MfTradedProductUpdqParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfTradedProductUpdqRow.TYPE );
    }


  /** 
   * {@@link MfTradedProductUpdqRow}を一意に特定する{@@link MfTradedProductUpdqPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MfTradedProductUpdqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MfTradedProductUpdqParams}オブジェクトの主キーとして利用可能な{@@link MfTradedProductUpdqPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MfTradedProductUpdqPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MfTradedProductUpdqRow}オブジェクトを検索します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_validForBizDate 検索対象であるp_validForBizDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfTradedProductUpdqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfTradedProductUpdqRow findRowByPk( long p_tradedProductId, String p_validForBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MfTradedProductUpdqPK pk = new MfTradedProductUpdqPK( p_tradedProductId, p_validForBizDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMfTradedProductUpdqPKオブジェクトから{@@link MfTradedProductUpdqRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMfTradedProductUpdqPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfTradedProductUpdqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfTradedProductUpdqRow findRowByPk( MfTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfTradedProductUpdqRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static MfTradedProductUpdqDao findDaoByPk( long p_tradedProductId, String p_validForBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MfTradedProductUpdqPK pk = new MfTradedProductUpdqPK( p_tradedProductId, p_validForBizDate );
        MfTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MfTradedProductUpdqPK)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static MfTradedProductUpdqDao findDaoByPk( MfTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link MfTradedProductUpdqDao}に紐付く{@@link MfTradedProductUpdqRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link MfTradedProductUpdqDao}と外部キーの関係にある{@@link MarketRow} 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


  /** 
   * この{@@link MfTradedProductUpdqDao}に紐付く{@@link MfTradedProductUpdqRow}内で外部キーの関係をもつ{@@link TradedProductRow}を検索します。 
   * 
   * @@return {@@link MfTradedProductUpdqDao}と外部キーの関係にある{@@link TradedProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchTradedProductRowViaTradedProductId()}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public TradedProductDao fetchTradedProductDaoViaTradedProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        TradedProductPK pk = new TradedProductPK( row.getTradedProductId() );
        DataAccessObject dao = TradedProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TradedProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TradedProductDao) dao;
    }


  /** 
   * この{@@link MfTradedProductUpdqDao}に紐付く{@@link MfTradedProductUpdqRow}内で外部キーの関係をもつ{@@link MutualFundProductRow}を検索します。 
   * 
   * @@return {@@link MfTradedProductUpdqDao}と外部キーの関係にある{@@link MutualFundProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public MutualFundProductRow fetchMutualFundProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        MutualFundProductPK pk = new MutualFundProductPK( row.getProductId() );
        Row row = MutualFundProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MutualFundProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MutualFundProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundProductRowViaProductId()}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public MutualFundProductDao fetchMutualFundProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        MutualFundProductPK pk = new MutualFundProductPK( row.getProductId() );
        DataAccessObject dao = MutualFundProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MutualFundProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MutualFundProductDao) dao;
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
   * {@@link MarketRow}と外部キーの関係にある{@@link MfTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link MfTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link MfTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link MfTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MfTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MfTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MfTradedProductUpdqRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
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
   * {@@link TradedProductRow}と外部キーの関係にある{@@link MfTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link TradedProductRow}オブジェクト 
   * @@return 指定の{@@link TradedProductRow}に外部キーを持つ{@@link MfTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( row.getTradedProductId() );
    }


  /** 
   * {@@link TradedProductPK}と外部キーの関係にある{@@link MfTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link TradedProductPK}オブジェクト 
   * @@return {@@link TradedProductPK}と外部キーが一致する値を持つ{@@link MfTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( pk.traded_product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MfTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MfTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( long p_tradedProductId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MfTradedProductUpdqRow.TYPE,
            "traded_product_id=?",
            null,
            new Object[] { new Long(p_tradedProductId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for TradedProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByTradedProductId(TradedProductRow)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(TradedProductRow)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(TradedProductPK)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( pk.traded_product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(long)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( long p_tradedProductId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( p_tradedProductId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for MutualFundProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(MutualFundProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( MutualFundProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getMutualFundProductRow() );
    }


  /** 
   * {@@link MutualFundProductRow}と外部キーの関係にある{@@link MfTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MutualFundProductRow}オブジェクト 
   * @@return 指定の{@@link MutualFundProductRow}に外部キーを持つ{@@link MfTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( MutualFundProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link MutualFundProductPK}と外部キーの関係にある{@@link MfTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MutualFundProductPK}オブジェクト 
   * @@return {@@link MutualFundProductPK}と外部キーが一致する値を持つ{@@link MfTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( MutualFundProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MfTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MfTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MfTradedProductUpdqRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MutualFundProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(MutualFundProductRow)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( MutualFundProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(MutualFundProductRow)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( MutualFundProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(MutualFundProductPK)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( MutualFundProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
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
   * p_tradedProductId, p_validForBizDate, and にて指定の値から一意の{@@link MfTradedProductUpdqRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_validForBizDate 検索対象であるp_validForBizDateフィールドの値
   * 
   * @@return 引数指定のp_tradedProductId, p_validForBizDate, and の値と一致する{@@link MfTradedProductUpdqRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MfTradedProductUpdqRow findRowByTradedProductIdValidForBizDate( long p_tradedProductId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfTradedProductUpdqRow.TYPE,
            "traded_product_id=? and valid_for_biz_date=?",
            null,
            new Object[] { new Long(p_tradedProductId), p_validForBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfTradedProductUpdqDao.findRowsByTradedProductIdValidForBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTradedProductIdValidForBizDate(long, String)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static MfTradedProductUpdqDao findDaoByTradedProductIdValidForBizDate( long p_tradedProductId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradedProductIdValidForBizDate( p_tradedProductId, p_validForBizDate ) );
    }


  /** 
   * p_productId, p_marketId, p_validForBizDate, and にて指定の値から一意の{@@link MfTradedProductUpdqRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_validForBizDate 検索対象であるp_validForBizDateフィールドの値
   * 
   * @@return 引数指定のp_productId, p_marketId, p_validForBizDate, and の値と一致する{@@link MfTradedProductUpdqRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MfTradedProductUpdqRow findRowByProductIdMarketIdValidForBizDate( long p_productId, long p_marketId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfTradedProductUpdqRow.TYPE,
            "product_id=? and market_id=? and valid_for_biz_date=?",
            null,
            new Object[] { new Long(p_productId), new Long(p_marketId), p_validForBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfTradedProductUpdqDao.findRowsByProductIdMarketIdValidForBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdMarketIdValidForBizDate(long, long, String)}および{@@link #forRow(MfTradedProductUpdqRow)}を使用してください。 
   */
    public static MfTradedProductUpdqDao findDaoByProductIdMarketIdValidForBizDate( long p_productId, long p_marketId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdMarketIdValidForBizDate( p_productId, p_marketId, p_validForBizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
