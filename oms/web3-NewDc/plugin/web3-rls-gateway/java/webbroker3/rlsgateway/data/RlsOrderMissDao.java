head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOrderMissDao.java;


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
 * {@@link RlsOrderMissDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RlsOrderMissRow}インスタンスへ関連付けることができます。 
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
 * @@see RlsOrderMissPK 
 * @@see RlsOrderMissRow 
 */
public class RlsOrderMissDao extends DataAccessObject {


  /** 
   * この{@@link RlsOrderMissDao}に関連する型指定のRowオブジェクト 
   */
    private RlsOrderMissRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RlsOrderMissRow}と仮定される{@@link DataAccessObject}から新たに{@@link RlsOrderMissDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RlsOrderMissDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RlsOrderMissRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsOrderMissRow )
                return new RlsOrderMissDao( (RlsOrderMissRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsOrderMissRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsOrderMissRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RlsOrderMissRow}オブジェクト 
    */
    protected RlsOrderMissDao( RlsOrderMissRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RlsOrderMissRow}オブジェクトを取得します。
   */
    public RlsOrderMissRow getRlsOrderMissRow() {
        return row;
    }


  /** 
   * 指定の{@@link RlsOrderMissRow}オブジェクトから{@@link RlsOrderMissDao}オブジェクトを作成します。 
   * これは実際の{@@link RlsOrderMissRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RlsOrderMissDao}取得のために指定の{@@link RlsOrderMissRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RlsOrderMissDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RlsOrderMissDao forRow( RlsOrderMissRow row ) throws java.lang.IllegalArgumentException {
        return (RlsOrderMissDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsOrderMissRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RlsOrderMissRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RlsOrderMissPK}やデータベースレコードとして挿入される{@@link RlsOrderMissParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsOrderMissRow.TYPE );
    }


  /** 
   * {@@link RlsOrderMissRow}を一意に特定する{@@link RlsOrderMissPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RlsOrderMissRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RlsOrderMissParams}オブジェクトの主キーとして利用可能な{@@link RlsOrderMissPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RlsOrderMissPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RlsOrderMissRow}オブジェクトを検索します。 
   * 
   * @@param p_missType 検索対象であるp_missTypeフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_orderActionSerialNo 検索対象であるp_orderActionSerialNoフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_omsCondOrderType 検索対象であるp_omsCondOrderTypeフィールドの値
   * @@param p_detectType 検索対象であるp_detectTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsOrderMissRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsOrderMissRow findRowByPk( String p_missType, long p_accountId, long p_subAccountId, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOrderMissPK pk = new RlsOrderMissPK( p_missType, p_accountId, p_subAccountId, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRlsOrderMissPKオブジェクトから{@@link RlsOrderMissRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRlsOrderMissPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsOrderMissRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsOrderMissRow findRowByPk( RlsOrderMissPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsOrderMissRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,long,long,long,int,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum,int,String)}および{@@link #forRow(RlsOrderMissRow)}を使用してください。 
   */
    public static RlsOrderMissDao findDaoByPk( String p_missType, long p_accountId, long p_subAccountId, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOrderMissPK pk = new RlsOrderMissPK( p_missType, p_accountId, p_subAccountId, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType );
        RlsOrderMissRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RlsOrderMissPK)}および{@@link #forRow(RlsOrderMissRow)}を使用してください。 
   */
    public static RlsOrderMissDao findDaoByPk( RlsOrderMissPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOrderMissRow row = findRowByPk( pk );
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
   * p_accountId, p_subAccountId, p_missType, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType, and にて指定の値から一意の{@@link RlsOrderMissRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_missType 検索対象であるp_missTypeフィールドの値
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_orderActionSerialNo 検索対象であるp_orderActionSerialNoフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_omsCondOrderType 検索対象であるp_omsCondOrderTypeフィールドの値
   * @@param p_detectType 検索対象であるp_detectTypeフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_missType, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType, and の値と一致する{@@link RlsOrderMissRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RlsOrderMissRow findRowByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType( long p_accountId, long p_subAccountId, String p_missType, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsOrderMissRow.TYPE,
            "account_id=? and sub_account_id=? and miss_type=? and order_unit_id=? and order_action_serial_no=? and product_type=? and oms_cond_order_type=? and detect_type=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), p_missType, new Long(p_orderUnitId), new Integer(p_orderActionSerialNo), p_productType, new Integer(p_omsCondOrderType), p_detectType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsOrderMissRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsOrderMissDao.findRowsByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType(long, long, String, long, int, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, int, String)}および{@@link #forRow(RlsOrderMissRow)}を使用してください。 
   */
    public static RlsOrderMissDao findDaoByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType( long p_accountId, long p_subAccountId, String p_missType, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType( p_accountId, p_subAccountId, p_missType, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_orderId, p_productType, and にて指定の値に一致する{@@link RlsOrderMissRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_orderId, p_productType, and の値と一致する{@@link RlsOrderMissRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderIdProductType( long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsOrderMissRow.TYPE,
            "order_id=? and product_type=?",
            null,
            new Object[] { new Long(p_orderId), p_productType } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderIdProductType(long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(RlsOrderMissRow)}を使用してください。 
   */
    public static List findDaosByOrderIdProductType( long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderIdProductType( p_orderId, p_productType ) );
    }

}
@
