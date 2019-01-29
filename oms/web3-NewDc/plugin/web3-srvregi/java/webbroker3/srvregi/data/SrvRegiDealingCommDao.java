head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiDealingCommDao.java;


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
 * {@@link SrvRegiDealingCommDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiDealingCommRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiDealingCommPK 
 * @@see SrvRegiDealingCommRow 
 */
public class SrvRegiDealingCommDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiDealingCommDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiDealingCommRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiDealingCommRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiDealingCommDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiDealingCommDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiDealingCommRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiDealingCommRow )
                return new SrvRegiDealingCommDao( (SrvRegiDealingCommRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiDealingCommRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiDealingCommRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiDealingCommRow}オブジェクト 
    */
    protected SrvRegiDealingCommDao( SrvRegiDealingCommRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiDealingCommRow}オブジェクトを取得します。
   */
    public SrvRegiDealingCommRow getSrvRegiDealingCommRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiDealingCommRow}オブジェクトから{@@link SrvRegiDealingCommDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiDealingCommRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiDealingCommDao}取得のために指定の{@@link SrvRegiDealingCommRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiDealingCommDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiDealingCommDao forRow( SrvRegiDealingCommRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiDealingCommDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiDealingCommRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiDealingCommRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiDealingCommPK}やデータベースレコードとして挿入される{@@link SrvRegiDealingCommParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiDealingCommRow.TYPE );
    }


  /** 
   * {@@link SrvRegiDealingCommRow}を一意に特定する{@@link SrvRegiDealingCommPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiDealingCommRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiDealingCommParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiDealingCommPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiDealingCommPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiDealingCommRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_accumulateTerm 検索対象であるp_accumulateTermフィールドの値
   * @@param p_orderAccProduct 検索対象であるp_orderAccProductフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiDealingCommRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiDealingCommRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiDealingCommPK pk = new SrvRegiDealingCommPK( p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiDealingCommPKオブジェクトから{@@link SrvRegiDealingCommRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiDealingCommPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiDealingCommRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiDealingCommRow findRowByPk( SrvRegiDealingCommPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiDealingCommRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(SrvRegiDealingCommRow)}を使用してください。 
   */
    public static SrvRegiDealingCommDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiDealingCommPK pk = new SrvRegiDealingCommPK( p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct );
        SrvRegiDealingCommRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiDealingCommPK)}および{@@link #forRow(SrvRegiDealingCommRow)}を使用してください。 
   */
    public static SrvRegiDealingCommDao findDaoByPk( SrvRegiDealingCommPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiDealingCommRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct, and にて指定の値から一意の{@@link SrvRegiDealingCommRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_accumulateTerm 検索対象であるp_accumulateTermフィールドの値
   * @@param p_orderAccProduct 検索対象であるp_orderAccProductフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct, and の値と一致する{@@link SrvRegiDealingCommRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiDealingCommRow findRowByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiDealingCommRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and accumulate_term=? and order_acc_product=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiDealingCommRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiDealingCommDao.findRowsByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct(String, String, String, String, String)}および{@@link #forRow(SrvRegiDealingCommRow)}を使用してください。 
   */
    public static SrvRegiDealingCommDao findDaoByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct( p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
