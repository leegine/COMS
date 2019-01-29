head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.43.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostForeignCashTransferDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link HostForeignCashTransferDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostForeignCashTransferRow}インスタンスへ関連付けることができます。 
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
 * @@see HostForeignCashTransferPK 
 * @@see HostForeignCashTransferRow 
 */
public class HostForeignCashTransferDao extends DataAccessObject {


  /** 
   * この{@@link HostForeignCashTransferDao}に関連する型指定のRowオブジェクト 
   */
    private HostForeignCashTransferRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostForeignCashTransferRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostForeignCashTransferDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostForeignCashTransferDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostForeignCashTransferRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostForeignCashTransferRow )
                return new HostForeignCashTransferDao( (HostForeignCashTransferRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostForeignCashTransferRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostForeignCashTransferRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostForeignCashTransferRow}オブジェクト 
    */
    protected HostForeignCashTransferDao( HostForeignCashTransferRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostForeignCashTransferRow}オブジェクトを取得します。
   */
    public HostForeignCashTransferRow getHostForeignCashTransferRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostForeignCashTransferRow}オブジェクトから{@@link HostForeignCashTransferDao}オブジェクトを作成します。 
   * これは実際の{@@link HostForeignCashTransferRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostForeignCashTransferDao}取得のために指定の{@@link HostForeignCashTransferRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostForeignCashTransferDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostForeignCashTransferDao forRow( HostForeignCashTransferRow row ) throws java.lang.IllegalArgumentException {
        return (HostForeignCashTransferDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostForeignCashTransferRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostForeignCashTransferRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostForeignCashTransferPK}やデータベースレコードとして挿入される{@@link HostForeignCashTransferParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostForeignCashTransferRow.TYPE );
    }


  /** 
   * {@@link HostForeignCashTransferRow}を一意に特定する{@@link HostForeignCashTransferPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostForeignCashTransferRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostForeignCashTransferParams}オブジェクトの主キーとして利用可能な{@@link HostForeignCashTransferPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostForeignCashTransferPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostForeignCashTransferRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostForeignCashTransferRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostForeignCashTransferRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostForeignCashTransferPK pk = new HostForeignCashTransferPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostForeignCashTransferPKオブジェクトから{@@link HostForeignCashTransferRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostForeignCashTransferPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostForeignCashTransferRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostForeignCashTransferRow findRowByPk( HostForeignCashTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostForeignCashTransferRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostForeignCashTransferRow)}を使用してください。 
   */
    public static HostForeignCashTransferDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostForeignCashTransferPK pk = new HostForeignCashTransferPK( p_rowid );
        HostForeignCashTransferRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostForeignCashTransferPK)}および{@@link #forRow(HostForeignCashTransferRow)}を使用してください。 
   */
    public static HostForeignCashTransferDao findDaoByPk( HostForeignCashTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostForeignCashTransferRow row = findRowByPk( pk );
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
   * p_requestCode, p_status, and にて指定の値に一致する{@@link HostForeignCashTransferRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_status, and の値と一致する{@@link HostForeignCashTransferRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostForeignCashTransferRow.TYPE,
            "request_code=? and status=?",
            null,
            new Object[] { p_requestCode, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeStatus(String, String)}および{@@link #forRow(HostForeignCashTransferRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeStatus( p_requestCode, p_status ) );
    }

}
@
