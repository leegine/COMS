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
filename	HostFeqChangecancelOrderDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;

/** 
 * {@@link HostFeqChangecancelOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostFeqChangecancelOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see HostFeqChangecancelOrderPK 
 * @@see HostFeqChangecancelOrderRow 
 */
public class HostFeqChangecancelOrderDao extends DataAccessObject {


  /** 
   * この{@@link HostFeqChangecancelOrderDao}に関連する型指定のRowオブジェクト 
   */
    private HostFeqChangecancelOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostFeqChangecancelOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostFeqChangecancelOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostFeqChangecancelOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostFeqChangecancelOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFeqChangecancelOrderRow )
                return new HostFeqChangecancelOrderDao( (HostFeqChangecancelOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFeqChangecancelOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFeqChangecancelOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostFeqChangecancelOrderRow}オブジェクト 
    */
    protected HostFeqChangecancelOrderDao( HostFeqChangecancelOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostFeqChangecancelOrderRow}オブジェクトを取得します。
   */
    public HostFeqChangecancelOrderRow getHostFeqChangecancelOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostFeqChangecancelOrderRow}オブジェクトから{@@link HostFeqChangecancelOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link HostFeqChangecancelOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostFeqChangecancelOrderDao}取得のために指定の{@@link HostFeqChangecancelOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostFeqChangecancelOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostFeqChangecancelOrderDao forRow( HostFeqChangecancelOrderRow row ) throws java.lang.IllegalArgumentException {
        return (HostFeqChangecancelOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFeqChangecancelOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostFeqChangecancelOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostFeqChangecancelOrderPK}やデータベースレコードとして挿入される{@@link HostFeqChangecancelOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFeqChangecancelOrderRow.TYPE );
    }


  /** 
   * {@@link HostFeqChangecancelOrderRow}を一意に特定する{@@link HostFeqChangecancelOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostFeqChangecancelOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostFeqChangecancelOrderParams}オブジェクトの主キーとして利用可能な{@@link HostFeqChangecancelOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostFeqChangecancelOrderPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostFeqChangecancelOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFeqChangecancelOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFeqChangecancelOrderRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqChangecancelOrderPK pk = new HostFeqChangecancelOrderPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostFeqChangecancelOrderPKオブジェクトから{@@link HostFeqChangecancelOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostFeqChangecancelOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFeqChangecancelOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFeqChangecancelOrderRow findRowByPk( HostFeqChangecancelOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFeqChangecancelOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostFeqChangecancelOrderRow)}を使用してください。 
   */
    public static HostFeqChangecancelOrderDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqChangecancelOrderPK pk = new HostFeqChangecancelOrderPK( p_rowid );
        HostFeqChangecancelOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostFeqChangecancelOrderPK)}および{@@link #forRow(HostFeqChangecancelOrderRow)}を使用してください。 
   */
    public static HostFeqChangecancelOrderDao findDaoByPk( HostFeqChangecancelOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqChangecancelOrderRow row = findRowByPk( pk );
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

        // (none) 

}
@
