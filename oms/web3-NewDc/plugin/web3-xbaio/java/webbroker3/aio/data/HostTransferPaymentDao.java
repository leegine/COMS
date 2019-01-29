head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.44.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostTransferPaymentDao.java;


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
 * {@@link HostTransferPaymentDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostTransferPaymentRow}インスタンスへ関連付けることができます。 
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
 * @@see HostTransferPaymentPK 
 * @@see HostTransferPaymentRow 
 */
public class HostTransferPaymentDao extends DataAccessObject {


  /** 
   * この{@@link HostTransferPaymentDao}に関連する型指定のRowオブジェクト 
   */
    private HostTransferPaymentRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostTransferPaymentRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostTransferPaymentDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostTransferPaymentDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostTransferPaymentRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostTransferPaymentRow )
                return new HostTransferPaymentDao( (HostTransferPaymentRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostTransferPaymentRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostTransferPaymentRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostTransferPaymentRow}オブジェクト 
    */
    protected HostTransferPaymentDao( HostTransferPaymentRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostTransferPaymentRow}オブジェクトを取得します。
   */
    public HostTransferPaymentRow getHostTransferPaymentRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostTransferPaymentRow}オブジェクトから{@@link HostTransferPaymentDao}オブジェクトを作成します。 
   * これは実際の{@@link HostTransferPaymentRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostTransferPaymentDao}取得のために指定の{@@link HostTransferPaymentRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostTransferPaymentDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostTransferPaymentDao forRow( HostTransferPaymentRow row ) throws java.lang.IllegalArgumentException {
        return (HostTransferPaymentDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostTransferPaymentRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostTransferPaymentRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostTransferPaymentPK}やデータベースレコードとして挿入される{@@link HostTransferPaymentParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostTransferPaymentRow.TYPE );
    }


  /** 
   * {@@link HostTransferPaymentRow}を一意に特定する{@@link HostTransferPaymentPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostTransferPaymentRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostTransferPaymentParams}オブジェクトの主キーとして利用可能な{@@link HostTransferPaymentPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostTransferPaymentPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostTransferPaymentRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostTransferPaymentRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostTransferPaymentRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferPaymentPK pk = new HostTransferPaymentPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostTransferPaymentPKオブジェクトから{@@link HostTransferPaymentRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostTransferPaymentPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostTransferPaymentRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostTransferPaymentRow findRowByPk( HostTransferPaymentPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostTransferPaymentRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostTransferPaymentRow)}を使用してください。 
   */
    public static HostTransferPaymentDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferPaymentPK pk = new HostTransferPaymentPK( p_rowid );
        HostTransferPaymentRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostTransferPaymentPK)}および{@@link #forRow(HostTransferPaymentRow)}を使用してください。 
   */
    public static HostTransferPaymentDao findDaoByPk( HostTransferPaymentPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferPaymentRow row = findRowByPk( pk );
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
   * p_requestCode, p_transferFlag, p_status, and にて指定の値に一致する{@@link HostTransferPaymentRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_transferFlag 検索対象であるp_transferFlagフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_transferFlag, p_status, and の値と一致する{@@link HostTransferPaymentRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeTransferFlagStatus( String p_requestCode, String p_transferFlag, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostTransferPaymentRow.TYPE,
            "request_code=? and transfer_flag=? and status=?",
            null,
            new Object[] { p_requestCode, p_transferFlag, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeTransferFlagStatus(String, String, String)}および{@@link #forRow(HostTransferPaymentRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeTransferFlagStatus( String p_requestCode, String p_transferFlag, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeTransferFlagStatus( p_requestCode, p_transferFlag, p_status ) );
    }

}
@
