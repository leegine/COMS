head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SecurityCashoutRestraintDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SecurityCashoutRestraintDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SecurityCashoutRestraintRow}インスタンスへ関連付けることができます。 
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
 * @@see SecurityCashoutRestraintPK 
 * @@see SecurityCashoutRestraintRow 
 */
public class SecurityCashoutRestraintDao extends DataAccessObject {


  /** 
   * この{@@link SecurityCashoutRestraintDao}に関連する型指定のRowオブジェクト 
   */
    private SecurityCashoutRestraintRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SecurityCashoutRestraintRow}と仮定される{@@link DataAccessObject}から新たに{@@link SecurityCashoutRestraintDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SecurityCashoutRestraintDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SecurityCashoutRestraintRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SecurityCashoutRestraintRow )
                return new SecurityCashoutRestraintDao( (SecurityCashoutRestraintRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SecurityCashoutRestraintRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SecurityCashoutRestraintRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SecurityCashoutRestraintRow}オブジェクト 
    */
    protected SecurityCashoutRestraintDao( SecurityCashoutRestraintRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SecurityCashoutRestraintRow}オブジェクトを取得します。
   */
    public SecurityCashoutRestraintRow getSecurityCashoutRestraintRow() {
        return row;
    }


  /** 
   * 指定の{@@link SecurityCashoutRestraintRow}オブジェクトから{@@link SecurityCashoutRestraintDao}オブジェクトを作成します。 
   * これは実際の{@@link SecurityCashoutRestraintRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SecurityCashoutRestraintDao}取得のために指定の{@@link SecurityCashoutRestraintRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SecurityCashoutRestraintDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SecurityCashoutRestraintDao forRow( SecurityCashoutRestraintRow row ) throws java.lang.IllegalArgumentException {
        return (SecurityCashoutRestraintDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SecurityCashoutRestraintRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SecurityCashoutRestraintRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SecurityCashoutRestraintPK}やデータベースレコードとして挿入される{@@link SecurityCashoutRestraintParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SecurityCashoutRestraintRow.TYPE );
    }


  /** 
   * {@@link SecurityCashoutRestraintRow}を一意に特定する{@@link SecurityCashoutRestraintPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SecurityCashoutRestraintRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SecurityCashoutRestraintParams}オブジェクトの主キーとして利用可能な{@@link SecurityCashoutRestraintPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SecurityCashoutRestraintPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SecurityCashoutRestraintPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SecurityCashoutRestraintRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SecurityCashoutRestraintRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SecurityCashoutRestraintRow findRowByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityCashoutRestraintPK pk = new SecurityCashoutRestraintPK( p_accountId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSecurityCashoutRestraintPKオブジェクトから{@@link SecurityCashoutRestraintRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSecurityCashoutRestraintPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SecurityCashoutRestraintRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SecurityCashoutRestraintRow findRowByPk( SecurityCashoutRestraintPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SecurityCashoutRestraintRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
   */
    public static SecurityCashoutRestraintDao findDaoByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityCashoutRestraintPK pk = new SecurityCashoutRestraintPK( p_accountId );
        SecurityCashoutRestraintRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SecurityCashoutRestraintPK)}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
   */
    public static SecurityCashoutRestraintDao findDaoByPk( SecurityCashoutRestraintPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityCashoutRestraintRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link SecurityCashoutRestraintDao}に紐付く{@@link SecurityCashoutRestraintRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link SecurityCashoutRestraintDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
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
    // Find Rows given foreign key values for MainAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAccountId(MainAccountRow)}を使用してください。 
   */
    public static List findRowsByAccountId( MainAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( dao.getMainAccountRow() );
    }


  /** 
   * {@@link MainAccountRow}と外部キーの関係にある{@@link SecurityCashoutRestraintRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link SecurityCashoutRestraintRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link SecurityCashoutRestraintRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link SecurityCashoutRestraintRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link SecurityCashoutRestraintRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link SecurityCashoutRestraintRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SecurityCashoutRestraintRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
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
   * p_accountId, and にて指定の値から一意の{@@link SecurityCashoutRestraintRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, and の値と一致する{@@link SecurityCashoutRestraintRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SecurityCashoutRestraintRow findRowByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SecurityCashoutRestraintRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SecurityCashoutRestraintRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SecurityCashoutRestraintDao.findRowsByAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountId(long)}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
   */
    public static SecurityCashoutRestraintDao findDaoByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountId( p_accountId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値から一意の{@@link SecurityCashoutRestraintRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link SecurityCashoutRestraintRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SecurityCashoutRestraintRow findRowByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SecurityCashoutRestraintRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SecurityCashoutRestraintRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SecurityCashoutRestraintDao.findRowsByInstitutionCodeBranchCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(SecurityCashoutRestraintRow)}を使用してください。 
   */
    public static SecurityCashoutRestraintDao findDaoByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
