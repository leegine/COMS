head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.18.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostStockholderRegVoucherDao.java;


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
 * {@@link HostStockholderRegVoucherDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostStockholderRegVoucherRow}インスタンスへ関連付けることができます。 
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
 * @@see HostStockholderRegVoucherPK 
 * @@see HostStockholderRegVoucherRow 
 */
public class HostStockholderRegVoucherDao extends DataAccessObject {


  /** 
   * この{@@link HostStockholderRegVoucherDao}に関連する型指定のRowオブジェクト 
   */
    private HostStockholderRegVoucherRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostStockholderRegVoucherRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostStockholderRegVoucherDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostStockholderRegVoucherDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostStockholderRegVoucherRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostStockholderRegVoucherRow )
                return new HostStockholderRegVoucherDao( (HostStockholderRegVoucherRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostStockholderRegVoucherRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostStockholderRegVoucherRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostStockholderRegVoucherRow}オブジェクト 
    */
    protected HostStockholderRegVoucherDao( HostStockholderRegVoucherRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostStockholderRegVoucherRow}オブジェクトを取得します。
   */
    public HostStockholderRegVoucherRow getHostStockholderRegVoucherRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostStockholderRegVoucherRow}オブジェクトから{@@link HostStockholderRegVoucherDao}オブジェクトを作成します。 
   * これは実際の{@@link HostStockholderRegVoucherRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostStockholderRegVoucherDao}取得のために指定の{@@link HostStockholderRegVoucherRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostStockholderRegVoucherDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostStockholderRegVoucherDao forRow( HostStockholderRegVoucherRow row ) throws java.lang.IllegalArgumentException {
        return (HostStockholderRegVoucherDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostStockholderRegVoucherRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostStockholderRegVoucherRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostStockholderRegVoucherPK}やデータベースレコードとして挿入される{@@link HostStockholderRegVoucherParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostStockholderRegVoucherRow.TYPE );
    }


  /** 
   * {@@link HostStockholderRegVoucherRow}を一意に特定する{@@link HostStockholderRegVoucherPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostStockholderRegVoucherRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostStockholderRegVoucherParams}オブジェクトの主キーとして利用可能な{@@link HostStockholderRegVoucherPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostStockholderRegVoucherPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostStockholderRegVoucherRow}オブジェクトを検索します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostStockholderRegVoucherRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostStockholderRegVoucherRow findRowByPk( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        HostStockholderRegVoucherPK pk = new HostStockholderRegVoucherPK( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostStockholderRegVoucherPKオブジェクトから{@@link HostStockholderRegVoucherRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostStockholderRegVoucherPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostStockholderRegVoucherRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostStockholderRegVoucherRow findRowByPk( HostStockholderRegVoucherPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostStockholderRegVoucherRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(HostStockholderRegVoucherRow)}を使用してください。 
   */
    public static HostStockholderRegVoucherDao findDaoByPk( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        HostStockholderRegVoucherPK pk = new HostStockholderRegVoucherPK( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode );
        HostStockholderRegVoucherRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostStockholderRegVoucherPK)}および{@@link #forRow(HostStockholderRegVoucherRow)}を使用してください。 
   */
    public static HostStockholderRegVoucherDao findDaoByPk( HostStockholderRegVoucherPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostStockholderRegVoucherRow row = findRowByPk( pk );
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
   * p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link HostStockholderRegVoucherRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link HostStockholderRegVoucherRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostStockholderRegVoucherRow.TYPE,
            "order_request_number=? and request_code=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode(String, String, String, String, String)}および{@@link #forRow(HostStockholderRegVoucherRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
