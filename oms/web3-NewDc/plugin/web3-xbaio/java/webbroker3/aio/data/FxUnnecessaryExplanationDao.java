head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.45.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	FxUnnecessaryExplanationDao.java;


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
 * {@@link FxUnnecessaryExplanationDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FxUnnecessaryExplanationRow}インスタンスへ関連付けることができます。 
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
 * @@see FxUnnecessaryExplanationPK 
 * @@see FxUnnecessaryExplanationRow 
 */
public class FxUnnecessaryExplanationDao extends DataAccessObject {


  /** 
   * この{@@link FxUnnecessaryExplanationDao}に関連する型指定のRowオブジェクト 
   */
    private FxUnnecessaryExplanationRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FxUnnecessaryExplanationRow}と仮定される{@@link DataAccessObject}から新たに{@@link FxUnnecessaryExplanationDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FxUnnecessaryExplanationDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FxUnnecessaryExplanationRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FxUnnecessaryExplanationRow )
                return new FxUnnecessaryExplanationDao( (FxUnnecessaryExplanationRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FxUnnecessaryExplanationRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FxUnnecessaryExplanationRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FxUnnecessaryExplanationRow}オブジェクト 
    */
    protected FxUnnecessaryExplanationDao( FxUnnecessaryExplanationRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FxUnnecessaryExplanationRow}オブジェクトを取得します。
   */
    public FxUnnecessaryExplanationRow getFxUnnecessaryExplanationRow() {
        return row;
    }


  /** 
   * 指定の{@@link FxUnnecessaryExplanationRow}オブジェクトから{@@link FxUnnecessaryExplanationDao}オブジェクトを作成します。 
   * これは実際の{@@link FxUnnecessaryExplanationRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FxUnnecessaryExplanationDao}取得のために指定の{@@link FxUnnecessaryExplanationRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FxUnnecessaryExplanationDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FxUnnecessaryExplanationDao forRow( FxUnnecessaryExplanationRow row ) throws java.lang.IllegalArgumentException {
        return (FxUnnecessaryExplanationDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FxUnnecessaryExplanationRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FxUnnecessaryExplanationRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FxUnnecessaryExplanationPK}やデータベースレコードとして挿入される{@@link FxUnnecessaryExplanationParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FxUnnecessaryExplanationRow.TYPE );
    }


  /** 
   * {@@link FxUnnecessaryExplanationRow}を一意に特定する{@@link FxUnnecessaryExplanationPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FxUnnecessaryExplanationRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FxUnnecessaryExplanationParams}オブジェクトの主キーとして利用可能な{@@link FxUnnecessaryExplanationPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FxUnnecessaryExplanationPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FxUnnecessaryExplanationRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_fxSerialNo 検索対象であるp_fxSerialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FxUnnecessaryExplanationRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FxUnnecessaryExplanationRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, int p_fxSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        FxUnnecessaryExplanationPK pk = new FxUnnecessaryExplanationPK( p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFxUnnecessaryExplanationPKオブジェクトから{@@link FxUnnecessaryExplanationRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFxUnnecessaryExplanationPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FxUnnecessaryExplanationRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FxUnnecessaryExplanationRow findRowByPk( FxUnnecessaryExplanationPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FxUnnecessaryExplanationRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,int)}および{@@link #forRow(FxUnnecessaryExplanationRow)}を使用してください。 
   */
    public static FxUnnecessaryExplanationDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, int p_fxSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        FxUnnecessaryExplanationPK pk = new FxUnnecessaryExplanationPK( p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo );
        FxUnnecessaryExplanationRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FxUnnecessaryExplanationPK)}および{@@link #forRow(FxUnnecessaryExplanationRow)}を使用してください。 
   */
    public static FxUnnecessaryExplanationDao findDaoByPk( FxUnnecessaryExplanationPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FxUnnecessaryExplanationRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo, and にて指定の値から一意の{@@link FxUnnecessaryExplanationRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_fxSerialNo 検索対象であるp_fxSerialNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo, and の値と一致する{@@link FxUnnecessaryExplanationRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FxUnnecessaryExplanationRow findRowByInstitutionCodeBranchCodeAccountCodeFxSerialNo( String p_institutionCode, String p_branchCode, String p_accountCode, int p_fxSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxUnnecessaryExplanationRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and fx_serial_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, new Integer(p_fxSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxUnnecessaryExplanationRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxUnnecessaryExplanationDao.findRowsByInstitutionCodeBranchCodeAccountCodeFxSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeFxSerialNo(String, String, String, int)}および{@@link #forRow(FxUnnecessaryExplanationRow)}を使用してください。 
   */
    public static FxUnnecessaryExplanationDao findDaoByInstitutionCodeBranchCodeAccountCodeFxSerialNo( String p_institutionCode, String p_branchCode, String p_accountCode, int p_fxSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeFxSerialNo( p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link FxUnnecessaryExplanationRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link FxUnnecessaryExplanationRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FxUnnecessaryExplanationRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(FxUnnecessaryExplanationRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
