head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	SecReceiptDeliveryActionDao.java;


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
 * {@@link SecReceiptDeliveryActionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SecReceiptDeliveryActionRow}インスタンスへ関連付けることができます。 
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
 * @@see SecReceiptDeliveryActionPK 
 * @@see SecReceiptDeliveryActionRow 
 */
public class SecReceiptDeliveryActionDao extends DataAccessObject {


  /** 
   * この{@@link SecReceiptDeliveryActionDao}に関連する型指定のRowオブジェクト 
   */
    private SecReceiptDeliveryActionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SecReceiptDeliveryActionRow}と仮定される{@@link DataAccessObject}から新たに{@@link SecReceiptDeliveryActionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SecReceiptDeliveryActionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SecReceiptDeliveryActionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SecReceiptDeliveryActionRow )
                return new SecReceiptDeliveryActionDao( (SecReceiptDeliveryActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SecReceiptDeliveryActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SecReceiptDeliveryActionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SecReceiptDeliveryActionRow}オブジェクト 
    */
    protected SecReceiptDeliveryActionDao( SecReceiptDeliveryActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SecReceiptDeliveryActionRow}オブジェクトを取得します。
   */
    public SecReceiptDeliveryActionRow getSecReceiptDeliveryActionRow() {
        return row;
    }


  /** 
   * 指定の{@@link SecReceiptDeliveryActionRow}オブジェクトから{@@link SecReceiptDeliveryActionDao}オブジェクトを作成します。 
   * これは実際の{@@link SecReceiptDeliveryActionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SecReceiptDeliveryActionDao}取得のために指定の{@@link SecReceiptDeliveryActionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SecReceiptDeliveryActionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SecReceiptDeliveryActionDao forRow( SecReceiptDeliveryActionRow row ) throws java.lang.IllegalArgumentException {
        return (SecReceiptDeliveryActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SecReceiptDeliveryActionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SecReceiptDeliveryActionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SecReceiptDeliveryActionPK}やデータベースレコードとして挿入される{@@link SecReceiptDeliveryActionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SecReceiptDeliveryActionRow.TYPE );
    }


  /** 
   * {@@link SecReceiptDeliveryActionRow}を一意に特定する{@@link SecReceiptDeliveryActionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SecReceiptDeliveryActionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SecReceiptDeliveryActionParams}オブジェクトの主キーとして利用可能な{@@link SecReceiptDeliveryActionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SecReceiptDeliveryActionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SecReceiptDeliveryActionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SecReceiptDeliveryActionRow}オブジェクトを検索します。 
   * 
   * @@param p_secReceiptDeliveryActionId 検索対象であるp_secReceiptDeliveryActionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SecReceiptDeliveryActionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SecReceiptDeliveryActionRow findRowByPk( long p_secReceiptDeliveryActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        SecReceiptDeliveryActionPK pk = new SecReceiptDeliveryActionPK( p_secReceiptDeliveryActionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSecReceiptDeliveryActionPKオブジェクトから{@@link SecReceiptDeliveryActionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSecReceiptDeliveryActionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SecReceiptDeliveryActionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SecReceiptDeliveryActionRow findRowByPk( SecReceiptDeliveryActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SecReceiptDeliveryActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(SecReceiptDeliveryActionRow)}を使用してください。 
   */
    public static SecReceiptDeliveryActionDao findDaoByPk( long p_secReceiptDeliveryActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        SecReceiptDeliveryActionPK pk = new SecReceiptDeliveryActionPK( p_secReceiptDeliveryActionId );
        SecReceiptDeliveryActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SecReceiptDeliveryActionPK)}および{@@link #forRow(SecReceiptDeliveryActionRow)}を使用してください。 
   */
    public static SecReceiptDeliveryActionDao findDaoByPk( SecReceiptDeliveryActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SecReceiptDeliveryActionRow row = findRowByPk( pk );
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
   * p_secReceiptDeliveryActionId, and にて指定の値から一意の{@@link SecReceiptDeliveryActionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_secReceiptDeliveryActionId 検索対象であるp_secReceiptDeliveryActionIdフィールドの値
   * 
   * @@return 引数指定のp_secReceiptDeliveryActionId, and の値と一致する{@@link SecReceiptDeliveryActionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SecReceiptDeliveryActionRow findRowBySecReceiptDeliveryActionId( long p_secReceiptDeliveryActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SecReceiptDeliveryActionRow.TYPE,
            "sec_receipt_delivery_action_id=?",
            null,
            new Object[] { new Long(p_secReceiptDeliveryActionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SecReceiptDeliveryActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SecReceiptDeliveryActionDao.findRowsBySecReceiptDeliveryActionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowBySecReceiptDeliveryActionId(long)}および{@@link #forRow(SecReceiptDeliveryActionRow)}を使用してください。 
   */
    public static SecReceiptDeliveryActionDao findDaoBySecReceiptDeliveryActionId( long p_secReceiptDeliveryActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySecReceiptDeliveryActionId( p_secReceiptDeliveryActionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productGroup, p_productCode, p_ioGroup, and にて指定の値に一致する{@@link SecReceiptDeliveryActionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_productGroup 検索対象であるp_productGroupフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_ioGroup 検索対象であるp_ioGroupフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productGroup, p_productCode, p_ioGroup, and の値と一致する{@@link SecReceiptDeliveryActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductGroupProductCodeIoGroup( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productGroup, String p_productCode, String p_ioGroup ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SecReceiptDeliveryActionRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=? and product_group=? and product_code=? and io_group=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productGroup, p_productCode, p_ioGroup } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductGroupProductCodeIoGroup(String, String, String, java.sql.Timestamp, String, String, String)}および{@@link #forRow(SecReceiptDeliveryActionRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductGroupProductCodeIoGroup( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productGroup, String p_productCode, String p_ioGroup ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductGroupProductCodeIoGroup( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productGroup, p_productCode, p_ioGroup ) );
    }

}
@
