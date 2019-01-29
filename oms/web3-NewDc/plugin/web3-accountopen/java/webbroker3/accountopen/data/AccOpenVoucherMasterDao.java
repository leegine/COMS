head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.20.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherMasterDao.java;


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
 * {@@link AccOpenVoucherMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenVoucherMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenVoucherMasterPK 
 * @@see AccOpenVoucherMasterRow 
 */
public class AccOpenVoucherMasterDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenVoucherMasterDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenVoucherMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenVoucherMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenVoucherMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenVoucherMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenVoucherMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenVoucherMasterRow )
                return new AccOpenVoucherMasterDao( (AccOpenVoucherMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenVoucherMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenVoucherMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenVoucherMasterRow}オブジェクト 
    */
    protected AccOpenVoucherMasterDao( AccOpenVoucherMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenVoucherMasterRow}オブジェクトを取得します。
   */
    public AccOpenVoucherMasterRow getAccOpenVoucherMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenVoucherMasterRow}オブジェクトから{@@link AccOpenVoucherMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenVoucherMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenVoucherMasterDao}取得のために指定の{@@link AccOpenVoucherMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenVoucherMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenVoucherMasterDao forRow( AccOpenVoucherMasterRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenVoucherMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenVoucherMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenVoucherMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenVoucherMasterPK}やデータベースレコードとして挿入される{@@link AccOpenVoucherMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenVoucherMasterRow.TYPE );
    }


  /** 
   * {@@link AccOpenVoucherMasterRow}を一意に特定する{@@link AccOpenVoucherMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenVoucherMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenVoucherMasterParams}オブジェクトの主キーとして利用可能な{@@link AccOpenVoucherMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenVoucherMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenVoucherMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenVoucherMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenVoucherMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherMasterPK pk = new AccOpenVoucherMasterPK( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenVoucherMasterPKオブジェクトから{@@link AccOpenVoucherMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenVoucherMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenVoucherMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenVoucherMasterRow findRowByPk( AccOpenVoucherMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenVoucherMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(AccOpenVoucherMasterRow)}を使用してください。 
   */
    public static AccOpenVoucherMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherMasterPK pk = new AccOpenVoucherMasterPK( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo );
        AccOpenVoucherMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenVoucherMasterPK)}および{@@link #forRow(AccOpenVoucherMasterRow)}を使用してください。 
   */
    public static AccOpenVoucherMasterDao findDaoByPk( AccOpenVoucherMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, and にて指定の値から一意の{@@link AccOpenVoucherMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, and の値と一致する{@@link AccOpenVoucherMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenVoucherMasterRow findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenVoucherMasterRow.TYPE,
            "institution_code=? and branch_code=? and account_div=? and request_code=? and serial_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenVoucherMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenVoucherMasterDao.findRowsByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo(String, String, String, String, String)}および{@@link #forRow(AccOpenVoucherMasterRow)}を使用してください。 
   */
    public static AccOpenVoucherMasterDao findDaoByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
