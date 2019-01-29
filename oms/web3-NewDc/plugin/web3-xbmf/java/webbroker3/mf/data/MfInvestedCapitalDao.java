head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfInvestedCapitalDao.java;


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
 * {@@link MfInvestedCapitalDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MfInvestedCapitalRow}インスタンスへ関連付けることができます。 
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
 * @@see MfInvestedCapitalPK 
 * @@see MfInvestedCapitalRow 
 */
public class MfInvestedCapitalDao extends DataAccessObject {


  /** 
   * この{@@link MfInvestedCapitalDao}に関連する型指定のRowオブジェクト 
   */
    private MfInvestedCapitalRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MfInvestedCapitalRow}と仮定される{@@link DataAccessObject}から新たに{@@link MfInvestedCapitalDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MfInvestedCapitalDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MfInvestedCapitalRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfInvestedCapitalRow )
                return new MfInvestedCapitalDao( (MfInvestedCapitalRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfInvestedCapitalRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfInvestedCapitalRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MfInvestedCapitalRow}オブジェクト 
    */
    protected MfInvestedCapitalDao( MfInvestedCapitalRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MfInvestedCapitalRow}オブジェクトを取得します。
   */
    public MfInvestedCapitalRow getMfInvestedCapitalRow() {
        return row;
    }


  /** 
   * 指定の{@@link MfInvestedCapitalRow}オブジェクトから{@@link MfInvestedCapitalDao}オブジェクトを作成します。 
   * これは実際の{@@link MfInvestedCapitalRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MfInvestedCapitalDao}取得のために指定の{@@link MfInvestedCapitalRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MfInvestedCapitalDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MfInvestedCapitalDao forRow( MfInvestedCapitalRow row ) throws java.lang.IllegalArgumentException {
        return (MfInvestedCapitalDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfInvestedCapitalRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MfInvestedCapitalRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MfInvestedCapitalPK}やデータベースレコードとして挿入される{@@link MfInvestedCapitalParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfInvestedCapitalRow.TYPE );
    }


  /** 
   * {@@link MfInvestedCapitalRow}を一意に特定する{@@link MfInvestedCapitalPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MfInvestedCapitalRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MfInvestedCapitalParams}オブジェクトの主キーとして利用可能な{@@link MfInvestedCapitalPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のコラムが含まれているかコラムのタイプがlong型でない場合 
   */
    public static MfInvestedCapitalPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MfInvestedCapitalRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_taxType 検索対象であるp_taxTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfInvestedCapitalRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfInvestedCapitalRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, int p_taxType ) throws DataFindException, DataQueryException, DataNetworkException {
        MfInvestedCapitalPK pk = new MfInvestedCapitalPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMfInvestedCapitalPKオブジェクトから{@@link MfInvestedCapitalRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMfInvestedCapitalPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfInvestedCapitalRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfInvestedCapitalRow findRowByPk( MfInvestedCapitalPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfInvestedCapitalRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,int)}および{@@link #forRow(MfInvestedCapitalRow)}を使用してください。 
   */
    public static MfInvestedCapitalDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, int p_taxType ) throws DataFindException, DataQueryException, DataNetworkException {
        MfInvestedCapitalPK pk = new MfInvestedCapitalPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType );
        MfInvestedCapitalRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MfInvestedCapitalPK)}および{@@link #forRow(MfInvestedCapitalRow)}を使用してください。 
   */
    public static MfInvestedCapitalDao findDaoByPk( MfInvestedCapitalPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfInvestedCapitalRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, and にて指定の値から一意の{@@link MfInvestedCapitalRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_taxType 検索対象であるp_taxTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, and の値と一致する{@@link MfInvestedCapitalRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MfInvestedCapitalRow findRowByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, int p_taxType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfInvestedCapitalRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and product_code=? and tax_type=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_productCode, new Integer(p_taxType) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfInvestedCapitalRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfInvestedCapitalDao.findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType(String, String, String, String, int)}および{@@link #forRow(MfInvestedCapitalRow)}を使用してください。 
   */
    public static MfInvestedCapitalDao findDaoByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, int p_taxType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
