head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.38.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeOrderDao.java;


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
 * {@@link EqtypeOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EqtypeOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see EqtypeOrderPK 
 * @@see EqtypeOrderRow 
 */
public class EqtypeOrderDao extends DataAccessObject {


  /** 
   * この{@@link EqtypeOrderDao}に関連する型指定のRowオブジェクト 
   */
    private EqtypeOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EqtypeOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link EqtypeOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EqtypeOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EqtypeOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeOrderRow )
                return new EqtypeOrderDao( (EqtypeOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EqtypeOrderRow}オブジェクト 
    */
    protected EqtypeOrderDao( EqtypeOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EqtypeOrderRow}オブジェクトを取得します。
   */
    public EqtypeOrderRow getEqtypeOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link EqtypeOrderRow}オブジェクトから{@@link EqtypeOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link EqtypeOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EqtypeOrderDao}取得のために指定の{@@link EqtypeOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EqtypeOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EqtypeOrderDao forRow( EqtypeOrderRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EqtypeOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EqtypeOrderPK}やデータベースレコードとして挿入される{@@link EqtypeOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeOrderRow.TYPE );
    }


  /** 
   * {@@link EqtypeOrderRow}を一意に特定する{@@link EqtypeOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EqtypeOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EqtypeOrderParams}オブジェクトの主キーとして利用可能な{@@link EqtypeOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EqtypeOrderPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new EqtypeOrderPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EqtypeOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeOrderRow findRowByPk( long p_orderId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderPK pk = new EqtypeOrderPK( p_orderId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEqtypeOrderPKオブジェクトから{@@link EqtypeOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEqtypeOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeOrderRow findRowByPk( EqtypeOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static EqtypeOrderDao findDaoByPk( long p_orderId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderPK pk = new EqtypeOrderPK( p_orderId );
        EqtypeOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EqtypeOrderPK)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static EqtypeOrderDao findDaoByPk( EqtypeOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeOrderDao}に紐付く{@@link EqtypeOrderRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link EqtypeOrderDao}に紐付く{@@link EqtypeOrderRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeOrderActionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchEqtypeOrderActionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeOrderActionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderActionRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeOrderActionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeOrderActionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderActionRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeOrderActionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchEqtypeOrderActionDaosByOrderId();
    }


  /** 
   * この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchEqtypeOrderExecutionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeOrderExecutionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderExecutionRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeOrderExecutionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeOrderExecutionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderExecutionRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeOrderExecutionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchEqtypeOrderExecutionDaosByOrderId();
    }


  /** 
   * この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeFinTransactionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchEqtypeFinTransactionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeFinTransactionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeFinTransactionRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeFinTransactionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeFinTransactionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeFinTransactionRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeFinTransactionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchEqtypeFinTransactionDaosByOrderId();
    }


  /** 
   * この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeOrderUnitRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchEqtypeOrderUnitRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeOrderUnitDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderUnitRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeOrderUnitDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeOrderUnitDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderUnitRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeOrderUnitDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchEqtypeOrderUnitDaosByOrderId();
    }


  /** 
   * この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link EqtypeOrderDao}に関連する{@@link EqtypeOrderRow}の外部キーがある{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchEqtypeClosingContractSpecRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeClosingContractSpecDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeClosingContractSpecRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeClosingContractSpecDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return EqtypeClosingContractSpecDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeClosingContractSpecRowsByOrderId()}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public List fetchEqtypeClosingContractSpecDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchEqtypeClosingContractSpecDaosByOrderId();
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link EqtypeOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link EqtypeOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link EqtypeOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link EqtypeOrderRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
    }


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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link EqtypeOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link EqtypeOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link EqtypeOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link EqtypeOrderRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
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
   * p_orderId, and にて指定の値から一意の{@@link EqtypeOrderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 引数指定のp_orderId, and の値と一致する{@@link EqtypeOrderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeOrderRow findRowByOrderId( long p_orderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeOrderRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeOrderDao.findRowsByOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderId(long)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static EqtypeOrderDao findDaoByOrderId( long p_orderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderId( p_orderId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_orderId, p_accountId, p_subAccountId, and にて指定の値に一致する{@@link EqtypeOrderRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 引数指定のp_orderId, p_accountId, p_subAccountId, and の値と一致する{@@link EqtypeOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderIdAccountIdSubAccountId( long p_orderId, long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderRow.TYPE,
            "order_id=? and account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_orderId), new Long(p_accountId), new Long(p_subAccountId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderIdAccountIdSubAccountId(long, long, long)}および{@@link #forRow(EqtypeOrderRow)}を使用してください。 
   */
    public static List findDaosByOrderIdAccountIdSubAccountId( long p_orderId, long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderIdAccountIdSubAccountId( p_orderId, p_accountId, p_subAccountId ) );
    }

}
@
