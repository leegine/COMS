head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultEquityDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpower.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TpCalcResultEquityDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TpCalcResultEquityRow}インスタンスへ関連付けることができます。 
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
 * @@see TpCalcResultEquityPK 
 * @@see TpCalcResultEquityRow 
 */
public class TpCalcResultEquityDao extends DataAccessObject {


  /** 
   * この{@@link TpCalcResultEquityDao}に関連する型指定のRowオブジェクト 
   */
    private TpCalcResultEquityRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TpCalcResultEquityRow}と仮定される{@@link DataAccessObject}から新たに{@@link TpCalcResultEquityDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TpCalcResultEquityDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TpCalcResultEquityRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpCalcResultEquityRow )
                return new TpCalcResultEquityDao( (TpCalcResultEquityRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpCalcResultEquityRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpCalcResultEquityRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TpCalcResultEquityRow}オブジェクト 
    */
    protected TpCalcResultEquityDao( TpCalcResultEquityRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TpCalcResultEquityRow}オブジェクトを取得します。
   */
    public TpCalcResultEquityRow getTpCalcResultEquityRow() {
        return row;
    }


  /** 
   * 指定の{@@link TpCalcResultEquityRow}オブジェクトから{@@link TpCalcResultEquityDao}オブジェクトを作成します。 
   * これは実際の{@@link TpCalcResultEquityRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TpCalcResultEquityDao}取得のために指定の{@@link TpCalcResultEquityRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TpCalcResultEquityDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TpCalcResultEquityDao forRow( TpCalcResultEquityRow row ) throws java.lang.IllegalArgumentException {
        return (TpCalcResultEquityDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpCalcResultEquityRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TpCalcResultEquityRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TpCalcResultEquityPK}やデータベースレコードとして挿入される{@@link TpCalcResultEquityParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpCalcResultEquityRow.TYPE );
    }


  /** 
   * {@@link TpCalcResultEquityRow}を一意に特定する{@@link TpCalcResultEquityPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TpCalcResultEquityRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TpCalcResultEquityParams}オブジェクトの主キーとして利用可能な{@@link TpCalcResultEquityPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TpCalcResultEquityPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpCalcResultEquityPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TpCalcResultEquityRow}オブジェクトを検索します。 
   * 
   * @@param p_calcResultEquityId 検索対象であるp_calcResultEquityIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TpCalcResultEquityRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TpCalcResultEquityRow findRowByPk( long p_calcResultEquityId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultEquityPK pk = new TpCalcResultEquityPK( p_calcResultEquityId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTpCalcResultEquityPKオブジェクトから{@@link TpCalcResultEquityRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTpCalcResultEquityPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TpCalcResultEquityRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TpCalcResultEquityRow findRowByPk( TpCalcResultEquityPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpCalcResultEquityRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
   */
    public static TpCalcResultEquityDao findDaoByPk( long p_calcResultEquityId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultEquityPK pk = new TpCalcResultEquityPK( p_calcResultEquityId );
        TpCalcResultEquityRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TpCalcResultEquityPK)}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
   */
    public static TpCalcResultEquityDao findDaoByPk( TpCalcResultEquityPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultEquityRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link TpCalcResultEquityDao}に紐付く{@@link TpCalcResultEquityRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link TpCalcResultEquityDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link TpCalcResultEquityDao}に関連する{@@link TpCalcResultEquityRow}の外部キーがある{@@link TpCalcResultEquityDetailRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link TpCalcResultEquityDao}に関連する{@@link TpCalcResultEquityRow}の外部キーがある{@@link TpCalcResultEquityDetailRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTpCalcResultEquityDetailRowsByCalcResultEquityId() throws DataNetworkException, DataQueryException  {
        return TpCalcResultEquityDetailDao.findRowsByCalcResultEquityId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTpCalcResultEquityDetailRowsByCalcResultEquityId()}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
   */
    public List fetchTpCalcResultEquityDetailDaosByCalcResultEquityId() throws DataNetworkException, DataQueryException  {
        return TpCalcResultEquityDetailDao.findDaosByCalcResultEquityId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTpCalcResultEquityDetailRowsByCalcResultEquityId()}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
   */
    public List fetchTpCalcResultEquityDetailDaosCalcResultEquityId() throws DataNetworkException, DataQueryException  {
        return fetchTpCalcResultEquityDetailDaosByCalcResultEquityId();
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link TpCalcResultEquityRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link TpCalcResultEquityRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link TpCalcResultEquityRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link TpCalcResultEquityRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TpCalcResultEquityRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TpCalcResultEquityRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultEquityRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_createdTimestamp, and にて指定の値に一致する{@@link TpCalcResultEquityRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_createdTimestamp 検索対象であるp_createdTimestampフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_createdTimestamp, and の値と一致する{@@link TpCalcResultEquityRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdCreatedTimestamp( long p_accountId, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultEquityRow.TYPE,
            "account_id=? and created_timestamp=?",
            null,
            new Object[] { new Long(p_accountId), p_createdTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdCreatedTimestamp(long, java.sql.Timestamp)}および{@@link #forRow(TpCalcResultEquityRow)}を使用してください。 
   */
    public static List findDaosByAccountIdCreatedTimestamp( long p_accountId, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdCreatedTimestamp( p_accountId, p_createdTimestamp ) );
    }

}
@
