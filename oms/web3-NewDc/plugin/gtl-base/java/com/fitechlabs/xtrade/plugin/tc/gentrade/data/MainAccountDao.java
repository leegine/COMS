head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MainAccountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MainAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MainAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see MainAccountPK 
 * @@see MainAccountRow 
 */
public class MainAccountDao extends DataAccessObject {


  /** 
   * この{@@link MainAccountDao}に関連する型指定のRowオブジェクト 
   */
    private MainAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MainAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link MainAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MainAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MainAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MainAccountRow )
                return new MainAccountDao( (MainAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MainAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MainAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MainAccountRow}オブジェクト 
    */
    protected MainAccountDao( MainAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MainAccountRow}オブジェクトを取得します。
   */
    public MainAccountRow getMainAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link MainAccountRow}オブジェクトから{@@link MainAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link MainAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MainAccountDao}取得のために指定の{@@link MainAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MainAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MainAccountDao forRow( MainAccountRow row ) throws java.lang.IllegalArgumentException {
        return (MainAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MainAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MainAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MainAccountPK}やデータベースレコードとして挿入される{@@link MainAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MainAccountRow.TYPE );
    }


  /** 
   * {@@link MainAccountRow}を一意に特定する{@@link MainAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MainAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MainAccountParams}オブジェクトの主キーとして利用可能な{@@link MainAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MainAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new MainAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MainAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MainAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MainAccountRow findRowByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountPK pk = new MainAccountPK( p_accountId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMainAccountPKオブジェクトから{@@link MainAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMainAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MainAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MainAccountRow findRowByPk( MainAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MainAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static MainAccountDao findDaoByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountPK pk = new MainAccountPK( p_accountId );
        MainAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MainAccountPK)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static MainAccountDao findDaoByPk( MainAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link MainAccountDao}に紐付く{@@link MainAccountRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link MainAccountDao}と外部キーの関係にある{@@link BranchRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public BranchRow fetchBranchRowViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        Row row = BranchDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BranchRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BranchRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * この{@@link MainAccountDao}に紐付く{@@link MainAccountRow}内で外部キーの関係をもつ{@@link InstitutionRow}を検索します。 
   * 
   * @@return {@@link MainAccountDao}と外部キーの関係にある{@@link InstitutionRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public InstitutionRow fetchInstitutionRowViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        Row row = InstitutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof InstitutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (InstitutionRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchInstitutionRowViaInstitutionId()}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public InstitutionDao fetchInstitutionDaoViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        DataAccessObject dao = InstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof InstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (InstitutionDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Branch
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByBranchId(BranchRow)}を使用してください。 
   */
    public static List findRowsByBranchId( BranchDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( dao.getBranchRow() );
    }


  /** 
   * {@@link BranchRow}と外部キーの関係にある{@@link MainAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link MainAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link MainAccountRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MainAccountRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Institution
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByInstitutionId(InstitutionRow)}を使用してください。 
   */
    public static List findRowsByInstitutionId( InstitutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( dao.getInstitutionRow() );
    }


  /** 
   * {@@link InstitutionRow}と外部キーの関係にある{@@link MainAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link InstitutionRow}オブジェクト 
   * @@return 指定の{@@link InstitutionRow}に外部キーを持つ{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( row.getInstitutionId() );
    }


  /** 
   * {@@link InstitutionPK}と外部キーの関係にある{@@link MainAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link InstitutionPK}オブジェクト 
   * @@return {@@link InstitutionPK}と外部キーが一致する値を持つ{@@link MainAccountRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( pk.institution_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MainAccountRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( long p_institutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Institution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionPK)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( pk.institution_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(long)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( long p_institutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( p_institutionId ) );
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
   * p_accountId, and にて指定の値から一意の{@@link MainAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, and の値と一致する{@@link MainAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MainAccountRow findRowByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MainAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MainAccountDao.findRowsByAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountId(long)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static MainAccountDao findDaoByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountId( p_accountId ) );
    }


  /** 
   * p_institutionId, p_branchId, p_accountCode, and にて指定の値から一意の{@@link MainAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionId, p_branchId, p_accountCode, and の値と一致する{@@link MainAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MainAccountRow findRowByInstitutionIdBranchIdAccountCode( long p_institutionId, long p_branchId, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "institution_id=? and branch_id=? and account_code=?",
            null,
            new Object[] { new Long(p_institutionId), new Long(p_branchId), p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MainAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MainAccountDao.findRowsByInstitutionIdBranchIdAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionIdBranchIdAccountCode(long, long, String)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static MainAccountDao findDaoByInstitutionIdBranchIdAccountCode( long p_institutionId, long p_branchId, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionIdBranchIdAccountCode( p_institutionId, p_branchId, p_accountCode ) );
    }


  /** 
   * p_institutionId, p_branchCode, p_accountCode, and にて指定の値から一意の{@@link MainAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionId, p_branchCode, p_accountCode, and の値と一致する{@@link MainAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MainAccountRow findRowByInstitutionIdBranchCodeAccountCode( long p_institutionId, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "institution_id=? and branch_code=? and account_code=?",
            null,
            new Object[] { new Long(p_institutionId), p_branchCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MainAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MainAccountDao.findRowsByInstitutionIdBranchCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionIdBranchCodeAccountCode(long, String, String)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static MainAccountDao findDaoByInstitutionIdBranchCodeAccountCode( long p_institutionId, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionIdBranchCodeAccountCode( p_institutionId, p_branchCode, p_accountCode ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値から一意の{@@link MainAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link MainAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MainAccountRow findRowByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MainAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MainAccountDao.findRowsByInstitutionCodeBranchCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static MainAccountDao findDaoByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_branchCode, and にて指定の値に一致する{@@link MainAccountRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_branchCode, and の値と一致する{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchCode( String p_branchCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "branch_code=?",
            null,
            new Object[] { p_branchCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchCode(String)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByBranchCode( String p_branchCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByBranchCode( p_branchCode ) );
    }


  /** 
   * p_institutionCode, p_eraBorn, p_bornDate, p_familyNameAlt1, and にて指定の値に一致する{@@link MainAccountRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_eraBorn 検索対象であるp_eraBornフィールドの値
   * @@param p_bornDate 検索対象であるp_bornDateフィールドの値
   * @@param p_familyNameAlt1 検索対象であるp_familyNameAlt1フィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_eraBorn, p_bornDate, p_familyNameAlt1, and の値と一致する{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeEraBornBornDateFamilyNameAlt1( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_familyNameAlt1 ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "institution_code=? and era_born=? and born_date=? and family_name_alt1=?",
            null,
            new Object[] { p_institutionCode, p_eraBorn, p_bornDate, p_familyNameAlt1 } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeEraBornBornDateFamilyNameAlt1(String, String, String, String)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeEraBornBornDateFamilyNameAlt1( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_familyNameAlt1 ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeEraBornBornDateFamilyNameAlt1( p_institutionCode, p_eraBorn, p_bornDate, p_familyNameAlt1 ) );
    }


  /** 
   * p_emailAddress, and にて指定の値に一致する{@@link MainAccountRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_emailAddress 検索対象であるp_emailAddressフィールドの値
   * 
   * @@return 引数指定のp_emailAddress, and の値と一致する{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByEmailAddress( String p_emailAddress ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "email_address=?",
            null,
            new Object[] { p_emailAddress } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByEmailAddress(String)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByEmailAddress( String p_emailAddress ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByEmailAddress( p_emailAddress ) );
    }


  /** 
   * p_telephone, and にて指定の値に一致する{@@link MainAccountRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_telephone 検索対象であるp_telephoneフィールドの値
   * 
   * @@return 引数指定のp_telephone, and の値と一致する{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTelephone( String p_telephone ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "telephone=?",
            null,
            new Object[] { p_telephone } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTelephone(String)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByTelephone( String p_telephone ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTelephone( p_telephone ) );
    }


  /** 
   * p_mobile, and にて指定の値に一致する{@@link MainAccountRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_mobile 検索対象であるp_mobileフィールドの値
   * 
   * @@return 引数指定のp_mobile, and の値と一致する{@@link MainAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMobile( String p_mobile ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountRow.TYPE,
            "mobile=?",
            null,
            new Object[] { p_mobile } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMobile(String)}および{@@link #forRow(MainAccountRow)}を使用してください。 
   */
    public static List findDaosByMobile( String p_mobile ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMobile( p_mobile ) );
    }

}
@
