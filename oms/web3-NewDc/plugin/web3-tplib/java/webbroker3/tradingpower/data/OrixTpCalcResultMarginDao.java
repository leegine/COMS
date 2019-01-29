head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	OrixTpCalcResultMarginDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpower.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OrixTpCalcResultMarginDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OrixTpCalcResultMarginRow}インスタンスへ関連付けることができます。 
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
 * @@see OrixTpCalcResultMarginPK 
 * @@see OrixTpCalcResultMarginRow 
 */
public class OrixTpCalcResultMarginDao extends DataAccessObject {


  /** 
   * この{@@link OrixTpCalcResultMarginDao}に関連する型指定のRowオブジェクト 
   */
    private OrixTpCalcResultMarginRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OrixTpCalcResultMarginRow}と仮定される{@@link DataAccessObject}から新たに{@@link OrixTpCalcResultMarginDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OrixTpCalcResultMarginDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OrixTpCalcResultMarginRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrixTpCalcResultMarginRow )
                return new OrixTpCalcResultMarginDao( (OrixTpCalcResultMarginRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrixTpCalcResultMarginRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrixTpCalcResultMarginRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OrixTpCalcResultMarginRow}オブジェクト 
    */
    protected OrixTpCalcResultMarginDao( OrixTpCalcResultMarginRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OrixTpCalcResultMarginRow}オブジェクトを取得します。
   */
    public OrixTpCalcResultMarginRow getOrixTpCalcResultMarginRow() {
        return row;
    }


  /** 
   * 指定の{@@link OrixTpCalcResultMarginRow}オブジェクトから{@@link OrixTpCalcResultMarginDao}オブジェクトを作成します。 
   * これは実際の{@@link OrixTpCalcResultMarginRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OrixTpCalcResultMarginDao}取得のために指定の{@@link OrixTpCalcResultMarginRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OrixTpCalcResultMarginDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OrixTpCalcResultMarginDao forRow( OrixTpCalcResultMarginRow row ) throws java.lang.IllegalArgumentException {
        return (OrixTpCalcResultMarginDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrixTpCalcResultMarginRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OrixTpCalcResultMarginRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OrixTpCalcResultMarginPK}やデータベースレコードとして挿入される{@@link OrixTpCalcResultMarginParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrixTpCalcResultMarginRow.TYPE );
    }


  /** 
   * {@@link OrixTpCalcResultMarginRow}を一意に特定する{@@link OrixTpCalcResultMarginPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OrixTpCalcResultMarginRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OrixTpCalcResultMarginParams}オブジェクトの主キーとして利用可能な{@@link OrixTpCalcResultMarginPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OrixTpCalcResultMarginPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new OrixTpCalcResultMarginPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OrixTpCalcResultMarginRow}オブジェクトを検索します。 
   * 
   * @@param p_calcResultMarginId 検索対象であるp_calcResultMarginIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrixTpCalcResultMarginRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrixTpCalcResultMarginRow findRowByPk( long p_calcResultMarginId ) throws DataFindException, DataQueryException, DataNetworkException {
        OrixTpCalcResultMarginPK pk = new OrixTpCalcResultMarginPK( p_calcResultMarginId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOrixTpCalcResultMarginPKオブジェクトから{@@link OrixTpCalcResultMarginRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOrixTpCalcResultMarginPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrixTpCalcResultMarginRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrixTpCalcResultMarginRow findRowByPk( OrixTpCalcResultMarginPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrixTpCalcResultMarginRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(OrixTpCalcResultMarginRow)}を使用してください。 
   */
    public static OrixTpCalcResultMarginDao findDaoByPk( long p_calcResultMarginId ) throws DataFindException, DataQueryException, DataNetworkException {
        OrixTpCalcResultMarginPK pk = new OrixTpCalcResultMarginPK( p_calcResultMarginId );
        OrixTpCalcResultMarginRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OrixTpCalcResultMarginPK)}および{@@link #forRow(OrixTpCalcResultMarginRow)}を使用してください。 
   */
    public static OrixTpCalcResultMarginDao findDaoByPk( OrixTpCalcResultMarginPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrixTpCalcResultMarginRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link OrixTpCalcResultMarginDao}に紐付く{@@link OrixTpCalcResultMarginRow}内で外部キーの関係をもつ{@@link MainAccountRow}を検索します。 
   * 
   * @@return {@@link OrixTpCalcResultMarginDao}と外部キーの関係にある{@@link MainAccountRow} 
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
   * @@deprecated 代わりに{@@link #fetchMainAccountRowViaAccountId()}および{@@link #forRow(OrixTpCalcResultMarginRow)}を使用してください。 
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
    // Find Rows given foreign key values for MainAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAccountId(MainAccountRow)}を使用してください。 
   */
    public static List findRowsByAccountId( MainAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( dao.getMainAccountRow() );
    }


  /** 
   * {@@link MainAccountRow}と外部キーの関係にある{@@link OrixTpCalcResultMarginRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link MainAccountRow}オブジェクト 
   * @@return 指定の{@@link MainAccountRow}に外部キーを持つ{@@link OrixTpCalcResultMarginRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}と外部キーの関係にある{@@link OrixTpCalcResultMarginRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link MainAccountPK}オブジェクト 
   * @@return {@@link MainAccountPK}と外部キーが一致する値を持つ{@@link OrixTpCalcResultMarginRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link OrixTpCalcResultMarginRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link OrixTpCalcResultMarginRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            OrixTpCalcResultMarginRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(OrixTpCalcResultMarginRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountRow)}および{@@link #forRow(OrixTpCalcResultMarginRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(MainAccountPK)}および{@@link #forRow(OrixTpCalcResultMarginRow)}を使用してください。 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(OrixTpCalcResultMarginRow)}を使用してください。 
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountCode, and にて指定の値に一致する{@@link OrixTpCalcResultMarginRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_accountCode, and の値と一致する{@@link OrixTpCalcResultMarginRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountCode( String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            OrixTpCalcResultMarginRow.TYPE,
            "account_code=?",
            null,
            new Object[] { p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountCode(String)}および{@@link #forRow(OrixTpCalcResultMarginRow)}を使用してください。 
   */
    public static List findDaosByAccountCode( String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountCode( p_accountCode ) );
    }

}
@
