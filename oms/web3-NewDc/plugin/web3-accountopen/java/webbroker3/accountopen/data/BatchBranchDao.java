head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.18.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BatchBranchDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BatchBranchDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BatchBranchRow}インスタンスへ関連付けることができます。 
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
 * @@see BatchBranchPK 
 * @@see BatchBranchRow 
 */
public class BatchBranchDao extends DataAccessObject {


  /** 
   * この{@@link BatchBranchDao}に関連する型指定のRowオブジェクト 
   */
    private BatchBranchRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BatchBranchRow}と仮定される{@@link DataAccessObject}から新たに{@@link BatchBranchDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BatchBranchDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BatchBranchRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatchBranchRow )
                return new BatchBranchDao( (BatchBranchRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatchBranchRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatchBranchRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BatchBranchRow}オブジェクト 
    */
    protected BatchBranchDao( BatchBranchRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BatchBranchRow}オブジェクトを取得します。
   */
    public BatchBranchRow getBatchBranchRow() {
        return row;
    }


  /** 
   * 指定の{@@link BatchBranchRow}オブジェクトから{@@link BatchBranchDao}オブジェクトを作成します。 
   * これは実際の{@@link BatchBranchRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BatchBranchDao}取得のために指定の{@@link BatchBranchRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BatchBranchDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BatchBranchDao forRow( BatchBranchRow row ) throws java.lang.IllegalArgumentException {
        return (BatchBranchDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatchBranchRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BatchBranchRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BatchBranchPK}やデータベースレコードとして挿入される{@@link BatchBranchParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatchBranchRow.TYPE );
    }


  /** 
   * {@@link BatchBranchRow}を一意に特定する{@@link BatchBranchPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BatchBranchRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BatchBranchParams}オブジェクトの主キーとして利用可能な{@@link BatchBranchPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BatchBranchPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BatchBranchRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatchBranchRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatchBranchRow findRowByPk( String p_institutionCode, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchBranchPK pk = new BatchBranchPK( p_institutionCode, p_branchCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBatchBranchPKオブジェクトから{@@link BatchBranchRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBatchBranchPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatchBranchRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatchBranchRow findRowByPk( BatchBranchPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatchBranchRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(BatchBranchRow)}を使用してください。 
   */
    public static BatchBranchDao findDaoByPk( String p_institutionCode, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchBranchPK pk = new BatchBranchPK( p_institutionCode, p_branchCode );
        BatchBranchRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BatchBranchPK)}および{@@link #forRow(BatchBranchRow)}を使用してください。 
   */
    public static BatchBranchDao findDaoByPk( BatchBranchPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchBranchRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, and にて指定の値から一意の{@@link BatchBranchRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, and の値と一致する{@@link BatchBranchRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BatchBranchRow findRowByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatchBranchRow.TYPE,
            "institution_code=? and branch_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatchBranchRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatchBranchDao.findRowsByInstitutionCodeBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCode(String, String)}および{@@link #forRow(BatchBranchRow)}を使用してください。 
   */
    public static BatchBranchDao findDaoByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCode( p_institutionCode, p_branchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
