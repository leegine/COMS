head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.33.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderexecutionEndDao.java;


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
 * {@@link OrderexecutionEndDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OrderexecutionEndRow}インスタンスへ関連付けることができます。 
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
 * @@see OrderexecutionEndPK 
 * @@see OrderexecutionEndRow 
 */
public class OrderexecutionEndDao extends DataAccessObject {


  /** 
   * この{@@link OrderexecutionEndDao}に関連する型指定のRowオブジェクト 
   */
    private OrderexecutionEndRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OrderexecutionEndRow}と仮定される{@@link DataAccessObject}から新たに{@@link OrderexecutionEndDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OrderexecutionEndDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OrderexecutionEndRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderexecutionEndRow )
                return new OrderexecutionEndDao( (OrderexecutionEndRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderexecutionEndRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderexecutionEndRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OrderexecutionEndRow}オブジェクト 
    */
    protected OrderexecutionEndDao( OrderexecutionEndRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OrderexecutionEndRow}オブジェクトを取得します。
   */
    public OrderexecutionEndRow getOrderexecutionEndRow() {
        return row;
    }


  /** 
   * 指定の{@@link OrderexecutionEndRow}オブジェクトから{@@link OrderexecutionEndDao}オブジェクトを作成します。 
   * これは実際の{@@link OrderexecutionEndRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OrderexecutionEndDao}取得のために指定の{@@link OrderexecutionEndRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OrderexecutionEndDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OrderexecutionEndDao forRow( OrderexecutionEndRow row ) throws java.lang.IllegalArgumentException {
        return (OrderexecutionEndDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderexecutionEndRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OrderexecutionEndRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OrderexecutionEndPK}やデータベースレコードとして挿入される{@@link OrderexecutionEndParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderexecutionEndRow.TYPE );
    }


  /** 
   * {@@link OrderexecutionEndRow}を一意に特定する{@@link OrderexecutionEndPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OrderexecutionEndRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OrderexecutionEndParams}オブジェクトの主キーとして利用可能な{@@link OrderexecutionEndPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OrderexecutionEndPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OrderexecutionEndRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * @@param p_orderexecutionEndType 検索対象であるp_orderexecutionEndTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderexecutionEndRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderexecutionEndRow findRowByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_orderexecutionEndType ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderexecutionEndPK pk = new OrderexecutionEndPK( p_institutionCode, p_productType, p_futureOptionDiv, p_orderexecutionEndType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOrderexecutionEndPKオブジェクトから{@@link OrderexecutionEndRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOrderexecutionEndPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OrderexecutionEndRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OrderexecutionEndRow findRowByPk( OrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderexecutionEndRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum,String,String)}および{@@link #forRow(OrderexecutionEndRow)}を使用してください。 
   */
    public static OrderexecutionEndDao findDaoByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_orderexecutionEndType ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderexecutionEndPK pk = new OrderexecutionEndPK( p_institutionCode, p_productType, p_futureOptionDiv, p_orderexecutionEndType );
        OrderexecutionEndRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OrderexecutionEndPK)}および{@@link #forRow(OrderexecutionEndRow)}を使用してください。 
   */
    public static OrderexecutionEndDao findDaoByPk( OrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderexecutionEndRow row = findRowByPk( pk );
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
   * p_institutionCode, p_productType, p_futureOptionDiv, p_orderexecutionEndType, and にて指定の値から一意の{@@link OrderexecutionEndRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * @@param p_orderexecutionEndType 検索対象であるp_orderexecutionEndTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productType, p_futureOptionDiv, p_orderexecutionEndType, and の値と一致する{@@link OrderexecutionEndRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OrderexecutionEndRow findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_orderexecutionEndType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderexecutionEndRow.TYPE,
            "institution_code=? and product_type=? and future_option_div=? and orderexecution_end_type=?",
            null,
            new Object[] { p_institutionCode, p_productType, p_futureOptionDiv, p_orderexecutionEndType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderexecutionEndRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderexecutionEndDao.findRowsByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String, String)}および{@@link #forRow(OrderexecutionEndRow)}を使用してください。 
   */
    public static OrderexecutionEndDao findDaoByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_orderexecutionEndType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType( p_institutionCode, p_productType, p_futureOptionDiv, p_orderexecutionEndType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
