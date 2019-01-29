head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsManualSubmitHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rlsgateway.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RlsManualSubmitHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RlsManualSubmitHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see RlsManualSubmitHistoryPK 
 * @@see RlsManualSubmitHistoryRow 
 */
public class RlsManualSubmitHistoryDao extends DataAccessObject {


  /** 
   * この{@@link RlsManualSubmitHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private RlsManualSubmitHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RlsManualSubmitHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link RlsManualSubmitHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RlsManualSubmitHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RlsManualSubmitHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsManualSubmitHistoryRow )
                return new RlsManualSubmitHistoryDao( (RlsManualSubmitHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsManualSubmitHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsManualSubmitHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RlsManualSubmitHistoryRow}オブジェクト 
    */
    protected RlsManualSubmitHistoryDao( RlsManualSubmitHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RlsManualSubmitHistoryRow}オブジェクトを取得します。
   */
    public RlsManualSubmitHistoryRow getRlsManualSubmitHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link RlsManualSubmitHistoryRow}オブジェクトから{@@link RlsManualSubmitHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link RlsManualSubmitHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RlsManualSubmitHistoryDao}取得のために指定の{@@link RlsManualSubmitHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RlsManualSubmitHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RlsManualSubmitHistoryDao forRow( RlsManualSubmitHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (RlsManualSubmitHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsManualSubmitHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RlsManualSubmitHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RlsManualSubmitHistoryPK}やデータベースレコードとして挿入される{@@link RlsManualSubmitHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsManualSubmitHistoryRow.TYPE );
    }


  /** 
   * {@@link RlsManualSubmitHistoryRow}を一意に特定する{@@link RlsManualSubmitHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RlsManualSubmitHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RlsManualSubmitHistoryParams}オブジェクトの主キーとして利用可能な{@@link RlsManualSubmitHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RlsManualSubmitHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RlsManualSubmitHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RlsManualSubmitHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_historyId 検索対象であるp_historyIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsManualSubmitHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsManualSubmitHistoryRow findRowByPk( long p_historyId ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsManualSubmitHistoryPK pk = new RlsManualSubmitHistoryPK( p_historyId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRlsManualSubmitHistoryPKオブジェクトから{@@link RlsManualSubmitHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRlsManualSubmitHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsManualSubmitHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsManualSubmitHistoryRow findRowByPk( RlsManualSubmitHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsManualSubmitHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RlsManualSubmitHistoryRow)}を使用してください。 
   */
    public static RlsManualSubmitHistoryDao findDaoByPk( long p_historyId ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsManualSubmitHistoryPK pk = new RlsManualSubmitHistoryPK( p_historyId );
        RlsManualSubmitHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RlsManualSubmitHistoryPK)}および{@@link #forRow(RlsManualSubmitHistoryRow)}を使用してください。 
   */
    public static RlsManualSubmitHistoryDao findDaoByPk( RlsManualSubmitHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsManualSubmitHistoryRow row = findRowByPk( pk );
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
   * p_historyId, and にて指定の値から一意の{@@link RlsManualSubmitHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_historyId 検索対象であるp_historyIdフィールドの値
   * 
   * @@return 引数指定のp_historyId, and の値と一致する{@@link RlsManualSubmitHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RlsManualSubmitHistoryRow findRowByHistoryId( long p_historyId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsManualSubmitHistoryRow.TYPE,
            "history_id=?",
            null,
            new Object[] { new Long(p_historyId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsManualSubmitHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsManualSubmitHistoryDao.findRowsByHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByHistoryId(long)}および{@@link #forRow(RlsManualSubmitHistoryRow)}を使用してください。 
   */
    public static RlsManualSubmitHistoryDao findDaoByHistoryId( long p_historyId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByHistoryId( p_historyId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType, and にて指定の値に一致する{@@link RlsManualSubmitHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderType 検索対象であるp_orderTypeフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_orderType, p_orderId, p_productType, and の値と一致する{@@link RlsManualSubmitHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountIdOrderTypeOrderIdProductType( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsManualSubmitHistoryRow.TYPE,
            "account_id=? and sub_account_id=? and order_type=? and order_id=? and product_type=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Integer(p_orderType), new Long(p_orderId), p_productType } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountIdOrderTypeOrderIdProductType(long, long, int, long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(RlsManualSubmitHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountIdOrderTypeOrderIdProductType( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdSubAccountIdOrderTypeOrderIdProductType( p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType ) );
    }

}
@
