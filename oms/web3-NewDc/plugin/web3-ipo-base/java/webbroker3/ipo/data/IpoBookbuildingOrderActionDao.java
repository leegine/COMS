head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingOrderActionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ipo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IpoBookbuildingOrderActionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IpoBookbuildingOrderActionRow}インスタンスへ関連付けることができます。 
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
 * @@see IpoBookbuildingOrderActionPK 
 * @@see IpoBookbuildingOrderActionRow 
 */
public class IpoBookbuildingOrderActionDao extends DataAccessObject {


  /** 
   * この{@@link IpoBookbuildingOrderActionDao}に関連する型指定のRowオブジェクト 
   */
    private IpoBookbuildingOrderActionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IpoBookbuildingOrderActionRow}と仮定される{@@link DataAccessObject}から新たに{@@link IpoBookbuildingOrderActionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IpoBookbuildingOrderActionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IpoBookbuildingOrderActionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IpoBookbuildingOrderActionRow )
                return new IpoBookbuildingOrderActionDao( (IpoBookbuildingOrderActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IpoBookbuildingOrderActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IpoBookbuildingOrderActionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IpoBookbuildingOrderActionRow}オブジェクト 
    */
    protected IpoBookbuildingOrderActionDao( IpoBookbuildingOrderActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IpoBookbuildingOrderActionRow}オブジェクトを取得します。
   */
    public IpoBookbuildingOrderActionRow getIpoBookbuildingOrderActionRow() {
        return row;
    }


  /** 
   * 指定の{@@link IpoBookbuildingOrderActionRow}オブジェクトから{@@link IpoBookbuildingOrderActionDao}オブジェクトを作成します。 
   * これは実際の{@@link IpoBookbuildingOrderActionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IpoBookbuildingOrderActionDao}取得のために指定の{@@link IpoBookbuildingOrderActionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IpoBookbuildingOrderActionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IpoBookbuildingOrderActionDao forRow( IpoBookbuildingOrderActionRow row ) throws java.lang.IllegalArgumentException {
        return (IpoBookbuildingOrderActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IpoBookbuildingOrderActionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IpoBookbuildingOrderActionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IpoBookbuildingOrderActionPK}やデータベースレコードとして挿入される{@@link IpoBookbuildingOrderActionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IpoBookbuildingOrderActionRow.TYPE );
    }


  /** 
   * {@@link IpoBookbuildingOrderActionRow}を一意に特定する{@@link IpoBookbuildingOrderActionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IpoBookbuildingOrderActionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IpoBookbuildingOrderActionParams}オブジェクトの主キーとして利用可能な{@@link IpoBookbuildingOrderActionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IpoBookbuildingOrderActionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IpoBookbuildingOrderActionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索します。 
   * 
   * @@param p_bookbuildingOrderActionId 検索対象であるp_bookbuildingOrderActionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IpoBookbuildingOrderActionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IpoBookbuildingOrderActionRow findRowByPk( long p_bookbuildingOrderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingOrderActionPK pk = new IpoBookbuildingOrderActionPK( p_bookbuildingOrderActionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIpoBookbuildingOrderActionPKオブジェクトから{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIpoBookbuildingOrderActionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IpoBookbuildingOrderActionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IpoBookbuildingOrderActionRow findRowByPk( IpoBookbuildingOrderActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IpoBookbuildingOrderActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static IpoBookbuildingOrderActionDao findDaoByPk( long p_bookbuildingOrderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingOrderActionPK pk = new IpoBookbuildingOrderActionPK( p_bookbuildingOrderActionId );
        IpoBookbuildingOrderActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IpoBookbuildingOrderActionPK)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static IpoBookbuildingOrderActionDao findDaoByPk( IpoBookbuildingOrderActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingOrderActionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link IpoBookbuildingOrderActionDao}に紐付く{@@link IpoBookbuildingOrderActionRow}内で外部キーの関係をもつ{@@link IpoOrderRow}を検索します。 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}と外部キーの関係にある{@@link IpoOrderRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public IpoOrderRow fetchIpoOrderRowViaIpoOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        IpoOrderPK pk = new IpoOrderPK( row.getIpoOrderId() );
        Row row = IpoOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IpoOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IpoOrderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIpoOrderRowViaIpoOrderId()}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public IpoOrderDao fetchIpoOrderDaoViaIpoOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        IpoOrderPK pk = new IpoOrderPK( row.getIpoOrderId() );
        DataAccessObject dao = IpoOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IpoOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IpoOrderDao) dao;
    }


  /** 
   * この{@@link IpoBookbuildingOrderActionDao}に紐付く{@@link IpoBookbuildingOrderActionRow}内で外部キーの関係をもつ{@@link IpoProductRow}を検索します。 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}と外部キーの関係にある{@@link IpoProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public IpoProductRow fetchIpoProductRowViaIpoProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        IpoProductPK pk = new IpoProductPK( row.getIpoProductId() );
        Row row = IpoProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IpoProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IpoProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIpoProductRowViaIpoProductId()}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public IpoProductDao fetchIpoProductDaoViaIpoProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        IpoProductPK pk = new IpoProductPK( row.getIpoProductId() );
        DataAccessObject dao = IpoProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IpoProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IpoProductDao) dao;
    }


  /** 
   * この{@@link IpoBookbuildingOrderActionDao}に紐付く{@@link IpoBookbuildingOrderActionRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}と外部キーの関係にある{@@link BranchRow} 
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
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * この{@@link IpoBookbuildingOrderActionDao}に紐付く{@@link IpoBookbuildingOrderActionRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link IpoBookbuildingOrderActionDao}に紐付く{@@link IpoBookbuildingOrderActionRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for IpoOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByIpoOrderId(IpoOrderRow)}を使用してください。 
   */
    public static List findRowsByIpoOrderId( IpoOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoOrderId( dao.getIpoOrderRow() );
    }


  /** 
   * {@@link IpoOrderRow}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IpoOrderRow}オブジェクト 
   * @@return 指定の{@@link IpoOrderRow}に外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIpoOrderId( IpoOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoOrderId( row.getIpoOrderId() );
    }


  /** 
   * {@@link IpoOrderPK}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IpoOrderPK}オブジェクト 
   * @@return {@@link IpoOrderPK}と外部キーが一致する値を持つ{@@link IpoBookbuildingOrderActionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIpoOrderId( IpoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoOrderId( pk.ipo_order_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_ipoOrderId 検索対象であるp_ipoOrderIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIpoOrderId( long p_ipoOrderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "ipo_order_id=?",
            null,
            new Object[] { new Long(p_ipoOrderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IpoOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByIpoOrderId(IpoOrderRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByIpoOrderId( IpoOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoOrderId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIpoOrderId(IpoOrderRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByIpoOrderId( IpoOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoOrderId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIpoOrderId(IpoOrderPK)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByIpoOrderId( IpoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoOrderId( pk.ipo_order_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIpoOrderId(long)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByIpoOrderId( long p_ipoOrderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoOrderId( p_ipoOrderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for IpoProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByIpoProductId(IpoProductRow)}を使用してください。 
   */
    public static List findRowsByIpoProductId( IpoProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoProductId( dao.getIpoProductRow() );
    }


  /** 
   * {@@link IpoProductRow}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IpoProductRow}オブジェクト 
   * @@return 指定の{@@link IpoProductRow}に外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIpoProductId( IpoProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoProductId( row.getIpoProductId() );
    }


  /** 
   * {@@link IpoProductPK}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IpoProductPK}オブジェクト 
   * @@return {@@link IpoProductPK}と外部キーが一致する値を持つ{@@link IpoBookbuildingOrderActionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIpoProductId( IpoProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoProductId( pk.ipo_product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_ipoProductId 検索対象であるp_ipoProductIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIpoProductId( long p_ipoProductId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "ipo_product_id=?",
            null,
            new Object[] { new Long(p_ipoProductId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IpoProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByIpoProductId(IpoProductRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByIpoProductId( IpoProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIpoProductId(IpoProductRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByIpoProductId( IpoProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIpoProductId(IpoProductPK)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByIpoProductId( IpoProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoProductId( pk.ipo_product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIpoProductId(long)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByIpoProductId( long p_ipoProductId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoProductId( p_ipoProductId ) );
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
   * {@@link BranchRow}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link IpoBookbuildingOrderActionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link IpoBookbuildingOrderActionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link IpoBookbuildingOrderActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link IpoBookbuildingOrderActionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IpoBookbuildingOrderActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * p_bookbuildingOrderActionId, and にて指定の値から一意の{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_bookbuildingOrderActionId 検索対象であるp_bookbuildingOrderActionIdフィールドの値
   * 
   * @@return 引数指定のp_bookbuildingOrderActionId, and の値と一致する{@@link IpoBookbuildingOrderActionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IpoBookbuildingOrderActionRow findRowByBookbuildingOrderActionId( long p_bookbuildingOrderActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "bookbuilding_order_action_id=?",
            null,
            new Object[] { new Long(p_bookbuildingOrderActionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IpoBookbuildingOrderActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IpoBookbuildingOrderActionDao.findRowsByBookbuildingOrderActionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBookbuildingOrderActionId(long)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static IpoBookbuildingOrderActionDao findDaoByBookbuildingOrderActionId( long p_bookbuildingOrderActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBookbuildingOrderActionId( p_bookbuildingOrderActionId ) );
    }


  /** 
   * p_ipoOrderId, p_orderActionSerialNo, and にて指定の値から一意の{@@link IpoBookbuildingOrderActionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_ipoOrderId 検索対象であるp_ipoOrderIdフィールドの値
   * @@param p_orderActionSerialNo 検索対象であるp_orderActionSerialNoフィールドの値
   * 
   * @@return 引数指定のp_ipoOrderId, p_orderActionSerialNo, and の値と一致する{@@link IpoBookbuildingOrderActionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IpoBookbuildingOrderActionRow findRowByIpoOrderIdOrderActionSerialNo( long p_ipoOrderId, int p_orderActionSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "ipo_order_id=? and order_action_serial_no=?",
            null,
            new Object[] { new Long(p_ipoOrderId), new Integer(p_orderActionSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IpoBookbuildingOrderActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IpoBookbuildingOrderActionDao.findRowsByIpoOrderIdOrderActionSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByIpoOrderIdOrderActionSerialNo(long, int)}および{@@link #forRow(IpoBookbuildingOrderActionRow)}を使用してください。 
   */
    public static IpoBookbuildingOrderActionDao findDaoByIpoOrderIdOrderActionSerialNo( long p_ipoOrderId, int p_orderActionSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByIpoOrderIdOrderActionSerialNo( p_ipoOrderId, p_orderActionSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
