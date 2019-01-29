head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.09.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoClosingContractSpecDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IfoClosingContractSpecDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoClosingContractSpecRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoClosingContractSpecPK 
 * @@see IfoClosingContractSpecRow 
 */
public class IfoClosingContractSpecDao extends DataAccessObject {


  /** 
   * この{@@link IfoClosingContractSpecDao}に関連する型指定のRowオブジェクト 
   */
    private IfoClosingContractSpecRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoClosingContractSpecRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoClosingContractSpecDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoClosingContractSpecDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoClosingContractSpecRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoClosingContractSpecRow )
                return new IfoClosingContractSpecDao( (IfoClosingContractSpecRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoClosingContractSpecRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoClosingContractSpecRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoClosingContractSpecRow}オブジェクト 
    */
    protected IfoClosingContractSpecDao( IfoClosingContractSpecRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoClosingContractSpecRow}オブジェクトを取得します。
   */
    public IfoClosingContractSpecRow getIfoClosingContractSpecRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoClosingContractSpecRow}オブジェクトから{@@link IfoClosingContractSpecDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoClosingContractSpecRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoClosingContractSpecDao}取得のために指定の{@@link IfoClosingContractSpecRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoClosingContractSpecDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoClosingContractSpecDao forRow( IfoClosingContractSpecRow row ) throws java.lang.IllegalArgumentException {
        return (IfoClosingContractSpecDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoClosingContractSpecRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoClosingContractSpecRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoClosingContractSpecPK}やデータベースレコードとして挿入される{@@link IfoClosingContractSpecParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoClosingContractSpecRow.TYPE );
    }


  /** 
   * {@@link IfoClosingContractSpecRow}を一意に特定する{@@link IfoClosingContractSpecPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoClosingContractSpecRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoClosingContractSpecParams}オブジェクトの主キーとして利用可能な{@@link IfoClosingContractSpecPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoClosingContractSpecPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoClosingContractSpecPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoClosingContractSpecRow}オブジェクトを検索します。 
   * 
   * @@param p_closingContractSpecId 検索対象であるp_closingContractSpecIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoClosingContractSpecRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoClosingContractSpecRow findRowByPk( long p_closingContractSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoClosingContractSpecPK pk = new IfoClosingContractSpecPK( p_closingContractSpecId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoClosingContractSpecPKオブジェクトから{@@link IfoClosingContractSpecRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoClosingContractSpecPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoClosingContractSpecRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoClosingContractSpecRow findRowByPk( IfoClosingContractSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoClosingContractSpecRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static IfoClosingContractSpecDao findDaoByPk( long p_closingContractSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoClosingContractSpecPK pk = new IfoClosingContractSpecPK( p_closingContractSpecId );
        IfoClosingContractSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoClosingContractSpecPK)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static IfoClosingContractSpecDao findDaoByPk( IfoClosingContractSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoClosingContractSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link IfoClosingContractSpecDao}に紐付く{@@link IfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link IfoClosingContractSpecDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link IfoClosingContractSpecDao}に紐付く{@@link IfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link IfoOrderRow}を検索します。 
   * 
   * @@return {@@link IfoClosingContractSpecDao}と外部キーの関係にある{@@link IfoOrderRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public IfoOrderRow fetchIfoOrderRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoOrderPK pk = new IfoOrderPK( row.getOrderId() );
        Row row = IfoOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IfoOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IfoOrderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoOrderRowViaOrderId()}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public IfoOrderDao fetchIfoOrderDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoOrderPK pk = new IfoOrderPK( row.getOrderId() );
        DataAccessObject dao = IfoOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoOrderDao) dao;
    }


  /** 
   * この{@@link IfoClosingContractSpecDao}に紐付く{@@link IfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link IfoOrderUnitRow}を検索します。 
   * 
   * @@return {@@link IfoClosingContractSpecDao}と外部キーの関係にある{@@link IfoOrderUnitRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public IfoOrderUnitRow fetchIfoOrderUnitRowViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoOrderUnitPK pk = new IfoOrderUnitPK( row.getOrderUnitId() );
        Row row = IfoOrderUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IfoOrderUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IfoOrderUnitRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoOrderUnitRowViaOrderUnitId()}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public IfoOrderUnitDao fetchIfoOrderUnitDaoViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoOrderUnitPK pk = new IfoOrderUnitPK( row.getOrderUnitId() );
        DataAccessObject dao = IfoOrderUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoOrderUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoOrderUnitDao) dao;
    }


  /** 
   * この{@@link IfoClosingContractSpecDao}に紐付く{@@link IfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link IfoContractRow}を検索します。 
   * 
   * @@return {@@link IfoClosingContractSpecDao}と外部キーの関係にある{@@link IfoContractRow} 
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
   * @@deprecated 代わりに{@@link #fetchIfoContractRowViaContractId()}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public IfoContractDao fetchIfoContractDaoViaContractId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoContractPK pk = new IfoContractPK( row.getContractId() );
        DataAccessObject dao = IfoContractDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoContractDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoContractDao) dao;
    }


  /** 
   * この{@@link IfoClosingContractSpecDao}に紐付く{@@link IfoClosingContractSpecRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link IfoClosingContractSpecDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
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
    // Find Rows given foreign key values for SubAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}を使用してください。 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( dao.getSubAccountRow() );
    }


  /** 
   * {@@link SubAccountRow}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link IfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for IfoOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderId(IfoOrderRow)}を使用してください。 
   */
    public static List findRowsByOrderId( IfoOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getIfoOrderRow() );
    }


  /** 
   * {@@link IfoOrderRow}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IfoOrderRow}オブジェクト 
   * @@return 指定の{@@link IfoOrderRow}に外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( IfoOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link IfoOrderPK}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IfoOrderPK}オブジェクト 
   * @@return {@@link IfoOrderPK}と外部キーが一致する値を持つ{@@link IfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( IfoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderId(IfoOrderRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( IfoOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(IfoOrderRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( IfoOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(IfoOrderPK)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( IfoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(long)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for IfoOrderUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderUnitId(IfoOrderUnitRow)}を使用してください。 
   */
    public static List findRowsByOrderUnitId( IfoOrderUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( dao.getIfoOrderUnitRow() );
    }


  /** 
   * {@@link IfoOrderUnitRow}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IfoOrderUnitRow}オブジェクト 
   * @@return 指定の{@@link IfoOrderUnitRow}に外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( IfoOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( row.getOrderUnitId() );
    }


  /** 
   * {@@link IfoOrderUnitPK}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IfoOrderUnitPK}オブジェクト 
   * @@return {@@link IfoOrderUnitPK}と外部キーが一致する値を持つ{@@link IfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( IfoOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( pk.order_unit_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( long p_orderUnitId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { new Long(p_orderUnitId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoOrderUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderUnitId(IfoOrderUnitRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( IfoOrderUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(IfoOrderUnitRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( IfoOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(IfoOrderUnitPK)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( IfoOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( pk.order_unit_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(long)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( p_orderUnitId ) );
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
   * {@@link IfoContractRow}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IfoContractRow}オブジェクト 
   * @@return 指定の{@@link IfoContractRow}に外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( IfoContractRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( row.getContractId() );
    }


  /** 
   * {@@link IfoContractPK}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IfoContractPK}オブジェクト 
   * @@return {@@link IfoContractPK}と外部キーが一致する値を持つ{@@link IfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( IfoContractPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( pk.contract_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( long p_contractId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "contract_id=?",
            null,
            new Object[] { new Long(p_contractId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoContract
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByContractId(IfoContractRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(IfoContractRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(IfoContractPK)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( pk.contract_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(long)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( long p_contractId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( p_contractId ) );
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link IfoClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link IfoClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
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
   * p_closingContractSpecId, and にて指定の値から一意の{@@link IfoClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_closingContractSpecId 検索対象であるp_closingContractSpecIdフィールドの値
   * 
   * @@return 引数指定のp_closingContractSpecId, and の値と一致する{@@link IfoClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoClosingContractSpecRow findRowByClosingContractSpecId( long p_closingContractSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "closing_contract_spec_id=?",
            null,
            new Object[] { new Long(p_closingContractSpecId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoClosingContractSpecDao.findRowsByClosingContractSpecId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByClosingContractSpecId(long)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static IfoClosingContractSpecDao findDaoByClosingContractSpecId( long p_closingContractSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByClosingContractSpecId( p_closingContractSpecId ) );
    }


  /** 
   * p_accountId, p_subAccountId, p_orderId, p_orderUnitId, p_contractId, p_closingSerialNo, and にて指定の値から一意の{@@link IfoClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * @@param p_closingSerialNo 検索対象であるp_closingSerialNoフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_orderId, p_orderUnitId, p_contractId, p_closingSerialNo, and の値と一致する{@@link IfoClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoClosingContractSpecRow findRowByAccountIdSubAccountIdOrderIdOrderUnitIdContractIdClosingSerialNo( long p_accountId, long p_subAccountId, long p_orderId, long p_orderUnitId, long p_contractId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "account_id=? and sub_account_id=? and order_id=? and order_unit_id=? and contract_id=? and closing_serial_no=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Long(p_orderId), new Long(p_orderUnitId), new Long(p_contractId), new Integer(p_closingSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoClosingContractSpecDao.findRowsByAccountIdSubAccountIdOrderIdOrderUnitIdContractIdClosingSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdSubAccountIdOrderIdOrderUnitIdContractIdClosingSerialNo(long, long, long, long, long, int)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static IfoClosingContractSpecDao findDaoByAccountIdSubAccountIdOrderIdOrderUnitIdContractIdClosingSerialNo( long p_accountId, long p_subAccountId, long p_orderId, long p_orderUnitId, long p_contractId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdOrderIdOrderUnitIdContractIdClosingSerialNo( p_accountId, p_subAccountId, p_orderId, p_orderUnitId, p_contractId, p_closingSerialNo ) );
    }


  /** 
   * p_orderUnitId, p_contractId, and にて指定の値から一意の{@@link IfoClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のp_orderUnitId, p_contractId, and の値と一致する{@@link IfoClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoClosingContractSpecRow findRowByOrderUnitIdContractId( long p_orderUnitId, long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "order_unit_id=? and contract_id=?",
            null,
            new Object[] { new Long(p_orderUnitId), new Long(p_contractId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoClosingContractSpecDao.findRowsByOrderUnitIdContractId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderUnitIdContractId(long, long)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static IfoClosingContractSpecDao findDaoByOrderUnitIdContractId( long p_orderUnitId, long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderUnitIdContractId( p_orderUnitId, p_contractId ) );
    }


  /** 
   * p_orderUnitId, p_closingSerialNo, and にて指定の値から一意の{@@link IfoClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_closingSerialNo 検索対象であるp_closingSerialNoフィールドの値
   * 
   * @@return 引数指定のp_orderUnitId, p_closingSerialNo, and の値と一致する{@@link IfoClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoClosingContractSpecRow findRowByOrderUnitIdClosingSerialNo( long p_orderUnitId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoClosingContractSpecRow.TYPE,
            "order_unit_id=? and closing_serial_no=?",
            null,
            new Object[] { new Long(p_orderUnitId), new Integer(p_closingSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoClosingContractSpecDao.findRowsByOrderUnitIdClosingSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderUnitIdClosingSerialNo(long, int)}および{@@link #forRow(IfoClosingContractSpecRow)}を使用してください。 
   */
    public static IfoClosingContractSpecDao findDaoByOrderUnitIdClosingSerialNo( long p_orderUnitId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderUnitIdClosingSerialNo( p_orderUnitId, p_closingSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

}
@
