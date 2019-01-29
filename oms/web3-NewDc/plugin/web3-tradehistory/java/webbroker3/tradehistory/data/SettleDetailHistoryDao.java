head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.47.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	SettleDetailHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradehistory.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SettleDetailHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SettleDetailHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see SettleDetailHistoryPK 
 * @@see SettleDetailHistoryRow 
 */
public class SettleDetailHistoryDao extends DataAccessObject {


  /** 
   * この{@@link SettleDetailHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private SettleDetailHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SettleDetailHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link SettleDetailHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SettleDetailHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SettleDetailHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SettleDetailHistoryRow )
                return new SettleDetailHistoryDao( (SettleDetailHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SettleDetailHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SettleDetailHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SettleDetailHistoryRow}オブジェクト 
    */
    protected SettleDetailHistoryDao( SettleDetailHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SettleDetailHistoryRow}オブジェクトを取得します。
   */
    public SettleDetailHistoryRow getSettleDetailHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link SettleDetailHistoryRow}オブジェクトから{@@link SettleDetailHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link SettleDetailHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SettleDetailHistoryDao}取得のために指定の{@@link SettleDetailHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SettleDetailHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SettleDetailHistoryDao forRow( SettleDetailHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (SettleDetailHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SettleDetailHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SettleDetailHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SettleDetailHistoryPK}やデータベースレコードとして挿入される{@@link SettleDetailHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SettleDetailHistoryRow.TYPE );
    }


  /** 
   * {@@link SettleDetailHistoryRow}を一意に特定する{@@link SettleDetailHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SettleDetailHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SettleDetailHistoryParams}オブジェクトの主キーとして利用可能な{@@link SettleDetailHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SettleDetailHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SettleDetailHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SettleDetailHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_settleDetailHistoryId 検索対象であるp_settleDetailHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SettleDetailHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SettleDetailHistoryRow findRowByPk( long p_settleDetailHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        SettleDetailHistoryPK pk = new SettleDetailHistoryPK( p_settleDetailHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSettleDetailHistoryPKオブジェクトから{@@link SettleDetailHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSettleDetailHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SettleDetailHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SettleDetailHistoryRow findRowByPk( SettleDetailHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SettleDetailHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(SettleDetailHistoryRow)}を使用してください。 
   */
    public static SettleDetailHistoryDao findDaoByPk( long p_settleDetailHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        SettleDetailHistoryPK pk = new SettleDetailHistoryPK( p_settleDetailHistoryId );
        SettleDetailHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SettleDetailHistoryPK)}および{@@link #forRow(SettleDetailHistoryRow)}を使用してください。 
   */
    public static SettleDetailHistoryDao findDaoByPk( SettleDetailHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SettleDetailHistoryRow row = findRowByPk( pk );
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
   * p_settleDetailHistoryId, and にて指定の値から一意の{@@link SettleDetailHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_settleDetailHistoryId 検索対象であるp_settleDetailHistoryIdフィールドの値
   * 
   * @@return 引数指定のp_settleDetailHistoryId, and の値と一致する{@@link SettleDetailHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SettleDetailHistoryRow findRowBySettleDetailHistoryId( long p_settleDetailHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SettleDetailHistoryRow.TYPE,
            "settle_detail_history_id=?",
            null,
            new Object[] { new Long(p_settleDetailHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SettleDetailHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SettleDetailHistoryDao.findRowsBySettleDetailHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowBySettleDetailHistoryId(long)}および{@@link #forRow(SettleDetailHistoryRow)}を使用してください。 
   */
    public static SettleDetailHistoryDao findDaoBySettleDetailHistoryId( long p_settleDetailHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySettleDetailHistoryId( p_settleDetailHistoryId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_closeContractPrice, p_netAmount, p_status, p_accountDiv, p_closeExecDate, and にて指定の値に一致する{@@link SettleDetailHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_closeContractPrice 検索対象であるp_closeContractPriceフィールドの値
   * @@param p_netAmount 検索対象であるp_netAmountフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_closeExecDate 検索対象であるp_closeExecDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_closeContractPrice, p_netAmount, p_status, p_accountDiv, p_closeExecDate, and の値と一致する{@@link SettleDetailHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeCloseContractPriceNetAmountStatusAccountDivCloseExecDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productCode, Double p_closeContractPrice, Double p_netAmount, String p_status, String p_accountDiv, java.sql.Timestamp p_closeExecDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SettleDetailHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=? and product_code=? and close_contract_price=? and net_amount=? and status=? and account_div=? and close_exec_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_closeContractPrice, p_netAmount, p_status, p_accountDiv, p_closeExecDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeCloseContractPriceNetAmountStatusAccountDivCloseExecDate(String, String, String, java.sql.Timestamp, String, Double, Double, String, String, java.sql.Timestamp)}および{@@link #forRow(SettleDetailHistoryRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeCloseContractPriceNetAmountStatusAccountDivCloseExecDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productCode, Double p_closeContractPrice, Double p_netAmount, String p_status, String p_accountDiv, java.sql.Timestamp p_closeExecDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeCloseContractPriceNetAmountStatusAccountDivCloseExecDate( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_closeContractPrice, p_netAmount, p_status, p_accountDiv, p_closeExecDate ) );
    }


  /** 
   * p_deliveryDate, and にて指定の値に一致する{@@link SettleDetailHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * 
   * @@return 引数指定のp_deliveryDate, and の値と一致する{@@link SettleDetailHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SettleDetailHistoryRow.TYPE,
            "delivery_date=?",
            null,
            new Object[] { p_deliveryDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByDeliveryDate(java.sql.Timestamp)}および{@@link #forRow(SettleDetailHistoryRow)}を使用してください。 
   */
    public static List findDaosByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByDeliveryDate( p_deliveryDate ) );
    }

}
@
