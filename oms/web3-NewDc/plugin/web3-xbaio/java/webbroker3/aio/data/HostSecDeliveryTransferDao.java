head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostSecDeliveryTransferDao.java;


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
 * {@@link HostSecDeliveryTransferDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostSecDeliveryTransferRow}インスタンスへ関連付けることができます。 
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
 * @@see HostSecDeliveryTransferPK 
 * @@see HostSecDeliveryTransferRow 
 */
public class HostSecDeliveryTransferDao extends DataAccessObject {


  /** 
   * この{@@link HostSecDeliveryTransferDao}に関連する型指定のRowオブジェクト 
   */
    private HostSecDeliveryTransferRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostSecDeliveryTransferRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostSecDeliveryTransferDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostSecDeliveryTransferDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostSecDeliveryTransferRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostSecDeliveryTransferRow )
                return new HostSecDeliveryTransferDao( (HostSecDeliveryTransferRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostSecDeliveryTransferRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostSecDeliveryTransferRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostSecDeliveryTransferRow}オブジェクト 
    */
    protected HostSecDeliveryTransferDao( HostSecDeliveryTransferRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostSecDeliveryTransferRow}オブジェクトを取得します。
   */
    public HostSecDeliveryTransferRow getHostSecDeliveryTransferRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostSecDeliveryTransferRow}オブジェクトから{@@link HostSecDeliveryTransferDao}オブジェクトを作成します。 
   * これは実際の{@@link HostSecDeliveryTransferRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostSecDeliveryTransferDao}取得のために指定の{@@link HostSecDeliveryTransferRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostSecDeliveryTransferDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostSecDeliveryTransferDao forRow( HostSecDeliveryTransferRow row ) throws java.lang.IllegalArgumentException {
        return (HostSecDeliveryTransferDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostSecDeliveryTransferRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostSecDeliveryTransferRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostSecDeliveryTransferPK}やデータベースレコードとして挿入される{@@link HostSecDeliveryTransferParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostSecDeliveryTransferRow.TYPE );
    }


  /** 
   * {@@link HostSecDeliveryTransferRow}を一意に特定する{@@link HostSecDeliveryTransferPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostSecDeliveryTransferRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostSecDeliveryTransferParams}オブジェクトの主キーとして利用可能な{@@link HostSecDeliveryTransferPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostSecDeliveryTransferPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostSecDeliveryTransferRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostSecDeliveryTransferRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostSecDeliveryTransferRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostSecDeliveryTransferPK pk = new HostSecDeliveryTransferPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostSecDeliveryTransferPKオブジェクトから{@@link HostSecDeliveryTransferRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostSecDeliveryTransferPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostSecDeliveryTransferRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostSecDeliveryTransferRow findRowByPk( HostSecDeliveryTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostSecDeliveryTransferRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostSecDeliveryTransferRow)}を使用してください。 
   */
    public static HostSecDeliveryTransferDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostSecDeliveryTransferPK pk = new HostSecDeliveryTransferPK( p_rowid );
        HostSecDeliveryTransferRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostSecDeliveryTransferPK)}および{@@link #forRow(HostSecDeliveryTransferRow)}を使用してください。 
   */
    public static HostSecDeliveryTransferDao findDaoByPk( HostSecDeliveryTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostSecDeliveryTransferRow row = findRowByPk( pk );
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
   * p_requestCode, p_status, and にて指定の値に一致する{@@link HostSecDeliveryTransferRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_status, and の値と一致する{@@link HostSecDeliveryTransferRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostSecDeliveryTransferRow.TYPE,
            "request_code=? and status=?",
            null,
            new Object[] { p_requestCode, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeStatus(String, String)}および{@@link #forRow(HostSecDeliveryTransferRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeStatus( p_requestCode, p_status ) );
    }

}
@
