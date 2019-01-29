head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsConOrderHitNotifyDao.java;


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
 * {@@link RlsConOrderHitNotifyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RlsConOrderHitNotifyRow}インスタンスへ関連付けることができます。 
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
 * @@see RlsConOrderHitNotifyPK 
 * @@see RlsConOrderHitNotifyRow 
 */
public class RlsConOrderHitNotifyDao extends DataAccessObject {


  /** 
   * この{@@link RlsConOrderHitNotifyDao}に関連する型指定のRowオブジェクト 
   */
    private RlsConOrderHitNotifyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RlsConOrderHitNotifyRow}と仮定される{@@link DataAccessObject}から新たに{@@link RlsConOrderHitNotifyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RlsConOrderHitNotifyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RlsConOrderHitNotifyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsConOrderHitNotifyRow )
                return new RlsConOrderHitNotifyDao( (RlsConOrderHitNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsConOrderHitNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsConOrderHitNotifyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RlsConOrderHitNotifyRow}オブジェクト 
    */
    protected RlsConOrderHitNotifyDao( RlsConOrderHitNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RlsConOrderHitNotifyRow}オブジェクトを取得します。
   */
    public RlsConOrderHitNotifyRow getRlsConOrderHitNotifyRow() {
        return row;
    }


  /** 
   * 指定の{@@link RlsConOrderHitNotifyRow}オブジェクトから{@@link RlsConOrderHitNotifyDao}オブジェクトを作成します。 
   * これは実際の{@@link RlsConOrderHitNotifyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RlsConOrderHitNotifyDao}取得のために指定の{@@link RlsConOrderHitNotifyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RlsConOrderHitNotifyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RlsConOrderHitNotifyDao forRow( RlsConOrderHitNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (RlsConOrderHitNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsConOrderHitNotifyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RlsConOrderHitNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RlsConOrderHitNotifyPK}やデータベースレコードとして挿入される{@@link RlsConOrderHitNotifyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsConOrderHitNotifyRow.TYPE );
    }


  /** 
   * {@@link RlsConOrderHitNotifyRow}を一意に特定する{@@link RlsConOrderHitNotifyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RlsConOrderHitNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RlsConOrderHitNotifyParams}オブジェクトの主キーとして利用可能な{@@link RlsConOrderHitNotifyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RlsConOrderHitNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RlsConOrderHitNotifyRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderType 検索対象であるp_orderTypeフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsConOrderHitNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsConOrderHitNotifyRow findRowByPk( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsConOrderHitNotifyPK pk = new RlsConOrderHitNotifyPK( p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRlsConOrderHitNotifyPKオブジェクトから{@@link RlsConOrderHitNotifyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRlsConOrderHitNotifyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsConOrderHitNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsConOrderHitNotifyRow findRowByPk( RlsConOrderHitNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsConOrderHitNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,long,int,long,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(RlsConOrderHitNotifyRow)}を使用してください。 
   */
    public static RlsConOrderHitNotifyDao findDaoByPk( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsConOrderHitNotifyPK pk = new RlsConOrderHitNotifyPK( p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType );
        RlsConOrderHitNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RlsConOrderHitNotifyPK)}および{@@link #forRow(RlsConOrderHitNotifyRow)}を使用してください。 
   */
    public static RlsConOrderHitNotifyDao findDaoByPk( RlsConOrderHitNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsConOrderHitNotifyRow row = findRowByPk( pk );
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
   * p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType, and にて指定の値から一意の{@@link RlsConOrderHitNotifyRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_orderType 検索対象であるp_orderTypeフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_orderType, p_orderId, p_productType, and の値と一致する{@@link RlsConOrderHitNotifyRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RlsConOrderHitNotifyRow findRowByAccountIdSubAccountIdOrderTypeOrderIdProductType( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsConOrderHitNotifyRow.TYPE,
            "account_id=? and sub_account_id=? and order_type=? and order_id=? and product_type=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Integer(p_orderType), new Long(p_orderId), p_productType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsConOrderHitNotifyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsConOrderHitNotifyDao.findRowsByAccountIdSubAccountIdOrderTypeOrderIdProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdSubAccountIdOrderTypeOrderIdProductType(long, long, int, long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(RlsConOrderHitNotifyRow)}を使用してください。 
   */
    public static RlsConOrderHitNotifyDao findDaoByAccountIdSubAccountIdOrderTypeOrderIdProductType( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdOrderTypeOrderIdProductType( p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and にて指定の値に一致する{@@link RlsConOrderHitNotifyRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_status, and の値と一致する{@@link RlsConOrderHitNotifyRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsConOrderHitNotifyRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdStatus(long, String)}および{@@link #forRow(RlsConOrderHitNotifyRow)}を使用してください。 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }

}
@
