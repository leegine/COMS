head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.41.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvAppliAttributeDao.java;


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
 * {@@link SrvAppliAttributeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvAppliAttributeRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvAppliAttributePK 
 * @@see SrvAppliAttributeRow 
 */
public class SrvAppliAttributeDao extends DataAccessObject {


  /** 
   * この{@@link SrvAppliAttributeDao}に関連する型指定のRowオブジェクト 
   */
    private SrvAppliAttributeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvAppliAttributeRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvAppliAttributeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvAppliAttributeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvAppliAttributeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvAppliAttributeRow )
                return new SrvAppliAttributeDao( (SrvAppliAttributeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvAppliAttributeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvAppliAttributeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvAppliAttributeRow}オブジェクト 
    */
    protected SrvAppliAttributeDao( SrvAppliAttributeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvAppliAttributeRow}オブジェクトを取得します。
   */
    public SrvAppliAttributeRow getSrvAppliAttributeRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvAppliAttributeRow}オブジェクトから{@@link SrvAppliAttributeDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvAppliAttributeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvAppliAttributeDao}取得のために指定の{@@link SrvAppliAttributeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvAppliAttributeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvAppliAttributeDao forRow( SrvAppliAttributeRow row ) throws java.lang.IllegalArgumentException {
        return (SrvAppliAttributeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvAppliAttributeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvAppliAttributeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvAppliAttributePK}やデータベースレコードとして挿入される{@@link SrvAppliAttributeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvAppliAttributeRow.TYPE );
    }


  /** 
   * {@@link SrvAppliAttributeRow}を一意に特定する{@@link SrvAppliAttributePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvAppliAttributeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvAppliAttributeParams}オブジェクトの主キーとして利用可能な{@@link SrvAppliAttributePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvAppliAttributePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvAppliAttributeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvAppliAttributeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvAppliAttributeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvAppliAttributePK pk = new SrvAppliAttributePK( p_institutionCode, p_branchCode, p_accountCode, p_srvDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvAppliAttributePKオブジェクトから{@@link SrvAppliAttributeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvAppliAttributePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvAppliAttributeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvAppliAttributeRow findRowByPk( SrvAppliAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvAppliAttributeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(SrvAppliAttributeRow)}を使用してください。 
   */
    public static SrvAppliAttributeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvAppliAttributePK pk = new SrvAppliAttributePK( p_institutionCode, p_branchCode, p_accountCode, p_srvDiv );
        SrvAppliAttributeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvAppliAttributePK)}および{@@link #forRow(SrvAppliAttributeRow)}を使用してください。 
   */
    public static SrvAppliAttributeDao findDaoByPk( SrvAppliAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvAppliAttributeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_srvDiv, and にて指定の値から一意の{@@link SrvAppliAttributeRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_srvDiv, and の値と一致する{@@link SrvAppliAttributeRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvAppliAttributeRow findRowByInstitutionCodeBranchCodeAccountCodeSrvDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvAppliAttributeRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and srv_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_srvDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvAppliAttributeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvAppliAttributeDao.findRowsByInstitutionCodeBranchCodeAccountCodeSrvDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeSrvDiv(String, String, String, String)}および{@@link #forRow(SrvAppliAttributeRow)}を使用してください。 
   */
    public static SrvAppliAttributeDao findDaoByInstitutionCodeBranchCodeAccountCodeSrvDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeSrvDiv( p_institutionCode, p_branchCode, p_accountCode, p_srvDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
