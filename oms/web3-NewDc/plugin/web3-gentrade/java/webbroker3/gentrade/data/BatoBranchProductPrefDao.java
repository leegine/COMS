head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoBranchProductPrefDao.java;


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
 * {@@link BatoBranchProductPrefDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BatoBranchProductPrefRow}インスタンスへ関連付けることができます。 
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
 * @@see BatoBranchProductPrefPK 
 * @@see BatoBranchProductPrefRow 
 */
public class BatoBranchProductPrefDao extends DataAccessObject {


  /** 
   * この{@@link BatoBranchProductPrefDao}に関連する型指定のRowオブジェクト 
   */
    private BatoBranchProductPrefRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BatoBranchProductPrefRow}と仮定される{@@link DataAccessObject}から新たに{@@link BatoBranchProductPrefDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BatoBranchProductPrefDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BatoBranchProductPrefRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatoBranchProductPrefRow )
                return new BatoBranchProductPrefDao( (BatoBranchProductPrefRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatoBranchProductPrefRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatoBranchProductPrefRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BatoBranchProductPrefRow}オブジェクト 
    */
    protected BatoBranchProductPrefDao( BatoBranchProductPrefRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BatoBranchProductPrefRow}オブジェクトを取得します。
   */
    public BatoBranchProductPrefRow getBatoBranchProductPrefRow() {
        return row;
    }


  /** 
   * 指定の{@@link BatoBranchProductPrefRow}オブジェクトから{@@link BatoBranchProductPrefDao}オブジェクトを作成します。 
   * これは実際の{@@link BatoBranchProductPrefRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BatoBranchProductPrefDao}取得のために指定の{@@link BatoBranchProductPrefRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BatoBranchProductPrefDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BatoBranchProductPrefDao forRow( BatoBranchProductPrefRow row ) throws java.lang.IllegalArgumentException {
        return (BatoBranchProductPrefDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatoBranchProductPrefRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BatoBranchProductPrefRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BatoBranchProductPrefPK}やデータベースレコードとして挿入される{@@link BatoBranchProductPrefParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatoBranchProductPrefRow.TYPE );
    }


  /** 
   * {@@link BatoBranchProductPrefRow}を一意に特定する{@@link BatoBranchProductPrefPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BatoBranchProductPrefRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BatoBranchProductPrefParams}オブジェクトの主キーとして利用可能な{@@link BatoBranchProductPrefPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BatoBranchProductPrefPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BatoBranchProductPrefRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatoBranchProductPrefRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatoBranchProductPrefRow findRowByPk( String p_institutionCode, String p_branchCode, String p_productCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoBranchProductPrefPK pk = new BatoBranchProductPrefPK( p_institutionCode, p_branchCode, p_productCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBatoBranchProductPrefPKオブジェクトから{@@link BatoBranchProductPrefRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBatoBranchProductPrefPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatoBranchProductPrefRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatoBranchProductPrefRow findRowByPk( BatoBranchProductPrefPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatoBranchProductPrefRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(BatoBranchProductPrefRow)}を使用してください。 
   */
    public static BatoBranchProductPrefDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_productCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoBranchProductPrefPK pk = new BatoBranchProductPrefPK( p_institutionCode, p_branchCode, p_productCode );
        BatoBranchProductPrefRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BatoBranchProductPrefPK)}および{@@link #forRow(BatoBranchProductPrefRow)}を使用してください。 
   */
    public static BatoBranchProductPrefDao findDaoByPk( BatoBranchProductPrefPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoBranchProductPrefRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_productCode, and にて指定の値から一意の{@@link BatoBranchProductPrefRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_productCode, and の値と一致する{@@link BatoBranchProductPrefRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BatoBranchProductPrefRow findRowByInstitutionCodeBranchCodeProductCode( String p_institutionCode, String p_branchCode, String p_productCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatoBranchProductPrefRow.TYPE,
            "institution_code=? and branch_code=? and product_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_productCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatoBranchProductPrefRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatoBranchProductPrefDao.findRowsByInstitutionCodeBranchCodeProductCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeProductCode(String, String, String)}および{@@link #forRow(BatoBranchProductPrefRow)}を使用してください。 
   */
    public static BatoBranchProductPrefDao findDaoByInstitutionCodeBranchCodeProductCode( String p_institutionCode, String p_branchCode, String p_productCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeProductCode( p_institutionCode, p_branchCode, p_productCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
