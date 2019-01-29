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
filename	HostEqtypeSwapDao.java;


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
 * {@@link HostEqtypeSwapDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostEqtypeSwapRow}インスタンスへ関連付けることができます。 
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
 * @@see HostEqtypeSwapPK 
 * @@see HostEqtypeSwapRow 
 */
public class HostEqtypeSwapDao extends DataAccessObject {


  /** 
   * この{@@link HostEqtypeSwapDao}に関連する型指定のRowオブジェクト 
   */
    private HostEqtypeSwapRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostEqtypeSwapRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostEqtypeSwapDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostEqtypeSwapDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostEqtypeSwapRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEqtypeSwapRow )
                return new HostEqtypeSwapDao( (HostEqtypeSwapRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEqtypeSwapRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEqtypeSwapRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostEqtypeSwapRow}オブジェクト 
    */
    protected HostEqtypeSwapDao( HostEqtypeSwapRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostEqtypeSwapRow}オブジェクトを取得します。
   */
    public HostEqtypeSwapRow getHostEqtypeSwapRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostEqtypeSwapRow}オブジェクトから{@@link HostEqtypeSwapDao}オブジェクトを作成します。 
   * これは実際の{@@link HostEqtypeSwapRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostEqtypeSwapDao}取得のために指定の{@@link HostEqtypeSwapRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostEqtypeSwapDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostEqtypeSwapDao forRow( HostEqtypeSwapRow row ) throws java.lang.IllegalArgumentException {
        return (HostEqtypeSwapDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEqtypeSwapRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostEqtypeSwapRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostEqtypeSwapPK}やデータベースレコードとして挿入される{@@link HostEqtypeSwapParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEqtypeSwapRow.TYPE );
    }


  /** 
   * {@@link HostEqtypeSwapRow}を一意に特定する{@@link HostEqtypeSwapPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostEqtypeSwapRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostEqtypeSwapParams}オブジェクトの主キーとして利用可能な{@@link HostEqtypeSwapPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostEqtypeSwapPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostEqtypeSwapRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeSwapRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeSwapRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeSwapPK pk = new HostEqtypeSwapPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostEqtypeSwapPKオブジェクトから{@@link HostEqtypeSwapRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostEqtypeSwapPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeSwapRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeSwapRow findRowByPk( HostEqtypeSwapPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEqtypeSwapRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostEqtypeSwapRow)}を使用してください。 
   */
    public static HostEqtypeSwapDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeSwapPK pk = new HostEqtypeSwapPK( p_rowid );
        HostEqtypeSwapRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostEqtypeSwapPK)}および{@@link #forRow(HostEqtypeSwapRow)}を使用してください。 
   */
    public static HostEqtypeSwapDao findDaoByPk( HostEqtypeSwapPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeSwapRow row = findRowByPk( pk );
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
   * p_orderRequestNumber, and にて指定の値に一致する{@@link HostEqtypeSwapRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link HostEqtypeSwapRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeSwapRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(HostEqtypeSwapRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }

}
@
