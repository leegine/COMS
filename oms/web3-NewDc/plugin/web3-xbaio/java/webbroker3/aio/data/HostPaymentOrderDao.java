head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.44.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostPaymentOrderDao.java;


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
 * {@@link HostPaymentOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostPaymentOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see HostPaymentOrderPK 
 * @@see HostPaymentOrderRow 
 */
public class HostPaymentOrderDao extends DataAccessObject {


  /** 
   * この{@@link HostPaymentOrderDao}に関連する型指定のRowオブジェクト 
   */
    private HostPaymentOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostPaymentOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostPaymentOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostPaymentOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostPaymentOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostPaymentOrderRow )
                return new HostPaymentOrderDao( (HostPaymentOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostPaymentOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostPaymentOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostPaymentOrderRow}オブジェクト 
    */
    protected HostPaymentOrderDao( HostPaymentOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostPaymentOrderRow}オブジェクトを取得します。
   */
    public HostPaymentOrderRow getHostPaymentOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostPaymentOrderRow}オブジェクトから{@@link HostPaymentOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link HostPaymentOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostPaymentOrderDao}取得のために指定の{@@link HostPaymentOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostPaymentOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostPaymentOrderDao forRow( HostPaymentOrderRow row ) throws java.lang.IllegalArgumentException {
        return (HostPaymentOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostPaymentOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostPaymentOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostPaymentOrderPK}やデータベースレコードとして挿入される{@@link HostPaymentOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostPaymentOrderRow.TYPE );
    }


  /** 
   * {@@link HostPaymentOrderRow}を一意に特定する{@@link HostPaymentOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostPaymentOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostPaymentOrderParams}オブジェクトの主キーとして利用可能な{@@link HostPaymentOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostPaymentOrderPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostPaymentOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostPaymentOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostPaymentOrderRow findRowByPk( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostPaymentOrderPK pk = new HostPaymentOrderPK( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostPaymentOrderPKオブジェクトから{@@link HostPaymentOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostPaymentOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostPaymentOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostPaymentOrderRow findRowByPk( HostPaymentOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostPaymentOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(HostPaymentOrderRow)}を使用してください。 
   */
    public static HostPaymentOrderDao findDaoByPk( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostPaymentOrderPK pk = new HostPaymentOrderPK( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        HostPaymentOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostPaymentOrderPK)}および{@@link #forRow(HostPaymentOrderRow)}を使用してください。 
   */
    public static HostPaymentOrderDao findDaoByPk( HostPaymentOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostPaymentOrderRow row = findRowByPk( pk );
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
   * p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and にて指定の値から一意の{@@link HostPaymentOrderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and の値と一致する{@@link HostPaymentOrderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static HostPaymentOrderRow findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostPaymentOrderRow.TYPE,
            "request_code=? and institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostPaymentOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostPaymentOrderDao.findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String, String)}および{@@link #forRow(HostPaymentOrderRow)}を使用してください。 
   */
    public static HostPaymentOrderDao findDaoByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_requestCode, p_status, and にて指定の値に一致する{@@link HostPaymentOrderRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_status, and の値と一致する{@@link HostPaymentOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostPaymentOrderRow.TYPE,
            "request_code=? and status=?",
            null,
            new Object[] { p_requestCode, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeStatus(String, String)}および{@@link #forRow(HostPaymentOrderRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeStatus( p_requestCode, p_status ) );
    }

}
@
