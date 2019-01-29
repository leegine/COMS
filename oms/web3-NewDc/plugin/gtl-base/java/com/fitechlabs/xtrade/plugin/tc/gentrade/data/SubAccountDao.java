head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	SubAccountDao.java;


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
 * {@@link SubAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SubAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see SubAccountPK 
 * @@see SubAccountRow 
 */
public class SubAccountDao extends DataAccessObject {


  /** 
   * この{@@link SubAccountDao}に関連する型指定のRowオブジェクト 
   */
    private SubAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SubAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link SubAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SubAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SubAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SubAccountRow )
                return new SubAccountDao( (SubAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SubAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SubAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SubAccountRow}オブジェクト 
    */
    protected SubAccountDao( SubAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SubAccountRow}オブジェクトを取得します。
   */
    public SubAccountRow getSubAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link SubAccountRow}オブジェクトから{@@link SubAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link SubAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SubAccountDao}取得のために指定の{@@link SubAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SubAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SubAccountDao forRow( SubAccountRow row ) throws java.lang.IllegalArgumentException {
        return (SubAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SubAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SubAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SubAccountPK}やデータベースレコードとして挿入される{@@link SubAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SubAccountRow.TYPE );
    }


  /** 
   * {@@link SubAccountRow}を一意に特定する{@@link SubAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SubAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SubAccountParams}オブジェクトの主キーとして利用可能な{@@link SubAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SubAccountPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SubAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SubAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SubAccountRow findRowByPk( long p_accountId, long p_subAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        SubAccountPK pk = new SubAccountPK( p_accountId, p_subAccountId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSubAccountPKオブジェクトから{@@link SubAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSubAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SubAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SubAccountRow findRowByPk( SubAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SubAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,long)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static SubAccountDao findDaoByPk( long p_accountId, long p_subAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        SubAccountPK pk = new SubAccountPK( p_accountId, p_subAccountId );
        SubAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SubAccountPK)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static SubAccountDao findDaoByPk( SubAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SubAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link SubAccountDao}に紐付く{@@link SubAccountRow}内で外部キーの関係をもつ{@@link InstitutionRow}を検索します。 
   * 
   * @@return {@@link SubAccountDao}と外部キーの関係にある{@@link InstitutionRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public InstitutionRow fetchInstitutionRowViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        Row row = InstitutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof InstitutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (InstitutionRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchInstitutionRowViaInstitutionId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public InstitutionDao fetchInstitutionDaoViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        DataAccessObject dao = InstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof InstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (InstitutionDao) dao;
    }


  /** 
   * この{@@link SubAccountDao}に紐付く{@@link SubAccountRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link SubAccountDao}と外部キーの関係にある{@@link BranchRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public BranchRow fetchBranchRowViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getBranchIdIsNull() )
            return null;
        BranchPK pk = new BranchPK( row.getBranchId() );
        Row row = BranchDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BranchRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BranchRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getBranchIdIsNull() )
            return null;
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * この{@@link SubAccountDao}に紐付く{@@link SubAccountRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link SubAccountDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link AssetUnitRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link AssetUnitRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchAssetUnitRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetUnitDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetUnitRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchAssetUnitDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetUnitDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetUnitRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchAssetUnitDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchAssetUnitDaosByAccountIdSubAccountId();
    }


  /** 
   * この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link SubAccountPreferencesRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link SubAccountPreferencesRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchSubAccountPreferencesRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return SubAccountPreferencesDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchSubAccountPreferencesRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchSubAccountPreferencesDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return SubAccountPreferencesDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchSubAccountPreferencesRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchSubAccountPreferencesDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchSubAccountPreferencesDaosByAccountIdSubAccountId();
    }


  /** 
   * この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link AssetUnitSalesRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link AssetUnitSalesRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchAssetUnitSalesRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetUnitSalesDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetUnitSalesRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchAssetUnitSalesDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetUnitSalesDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetUnitSalesRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchAssetUnitSalesDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchAssetUnitSalesDaosByAccountIdSubAccountId();
    }


  /** 
   * この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link LockedAssetDetailsRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link LockedAssetDetailsRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchLockedAssetDetailsRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return LockedAssetDetailsDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchLockedAssetDetailsRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchLockedAssetDetailsDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return LockedAssetDetailsDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchLockedAssetDetailsRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchLockedAssetDetailsDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchLockedAssetDetailsDaosByAccountIdSubAccountId();
    }


  /** 
   * この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link AssetRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link AssetRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchAssetRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchAssetDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchAssetRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchAssetDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchAssetDaosByAccountIdSubAccountId();
    }


  /** 
   * この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link GenFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link SubAccountDao}に関連する{@@link SubAccountRow}の外部キーがある{@@link GenFinTransactionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchGenFinTransactionRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return GenFinTransactionDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchGenFinTransactionRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchGenFinTransactionDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return GenFinTransactionDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchGenFinTransactionRowsByAccountIdSubAccountId()}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public List fetchGenFinTransactionDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchGenFinTransactionDaosByAccountIdSubAccountId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Institution
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByInstitutionId(InstitutionRow)}を使用してください。 
   */
    public static List findRowsByInstitutionId( InstitutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( dao.getInstitutionRow() );
    }


  /** 
   * {@@link InstitutionRow}と外部キーの関係にある{@@link SubAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link InstitutionRow}オブジェクト 
   * @@return 指定の{@@link InstitutionRow}に外部キーを持つ{@@link SubAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( row.getInstitutionId() );
    }


  /** 
   * {@@link InstitutionPK}と外部キーの関係にある{@@link SubAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link InstitutionPK}オブジェクト 
   * @@return {@@link InstitutionPK}と外部キーが一致する値を持つ{@@link SubAccountRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( pk.institution_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link SubAccountRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link SubAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( long p_institutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Institution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionPK)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( pk.institution_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(long)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( long p_institutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( p_institutionId ) );
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
   * {@@link BranchRow}と外部キーの関係にある{@@link SubAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link SubAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link SubAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link SubAccountRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link SubAccountRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link SubAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(SubAccountRow)}を使用してください。 
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link SubAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link SubAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link SubAccountRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link SubAccountRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link SubAccountRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link SubAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(SubAccountRow)}を使用してください。 
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
   * p_accountId, p_subAccountId, and にて指定の値から一意の{@@link SubAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, and の値と一致する{@@link SubAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SubAccountRow findRowByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SubAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SubAccountDao.findRowsByAccountIdSubAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdSubAccountId(long, long)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static SubAccountDao findDaoByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


  /** 
   * p_accountId, p_subAccountType, and にて指定の値から一意の{@@link SubAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountType 検索対象であるp_subAccountTypeフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountType, and の値と一致する{@@link SubAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SubAccountRow findRowByAccountIdSubAccountType( long p_accountId, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "account_id=? and sub_account_type=?",
            null,
            new Object[] { new Long(p_accountId), p_subAccountType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SubAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SubAccountDao.findRowsByAccountIdSubAccountType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdSubAccountType(long, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static SubAccountDao findDaoByAccountIdSubAccountType( long p_accountId, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountType( p_accountId, p_subAccountType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, p_subAccountType, p_institutionCode, p_branchId, and にて指定の値に一致する{@@link SubAccountRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_subAccountType 検索対象であるp_subAccountTypeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_subAccountType, p_institutionCode, p_branchId, and の値と一致する{@@link SubAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountIdSubAccountTypeInstitutionCodeBranchId( long p_accountId, long p_subAccountId, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType, String p_institutionCode, Long p_branchId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "account_id=? and sub_account_id=? and sub_account_type=? and institution_code=? and branch_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), p_subAccountType, p_institutionCode, p_branchId } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountIdSubAccountTypeInstitutionCodeBranchId(long, long, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum, String, Long)}および{@@link #forRow(SubAccountRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountIdSubAccountTypeInstitutionCodeBranchId( long p_accountId, long p_subAccountId, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType, String p_institutionCode, Long p_branchId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdSubAccountIdSubAccountTypeInstitutionCodeBranchId( p_accountId, p_subAccountId, p_subAccountType, p_institutionCode, p_branchId ) );
    }

}
@
