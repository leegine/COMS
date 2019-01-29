head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.25.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FinInstitutionAvailableDao.java;


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
 * {@@link FinInstitutionAvailableDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FinInstitutionAvailableRow}インスタンスへ関連付けることができます。 
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
 * @@see FinInstitutionAvailablePK 
 * @@see FinInstitutionAvailableRow 
 */
public class FinInstitutionAvailableDao extends DataAccessObject {


  /** 
   * この{@@link FinInstitutionAvailableDao}に関連する型指定のRowオブジェクト 
   */
    private FinInstitutionAvailableRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FinInstitutionAvailableRow}と仮定される{@@link DataAccessObject}から新たに{@@link FinInstitutionAvailableDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FinInstitutionAvailableDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FinInstitutionAvailableRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FinInstitutionAvailableRow )
                return new FinInstitutionAvailableDao( (FinInstitutionAvailableRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FinInstitutionAvailableRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FinInstitutionAvailableRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FinInstitutionAvailableRow}オブジェクト 
    */
    protected FinInstitutionAvailableDao( FinInstitutionAvailableRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FinInstitutionAvailableRow}オブジェクトを取得します。
   */
    public FinInstitutionAvailableRow getFinInstitutionAvailableRow() {
        return row;
    }


  /** 
   * 指定の{@@link FinInstitutionAvailableRow}オブジェクトから{@@link FinInstitutionAvailableDao}オブジェクトを作成します。 
   * これは実際の{@@link FinInstitutionAvailableRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FinInstitutionAvailableDao}取得のために指定の{@@link FinInstitutionAvailableRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FinInstitutionAvailableDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FinInstitutionAvailableDao forRow( FinInstitutionAvailableRow row ) throws java.lang.IllegalArgumentException {
        return (FinInstitutionAvailableDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


      // (this object has no primary key components)


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


      // (this object has no primary key components)


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
   * p_institutionCode, p_branchCode, p_finInstitutionCode, and にて指定の値から一意の{@@link FinInstitutionAvailableRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_finInstitutionCode, and の値と一致する{@@link FinInstitutionAvailableRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FinInstitutionAvailableRow findRowByInstitutionCodeBranchCodeFinInstitutionCode( String p_institutionCode, String p_branchCode, String p_finInstitutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FinInstitutionAvailableRow.TYPE,
            "institution_code=? and branch_code=? and fin_institution_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_finInstitutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FinInstitutionAvailableRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FinInstitutionAvailableDao.findRowsByInstitutionCodeBranchCodeFinInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeFinInstitutionCode(String, String, String)}および{@@link #forRow(FinInstitutionAvailableRow)}を使用してください。 
   */
    public static FinInstitutionAvailableDao findDaoByInstitutionCodeBranchCodeFinInstitutionCode( String p_institutionCode, String p_branchCode, String p_finInstitutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFinInstitutionCode( p_institutionCode, p_branchCode, p_finInstitutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
