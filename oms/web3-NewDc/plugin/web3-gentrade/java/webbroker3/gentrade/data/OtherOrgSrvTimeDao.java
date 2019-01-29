head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgSrvTimeDao.java;


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
 * {@@link OtherOrgSrvTimeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OtherOrgSrvTimeRow}インスタンスへ関連付けることができます。 
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
 * @@see OtherOrgSrvTimePK 
 * @@see OtherOrgSrvTimeRow 
 */
public class OtherOrgSrvTimeDao extends DataAccessObject {


  /** 
   * この{@@link OtherOrgSrvTimeDao}に関連する型指定のRowオブジェクト 
   */
    private OtherOrgSrvTimeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OtherOrgSrvTimeRow}と仮定される{@@link DataAccessObject}から新たに{@@link OtherOrgSrvTimeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OtherOrgSrvTimeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OtherOrgSrvTimeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OtherOrgSrvTimeRow )
                return new OtherOrgSrvTimeDao( (OtherOrgSrvTimeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OtherOrgSrvTimeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OtherOrgSrvTimeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OtherOrgSrvTimeRow}オブジェクト 
    */
    protected OtherOrgSrvTimeDao( OtherOrgSrvTimeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OtherOrgSrvTimeRow}オブジェクトを取得します。
   */
    public OtherOrgSrvTimeRow getOtherOrgSrvTimeRow() {
        return row;
    }


  /** 
   * 指定の{@@link OtherOrgSrvTimeRow}オブジェクトから{@@link OtherOrgSrvTimeDao}オブジェクトを作成します。 
   * これは実際の{@@link OtherOrgSrvTimeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OtherOrgSrvTimeDao}取得のために指定の{@@link OtherOrgSrvTimeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OtherOrgSrvTimeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OtherOrgSrvTimeDao forRow( OtherOrgSrvTimeRow row ) throws java.lang.IllegalArgumentException {
        return (OtherOrgSrvTimeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OtherOrgSrvTimeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OtherOrgSrvTimeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OtherOrgSrvTimePK}やデータベースレコードとして挿入される{@@link OtherOrgSrvTimeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OtherOrgSrvTimeRow.TYPE );
    }


  /** 
   * {@@link OtherOrgSrvTimeRow}を一意に特定する{@@link OtherOrgSrvTimePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OtherOrgSrvTimeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OtherOrgSrvTimeParams}オブジェクトの主キーとして利用可能な{@@link OtherOrgSrvTimePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OtherOrgSrvTimePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OtherOrgSrvTimeRow}オブジェクトを検索します。 
   * 
   * @@param p_otherOrgCode 検索対象であるp_otherOrgCodeフィールドの値
   * @@param p_weekDiv 検索対象であるp_weekDivフィールドの値
   * @@param p_serviceStartTime 検索対象であるp_serviceStartTimeフィールドの値
   * @@param p_serviceEndTime 検索対象であるp_serviceEndTimeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrgSrvTimeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrgSrvTimeRow findRowByPk( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgSrvTimePK pk = new OtherOrgSrvTimePK( p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOtherOrgSrvTimePKオブジェクトから{@@link OtherOrgSrvTimeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOtherOrgSrvTimePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrgSrvTimeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrgSrvTimeRow findRowByPk( OtherOrgSrvTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OtherOrgSrvTimeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(OtherOrgSrvTimeRow)}を使用してください。 
   */
    public static OtherOrgSrvTimeDao findDaoByPk( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgSrvTimePK pk = new OtherOrgSrvTimePK( p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime );
        OtherOrgSrvTimeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OtherOrgSrvTimePK)}および{@@link #forRow(OtherOrgSrvTimeRow)}を使用してください。 
   */
    public static OtherOrgSrvTimeDao findDaoByPk( OtherOrgSrvTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgSrvTimeRow row = findRowByPk( pk );
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
   * p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime, and にて指定の値から一意の{@@link OtherOrgSrvTimeRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_otherOrgCode 検索対象であるp_otherOrgCodeフィールドの値
   * @@param p_weekDiv 検索対象であるp_weekDivフィールドの値
   * @@param p_serviceStartTime 検索対象であるp_serviceStartTimeフィールドの値
   * @@param p_serviceEndTime 検索対象であるp_serviceEndTimeフィールドの値
   * 
   * @@return 引数指定のp_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime, and の値と一致する{@@link OtherOrgSrvTimeRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OtherOrgSrvTimeRow findRowByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OtherOrgSrvTimeRow.TYPE,
            "other_org_code=? and week_div=? and service_start_time=? and service_end_time=?",
            null,
            new Object[] { p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OtherOrgSrvTimeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OtherOrgSrvTimeDao.findRowsByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime(String, String, String, String)}および{@@link #forRow(OtherOrgSrvTimeRow)}を使用してください。 
   */
    public static OtherOrgSrvTimeDao findDaoByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime( p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
