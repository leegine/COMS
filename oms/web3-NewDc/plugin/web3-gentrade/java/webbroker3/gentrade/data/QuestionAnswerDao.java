head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	QuestionAnswerDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link QuestionAnswerDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QuestionAnswerRow}インスタンスへ関連付けることができます。 
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
 * @@see QuestionAnswerPK 
 * @@see QuestionAnswerRow 
 */
public class QuestionAnswerDao extends DataAccessObject {


  /** 
   * この{@@link QuestionAnswerDao}に関連する型指定のRowオブジェクト 
   */
    private QuestionAnswerRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QuestionAnswerRow}と仮定される{@@link DataAccessObject}から新たに{@@link QuestionAnswerDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QuestionAnswerDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QuestionAnswerRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QuestionAnswerRow )
                return new QuestionAnswerDao( (QuestionAnswerRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QuestionAnswerRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QuestionAnswerRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QuestionAnswerRow}オブジェクト 
    */
    protected QuestionAnswerDao( QuestionAnswerRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QuestionAnswerRow}オブジェクトを取得します。
   */
    public QuestionAnswerRow getQuestionAnswerRow() {
        return row;
    }


  /** 
   * 指定の{@@link QuestionAnswerRow}オブジェクトから{@@link QuestionAnswerDao}オブジェクトを作成します。 
   * これは実際の{@@link QuestionAnswerRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QuestionAnswerDao}取得のために指定の{@@link QuestionAnswerRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QuestionAnswerDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QuestionAnswerDao forRow( QuestionAnswerRow row ) throws java.lang.IllegalArgumentException {
        return (QuestionAnswerDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QuestionAnswerRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QuestionAnswerRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QuestionAnswerPK}やデータベースレコードとして挿入される{@@link QuestionAnswerParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QuestionAnswerRow.TYPE );
    }


  /** 
   * {@@link QuestionAnswerRow}を一意に特定する{@@link QuestionAnswerPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QuestionAnswerRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QuestionAnswerParams}オブジェクトの主キーとして利用可能な{@@link QuestionAnswerPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QuestionAnswerPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QuestionAnswerRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_questionDiv 検索対象であるp_questionDivフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_questionNo 検索対象であるp_questionNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QuestionAnswerRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QuestionAnswerRow findRowByPk( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionAnswerPK pk = new QuestionAnswerPK( p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQuestionAnswerPKオブジェクトから{@@link QuestionAnswerRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQuestionAnswerPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QuestionAnswerRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QuestionAnswerRow findRowByPk( QuestionAnswerPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QuestionAnswerRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(QuestionAnswerRow)}を使用してください。 
   */
    public static QuestionAnswerDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionAnswerPK pk = new QuestionAnswerPK( p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo );
        QuestionAnswerRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QuestionAnswerPK)}および{@@link #forRow(QuestionAnswerRow)}を使用してください。 
   */
    public static QuestionAnswerDao findDaoByPk( QuestionAnswerPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionAnswerRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo, and にて指定の値から一意の{@@link QuestionAnswerRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_questionDiv 検索対象であるp_questionDivフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_questionNo 検索対象であるp_questionNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo, and の値と一致する{@@link QuestionAnswerRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static QuestionAnswerRow findRowByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QuestionAnswerRow.TYPE,
            "institution_code=? and branch_code=? and question_div=? and order_request_number=? and question_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QuestionAnswerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QuestionAnswerDao.findRowsByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo(String, String, String, String, String)}および{@@link #forRow(QuestionAnswerRow)}を使用してください。 
   */
    public static QuestionAnswerDao findDaoByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo( p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, and にて指定の値に一致する{@@link QuestionAnswerRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_questionDiv 検索対象であるp_questionDivフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, and の値と一致する{@@link QuestionAnswerRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeQuestionDivOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QuestionAnswerRow.TYPE,
            "institution_code=? and branch_code=? and question_div=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeQuestionDivOrderRequestNumber(String, String, String, String)}および{@@link #forRow(QuestionAnswerRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeQuestionDivOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeQuestionDivOrderRequestNumber( p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber ) );
    }

}
@
