head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	OmsConOrderRequestDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rlsgateway.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link OmsConOrderRequestDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OmsConOrderRequestRow}インスタンスへ関連付けることができます。 
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
 * @@see OmsConOrderRequestPK 
 * @@see OmsConOrderRequestRow 
 */
public class OmsConOrderRequestDao extends DataAccessObject {


  /** 
   * この{@@link OmsConOrderRequestDao}に関連する型指定のRowオブジェクト 
   */
    private OmsConOrderRequestRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OmsConOrderRequestRow}と仮定される{@@link DataAccessObject}から新たに{@@link OmsConOrderRequestDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OmsConOrderRequestDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OmsConOrderRequestRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OmsConOrderRequestRow )
                return new OmsConOrderRequestDao( (OmsConOrderRequestRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OmsConOrderRequestRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OmsConOrderRequestRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OmsConOrderRequestRow}オブジェクト 
    */
    protected OmsConOrderRequestDao( OmsConOrderRequestRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OmsConOrderRequestRow}オブジェクトを取得します。
   */
    public OmsConOrderRequestRow getOmsConOrderRequestRow() {
        return row;
    }


  /** 
   * 指定の{@@link OmsConOrderRequestRow}オブジェクトから{@@link OmsConOrderRequestDao}オブジェクトを作成します。 
   * これは実際の{@@link OmsConOrderRequestRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OmsConOrderRequestDao}取得のために指定の{@@link OmsConOrderRequestRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OmsConOrderRequestDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OmsConOrderRequestDao forRow( OmsConOrderRequestRow row ) throws java.lang.IllegalArgumentException {
        return (OmsConOrderRequestDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OmsConOrderRequestRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OmsConOrderRequestRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OmsConOrderRequestPK}やデータベースレコードとして挿入される{@@link OmsConOrderRequestParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OmsConOrderRequestRow.TYPE );
    }


  /** 
   * {@@link OmsConOrderRequestRow}を一意に特定する{@@link OmsConOrderRequestPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OmsConOrderRequestRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OmsConOrderRequestParams}オブジェクトの主キーとして利用可能な{@@link OmsConOrderRequestPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OmsConOrderRequestPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OmsConOrderRequestRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderType 検索対象であるp_orderTypeフィールドの値
   * @@param p_requestType 検索対象であるp_requestTypeフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_subOrderId 検索対象であるp_subOrderIdフィールドの値
   * @@param p_subProductType 検索対象であるp_subProductTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OmsConOrderRequestRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OmsConOrderRequestRow findRowByPk( long p_accountId, long p_subAccountId, int p_orderType, int p_requestType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, long p_subOrderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_subProductType ) throws DataFindException, DataQueryException, DataNetworkException {
        OmsConOrderRequestPK pk = new OmsConOrderRequestPK( p_accountId, p_subAccountId, p_orderType, p_requestType, p_orderId, p_productType, p_subOrderId, p_subProductType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOmsConOrderRequestPKオブジェクトから{@@link OmsConOrderRequestRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOmsConOrderRequestPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OmsConOrderRequestRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OmsConOrderRequestRow findRowByPk( OmsConOrderRequestPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OmsConOrderRequestRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,long,int,int,long,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum,long,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(OmsConOrderRequestRow)}を使用してください。 
   */
    public static OmsConOrderRequestDao findDaoByPk( long p_accountId, long p_subAccountId, int p_orderType, int p_requestType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, long p_subOrderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_subProductType ) throws DataFindException, DataQueryException, DataNetworkException {
        OmsConOrderRequestPK pk = new OmsConOrderRequestPK( p_accountId, p_subAccountId, p_orderType, p_requestType, p_orderId, p_productType, p_subOrderId, p_subProductType );
        OmsConOrderRequestRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OmsConOrderRequestPK)}および{@@link #forRow(OmsConOrderRequestRow)}を使用してください。 
   */
    public static OmsConOrderRequestDao findDaoByPk( OmsConOrderRequestPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OmsConOrderRequestRow row = findRowByPk( pk );
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
   * p_accountId, p_subAccountId, p_orderType, p_requestType, p_orderId, p_productType, p_subOrderId, p_subProductType, and にて指定の値から一意の{@@link OmsConOrderRequestRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderType 検索対象であるp_orderTypeフィールドの値
   * @@param p_requestType 検索対象であるp_requestTypeフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_subOrderId 検索対象であるp_subOrderIdフィールドの値
   * @@param p_subProductType 検索対象であるp_subProductTypeフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_orderType, p_requestType, p_orderId, p_productType, p_subOrderId, p_subProductType, and の値と一致する{@@link OmsConOrderRequestRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OmsConOrderRequestRow findRowByAccountIdSubAccountIdOrderTypeRequestTypeOrderIdProductTypeSubOrderIdSubProductType( long p_accountId, long p_subAccountId, int p_orderType, int p_requestType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, long p_subOrderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_subProductType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OmsConOrderRequestRow.TYPE,
            "account_id=? and sub_account_id=? and order_type=? and request_type=? and order_id=? and product_type=? and sub_order_id=? and sub_product_type=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Integer(p_orderType), new Integer(p_requestType), new Long(p_orderId), p_productType, new Long(p_subOrderId), p_subProductType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OmsConOrderRequestRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OmsConOrderRequestDao.findRowsByAccountIdSubAccountIdOrderTypeRequestTypeOrderIdProductTypeSubOrderIdSubProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdSubAccountIdOrderTypeRequestTypeOrderIdProductTypeSubOrderIdSubProductType(long, long, int, int, long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(OmsConOrderRequestRow)}を使用してください。 
   */
    public static OmsConOrderRequestDao findDaoByAccountIdSubAccountIdOrderTypeRequestTypeOrderIdProductTypeSubOrderIdSubProductType( long p_accountId, long p_subAccountId, int p_orderType, int p_requestType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, long p_subOrderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_subProductType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdOrderTypeRequestTypeOrderIdProductTypeSubOrderIdSubProductType( p_accountId, p_subAccountId, p_orderType, p_requestType, p_orderId, p_productType, p_subOrderId, p_subProductType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and にて指定の値に一致する{@@link OmsConOrderRequestRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_status, and の値と一致する{@@link OmsConOrderRequestRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            OmsConOrderRequestRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdStatus(long, String)}および{@@link #forRow(OmsConOrderRequestRow)}を使用してください。 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }

}
@
