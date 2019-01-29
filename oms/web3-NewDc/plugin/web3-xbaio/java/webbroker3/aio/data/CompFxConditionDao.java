head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	CompFxConditionDao.java;


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
 * {@@link CompFxConditionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CompFxConditionRow}インスタンスへ関連付けることができます。 
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
 * @@see CompFxConditionPK 
 * @@see CompFxConditionRow 
 */
public class CompFxConditionDao extends DataAccessObject {


  /** 
   * この{@@link CompFxConditionDao}に関連する型指定のRowオブジェクト 
   */
    private CompFxConditionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CompFxConditionRow}と仮定される{@@link DataAccessObject}から新たに{@@link CompFxConditionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CompFxConditionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CompFxConditionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CompFxConditionRow )
                return new CompFxConditionDao( (CompFxConditionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CompFxConditionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CompFxConditionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CompFxConditionRow}オブジェクト 
    */
    protected CompFxConditionDao( CompFxConditionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CompFxConditionRow}オブジェクトを取得します。
   */
    public CompFxConditionRow getCompFxConditionRow() {
        return row;
    }


  /** 
   * 指定の{@@link CompFxConditionRow}オブジェクトから{@@link CompFxConditionDao}オブジェクトを作成します。 
   * これは実際の{@@link CompFxConditionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CompFxConditionDao}取得のために指定の{@@link CompFxConditionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CompFxConditionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CompFxConditionDao forRow( CompFxConditionRow row ) throws java.lang.IllegalArgumentException {
        return (CompFxConditionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CompFxConditionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CompFxConditionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CompFxConditionPK}やデータベースレコードとして挿入される{@@link CompFxConditionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CompFxConditionRow.TYPE );
    }


  /** 
   * {@@link CompFxConditionRow}を一意に特定する{@@link CompFxConditionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CompFxConditionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CompFxConditionParams}オブジェクトの主キーとして利用可能な{@@link CompFxConditionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CompFxConditionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CompFxConditionRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fxSystemCode 検索対象であるp_fxSystemCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CompFxConditionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CompFxConditionRow findRowByPk( String p_institutionCode, String p_branchCode, String p_fxSystemCode ) throws DataFindException, DataQueryException, DataNetworkException {
        CompFxConditionPK pk = new CompFxConditionPK( p_institutionCode, p_branchCode, p_fxSystemCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCompFxConditionPKオブジェクトから{@@link CompFxConditionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCompFxConditionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CompFxConditionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CompFxConditionRow findRowByPk( CompFxConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CompFxConditionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(CompFxConditionRow)}を使用してください。 
   */
    public static CompFxConditionDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_fxSystemCode ) throws DataFindException, DataQueryException, DataNetworkException {
        CompFxConditionPK pk = new CompFxConditionPK( p_institutionCode, p_branchCode, p_fxSystemCode );
        CompFxConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CompFxConditionPK)}および{@@link #forRow(CompFxConditionRow)}を使用してください。 
   */
    public static CompFxConditionDao findDaoByPk( CompFxConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CompFxConditionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_fxSystemCode, and にて指定の値から一意の{@@link CompFxConditionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fxSystemCode 検索対象であるp_fxSystemCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_fxSystemCode, and の値と一致する{@@link CompFxConditionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CompFxConditionRow findRowByInstitutionCodeBranchCodeFxSystemCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CompFxConditionRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CompFxConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CompFxConditionDao.findRowsByInstitutionCodeBranchCodeFxSystemCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeFxSystemCode(String, String, String)}および{@@link #forRow(CompFxConditionRow)}を使用してください。 
   */
    public static CompFxConditionDao findDaoByInstitutionCodeBranchCodeFxSystemCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFxSystemCode( p_institutionCode, p_branchCode, p_fxSystemCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, and にて指定の値に一致する{@@link CompFxConditionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, and の値と一致する{@@link CompFxConditionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CompFxConditionRow.TYPE,
            "institution_code=? and branch_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCode(String, String)}および{@@link #forRow(CompFxConditionRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCode( p_institutionCode, p_branchCode ) );
    }

}
@
