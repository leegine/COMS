head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.12.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundOrderDao.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MutualFundOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MutualFundOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see MutualFundOrderPK 
 * @@see MutualFundOrderRow 
 */
public class MutualFundOrderDao extends DataAccessObject {


  /** 
   * この{@@link MutualFundOrderDao}に関連する型指定のRowオブジェクト 
   */
    private MutualFundOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MutualFundOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link MutualFundOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MutualFundOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MutualFundOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundOrderRow )
                return new MutualFundOrderDao( (MutualFundOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MutualFundOrderRow}オブジェクト 
    */
    protected MutualFundOrderDao( MutualFundOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MutualFundOrderRow}オブジェクトを取得します。
   */
    public MutualFundOrderRow getMutualFundOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link MutualFundOrderRow}オブジェクトから{@@link MutualFundOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link MutualFundOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MutualFundOrderDao}取得のために指定の{@@link MutualFundOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MutualFundOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MutualFundOrderDao forRow( MutualFundOrderRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFundOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MutualFundOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MutualFundOrderPK}やデータベースレコードとして挿入される{@@link MutualFundOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFundOrderRow.TYPE );
    }


  /** 
   * {@@link MutualFundOrderRow}を一意に特定する{@@link MutualFundOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MutualFundOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MutualFundOrderParams}オブジェクトの主キーとして利用可能な{@@link MutualFundOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MutualFundOrderPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new MutualFundOrderPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MutualFundOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundOrderRow findRowByPk( long p_orderId ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundOrderPK pk = new MutualFundOrderPK( p_orderId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMutualFundOrderPKオブジェクトから{@@link MutualFundOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMutualFundOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundOrderRow findRowByPk( MutualFundOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFundOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static MutualFundOrderDao findDaoByPk( long p_orderId ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundOrderPK pk = new MutualFundOrderPK( p_orderId );
        MutualFundOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MutualFundOrderPK)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static MutualFundOrderDao findDaoByPk( MutualFundOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link MutualFundOrderDao}に紐付く{@@link MutualFundOrderRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link MutualFundOrderDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link MutualFundOrderDao}に紐付く{@@link MutualFundOrderRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link MutualFundOrderDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
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
   * この{@@link MutualFundOrderDao}に関連する{@@link MutualFundOrderRow}の外部キーがある{@@link MutualFundFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MutualFundOrderDao}に関連する{@@link MutualFundOrderRow}の外部キーがある{@@link MutualFundFinTransactionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchMutualFundFinTransactionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return MutualFundFinTransactionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundFinTransactionRowsByOrderId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public List fetchMutualFundFinTransactionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return MutualFundFinTransactionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundFinTransactionRowsByOrderId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public List fetchMutualFundFinTransactionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchMutualFundFinTransactionDaosByOrderId();
    }


  /** 
   * この{@@link MutualFundOrderDao}に関連する{@@link MutualFundOrderRow}の外部キーがある{@@link MutualFundOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MutualFundOrderDao}に関連する{@@link MutualFundOrderRow}の外部キーがある{@@link MutualFundOrderUnitRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchMutualFundOrderUnitRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return MutualFundOrderUnitDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundOrderUnitRowsByOrderId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public List fetchMutualFundOrderUnitDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return MutualFundOrderUnitDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundOrderUnitRowsByOrderId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public List fetchMutualFundOrderUnitDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchMutualFundOrderUnitDaosByOrderId();
    }


  /** 
   * この{@@link MutualFundOrderDao}に関連する{@@link MutualFundOrderRow}の外部キーがある{@@link MutualFundOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MutualFundOrderDao}に関連する{@@link MutualFundOrderRow}の外部キーがある{@@link MutualFundOrderActionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchMutualFundOrderActionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return MutualFundOrderActionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundOrderActionRowsByOrderId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public List fetchMutualFundOrderActionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return MutualFundOrderActionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundOrderActionRowsByOrderId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public List fetchMutualFundOrderActionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchMutualFundOrderActionDaosByOrderId();
    }


  /** 
   * この{@@link MutualFundOrderDao}に関連する{@@link MutualFundOrderRow}の外部キーがある{@@link MutualFundOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link MutualFundOrderDao}に関連する{@@link MutualFundOrderRow}の外部キーがある{@@link MutualFundOrderExecutionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchMutualFundOrderExecutionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return MutualFundOrderExecutionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundOrderExecutionRowsByOrderId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public List fetchMutualFundOrderExecutionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return MutualFundOrderExecutionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMutualFundOrderExecutionRowsByOrderId()}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public List fetchMutualFundOrderExecutionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchMutualFundOrderExecutionDaosByOrderId();
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link MutualFundOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link MutualFundOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link MutualFundOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link MutualFundOrderRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MutualFundOrderRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MutualFundOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MutualFundOrderRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link MutualFundOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link MutualFundOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link MutualFundOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link MutualFundOrderRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MutualFundOrderRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MutualFundOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MutualFundOrderRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
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
   * p_orderId, and にて指定の値から一意の{@@link MutualFundOrderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 引数指定のp_orderId, and の値と一致する{@@link MutualFundOrderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MutualFundOrderRow findRowByOrderId( long p_orderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MutualFundOrderRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MutualFundOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MutualFundOrderDao.findRowsByOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderId(long)}および{@@link #forRow(MutualFundOrderRow)}を使用してください。 
   */
    public static MutualFundOrderDao findDaoByOrderId( long p_orderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderId( p_orderId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

}
@
