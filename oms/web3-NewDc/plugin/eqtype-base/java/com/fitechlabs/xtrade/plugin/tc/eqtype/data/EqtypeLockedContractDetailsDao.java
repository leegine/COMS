head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.37.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeLockedContractDetailsDao.java;


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
 * {@@link EqtypeLockedContractDetailsDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EqtypeLockedContractDetailsRow}インスタンスへ関連付けることができます。 
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
 * @@see EqtypeLockedContractDetailsPK 
 * @@see EqtypeLockedContractDetailsRow 
 */
public class EqtypeLockedContractDetailsDao extends DataAccessObject {


  /** 
   * この{@@link EqtypeLockedContractDetailsDao}に関連する型指定のRowオブジェクト 
   */
    private EqtypeLockedContractDetailsRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EqtypeLockedContractDetailsRow}と仮定される{@@link DataAccessObject}から新たに{@@link EqtypeLockedContractDetailsDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EqtypeLockedContractDetailsDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EqtypeLockedContractDetailsRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeLockedContractDetailsRow )
                return new EqtypeLockedContractDetailsDao( (EqtypeLockedContractDetailsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeLockedContractDetailsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeLockedContractDetailsRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EqtypeLockedContractDetailsRow}オブジェクト 
    */
    protected EqtypeLockedContractDetailsDao( EqtypeLockedContractDetailsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EqtypeLockedContractDetailsRow}オブジェクトを取得します。
   */
    public EqtypeLockedContractDetailsRow getEqtypeLockedContractDetailsRow() {
        return row;
    }


  /** 
   * 指定の{@@link EqtypeLockedContractDetailsRow}オブジェクトから{@@link EqtypeLockedContractDetailsDao}オブジェクトを作成します。 
   * これは実際の{@@link EqtypeLockedContractDetailsRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EqtypeLockedContractDetailsDao}取得のために指定の{@@link EqtypeLockedContractDetailsRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EqtypeLockedContractDetailsDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EqtypeLockedContractDetailsDao forRow( EqtypeLockedContractDetailsRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeLockedContractDetailsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeLockedContractDetailsRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EqtypeLockedContractDetailsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EqtypeLockedContractDetailsPK}やデータベースレコードとして挿入される{@@link EqtypeLockedContractDetailsParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeLockedContractDetailsRow.TYPE );
    }


  /** 
   * {@@link EqtypeLockedContractDetailsRow}を一意に特定する{@@link EqtypeLockedContractDetailsPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EqtypeLockedContractDetailsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EqtypeLockedContractDetailsParams}オブジェクトの主キーとして利用可能な{@@link EqtypeLockedContractDetailsPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EqtypeLockedContractDetailsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new EqtypeLockedContractDetailsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EqtypeLockedContractDetailsRow}オブジェクトを検索します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeLockedContractDetailsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeLockedContractDetailsRow findRowByPk( long p_contractId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeLockedContractDetailsPK pk = new EqtypeLockedContractDetailsPK( p_contractId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEqtypeLockedContractDetailsPKオブジェクトから{@@link EqtypeLockedContractDetailsRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEqtypeLockedContractDetailsPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeLockedContractDetailsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeLockedContractDetailsRow findRowByPk( EqtypeLockedContractDetailsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeLockedContractDetailsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static EqtypeLockedContractDetailsDao findDaoByPk( long p_contractId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeLockedContractDetailsPK pk = new EqtypeLockedContractDetailsPK( p_contractId );
        EqtypeLockedContractDetailsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EqtypeLockedContractDetailsPK)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static EqtypeLockedContractDetailsDao findDaoByPk( EqtypeLockedContractDetailsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeLockedContractDetailsRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeLockedContractDetailsDao}に紐付く{@@link EqtypeLockedContractDetailsRow}内で外部キーの関係をもつ{@@link EqtypeContractRow}を検索します。 
   * 
   * @@return {@@link EqtypeLockedContractDetailsDao}と外部キーの関係にある{@@link EqtypeContractRow} 
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
   * @@deprecated 代わりに{@@link #fetchEqtypeContractRowViaContractId()}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public EqtypeContractDao fetchEqtypeContractDaoViaContractId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeContractPK pk = new EqtypeContractPK( row.getContractId() );
        DataAccessObject dao = EqtypeContractDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeContractDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeContractDao) dao;
    }


  /** 
   * この{@@link EqtypeLockedContractDetailsDao}に紐付く{@@link EqtypeLockedContractDetailsRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link EqtypeLockedContractDetailsDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
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
    // Find Rows given foreign key values for EqtypeContract
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByContractId(EqtypeContractRow)}を使用してください。 
   */
    public static List findRowsByContractId( EqtypeContractDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( dao.getEqtypeContractRow() );
    }


  /** 
   * {@@link EqtypeContractRow}と外部キーの関係にある{@@link EqtypeLockedContractDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeContractRow}オブジェクト 
   * @@return 指定の{@@link EqtypeContractRow}に外部キーを持つ{@@link EqtypeLockedContractDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( EqtypeContractRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( row.getContractId() );
    }


  /** 
   * {@@link EqtypeContractPK}と外部キーの関係にある{@@link EqtypeLockedContractDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeContractPK}オブジェクト 
   * @@return {@@link EqtypeContractPK}と外部キーが一致する値を持つ{@@link EqtypeLockedContractDetailsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( EqtypeContractPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByContractId( pk.contract_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeLockedContractDetailsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeLockedContractDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContractId( long p_contractId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeLockedContractDetailsRow.TYPE,
            "contract_id=?",
            null,
            new Object[] { new Long(p_contractId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeContract
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByContractId(EqtypeContractRow)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(EqtypeContractRow)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(EqtypeContractPK)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByContractId( EqtypeContractPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByContractId( pk.contract_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContractId(long)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link EqtypeLockedContractDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link EqtypeLockedContractDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link EqtypeLockedContractDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link EqtypeLockedContractDetailsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeLockedContractDetailsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeLockedContractDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeLockedContractDetailsRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
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
   * p_contractId, p_accountId, p_subAccountId, and にて指定の値から一意の{@@link EqtypeLockedContractDetailsRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_contractId 検索対象であるp_contractIdフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 引数指定のp_contractId, p_accountId, p_subAccountId, and の値と一致する{@@link EqtypeLockedContractDetailsRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeLockedContractDetailsRow findRowByContractIdAccountIdSubAccountId( long p_contractId, long p_accountId, long p_subAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeLockedContractDetailsRow.TYPE,
            "contract_id=? and account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_contractId), new Long(p_accountId), new Long(p_subAccountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeLockedContractDetailsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeLockedContractDetailsDao.findRowsByContractIdAccountIdSubAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByContractIdAccountIdSubAccountId(long, long, long)}および{@@link #forRow(EqtypeLockedContractDetailsRow)}を使用してください。 
   */
    public static EqtypeLockedContractDetailsDao findDaoByContractIdAccountIdSubAccountId( long p_contractId, long p_accountId, long p_subAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByContractIdAccountIdSubAccountId( p_contractId, p_accountId, p_subAccountId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
