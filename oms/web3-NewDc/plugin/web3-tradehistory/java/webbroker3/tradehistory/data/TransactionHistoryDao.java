head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TransactionHistoryDao.java;


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
 * {@@link TransactionHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TransactionHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see TransactionHistoryPK 
 * @@see TransactionHistoryRow 
 */
public class TransactionHistoryDao extends DataAccessObject {


  /** 
   * この{@@link TransactionHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private TransactionHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TransactionHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link TransactionHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TransactionHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TransactionHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TransactionHistoryRow )
                return new TransactionHistoryDao( (TransactionHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TransactionHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TransactionHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TransactionHistoryRow}オブジェクト 
    */
    protected TransactionHistoryDao( TransactionHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TransactionHistoryRow}オブジェクトを取得します。
   */
    public TransactionHistoryRow getTransactionHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link TransactionHistoryRow}オブジェクトから{@@link TransactionHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link TransactionHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TransactionHistoryDao}取得のために指定の{@@link TransactionHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TransactionHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TransactionHistoryDao forRow( TransactionHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (TransactionHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TransactionHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TransactionHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TransactionHistoryPK}やデータベースレコードとして挿入される{@@link TransactionHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TransactionHistoryRow.TYPE );
    }


  /** 
   * {@@link TransactionHistoryRow}を一意に特定する{@@link TransactionHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TransactionHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TransactionHistoryParams}オブジェクトの主キーとして利用可能な{@@link TransactionHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TransactionHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TransactionHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TransactionHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_transactionHistoryId 検索対象であるp_transactionHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TransactionHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TransactionHistoryRow findRowByPk( long p_transactionHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TransactionHistoryPK pk = new TransactionHistoryPK( p_transactionHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTransactionHistoryPKオブジェクトから{@@link TransactionHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTransactionHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TransactionHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TransactionHistoryRow findRowByPk( TransactionHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TransactionHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TransactionHistoryRow)}を使用してください。 
   */
    public static TransactionHistoryDao findDaoByPk( long p_transactionHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TransactionHistoryPK pk = new TransactionHistoryPK( p_transactionHistoryId );
        TransactionHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TransactionHistoryPK)}および{@@link #forRow(TransactionHistoryRow)}を使用してください。 
   */
    public static TransactionHistoryDao findDaoByPk( TransactionHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TransactionHistoryRow row = findRowByPk( pk );
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
   * p_transactionHistoryId, and にて指定の値から一意の{@@link TransactionHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_transactionHistoryId 検索対象であるp_transactionHistoryIdフィールドの値
   * 
   * @@return 引数指定のp_transactionHistoryId, and の値と一致する{@@link TransactionHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TransactionHistoryRow findRowByTransactionHistoryId( long p_transactionHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TransactionHistoryRow.TYPE,
            "transaction_history_id=?",
            null,
            new Object[] { new Long(p_transactionHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TransactionHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TransactionHistoryDao.findRowsByTransactionHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTransactionHistoryId(long)}および{@@link #forRow(TransactionHistoryRow)}を使用してください。 
   */
    public static TransactionHistoryDao findDaoByTransactionHistoryId( long p_transactionHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTransactionHistoryId( p_transactionHistoryId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_depositMarginDiv, and にて指定の値から一意の{@@link TransactionHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_depositMarginDiv 検索対象であるp_depositMarginDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_depositMarginDiv, and の値と一致する{@@link TransactionHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TransactionHistoryRow findRowByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_depositMarginDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TransactionHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=? and deposit_margin_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_depositMarginDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TransactionHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TransactionHistoryDao.findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv(String, String, String, java.sql.Timestamp, String)}および{@@link #forRow(TransactionHistoryRow)}を使用してください。 
   */
    public static TransactionHistoryDao findDaoByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_depositMarginDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_depositMarginDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_deliveryDate, p_depositMarginDiv, and にて指定の値に一致する{@@link TransactionHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_depositMarginDiv 検索対象であるp_depositMarginDivフィールドの値
   * 
   * @@return 引数指定のp_deliveryDate, p_depositMarginDiv, and の値と一致する{@@link TransactionHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByDeliveryDateDepositMarginDiv( java.sql.Timestamp p_deliveryDate, String p_depositMarginDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TransactionHistoryRow.TYPE,
            "delivery_date=? and deposit_margin_div=?",
            null,
            new Object[] { p_deliveryDate, p_depositMarginDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByDeliveryDateDepositMarginDiv(java.sql.Timestamp, String)}および{@@link #forRow(TransactionHistoryRow)}を使用してください。 
   */
    public static List findDaosByDeliveryDateDepositMarginDiv( java.sql.Timestamp p_deliveryDate, String p_depositMarginDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByDeliveryDateDepositMarginDiv( p_deliveryDate, p_depositMarginDiv ) );
    }

}
@
