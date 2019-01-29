head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TradeDetailHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradehistory.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TradeDetailHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TradeDetailHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see TradeDetailHistoryPK 
 * @@see TradeDetailHistoryRow 
 */
public class TradeDetailHistoryDao extends DataAccessObject {


  /** 
   * この{@@link TradeDetailHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private TradeDetailHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TradeDetailHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link TradeDetailHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TradeDetailHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TradeDetailHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TradeDetailHistoryRow )
                return new TradeDetailHistoryDao( (TradeDetailHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TradeDetailHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TradeDetailHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TradeDetailHistoryRow}オブジェクト 
    */
    protected TradeDetailHistoryDao( TradeDetailHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TradeDetailHistoryRow}オブジェクトを取得します。
   */
    public TradeDetailHistoryRow getTradeDetailHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link TradeDetailHistoryRow}オブジェクトから{@@link TradeDetailHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link TradeDetailHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TradeDetailHistoryDao}取得のために指定の{@@link TradeDetailHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TradeDetailHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TradeDetailHistoryDao forRow( TradeDetailHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (TradeDetailHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TradeDetailHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TradeDetailHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TradeDetailHistoryPK}やデータベースレコードとして挿入される{@@link TradeDetailHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TradeDetailHistoryRow.TYPE );
    }


  /** 
   * {@@link TradeDetailHistoryRow}を一意に特定する{@@link TradeDetailHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TradeDetailHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TradeDetailHistoryParams}オブジェクトの主キーとして利用可能な{@@link TradeDetailHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TradeDetailHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TradeDetailHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TradeDetailHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_tradeDetailHistoryId 検索対象であるp_tradeDetailHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradeDetailHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradeDetailHistoryRow findRowByPk( long p_tradeDetailHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeDetailHistoryPK pk = new TradeDetailHistoryPK( p_tradeDetailHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTradeDetailHistoryPKオブジェクトから{@@link TradeDetailHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTradeDetailHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradeDetailHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradeDetailHistoryRow findRowByPk( TradeDetailHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TradeDetailHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TradeDetailHistoryRow)}を使用してください。 
   */
    public static TradeDetailHistoryDao findDaoByPk( long p_tradeDetailHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeDetailHistoryPK pk = new TradeDetailHistoryPK( p_tradeDetailHistoryId );
        TradeDetailHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TradeDetailHistoryPK)}および{@@link #forRow(TradeDetailHistoryRow)}を使用してください。 
   */
    public static TradeDetailHistoryDao findDaoByPk( TradeDetailHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeDetailHistoryRow row = findRowByPk( pk );
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
   * p_tradeDetailHistoryId, and にて指定の値から一意の{@@link TradeDetailHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tradeDetailHistoryId 検索対象であるp_tradeDetailHistoryIdフィールドの値
   * 
   * @@return 引数指定のp_tradeDetailHistoryId, and の値と一致する{@@link TradeDetailHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TradeDetailHistoryRow findRowByTradeDetailHistoryId( long p_tradeDetailHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TradeDetailHistoryRow.TYPE,
            "trade_detail_history_id=?",
            null,
            new Object[] { new Long(p_tradeDetailHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TradeDetailHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TradeDetailHistoryDao.findRowsByTradeDetailHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTradeDetailHistoryId(long)}および{@@link #forRow(TradeDetailHistoryRow)}を使用してください。 
   */
    public static TradeDetailHistoryDao findDaoByTradeDetailHistoryId( long p_tradeDetailHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradeDetailHistoryId( p_tradeDetailHistoryId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_quantity, p_price, p_accountDiv, p_netAmount, p_status, p_execDate, and にて指定の値に一致する{@@link TradeDetailHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_quantity 検索対象であるp_quantityフィールドの値
   * @@param p_price 検索対象であるp_priceフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_netAmount 検索対象であるp_netAmountフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_execDate 検索対象であるp_execDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_quantity, p_price, p_accountDiv, p_netAmount, p_status, p_execDate, and の値と一致する{@@link TradeDetailHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeQuantityPriceAccountDivNetAmountStatusExecDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productCode, Double p_quantity, Double p_price, String p_accountDiv, Double p_netAmount, String p_status, java.sql.Timestamp p_execDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradeDetailHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=? and product_code=? and quantity=? and price=? and account_div=? and net_amount=? and status=? and exec_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_quantity, p_price, p_accountDiv, p_netAmount, p_status, p_execDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeQuantityPriceAccountDivNetAmountStatusExecDate(String, String, String, java.sql.Timestamp, String, Double, Double, String, Double, String, java.sql.Timestamp)}および{@@link #forRow(TradeDetailHistoryRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeQuantityPriceAccountDivNetAmountStatusExecDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productCode, Double p_quantity, Double p_price, String p_accountDiv, Double p_netAmount, String p_status, java.sql.Timestamp p_execDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeQuantityPriceAccountDivNetAmountStatusExecDate( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_quantity, p_price, p_accountDiv, p_netAmount, p_status, p_execDate ) );
    }


  /** 
   * p_deliveryDate, and にて指定の値に一致する{@@link TradeDetailHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * 
   * @@return 引数指定のp_deliveryDate, and の値と一致する{@@link TradeDetailHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradeDetailHistoryRow.TYPE,
            "delivery_date=?",
            null,
            new Object[] { p_deliveryDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByDeliveryDate(java.sql.Timestamp)}および{@@link #forRow(TradeDetailHistoryRow)}を使用してください。 
   */
    public static List findDaosByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByDeliveryDate( p_deliveryDate ) );
    }

}
@
