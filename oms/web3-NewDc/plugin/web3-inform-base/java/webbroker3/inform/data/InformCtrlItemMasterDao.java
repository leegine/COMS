head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlItemMasterDao.java;


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
 * {@@link InformCtrlItemMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link InformCtrlItemMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see InformCtrlItemMasterPK 
 * @@see InformCtrlItemMasterRow 
 */
public class InformCtrlItemMasterDao extends DataAccessObject {


  /** 
   * この{@@link InformCtrlItemMasterDao}に関連する型指定のRowオブジェクト 
   */
    private InformCtrlItemMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link InformCtrlItemMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link InformCtrlItemMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link InformCtrlItemMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link InformCtrlItemMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformCtrlItemMasterRow )
                return new InformCtrlItemMasterDao( (InformCtrlItemMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformCtrlItemMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformCtrlItemMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link InformCtrlItemMasterRow}オブジェクト 
    */
    protected InformCtrlItemMasterDao( InformCtrlItemMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link InformCtrlItemMasterRow}オブジェクトを取得します。
   */
    public InformCtrlItemMasterRow getInformCtrlItemMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link InformCtrlItemMasterRow}オブジェクトから{@@link InformCtrlItemMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link InformCtrlItemMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link InformCtrlItemMasterDao}取得のために指定の{@@link InformCtrlItemMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link InformCtrlItemMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static InformCtrlItemMasterDao forRow( InformCtrlItemMasterRow row ) throws java.lang.IllegalArgumentException {
        return (InformCtrlItemMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformCtrlItemMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link InformCtrlItemMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link InformCtrlItemMasterPK}やデータベースレコードとして挿入される{@@link InformCtrlItemMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformCtrlItemMasterRow.TYPE );
    }


  /** 
   * {@@link InformCtrlItemMasterRow}を一意に特定する{@@link InformCtrlItemMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link InformCtrlItemMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link InformCtrlItemMasterParams}オブジェクトの主キーとして利用可能な{@@link InformCtrlItemMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static InformCtrlItemMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link InformCtrlItemMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * @@param p_itemSymbolName 検索対象であるp_itemSymbolNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformCtrlItemMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformCtrlItemMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemMasterPK pk = new InformCtrlItemMasterPK( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のInformCtrlItemMasterPKオブジェクトから{@@link InformCtrlItemMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するInformCtrlItemMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformCtrlItemMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformCtrlItemMasterRow findRowByPk( InformCtrlItemMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformCtrlItemMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(InformCtrlItemMasterRow)}を使用してください。 
   */
    public static InformCtrlItemMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemMasterPK pk = new InformCtrlItemMasterPK( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName );
        InformCtrlItemMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(InformCtrlItemMasterPK)}および{@@link #forRow(InformCtrlItemMasterRow)}を使用してください。 
   */
    public static InformCtrlItemMasterDao findDaoByPk( InformCtrlItemMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, and にて指定の値から一意の{@@link InformCtrlItemMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * @@param p_itemSymbolName 検索対象であるp_itemSymbolNameフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, and の値と一致する{@@link InformCtrlItemMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static InformCtrlItemMasterRow findRowByInstitutionCodeBranchCodeInformDivItemSymbolName( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InformCtrlItemMasterRow.TYPE,
            "institution_code=? and branch_code=? and inform_div=? and item_symbol_name=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InformCtrlItemMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InformCtrlItemMasterDao.findRowsByInstitutionCodeBranchCodeInformDivItemSymbolName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeInformDivItemSymbolName(String, String, String, String)}および{@@link #forRow(InformCtrlItemMasterRow)}を使用してください。 
   */
    public static InformCtrlItemMasterDao findDaoByInstitutionCodeBranchCodeInformDivItemSymbolName( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeInformDivItemSymbolName( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
