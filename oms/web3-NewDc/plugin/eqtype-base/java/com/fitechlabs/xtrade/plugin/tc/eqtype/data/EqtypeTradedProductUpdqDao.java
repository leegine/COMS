head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.37.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeTradedProductUpdqDao.java;


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
 * {@@link EqtypeTradedProductUpdqDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EqtypeTradedProductUpdqRow}インスタンスへ関連付けることができます。 
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
 * @@see EqtypeTradedProductUpdqPK 
 * @@see EqtypeTradedProductUpdqRow 
 */
public class EqtypeTradedProductUpdqDao extends DataAccessObject {


  /** 
   * この{@@link EqtypeTradedProductUpdqDao}に関連する型指定のRowオブジェクト 
   */
    private EqtypeTradedProductUpdqRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EqtypeTradedProductUpdqRow}と仮定される{@@link DataAccessObject}から新たに{@@link EqtypeTradedProductUpdqDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EqtypeTradedProductUpdqDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EqtypeTradedProductUpdqRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeTradedProductUpdqRow )
                return new EqtypeTradedProductUpdqDao( (EqtypeTradedProductUpdqRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeTradedProductUpdqRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeTradedProductUpdqRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EqtypeTradedProductUpdqRow}オブジェクト 
    */
    protected EqtypeTradedProductUpdqDao( EqtypeTradedProductUpdqRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EqtypeTradedProductUpdqRow}オブジェクトを取得します。
   */
    public EqtypeTradedProductUpdqRow getEqtypeTradedProductUpdqRow() {
        return row;
    }


  /** 
   * 指定の{@@link EqtypeTradedProductUpdqRow}オブジェクトから{@@link EqtypeTradedProductUpdqDao}オブジェクトを作成します。 
   * これは実際の{@@link EqtypeTradedProductUpdqRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EqtypeTradedProductUpdqDao}取得のために指定の{@@link EqtypeTradedProductUpdqRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EqtypeTradedProductUpdqDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EqtypeTradedProductUpdqDao forRow( EqtypeTradedProductUpdqRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeTradedProductUpdqDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeTradedProductUpdqRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EqtypeTradedProductUpdqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EqtypeTradedProductUpdqPK}やデータベースレコードとして挿入される{@@link EqtypeTradedProductUpdqParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeTradedProductUpdqRow.TYPE );
    }


  /** 
   * {@@link EqtypeTradedProductUpdqRow}を一意に特定する{@@link EqtypeTradedProductUpdqPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EqtypeTradedProductUpdqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EqtypeTradedProductUpdqParams}オブジェクトの主キーとして利用可能な{@@link EqtypeTradedProductUpdqPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EqtypeTradedProductUpdqPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EqtypeTradedProductUpdqRow}オブジェクトを検索します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_validUntilBizDate 検索対象であるp_validUntilBizDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeTradedProductUpdqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeTradedProductUpdqRow findRowByPk( long p_tradedProductId, String p_validUntilBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeTradedProductUpdqPK pk = new EqtypeTradedProductUpdqPK( p_tradedProductId, p_validUntilBizDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEqtypeTradedProductUpdqPKオブジェクトから{@@link EqtypeTradedProductUpdqRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEqtypeTradedProductUpdqPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeTradedProductUpdqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeTradedProductUpdqRow findRowByPk( EqtypeTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeTradedProductUpdqRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static EqtypeTradedProductUpdqDao findDaoByPk( long p_tradedProductId, String p_validUntilBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeTradedProductUpdqPK pk = new EqtypeTradedProductUpdqPK( p_tradedProductId, p_validUntilBizDate );
        EqtypeTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EqtypeTradedProductUpdqPK)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static EqtypeTradedProductUpdqDao findDaoByPk( EqtypeTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeTradedProductUpdqDao}に紐付く{@@link EqtypeTradedProductUpdqRow}内で外部キーの関係をもつ{@@link TradedProductRow}を検索します。 
   * 
   * @@return {@@link EqtypeTradedProductUpdqDao}と外部キーの関係にある{@@link TradedProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchTradedProductRowViaTradedProductId()}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public TradedProductDao fetchTradedProductDaoViaTradedProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        TradedProductPK pk = new TradedProductPK( row.getTradedProductId() );
        DataAccessObject dao = TradedProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TradedProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TradedProductDao) dao;
    }


  /** 
   * この{@@link EqtypeTradedProductUpdqDao}に紐付く{@@link EqtypeTradedProductUpdqRow}内で外部キーの関係をもつ{@@link EqtypeProductRow}を検索します。 
   * 
   * @@return {@@link EqtypeTradedProductUpdqDao}と外部キーの関係にある{@@link EqtypeProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public EqtypeProductRow fetchEqtypeProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeProductPK pk = new EqtypeProductPK( row.getProductId() );
        Row row = EqtypeProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof EqtypeProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (EqtypeProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeProductRowViaProductId()}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public EqtypeProductDao fetchEqtypeProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeProductPK pk = new EqtypeProductPK( row.getProductId() );
        DataAccessObject dao = EqtypeProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeProductDao) dao;
    }


  /** 
   * この{@@link EqtypeTradedProductUpdqDao}に紐付く{@@link EqtypeTradedProductUpdqRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link EqtypeTradedProductUpdqDao}と外部キーの関係にある{@@link MarketRow} 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
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
   * {@@link TradedProductRow}と外部キーの関係にある{@@link EqtypeTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link TradedProductRow}オブジェクト 
   * @@return 指定の{@@link TradedProductRow}に外部キーを持つ{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( row.getTradedProductId() );
    }


  /** 
   * {@@link TradedProductPK}と外部キーの関係にある{@@link EqtypeTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link TradedProductPK}オブジェクト 
   * @@return {@@link TradedProductPK}と外部キーが一致する値を持つ{@@link EqtypeTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( pk.traded_product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductId( long p_tradedProductId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "traded_product_id=?",
            null,
            new Object[] { new Long(p_tradedProductId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for TradedProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByTradedProductId(TradedProductRow)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(TradedProductRow)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(TradedProductPK)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( pk.traded_product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductId(long)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductId( long p_tradedProductId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( p_tradedProductId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for EqtypeProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(EqtypeProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( EqtypeProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getEqtypeProductRow() );
    }


  /** 
   * {@@link EqtypeProductRow}と外部キーの関係にある{@@link EqtypeTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeProductRow}オブジェクト 
   * @@return 指定の{@@link EqtypeProductRow}に外部キーを持つ{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( EqtypeProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link EqtypeProductPK}と外部キーの関係にある{@@link EqtypeTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeProductPK}オブジェクト 
   * @@return {@@link EqtypeProductPK}と外部キーが一致する値を持つ{@@link EqtypeTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( EqtypeProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(EqtypeProductRow)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( EqtypeProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(EqtypeProductRow)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( EqtypeProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(EqtypeProductPK)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( EqtypeProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
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
   * {@@link MarketRow}と外部キーの関係にある{@@link EqtypeTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link EqtypeTradedProductUpdqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link EqtypeTradedProductUpdqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
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
   * p_tradedProductId, p_validUntilBizDate, and にて指定の値から一意の{@@link EqtypeTradedProductUpdqRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_validUntilBizDate 検索対象であるp_validUntilBizDateフィールドの値
   * 
   * @@return 引数指定のp_tradedProductId, p_validUntilBizDate, and の値と一致する{@@link EqtypeTradedProductUpdqRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeTradedProductUpdqRow findRowByTradedProductIdValidUntilBizDate( long p_tradedProductId, String p_validUntilBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "traded_product_id=? and valid_until_biz_date=?",
            null,
            new Object[] { new Long(p_tradedProductId), p_validUntilBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeTradedProductUpdqDao.findRowsByTradedProductIdValidUntilBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTradedProductIdValidUntilBizDate(long, String)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static EqtypeTradedProductUpdqDao findDaoByTradedProductIdValidUntilBizDate( long p_tradedProductId, String p_validUntilBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradedProductIdValidUntilBizDate( p_tradedProductId, p_validUntilBizDate ) );
    }


  /** 
   * p_productId, p_marketId, p_validUntilBizDate, and にて指定の値から一意の{@@link EqtypeTradedProductUpdqRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_validUntilBizDate 検索対象であるp_validUntilBizDateフィールドの値
   * 
   * @@return 引数指定のp_productId, p_marketId, p_validUntilBizDate, and の値と一致する{@@link EqtypeTradedProductUpdqRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeTradedProductUpdqRow findRowByProductIdMarketIdValidUntilBizDate( long p_productId, long p_marketId, String p_validUntilBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "product_id=? and market_id=? and valid_until_biz_date=?",
            null,
            new Object[] { new Long(p_productId), new Long(p_marketId), p_validUntilBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeTradedProductUpdqDao.findRowsByProductIdMarketIdValidUntilBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdMarketIdValidUntilBizDate(long, long, String)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static EqtypeTradedProductUpdqDao findDaoByProductIdMarketIdValidUntilBizDate( long p_productId, long p_marketId, String p_validUntilBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdMarketIdValidUntilBizDate( p_productId, p_marketId, p_validUntilBizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_tradedProductId, p_institutionCode, p_productId, p_marketId, and にて指定の値に一致する{@@link EqtypeTradedProductUpdqRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_tradedProductId 検索対象であるp_tradedProductIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 引数指定のp_tradedProductId, p_institutionCode, p_productId, p_marketId, and の値と一致する{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTradedProductIdInstitutionCodeProductIdMarketId( long p_tradedProductId, String p_institutionCode, long p_productId, long p_marketId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "traded_product_id=? and institution_code=? and product_id=? and market_id=?",
            null,
            new Object[] { new Long(p_tradedProductId), p_institutionCode, new Long(p_productId), new Long(p_marketId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTradedProductIdInstitutionCodeProductIdMarketId(long, String, long, long)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByTradedProductIdInstitutionCodeProductIdMarketId( long p_tradedProductId, String p_institutionCode, long p_productId, long p_marketId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTradedProductIdInstitutionCodeProductIdMarketId( p_tradedProductId, p_institutionCode, p_productId, p_marketId ) );
    }


  /** 
   * p_institutionCode, p_productId, p_validUntilBizDate, and にて指定の値に一致する{@@link EqtypeTradedProductUpdqRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_validUntilBizDate 検索対象であるp_validUntilBizDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productId, p_validUntilBizDate, and の値と一致する{@@link EqtypeTradedProductUpdqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeProductIdValidUntilBizDate( String p_institutionCode, long p_productId, String p_validUntilBizDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "institution_code=? and product_id=? and valid_until_biz_date=?",
            null,
            new Object[] { p_institutionCode, new Long(p_productId), p_validUntilBizDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeProductIdValidUntilBizDate(String, long, String)}および{@@link #forRow(EqtypeTradedProductUpdqRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeProductIdValidUntilBizDate( String p_institutionCode, long p_productId, String p_validUntilBizDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductIdValidUntilBizDate( p_institutionCode, p_productId, p_validUntilBizDate ) );
    }

}
@
