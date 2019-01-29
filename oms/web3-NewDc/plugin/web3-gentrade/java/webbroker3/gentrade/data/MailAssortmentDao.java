head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MailAssortmentDao.java;


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
 * {@@link MailAssortmentDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MailAssortmentRow}インスタンスへ関連付けることができます。 
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
 * @@see MailAssortmentPK 
 * @@see MailAssortmentRow 
 */
public class MailAssortmentDao extends DataAccessObject {


  /** 
   * この{@@link MailAssortmentDao}に関連する型指定のRowオブジェクト 
   */
    private MailAssortmentRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MailAssortmentRow}と仮定される{@@link DataAccessObject}から新たに{@@link MailAssortmentDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MailAssortmentDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MailAssortmentRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MailAssortmentRow )
                return new MailAssortmentDao( (MailAssortmentRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MailAssortmentRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MailAssortmentRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MailAssortmentRow}オブジェクト 
    */
    protected MailAssortmentDao( MailAssortmentRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MailAssortmentRow}オブジェクトを取得します。
   */
    public MailAssortmentRow getMailAssortmentRow() {
        return row;
    }


  /** 
   * 指定の{@@link MailAssortmentRow}オブジェクトから{@@link MailAssortmentDao}オブジェクトを作成します。 
   * これは実際の{@@link MailAssortmentRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MailAssortmentDao}取得のために指定の{@@link MailAssortmentRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MailAssortmentDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MailAssortmentDao forRow( MailAssortmentRow row ) throws java.lang.IllegalArgumentException {
        return (MailAssortmentDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MailAssortmentRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MailAssortmentRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MailAssortmentPK}やデータベースレコードとして挿入される{@@link MailAssortmentParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MailAssortmentRow.TYPE );
    }


  /** 
   * {@@link MailAssortmentRow}を一意に特定する{@@link MailAssortmentPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MailAssortmentRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MailAssortmentParams}オブジェクトの主キーとして利用可能な{@@link MailAssortmentPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MailAssortmentPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MailAssortmentRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_emailAddressNumber 検索対象であるp_emailAddressNumberフィールドの値
   * @@param p_mailAssortmentDiv 検索対象であるp_mailAssortmentDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailAssortmentRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailAssortmentRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber, String p_mailAssortmentDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        MailAssortmentPK pk = new MailAssortmentPK( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, p_mailAssortmentDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMailAssortmentPKオブジェクトから{@@link MailAssortmentRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMailAssortmentPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailAssortmentRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailAssortmentRow findRowByPk( MailAssortmentPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MailAssortmentRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,long,String)}および{@@link #forRow(MailAssortmentRow)}を使用してください。 
   */
    public static MailAssortmentDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber, String p_mailAssortmentDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        MailAssortmentPK pk = new MailAssortmentPK( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, p_mailAssortmentDiv );
        MailAssortmentRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MailAssortmentPK)}および{@@link #forRow(MailAssortmentRow)}を使用してください。 
   */
    public static MailAssortmentDao findDaoByPk( MailAssortmentPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MailAssortmentRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, p_mailAssortmentDiv, and にて指定の値から一意の{@@link MailAssortmentRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_emailAddressNumber 検索対象であるp_emailAddressNumberフィールドの値
   * @@param p_mailAssortmentDiv 検索対象であるp_mailAssortmentDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, p_mailAssortmentDiv, and の値と一致する{@@link MailAssortmentRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MailAssortmentRow findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumberMailAssortmentDiv( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber, String p_mailAssortmentDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MailAssortmentRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and email_address_number=? and mail_assortment_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, new Long(p_emailAddressNumber), p_mailAssortmentDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MailAssortmentRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MailAssortmentDao.findRowsByInstitutionCodeBranchCodeAccountCodeEmailAddressNumberMailAssortmentDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumberMailAssortmentDiv(String, String, String, long, String)}および{@@link #forRow(MailAssortmentRow)}を使用してください。 
   */
    public static MailAssortmentDao findDaoByInstitutionCodeBranchCodeAccountCodeEmailAddressNumberMailAssortmentDiv( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber, String p_mailAssortmentDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumberMailAssortmentDiv( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, p_mailAssortmentDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
