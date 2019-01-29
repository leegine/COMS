head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdmintoEqtypeOrderUnitDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.admintriggerorder.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AdmintoEqtypeOrderUnitDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AdmintoEqtypeOrderUnitRow}インスタンスへ関連付けることができます。 
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
 * @@see AdmintoEqtypeOrderUnitPK 
 * @@see AdmintoEqtypeOrderUnitRow 
 */
public class AdmintoEqtypeOrderUnitDao extends DataAccessObject {


  /** 
   * この{@@link AdmintoEqtypeOrderUnitDao}に関連する型指定のRowオブジェクト 
   */
    private AdmintoEqtypeOrderUnitRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AdmintoEqtypeOrderUnitRow}と仮定される{@@link DataAccessObject}から新たに{@@link AdmintoEqtypeOrderUnitDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AdmintoEqtypeOrderUnitDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AdmintoEqtypeOrderUnitRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdmintoEqtypeOrderUnitRow )
                return new AdmintoEqtypeOrderUnitDao( (AdmintoEqtypeOrderUnitRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdmintoEqtypeOrderUnitRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdmintoEqtypeOrderUnitRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AdmintoEqtypeOrderUnitRow}オブジェクト 
    */
    protected AdmintoEqtypeOrderUnitDao( AdmintoEqtypeOrderUnitRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AdmintoEqtypeOrderUnitRow}オブジェクトを取得します。
   */
    public AdmintoEqtypeOrderUnitRow getAdmintoEqtypeOrderUnitRow() {
        return row;
    }


  /** 
   * 指定の{@@link AdmintoEqtypeOrderUnitRow}オブジェクトから{@@link AdmintoEqtypeOrderUnitDao}オブジェクトを作成します。 
   * これは実際の{@@link AdmintoEqtypeOrderUnitRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AdmintoEqtypeOrderUnitDao}取得のために指定の{@@link AdmintoEqtypeOrderUnitRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AdmintoEqtypeOrderUnitDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AdmintoEqtypeOrderUnitDao forRow( AdmintoEqtypeOrderUnitRow row ) throws java.lang.IllegalArgumentException {
        return (AdmintoEqtypeOrderUnitDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdmintoEqtypeOrderUnitRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AdmintoEqtypeOrderUnitRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AdmintoEqtypeOrderUnitPK}やデータベースレコードとして挿入される{@@link AdmintoEqtypeOrderUnitParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdmintoEqtypeOrderUnitRow.TYPE );
    }


  /** 
   * {@@link AdmintoEqtypeOrderUnitRow}を一意に特定する{@@link AdmintoEqtypeOrderUnitPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AdmintoEqtypeOrderUnitRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AdmintoEqtypeOrderUnitParams}オブジェクトの主キーとして利用可能な{@@link AdmintoEqtypeOrderUnitPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AdmintoEqtypeOrderUnitPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AdmintoEqtypeOrderUnitPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AdmintoEqtypeOrderUnitRow}オブジェクトを検索します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdmintoEqtypeOrderUnitRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdmintoEqtypeOrderUnitRow findRowByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoEqtypeOrderUnitPK pk = new AdmintoEqtypeOrderUnitPK( p_orderUnitId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAdmintoEqtypeOrderUnitPKオブジェクトから{@@link AdmintoEqtypeOrderUnitRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAdmintoEqtypeOrderUnitPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdmintoEqtypeOrderUnitRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdmintoEqtypeOrderUnitRow findRowByPk( AdmintoEqtypeOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdmintoEqtypeOrderUnitRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(AdmintoEqtypeOrderUnitRow)}を使用してください。 
   */
    public static AdmintoEqtypeOrderUnitDao findDaoByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoEqtypeOrderUnitPK pk = new AdmintoEqtypeOrderUnitPK( p_orderUnitId );
        AdmintoEqtypeOrderUnitRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AdmintoEqtypeOrderUnitPK)}および{@@link #forRow(AdmintoEqtypeOrderUnitRow)}を使用してください。 
   */
    public static AdmintoEqtypeOrderUnitDao findDaoByPk( AdmintoEqtypeOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoEqtypeOrderUnitRow row = findRowByPk( pk );
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

        // (none) 

}
@
