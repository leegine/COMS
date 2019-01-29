head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpAssetHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpower.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TpAssetHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TpAssetHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see TpAssetHistoryPK 
 * @@see TpAssetHistoryRow 
 */
public class TpAssetHistoryDao extends DataAccessObject {


  /** 
   * この{@@link TpAssetHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private TpAssetHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TpAssetHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link TpAssetHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TpAssetHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TpAssetHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpAssetHistoryRow )
                return new TpAssetHistoryDao( (TpAssetHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpAssetHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpAssetHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TpAssetHistoryRow}オブジェクト 
    */
    protected TpAssetHistoryDao( TpAssetHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TpAssetHistoryRow}オブジェクトを取得します。
   */
    public TpAssetHistoryRow getTpAssetHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link TpAssetHistoryRow}オブジェクトから{@@link TpAssetHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link TpAssetHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TpAssetHistoryDao}取得のために指定の{@@link TpAssetHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TpAssetHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TpAssetHistoryDao forRow( TpAssetHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (TpAssetHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpAssetHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TpAssetHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TpAssetHistoryPK}やデータベースレコードとして挿入される{@@link TpAssetHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpAssetHistoryRow.TYPE );
    }


  /** 
   * {@@link TpAssetHistoryRow}を一意に特定する{@@link TpAssetHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TpAssetHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TpAssetHistoryParams}オブジェクトの主キーとして利用可能な{@@link TpAssetHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TpAssetHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpAssetHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TpAssetHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_tpAssetHistoryId 検索対象であるp_tpAssetHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TpAssetHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TpAssetHistoryRow findRowByPk( long p_tpAssetHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpAssetHistoryPK pk = new TpAssetHistoryPK( p_tpAssetHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTpAssetHistoryPKオブジェクトから{@@link TpAssetHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTpAssetHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TpAssetHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TpAssetHistoryRow findRowByPk( TpAssetHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpAssetHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TpAssetHistoryRow)}を使用してください。 
   */
    public static TpAssetHistoryDao findDaoByPk( long p_tpAssetHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpAssetHistoryPK pk = new TpAssetHistoryPK( p_tpAssetHistoryId );
        TpAssetHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TpAssetHistoryPK)}および{@@link #forRow(TpAssetHistoryRow)}を使用してください。 
   */
    public static TpAssetHistoryDao findDaoByPk( TpAssetHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpAssetHistoryRow row = findRowByPk( pk );
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
   * p_accountId, p_calcDate, and にて指定の値から一意の{@@link TpAssetHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_calcDate 検索対象であるp_calcDateフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_calcDate, and の値と一致する{@@link TpAssetHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TpAssetHistoryRow findRowByAccountIdCalcDate( long p_accountId, java.sql.Timestamp p_calcDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TpAssetHistoryRow.TYPE,
            "account_id=? and calc_date=?",
            null,
            new Object[] { new Long(p_accountId), p_calcDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TpAssetHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TpAssetHistoryDao.findRowsByAccountIdCalcDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdCalcDate(long, java.sql.Timestamp)}および{@@link #forRow(TpAssetHistoryRow)}を使用してください。 
   */
    public static TpAssetHistoryDao findDaoByAccountIdCalcDate( long p_accountId, java.sql.Timestamp p_calcDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdCalcDate( p_accountId, p_calcDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
