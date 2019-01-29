head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignAccHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommCampaignAccHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CommCampaignAccHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see CommCampaignAccHistoryPK 
 * @@see CommCampaignAccHistoryRow 
 */
public class CommCampaignAccHistoryDao extends DataAccessObject {


  /** 
   * この{@@link CommCampaignAccHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private CommCampaignAccHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CommCampaignAccHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link CommCampaignAccHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CommCampaignAccHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CommCampaignAccHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommCampaignAccHistoryRow )
                return new CommCampaignAccHistoryDao( (CommCampaignAccHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommCampaignAccHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommCampaignAccHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CommCampaignAccHistoryRow}オブジェクト 
    */
    protected CommCampaignAccHistoryDao( CommCampaignAccHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CommCampaignAccHistoryRow}オブジェクトを取得します。
   */
    public CommCampaignAccHistoryRow getCommCampaignAccHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link CommCampaignAccHistoryRow}オブジェクトから{@@link CommCampaignAccHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link CommCampaignAccHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CommCampaignAccHistoryDao}取得のために指定の{@@link CommCampaignAccHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CommCampaignAccHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CommCampaignAccHistoryDao forRow( CommCampaignAccHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (CommCampaignAccHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommCampaignAccHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CommCampaignAccHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CommCampaignAccHistoryPK}やデータベースレコードとして挿入される{@@link CommCampaignAccHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommCampaignAccHistoryRow.TYPE );
    }


  /** 
   * {@@link CommCampaignAccHistoryRow}を一意に特定する{@@link CommCampaignAccHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CommCampaignAccHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CommCampaignAccHistoryParams}オブジェクトの主キーとして利用可能な{@@link CommCampaignAccHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CommCampaignAccHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CommCampaignAccHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_campaignId 検索対象であるp_campaignIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommCampaignAccHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommCampaignAccHistoryRow findRowByPk( long p_accountId, long p_campaignId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignAccHistoryPK pk = new CommCampaignAccHistoryPK( p_accountId, p_campaignId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCommCampaignAccHistoryPKオブジェクトから{@@link CommCampaignAccHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCommCampaignAccHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommCampaignAccHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommCampaignAccHistoryRow findRowByPk( CommCampaignAccHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommCampaignAccHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,long)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static CommCampaignAccHistoryDao findDaoByPk( long p_accountId, long p_campaignId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignAccHistoryPK pk = new CommCampaignAccHistoryPK( p_accountId, p_campaignId );
        CommCampaignAccHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CommCampaignAccHistoryPK)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static CommCampaignAccHistoryDao findDaoByPk( CommCampaignAccHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignAccHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link CommCampaignAccHistoryDao}に紐付く{@@link CommCampaignAccHistoryRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link CommCampaignAccHistoryDao}と外部キーの関係にある{@@link MainAccountRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public MainAccountRow fetchMainAccountRowViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        Row row = MainAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MainAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MainAccountRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for MainAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAccountId(MainAccountRow)}を使用してください。 
   */
    public static List findRowsByAccountId( MainAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( dao.getMainAccountRow() );
    }


  /** 
   * {@@link MainAccountRow}と外部キーの関係にある{@@link CommCampaignAccHistoryRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link CommCampaignAccHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link CommCampaignAccHistoryRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link CommCampaignAccHistoryRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link CommCampaignAccHistoryRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link CommCampaignAccHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
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
   * p_accountId, p_campaignId, and にて指定の値から一意の{@@link CommCampaignAccHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_campaignId 検索対象であるp_campaignIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_campaignId, and の値と一致する{@@link CommCampaignAccHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CommCampaignAccHistoryRow findRowByAccountIdCampaignId( long p_accountId, long p_campaignId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "account_id=? and campaign_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_campaignId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommCampaignAccHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommCampaignAccHistoryDao.findRowsByAccountIdCampaignId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdCampaignId(long, long)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static CommCampaignAccHistoryDao findDaoByAccountIdCampaignId( long p_accountId, long p_campaignId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdCampaignId( p_accountId, p_campaignId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_campaignId, and にて指定の値に一致する{@@link CommCampaignAccHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_campaignId 検索対象であるp_campaignIdフィールドの値
   * 
   * @@return 引数指定のp_campaignId, and の値と一致する{@@link CommCampaignAccHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCampaignId( long p_campaignId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "campaign_id=?",
            null,
            new Object[] { new Long(p_campaignId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCampaignId(long)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByCampaignId( long p_campaignId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCampaignId( p_campaignId ) );
    }


  /** 
   * p_appliEndDate, and にて指定の値に一致する{@@link CommCampaignAccHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_appliEndDate 検索対象であるp_appliEndDateフィールドの値
   * 
   * @@return 引数指定のp_appliEndDate, and の値と一致する{@@link CommCampaignAccHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAppliEndDate( java.sql.Timestamp p_appliEndDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "appli_end_date=?",
            null,
            new Object[] { p_appliEndDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAppliEndDate(java.sql.Timestamp)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByAppliEndDate( java.sql.Timestamp p_appliEndDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAppliEndDate( p_appliEndDate ) );
    }


  /** 
   * p_lastUpdatedTimestamp, and にて指定の値に一致する{@@link CommCampaignAccHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_lastUpdatedTimestamp 検索対象であるp_lastUpdatedTimestampフィールドの値
   * 
   * @@return 引数指定のp_lastUpdatedTimestamp, and の値と一致する{@@link CommCampaignAccHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "last_updated_timestamp=?",
            null,
            new Object[] { p_lastUpdatedTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByLastUpdatedTimestamp(java.sql.Timestamp)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByLastUpdatedTimestamp( p_lastUpdatedTimestamp ) );
    }


  /** 
   * p_appliStartDate, and にて指定の値に一致する{@@link CommCampaignAccHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のp_appliStartDate, and の値と一致する{@@link CommCampaignAccHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAppliStartDate( java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "appli_start_date=?",
            null,
            new Object[] { p_appliStartDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAppliStartDate(java.sql.Timestamp)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByAppliStartDate( java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAppliStartDate( p_appliStartDate ) );
    }


  /** 
   * p_accountCode, and にて指定の値に一致する{@@link CommCampaignAccHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_accountCode, and の値と一致する{@@link CommCampaignAccHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountCode( String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "account_code=?",
            null,
            new Object[] { p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountCode(String)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountCode( String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountCode( p_accountCode ) );
    }


  /** 
   * p_accOpenKindDiv, and にて指定の値に一致する{@@link CommCampaignAccHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accOpenKindDiv 検索対象であるp_accOpenKindDivフィールドの値
   * 
   * @@return 引数指定のp_accOpenKindDiv, and の値と一致する{@@link CommCampaignAccHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccOpenKindDiv( String p_accOpenKindDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "acc_open_kind_div=?",
            null,
            new Object[] { p_accOpenKindDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccOpenKindDiv(String)}および{@@link #forRow(CommCampaignAccHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccOpenKindDiv( String p_accOpenKindDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccOpenKindDiv( p_accOpenKindDiv ) );
    }

}
@
