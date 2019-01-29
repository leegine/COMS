head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.16.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherItemDao.java;


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
 * {@@link AccOpenVoucherItemDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenVoucherItemRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenVoucherItemPK 
 * @@see AccOpenVoucherItemRow 
 */
public class AccOpenVoucherItemDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenVoucherItemDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenVoucherItemRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenVoucherItemRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenVoucherItemDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenVoucherItemDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenVoucherItemRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenVoucherItemRow )
                return new AccOpenVoucherItemDao( (AccOpenVoucherItemRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenVoucherItemRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenVoucherItemRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenVoucherItemRow}オブジェクト 
    */
    protected AccOpenVoucherItemDao( AccOpenVoucherItemRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenVoucherItemRow}オブジェクトを取得します。
   */
    public AccOpenVoucherItemRow getAccOpenVoucherItemRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenVoucherItemRow}オブジェクトから{@@link AccOpenVoucherItemDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenVoucherItemRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenVoucherItemDao}取得のために指定の{@@link AccOpenVoucherItemRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenVoucherItemDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenVoucherItemDao forRow( AccOpenVoucherItemRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenVoucherItemDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenVoucherItemRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenVoucherItemRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenVoucherItemPK}やデータベースレコードとして挿入される{@@link AccOpenVoucherItemParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenVoucherItemRow.TYPE );
    }


  /** 
   * {@@link AccOpenVoucherItemRow}を一意に特定する{@@link AccOpenVoucherItemPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenVoucherItemRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenVoucherItemParams}オブジェクトの主キーとして利用可能な{@@link AccOpenVoucherItemPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenVoucherItemPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenVoucherItemRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * @@param p_outputItemSymbolName 検索対象であるp_outputItemSymbolNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenVoucherItemRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenVoucherItemRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo, String p_outputItemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherItemPK pk = new AccOpenVoucherItemPK( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenVoucherItemPKオブジェクトから{@@link AccOpenVoucherItemRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenVoucherItemPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenVoucherItemRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenVoucherItemRow findRowByPk( AccOpenVoucherItemPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenVoucherItemRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String,String)}および{@@link #forRow(AccOpenVoucherItemRow)}を使用してください。 
   */
    public static AccOpenVoucherItemDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo, String p_outputItemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherItemPK pk = new AccOpenVoucherItemPK( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName );
        AccOpenVoucherItemRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenVoucherItemPK)}および{@@link #forRow(AccOpenVoucherItemRow)}を使用してください。 
   */
    public static AccOpenVoucherItemDao findDaoByPk( AccOpenVoucherItemPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherItemRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName, and にて指定の値から一意の{@@link AccOpenVoucherItemRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * @@param p_outputItemSymbolName 検索対象であるp_outputItemSymbolNameフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName, and の値と一致する{@@link AccOpenVoucherItemRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenVoucherItemRow findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo, String p_outputItemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenVoucherItemRow.TYPE,
            "institution_code=? and branch_code=? and account_div=? and request_code=? and serial_no=? and output_item_symbol_name=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenVoucherItemRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenVoucherItemDao.findRowsByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName(String, String, String, String, String, String)}および{@@link #forRow(AccOpenVoucherItemRow)}を使用してください。 
   */
    public static AccOpenVoucherItemDao findDaoByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo, String p_outputItemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
