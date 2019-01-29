head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.19.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	RsvIfoClosingContractSpecDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.triggerorder.base.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link RsvIfoClosingContractSpecDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RsvIfoClosingContractSpecRow}インスタンスへ関連付けることができます。 
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
 * @@see RsvIfoClosingContractSpecPK 
 * @@see RsvIfoClosingContractSpecRow 
 */
public class RsvIfoClosingContractSpecDao extends DataAccessObject {


  /** 
   * この{@@link RsvIfoClosingContractSpecDao}に関連する型指定のRowオブジェクト 
   */
    private RsvIfoClosingContractSpecRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RsvIfoClosingContractSpecRow}と仮定される{@@link DataAccessObject}から新たに{@@link RsvIfoClosingContractSpecDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RsvIfoClosingContractSpecDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RsvIfoClosingContractSpecRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RsvIfoClosingContractSpecRow )
                return new RsvIfoClosingContractSpecDao( (RsvIfoClosingContractSpecRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RsvIfoClosingContractSpecRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RsvIfoClosingContractSpecRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RsvIfoClosingContractSpecRow}オブジェクト 
    */
    protected RsvIfoClosingContractSpecDao( RsvIfoClosingContractSpecRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RsvIfoClosingContractSpecRow}オブジェクトを取得します。
   */
    public RsvIfoClosingContractSpecRow getRsvIfoClosingContractSpecRow() {
        return row;
    }


  /** 
   * 指定の{@@link RsvIfoClosingContractSpecRow}オブジェクトから{@@link RsvIfoClosingContractSpecDao}オブジェクトを作成します。 
   * これは実際の{@@link RsvIfoClosingContractSpecRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RsvIfoClosingContractSpecDao}取得のために指定の{@@link RsvIfoClosingContractSpecRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RsvIfoClosingContractSpecDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RsvIfoClosingContractSpecDao forRow( RsvIfoClosingContractSpecRow row ) throws java.lang.IllegalArgumentException {
        return (RsvIfoClosingContractSpecDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


      // (this object has no primary key components)


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


      // (this object has no primary key components)


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link RsvIfoClosingContractSpecDao}に紐付く{@@link RsvIfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link RsvIfoClosingContractSpecDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link RsvIfoClosingContractSpecDao}に紐付く{@@link RsvIfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link RsvIfoClosingContractSpecDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link RsvIfoClosingContractSpecDao}に紐付く{@@link RsvIfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link RsvIfoOrderUnitRow}を検索します。 
   * 
   * @@return {@@link RsvIfoClosingContractSpecDao}と外部キーの関係にある{@@link RsvIfoOrderUnitRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public RsvIfoOrderUnitRow fetchRsvIfoOrderUnitRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        RsvIfoOrderUnitPK pk = new RsvIfoOrderUnitPK( row.getOrderId() );
        Row row = RsvIfoOrderUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RsvIfoOrderUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RsvIfoOrderUnitRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRsvIfoOrderUnitRowViaOrderId()}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public RsvIfoOrderUnitDao fetchRsvIfoOrderUnitDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        RsvIfoOrderUnitPK pk = new RsvIfoOrderUnitPK( row.getOrderId() );
        DataAccessObject dao = RsvIfoOrderUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RsvIfoOrderUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RsvIfoOrderUnitDao) dao;
    }


  /** 
   * この{@@link RsvIfoClosingContractSpecDao}に紐付く{@@link RsvIfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link IfoContractRow}を検索します。 
   * 
   * @@return {@@link RsvIfoClosingContractSpecDao}と外部キーの関係にある{@@link IfoContractRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public IfoContractRow fetchIfoContractRowViaContractId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoContractPK pk = new IfoContractPK( row.getContractId() );
        Row row = IfoContractDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IfoContractRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IfoContractRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoContractRowViaContractId()}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public IfoContractDao fetchIfoContractDaoViaContractId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoContractPK pk = new IfoContractPK( row.getContractId() );
        DataAccessObject dao = IfoContractDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoContractDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoContractDao) dao;
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link RsvIfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link RsvIfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link RsvIfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link RsvIfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RsvIfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RsvIfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RsvIfoClosingContractSpecRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link RsvIfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link RsvIfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link RsvIfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link RsvIfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RsvIfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RsvIfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RsvIfoClosingContractSpecRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for RsvIfoOrderUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderId(RsvIfoOrderUnitRow)}を使用してください。 
   */
    public static List findRowsByOrderId( RsvIfoOrderUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getRsvIfoOrderUnitRow() );
    }


  /** 
   * {@@link RsvIfoOrderUnitRow}と外部キーの関係にある{@@link RsvIfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RsvIfoOrderUnitRow}オブジェクト 
   * @@return 指定の{@@link RsvIfoOrderUnitRow}に外部キーを持つ{@@link RsvIfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( RsvIfoOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link RsvIfoOrderUnitPK}と外部キーの関係にある{@@link RsvIfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RsvIfoOrderUnitPK}オブジェクト 
   * @@return {@@link RsvIfoOrderUnitPK}と外部キーが一致する値を持つ{@@link RsvIfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( RsvIfoOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RsvIfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RsvIfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RsvIfoClosingContractSpecRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RsvIfoOrderUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderId(RsvIfoOrderUnitRow)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RsvIfoOrderUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(RsvIfoOrderUnitRow)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RsvIfoOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(RsvIfoOrderUnitPK)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RsvIfoOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(long)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for IfoContract
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByContractId(IfoContractRow)}を使用してください。 
   */
    public static List findRowsByContractId( IfoContractDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( dao.getIfoContractRow() );
    }


  /** 
   * {@@link IfoContractRow}と外部キーの関係にある{@@link RsvIfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IfoContractRow}オブジェクト 
   * @@return 指定の{@@link IfoContractRow}に外部キーを持つ{@@link RsvIfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( IfoContractRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( row.getContractId() );
    }


  /** 
   * {@@link IfoContractPK}と外部キーの関係にある{@@link RsvIfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IfoContractPK}オブジェクト 
   * @@return {@@link IfoContractPK}と外部キーが一致する値を持つ{@@link RsvIfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( IfoContractPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( pk.contract_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RsvIfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RsvIfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( long p_contractId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RsvIfoClosingContractSpecRow.TYPE,
            "contract_id=?",
            null,
            new Object[] { new Long(p_contractId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoContract
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByContractId(IfoContractRow)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(IfoContractRow)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(IfoContractPK)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( pk.contract_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(long)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( long p_contractId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( p_contractId ) );
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
   * p_orderId, p_closingSerialNo, and にて指定の値から一意の{@@link RsvIfoClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_closingSerialNo 検索対象であるp_closingSerialNoフィールドの値
   * 
   * @@return 引数指定のp_orderId, p_closingSerialNo, and の値と一致する{@@link RsvIfoClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RsvIfoClosingContractSpecRow findRowByOrderIdClosingSerialNo( long p_orderId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RsvIfoClosingContractSpecRow.TYPE,
            "order_id=? and closing_serial_no=?",
            null,
            new Object[] { new Long(p_orderId), new Integer(p_closingSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RsvIfoClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RsvIfoClosingContractSpecDao.findRowsByOrderIdClosingSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderIdClosingSerialNo(long, int)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static RsvIfoClosingContractSpecDao findDaoByOrderIdClosingSerialNo( long p_orderId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderIdClosingSerialNo( p_orderId, p_closingSerialNo ) );
    }


  /** 
   * p_orderId, p_contractId, and にて指定の値から一意の{@@link RsvIfoClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のp_orderId, p_contractId, and の値と一致する{@@link RsvIfoClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RsvIfoClosingContractSpecRow findRowByOrderIdContractId( long p_orderId, long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RsvIfoClosingContractSpecRow.TYPE,
            "order_id=? and contract_id=?",
            null,
            new Object[] { new Long(p_orderId), new Long(p_contractId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RsvIfoClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RsvIfoClosingContractSpecDao.findRowsByOrderIdContractId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderIdContractId(long, long)}および{@@link #forRow(RsvIfoClosingContractSpecRow)}を使用してください。 
   */
    public static RsvIfoClosingContractSpecDao findDaoByOrderIdContractId( long p_orderId, long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderIdContractId( p_orderId, p_contractId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
