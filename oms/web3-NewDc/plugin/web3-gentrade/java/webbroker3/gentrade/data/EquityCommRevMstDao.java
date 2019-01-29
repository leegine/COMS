head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommRevMstDao.java;


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
 * {@@link EquityCommRevMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EquityCommRevMstRow}インスタンスへ関連付けることができます。 
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
 * @@see EquityCommRevMstPK 
 * @@see EquityCommRevMstRow 
 */
public class EquityCommRevMstDao extends DataAccessObject {


  /** 
   * この{@@link EquityCommRevMstDao}に関連する型指定のRowオブジェクト 
   */
    private EquityCommRevMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EquityCommRevMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link EquityCommRevMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EquityCommRevMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EquityCommRevMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EquityCommRevMstRow )
                return new EquityCommRevMstDao( (EquityCommRevMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EquityCommRevMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EquityCommRevMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EquityCommRevMstRow}オブジェクト 
    */
    protected EquityCommRevMstDao( EquityCommRevMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EquityCommRevMstRow}オブジェクトを取得します。
   */
    public EquityCommRevMstRow getEquityCommRevMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link EquityCommRevMstRow}オブジェクトから{@@link EquityCommRevMstDao}オブジェクトを作成します。 
   * これは実際の{@@link EquityCommRevMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EquityCommRevMstDao}取得のために指定の{@@link EquityCommRevMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EquityCommRevMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EquityCommRevMstDao forRow( EquityCommRevMstRow row ) throws java.lang.IllegalArgumentException {
        return (EquityCommRevMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EquityCommRevMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EquityCommRevMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EquityCommRevMstPK}やデータベースレコードとして挿入される{@@link EquityCommRevMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EquityCommRevMstRow.TYPE );
    }


  /** 
   * {@@link EquityCommRevMstRow}を一意に特定する{@@link EquityCommRevMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EquityCommRevMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EquityCommRevMstParams}オブジェクトの主キーとして利用可能な{@@link EquityCommRevMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EquityCommRevMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EquityCommRevMstRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * @@param p_orderChannel 検索対象であるp_orderChannelフィールドの値
   * @@param p_transactionType 検索対象であるp_transactionTypeフィールドの値
   * @@param p_execCondType 検索対象であるp_execCondTypeフィールドの値
   * @@param p_payType 検索対象であるp_payTypeフィールドの値
   * @@param p_sonarMarketCode 検索対象であるp_sonarMarketCodeフィールドの値
   * @@param p_underlyingProductCode 検索対象であるp_underlyingProductCodeフィールドの値
   * @@param p_dayTradeType 検索対象であるp_dayTradeTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityCommRevMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityCommRevMstRow findRowByPk( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommRevMstPK pk = new EquityCommRevMstPK( p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEquityCommRevMstPKオブジェクトから{@@link EquityCommRevMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEquityCommRevMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityCommRevMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityCommRevMstRow findRowByPk( EquityCommRevMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EquityCommRevMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,java.sql.Timestamp,String,String,String,String,String,String,String)}および{@@link #forRow(EquityCommRevMstRow)}を使用してください。 
   */
    public static EquityCommRevMstDao findDaoByPk( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommRevMstPK pk = new EquityCommRevMstPK( p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType );
        EquityCommRevMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EquityCommRevMstPK)}および{@@link #forRow(EquityCommRevMstRow)}を使用してください。 
   */
    public static EquityCommRevMstDao findDaoByPk( EquityCommRevMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommRevMstRow row = findRowByPk( pk );
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
   * p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType, and にて指定の値から一意の{@@link EquityCommRevMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * @@param p_orderChannel 検索対象であるp_orderChannelフィールドの値
   * @@param p_transactionType 検索対象であるp_transactionTypeフィールドの値
   * @@param p_execCondType 検索対象であるp_execCondTypeフィールドの値
   * @@param p_payType 検索対象であるp_payTypeフィールドの値
   * @@param p_sonarMarketCode 検索対象であるp_sonarMarketCodeフィールドの値
   * @@param p_underlyingProductCode 検索対象であるp_underlyingProductCodeフィールドの値
   * @@param p_dayTradeType 検索対象であるp_dayTradeTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType, and の値と一致する{@@link EquityCommRevMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EquityCommRevMstRow findRowByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EquityCommRevMstRow.TYPE,
            "institution_code=? and comm_product_code=? and appli_start_date=? and order_channel=? and transaction_type=? and exec_cond_type=? and pay_type=? and sonar_market_code=? and underlying_product_code=? and day_trade_type=?",
            null,
            new Object[] { p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EquityCommRevMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EquityCommRevMstDao.findRowsByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType(String, String, java.sql.Timestamp, String, String, String, String, String, String, String)}および{@@link #forRow(EquityCommRevMstRow)}を使用してください。 
   */
    public static EquityCommRevMstDao findDaoByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType( p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
