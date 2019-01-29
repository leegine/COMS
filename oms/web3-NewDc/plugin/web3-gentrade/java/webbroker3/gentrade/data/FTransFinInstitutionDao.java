head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FTransFinInstitutionDao.java;


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
 * {@@link FTransFinInstitutionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FTransFinInstitutionRow}インスタンスへ関連付けることができます。 
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
 * @@see FTransFinInstitutionPK 
 * @@see FTransFinInstitutionRow 
 */
public class FTransFinInstitutionDao extends DataAccessObject {


  /** 
   * この{@@link FTransFinInstitutionDao}に関連する型指定のRowオブジェクト 
   */
    private FTransFinInstitutionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FTransFinInstitutionRow}と仮定される{@@link DataAccessObject}から新たに{@@link FTransFinInstitutionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FTransFinInstitutionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FTransFinInstitutionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FTransFinInstitutionRow )
                return new FTransFinInstitutionDao( (FTransFinInstitutionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FTransFinInstitutionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FTransFinInstitutionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FTransFinInstitutionRow}オブジェクト 
    */
    protected FTransFinInstitutionDao( FTransFinInstitutionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FTransFinInstitutionRow}オブジェクトを取得します。
   */
    public FTransFinInstitutionRow getFTransFinInstitutionRow() {
        return row;
    }


  /** 
   * 指定の{@@link FTransFinInstitutionRow}オブジェクトから{@@link FTransFinInstitutionDao}オブジェクトを作成します。 
   * これは実際の{@@link FTransFinInstitutionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FTransFinInstitutionDao}取得のために指定の{@@link FTransFinInstitutionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FTransFinInstitutionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FTransFinInstitutionDao forRow( FTransFinInstitutionRow row ) throws java.lang.IllegalArgumentException {
        return (FTransFinInstitutionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FTransFinInstitutionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FTransFinInstitutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FTransFinInstitutionPK}やデータベースレコードとして挿入される{@@link FTransFinInstitutionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FTransFinInstitutionRow.TYPE );
    }


  /** 
   * {@@link FTransFinInstitutionRow}を一意に特定する{@@link FTransFinInstitutionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FTransFinInstitutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FTransFinInstitutionParams}オブジェクトの主キーとして利用可能な{@@link FTransFinInstitutionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FTransFinInstitutionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FTransFinInstitutionRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_currencyCode 検索対象であるp_currencyCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FTransFinInstitutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FTransFinInstitutionRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FTransFinInstitutionPK pk = new FTransFinInstitutionPK( p_institutionCode, p_branchCode, p_accountCode, p_currencyCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFTransFinInstitutionPKオブジェクトから{@@link FTransFinInstitutionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFTransFinInstitutionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FTransFinInstitutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FTransFinInstitutionRow findRowByPk( FTransFinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FTransFinInstitutionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(FTransFinInstitutionRow)}を使用してください。 
   */
    public static FTransFinInstitutionDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FTransFinInstitutionPK pk = new FTransFinInstitutionPK( p_institutionCode, p_branchCode, p_accountCode, p_currencyCode );
        FTransFinInstitutionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FTransFinInstitutionPK)}および{@@link #forRow(FTransFinInstitutionRow)}を使用してください。 
   */
    public static FTransFinInstitutionDao findDaoByPk( FTransFinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FTransFinInstitutionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_currencyCode, and にて指定の値から一意の{@@link FTransFinInstitutionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_currencyCode 検索対象であるp_currencyCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_currencyCode, and の値と一致する{@@link FTransFinInstitutionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FTransFinInstitutionRow findRowByInstitutionCodeBranchCodeAccountCodeCurrencyCode( String p_institutionCode, String p_branchCode, String p_accountCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FTransFinInstitutionRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and currency_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_currencyCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FTransFinInstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FTransFinInstitutionDao.findRowsByInstitutionCodeBranchCodeAccountCodeCurrencyCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeCurrencyCode(String, String, String, String)}および{@@link #forRow(FTransFinInstitutionRow)}を使用してください。 
   */
    public static FTransFinInstitutionDao findDaoByInstitutionCodeBranchCodeAccountCodeCurrencyCode( String p_institutionCode, String p_branchCode, String p_accountCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeCurrencyCode( p_institutionCode, p_branchCode, p_accountCode, p_currencyCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
