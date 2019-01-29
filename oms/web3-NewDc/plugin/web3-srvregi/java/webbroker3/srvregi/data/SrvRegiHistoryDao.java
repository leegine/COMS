head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.srvregi.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SrvRegiHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiHistoryPK 
 * @@see SrvRegiHistoryRow 
 */
public class SrvRegiHistoryDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiHistoryRow )
                return new SrvRegiHistoryDao( (SrvRegiHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiHistoryRow}オブジェクト 
    */
    protected SrvRegiHistoryDao( SrvRegiHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiHistoryRow}オブジェクトを取得します。
   */
    public SrvRegiHistoryRow getSrvRegiHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiHistoryRow}オブジェクトから{@@link SrvRegiHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiHistoryDao}取得のために指定の{@@link SrvRegiHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiHistoryDao forRow( SrvRegiHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiHistoryPK}やデータベースレコードとして挿入される{@@link SrvRegiHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiHistoryRow.TYPE );
    }


  /** 
   * {@@link SrvRegiHistoryRow}を一意に特定する{@@link SrvRegiHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiHistoryParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiHistoryRow findRowByPk( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiHistoryPK pk = new SrvRegiHistoryPK( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiHistoryPKオブジェクトから{@@link SrvRegiHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiHistoryRow findRowByPk( SrvRegiHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(SrvRegiHistoryRow)}を使用してください。 
   */
    public static SrvRegiHistoryDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiHistoryPK pk = new SrvRegiHistoryPK( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode );
        SrvRegiHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiHistoryPK)}および{@@link #forRow(SrvRegiHistoryRow)}を使用してください。 
   */
    public static SrvRegiHistoryDao findDaoByPk( SrvRegiHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiHistoryRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, and にて指定の値から一意の{@@link SrvRegiHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_srvDiv, p_accountCode, and の値と一致する{@@link SrvRegiHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiHistoryRow findRowByInstitutionCodeBranchCodeSrvDivAccountCode( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiHistoryRow.TYPE,
            "institution_code=? and branch_code=? and srv_div=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_srvDiv, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiHistoryDao.findRowsByInstitutionCodeBranchCodeSrvDivAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeSrvDivAccountCode(String, String, String, String)}および{@@link #forRow(SrvRegiHistoryRow)}を使用してください。 
   */
    public static SrvRegiHistoryDao findDaoByInstitutionCodeBranchCodeSrvDivAccountCode( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSrvDivAccountCode( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
