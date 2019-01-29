head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.16.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostAgencyNotifyVoucherDao.java;


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
 * {@@link HostAgencyNotifyVoucherDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostAgencyNotifyVoucherRow}インスタンスへ関連付けることができます。 
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
 * @@see HostAgencyNotifyVoucherPK 
 * @@see HostAgencyNotifyVoucherRow 
 */
public class HostAgencyNotifyVoucherDao extends DataAccessObject {


  /** 
   * この{@@link HostAgencyNotifyVoucherDao}に関連する型指定のRowオブジェクト 
   */
    private HostAgencyNotifyVoucherRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostAgencyNotifyVoucherRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostAgencyNotifyVoucherDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostAgencyNotifyVoucherDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostAgencyNotifyVoucherRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostAgencyNotifyVoucherRow )
                return new HostAgencyNotifyVoucherDao( (HostAgencyNotifyVoucherRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostAgencyNotifyVoucherRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostAgencyNotifyVoucherRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostAgencyNotifyVoucherRow}オブジェクト 
    */
    protected HostAgencyNotifyVoucherDao( HostAgencyNotifyVoucherRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostAgencyNotifyVoucherRow}オブジェクトを取得します。
   */
    public HostAgencyNotifyVoucherRow getHostAgencyNotifyVoucherRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostAgencyNotifyVoucherRow}オブジェクトから{@@link HostAgencyNotifyVoucherDao}オブジェクトを作成します。 
   * これは実際の{@@link HostAgencyNotifyVoucherRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostAgencyNotifyVoucherDao}取得のために指定の{@@link HostAgencyNotifyVoucherRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostAgencyNotifyVoucherDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostAgencyNotifyVoucherDao forRow( HostAgencyNotifyVoucherRow row ) throws java.lang.IllegalArgumentException {
        return (HostAgencyNotifyVoucherDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostAgencyNotifyVoucherRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostAgencyNotifyVoucherRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostAgencyNotifyVoucherPK}やデータベースレコードとして挿入される{@@link HostAgencyNotifyVoucherParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostAgencyNotifyVoucherRow.TYPE );
    }


  /** 
   * {@@link HostAgencyNotifyVoucherRow}を一意に特定する{@@link HostAgencyNotifyVoucherPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostAgencyNotifyVoucherRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostAgencyNotifyVoucherParams}オブジェクトの主キーとして利用可能な{@@link HostAgencyNotifyVoucherPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostAgencyNotifyVoucherPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostAgencyNotifyVoucherRow}オブジェクトを検索します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostAgencyNotifyVoucherRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostAgencyNotifyVoucherRow findRowByPk( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAgencyNotifyVoucherPK pk = new HostAgencyNotifyVoucherPK( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostAgencyNotifyVoucherPKオブジェクトから{@@link HostAgencyNotifyVoucherRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostAgencyNotifyVoucherPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostAgencyNotifyVoucherRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostAgencyNotifyVoucherRow findRowByPk( HostAgencyNotifyVoucherPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostAgencyNotifyVoucherRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(HostAgencyNotifyVoucherRow)}を使用してください。 
   */
    public static HostAgencyNotifyVoucherDao findDaoByPk( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAgencyNotifyVoucherPK pk = new HostAgencyNotifyVoucherPK( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode );
        HostAgencyNotifyVoucherRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostAgencyNotifyVoucherPK)}および{@@link #forRow(HostAgencyNotifyVoucherRow)}を使用してください。 
   */
    public static HostAgencyNotifyVoucherDao findDaoByPk( HostAgencyNotifyVoucherPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAgencyNotifyVoucherRow row = findRowByPk( pk );
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
   * p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode, and にて指定の値から一意の{@@link HostAgencyNotifyVoucherRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link HostAgencyNotifyVoucherRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static HostAgencyNotifyVoucherRow findRowByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostAgencyNotifyVoucherRow.TYPE,
            "order_request_number=? and request_code=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostAgencyNotifyVoucherRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostAgencyNotifyVoucherDao.findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode(String, String, String, String, String)}および{@@link #forRow(HostAgencyNotifyVoucherRow)}を使用してください。 
   */
    public static HostAgencyNotifyVoucherDao findDaoByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
