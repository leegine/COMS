head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendQErrorsDao.java;


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
 * {@@link SleSendQErrorsDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SleSendQErrorsRow}インスタンスへ関連付けることができます。 
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
 * @@see SleSendQErrorsPK 
 * @@see SleSendQErrorsRow 
 */
public class SleSendQErrorsDao extends DataAccessObject {


  /** 
   * この{@@link SleSendQErrorsDao}に関連する型指定のRowオブジェクト 
   */
    private SleSendQErrorsRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SleSendQErrorsRow}と仮定される{@@link DataAccessObject}から新たに{@@link SleSendQErrorsDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SleSendQErrorsDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SleSendQErrorsRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleSendQErrorsRow )
                return new SleSendQErrorsDao( (SleSendQErrorsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleSendQErrorsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleSendQErrorsRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SleSendQErrorsRow}オブジェクト 
    */
    protected SleSendQErrorsDao( SleSendQErrorsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SleSendQErrorsRow}オブジェクトを取得します。
   */
    public SleSendQErrorsRow getSleSendQErrorsRow() {
        return row;
    }


  /** 
   * 指定の{@@link SleSendQErrorsRow}オブジェクトから{@@link SleSendQErrorsDao}オブジェクトを作成します。 
   * これは実際の{@@link SleSendQErrorsRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SleSendQErrorsDao}取得のために指定の{@@link SleSendQErrorsRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SleSendQErrorsDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SleSendQErrorsDao forRow( SleSendQErrorsRow row ) throws java.lang.IllegalArgumentException {
        return (SleSendQErrorsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleSendQErrorsRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SleSendQErrorsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SleSendQErrorsPK}やデータベースレコードとして挿入される{@@link SleSendQErrorsParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleSendQErrorsRow.TYPE );
    }


  /** 
   * {@@link SleSendQErrorsRow}を一意に特定する{@@link SleSendQErrorsPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SleSendQErrorsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SleSendQErrorsParams}オブジェクトの主キーとして利用可能な{@@link SleSendQErrorsPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のコラムが含まれているかコラムのタイプがlong型でない場合 
   */
    public static SleSendQErrorsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SleSendQErrorsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SleSendQErrorsRow}オブジェクトを検索します。 
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleSendQErrorsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleSendQErrorsRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQErrorsPK pk = new SleSendQErrorsPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSleSendQErrorsPKオブジェクトから{@@link SleSendQErrorsRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSleSendQErrorsPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleSendQErrorsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleSendQErrorsRow findRowByPk( SleSendQErrorsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleSendQErrorsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public static SleSendQErrorsDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQErrorsPK pk = new SleSendQErrorsPK( p_queueId );
        SleSendQErrorsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SleSendQErrorsPK)}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public static SleSendQErrorsDao findDaoByPk( SleSendQErrorsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQErrorsRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link SleSendQErrorsDao}に紐付く{@@link SleSendQErrorsRow}内で外部キーの関係をもつ{@@link SleSendQRow}を検索します。 
   * 
   * @@return {@@link SleSendQErrorsDao}と外部キーの関係にある{@@link SleSendQRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public SleSendQRow fetchSleSendQRowViaQueueId() throws DataNetworkException, DataFindException, DataQueryException  {
        SleSendQPK pk = new SleSendQPK( row.getQueueId() );
        Row row = SleSendQDao.findRowByPk( pk );
        if ( row != null && !(row instanceof SleSendQRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (SleSendQRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchSleSendQRowViaQueueId()}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public SleSendQDao fetchSleSendQDaoViaQueueId() throws DataNetworkException, DataFindException, DataQueryException  {
        SleSendQPK pk = new SleSendQPK( row.getQueueId() );
        DataAccessObject dao = SleSendQDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SleSendQDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SleSendQDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for SleSendQ
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByQueueId(SleSendQRow)}を使用してください。 
   */
    public static List findRowsByQueueId( SleSendQDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByQueueId( dao.getSleSendQRow() );
    }


  /** 
   * {@@link SleSendQRow}と外部キーの関係にある{@@link SleSendQErrorsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SleSendQRow}オブジェクト 
   * @@return 指定の{@@link SleSendQRow}に外部キーを持つ{@@link SleSendQErrorsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByQueueId( SleSendQRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByQueueId( row.getQueueId() );
    }


  /** 
   * {@@link SleSendQPK}と外部キーの関係にある{@@link SleSendQErrorsRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SleSendQPK}オブジェクト 
   * @@return {@@link SleSendQPK}と外部キーが一致する値を持つ{@@link SleSendQErrorsRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByQueueId( SleSendQPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByQueueId( pk.queue_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link SleSendQErrorsRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link SleSendQErrorsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByQueueId( long p_queueId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleSendQErrorsRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SleSendQ
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByQueueId(SleSendQRow)}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public static List findDaosByQueueId( SleSendQDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByQueueId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByQueueId(SleSendQRow)}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public static List findDaosByQueueId( SleSendQRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByQueueId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByQueueId(SleSendQPK)}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public static List findDaosByQueueId( SleSendQPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByQueueId( pk.queue_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByQueueId(long)}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public static List findDaosByQueueId( long p_queueId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByQueueId( p_queueId ) );
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
   * p_queueId, and にて指定の値から一意の{@@link SleSendQErrorsRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のp_queueId, and の値と一致する{@@link SleSendQErrorsRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SleSendQErrorsRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleSendQErrorsRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleSendQErrorsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleSendQErrorsDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByQueueId(long)}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public static SleSendQErrorsDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_openStatus, and にて指定の値に一致する{@@link SleSendQErrorsRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_openStatus 検索対象であるp_openStatusフィールドの値
   * 
   * @@return 引数指定のp_openStatus, and の値と一致する{@@link SleSendQErrorsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOpenStatus( webbroker3.slebase.enums.SleOpenStatusEnum p_openStatus ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleSendQErrorsRow.TYPE,
            "open_status=?",
            null,
            new Object[] { p_openStatus } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOpenStatus(webbroker3.slebase.enums.SleOpenStatusEnum)}および{@@link #forRow(SleSendQErrorsRow)}を使用してください。 
   */
    public static List findDaosByOpenStatus( webbroker3.slebase.enums.SleOpenStatusEnum p_openStatus ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOpenStatus( p_openStatus ) );
    }

}
@
