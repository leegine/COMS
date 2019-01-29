head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgOutOfSrvDateDao.java;


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
 * {@@link OtherOrgOutOfSrvDateDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OtherOrgOutOfSrvDateRow}インスタンスへ関連付けることができます。 
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
 * @@see OtherOrgOutOfSrvDatePK 
 * @@see OtherOrgOutOfSrvDateRow 
 */
public class OtherOrgOutOfSrvDateDao extends DataAccessObject {


  /** 
   * この{@@link OtherOrgOutOfSrvDateDao}に関連する型指定のRowオブジェクト 
   */
    private OtherOrgOutOfSrvDateRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OtherOrgOutOfSrvDateRow}と仮定される{@@link DataAccessObject}から新たに{@@link OtherOrgOutOfSrvDateDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OtherOrgOutOfSrvDateDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OtherOrgOutOfSrvDateRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OtherOrgOutOfSrvDateRow )
                return new OtherOrgOutOfSrvDateDao( (OtherOrgOutOfSrvDateRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OtherOrgOutOfSrvDateRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OtherOrgOutOfSrvDateRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OtherOrgOutOfSrvDateRow}オブジェクト 
    */
    protected OtherOrgOutOfSrvDateDao( OtherOrgOutOfSrvDateRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OtherOrgOutOfSrvDateRow}オブジェクトを取得します。
   */
    public OtherOrgOutOfSrvDateRow getOtherOrgOutOfSrvDateRow() {
        return row;
    }


  /** 
   * 指定の{@@link OtherOrgOutOfSrvDateRow}オブジェクトから{@@link OtherOrgOutOfSrvDateDao}オブジェクトを作成します。 
   * これは実際の{@@link OtherOrgOutOfSrvDateRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OtherOrgOutOfSrvDateDao}取得のために指定の{@@link OtherOrgOutOfSrvDateRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OtherOrgOutOfSrvDateDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OtherOrgOutOfSrvDateDao forRow( OtherOrgOutOfSrvDateRow row ) throws java.lang.IllegalArgumentException {
        return (OtherOrgOutOfSrvDateDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OtherOrgOutOfSrvDateRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OtherOrgOutOfSrvDateRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OtherOrgOutOfSrvDatePK}やデータベースレコードとして挿入される{@@link OtherOrgOutOfSrvDateParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OtherOrgOutOfSrvDateRow.TYPE );
    }


  /** 
   * {@@link OtherOrgOutOfSrvDateRow}を一意に特定する{@@link OtherOrgOutOfSrvDatePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OtherOrgOutOfSrvDateRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OtherOrgOutOfSrvDateParams}オブジェクトの主キーとして利用可能な{@@link OtherOrgOutOfSrvDatePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OtherOrgOutOfSrvDatePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OtherOrgOutOfSrvDateRow}オブジェクトを検索します。 
   * 
   * @@param p_otherOrgCode 検索対象であるp_otherOrgCodeフィールドの値
   * @@param p_suspensionDate 検索対象であるp_suspensionDateフィールドの値
   * @@param p_suspensionStartTime 検索対象であるp_suspensionStartTimeフィールドの値
   * @@param p_suspensionEndTime 検索対象であるp_suspensionEndTimeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrgOutOfSrvDateRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrgOutOfSrvDateRow findRowByPk( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvDatePK pk = new OtherOrgOutOfSrvDatePK( p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOtherOrgOutOfSrvDatePKオブジェクトから{@@link OtherOrgOutOfSrvDateRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOtherOrgOutOfSrvDatePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrgOutOfSrvDateRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrgOutOfSrvDateRow findRowByPk( OtherOrgOutOfSrvDatePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OtherOrgOutOfSrvDateRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(OtherOrgOutOfSrvDateRow)}を使用してください。 
   */
    public static OtherOrgOutOfSrvDateDao findDaoByPk( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvDatePK pk = new OtherOrgOutOfSrvDatePK( p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime );
        OtherOrgOutOfSrvDateRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OtherOrgOutOfSrvDatePK)}および{@@link #forRow(OtherOrgOutOfSrvDateRow)}を使用してください。 
   */
    public static OtherOrgOutOfSrvDateDao findDaoByPk( OtherOrgOutOfSrvDatePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvDateRow row = findRowByPk( pk );
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
   * p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime, and にて指定の値から一意の{@@link OtherOrgOutOfSrvDateRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_otherOrgCode 検索対象であるp_otherOrgCodeフィールドの値
   * @@param p_suspensionDate 検索対象であるp_suspensionDateフィールドの値
   * @@param p_suspensionStartTime 検索対象であるp_suspensionStartTimeフィールドの値
   * @@param p_suspensionEndTime 検索対象であるp_suspensionEndTimeフィールドの値
   * 
   * @@return 引数指定のp_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime, and の値と一致する{@@link OtherOrgOutOfSrvDateRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OtherOrgOutOfSrvDateRow findRowByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OtherOrgOutOfSrvDateRow.TYPE,
            "other_org_code=? and suspension_date=? and suspension_start_time=? and suspension_end_time=?",
            null,
            new Object[] { p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OtherOrgOutOfSrvDateRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OtherOrgOutOfSrvDateDao.findRowsByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime(String, String, String, String)}および{@@link #forRow(OtherOrgOutOfSrvDateRow)}を使用してください。 
   */
    public static OtherOrgOutOfSrvDateDao findDaoByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime( p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
