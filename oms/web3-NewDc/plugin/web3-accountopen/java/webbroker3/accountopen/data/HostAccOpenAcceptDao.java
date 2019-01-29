head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.20.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostAccOpenAcceptDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link HostAccOpenAcceptDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostAccOpenAcceptRow}インスタンスへ関連付けることができます。 
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
 * @@see HostAccOpenAcceptPK 
 * @@see HostAccOpenAcceptRow 
 */
public class HostAccOpenAcceptDao extends DataAccessObject {


  /** 
   * この{@@link HostAccOpenAcceptDao}に関連する型指定のRowオブジェクト 
   */
    private HostAccOpenAcceptRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostAccOpenAcceptRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostAccOpenAcceptDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostAccOpenAcceptDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostAccOpenAcceptRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostAccOpenAcceptRow )
                return new HostAccOpenAcceptDao( (HostAccOpenAcceptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostAccOpenAcceptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostAccOpenAcceptRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostAccOpenAcceptRow}オブジェクト 
    */
    protected HostAccOpenAcceptDao( HostAccOpenAcceptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostAccOpenAcceptRow}オブジェクトを取得します。
   */
    public HostAccOpenAcceptRow getHostAccOpenAcceptRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostAccOpenAcceptRow}オブジェクトから{@@link HostAccOpenAcceptDao}オブジェクトを作成します。 
   * これは実際の{@@link HostAccOpenAcceptRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostAccOpenAcceptDao}取得のために指定の{@@link HostAccOpenAcceptRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostAccOpenAcceptDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostAccOpenAcceptDao forRow( HostAccOpenAcceptRow row ) throws java.lang.IllegalArgumentException {
        return (HostAccOpenAcceptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostAccOpenAcceptRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostAccOpenAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostAccOpenAcceptPK}やデータベースレコードとして挿入される{@@link HostAccOpenAcceptParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostAccOpenAcceptRow.TYPE );
    }


  /** 
   * {@@link HostAccOpenAcceptRow}を一意に特定する{@@link HostAccOpenAcceptPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostAccOpenAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostAccOpenAcceptParams}オブジェクトの主キーとして利用可能な{@@link HostAccOpenAcceptPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostAccOpenAcceptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostAccOpenAcceptRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostAccOpenAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostAccOpenAcceptRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAccOpenAcceptPK pk = new HostAccOpenAcceptPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostAccOpenAcceptPKオブジェクトから{@@link HostAccOpenAcceptRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostAccOpenAcceptPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostAccOpenAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostAccOpenAcceptRow findRowByPk( HostAccOpenAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostAccOpenAcceptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostAccOpenAcceptRow)}を使用してください。 
   */
    public static HostAccOpenAcceptDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAccOpenAcceptPK pk = new HostAccOpenAcceptPK( p_rowid );
        HostAccOpenAcceptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostAccOpenAcceptPK)}および{@@link #forRow(HostAccOpenAcceptRow)}を使用してください。 
   */
    public static HostAccOpenAcceptDao findDaoByPk( HostAccOpenAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAccOpenAcceptRow row = findRowByPk( pk );
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
