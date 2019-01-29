head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FixSendQDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FixSendQDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FixSendQRow}インスタンスへ関連付けることができます。 
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
 * @@see FixSendQPK 
 * @@see FixSendQRow 
 */
public class FixSendQDao extends DataAccessObject {


  /** 
   * この{@@link FixSendQDao}に関連する型指定のRowオブジェクト 
   */
    private FixSendQRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FixSendQRow}と仮定される{@@link DataAccessObject}から新たに{@@link FixSendQDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FixSendQDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FixSendQRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FixSendQRow )
                return new FixSendQDao( (FixSendQRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FixSendQRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FixSendQRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FixSendQRow}オブジェクト 
    */
    protected FixSendQDao( FixSendQRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FixSendQRow}オブジェクトを取得します。
   */
    public FixSendQRow getFixSendQRow() {
        return row;
    }


  /** 
   * 指定の{@@link FixSendQRow}オブジェクトから{@@link FixSendQDao}オブジェクトを作成します。 
   * これは実際の{@@link FixSendQRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FixSendQDao}取得のために指定の{@@link FixSendQRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FixSendQDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FixSendQDao forRow( FixSendQRow row ) throws java.lang.IllegalArgumentException {
        return (FixSendQDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FixSendQRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FixSendQRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FixSendQPK}やデータベースレコードとして挿入される{@@link FixSendQParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FixSendQRow.TYPE );
    }


  /** 
   * {@@link FixSendQRow}を一意に特定する{@@link FixSendQPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FixSendQRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FixSendQParams}オブジェクトの主キーとして利用可能な{@@link FixSendQPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FixSendQPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new FixSendQPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FixSendQRow}オブジェクトを検索します。 
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FixSendQRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FixSendQRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        FixSendQPK pk = new FixSendQPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFixSendQPKオブジェクトから{@@link FixSendQRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFixSendQPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FixSendQRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FixSendQRow findRowByPk( FixSendQPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FixSendQRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(FixSendQRow)}を使用してください。 
   */
    public static FixSendQDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        FixSendQPK pk = new FixSendQPK( p_queueId );
        FixSendQRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FixSendQPK)}および{@@link #forRow(FixSendQRow)}を使用してください。 
   */
    public static FixSendQDao findDaoByPk( FixSendQPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FixSendQRow row = findRowByPk( pk );
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
   * p_queueId, and にて指定の値から一意の{@@link FixSendQRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のp_queueId, and の値と一致する{@@link FixSendQRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FixSendQRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FixSendQRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FixSendQRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FixSendQDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByQueueId(long)}および{@@link #forRow(FixSendQRow)}を使用してください。 
   */
    public static FixSendQDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_clOrdId, and にて指定の値に一致する{@@link FixSendQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_clOrdId 検索対象であるp_clOrdIdフィールドの値
   * 
   * @@return 引数指定のp_clOrdId, and の値と一致する{@@link FixSendQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByClOrdId( String p_clOrdId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixSendQRow.TYPE,
            "cl_ord_id=?",
            null,
            new Object[] { p_clOrdId } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByClOrdId(String)}および{@@link #forRow(FixSendQRow)}を使用してください。 
   */
    public static List findDaosByClOrdId( String p_clOrdId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByClOrdId( p_clOrdId ) );
    }


  /** 
   * p_status, p_sessionId, p_productType, p_orderRequestNumber, and にて指定の値に一致する{@@link FixSendQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_sessionId 検索対象であるp_sessionIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_status, p_sessionId, p_productType, p_orderRequestNumber, and の値と一致する{@@link FixSendQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatusSessionIdProductTypeOrderRequestNumber( String p_status, int p_sessionId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixSendQRow.TYPE,
            "status=? and session_id=? and product_type=? and order_request_number=?",
            null,
            new Object[] { p_status, new Integer(p_sessionId), p_productType, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatusSessionIdProductTypeOrderRequestNumber(String, int, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String)}および{@@link #forRow(FixSendQRow)}を使用してください。 
   */
    public static List findDaosByStatusSessionIdProductTypeOrderRequestNumber( String p_status, int p_sessionId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusSessionIdProductTypeOrderRequestNumber( p_status, p_sessionId, p_productType, p_orderRequestNumber ) );
    }


  /** 
   * p_orderRequestNumber, and にて指定の値に一致する{@@link FixSendQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link FixSendQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixSendQRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(FixSendQRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }

}
@
