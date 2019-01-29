head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.15.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoFinTransactionDao.java;


desc
@@


1.1
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
 * {@@link RuitoFinTransactionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RuitoFinTransactionRow}インスタンスへ関連付けることができます。 
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
 * @@see RuitoFinTransactionPK 
 * @@see RuitoFinTransactionRow 
 */
public class RuitoFinTransactionDao extends DataAccessObject {


  /** 
   * この{@@link RuitoFinTransactionDao}に関連する型指定のRowオブジェクト 
   */
    private RuitoFinTransactionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RuitoFinTransactionRow}と仮定される{@@link DataAccessObject}から新たに{@@link RuitoFinTransactionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RuitoFinTransactionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RuitoFinTransactionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RuitoFinTransactionRow )
                return new RuitoFinTransactionDao( (RuitoFinTransactionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RuitoFinTransactionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RuitoFinTransactionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RuitoFinTransactionRow}オブジェクト 
    */
    protected RuitoFinTransactionDao( RuitoFinTransactionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RuitoFinTransactionRow}オブジェクトを取得します。
   */
    public RuitoFinTransactionRow getRuitoFinTransactionRow() {
        return row;
    }


  /** 
   * 指定の{@@link RuitoFinTransactionRow}オブジェクトから{@@link RuitoFinTransactionDao}オブジェクトを作成します。 
   * これは実際の{@@link RuitoFinTransactionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RuitoFinTransactionDao}取得のために指定の{@@link RuitoFinTransactionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RuitoFinTransactionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RuitoFinTransactionDao forRow( RuitoFinTransactionRow row ) throws java.lang.IllegalArgumentException {
        return (RuitoFinTransactionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RuitoFinTransactionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RuitoFinTransactionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RuitoFinTransactionPK}やデータベースレコードとして挿入される{@@link RuitoFinTransactionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RuitoFinTransactionRow.TYPE );
    }


  /** 
   * {@@link RuitoFinTransactionRow}を一意に特定する{@@link RuitoFinTransactionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RuitoFinTransactionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RuitoFinTransactionParams}オブジェクトの主キーとして利用可能な{@@link RuitoFinTransactionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RuitoFinTransactionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RuitoFinTransactionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RuitoFinTransactionRow}オブジェクトを検索します。 
   * 
   * @@param p_finTransactionId 検索対象であるp_finTransactionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RuitoFinTransactionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RuitoFinTransactionRow findRowByPk( long p_finTransactionId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoFinTransactionPK pk = new RuitoFinTransactionPK( p_finTransactionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRuitoFinTransactionPKオブジェクトから{@@link RuitoFinTransactionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRuitoFinTransactionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RuitoFinTransactionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RuitoFinTransactionRow findRowByPk( RuitoFinTransactionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RuitoFinTransactionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static RuitoFinTransactionDao findDaoByPk( long p_finTransactionId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoFinTransactionPK pk = new RuitoFinTransactionPK( p_finTransactionId );
        RuitoFinTransactionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RuitoFinTransactionPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static RuitoFinTransactionDao findDaoByPk( RuitoFinTransactionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoFinTransactionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link RuitoFinTransactionDao}に紐付く{@@link RuitoFinTransactionRow}内で外部キーの関係をもつ{@@link RuitoProductRow}を検索します。 
   * 
   * @@return {@@link RuitoFinTransactionDao}と外部キーの関係にある{@@link RuitoProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchRuitoProductRowViaProductId()}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public RuitoProductDao fetchRuitoProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        RuitoProductPK pk = new RuitoProductPK( row.getProductId() );
        DataAccessObject dao = RuitoProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RuitoProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RuitoProductDao) dao;
    }


  /** 
   * この{@@link RuitoFinTransactionDao}に紐付く{@@link RuitoFinTransactionRow}内で外部キーの関係をもつ{@@link RuitoOrderRow}を検索します。 
   * 
   * @@return {@@link RuitoFinTransactionDao}と外部キーの関係にある{@@link RuitoOrderRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public RuitoOrderRow fetchRuitoOrderRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderIdIsNull() )
            return null;
        RuitoOrderPK pk = new RuitoOrderPK( row.getOrderId() );
        Row row = RuitoOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RuitoOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RuitoOrderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderRowViaOrderId()}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public RuitoOrderDao fetchRuitoOrderDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderIdIsNull() )
            return null;
        RuitoOrderPK pk = new RuitoOrderPK( row.getOrderId() );
        DataAccessObject dao = RuitoOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RuitoOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RuitoOrderDao) dao;
    }


  /** 
   * この{@@link RuitoFinTransactionDao}に紐付く{@@link RuitoFinTransactionRow}内で外部キーの関係をもつ{@@link RuitoOrderUnitRow}を検索します。 
   * 
   * @@return {@@link RuitoFinTransactionDao}と外部キーの関係にある{@@link RuitoOrderUnitRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public RuitoOrderUnitRow fetchRuitoOrderUnitRowViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderUnitIdIsNull() )
            return null;
        RuitoOrderUnitPK pk = new RuitoOrderUnitPK( row.getOrderUnitId() );
        Row row = RuitoOrderUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RuitoOrderUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RuitoOrderUnitRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderUnitRowViaOrderUnitId()}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public RuitoOrderUnitDao fetchRuitoOrderUnitDaoViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderUnitIdIsNull() )
            return null;
        RuitoOrderUnitPK pk = new RuitoOrderUnitPK( row.getOrderUnitId() );
        DataAccessObject dao = RuitoOrderUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RuitoOrderUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RuitoOrderUnitDao) dao;
    }


  /** 
   * この{@@link RuitoFinTransactionDao}に紐付く{@@link RuitoFinTransactionRow}内で外部キーの関係をもつ{@@link RuitoOrderExecutionRow}を検索します。 
   * 
   * @@return {@@link RuitoFinTransactionDao}と外部キーの関係にある{@@link RuitoOrderExecutionRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public RuitoOrderExecutionRow fetchRuitoOrderExecutionRowViaOrderExecutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderExecutionIdIsNull() )
            return null;
        RuitoOrderExecutionPK pk = new RuitoOrderExecutionPK( row.getOrderExecutionId() );
        Row row = RuitoOrderExecutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RuitoOrderExecutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RuitoOrderExecutionRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoOrderExecutionRowViaOrderExecutionId()}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public RuitoOrderExecutionDao fetchRuitoOrderExecutionDaoViaOrderExecutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderExecutionIdIsNull() )
            return null;
        RuitoOrderExecutionPK pk = new RuitoOrderExecutionPK( row.getOrderExecutionId() );
        DataAccessObject dao = RuitoOrderExecutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RuitoOrderExecutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RuitoOrderExecutionDao) dao;
    }


  /** 
   * この{@@link RuitoFinTransactionDao}に紐付く{@@link RuitoFinTransactionRow}内で外部キーの関係をもつ{@@link AssetRow}を検索します。 
   * 
   * @@return {@@link RuitoFinTransactionDao}と外部キーの関係にある{@@link AssetRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public AssetRow fetchAssetRowViaAssetId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getAssetIdIsNull() )
            return null;
        AssetPK pk = new AssetPK( row.getAssetId() );
        Row row = AssetDao.findRowByPk( pk );
        if ( row != null && !(row instanceof AssetRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (AssetRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetRowViaAssetId()}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public AssetDao fetchAssetDaoViaAssetId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getAssetIdIsNull() )
            return null;
        AssetPK pk = new AssetPK( row.getAssetId() );
        DataAccessObject dao = AssetDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof AssetDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (AssetDao) dao;
    }


  /** 
   * この{@@link RuitoFinTransactionDao}に紐付く{@@link RuitoFinTransactionRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link RuitoFinTransactionDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link RuitoFinTransactionDao}に紐付く{@@link RuitoFinTransactionRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link RuitoFinTransactionDao}と外部キーの関係にある{@@link MarketRow}または外部キーIDの値が現在nullに設定の場合はnull 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
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
   * この{@@link RuitoFinTransactionDao}に紐付く{@@link RuitoFinTransactionRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link RuitoFinTransactionDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

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
   * {@@link RuitoProductRow}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RuitoProductRow}オブジェクト 
   * @@return 指定の{@@link RuitoProductRow}に外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( RuitoProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link RuitoProductPK}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RuitoProductPK}オブジェクト 
   * @@return {@@link RuitoProductPK}と外部キーが一致する値を持つ{@@link RuitoFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( RuitoProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RuitoProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(RuitoProductRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByProductId( RuitoProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(RuitoProductRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByProductId( RuitoProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(RuitoProductPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByProductId( RuitoProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
    }


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
   * {@@link RuitoOrderRow}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RuitoOrderRow}オブジェクト 
   * @@return 指定の{@@link RuitoOrderRow}に外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( RuitoOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link RuitoOrderPK}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RuitoOrderPK}オブジェクト 
   * @@return {@@link RuitoOrderPK}と外部キーが一致する値を持つ{@@link RuitoFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( RuitoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RuitoOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderId(RuitoOrderRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RuitoOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(RuitoOrderRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RuitoOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(RuitoOrderPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( RuitoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for RuitoOrderUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderUnitId(RuitoOrderUnitRow)}を使用してください。 
   */
    public static List findRowsByOrderUnitId( RuitoOrderUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( dao.getRuitoOrderUnitRow() );
    }


  /** 
   * {@@link RuitoOrderUnitRow}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RuitoOrderUnitRow}オブジェクト 
   * @@return 指定の{@@link RuitoOrderUnitRow}に外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( RuitoOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( row.getOrderUnitId() );
    }


  /** 
   * {@@link RuitoOrderUnitPK}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RuitoOrderUnitPK}オブジェクト 
   * @@return {@@link RuitoOrderUnitPK}と外部キーが一致する値を持つ{@@link RuitoFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( RuitoOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( pk.order_unit_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( long p_orderUnitId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { new Long(p_orderUnitId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RuitoOrderUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderUnitId(RuitoOrderUnitRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( RuitoOrderUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(RuitoOrderUnitRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( RuitoOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(RuitoOrderUnitPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( RuitoOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( pk.order_unit_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( p_orderUnitId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for RuitoOrderExecution
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderExecutionId(RuitoOrderExecutionRow)}を使用してください。 
   */
    public static List findRowsByOrderExecutionId( RuitoOrderExecutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderExecutionId( dao.getRuitoOrderExecutionRow() );
    }


  /** 
   * {@@link RuitoOrderExecutionRow}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RuitoOrderExecutionRow}オブジェクト 
   * @@return 指定の{@@link RuitoOrderExecutionRow}に外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderExecutionId( RuitoOrderExecutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderExecutionId( row.getOrderExecutionId() );
    }


  /** 
   * {@@link RuitoOrderExecutionPK}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RuitoOrderExecutionPK}オブジェクト 
   * @@return {@@link RuitoOrderExecutionPK}と外部キーが一致する値を持つ{@@link RuitoFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderExecutionId( RuitoOrderExecutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderExecutionId( pk.order_execution_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderExecutionId 検索対象であるp_orderExecutionIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderExecutionId( long p_orderExecutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "order_execution_id=?",
            null,
            new Object[] { new Long(p_orderExecutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RuitoOrderExecution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderExecutionId(RuitoOrderExecutionRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionId( RuitoOrderExecutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderExecutionId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderExecutionId(RuitoOrderExecutionRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionId( RuitoOrderExecutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderExecutionId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderExecutionId(RuitoOrderExecutionPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionId( RuitoOrderExecutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderExecutionId( pk.order_execution_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderExecutionId(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionId( long p_orderExecutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderExecutionId( p_orderExecutionId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Asset
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAssetId(AssetRow)}を使用してください。 
   */
    public static List findRowsByAssetId( AssetDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetId( dao.getAssetRow() );
    }


  /** 
   * {@@link AssetRow}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link AssetRow}オブジェクト 
   * @@return 指定の{@@link AssetRow}に外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( AssetRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetId( row.getAssetId() );
    }


  /** 
   * {@@link AssetPK}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link AssetPK}オブジェクト 
   * @@return {@@link AssetPK}と外部キーが一致する値を持つ{@@link RuitoFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( AssetPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetId( pk.asset_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( long p_assetId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "asset_id=?",
            null,
            new Object[] { new Long(p_assetId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Asset
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAssetId(AssetRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(AssetRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(AssetPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( pk.asset_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAssetId( long p_assetId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( p_assetId ) );
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link RuitoFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
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
   * {@@link MarketRow}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link RuitoFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link RuitoFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link RuitoFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
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
   * p_finTransactionId, and にて指定の値から一意の{@@link RuitoFinTransactionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_finTransactionId 検索対象であるp_finTransactionIdフィールドの値
   * 
   * @@return 引数指定のp_finTransactionId, and の値と一致する{@@link RuitoFinTransactionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RuitoFinTransactionRow findRowByFinTransactionId( long p_finTransactionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RuitoFinTransactionRow.TYPE,
            "fin_transaction_id=?",
            null,
            new Object[] { new Long(p_finTransactionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RuitoFinTransactionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RuitoFinTransactionDao.findRowsByFinTransactionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFinTransactionId(long)}および{@@link #forRow(RuitoFinTransactionRow)}を使用してください。 
   */
    public static RuitoFinTransactionDao findDaoByFinTransactionId( long p_finTransactionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFinTransactionId( p_finTransactionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

}
@
