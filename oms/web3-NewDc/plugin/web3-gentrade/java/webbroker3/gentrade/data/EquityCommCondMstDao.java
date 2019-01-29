head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommCondMstDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link EquityCommCondMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EquityCommCondMstRow}インスタンスへ関連付けることができます。 
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
 * @@see EquityCommCondMstPK 
 * @@see EquityCommCondMstRow 
 */
public class EquityCommCondMstDao extends DataAccessObject {


  /** 
   * この{@@link EquityCommCondMstDao}に関連する型指定のRowオブジェクト 
   */
    private EquityCommCondMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EquityCommCondMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link EquityCommCondMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EquityCommCondMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EquityCommCondMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EquityCommCondMstRow )
                return new EquityCommCondMstDao( (EquityCommCondMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EquityCommCondMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EquityCommCondMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EquityCommCondMstRow}オブジェクト 
    */
    protected EquityCommCondMstDao( EquityCommCondMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EquityCommCondMstRow}オブジェクトを取得します。
   */
    public EquityCommCondMstRow getEquityCommCondMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link EquityCommCondMstRow}オブジェクトから{@@link EquityCommCondMstDao}オブジェクトを作成します。 
   * これは実際の{@@link EquityCommCondMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EquityCommCondMstDao}取得のために指定の{@@link EquityCommCondMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EquityCommCondMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EquityCommCondMstDao forRow( EquityCommCondMstRow row ) throws java.lang.IllegalArgumentException {
        return (EquityCommCondMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EquityCommCondMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EquityCommCondMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EquityCommCondMstPK}やデータベースレコードとして挿入される{@@link EquityCommCondMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EquityCommCondMstRow.TYPE );
    }


  /** 
   * {@@link EquityCommCondMstRow}を一意に特定する{@@link EquityCommCondMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EquityCommCondMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EquityCommCondMstParams}オブジェクトの主キーとして利用可能な{@@link EquityCommCondMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EquityCommCondMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EquityCommCondMstRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_regNo 検索対象であるp_regNoフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityCommCondMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityCommCondMstRow findRowByPk( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondMstPK pk = new EquityCommCondMstPK( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEquityCommCondMstPKオブジェクトから{@@link EquityCommCondMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEquityCommCondMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityCommCondMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityCommCondMstRow findRowByPk( EquityCommCondMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EquityCommCondMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,java.sql.Timestamp)}および{@@link #forRow(EquityCommCondMstRow)}を使用してください。 
   */
    public static EquityCommCondMstDao findDaoByPk( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondMstPK pk = new EquityCommCondMstPK( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate );
        EquityCommCondMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EquityCommCondMstPK)}および{@@link #forRow(EquityCommCondMstRow)}を使用してください。 
   */
    public static EquityCommCondMstDao findDaoByPk( EquityCommCondMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondMstRow row = findRowByPk( pk );
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
   * p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, and にて指定の値から一意の{@@link EquityCommCondMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_regNo 検索対象であるp_regNoフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, and の値と一致する{@@link EquityCommCondMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EquityCommCondMstRow findRowByInstitutionCodeCommProductCodeRegNoAppliStartDate( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EquityCommCondMstRow.TYPE,
            "institution_code=? and comm_product_code=? and reg_no=? and appli_start_date=?",
            null,
            new Object[] { p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EquityCommCondMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EquityCommCondMstDao.findRowsByInstitutionCodeCommProductCodeRegNoAppliStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeCommProductCodeRegNoAppliStartDate(String, String, String, java.sql.Timestamp)}および{@@link #forRow(EquityCommCondMstRow)}を使用してください。 
   */
    public static EquityCommCondMstDao findDaoByInstitutionCodeCommProductCodeRegNoAppliStartDate( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCommProductCodeRegNoAppliStartDate( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
