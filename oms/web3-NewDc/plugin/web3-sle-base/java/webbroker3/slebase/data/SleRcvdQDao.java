head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleRcvdQDao.java;


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
 * {@@link SleRcvdQDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SleRcvdQRow}インスタンスへ関連付けることができます。 
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
 * @@see SleRcvdQPK 
 * @@see SleRcvdQRow 
 */
public class SleRcvdQDao extends DataAccessObject {


  /** 
   * この{@@link SleRcvdQDao}に関連する型指定のRowオブジェクト 
   */
    private SleRcvdQRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SleRcvdQRow}と仮定される{@@link DataAccessObject}から新たに{@@link SleRcvdQDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SleRcvdQDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SleRcvdQRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleRcvdQRow )
                return new SleRcvdQDao( (SleRcvdQRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleRcvdQRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleRcvdQRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SleRcvdQRow}オブジェクト 
    */
    protected SleRcvdQDao( SleRcvdQRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SleRcvdQRow}オブジェクトを取得します。
   */
    public SleRcvdQRow getSleRcvdQRow() {
        return row;
    }


  /** 
   * 指定の{@@link SleRcvdQRow}オブジェクトから{@@link SleRcvdQDao}オブジェクトを作成します。 
   * これは実際の{@@link SleRcvdQRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SleRcvdQDao}取得のために指定の{@@link SleRcvdQRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SleRcvdQDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SleRcvdQDao forRow( SleRcvdQRow row ) throws java.lang.IllegalArgumentException {
        return (SleRcvdQDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleRcvdQRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SleRcvdQRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SleRcvdQPK}やデータベースレコードとして挿入される{@@link SleRcvdQParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleRcvdQRow.TYPE );
    }


  /** 
   * {@@link SleRcvdQRow}を一意に特定する{@@link SleRcvdQPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SleRcvdQRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SleRcvdQParams}オブジェクトの主キーとして利用可能な{@@link SleRcvdQPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のコラムが含まれているかコラムのタイプがlong型でない場合 
   */
    public static SleRcvdQPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SleRcvdQPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SleRcvdQRow}オブジェクトを検索します。 
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleRcvdQRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleRcvdQRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleRcvdQPK pk = new SleRcvdQPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSleRcvdQPKオブジェクトから{@@link SleRcvdQRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSleRcvdQPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleRcvdQRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleRcvdQRow findRowByPk( SleRcvdQPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleRcvdQRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(SleRcvdQRow)}を使用してください。 
   */
    public static SleRcvdQDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleRcvdQPK pk = new SleRcvdQPK( p_queueId );
        SleRcvdQRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SleRcvdQPK)}および{@@link #forRow(SleRcvdQRow)}を使用してください。 
   */
    public static SleRcvdQDao findDaoByPk( SleRcvdQPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleRcvdQRow row = findRowByPk( pk );
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
   * p_queueId, and にて指定の値から一意の{@@link SleRcvdQRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_queueId 検索対象であるp_queueIdフィールドの値
   * 
   * @@return 引数指定のp_queueId, and の値と一致する{@@link SleRcvdQRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SleRcvdQRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleRcvdQRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleRcvdQRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleRcvdQDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByQueueId(long)}および{@@link #forRow(SleRcvdQRow)}を使用してください。 
   */
    public static SleRcvdQDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and にて指定の値に一致する{@@link SleRcvdQRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_status, and の値と一致する{@@link SleRcvdQRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, webbroker3.slebase.enums.SleRcvdqProcStatusEnum p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleRcvdQRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdStatus(long, webbroker3.slebase.enums.SleRcvdqProcStatusEnum)}および{@@link #forRow(SleRcvdQRow)}を使用してください。 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, webbroker3.slebase.enums.SleRcvdqProcStatusEnum p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }

}
@
