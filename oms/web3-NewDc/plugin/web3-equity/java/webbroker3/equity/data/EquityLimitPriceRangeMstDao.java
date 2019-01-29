head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityLimitPriceRangeMstDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link EquityLimitPriceRangeMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EquityLimitPriceRangeMstRow}インスタンスへ関連付けることができます。 
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
 * @@see EquityLimitPriceRangeMstPK 
 * @@see EquityLimitPriceRangeMstRow 
 */
public class EquityLimitPriceRangeMstDao extends DataAccessObject {


  /** 
   * この{@@link EquityLimitPriceRangeMstDao}に関連する型指定のRowオブジェクト 
   */
    private EquityLimitPriceRangeMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EquityLimitPriceRangeMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link EquityLimitPriceRangeMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EquityLimitPriceRangeMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EquityLimitPriceRangeMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EquityLimitPriceRangeMstRow )
                return new EquityLimitPriceRangeMstDao( (EquityLimitPriceRangeMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EquityLimitPriceRangeMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EquityLimitPriceRangeMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EquityLimitPriceRangeMstRow}オブジェクト 
    */
    protected EquityLimitPriceRangeMstDao( EquityLimitPriceRangeMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EquityLimitPriceRangeMstRow}オブジェクトを取得します。
   */
    public EquityLimitPriceRangeMstRow getEquityLimitPriceRangeMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link EquityLimitPriceRangeMstRow}オブジェクトから{@@link EquityLimitPriceRangeMstDao}オブジェクトを作成します。 
   * これは実際の{@@link EquityLimitPriceRangeMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EquityLimitPriceRangeMstDao}取得のために指定の{@@link EquityLimitPriceRangeMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EquityLimitPriceRangeMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EquityLimitPriceRangeMstDao forRow( EquityLimitPriceRangeMstRow row ) throws java.lang.IllegalArgumentException {
        return (EquityLimitPriceRangeMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EquityLimitPriceRangeMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EquityLimitPriceRangeMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EquityLimitPriceRangeMstPK}やデータベースレコードとして挿入される{@@link EquityLimitPriceRangeMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EquityLimitPriceRangeMstRow.TYPE );
    }


  /** 
   * {@@link EquityLimitPriceRangeMstRow}を一意に特定する{@@link EquityLimitPriceRangeMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EquityLimitPriceRangeMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EquityLimitPriceRangeMstParams}オブジェクトの主キーとして利用可能な{@@link EquityLimitPriceRangeMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EquityLimitPriceRangeMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EquityLimitPriceRangeMstRow}オブジェクトを検索します。 
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityLimitPriceRangeMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityLimitPriceRangeMstRow findRowByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityLimitPriceRangeMstPK pk = new EquityLimitPriceRangeMstPK( p_marketCode, p_lowPrice );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEquityLimitPriceRangeMstPKオブジェクトから{@@link EquityLimitPriceRangeMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEquityLimitPriceRangeMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityLimitPriceRangeMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityLimitPriceRangeMstRow findRowByPk( EquityLimitPriceRangeMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EquityLimitPriceRangeMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,double)}および{@@link #forRow(EquityLimitPriceRangeMstRow)}を使用してください。 
   */
    public static EquityLimitPriceRangeMstDao findDaoByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityLimitPriceRangeMstPK pk = new EquityLimitPriceRangeMstPK( p_marketCode, p_lowPrice );
        EquityLimitPriceRangeMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EquityLimitPriceRangeMstPK)}および{@@link #forRow(EquityLimitPriceRangeMstRow)}を使用してください。 
   */
    public static EquityLimitPriceRangeMstDao findDaoByPk( EquityLimitPriceRangeMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityLimitPriceRangeMstRow row = findRowByPk( pk );
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
   * p_marketCode, p_lowPrice, and にて指定の値から一意の{@@link EquityLimitPriceRangeMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のp_marketCode, p_lowPrice, and の値と一致する{@@link EquityLimitPriceRangeMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EquityLimitPriceRangeMstRow findRowByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EquityLimitPriceRangeMstRow.TYPE,
            "market_code=? and low_price=?",
            null,
            new Object[] { p_marketCode, new Double(p_lowPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EquityLimitPriceRangeMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EquityLimitPriceRangeMstDao.findRowsByMarketCodeLowPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMarketCodeLowPrice(String, double)}および{@@link #forRow(EquityLimitPriceRangeMstRow)}を使用してください。 
   */
    public static EquityLimitPriceRangeMstDao findDaoByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketCodeLowPrice( p_marketCode, p_lowPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
