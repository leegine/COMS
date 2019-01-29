head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DailyAssetDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link DailyAssetDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DailyAssetRow}インスタンスへ関連付けることができます。 
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
 * @@see DailyAssetPK 
 * @@see DailyAssetRow 
 */
public class DailyAssetDao extends DataAccessObject {


  /** 
   * この{@@link DailyAssetDao}に関連する型指定のRowオブジェクト 
   */
    private DailyAssetRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DailyAssetRow}と仮定される{@@link DataAccessObject}から新たに{@@link DailyAssetDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DailyAssetDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DailyAssetRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DailyAssetRow )
                return new DailyAssetDao( (DailyAssetRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DailyAssetRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DailyAssetRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DailyAssetRow}オブジェクト 
    */
    protected DailyAssetDao( DailyAssetRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DailyAssetRow}オブジェクトを取得します。
   */
    public DailyAssetRow getDailyAssetRow() {
        return row;
    }


  /** 
   * 指定の{@@link DailyAssetRow}オブジェクトから{@@link DailyAssetDao}オブジェクトを作成します。 
   * これは実際の{@@link DailyAssetRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DailyAssetDao}取得のために指定の{@@link DailyAssetRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DailyAssetDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DailyAssetDao forRow( DailyAssetRow row ) throws java.lang.IllegalArgumentException {
        return (DailyAssetDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DailyAssetRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DailyAssetRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DailyAssetPK}やデータベースレコードとして挿入される{@@link DailyAssetParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DailyAssetRow.TYPE );
    }


  /** 
   * {@@link DailyAssetRow}を一意に特定する{@@link DailyAssetPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DailyAssetRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DailyAssetParams}オブジェクトの主キーとして利用可能な{@@link DailyAssetPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DailyAssetPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DailyAssetRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_taxType 検索対象であるp_taxTypeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_miniStockDiv 検索対象であるp_miniStockDivフィールドの値
   * @@param p_splitNewStockDiv 検索対象であるp_splitNewStockDivフィールドの値
   * @@param p_originalExecDate 検索対象であるp_originalExecDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DailyAssetRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DailyAssetRow findRowByPk( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) throws DataFindException, DataQueryException, DataNetworkException {
        DailyAssetPK pk = new DailyAssetPK( p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDailyAssetPKオブジェクトから{@@link DailyAssetRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDailyAssetPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DailyAssetRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DailyAssetRow findRowByPk( DailyAssetPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DailyAssetRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,long,long,com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum,java.sql.Timestamp,com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum,String,java.sql.Timestamp)}および{@@link #forRow(DailyAssetRow)}を使用してください。 
   */
    public static DailyAssetDao findDaoByPk( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) throws DataFindException, DataQueryException, DataNetworkException {
        DailyAssetPK pk = new DailyAssetPK( p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate );
        DailyAssetRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DailyAssetPK)}および{@@link #forRow(DailyAssetRow)}を使用してください。 
   */
    public static DailyAssetDao findDaoByPk( DailyAssetPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DailyAssetRow row = findRowByPk( pk );
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
   * p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate, and にて指定の値から一意の{@@link DailyAssetRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_taxType 検索対象であるp_taxTypeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_miniStockDiv 検索対象であるp_miniStockDivフィールドの値
   * @@param p_splitNewStockDiv 検索対象であるp_splitNewStockDivフィールドの値
   * @@param p_originalExecDate 検索対象であるp_originalExecDateフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate, and の値と一致する{@@link DailyAssetRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DailyAssetRow findRowByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DailyAssetRow.TYPE,
            "account_id=? and sub_account_id=? and product_id=? and tax_type=? and delivery_date=? and mini_stock_div=? and split_new_stock_div=? and original_exec_date=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Long(p_productId), p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DailyAssetRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DailyAssetDao.findRowsByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate(long, long, long, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum, java.sql.Timestamp, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum, String, java.sql.Timestamp)}および{@@link #forRow(DailyAssetRow)}を使用してください。 
   */
    public static DailyAssetDao findDaoByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate( p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, and にて指定の値に一致する{@@link DailyAssetRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, and の値と一致する{@@link DailyAssetRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DailyAssetRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(DailyAssetRow)}を使用してください。 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountId( p_accountId ) );
    }

}
@
