head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFrgnMmfOrderAcceptDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link HostFrgnMmfOrderAcceptDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostFrgnMmfOrderAcceptRow}インスタンスへ関連付けることができます。 
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
 * @@see HostFrgnMmfOrderAcceptPK 
 * @@see HostFrgnMmfOrderAcceptRow 
 */
public class HostFrgnMmfOrderAcceptDao extends DataAccessObject {


  /** 
   * この{@@link HostFrgnMmfOrderAcceptDao}に関連する型指定のRowオブジェクト 
   */
    private HostFrgnMmfOrderAcceptRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostFrgnMmfOrderAcceptRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostFrgnMmfOrderAcceptDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostFrgnMmfOrderAcceptDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostFrgnMmfOrderAcceptRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFrgnMmfOrderAcceptRow )
                return new HostFrgnMmfOrderAcceptDao( (HostFrgnMmfOrderAcceptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFrgnMmfOrderAcceptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFrgnMmfOrderAcceptRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostFrgnMmfOrderAcceptRow}オブジェクト 
    */
    protected HostFrgnMmfOrderAcceptDao( HostFrgnMmfOrderAcceptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostFrgnMmfOrderAcceptRow}オブジェクトを取得します。
   */
    public HostFrgnMmfOrderAcceptRow getHostFrgnMmfOrderAcceptRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostFrgnMmfOrderAcceptRow}オブジェクトから{@@link HostFrgnMmfOrderAcceptDao}オブジェクトを作成します。 
   * これは実際の{@@link HostFrgnMmfOrderAcceptRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostFrgnMmfOrderAcceptDao}取得のために指定の{@@link HostFrgnMmfOrderAcceptRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostFrgnMmfOrderAcceptDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostFrgnMmfOrderAcceptDao forRow( HostFrgnMmfOrderAcceptRow row ) throws java.lang.IllegalArgumentException {
        return (HostFrgnMmfOrderAcceptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFrgnMmfOrderAcceptRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostFrgnMmfOrderAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostFrgnMmfOrderAcceptPK}やデータベースレコードとして挿入される{@@link HostFrgnMmfOrderAcceptParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFrgnMmfOrderAcceptRow.TYPE );
    }


  /** 
   * {@@link HostFrgnMmfOrderAcceptRow}を一意に特定する{@@link HostFrgnMmfOrderAcceptPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostFrgnMmfOrderAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostFrgnMmfOrderAcceptParams}オブジェクトの主キーとして利用可能な{@@link HostFrgnMmfOrderAcceptPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostFrgnMmfOrderAcceptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostFrgnMmfOrderAcceptRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFrgnMmfOrderAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFrgnMmfOrderAcceptRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFrgnMmfOrderAcceptPK pk = new HostFrgnMmfOrderAcceptPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostFrgnMmfOrderAcceptPKオブジェクトから{@@link HostFrgnMmfOrderAcceptRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostFrgnMmfOrderAcceptPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFrgnMmfOrderAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFrgnMmfOrderAcceptRow findRowByPk( HostFrgnMmfOrderAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFrgnMmfOrderAcceptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(HostFrgnMmfOrderAcceptRow)}を使用してください。 
   */
    public static HostFrgnMmfOrderAcceptDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFrgnMmfOrderAcceptPK pk = new HostFrgnMmfOrderAcceptPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        HostFrgnMmfOrderAcceptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostFrgnMmfOrderAcceptPK)}および{@@link #forRow(HostFrgnMmfOrderAcceptRow)}を使用してください。 
   */
    public static HostFrgnMmfOrderAcceptDao findDaoByPk( HostFrgnMmfOrderAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFrgnMmfOrderAcceptRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_orderRequestNumber, and にて指定の値から一意の{@@link HostFrgnMmfOrderAcceptRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_orderRequestNumber, and の値と一致する{@@link HostFrgnMmfOrderAcceptRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static HostFrgnMmfOrderAcceptRow findRowByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostFrgnMmfOrderAcceptRow.TYPE,
            "institution_code=? and branch_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostFrgnMmfOrderAcceptRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostFrgnMmfOrderAcceptDao.findRowsByInstitutionCodeBranchCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeOrderRequestNumber(String, String, String)}および{@@link #forRow(HostFrgnMmfOrderAcceptRow)}を使用してください。 
   */
    public static HostFrgnMmfOrderAcceptDao findDaoByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
