head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CodeTranslationDao.java;


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
 * {@@link CodeTranslationDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CodeTranslationRow}インスタンスへ関連付けることができます。 
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
 * @@see CodeTranslationPK 
 * @@see CodeTranslationRow 
 */
public class CodeTranslationDao extends DataAccessObject {


  /** 
   * この{@@link CodeTranslationDao}に関連する型指定のRowオブジェクト 
   */
    private CodeTranslationRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CodeTranslationRow}と仮定される{@@link DataAccessObject}から新たに{@@link CodeTranslationDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CodeTranslationDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CodeTranslationRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CodeTranslationRow )
                return new CodeTranslationDao( (CodeTranslationRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CodeTranslationRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CodeTranslationRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CodeTranslationRow}オブジェクト 
    */
    protected CodeTranslationDao( CodeTranslationRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CodeTranslationRow}オブジェクトを取得します。
   */
    public CodeTranslationRow getCodeTranslationRow() {
        return row;
    }


  /** 
   * 指定の{@@link CodeTranslationRow}オブジェクトから{@@link CodeTranslationDao}オブジェクトを作成します。 
   * これは実際の{@@link CodeTranslationRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CodeTranslationDao}取得のために指定の{@@link CodeTranslationRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CodeTranslationDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CodeTranslationDao forRow( CodeTranslationRow row ) throws java.lang.IllegalArgumentException {
        return (CodeTranslationDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CodeTranslationRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CodeTranslationRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CodeTranslationPK}やデータベースレコードとして挿入される{@@link CodeTranslationParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CodeTranslationRow.TYPE );
    }


  /** 
   * {@@link CodeTranslationRow}を一意に特定する{@@link CodeTranslationPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CodeTranslationRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CodeTranslationParams}オブジェクトの主キーとして利用可能な{@@link CodeTranslationPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CodeTranslationPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CodeTranslationRow}オブジェクトを検索します。 
   * 
   * @@param p_codeType 検索対象であるp_codeTypeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_code 検索対象であるp_codeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CodeTranslationRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CodeTranslationRow findRowByPk( String p_codeType, String p_institutionCode, String p_code ) throws DataFindException, DataQueryException, DataNetworkException {
        CodeTranslationPK pk = new CodeTranslationPK( p_codeType, p_institutionCode, p_code );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCodeTranslationPKオブジェクトから{@@link CodeTranslationRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCodeTranslationPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CodeTranslationRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CodeTranslationRow findRowByPk( CodeTranslationPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CodeTranslationRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(CodeTranslationRow)}を使用してください。 
   */
    public static CodeTranslationDao findDaoByPk( String p_codeType, String p_institutionCode, String p_code ) throws DataFindException, DataQueryException, DataNetworkException {
        CodeTranslationPK pk = new CodeTranslationPK( p_codeType, p_institutionCode, p_code );
        CodeTranslationRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CodeTranslationPK)}および{@@link #forRow(CodeTranslationRow)}を使用してください。 
   */
    public static CodeTranslationDao findDaoByPk( CodeTranslationPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CodeTranslationRow row = findRowByPk( pk );
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
   * p_codeType, p_institutionCode, p_code, and にて指定の値から一意の{@@link CodeTranslationRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_codeType 検索対象であるp_codeTypeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_code 検索対象であるp_codeフィールドの値
   * 
   * @@return 引数指定のp_codeType, p_institutionCode, p_code, and の値と一致する{@@link CodeTranslationRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CodeTranslationRow findRowByCodeTypeInstitutionCodeCode( String p_codeType, String p_institutionCode, String p_code ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CodeTranslationRow.TYPE,
            "code_type=? and institution_code=? and code=?",
            null,
            new Object[] { p_codeType, p_institutionCode, p_code } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CodeTranslationRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CodeTranslationDao.findRowsByCodeTypeInstitutionCodeCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByCodeTypeInstitutionCodeCode(String, String, String)}および{@@link #forRow(CodeTranslationRow)}を使用してください。 
   */
    public static CodeTranslationDao findDaoByCodeTypeInstitutionCodeCode( String p_codeType, String p_institutionCode, String p_code ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCodeTypeInstitutionCodeCode( p_codeType, p_institutionCode, p_code ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
