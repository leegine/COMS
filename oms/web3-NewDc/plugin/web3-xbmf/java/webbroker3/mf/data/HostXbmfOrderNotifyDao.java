head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostXbmfOrderNotifyDao.java;


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
 * {@@link HostXbmfOrderNotifyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostXbmfOrderNotifyRow}インスタンスへ関連付けることができます。 
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
 * @@see HostXbmfOrderNotifyPK 
 * @@see HostXbmfOrderNotifyRow 
 */
public class HostXbmfOrderNotifyDao extends DataAccessObject {


  /** 
   * この{@@link HostXbmfOrderNotifyDao}に関連する型指定のRowオブジェクト 
   */
    private HostXbmfOrderNotifyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostXbmfOrderNotifyRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostXbmfOrderNotifyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostXbmfOrderNotifyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostXbmfOrderNotifyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostXbmfOrderNotifyRow )
                return new HostXbmfOrderNotifyDao( (HostXbmfOrderNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostXbmfOrderNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostXbmfOrderNotifyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostXbmfOrderNotifyRow}オブジェクト 
    */
    protected HostXbmfOrderNotifyDao( HostXbmfOrderNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostXbmfOrderNotifyRow}オブジェクトを取得します。
   */
    public HostXbmfOrderNotifyRow getHostXbmfOrderNotifyRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostXbmfOrderNotifyRow}オブジェクトから{@@link HostXbmfOrderNotifyDao}オブジェクトを作成します。 
   * これは実際の{@@link HostXbmfOrderNotifyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostXbmfOrderNotifyDao}取得のために指定の{@@link HostXbmfOrderNotifyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostXbmfOrderNotifyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostXbmfOrderNotifyDao forRow( HostXbmfOrderNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (HostXbmfOrderNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostXbmfOrderNotifyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostXbmfOrderNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostXbmfOrderNotifyPK}やデータベースレコードとして挿入される{@@link HostXbmfOrderNotifyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostXbmfOrderNotifyRow.TYPE );
    }


  /** 
   * {@@link HostXbmfOrderNotifyRow}を一意に特定する{@@link HostXbmfOrderNotifyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostXbmfOrderNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostXbmfOrderNotifyParams}オブジェクトの主キーとして利用可能な{@@link HostXbmfOrderNotifyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostXbmfOrderNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostXbmfOrderNotifyRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostXbmfOrderNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostXbmfOrderNotifyRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderNotifyPK pk = new HostXbmfOrderNotifyPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostXbmfOrderNotifyPKオブジェクトから{@@link HostXbmfOrderNotifyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostXbmfOrderNotifyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostXbmfOrderNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostXbmfOrderNotifyRow findRowByPk( HostXbmfOrderNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostXbmfOrderNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(HostXbmfOrderNotifyRow)}を使用してください。 
   */
    public static HostXbmfOrderNotifyDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderNotifyPK pk = new HostXbmfOrderNotifyPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        HostXbmfOrderNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostXbmfOrderNotifyPK)}および{@@link #forRow(HostXbmfOrderNotifyRow)}を使用してください。 
   */
    public static HostXbmfOrderNotifyDao findDaoByPk( HostXbmfOrderNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderNotifyRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_orderRequestNumber, and にて指定の値から一意の{@@link HostXbmfOrderNotifyRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_orderRequestNumber, and の値と一致する{@@link HostXbmfOrderNotifyRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static HostXbmfOrderNotifyRow findRowByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostXbmfOrderNotifyRow.TYPE,
            "institution_code=? and branch_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostXbmfOrderNotifyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostXbmfOrderNotifyDao.findRowsByInstitutionCodeBranchCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeOrderRequestNumber(String, String, String)}および{@@link #forRow(HostXbmfOrderNotifyRow)}を使用してください。 
   */
    public static HostXbmfOrderNotifyDao findDaoByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
