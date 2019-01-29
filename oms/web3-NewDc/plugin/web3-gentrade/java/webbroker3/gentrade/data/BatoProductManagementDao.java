head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.43.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoProductManagementDao.java;


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
 * {@@link BatoProductManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BatoProductManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see BatoProductManagementPK 
 * @@see BatoProductManagementRow 
 */
public class BatoProductManagementDao extends DataAccessObject {


  /** 
   * この{@@link BatoProductManagementDao}に関連する型指定のRowオブジェクト 
   */
    private BatoProductManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BatoProductManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link BatoProductManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BatoProductManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BatoProductManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatoProductManagementRow )
                return new BatoProductManagementDao( (BatoProductManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatoProductManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatoProductManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BatoProductManagementRow}オブジェクト 
    */
    protected BatoProductManagementDao( BatoProductManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BatoProductManagementRow}オブジェクトを取得します。
   */
    public BatoProductManagementRow getBatoProductManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link BatoProductManagementRow}オブジェクトから{@@link BatoProductManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link BatoProductManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BatoProductManagementDao}取得のために指定の{@@link BatoProductManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BatoProductManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BatoProductManagementDao forRow( BatoProductManagementRow row ) throws java.lang.IllegalArgumentException {
        return (BatoProductManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatoProductManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BatoProductManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BatoProductManagementPK}やデータベースレコードとして挿入される{@@link BatoProductManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatoProductManagementRow.TYPE );
    }


  /** 
   * {@@link BatoProductManagementRow}を一意に特定する{@@link BatoProductManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BatoProductManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BatoProductManagementParams}オブジェクトの主キーとして利用可能な{@@link BatoProductManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BatoProductManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BatoProductManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_batoProductCode 検索対象であるp_batoProductCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatoProductManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatoProductManagementRow findRowByPk( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_batoProductCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoProductManagementPK pk = new BatoProductManagementPK( p_institutionCode, p_branchCode, p_documentDiv, p_batoProductCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBatoProductManagementPKオブジェクトから{@@link BatoProductManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBatoProductManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatoProductManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatoProductManagementRow findRowByPk( BatoProductManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatoProductManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(BatoProductManagementRow)}を使用してください。 
   */
    public static BatoProductManagementDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_batoProductCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoProductManagementPK pk = new BatoProductManagementPK( p_institutionCode, p_branchCode, p_documentDiv, p_batoProductCode );
        BatoProductManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BatoProductManagementPK)}および{@@link #forRow(BatoProductManagementRow)}を使用してください。 
   */
    public static BatoProductManagementDao findDaoByPk( BatoProductManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoProductManagementRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_documentDiv, p_batoProductCode, and にて指定の値から一意の{@@link BatoProductManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_batoProductCode 検索対象であるp_batoProductCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_documentDiv, p_batoProductCode, and の値と一致する{@@link BatoProductManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BatoProductManagementRow findRowByInstitutionCodeBranchCodeDocumentDivBatoProductCode( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_batoProductCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatoProductManagementRow.TYPE,
            "institution_code=? and branch_code=? and document_div=? and bato_product_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_documentDiv, p_batoProductCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatoProductManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatoProductManagementDao.findRowsByInstitutionCodeBranchCodeDocumentDivBatoProductCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeDocumentDivBatoProductCode(String, String, String, String)}および{@@link #forRow(BatoProductManagementRow)}を使用してください。 
   */
    public static BatoProductManagementDao findDaoByInstitutionCodeBranchCodeDocumentDivBatoProductCode( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_batoProductCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDocumentDivBatoProductCode( p_institutionCode, p_branchCode, p_documentDiv, p_batoProductCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
