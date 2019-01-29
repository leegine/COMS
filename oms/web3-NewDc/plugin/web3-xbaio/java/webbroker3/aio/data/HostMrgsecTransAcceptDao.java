head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostMrgsecTransAcceptDao.java;


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
 * {@@link HostMrgsecTransAcceptDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostMrgsecTransAcceptRow}インスタンスへ関連付けることができます。 
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
 * @@see HostMrgsecTransAcceptPK 
 * @@see HostMrgsecTransAcceptRow 
 */
public class HostMrgsecTransAcceptDao extends DataAccessObject {


  /** 
   * この{@@link HostMrgsecTransAcceptDao}に関連する型指定のRowオブジェクト 
   */
    private HostMrgsecTransAcceptRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostMrgsecTransAcceptRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostMrgsecTransAcceptDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostMrgsecTransAcceptDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostMrgsecTransAcceptRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostMrgsecTransAcceptRow )
                return new HostMrgsecTransAcceptDao( (HostMrgsecTransAcceptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostMrgsecTransAcceptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostMrgsecTransAcceptRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostMrgsecTransAcceptRow}オブジェクト 
    */
    protected HostMrgsecTransAcceptDao( HostMrgsecTransAcceptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostMrgsecTransAcceptRow}オブジェクトを取得します。
   */
    public HostMrgsecTransAcceptRow getHostMrgsecTransAcceptRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostMrgsecTransAcceptRow}オブジェクトから{@@link HostMrgsecTransAcceptDao}オブジェクトを作成します。 
   * これは実際の{@@link HostMrgsecTransAcceptRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostMrgsecTransAcceptDao}取得のために指定の{@@link HostMrgsecTransAcceptRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostMrgsecTransAcceptDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostMrgsecTransAcceptDao forRow( HostMrgsecTransAcceptRow row ) throws java.lang.IllegalArgumentException {
        return (HostMrgsecTransAcceptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


      // (this object has no primary key components)


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


      // (this object has no primary key components)


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
   * p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and にて指定の値に一致する{@@link HostMrgsecTransAcceptRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and の値と一致する{@@link HostMrgsecTransAcceptRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostMrgsecTransAcceptRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String)}および{@@link #forRow(HostMrgsecTransAcceptRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }


  /** 
   * p_requestCode, p_status, and にて指定の値に一致する{@@link HostMrgsecTransAcceptRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_status, and の値と一致する{@@link HostMrgsecTransAcceptRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostMrgsecTransAcceptRow.TYPE,
            "request_code=? and status=?",
            null,
            new Object[] { p_requestCode, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeStatus(String, String)}および{@@link #forRow(HostMrgsecTransAcceptRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeStatus( p_requestCode, p_status ) );
    }

}
@
