head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostTransferAcceptDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link HostTransferAcceptDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostTransferAcceptRow}インスタンスへ関連付けることができます。 
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
 * @@see HostTransferAcceptPK 
 * @@see HostTransferAcceptRow 
 */
public class HostTransferAcceptDao extends DataAccessObject {


  /** 
   * この{@@link HostTransferAcceptDao}に関連する型指定のRowオブジェクト 
   */
    private HostTransferAcceptRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostTransferAcceptRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostTransferAcceptDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostTransferAcceptDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostTransferAcceptRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostTransferAcceptRow )
                return new HostTransferAcceptDao( (HostTransferAcceptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostTransferAcceptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostTransferAcceptRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostTransferAcceptRow}オブジェクト 
    */
    protected HostTransferAcceptDao( HostTransferAcceptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostTransferAcceptRow}オブジェクトを取得します。
   */
    public HostTransferAcceptRow getHostTransferAcceptRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostTransferAcceptRow}オブジェクトから{@@link HostTransferAcceptDao}オブジェクトを作成します。 
   * これは実際の{@@link HostTransferAcceptRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostTransferAcceptDao}取得のために指定の{@@link HostTransferAcceptRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostTransferAcceptDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostTransferAcceptDao forRow( HostTransferAcceptRow row ) throws java.lang.IllegalArgumentException {
        return (HostTransferAcceptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostTransferAcceptRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostTransferAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostTransferAcceptPK}やデータベースレコードとして挿入される{@@link HostTransferAcceptParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostTransferAcceptRow.TYPE );
    }


  /** 
   * {@@link HostTransferAcceptRow}を一意に特定する{@@link HostTransferAcceptPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostTransferAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostTransferAcceptParams}オブジェクトの主キーとして利用可能な{@@link HostTransferAcceptPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostTransferAcceptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostTransferAcceptRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostTransferAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostTransferAcceptRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferAcceptPK pk = new HostTransferAcceptPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostTransferAcceptPKオブジェクトから{@@link HostTransferAcceptRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostTransferAcceptPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostTransferAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostTransferAcceptRow findRowByPk( HostTransferAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostTransferAcceptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(HostTransferAcceptRow)}を使用してください。 
   */
    public static HostTransferAcceptDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferAcceptPK pk = new HostTransferAcceptPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        HostTransferAcceptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostTransferAcceptPK)}および{@@link #forRow(HostTransferAcceptRow)}を使用してください。 
   */
    public static HostTransferAcceptDao findDaoByPk( HostTransferAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferAcceptRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and にて指定の値から一意の{@@link HostTransferAcceptRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and の値と一致する{@@link HostTransferAcceptRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static HostTransferAcceptRow findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostTransferAcceptRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostTransferAcceptRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostTransferAcceptDao.findRowsByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String)}および{@@link #forRow(HostTransferAcceptRow)}を使用してください。 
   */
    public static HostTransferAcceptDao findDaoByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_requestCode, p_status, and にて指定の値に一致する{@@link HostTransferAcceptRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_status, and の値と一致する{@@link HostTransferAcceptRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostTransferAcceptRow.TYPE,
            "request_code=? and status=?",
            null,
            new Object[] { p_requestCode, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeStatus(String, String)}および{@@link #forRow(HostTransferAcceptRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeStatus( p_requestCode, p_status ) );
    }

}
@
