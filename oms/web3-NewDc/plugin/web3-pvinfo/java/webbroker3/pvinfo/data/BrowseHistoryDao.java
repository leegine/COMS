head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.12.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	BrowseHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.pvinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BrowseHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BrowseHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see BrowseHistoryPK 
 * @@see BrowseHistoryRow 
 */
public class BrowseHistoryDao extends DataAccessObject {


  /** 
   * この{@@link BrowseHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private BrowseHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BrowseHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link BrowseHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BrowseHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BrowseHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BrowseHistoryRow )
                return new BrowseHistoryDao( (BrowseHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BrowseHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BrowseHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BrowseHistoryRow}オブジェクト 
    */
    protected BrowseHistoryDao( BrowseHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BrowseHistoryRow}オブジェクトを取得します。
   */
    public BrowseHistoryRow getBrowseHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link BrowseHistoryRow}オブジェクトから{@@link BrowseHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link BrowseHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BrowseHistoryDao}取得のために指定の{@@link BrowseHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BrowseHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BrowseHistoryDao forRow( BrowseHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (BrowseHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BrowseHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BrowseHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BrowseHistoryPK}やデータベースレコードとして挿入される{@@link BrowseHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BrowseHistoryRow.TYPE );
    }


  /** 
   * {@@link BrowseHistoryRow}を一意に特定する{@@link BrowseHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BrowseHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BrowseHistoryParams}オブジェクトの主キーとして利用可能な{@@link BrowseHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BrowseHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BrowseHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BrowseHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_browseHistoryId 検索対象であるp_browseHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BrowseHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BrowseHistoryRow findRowByPk( long p_browseHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        BrowseHistoryPK pk = new BrowseHistoryPK( p_browseHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBrowseHistoryPKオブジェクトから{@@link BrowseHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBrowseHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BrowseHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BrowseHistoryRow findRowByPk( BrowseHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BrowseHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static BrowseHistoryDao findDaoByPk( long p_browseHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        BrowseHistoryPK pk = new BrowseHistoryPK( p_browseHistoryId );
        BrowseHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BrowseHistoryPK)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static BrowseHistoryDao findDaoByPk( BrowseHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BrowseHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link BrowseHistoryDao}に紐付く{@@link BrowseHistoryRow}内で外部キーの関係をもつ{@@link DisplayContentsRow}を検索します。 
   * 
   * @@return {@@link BrowseHistoryDao}と外部キーの関係にある{@@link DisplayContentsRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public DisplayContentsRow fetchDisplayContentsRowViaDisplayContentsId() throws DataNetworkException, DataFindException, DataQueryException  {
        DisplayContentsPK pk = new DisplayContentsPK( row.getDisplayContentsId() );
        Row row = DisplayContentsDao.findRowByPk( pk );
        if ( row != null && !(row instanceof DisplayContentsRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (DisplayContentsRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchDisplayContentsRowViaDisplayContentsId()}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public DisplayContentsDao fetchDisplayContentsDaoViaDisplayContentsId() throws DataNetworkException, DataFindException, DataQueryException  {
        DisplayContentsPK pk = new DisplayContentsPK( row.getDisplayContentsId() );
        DataAccessObject dao = DisplayContentsDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof DisplayContentsDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (DisplayContentsDao) dao;
    }


  /** 
   * この{@@link BrowseHistoryDao}に紐付く{@@link BrowseHistoryRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link BrowseHistoryDao}と外部キーの関係にある{@@link MainAccountRow}または外部キーIDの値が現在nullに設定の場合はnull 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */	
    public MainAccountRow fetchMainAccountRowViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getAccountIdIsNull() )
            return null;
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        Row row = MainAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MainAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MainAccountRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getAccountIdIsNull() )
            return null;
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
    // Find Rows given foreign key values for DisplayContents
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByDisplayContentsId(DisplayContentsRow)}を使用してください。 
   */
    public static List findRowsByDisplayContentsId( DisplayContentsDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByDisplayContentsId( dao.getDisplayContentsRow() );
    }


  /** 
   * {@@link DisplayContentsRow}と外部キーの関係にある{@@link BrowseHistoryRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link DisplayContentsRow}オブジェクト 
   * @@return 指定の{@@link DisplayContentsRow}に外部キーを持つ{@@link BrowseHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByDisplayContentsId( DisplayContentsRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByDisplayContentsId( row.getDisplayContentsId() );
    }


  /** 
   * {@@link DisplayContentsPK}と外部キーの関係にある{@@link BrowseHistoryRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link DisplayContentsPK}オブジェクト 
   * @@return {@@link DisplayContentsPK}と外部キーが一致する値を持つ{@@link BrowseHistoryRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByDisplayContentsId( DisplayContentsPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByDisplayContentsId( pk.display_contents_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BrowseHistoryRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_displayContentsId 検索対象であるp_displayContentsIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BrowseHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByDisplayContentsId( long p_displayContentsId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "display_contents_id=?",
            null,
            new Object[] { new Long(p_displayContentsId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for DisplayContents
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByDisplayContentsId(DisplayContentsRow)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static List findDaosByDisplayContentsId( DisplayContentsDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByDisplayContentsId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByDisplayContentsId(DisplayContentsRow)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static List findDaosByDisplayContentsId( DisplayContentsRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByDisplayContentsId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByDisplayContentsId(DisplayContentsPK)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static List findDaosByDisplayContentsId( DisplayContentsPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByDisplayContentsId( pk.display_contents_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByDisplayContentsId(long)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static List findDaosByDisplayContentsId( long p_displayContentsId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByDisplayContentsId( p_displayContentsId ) );
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
   * {@@link MainAccountRow}と外部キーの関係にある{@@link BrowseHistoryRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link BrowseHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link BrowseHistoryRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link BrowseHistoryRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BrowseHistoryRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BrowseHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
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
   * p_browseHistoryId, and にて指定の値から一意の{@@link BrowseHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_browseHistoryId 検索対象であるp_browseHistoryIdフィールドの値
   * 
   * @@return 引数指定のp_browseHistoryId, and の値と一致する{@@link BrowseHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BrowseHistoryRow findRowByBrowseHistoryId( long p_browseHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "browse_history_id=?",
            null,
            new Object[] { new Long(p_browseHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BrowseHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BrowseHistoryDao.findRowsByBrowseHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBrowseHistoryId(long)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static BrowseHistoryDao findDaoByBrowseHistoryId( long p_browseHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBrowseHistoryId( p_browseHistoryId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_displayContentsId, p_accountCode, and にて指定の値から一意の{@@link BrowseHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_displayContentsId 検索対象であるp_displayContentsIdフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_displayContentsId, p_accountCode, and の値と一致する{@@link BrowseHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BrowseHistoryRow findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode( String p_institutionCode, String p_branchCode, long p_displayContentsId, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "institution_code=? and branch_code=? and display_contents_id=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, new Long(p_displayContentsId), p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BrowseHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BrowseHistoryDao.findRowsByInstitutionCodeBranchCodeDisplayContentsIdAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode(String, String, long, String)}および{@@link #forRow(BrowseHistoryRow)}を使用してください。 
   */
    public static BrowseHistoryDao findDaoByInstitutionCodeBranchCodeDisplayContentsIdAccountCode( String p_institutionCode, String p_branchCode, long p_displayContentsId, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode( p_institutionCode, p_branchCode, p_displayContentsId, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
