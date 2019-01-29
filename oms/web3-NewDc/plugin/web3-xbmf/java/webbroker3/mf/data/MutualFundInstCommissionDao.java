head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCommissionDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MutualFundInstCommissionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MutualFundInstCommissionRow}インスタンスへ関連付けることができます。 
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
 * @@see MutualFundInstCommissionPK 
 * @@see MutualFundInstCommissionRow 
 */
public class MutualFundInstCommissionDao extends DataAccessObject {


  /** 
   * この{@@link MutualFundInstCommissionDao}に関連する型指定のRowオブジェクト 
   */
    private MutualFundInstCommissionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MutualFundInstCommissionRow}と仮定される{@@link DataAccessObject}から新たに{@@link MutualFundInstCommissionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MutualFundInstCommissionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MutualFundInstCommissionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundInstCommissionRow )
                return new MutualFundInstCommissionDao( (MutualFundInstCommissionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundInstCommissionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundInstCommissionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MutualFundInstCommissionRow}オブジェクト 
    */
    protected MutualFundInstCommissionDao( MutualFundInstCommissionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MutualFundInstCommissionRow}オブジェクトを取得します。
   */
    public MutualFundInstCommissionRow getMutualFundInstCommissionRow() {
        return row;
    }


  /** 
   * 指定の{@@link MutualFundInstCommissionRow}オブジェクトから{@@link MutualFundInstCommissionDao}オブジェクトを作成します。 
   * これは実際の{@@link MutualFundInstCommissionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MutualFundInstCommissionDao}取得のために指定の{@@link MutualFundInstCommissionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MutualFundInstCommissionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MutualFundInstCommissionDao forRow( MutualFundInstCommissionRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundInstCommissionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFundInstCommissionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MutualFundInstCommissionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MutualFundInstCommissionPK}やデータベースレコードとして挿入される{@@link MutualFundInstCommissionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFundInstCommissionRow.TYPE );
    }


  /** 
   * {@@link MutualFundInstCommissionRow}を一意に特定する{@@link MutualFundInstCommissionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MutualFundInstCommissionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MutualFundInstCommissionParams}オブジェクトの主キーとして利用可能な{@@link MutualFundInstCommissionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MutualFundInstCommissionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MutualFundInstCommissionRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_dealDiv 検索対象であるp_dealDivフィールドの値
   * @@param p_orderChanel 検索対象であるp_orderChanelフィールドの値
   * @@param p_validDateFrom 検索対象であるp_validDateFromフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundInstCommissionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundInstCommissionRow findRowByPk( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCommissionPK pk = new MutualFundInstCommissionPK( p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMutualFundInstCommissionPKオブジェクトから{@@link MutualFundInstCommissionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMutualFundInstCommissionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundInstCommissionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundInstCommissionRow findRowByPk( MutualFundInstCommissionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFundInstCommissionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,java.sql.Timestamp)}および{@@link #forRow(MutualFundInstCommissionRow)}を使用してください。 
   */
    public static MutualFundInstCommissionDao findDaoByPk( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCommissionPK pk = new MutualFundInstCommissionPK( p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom );
        MutualFundInstCommissionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MutualFundInstCommissionPK)}および{@@link #forRow(MutualFundInstCommissionRow)}を使用してください。 
   */
    public static MutualFundInstCommissionDao findDaoByPk( MutualFundInstCommissionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCommissionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom, and にて指定の値から一意の{@@link MutualFundInstCommissionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_dealDiv 検索対象であるp_dealDivフィールドの値
   * @@param p_orderChanel 検索対象であるp_orderChanelフィールドの値
   * @@param p_validDateFrom 検索対象であるp_validDateFromフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom, and の値と一致する{@@link MutualFundInstCommissionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MutualFundInstCommissionRow findRowByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MutualFundInstCommissionRow.TYPE,
            "institution_code=? and product_code=? and deal_div=? and order_chanel=? and valid_date_from=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MutualFundInstCommissionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MutualFundInstCommissionDao.findRowsByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom(String, String, String, String, java.sql.Timestamp)}および{@@link #forRow(MutualFundInstCommissionRow)}を使用してください。 
   */
    public static MutualFundInstCommissionDao findDaoByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom( p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
