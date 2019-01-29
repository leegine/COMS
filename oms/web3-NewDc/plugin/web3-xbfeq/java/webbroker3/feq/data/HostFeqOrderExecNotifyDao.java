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
filename	HostFeqOrderExecNotifyDao.java;


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
 * {@@link HostFeqOrderExecNotifyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostFeqOrderExecNotifyRow}インスタンスへ関連付けることができます。 
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
 * @@see HostFeqOrderExecNotifyPK 
 * @@see HostFeqOrderExecNotifyRow 
 */
public class HostFeqOrderExecNotifyDao extends DataAccessObject {


  /** 
   * この{@@link HostFeqOrderExecNotifyDao}に関連する型指定のRowオブジェクト 
   */
    private HostFeqOrderExecNotifyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostFeqOrderExecNotifyRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostFeqOrderExecNotifyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostFeqOrderExecNotifyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostFeqOrderExecNotifyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFeqOrderExecNotifyRow )
                return new HostFeqOrderExecNotifyDao( (HostFeqOrderExecNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFeqOrderExecNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFeqOrderExecNotifyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostFeqOrderExecNotifyRow}オブジェクト 
    */
    protected HostFeqOrderExecNotifyDao( HostFeqOrderExecNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostFeqOrderExecNotifyRow}オブジェクトを取得します。
   */
    public HostFeqOrderExecNotifyRow getHostFeqOrderExecNotifyRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostFeqOrderExecNotifyRow}オブジェクトから{@@link HostFeqOrderExecNotifyDao}オブジェクトを作成します。 
   * これは実際の{@@link HostFeqOrderExecNotifyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostFeqOrderExecNotifyDao}取得のために指定の{@@link HostFeqOrderExecNotifyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostFeqOrderExecNotifyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostFeqOrderExecNotifyDao forRow( HostFeqOrderExecNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (HostFeqOrderExecNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFeqOrderExecNotifyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostFeqOrderExecNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostFeqOrderExecNotifyPK}やデータベースレコードとして挿入される{@@link HostFeqOrderExecNotifyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFeqOrderExecNotifyRow.TYPE );
    }


  /** 
   * {@@link HostFeqOrderExecNotifyRow}を一意に特定する{@@link HostFeqOrderExecNotifyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostFeqOrderExecNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostFeqOrderExecNotifyParams}オブジェクトの主キーとして利用可能な{@@link HostFeqOrderExecNotifyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostFeqOrderExecNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostFeqOrderExecNotifyRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFeqOrderExecNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFeqOrderExecNotifyRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqOrderExecNotifyPK pk = new HostFeqOrderExecNotifyPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostFeqOrderExecNotifyPKオブジェクトから{@@link HostFeqOrderExecNotifyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostFeqOrderExecNotifyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFeqOrderExecNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFeqOrderExecNotifyRow findRowByPk( HostFeqOrderExecNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFeqOrderExecNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostFeqOrderExecNotifyRow)}を使用してください。 
   */
    public static HostFeqOrderExecNotifyDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqOrderExecNotifyPK pk = new HostFeqOrderExecNotifyPK( p_rowid );
        HostFeqOrderExecNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostFeqOrderExecNotifyPK)}および{@@link #forRow(HostFeqOrderExecNotifyRow)}を使用してください。 
   */
    public static HostFeqOrderExecNotifyDao findDaoByPk( HostFeqOrderExecNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFeqOrderExecNotifyRow row = findRowByPk( pk );
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
