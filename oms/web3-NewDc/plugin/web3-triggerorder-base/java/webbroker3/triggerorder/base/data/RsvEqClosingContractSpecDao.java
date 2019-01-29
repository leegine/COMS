head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.19.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	RsvEqClosingContractSpecDao.java;


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
 * {@@link RsvEqClosingContractSpecDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RsvEqClosingContractSpecRow}インスタンスへ関連付けることができます。 
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
 * @@see RsvEqClosingContractSpecPK 
 * @@see RsvEqClosingContractSpecRow 
 */
public class RsvEqClosingContractSpecDao extends DataAccessObject {


  /** 
   * この{@@link RsvEqClosingContractSpecDao}に関連する型指定のRowオブジェクト 
   */
    private RsvEqClosingContractSpecRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RsvEqClosingContractSpecRow}と仮定される{@@link DataAccessObject}から新たに{@@link RsvEqClosingContractSpecDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RsvEqClosingContractSpecDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RsvEqClosingContractSpecRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RsvEqClosingContractSpecRow )
                return new RsvEqClosingContractSpecDao( (RsvEqClosingContractSpecRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RsvEqClosingContractSpecRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RsvEqClosingContractSpecRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RsvEqClosingContractSpecRow}オブジェクト 
    */
    protected RsvEqClosingContractSpecDao( RsvEqClosingContractSpecRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RsvEqClosingContractSpecRow}オブジェクトを取得します。
   */
    public RsvEqClosingContractSpecRow getRsvEqClosingContractSpecRow() {
        return row;
    }


  /** 
   * 指定の{@@link RsvEqClosingContractSpecRow}オブジェクトから{@@link RsvEqClosingContractSpecDao}オブジェクトを作成します。 
   * これは実際の{@@link RsvEqClosingContractSpecRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RsvEqClosingContractSpecDao}取得のために指定の{@@link RsvEqClosingContractSpecRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RsvEqClosingContractSpecDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RsvEqClosingContractSpecDao forRow( RsvEqClosingContractSpecRow row ) throws java.lang.IllegalArgumentException {
        return (RsvEqClosingContractSpecDao) DataAccessObject.forRow( row );
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
   * この{@@link RsvEqClosingContractSpecDao}に紐付く{@@link RsvEqClosingContractSpecRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link RsvEqClosingContractSpecDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link RsvEqClosingContractSpecDao}に紐付く{@@link RsvEqClosingContractSpecRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link RsvEqClosingContractSpecDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link RsvEqClosingContractSpecDao}に紐付く{@@link RsvEqClosingContractSpecRow}内で外部キーの関係をもつ{@@link RsvEqOrderUnitRow}を検索します。 
   * 
   * @@return {@@link RsvEqClosingContractSpecDao}と外部キーの関係にある{@@link RsvEqOrderUnitRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public RsvEqOrderUnitRow fetchRsvEqOrderUnitRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        RsvEqOrderUnitPK pk = new RsvEqOrderUnitPK( row.getOrderId() );
        Row row = RsvEqOrderUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RsvEqOrderUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RsvEqOrderUnitRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRsvEqOrderUnitRowViaOrderId()}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public RsvEqOrderUnitDao fetchRsvEqOrderUnitDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        RsvEqOrderUnitPK pk = new RsvEqOrderUnitPK( row.getOrderId() );
        DataAccessObject dao = RsvEqOrderUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RsvEqOrderUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RsvEqOrderUnitDao) dao;
    }


  /** 
   * この{@@link RsvEqClosingContractSpecDao}に紐付く{@@link RsvEqClosingContractSpecRow}内で外部キーの関係をもつ{@@link EqtypeContractRow}を検索します。 
   * 
   * @@return {@@link RsvEqClosingContractSpecDao}と外部キーの関係にある{@@link EqtypeContractRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public EqtypeContractRow fetchEqtypeContractRowViaContractId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeContractPK pk = new EqtypeContractPK( row.getContractId() );
        Row row = EqtypeContractDao.findRowByPk( pk );
        if ( row != null && !(row instanceof EqtypeContractRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (EqtypeContractRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeContractRowViaContractId()}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public EqtypeContractDao fetchEqtypeContractDaoViaContractId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeContractPK pk = new EqtypeContractPK( row.getContractId() );
        DataAccessObject dao = EqtypeContractDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeContractDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeContractDao) dao;
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link RsvEqClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link RsvEqClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link RsvEqClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link RsvEqClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RsvEqClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RsvEqClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RsvEqClosingContractSpecRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link RsvEqClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link RsvEqClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link RsvEqClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link RsvEqClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RsvEqClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RsvEqClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RsvEqClosingContractSpecRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for RsvEqOrderUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderId(RsvEqOrderUnitRow)}を使用してください。 
   */
    public static List findRowsByOrderId( RsvEqOrderUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getRsvEqOrderUnitRow() );
    }


  /** 
   * {@@link RsvEqOrderUnitRow}と外部キーの関係にある{@@link RsvEqClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RsvEqOrderUnitRow}オブジェクト 
   * @@return 指定の{@@link RsvEqOrderUnitRow}に外部キーを持つ{@@link RsvEqClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( RsvEqOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link RsvEqOrderUnitPK}と外部キーの関係にある{@@link RsvEqClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RsvEqOrderUnitPK}オブジェクト 
   * @@return {@@link RsvEqOrderUnitPK}と外部キーが一致する値を持つ{@@link RsvEqClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( RsvEqOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RsvEqClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RsvEqClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RsvEqClosingContractSpecRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RsvEqOrderUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderId(RsvEqOrderUnitRow)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RsvEqOrderUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(RsvEqOrderUnitRow)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RsvEqOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(RsvEqOrderUnitPK)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RsvEqOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(long)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for EqtypeContract
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByContractId(EqtypeContractRow)}を使用してください。 
   */
    public static List findRowsByContractId( EqtypeContractDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( dao.getEqtypeContractRow() );
    }


  /** 
   * {@@link EqtypeContractRow}と外部キーの関係にある{@@link RsvEqClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeContractRow}オブジェクト 
   * @@return 指定の{@@link EqtypeContractRow}に外部キーを持つ{@@link RsvEqClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( EqtypeContractRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( row.getContractId() );
    }


  /** 
   * {@@link EqtypeContractPK}と外部キーの関係にある{@@link RsvEqClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeContractPK}オブジェクト 
   * @@return {@@link EqtypeContractPK}と外部キーが一致する値を持つ{@@link RsvEqClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( EqtypeContractPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( pk.contract_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RsvEqClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RsvEqClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( long p_contractId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RsvEqClosingContractSpecRow.TYPE,
            "contract_id=?",
            null,
            new Object[] { new Long(p_contractId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeContract
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByContractId(EqtypeContractRow)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(EqtypeContractRow)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(EqtypeContractPK)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( pk.contract_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(long)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
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
   * p_orderId, p_closingSerialNo, and にて指定の値から一意の{@@link RsvEqClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_closingSerialNo 検索対象であるp_closingSerialNoフィールドの値
   * 
   * @@return 引数指定のp_orderId, p_closingSerialNo, and の値と一致する{@@link RsvEqClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RsvEqClosingContractSpecRow findRowByOrderIdClosingSerialNo( long p_orderId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RsvEqClosingContractSpecRow.TYPE,
            "order_id=? and closing_serial_no=?",
            null,
            new Object[] { new Long(p_orderId), new Integer(p_closingSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RsvEqClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RsvEqClosingContractSpecDao.findRowsByOrderIdClosingSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderIdClosingSerialNo(long, int)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static RsvEqClosingContractSpecDao findDaoByOrderIdClosingSerialNo( long p_orderId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderIdClosingSerialNo( p_orderId, p_closingSerialNo ) );
    }


  /** 
   * p_orderId, p_contractId, and にて指定の値から一意の{@@link RsvEqClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のp_orderId, p_contractId, and の値と一致する{@@link RsvEqClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RsvEqClosingContractSpecRow findRowByOrderIdContractId( long p_orderId, long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RsvEqClosingContractSpecRow.TYPE,
            "order_id=? and contract_id=?",
            null,
            new Object[] { new Long(p_orderId), new Long(p_contractId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RsvEqClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RsvEqClosingContractSpecDao.findRowsByOrderIdContractId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderIdContractId(long, long)}および{@@link #forRow(RsvEqClosingContractSpecRow)}を使用してください。 
   */
    public static RsvEqClosingContractSpecDao findDaoByOrderIdContractId( long p_orderId, long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderIdContractId( p_orderId, p_contractId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
