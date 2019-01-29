head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderExecutedCountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.adminorderexecinquiry.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OrderExecutedCountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OrderExecutedCountRow}インスタンスへ関連付けることができます。 
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
 * @@see OrderExecutedCountPK 
 * @@see OrderExecutedCountRow 
 */
public class OrderExecutedCountDao extends DataAccessObject {


  /** 
   * この{@@link OrderExecutedCountDao}に関連する型指定のRowオブジェクト 
   */
    private OrderExecutedCountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OrderExecutedCountRow}と仮定される{@@link DataAccessObject}から新たに{@@link OrderExecutedCountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OrderExecutedCountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OrderExecutedCountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderExecutedCountRow )
                return new OrderExecutedCountDao( (OrderExecutedCountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderExecutedCountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderExecutedCountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OrderExecutedCountRow}オブジェクト 
    */
    protected OrderExecutedCountDao( OrderExecutedCountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OrderExecutedCountRow}オブジェクトを取得します。
   */
    public OrderExecutedCountRow getOrderExecutedCountRow() {
        return row;
    }


  /** 
   * 指定の{@@link OrderExecutedCountRow}オブジェクトから{@@link OrderExecutedCountDao}オブジェクトを作成します。 
   * これは実際の{@@link OrderExecutedCountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OrderExecutedCountDao}取得のために指定の{@@link OrderExecutedCountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OrderExecutedCountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OrderExecutedCountDao forRow( OrderExecutedCountRow row ) throws java.lang.IllegalArgumentException {
        return (OrderExecutedCountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderExecutedCountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OrderExecutedCountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OrderExecutedCountPK}やデータベースレコードとして挿入される{@@link OrderExecutedCountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderExecutedCountRow.TYPE );
    }


  /** 
   * {@@link OrderExecutedCountRow}を一意に特定する{@@link OrderExecutedCountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OrderExecutedCountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OrderExecutedCountParams}オブジェクトの主キーとして利用可能な{@@link OrderExecutedCountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OrderExecutedCountPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OrderExecutedCountRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_recordDiv 検索対象であるp_recordDivフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_productDiv 検索対象であるp_productDivフィールドの値
   * @@param p_orderChanel 検索対象であるp_orderChanelフィールドの値
   * @@param p_orderRootDiv 検索対象であるp_orderRootDivフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderExecutedCountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderExecutedCountRow findRowByPk( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderExecutedCountPK pk = new OrderExecutedCountPK( p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOrderExecutedCountPKオブジェクトから{@@link OrderExecutedCountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOrderExecutedCountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderExecutedCountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderExecutedCountRow findRowByPk( OrderExecutedCountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderExecutedCountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String,String,String,String)}および{@@link #forRow(OrderExecutedCountRow)}を使用してください。 
   */
    public static OrderExecutedCountDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderExecutedCountPK pk = new OrderExecutedCountPK( p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate );
        OrderExecutedCountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OrderExecutedCountPK)}および{@@link #forRow(OrderExecutedCountRow)}を使用してください。 
   */
    public static OrderExecutedCountDao findDaoByPk( OrderExecutedCountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderExecutedCountRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate, and にて指定の値から一意の{@@link OrderExecutedCountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_recordDiv 検索対象であるp_recordDivフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_productDiv 検索対象であるp_productDivフィールドの値
   * @@param p_orderChanel 検索対象であるp_orderChanelフィールドの値
   * @@param p_orderRootDiv 検索対象であるp_orderRootDivフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate, and の値と一致する{@@link OrderExecutedCountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OrderExecutedCountRow findRowByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderExecutedCountRow.TYPE,
            "institution_code=? and branch_code=? and record_div=? and market_code=? and product_div=? and order_chanel=? and order_root_div=? and biz_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderExecutedCountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderExecutedCountDao.findRowsByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate(String, String, String, String, String, String, String, String)}および{@@link #forRow(OrderExecutedCountRow)}を使用してください。 
   */
    public static OrderExecutedCountDao findDaoByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate( p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
