head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendQDao.java;


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
 * {@@link SleSendQDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SleSendQRow}インスタンスへ関連付けることができます。 
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
 * @@see SleSendQPK 
 * @@see SleSendQRow 
 */
public class SleSendQDao extends DataAccessObject {


  /** 
   * この{@@link SleSendQDao}に関連する型指定のRowオブジェクト 
   */
    private SleSendQRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SleSendQRow}と仮定される{@@link DataAccessObject}から新たに{@@link SleSendQDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SleSendQDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SleSendQRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleSendQRow )
                return new SleSendQDao( (SleSendQRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleSendQRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleSendQRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SleSendQRow}オブジェクト 
    */
    protected SleSendQDao( SleSendQRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SleSendQRow}オブジェクトを取得します。
   */
    public SleSendQRow getSleSendQRow() {
        return row;
    }


  /** 
   * 指定の{@@link SleSendQRow}オブジェクトから{@@link SleSendQDao}オブジェクトを作成します。 
   * これは実際の{@@link SleSendQRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SleSendQDao}取得のために指定の{@@link SleSendQRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SleSendQDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SleSendQDao forRow( SleSendQRow row ) throws java.lang.IllegalArgumentException {
        return (SleSendQDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleSendQRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SleSendQRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SleSendQPK}やデータベースレコードとして挿入される{@@link SleSendQParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleSendQRow.TYPE );
    }


  /** 
   * {@@link SleSendQRow}を一意に特定する{@@link SleSendQPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SleSendQRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SleSendQParams}オブジェクトの主キーとして利用可能な{@@link SleSendQPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のコラムが含まれているかコラムのタイプがlong型でない場合 
   */
    public static SleSendQPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SleSendQPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SleSendQRow}オブジェクトを検索します。 
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleSendQRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleSendQRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQPK pk = new SleSendQPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSleSendQPKオブジェクトから{@@link SleSendQRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSleSendQPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleSendQRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleSendQRow findRowByPk( SleSendQPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleSendQRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(SleSendQRow)}を使用してください。 
   */
    public static SleSendQDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQPK pk = new SleSendQPK( p_queueId );
        SleSendQRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SleSendQPK)}および{@@link #forRow(SleSendQRow)}を使用してください。 
   */
    public static SleSendQDao findDaoByPk( SleSendQPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link SleSendQDao}に関連する{@@link SleSendQRow}の外部キーがある{@@link SleSendQErrorsRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link SleSendQDao}に関連する{@@link SleSendQRow}の外部キーがある{@@link SleSendQErrorsRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchSleSendQErrorsRowsByQueueId() throws DataNetworkException, DataQueryException  {
        return SleSendQErrorsDao.findRowsByQueueId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchSleSendQErrorsRowsByQueueId()}および{@@link #forRow(SleSendQRow)}を使用してください。 
   */
    public List fetchSleSendQErrorsDaosByQueueId() throws DataNetworkException, DataQueryException  {
        return SleSendQErrorsDao.findDaosByQueueId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchSleSendQErrorsRowsByQueueId()}および{@@link #forRow(SleSendQRow)}を使用してください。 
   */
    public List fetchSleSendQErrorsDaosQueueId() throws DataNetworkException, DataQueryException  {
        return fetchSleSendQErrorsDaosByQueueId();
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
   * p_queueId, and にて指定の値から一意の{@@link SleSendQRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のp_queueId, and の値と一致する{@@link SleSendQRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SleSendQRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleSendQRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleSendQRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleSendQDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByQueueId(long)}および{@@link #forRow(SleSendQRow)}を使用してください。 
   */
    public static SleSendQDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_status, p_accountId, p_bizDate, and にて指定の値に一致する{@@link SleSendQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のp_status, p_accountId, p_bizDate, and の値と一致する{@@link SleSendQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatusAccountIdBizDate( webbroker3.slebase.enums.SleSendqProcStatusEnum p_status, long p_accountId, String p_bizDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleSendQRow.TYPE,
            "status=? and account_id=? and biz_date=?",
            null,
            new Object[] { p_status, new Long(p_accountId), p_bizDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatusAccountIdBizDate(webbroker3.slebase.enums.SleSendqProcStatusEnum, long, String)}および{@@link #forRow(SleSendQRow)}を使用してください。 
   */
    public static List findDaosByStatusAccountIdBizDate( webbroker3.slebase.enums.SleSendqProcStatusEnum p_status, long p_accountId, String p_bizDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusAccountIdBizDate( p_status, p_accountId, p_bizDate ) );
    }


  /** 
   * p_productCode, p_accountId, p_status, p_createdTimestamp, and にて指定の値に一致する{@@link SleSendQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_createdTimestamp 検索対象であるp_createdTimestampフィールドの値
   * 
   * @@return 引数指定のp_productCode, p_accountId, p_status, p_createdTimestamp, and の値と一致する{@@link SleSendQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductCodeAccountIdStatusCreatedTimestamp( String p_productCode, long p_accountId, webbroker3.slebase.enums.SleSendqProcStatusEnum p_status, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleSendQRow.TYPE,
            "product_code=? and account_id=? and status=? and created_timestamp=?",
            null,
            new Object[] { p_productCode, new Long(p_accountId), p_status, p_createdTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductCodeAccountIdStatusCreatedTimestamp(String, long, webbroker3.slebase.enums.SleSendqProcStatusEnum, java.sql.Timestamp)}および{@@link #forRow(SleSendQRow)}を使用してください。 
   */
    public static List findDaosByProductCodeAccountIdStatusCreatedTimestamp( String p_productCode, long p_accountId, webbroker3.slebase.enums.SleSendqProcStatusEnum p_status, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductCodeAccountIdStatusCreatedTimestamp( p_productCode, p_accountId, p_status, p_createdTimestamp ) );
    }

}
@
