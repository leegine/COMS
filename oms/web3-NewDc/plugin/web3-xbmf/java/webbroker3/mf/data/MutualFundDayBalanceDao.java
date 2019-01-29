head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundDayBalanceDao.java;


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
 * {@@link MutualFundDayBalanceDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MutualFundDayBalanceRow}インスタンスへ関連付けることができます。 
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
 * @@see MutualFundDayBalancePK 
 * @@see MutualFundDayBalanceRow 
 */
public class MutualFundDayBalanceDao extends DataAccessObject {


  /** 
   * この{@@link MutualFundDayBalanceDao}に関連する型指定のRowオブジェクト 
   */
    private MutualFundDayBalanceRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MutualFundDayBalanceRow}と仮定される{@@link DataAccessObject}から新たに{@@link MutualFundDayBalanceDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MutualFundDayBalanceDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MutualFundDayBalanceRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundDayBalanceRow )
                return new MutualFundDayBalanceDao( (MutualFundDayBalanceRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundDayBalanceRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundDayBalanceRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MutualFundDayBalanceRow}オブジェクト 
    */
    protected MutualFundDayBalanceDao( MutualFundDayBalanceRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MutualFundDayBalanceRow}オブジェクトを取得します。
   */
    public MutualFundDayBalanceRow getMutualFundDayBalanceRow() {
        return row;
    }


  /** 
   * 指定の{@@link MutualFundDayBalanceRow}オブジェクトから{@@link MutualFundDayBalanceDao}オブジェクトを作成します。 
   * これは実際の{@@link MutualFundDayBalanceRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MutualFundDayBalanceDao}取得のために指定の{@@link MutualFundDayBalanceRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MutualFundDayBalanceDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MutualFundDayBalanceDao forRow( MutualFundDayBalanceRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundDayBalanceDao) DataAccessObject.forRow( row );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, p_deliveryDate, and にて指定の値に一致する{@@link MutualFundDayBalanceRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_taxType 検索対象であるp_taxTypeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, p_deliveryDate, and の値と一致する{@@link MutualFundDayBalanceRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeTaxTypeDeliveryDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, String p_taxType, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MutualFundDayBalanceRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and product_code=? and tax_type=? and delivery_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, p_deliveryDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeTaxTypeDeliveryDate(String, String, String, String, String, java.sql.Timestamp)}および{@@link #forRow(MutualFundDayBalanceRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeProductCodeTaxTypeDeliveryDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, String p_taxType, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeTaxTypeDeliveryDate( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, p_deliveryDate ) );
    }

}
@
