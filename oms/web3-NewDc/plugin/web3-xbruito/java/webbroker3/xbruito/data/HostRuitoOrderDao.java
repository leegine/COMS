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
filename	HostRuitoOrderDao.java;


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
 * {@@link HostRuitoOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostRuitoOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see HostRuitoOrderPK 
 * @@see HostRuitoOrderRow 
 */
public class HostRuitoOrderDao extends DataAccessObject {


  /** 
   * この{@@link HostRuitoOrderDao}に関連する型指定のRowオブジェクト 
   */
    private HostRuitoOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostRuitoOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostRuitoOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostRuitoOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostRuitoOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostRuitoOrderRow )
                return new HostRuitoOrderDao( (HostRuitoOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostRuitoOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostRuitoOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostRuitoOrderRow}オブジェクト 
    */
    protected HostRuitoOrderDao( HostRuitoOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostRuitoOrderRow}オブジェクトを取得します。
   */
    public HostRuitoOrderRow getHostRuitoOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostRuitoOrderRow}オブジェクトから{@@link HostRuitoOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link HostRuitoOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostRuitoOrderDao}取得のために指定の{@@link HostRuitoOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostRuitoOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostRuitoOrderDao forRow( HostRuitoOrderRow row ) throws java.lang.IllegalArgumentException {
        return (HostRuitoOrderDao) DataAccessObject.forRow( row );
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
