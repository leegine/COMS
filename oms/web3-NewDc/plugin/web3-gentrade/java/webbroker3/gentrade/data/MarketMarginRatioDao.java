head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.43.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketMarginRatioDao.java;


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
 * {@@link MarketMarginRatioDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MarketMarginRatioRow}インスタンスへ関連付けることができます。 
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
 * @@see MarketMarginRatioPK 
 * @@see MarketMarginRatioRow 
 */
public class MarketMarginRatioDao extends DataAccessObject {


  /** 
   * この{@@link MarketMarginRatioDao}に関連する型指定のRowオブジェクト 
   */
    private MarketMarginRatioRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MarketMarginRatioRow}と仮定される{@@link DataAccessObject}から新たに{@@link MarketMarginRatioDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MarketMarginRatioDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MarketMarginRatioRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MarketMarginRatioRow )
                return new MarketMarginRatioDao( (MarketMarginRatioRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MarketMarginRatioRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MarketMarginRatioRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MarketMarginRatioRow}オブジェクト 
    */
    protected MarketMarginRatioDao( MarketMarginRatioRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MarketMarginRatioRow}オブジェクトを取得します。
   */
    public MarketMarginRatioRow getMarketMarginRatioRow() {
        return row;
    }


  /** 
   * 指定の{@@link MarketMarginRatioRow}オブジェクトから{@@link MarketMarginRatioDao}オブジェクトを作成します。 
   * これは実際の{@@link MarketMarginRatioRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MarketMarginRatioDao}取得のために指定の{@@link MarketMarginRatioRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MarketMarginRatioDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MarketMarginRatioDao forRow( MarketMarginRatioRow row ) throws java.lang.IllegalArgumentException {
        return (MarketMarginRatioDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MarketMarginRatioRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MarketMarginRatioRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MarketMarginRatioPK}やデータベースレコードとして挿入される{@@link MarketMarginRatioParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MarketMarginRatioRow.TYPE );
    }


  /** 
   * {@@link MarketMarginRatioRow}を一意に特定する{@@link MarketMarginRatioPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MarketMarginRatioRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MarketMarginRatioParams}オブジェクトの主キーとして利用可能な{@@link MarketMarginRatioPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MarketMarginRatioPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MarketMarginRatioRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_listType 検索対象であるp_listTypeフィールドの値
   * @@param p_newListType 検索対象であるp_newListTypeフィールドの値
   * @@param p_securitiesEstimationDiv 検索対象であるp_securitiesEstimationDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketMarginRatioRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketMarginRatioRow findRowByPk( String p_institutionCode, long p_marketId, String p_listType, String p_newListType, String p_securitiesEstimationDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketMarginRatioPK pk = new MarketMarginRatioPK( p_institutionCode, p_marketId, p_listType, p_newListType, p_securitiesEstimationDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMarketMarginRatioPKオブジェクトから{@@link MarketMarginRatioRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMarketMarginRatioPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MarketMarginRatioRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MarketMarginRatioRow findRowByPk( MarketMarginRatioPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MarketMarginRatioRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,long,String,String,String)}および{@@link #forRow(MarketMarginRatioRow)}を使用してください。 
   */
    public static MarketMarginRatioDao findDaoByPk( String p_institutionCode, long p_marketId, String p_listType, String p_newListType, String p_securitiesEstimationDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketMarginRatioPK pk = new MarketMarginRatioPK( p_institutionCode, p_marketId, p_listType, p_newListType, p_securitiesEstimationDiv );
        MarketMarginRatioRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MarketMarginRatioPK)}および{@@link #forRow(MarketMarginRatioRow)}を使用してください。 
   */
    public static MarketMarginRatioDao findDaoByPk( MarketMarginRatioPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketMarginRatioRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link MarketMarginRatioDao}に紐付く{@@link MarketMarginRatioRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link MarketMarginRatioDao}と外部キーの関係にある{@@link MarketRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public MarketRow fetchMarketRowViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        Row row = MarketDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MarketRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MarketRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(MarketMarginRatioRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Market
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByMarketId(MarketRow)}を使用してください。 
   */
    public static List findRowsByMarketId( MarketDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( dao.getMarketRow() );
    }


  /** 
   * {@@link MarketRow}と外部キーの関係にある{@@link MarketMarginRatioRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link MarketMarginRatioRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link MarketMarginRatioRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link MarketMarginRatioRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MarketMarginRatioRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MarketMarginRatioRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MarketMarginRatioRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(MarketMarginRatioRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(MarketMarginRatioRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(MarketMarginRatioRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(MarketMarginRatioRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_marketId, p_listType, p_newListType, p_securitiesEstimationDiv, and にて指定の値から一意の{@@link MarketMarginRatioRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_listType 検索対象であるp_listTypeフィールドの値
   * @@param p_newListType 検索対象であるp_newListTypeフィールドの値
   * @@param p_securitiesEstimationDiv 検索対象であるp_securitiesEstimationDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_marketId, p_listType, p_newListType, p_securitiesEstimationDiv, and の値と一致する{@@link MarketMarginRatioRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MarketMarginRatioRow findRowByInstitutionCodeMarketIdListTypeNewListTypeSecuritiesEstimationDiv( String p_institutionCode, long p_marketId, String p_listType, String p_newListType, String p_securitiesEstimationDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketMarginRatioRow.TYPE,
            "institution_code=? and market_id=? and list_type=? and new_list_type=? and securities_estimation_div=?",
            null,
            new Object[] { p_institutionCode, new Long(p_marketId), p_listType, p_newListType, p_securitiesEstimationDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketMarginRatioRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketMarginRatioDao.findRowsByInstitutionCodeMarketIdListTypeNewListTypeSecuritiesEstimationDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeMarketIdListTypeNewListTypeSecuritiesEstimationDiv(String, long, String, String, String)}および{@@link #forRow(MarketMarginRatioRow)}を使用してください。 
   */
    public static MarketMarginRatioDao findDaoByInstitutionCodeMarketIdListTypeNewListTypeSecuritiesEstimationDiv( String p_institutionCode, long p_marketId, String p_listType, String p_newListType, String p_securitiesEstimationDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketIdListTypeNewListTypeSecuritiesEstimationDiv( p_institutionCode, p_marketId, p_listType, p_newListType, p_securitiesEstimationDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
