head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	FxAccountCodeDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link FxAccountCodeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FxAccountCodeRow}インスタンスへ関連付けることができます。 
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
 * @@see FxAccountCodePK 
 * @@see FxAccountCodeRow 
 */
public class FxAccountCodeDao extends DataAccessObject {


  /** 
   * この{@@link FxAccountCodeDao}に関連する型指定のRowオブジェクト 
   */
    private FxAccountCodeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FxAccountCodeRow}と仮定される{@@link DataAccessObject}から新たに{@@link FxAccountCodeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FxAccountCodeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FxAccountCodeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FxAccountCodeRow )
                return new FxAccountCodeDao( (FxAccountCodeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FxAccountCodeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FxAccountCodeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FxAccountCodeRow}オブジェクト 
    */
    protected FxAccountCodeDao( FxAccountCodeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FxAccountCodeRow}オブジェクトを取得します。
   */
    public FxAccountCodeRow getFxAccountCodeRow() {
        return row;
    }


  /** 
   * 指定の{@@link FxAccountCodeRow}オブジェクトから{@@link FxAccountCodeDao}オブジェクトを作成します。 
   * これは実際の{@@link FxAccountCodeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FxAccountCodeDao}取得のために指定の{@@link FxAccountCodeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FxAccountCodeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FxAccountCodeDao forRow( FxAccountCodeRow row ) throws java.lang.IllegalArgumentException {
        return (FxAccountCodeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FxAccountCodeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FxAccountCodeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FxAccountCodePK}やデータベースレコードとして挿入される{@@link FxAccountCodeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FxAccountCodeRow.TYPE );
    }


  /** 
   * {@@link FxAccountCodeRow}を一意に特定する{@@link FxAccountCodePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FxAccountCodeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FxAccountCodeParams}オブジェクトの主キーとして利用可能な{@@link FxAccountCodePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FxAccountCodePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FxAccountCodeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fxSystemCode 検索対象であるp_fxSystemCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_fxCourseDiv 検索対象であるp_fxCourseDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FxAccountCodeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FxAccountCodeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountCodePK pk = new FxAccountCodePK( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFxAccountCodePKオブジェクトから{@@link FxAccountCodeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFxAccountCodePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FxAccountCodeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FxAccountCodeRow findRowByPk( FxAccountCodePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FxAccountCodeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(FxAccountCodeRow)}を使用してください。 
   */
    public static FxAccountCodeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountCodePK pk = new FxAccountCodePK( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv );
        FxAccountCodeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FxAccountCodePK)}および{@@link #forRow(FxAccountCodeRow)}を使用してください。 
   */
    public static FxAccountCodeDao findDaoByPk( FxAccountCodePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountCodeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv, and にて指定の値から一意の{@@link FxAccountCodeRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fxSystemCode 検索対象であるp_fxSystemCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_fxCourseDiv 検索対象であるp_fxCourseDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv, and の値と一致する{@@link FxAccountCodeRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FxAccountCodeRow findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxAccountCodeRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=? and account_code=? and fx_course_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxAccountCodeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxAccountCodeDao.findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv(String, String, String, String, String)}および{@@link #forRow(FxAccountCodeRow)}を使用してください。 
   */
    public static FxAccountCodeDao findDaoByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_fxSystemCode, p_fxAccountCode, and にて指定の値に一致する{@@link FxAccountCodeRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fxSystemCode 検索対象であるp_fxSystemCodeフィールドの値
   * @@param p_fxAccountCode 検索対象であるp_fxAccountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_fxSystemCode, p_fxAccountCode, and の値と一致する{@@link FxAccountCodeRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeFxSystemCodeFxAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_fxAccountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FxAccountCodeRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=? and fx_account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode, p_fxAccountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeFxSystemCodeFxAccountCode(String, String, String, String)}および{@@link #forRow(FxAccountCodeRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeFxSystemCodeFxAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_fxAccountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeFxSystemCodeFxAccountCode( p_institutionCode, p_branchCode, p_fxSystemCode, p_fxAccountCode ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, and にて指定の値に一致する{@@link FxAccountCodeRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fxSystemCode 検索対象であるp_fxSystemCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, and の値と一致する{@@link FxAccountCodeRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FxAccountCodeRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCode(String, String, String, String)}および{@@link #forRow(FxAccountCodeRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeFxSystemCodeAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCode( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode ) );
    }

}
@
