head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlItemAttributeDao.java;


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
 * {@@link InformCtrlItemAttributeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link InformCtrlItemAttributeRow}インスタンスへ関連付けることができます。 
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
 * @@see InformCtrlItemAttributePK 
 * @@see InformCtrlItemAttributeRow 
 */
public class InformCtrlItemAttributeDao extends DataAccessObject {


  /** 
   * この{@@link InformCtrlItemAttributeDao}に関連する型指定のRowオブジェクト 
   */
    private InformCtrlItemAttributeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link InformCtrlItemAttributeRow}と仮定される{@@link DataAccessObject}から新たに{@@link InformCtrlItemAttributeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link InformCtrlItemAttributeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link InformCtrlItemAttributeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformCtrlItemAttributeRow )
                return new InformCtrlItemAttributeDao( (InformCtrlItemAttributeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformCtrlItemAttributeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformCtrlItemAttributeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link InformCtrlItemAttributeRow}オブジェクト 
    */
    protected InformCtrlItemAttributeDao( InformCtrlItemAttributeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link InformCtrlItemAttributeRow}オブジェクトを取得します。
   */
    public InformCtrlItemAttributeRow getInformCtrlItemAttributeRow() {
        return row;
    }


  /** 
   * 指定の{@@link InformCtrlItemAttributeRow}オブジェクトから{@@link InformCtrlItemAttributeDao}オブジェクトを作成します。 
   * これは実際の{@@link InformCtrlItemAttributeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link InformCtrlItemAttributeDao}取得のために指定の{@@link InformCtrlItemAttributeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link InformCtrlItemAttributeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static InformCtrlItemAttributeDao forRow( InformCtrlItemAttributeRow row ) throws java.lang.IllegalArgumentException {
        return (InformCtrlItemAttributeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformCtrlItemAttributeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link InformCtrlItemAttributeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link InformCtrlItemAttributePK}やデータベースレコードとして挿入される{@@link InformCtrlItemAttributeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformCtrlItemAttributeRow.TYPE );
    }


  /** 
   * {@@link InformCtrlItemAttributeRow}を一意に特定する{@@link InformCtrlItemAttributePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link InformCtrlItemAttributeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link InformCtrlItemAttributeParams}オブジェクトの主キーとして利用可能な{@@link InformCtrlItemAttributePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static InformCtrlItemAttributePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link InformCtrlItemAttributeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * @@param p_itemSymbolName 検索対象であるp_itemSymbolNameフィールドの値
   * @@param p_attributeValue 検索対象であるp_attributeValueフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformCtrlItemAttributeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformCtrlItemAttributeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemAttributePK pk = new InformCtrlItemAttributePK( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue );
        return findRowByPk( pk );
    }


  /** 
   * 指定のInformCtrlItemAttributePKオブジェクトから{@@link InformCtrlItemAttributeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するInformCtrlItemAttributePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformCtrlItemAttributeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformCtrlItemAttributeRow findRowByPk( InformCtrlItemAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformCtrlItemAttributeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(InformCtrlItemAttributeRow)}を使用してください。 
   */
    public static InformCtrlItemAttributeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemAttributePK pk = new InformCtrlItemAttributePK( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue );
        InformCtrlItemAttributeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(InformCtrlItemAttributePK)}および{@@link #forRow(InformCtrlItemAttributeRow)}を使用してください。 
   */
    public static InformCtrlItemAttributeDao findDaoByPk( InformCtrlItemAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemAttributeRow row = findRowByPk( pk );
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


  /** 
   * p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue, and にて指定の値に一致する{@@link InformCtrlItemAttributeRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * @@param p_itemSymbolName 検索対象であるp_itemSymbolNameフィールドの値
   * @@param p_attributeValue 検索対象であるp_attributeValueフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue, and の値と一致する{@@link InformCtrlItemAttributeRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeInformDivItemSymbolNameAttributeValue( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            InformCtrlItemAttributeRow.TYPE,
            "institution_code=? and branch_code=? and inform_div=? and item_symbol_name=? and attribute_value=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeInformDivItemSymbolNameAttributeValue(String, String, String, String, String)}および{@@link #forRow(InformCtrlItemAttributeRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeInformDivItemSymbolNameAttributeValue( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeInformDivItemSymbolNameAttributeValue( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue ) );
    }

}
@
