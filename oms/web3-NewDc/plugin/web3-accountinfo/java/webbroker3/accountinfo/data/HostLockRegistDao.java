head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	HostLockRegistDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link HostLockRegistDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostLockRegistRow}インスタンスへ関連付けることができます。 
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
 * @@see HostLockRegistPK 
 * @@see HostLockRegistRow 
 */
public class HostLockRegistDao extends DataAccessObject {


  /** 
   * この{@@link HostLockRegistDao}に関連する型指定のRowオブジェクト 
   */
    private HostLockRegistRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostLockRegistRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostLockRegistDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostLockRegistDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostLockRegistRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostLockRegistRow )
                return new HostLockRegistDao( (HostLockRegistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostLockRegistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostLockRegistRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostLockRegistRow}オブジェクト 
    */
    protected HostLockRegistDao( HostLockRegistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostLockRegistRow}オブジェクトを取得します。
   */
    public HostLockRegistRow getHostLockRegistRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostLockRegistRow}オブジェクトから{@@link HostLockRegistDao}オブジェクトを作成します。 
   * これは実際の{@@link HostLockRegistRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostLockRegistDao}取得のために指定の{@@link HostLockRegistRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostLockRegistDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostLockRegistDao forRow( HostLockRegistRow row ) throws java.lang.IllegalArgumentException {
        return (HostLockRegistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostLockRegistRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostLockRegistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostLockRegistPK}やデータベースレコードとして挿入される{@@link HostLockRegistParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostLockRegistRow.TYPE );
    }


  /** 
   * {@@link HostLockRegistRow}を一意に特定する{@@link HostLockRegistPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostLockRegistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostLockRegistParams}オブジェクトの主キーとして利用可能な{@@link HostLockRegistPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostLockRegistPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostLockRegistRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostLockRegistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostLockRegistRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegistPK pk = new HostLockRegistPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostLockRegistPKオブジェクトから{@@link HostLockRegistRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostLockRegistPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostLockRegistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostLockRegistRow findRowByPk( HostLockRegistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostLockRegistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostLockRegistRow)}を使用してください。 
   */
    public static HostLockRegistDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegistPK pk = new HostLockRegistPK( p_rowid );
        HostLockRegistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostLockRegistPK)}および{@@link #forRow(HostLockRegistRow)}を使用してください。 
   */
    public static HostLockRegistDao findDaoByPk( HostLockRegistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegistRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_lastUpdatedTimestamp, and にて指定の値に一致する{@@link HostLockRegistRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_lastUpdatedTimestamp 検索対象であるp_lastUpdatedTimestampフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_lastUpdatedTimestamp, and の値と一致する{@@link HostLockRegistRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeLastUpdatedTimestamp( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_lastUpdatedTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostLockRegistRow.TYPE,
            "request_code=? and institution_code=? and branch_code=? and account_code=? and last_updated_timestamp=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_lastUpdatedTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeLastUpdatedTimestamp(String, String, String, String, java.sql.Timestamp)}および{@@link #forRow(HostLockRegistRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeInstitutionCodeBranchCodeAccountCodeLastUpdatedTimestamp( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_lastUpdatedTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeLastUpdatedTimestamp( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_lastUpdatedTimestamp ) );
    }


  /** 
   * p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and にて指定の値に一致する{@@link HostLockRegistRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and の値と一致する{@@link HostLockRegistRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostLockRegistRow.TYPE,
            "request_code=? and institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String, String)}および{@@link #forRow(HostLockRegistRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }

}
@
