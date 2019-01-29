head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.37.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AssetUnitSalesDao.java;


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
 * {@@link AssetUnitSalesDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AssetUnitSalesRow}インスタンスへ関連付けることができます。 
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
 * @@see AssetUnitSalesPK 
 * @@see AssetUnitSalesRow 
 */
public class AssetUnitSalesDao extends DataAccessObject {


  /** 
   * この{@@link AssetUnitSalesDao}に関連する型指定のRowオブジェクト 
   */
    private AssetUnitSalesRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AssetUnitSalesRow}と仮定される{@@link DataAccessObject}から新たに{@@link AssetUnitSalesDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AssetUnitSalesDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AssetUnitSalesRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AssetUnitSalesRow )
                return new AssetUnitSalesDao( (AssetUnitSalesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AssetUnitSalesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AssetUnitSalesRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AssetUnitSalesRow}オブジェクト 
    */
    protected AssetUnitSalesDao( AssetUnitSalesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AssetUnitSalesRow}オブジェクトを取得します。
   */
    public AssetUnitSalesRow getAssetUnitSalesRow() {
        return row;
    }


  /** 
   * 指定の{@@link AssetUnitSalesRow}オブジェクトから{@@link AssetUnitSalesDao}オブジェクトを作成します。 
   * これは実際の{@@link AssetUnitSalesRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AssetUnitSalesDao}取得のために指定の{@@link AssetUnitSalesRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AssetUnitSalesDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AssetUnitSalesDao forRow( AssetUnitSalesRow row ) throws java.lang.IllegalArgumentException {
        return (AssetUnitSalesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AssetUnitSalesRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AssetUnitSalesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AssetUnitSalesPK}やデータベースレコードとして挿入される{@@link AssetUnitSalesParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AssetUnitSalesRow.TYPE );
    }


  /** 
   * {@@link AssetUnitSalesRow}を一意に特定する{@@link AssetUnitSalesPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AssetUnitSalesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AssetUnitSalesParams}オブジェクトの主キーとして利用可能な{@@link AssetUnitSalesPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AssetUnitSalesPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AssetUnitSalesPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AssetUnitSalesRow}オブジェクトを検索します。 
   * 
   * @@param p_assetUnitSalesId 検索対象であるp_assetUnitSalesIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AssetUnitSalesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AssetUnitSalesRow findRowByPk( long p_assetUnitSalesId ) throws DataFindException, DataQueryException, DataNetworkException {
        AssetUnitSalesPK pk = new AssetUnitSalesPK( p_assetUnitSalesId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAssetUnitSalesPKオブジェクトから{@@link AssetUnitSalesRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAssetUnitSalesPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AssetUnitSalesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AssetUnitSalesRow findRowByPk( AssetUnitSalesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AssetUnitSalesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static AssetUnitSalesDao findDaoByPk( long p_assetUnitSalesId ) throws DataFindException, DataQueryException, DataNetworkException {
        AssetUnitSalesPK pk = new AssetUnitSalesPK( p_assetUnitSalesId );
        AssetUnitSalesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AssetUnitSalesPK)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static AssetUnitSalesDao findDaoByPk( AssetUnitSalesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AssetUnitSalesRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link AssetUnitSalesDao}に紐付く{@@link AssetUnitSalesRow}内で外部キーの関係をもつ{@@link AssetUnitRow}を検索します。 
   * 
   * @@return {@@link AssetUnitSalesDao}と外部キーの関係にある{@@link AssetUnitRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public AssetUnitRow fetchAssetUnitRowViaAssetUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        AssetUnitPK pk = new AssetUnitPK( row.getAssetUnitId() );
        Row row = AssetUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof AssetUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (AssetUnitRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetUnitRowViaAssetUnitId()}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public AssetUnitDao fetchAssetUnitDaoViaAssetUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        AssetUnitPK pk = new AssetUnitPK( row.getAssetUnitId() );
        DataAccessObject dao = AssetUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof AssetUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (AssetUnitDao) dao;
    }


  /** 
   * この{@@link AssetUnitSalesDao}に紐付く{@@link AssetUnitSalesRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link AssetUnitSalesDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * この{@@link AssetUnitSalesDao}に紐付く{@@link AssetUnitSalesRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link AssetUnitSalesDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
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
    // Find Rows given foreign key values for AssetUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAssetUnitId(AssetUnitRow)}を使用してください。 
   */
    public static List findRowsByAssetUnitId( AssetUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetUnitId( dao.getAssetUnitRow() );
    }


  /** 
   * {@@link AssetUnitRow}と外部キーの関係にある{@@link AssetUnitSalesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link AssetUnitRow}オブジェクト 
   * @@return 指定の{@@link AssetUnitRow}に外部キーを持つ{@@link AssetUnitSalesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetUnitId( AssetUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetUnitId( row.getAssetUnitId() );
    }


  /** 
   * {@@link AssetUnitPK}と外部キーの関係にある{@@link AssetUnitSalesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link AssetUnitPK}オブジェクト 
   * @@return {@@link AssetUnitPK}と外部キーが一致する値を持つ{@@link AssetUnitSalesRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetUnitId( AssetUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetUnitId( pk.asset_unit_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link AssetUnitSalesRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_assetUnitId 検索対象であるp_assetUnitIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link AssetUnitSalesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetUnitId( long p_assetUnitId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AssetUnitSalesRow.TYPE,
            "asset_unit_id=?",
            null,
            new Object[] { new Long(p_assetUnitId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for AssetUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAssetUnitId(AssetUnitRow)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAssetUnitId( AssetUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetUnitId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetUnitId(AssetUnitRow)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAssetUnitId( AssetUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetUnitId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetUnitId(AssetUnitPK)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAssetUnitId( AssetUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetUnitId( pk.asset_unit_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetUnitId(long)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAssetUnitId( long p_assetUnitId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetUnitId( p_assetUnitId ) );
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link AssetUnitSalesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link AssetUnitSalesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link AssetUnitSalesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link AssetUnitSalesRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link AssetUnitSalesRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link AssetUnitSalesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AssetUnitSalesRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link AssetUnitSalesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link AssetUnitSalesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link AssetUnitSalesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link AssetUnitSalesRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link AssetUnitSalesRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link AssetUnitSalesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AssetUnitSalesRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
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
   * p_assetUnitSalesId, and にて指定の値から一意の{@@link AssetUnitSalesRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_assetUnitSalesId 検索対象であるp_assetUnitSalesIdフィールドの値
   * 
   * @@return 引数指定のp_assetUnitSalesId, and の値と一致する{@@link AssetUnitSalesRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AssetUnitSalesRow findRowByAssetUnitSalesId( long p_assetUnitSalesId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AssetUnitSalesRow.TYPE,
            "asset_unit_sales_id=?",
            null,
            new Object[] { new Long(p_assetUnitSalesId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AssetUnitSalesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AssetUnitSalesDao.findRowsByAssetUnitSalesId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAssetUnitSalesId(long)}および{@@link #forRow(AssetUnitSalesRow)}を使用してください。 
   */
    public static AssetUnitSalesDao findDaoByAssetUnitSalesId( long p_assetUnitSalesId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAssetUnitSalesId( p_assetUnitSalesId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
