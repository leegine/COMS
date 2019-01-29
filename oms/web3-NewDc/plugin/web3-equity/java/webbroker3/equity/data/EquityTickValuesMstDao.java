head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityTickValuesMstDao.java;


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
 * {@@link EquityTickValuesMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EquityTickValuesMstRow}インスタンスへ関連付けることができます。 
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
 * @@see EquityTickValuesMstPK 
 * @@see EquityTickValuesMstRow 
 */
public class EquityTickValuesMstDao extends DataAccessObject {


  /** 
   * この{@@link EquityTickValuesMstDao}に関連する型指定のRowオブジェクト 
   */
    private EquityTickValuesMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EquityTickValuesMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link EquityTickValuesMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EquityTickValuesMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EquityTickValuesMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EquityTickValuesMstRow )
                return new EquityTickValuesMstDao( (EquityTickValuesMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EquityTickValuesMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EquityTickValuesMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EquityTickValuesMstRow}オブジェクト 
    */
    protected EquityTickValuesMstDao( EquityTickValuesMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EquityTickValuesMstRow}オブジェクトを取得します。
   */
    public EquityTickValuesMstRow getEquityTickValuesMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link EquityTickValuesMstRow}オブジェクトから{@@link EquityTickValuesMstDao}オブジェクトを作成します。 
   * これは実際の{@@link EquityTickValuesMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EquityTickValuesMstDao}取得のために指定の{@@link EquityTickValuesMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EquityTickValuesMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EquityTickValuesMstDao forRow( EquityTickValuesMstRow row ) throws java.lang.IllegalArgumentException {
        return (EquityTickValuesMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EquityTickValuesMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EquityTickValuesMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EquityTickValuesMstPK}やデータベースレコードとして挿入される{@@link EquityTickValuesMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EquityTickValuesMstRow.TYPE );
    }


  /** 
   * {@@link EquityTickValuesMstRow}を一意に特定する{@@link EquityTickValuesMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EquityTickValuesMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EquityTickValuesMstParams}オブジェクトの主キーとして利用可能な{@@link EquityTickValuesMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EquityTickValuesMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EquityTickValuesMstRow}オブジェクトを検索します。 
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityTickValuesMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityTickValuesMstRow findRowByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityTickValuesMstPK pk = new EquityTickValuesMstPK( p_marketCode, p_lowPrice );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEquityTickValuesMstPKオブジェクトから{@@link EquityTickValuesMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEquityTickValuesMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityTickValuesMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityTickValuesMstRow findRowByPk( EquityTickValuesMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EquityTickValuesMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,double)}および{@@link #forRow(EquityTickValuesMstRow)}を使用してください。 
   */
    public static EquityTickValuesMstDao findDaoByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityTickValuesMstPK pk = new EquityTickValuesMstPK( p_marketCode, p_lowPrice );
        EquityTickValuesMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EquityTickValuesMstPK)}および{@@link #forRow(EquityTickValuesMstRow)}を使用してください。 
   */
    public static EquityTickValuesMstDao findDaoByPk( EquityTickValuesMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityTickValuesMstRow row = findRowByPk( pk );
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
   * p_marketCode, p_lowPrice, and にて指定の値から一意の{@@link EquityTickValuesMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のp_marketCode, p_lowPrice, and の値と一致する{@@link EquityTickValuesMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EquityTickValuesMstRow findRowByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EquityTickValuesMstRow.TYPE,
            "market_code=? and low_price=?",
            null,
            new Object[] { p_marketCode, new Double(p_lowPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EquityTickValuesMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EquityTickValuesMstDao.findRowsByMarketCodeLowPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMarketCodeLowPrice(String, double)}および{@@link #forRow(EquityTickValuesMstRow)}を使用してください。 
   */
    public static EquityTickValuesMstDao findDaoByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketCodeLowPrice( p_marketCode, p_lowPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
