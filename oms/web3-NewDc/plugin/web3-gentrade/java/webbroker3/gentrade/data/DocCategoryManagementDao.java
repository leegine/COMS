head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocCategoryManagementDao.java;


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
 * {@@link DocCategoryManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DocCategoryManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see DocCategoryManagementPK 
 * @@see DocCategoryManagementRow 
 */
public class DocCategoryManagementDao extends DataAccessObject {


  /** 
   * この{@@link DocCategoryManagementDao}に関連する型指定のRowオブジェクト 
   */
    private DocCategoryManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DocCategoryManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link DocCategoryManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DocCategoryManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DocCategoryManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocCategoryManagementRow )
                return new DocCategoryManagementDao( (DocCategoryManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocCategoryManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocCategoryManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DocCategoryManagementRow}オブジェクト 
    */
    protected DocCategoryManagementDao( DocCategoryManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DocCategoryManagementRow}オブジェクトを取得します。
   */
    public DocCategoryManagementRow getDocCategoryManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link DocCategoryManagementRow}オブジェクトから{@@link DocCategoryManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link DocCategoryManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DocCategoryManagementDao}取得のために指定の{@@link DocCategoryManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DocCategoryManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DocCategoryManagementDao forRow( DocCategoryManagementRow row ) throws java.lang.IllegalArgumentException {
        return (DocCategoryManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocCategoryManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DocCategoryManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DocCategoryManagementPK}やデータベースレコードとして挿入される{@@link DocCategoryManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocCategoryManagementRow.TYPE );
    }


  /** 
   * {@@link DocCategoryManagementRow}を一意に特定する{@@link DocCategoryManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DocCategoryManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DocCategoryManagementParams}オブジェクトの主キーとして利用可能な{@@link DocCategoryManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DocCategoryManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DocCategoryManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocCategoryManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocCategoryManagementRow findRowByPk( String p_institutionCode, String p_branchCode, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocCategoryManagementPK pk = new DocCategoryManagementPK( p_institutionCode, p_branchCode, p_documentCategory );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDocCategoryManagementPKオブジェクトから{@@link DocCategoryManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDocCategoryManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocCategoryManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocCategoryManagementRow findRowByPk( DocCategoryManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocCategoryManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(DocCategoryManagementRow)}を使用してください。 
   */
    public static DocCategoryManagementDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocCategoryManagementPK pk = new DocCategoryManagementPK( p_institutionCode, p_branchCode, p_documentCategory );
        DocCategoryManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DocCategoryManagementPK)}および{@@link #forRow(DocCategoryManagementRow)}を使用してください。 
   */
    public static DocCategoryManagementDao findDaoByPk( DocCategoryManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocCategoryManagementRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_documentCategory, and にて指定の値から一意の{@@link DocCategoryManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_documentCategory, and の値と一致する{@@link DocCategoryManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DocCategoryManagementRow findRowByInstitutionCodeBranchCodeDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocCategoryManagementRow.TYPE,
            "institution_code=? and branch_code=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocCategoryManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocCategoryManagementDao.findRowsByInstitutionCodeBranchCodeDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeDocumentCategory(String, String, String)}および{@@link #forRow(DocCategoryManagementRow)}を使用してください。 
   */
    public static DocCategoryManagementDao findDaoByInstitutionCodeBranchCodeDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDocumentCategory( p_institutionCode, p_branchCode, p_documentCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
