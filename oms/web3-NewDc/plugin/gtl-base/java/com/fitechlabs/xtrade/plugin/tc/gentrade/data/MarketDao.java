head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.38.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketDao.java;


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
 * {@@link MarketDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MarketRow}インスタンスへ関連付けることができます。 
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
 * @@see MarketPK 
 * @@see MarketRow 
 */
public class MarketDao extends DataAccessObject {


  /** 
   * この{@@link MarketDao}に関連する型指定のRowオブジェクト 
   */
    private MarketRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MarketRow}と仮定される{@@link DataAccessObject}から新たに{@@link MarketDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MarketDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MarketRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MarketRow )
                return new MarketDao( (MarketRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MarketRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MarketRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MarketRow}オブジェクト 
    */
    protected MarketDao( MarketRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MarketRow}オブジェクトを取得します。
   */
    public MarketRow getMarketRow() {
        return row;
    }


  /** 
   * 指定の{@@link MarketRow}オブジェクトから{@@link MarketDao}オブジェクトを作成します。 
   * これは実際の{@@link MarketRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MarketDao}取得のために指定の{@@link MarketRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MarketDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MarketDao forRow( MarketRow row ) throws java.lang.IllegalArgumentException {
        return (MarketDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MarketRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MarketRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MarketPK}やデータベースレコードとして挿入される{@@link MarketParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MarketRow.TYPE );
    }


  /** 
   * {@@link MarketRow}を一意に特定する{@@link MarketPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MarketRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MarketParams}オブジェクトの主キーとして利用可能な{@@link MarketPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MarketPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new MarketPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MarketRow}オブジェクトを検索します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketRow findRowByPk( long p_marketId ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketPK pk = new MarketPK( p_marketId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMarketPKオブジェクトから{@@link MarketRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMarketPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketRow findRowByPk( MarketPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MarketRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public static MarketDao findDaoByPk( long p_marketId ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketPK pk = new MarketPK( p_marketId );
        MarketRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MarketPK)}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public static MarketDao findDaoByPk( MarketPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link TradedProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link TradedProductRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTradedProductRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchTradedProductDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchTradedProductDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductDaosByMarketId();
    }


  /** 
   * この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link TickValuesDefsRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link TickValuesDefsRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTickValuesDefsRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return TickValuesDefsDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTickValuesDefsRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchTickValuesDefsDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return TickValuesDefsDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTickValuesDefsRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchTickValuesDefsDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchTickValuesDefsDaosByMarketId();
    }


  /** 
   * この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link MarketCalendarRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link MarketCalendarRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchMarketCalendarRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return MarketCalendarDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMarketCalendarRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchMarketCalendarDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return MarketCalendarDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMarketCalendarRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchMarketCalendarDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchMarketCalendarDaosByMarketId();
    }


  /** 
   * この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link TradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link TradedProductUpdqRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTradedProductUpdqRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductUpdqRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchTradedProductUpdqDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductUpdqRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchTradedProductUpdqDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductUpdqDaosByMarketId();
    }


  /** 
   * この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link MarketPreferencesRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link MarketPreferencesRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchMarketPreferencesRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return MarketPreferencesDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMarketPreferencesRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchMarketPreferencesDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return MarketPreferencesDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMarketPreferencesRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchMarketPreferencesDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchMarketPreferencesDaosByMarketId();
    }


  /** 
   * この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link TradedProductCalendarRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link TradedProductCalendarRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTradedProductCalendarRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductCalendarRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchTradedProductCalendarDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductCalendarRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchTradedProductCalendarDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductCalendarDaosByMarketId();
    }


  /** 
   * この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link LimitPriceRangeDefsRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MarketDao}に関連する{@@link MarketRow}の外部キーがある{@@link LimitPriceRangeDefsRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchLimitPriceRangeDefsRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return LimitPriceRangeDefsDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchLimitPriceRangeDefsRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchLimitPriceRangeDefsDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return LimitPriceRangeDefsDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchLimitPriceRangeDefsRowsByMarketId()}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public List fetchLimitPriceRangeDefsDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchLimitPriceRangeDefsDaosByMarketId();
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
   * p_marketId, and にて指定の値から一意の{@@link MarketRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 引数指定のp_marketId, and の値と一致する{@@link MarketRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MarketRow findRowByMarketId( long p_marketId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketDao.findRowsByMarketId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMarketId(long)}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public static MarketDao findDaoByMarketId( long p_marketId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketId( p_marketId ) );
    }


  /** 
   * p_institutionCode, p_marketCode, and にて指定の値から一意の{@@link MarketRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_marketCode, and の値と一致する{@@link MarketRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MarketRow findRowByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketRow.TYPE,
            "institution_code=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_marketCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketDao.findRowsByInstitutionCodeMarketCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeMarketCode(String, String)}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public static MarketDao findDaoByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCode( p_institutionCode, p_marketCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_marketId, p_institutionCode, p_marketCode, and にて指定の値に一致する{@@link MarketRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のp_marketId, p_institutionCode, p_marketCode, and の値と一致する{@@link MarketRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketIdInstitutionCodeMarketCode( long p_marketId, String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MarketRow.TYPE,
            "market_id=? and institution_code=? and market_code=?",
            null,
            new Object[] { new Long(p_marketId), p_institutionCode, p_marketCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketIdInstitutionCodeMarketCode(long, String, String)}および{@@link #forRow(MarketRow)}を使用してください。 
   */
    public static List findDaosByMarketIdInstitutionCodeMarketCode( long p_marketId, String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMarketIdInstitutionCodeMarketCode( p_marketId, p_institutionCode, p_marketCode ) );
    }

}
@
