head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.41.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CompBankCareerManagementDao.java;


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
 * {@@link CompBankCareerManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CompBankCareerManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see CompBankCareerManagementPK 
 * @@see CompBankCareerManagementRow 
 */
public class CompBankCareerManagementDao extends DataAccessObject {


  /** 
   * この{@@link CompBankCareerManagementDao}に関連する型指定のRowオブジェクト 
   */
    private CompBankCareerManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CompBankCareerManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link CompBankCareerManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CompBankCareerManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CompBankCareerManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CompBankCareerManagementRow )
                return new CompBankCareerManagementDao( (CompBankCareerManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CompBankCareerManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CompBankCareerManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CompBankCareerManagementRow}オブジェクト 
    */
    protected CompBankCareerManagementDao( CompBankCareerManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CompBankCareerManagementRow}オブジェクトを取得します。
   */
    public CompBankCareerManagementRow getCompBankCareerManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link CompBankCareerManagementRow}オブジェクトから{@@link CompBankCareerManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link CompBankCareerManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CompBankCareerManagementDao}取得のために指定の{@@link CompBankCareerManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CompBankCareerManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CompBankCareerManagementDao forRow( CompBankCareerManagementRow row ) throws java.lang.IllegalArgumentException {
        return (CompBankCareerManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CompBankCareerManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CompBankCareerManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CompBankCareerManagementPK}やデータベースレコードとして挿入される{@@link CompBankCareerManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CompBankCareerManagementRow.TYPE );
    }


  /** 
   * {@@link CompBankCareerManagementRow}を一意に特定する{@@link CompBankCareerManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CompBankCareerManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CompBankCareerManagementParams}オブジェクトの主キーとして利用可能な{@@link CompBankCareerManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CompBankCareerManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CompBankCareerManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_paySchemeId 検索対象であるp_paySchemeIdフィールドの値
   * @@param p_careerDiv 検索対象であるp_careerDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CompBankCareerManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CompBankCareerManagementRow findRowByPk( String p_institutionCode, String p_branchCode, String p_paySchemeId, String p_careerDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankCareerManagementPK pk = new CompBankCareerManagementPK( p_institutionCode, p_branchCode, p_paySchemeId, p_careerDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCompBankCareerManagementPKオブジェクトから{@@link CompBankCareerManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCompBankCareerManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CompBankCareerManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CompBankCareerManagementRow findRowByPk( CompBankCareerManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CompBankCareerManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(CompBankCareerManagementRow)}を使用してください。 
   */
    public static CompBankCareerManagementDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_paySchemeId, String p_careerDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankCareerManagementPK pk = new CompBankCareerManagementPK( p_institutionCode, p_branchCode, p_paySchemeId, p_careerDiv );
        CompBankCareerManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CompBankCareerManagementPK)}および{@@link #forRow(CompBankCareerManagementRow)}を使用してください。 
   */
    public static CompBankCareerManagementDao findDaoByPk( CompBankCareerManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankCareerManagementRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
