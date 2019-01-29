head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	Web3QuoteProtoDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.prototype.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.quoteadaptor.prototype.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link Web3QuoteProtoDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link Web3QuoteProtoRow}インスタンスへ関連付けることができます。 
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
 * @@see Web3QuoteProtoPK 
 * @@see Web3QuoteProtoRow 
 */
public class Web3QuoteProtoDao extends DataAccessObject {


  /** 
   * この{@@link Web3QuoteProtoDao}に関連する型指定のRowオブジェクト 
   */
    private Web3QuoteProtoRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link Web3QuoteProtoRow}と仮定される{@@link DataAccessObject}から新たに{@@link Web3QuoteProtoDao}を返します。 
         * @@return 指定のRowに結びつく{@@link Web3QuoteProtoDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link Web3QuoteProtoRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof Web3QuoteProtoRow )
                return new Web3QuoteProtoDao( (Web3QuoteProtoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a Web3QuoteProtoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link Web3QuoteProtoRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link Web3QuoteProtoRow}オブジェクト 
    */
    protected Web3QuoteProtoDao( Web3QuoteProtoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link Web3QuoteProtoRow}オブジェクトを取得します。
   */
    public Web3QuoteProtoRow getWeb3QuoteProtoRow() {
        return row;
    }


  /** 
   * 指定の{@@link Web3QuoteProtoRow}オブジェクトから{@@link Web3QuoteProtoDao}オブジェクトを作成します。 
   * これは実際の{@@link Web3QuoteProtoRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link Web3QuoteProtoDao}取得のために指定の{@@link Web3QuoteProtoRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link Web3QuoteProtoDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static Web3QuoteProtoDao forRow( Web3QuoteProtoRow row ) throws java.lang.IllegalArgumentException {
        return (Web3QuoteProtoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link Web3QuoteProtoRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link Web3QuoteProtoRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link Web3QuoteProtoPK}やデータベースレコードとして挿入される{@@link Web3QuoteProtoParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( Web3QuoteProtoRow.TYPE );
    }


  /** 
   * {@@link Web3QuoteProtoRow}を一意に特定する{@@link Web3QuoteProtoPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link Web3QuoteProtoRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link Web3QuoteProtoParams}オブジェクトの主キーとして利用可能な{@@link Web3QuoteProtoPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static Web3QuoteProtoPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new Web3QuoteProtoPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link Web3QuoteProtoRow}オブジェクトを検索します。 
   * 
   * @@param p_quoteDataId 検索対象であるp_quoteDataIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link Web3QuoteProtoRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static Web3QuoteProtoRow findRowByPk( long p_quoteDataId ) throws DataFindException, DataQueryException, DataNetworkException {
        Web3QuoteProtoPK pk = new Web3QuoteProtoPK( p_quoteDataId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のWeb3QuoteProtoPKオブジェクトから{@@link Web3QuoteProtoRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するWeb3QuoteProtoPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link Web3QuoteProtoRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static Web3QuoteProtoRow findRowByPk( Web3QuoteProtoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (Web3QuoteProtoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(Web3QuoteProtoRow)}を使用してください。 
   */
    public static Web3QuoteProtoDao findDaoByPk( long p_quoteDataId ) throws DataFindException, DataQueryException, DataNetworkException {
        Web3QuoteProtoPK pk = new Web3QuoteProtoPK( p_quoteDataId );
        Web3QuoteProtoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(Web3QuoteProtoPK)}および{@@link #forRow(Web3QuoteProtoRow)}を使用してください。 
   */
    public static Web3QuoteProtoDao findDaoByPk( Web3QuoteProtoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        Web3QuoteProtoRow row = findRowByPk( pk );
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


  /** 
   * p_realType, p_dataType, p_marketCode, p_productCode, and にて指定の値に一致する{@@link Web3QuoteProtoRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_realType 検索対象であるp_realTypeフィールドの値
   * @@param p_dataType 検索対象であるp_dataTypeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のp_realType, p_dataType, p_marketCode, p_productCode, and の値と一致する{@@link Web3QuoteProtoRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRealTypeDataTypeMarketCodeProductCode( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            Web3QuoteProtoRow.TYPE,
            "real_type=? and data_type=? and market_code=? and product_code=?",
            null,
            new Object[] { p_realType, p_dataType, p_marketCode, p_productCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRealTypeDataTypeMarketCodeProductCode(webbroker3.quoteadaptor.RealType, webbroker3.quoteadaptor.DataType, String, String)}および{@@link #forRow(Web3QuoteProtoRow)}を使用してください。 
   */
    public static List findDaosByRealTypeDataTypeMarketCodeProductCode( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRealTypeDataTypeMarketCodeProductCode( p_realType, p_dataType, p_marketCode, p_productCode ) );
    }


  /** 
   * p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, and にて指定の値に一致する{@@link Web3QuoteProtoRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_realType 検索対象であるp_realTypeフィールドの値
   * @@param p_dataType 検索対象であるp_dataTypeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_contractMonth 検索対象であるp_contractMonthフィールドの値
   * 
   * @@return 引数指定のp_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, and の値と一致する{@@link Web3QuoteProtoRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonth( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode, String p_contractMonth ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            Web3QuoteProtoRow.TYPE,
            "real_type=? and data_type=? and market_code=? and product_code=? and contract_month=?",
            null,
            new Object[] { p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonth(webbroker3.quoteadaptor.RealType, webbroker3.quoteadaptor.DataType, String, String, String)}および{@@link #forRow(Web3QuoteProtoRow)}を使用してください。 
   */
    public static List findDaosByRealTypeDataTypeMarketCodeProductCodeContractMonth( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode, String p_contractMonth ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonth( p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth ) );
    }


  /** 
   * p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, p_putAndCall, p_strikePrice, and にて指定の値に一致する{@@link Web3QuoteProtoRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_realType 検索対象であるp_realTypeフィールドの値
   * @@param p_dataType 検索対象であるp_dataTypeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_contractMonth 検索対象であるp_contractMonthフィールドの値
   * @@param p_putAndCall 検索対象であるp_putAndCallフィールドの値
   * @@param p_strikePrice 検索対象であるp_strikePriceフィールドの値
   * 
   * @@return 引数指定のp_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, p_putAndCall, p_strikePrice, and の値と一致する{@@link Web3QuoteProtoRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonthPutAndCallStrikePrice( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode, String p_contractMonth, String p_putAndCall, Double p_strikePrice ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            Web3QuoteProtoRow.TYPE,
            "real_type=? and data_type=? and market_code=? and product_code=? and contract_month=? and put_and_call=? and strike_price=?",
            null,
            new Object[] { p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, p_putAndCall, p_strikePrice } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonthPutAndCallStrikePrice(webbroker3.quoteadaptor.RealType, webbroker3.quoteadaptor.DataType, String, String, String, String, Double)}および{@@link #forRow(Web3QuoteProtoRow)}を使用してください。 
   */
    public static List findDaosByRealTypeDataTypeMarketCodeProductCodeContractMonthPutAndCallStrikePrice( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode, String p_contractMonth, String p_putAndCall, Double p_strikePrice ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonthPutAndCallStrikePrice( p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, p_putAndCall, p_strikePrice ) );
    }


  /** 
   * p_realType, p_dataType, p_productCode, and にて指定の値に一致する{@@link Web3QuoteProtoRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_realType 検索対象であるp_realTypeフィールドの値
   * @@param p_dataType 検索対象であるp_dataTypeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のp_realType, p_dataType, p_productCode, and の値と一致する{@@link Web3QuoteProtoRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRealTypeDataTypeProductCode( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_productCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            Web3QuoteProtoRow.TYPE,
            "real_type=? and data_type=? and product_code=?",
            null,
            new Object[] { p_realType, p_dataType, p_productCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRealTypeDataTypeProductCode(webbroker3.quoteadaptor.RealType, webbroker3.quoteadaptor.DataType, String)}および{@@link #forRow(Web3QuoteProtoRow)}を使用してください。 
   */
    public static List findDaosByRealTypeDataTypeProductCode( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_productCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRealTypeDataTypeProductCode( p_realType, p_dataType, p_productCode ) );
    }

}
@
