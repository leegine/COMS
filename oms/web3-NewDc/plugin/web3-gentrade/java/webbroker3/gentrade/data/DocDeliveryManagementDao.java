head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDeliveryManagementDao.java;


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
 * {@@link DocDeliveryManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DocDeliveryManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see DocDeliveryManagementPK 
 * @@see DocDeliveryManagementRow 
 */
public class DocDeliveryManagementDao extends DataAccessObject {


  /** 
   * この{@@link DocDeliveryManagementDao}に関連する型指定のRowオブジェクト 
   */
    private DocDeliveryManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DocDeliveryManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link DocDeliveryManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DocDeliveryManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DocDeliveryManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocDeliveryManagementRow )
                return new DocDeliveryManagementDao( (DocDeliveryManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocDeliveryManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocDeliveryManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DocDeliveryManagementRow}オブジェクト 
    */
    protected DocDeliveryManagementDao( DocDeliveryManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DocDeliveryManagementRow}オブジェクトを取得します。
   */
    public DocDeliveryManagementRow getDocDeliveryManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link DocDeliveryManagementRow}オブジェクトから{@@link DocDeliveryManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link DocDeliveryManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DocDeliveryManagementDao}取得のために指定の{@@link DocDeliveryManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DocDeliveryManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DocDeliveryManagementDao forRow( DocDeliveryManagementRow row ) throws java.lang.IllegalArgumentException {
        return (DocDeliveryManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocDeliveryManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DocDeliveryManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DocDeliveryManagementPK}やデータベースレコードとして挿入される{@@link DocDeliveryManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocDeliveryManagementRow.TYPE );
    }


  /** 
   * {@@link DocDeliveryManagementRow}を一意に特定する{@@link DocDeliveryManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DocDeliveryManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DocDeliveryManagementParams}オブジェクトの主キーとして利用可能な{@@link DocDeliveryManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DocDeliveryManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DocDeliveryManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocDeliveryManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocDeliveryManagementRow findRowByPk( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementPK pk = new DocDeliveryManagementPK( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDocDeliveryManagementPKオブジェクトから{@@link DocDeliveryManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDocDeliveryManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocDeliveryManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocDeliveryManagementRow findRowByPk( DocDeliveryManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocDeliveryManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,String,java.sql.Timestamp,String)}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
   */
    public static DocDeliveryManagementDao findDaoByPk( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementPK pk = new DocDeliveryManagementPK( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory );
        DocDeliveryManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DocDeliveryManagementPK)}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
   */
    public static DocDeliveryManagementDao findDaoByPk( DocDeliveryManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link DocDeliveryManagementDao}に紐付く{@@link DocDeliveryManagementRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link DocDeliveryManagementDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link DocDeliveryManagementRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link DocDeliveryManagementRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link DocDeliveryManagementRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link DocDeliveryManagementRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link DocDeliveryManagementRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link DocDeliveryManagementRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DocDeliveryManagementRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
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
   * p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory, and にて指定の値から一意の{@@link DocDeliveryManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory, and の値と一致する{@@link DocDeliveryManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DocDeliveryManagementRow findRowByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDeliveryManagementRow.TYPE,
            "account_id=? and document_div=? and product_code=? and delivery_date=? and document_category=?",
            null,
            new Object[] { new Long(p_accountId), p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDeliveryManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDeliveryManagementDao.findRowsByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory(long, String, String, java.sql.Timestamp, String)}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
   */
    public static DocDeliveryManagementDao findDaoByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory, and にて指定の値から一意の{@@link DocDeliveryManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_documentDiv 検索対象であるp_documentDivフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_documentCategory 検索対象であるp_documentCategoryフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory, and の値と一致する{@@link DocDeliveryManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DocDeliveryManagementRow findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory( String p_institutionCode, String p_branchCode, String p_accountCode, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDeliveryManagementRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and document_div=? and product_code=? and delivery_date=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDeliveryManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDeliveryManagementDao.findRowsByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory(String, String, String, String, String, java.sql.Timestamp, String)}および{@@link #forRow(DocDeliveryManagementRow)}を使用してください。 
   */
    public static DocDeliveryManagementDao findDaoByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory( String p_institutionCode, String p_branchCode, String p_accountCode, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory( p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
