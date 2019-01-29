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
filename	IfoLimitPriceRangeMasterDao.java;


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
 * {@@link IfoLimitPriceRangeMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoLimitPriceRangeMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoLimitPriceRangeMasterPK 
 * @@see IfoLimitPriceRangeMasterRow 
 */
public class IfoLimitPriceRangeMasterDao extends DataAccessObject {


  /** 
   * この{@@link IfoLimitPriceRangeMasterDao}に関連する型指定のRowオブジェクト 
   */
    private IfoLimitPriceRangeMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoLimitPriceRangeMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoLimitPriceRangeMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoLimitPriceRangeMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoLimitPriceRangeMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoLimitPriceRangeMasterRow )
                return new IfoLimitPriceRangeMasterDao( (IfoLimitPriceRangeMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoLimitPriceRangeMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoLimitPriceRangeMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoLimitPriceRangeMasterRow}オブジェクト 
    */
    protected IfoLimitPriceRangeMasterDao( IfoLimitPriceRangeMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoLimitPriceRangeMasterRow}オブジェクトを取得します。
   */
    public IfoLimitPriceRangeMasterRow getIfoLimitPriceRangeMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoLimitPriceRangeMasterRow}オブジェクトから{@@link IfoLimitPriceRangeMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoLimitPriceRangeMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoLimitPriceRangeMasterDao}取得のために指定の{@@link IfoLimitPriceRangeMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoLimitPriceRangeMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoLimitPriceRangeMasterDao forRow( IfoLimitPriceRangeMasterRow row ) throws java.lang.IllegalArgumentException {
        return (IfoLimitPriceRangeMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoLimitPriceRangeMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoLimitPriceRangeMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoLimitPriceRangeMasterPK}やデータベースレコードとして挿入される{@@link IfoLimitPriceRangeMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoLimitPriceRangeMasterRow.TYPE );
    }


  /** 
   * {@@link IfoLimitPriceRangeMasterRow}を一意に特定する{@@link IfoLimitPriceRangeMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoLimitPriceRangeMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoLimitPriceRangeMasterParams}オブジェクトの主キーとして利用可能な{@@link IfoLimitPriceRangeMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoLimitPriceRangeMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoLimitPriceRangeMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_targetProductCode 検索対象であるp_targetProductCodeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoLimitPriceRangeMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoLimitPriceRangeMasterRow findRowByPk( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLimitPriceRangeMasterPK pk = new IfoLimitPriceRangeMasterPK( p_targetProductCode, p_futureOptionDiv, p_lowPrice );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoLimitPriceRangeMasterPKオブジェクトから{@@link IfoLimitPriceRangeMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoLimitPriceRangeMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoLimitPriceRangeMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoLimitPriceRangeMasterRow findRowByPk( IfoLimitPriceRangeMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoLimitPriceRangeMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,double)}および{@@link #forRow(IfoLimitPriceRangeMasterRow)}を使用してください。 
   */
    public static IfoLimitPriceRangeMasterDao findDaoByPk( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLimitPriceRangeMasterPK pk = new IfoLimitPriceRangeMasterPK( p_targetProductCode, p_futureOptionDiv, p_lowPrice );
        IfoLimitPriceRangeMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoLimitPriceRangeMasterPK)}および{@@link #forRow(IfoLimitPriceRangeMasterRow)}を使用してください。 
   */
    public static IfoLimitPriceRangeMasterDao findDaoByPk( IfoLimitPriceRangeMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLimitPriceRangeMasterRow row = findRowByPk( pk );
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
   * p_targetProductCode, p_futureOptionDiv, p_lowPrice, and にて指定の値から一意の{@@link IfoLimitPriceRangeMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_targetProductCode 検索対象であるp_targetProductCodeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のp_targetProductCode, p_futureOptionDiv, p_lowPrice, and の値と一致する{@@link IfoLimitPriceRangeMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoLimitPriceRangeMasterRow findRowByTargetProductCodeFutureOptionDivLowPrice( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoLimitPriceRangeMasterRow.TYPE,
            "target_product_code=? and future_option_div=? and low_price=?",
            null,
            new Object[] { p_targetProductCode, p_futureOptionDiv, new Double(p_lowPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoLimitPriceRangeMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoLimitPriceRangeMasterDao.findRowsByTargetProductCodeFutureOptionDivLowPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTargetProductCodeFutureOptionDivLowPrice(String, String, double)}および{@@link #forRow(IfoLimitPriceRangeMasterRow)}を使用してください。 
   */
    public static IfoLimitPriceRangeMasterDao findDaoByTargetProductCodeFutureOptionDivLowPrice( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTargetProductCodeFutureOptionDivLowPrice( p_targetProductCode, p_futureOptionDiv, p_lowPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
