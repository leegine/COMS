head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.38.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeOrderExecutionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link EqtypeOrderExecutionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EqtypeOrderExecutionRow}インスタンスへ関連付けることができます。 
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
 * @@see EqtypeOrderExecutionPK 
 * @@see EqtypeOrderExecutionRow 
 */
public class EqtypeOrderExecutionDao extends DataAccessObject {


  /** 
   * この{@@link EqtypeOrderExecutionDao}に関連する型指定のRowオブジェクト 
   */
    private EqtypeOrderExecutionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EqtypeOrderExecutionRow}と仮定される{@@link DataAccessObject}から新たに{@@link EqtypeOrderExecutionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EqtypeOrderExecutionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EqtypeOrderExecutionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeOrderExecutionRow )
                return new EqtypeOrderExecutionDao( (EqtypeOrderExecutionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeOrderExecutionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeOrderExecutionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EqtypeOrderExecutionRow}オブジェクト 
    */
    protected EqtypeOrderExecutionDao( EqtypeOrderExecutionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EqtypeOrderExecutionRow}オブジェクトを取得します。
   */
    public EqtypeOrderExecutionRow getEqtypeOrderExecutionRow() {
        return row;
    }


  /** 
   * 指定の{@@link EqtypeOrderExecutionRow}オブジェクトから{@@link EqtypeOrderExecutionDao}オブジェクトを作成します。 
   * これは実際の{@@link EqtypeOrderExecutionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EqtypeOrderExecutionDao}取得のために指定の{@@link EqtypeOrderExecutionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EqtypeOrderExecutionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EqtypeOrderExecutionDao forRow( EqtypeOrderExecutionRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeOrderExecutionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeOrderExecutionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EqtypeOrderExecutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EqtypeOrderExecutionPK}やデータベースレコードとして挿入される{@@link EqtypeOrderExecutionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeOrderExecutionRow.TYPE );
    }


  /** 
   * {@@link EqtypeOrderExecutionRow}を一意に特定する{@@link EqtypeOrderExecutionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EqtypeOrderExecutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EqtypeOrderExecutionParams}オブジェクトの主キーとして利用可能な{@@link EqtypeOrderExecutionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EqtypeOrderExecutionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new EqtypeOrderExecutionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EqtypeOrderExecutionRow}オブジェクトを検索します。 
   * 
   * @@param p_orderExecutionId 検索対象であるp_orderExecutionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeOrderExecutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeOrderExecutionRow findRowByPk( long p_orderExecutionId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecutionPK pk = new EqtypeOrderExecutionPK( p_orderExecutionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEqtypeOrderExecutionPKオブジェクトから{@@link EqtypeOrderExecutionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEqtypeOrderExecutionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeOrderExecutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeOrderExecutionRow findRowByPk( EqtypeOrderExecutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeOrderExecutionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static EqtypeOrderExecutionDao findDaoByPk( long p_orderExecutionId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecutionPK pk = new EqtypeOrderExecutionPK( p_orderExecutionId );
        EqtypeOrderExecutionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EqtypeOrderExecutionPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static EqtypeOrderExecutionDao findDaoByPk( EqtypeOrderExecutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecutionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeOrderExecutionDao}に紐付く{@@link EqtypeOrderExecutionRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderExecutionDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link EqtypeOrderExecutionDao}に紐付く{@@link EqtypeOrderExecutionRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderExecutionDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link EqtypeOrderExecutionDao}に紐付く{@@link EqtypeOrderExecutionRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderExecutionDao}と外部キーの関係にある{@@link BranchRow} 
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
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * この{@@link EqtypeOrderExecutionDao}に紐付く{@@link EqtypeOrderExecutionRow}内で外部キーの関係をもつ{@@link TraderRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderExecutionDao}と外部キーの関係にある{@@link TraderRow}または外部キーIDの値が現在nullに設定の場合はnull 
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
   * @@deprecated 代わりに{@@link #fetchTraderRowViaTraderId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
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
   * この{@@link EqtypeOrderExecutionDao}に紐付く{@@link EqtypeOrderExecutionRow}内で外部キーの関係をもつ{@@link EqtypeOrderRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderExecutionDao}と外部キーの関係にある{@@link EqtypeOrderRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public EqtypeOrderRow fetchEqtypeOrderRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeOrderPK pk = new EqtypeOrderPK( row.getOrderId() );
        Row row = EqtypeOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof EqtypeOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (EqtypeOrderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderRowViaOrderId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public EqtypeOrderDao fetchEqtypeOrderDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeOrderPK pk = new EqtypeOrderPK( row.getOrderId() );
        DataAccessObject dao = EqtypeOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeOrderDao) dao;
    }


  /** 
   * この{@@link EqtypeOrderExecutionDao}に紐付く{@@link EqtypeOrderExecutionRow}内で外部キーの関係をもつ{@@link EqtypeOrderUnitRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderExecutionDao}と外部キーの関係にある{@@link EqtypeOrderUnitRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public EqtypeOrderUnitRow fetchEqtypeOrderUnitRowViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeOrderUnitPK pk = new EqtypeOrderUnitPK( row.getOrderUnitId() );
        Row row = EqtypeOrderUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof EqtypeOrderUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (EqtypeOrderUnitRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeOrderUnitRowViaOrderUnitId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public EqtypeOrderUnitDao fetchEqtypeOrderUnitDaoViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeOrderUnitPK pk = new EqtypeOrderUnitPK( row.getOrderUnitId() );
        DataAccessObject dao = EqtypeOrderUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeOrderUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeOrderUnitDao) dao;
    }


  /** 
   * この{@@link EqtypeOrderExecutionDao}に紐付く{@@link EqtypeOrderExecutionRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderExecutionDao}と外部キーの関係にある{@@link MarketRow}または外部キーIDの値が現在nullに設定の場合はnull 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
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
   * この{@@link EqtypeOrderExecutionDao}に紐付く{@@link EqtypeOrderExecutionRow}内で外部キーの関係をもつ{@@link EqtypeProductRow}を検索します。 
   * 
   * @@return {@@link EqtypeOrderExecutionDao}と外部キーの関係にある{@@link EqtypeProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public EqtypeProductRow fetchEqtypeProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeProductPK pk = new EqtypeProductPK( row.getProductId() );
        Row row = EqtypeProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof EqtypeProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (EqtypeProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeProductRowViaProductId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public EqtypeProductDao fetchEqtypeProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeProductPK pk = new EqtypeProductPK( row.getProductId() );
        DataAccessObject dao = EqtypeProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeProductDao) dao;
    }


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link EqtypeOrderExecutionDao}に関連する{@@link EqtypeOrderExecutionRow}の外部キーがある{@@link EqtypeFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link EqtypeOrderExecutionDao}に関連する{@@link EqtypeOrderExecutionRow}の外部キーがある{@@link EqtypeFinTransactionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchEqtypeFinTransactionRowsByOrderExecutionId() throws DataNetworkException, DataQueryException  {
        return EqtypeFinTransactionDao.findRowsByOrderExecutionId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeFinTransactionRowsByOrderExecutionId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public List fetchEqtypeFinTransactionDaosByOrderExecutionId() throws DataNetworkException, DataQueryException  {
        return EqtypeFinTransactionDao.findDaosByOrderExecutionId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchEqtypeFinTransactionRowsByOrderExecutionId()}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public List fetchEqtypeFinTransactionDaosOrderExecutionId() throws DataNetworkException, DataQueryException  {
        return fetchEqtypeFinTransactionDaosByOrderExecutionId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link EqtypeOrderExecutionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link EqtypeOrderExecutionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * {@@link BranchRow}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link EqtypeOrderExecutionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
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
   * {@@link TraderRow}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link TraderRow}オブジェクト 
   * @@return 指定の{@@link TraderRow}に外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTraderId( TraderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( row.getTraderId() );
    }


  /** 
   * {@@link TraderPK}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link TraderPK}オブジェクト 
   * @@return {@@link TraderPK}と外部キーが一致する値を持つ{@@link EqtypeOrderExecutionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTraderId( TraderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( pk.trader_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_traderId 検索対象であるp_traderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTraderId( long p_traderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "trader_id=?",
            null,
            new Object[] { new Long(p_traderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Trader
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByTraderId(TraderRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByTraderId( TraderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTraderId(TraderRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByTraderId( TraderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTraderId(TraderPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByTraderId( TraderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( pk.trader_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTraderId(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByTraderId( long p_traderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( p_traderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for EqtypeOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderId(EqtypeOrderRow)}を使用してください。 
   */
    public static List findRowsByOrderId( EqtypeOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getEqtypeOrderRow() );
    }


  /** 
   * {@@link EqtypeOrderRow}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeOrderRow}オブジェクト 
   * @@return 指定の{@@link EqtypeOrderRow}に外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( EqtypeOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link EqtypeOrderPK}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeOrderPK}オブジェクト 
   * @@return {@@link EqtypeOrderPK}と外部キーが一致する値を持つ{@@link EqtypeOrderExecutionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( EqtypeOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderId(EqtypeOrderRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( EqtypeOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(EqtypeOrderRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( EqtypeOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(EqtypeOrderPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( EqtypeOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for EqtypeOrderUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderUnitId(EqtypeOrderUnitRow)}を使用してください。 
   */
    public static List findRowsByOrderUnitId( EqtypeOrderUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( dao.getEqtypeOrderUnitRow() );
    }


  /** 
   * {@@link EqtypeOrderUnitRow}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeOrderUnitRow}オブジェクト 
   * @@return 指定の{@@link EqtypeOrderUnitRow}に外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( EqtypeOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( row.getOrderUnitId() );
    }


  /** 
   * {@@link EqtypeOrderUnitPK}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeOrderUnitPK}オブジェクト 
   * @@return {@@link EqtypeOrderUnitPK}と外部キーが一致する値を持つ{@@link EqtypeOrderExecutionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( EqtypeOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( pk.order_unit_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( long p_orderUnitId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { new Long(p_orderUnitId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeOrderUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderUnitId(EqtypeOrderUnitRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( EqtypeOrderUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(EqtypeOrderUnitRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( EqtypeOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(EqtypeOrderUnitPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( EqtypeOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( pk.order_unit_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( p_orderUnitId ) );
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
   * {@@link MarketRow}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link EqtypeOrderExecutionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for EqtypeProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(EqtypeProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( EqtypeProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getEqtypeProductRow() );
    }


  /** 
   * {@@link EqtypeProductRow}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link EqtypeProductRow}オブジェクト 
   * @@return 指定の{@@link EqtypeProductRow}に外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( EqtypeProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link EqtypeProductPK}と外部キーの関係にある{@@link EqtypeOrderExecutionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link EqtypeProductPK}オブジェクト 
   * @@return {@@link EqtypeProductPK}と外部キーが一致する値を持つ{@@link EqtypeOrderExecutionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( EqtypeProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link EqtypeOrderExecutionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(EqtypeProductRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByProductId( EqtypeProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(EqtypeProductRow)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByProductId( EqtypeProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(EqtypeProductPK)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByProductId( EqtypeProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
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
   * p_orderExecutionId, and にて指定の値から一意の{@@link EqtypeOrderExecutionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_orderExecutionId 検索対象であるp_orderExecutionIdフィールドの値
   * 
   * @@return 引数指定のp_orderExecutionId, and の値と一致する{@@link EqtypeOrderExecutionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EqtypeOrderExecutionRow findRowByOrderExecutionId( long p_orderExecutionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "order_execution_id=?",
            null,
            new Object[] { new Long(p_orderExecutionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeOrderExecutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeOrderExecutionDao.findRowsByOrderExecutionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrderExecutionId(long)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static EqtypeOrderExecutionDao findDaoByOrderExecutionId( long p_orderExecutionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderExecutionId( p_orderExecutionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_orderExecutionId, p_accountId, p_subAccountId, p_branchId, p_traderId, p_orderId, p_orderUnitId, p_marketId, p_productId, p_orderRequestNumber, p_productType, and にて指定の値に一致する{@@link EqtypeOrderExecutionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderExecutionId 検索対象であるp_orderExecutionIdフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_traderId 検索対象であるp_traderIdフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_orderExecutionId, p_accountId, p_subAccountId, p_branchId, p_traderId, p_orderId, p_orderUnitId, p_marketId, p_productId, p_orderRequestNumber, p_productType, and の値と一致する{@@link EqtypeOrderExecutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderExecutionIdAccountIdSubAccountIdBranchIdTraderIdOrderIdOrderUnitIdMarketIdProductIdOrderRequestNumberProductType( long p_orderExecutionId, long p_accountId, long p_subAccountId, long p_branchId, Long p_traderId, long p_orderId, long p_orderUnitId, Long p_marketId, long p_productId, String p_orderRequestNumber, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecutionRow.TYPE,
            "order_execution_id=? and account_id=? and sub_account_id=? and branch_id=? and trader_id=? and order_id=? and order_unit_id=? and market_id=? and product_id=? and order_request_number=? and product_type=?",
            null,
            new Object[] { new Long(p_orderExecutionId), new Long(p_accountId), new Long(p_subAccountId), new Long(p_branchId), p_traderId, new Long(p_orderId), new Long(p_orderUnitId), p_marketId, new Long(p_productId), p_orderRequestNumber, p_productType } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderExecutionIdAccountIdSubAccountIdBranchIdTraderIdOrderIdOrderUnitIdMarketIdProductIdOrderRequestNumberProductType(long, long, long, long, Long, long, long, Long, long, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(EqtypeOrderExecutionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionIdAccountIdSubAccountIdBranchIdTraderIdOrderIdOrderUnitIdMarketIdProductIdOrderRequestNumberProductType( long p_orderExecutionId, long p_accountId, long p_subAccountId, long p_branchId, Long p_traderId, long p_orderId, long p_orderUnitId, Long p_marketId, long p_productId, String p_orderRequestNumber, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderExecutionIdAccountIdSubAccountIdBranchIdTraderIdOrderIdOrderUnitIdMarketIdProductIdOrderRequestNumberProductType( p_orderExecutionId, p_accountId, p_subAccountId, p_branchId, p_traderId, p_orderId, p_orderUnitId, p_marketId, p_productId, p_orderRequestNumber, p_productType ) );
    }

}
@
