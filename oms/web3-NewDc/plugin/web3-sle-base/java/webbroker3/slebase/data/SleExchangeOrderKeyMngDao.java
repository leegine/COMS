head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleExchangeOrderKeyMngDao.java;


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
 * {@@link SleExchangeOrderKeyMngDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SleExchangeOrderKeyMngRow}インスタンスへ関連付けることができます。 
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
 * @@see SleExchangeOrderKeyMngPK 
 * @@see SleExchangeOrderKeyMngRow 
 */
public class SleExchangeOrderKeyMngDao extends DataAccessObject {


  /** 
   * この{@@link SleExchangeOrderKeyMngDao}に関連する型指定のRowオブジェクト 
   */
    private SleExchangeOrderKeyMngRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SleExchangeOrderKeyMngRow}と仮定される{@@link DataAccessObject}から新たに{@@link SleExchangeOrderKeyMngDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SleExchangeOrderKeyMngDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SleExchangeOrderKeyMngRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleExchangeOrderKeyMngRow )
                return new SleExchangeOrderKeyMngDao( (SleExchangeOrderKeyMngRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleExchangeOrderKeyMngRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleExchangeOrderKeyMngRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SleExchangeOrderKeyMngRow}オブジェクト 
    */
    protected SleExchangeOrderKeyMngDao( SleExchangeOrderKeyMngRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SleExchangeOrderKeyMngRow}オブジェクトを取得します。
   */
    public SleExchangeOrderKeyMngRow getSleExchangeOrderKeyMngRow() {
        return row;
    }


  /** 
   * 指定の{@@link SleExchangeOrderKeyMngRow}オブジェクトから{@@link SleExchangeOrderKeyMngDao}オブジェクトを作成します。 
   * これは実際の{@@link SleExchangeOrderKeyMngRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SleExchangeOrderKeyMngDao}取得のために指定の{@@link SleExchangeOrderKeyMngRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SleExchangeOrderKeyMngDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SleExchangeOrderKeyMngDao forRow( SleExchangeOrderKeyMngRow row ) throws java.lang.IllegalArgumentException {
        return (SleExchangeOrderKeyMngDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleExchangeOrderKeyMngRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SleExchangeOrderKeyMngRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SleExchangeOrderKeyMngPK}やデータベースレコードとして挿入される{@@link SleExchangeOrderKeyMngParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleExchangeOrderKeyMngRow.TYPE );
    }


  /** 
   * {@@link SleExchangeOrderKeyMngRow}を一意に特定する{@@link SleExchangeOrderKeyMngPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SleExchangeOrderKeyMngRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SleExchangeOrderKeyMngParams}オブジェクトの主キーとして利用可能な{@@link SleExchangeOrderKeyMngPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のコラムが含まれているかコラムのタイプがlong型でない場合 
   */
    public static SleExchangeOrderKeyMngPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SleExchangeOrderKeyMngRow}オブジェクトを検索します。 
   * 
   * @@param p_xblocksProductType 検索対象であるp_xblocksProductTypeフィールドの値
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleExchangeOrderKeyMngRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleExchangeOrderKeyMngRow findRowByPk( int p_xblocksProductType, long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleExchangeOrderKeyMngPK pk = new SleExchangeOrderKeyMngPK( p_xblocksProductType, p_orderUnitId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSleExchangeOrderKeyMngPKオブジェクトから{@@link SleExchangeOrderKeyMngRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSleExchangeOrderKeyMngPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SleExchangeOrderKeyMngRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SleExchangeOrderKeyMngRow findRowByPk( SleExchangeOrderKeyMngPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleExchangeOrderKeyMngRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(int,long)}および{@@link #forRow(SleExchangeOrderKeyMngRow)}を使用してください。 
   */
    public static SleExchangeOrderKeyMngDao findDaoByPk( int p_xblocksProductType, long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleExchangeOrderKeyMngPK pk = new SleExchangeOrderKeyMngPK( p_xblocksProductType, p_orderUnitId );
        SleExchangeOrderKeyMngRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SleExchangeOrderKeyMngPK)}および{@@link #forRow(SleExchangeOrderKeyMngRow)}を使用してください。 
   */
    public static SleExchangeOrderKeyMngDao findDaoByPk( SleExchangeOrderKeyMngPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleExchangeOrderKeyMngRow row = findRowByPk( pk );
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
   * p_xblocksProductType, p_orderUnitId, and にて指定の値から一意の{@@link SleExchangeOrderKeyMngRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_xblocksProductType 検索対象であるp_xblocksProductTypeフィールドの値
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 引数指定のp_xblocksProductType, p_orderUnitId, and の値と一致する{@@link SleExchangeOrderKeyMngRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SleExchangeOrderKeyMngRow findRowByXblocksProductTypeOrderUnitId( int p_xblocksProductType, long p_orderUnitId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleExchangeOrderKeyMngRow.TYPE,
            "xblocks_product_type=? and order_unit_id=?",
            null,
            new Object[] { new Integer(p_xblocksProductType), new Long(p_orderUnitId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleExchangeOrderKeyMngRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleExchangeOrderKeyMngDao.findRowsByXblocksProductTypeOrderUnitId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByXblocksProductTypeOrderUnitId(int, long)}および{@@link #forRow(SleExchangeOrderKeyMngRow)}を使用してください。 
   */
    public static SleExchangeOrderKeyMngDao findDaoByXblocksProductTypeOrderUnitId( int p_xblocksProductType, long p_orderUnitId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByXblocksProductTypeOrderUnitId( p_xblocksProductType, p_orderUnitId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
