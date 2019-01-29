head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.10.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AccOpenItemMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenItemMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenItemMasterPK 
 * @@see AccOpenItemMasterRow 
 */
public class AccOpenItemMasterDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenItemMasterDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenItemMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenItemMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenItemMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenItemMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenItemMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenItemMasterRow )
                return new AccOpenItemMasterDao( (AccOpenItemMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenItemMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenItemMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenItemMasterRow}オブジェクト 
    */
    protected AccOpenItemMasterDao( AccOpenItemMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenItemMasterRow}オブジェクトを取得します。
   */
    public AccOpenItemMasterRow getAccOpenItemMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenItemMasterRow}オブジェクトから{@@link AccOpenItemMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenItemMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenItemMasterDao}取得のために指定の{@@link AccOpenItemMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenItemMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenItemMasterDao forRow( AccOpenItemMasterRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenItemMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenItemMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenItemMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenItemMasterPK}やデータベースレコードとして挿入される{@@link AccOpenItemMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenItemMasterRow.TYPE );
    }


  /** 
   * {@@link AccOpenItemMasterRow}を一意に特定する{@@link AccOpenItemMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenItemMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenItemMasterParams}オブジェクトの主キーとして利用可能な{@@link AccOpenItemMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenItemMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenItemMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_validateType 検索対象であるp_validateTypeフィールドの値
   * @@param p_itemSymbolName 検索対象であるp_itemSymbolNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenItemMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenItemMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemMasterPK pk = new AccOpenItemMasterPK( p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenItemMasterPKオブジェクトから{@@link AccOpenItemMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenItemMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenItemMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenItemMasterRow findRowByPk( AccOpenItemMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenItemMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(AccOpenItemMasterRow)}を使用してください。 
   */
    public static AccOpenItemMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemMasterPK pk = new AccOpenItemMasterPK( p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName );
        AccOpenItemMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenItemMasterPK)}および{@@link #forRow(AccOpenItemMasterRow)}を使用してください。 
   */
    public static AccOpenItemMasterDao findDaoByPk( AccOpenItemMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName, and にて指定の値から一意の{@@link AccOpenItemMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_validateType 検索対象であるp_validateTypeフィールドの値
   * @@param p_itemSymbolName 検索対象であるp_itemSymbolNameフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName, and の値と一致する{@@link AccOpenItemMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenItemMasterRow findRowByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenItemMasterRow.TYPE,
            "institution_code=? and branch_code=? and account_div=? and validate_type=? and item_symbol_name=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenItemMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenItemMasterDao.findRowsByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName(String, String, String, String, String)}および{@@link #forRow(AccOpenItemMasterRow)}を使用してください。 
   */
    public static AccOpenItemMasterDao findDaoByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName( p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
