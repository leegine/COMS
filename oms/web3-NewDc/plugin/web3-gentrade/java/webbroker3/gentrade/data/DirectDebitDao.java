head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DirectDebitDao.java;


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
 * {@@link DirectDebitDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DirectDebitRow}インスタンスへ関連付けることができます。 
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
 * @@see DirectDebitPK 
 * @@see DirectDebitRow 
 */
public class DirectDebitDao extends DataAccessObject {


  /** 
   * この{@@link DirectDebitDao}に関連する型指定のRowオブジェクト 
   */
    private DirectDebitRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DirectDebitRow}と仮定される{@@link DataAccessObject}から新たに{@@link DirectDebitDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DirectDebitDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DirectDebitRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DirectDebitRow )
                return new DirectDebitDao( (DirectDebitRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DirectDebitRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DirectDebitRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DirectDebitRow}オブジェクト 
    */
    protected DirectDebitDao( DirectDebitRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DirectDebitRow}オブジェクトを取得します。
   */
    public DirectDebitRow getDirectDebitRow() {
        return row;
    }


  /** 
   * 指定の{@@link DirectDebitRow}オブジェクトから{@@link DirectDebitDao}オブジェクトを作成します。 
   * これは実際の{@@link DirectDebitRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DirectDebitDao}取得のために指定の{@@link DirectDebitRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DirectDebitDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DirectDebitDao forRow( DirectDebitRow row ) throws java.lang.IllegalArgumentException {
        return (DirectDebitDao) DataAccessObject.forRow( row );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, p_comodity, p_fundCode, p_transferDiv, and にて指定の値から一意の{@@link DirectDebitRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_designateDiv 検索対象であるp_designateDivフィールドの値
   * @@param p_comodity 検索対象であるp_comodityフィールドの値
   * @@param p_fundCode 検索対象であるp_fundCodeフィールドの値
   * @@param p_transferDiv 検索対象であるp_transferDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_designateDiv, p_comodity, p_fundCode, p_transferDiv, and の値と一致する{@@link DirectDebitRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DirectDebitRow findRowByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv, String p_comodity, String p_fundCode, String p_transferDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DirectDebitRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and designate_div=? and comodity=? and fund_code=? and transfer_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, p_comodity, p_fundCode, p_transferDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DirectDebitRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DirectDebitDao.findRowsByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv(String, String, String, String, String, String, String)}および{@@link #forRow(DirectDebitRow)}を使用してください。 
   */
    public static DirectDebitDao findDaoByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv, String p_comodity, String p_fundCode, String p_transferDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv( p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, p_comodity, p_fundCode, p_transferDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
