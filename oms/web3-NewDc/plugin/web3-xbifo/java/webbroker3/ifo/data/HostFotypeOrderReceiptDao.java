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
filename	HostFotypeOrderReceiptDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link HostFotypeOrderReceiptDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostFotypeOrderReceiptRow}インスタンスへ関連付けることができます。 
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
 * @@see HostFotypeOrderReceiptPK 
 * @@see HostFotypeOrderReceiptRow 
 */
public class HostFotypeOrderReceiptDao extends DataAccessObject {


  /** 
   * この{@@link HostFotypeOrderReceiptDao}に関連する型指定のRowオブジェクト 
   */
    private HostFotypeOrderReceiptRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostFotypeOrderReceiptRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostFotypeOrderReceiptDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostFotypeOrderReceiptDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostFotypeOrderReceiptRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFotypeOrderReceiptRow )
                return new HostFotypeOrderReceiptDao( (HostFotypeOrderReceiptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFotypeOrderReceiptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFotypeOrderReceiptRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostFotypeOrderReceiptRow}オブジェクト 
    */
    protected HostFotypeOrderReceiptDao( HostFotypeOrderReceiptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostFotypeOrderReceiptRow}オブジェクトを取得します。
   */
    public HostFotypeOrderReceiptRow getHostFotypeOrderReceiptRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostFotypeOrderReceiptRow}オブジェクトから{@@link HostFotypeOrderReceiptDao}オブジェクトを作成します。 
   * これは実際の{@@link HostFotypeOrderReceiptRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostFotypeOrderReceiptDao}取得のために指定の{@@link HostFotypeOrderReceiptRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostFotypeOrderReceiptDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostFotypeOrderReceiptDao forRow( HostFotypeOrderReceiptRow row ) throws java.lang.IllegalArgumentException {
        return (HostFotypeOrderReceiptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFotypeOrderReceiptRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostFotypeOrderReceiptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostFotypeOrderReceiptPK}やデータベースレコードとして挿入される{@@link HostFotypeOrderReceiptParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFotypeOrderReceiptRow.TYPE );
    }


  /** 
   * {@@link HostFotypeOrderReceiptRow}を一意に特定する{@@link HostFotypeOrderReceiptPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostFotypeOrderReceiptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostFotypeOrderReceiptParams}オブジェクトの主キーとして利用可能な{@@link HostFotypeOrderReceiptPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostFotypeOrderReceiptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostFotypeOrderReceiptRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFotypeOrderReceiptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFotypeOrderReceiptRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderReceiptPK pk = new HostFotypeOrderReceiptPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostFotypeOrderReceiptPKオブジェクトから{@@link HostFotypeOrderReceiptRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostFotypeOrderReceiptPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFotypeOrderReceiptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFotypeOrderReceiptRow findRowByPk( HostFotypeOrderReceiptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFotypeOrderReceiptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostFotypeOrderReceiptRow)}を使用してください。 
   */
    public static HostFotypeOrderReceiptDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderReceiptPK pk = new HostFotypeOrderReceiptPK( p_rowid );
        HostFotypeOrderReceiptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostFotypeOrderReceiptPK)}および{@@link #forRow(HostFotypeOrderReceiptRow)}を使用してください。 
   */
    public static HostFotypeOrderReceiptDao findDaoByPk( HostFotypeOrderReceiptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderReceiptRow row = findRowByPk( pk );
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
   * p_orderRequestNumber, and にて指定の値に一致する{@@link HostFotypeOrderReceiptRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link HostFotypeOrderReceiptRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderReceiptRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(HostFotypeOrderReceiptRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }

}
@
