head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommAccountSendDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommAccountSendDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CommAccountSendRow}インスタンスへ関連付けることができます。 
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
 * @@see CommAccountSendPK 
 * @@see CommAccountSendRow 
 */
public class CommAccountSendDao extends DataAccessObject {


  /** 
   * この{@@link CommAccountSendDao}に関連する型指定のRowオブジェクト 
   */
    private CommAccountSendRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CommAccountSendRow}と仮定される{@@link DataAccessObject}から新たに{@@link CommAccountSendDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CommAccountSendDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CommAccountSendRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommAccountSendRow )
                return new CommAccountSendDao( (CommAccountSendRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommAccountSendRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommAccountSendRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CommAccountSendRow}オブジェクト 
    */
    protected CommAccountSendDao( CommAccountSendRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CommAccountSendRow}オブジェクトを取得します。
   */
    public CommAccountSendRow getCommAccountSendRow() {
        return row;
    }


  /** 
   * 指定の{@@link CommAccountSendRow}オブジェクトから{@@link CommAccountSendDao}オブジェクトを作成します。 
   * これは実際の{@@link CommAccountSendRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CommAccountSendDao}取得のために指定の{@@link CommAccountSendRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CommAccountSendDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CommAccountSendDao forRow( CommAccountSendRow row ) throws java.lang.IllegalArgumentException {
        return (CommAccountSendDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommAccountSendRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CommAccountSendRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CommAccountSendPK}やデータベースレコードとして挿入される{@@link CommAccountSendParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommAccountSendRow.TYPE );
    }


  /** 
   * {@@link CommAccountSendRow}を一意に特定する{@@link CommAccountSendPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CommAccountSendRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CommAccountSendParams}オブジェクトの主キーとして利用可能な{@@link CommAccountSendPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CommAccountSendPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CommAccountSendRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommAccountSendRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommAccountSendRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        CommAccountSendPK pk = new CommAccountSendPK( p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCommAccountSendPKオブジェクトから{@@link CommAccountSendRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCommAccountSendPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommAccountSendRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommAccountSendRow findRowByPk( CommAccountSendPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommAccountSendRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(CommAccountSendRow)}を使用してください。 
   */
    public static CommAccountSendDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        CommAccountSendPK pk = new CommAccountSendPK( p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate );
        CommAccountSendRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CommAccountSendPK)}および{@@link #forRow(CommAccountSendRow)}を使用してください。 
   */
    public static CommAccountSendDao findDaoByPk( CommAccountSendPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommAccountSendRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate, and にて指定の値から一意の{@@link CommAccountSendRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate, and の値と一致する{@@link CommAccountSendRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CommAccountSendRow findRowByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommAccountSendRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and comm_product_code=? and appli_start_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommAccountSendRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommAccountSendDao.findRowsByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate(String, String, String, String, String)}および{@@link #forRow(CommAccountSendRow)}を使用してください。 
   */
    public static CommAccountSendDao findDaoByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate( p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
