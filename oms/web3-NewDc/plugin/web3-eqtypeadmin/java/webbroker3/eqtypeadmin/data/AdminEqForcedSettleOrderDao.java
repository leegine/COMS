head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdminEqForcedSettleOrderDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.eqtypeadmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * {@@link AdminEqForcedSettleOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AdminEqForcedSettleOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see AdminEqForcedSettleOrderPK 
 * @@see AdminEqForcedSettleOrderRow 
 */
public class AdminEqForcedSettleOrderDao extends DataAccessObject {


  /** 
   * この{@@link AdminEqForcedSettleOrderDao}に関連する型指定のRowオブジェクト 
   */
    private AdminEqForcedSettleOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AdminEqForcedSettleOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link AdminEqForcedSettleOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AdminEqForcedSettleOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AdminEqForcedSettleOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdminEqForcedSettleOrderRow )
                return new AdminEqForcedSettleOrderDao( (AdminEqForcedSettleOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdminEqForcedSettleOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdminEqForcedSettleOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AdminEqForcedSettleOrderRow}オブジェクト 
    */
    protected AdminEqForcedSettleOrderDao( AdminEqForcedSettleOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AdminEqForcedSettleOrderRow}オブジェクトを取得します。
   */
    public AdminEqForcedSettleOrderRow getAdminEqForcedSettleOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link AdminEqForcedSettleOrderRow}オブジェクトから{@@link AdminEqForcedSettleOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link AdminEqForcedSettleOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AdminEqForcedSettleOrderDao}取得のために指定の{@@link AdminEqForcedSettleOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AdminEqForcedSettleOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AdminEqForcedSettleOrderDao forRow( AdminEqForcedSettleOrderRow row ) throws java.lang.IllegalArgumentException {
        return (AdminEqForcedSettleOrderDao) DataAccessObject.forRow( row );
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
