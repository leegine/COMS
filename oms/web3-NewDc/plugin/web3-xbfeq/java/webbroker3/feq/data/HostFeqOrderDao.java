head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFeqOrderDao.java;


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
 * {@@link HostFeqOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostFeqOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see HostFeqOrderPK 
 * @@see HostFeqOrderRow 
 */
public class HostFeqOrderDao extends DataAccessObject {


  /** 
   * この{@@link HostFeqOrderDao}に関連する型指定のRowオブジェクト 
   */
    private HostFeqOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostFeqOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostFeqOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostFeqOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostFeqOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFeqOrderRow )
                return new HostFeqOrderDao( (HostFeqOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFeqOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFeqOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostFeqOrderRow}オブジェクト 
    */
    protected HostFeqOrderDao( HostFeqOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostFeqOrderRow}オブジェクトを取得します。
   */
    public HostFeqOrderRow getHostFeqOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostFeqOrderRow}オブジェクトから{@@link HostFeqOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link HostFeqOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostFeqOrderDao}取得のために指定の{@@link HostFeqOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostFeqOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostFeqOrderDao forRow( HostFeqOrderRow row ) throws java.lang.IllegalArgumentException {
        return (HostFeqOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFeqOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostFeqOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostFeqOrderPK}やデータベースレコードとして挿入される{@@link HostFeqOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFeqOrderRow.TYPE );
    }


  /** 
   * {@@link HostFeqOrderRow}を一意に特定する{@@link HostFeqOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostFeqOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostFeqOrderParams}オブジェクトの主キーとして利用可能な{@@link HostFeqOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostFeqOrderPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostFeqOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFeqOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFeqOrderRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqOrderPK pk = new HostFeqOrderPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostFeqOrderPKオブジェクトから{@@link HostFeqOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostFeqOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFeqOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFeqOrderRow findRowByPk( HostFeqOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFeqOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostFeqOrderRow)}を使用してください。 
   */
    public static HostFeqOrderDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqOrderPK pk = new HostFeqOrderPK( p_rowid );
        HostFeqOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostFeqOrderPK)}および{@@link #forRow(HostFeqOrderRow)}を使用してください。 
   */
    public static HostFeqOrderDao findDaoByPk( HostFeqOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqOrderRow row = findRowByPk( pk );
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
