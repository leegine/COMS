head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDeliveryManagementHistDao.java;


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
 * {@@link DocDeliveryManagementHistDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DocDeliveryManagementHistRow}インスタンスへ関連付けることができます。 
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
 * @@see DocDeliveryManagementHistPK 
 * @@see DocDeliveryManagementHistRow 
 */
public class DocDeliveryManagementHistDao extends DataAccessObject {


  /** 
   * この{@@link DocDeliveryManagementHistDao}に関連する型指定のRowオブジェクト 
   */
    private DocDeliveryManagementHistRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DocDeliveryManagementHistRow}と仮定される{@@link DataAccessObject}から新たに{@@link DocDeliveryManagementHistDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DocDeliveryManagementHistDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DocDeliveryManagementHistRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocDeliveryManagementHistRow )
                return new DocDeliveryManagementHistDao( (DocDeliveryManagementHistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocDeliveryManagementHistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocDeliveryManagementHistRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DocDeliveryManagementHistRow}オブジェクト 
    */
    protected DocDeliveryManagementHistDao( DocDeliveryManagementHistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DocDeliveryManagementHistRow}オブジェクトを取得します。
   */
    public DocDeliveryManagementHistRow getDocDeliveryManagementHistRow() {
        return row;
    }


  /** 
   * 指定の{@@link DocDeliveryManagementHistRow}オブジェクトから{@@link DocDeliveryManagementHistDao}オブジェクトを作成します。 
   * これは実際の{@@link DocDeliveryManagementHistRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DocDeliveryManagementHistDao}取得のために指定の{@@link DocDeliveryManagementHistRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DocDeliveryManagementHistDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DocDeliveryManagementHistDao forRow( DocDeliveryManagementHistRow row ) throws java.lang.IllegalArgumentException {
        return (DocDeliveryManagementHistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocDeliveryManagementHistRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DocDeliveryManagementHistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DocDeliveryManagementHistPK}やデータベースレコードとして挿入される{@@link DocDeliveryManagementHistParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocDeliveryManagementHistRow.TYPE );
    }


  /** 
   * {@@link DocDeliveryManagementHistRow}を一意に特定する{@@link DocDeliveryManagementHistPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DocDeliveryManagementHistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DocDeliveryManagementHistParams}オブジェクトの主キーとして利用可能な{@@link DocDeliveryManagementHistPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DocDeliveryManagementHistPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DocDeliveryManagementHistRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_createdTimestamp 検索対象であるp_createdTimestampフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocDeliveryManagementHistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocDeliveryManagementHistRow findRowByPk( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementHistPK pk = new DocDeliveryManagementHistPK( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDocDeliveryManagementHistPKオブジェクトから{@@link DocDeliveryManagementHistRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDocDeliveryManagementHistPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocDeliveryManagementHistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocDeliveryManagementHistRow findRowByPk( DocDeliveryManagementHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocDeliveryManagementHistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,String,java.sql.Timestamp,java.sql.Timestamp,String)}および{@@link #forRow(DocDeliveryManagementHistRow)}を使用してください。 
   */
    public static DocDeliveryManagementHistDao findDaoByPk( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementHistPK pk = new DocDeliveryManagementHistPK( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory );
        DocDeliveryManagementHistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DocDeliveryManagementHistPK)}および{@@link #forRow(DocDeliveryManagementHistRow)}を使用してください。 
   */
    public static DocDeliveryManagementHistDao findDaoByPk( DocDeliveryManagementHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementHistRow row = findRowByPk( pk );
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
   * p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory, and にて指定の値から一意の{@@link DocDeliveryManagementHistRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_createdTimestamp 検索対象であるp_createdTimestampフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory, and の値と一致する{@@link DocDeliveryManagementHistRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DocDeliveryManagementHistRow findRowByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDeliveryManagementHistRow.TYPE,
            "account_id=? and document_div=? and product_code=? and delivery_date=? and created_timestamp=? and document_category=?",
            null,
            new Object[] { new Long(p_accountId), p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDeliveryManagementHistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDeliveryManagementHistDao.findRowsByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory(long, String, String, java.sql.Timestamp, java.sql.Timestamp, String)}および{@@link #forRow(DocDeliveryManagementHistRow)}を使用してください。 
   */
    public static DocDeliveryManagementHistDao findDaoByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory, and にて指定の値から一意の{@@link DocDeliveryManagementHistRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_createdTimestamp 検索対象であるp_createdTimestampフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory, and の値と一致する{@@link DocDeliveryManagementHistRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DocDeliveryManagementHistRow findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( String p_institutionCode, String p_branchCode, String p_accountCode, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDeliveryManagementHistRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and document_div=? and product_code=? and delivery_date=? and created_timestamp=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDeliveryManagementHistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDeliveryManagementHistDao.findRowsByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory(String, String, String, String, String, java.sql.Timestamp, java.sql.Timestamp, String)}および{@@link #forRow(DocDeliveryManagementHistRow)}を使用してください。 
   */
    public static DocDeliveryManagementHistDao findDaoByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( String p_institutionCode, String p_branchCode, String p_accountCode, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
