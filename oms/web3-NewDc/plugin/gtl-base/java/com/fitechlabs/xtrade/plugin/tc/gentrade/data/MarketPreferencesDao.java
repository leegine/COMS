head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.37.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketPreferencesDao.java;


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
 * {@@link MarketPreferencesDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MarketPreferencesRow}インスタンスへ関連付けることができます。 
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
 * @@see MarketPreferencesPK 
 * @@see MarketPreferencesRow 
 */
public class MarketPreferencesDao extends DataAccessObject {


  /** 
   * この{@@link MarketPreferencesDao}に関連する型指定のRowオブジェクト 
   */
    private MarketPreferencesRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MarketPreferencesRow}と仮定される{@@link DataAccessObject}から新たに{@@link MarketPreferencesDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MarketPreferencesDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MarketPreferencesRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MarketPreferencesRow )
                return new MarketPreferencesDao( (MarketPreferencesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MarketPreferencesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MarketPreferencesRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MarketPreferencesRow}オブジェクト 
    */
    protected MarketPreferencesDao( MarketPreferencesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MarketPreferencesRow}オブジェクトを取得します。
   */
    public MarketPreferencesRow getMarketPreferencesRow() {
        return row;
    }


  /** 
   * 指定の{@@link MarketPreferencesRow}オブジェクトから{@@link MarketPreferencesDao}オブジェクトを作成します。 
   * これは実際の{@@link MarketPreferencesRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MarketPreferencesDao}取得のために指定の{@@link MarketPreferencesRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MarketPreferencesDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MarketPreferencesDao forRow( MarketPreferencesRow row ) throws java.lang.IllegalArgumentException {
        return (MarketPreferencesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MarketPreferencesRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MarketPreferencesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MarketPreferencesPK}やデータベースレコードとして挿入される{@@link MarketPreferencesParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MarketPreferencesRow.TYPE );
    }


  /** 
   * {@@link MarketPreferencesRow}を一意に特定する{@@link MarketPreferencesPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MarketPreferencesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MarketPreferencesParams}オブジェクトの主キーとして利用可能な{@@link MarketPreferencesPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MarketPreferencesPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MarketPreferencesRow}オブジェクトを検索します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * @@param p_nameSerialNo 検索対象であるp_nameSerialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketPreferencesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketPreferencesRow findRowByPk( long p_marketId, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketPreferencesPK pk = new MarketPreferencesPK( p_marketId, p_name, p_nameSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMarketPreferencesPKオブジェクトから{@@link MarketPreferencesRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMarketPreferencesPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketPreferencesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketPreferencesRow findRowByPk( MarketPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MarketPreferencesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,int)}および{@@link #forRow(MarketPreferencesRow)}を使用してください。 
   */
    public static MarketPreferencesDao findDaoByPk( long p_marketId, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketPreferencesPK pk = new MarketPreferencesPK( p_marketId, p_name, p_nameSerialNo );
        MarketPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MarketPreferencesPK)}および{@@link #forRow(MarketPreferencesRow)}を使用してください。 
   */
    public static MarketPreferencesDao findDaoByPk( MarketPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link MarketPreferencesDao}に紐付く{@@link MarketPreferencesRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link MarketPreferencesDao}と外部キーの関係にある{@@link MarketRow} 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(MarketPreferencesRow)}を使用してください。 
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
   * {@@link MarketRow}と外部キーの関係にある{@@link MarketPreferencesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link MarketPreferencesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link MarketPreferencesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link MarketPreferencesRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MarketPreferencesRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MarketPreferencesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MarketPreferencesRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(MarketPreferencesRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(MarketPreferencesRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(MarketPreferencesRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(MarketPreferencesRow)}を使用してください。 
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
   * p_marketId, p_name, p_nameSerialNo, and にて指定の値から一意の{@@link MarketPreferencesRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * @@param p_nameSerialNo 検索対象であるp_nameSerialNoフィールドの値
   * 
   * @@return 引数指定のp_marketId, p_name, p_nameSerialNo, and の値と一致する{@@link MarketPreferencesRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MarketPreferencesRow findRowByMarketIdNameNameSerialNo( long p_marketId, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketPreferencesRow.TYPE,
            "market_id=? and name=? and name_serial_no=?",
            null,
            new Object[] { new Long(p_marketId), p_name, new Integer(p_nameSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketPreferencesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketPreferencesDao.findRowsByMarketIdNameNameSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMarketIdNameNameSerialNo(long, String, int)}および{@@link #forRow(MarketPreferencesRow)}を使用してください。 
   */
    public static MarketPreferencesDao findDaoByMarketIdNameNameSerialNo( long p_marketId, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketIdNameNameSerialNo( p_marketId, p_name, p_nameSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
