head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerChangeDao.java;


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
 * {@@link VirtualServerChangeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link VirtualServerChangeRow}インスタンスへ関連付けることができます。 
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
 * @@see VirtualServerChangePK 
 * @@see VirtualServerChangeRow 
 */
public class VirtualServerChangeDao extends DataAccessObject {


  /** 
   * この{@@link VirtualServerChangeDao}に関連する型指定のRowオブジェクト 
   */
    private VirtualServerChangeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link VirtualServerChangeRow}と仮定される{@@link DataAccessObject}から新たに{@@link VirtualServerChangeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link VirtualServerChangeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link VirtualServerChangeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof VirtualServerChangeRow )
                return new VirtualServerChangeDao( (VirtualServerChangeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a VirtualServerChangeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link VirtualServerChangeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link VirtualServerChangeRow}オブジェクト 
    */
    protected VirtualServerChangeDao( VirtualServerChangeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link VirtualServerChangeRow}オブジェクトを取得します。
   */
    public VirtualServerChangeRow getVirtualServerChangeRow() {
        return row;
    }


  /** 
   * 指定の{@@link VirtualServerChangeRow}オブジェクトから{@@link VirtualServerChangeDao}オブジェクトを作成します。 
   * これは実際の{@@link VirtualServerChangeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link VirtualServerChangeDao}取得のために指定の{@@link VirtualServerChangeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link VirtualServerChangeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static VirtualServerChangeDao forRow( VirtualServerChangeRow row ) throws java.lang.IllegalArgumentException {
        return (VirtualServerChangeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link VirtualServerChangeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link VirtualServerChangeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link VirtualServerChangePK}やデータベースレコードとして挿入される{@@link VirtualServerChangeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( VirtualServerChangeRow.TYPE );
    }


  /** 
   * {@@link VirtualServerChangeRow}を一意に特定する{@@link VirtualServerChangePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link VirtualServerChangeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link VirtualServerChangeParams}オブジェクトの主キーとして利用可能な{@@link VirtualServerChangePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static VirtualServerChangePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link VirtualServerChangeRow}オブジェクトを検索します。 
   * 
   * @@param p_virtualServerNumberMarket 検索対象であるp_virtualServerNumberMarketフィールドの値
   * @@param p_changeReqResDiv 検索対象であるp_changeReqResDivフィールドの値
   * @@param p_noticeType 検索対象であるp_noticeTypeフィールドの値
   * @@param p_frontOrderExchangeCode 検索対象であるp_frontOrderExchangeCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link VirtualServerChangeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static VirtualServerChangeRow findRowByPk( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerChangePK pk = new VirtualServerChangePK( p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のVirtualServerChangePKオブジェクトから{@@link VirtualServerChangeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するVirtualServerChangePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link VirtualServerChangeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static VirtualServerChangeRow findRowByPk( VirtualServerChangePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (VirtualServerChangeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(VirtualServerChangeRow)}を使用してください。 
   */
    public static VirtualServerChangeDao findDaoByPk( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerChangePK pk = new VirtualServerChangePK( p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode );
        VirtualServerChangeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(VirtualServerChangePK)}および{@@link #forRow(VirtualServerChangeRow)}を使用してください。 
   */
    public static VirtualServerChangeDao findDaoByPk( VirtualServerChangePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerChangeRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode, and にて指定の値に一致する{@@link VirtualServerChangeRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_virtualServerNumberMarket 検索対象であるp_virtualServerNumberMarketフィールドの値
   * @@param p_changeReqResDiv 検索対象であるp_changeReqResDivフィールドの値
   * @@param p_noticeType 検索対象であるp_noticeTypeフィールドの値
   * @@param p_frontOrderExchangeCode 検索対象であるp_frontOrderExchangeCodeフィールドの値
   * 
   * @@return 引数指定のp_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode, and の値と一致する{@@link VirtualServerChangeRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByVirtualServerNumberMarketChangeReqResDivNoticeTypeFrontOrderExchangeCode( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            VirtualServerChangeRow.TYPE,
            "virtual_server_number_market=? and change_req_res_div=? and notice_type=? and front_order_exchange_code=?",
            null,
            new Object[] { p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByVirtualServerNumberMarketChangeReqResDivNoticeTypeFrontOrderExchangeCode(String, String, String, String)}および{@@link #forRow(VirtualServerChangeRow)}を使用してください。 
   */
    public static List findDaosByVirtualServerNumberMarketChangeReqResDivNoticeTypeFrontOrderExchangeCode( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByVirtualServerNumberMarketChangeReqResDivNoticeTypeFrontOrderExchangeCode( p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode ) );
    }

}
@
