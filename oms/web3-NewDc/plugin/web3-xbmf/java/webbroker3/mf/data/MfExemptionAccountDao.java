head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfExemptionAccountDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MfExemptionAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MfExemptionAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see MfExemptionAccountPK 
 * @@see MfExemptionAccountRow 
 */
public class MfExemptionAccountDao extends DataAccessObject {


  /** 
   * この{@@link MfExemptionAccountDao}に関連する型指定のRowオブジェクト 
   */
    private MfExemptionAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MfExemptionAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link MfExemptionAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MfExemptionAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MfExemptionAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfExemptionAccountRow )
                return new MfExemptionAccountDao( (MfExemptionAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfExemptionAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfExemptionAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MfExemptionAccountRow}オブジェクト 
    */
    protected MfExemptionAccountDao( MfExemptionAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MfExemptionAccountRow}オブジェクトを取得します。
   */
    public MfExemptionAccountRow getMfExemptionAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link MfExemptionAccountRow}オブジェクトから{@@link MfExemptionAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link MfExemptionAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MfExemptionAccountDao}取得のために指定の{@@link MfExemptionAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MfExemptionAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MfExemptionAccountDao forRow( MfExemptionAccountRow row ) throws java.lang.IllegalArgumentException {
        return (MfExemptionAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfExemptionAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MfExemptionAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MfExemptionAccountPK}やデータベースレコードとして挿入される{@@link MfExemptionAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfExemptionAccountRow.TYPE );
    }


  /** 
   * {@@link MfExemptionAccountRow}を一意に特定する{@@link MfExemptionAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MfExemptionAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MfExemptionAccountParams}オブジェクトの主キーとして利用可能な{@@link MfExemptionAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MfExemptionAccountPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MfExemptionAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_exemptionDiv 検索対象であるp_exemptionDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfExemptionAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfExemptionAccountRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_exemptionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        MfExemptionAccountPK pk = new MfExemptionAccountPK( p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMfExemptionAccountPKオブジェクトから{@@link MfExemptionAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMfExemptionAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfExemptionAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfExemptionAccountRow findRowByPk( MfExemptionAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfExemptionAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(MfExemptionAccountRow)}を使用してください。 
   */
    public static MfExemptionAccountDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_exemptionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        MfExemptionAccountPK pk = new MfExemptionAccountPK( p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv );
        MfExemptionAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MfExemptionAccountPK)}および{@@link #forRow(MfExemptionAccountRow)}を使用してください。 
   */
    public static MfExemptionAccountDao findDaoByPk( MfExemptionAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfExemptionAccountRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv, and にて指定の値から一意の{@@link MfExemptionAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_exemptionDiv 検索対象であるp_exemptionDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv, and の値と一致する{@@link MfExemptionAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MfExemptionAccountRow findRowByInstitutionCodeBranchCodeAccountCodeExemptionDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_exemptionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfExemptionAccountRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and exemption_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfExemptionAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfExemptionAccountDao.findRowsByInstitutionCodeBranchCodeAccountCodeExemptionDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeExemptionDiv(String, String, String, String)}および{@@link #forRow(MfExemptionAccountRow)}を使用してください。 
   */
    public static MfExemptionAccountDao findDaoByInstitutionCodeBranchCodeAccountCodeExemptionDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_exemptionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeExemptionDiv( p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
