head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCashBalanceFrgnDao.java;


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
 * {@@link TpCashBalanceFrgnDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TpCashBalanceFrgnRow}インスタンスへ関連付けることができます。 
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
 * @@see TpCashBalanceFrgnPK 
 * @@see TpCashBalanceFrgnRow 
 */
public class TpCashBalanceFrgnDao extends DataAccessObject {


  /** 
   * この{@@link TpCashBalanceFrgnDao}に関連する型指定のRowオブジェクト 
   */
    private TpCashBalanceFrgnRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TpCashBalanceFrgnRow}と仮定される{@@link DataAccessObject}から新たに{@@link TpCashBalanceFrgnDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TpCashBalanceFrgnDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TpCashBalanceFrgnRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpCashBalanceFrgnRow )
                return new TpCashBalanceFrgnDao( (TpCashBalanceFrgnRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpCashBalanceFrgnRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpCashBalanceFrgnRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TpCashBalanceFrgnRow}オブジェクト 
    */
    protected TpCashBalanceFrgnDao( TpCashBalanceFrgnRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TpCashBalanceFrgnRow}オブジェクトを取得します。
   */
    public TpCashBalanceFrgnRow getTpCashBalanceFrgnRow() {
        return row;
    }


  /** 
   * 指定の{@@link TpCashBalanceFrgnRow}オブジェクトから{@@link TpCashBalanceFrgnDao}オブジェクトを作成します。 
   * これは実際の{@@link TpCashBalanceFrgnRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TpCashBalanceFrgnDao}取得のために指定の{@@link TpCashBalanceFrgnRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TpCashBalanceFrgnDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TpCashBalanceFrgnDao forRow( TpCashBalanceFrgnRow row ) throws java.lang.IllegalArgumentException {
        return (TpCashBalanceFrgnDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpCashBalanceFrgnRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TpCashBalanceFrgnRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TpCashBalanceFrgnPK}やデータベースレコードとして挿入される{@@link TpCashBalanceFrgnParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpCashBalanceFrgnRow.TYPE );
    }


  /** 
   * {@@link TpCashBalanceFrgnRow}を一意に特定する{@@link TpCashBalanceFrgnPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TpCashBalanceFrgnRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TpCashBalanceFrgnParams}オブジェクトの主キーとして利用可能な{@@link TpCashBalanceFrgnPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TpCashBalanceFrgnPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpCashBalanceFrgnPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TpCashBalanceFrgnRow}オブジェクトを検索します。 
   * 
   * @@param p_tpCashBalanceFrgnId 検索対象であるp_tpCashBalanceFrgnIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TpCashBalanceFrgnRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TpCashBalanceFrgnRow findRowByPk( long p_tpCashBalanceFrgnId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalanceFrgnPK pk = new TpCashBalanceFrgnPK( p_tpCashBalanceFrgnId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTpCashBalanceFrgnPKオブジェクトから{@@link TpCashBalanceFrgnRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTpCashBalanceFrgnPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TpCashBalanceFrgnRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TpCashBalanceFrgnRow findRowByPk( TpCashBalanceFrgnPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpCashBalanceFrgnRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public static TpCashBalanceFrgnDao findDaoByPk( long p_tpCashBalanceFrgnId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalanceFrgnPK pk = new TpCashBalanceFrgnPK( p_tpCashBalanceFrgnId );
        TpCashBalanceFrgnRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TpCashBalanceFrgnPK)}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public static TpCashBalanceFrgnDao findDaoByPk( TpCashBalanceFrgnPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalanceFrgnRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link TpCashBalanceFrgnDao}に紐付く{@@link TpCashBalanceFrgnRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link TpCashBalanceFrgnDao}と外部キーの関係にある{@@link SubAccountRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public SubAccountRow fetchSubAccountRowViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        Row row = SubAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof SubAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (SubAccountRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for SubAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}を使用してください。 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( dao.getSubAccountRow() );
    }


  /** 
   * {@@link SubAccountRow}と外部キーの関係にある{@@link TpCashBalanceFrgnRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link TpCashBalanceFrgnRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link TpCashBalanceFrgnRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link TpCashBalanceFrgnRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TpCashBalanceFrgnRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TpCashBalanceFrgnRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCashBalanceFrgnRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * p_tpCashBalanceFrgnId, and にて指定の値から一意の{@@link TpCashBalanceFrgnRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tpCashBalanceFrgnId 検索対象であるp_tpCashBalanceFrgnIdフィールドの値
   * 
   * @@return 引数指定のp_tpCashBalanceFrgnId, and の値と一致する{@@link TpCashBalanceFrgnRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TpCashBalanceFrgnRow findRowByTpCashBalanceFrgnId( long p_tpCashBalanceFrgnId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TpCashBalanceFrgnRow.TYPE,
            "tp_cash_balance_frgn_id=?",
            null,
            new Object[] { new Long(p_tpCashBalanceFrgnId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TpCashBalanceFrgnRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TpCashBalanceFrgnDao.findRowsByTpCashBalanceFrgnId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTpCashBalanceFrgnId(long)}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public static TpCashBalanceFrgnDao findDaoByTpCashBalanceFrgnId( long p_tpCashBalanceFrgnId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTpCashBalanceFrgnId( p_tpCashBalanceFrgnId ) );
    }


  /** 
   * p_accountId, p_currencyCode, and にて指定の値から一意の{@@link TpCashBalanceFrgnRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_currencyCode 検索対象であるp_currencyCodeフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_currencyCode, and の値と一致する{@@link TpCashBalanceFrgnRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TpCashBalanceFrgnRow findRowByAccountIdCurrencyCode( long p_accountId, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TpCashBalanceFrgnRow.TYPE,
            "account_id=? and currency_code=?",
            null,
            new Object[] { new Long(p_accountId), p_currencyCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TpCashBalanceFrgnRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TpCashBalanceFrgnDao.findRowsByAccountIdCurrencyCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdCurrencyCode(long, String)}および{@@link #forRow(TpCashBalanceFrgnRow)}を使用してください。 
   */
    public static TpCashBalanceFrgnDao findDaoByAccountIdCurrencyCode( long p_accountId, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdCurrencyCode( p_accountId, p_currencyCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
