head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommCondDao.java;


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
 * {@@link EquityCommCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EquityCommCondRow}インスタンスへ関連付けることができます。 
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
 * @@see EquityCommCondPK 
 * @@see EquityCommCondRow 
 */
public class EquityCommCondDao extends DataAccessObject {


  /** 
   * この{@@link EquityCommCondDao}に関連する型指定のRowオブジェクト 
   */
    private EquityCommCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EquityCommCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link EquityCommCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EquityCommCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EquityCommCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EquityCommCondRow )
                return new EquityCommCondDao( (EquityCommCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EquityCommCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EquityCommCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EquityCommCondRow}オブジェクト 
    */
    protected EquityCommCondDao( EquityCommCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EquityCommCondRow}オブジェクトを取得します。
   */
    public EquityCommCondRow getEquityCommCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link EquityCommCondRow}オブジェクトから{@@link EquityCommCondDao}オブジェクトを作成します。 
   * これは実際の{@@link EquityCommCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EquityCommCondDao}取得のために指定の{@@link EquityCommCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EquityCommCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EquityCommCondDao forRow( EquityCommCondRow row ) throws java.lang.IllegalArgumentException {
        return (EquityCommCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EquityCommCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EquityCommCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EquityCommCondPK}やデータベースレコードとして挿入される{@@link EquityCommCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EquityCommCondRow.TYPE );
    }


  /** 
   * {@@link EquityCommCondRow}を一意に特定する{@@link EquityCommCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EquityCommCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EquityCommCondParams}オブジェクトの主キーとして利用可能な{@@link EquityCommCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EquityCommCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EquityCommCondRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_regNo 検索対象であるp_regNoフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * @@param p_sequenceNo 検索対象であるp_sequenceNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityCommCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityCommCondRow findRowByPk( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate, String p_sequenceNo ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondPK pk = new EquityCommCondPK( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, p_sequenceNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEquityCommCondPKオブジェクトから{@@link EquityCommCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEquityCommCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EquityCommCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EquityCommCondRow findRowByPk( EquityCommCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EquityCommCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,java.sql.Timestamp,String)}および{@@link #forRow(EquityCommCondRow)}を使用してください。 
   */
    public static EquityCommCondDao findDaoByPk( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate, String p_sequenceNo ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondPK pk = new EquityCommCondPK( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, p_sequenceNo );
        EquityCommCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EquityCommCondPK)}および{@@link #forRow(EquityCommCondRow)}を使用してください。 
   */
    public static EquityCommCondDao findDaoByPk( EquityCommCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondRow row = findRowByPk( pk );
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
   * p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, p_sequenceNo, and にて指定の値から一意の{@@link EquityCommCondRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_regNo 検索対象であるp_regNoフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * @@param p_sequenceNo 検索対象であるp_sequenceNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, p_sequenceNo, and の値と一致する{@@link EquityCommCondRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static EquityCommCondRow findRowByInstitutionCodeCommProductCodeRegNoAppliStartDateSequenceNo( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate, String p_sequenceNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EquityCommCondRow.TYPE,
            "institution_code=? and comm_product_code=? and reg_no=? and appli_start_date=? and sequence_no=?",
            null,
            new Object[] { p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, p_sequenceNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EquityCommCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EquityCommCondDao.findRowsByInstitutionCodeCommProductCodeRegNoAppliStartDateSequenceNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeCommProductCodeRegNoAppliStartDateSequenceNo(String, String, String, java.sql.Timestamp, String)}および{@@link #forRow(EquityCommCondRow)}を使用してください。 
   */
    public static EquityCommCondDao findDaoByInstitutionCodeCommProductCodeRegNoAppliStartDateSequenceNo( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate, String p_sequenceNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCommProductCodeRegNoAppliStartDateSequenceNo( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, p_sequenceNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
