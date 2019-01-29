head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.33.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SrvRegiApplicationDao.java;


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
 * {@@link SrvRegiApplicationDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiApplicationRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiApplicationPK 
 * @@see SrvRegiApplicationRow 
 */
public class SrvRegiApplicationDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiApplicationDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiApplicationRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiApplicationRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiApplicationDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiApplicationDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiApplicationRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiApplicationRow )
                return new SrvRegiApplicationDao( (SrvRegiApplicationRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiApplicationRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiApplicationRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiApplicationRow}オブジェクト 
    */
    protected SrvRegiApplicationDao( SrvRegiApplicationRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiApplicationRow}オブジェクトを取得します。
   */
    public SrvRegiApplicationRow getSrvRegiApplicationRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiApplicationRow}オブジェクトから{@@link SrvRegiApplicationDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiApplicationRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiApplicationDao}取得のために指定の{@@link SrvRegiApplicationRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiApplicationDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiApplicationDao forRow( SrvRegiApplicationRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiApplicationDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiApplicationRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiApplicationRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiApplicationPK}やデータベースレコードとして挿入される{@@link SrvRegiApplicationParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiApplicationRow.TYPE );
    }


  /** 
   * {@@link SrvRegiApplicationRow}を一意に特定する{@@link SrvRegiApplicationPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiApplicationRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiApplicationParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiApplicationPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiApplicationPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiApplicationRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_registId 検索対象であるp_registIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiApplicationRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiApplicationRow findRowByPk( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode, long p_registId ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiApplicationPK pk = new SrvRegiApplicationPK( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiApplicationPKオブジェクトから{@@link SrvRegiApplicationRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiApplicationPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiApplicationRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiApplicationRow findRowByPk( SrvRegiApplicationPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiApplicationRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,long)}および{@@link #forRow(SrvRegiApplicationRow)}を使用してください。 
   */
    public static SrvRegiApplicationDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode, long p_registId ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiApplicationPK pk = new SrvRegiApplicationPK( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId );
        SrvRegiApplicationRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiApplicationPK)}および{@@link #forRow(SrvRegiApplicationRow)}を使用してください。 
   */
    public static SrvRegiApplicationDao findDaoByPk( SrvRegiApplicationPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiApplicationRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId, and にて指定の値から一意の{@@link SrvRegiApplicationRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_registId 検索対象であるp_registIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId, and の値と一致する{@@link SrvRegiApplicationRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiApplicationRow findRowByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode, long p_registId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiApplicationRow.TYPE,
            "institution_code=? and branch_code=? and srv_div=? and account_code=? and regist_id=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, new Long(p_registId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiApplicationRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiApplicationDao.findRowsByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId(String, String, String, String, long)}および{@@link #forRow(SrvRegiApplicationRow)}を使用してください。 
   */
    public static SrvRegiApplicationDao findDaoByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode, long p_registId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
