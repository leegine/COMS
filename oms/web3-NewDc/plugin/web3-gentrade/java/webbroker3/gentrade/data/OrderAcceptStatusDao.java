head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.37.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderAcceptStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OrderAcceptStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OrderAcceptStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see OrderAcceptStatusPK 
 * @@see OrderAcceptStatusRow 
 */
public class OrderAcceptStatusDao extends DataAccessObject {


  /** 
   * この{@@link OrderAcceptStatusDao}に関連する型指定のRowオブジェクト 
   */
    private OrderAcceptStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OrderAcceptStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link OrderAcceptStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OrderAcceptStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OrderAcceptStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderAcceptStatusRow )
                return new OrderAcceptStatusDao( (OrderAcceptStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderAcceptStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderAcceptStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OrderAcceptStatusRow}オブジェクト 
    */
    protected OrderAcceptStatusDao( OrderAcceptStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OrderAcceptStatusRow}オブジェクトを取得します。
   */
    public OrderAcceptStatusRow getOrderAcceptStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link OrderAcceptStatusRow}オブジェクトから{@@link OrderAcceptStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link OrderAcceptStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OrderAcceptStatusDao}取得のために指定の{@@link OrderAcceptStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OrderAcceptStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OrderAcceptStatusDao forRow( OrderAcceptStatusRow row ) throws java.lang.IllegalArgumentException {
        return (OrderAcceptStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderAcceptStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OrderAcceptStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OrderAcceptStatusPK}やデータベースレコードとして挿入される{@@link OrderAcceptStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderAcceptStatusRow.TYPE );
    }


  /** 
   * {@@link OrderAcceptStatusRow}を一意に特定する{@@link OrderAcceptStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OrderAcceptStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OrderAcceptStatusParams}オブジェクトの主キーとして利用可能な{@@link OrderAcceptStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OrderAcceptStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OrderAcceptStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderAccProduct 検索対象であるp_orderAccProductフィールドの値
   * @@param p_orderAccTransaction 検索対象であるp_orderAccTransactionフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderAcceptStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderAcceptStatusRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderAcceptStatusPK pk = new OrderAcceptStatusPK( p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOrderAcceptStatusPKオブジェクトから{@@link OrderAcceptStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOrderAcceptStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderAcceptStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderAcceptStatusRow findRowByPk( OrderAcceptStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderAcceptStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(OrderAcceptStatusRow)}を使用してください。 
   */
    public static OrderAcceptStatusDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderAcceptStatusPK pk = new OrderAcceptStatusPK( p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction );
        OrderAcceptStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OrderAcceptStatusPK)}および{@@link #forRow(OrderAcceptStatusRow)}を使用してください。 
   */
    public static OrderAcceptStatusDao findDaoByPk( OrderAcceptStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderAcceptStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction, and にて指定の値から一意の{@@link OrderAcceptStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderAccProduct 検索対象であるp_orderAccProductフィールドの値
   * @@param p_orderAccTransaction 検索対象であるp_orderAccTransactionフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction, and の値と一致する{@@link OrderAcceptStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OrderAcceptStatusRow findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderAcceptStatusRow.TYPE,
            "institution_code=? and branch_code=? and order_acc_product=? and order_acc_transaction=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderAcceptStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderAcceptStatusDao.findRowsByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(String, String, String, String)}および{@@link #forRow(OrderAcceptStatusRow)}を使用してください。 
   */
    public static OrderAcceptStatusDao findDaoByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction( p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
