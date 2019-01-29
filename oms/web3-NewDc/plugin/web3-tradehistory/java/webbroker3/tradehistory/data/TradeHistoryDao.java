head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TradeHistoryDao.java;


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
 * {@@link TradeHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TradeHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see TradeHistoryPK 
 * @@see TradeHistoryRow 
 */
public class TradeHistoryDao extends DataAccessObject {


  /** 
   * この{@@link TradeHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private TradeHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TradeHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link TradeHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TradeHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TradeHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TradeHistoryRow )
                return new TradeHistoryDao( (TradeHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TradeHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TradeHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TradeHistoryRow}オブジェクト 
    */
    protected TradeHistoryDao( TradeHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TradeHistoryRow}オブジェクトを取得します。
   */
    public TradeHistoryRow getTradeHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link TradeHistoryRow}オブジェクトから{@@link TradeHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link TradeHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TradeHistoryDao}取得のために指定の{@@link TradeHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TradeHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TradeHistoryDao forRow( TradeHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (TradeHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TradeHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TradeHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TradeHistoryPK}やデータベースレコードとして挿入される{@@link TradeHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TradeHistoryRow.TYPE );
    }


  /** 
   * {@@link TradeHistoryRow}を一意に特定する{@@link TradeHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TradeHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TradeHistoryParams}オブジェクトの主キーとして利用可能な{@@link TradeHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TradeHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TradeHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TradeHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_tradeHistoryId 検索対象であるp_tradeHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradeHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradeHistoryRow findRowByPk( long p_tradeHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeHistoryPK pk = new TradeHistoryPK( p_tradeHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTradeHistoryPKオブジェクトから{@@link TradeHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTradeHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TradeHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TradeHistoryRow findRowByPk( TradeHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TradeHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TradeHistoryRow)}を使用してください。 
   */
    public static TradeHistoryDao findDaoByPk( long p_tradeHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeHistoryPK pk = new TradeHistoryPK( p_tradeHistoryId );
        TradeHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TradeHistoryPK)}および{@@link #forRow(TradeHistoryRow)}を使用してください。 
   */
    public static TradeHistoryDao findDaoByPk( TradeHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeHistoryRow row = findRowByPk( pk );
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
   * p_tradeHistoryId, and にて指定の値から一意の{@@link TradeHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tradeHistoryId 検索対象であるp_tradeHistoryIdフィールドの値
   * 
   * @@return 引数指定のp_tradeHistoryId, and の値と一致する{@@link TradeHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TradeHistoryRow findRowByTradeHistoryId( long p_tradeHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TradeHistoryRow.TYPE,
            "trade_history_id=?",
            null,
            new Object[] { new Long(p_tradeHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TradeHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TradeHistoryDao.findRowsByTradeHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTradeHistoryId(long)}および{@@link #forRow(TradeHistoryRow)}を使用してください。 
   */
    public static TradeHistoryDao findDaoByTradeHistoryId( long p_tradeHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradeHistoryId( p_tradeHistoryId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, and にて指定の値に一致する{@@link TradeHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, and の値と一致する{@@link TradeHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradeHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDate(String, String, String, java.sql.Timestamp)}および{@@link #forRow(TradeHistoryRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDeliveryDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDate( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate ) );
    }


  /** 
   * p_deliveryDate, and にて指定の値に一致する{@@link TradeHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * 
   * @@return 引数指定のp_deliveryDate, and の値と一致する{@@link TradeHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradeHistoryRow.TYPE,
            "delivery_date=?",
            null,
            new Object[] { p_deliveryDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByDeliveryDate(java.sql.Timestamp)}および{@@link #forRow(TradeHistoryRow)}を使用してください。 
   */
    public static List findDaosByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByDeliveryDate( p_deliveryDate ) );
    }

}
@
