head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.08.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoContractDao.java;


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
 * {@@link IfoContractDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoContractRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoContractPK 
 * @@see IfoContractRow 
 */
public class IfoContractDao extends DataAccessObject {


  /** 
   * この{@@link IfoContractDao}に関連する型指定のRowオブジェクト 
   */
    private IfoContractRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoContractRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoContractDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoContractDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoContractRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoContractRow )
                return new IfoContractDao( (IfoContractRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoContractRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoContractRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoContractRow}オブジェクト 
    */
    protected IfoContractDao( IfoContractRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoContractRow}オブジェクトを取得します。
   */
    public IfoContractRow getIfoContractRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoContractRow}オブジェクトから{@@link IfoContractDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoContractRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoContractDao}取得のために指定の{@@link IfoContractRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoContractDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoContractDao forRow( IfoContractRow row ) throws java.lang.IllegalArgumentException {
        return (IfoContractDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoContractRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoContractRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoContractPK}やデータベースレコードとして挿入される{@@link IfoContractParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoContractRow.TYPE );
    }


  /** 
   * {@@link IfoContractRow}を一意に特定する{@@link IfoContractPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoContractRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoContractParams}オブジェクトの主キーとして利用可能な{@@link IfoContractPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoContractPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoContractPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoContractRow}オブジェクトを検索します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoContractRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoContractRow findRowByPk( long p_contractId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoContractPK pk = new IfoContractPK( p_contractId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoContractPKオブジェクトから{@@link IfoContractRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoContractPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoContractRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoContractRow findRowByPk( IfoContractPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoContractRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static IfoContractDao findDaoByPk( long p_contractId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoContractPK pk = new IfoContractPK( p_contractId );
        IfoContractRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoContractPK)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static IfoContractDao findDaoByPk( IfoContractPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoContractRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link IfoContractDao}に紐付く{@@link IfoContractRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link IfoContractDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link IfoContractDao}に紐付く{@@link IfoContractRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link IfoContractDao}と外部キーの関係にある{@@link MarketRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public MarketRow fetchMarketRowViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        Row row = MarketDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MarketRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MarketRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


  /** 
   * この{@@link IfoContractDao}に紐付く{@@link IfoContractRow}内で外部キーの関係をもつ{@@link IfoProductRow}を検索します。 
   * 
   * @@return {@@link IfoContractDao}と外部キーの関係にある{@@link IfoProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public IfoProductRow fetchIfoProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoProductPK pk = new IfoProductPK( row.getProductId() );
        Row row = IfoProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IfoProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IfoProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoProductRowViaProductId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public IfoProductDao fetchIfoProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoProductPK pk = new IfoProductPK( row.getProductId() );
        DataAccessObject dao = IfoProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoProductDao) dao;
    }


  /** 
   * この{@@link IfoContractDao}に紐付く{@@link IfoContractRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link IfoContractDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
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
   * この{@@link IfoContractDao}に関連する{@@link IfoContractRow}の外部キーがある{@@link IfoLockedContractDetailsRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link IfoContractDao}に関連する{@@link IfoContractRow}の外部キーがある{@@link IfoLockedContractDetailsRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchIfoLockedContractDetailsRowsByContractId() throws DataNetworkException, DataQueryException  {
        return IfoLockedContractDetailsDao.findRowsByContractId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoLockedContractDetailsRowsByContractId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public List fetchIfoLockedContractDetailsDaosByContractId() throws DataNetworkException, DataQueryException  {
        return IfoLockedContractDetailsDao.findDaosByContractId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoLockedContractDetailsRowsByContractId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public List fetchIfoLockedContractDetailsDaosContractId() throws DataNetworkException, DataQueryException  {
        return fetchIfoLockedContractDetailsDaosByContractId();
    }


  /** 
   * この{@@link IfoContractDao}に関連する{@@link IfoContractRow}の外部キーがある{@@link IfoClosingContractSpecRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link IfoContractDao}に関連する{@@link IfoContractRow}の外部キーがある{@@link IfoClosingContractSpecRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchIfoClosingContractSpecRowsByContractId() throws DataNetworkException, DataQueryException  {
        return IfoClosingContractSpecDao.findRowsByContractId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoClosingContractSpecRowsByContractId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public List fetchIfoClosingContractSpecDaosByContractId() throws DataNetworkException, DataQueryException  {
        return IfoClosingContractSpecDao.findDaosByContractId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoClosingContractSpecRowsByContractId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public List fetchIfoClosingContractSpecDaosContractId() throws DataNetworkException, DataQueryException  {
        return fetchIfoClosingContractSpecDaosByContractId();
    }


  /** 
   * この{@@link IfoContractDao}に関連する{@@link IfoContractRow}の外部キーがある{@@link IfoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link IfoContractDao}に関連する{@@link IfoContractRow}の外部キーがある{@@link IfoFinTransactionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchIfoFinTransactionRowsByContractId() throws DataNetworkException, DataQueryException  {
        return IfoFinTransactionDao.findRowsByContractId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoFinTransactionRowsByContractId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public List fetchIfoFinTransactionDaosByContractId() throws DataNetworkException, DataQueryException  {
        return IfoFinTransactionDao.findDaosByContractId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoFinTransactionRowsByContractId()}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public List fetchIfoFinTransactionDaosContractId() throws DataNetworkException, DataQueryException  {
        return fetchIfoFinTransactionDaosByContractId();
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link IfoContractRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link IfoContractRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link IfoContractRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link IfoContractRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoContractRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoContractRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoContractRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Market
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByMarketId(MarketRow)}を使用してください。 
   */
    public static List findRowsByMarketId( MarketDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( dao.getMarketRow() );
    }


  /** 
   * {@@link MarketRow}と外部キーの関係にある{@@link IfoContractRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link IfoContractRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link IfoContractRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link IfoContractRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoContractRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoContractRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoContractRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for IfoProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(IfoProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( IfoProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getIfoProductRow() );
    }


  /** 
   * {@@link IfoProductRow}と外部キーの関係にある{@@link IfoContractRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IfoProductRow}オブジェクト 
   * @@return 指定の{@@link IfoProductRow}に外部キーを持つ{@@link IfoContractRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( IfoProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link IfoProductPK}と外部キーの関係にある{@@link IfoContractRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IfoProductPK}オブジェクト 
   * @@return {@@link IfoProductPK}と外部キーが一致する値を持つ{@@link IfoContractRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( IfoProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoContractRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoContractRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoContractRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(IfoProductRow)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByProductId( IfoProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(IfoProductRow)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByProductId( IfoProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(IfoProductPK)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByProductId( IfoProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link IfoContractRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link IfoContractRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link IfoContractRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link IfoContractRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoContractRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoContractRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoContractRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(IfoContractRow)}を使用してください。 
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
   * p_contractId, and にて指定の値から一意の{@@link IfoContractRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のp_contractId, and の値と一致する{@@link IfoContractRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoContractRow findRowByContractId( long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoContractRow.TYPE,
            "contract_id=?",
            null,
            new Object[] { new Long(p_contractId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoContractRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoContractDao.findRowsByContractId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByContractId(long)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static IfoContractDao findDaoByContractId( long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByContractId( p_contractId ) );
    }


  /** 
   * p_accountId, p_subAccountId, p_marketId, p_contractPrice, p_contractType, p_openDate, p_closeDate, p_productId, p_deliveryDate, and にて指定の値から一意の{@@link IfoContractRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_contractPrice 検索対象であるp_contractPriceフィールドの値
   * @@param p_contractType 検索対象であるp_contractTypeフィールドの値
   * @@param p_openDate 検索対象であるp_openDateフィールドの値
   * @@param p_closeDate 検索対象であるp_closeDateフィールドの値
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_marketId, p_contractPrice, p_contractType, p_openDate, p_closeDate, p_productId, p_deliveryDate, and の値と一致する{@@link IfoContractRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoContractRow findRowByAccountIdSubAccountIdMarketIdContractPriceContractTypeOpenDateCloseDateProductIdDeliveryDate( long p_accountId, long p_subAccountId, long p_marketId, double p_contractPrice, com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum p_contractType, java.sql.Timestamp p_openDate, java.sql.Timestamp p_closeDate, long p_productId, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoContractRow.TYPE,
            "account_id=? and sub_account_id=? and market_id=? and contract_price=? and contract_type=? and open_date=? and close_date=? and product_id=? and delivery_date=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Long(p_marketId), new Double(p_contractPrice), p_contractType, p_openDate, p_closeDate, new Long(p_productId), p_deliveryDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoContractRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoContractDao.findRowsByAccountIdSubAccountIdMarketIdContractPriceContractTypeOpenDateCloseDateProductIdDeliveryDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdSubAccountIdMarketIdContractPriceContractTypeOpenDateCloseDateProductIdDeliveryDate(long, long, long, double, com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum, java.sql.Timestamp, java.sql.Timestamp, long, java.sql.Timestamp)}および{@@link #forRow(IfoContractRow)}を使用してください。 
   */
    public static IfoContractDao findDaoByAccountIdSubAccountIdMarketIdContractPriceContractTypeOpenDateCloseDateProductIdDeliveryDate( long p_accountId, long p_subAccountId, long p_marketId, double p_contractPrice, com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum p_contractType, java.sql.Timestamp p_openDate, java.sql.Timestamp p_closeDate, long p_productId, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdMarketIdContractPriceContractTypeOpenDateCloseDateProductIdDeliveryDate( p_accountId, p_subAccountId, p_marketId, p_contractPrice, p_contractType, p_openDate, p_closeDate, p_productId, p_deliveryDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

}
@
