head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.38.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	LockedAssetDetailsDao.java;


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
 * {@@link LockedAssetDetailsDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link LockedAssetDetailsRow}インスタンスへ関連付けることができます。 
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
 * @@see LockedAssetDetailsPK 
 * @@see LockedAssetDetailsRow 
 */
public class LockedAssetDetailsDao extends DataAccessObject {


  /** 
   * この{@@link LockedAssetDetailsDao}に関連する型指定のRowオブジェクト 
   */
    private LockedAssetDetailsRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link LockedAssetDetailsRow}と仮定される{@@link DataAccessObject}から新たに{@@link LockedAssetDetailsDao}を返します。 
         * @@return 指定のRowに結びつく{@@link LockedAssetDetailsDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link LockedAssetDetailsRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof LockedAssetDetailsRow )
                return new LockedAssetDetailsDao( (LockedAssetDetailsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a LockedAssetDetailsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link LockedAssetDetailsRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link LockedAssetDetailsRow}オブジェクト 
    */
    protected LockedAssetDetailsDao( LockedAssetDetailsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link LockedAssetDetailsRow}オブジェクトを取得します。
   */
    public LockedAssetDetailsRow getLockedAssetDetailsRow() {
        return row;
    }


  /** 
   * 指定の{@@link LockedAssetDetailsRow}オブジェクトから{@@link LockedAssetDetailsDao}オブジェクトを作成します。 
   * これは実際の{@@link LockedAssetDetailsRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link LockedAssetDetailsDao}取得のために指定の{@@link LockedAssetDetailsRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link LockedAssetDetailsDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static LockedAssetDetailsDao forRow( LockedAssetDetailsRow row ) throws java.lang.IllegalArgumentException {
        return (LockedAssetDetailsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link LockedAssetDetailsRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link LockedAssetDetailsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link LockedAssetDetailsPK}やデータベースレコードとして挿入される{@@link LockedAssetDetailsParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( LockedAssetDetailsRow.TYPE );
    }


  /** 
   * {@@link LockedAssetDetailsRow}を一意に特定する{@@link LockedAssetDetailsPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link LockedAssetDetailsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link LockedAssetDetailsParams}オブジェクトの主キーとして利用可能な{@@link LockedAssetDetailsPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static LockedAssetDetailsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new LockedAssetDetailsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link LockedAssetDetailsRow}オブジェクトを検索します。 
   * 
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link LockedAssetDetailsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static LockedAssetDetailsRow findRowByPk( long p_assetId ) throws DataFindException, DataQueryException, DataNetworkException {
        LockedAssetDetailsPK pk = new LockedAssetDetailsPK( p_assetId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のLockedAssetDetailsPKオブジェクトから{@@link LockedAssetDetailsRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するLockedAssetDetailsPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link LockedAssetDetailsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static LockedAssetDetailsRow findRowByPk( LockedAssetDetailsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (LockedAssetDetailsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static LockedAssetDetailsDao findDaoByPk( long p_assetId ) throws DataFindException, DataQueryException, DataNetworkException {
        LockedAssetDetailsPK pk = new LockedAssetDetailsPK( p_assetId );
        LockedAssetDetailsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(LockedAssetDetailsPK)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static LockedAssetDetailsDao findDaoByPk( LockedAssetDetailsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        LockedAssetDetailsRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link LockedAssetDetailsDao}に紐付く{@@link LockedAssetDetailsRow}内で外部キーの関係をもつ{@@link AssetRow}を検索します。 
   * 
   * @@return {@@link LockedAssetDetailsDao}と外部キーの関係にある{@@link AssetRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public AssetRow fetchAssetRowViaAssetId() throws DataNetworkException, DataFindException, DataQueryException  {
        AssetPK pk = new AssetPK( row.getAssetId() );
        Row row = AssetDao.findRowByPk( pk );
        if ( row != null && !(row instanceof AssetRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (AssetRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetRowViaAssetId()}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public AssetDao fetchAssetDaoViaAssetId() throws DataNetworkException, DataFindException, DataQueryException  {
        AssetPK pk = new AssetPK( row.getAssetId() );
        DataAccessObject dao = AssetDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof AssetDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (AssetDao) dao;
    }


  /** 
   * この{@@link LockedAssetDetailsDao}に紐付く{@@link LockedAssetDetailsRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link LockedAssetDetailsDao}と外部キーの関係にある{@@link SubAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
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
    // Find Rows given foreign key values for Asset
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAssetId(AssetRow)}を使用してください。 
   */
    public static List findRowsByAssetId( AssetDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetId( dao.getAssetRow() );
    }


  /** 
   * {@@link AssetRow}と外部キーの関係にある{@@link LockedAssetDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link AssetRow}オブジェクト 
   * @@return 指定の{@@link AssetRow}に外部キーを持つ{@@link LockedAssetDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( AssetRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetId( row.getAssetId() );
    }


  /** 
   * {@@link AssetPK}と外部キーの関係にある{@@link LockedAssetDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link AssetPK}オブジェクト 
   * @@return {@@link AssetPK}と外部キーが一致する値を持つ{@@link LockedAssetDetailsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( AssetPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAssetId( pk.asset_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link LockedAssetDetailsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link LockedAssetDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetId( long p_assetId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LockedAssetDetailsRow.TYPE,
            "asset_id=?",
            null,
            new Object[] { new Long(p_assetId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Asset
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAssetId(AssetRow)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(AssetRow)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(AssetPK)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static List findDaosByAssetId( AssetPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAssetId( pk.asset_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetId(long)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
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
   * {@@link SubAccountRow}と外部キーの関係にある{@@link LockedAssetDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link LockedAssetDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link LockedAssetDetailsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link LockedAssetDetailsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link LockedAssetDetailsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link LockedAssetDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LockedAssetDetailsRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
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
   * p_assetId, and にて指定の値から一意の{@@link LockedAssetDetailsRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * 
   * @@return 引数指定のp_assetId, and の値と一致する{@@link LockedAssetDetailsRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static LockedAssetDetailsRow findRowByAssetId( long p_assetId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LockedAssetDetailsRow.TYPE,
            "asset_id=?",
            null,
            new Object[] { new Long(p_assetId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LockedAssetDetailsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LockedAssetDetailsDao.findRowsByAssetId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAssetId(long)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static LockedAssetDetailsDao findDaoByAssetId( long p_assetId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAssetId( p_assetId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_assetId, p_accountId, p_subAccountId, and にて指定の値に一致する{@@link LockedAssetDetailsRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 引数指定のp_assetId, p_accountId, p_subAccountId, and の値と一致する{@@link LockedAssetDetailsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAssetIdAccountIdSubAccountId( long p_assetId, long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LockedAssetDetailsRow.TYPE,
            "asset_id=? and account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_assetId), new Long(p_accountId), new Long(p_subAccountId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAssetIdAccountIdSubAccountId(long, long, long)}および{@@link #forRow(LockedAssetDetailsRow)}を使用してください。 
   */
    public static List findDaosByAssetIdAccountIdSubAccountId( long p_assetId, long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAssetIdAccountIdSubAccountId( p_assetId, p_accountId, p_subAccountId ) );
    }

}
@
