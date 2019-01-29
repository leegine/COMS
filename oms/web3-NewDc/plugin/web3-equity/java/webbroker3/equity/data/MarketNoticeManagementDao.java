head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketNoticeManagementDao.java;


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

/** 
 * {@@link MarketNoticeManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MarketNoticeManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see MarketNoticeManagementPK 
 * @@see MarketNoticeManagementRow 
 */
public class MarketNoticeManagementDao extends DataAccessObject {


  /** 
   * この{@@link MarketNoticeManagementDao}に関連する型指定のRowオブジェクト 
   */
    private MarketNoticeManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MarketNoticeManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link MarketNoticeManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MarketNoticeManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MarketNoticeManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MarketNoticeManagementRow )
                return new MarketNoticeManagementDao( (MarketNoticeManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MarketNoticeManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MarketNoticeManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MarketNoticeManagementRow}オブジェクト 
    */
    protected MarketNoticeManagementDao( MarketNoticeManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MarketNoticeManagementRow}オブジェクトを取得します。
   */
    public MarketNoticeManagementRow getMarketNoticeManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link MarketNoticeManagementRow}オブジェクトから{@@link MarketNoticeManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link MarketNoticeManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MarketNoticeManagementDao}取得のために指定の{@@link MarketNoticeManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MarketNoticeManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MarketNoticeManagementDao forRow( MarketNoticeManagementRow row ) throws java.lang.IllegalArgumentException {
        return (MarketNoticeManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MarketNoticeManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MarketNoticeManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MarketNoticeManagementPK}やデータベースレコードとして挿入される{@@link MarketNoticeManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MarketNoticeManagementRow.TYPE );
    }


  /** 
   * {@@link MarketNoticeManagementRow}を一意に特定する{@@link MarketNoticeManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MarketNoticeManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MarketNoticeManagementParams}オブジェクトの主キーとして利用可能な{@@link MarketNoticeManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MarketNoticeManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MarketNoticeManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_virtualServerNumberMarket 検索対象であるp_virtualServerNumberMarketフィールドの値
   * @@param p_noticeType 検索対象であるp_noticeTypeフィールドの値
   * @@param p_noticeNumber 検索対象であるp_noticeNumberフィールドの値
   * @@param p_frontOrderExchangeCode 検索対象であるp_frontOrderExchangeCodeフィールドの値
   * @@param p_frontOrderSystemCode 検索対象であるp_frontOrderSystemCodeフィールドの値
   * @@param p_bizDateCount 検索対象であるp_bizDateCountフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketNoticeManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketNoticeManagementRow findRowByPk( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketNoticeManagementPK pk = new MarketNoticeManagementPK( p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMarketNoticeManagementPKオブジェクトから{@@link MarketNoticeManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMarketNoticeManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketNoticeManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketNoticeManagementRow findRowByPk( MarketNoticeManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MarketNoticeManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,long,String,String,int)}および{@@link #forRow(MarketNoticeManagementRow)}を使用してください。 
   */
    public static MarketNoticeManagementDao findDaoByPk( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketNoticeManagementPK pk = new MarketNoticeManagementPK( p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount );
        MarketNoticeManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MarketNoticeManagementPK)}および{@@link #forRow(MarketNoticeManagementRow)}を使用してください。 
   */
    public static MarketNoticeManagementDao findDaoByPk( MarketNoticeManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketNoticeManagementRow row = findRowByPk( pk );
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
   * p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount, and にて指定の値から一意の{@@link MarketNoticeManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_virtualServerNumberMarket 検索対象であるp_virtualServerNumberMarketフィールドの値
   * @@param p_noticeType 検索対象であるp_noticeTypeフィールドの値
   * @@param p_noticeNumber 検索対象であるp_noticeNumberフィールドの値
   * @@param p_frontOrderExchangeCode 検索対象であるp_frontOrderExchangeCodeフィールドの値
   * @@param p_frontOrderSystemCode 検索対象であるp_frontOrderSystemCodeフィールドの値
   * @@param p_bizDateCount 検索対象であるp_bizDateCountフィールドの値
   * 
   * @@return 引数指定のp_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount, and の値と一致する{@@link MarketNoticeManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MarketNoticeManagementRow findRowByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketNoticeManagementRow.TYPE,
            "virtual_server_number_market=? and notice_type=? and notice_number=? and front_order_exchange_code=? and front_order_system_code=? and biz_date_count=?",
            null,
            new Object[] { p_virtualServerNumberMarket, p_noticeType, new Long(p_noticeNumber), p_frontOrderExchangeCode, p_frontOrderSystemCode, new Integer(p_bizDateCount) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketNoticeManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketNoticeManagementDao.findRowsByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount(String, String, long, String, String, int)}および{@@link #forRow(MarketNoticeManagementRow)}を使用してください。 
   */
    public static MarketNoticeManagementDao findDaoByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount( p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_frontOrderExchangeCode, p_frontOrderSystemCode, and にて指定の値に一致する{@@link MarketNoticeManagementRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_frontOrderExchangeCode 検索対象であるp_frontOrderExchangeCodeフィールドの値
   * @@param p_frontOrderSystemCode 検索対象であるp_frontOrderSystemCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_frontOrderExchangeCode, p_frontOrderSystemCode, and の値と一致する{@@link MarketNoticeManagementRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeFrontOrderExchangeCodeFrontOrderSystemCode( String p_institutionCode, String p_frontOrderExchangeCode, String p_frontOrderSystemCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MarketNoticeManagementRow.TYPE,
            "institution_code=? and front_order_exchange_code=? and front_order_system_code=?",
            null,
            new Object[] { p_institutionCode, p_frontOrderExchangeCode, p_frontOrderSystemCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeFrontOrderExchangeCodeFrontOrderSystemCode(String, String, String)}および{@@link #forRow(MarketNoticeManagementRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeFrontOrderExchangeCodeFrontOrderSystemCode( String p_institutionCode, String p_frontOrderExchangeCode, String p_frontOrderSystemCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeFrontOrderExchangeCodeFrontOrderSystemCode( p_institutionCode, p_frontOrderExchangeCode, p_frontOrderSystemCode ) );
    }

}
@
