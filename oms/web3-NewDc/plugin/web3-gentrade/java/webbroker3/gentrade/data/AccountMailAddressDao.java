head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountMailAddressDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AccountMailAddressDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccountMailAddressRow}インスタンスへ関連付けることができます。 
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
 * @@see AccountMailAddressPK 
 * @@see AccountMailAddressRow 
 */
public class AccountMailAddressDao extends DataAccessObject {


  /** 
   * この{@@link AccountMailAddressDao}に関連する型指定のRowオブジェクト 
   */
    private AccountMailAddressRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccountMailAddressRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccountMailAddressDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccountMailAddressDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccountMailAddressRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccountMailAddressRow )
                return new AccountMailAddressDao( (AccountMailAddressRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccountMailAddressRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccountMailAddressRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccountMailAddressRow}オブジェクト 
    */
    protected AccountMailAddressDao( AccountMailAddressRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccountMailAddressRow}オブジェクトを取得します。
   */
    public AccountMailAddressRow getAccountMailAddressRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccountMailAddressRow}オブジェクトから{@@link AccountMailAddressDao}オブジェクトを作成します。 
   * これは実際の{@@link AccountMailAddressRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccountMailAddressDao}取得のために指定の{@@link AccountMailAddressRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccountMailAddressDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccountMailAddressDao forRow( AccountMailAddressRow row ) throws java.lang.IllegalArgumentException {
        return (AccountMailAddressDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccountMailAddressRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccountMailAddressRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccountMailAddressPK}やデータベースレコードとして挿入される{@@link AccountMailAddressParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccountMailAddressRow.TYPE );
    }


  /** 
   * {@@link AccountMailAddressRow}を一意に特定する{@@link AccountMailAddressPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccountMailAddressRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccountMailAddressParams}オブジェクトの主キーとして利用可能な{@@link AccountMailAddressPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccountMailAddressPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccountMailAddressRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_emailAddressNumber 検索対象であるp_emailAddressNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccountMailAddressRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccountMailAddressRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountMailAddressPK pk = new AccountMailAddressPK( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccountMailAddressPKオブジェクトから{@@link AccountMailAddressRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccountMailAddressPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccountMailAddressRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccountMailAddressRow findRowByPk( AccountMailAddressPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccountMailAddressRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,long)}および{@@link #forRow(AccountMailAddressRow)}を使用してください。 
   */
    public static AccountMailAddressDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountMailAddressPK pk = new AccountMailAddressPK( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber );
        AccountMailAddressRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccountMailAddressPK)}および{@@link #forRow(AccountMailAddressRow)}を使用してください。 
   */
    public static AccountMailAddressDao findDaoByPk( AccountMailAddressPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountMailAddressRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, and にて指定の値から一意の{@@link AccountMailAddressRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_emailAddressNumber 検索対象であるp_emailAddressNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, and の値と一致する{@@link AccountMailAddressRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccountMailAddressRow findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccountMailAddressRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and email_address_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, new Long(p_emailAddressNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccountMailAddressRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccountMailAddressDao.findRowsByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber(String, String, String, long)}および{@@link #forRow(AccountMailAddressRow)}を使用してください。 
   */
    public static AccountMailAddressDao findDaoByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
