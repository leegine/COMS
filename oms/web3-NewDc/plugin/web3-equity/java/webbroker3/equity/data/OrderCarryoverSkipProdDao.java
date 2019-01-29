head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderCarryoverSkipProdDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OrderCarryoverSkipProdDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OrderCarryoverSkipProdRow}インスタンスへ関連付けることができます。 
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
 * @@see OrderCarryoverSkipProdPK 
 * @@see OrderCarryoverSkipProdRow 
 */
public class OrderCarryoverSkipProdDao extends DataAccessObject {


  /** 
   * この{@@link OrderCarryoverSkipProdDao}に関連する型指定のRowオブジェクト 
   */
    private OrderCarryoverSkipProdRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OrderCarryoverSkipProdRow}と仮定される{@@link DataAccessObject}から新たに{@@link OrderCarryoverSkipProdDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OrderCarryoverSkipProdDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OrderCarryoverSkipProdRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderCarryoverSkipProdRow )
                return new OrderCarryoverSkipProdDao( (OrderCarryoverSkipProdRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderCarryoverSkipProdRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderCarryoverSkipProdRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OrderCarryoverSkipProdRow}オブジェクト 
    */
    protected OrderCarryoverSkipProdDao( OrderCarryoverSkipProdRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OrderCarryoverSkipProdRow}オブジェクトを取得します。
   */
    public OrderCarryoverSkipProdRow getOrderCarryoverSkipProdRow() {
        return row;
    }


  /** 
   * 指定の{@@link OrderCarryoverSkipProdRow}オブジェクトから{@@link OrderCarryoverSkipProdDao}オブジェクトを作成します。 
   * これは実際の{@@link OrderCarryoverSkipProdRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OrderCarryoverSkipProdDao}取得のために指定の{@@link OrderCarryoverSkipProdRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OrderCarryoverSkipProdDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OrderCarryoverSkipProdDao forRow( OrderCarryoverSkipProdRow row ) throws java.lang.IllegalArgumentException {
        return (OrderCarryoverSkipProdDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderCarryoverSkipProdRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OrderCarryoverSkipProdRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OrderCarryoverSkipProdPK}やデータベースレコードとして挿入される{@@link OrderCarryoverSkipProdParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderCarryoverSkipProdRow.TYPE );
    }


  /** 
   * {@@link OrderCarryoverSkipProdRow}を一意に特定する{@@link OrderCarryoverSkipProdPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OrderCarryoverSkipProdRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OrderCarryoverSkipProdParams}オブジェクトの主キーとして利用可能な{@@link OrderCarryoverSkipProdPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OrderCarryoverSkipProdPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OrderCarryoverSkipProdRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderCarryoverSkipProdRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderCarryoverSkipProdRow findRowByPk( String p_institutionCode, String p_productCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderCarryoverSkipProdPK pk = new OrderCarryoverSkipProdPK( p_institutionCode, p_productCode, p_marketCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOrderCarryoverSkipProdPKオブジェクトから{@@link OrderCarryoverSkipProdRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOrderCarryoverSkipProdPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderCarryoverSkipProdRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderCarryoverSkipProdRow findRowByPk( OrderCarryoverSkipProdPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderCarryoverSkipProdRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(OrderCarryoverSkipProdRow)}を使用してください。 
   */
    public static OrderCarryoverSkipProdDao findDaoByPk( String p_institutionCode, String p_productCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderCarryoverSkipProdPK pk = new OrderCarryoverSkipProdPK( p_institutionCode, p_productCode, p_marketCode );
        OrderCarryoverSkipProdRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OrderCarryoverSkipProdPK)}および{@@link #forRow(OrderCarryoverSkipProdRow)}を使用してください。 
   */
    public static OrderCarryoverSkipProdDao findDaoByPk( OrderCarryoverSkipProdPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderCarryoverSkipProdRow row = findRowByPk( pk );
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

        // (none) 

}
@
