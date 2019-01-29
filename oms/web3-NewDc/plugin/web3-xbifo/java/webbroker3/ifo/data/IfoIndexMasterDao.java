head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoIndexMasterDao.java;


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
 * {@@link IfoIndexMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoIndexMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoIndexMasterPK 
 * @@see IfoIndexMasterRow 
 */
public class IfoIndexMasterDao extends DataAccessObject {


  /** 
   * この{@@link IfoIndexMasterDao}に関連する型指定のRowオブジェクト 
   */
    private IfoIndexMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoIndexMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoIndexMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoIndexMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoIndexMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoIndexMasterRow )
                return new IfoIndexMasterDao( (IfoIndexMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoIndexMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoIndexMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoIndexMasterRow}オブジェクト 
    */
    protected IfoIndexMasterDao( IfoIndexMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoIndexMasterRow}オブジェクトを取得します。
   */
    public IfoIndexMasterRow getIfoIndexMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoIndexMasterRow}オブジェクトから{@@link IfoIndexMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoIndexMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoIndexMasterDao}取得のために指定の{@@link IfoIndexMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoIndexMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoIndexMasterDao forRow( IfoIndexMasterRow row ) throws java.lang.IllegalArgumentException {
        return (IfoIndexMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoIndexMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoIndexMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoIndexMasterPK}やデータベースレコードとして挿入される{@@link IfoIndexMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoIndexMasterRow.TYPE );
    }


  /** 
   * {@@link IfoIndexMasterRow}を一意に特定する{@@link IfoIndexMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoIndexMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoIndexMasterParams}オブジェクトの主キーとして利用可能な{@@link IfoIndexMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoIndexMasterPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoIndexMasterPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoIndexMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_indexId 検索対象であるp_indexIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoIndexMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoIndexMasterRow findRowByPk( long p_indexId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoIndexMasterPK pk = new IfoIndexMasterPK( p_indexId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoIndexMasterPKオブジェクトから{@@link IfoIndexMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoIndexMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoIndexMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoIndexMasterRow findRowByPk( IfoIndexMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoIndexMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(IfoIndexMasterRow)}を使用してください。 
   */
    public static IfoIndexMasterDao findDaoByPk( long p_indexId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoIndexMasterPK pk = new IfoIndexMasterPK( p_indexId );
        IfoIndexMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoIndexMasterPK)}および{@@link #forRow(IfoIndexMasterRow)}を使用してください。 
   */
    public static IfoIndexMasterDao findDaoByPk( IfoIndexMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoIndexMasterRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link IfoIndexMasterDao}に関連する{@@link IfoIndexMasterRow}の外部キーがある{@@link IfoDeliveryMonthMasterRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link IfoIndexMasterDao}に関連する{@@link IfoIndexMasterRow}の外部キーがある{@@link IfoDeliveryMonthMasterRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchIfoDeliveryMonthMasterRowsByIndexId() throws DataNetworkException, DataQueryException  {
        return IfoDeliveryMonthMasterDao.findRowsByIndexId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoDeliveryMonthMasterRowsByIndexId()}および{@@link #forRow(IfoIndexMasterRow)}を使用してください。 
   */
    public List fetchIfoDeliveryMonthMasterDaosByIndexId() throws DataNetworkException, DataQueryException  {
        return IfoDeliveryMonthMasterDao.findDaosByIndexId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoDeliveryMonthMasterRowsByIndexId()}および{@@link #forRow(IfoIndexMasterRow)}を使用してください。 
   */
    public List fetchIfoDeliveryMonthMasterDaosIndexId() throws DataNetworkException, DataQueryException  {
        return fetchIfoDeliveryMonthMasterDaosByIndexId();
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
   * p_underlyingProductCode, p_futureOptionDiv, and にて指定の値から一意の{@@link IfoIndexMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_underlyingProductCode 検索対象であるp_underlyingProductCodeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * 
   * @@return 引数指定のp_underlyingProductCode, p_futureOptionDiv, and の値と一致する{@@link IfoIndexMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoIndexMasterRow findRowByUnderlyingProductCodeFutureOptionDiv( String p_underlyingProductCode, String p_futureOptionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoIndexMasterRow.TYPE,
            "underlying_product_code=? and future_option_div=?",
            null,
            new Object[] { p_underlyingProductCode, p_futureOptionDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoIndexMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoIndexMasterDao.findRowsByUnderlyingProductCodeFutureOptionDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByUnderlyingProductCodeFutureOptionDiv(String, String)}および{@@link #forRow(IfoIndexMasterRow)}を使用してください。 
   */
    public static IfoIndexMasterDao findDaoByUnderlyingProductCodeFutureOptionDiv( String p_underlyingProductCode, String p_futureOptionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByUnderlyingProductCodeFutureOptionDiv( p_underlyingProductCode, p_futureOptionDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
