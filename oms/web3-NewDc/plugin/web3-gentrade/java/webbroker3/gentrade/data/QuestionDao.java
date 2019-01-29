head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	QuestionDao.java;


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
 * {@@link QuestionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QuestionRow}インスタンスへ関連付けることができます。 
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
 * @@see QuestionPK 
 * @@see QuestionRow 
 */
public class QuestionDao extends DataAccessObject {


  /** 
   * この{@@link QuestionDao}に関連する型指定のRowオブジェクト 
   */
    private QuestionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QuestionRow}と仮定される{@@link DataAccessObject}から新たに{@@link QuestionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QuestionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QuestionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QuestionRow )
                return new QuestionDao( (QuestionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QuestionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QuestionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QuestionRow}オブジェクト 
    */
    protected QuestionDao( QuestionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QuestionRow}オブジェクトを取得します。
   */
    public QuestionRow getQuestionRow() {
        return row;
    }


  /** 
   * 指定の{@@link QuestionRow}オブジェクトから{@@link QuestionDao}オブジェクトを作成します。 
   * これは実際の{@@link QuestionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QuestionDao}取得のために指定の{@@link QuestionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QuestionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QuestionDao forRow( QuestionRow row ) throws java.lang.IllegalArgumentException {
        return (QuestionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QuestionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QuestionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QuestionPK}やデータベースレコードとして挿入される{@@link QuestionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QuestionRow.TYPE );
    }


  /** 
   * {@@link QuestionRow}を一意に特定する{@@link QuestionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QuestionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QuestionParams}オブジェクトの主キーとして利用可能な{@@link QuestionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QuestionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QuestionRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_questionDiv 検索対象であるp_questionDivフィールドの値
   * @@param p_questionNo 検索対象であるp_questionNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QuestionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QuestionRow findRowByPk( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_questionNo ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionPK pk = new QuestionPK( p_institutionCode, p_branchCode, p_questionDiv, p_questionNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQuestionPKオブジェクトから{@@link QuestionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQuestionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QuestionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QuestionRow findRowByPk( QuestionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QuestionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(QuestionRow)}を使用してください。 
   */
    public static QuestionDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_questionNo ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionPK pk = new QuestionPK( p_institutionCode, p_branchCode, p_questionDiv, p_questionNo );
        QuestionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QuestionPK)}および{@@link #forRow(QuestionRow)}を使用してください。 
   */
    public static QuestionDao findDaoByPk( QuestionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_questionDiv, p_questionNo, and にて指定の値から一意の{@@link QuestionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_questionDiv 検索対象であるp_questionDivフィールドの値
   * @@param p_questionNo 検索対象であるp_questionNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_questionDiv, p_questionNo, and の値と一致する{@@link QuestionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static QuestionRow findRowByInstitutionCodeBranchCodeQuestionDivQuestionNo( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_questionNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QuestionRow.TYPE,
            "institution_code=? and branch_code=? and question_div=? and question_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_questionDiv, p_questionNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QuestionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QuestionDao.findRowsByInstitutionCodeBranchCodeQuestionDivQuestionNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeQuestionDivQuestionNo(String, String, String, String)}および{@@link #forRow(QuestionRow)}を使用してください。 
   */
    public static QuestionDao findDaoByInstitutionCodeBranchCodeQuestionDivQuestionNo( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_questionNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeQuestionDivQuestionNo( p_institutionCode, p_branchCode, p_questionDiv, p_questionNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_questionDiv, and にて指定の値に一致する{@@link QuestionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_questionDiv 検索対象であるp_questionDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_questionDiv, and の値と一致する{@@link QuestionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeQuestionDiv( String p_institutionCode, String p_branchCode, String p_questionDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QuestionRow.TYPE,
            "institution_code=? and branch_code=? and question_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_questionDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeQuestionDiv(String, String, String)}および{@@link #forRow(QuestionRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeQuestionDiv( String p_institutionCode, String p_branchCode, String p_questionDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeQuestionDiv( p_institutionCode, p_branchCode, p_questionDiv ) );
    }

}
@
