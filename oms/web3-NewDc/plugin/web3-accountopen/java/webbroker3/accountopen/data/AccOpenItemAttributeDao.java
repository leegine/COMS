head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemAttributeDao.java;


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
 * {@@link AccOpenItemAttributeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenItemAttributeRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenItemAttributePK 
 * @@see AccOpenItemAttributeRow 
 */
public class AccOpenItemAttributeDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenItemAttributeDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenItemAttributeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenItemAttributeRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenItemAttributeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenItemAttributeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenItemAttributeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenItemAttributeRow )
                return new AccOpenItemAttributeDao( (AccOpenItemAttributeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenItemAttributeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenItemAttributeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenItemAttributeRow}オブジェクト 
    */
    protected AccOpenItemAttributeDao( AccOpenItemAttributeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenItemAttributeRow}オブジェクトを取得します。
   */
    public AccOpenItemAttributeRow getAccOpenItemAttributeRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenItemAttributeRow}オブジェクトから{@@link AccOpenItemAttributeDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenItemAttributeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenItemAttributeDao}取得のために指定の{@@link AccOpenItemAttributeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenItemAttributeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenItemAttributeDao forRow( AccOpenItemAttributeRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenItemAttributeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenItemAttributeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenItemAttributeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenItemAttributePK}やデータベースレコードとして挿入される{@@link AccOpenItemAttributeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenItemAttributeRow.TYPE );
    }


  /** 
   * {@@link AccOpenItemAttributeRow}を一意に特定する{@@link AccOpenItemAttributePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenItemAttributeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenItemAttributeParams}オブジェクトの主キーとして利用可能な{@@link AccOpenItemAttributePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenItemAttributePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenItemAttributeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_itemSymbolName 検索対象であるp_itemSymbolNameフィールドの値
   * @@param p_attributeValue 検索対象であるp_attributeValueフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenItemAttributeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenItemAttributeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemAttributePK pk = new AccOpenItemAttributePK( p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenItemAttributePKオブジェクトから{@@link AccOpenItemAttributeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenItemAttributePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenItemAttributeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenItemAttributeRow findRowByPk( AccOpenItemAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenItemAttributeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(AccOpenItemAttributeRow)}を使用してください。 
   */
    public static AccOpenItemAttributeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemAttributePK pk = new AccOpenItemAttributePK( p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue );
        AccOpenItemAttributeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenItemAttributePK)}および{@@link #forRow(AccOpenItemAttributeRow)}を使用してください。 
   */
    public static AccOpenItemAttributeDao findDaoByPk( AccOpenItemAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemAttributeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue, and にて指定の値から一意の{@@link AccOpenItemAttributeRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_itemSymbolName 検索対象であるp_itemSymbolNameフィールドの値
   * @@param p_attributeValue 検索対象であるp_attributeValueフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue, and の値と一致する{@@link AccOpenItemAttributeRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenItemAttributeRow findRowByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenItemAttributeRow.TYPE,
            "institution_code=? and branch_code=? and account_div=? and item_symbol_name=? and attribute_value=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenItemAttributeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenItemAttributeDao.findRowsByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue(String, String, String, String, String)}および{@@link #forRow(AccOpenItemAttributeRow)}を使用してください。 
   */
    public static AccOpenItemAttributeDao findDaoByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue( p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
