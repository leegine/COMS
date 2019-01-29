head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	GftAccountOpenStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link GftAccountOpenStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link GftAccountOpenStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see GftAccountOpenStatusPK 
 * @@see GftAccountOpenStatusRow 
 */
public class GftAccountOpenStatusDao extends DataAccessObject {


  /** 
   * この{@@link GftAccountOpenStatusDao}に関連する型指定のRowオブジェクト 
   */
    private GftAccountOpenStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link GftAccountOpenStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link GftAccountOpenStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link GftAccountOpenStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link GftAccountOpenStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof GftAccountOpenStatusRow )
                return new GftAccountOpenStatusDao( (GftAccountOpenStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a GftAccountOpenStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link GftAccountOpenStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link GftAccountOpenStatusRow}オブジェクト 
    */
    protected GftAccountOpenStatusDao( GftAccountOpenStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link GftAccountOpenStatusRow}オブジェクトを取得します。
   */
    public GftAccountOpenStatusRow getGftAccountOpenStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link GftAccountOpenStatusRow}オブジェクトから{@@link GftAccountOpenStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link GftAccountOpenStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link GftAccountOpenStatusDao}取得のために指定の{@@link GftAccountOpenStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link GftAccountOpenStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static GftAccountOpenStatusDao forRow( GftAccountOpenStatusRow row ) throws java.lang.IllegalArgumentException {
        return (GftAccountOpenStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link GftAccountOpenStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link GftAccountOpenStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link GftAccountOpenStatusPK}やデータベースレコードとして挿入される{@@link GftAccountOpenStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( GftAccountOpenStatusRow.TYPE );
    }


  /** 
   * {@@link GftAccountOpenStatusRow}を一意に特定する{@@link GftAccountOpenStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link GftAccountOpenStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link GftAccountOpenStatusParams}オブジェクトの主キーとして利用可能な{@@link GftAccountOpenStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static GftAccountOpenStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link GftAccountOpenStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link GftAccountOpenStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static GftAccountOpenStatusRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        GftAccountOpenStatusPK pk = new GftAccountOpenStatusPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のGftAccountOpenStatusPKオブジェクトから{@@link GftAccountOpenStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するGftAccountOpenStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link GftAccountOpenStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static GftAccountOpenStatusRow findRowByPk( GftAccountOpenStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (GftAccountOpenStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(GftAccountOpenStatusRow)}を使用してください。 
   */
    public static GftAccountOpenStatusDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        GftAccountOpenStatusPK pk = new GftAccountOpenStatusPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        GftAccountOpenStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(GftAccountOpenStatusPK)}および{@@link #forRow(GftAccountOpenStatusRow)}を使用してください。 
   */
    public static GftAccountOpenStatusDao findDaoByPk( GftAccountOpenStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        GftAccountOpenStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_orderRequestNumber, and にて指定の値から一意の{@@link GftAccountOpenStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_orderRequestNumber, and の値と一致する{@@link GftAccountOpenStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static GftAccountOpenStatusRow findRowByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            GftAccountOpenStatusRow.TYPE,
            "institution_code=? and branch_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (GftAccountOpenStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query GftAccountOpenStatusDao.findRowsByInstitutionCodeBranchCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeOrderRequestNumber(String, String, String)}および{@@link #forRow(GftAccountOpenStatusRow)}を使用してください。 
   */
    public static GftAccountOpenStatusDao findDaoByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
