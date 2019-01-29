head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderSwitchingDao.java;


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
 * {@@link OrderSwitchingDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OrderSwitchingRow}インスタンスへ関連付けることができます。 
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
 * @@see OrderSwitchingPK 
 * @@see OrderSwitchingRow 
 */
public class OrderSwitchingDao extends DataAccessObject {


  /** 
   * この{@@link OrderSwitchingDao}に関連する型指定のRowオブジェクト 
   */
    private OrderSwitchingRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OrderSwitchingRow}と仮定される{@@link DataAccessObject}から新たに{@@link OrderSwitchingDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OrderSwitchingDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OrderSwitchingRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderSwitchingRow )
                return new OrderSwitchingDao( (OrderSwitchingRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderSwitchingRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderSwitchingRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OrderSwitchingRow}オブジェクト 
    */
    protected OrderSwitchingDao( OrderSwitchingRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OrderSwitchingRow}オブジェクトを取得します。
   */
    public OrderSwitchingRow getOrderSwitchingRow() {
        return row;
    }


  /** 
   * 指定の{@@link OrderSwitchingRow}オブジェクトから{@@link OrderSwitchingDao}オブジェクトを作成します。 
   * これは実際の{@@link OrderSwitchingRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OrderSwitchingDao}取得のために指定の{@@link OrderSwitchingRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OrderSwitchingDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OrderSwitchingDao forRow( OrderSwitchingRow row ) throws java.lang.IllegalArgumentException {
        return (OrderSwitchingDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderSwitchingRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OrderSwitchingRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OrderSwitchingPK}やデータベースレコードとして挿入される{@@link OrderSwitchingParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderSwitchingRow.TYPE );
    }


  /** 
   * {@@link OrderSwitchingRow}を一意に特定する{@@link OrderSwitchingPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OrderSwitchingRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OrderSwitchingParams}オブジェクトの主キーとして利用可能な{@@link OrderSwitchingPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OrderSwitchingPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OrderSwitchingRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_submitOrderRouteDiv 検索対象であるp_submitOrderRouteDivフィールドの値
   * @@param p_frontOrderSystemCode 検索対象であるp_frontOrderSystemCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderSwitchingRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderSwitchingRow findRowByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderSwitchingPK pk = new OrderSwitchingPK( p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOrderSwitchingPKオブジェクトから{@@link OrderSwitchingRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOrderSwitchingPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderSwitchingRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderSwitchingRow findRowByPk( OrderSwitchingPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderSwitchingRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum,String,String,String)}および{@@link #forRow(OrderSwitchingRow)}を使用してください。 
   */
    public static OrderSwitchingDao findDaoByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderSwitchingPK pk = new OrderSwitchingPK( p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode );
        OrderSwitchingRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OrderSwitchingPK)}および{@@link #forRow(OrderSwitchingRow)}を使用してください。 
   */
    public static OrderSwitchingDao findDaoByPk( OrderSwitchingPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderSwitchingRow row = findRowByPk( pk );
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
   * p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode, and にて指定の値から一意の{@@link OrderSwitchingRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_submitOrderRouteDiv 検索対象であるp_submitOrderRouteDivフィールドの値
   * @@param p_frontOrderSystemCode 検索対象であるp_frontOrderSystemCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode, and の値と一致する{@@link OrderSwitchingRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OrderSwitchingRow findRowByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderSwitchingRow.TYPE,
            "institution_code=? and product_type=? and market_code=? and submit_order_route_div=? and front_order_system_code=?",
            null,
            new Object[] { p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderSwitchingRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderSwitchingDao.findRowsByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode(String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String, String, String)}および{@@link #forRow(OrderSwitchingRow)}を使用してください。 
   */
    public static OrderSwitchingDao findDaoByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode( p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
