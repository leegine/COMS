head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SoapConnectPrefRpcDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SoapConnectPrefRpcDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SoapConnectPrefRpcRow}インスタンスへ関連付けることができます。 
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
 * @@see SoapConnectPrefRpcPK 
 * @@see SoapConnectPrefRpcRow 
 */
public class SoapConnectPrefRpcDao extends DataAccessObject {


  /** 
   * この{@@link SoapConnectPrefRpcDao}に関連する型指定のRowオブジェクト 
   */
    private SoapConnectPrefRpcRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SoapConnectPrefRpcRow}と仮定される{@@link DataAccessObject}から新たに{@@link SoapConnectPrefRpcDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SoapConnectPrefRpcDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SoapConnectPrefRpcRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SoapConnectPrefRpcRow )
                return new SoapConnectPrefRpcDao( (SoapConnectPrefRpcRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SoapConnectPrefRpcRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SoapConnectPrefRpcRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SoapConnectPrefRpcRow}オブジェクト 
    */
    protected SoapConnectPrefRpcDao( SoapConnectPrefRpcRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SoapConnectPrefRpcRow}オブジェクトを取得します。
   */
    public SoapConnectPrefRpcRow getSoapConnectPrefRpcRow() {
        return row;
    }


  /** 
   * 指定の{@@link SoapConnectPrefRpcRow}オブジェクトから{@@link SoapConnectPrefRpcDao}オブジェクトを作成します。 
   * これは実際の{@@link SoapConnectPrefRpcRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SoapConnectPrefRpcDao}取得のために指定の{@@link SoapConnectPrefRpcRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SoapConnectPrefRpcDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SoapConnectPrefRpcDao forRow( SoapConnectPrefRpcRow row ) throws java.lang.IllegalArgumentException {
        return (SoapConnectPrefRpcDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SoapConnectPrefRpcRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SoapConnectPrefRpcRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SoapConnectPrefRpcPK}やデータベースレコードとして挿入される{@@link SoapConnectPrefRpcParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SoapConnectPrefRpcRow.TYPE );
    }


  /** 
   * {@@link SoapConnectPrefRpcRow}を一意に特定する{@@link SoapConnectPrefRpcPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SoapConnectPrefRpcRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SoapConnectPrefRpcParams}オブジェクトの主キーとして利用可能な{@@link SoapConnectPrefRpcPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SoapConnectPrefRpcPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SoapConnectPrefRpcRow}オブジェクトを検索します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_connectDiv 検索対象であるp_connectDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SoapConnectPrefRpcRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SoapConnectPrefRpcRow findRowByPk( long p_branchId, String p_connectDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefRpcPK pk = new SoapConnectPrefRpcPK( p_branchId, p_connectDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSoapConnectPrefRpcPKオブジェクトから{@@link SoapConnectPrefRpcRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSoapConnectPrefRpcPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SoapConnectPrefRpcRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SoapConnectPrefRpcRow findRowByPk( SoapConnectPrefRpcPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SoapConnectPrefRpcRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(SoapConnectPrefRpcRow)}を使用してください。 
   */
    public static SoapConnectPrefRpcDao findDaoByPk( long p_branchId, String p_connectDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefRpcPK pk = new SoapConnectPrefRpcPK( p_branchId, p_connectDiv );
        SoapConnectPrefRpcRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SoapConnectPrefRpcPK)}および{@@link #forRow(SoapConnectPrefRpcRow)}を使用してください。 
   */
    public static SoapConnectPrefRpcDao findDaoByPk( SoapConnectPrefRpcPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefRpcRow row = findRowByPk( pk );
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


  /** 
   * p_branchId, p_connectDiv, and にて指定の値から一意の{@@link SoapConnectPrefRpcRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_connectDiv 検索対象であるp_connectDivフィールドの値
   * 
   * @@return 引数指定のp_branchId, p_connectDiv, and の値と一致する{@@link SoapConnectPrefRpcRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SoapConnectPrefRpcRow findRowByBranchIdConnectDiv( long p_branchId, String p_connectDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SoapConnectPrefRpcRow.TYPE,
            "branch_id=? and connect_div=?",
            null,
            new Object[] { new Long(p_branchId), p_connectDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SoapConnectPrefRpcRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SoapConnectPrefRpcDao.findRowsByBranchIdConnectDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBranchIdConnectDiv(long, String)}および{@@link #forRow(SoapConnectPrefRpcRow)}を使用してください。 
   */
    public static SoapConnectPrefRpcDao findDaoByBranchIdConnectDiv( long p_branchId, String p_connectDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchIdConnectDiv( p_branchId, p_connectDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
