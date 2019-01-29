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
filename	HostFeqCloseOrderNotifyDao.java;


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
 * {@@link HostFeqCloseOrderNotifyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostFeqCloseOrderNotifyRow}インスタンスへ関連付けることができます。 
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
 * @@see HostFeqCloseOrderNotifyPK 
 * @@see HostFeqCloseOrderNotifyRow 
 */
public class HostFeqCloseOrderNotifyDao extends DataAccessObject {


  /** 
   * この{@@link HostFeqCloseOrderNotifyDao}に関連する型指定のRowオブジェクト 
   */
    private HostFeqCloseOrderNotifyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostFeqCloseOrderNotifyRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostFeqCloseOrderNotifyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostFeqCloseOrderNotifyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostFeqCloseOrderNotifyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFeqCloseOrderNotifyRow )
                return new HostFeqCloseOrderNotifyDao( (HostFeqCloseOrderNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFeqCloseOrderNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFeqCloseOrderNotifyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostFeqCloseOrderNotifyRow}オブジェクト 
    */
    protected HostFeqCloseOrderNotifyDao( HostFeqCloseOrderNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostFeqCloseOrderNotifyRow}オブジェクトを取得します。
   */
    public HostFeqCloseOrderNotifyRow getHostFeqCloseOrderNotifyRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostFeqCloseOrderNotifyRow}オブジェクトから{@@link HostFeqCloseOrderNotifyDao}オブジェクトを作成します。 
   * これは実際の{@@link HostFeqCloseOrderNotifyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostFeqCloseOrderNotifyDao}取得のために指定の{@@link HostFeqCloseOrderNotifyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostFeqCloseOrderNotifyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostFeqCloseOrderNotifyDao forRow( HostFeqCloseOrderNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (HostFeqCloseOrderNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFeqCloseOrderNotifyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostFeqCloseOrderNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostFeqCloseOrderNotifyPK}やデータベースレコードとして挿入される{@@link HostFeqCloseOrderNotifyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFeqCloseOrderNotifyRow.TYPE );
    }


  /** 
   * {@@link HostFeqCloseOrderNotifyRow}を一意に特定する{@@link HostFeqCloseOrderNotifyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostFeqCloseOrderNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostFeqCloseOrderNotifyParams}オブジェクトの主キーとして利用可能な{@@link HostFeqCloseOrderNotifyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostFeqCloseOrderNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostFeqCloseOrderNotifyRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFeqCloseOrderNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFeqCloseOrderNotifyRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqCloseOrderNotifyPK pk = new HostFeqCloseOrderNotifyPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostFeqCloseOrderNotifyPKオブジェクトから{@@link HostFeqCloseOrderNotifyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostFeqCloseOrderNotifyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFeqCloseOrderNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFeqCloseOrderNotifyRow findRowByPk( HostFeqCloseOrderNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFeqCloseOrderNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostFeqCloseOrderNotifyRow)}を使用してください。 
   */
    public static HostFeqCloseOrderNotifyDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqCloseOrderNotifyPK pk = new HostFeqCloseOrderNotifyPK( p_rowid );
        HostFeqCloseOrderNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostFeqCloseOrderNotifyPK)}および{@@link #forRow(HostFeqCloseOrderNotifyRow)}を使用してください。 
   */
    public static HostFeqCloseOrderNotifyDao findDaoByPk( HostFeqCloseOrderNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqCloseOrderNotifyRow row = findRowByPk( pk );
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
