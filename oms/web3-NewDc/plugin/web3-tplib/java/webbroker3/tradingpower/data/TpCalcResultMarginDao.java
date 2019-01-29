head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultMarginDao.java;


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
 * {@@link TpCalcResultMarginDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TpCalcResultMarginRow}インスタンスへ関連付けることができます。 
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
 * @@see TpCalcResultMarginPK 
 * @@see TpCalcResultMarginRow 
 */
public class TpCalcResultMarginDao extends DataAccessObject {


  /** 
   * この{@@link TpCalcResultMarginDao}に関連する型指定のRowオブジェクト 
   */
    private TpCalcResultMarginRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TpCalcResultMarginRow}と仮定される{@@link DataAccessObject}から新たに{@@link TpCalcResultMarginDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TpCalcResultMarginDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TpCalcResultMarginRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpCalcResultMarginRow )
                return new TpCalcResultMarginDao( (TpCalcResultMarginRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpCalcResultMarginRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpCalcResultMarginRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TpCalcResultMarginRow}オブジェクト 
    */
    protected TpCalcResultMarginDao( TpCalcResultMarginRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TpCalcResultMarginRow}オブジェクトを取得します。
   */
    public TpCalcResultMarginRow getTpCalcResultMarginRow() {
        return row;
    }


  /** 
   * 指定の{@@link TpCalcResultMarginRow}オブジェクトから{@@link TpCalcResultMarginDao}オブジェクトを作成します。 
   * これは実際の{@@link TpCalcResultMarginRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TpCalcResultMarginDao}取得のために指定の{@@link TpCalcResultMarginRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TpCalcResultMarginDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TpCalcResultMarginDao forRow( TpCalcResultMarginRow row ) throws java.lang.IllegalArgumentException {
        return (TpCalcResultMarginDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpCalcResultMarginRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TpCalcResultMarginRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TpCalcResultMarginPK}やデータベースレコードとして挿入される{@@link TpCalcResultMarginParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpCalcResultMarginRow.TYPE );
    }


  /** 
   * {@@link TpCalcResultMarginRow}を一意に特定する{@@link TpCalcResultMarginPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TpCalcResultMarginRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TpCalcResultMarginParams}オブジェクトの主キーとして利用可能な{@@link TpCalcResultMarginPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TpCalcResultMarginPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpCalcResultMarginPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TpCalcResultMarginRow}オブジェクトを検索します。 
   * 
   * @@param p_calcResultMarginId 検索対象であるp_calcResultMarginIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TpCalcResultMarginRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TpCalcResultMarginRow findRowByPk( long p_calcResultMarginId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultMarginPK pk = new TpCalcResultMarginPK( p_calcResultMarginId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTpCalcResultMarginPKオブジェクトから{@@link TpCalcResultMarginRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTpCalcResultMarginPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TpCalcResultMarginRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TpCalcResultMarginRow findRowByPk( TpCalcResultMarginPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpCalcResultMarginRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
   */
    public static TpCalcResultMarginDao findDaoByPk( long p_calcResultMarginId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultMarginPK pk = new TpCalcResultMarginPK( p_calcResultMarginId );
        TpCalcResultMarginRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TpCalcResultMarginPK)}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
   */
    public static TpCalcResultMarginDao findDaoByPk( TpCalcResultMarginPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultMarginRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link TpCalcResultMarginDao}に紐付く{@@link TpCalcResultMarginRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link TpCalcResultMarginDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
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
   * この{@@link TpCalcResultMarginDao}に関連する{@@link TpCalcResultMarginRow}の外部キーがある{@@link TpCalcResultMarginDetailRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link TpCalcResultMarginDao}に関連する{@@link TpCalcResultMarginRow}の外部キーがある{@@link TpCalcResultMarginDetailRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTpCalcResultMarginDetailRowsByCalcResultMarginId() throws DataNetworkException, DataQueryException  {
        return TpCalcResultMarginDetailDao.findRowsByCalcResultMarginId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTpCalcResultMarginDetailRowsByCalcResultMarginId()}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
   */
    public List fetchTpCalcResultMarginDetailDaosByCalcResultMarginId() throws DataNetworkException, DataQueryException  {
        return TpCalcResultMarginDetailDao.findDaosByCalcResultMarginId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTpCalcResultMarginDetailRowsByCalcResultMarginId()}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
   */
    public List fetchTpCalcResultMarginDetailDaosCalcResultMarginId() throws DataNetworkException, DataQueryException  {
        return fetchTpCalcResultMarginDetailDaosByCalcResultMarginId();
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link TpCalcResultMarginRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link TpCalcResultMarginRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link TpCalcResultMarginRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link TpCalcResultMarginRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TpCalcResultMarginRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TpCalcResultMarginRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultMarginRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
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
   * p_accountId, p_createdTimestamp, and にて指定の値に一致する{@@link TpCalcResultMarginRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_createdTimestamp 検索対象であるp_createdTimestampフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_createdTimestamp, and の値と一致する{@@link TpCalcResultMarginRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdCreatedTimestamp( long p_accountId, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultMarginRow.TYPE,
            "account_id=? and created_timestamp=?",
            null,
            new Object[] { new Long(p_accountId), p_createdTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdCreatedTimestamp(long, java.sql.Timestamp)}および{@@link #forRow(TpCalcResultMarginRow)}を使用してください。 
   */
    public static List findDaosByAccountIdCreatedTimestamp( long p_accountId, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdCreatedTimestamp( p_accountId, p_createdTimestamp ) );
    }

}
@
