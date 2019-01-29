head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.37.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketCalendarDao.java;


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
 * {@@link MarketCalendarDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MarketCalendarRow}インスタンスへ関連付けることができます。 
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
 * @@see MarketCalendarPK 
 * @@see MarketCalendarRow 
 */
public class MarketCalendarDao extends DataAccessObject {


  /** 
   * この{@@link MarketCalendarDao}に関連する型指定のRowオブジェクト 
   */
    private MarketCalendarRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MarketCalendarRow}と仮定される{@@link DataAccessObject}から新たに{@@link MarketCalendarDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MarketCalendarDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MarketCalendarRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MarketCalendarRow )
                return new MarketCalendarDao( (MarketCalendarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MarketCalendarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MarketCalendarRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MarketCalendarRow}オブジェクト 
    */
    protected MarketCalendarDao( MarketCalendarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MarketCalendarRow}オブジェクトを取得します。
   */
    public MarketCalendarRow getMarketCalendarRow() {
        return row;
    }


  /** 
   * 指定の{@@link MarketCalendarRow}オブジェクトから{@@link MarketCalendarDao}オブジェクトを作成します。 
   * これは実際の{@@link MarketCalendarRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MarketCalendarDao}取得のために指定の{@@link MarketCalendarRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MarketCalendarDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MarketCalendarDao forRow( MarketCalendarRow row ) throws java.lang.IllegalArgumentException {
        return (MarketCalendarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MarketCalendarRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MarketCalendarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MarketCalendarPK}やデータベースレコードとして挿入される{@@link MarketCalendarParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MarketCalendarRow.TYPE );
    }


  /** 
   * {@@link MarketCalendarRow}を一意に特定する{@@link MarketCalendarPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MarketCalendarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MarketCalendarParams}オブジェクトの主キーとして利用可能な{@@link MarketCalendarPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MarketCalendarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MarketCalendarRow}オブジェクトを検索します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_tradeDate 検索対象であるp_tradeDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketCalendarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketCalendarRow findRowByPk( long p_marketId, java.sql.Timestamp p_tradeDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketCalendarPK pk = new MarketCalendarPK( p_marketId, p_tradeDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMarketCalendarPKオブジェクトから{@@link MarketCalendarRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMarketCalendarPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketCalendarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketCalendarRow findRowByPk( MarketCalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MarketCalendarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,java.sql.Timestamp)}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
   */
    public static MarketCalendarDao findDaoByPk( long p_marketId, java.sql.Timestamp p_tradeDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketCalendarPK pk = new MarketCalendarPK( p_marketId, p_tradeDate );
        MarketCalendarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MarketCalendarPK)}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
   */
    public static MarketCalendarDao findDaoByPk( MarketCalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketCalendarRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link MarketCalendarDao}に紐付く{@@link MarketCalendarRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link MarketCalendarDao}と外部キーの関係にある{@@link MarketRow} 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
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
    // Find Rows given foreign key values for Market
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByMarketId(MarketRow)}を使用してください。 
   */
    public static List findRowsByMarketId( MarketDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( dao.getMarketRow() );
    }


  /** 
   * {@@link MarketRow}と外部キーの関係にある{@@link MarketCalendarRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link MarketCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link MarketCalendarRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link MarketCalendarRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MarketCalendarRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MarketCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MarketCalendarRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
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
   * p_marketId, p_tradeDate, and にて指定の値から一意の{@@link MarketCalendarRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_tradeDate 検索対象であるp_tradeDateフィールドの値
   * 
   * @@return 引数指定のp_marketId, p_tradeDate, and の値と一致する{@@link MarketCalendarRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MarketCalendarRow findRowByMarketIdTradeDate( long p_marketId, java.sql.Timestamp p_tradeDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketCalendarRow.TYPE,
            "market_id=? and trade_date=?",
            null,
            new Object[] { new Long(p_marketId), p_tradeDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketCalendarRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketCalendarDao.findRowsByMarketIdTradeDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMarketIdTradeDate(long, java.sql.Timestamp)}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
   */
    public static MarketCalendarDao findDaoByMarketIdTradeDate( long p_marketId, java.sql.Timestamp p_tradeDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketIdTradeDate( p_marketId, p_tradeDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_marketId, p_holidayFlag, and にて指定の値に一致する{@@link MarketCalendarRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_holidayFlag 検索対象であるp_holidayFlagフィールドの値
   * 
   * @@return 引数指定のp_marketId, p_holidayFlag, and の値と一致する{@@link MarketCalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketIdHolidayFlag( long p_marketId, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_holidayFlag ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MarketCalendarRow.TYPE,
            "market_id=? and holiday_flag=?",
            null,
            new Object[] { new Long(p_marketId), p_holidayFlag } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketIdHolidayFlag(long, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum)}および{@@link #forRow(MarketCalendarRow)}を使用してください。 
   */
    public static List findDaosByMarketIdHolidayFlag( long p_marketId, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_holidayFlag ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMarketIdHolidayFlag( p_marketId, p_holidayFlag ) );
    }

}
@
