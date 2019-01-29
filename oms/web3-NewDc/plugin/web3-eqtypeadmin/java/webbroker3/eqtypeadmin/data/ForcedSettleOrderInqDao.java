head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ForcedSettleOrderInqDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.eqtypeadmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * {@@link ForcedSettleOrderInqDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ForcedSettleOrderInqRow}インスタンスへ関連付けることができます。 
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
 * @@see ForcedSettleOrderInqPK 
 * @@see ForcedSettleOrderInqRow 
 */
public class ForcedSettleOrderInqDao extends DataAccessObject {


  /** 
   * この{@@link ForcedSettleOrderInqDao}に関連する型指定のRowオブジェクト 
   */
    private ForcedSettleOrderInqRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ForcedSettleOrderInqRow}と仮定される{@@link DataAccessObject}から新たに{@@link ForcedSettleOrderInqDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ForcedSettleOrderInqDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ForcedSettleOrderInqRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ForcedSettleOrderInqRow )
                return new ForcedSettleOrderInqDao( (ForcedSettleOrderInqRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ForcedSettleOrderInqRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ForcedSettleOrderInqRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ForcedSettleOrderInqRow}オブジェクト 
    */
    protected ForcedSettleOrderInqDao( ForcedSettleOrderInqRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ForcedSettleOrderInqRow}オブジェクトを取得します。
   */
    public ForcedSettleOrderInqRow getForcedSettleOrderInqRow() {
        return row;
    }


  /** 
   * 指定の{@@link ForcedSettleOrderInqRow}オブジェクトから{@@link ForcedSettleOrderInqDao}オブジェクトを作成します。 
   * これは実際の{@@link ForcedSettleOrderInqRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ForcedSettleOrderInqDao}取得のために指定の{@@link ForcedSettleOrderInqRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ForcedSettleOrderInqDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ForcedSettleOrderInqDao forRow( ForcedSettleOrderInqRow row ) throws java.lang.IllegalArgumentException {
        return (ForcedSettleOrderInqDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ForcedSettleOrderInqRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ForcedSettleOrderInqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ForcedSettleOrderInqPK}やデータベースレコードとして挿入される{@@link ForcedSettleOrderInqParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ForcedSettleOrderInqRow.TYPE );
    }


  /** 
   * {@@link ForcedSettleOrderInqRow}を一意に特定する{@@link ForcedSettleOrderInqPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ForcedSettleOrderInqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ForcedSettleOrderInqParams}オブジェクトの主キーとして利用可能な{@@link ForcedSettleOrderInqPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ForcedSettleOrderInqPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new ForcedSettleOrderInqPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ForcedSettleOrderInqRow}オブジェクトを検索します。 
   * 
   * @@param p_forcedSettleOrderInqId 検索対象であるp_forcedSettleOrderInqIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ForcedSettleOrderInqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ForcedSettleOrderInqRow findRowByPk( long p_forcedSettleOrderInqId ) throws DataFindException, DataQueryException, DataNetworkException {
        ForcedSettleOrderInqPK pk = new ForcedSettleOrderInqPK( p_forcedSettleOrderInqId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のForcedSettleOrderInqPKオブジェクトから{@@link ForcedSettleOrderInqRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するForcedSettleOrderInqPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ForcedSettleOrderInqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ForcedSettleOrderInqRow findRowByPk( ForcedSettleOrderInqPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ForcedSettleOrderInqRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static ForcedSettleOrderInqDao findDaoByPk( long p_forcedSettleOrderInqId ) throws DataFindException, DataQueryException, DataNetworkException {
        ForcedSettleOrderInqPK pk = new ForcedSettleOrderInqPK( p_forcedSettleOrderInqId );
        ForcedSettleOrderInqRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ForcedSettleOrderInqPK)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static ForcedSettleOrderInqDao findDaoByPk( ForcedSettleOrderInqPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ForcedSettleOrderInqRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link ForcedSettleOrderInqDao}に紐付く{@@link ForcedSettleOrderInqRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link ForcedSettleOrderInqDao}と外部キーの関係にある{@@link BranchRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public BranchRow fetchBranchRowViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        Row row = BranchDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BranchRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BranchRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * この{@@link ForcedSettleOrderInqDao}に紐付く{@@link ForcedSettleOrderInqRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link ForcedSettleOrderInqDao}と外部キーの関係にある{@@link MarketRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public MarketRow fetchMarketRowViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getMarketIdIsNull() )
            return null;
        MarketPK pk = new MarketPK( row.getMarketId() );
        Row row = MarketDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MarketRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MarketRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getMarketIdIsNull() )
            return null;
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
    // Find Rows given foreign key values for Branch
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByBranchId(BranchRow)}を使用してください。 
   */
    public static List findRowsByBranchId( BranchDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( dao.getBranchRow() );
    }


  /** 
   * {@@link BranchRow}と外部キーの関係にある{@@link ForcedSettleOrderInqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link ForcedSettleOrderInqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link ForcedSettleOrderInqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link ForcedSettleOrderInqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link ForcedSettleOrderInqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link ForcedSettleOrderInqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ForcedSettleOrderInqRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
    }


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
   * {@@link MarketRow}と外部キーの関係にある{@@link ForcedSettleOrderInqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link ForcedSettleOrderInqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link ForcedSettleOrderInqRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link ForcedSettleOrderInqRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link ForcedSettleOrderInqRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link ForcedSettleOrderInqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ForcedSettleOrderInqRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
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
   * p_forcedSettleOrderInqId, and にて指定の値から一意の{@@link ForcedSettleOrderInqRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_forcedSettleOrderInqId 検索対象であるp_forcedSettleOrderInqIdフィールドの値
   * 
   * @@return 引数指定のp_forcedSettleOrderInqId, and の値と一致する{@@link ForcedSettleOrderInqRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ForcedSettleOrderInqRow findRowByForcedSettleOrderInqId( long p_forcedSettleOrderInqId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ForcedSettleOrderInqRow.TYPE,
            "forced_settle_order_inq_id=?",
            null,
            new Object[] { new Long(p_forcedSettleOrderInqId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ForcedSettleOrderInqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ForcedSettleOrderInqDao.findRowsByForcedSettleOrderInqId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByForcedSettleOrderInqId(long)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static ForcedSettleOrderInqDao findDaoByForcedSettleOrderInqId( long p_forcedSettleOrderInqId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByForcedSettleOrderInqId( p_forcedSettleOrderInqId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_forcedSettleOrderInqId, p_accountId, p_subAccountId, p_branchId, p_marketId, p_productId, and にて指定の値に一致する{@@link ForcedSettleOrderInqRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_forcedSettleOrderInqId 検索対象であるp_forcedSettleOrderInqIdフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のp_forcedSettleOrderInqId, p_accountId, p_subAccountId, p_branchId, p_marketId, p_productId, and の値と一致する{@@link ForcedSettleOrderInqRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByForcedSettleOrderInqIdAccountIdSubAccountIdBranchIdMarketIdProductId( long p_forcedSettleOrderInqId, long p_accountId, long p_subAccountId, long p_branchId, Long p_marketId, long p_productId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ForcedSettleOrderInqRow.TYPE,
            "forced_settle_order_inq_id=? and account_id=? and sub_account_id=? and branch_id=? and market_id=? and product_id=?",
            null,
            new Object[] { new Long(p_forcedSettleOrderInqId), new Long(p_accountId), new Long(p_subAccountId), new Long(p_branchId), p_marketId, new Long(p_productId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByForcedSettleOrderInqIdAccountIdSubAccountIdBranchIdMarketIdProductId(long, long, long, long, Long, long)}および{@@link #forRow(ForcedSettleOrderInqRow)}を使用してください。 
   */
    public static List findDaosByForcedSettleOrderInqIdAccountIdSubAccountIdBranchIdMarketIdProductId( long p_forcedSettleOrderInqId, long p_accountId, long p_subAccountId, long p_branchId, Long p_marketId, long p_productId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByForcedSettleOrderInqIdAccountIdSubAccountIdBranchIdMarketIdProductId( p_forcedSettleOrderInqId, p_accountId, p_subAccountId, p_branchId, p_marketId, p_productId ) );
    }

}
@
