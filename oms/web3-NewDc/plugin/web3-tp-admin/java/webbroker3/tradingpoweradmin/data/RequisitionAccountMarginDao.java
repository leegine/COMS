head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	RequisitionAccountMarginDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpoweradmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link RequisitionAccountMarginDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RequisitionAccountMarginRow}インスタンスへ関連付けることができます。 
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
 * @@see RequisitionAccountMarginPK 
 * @@see RequisitionAccountMarginRow 
 */
public class RequisitionAccountMarginDao extends DataAccessObject {


  /** 
   * この{@@link RequisitionAccountMarginDao}に関連する型指定のRowオブジェクト 
   */
    private RequisitionAccountMarginRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RequisitionAccountMarginRow}と仮定される{@@link DataAccessObject}から新たに{@@link RequisitionAccountMarginDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RequisitionAccountMarginDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RequisitionAccountMarginRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RequisitionAccountMarginRow )
                return new RequisitionAccountMarginDao( (RequisitionAccountMarginRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RequisitionAccountMarginRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RequisitionAccountMarginRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RequisitionAccountMarginRow}オブジェクト 
    */
    protected RequisitionAccountMarginDao( RequisitionAccountMarginRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RequisitionAccountMarginRow}オブジェクトを取得します。
   */
    public RequisitionAccountMarginRow getRequisitionAccountMarginRow() {
        return row;
    }


  /** 
   * 指定の{@@link RequisitionAccountMarginRow}オブジェクトから{@@link RequisitionAccountMarginDao}オブジェクトを作成します。 
   * これは実際の{@@link RequisitionAccountMarginRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RequisitionAccountMarginDao}取得のために指定の{@@link RequisitionAccountMarginRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RequisitionAccountMarginDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RequisitionAccountMarginDao forRow( RequisitionAccountMarginRow row ) throws java.lang.IllegalArgumentException {
        return (RequisitionAccountMarginDao) DataAccessObject.forRow( row );
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
