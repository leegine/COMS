head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfFixedBuyingChangeHistDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MfFixedBuyingChangeHistDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MfFixedBuyingChangeHistRow}インスタンスへ関連付けることができます。 
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
 * @@see MfFixedBuyingChangeHistPK 
 * @@see MfFixedBuyingChangeHistRow 
 */
public class MfFixedBuyingChangeHistDao extends DataAccessObject {


  /** 
   * この{@@link MfFixedBuyingChangeHistDao}に関連する型指定のRowオブジェクト 
   */
    private MfFixedBuyingChangeHistRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MfFixedBuyingChangeHistRow}と仮定される{@@link DataAccessObject}から新たに{@@link MfFixedBuyingChangeHistDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MfFixedBuyingChangeHistDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MfFixedBuyingChangeHistRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfFixedBuyingChangeHistRow )
                return new MfFixedBuyingChangeHistDao( (MfFixedBuyingChangeHistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfFixedBuyingChangeHistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfFixedBuyingChangeHistRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MfFixedBuyingChangeHistRow}オブジェクト 
    */
    protected MfFixedBuyingChangeHistDao( MfFixedBuyingChangeHistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MfFixedBuyingChangeHistRow}オブジェクトを取得します。
   */
    public MfFixedBuyingChangeHistRow getMfFixedBuyingChangeHistRow() {
        return row;
    }


  /** 
   * 指定の{@@link MfFixedBuyingChangeHistRow}オブジェクトから{@@link MfFixedBuyingChangeHistDao}オブジェクトを作成します。 
   * これは実際の{@@link MfFixedBuyingChangeHistRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MfFixedBuyingChangeHistDao}取得のために指定の{@@link MfFixedBuyingChangeHistRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MfFixedBuyingChangeHistDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MfFixedBuyingChangeHistDao forRow( MfFixedBuyingChangeHistRow row ) throws java.lang.IllegalArgumentException {
        return (MfFixedBuyingChangeHistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfFixedBuyingChangeHistRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MfFixedBuyingChangeHistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MfFixedBuyingChangeHistPK}やデータベースレコードとして挿入される{@@link MfFixedBuyingChangeHistParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfFixedBuyingChangeHistRow.TYPE );
    }


  /** 
   * {@@link MfFixedBuyingChangeHistRow}を一意に特定する{@@link MfFixedBuyingChangeHistPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MfFixedBuyingChangeHistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MfFixedBuyingChangeHistParams}オブジェクトの主キーとして利用可能な{@@link MfFixedBuyingChangeHistPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MfFixedBuyingChangeHistPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MfFixedBuyingChangeHistRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_validStartDate 検索対象であるp_validStartDateフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfFixedBuyingChangeHistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfFixedBuyingChangeHistRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate, int p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingChangeHistPK pk = new MfFixedBuyingChangeHistPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate, p_serialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMfFixedBuyingChangeHistPKオブジェクトから{@@link MfFixedBuyingChangeHistRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMfFixedBuyingChangeHistPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfFixedBuyingChangeHistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfFixedBuyingChangeHistRow findRowByPk( MfFixedBuyingChangeHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfFixedBuyingChangeHistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,java.sql.Timestamp,int)}および{@@link #forRow(MfFixedBuyingChangeHistRow)}を使用してください。 
   */
    public static MfFixedBuyingChangeHistDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate, int p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingChangeHistPK pk = new MfFixedBuyingChangeHistPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate, p_serialNo );
        MfFixedBuyingChangeHistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MfFixedBuyingChangeHistPK)}および{@@link #forRow(MfFixedBuyingChangeHistRow)}を使用してください。 
   */
    public static MfFixedBuyingChangeHistDao findDaoByPk( MfFixedBuyingChangeHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingChangeHistRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate, p_serialNo, and にて指定の値から一意の{@@link MfFixedBuyingChangeHistRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_validStartDate 検索対象であるp_validStartDateフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate, p_serialNo, and の値と一致する{@@link MfFixedBuyingChangeHistRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MfFixedBuyingChangeHistRow findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate, int p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfFixedBuyingChangeHistRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and product_code=? and valid_start_date=? and serial_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate, new Integer(p_serialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfFixedBuyingChangeHistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfFixedBuyingChangeHistDao.findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(String, String, String, String, java.sql.Timestamp, int)}および{@@link #forRow(MfFixedBuyingChangeHistRow)}を使用してください。 
   */
    public static MfFixedBuyingChangeHistDao findDaoByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate, int p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate, p_serialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
