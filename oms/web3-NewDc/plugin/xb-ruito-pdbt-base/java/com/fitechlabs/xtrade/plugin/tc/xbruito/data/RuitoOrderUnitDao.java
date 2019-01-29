head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.28.09.09.33;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5644d9050285d38;
filename	RuitoOrderUnitDao.java;

1.1
date	2011.03.22.09.17.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoOrderUnitDao.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link RuitoOrderUnitDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RuitoOrderUnitRow}インスタンスへ関連付けることができます。 
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
 * @@see RuitoOrderUnitPK 
 * @@see RuitoOrderUnitRow 
 */
public class RuitoOrderUnitDao extends DataAccessObject {


  /** 
   * この{@@link RuitoOrderUnitDao}に関連する型指定のRowオブジェクト 
   */
    private RuitoOrderUnitRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RuitoOrderUnitRow}と仮定される{@@link DataAccessObject}から新たに{@@link RuitoOrderUnitDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RuitoOrderUnitDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RuitoOrderUnitRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RuitoOrderUnitRow )
                return new RuitoOrderUnitDao( (RuitoOrderUnitRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RuitoOrderUnitRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RuitoOrderUnitRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RuitoOrderUnitRow}オブジェクト 
    */
    protected RuitoOrderUnitDao( RuitoOrderUnitRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RuitoOrderUnitRow}オブジェクトを取得します。
   */
    public RuitoOrderUnitRow getRuitoOrderUnitRow() {
        return row;
    }


  /** 
   * 指定の{@@link RuitoOrderUnitRow}オブジェクトから{@@link RuitoOrderUnitDao}オブジェクトを作成します。 
   * これは実際の{@@link RuitoOrderUnitRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RuitoOrderUnitDao}取得のために指定の{@@link RuitoOrderUnitRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RuitoOrderUnitDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RuitoOrderUnitDao forRow( RuitoOrderUnitRow row ) throws java.lang.IllegalArgumentException {
        return (RuitoOrderUnitDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RuitoOrderUnitRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RuitoOrderUnitRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RuitoOrderUnitPK}やデータベースレコードとして挿入される{@@link RuitoOrderUnitParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RuitoOrderUnitRow.TYPE );
    }


  /** 
   * {@@link RuitoOrderUnitRow}を一意に特定する{@@link RuitoOrderUnitPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RuitoOrderUnitRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RuitoOrderUnitParams}オブジェクトの主キーとして利用可能な{@@link RuitoOrderUnitPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RuitoOrderUnitPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RuitoOrderUnitPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RuitoOrderUnitRow}オブジェクトを検索します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RuitoOrderUnitRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RuitoOrderUnitRow findRowByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoOrderUnitPK pk = new RuitoOrderUnitPK( p_orderUnitId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRuitoOrderUnitPKオブジェクトから{@@link RuitoOrderUnitRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRuitoOrderUnitPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RuitoOrderUnitRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RuitoOrderUnitRow findRowByPk( RuitoOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RuitoOrderUnitRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static RuitoOrderUnitDao findDaoByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoOrderUnitPK pk = new RuitoOrderUnitPK( p_orderUnitId );
        RuitoOrderUnitRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RuitoOrderUnitPK)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static RuitoOrderUnitDao findDaoByPk( RuitoOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoOrderUnitRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link RuitoOrderUnitDao}に紐付く{@@link RuitoOrderUnitRow}内で外部キーの関係をもつ{@@link RuitoOrderRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderUnitDao}と外部キーの関係にある{@@link RuitoOrderRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public RuitoOrderRow fetchRuitoOrderRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        RuitoOrderPK pk = new RuitoOrderPK( row.getOrderId() );
        Row row = RuitoOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RuitoOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RuitoOrderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderRowViaOrderId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public RuitoOrderDao fetchRuitoOrderDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        RuitoOrderPK pk = new RuitoOrderPK( row.getOrderId() );
        DataAccessObject dao = RuitoOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RuitoOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RuitoOrderDao) dao;
    }


  /** 
   * この{@@link RuitoOrderUnitDao}に紐付く{@@link RuitoOrderUnitRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderUnitDao}と外部キーの関係にある{@@link SubAccountRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public SubAccountRow fetchSubAccountRowViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        Row row = SubAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof SubAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (SubAccountRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link RuitoOrderUnitDao}に紐付く{@@link RuitoOrderUnitRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderUnitDao}と外部キーの関係にある{@@link MarketRow}または外部キーIDの値が現在nullに設定の場合はnull 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
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


  /** 
   * この{@@link RuitoOrderUnitDao}に紐付く{@@link RuitoOrderUnitRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderUnitDao}と外部キーの関係にある{@@link MainAccountRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public MainAccountRow fetchMainAccountRowViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        Row row = MainAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MainAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MainAccountRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link RuitoOrderUnitDao}に紐付く{@@link RuitoOrderUnitRow}内で外部キーの関係をもつ{@@link TraderRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderUnitDao}と外部キーの関係にある{@@link TraderRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public TraderRow fetchTraderRowViaTraderId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getTraderIdIsNull() )
            return null;
        TraderPK pk = new TraderPK( row.getTraderId() );
        Row row = TraderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof TraderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (TraderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTraderRowViaTraderId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public TraderDao fetchTraderDaoViaTraderId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getTraderIdIsNull() )
            return null;
        TraderPK pk = new TraderPK( row.getTraderId() );
        DataAccessObject dao = TraderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TraderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TraderDao) dao;
    }


  /** 
   * この{@@link RuitoOrderUnitDao}に紐付く{@@link RuitoOrderUnitRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderUnitDao}と外部キーの関係にある{@@link BranchRow} 
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
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * この{@@link RuitoOrderUnitDao}に紐付く{@@link RuitoOrderUnitRow}内で外部キーの関係をもつ{@@link RuitoProductRow}を検索します。 
   * 
   * @@return {@@link RuitoOrderUnitDao}と外部キーの関係にある{@@link RuitoProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public RuitoProductRow fetchRuitoProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        RuitoProductPK pk = new RuitoProductPK( row.getProductId() );
        Row row = RuitoProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RuitoProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RuitoProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoProductRowViaProductId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public RuitoProductDao fetchRuitoProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        RuitoProductPK pk = new RuitoProductPK( row.getProductId() );
        DataAccessObject dao = RuitoProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RuitoProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RuitoProductDao) dao;
    }


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoFinTransactionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoFinTransactionRowsByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoFinTransactionDao.findRowsByOrderUnitId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoFinTransactionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public List fetchRuitoFinTransactionDaosByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoFinTransactionDao.findDaosByOrderUnitId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoFinTransactionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public List fetchRuitoFinTransactionDaosOrderUnitId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoFinTransactionDaosByOrderUnitId();
    }


  /** 
   * この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoOrderExecutionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoOrderExecutionRowsByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderExecutionDao.findRowsByOrderUnitId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderExecutionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public List fetchRuitoOrderExecutionDaosByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderExecutionDao.findDaosByOrderUnitId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderExecutionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public List fetchRuitoOrderExecutionDaosOrderUnitId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoOrderExecutionDaosByOrderUnitId();
    }


  /** 
   * この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoOrderActionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoOrderActionRowsByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderActionDao.findRowsByOrderUnitId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderActionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public List fetchRuitoOrderActionDaosByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderActionDao.findDaosByOrderUnitId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderActionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public List fetchRuitoOrderActionDaosOrderUnitId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoOrderActionDaosByOrderUnitId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for RuitoOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderId(RuitoOrderRow)}を使用してください。 
   */
    public static List findRowsByOrderId( RuitoOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getRuitoOrderRow() );
    }


  /** 
   * {@@link RuitoOrderRow}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RuitoOrderRow}オブジェクト 
   * @@return 指定の{@@link RuitoOrderRow}に外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( RuitoOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link RuitoOrderPK}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RuitoOrderPK}オブジェクト 
   * @@return {@@link RuitoOrderPK}と外部キーが一致する値を持つ{@@link RuitoOrderUnitRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( RuitoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RuitoOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderId(RuitoOrderRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RuitoOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(RuitoOrderRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RuitoOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(RuitoOrderPK)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RuitoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for SubAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}を使用してください。 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( dao.getSubAccountRow() );
    }


  /** 
   * {@@link SubAccountRow}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link RuitoOrderUnitRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * {@@link MarketRow}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link RuitoOrderUnitRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for MainAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAccountId(MainAccountRow)}を使用してください。 
   */
    public static List findRowsByAccountId( MainAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( dao.getMainAccountRow() );
    }


  /** 
   * {@@link MainAccountRow}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link RuitoOrderUnitRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Trader
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByTraderId(TraderRow)}を使用してください。 
   */
    public static List findRowsByTraderId( TraderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( dao.getTraderRow() );
    }


  /** 
   * {@@link TraderRow}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link TraderRow}オブジェクト 
   * @@return 指定の{@@link TraderRow}に外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTraderId( TraderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( row.getTraderId() );
    }


  /** 
   * {@@link TraderPK}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link TraderPK}オブジェクト 
   * @@return {@@link TraderPK}と外部キーが一致する値を持つ{@@link RuitoOrderUnitRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTraderId( TraderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( pk.trader_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_traderId 検索対象であるp_traderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTraderId( long p_traderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "trader_id=?",
            null,
            new Object[] { new Long(p_traderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Trader
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByTraderId(TraderRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByTraderId( TraderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTraderId(TraderRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByTraderId( TraderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTraderId(TraderPK)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByTraderId( TraderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( pk.trader_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTraderId(long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByTraderId( long p_traderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( p_traderId ) );
    }


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
   * {@@link BranchRow}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link RuitoOrderUnitRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for RuitoProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(RuitoProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( RuitoProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getRuitoProductRow() );
    }


  /** 
   * {@@link RuitoProductRow}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RuitoProductRow}オブジェクト 
   * @@return 指定の{@@link RuitoProductRow}に外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( RuitoProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link RuitoProductPK}と外部キーの関係にある{@@link RuitoOrderUnitRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RuitoProductPK}オブジェクト 
   * @@return {@@link RuitoProductPK}と外部キーが一致する値を持つ{@@link RuitoOrderUnitRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( RuitoProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoOrderUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RuitoProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(RuitoProductRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByProductId( RuitoProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(RuitoProductRow)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByProductId( RuitoProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(RuitoProductPK)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByProductId( RuitoProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
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
   * p_orderUnitId, and にて指定の値から一意の{@@link RuitoOrderUnitRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 引数指定のp_orderUnitId, and の値と一致する{@@link RuitoOrderUnitRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RuitoOrderUnitRow findRowByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { new Long(p_orderUnitId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RuitoOrderUnitRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RuitoOrderUnitDao.findRowsByOrderUnitId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderUnitId(long)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static RuitoOrderUnitDao findDaoByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderUnitId( p_orderUnitId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, p_orderOpenStatus, and にて指定の値に一致する{@@link RuitoOrderUnitRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderOpenStatus 検索対象であるp_orderOpenStatusフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_orderOpenStatus, and の値と一致する{@@link RuitoOrderUnitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountIdOrderOpenStatus( long p_accountId, long p_subAccountId, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoOrderUnitRow.TYPE,
            "account_id=? and sub_account_id=? and order_open_status=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), p_orderOpenStatus } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountIdOrderOpenStatus(long, long, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum)}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountIdOrderOpenStatus( long p_accountId, long p_subAccountId, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdSubAccountIdOrderOpenStatus( p_accountId, p_subAccountId, p_orderOpenStatus ) );
    }

}
@


1.1
log
@*** empty log message ***
@
text
@d403 1
a403 1
   * この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
d405 1
a405 1
   * @@return この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoOrderActionRow}オブジェクトの{@@link List}
d409 2
a410 2
    public List fetchRuitoOrderActionRowsByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderActionDao.findRowsByOrderUnitId( row );
d415 1
a415 1
   * @@deprecated 代わりに{@@link #fetchRuitoOrderActionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
d417 2
a418 2
    public List fetchRuitoOrderActionDaosByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoOrderActionDao.findDaosByOrderUnitId( row );
d423 1
a423 1
   * @@deprecated 代わりに{@@link #fetchRuitoOrderActionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
d425 2
a426 2
    public List fetchRuitoOrderActionDaosOrderUnitId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoOrderActionDaosByOrderUnitId();
d459 1
a459 1
   * この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
d461 1
a461 1
   * @@return この{@@link RuitoOrderUnitDao}に関連する{@@link RuitoOrderUnitRow}の外部キーがある{@@link RuitoFinTransactionRow}オブジェクトの{@@link List}
d465 2
a466 2
    public List fetchRuitoFinTransactionRowsByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoFinTransactionDao.findRowsByOrderUnitId( row );
d471 1
a471 1
   * @@deprecated 代わりに{@@link #fetchRuitoFinTransactionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
d473 2
a474 2
    public List fetchRuitoFinTransactionDaosByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return RuitoFinTransactionDao.findDaosByOrderUnitId( row );
d479 1
a479 1
   * @@deprecated 代わりに{@@link #fetchRuitoFinTransactionRowsByOrderUnitId()}および{@@link #forRow(RuitoOrderUnitRow)}を使用してください。 
d481 2
a482 2
    public List fetchRuitoFinTransactionDaosOrderUnitId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoFinTransactionDaosByOrderUnitId();
@

