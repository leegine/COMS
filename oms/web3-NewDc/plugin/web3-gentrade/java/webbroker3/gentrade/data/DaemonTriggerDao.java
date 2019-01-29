head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DaemonTriggerDao.java;


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
 * {@@link DaemonTriggerDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DaemonTriggerRow}インスタンスへ関連付けることができます。 
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
 * @@see DaemonTriggerPK 
 * @@see DaemonTriggerRow 
 */
public class DaemonTriggerDao extends DataAccessObject {


  /** 
   * この{@@link DaemonTriggerDao}に関連する型指定のRowオブジェクト 
   */
    private DaemonTriggerRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DaemonTriggerRow}と仮定される{@@link DataAccessObject}から新たに{@@link DaemonTriggerDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DaemonTriggerDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DaemonTriggerRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DaemonTriggerRow )
                return new DaemonTriggerDao( (DaemonTriggerRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DaemonTriggerRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DaemonTriggerRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DaemonTriggerRow}オブジェクト 
    */
    protected DaemonTriggerDao( DaemonTriggerRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DaemonTriggerRow}オブジェクトを取得します。
   */
    public DaemonTriggerRow getDaemonTriggerRow() {
        return row;
    }


  /** 
   * 指定の{@@link DaemonTriggerRow}オブジェクトから{@@link DaemonTriggerDao}オブジェクトを作成します。 
   * これは実際の{@@link DaemonTriggerRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DaemonTriggerDao}取得のために指定の{@@link DaemonTriggerRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DaemonTriggerDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DaemonTriggerDao forRow( DaemonTriggerRow row ) throws java.lang.IllegalArgumentException {
        return (DaemonTriggerDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DaemonTriggerRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DaemonTriggerRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DaemonTriggerPK}やデータベースレコードとして挿入される{@@link DaemonTriggerParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DaemonTriggerRow.TYPE );
    }


  /** 
   * {@@link DaemonTriggerRow}を一意に特定する{@@link DaemonTriggerPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DaemonTriggerRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DaemonTriggerParams}オブジェクトの主キーとして利用可能な{@@link DaemonTriggerPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DaemonTriggerPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new DaemonTriggerPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DaemonTriggerRow}オブジェクトを検索します。 
   * 
   * @@param p_threadNo 検索対象であるp_threadNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DaemonTriggerRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DaemonTriggerRow findRowByPk( long p_threadNo ) throws DataFindException, DataQueryException, DataNetworkException {
        DaemonTriggerPK pk = new DaemonTriggerPK( p_threadNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDaemonTriggerPKオブジェクトから{@@link DaemonTriggerRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDaemonTriggerPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DaemonTriggerRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DaemonTriggerRow findRowByPk( DaemonTriggerPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DaemonTriggerRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(DaemonTriggerRow)}を使用してください。 
   */
    public static DaemonTriggerDao findDaoByPk( long p_threadNo ) throws DataFindException, DataQueryException, DataNetworkException {
        DaemonTriggerPK pk = new DaemonTriggerPK( p_threadNo );
        DaemonTriggerRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DaemonTriggerPK)}および{@@link #forRow(DaemonTriggerRow)}を使用してください。 
   */
    public static DaemonTriggerDao findDaoByPk( DaemonTriggerPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DaemonTriggerRow row = findRowByPk( pk );
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
   * p_threadNo, and にて指定の値から一意の{@@link DaemonTriggerRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_threadNo 検索対象であるp_threadNoフィールドの値
   * 
   * @@return 引数指定のp_threadNo, and の値と一致する{@@link DaemonTriggerRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DaemonTriggerRow findRowByThreadNo( long p_threadNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DaemonTriggerRow.TYPE,
            "thread_no=?",
            null,
            new Object[] { new Long(p_threadNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DaemonTriggerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DaemonTriggerDao.findRowsByThreadNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByThreadNo(long)}および{@@link #forRow(DaemonTriggerRow)}を使用してください。 
   */
    public static DaemonTriggerDao findDaoByThreadNo( long p_threadNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByThreadNo( p_threadNo ) );
    }


  /** 
   * p_triggerType, p_orderRequestNumber, p_rangeFrom, p_rangeTo, and にて指定の値から一意の{@@link DaemonTriggerRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_triggerType 検索対象であるp_triggerTypeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_rangeFrom 検索対象であるp_rangeFromフィールドの値
   * @@param p_rangeTo 検索対象であるp_rangeToフィールドの値
   * 
   * @@return 引数指定のp_triggerType, p_orderRequestNumber, p_rangeFrom, p_rangeTo, and の値と一致する{@@link DaemonTriggerRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DaemonTriggerRow findRowByTriggerTypeOrderRequestNumberRangeFromRangeTo( String p_triggerType, String p_orderRequestNumber, long p_rangeFrom, long p_rangeTo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DaemonTriggerRow.TYPE,
            "trigger_type=? and order_request_number=? and range_from=? and range_to=?",
            null,
            new Object[] { p_triggerType, p_orderRequestNumber, new Long(p_rangeFrom), new Long(p_rangeTo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DaemonTriggerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DaemonTriggerDao.findRowsByTriggerTypeOrderRequestNumberRangeFromRangeTo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTriggerTypeOrderRequestNumberRangeFromRangeTo(String, String, long, long)}および{@@link #forRow(DaemonTriggerRow)}を使用してください。 
   */
    public static DaemonTriggerDao findDaoByTriggerTypeOrderRequestNumberRangeFromRangeTo( String p_triggerType, String p_orderRequestNumber, long p_rangeFrom, long p_rangeTo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTriggerTypeOrderRequestNumberRangeFromRangeTo( p_triggerType, p_orderRequestNumber, p_rangeFrom, p_rangeTo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
