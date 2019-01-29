head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	OtherOrderExecutedCountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link OtherOrderExecutedCountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OtherOrderExecutedCountRow}インスタンスへ関連付けることができます。 
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
 * @@see OtherOrderExecutedCountPK 
 * @@see OtherOrderExecutedCountRow 
 */
public class OtherOrderExecutedCountDao extends DataAccessObject {


  /** 
   * この{@@link OtherOrderExecutedCountDao}に関連する型指定のRowオブジェクト 
   */
    private OtherOrderExecutedCountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OtherOrderExecutedCountRow}と仮定される{@@link DataAccessObject}から新たに{@@link OtherOrderExecutedCountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OtherOrderExecutedCountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OtherOrderExecutedCountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OtherOrderExecutedCountRow )
                return new OtherOrderExecutedCountDao( (OtherOrderExecutedCountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OtherOrderExecutedCountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OtherOrderExecutedCountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OtherOrderExecutedCountRow}オブジェクト 
    */
    protected OtherOrderExecutedCountDao( OtherOrderExecutedCountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OtherOrderExecutedCountRow}オブジェクトを取得します。
   */
    public OtherOrderExecutedCountRow getOtherOrderExecutedCountRow() {
        return row;
    }


  /** 
   * 指定の{@@link OtherOrderExecutedCountRow}オブジェクトから{@@link OtherOrderExecutedCountDao}オブジェクトを作成します。 
   * これは実際の{@@link OtherOrderExecutedCountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OtherOrderExecutedCountDao}取得のために指定の{@@link OtherOrderExecutedCountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OtherOrderExecutedCountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OtherOrderExecutedCountDao forRow( OtherOrderExecutedCountRow row ) throws java.lang.IllegalArgumentException {
        return (OtherOrderExecutedCountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OtherOrderExecutedCountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OtherOrderExecutedCountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OtherOrderExecutedCountPK}やデータベースレコードとして挿入される{@@link OtherOrderExecutedCountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OtherOrderExecutedCountRow.TYPE );
    }


  /** 
   * {@@link OtherOrderExecutedCountRow}を一意に特定する{@@link OtherOrderExecutedCountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OtherOrderExecutedCountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OtherOrderExecutedCountParams}オブジェクトの主キーとして利用可能な{@@link OtherOrderExecutedCountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OtherOrderExecutedCountPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OtherOrderExecutedCountRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_recordDiv 検索対象であるp_recordDivフィールドの値
   * @@param p_productDiv 検索対象であるp_productDivフィールドの値
   * @@param p_paySchemeId 検索対象であるp_paySchemeIdフィールドの値
   * @@param p_orderChanel 検索対象であるp_orderChanelフィールドの値
   * @@param p_orderRootDiv 検索対象であるp_orderRootDivフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrderExecutedCountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrderExecutedCountRow findRowByPk( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_productDiv, String p_paySchemeId, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrderExecutedCountPK pk = new OtherOrderExecutedCountPK( p_institutionCode, p_branchCode, p_recordDiv, p_productDiv, p_paySchemeId, p_orderChanel, p_orderRootDiv, p_bizDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOtherOrderExecutedCountPKオブジェクトから{@@link OtherOrderExecutedCountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOtherOrderExecutedCountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrderExecutedCountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrderExecutedCountRow findRowByPk( OtherOrderExecutedCountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OtherOrderExecutedCountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String,String,String,String)}および{@@link #forRow(OtherOrderExecutedCountRow)}を使用してください。 
   */
    public static OtherOrderExecutedCountDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_productDiv, String p_paySchemeId, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrderExecutedCountPK pk = new OtherOrderExecutedCountPK( p_institutionCode, p_branchCode, p_recordDiv, p_productDiv, p_paySchemeId, p_orderChanel, p_orderRootDiv, p_bizDate );
        OtherOrderExecutedCountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OtherOrderExecutedCountPK)}および{@@link #forRow(OtherOrderExecutedCountRow)}を使用してください。 
   */
    public static OtherOrderExecutedCountDao findDaoByPk( OtherOrderExecutedCountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrderExecutedCountRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_recordDiv, p_productDiv, p_paySchemeId, p_orderChanel, p_orderRootDiv, p_bizDate, and にて指定の値から一意の{@@link OtherOrderExecutedCountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_recordDiv 検索対象であるp_recordDivフィールドの値
   * @@param p_productDiv 検索対象であるp_productDivフィールドの値
   * @@param p_paySchemeId 検索対象であるp_paySchemeIdフィールドの値
   * @@param p_orderChanel 検索対象であるp_orderChanelフィールドの値
   * @@param p_orderRootDiv 検索対象であるp_orderRootDivフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_recordDiv, p_productDiv, p_paySchemeId, p_orderChanel, p_orderRootDiv, p_bizDate, and の値と一致する{@@link OtherOrderExecutedCountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OtherOrderExecutedCountRow findRowByInstitutionCodeBranchCodeRecordDivProductDivPaySchemeIdOrderChanelOrderRootDivBizDate( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_productDiv, String p_paySchemeId, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OtherOrderExecutedCountRow.TYPE,
            "institution_code=? and branch_code=? and record_div=? and product_div=? and pay_scheme_id=? and order_chanel=? and order_root_div=? and biz_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_recordDiv, p_productDiv, p_paySchemeId, p_orderChanel, p_orderRootDiv, p_bizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OtherOrderExecutedCountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OtherOrderExecutedCountDao.findRowsByInstitutionCodeBranchCodeRecordDivProductDivPaySchemeIdOrderChanelOrderRootDivBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeRecordDivProductDivPaySchemeIdOrderChanelOrderRootDivBizDate(String, String, String, String, String, String, String, String)}および{@@link #forRow(OtherOrderExecutedCountRow)}を使用してください。 
   */
    public static OtherOrderExecutedCountDao findDaoByInstitutionCodeBranchCodeRecordDivProductDivPaySchemeIdOrderChanelOrderRootDivBizDate( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_productDiv, String p_paySchemeId, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeRecordDivProductDivPaySchemeIdOrderChanelOrderRootDivBizDate( p_institutionCode, p_branchCode, p_recordDiv, p_productDiv, p_paySchemeId, p_orderChanel, p_orderRootDiv, p_bizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
