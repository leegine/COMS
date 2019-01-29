head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqLimitPriceRangeMstDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FeqLimitPriceRangeMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqLimitPriceRangeMstRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqLimitPriceRangeMstPK 
 * @@see FeqLimitPriceRangeMstRow 
 */
public class FeqLimitPriceRangeMstDao extends DataAccessObject {


  /** 
   * この{@@link FeqLimitPriceRangeMstDao}に関連する型指定のRowオブジェクト 
   */
    private FeqLimitPriceRangeMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqLimitPriceRangeMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqLimitPriceRangeMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqLimitPriceRangeMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqLimitPriceRangeMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqLimitPriceRangeMstRow )
                return new FeqLimitPriceRangeMstDao( (FeqLimitPriceRangeMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqLimitPriceRangeMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqLimitPriceRangeMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqLimitPriceRangeMstRow}オブジェクト 
    */
    protected FeqLimitPriceRangeMstDao( FeqLimitPriceRangeMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqLimitPriceRangeMstRow}オブジェクトを取得します。
   */
    public FeqLimitPriceRangeMstRow getFeqLimitPriceRangeMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqLimitPriceRangeMstRow}オブジェクトから{@@link FeqLimitPriceRangeMstDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqLimitPriceRangeMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqLimitPriceRangeMstDao}取得のために指定の{@@link FeqLimitPriceRangeMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqLimitPriceRangeMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqLimitPriceRangeMstDao forRow( FeqLimitPriceRangeMstRow row ) throws java.lang.IllegalArgumentException {
        return (FeqLimitPriceRangeMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqLimitPriceRangeMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqLimitPriceRangeMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqLimitPriceRangeMstPK}やデータベースレコードとして挿入される{@@link FeqLimitPriceRangeMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqLimitPriceRangeMstRow.TYPE );
    }


  /** 
   * {@@link FeqLimitPriceRangeMstRow}を一意に特定する{@@link FeqLimitPriceRangeMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqLimitPriceRangeMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqLimitPriceRangeMstParams}オブジェクトの主キーとして利用可能な{@@link FeqLimitPriceRangeMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqLimitPriceRangeMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqLimitPriceRangeMstRow}オブジェクトを検索します。 
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqLimitPriceRangeMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqLimitPriceRangeMstRow findRowByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLimitPriceRangeMstPK pk = new FeqLimitPriceRangeMstPK( p_marketCode, p_lowPrice );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqLimitPriceRangeMstPKオブジェクトから{@@link FeqLimitPriceRangeMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqLimitPriceRangeMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqLimitPriceRangeMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqLimitPriceRangeMstRow findRowByPk( FeqLimitPriceRangeMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqLimitPriceRangeMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,double)}および{@@link #forRow(FeqLimitPriceRangeMstRow)}を使用してください。 
   */
    public static FeqLimitPriceRangeMstDao findDaoByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLimitPriceRangeMstPK pk = new FeqLimitPriceRangeMstPK( p_marketCode, p_lowPrice );
        FeqLimitPriceRangeMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqLimitPriceRangeMstPK)}および{@@link #forRow(FeqLimitPriceRangeMstRow)}を使用してください。 
   */
    public static FeqLimitPriceRangeMstDao findDaoByPk( FeqLimitPriceRangeMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLimitPriceRangeMstRow row = findRowByPk( pk );
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
   * p_marketCode, p_lowPrice, and にて指定の値から一意の{@@link FeqLimitPriceRangeMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のp_marketCode, p_lowPrice, and の値と一致する{@@link FeqLimitPriceRangeMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqLimitPriceRangeMstRow findRowByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqLimitPriceRangeMstRow.TYPE,
            "market_code=? and low_price=?",
            null,
            new Object[] { p_marketCode, new Double(p_lowPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqLimitPriceRangeMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqLimitPriceRangeMstDao.findRowsByMarketCodeLowPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMarketCodeLowPrice(String, double)}および{@@link #forRow(FeqLimitPriceRangeMstRow)}を使用してください。 
   */
    public static FeqLimitPriceRangeMstDao findDaoByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketCodeLowPrice( p_marketCode, p_lowPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
