head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.11.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostConditionRegVoucherDao.java;


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
 * {@@link HostConditionRegVoucherDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostConditionRegVoucherRow}インスタンスへ関連付けることができます。 
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
 * @@see HostConditionRegVoucherPK 
 * @@see HostConditionRegVoucherRow 
 */
public class HostConditionRegVoucherDao extends DataAccessObject {


  /** 
   * この{@@link HostConditionRegVoucherDao}に関連する型指定のRowオブジェクト 
   */
    private HostConditionRegVoucherRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostConditionRegVoucherRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostConditionRegVoucherDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostConditionRegVoucherDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostConditionRegVoucherRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostConditionRegVoucherRow )
                return new HostConditionRegVoucherDao( (HostConditionRegVoucherRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostConditionRegVoucherRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostConditionRegVoucherRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostConditionRegVoucherRow}オブジェクト 
    */
    protected HostConditionRegVoucherDao( HostConditionRegVoucherRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostConditionRegVoucherRow}オブジェクトを取得します。
   */
    public HostConditionRegVoucherRow getHostConditionRegVoucherRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostConditionRegVoucherRow}オブジェクトから{@@link HostConditionRegVoucherDao}オブジェクトを作成します。 
   * これは実際の{@@link HostConditionRegVoucherRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostConditionRegVoucherDao}取得のために指定の{@@link HostConditionRegVoucherRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostConditionRegVoucherDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostConditionRegVoucherDao forRow( HostConditionRegVoucherRow row ) throws java.lang.IllegalArgumentException {
        return (HostConditionRegVoucherDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostConditionRegVoucherRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostConditionRegVoucherRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostConditionRegVoucherPK}やデータベースレコードとして挿入される{@@link HostConditionRegVoucherParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostConditionRegVoucherRow.TYPE );
    }


  /** 
   * {@@link HostConditionRegVoucherRow}を一意に特定する{@@link HostConditionRegVoucherPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostConditionRegVoucherRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostConditionRegVoucherParams}オブジェクトの主キーとして利用可能な{@@link HostConditionRegVoucherPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostConditionRegVoucherPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostConditionRegVoucherRow}オブジェクトを検索します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostConditionRegVoucherRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostConditionRegVoucherRow findRowByPk( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        HostConditionRegVoucherPK pk = new HostConditionRegVoucherPK( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostConditionRegVoucherPKオブジェクトから{@@link HostConditionRegVoucherRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostConditionRegVoucherPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostConditionRegVoucherRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostConditionRegVoucherRow findRowByPk( HostConditionRegVoucherPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostConditionRegVoucherRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(HostConditionRegVoucherRow)}を使用してください。 
   */
    public static HostConditionRegVoucherDao findDaoByPk( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        HostConditionRegVoucherPK pk = new HostConditionRegVoucherPK( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode );
        HostConditionRegVoucherRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostConditionRegVoucherPK)}および{@@link #forRow(HostConditionRegVoucherRow)}を使用してください。 
   */
    public static HostConditionRegVoucherDao findDaoByPk( HostConditionRegVoucherPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostConditionRegVoucherRow row = findRowByPk( pk );
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
   * p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link HostConditionRegVoucherRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link HostConditionRegVoucherRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostConditionRegVoucherRow.TYPE,
            "order_request_number=? and request_code=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode(String, String, String, String, String)}および{@@link #forRow(HostConditionRegVoucherRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode( p_orderRequestNumber, p_requestCode, p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
