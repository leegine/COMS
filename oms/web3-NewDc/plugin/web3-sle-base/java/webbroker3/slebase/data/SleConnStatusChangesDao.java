head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnStatusChangesDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.slebase.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * {@@link SleConnStatusChangesDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SleConnStatusChangesRow}インスタンスへ関連付けることができます。 
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
 * @@see SleConnStatusChangesPK 
 * @@see SleConnStatusChangesRow 
 */
public class SleConnStatusChangesDao extends DataAccessObject {


  /** 
   * この{@@link SleConnStatusChangesDao}に関連する型指定のRowオブジェクト 
   */
    private SleConnStatusChangesRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SleConnStatusChangesRow}と仮定される{@@link DataAccessObject}から新たに{@@link SleConnStatusChangesDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SleConnStatusChangesDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SleConnStatusChangesRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleConnStatusChangesRow )
                return new SleConnStatusChangesDao( (SleConnStatusChangesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleConnStatusChangesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleConnStatusChangesRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SleConnStatusChangesRow}オブジェクト 
    */
    protected SleConnStatusChangesDao( SleConnStatusChangesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SleConnStatusChangesRow}オブジェクトを取得します。
   */
    public SleConnStatusChangesRow getSleConnStatusChangesRow() {
        return row;
    }


  /** 
   * 指定の{@@link SleConnStatusChangesRow}オブジェクトから{@@link SleConnStatusChangesDao}オブジェクトを作成します。 
   * これは実際の{@@link SleConnStatusChangesRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SleConnStatusChangesDao}取得のために指定の{@@link SleConnStatusChangesRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SleConnStatusChangesDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SleConnStatusChangesDao forRow( SleConnStatusChangesRow row ) throws java.lang.IllegalArgumentException {
        return (SleConnStatusChangesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleConnStatusChangesRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SleConnStatusChangesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SleConnStatusChangesPK}やデータベースレコードとして挿入される{@@link SleConnStatusChangesParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleConnStatusChangesRow.TYPE );
    }


  /** 
   * {@@link SleConnStatusChangesRow}を一意に特定する{@@link SleConnStatusChangesPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SleConnStatusChangesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SleConnStatusChangesParams}オブジェクトの主キーとして利用可能な{@@link SleConnStatusChangesPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のコラムが含まれているかコラムのタイプがlong型でない場合 
   */
    public static SleConnStatusChangesPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SleConnStatusChangesPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SleConnStatusChangesRow}オブジェクトを検索します。 
   * 
   * @@param p_queId 検索対象であるp_queIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleConnStatusChangesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleConnStatusChangesRow findRowByPk( long p_queId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleConnStatusChangesPK pk = new SleConnStatusChangesPK( p_queId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSleConnStatusChangesPKオブジェクトから{@@link SleConnStatusChangesRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSleConnStatusChangesPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleConnStatusChangesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleConnStatusChangesRow findRowByPk( SleConnStatusChangesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleConnStatusChangesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(SleConnStatusChangesRow)}を使用してください。 
   */
    public static SleConnStatusChangesDao findDaoByPk( long p_queId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleConnStatusChangesPK pk = new SleConnStatusChangesPK( p_queId );
        SleConnStatusChangesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SleConnStatusChangesPK)}および{@@link #forRow(SleConnStatusChangesRow)}を使用してください。 
   */
    public static SleConnStatusChangesDao findDaoByPk( SleConnStatusChangesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleConnStatusChangesRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_queId, and にて指定の値から一意の{@@link SleConnStatusChangesRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_queId 検索対象であるp_queIdフィールドの値
   * 
   * @@return 引数指定のp_queId, and の値と一致する{@@link SleConnStatusChangesRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SleConnStatusChangesRow findRowByQueId( long p_queId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleConnStatusChangesRow.TYPE,
            "que_id=?",
            null,
            new Object[] { new Long(p_queId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleConnStatusChangesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleConnStatusChangesDao.findRowsByQueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByQueId(long)}および{@@link #forRow(SleConnStatusChangesRow)}を使用してください。 
   */
    public static SleConnStatusChangesDao findDaoByQueId( long p_queId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueId( p_queId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_marketCode, p_sleConnDiv, and にて指定の値に一致する{@@link SleConnStatusChangesRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_sleConnDiv 検索対象であるp_sleConnDivフィールドの値
   * 
   * @@return 引数指定のp_marketCode, p_sleConnDiv, and の値と一致する{@@link SleConnStatusChangesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMarketCodeSleConnDiv( String p_marketCode, int p_sleConnDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleConnStatusChangesRow.TYPE,
            "market_code=? and sle_conn_div=?",
            null,
            new Object[] { p_marketCode, new Integer(p_sleConnDiv) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMarketCodeSleConnDiv(String, int)}および{@@link #forRow(SleConnStatusChangesRow)}を使用してください。 
   */
    public static List findDaosByMarketCodeSleConnDiv( String p_marketCode, int p_sleConnDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMarketCodeSleConnDiv( p_marketCode, p_sleConnDiv ) );
    }


  /** 
   * p_eventAckedDiv, and にて指定の値に一致する{@@link SleConnStatusChangesRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_eventAckedDiv 検索対象であるp_eventAckedDivフィールドの値
   * 
   * @@return 引数指定のp_eventAckedDiv, and の値と一致する{@@link SleConnStatusChangesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByEventAckedDiv( int p_eventAckedDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleConnStatusChangesRow.TYPE,
            "event_acked_div=?",
            null,
            new Object[] { new Integer(p_eventAckedDiv) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByEventAckedDiv(int)}および{@@link #forRow(SleConnStatusChangesRow)}を使用してください。 
   */
    public static List findDaosByEventAckedDiv( int p_eventAckedDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByEventAckedDiv( p_eventAckedDiv ) );
    }

}
@
