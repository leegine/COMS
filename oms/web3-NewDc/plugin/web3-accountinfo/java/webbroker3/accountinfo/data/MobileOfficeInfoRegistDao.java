head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	MobileOfficeInfoRegistDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MobileOfficeInfoRegistDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MobileOfficeInfoRegistRow}インスタンスへ関連付けることができます。 
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
 * @@see MobileOfficeInfoRegistPK 
 * @@see MobileOfficeInfoRegistRow 
 */
public class MobileOfficeInfoRegistDao extends DataAccessObject {


  /** 
   * この{@@link MobileOfficeInfoRegistDao}に関連する型指定のRowオブジェクト 
   */
    private MobileOfficeInfoRegistRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MobileOfficeInfoRegistRow}と仮定される{@@link DataAccessObject}から新たに{@@link MobileOfficeInfoRegistDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MobileOfficeInfoRegistDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MobileOfficeInfoRegistRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MobileOfficeInfoRegistRow )
                return new MobileOfficeInfoRegistDao( (MobileOfficeInfoRegistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MobileOfficeInfoRegistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MobileOfficeInfoRegistRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MobileOfficeInfoRegistRow}オブジェクト 
    */
    protected MobileOfficeInfoRegistDao( MobileOfficeInfoRegistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MobileOfficeInfoRegistRow}オブジェクトを取得します。
   */
    public MobileOfficeInfoRegistRow getMobileOfficeInfoRegistRow() {
        return row;
    }


  /** 
   * 指定の{@@link MobileOfficeInfoRegistRow}オブジェクトから{@@link MobileOfficeInfoRegistDao}オブジェクトを作成します。 
   * これは実際の{@@link MobileOfficeInfoRegistRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MobileOfficeInfoRegistDao}取得のために指定の{@@link MobileOfficeInfoRegistRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MobileOfficeInfoRegistDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MobileOfficeInfoRegistDao forRow( MobileOfficeInfoRegistRow row ) throws java.lang.IllegalArgumentException {
        return (MobileOfficeInfoRegistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MobileOfficeInfoRegistRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MobileOfficeInfoRegistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MobileOfficeInfoRegistPK}やデータベースレコードとして挿入される{@@link MobileOfficeInfoRegistParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MobileOfficeInfoRegistRow.TYPE );
    }


  /** 
   * {@@link MobileOfficeInfoRegistRow}を一意に特定する{@@link MobileOfficeInfoRegistPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MobileOfficeInfoRegistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MobileOfficeInfoRegistParams}オブジェクトの主キーとして利用可能な{@@link MobileOfficeInfoRegistPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MobileOfficeInfoRegistPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new MobileOfficeInfoRegistPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MobileOfficeInfoRegistRow}オブジェクトを検索します。 
   * 
   * @@param p_mobileOfficeInfoRegistId 検索対象であるp_mobileOfficeInfoRegistIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MobileOfficeInfoRegistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MobileOfficeInfoRegistRow findRowByPk( long p_mobileOfficeInfoRegistId ) throws DataFindException, DataQueryException, DataNetworkException {
        MobileOfficeInfoRegistPK pk = new MobileOfficeInfoRegistPK( p_mobileOfficeInfoRegistId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMobileOfficeInfoRegistPKオブジェクトから{@@link MobileOfficeInfoRegistRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMobileOfficeInfoRegistPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MobileOfficeInfoRegistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MobileOfficeInfoRegistRow findRowByPk( MobileOfficeInfoRegistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MobileOfficeInfoRegistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static MobileOfficeInfoRegistDao findDaoByPk( long p_mobileOfficeInfoRegistId ) throws DataFindException, DataQueryException, DataNetworkException {
        MobileOfficeInfoRegistPK pk = new MobileOfficeInfoRegistPK( p_mobileOfficeInfoRegistId );
        MobileOfficeInfoRegistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MobileOfficeInfoRegistPK)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static MobileOfficeInfoRegistDao findDaoByPk( MobileOfficeInfoRegistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MobileOfficeInfoRegistRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link MobileOfficeInfoRegistDao}に紐付く{@@link MobileOfficeInfoRegistRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link MobileOfficeInfoRegistDao}と外部キーの関係にある{@@link BranchRow} 
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
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * この{@@link MobileOfficeInfoRegistDao}に紐付く{@@link MobileOfficeInfoRegistRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link MobileOfficeInfoRegistDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
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
    // Find Rows given foreign key values for Branch
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByBranchId(BranchRow)}を使用してください。 
   */
    public static List findRowsByBranchId( BranchDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( dao.getBranchRow() );
    }


  /** 
   * {@@link BranchRow}と外部キーの関係にある{@@link MobileOfficeInfoRegistRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link MobileOfficeInfoRegistRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link MobileOfficeInfoRegistRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link MobileOfficeInfoRegistRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MobileOfficeInfoRegistRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MobileOfficeInfoRegistRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MobileOfficeInfoRegistRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link MobileOfficeInfoRegistRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link MobileOfficeInfoRegistRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link MobileOfficeInfoRegistRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link MobileOfficeInfoRegistRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link MobileOfficeInfoRegistRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link MobileOfficeInfoRegistRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MobileOfficeInfoRegistRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
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
   * p_mobileOfficeInfoRegistId, and にて指定の値から一意の{@@link MobileOfficeInfoRegistRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_mobileOfficeInfoRegistId 検索対象であるp_mobileOfficeInfoRegistIdフィールドの値
   * 
   * @@return 引数指定のp_mobileOfficeInfoRegistId, and の値と一致する{@@link MobileOfficeInfoRegistRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MobileOfficeInfoRegistRow findRowByMobileOfficeInfoRegistId( long p_mobileOfficeInfoRegistId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MobileOfficeInfoRegistRow.TYPE,
            "mobile_office_info_regist_id=?",
            null,
            new Object[] { new Long(p_mobileOfficeInfoRegistId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MobileOfficeInfoRegistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MobileOfficeInfoRegistDao.findRowsByMobileOfficeInfoRegistId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMobileOfficeInfoRegistId(long)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static MobileOfficeInfoRegistDao findDaoByMobileOfficeInfoRegistId( long p_mobileOfficeInfoRegistId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMobileOfficeInfoRegistId( p_mobileOfficeInfoRegistId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchId, p_accountId, and にて指定の値に一致する{@@link MobileOfficeInfoRegistRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchId, p_accountId, and の値と一致する{@@link MobileOfficeInfoRegistRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchIdAccountId( String p_institutionCode, long p_branchId, long p_accountId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MobileOfficeInfoRegistRow.TYPE,
            "institution_code=? and branch_id=? and account_id=?",
            null,
            new Object[] { p_institutionCode, new Long(p_branchId), new Long(p_accountId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchIdAccountId(String, long, long)}および{@@link #forRow(MobileOfficeInfoRegistRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchIdAccountId( String p_institutionCode, long p_branchId, long p_accountId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchIdAccountId( p_institutionCode, p_branchId, p_accountId ) );
    }

}
@
