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
filename	HostRuitoSellDao.java;


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
 * {@@link HostRuitoSellDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostRuitoSellRow}インスタンスへ関連付けることができます。 
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
 * @@see HostRuitoSellPK 
 * @@see HostRuitoSellRow 
 */
public class HostRuitoSellDao extends DataAccessObject {


  /** 
   * この{@@link HostRuitoSellDao}に関連する型指定のRowオブジェクト 
   */
    private HostRuitoSellRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostRuitoSellRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostRuitoSellDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostRuitoSellDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostRuitoSellRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostRuitoSellRow )
                return new HostRuitoSellDao( (HostRuitoSellRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostRuitoSellRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostRuitoSellRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostRuitoSellRow}オブジェクト 
    */
    protected HostRuitoSellDao( HostRuitoSellRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostRuitoSellRow}オブジェクトを取得します。
   */
    public HostRuitoSellRow getHostRuitoSellRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostRuitoSellRow}オブジェクトから{@@link HostRuitoSellDao}オブジェクトを作成します。 
   * これは実際の{@@link HostRuitoSellRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostRuitoSellDao}取得のために指定の{@@link HostRuitoSellRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostRuitoSellDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostRuitoSellDao forRow( HostRuitoSellRow row ) throws java.lang.IllegalArgumentException {
        return (HostRuitoSellDao) DataAccessObject.forRow( row );
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
