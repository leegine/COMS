head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.25.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDivManagementDao.java;


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
 * {@@link DocDivManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DocDivManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see DocDivManagementPK 
 * @@see DocDivManagementRow 
 */
public class DocDivManagementDao extends DataAccessObject {


  /** 
   * この{@@link DocDivManagementDao}に関連する型指定のRowオブジェクト 
   */
    private DocDivManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DocDivManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link DocDivManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DocDivManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DocDivManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocDivManagementRow )
                return new DocDivManagementDao( (DocDivManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocDivManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocDivManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DocDivManagementRow}オブジェクト 
    */
    protected DocDivManagementDao( DocDivManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DocDivManagementRow}オブジェクトを取得します。
   */
    public DocDivManagementRow getDocDivManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link DocDivManagementRow}オブジェクトから{@@link DocDivManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link DocDivManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DocDivManagementDao}取得のために指定の{@@link DocDivManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DocDivManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DocDivManagementDao forRow( DocDivManagementRow row ) throws java.lang.IllegalArgumentException {
        return (DocDivManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocDivManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DocDivManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DocDivManagementPK}やデータベースレコードとして挿入される{@@link DocDivManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocDivManagementRow.TYPE );
    }


  /** 
   * {@@link DocDivManagementRow}を一意に特定する{@@link DocDivManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DocDivManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DocDivManagementParams}オブジェクトの主キーとして利用可能な{@@link DocDivManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DocDivManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DocDivManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocDivManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocDivManagementRow findRowByPk( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDivManagementPK pk = new DocDivManagementPK( p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDocDivManagementPKオブジェクトから{@@link DocDivManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDocDivManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocDivManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocDivManagementRow findRowByPk( DocDivManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocDivManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(DocDivManagementRow)}を使用してください。 
   */
    public static DocDivManagementDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDivManagementPK pk = new DocDivManagementPK( p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory );
        DocDivManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DocDivManagementPK)}および{@@link #forRow(DocDivManagementRow)}を使用してください。 
   */
    public static DocDivManagementDao findDaoByPk( DocDivManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDivManagementRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory, and にて指定の値から一意の{@@link DocDivManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_documentDiv, p_documentCategory, and の値と一致する{@@link DocDivManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DocDivManagementRow findRowByInstitutionCodeBranchCodeDocumentDivDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDivManagementRow.TYPE,
            "institution_code=? and branch_code=? and document_div=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDivManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDivManagementDao.findRowsByInstitutionCodeBranchCodeDocumentDivDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeDocumentDivDocumentCategory(String, String, String, String)}および{@@link #forRow(DocDivManagementRow)}を使用してください。 
   */
    public static DocDivManagementDao findDaoByInstitutionCodeBranchCodeDocumentDivDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDocumentDivDocumentCategory( p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_documentCheckDiv, p_documentNumber, p_documentCategory, and にて指定の値から一意の{@@link DocDivManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_documentCheckDiv 検索対象であるp_documentCheckDivフィールドの値
   * @@param p_documentNumber 検索対象であるp_documentNumberフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_documentCheckDiv, p_documentNumber, p_documentCategory, and の値と一致する{@@link DocDivManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DocDivManagementRow findRowByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentCheckDiv, String p_documentNumber, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDivManagementRow.TYPE,
            "institution_code=? and branch_code=? and document_check_div=? and document_number=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_documentCheckDiv, p_documentNumber, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDivManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDivManagementDao.findRowsByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory(String, String, String, String, String)}および{@@link #forRow(DocDivManagementRow)}を使用してください。 
   */
    public static DocDivManagementDao findDaoByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentCheckDiv, String p_documentNumber, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory( p_institutionCode, p_branchCode, p_documentCheckDiv, p_documentNumber, p_documentCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
