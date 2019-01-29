head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.27.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FixRcvdQDao.java;


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
 * {@@link FixRcvdQDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FixRcvdQRow}インスタンスへ関連付けることができます。 
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
 * @@see FixRcvdQPK 
 * @@see FixRcvdQRow 
 */
public class FixRcvdQDao extends DataAccessObject {


  /** 
   * この{@@link FixRcvdQDao}に関連する型指定のRowオブジェクト 
   */
    private FixRcvdQRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FixRcvdQRow}と仮定される{@@link DataAccessObject}から新たに{@@link FixRcvdQDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FixRcvdQDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FixRcvdQRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FixRcvdQRow )
                return new FixRcvdQDao( (FixRcvdQRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FixRcvdQRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FixRcvdQRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FixRcvdQRow}オブジェクト 
    */
    protected FixRcvdQDao( FixRcvdQRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FixRcvdQRow}オブジェクトを取得します。
   */
    public FixRcvdQRow getFixRcvdQRow() {
        return row;
    }


  /** 
   * 指定の{@@link FixRcvdQRow}オブジェクトから{@@link FixRcvdQDao}オブジェクトを作成します。 
   * これは実際の{@@link FixRcvdQRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FixRcvdQDao}取得のために指定の{@@link FixRcvdQRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FixRcvdQDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FixRcvdQDao forRow( FixRcvdQRow row ) throws java.lang.IllegalArgumentException {
        return (FixRcvdQDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FixRcvdQRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FixRcvdQRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FixRcvdQPK}やデータベースレコードとして挿入される{@@link FixRcvdQParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FixRcvdQRow.TYPE );
    }


  /** 
   * {@@link FixRcvdQRow}を一意に特定する{@@link FixRcvdQPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FixRcvdQRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FixRcvdQParams}オブジェクトの主キーとして利用可能な{@@link FixRcvdQPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FixRcvdQPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new FixRcvdQPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FixRcvdQRow}オブジェクトを検索します。 
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FixRcvdQRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FixRcvdQRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        FixRcvdQPK pk = new FixRcvdQPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFixRcvdQPKオブジェクトから{@@link FixRcvdQRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFixRcvdQPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FixRcvdQRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FixRcvdQRow findRowByPk( FixRcvdQPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FixRcvdQRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(FixRcvdQRow)}を使用してください。 
   */
    public static FixRcvdQDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        FixRcvdQPK pk = new FixRcvdQPK( p_queueId );
        FixRcvdQRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FixRcvdQPK)}および{@@link #forRow(FixRcvdQRow)}を使用してください。 
   */
    public static FixRcvdQDao findDaoByPk( FixRcvdQPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FixRcvdQRow row = findRowByPk( pk );
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
   * p_queueId, and にて指定の値から一意の{@@link FixRcvdQRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のp_queueId, and の値と一致する{@@link FixRcvdQRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FixRcvdQRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FixRcvdQRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FixRcvdQRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FixRcvdQDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByQueueId(long)}および{@@link #forRow(FixRcvdQRow)}を使用してください。 
   */
    public static FixRcvdQDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_status, p_clOrdId, p_productType, p_orderRequestNumber, and にて指定の値に一致する{@@link FixRcvdQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_clOrdId 検索対象であるp_clOrdIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_status, p_clOrdId, p_productType, p_orderRequestNumber, and の値と一致する{@@link FixRcvdQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatusClOrdIdProductTypeOrderRequestNumber( String p_status, String p_clOrdId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixRcvdQRow.TYPE,
            "status=? and cl_ord_id=? and product_type=? and order_request_number=?",
            null,
            new Object[] { p_status, p_clOrdId, p_productType, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatusClOrdIdProductTypeOrderRequestNumber(String, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String)}および{@@link #forRow(FixRcvdQRow)}を使用してください。 
   */
    public static List findDaosByStatusClOrdIdProductTypeOrderRequestNumber( String p_status, String p_clOrdId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusClOrdIdProductTypeOrderRequestNumber( p_status, p_clOrdId, p_productType, p_orderRequestNumber ) );
    }


  /** 
   * p_orderRequestNumber, and にて指定の値に一致する{@@link FixRcvdQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link FixRcvdQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixRcvdQRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(FixRcvdQRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }


  /** 
   * p_execId, and にて指定の値に一致する{@@link FixRcvdQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_execId 検索対象であるp_execIdフィールドの値
   * 
   * @@return 引数指定のp_execId, and の値と一致する{@@link FixRcvdQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByExecId( String p_execId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixRcvdQRow.TYPE,
            "exec_id=?",
            null,
            new Object[] { p_execId } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByExecId(String)}および{@@link #forRow(FixRcvdQRow)}を使用してください。 
   */
    public static List findDaosByExecId( String p_execId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByExecId( p_execId ) );
    }

}
@
