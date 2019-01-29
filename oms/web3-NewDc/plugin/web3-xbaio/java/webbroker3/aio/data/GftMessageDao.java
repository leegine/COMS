head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	GftMessageDao.java;


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
 * {@@link GftMessageDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link GftMessageRow}インスタンスへ関連付けることができます。 
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
 * @@see GftMessagePK 
 * @@see GftMessageRow 
 */
public class GftMessageDao extends DataAccessObject {


  /** 
   * この{@@link GftMessageDao}に関連する型指定のRowオブジェクト 
   */
    private GftMessageRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link GftMessageRow}と仮定される{@@link DataAccessObject}から新たに{@@link GftMessageDao}を返します。 
         * @@return 指定のRowに結びつく{@@link GftMessageDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link GftMessageRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof GftMessageRow )
                return new GftMessageDao( (GftMessageRow) row );
            throw new java.lang.IllegalArgumentException( "Not a GftMessageRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link GftMessageRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link GftMessageRow}オブジェクト 
    */
    protected GftMessageDao( GftMessageRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link GftMessageRow}オブジェクトを取得します。
   */
    public GftMessageRow getGftMessageRow() {
        return row;
    }


  /** 
   * 指定の{@@link GftMessageRow}オブジェクトから{@@link GftMessageDao}オブジェクトを作成します。 
   * これは実際の{@@link GftMessageRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link GftMessageDao}取得のために指定の{@@link GftMessageRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link GftMessageDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static GftMessageDao forRow( GftMessageRow row ) throws java.lang.IllegalArgumentException {
        return (GftMessageDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


      // (this object has no primary key components)


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


      // (this object has no primary key components)


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
   * p_messageDiv, p_cpy, p_brn, p_req, and にて指定の値に一致する{@@link GftMessageRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_messageDiv 検索対象であるp_messageDivフィールドの値
   * @@param p_cpy 検索対象であるp_cpyフィールドの値
   * @@param p_brn 検索対象であるp_brnフィールドの値
   * @@param p_req 検索対象であるp_reqフィールドの値
   * 
   * @@return 引数指定のp_messageDiv, p_cpy, p_brn, p_req, and の値と一致する{@@link GftMessageRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByMessageDivCpyBrnReq( String p_messageDiv, String p_cpy, String p_brn, String p_req ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            GftMessageRow.TYPE,
            "message_div=? and cpy=? and brn=? and req=?",
            null,
            new Object[] { p_messageDiv, p_cpy, p_brn, p_req } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByMessageDivCpyBrnReq(String, String, String, String)}および{@@link #forRow(GftMessageRow)}を使用してください。 
   */
    public static List findDaosByMessageDivCpyBrnReq( String p_messageDiv, String p_cpy, String p_brn, String p_req ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMessageDivCpyBrnReq( p_messageDiv, p_cpy, p_brn, p_req ) );
    }

}
@
