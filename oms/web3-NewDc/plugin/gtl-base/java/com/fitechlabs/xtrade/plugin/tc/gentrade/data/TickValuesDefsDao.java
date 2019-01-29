head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.34.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TickValuesDefsDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link TickValuesDefsDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TickValuesDefsRow}インスタンスへ関連付けることができます。 
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
 * @@see TickValuesDefsPK 
 * @@see TickValuesDefsRow 
 */
public class TickValuesDefsDao extends DataAccessObject {


  /** 
   * この{@@link TickValuesDefsDao}に関連する型指定のRowオブジェクト 
   */
    private TickValuesDefsRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TickValuesDefsRow}と仮定される{@@link DataAccessObject}から新たに{@@link TickValuesDefsDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TickValuesDefsDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TickValuesDefsRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TickValuesDefsRow )
                return new TickValuesDefsDao( (TickValuesDefsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TickValuesDefsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TickValuesDefsRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TickValuesDefsRow}オブジェクト 
    */
    protected TickValuesDefsDao( TickValuesDefsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TickValuesDefsRow}オブジェクトを取得します。
   */
    public TickValuesDefsRow getTickValuesDefsRow() {
        return row;
    }


  /** 
   * 指定の{@@link TickValuesDefsRow}オブジェクトから{@@link TickValuesDefsDao}オブジェクトを作成します。 
   * これは実際の{@@link TickValuesDefsRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TickValuesDefsDao}取得のために指定の{@@link TickValuesDefsRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TickValuesDefsDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TickValuesDefsDao forRow( TickValuesDefsRow row ) throws java.lang.IllegalArgumentException {
        return (TickValuesDefsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TickValuesDefsRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TickValuesDefsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TickValuesDefsPK}やデータベースレコードとして挿入される{@@link TickValuesDefsParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TickValuesDefsRow.TYPE );
    }


  /** 
   * {@@link TickValuesDefsRow}を一意に特定する{@@link TickValuesDefsPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TickValuesDefsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TickValuesDefsParams}オブジェクトの主キーとして利用可能な{@@link TickValuesDefsPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TickValuesDefsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TickValuesDefsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TickValuesDefsRow}オブジェクトを検索します。 
   * 
   * @@param p_tickValuesDefsId 検索対象であるp_tickValuesDefsIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TickValuesDefsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TickValuesDefsRow findRowByPk( long p_tickValuesDefsId ) throws DataFindException, DataQueryException, DataNetworkException {
        TickValuesDefsPK pk = new TickValuesDefsPK( p_tickValuesDefsId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTickValuesDefsPKオブジェクトから{@@link TickValuesDefsRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTickValuesDefsPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TickValuesDefsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TickValuesDefsRow findRowByPk( TickValuesDefsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TickValuesDefsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static TickValuesDefsDao findDaoByPk( long p_tickValuesDefsId ) throws DataFindException, DataQueryException, DataNetworkException {
        TickValuesDefsPK pk = new TickValuesDefsPK( p_tickValuesDefsId );
        TickValuesDefsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TickValuesDefsPK)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static TickValuesDefsDao findDaoByPk( TickValuesDefsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TickValuesDefsRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link TickValuesDefsDao}に紐付く{@@link TickValuesDefsRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link TickValuesDefsDao}と外部キーの関係にある{@@link MarketRow} 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


  /** 
   * この{@@link TickValuesDefsDao}に紐付く{@@link TickValuesDefsRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link TickValuesDefsDao}と外部キーの関係にある{@@link ProductRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public ProductRow fetchProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getProductIdIsNull() )
            return null;
        ProductPK pk = new ProductPK( row.getProductId() );
        Row row = ProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof ProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (ProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public ProductDao fetchProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getProductIdIsNull() )
            return null;
        ProductPK pk = new ProductPK( row.getProductId() );
        DataAccessObject dao = ProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof ProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (ProductDao) dao;
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
   * {@@link MarketRow}と外部キーの関係にある{@@link TickValuesDefsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link TickValuesDefsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link TickValuesDefsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link TickValuesDefsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TickValuesDefsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TickValuesDefsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TickValuesDefsRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Product
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(ProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( ProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getProductRow() );
    }


  /** 
   * {@@link ProductRow}と外部キーの関係にある{@@link TickValuesDefsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link TickValuesDefsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link TickValuesDefsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link TickValuesDefsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TickValuesDefsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TickValuesDefsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TickValuesDefsRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
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
   * p_tickValuesDefsId, and にて指定の値から一意の{@@link TickValuesDefsRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tickValuesDefsId 検索対象であるp_tickValuesDefsIdフィールドの値
   * 
   * @@return 引数指定のp_tickValuesDefsId, and の値と一致する{@@link TickValuesDefsRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TickValuesDefsRow findRowByTickValuesDefsId( long p_tickValuesDefsId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TickValuesDefsRow.TYPE,
            "tick_values_defs_id=?",
            null,
            new Object[] { new Long(p_tickValuesDefsId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TickValuesDefsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TickValuesDefsDao.findRowsByTickValuesDefsId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTickValuesDefsId(long)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static TickValuesDefsDao findDaoByTickValuesDefsId( long p_tickValuesDefsId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTickValuesDefsId( p_tickValuesDefsId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_marketId, p_productType, p_capPrice, and にて指定の値に一致する{@@link TickValuesDefsRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_capPrice 検索対象であるp_capPriceフィールドの値
   * 
   * @@return 引数指定のp_marketId, p_productType, p_capPrice, and の値と一致する{@@link TickValuesDefsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketIdProductTypeCapPrice( long p_marketId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, double p_capPrice ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TickValuesDefsRow.TYPE,
            "market_id=? and product_type=? and cap_price=?",
            null,
            new Object[] { new Long(p_marketId), p_productType, new Double(p_capPrice) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketIdProductTypeCapPrice(long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, double)}および{@@link #forRow(TickValuesDefsRow)}を使用してください。 
   */
    public static List findDaosByMarketIdProductTypeCapPrice( long p_marketId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, double p_capPrice ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMarketIdProductTypeCapPrice( p_marketId, p_productType, p_capPrice ) );
    }

}
@
