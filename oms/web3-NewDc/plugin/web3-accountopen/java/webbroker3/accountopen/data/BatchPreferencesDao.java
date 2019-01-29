head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BatchPreferencesDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BatchPreferencesDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BatchPreferencesRow}インスタンスへ関連付けることができます。 
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
 * @@see BatchPreferencesPK 
 * @@see BatchPreferencesRow 
 */
public class BatchPreferencesDao extends DataAccessObject {


  /** 
   * この{@@link BatchPreferencesDao}に関連する型指定のRowオブジェクト 
   */
    private BatchPreferencesRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BatchPreferencesRow}と仮定される{@@link DataAccessObject}から新たに{@@link BatchPreferencesDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BatchPreferencesDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BatchPreferencesRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatchPreferencesRow )
                return new BatchPreferencesDao( (BatchPreferencesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatchPreferencesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatchPreferencesRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BatchPreferencesRow}オブジェクト 
    */
    protected BatchPreferencesDao( BatchPreferencesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BatchPreferencesRow}オブジェクトを取得します。
   */
    public BatchPreferencesRow getBatchPreferencesRow() {
        return row;
    }


  /** 
   * 指定の{@@link BatchPreferencesRow}オブジェクトから{@@link BatchPreferencesDao}オブジェクトを作成します。 
   * これは実際の{@@link BatchPreferencesRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BatchPreferencesDao}取得のために指定の{@@link BatchPreferencesRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BatchPreferencesDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BatchPreferencesDao forRow( BatchPreferencesRow row ) throws java.lang.IllegalArgumentException {
        return (BatchPreferencesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatchPreferencesRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BatchPreferencesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BatchPreferencesPK}やデータベースレコードとして挿入される{@@link BatchPreferencesParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatchPreferencesRow.TYPE );
    }


  /** 
   * {@@link BatchPreferencesRow}を一意に特定する{@@link BatchPreferencesPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BatchPreferencesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BatchPreferencesParams}オブジェクトの主キーとして利用可能な{@@link BatchPreferencesPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BatchPreferencesPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BatchPreferencesRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * @@param p_nameSerialNo 検索対象であるp_nameSerialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatchPreferencesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatchPreferencesRow findRowByPk( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchPreferencesPK pk = new BatchPreferencesPK( p_institutionCode, p_branchCode, p_name, p_nameSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBatchPreferencesPKオブジェクトから{@@link BatchPreferencesRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBatchPreferencesPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatchPreferencesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatchPreferencesRow findRowByPk( BatchPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatchPreferencesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,int)}および{@@link #forRow(BatchPreferencesRow)}を使用してください。 
   */
    public static BatchPreferencesDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchPreferencesPK pk = new BatchPreferencesPK( p_institutionCode, p_branchCode, p_name, p_nameSerialNo );
        BatchPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BatchPreferencesPK)}および{@@link #forRow(BatchPreferencesRow)}を使用してください。 
   */
    public static BatchPreferencesDao findDaoByPk( BatchPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchPreferencesRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_name, p_nameSerialNo, and にて指定の値から一意の{@@link BatchPreferencesRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * @@param p_nameSerialNo 検索対象であるp_nameSerialNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_name, p_nameSerialNo, and の値と一致する{@@link BatchPreferencesRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BatchPreferencesRow findRowByInstitutionCodeBranchCodeNameNameSerialNo( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatchPreferencesRow.TYPE,
            "institution_code=? and branch_code=? and name=? and name_serial_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_name, new Integer(p_nameSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatchPreferencesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatchPreferencesDao.findRowsByInstitutionCodeBranchCodeNameNameSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeNameNameSerialNo(String, String, String, int)}および{@@link #forRow(BatchPreferencesRow)}を使用してください。 
   */
    public static BatchPreferencesDao findDaoByInstitutionCodeBranchCodeNameNameSerialNo( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeNameNameSerialNo( p_institutionCode, p_branchCode, p_name, p_nameSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
