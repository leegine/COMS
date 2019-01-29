head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	HostLockRegiAcceptDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link HostLockRegiAcceptDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostLockRegiAcceptRow}インスタンスへ関連付けることができます。 
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
 * @@see HostLockRegiAcceptPK 
 * @@see HostLockRegiAcceptRow 
 */
public class HostLockRegiAcceptDao extends DataAccessObject {


  /** 
   * この{@@link HostLockRegiAcceptDao}に関連する型指定のRowオブジェクト 
   */
    private HostLockRegiAcceptRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostLockRegiAcceptRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostLockRegiAcceptDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostLockRegiAcceptDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostLockRegiAcceptRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostLockRegiAcceptRow )
                return new HostLockRegiAcceptDao( (HostLockRegiAcceptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostLockRegiAcceptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostLockRegiAcceptRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostLockRegiAcceptRow}オブジェクト 
    */
    protected HostLockRegiAcceptDao( HostLockRegiAcceptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostLockRegiAcceptRow}オブジェクトを取得します。
   */
    public HostLockRegiAcceptRow getHostLockRegiAcceptRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostLockRegiAcceptRow}オブジェクトから{@@link HostLockRegiAcceptDao}オブジェクトを作成します。 
   * これは実際の{@@link HostLockRegiAcceptRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostLockRegiAcceptDao}取得のために指定の{@@link HostLockRegiAcceptRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostLockRegiAcceptDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostLockRegiAcceptDao forRow( HostLockRegiAcceptRow row ) throws java.lang.IllegalArgumentException {
        return (HostLockRegiAcceptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostLockRegiAcceptRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostLockRegiAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostLockRegiAcceptPK}やデータベースレコードとして挿入される{@@link HostLockRegiAcceptParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostLockRegiAcceptRow.TYPE );
    }


  /** 
   * {@@link HostLockRegiAcceptRow}を一意に特定する{@@link HostLockRegiAcceptPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostLockRegiAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostLockRegiAcceptParams}オブジェクトの主キーとして利用可能な{@@link HostLockRegiAcceptPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostLockRegiAcceptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostLockRegiAcceptRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostLockRegiAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostLockRegiAcceptRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegiAcceptPK pk = new HostLockRegiAcceptPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostLockRegiAcceptPKオブジェクトから{@@link HostLockRegiAcceptRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostLockRegiAcceptPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostLockRegiAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostLockRegiAcceptRow findRowByPk( HostLockRegiAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostLockRegiAcceptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostLockRegiAcceptRow)}を使用してください。 
   */
    public static HostLockRegiAcceptDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegiAcceptPK pk = new HostLockRegiAcceptPK( p_rowid );
        HostLockRegiAcceptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostLockRegiAcceptPK)}および{@@link #forRow(HostLockRegiAcceptRow)}を使用してください。 
   */
    public static HostLockRegiAcceptDao findDaoByPk( HostLockRegiAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegiAcceptRow row = findRowByPk( pk );
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
