head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgOutOfSrvWeekDao.java;


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
 * {@@link OtherOrgOutOfSrvWeekDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OtherOrgOutOfSrvWeekRow}インスタンスへ関連付けることができます。 
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
 * @@see OtherOrgOutOfSrvWeekPK 
 * @@see OtherOrgOutOfSrvWeekRow 
 */
public class OtherOrgOutOfSrvWeekDao extends DataAccessObject {


  /** 
   * この{@@link OtherOrgOutOfSrvWeekDao}に関連する型指定のRowオブジェクト 
   */
    private OtherOrgOutOfSrvWeekRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OtherOrgOutOfSrvWeekRow}と仮定される{@@link DataAccessObject}から新たに{@@link OtherOrgOutOfSrvWeekDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OtherOrgOutOfSrvWeekDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OtherOrgOutOfSrvWeekRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OtherOrgOutOfSrvWeekRow )
                return new OtherOrgOutOfSrvWeekDao( (OtherOrgOutOfSrvWeekRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OtherOrgOutOfSrvWeekRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OtherOrgOutOfSrvWeekRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OtherOrgOutOfSrvWeekRow}オブジェクト 
    */
    protected OtherOrgOutOfSrvWeekDao( OtherOrgOutOfSrvWeekRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OtherOrgOutOfSrvWeekRow}オブジェクトを取得します。
   */
    public OtherOrgOutOfSrvWeekRow getOtherOrgOutOfSrvWeekRow() {
        return row;
    }


  /** 
   * 指定の{@@link OtherOrgOutOfSrvWeekRow}オブジェクトから{@@link OtherOrgOutOfSrvWeekDao}オブジェクトを作成します。 
   * これは実際の{@@link OtherOrgOutOfSrvWeekRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OtherOrgOutOfSrvWeekDao}取得のために指定の{@@link OtherOrgOutOfSrvWeekRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OtherOrgOutOfSrvWeekDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OtherOrgOutOfSrvWeekDao forRow( OtherOrgOutOfSrvWeekRow row ) throws java.lang.IllegalArgumentException {
        return (OtherOrgOutOfSrvWeekDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OtherOrgOutOfSrvWeekRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OtherOrgOutOfSrvWeekRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OtherOrgOutOfSrvWeekPK}やデータベースレコードとして挿入される{@@link OtherOrgOutOfSrvWeekParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OtherOrgOutOfSrvWeekRow.TYPE );
    }


  /** 
   * {@@link OtherOrgOutOfSrvWeekRow}を一意に特定する{@@link OtherOrgOutOfSrvWeekPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OtherOrgOutOfSrvWeekRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OtherOrgOutOfSrvWeekParams}オブジェクトの主キーとして利用可能な{@@link OtherOrgOutOfSrvWeekPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OtherOrgOutOfSrvWeekPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OtherOrgOutOfSrvWeekRow}オブジェクトを検索します。 
   * 
   * @@param p_otherOrgCode 検索対象であるp_otherOrgCodeフィールドの値
   * @@param p_month 検索対象であるp_monthフィールドの値
   * @@param p_weekDiv 検索対象であるp_weekDivフィールドの値
   * @@param p_weekNo 検索対象であるp_weekNoフィールドの値
   * @@param p_suspensionStartTime 検索対象であるp_suspensionStartTimeフィールドの値
   * @@param p_suspensionEndTime 検索対象であるp_suspensionEndTimeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrgOutOfSrvWeekRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrgOutOfSrvWeekRow findRowByPk( String p_otherOrgCode, String p_month, String p_weekDiv, String p_weekNo, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvWeekPK pk = new OtherOrgOutOfSrvWeekPK( p_otherOrgCode, p_month, p_weekDiv, p_weekNo, p_suspensionStartTime, p_suspensionEndTime );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOtherOrgOutOfSrvWeekPKオブジェクトから{@@link OtherOrgOutOfSrvWeekRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOtherOrgOutOfSrvWeekPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrgOutOfSrvWeekRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrgOutOfSrvWeekRow findRowByPk( OtherOrgOutOfSrvWeekPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OtherOrgOutOfSrvWeekRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String,String)}および{@@link #forRow(OtherOrgOutOfSrvWeekRow)}を使用してください。 
   */
    public static OtherOrgOutOfSrvWeekDao findDaoByPk( String p_otherOrgCode, String p_month, String p_weekDiv, String p_weekNo, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvWeekPK pk = new OtherOrgOutOfSrvWeekPK( p_otherOrgCode, p_month, p_weekDiv, p_weekNo, p_suspensionStartTime, p_suspensionEndTime );
        OtherOrgOutOfSrvWeekRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OtherOrgOutOfSrvWeekPK)}および{@@link #forRow(OtherOrgOutOfSrvWeekRow)}を使用してください。 
   */
    public static OtherOrgOutOfSrvWeekDao findDaoByPk( OtherOrgOutOfSrvWeekPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvWeekRow row = findRowByPk( pk );
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
   * p_otherOrgCode, p_month, p_weekDiv, p_weekNo, p_suspensionStartTime, p_suspensionEndTime, and にて指定の値から一意の{@@link OtherOrgOutOfSrvWeekRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_otherOrgCode 検索対象であるp_otherOrgCodeフィールドの値
   * @@param p_month 検索対象であるp_monthフィールドの値
   * @@param p_weekDiv 検索対象であるp_weekDivフィールドの値
   * @@param p_weekNo 検索対象であるp_weekNoフィールドの値
   * @@param p_suspensionStartTime 検索対象であるp_suspensionStartTimeフィールドの値
   * @@param p_suspensionEndTime 検索対象であるp_suspensionEndTimeフィールドの値
   * 
   * @@return 引数指定のp_otherOrgCode, p_month, p_weekDiv, p_weekNo, p_suspensionStartTime, p_suspensionEndTime, and の値と一致する{@@link OtherOrgOutOfSrvWeekRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OtherOrgOutOfSrvWeekRow findRowByOtherOrgCodeMonthWeekDivWeekNoSuspensionStartTimeSuspensionEndTime( String p_otherOrgCode, String p_month, String p_weekDiv, String p_weekNo, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OtherOrgOutOfSrvWeekRow.TYPE,
            "other_org_code=? and month=? and week_div=? and week_no=? and suspension_start_time=? and suspension_end_time=?",
            null,
            new Object[] { p_otherOrgCode, p_month, p_weekDiv, p_weekNo, p_suspensionStartTime, p_suspensionEndTime } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OtherOrgOutOfSrvWeekRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OtherOrgOutOfSrvWeekDao.findRowsByOtherOrgCodeMonthWeekDivWeekNoSuspensionStartTimeSuspensionEndTime(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOtherOrgCodeMonthWeekDivWeekNoSuspensionStartTimeSuspensionEndTime(String, String, String, String, String, String)}および{@@link #forRow(OtherOrgOutOfSrvWeekRow)}を使用してください。 
   */
    public static OtherOrgOutOfSrvWeekDao findDaoByOtherOrgCodeMonthWeekDivWeekNoSuspensionStartTimeSuspensionEndTime( String p_otherOrgCode, String p_month, String p_weekDiv, String p_weekNo, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOtherOrgCodeMonthWeekDivWeekNoSuspensionStartTimeSuspensionEndTime( p_otherOrgCode, p_month, p_weekDiv, p_weekNo, p_suspensionStartTime, p_suspensionEndTime ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
