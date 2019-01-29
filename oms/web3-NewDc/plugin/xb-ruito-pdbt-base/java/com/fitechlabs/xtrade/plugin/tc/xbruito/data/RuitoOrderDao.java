head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.28.09.09.24;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5644d9050285d38;
filename	RuitoOrderDao.java;

1.1
date	2011.03.22.09.16.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoOrderDao.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link RuitoOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RuitoOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see RuitoOrderPK 
 * @@see RuitoOrderRow 
 */
public class RuitoOrderDao extends DataAccessObject {


  /** 
   * この{@@link RuitoOrderDao}に関連する型指定のRowオブジェクト 
   */
    private RuitoOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RuitoOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link RuitoOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RuitoOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RuitoOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RuitoOrderRow )
                return new RuitoOrderDao( (RuitoOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RuitoOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RuitoOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RuitoOrderRow}オブジェクト 
    */
    protected RuitoOrderDao( RuitoOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RuitoOrderRow}オブジェクトを取得します。
   */
    public RuitoOrderRow getRuitoOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link RuitoOrderRow}オブジェクトから{@@link RuitoOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link RuitoOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RuitoOrderDao}取得のために指定の{@@link RuitoOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RuitoOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RuitoOrderDao forRow( RuitoOrderRow row ) throws java.lang.IllegalArgumentException {
        return (RuitoOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RuitoOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RuitoOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RuitoOrderPK}やデータベースレコードとして挿入される{@@link RuitoOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RuitoOrderRow.TYPE );
    }


  /** 
   * {@@link RuitoOrderRow}を一意に特定する{@@link RuitoOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RuitoOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RuitoOrderParams}オブジェクトの主キーとして利用可能な{@@link RuitoOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RuitoOrderPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RuitoOrderPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RuitoOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RuitoOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RuitoOrderRow findRowByPk( long p_orderId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoOrderPK pk = new RuitoOrderPK( p_orderId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRuitoOrderPKオブジェクトから{@@link RuitoOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRuitoOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RuitoOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RuitoOrderRow findRowByPk( RuitoOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RuitoOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static RuitoOrderDao findDaoByPk( long p_orderId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoOrderPK pk = new RuitoOrderPK( p_orderId );
        RuitoOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RuitoOrderPK)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static RuitoOrderDao findDaoByPk( RuitoOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link RuitoOrderDao}に紐付く{@@link RuitoOrderRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link RuitoOrderDao}に紐付く{@@link RuitoOrderRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
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
   * この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoOrderActionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoOrderActionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderActionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderActionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public List fetchRuitoOrderActionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderActionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderActionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public List fetchRuitoOrderActionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoOrderActionDaosByOrderId();
    }


  /** 
   * この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoOrderExecutionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoOrderExecutionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderExecutionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderExecutionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public List fetchRuitoOrderExecutionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderExecutionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderExecutionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public List fetchRuitoOrderExecutionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoOrderExecutionDaosByOrderId();
    }


  /** 
   * この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoOrderUnitRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoOrderUnitRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderUnitDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderUnitRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public List fetchRuitoOrderUnitDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderUnitDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderUnitRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public List fetchRuitoOrderUnitDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoOrderUnitDaosByOrderId();
    }


  /** 
   * この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoFinTransactionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoFinTransactionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoFinTransactionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoFinTransactionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public List fetchRuitoFinTransactionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoFinTransactionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoFinTransactionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public List fetchRuitoFinTransactionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoFinTransactionDaosByOrderId();
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link RuitoOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link RuitoOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link RuitoOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link RuitoOrderRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link RuitoOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link RuitoOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link RuitoOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link RuitoOrderRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
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
   * p_orderId, and にて指定の値から一意の{@@link RuitoOrderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 引数指定のp_orderId, and の値と一致する{@@link RuitoOrderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RuitoOrderRow findRowByOrderId( long p_orderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RuitoOrderRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RuitoOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RuitoOrderDao.findRowsByOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderId(long)}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
   */
    public static RuitoOrderDao findDaoByOrderId( long p_orderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderId( p_orderId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

}
@


1.1
log
@*** empty log message ***
@
text
@d250 1
a250 1
   * この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
d252 1
a252 1
   * @@return この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoFinTransactionRow}オブジェクトの{@@link List}
d256 2
a257 2
    public List fetchRuitoFinTransactionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoFinTransactionDao.findRowsByOrderId( row );
d262 1
a262 1
   * @@deprecated 代わりに{@@link #fetchRuitoFinTransactionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
d264 2
a265 2
    public List fetchRuitoFinTransactionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoFinTransactionDao.findDaosByOrderId( row );
d270 1
a270 1
   * @@deprecated 代わりに{@@link #fetchRuitoFinTransactionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
d272 2
a273 2
    public List fetchRuitoFinTransactionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoFinTransactionDaosByOrderId();
d334 1
a334 1
   * この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
d336 1
a336 1
   * @@return この{@@link RuitoOrderDao}に関連する{@@link RuitoOrderRow}の外部キーがある{@@link RuitoOrderActionRow}オブジェクトの{@@link List}
d340 2
a341 2
    public List fetchRuitoOrderActionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderActionDao.findRowsByOrderId( row );
d346 1
a346 1
   * @@deprecated 代わりに{@@link #fetchRuitoOrderActionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
d348 2
a349 2
    public List fetchRuitoOrderActionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderActionDao.findDaosByOrderId( row );
d354 1
a354 1
   * @@deprecated 代わりに{@@link #fetchRuitoOrderActionRowsByOrderId()}および{@@link #forRow(RuitoOrderRow)}を使用してください。 
d356 2
a357 2
    public List fetchRuitoOrderActionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoOrderActionDaosByOrderId();
@

