head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfFixedBuyingCondDao.java;


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
 * {@@link MfFixedBuyingCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MfFixedBuyingCondRow}インスタンスへ関連付けることができます。 
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
 * @@see MfFixedBuyingCondPK 
 * @@see MfFixedBuyingCondRow 
 */
public class MfFixedBuyingCondDao extends DataAccessObject {


  /** 
   * この{@@link MfFixedBuyingCondDao}に関連する型指定のRowオブジェクト 
   */
    private MfFixedBuyingCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MfFixedBuyingCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link MfFixedBuyingCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MfFixedBuyingCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MfFixedBuyingCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfFixedBuyingCondRow )
                return new MfFixedBuyingCondDao( (MfFixedBuyingCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfFixedBuyingCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfFixedBuyingCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MfFixedBuyingCondRow}オブジェクト 
    */
    protected MfFixedBuyingCondDao( MfFixedBuyingCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MfFixedBuyingCondRow}オブジェクトを取得します。
   */
    public MfFixedBuyingCondRow getMfFixedBuyingCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link MfFixedBuyingCondRow}オブジェクトから{@@link MfFixedBuyingCondDao}オブジェクトを作成します。 
   * これは実際の{@@link MfFixedBuyingCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MfFixedBuyingCondDao}取得のために指定の{@@link MfFixedBuyingCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MfFixedBuyingCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MfFixedBuyingCondDao forRow( MfFixedBuyingCondRow row ) throws java.lang.IllegalArgumentException {
        return (MfFixedBuyingCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfFixedBuyingCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MfFixedBuyingCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MfFixedBuyingCondPK}やデータベースレコードとして挿入される{@@link MfFixedBuyingCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfFixedBuyingCondRow.TYPE );
    }


  /** 
   * {@@link MfFixedBuyingCondRow}を一意に特定する{@@link MfFixedBuyingCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MfFixedBuyingCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MfFixedBuyingCondParams}オブジェクトの主キーとして利用可能な{@@link MfFixedBuyingCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MfFixedBuyingCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MfFixedBuyingCondRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_drawDate 検索対象であるp_drawDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfFixedBuyingCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfFixedBuyingCondRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_drawDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingCondPK pk = new MfFixedBuyingCondPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMfFixedBuyingCondPKオブジェクトから{@@link MfFixedBuyingCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMfFixedBuyingCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfFixedBuyingCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfFixedBuyingCondRow findRowByPk( MfFixedBuyingCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfFixedBuyingCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,java.sql.Timestamp)}および{@@link #forRow(MfFixedBuyingCondRow)}を使用してください。 
   */
    public static MfFixedBuyingCondDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_drawDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingCondPK pk = new MfFixedBuyingCondPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate );
        MfFixedBuyingCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MfFixedBuyingCondPK)}および{@@link #forRow(MfFixedBuyingCondRow)}を使用してください。 
   */
    public static MfFixedBuyingCondDao findDaoByPk( MfFixedBuyingCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingCondRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate, and にて指定の値から一意の{@@link MfFixedBuyingCondRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_drawDate 検索対象であるp_drawDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate, and の値と一致する{@@link MfFixedBuyingCondRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MfFixedBuyingCondRow findRowByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_drawDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfFixedBuyingCondRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and product_code=? and draw_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfFixedBuyingCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfFixedBuyingCondDao.findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate(String, String, String, String, java.sql.Timestamp)}および{@@link #forRow(MfFixedBuyingCondRow)}を使用してください。 
   */
    public static MfFixedBuyingCondDao findDaoByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_drawDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_drawDate, and にて指定の値に一致する{@@link MfFixedBuyingCondRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_drawDate 検索対象であるp_drawDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_drawDate, and の値と一致する{@@link MfFixedBuyingCondRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDrawDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_drawDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MfFixedBuyingCondRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and draw_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_drawDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDrawDate(String, String, String, java.sql.Timestamp)}および{@@link #forRow(MfFixedBuyingCondRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDrawDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_drawDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDrawDate( p_institutionCode, p_branchCode, p_accountCode, p_drawDate ) );
    }

}
@
