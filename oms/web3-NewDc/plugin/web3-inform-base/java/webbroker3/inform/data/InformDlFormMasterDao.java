head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.59.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformDlFormMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.inform.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link InformDlFormMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link InformDlFormMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see InformDlFormMasterPK 
 * @@see InformDlFormMasterRow 
 */
public class InformDlFormMasterDao extends DataAccessObject {


  /** 
   * この{@@link InformDlFormMasterDao}に関連する型指定のRowオブジェクト 
   */
    private InformDlFormMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link InformDlFormMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link InformDlFormMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link InformDlFormMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link InformDlFormMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformDlFormMasterRow )
                return new InformDlFormMasterDao( (InformDlFormMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformDlFormMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformDlFormMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link InformDlFormMasterRow}オブジェクト 
    */
    protected InformDlFormMasterDao( InformDlFormMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link InformDlFormMasterRow}オブジェクトを取得します。
   */
    public InformDlFormMasterRow getInformDlFormMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link InformDlFormMasterRow}オブジェクトから{@@link InformDlFormMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link InformDlFormMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link InformDlFormMasterDao}取得のために指定の{@@link InformDlFormMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link InformDlFormMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static InformDlFormMasterDao forRow( InformDlFormMasterRow row ) throws java.lang.IllegalArgumentException {
        return (InformDlFormMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformDlFormMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link InformDlFormMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link InformDlFormMasterPK}やデータベースレコードとして挿入される{@@link InformDlFormMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformDlFormMasterRow.TYPE );
    }


  /** 
   * {@@link InformDlFormMasterRow}を一意に特定する{@@link InformDlFormMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link InformDlFormMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link InformDlFormMasterParams}オブジェクトの主キーとして利用可能な{@@link InformDlFormMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static InformDlFormMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link InformDlFormMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * @@param p_columnNumber 検索対象であるp_columnNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformDlFormMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformDlFormMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_informDiv, int p_columnNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        InformDlFormMasterPK pk = new InformDlFormMasterPK( p_institutionCode, p_branchCode, p_informDiv, p_columnNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のInformDlFormMasterPKオブジェクトから{@@link InformDlFormMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するInformDlFormMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformDlFormMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformDlFormMasterRow findRowByPk( InformDlFormMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformDlFormMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,int)}および{@@link #forRow(InformDlFormMasterRow)}を使用してください。 
   */
    public static InformDlFormMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_informDiv, int p_columnNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        InformDlFormMasterPK pk = new InformDlFormMasterPK( p_institutionCode, p_branchCode, p_informDiv, p_columnNumber );
        InformDlFormMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(InformDlFormMasterPK)}および{@@link #forRow(InformDlFormMasterRow)}を使用してください。 
   */
    public static InformDlFormMasterDao findDaoByPk( InformDlFormMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformDlFormMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_informDiv, p_columnNumber, and にて指定の値から一意の{@@link InformDlFormMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * @@param p_columnNumber 検索対象であるp_columnNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_informDiv, p_columnNumber, and の値と一致する{@@link InformDlFormMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static InformDlFormMasterRow findRowByInstitutionCodeBranchCodeInformDivColumnNumber( String p_institutionCode, String p_branchCode, String p_informDiv, int p_columnNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InformDlFormMasterRow.TYPE,
            "institution_code=? and branch_code=? and inform_div=? and column_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_informDiv, new Integer(p_columnNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InformDlFormMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InformDlFormMasterDao.findRowsByInstitutionCodeBranchCodeInformDivColumnNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeInformDivColumnNumber(String, String, String, int)}および{@@link #forRow(InformDlFormMasterRow)}を使用してください。 
   */
    public static InformDlFormMasterDao findDaoByInstitutionCodeBranchCodeInformDivColumnNumber( String p_institutionCode, String p_branchCode, String p_informDiv, int p_columnNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeInformDivColumnNumber( p_institutionCode, p_branchCode, p_informDiv, p_columnNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
