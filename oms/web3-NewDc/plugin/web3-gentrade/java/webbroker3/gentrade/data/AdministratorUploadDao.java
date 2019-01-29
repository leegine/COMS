head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadDao.java;


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
 * {@@link AdministratorUploadDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AdministratorUploadRow}インスタンスへ関連付けることができます。 
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
 * @@see AdministratorUploadPK 
 * @@see AdministratorUploadRow 
 */
public class AdministratorUploadDao extends DataAccessObject {


  /** 
   * この{@@link AdministratorUploadDao}に関連する型指定のRowオブジェクト 
   */
    private AdministratorUploadRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AdministratorUploadRow}と仮定される{@@link DataAccessObject}から新たに{@@link AdministratorUploadDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AdministratorUploadDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AdministratorUploadRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdministratorUploadRow )
                return new AdministratorUploadDao( (AdministratorUploadRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdministratorUploadRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdministratorUploadRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AdministratorUploadRow}オブジェクト 
    */
    protected AdministratorUploadDao( AdministratorUploadRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AdministratorUploadRow}オブジェクトを取得します。
   */
    public AdministratorUploadRow getAdministratorUploadRow() {
        return row;
    }


  /** 
   * 指定の{@@link AdministratorUploadRow}オブジェクトから{@@link AdministratorUploadDao}オブジェクトを作成します。 
   * これは実際の{@@link AdministratorUploadRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AdministratorUploadDao}取得のために指定の{@@link AdministratorUploadRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AdministratorUploadDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AdministratorUploadDao forRow( AdministratorUploadRow row ) throws java.lang.IllegalArgumentException {
        return (AdministratorUploadDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdministratorUploadRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AdministratorUploadRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AdministratorUploadPK}やデータベースレコードとして挿入される{@@link AdministratorUploadParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdministratorUploadRow.TYPE );
    }


  /** 
   * {@@link AdministratorUploadRow}を一意に特定する{@@link AdministratorUploadPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AdministratorUploadRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AdministratorUploadParams}オブジェクトの主キーとして利用可能な{@@link AdministratorUploadPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AdministratorUploadPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AdministratorUploadPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AdministratorUploadRow}オブジェクトを検索します。 
   * 
   * @@param p_administratorUploadId 検索対象であるp_administratorUploadIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdministratorUploadRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdministratorUploadRow findRowByPk( long p_administratorUploadId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadPK pk = new AdministratorUploadPK( p_administratorUploadId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAdministratorUploadPKオブジェクトから{@@link AdministratorUploadRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAdministratorUploadPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdministratorUploadRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdministratorUploadRow findRowByPk( AdministratorUploadPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdministratorUploadRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(AdministratorUploadRow)}を使用してください。 
   */
    public static AdministratorUploadDao findDaoByPk( long p_administratorUploadId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadPK pk = new AdministratorUploadPK( p_administratorUploadId );
        AdministratorUploadRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AdministratorUploadPK)}および{@@link #forRow(AdministratorUploadRow)}を使用してください。 
   */
    public static AdministratorUploadDao findDaoByPk( AdministratorUploadPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadRow row = findRowByPk( pk );
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
   * p_administratorUploadId, and にて指定の値から一意の{@@link AdministratorUploadRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_administratorUploadId 検索対象であるp_administratorUploadIdフィールドの値
   * 
   * @@return 引数指定のp_administratorUploadId, and の値と一致する{@@link AdministratorUploadRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AdministratorUploadRow findRowByAdministratorUploadId( long p_administratorUploadId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorUploadRow.TYPE,
            "administrator_upload_id=?",
            null,
            new Object[] { new Long(p_administratorUploadId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorUploadRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorUploadDao.findRowsByAdministratorUploadId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAdministratorUploadId(long)}および{@@link #forRow(AdministratorUploadRow)}を使用してください。 
   */
    public static AdministratorUploadDao findDaoByAdministratorUploadId( long p_administratorUploadId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAdministratorUploadId( p_administratorUploadId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_productType, p_uploadFileId, p_uploadStartTimestamp, and にて指定の値から一意の{@@link AdministratorUploadRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_uploadFileId 検索対象であるp_uploadFileIdフィールドの値
   * @@param p_uploadStartTimestamp 検索対象であるp_uploadStartTimestampフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_productType, p_uploadFileId, p_uploadStartTimestamp, and の値と一致する{@@link AdministratorUploadRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AdministratorUploadRow findRowByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp( String p_institutionCode, String p_branchCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_uploadFileId, java.sql.Timestamp p_uploadStartTimestamp ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorUploadRow.TYPE,
            "institution_code=? and branch_code=? and product_type=? and upload_file_id=? and upload_start_timestamp=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_productType, p_uploadFileId, p_uploadStartTimestamp } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorUploadRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorUploadDao.findRowsByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp(String, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String, java.sql.Timestamp)}および{@@link #forRow(AdministratorUploadRow)}を使用してください。 
   */
    public static AdministratorUploadDao findDaoByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp( String p_institutionCode, String p_branchCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_uploadFileId, java.sql.Timestamp p_uploadStartTimestamp ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp( p_institutionCode, p_branchCode, p_productType, p_uploadFileId, p_uploadStartTimestamp ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
