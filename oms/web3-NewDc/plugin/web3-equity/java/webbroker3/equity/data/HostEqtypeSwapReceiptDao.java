head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeSwapReceiptDao.java;


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

/** 
 * {@@link HostEqtypeSwapReceiptDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostEqtypeSwapReceiptRow}インスタンスへ関連付けることができます。 
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
 * @@see HostEqtypeSwapReceiptPK 
 * @@see HostEqtypeSwapReceiptRow 
 */
public class HostEqtypeSwapReceiptDao extends DataAccessObject {


  /** 
   * この{@@link HostEqtypeSwapReceiptDao}に関連する型指定のRowオブジェクト 
   */
    private HostEqtypeSwapReceiptRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostEqtypeSwapReceiptRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostEqtypeSwapReceiptDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostEqtypeSwapReceiptDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostEqtypeSwapReceiptRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEqtypeSwapReceiptRow )
                return new HostEqtypeSwapReceiptDao( (HostEqtypeSwapReceiptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEqtypeSwapReceiptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEqtypeSwapReceiptRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostEqtypeSwapReceiptRow}オブジェクト 
    */
    protected HostEqtypeSwapReceiptDao( HostEqtypeSwapReceiptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostEqtypeSwapReceiptRow}オブジェクトを取得します。
   */
    public HostEqtypeSwapReceiptRow getHostEqtypeSwapReceiptRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostEqtypeSwapReceiptRow}オブジェクトから{@@link HostEqtypeSwapReceiptDao}オブジェクトを作成します。 
   * これは実際の{@@link HostEqtypeSwapReceiptRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostEqtypeSwapReceiptDao}取得のために指定の{@@link HostEqtypeSwapReceiptRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostEqtypeSwapReceiptDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostEqtypeSwapReceiptDao forRow( HostEqtypeSwapReceiptRow row ) throws java.lang.IllegalArgumentException {
        return (HostEqtypeSwapReceiptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEqtypeSwapReceiptRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostEqtypeSwapReceiptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostEqtypeSwapReceiptPK}やデータベースレコードとして挿入される{@@link HostEqtypeSwapReceiptParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEqtypeSwapReceiptRow.TYPE );
    }


  /** 
   * {@@link HostEqtypeSwapReceiptRow}を一意に特定する{@@link HostEqtypeSwapReceiptPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostEqtypeSwapReceiptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostEqtypeSwapReceiptParams}オブジェクトの主キーとして利用可能な{@@link HostEqtypeSwapReceiptPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostEqtypeSwapReceiptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostEqtypeSwapReceiptRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeSwapReceiptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeSwapReceiptRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeSwapReceiptPK pk = new HostEqtypeSwapReceiptPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostEqtypeSwapReceiptPKオブジェクトから{@@link HostEqtypeSwapReceiptRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostEqtypeSwapReceiptPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeSwapReceiptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeSwapReceiptRow findRowByPk( HostEqtypeSwapReceiptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEqtypeSwapReceiptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostEqtypeSwapReceiptRow)}を使用してください。 
   */
    public static HostEqtypeSwapReceiptDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeSwapReceiptPK pk = new HostEqtypeSwapReceiptPK( p_rowid );
        HostEqtypeSwapReceiptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostEqtypeSwapReceiptPK)}および{@@link #forRow(HostEqtypeSwapReceiptRow)}を使用してください。 
   */
    public static HostEqtypeSwapReceiptDao findDaoByPk( HostEqtypeSwapReceiptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeSwapReceiptRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_orderRequestNumber, and にて指定の値に一致する{@@link HostEqtypeSwapReceiptRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link HostEqtypeSwapReceiptRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeSwapReceiptRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(HostEqtypeSwapReceiptRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }

}
@
