head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.59.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondFinTransactionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BondFinTransactionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BondFinTransactionRow}インスタンスへ関連付けることができます。 
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
 * @@see BondFinTransactionPK 
 * @@see BondFinTransactionRow 
 */
public class BondFinTransactionDao extends DataAccessObject {


  /** 
   * この{@@link BondFinTransactionDao}に関連する型指定のRowオブジェクト 
   */
    private BondFinTransactionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BondFinTransactionRow}と仮定される{@@link DataAccessObject}から新たに{@@link BondFinTransactionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BondFinTransactionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BondFinTransactionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondFinTransactionRow )
                return new BondFinTransactionDao( (BondFinTransactionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondFinTransactionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondFinTransactionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BondFinTransactionRow}オブジェクト 
    */
    protected BondFinTransactionDao( BondFinTransactionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BondFinTransactionRow}オブジェクトを取得します。
   */
    public BondFinTransactionRow getBondFinTransactionRow() {
        return row;
    }


  /** 
   * 指定の{@@link BondFinTransactionRow}オブジェクトから{@@link BondFinTransactionDao}オブジェクトを作成します。 
   * これは実際の{@@link BondFinTransactionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BondFinTransactionDao}取得のために指定の{@@link BondFinTransactionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BondFinTransactionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BondFinTransactionDao forRow( BondFinTransactionRow row ) throws java.lang.IllegalArgumentException {
        return (BondFinTransactionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondFinTransactionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BondFinTransactionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BondFinTransactionPK}やデータベースレコードとして挿入される{@@link BondFinTransactionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondFinTransactionRow.TYPE );
    }


  /** 
   * {@@link BondFinTransactionRow}を一意に特定する{@@link BondFinTransactionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BondFinTransactionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BondFinTransactionParams}オブジェクトの主キーとして利用可能な{@@link BondFinTransactionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BondFinTransactionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BondFinTransactionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BondFinTransactionRow}オブジェクトを検索します。 
   * 
   * @@param p_finTransactionId 検索対象であるp_finTransactionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondFinTransactionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondFinTransactionRow findRowByPk( long p_finTransactionId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondFinTransactionPK pk = new BondFinTransactionPK( p_finTransactionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBondFinTransactionPKオブジェクトから{@@link BondFinTransactionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBondFinTransactionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondFinTransactionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondFinTransactionRow findRowByPk( BondFinTransactionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondFinTransactionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static BondFinTransactionDao findDaoByPk( long p_finTransactionId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondFinTransactionPK pk = new BondFinTransactionPK( p_finTransactionId );
        BondFinTransactionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BondFinTransactionPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static BondFinTransactionDao findDaoByPk( BondFinTransactionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondFinTransactionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link BondFinTransactionDao}に紐付く{@@link BondFinTransactionRow}内で外部キーの関係をもつ{@@link BondProductRow}を検索します。 
   * 
   * @@return {@@link BondFinTransactionDao}と外部キーの関係にある{@@link BondProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public BondProductRow fetchBondProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        Row row = BondProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondProductRowViaProductId()}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public BondProductDao fetchBondProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        DataAccessObject dao = BondProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondProductDao) dao;
    }


  /** 
   * この{@@link BondFinTransactionDao}に紐付く{@@link BondFinTransactionRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link BondFinTransactionDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link BondFinTransactionDao}に紐付く{@@link BondFinTransactionRow}内で外部キーの関係をもつ{@@link MarketRow}を検索します。 
   * 
   * @@return {@@link BondFinTransactionDao}と外部キーの関係にある{@@link MarketRow}または外部キーIDの値が現在nullに設定の場合はnull 
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
   * @@deprecated 代わりに{@@link #fetchMarketRowViaMarketId()}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
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
   * この{@@link BondFinTransactionDao}に紐付く{@@link BondFinTransactionRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link BondFinTransactionDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * この{@@link BondFinTransactionDao}に紐付く{@@link BondFinTransactionRow}内で外部キーの関係をもつ{@@link AssetRow}を検索します。 
   * 
   * @@return {@@link BondFinTransactionDao}と外部キーの関係にある{@@link AssetRow}または外部キーIDの値が現在nullに設定の場合はnull 
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
   * @@deprecated 代わりに{@@link #fetchAssetRowViaAssetId()}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
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
   * この{@@link BondFinTransactionDao}に紐付く{@@link BondFinTransactionRow}内で外部キーの関係をもつ{@@link BondOrderExecutionRow}を検索します。 
   * 
   * @@return {@@link BondFinTransactionDao}と外部キーの関係にある{@@link BondOrderExecutionRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public BondOrderExecutionRow fetchBondOrderExecutionRowViaOrderExecutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderExecutionIdIsNull() )
            return null;
        BondOrderExecutionPK pk = new BondOrderExecutionPK( row.getOrderExecutionId() );
        Row row = BondOrderExecutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondOrderExecutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondOrderExecutionRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondOrderExecutionRowViaOrderExecutionId()}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public BondOrderExecutionDao fetchBondOrderExecutionDaoViaOrderExecutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderExecutionIdIsNull() )
            return null;
        BondOrderExecutionPK pk = new BondOrderExecutionPK( row.getOrderExecutionId() );
        DataAccessObject dao = BondOrderExecutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondOrderExecutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondOrderExecutionDao) dao;
    }


  /** 
   * この{@@link BondFinTransactionDao}に紐付く{@@link BondFinTransactionRow}内で外部キーの関係をもつ{@@link BondOrderUnitRow}を検索します。 
   * 
   * @@return {@@link BondFinTransactionDao}と外部キーの関係にある{@@link BondOrderUnitRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public BondOrderUnitRow fetchBondOrderUnitRowViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderUnitIdIsNull() )
            return null;
        BondOrderUnitPK pk = new BondOrderUnitPK( row.getOrderUnitId() );
        Row row = BondOrderUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondOrderUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondOrderUnitRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondOrderUnitRowViaOrderUnitId()}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public BondOrderUnitDao fetchBondOrderUnitDaoViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderUnitIdIsNull() )
            return null;
        BondOrderUnitPK pk = new BondOrderUnitPK( row.getOrderUnitId() );
        DataAccessObject dao = BondOrderUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondOrderUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondOrderUnitDao) dao;
    }


  /** 
   * この{@@link BondFinTransactionDao}に紐付く{@@link BondFinTransactionRow}内で外部キーの関係をもつ{@@link BondOrderRow}を検索します。 
   * 
   * @@return {@@link BondFinTransactionDao}と外部キーの関係にある{@@link BondOrderRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public BondOrderRow fetchBondOrderRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderIdIsNull() )
            return null;
        BondOrderPK pk = new BondOrderPK( row.getOrderId() );
        Row row = BondOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondOrderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondOrderRowViaOrderId()}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public BondOrderDao fetchBondOrderDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getOrderIdIsNull() )
            return null;
        BondOrderPK pk = new BondOrderPK( row.getOrderId() );
        DataAccessObject dao = BondOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondOrderDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(BondProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( BondProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getBondProductRow() );
    }


  /** 
   * {@@link BondProductRow}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BondProductRow}オブジェクト 
   * @@return 指定の{@@link BondProductRow}に外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link BondProductPK}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BondProductPK}オブジェクト 
   * @@return {@@link BondProductPK}と外部キーが一致する値を持つ{@@link BondFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(BondProductRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(BondProductRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(BondProductPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link BondFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
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
   * {@@link MarketRow}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MarketRow}オブジェクト 
   * @@return 指定の{@@link MarketRow}に外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MarketPK}オブジェクト 
   * @@return {@@link MarketPK}と外部キーが一致する値を持つ{@@link BondFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketId 検索対象であるp_marketIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(MarketPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketId(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link BondFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * {@@link AssetRow}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link AssetRow}オブジェクト 
   * @@return 指定の{@@link AssetRow}に外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( AssetRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetId( row.getAssetId() );
    }


  /** 
   * {@@link AssetPK}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link AssetPK}オブジェクト 
   * @@return {@@link AssetPK}と外部キーが一致する値を持つ{@@link BondFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( AssetPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetId( pk.asset_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( long p_assetId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "asset_id=?",
            null,
            new Object[] { new Long(p_assetId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Asset
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAssetId(AssetRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(AssetRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(AssetPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( pk.asset_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAssetId( long p_assetId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( p_assetId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondOrderExecution
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderExecutionId(BondOrderExecutionRow)}を使用してください。 
   */
    public static List findRowsByOrderExecutionId( BondOrderExecutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderExecutionId( dao.getBondOrderExecutionRow() );
    }


  /** 
   * {@@link BondOrderExecutionRow}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BondOrderExecutionRow}オブジェクト 
   * @@return 指定の{@@link BondOrderExecutionRow}に外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderExecutionId( BondOrderExecutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderExecutionId( row.getOrderExecutionId() );
    }


  /** 
   * {@@link BondOrderExecutionPK}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BondOrderExecutionPK}オブジェクト 
   * @@return {@@link BondOrderExecutionPK}と外部キーが一致する値を持つ{@@link BondFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderExecutionId( BondOrderExecutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderExecutionId( pk.order_execution_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderExecutionId 検索対象であるp_orderExecutionIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderExecutionId( long p_orderExecutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "order_execution_id=?",
            null,
            new Object[] { new Long(p_orderExecutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondOrderExecution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderExecutionId(BondOrderExecutionRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionId( BondOrderExecutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderExecutionId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderExecutionId(BondOrderExecutionRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionId( BondOrderExecutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderExecutionId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderExecutionId(BondOrderExecutionPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionId( BondOrderExecutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderExecutionId( pk.order_execution_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderExecutionId(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderExecutionId( long p_orderExecutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderExecutionId( p_orderExecutionId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondOrderUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderUnitId(BondOrderUnitRow)}を使用してください。 
   */
    public static List findRowsByOrderUnitId( BondOrderUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( dao.getBondOrderUnitRow() );
    }


  /** 
   * {@@link BondOrderUnitRow}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BondOrderUnitRow}オブジェクト 
   * @@return 指定の{@@link BondOrderUnitRow}に外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( BondOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( row.getOrderUnitId() );
    }


  /** 
   * {@@link BondOrderUnitPK}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BondOrderUnitPK}オブジェクト 
   * @@return {@@link BondOrderUnitPK}と外部キーが一致する値を持つ{@@link BondFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( BondOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( pk.order_unit_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( long p_orderUnitId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { new Long(p_orderUnitId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondOrderUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderUnitId(BondOrderUnitRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( BondOrderUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(BondOrderUnitRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( BondOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(BondOrderUnitPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( BondOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( pk.order_unit_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( p_orderUnitId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByOrderId(BondOrderRow)}を使用してください。 
   */
    public static List findRowsByOrderId( BondOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getBondOrderRow() );
    }


  /** 
   * {@@link BondOrderRow}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BondOrderRow}オブジェクト 
   * @@return 指定の{@@link BondOrderRow}に外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( BondOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link BondOrderPK}と外部キーの関係にある{@@link BondFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BondOrderPK}オブジェクト 
   * @@return {@@link BondOrderPK}と外部キーが一致する値を持つ{@@link BondFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( BondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByOrderId(BondOrderRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( BondOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(BondOrderRow)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( BondOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(BondOrderPK)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( BondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderId(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
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
   * p_finTransactionId, and にて指定の値から一意の{@@link BondFinTransactionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_finTransactionId 検索対象であるp_finTransactionIdフィールドの値
   * 
   * @@return 引数指定のp_finTransactionId, and の値と一致する{@@link BondFinTransactionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondFinTransactionRow findRowByFinTransactionId( long p_finTransactionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "fin_transaction_id=?",
            null,
            new Object[] { new Long(p_finTransactionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondFinTransactionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondFinTransactionDao.findRowsByFinTransactionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFinTransactionId(long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static BondFinTransactionDao findDaoByFinTransactionId( long p_finTransactionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFinTransactionId( p_finTransactionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, p_productId, p_orderUnitId, p_orderExecutionId, p_assetId, and にて指定の値に一致する{@@link BondFinTransactionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * @@param p_orderExecutionId 検索対象であるp_orderExecutionIdフィールドの値
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_productId, p_orderUnitId, p_orderExecutionId, p_assetId, and の値と一致する{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountIdProductIdOrderUnitIdOrderExecutionIdAssetId( long p_accountId, long p_subAccountId, long p_productId, Long p_orderUnitId, Long p_orderExecutionId, Long p_assetId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "account_id=? and sub_account_id=? and product_id=? and order_unit_id=? and order_execution_id=? and asset_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Long(p_productId), p_orderUnitId, p_orderExecutionId, p_assetId } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountIdProductIdOrderUnitIdOrderExecutionIdAssetId(long, long, long, Long, Long, Long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountIdProductIdOrderUnitIdOrderExecutionIdAssetId( long p_accountId, long p_subAccountId, long p_productId, Long p_orderUnitId, Long p_orderExecutionId, Long p_assetId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdSubAccountIdProductIdOrderUnitIdOrderExecutionIdAssetId( p_accountId, p_subAccountId, p_productId, p_orderUnitId, p_orderExecutionId, p_assetId ) );
    }


  /** 
   * p_orderUnitId, and にて指定の値に一致する{@@link BondFinTransactionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 引数指定のp_orderUnitId, and の値と一致する{@@link BondFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderUnitId( Long p_orderUnitId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondFinTransactionRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { p_orderUnitId } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderUnitId(Long)}および{@@link #forRow(BondFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByOrderUnitId( Long p_orderUnitId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderUnitId( p_orderUnitId ) );
    }

}
@
