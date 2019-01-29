head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostAttentionInfoNotifyDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.eqtypeadmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * {@@link HostAttentionInfoNotifyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostAttentionInfoNotifyRow}インスタンスへ関連付けることができます。 
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
 * @@see HostAttentionInfoNotifyPK 
 * @@see HostAttentionInfoNotifyRow 
 */
public class HostAttentionInfoNotifyDao extends DataAccessObject {


  /** 
   * この{@@link HostAttentionInfoNotifyDao}に関連する型指定のRowオブジェクト 
   */
    private HostAttentionInfoNotifyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostAttentionInfoNotifyRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostAttentionInfoNotifyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostAttentionInfoNotifyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostAttentionInfoNotifyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostAttentionInfoNotifyRow )
                return new HostAttentionInfoNotifyDao( (HostAttentionInfoNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostAttentionInfoNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostAttentionInfoNotifyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostAttentionInfoNotifyRow}オブジェクト 
    */
    protected HostAttentionInfoNotifyDao( HostAttentionInfoNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostAttentionInfoNotifyRow}オブジェクトを取得します。
   */
    public HostAttentionInfoNotifyRow getHostAttentionInfoNotifyRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostAttentionInfoNotifyRow}オブジェクトから{@@link HostAttentionInfoNotifyDao}オブジェクトを作成します。 
   * これは実際の{@@link HostAttentionInfoNotifyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostAttentionInfoNotifyDao}取得のために指定の{@@link HostAttentionInfoNotifyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostAttentionInfoNotifyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostAttentionInfoNotifyDao forRow( HostAttentionInfoNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (HostAttentionInfoNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostAttentionInfoNotifyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostAttentionInfoNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostAttentionInfoNotifyPK}やデータベースレコードとして挿入される{@@link HostAttentionInfoNotifyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostAttentionInfoNotifyRow.TYPE );
    }


  /** 
   * {@@link HostAttentionInfoNotifyRow}を一意に特定する{@@link HostAttentionInfoNotifyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostAttentionInfoNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostAttentionInfoNotifyParams}オブジェクトの主キーとして利用可能な{@@link HostAttentionInfoNotifyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostAttentionInfoNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostAttentionInfoNotifyRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostAttentionInfoNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostAttentionInfoNotifyRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAttentionInfoNotifyPK pk = new HostAttentionInfoNotifyPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostAttentionInfoNotifyPKオブジェクトから{@@link HostAttentionInfoNotifyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostAttentionInfoNotifyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostAttentionInfoNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostAttentionInfoNotifyRow findRowByPk( HostAttentionInfoNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostAttentionInfoNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostAttentionInfoNotifyRow)}を使用してください。 
   */
    public static HostAttentionInfoNotifyDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAttentionInfoNotifyPK pk = new HostAttentionInfoNotifyPK( p_rowid );
        HostAttentionInfoNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostAttentionInfoNotifyPK)}および{@@link #forRow(HostAttentionInfoNotifyRow)}を使用してください。 
   */
    public static HostAttentionInfoNotifyDao findDaoByPk( HostAttentionInfoNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAttentionInfoNotifyRow row = findRowByPk( pk );
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
