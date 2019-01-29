head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostMrfOrderCancelDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.xbruito.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.xbruito.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link HostMrfOrderCancelDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostMrfOrderCancelRow}インスタンスへ関連付けることができます。 
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
 * @@see HostMrfOrderCancelPK 
 * @@see HostMrfOrderCancelRow 
 */
public class HostMrfOrderCancelDao extends DataAccessObject {


  /** 
   * この{@@link HostMrfOrderCancelDao}に関連する型指定のRowオブジェクト 
   */
    private HostMrfOrderCancelRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostMrfOrderCancelRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostMrfOrderCancelDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostMrfOrderCancelDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostMrfOrderCancelRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostMrfOrderCancelRow )
                return new HostMrfOrderCancelDao( (HostMrfOrderCancelRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostMrfOrderCancelRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostMrfOrderCancelRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostMrfOrderCancelRow}オブジェクト 
    */
    protected HostMrfOrderCancelDao( HostMrfOrderCancelRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostMrfOrderCancelRow}オブジェクトを取得します。
   */
    public HostMrfOrderCancelRow getHostMrfOrderCancelRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostMrfOrderCancelRow}オブジェクトから{@@link HostMrfOrderCancelDao}オブジェクトを作成します。 
   * これは実際の{@@link HostMrfOrderCancelRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostMrfOrderCancelDao}取得のために指定の{@@link HostMrfOrderCancelRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostMrfOrderCancelDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostMrfOrderCancelDao forRow( HostMrfOrderCancelRow row ) throws java.lang.IllegalArgumentException {
        return (HostMrfOrderCancelDao) DataAccessObject.forRow( row );
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

        // (none) 

}
@
