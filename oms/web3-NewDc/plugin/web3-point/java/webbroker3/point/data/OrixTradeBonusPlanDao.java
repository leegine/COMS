head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	OrixTradeBonusPlanDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.point.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OrixTradeBonusPlanDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OrixTradeBonusPlanRow}インスタンスへ関連付けることができます。 
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
 * @@see OrixTradeBonusPlanPK 
 * @@see OrixTradeBonusPlanRow 
 */
public class OrixTradeBonusPlanDao extends DataAccessObject {


  /** 
   * この{@@link OrixTradeBonusPlanDao}に関連する型指定のRowオブジェクト 
   */
    private OrixTradeBonusPlanRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OrixTradeBonusPlanRow}と仮定される{@@link DataAccessObject}から新たに{@@link OrixTradeBonusPlanDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OrixTradeBonusPlanDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OrixTradeBonusPlanRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrixTradeBonusPlanRow )
                return new OrixTradeBonusPlanDao( (OrixTradeBonusPlanRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrixTradeBonusPlanRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrixTradeBonusPlanRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OrixTradeBonusPlanRow}オブジェクト 
    */
    protected OrixTradeBonusPlanDao( OrixTradeBonusPlanRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OrixTradeBonusPlanRow}オブジェクトを取得します。
   */
    public OrixTradeBonusPlanRow getOrixTradeBonusPlanRow() {
        return row;
    }


  /** 
   * 指定の{@@link OrixTradeBonusPlanRow}オブジェクトから{@@link OrixTradeBonusPlanDao}オブジェクトを作成します。 
   * これは実際の{@@link OrixTradeBonusPlanRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OrixTradeBonusPlanDao}取得のために指定の{@@link OrixTradeBonusPlanRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OrixTradeBonusPlanDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OrixTradeBonusPlanDao forRow( OrixTradeBonusPlanRow row ) throws java.lang.IllegalArgumentException {
        return (OrixTradeBonusPlanDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrixTradeBonusPlanRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OrixTradeBonusPlanRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OrixTradeBonusPlanPK}やデータベースレコードとして挿入される{@@link OrixTradeBonusPlanParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrixTradeBonusPlanRow.TYPE );
    }


  /** 
   * {@@link OrixTradeBonusPlanRow}を一意に特定する{@@link OrixTradeBonusPlanPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OrixTradeBonusPlanRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OrixTradeBonusPlanParams}オブジェクトの主キーとして利用可能な{@@link OrixTradeBonusPlanPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OrixTradeBonusPlanPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OrixTradeBonusPlanRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrixTradeBonusPlanRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrixTradeBonusPlanRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        OrixTradeBonusPlanPK pk = new OrixTradeBonusPlanPK( p_institutionCode, p_branchCode, p_accountCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOrixTradeBonusPlanPKオブジェクトから{@@link OrixTradeBonusPlanRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOrixTradeBonusPlanPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrixTradeBonusPlanRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrixTradeBonusPlanRow findRowByPk( OrixTradeBonusPlanPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrixTradeBonusPlanRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(OrixTradeBonusPlanRow)}を使用してください。 
   */
    public static OrixTradeBonusPlanDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        OrixTradeBonusPlanPK pk = new OrixTradeBonusPlanPK( p_institutionCode, p_branchCode, p_accountCode );
        OrixTradeBonusPlanRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OrixTradeBonusPlanPK)}および{@@link #forRow(OrixTradeBonusPlanRow)}を使用してください。 
   */
    public static OrixTradeBonusPlanDao findDaoByPk( OrixTradeBonusPlanPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrixTradeBonusPlanRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値から一意の{@@link OrixTradeBonusPlanRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link OrixTradeBonusPlanRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OrixTradeBonusPlanRow findRowByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrixTradeBonusPlanRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrixTradeBonusPlanRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrixTradeBonusPlanDao.findRowsByInstitutionCodeBranchCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(OrixTradeBonusPlanRow)}を使用してください。 
   */
    public static OrixTradeBonusPlanDao findDaoByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
