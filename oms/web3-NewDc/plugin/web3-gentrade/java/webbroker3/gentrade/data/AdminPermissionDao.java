head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdminPermissionDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AdminPermissionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AdminPermissionRow}インスタンスへ関連付けることができます。 
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
 * @@see AdminPermissionPK 
 * @@see AdminPermissionRow 
 */
public class AdminPermissionDao extends DataAccessObject {


  /** 
   * この{@@link AdminPermissionDao}に関連する型指定のRowオブジェクト 
   */
    private AdminPermissionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AdminPermissionRow}と仮定される{@@link DataAccessObject}から新たに{@@link AdminPermissionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AdminPermissionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AdminPermissionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdminPermissionRow )
                return new AdminPermissionDao( (AdminPermissionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdminPermissionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdminPermissionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AdminPermissionRow}オブジェクト 
    */
    protected AdminPermissionDao( AdminPermissionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AdminPermissionRow}オブジェクトを取得します。
   */
    public AdminPermissionRow getAdminPermissionRow() {
        return row;
    }


  /** 
   * 指定の{@@link AdminPermissionRow}オブジェクトから{@@link AdminPermissionDao}オブジェクトを作成します。 
   * これは実際の{@@link AdminPermissionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AdminPermissionDao}取得のために指定の{@@link AdminPermissionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AdminPermissionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AdminPermissionDao forRow( AdminPermissionRow row ) throws java.lang.IllegalArgumentException {
        return (AdminPermissionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdminPermissionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AdminPermissionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AdminPermissionPK}やデータベースレコードとして挿入される{@@link AdminPermissionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdminPermissionRow.TYPE );
    }


  /** 
   * {@@link AdminPermissionRow}を一意に特定する{@@link AdminPermissionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AdminPermissionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AdminPermissionParams}オブジェクトの主キーとして利用可能な{@@link AdminPermissionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AdminPermissionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AdminPermissionRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_permissionLevel 検索対象であるp_permissionLevelフィールドの値
   * @@param p_transactionCategory 検索対象であるp_transactionCategoryフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdminPermissionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdminPermissionRow findRowByPk( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        AdminPermissionPK pk = new AdminPermissionPK( p_institutionCode, p_permissionLevel, p_transactionCategory );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAdminPermissionPKオブジェクトから{@@link AdminPermissionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAdminPermissionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdminPermissionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdminPermissionRow findRowByPk( AdminPermissionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdminPermissionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(AdminPermissionRow)}を使用してください。 
   */
    public static AdminPermissionDao findDaoByPk( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        AdminPermissionPK pk = new AdminPermissionPK( p_institutionCode, p_permissionLevel, p_transactionCategory );
        AdminPermissionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AdminPermissionPK)}および{@@link #forRow(AdminPermissionRow)}を使用してください。 
   */
    public static AdminPermissionDao findDaoByPk( AdminPermissionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdminPermissionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_permissionLevel, p_transactionCategory, and にて指定の値から一意の{@@link AdminPermissionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_permissionLevel 検索対象であるp_permissionLevelフィールドの値
   * @@param p_transactionCategory 検索対象であるp_transactionCategoryフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_permissionLevel, p_transactionCategory, and の値と一致する{@@link AdminPermissionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AdminPermissionRow findRowByInstitutionCodePermissionLevelTransactionCategory( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdminPermissionRow.TYPE,
            "institution_code=? and permission_level=? and transaction_category=?",
            null,
            new Object[] { p_institutionCode, p_permissionLevel, p_transactionCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdminPermissionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdminPermissionDao.findRowsByInstitutionCodePermissionLevelTransactionCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodePermissionLevelTransactionCategory(String, String, String)}および{@@link #forRow(AdminPermissionRow)}を使用してください。 
   */
    public static AdminPermissionDao findDaoByInstitutionCodePermissionLevelTransactionCategory( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodePermissionLevelTransactionCategory( p_institutionCode, p_permissionLevel, p_transactionCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
