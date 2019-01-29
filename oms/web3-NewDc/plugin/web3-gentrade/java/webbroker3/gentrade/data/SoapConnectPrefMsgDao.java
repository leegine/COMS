head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SoapConnectPrefMsgDao.java;


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
 * {@@link SoapConnectPrefMsgDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SoapConnectPrefMsgRow}インスタンスへ関連付けることができます。 
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
 * @@see SoapConnectPrefMsgPK 
 * @@see SoapConnectPrefMsgRow 
 */
public class SoapConnectPrefMsgDao extends DataAccessObject {


  /** 
   * この{@@link SoapConnectPrefMsgDao}に関連する型指定のRowオブジェクト 
   */
    private SoapConnectPrefMsgRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SoapConnectPrefMsgRow}と仮定される{@@link DataAccessObject}から新たに{@@link SoapConnectPrefMsgDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SoapConnectPrefMsgDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SoapConnectPrefMsgRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SoapConnectPrefMsgRow )
                return new SoapConnectPrefMsgDao( (SoapConnectPrefMsgRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SoapConnectPrefMsgRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SoapConnectPrefMsgRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SoapConnectPrefMsgRow}オブジェクト 
    */
    protected SoapConnectPrefMsgDao( SoapConnectPrefMsgRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SoapConnectPrefMsgRow}オブジェクトを取得します。
   */
    public SoapConnectPrefMsgRow getSoapConnectPrefMsgRow() {
        return row;
    }


  /** 
   * 指定の{@@link SoapConnectPrefMsgRow}オブジェクトから{@@link SoapConnectPrefMsgDao}オブジェクトを作成します。 
   * これは実際の{@@link SoapConnectPrefMsgRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SoapConnectPrefMsgDao}取得のために指定の{@@link SoapConnectPrefMsgRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SoapConnectPrefMsgDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SoapConnectPrefMsgDao forRow( SoapConnectPrefMsgRow row ) throws java.lang.IllegalArgumentException {
        return (SoapConnectPrefMsgDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SoapConnectPrefMsgRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SoapConnectPrefMsgRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SoapConnectPrefMsgPK}やデータベースレコードとして挿入される{@@link SoapConnectPrefMsgParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SoapConnectPrefMsgRow.TYPE );
    }


  /** 
   * {@@link SoapConnectPrefMsgRow}を一意に特定する{@@link SoapConnectPrefMsgPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SoapConnectPrefMsgRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SoapConnectPrefMsgParams}オブジェクトの主キーとして利用可能な{@@link SoapConnectPrefMsgPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SoapConnectPrefMsgPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SoapConnectPrefMsgRow}オブジェクトを検索します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_connectDiv 検索対象であるp_connectDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SoapConnectPrefMsgRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SoapConnectPrefMsgRow findRowByPk( long p_branchId, String p_connectDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefMsgPK pk = new SoapConnectPrefMsgPK( p_branchId, p_connectDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSoapConnectPrefMsgPKオブジェクトから{@@link SoapConnectPrefMsgRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSoapConnectPrefMsgPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SoapConnectPrefMsgRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SoapConnectPrefMsgRow findRowByPk( SoapConnectPrefMsgPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SoapConnectPrefMsgRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(SoapConnectPrefMsgRow)}を使用してください。 
   */
    public static SoapConnectPrefMsgDao findDaoByPk( long p_branchId, String p_connectDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefMsgPK pk = new SoapConnectPrefMsgPK( p_branchId, p_connectDiv );
        SoapConnectPrefMsgRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SoapConnectPrefMsgPK)}および{@@link #forRow(SoapConnectPrefMsgRow)}を使用してください。 
   */
    public static SoapConnectPrefMsgDao findDaoByPk( SoapConnectPrefMsgPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefMsgRow row = findRowByPk( pk );
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
   * p_branchId, p_connectDiv, and にて指定の値から一意の{@@link SoapConnectPrefMsgRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_connectDiv 検索対象であるp_connectDivフィールドの値
   * 
   * @@return 引数指定のp_branchId, p_connectDiv, and の値と一致する{@@link SoapConnectPrefMsgRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SoapConnectPrefMsgRow findRowByBranchIdConnectDiv( long p_branchId, String p_connectDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SoapConnectPrefMsgRow.TYPE,
            "branch_id=? and connect_div=?",
            null,
            new Object[] { new Long(p_branchId), p_connectDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SoapConnectPrefMsgRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SoapConnectPrefMsgDao.findRowsByBranchIdConnectDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBranchIdConnectDiv(long, String)}および{@@link #forRow(SoapConnectPrefMsgRow)}を使用してください。 
   */
    public static SoapConnectPrefMsgDao findDaoByBranchIdConnectDiv( long p_branchId, String p_connectDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchIdConnectDiv( p_branchId, p_connectDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
