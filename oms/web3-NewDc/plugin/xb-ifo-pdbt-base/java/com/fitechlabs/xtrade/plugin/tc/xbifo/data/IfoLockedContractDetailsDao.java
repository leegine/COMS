head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.07.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoLockedContractDetailsDao.java;


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
 * {@@link IfoLockedContractDetailsDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoLockedContractDetailsRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoLockedContractDetailsPK 
 * @@see IfoLockedContractDetailsRow 
 */
public class IfoLockedContractDetailsDao extends DataAccessObject {


  /** 
   * この{@@link IfoLockedContractDetailsDao}に関連する型指定のRowオブジェクト 
   */
    private IfoLockedContractDetailsRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoLockedContractDetailsRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoLockedContractDetailsDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoLockedContractDetailsDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoLockedContractDetailsRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoLockedContractDetailsRow )
                return new IfoLockedContractDetailsDao( (IfoLockedContractDetailsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoLockedContractDetailsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoLockedContractDetailsRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoLockedContractDetailsRow}オブジェクト 
    */
    protected IfoLockedContractDetailsDao( IfoLockedContractDetailsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoLockedContractDetailsRow}オブジェクトを取得します。
   */
    public IfoLockedContractDetailsRow getIfoLockedContractDetailsRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoLockedContractDetailsRow}オブジェクトから{@@link IfoLockedContractDetailsDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoLockedContractDetailsRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoLockedContractDetailsDao}取得のために指定の{@@link IfoLockedContractDetailsRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoLockedContractDetailsDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoLockedContractDetailsDao forRow( IfoLockedContractDetailsRow row ) throws java.lang.IllegalArgumentException {
        return (IfoLockedContractDetailsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoLockedContractDetailsRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoLockedContractDetailsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoLockedContractDetailsPK}やデータベースレコードとして挿入される{@@link IfoLockedContractDetailsParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoLockedContractDetailsRow.TYPE );
    }


  /** 
   * {@@link IfoLockedContractDetailsRow}を一意に特定する{@@link IfoLockedContractDetailsPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoLockedContractDetailsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoLockedContractDetailsParams}オブジェクトの主キーとして利用可能な{@@link IfoLockedContractDetailsPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoLockedContractDetailsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoLockedContractDetailsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoLockedContractDetailsRow}オブジェクトを検索します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoLockedContractDetailsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoLockedContractDetailsRow findRowByPk( long p_contractId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLockedContractDetailsPK pk = new IfoLockedContractDetailsPK( p_contractId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoLockedContractDetailsPKオブジェクトから{@@link IfoLockedContractDetailsRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoLockedContractDetailsPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoLockedContractDetailsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoLockedContractDetailsRow findRowByPk( IfoLockedContractDetailsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoLockedContractDetailsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static IfoLockedContractDetailsDao findDaoByPk( long p_contractId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLockedContractDetailsPK pk = new IfoLockedContractDetailsPK( p_contractId );
        IfoLockedContractDetailsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoLockedContractDetailsPK)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static IfoLockedContractDetailsDao findDaoByPk( IfoLockedContractDetailsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLockedContractDetailsRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link IfoLockedContractDetailsDao}に紐付く{@@link IfoLockedContractDetailsRow}内で外部キーの関係をもつ{@@link IfoContractRow}を検索します。 
   * 
   * @@return {@@link IfoLockedContractDetailsDao}と外部キーの関係にある{@@link IfoContractRow} 
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
   * @@deprecated 代わりに{@@link #fetchIfoContractRowViaContractId()}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public IfoContractDao fetchIfoContractDaoViaContractId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoContractPK pk = new IfoContractPK( row.getContractId() );
        DataAccessObject dao = IfoContractDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoContractDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoContractDao) dao;
    }


  /** 
   * この{@@link IfoLockedContractDetailsDao}に紐付く{@@link IfoLockedContractDetailsRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link IfoLockedContractDetailsDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
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
    // Find Rows given foreign key values for IfoContract
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByContractId(IfoContractRow)}を使用してください。 
   */
    public static List findRowsByContractId( IfoContractDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( dao.getIfoContractRow() );
    }


  /** 
   * {@@link IfoContractRow}と外部キーの関係にある{@@link IfoLockedContractDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IfoContractRow}オブジェクト 
   * @@return 指定の{@@link IfoContractRow}に外部キーを持つ{@@link IfoLockedContractDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( IfoContractRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( row.getContractId() );
    }


  /** 
   * {@@link IfoContractPK}と外部キーの関係にある{@@link IfoLockedContractDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IfoContractPK}オブジェクト 
   * @@return {@@link IfoContractPK}と外部キーが一致する値を持つ{@@link IfoLockedContractDetailsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( IfoContractPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( pk.contract_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoLockedContractDetailsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoLockedContractDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( long p_contractId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoLockedContractDetailsRow.TYPE,
            "contract_id=?",
            null,
            new Object[] { new Long(p_contractId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoContract
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByContractId(IfoContractRow)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(IfoContractRow)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(IfoContractPK)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByContractId( IfoContractPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( pk.contract_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(long)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByContractId( long p_contractId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( p_contractId ) );
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link IfoLockedContractDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link IfoLockedContractDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link IfoLockedContractDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link IfoLockedContractDetailsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoLockedContractDetailsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoLockedContractDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoLockedContractDetailsRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
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
   * p_contractId, and にて指定の値から一意の{@@link IfoLockedContractDetailsRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のp_contractId, and の値と一致する{@@link IfoLockedContractDetailsRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoLockedContractDetailsRow findRowByContractId( long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoLockedContractDetailsRow.TYPE,
            "contract_id=?",
            null,
            new Object[] { new Long(p_contractId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoLockedContractDetailsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoLockedContractDetailsDao.findRowsByContractId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByContractId(long)}および{@@link #forRow(IfoLockedContractDetailsRow)}を使用してください。 
   */
    public static IfoLockedContractDetailsDao findDaoByContractId( long p_contractId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByContractId( p_contractId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

}
@
