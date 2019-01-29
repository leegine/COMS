head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TradingTimeDao.java;


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
 * {@@link TradingTimeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TradingTimeRow}インスタンスへ関連付けることができます。 
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
 * @@see TradingTimePK 
 * @@see TradingTimeRow 
 */
public class TradingTimeDao extends DataAccessObject {


  /** 
   * この{@@link TradingTimeDao}に関連する型指定のRowオブジェクト 
   */
    private TradingTimeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TradingTimeRow}と仮定される{@@link DataAccessObject}から新たに{@@link TradingTimeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TradingTimeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TradingTimeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TradingTimeRow )
                return new TradingTimeDao( (TradingTimeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TradingTimeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TradingTimeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TradingTimeRow}オブジェクト 
    */
    protected TradingTimeDao( TradingTimeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TradingTimeRow}オブジェクトを取得します。
   */
    public TradingTimeRow getTradingTimeRow() {
        return row;
    }


  /** 
   * 指定の{@@link TradingTimeRow}オブジェクトから{@@link TradingTimeDao}オブジェクトを作成します。 
   * これは実際の{@@link TradingTimeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TradingTimeDao}取得のために指定の{@@link TradingTimeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TradingTimeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TradingTimeDao forRow( TradingTimeRow row ) throws java.lang.IllegalArgumentException {
        return (TradingTimeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TradingTimeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TradingTimeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TradingTimePK}やデータベースレコードとして挿入される{@@link TradingTimeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TradingTimeRow.TYPE );
    }


  /** 
   * {@@link TradingTimeRow}を一意に特定する{@@link TradingTimePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TradingTimeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TradingTimeParams}オブジェクトの主キーとして利用可能な{@@link TradingTimePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TradingTimePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TradingTimeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_tradingTimeType 検索対象であるp_tradingTimeTypeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_bizDateType 検索対象であるp_bizDateTypeフィールドの値
   * @@param p_startTime 検索対象であるp_startTimeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradingTimeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradingTimeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) throws DataFindException, DataQueryException, DataNetworkException {
        TradingTimePK pk = new TradingTimePK( p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTradingTimePKオブジェクトから{@@link TradingTimeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTradingTimePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradingTimeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradingTimeRow findRowByPk( TradingTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TradingTimeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String,String,String)}および{@@link #forRow(TradingTimeRow)}を使用してください。 
   */
    public static TradingTimeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) throws DataFindException, DataQueryException, DataNetworkException {
        TradingTimePK pk = new TradingTimePK( p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime );
        TradingTimeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TradingTimePK)}および{@@link #forRow(TradingTimeRow)}を使用してください。 
   */
    public static TradingTimeDao findDaoByPk( TradingTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TradingTimeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime, and にて指定の値に一致する{@@link TradingTimeRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_tradingTimeType 検索対象であるp_tradingTimeTypeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_bizDateType 検索対象であるp_bizDateTypeフィールドの値
   * @@param p_startTime 検索対象であるp_startTimeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime, and の値と一致する{@@link TradingTimeRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeMarketCodeTradingTimeTypeProductCodeBizDateTypeStartTime( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradingTimeRow.TYPE,
            "institution_code=? and branch_code=? and market_code=? and trading_time_type=? and product_code=? and biz_date_type=? and start_time=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeMarketCodeTradingTimeTypeProductCodeBizDateTypeStartTime(String, String, String, String, String, String, String)}および{@@link #forRow(TradingTimeRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeMarketCodeTradingTimeTypeProductCodeBizDateTypeStartTime( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeMarketCodeTradingTimeTypeProductCodeBizDateTypeStartTime( p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime ) );
    }

}
@
