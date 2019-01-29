head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.28.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderUnitIntroduceDivDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OrderUnitIntroduceDivDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OrderUnitIntroduceDivRow}インスタンスへ関連付けることができます。 
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
 * @@see OrderUnitIntroduceDivPK 
 * @@see OrderUnitIntroduceDivRow 
 */
public class OrderUnitIntroduceDivDao extends DataAccessObject {


  /** 
   * この{@@link OrderUnitIntroduceDivDao}に関連する型指定のRowオブジェクト 
   */
    private OrderUnitIntroduceDivRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OrderUnitIntroduceDivRow}と仮定される{@@link DataAccessObject}から新たに{@@link OrderUnitIntroduceDivDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OrderUnitIntroduceDivDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OrderUnitIntroduceDivRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderUnitIntroduceDivRow )
                return new OrderUnitIntroduceDivDao( (OrderUnitIntroduceDivRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderUnitIntroduceDivRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderUnitIntroduceDivRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OrderUnitIntroduceDivRow}オブジェクト 
    */
    protected OrderUnitIntroduceDivDao( OrderUnitIntroduceDivRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OrderUnitIntroduceDivRow}オブジェクトを取得します。
   */
    public OrderUnitIntroduceDivRow getOrderUnitIntroduceDivRow() {
        return row;
    }


  /** 
   * 指定の{@@link OrderUnitIntroduceDivRow}オブジェクトから{@@link OrderUnitIntroduceDivDao}オブジェクトを作成します。 
   * これは実際の{@@link OrderUnitIntroduceDivRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OrderUnitIntroduceDivDao}取得のために指定の{@@link OrderUnitIntroduceDivRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OrderUnitIntroduceDivDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OrderUnitIntroduceDivDao forRow( OrderUnitIntroduceDivRow row ) throws java.lang.IllegalArgumentException {
        return (OrderUnitIntroduceDivDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderUnitIntroduceDivRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OrderUnitIntroduceDivRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OrderUnitIntroduceDivPK}やデータベースレコードとして挿入される{@@link OrderUnitIntroduceDivParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderUnitIntroduceDivRow.TYPE );
    }


  /** 
   * {@@link OrderUnitIntroduceDivRow}を一意に特定する{@@link OrderUnitIntroduceDivPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OrderUnitIntroduceDivRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OrderUnitIntroduceDivParams}オブジェクトの主キーとして利用可能な{@@link OrderUnitIntroduceDivPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OrderUnitIntroduceDivPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OrderUnitIntroduceDivRow}オブジェクトを検索します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderUnitIntroduceDivRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderUnitIntroduceDivRow findRowByPk( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderUnitIntroduceDivPK pk = new OrderUnitIntroduceDivPK( p_orderUnitId, p_productType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOrderUnitIntroduceDivPKオブジェクトから{@@link OrderUnitIntroduceDivRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOrderUnitIntroduceDivPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderUnitIntroduceDivRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderUnitIntroduceDivRow findRowByPk( OrderUnitIntroduceDivPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderUnitIntroduceDivRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(OrderUnitIntroduceDivRow)}を使用してください。 
   */
    public static OrderUnitIntroduceDivDao findDaoByPk( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderUnitIntroduceDivPK pk = new OrderUnitIntroduceDivPK( p_orderUnitId, p_productType );
        OrderUnitIntroduceDivRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OrderUnitIntroduceDivPK)}および{@@link #forRow(OrderUnitIntroduceDivRow)}を使用してください。 
   */
    public static OrderUnitIntroduceDivDao findDaoByPk( OrderUnitIntroduceDivPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderUnitIntroduceDivRow row = findRowByPk( pk );
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
   * p_orderUnitId, p_productType, and にて指定の値から一意の{@@link OrderUnitIntroduceDivRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_orderUnitId, p_productType, and の値と一致する{@@link OrderUnitIntroduceDivRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OrderUnitIntroduceDivRow findRowByOrderUnitIdProductType( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderUnitIntroduceDivRow.TYPE,
            "order_unit_id=? and product_type=?",
            null,
            new Object[] { new Long(p_orderUnitId), p_productType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderUnitIntroduceDivRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderUnitIntroduceDivDao.findRowsByOrderUnitIdProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderUnitIdProductType(long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(OrderUnitIntroduceDivRow)}を使用してください。 
   */
    public static OrderUnitIntroduceDivDao findDaoByOrderUnitIdProductType( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderUnitIdProductType( p_orderUnitId, p_productType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
