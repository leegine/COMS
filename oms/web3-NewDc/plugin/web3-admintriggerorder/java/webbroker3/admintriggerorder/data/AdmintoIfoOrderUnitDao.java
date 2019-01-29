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
filename	AdmintoIfoOrderUnitDao.java;


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
 * {@@link AdmintoIfoOrderUnitDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AdmintoIfoOrderUnitRow}インスタンスへ関連付けることができます。 
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
 * @@see AdmintoIfoOrderUnitPK 
 * @@see AdmintoIfoOrderUnitRow 
 */
public class AdmintoIfoOrderUnitDao extends DataAccessObject {


  /** 
   * この{@@link AdmintoIfoOrderUnitDao}に関連する型指定のRowオブジェクト 
   */
    private AdmintoIfoOrderUnitRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AdmintoIfoOrderUnitRow}と仮定される{@@link DataAccessObject}から新たに{@@link AdmintoIfoOrderUnitDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AdmintoIfoOrderUnitDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AdmintoIfoOrderUnitRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdmintoIfoOrderUnitRow )
                return new AdmintoIfoOrderUnitDao( (AdmintoIfoOrderUnitRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdmintoIfoOrderUnitRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdmintoIfoOrderUnitRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AdmintoIfoOrderUnitRow}オブジェクト 
    */
    protected AdmintoIfoOrderUnitDao( AdmintoIfoOrderUnitRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AdmintoIfoOrderUnitRow}オブジェクトを取得します。
   */
    public AdmintoIfoOrderUnitRow getAdmintoIfoOrderUnitRow() {
        return row;
    }


  /** 
   * 指定の{@@link AdmintoIfoOrderUnitRow}オブジェクトから{@@link AdmintoIfoOrderUnitDao}オブジェクトを作成します。 
   * これは実際の{@@link AdmintoIfoOrderUnitRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AdmintoIfoOrderUnitDao}取得のために指定の{@@link AdmintoIfoOrderUnitRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AdmintoIfoOrderUnitDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AdmintoIfoOrderUnitDao forRow( AdmintoIfoOrderUnitRow row ) throws java.lang.IllegalArgumentException {
        return (AdmintoIfoOrderUnitDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdmintoIfoOrderUnitRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AdmintoIfoOrderUnitRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AdmintoIfoOrderUnitPK}やデータベースレコードとして挿入される{@@link AdmintoIfoOrderUnitParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdmintoIfoOrderUnitRow.TYPE );
    }


  /** 
   * {@@link AdmintoIfoOrderUnitRow}を一意に特定する{@@link AdmintoIfoOrderUnitPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AdmintoIfoOrderUnitRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AdmintoIfoOrderUnitParams}オブジェクトの主キーとして利用可能な{@@link AdmintoIfoOrderUnitPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AdmintoIfoOrderUnitPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AdmintoIfoOrderUnitPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AdmintoIfoOrderUnitRow}オブジェクトを検索します。 
   * 
   * @@param p_orderUnitId 検索対象であるp_orderUnitIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdmintoIfoOrderUnitRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdmintoIfoOrderUnitRow findRowByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoIfoOrderUnitPK pk = new AdmintoIfoOrderUnitPK( p_orderUnitId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAdmintoIfoOrderUnitPKオブジェクトから{@@link AdmintoIfoOrderUnitRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAdmintoIfoOrderUnitPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdmintoIfoOrderUnitRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdmintoIfoOrderUnitRow findRowByPk( AdmintoIfoOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdmintoIfoOrderUnitRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(AdmintoIfoOrderUnitRow)}を使用してください。 
   */
    public static AdmintoIfoOrderUnitDao findDaoByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoIfoOrderUnitPK pk = new AdmintoIfoOrderUnitPK( p_orderUnitId );
        AdmintoIfoOrderUnitRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AdmintoIfoOrderUnitPK)}および{@@link #forRow(AdmintoIfoOrderUnitRow)}を使用してください。 
   */
    public static AdmintoIfoOrderUnitDao findDaoByPk( AdmintoIfoOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoIfoOrderUnitRow row = findRowByPk( pk );
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
