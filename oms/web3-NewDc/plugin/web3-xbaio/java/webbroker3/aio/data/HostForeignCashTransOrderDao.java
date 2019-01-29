head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.42.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostForeignCashTransOrderDao.java;


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
 * {@@link HostForeignCashTransOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostForeignCashTransOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see HostForeignCashTransOrderPK 
 * @@see HostForeignCashTransOrderRow 
 */
public class HostForeignCashTransOrderDao extends DataAccessObject {


  /** 
   * この{@@link HostForeignCashTransOrderDao}に関連する型指定のRowオブジェクト 
   */
    private HostForeignCashTransOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostForeignCashTransOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostForeignCashTransOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostForeignCashTransOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostForeignCashTransOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostForeignCashTransOrderRow )
                return new HostForeignCashTransOrderDao( (HostForeignCashTransOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostForeignCashTransOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostForeignCashTransOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostForeignCashTransOrderRow}オブジェクト 
    */
    protected HostForeignCashTransOrderDao( HostForeignCashTransOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostForeignCashTransOrderRow}オブジェクトを取得します。
   */
    public HostForeignCashTransOrderRow getHostForeignCashTransOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostForeignCashTransOrderRow}オブジェクトから{@@link HostForeignCashTransOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link HostForeignCashTransOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostForeignCashTransOrderDao}取得のために指定の{@@link HostForeignCashTransOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostForeignCashTransOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostForeignCashTransOrderDao forRow( HostForeignCashTransOrderRow row ) throws java.lang.IllegalArgumentException {
        return (HostForeignCashTransOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostForeignCashTransOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostForeignCashTransOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostForeignCashTransOrderPK}やデータベースレコードとして挿入される{@@link HostForeignCashTransOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostForeignCashTransOrderRow.TYPE );
    }


  /** 
   * {@@link HostForeignCashTransOrderRow}を一意に特定する{@@link HostForeignCashTransOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostForeignCashTransOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostForeignCashTransOrderParams}オブジェクトの主キーとして利用可能な{@@link HostForeignCashTransOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostForeignCashTransOrderPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostForeignCashTransOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostForeignCashTransOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostForeignCashTransOrderRow findRowByPk( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostForeignCashTransOrderPK pk = new HostForeignCashTransOrderPK( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostForeignCashTransOrderPKオブジェクトから{@@link HostForeignCashTransOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostForeignCashTransOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostForeignCashTransOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostForeignCashTransOrderRow findRowByPk( HostForeignCashTransOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostForeignCashTransOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(HostForeignCashTransOrderRow)}を使用してください。 
   */
    public static HostForeignCashTransOrderDao findDaoByPk( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostForeignCashTransOrderPK pk = new HostForeignCashTransOrderPK( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        HostForeignCashTransOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostForeignCashTransOrderPK)}および{@@link #forRow(HostForeignCashTransOrderRow)}を使用してください。 
   */
    public static HostForeignCashTransOrderDao findDaoByPk( HostForeignCashTransOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostForeignCashTransOrderRow row = findRowByPk( pk );
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
   * p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and にて指定の値から一意の{@@link HostForeignCashTransOrderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and の値と一致する{@@link HostForeignCashTransOrderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static HostForeignCashTransOrderRow findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostForeignCashTransOrderRow.TYPE,
            "request_code=? and institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostForeignCashTransOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostForeignCashTransOrderDao.findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String, String)}および{@@link #forRow(HostForeignCashTransOrderRow)}を使用してください。 
   */
    public static HostForeignCashTransOrderDao findDaoByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
