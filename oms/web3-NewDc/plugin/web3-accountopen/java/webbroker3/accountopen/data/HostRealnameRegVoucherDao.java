head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.16.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostRealnameRegVoucherDao.java;


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
 * {@@link HostRealnameRegVoucherDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostRealnameRegVoucherRow}インスタンスへ関連付けることができます。 
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
 * @@see HostRealnameRegVoucherPK 
 * @@see HostRealnameRegVoucherRow 
 */
public class HostRealnameRegVoucherDao extends DataAccessObject {


  /** 
   * この{@@link HostRealnameRegVoucherDao}に関連する型指定のRowオブジェクト 
   */
    private HostRealnameRegVoucherRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostRealnameRegVoucherRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostRealnameRegVoucherDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostRealnameRegVoucherDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostRealnameRegVoucherRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostRealnameRegVoucherRow )
                return new HostRealnameRegVoucherDao( (HostRealnameRegVoucherRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostRealnameRegVoucherRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostRealnameRegVoucherRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostRealnameRegVoucherRow}オブジェクト 
    */
    protected HostRealnameRegVoucherDao( HostRealnameRegVoucherRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostRealnameRegVoucherRow}オブジェクトを取得します。
   */
    public HostRealnameRegVoucherRow getHostRealnameRegVoucherRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostRealnameRegVoucherRow}オブジェクトから{@@link HostRealnameRegVoucherDao}オブジェクトを作成します。 
   * これは実際の{@@link HostRealnameRegVoucherRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostRealnameRegVoucherDao}取得のために指定の{@@link HostRealnameRegVoucherRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostRealnameRegVoucherDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostRealnameRegVoucherDao forRow( HostRealnameRegVoucherRow row ) throws java.lang.IllegalArgumentException {
        return (HostRealnameRegVoucherDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostRealnameRegVoucherRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostRealnameRegVoucherRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostRealnameRegVoucherPK}やデータベースレコードとして挿入される{@@link HostRealnameRegVoucherParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostRealnameRegVoucherRow.TYPE );
    }


  /** 
   * {@@link HostRealnameRegVoucherRow}を一意に特定する{@@link HostRealnameRegVoucherPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostRealnameRegVoucherRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostRealnameRegVoucherParams}オブジェクトの主キーとして利用可能な{@@link HostRealnameRegVoucherPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostRealnameRegVoucherPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostRealnameRegVoucherRow}オブジェクトを検索します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostRealnameRegVoucherRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostRealnameRegVoucherRow findRowByPk( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        HostRealnameRegVoucherPK pk = new HostRealnameRegVoucherPK( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostRealnameRegVoucherPKオブジェクトから{@@link HostRealnameRegVoucherRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostRealnameRegVoucherPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostRealnameRegVoucherRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostRealnameRegVoucherRow findRowByPk( HostRealnameRegVoucherPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostRealnameRegVoucherRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(HostRealnameRegVoucherRow)}を使用してください。 
   */
    public static HostRealnameRegVoucherDao findDaoByPk( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        HostRealnameRegVoucherPK pk = new HostRealnameRegVoucherPK( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode );
        HostRealnameRegVoucherRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostRealnameRegVoucherPK)}および{@@link #forRow(HostRealnameRegVoucherRow)}を使用してください。 
   */
    public static HostRealnameRegVoucherDao findDaoByPk( HostRealnameRegVoucherPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostRealnameRegVoucherRow row = findRowByPk( pk );
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
   * p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link HostRealnameRegVoucherRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link HostRealnameRegVoucherRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostRealnameRegVoucherRow.TYPE,
            "order_request_number=? and request_code=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode(String, String, String, String, String)}および{@@link #forRow(HostRealnameRegVoucherRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
