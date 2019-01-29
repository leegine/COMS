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
filename	FeqTickValuesMstDao.java;


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
 * {@@link FeqTickValuesMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqTickValuesMstRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqTickValuesMstPK 
 * @@see FeqTickValuesMstRow 
 */
public class FeqTickValuesMstDao extends DataAccessObject {


  /** 
   * この{@@link FeqTickValuesMstDao}に関連する型指定のRowオブジェクト 
   */
    private FeqTickValuesMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqTickValuesMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqTickValuesMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqTickValuesMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqTickValuesMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqTickValuesMstRow )
                return new FeqTickValuesMstDao( (FeqTickValuesMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqTickValuesMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqTickValuesMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqTickValuesMstRow}オブジェクト 
    */
    protected FeqTickValuesMstDao( FeqTickValuesMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqTickValuesMstRow}オブジェクトを取得します。
   */
    public FeqTickValuesMstRow getFeqTickValuesMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqTickValuesMstRow}オブジェクトから{@@link FeqTickValuesMstDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqTickValuesMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqTickValuesMstDao}取得のために指定の{@@link FeqTickValuesMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqTickValuesMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqTickValuesMstDao forRow( FeqTickValuesMstRow row ) throws java.lang.IllegalArgumentException {
        return (FeqTickValuesMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqTickValuesMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqTickValuesMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqTickValuesMstPK}やデータベースレコードとして挿入される{@@link FeqTickValuesMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqTickValuesMstRow.TYPE );
    }


  /** 
   * {@@link FeqTickValuesMstRow}を一意に特定する{@@link FeqTickValuesMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqTickValuesMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqTickValuesMstParams}オブジェクトの主キーとして利用可能な{@@link FeqTickValuesMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqTickValuesMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqTickValuesMstRow}オブジェクトを検索します。 
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqTickValuesMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqTickValuesMstRow findRowByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqTickValuesMstPK pk = new FeqTickValuesMstPK( p_marketCode, p_lowPrice );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqTickValuesMstPKオブジェクトから{@@link FeqTickValuesMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqTickValuesMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqTickValuesMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqTickValuesMstRow findRowByPk( FeqTickValuesMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqTickValuesMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,double)}および{@@link #forRow(FeqTickValuesMstRow)}を使用してください。 
   */
    public static FeqTickValuesMstDao findDaoByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqTickValuesMstPK pk = new FeqTickValuesMstPK( p_marketCode, p_lowPrice );
        FeqTickValuesMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqTickValuesMstPK)}および{@@link #forRow(FeqTickValuesMstRow)}を使用してください。 
   */
    public static FeqTickValuesMstDao findDaoByPk( FeqTickValuesMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqTickValuesMstRow row = findRowByPk( pk );
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
   * p_marketCode, p_lowPrice, and にて指定の値から一意の{@@link FeqTickValuesMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_lowPrice 検索対象であるp_lowPriceフィールドの値
   * 
   * @@return 引数指定のp_marketCode, p_lowPrice, and の値と一致する{@@link FeqTickValuesMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqTickValuesMstRow findRowByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqTickValuesMstRow.TYPE,
            "market_code=? and low_price=?",
            null,
            new Object[] { p_marketCode, new Double(p_lowPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqTickValuesMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqTickValuesMstDao.findRowsByMarketCodeLowPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMarketCodeLowPrice(String, double)}および{@@link #forRow(FeqTickValuesMstRow)}を使用してください。 
   */
    public static FeqTickValuesMstDao findDaoByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketCodeLowPrice( p_marketCode, p_lowPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
