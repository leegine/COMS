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
filename	HostSellCancelDao.java;


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
 * {@@link HostSellCancelDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostSellCancelRow}インスタンスへ関連付けることができます。 
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
 * @@see HostSellCancelPK 
 * @@see HostSellCancelRow 
 */
public class HostSellCancelDao extends DataAccessObject {


  /** 
   * この{@@link HostSellCancelDao}に関連する型指定のRowオブジェクト 
   */
    private HostSellCancelRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostSellCancelRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostSellCancelDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostSellCancelDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostSellCancelRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostSellCancelRow )
                return new HostSellCancelDao( (HostSellCancelRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostSellCancelRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostSellCancelRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostSellCancelRow}オブジェクト 
    */
    protected HostSellCancelDao( HostSellCancelRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostSellCancelRow}オブジェクトを取得します。
   */
    public HostSellCancelRow getHostSellCancelRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostSellCancelRow}オブジェクトから{@@link HostSellCancelDao}オブジェクトを作成します。 
   * これは実際の{@@link HostSellCancelRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostSellCancelDao}取得のために指定の{@@link HostSellCancelRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostSellCancelDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostSellCancelDao forRow( HostSellCancelRow row ) throws java.lang.IllegalArgumentException {
        return (HostSellCancelDao) DataAccessObject.forRow( row );
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
