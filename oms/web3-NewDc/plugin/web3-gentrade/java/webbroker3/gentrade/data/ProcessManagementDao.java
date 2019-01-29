head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.28.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ProcessManagementDao.java;


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
 * {@@link ProcessManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ProcessManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see ProcessManagementPK 
 * @@see ProcessManagementRow 
 */
public class ProcessManagementDao extends DataAccessObject {


  /** 
   * この{@@link ProcessManagementDao}に関連する型指定のRowオブジェクト 
   */
    private ProcessManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ProcessManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link ProcessManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ProcessManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ProcessManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ProcessManagementRow )
                return new ProcessManagementDao( (ProcessManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ProcessManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ProcessManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ProcessManagementRow}オブジェクト 
    */
    protected ProcessManagementDao( ProcessManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ProcessManagementRow}オブジェクトを取得します。
   */
    public ProcessManagementRow getProcessManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link ProcessManagementRow}オブジェクトから{@@link ProcessManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link ProcessManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ProcessManagementDao}取得のために指定の{@@link ProcessManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ProcessManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ProcessManagementDao forRow( ProcessManagementRow row ) throws java.lang.IllegalArgumentException {
        return (ProcessManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ProcessManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ProcessManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ProcessManagementPK}やデータベースレコードとして挿入される{@@link ProcessManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ProcessManagementRow.TYPE );
    }


  /** 
   * {@@link ProcessManagementRow}を一意に特定する{@@link ProcessManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ProcessManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ProcessManagementParams}オブジェクトの主キーとして利用可能な{@@link ProcessManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ProcessManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ProcessManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_processId 検索対象であるp_processIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ProcessManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ProcessManagementRow findRowByPk( String p_processId, String p_institutionCode, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        ProcessManagementPK pk = new ProcessManagementPK( p_processId, p_institutionCode, p_branchCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のProcessManagementPKオブジェクトから{@@link ProcessManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するProcessManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ProcessManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ProcessManagementRow findRowByPk( ProcessManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ProcessManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(ProcessManagementRow)}を使用してください。 
   */
    public static ProcessManagementDao findDaoByPk( String p_processId, String p_institutionCode, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        ProcessManagementPK pk = new ProcessManagementPK( p_processId, p_institutionCode, p_branchCode );
        ProcessManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ProcessManagementPK)}および{@@link #forRow(ProcessManagementRow)}を使用してください。 
   */
    public static ProcessManagementDao findDaoByPk( ProcessManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ProcessManagementRow row = findRowByPk( pk );
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
   * p_processId, p_institutionCode, p_branchCode, and にて指定の値から一意の{@@link ProcessManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_processId 検索対象であるp_processIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_processId, p_institutionCode, p_branchCode, and の値と一致する{@@link ProcessManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ProcessManagementRow findRowByProcessIdInstitutionCodeBranchCode( String p_processId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ProcessManagementRow.TYPE,
            "process_id=? and institution_code=? and branch_code=?",
            null,
            new Object[] { p_processId, p_institutionCode, p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ProcessManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ProcessManagementDao.findRowsByProcessIdInstitutionCodeBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProcessIdInstitutionCodeBranchCode(String, String, String)}および{@@link #forRow(ProcessManagementRow)}を使用してください。 
   */
    public static ProcessManagementDao findDaoByProcessIdInstitutionCodeBranchCode( String p_processId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProcessIdInstitutionCodeBranchCode( p_processId, p_institutionCode, p_branchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
