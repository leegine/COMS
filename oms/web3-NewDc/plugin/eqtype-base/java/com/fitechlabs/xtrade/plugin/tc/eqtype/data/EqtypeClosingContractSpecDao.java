head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.39.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeClosingContractSpecDao.java;


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
 * {@@link EqtypeClosingContractSpecDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EqtypeClosingContractSpecRow}インスタンスへ関連付けることができます。 
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
 * @@see EqtypeClosingContractSpecPK 
 * @@see EqtypeClosingContractSpecRow 
 */
public class EqtypeClosingContractSpecDao extends DataAccessObject {


  /** 
   * この{@@link EqtypeClosingContractSpecDao}に関連する型指定のRowオブジェクト 
   */
    private EqtypeClosingContractSpecRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EqtypeClosingContractSpecRow}と仮定される{@@link DataAccessObject}から新たに{@@link EqtypeClosingContractSpecDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EqtypeClosingContractSpecDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EqtypeClosingContractSpecRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeClosingContractSpecRow )
                return new EqtypeClosingContractSpecDao( (EqtypeClosingContractSpecRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeClosingContractSpecRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeClosingContractSpecRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EqtypeClosingContractSpecRow}オブジェクト 
    */
    protected EqtypeClosingContractSpecDao( EqtypeClosingContractSpecRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EqtypeClosingContractSpecRow}オブジェクトを取得します。
   */
    public EqtypeClosingContractSpecRow getEqtypeClosingContractSpecRow() {
        return row;
    }


  /** 
   * 指定の{@@link EqtypeClosingContractSpecRow}オブジェクトから{@@link EqtypeClosingContractSpecDao}オブジェクトを作成します。 
   * これは実際の{@@link EqtypeClosingContractSpecRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EqtypeClosingContractSpecDao}取得のために指定の{@@link EqtypeClosingContractSpecRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EqtypeClosingContractSpecDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EqtypeClosingContractSpecDao forRow( EqtypeClosingContractSpecRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeClosingContractSpecDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeClosingContractSpecRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EqtypeClosingContractSpecRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EqtypeClosingContractSpecPK}やデータベースレコードとして挿入される{@@link EqtypeClosingContractSpecParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeClosingContractSpecRow.TYPE );
    }


  /** 
   * {@@link EqtypeClosingContractSpecRow}を一意に特定する{@@link EqtypeClosingContractSpecPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EqtypeClosingContractSpecRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EqtypeClosingContractSpecParams}オブジェクトの主キーとして利用可能な{@@link EqtypeClosingContractSpecPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EqtypeClosingContractSpecPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new EqtypeClosingContractSpecPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EqtypeClosingContractSpecRow}オブジェクトを検索します。 
   * 
   * @@param p_closingContractSpecId 検索対象であるp_closingContractSpecIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeClosingContractSpecRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeClosingContractSpecRow findRowByPk( long p_closingContractSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeClosingContractSpecPK pk = new EqtypeClosingContractSpecPK( p_closingContractSpecId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEqtypeClosingContractSpecPKオブジェクトから{@@link EqtypeClosingContractSpecRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEqtypeClosingContractSpecPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeClosingContractSpecRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeClosingContractSpecRow findRowByPk( EqtypeClosingContractSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeClosingContractSpecRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static EqtypeClosingContractSpecDao findDaoByPk( long p_closingContractSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeClosingContractSpecPK pk = new EqtypeClosingContractSpecPK( p_closingContractSpecId );
        EqtypeClosingContractSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EqtypeClosingContractSpecPK)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static EqtypeClosingContractSpecDao findDaoByPk( EqtypeClosingContractSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeClosingContractSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeClosingContractSpecDao}に紐付く{@@link EqtypeClosingContractSpecRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link EqtypeClosingContractSpecDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link EqtypeClosingContractSpecDao}に紐付く{@@link EqtypeClosingContractSpecRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link EqtypeClosingContractSpecDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link EqtypeClosingContractSpecDao}に紐付く{@@link EqtypeClosingContractSpecRow}内で外部キーの関係をもつ{@@link EqtypeOrderRow}を検索します。 
   * 
   * @@return {@@link EqtypeClosingContractSpecDao}と外部キーの関係にある{@@link EqtypeOrderRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public EqtypeOrderRow fetchEqtypeOrderRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeOrderPK pk = new EqtypeOrderPK( row.getOrderId() );
        Row row = EqtypeOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof EqtypeOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (EqtypeOrderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderRowViaOrderId()}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public EqtypeOrderDao fetchEqtypeOrderDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeOrderPK pk = new EqtypeOrderPK( row.getOrderId() );
        DataAccessObject dao = EqtypeOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeOrderDao) dao;
    }


  /** 
   * この{@@link EqtypeClosingContractSpecDao}に紐付く{@@link EqtypeClosingContractSpecRow}内で外部キーの関係をもつ{@@link EqtypeOrderUnitRow}を検索します。 
   * 
   * @@return {@@link EqtypeClosingContractSpecDao}と外部キーの関係にある{@@link EqtypeOrderUnitRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public EqtypeOrderUnitRow fetchEqtypeOrderUnitRowViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeOrderUnitPK pk = new EqtypeOrderUnitPK( row.getOrderUnitId() );
        Row row = EqtypeOrderUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof EqtypeOrderUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (EqtypeOrderUnitRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderUnitRowViaOrderUnitId()}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public EqtypeOrderUnitDao fetchEqtypeOrderUnitDaoViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeOrderUnitPK pk = new EqtypeOrderUnitPK( row.getOrderUnitId() );
        DataAccessObject dao = EqtypeOrderUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeOrderUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeOrderUnitDao) dao;
    }


  /** 
   * この{@@link EqtypeClosingContractSpecDao}に紐付く{@@link EqtypeClosingContractSpecRow}内で外部キーの関係をもつ{@@link EqtypeContractRow}を検索します。 
   * 
   * @@return {@@link EqtypeClosingContractSpecDao}と外部キーの関係にある{@@link EqtypeContractRow} 
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
   * @@deprecated 代わりに{@@link #fetchEqtypeContractRowViaContractId()}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link EqtypeClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link EqtypeClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for EqtypeOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderId(EqtypeOrderRow)}を使用してください。 
   */
    public static List findRowsByOrderId( EqtypeOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getEqtypeOrderRow() );
    }


  /** 
   * {@@link EqtypeOrderRow}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeOrderRow}オブジェクト 
   * @@return 指定の{@@link EqtypeOrderRow}に外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( EqtypeOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link EqtypeOrderPK}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeOrderPK}オブジェクト 
   * @@return {@@link EqtypeOrderPK}と外部キーが一致する値を持つ{@@link EqtypeClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( EqtypeOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderId(EqtypeOrderRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( EqtypeOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(EqtypeOrderRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( EqtypeOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(EqtypeOrderPK)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( EqtypeOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for EqtypeOrderUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderUnitId(EqtypeOrderUnitRow)}を使用してください。 
   */
    public static List findRowsByOrderUnitId( EqtypeOrderUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( dao.getEqtypeOrderUnitRow() );
    }


  /** 
   * {@@link EqtypeOrderUnitRow}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeOrderUnitRow}オブジェクト 
   * @@return 指定の{@@link EqtypeOrderUnitRow}に外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( EqtypeOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( row.getOrderUnitId() );
    }


  /** 
   * {@@link EqtypeOrderUnitPK}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeOrderUnitPK}オブジェクト 
   * @@return {@@link EqtypeOrderUnitPK}と外部キーが一致する値を持つ{@@link EqtypeClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( EqtypeOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( pk.order_unit_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( long p_orderUnitId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { new Long(p_orderUnitId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeOrderUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderUnitId(EqtypeOrderUnitRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( EqtypeOrderUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(EqtypeOrderUnitRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( EqtypeOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(EqtypeOrderUnitPK)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( EqtypeOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( pk.order_unit_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( p_orderUnitId ) );
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
   * {@@link EqtypeContractRow}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeContractRow}オブジェクト 
   * @@return 指定の{@@link EqtypeContractRow}に外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( EqtypeContractRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( row.getContractId() );
    }


  /** 
   * {@@link EqtypeContractPK}と外部キーの関係にある{@@link EqtypeClosingContractSpecRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeContractPK}オブジェクト 
   * @@return {@@link EqtypeContractPK}と外部キーが一致する値を持つ{@@link EqtypeClosingContractSpecRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( EqtypeContractPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( pk.contract_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( long p_contractId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "contract_id=?",
            null,
            new Object[] { new Long(p_contractId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeContract
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByContractId(EqtypeContractRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(EqtypeContractRow)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(EqtypeContractPK)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( pk.contract_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
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
   * p_closingContractSpecId, and にて指定の値から一意の{@@link EqtypeClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_closingContractSpecId 検索対象であるp_closingContractSpecIdフィールドの値
   * 
   * @@return 引数指定のp_closingContractSpecId, and の値と一致する{@@link EqtypeClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeClosingContractSpecRow findRowByClosingContractSpecId( long p_closingContractSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "closing_contract_spec_id=?",
            null,
            new Object[] { new Long(p_closingContractSpecId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeClosingContractSpecDao.findRowsByClosingContractSpecId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByClosingContractSpecId(long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static EqtypeClosingContractSpecDao findDaoByClosingContractSpecId( long p_closingContractSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByClosingContractSpecId( p_closingContractSpecId ) );
    }


  /** 
   * p_orderUnitId, p_contractId, and にて指定の値から一意の{@@link EqtypeClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のp_orderUnitId, p_contractId, and の値と一致する{@@link EqtypeClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeClosingContractSpecRow findRowByOrderUnitIdContractId( long p_orderUnitId, long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "order_unit_id=? and contract_id=?",
            null,
            new Object[] { new Long(p_orderUnitId), new Long(p_contractId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeClosingContractSpecDao.findRowsByOrderUnitIdContractId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderUnitIdContractId(long, long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static EqtypeClosingContractSpecDao findDaoByOrderUnitIdContractId( long p_orderUnitId, long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderUnitIdContractId( p_orderUnitId, p_contractId ) );
    }


  /** 
   * p_orderUnitId, p_closingSerialNo, and にて指定の値から一意の{@@link EqtypeClosingContractSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_closingSerialNo 検索対象であるp_closingSerialNoフィールドの値
   * 
   * @@return 引数指定のp_orderUnitId, p_closingSerialNo, and の値と一致する{@@link EqtypeClosingContractSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeClosingContractSpecRow findRowByOrderUnitIdClosingSerialNo( long p_orderUnitId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "order_unit_id=? and closing_serial_no=?",
            null,
            new Object[] { new Long(p_orderUnitId), new Integer(p_closingSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeClosingContractSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeClosingContractSpecDao.findRowsByOrderUnitIdClosingSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderUnitIdClosingSerialNo(long, int)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static EqtypeClosingContractSpecDao findDaoByOrderUnitIdClosingSerialNo( long p_orderUnitId, int p_closingSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderUnitIdClosingSerialNo( p_orderUnitId, p_closingSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, p_orderId, p_orderUnitId, p_contractId, and にて指定の値に一致する{@@link EqtypeClosingContractSpecRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_orderId, p_orderUnitId, p_contractId, and の値と一致する{@@link EqtypeClosingContractSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountIdOrderIdOrderUnitIdContractId( long p_accountId, long p_subAccountId, long p_orderId, long p_orderUnitId, long p_contractId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeClosingContractSpecRow.TYPE,
            "account_id=? and sub_account_id=? and order_id=? and order_unit_id=? and contract_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Long(p_orderId), new Long(p_orderUnitId), new Long(p_contractId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountIdOrderIdOrderUnitIdContractId(long, long, long, long, long)}および{@@link #forRow(EqtypeClosingContractSpecRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountIdOrderIdOrderUnitIdContractId( long p_accountId, long p_subAccountId, long p_orderId, long p_orderUnitId, long p_contractId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdSubAccountIdOrderIdOrderUnitIdContractId( p_accountId, p_subAccountId, p_orderId, p_orderUnitId, p_contractId ) );
    }

}
@
