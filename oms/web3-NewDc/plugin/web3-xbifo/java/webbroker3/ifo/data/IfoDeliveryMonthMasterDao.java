head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoDeliveryMonthMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link IfoDeliveryMonthMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoDeliveryMonthMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoDeliveryMonthMasterPK 
 * @@see IfoDeliveryMonthMasterRow 
 */
public class IfoDeliveryMonthMasterDao extends DataAccessObject {


  /** 
   * この{@@link IfoDeliveryMonthMasterDao}に関連する型指定のRowオブジェクト 
   */
    private IfoDeliveryMonthMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoDeliveryMonthMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoDeliveryMonthMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoDeliveryMonthMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoDeliveryMonthMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoDeliveryMonthMasterRow )
                return new IfoDeliveryMonthMasterDao( (IfoDeliveryMonthMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoDeliveryMonthMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoDeliveryMonthMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoDeliveryMonthMasterRow}オブジェクト 
    */
    protected IfoDeliveryMonthMasterDao( IfoDeliveryMonthMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoDeliveryMonthMasterRow}オブジェクトを取得します。
   */
    public IfoDeliveryMonthMasterRow getIfoDeliveryMonthMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoDeliveryMonthMasterRow}オブジェクトから{@@link IfoDeliveryMonthMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoDeliveryMonthMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoDeliveryMonthMasterDao}取得のために指定の{@@link IfoDeliveryMonthMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoDeliveryMonthMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoDeliveryMonthMasterDao forRow( IfoDeliveryMonthMasterRow row ) throws java.lang.IllegalArgumentException {
        return (IfoDeliveryMonthMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoDeliveryMonthMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoDeliveryMonthMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoDeliveryMonthMasterPK}やデータベースレコードとして挿入される{@@link IfoDeliveryMonthMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoDeliveryMonthMasterRow.TYPE );
    }


  /** 
   * {@@link IfoDeliveryMonthMasterRow}を一意に特定する{@@link IfoDeliveryMonthMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoDeliveryMonthMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoDeliveryMonthMasterParams}オブジェクトの主キーとして利用可能な{@@link IfoDeliveryMonthMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoDeliveryMonthMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoDeliveryMonthMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_underlyingProductCode 検索対象であるp_underlyingProductCodeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * @@param p_deliveryMonth 検索対象であるp_deliveryMonthフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoDeliveryMonthMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoDeliveryMonthMasterRow findRowByPk( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDeliveryMonthMasterPK pk = new IfoDeliveryMonthMasterPK( p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoDeliveryMonthMasterPKオブジェクトから{@@link IfoDeliveryMonthMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoDeliveryMonthMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoDeliveryMonthMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoDeliveryMonthMasterRow findRowByPk( IfoDeliveryMonthMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoDeliveryMonthMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(IfoDeliveryMonthMasterRow)}を使用してください。 
   */
    public static IfoDeliveryMonthMasterDao findDaoByPk( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDeliveryMonthMasterPK pk = new IfoDeliveryMonthMasterPK( p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth );
        IfoDeliveryMonthMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoDeliveryMonthMasterPK)}および{@@link #forRow(IfoDeliveryMonthMasterRow)}を使用してください。 
   */
    public static IfoDeliveryMonthMasterDao findDaoByPk( IfoDeliveryMonthMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDeliveryMonthMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link IfoDeliveryMonthMasterDao}に紐付く{@@link IfoDeliveryMonthMasterRow}内で外部キーの関係をもつ{@@link IfoIndexMasterRow}を検索します。 
   * 
   * @@return {@@link IfoDeliveryMonthMasterDao}と外部キーの関係にある{@@link IfoIndexMasterRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public IfoIndexMasterRow fetchIfoIndexMasterRowViaIndexId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoIndexMasterPK pk = new IfoIndexMasterPK( row.getIndexId() );
        Row row = IfoIndexMasterDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IfoIndexMasterRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IfoIndexMasterRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoIndexMasterRowViaIndexId()}および{@@link #forRow(IfoDeliveryMonthMasterRow)}を使用してください。 
   */
    public IfoIndexMasterDao fetchIfoIndexMasterDaoViaIndexId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoIndexMasterPK pk = new IfoIndexMasterPK( row.getIndexId() );
        DataAccessObject dao = IfoIndexMasterDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoIndexMasterDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoIndexMasterDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for IfoIndexMaster
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByIndexId(IfoIndexMasterRow)}を使用してください。 
   */
    public static List findRowsByIndexId( IfoIndexMasterDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByIndexId( dao.getIfoIndexMasterRow() );
    }


  /** 
   * {@@link IfoIndexMasterRow}と外部キーの関係にある{@@link IfoDeliveryMonthMasterRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link IfoIndexMasterRow}オブジェクト 
   * @@return 指定の{@@link IfoIndexMasterRow}に外部キーを持つ{@@link IfoDeliveryMonthMasterRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIndexId( IfoIndexMasterRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByIndexId( row.getIndexId() );
    }


  /** 
   * {@@link IfoIndexMasterPK}と外部キーの関係にある{@@link IfoDeliveryMonthMasterRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link IfoIndexMasterPK}オブジェクト 
   * @@return {@@link IfoIndexMasterPK}と外部キーが一致する値を持つ{@@link IfoDeliveryMonthMasterRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIndexId( IfoIndexMasterPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByIndexId( pk.index_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoDeliveryMonthMasterRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_indexId 検索対象であるp_indexIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoDeliveryMonthMasterRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByIndexId( long p_indexId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoDeliveryMonthMasterRow.TYPE,
            "index_id=?",
            null,
            new Object[] { new Long(p_indexId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoIndexMaster
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByIndexId(IfoIndexMasterRow)}および{@@link #forRow(IfoDeliveryMonthMasterRow)}を使用してください。 
   */
    public static List findDaosByIndexId( IfoIndexMasterDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIndexId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIndexId(IfoIndexMasterRow)}および{@@link #forRow(IfoDeliveryMonthMasterRow)}を使用してください。 
   */
    public static List findDaosByIndexId( IfoIndexMasterRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIndexId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIndexId(IfoIndexMasterPK)}および{@@link #forRow(IfoDeliveryMonthMasterRow)}を使用してください。 
   */
    public static List findDaosByIndexId( IfoIndexMasterPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIndexId( pk.index_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByIndexId(long)}および{@@link #forRow(IfoDeliveryMonthMasterRow)}を使用してください。 
   */
    public static List findDaosByIndexId( long p_indexId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIndexId( p_indexId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth, and にて指定の値から一意の{@@link IfoDeliveryMonthMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_underlyingProductCode 検索対象であるp_underlyingProductCodeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * @@param p_deliveryMonth 検索対象であるp_deliveryMonthフィールドの値
   * 
   * @@return 引数指定のp_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth, and の値と一致する{@@link IfoDeliveryMonthMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoDeliveryMonthMasterRow findRowByUnderlyingProductCodeFutureOptionDivDeliveryMonth( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoDeliveryMonthMasterRow.TYPE,
            "underlying_product_code=? and future_option_div=? and delivery_month=?",
            null,
            new Object[] { p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoDeliveryMonthMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoDeliveryMonthMasterDao.findRowsByUnderlyingProductCodeFutureOptionDivDeliveryMonth(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByUnderlyingProductCodeFutureOptionDivDeliveryMonth(String, String, String)}および{@@link #forRow(IfoDeliveryMonthMasterRow)}を使用してください。 
   */
    public static IfoDeliveryMonthMasterDao findDaoByUnderlyingProductCodeFutureOptionDivDeliveryMonth( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByUnderlyingProductCodeFutureOptionDivDeliveryMonth( p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
