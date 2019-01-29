head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CalendarDao.java;


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
 * {@@link CalendarDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CalendarRow}インスタンスへ関連付けることができます。 
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
 * @@see CalendarPK 
 * @@see CalendarRow 
 */
public class CalendarDao extends DataAccessObject {


  /** 
   * この{@@link CalendarDao}に関連する型指定のRowオブジェクト 
   */
    private CalendarRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CalendarRow}と仮定される{@@link DataAccessObject}から新たに{@@link CalendarDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CalendarDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CalendarRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CalendarRow )
                return new CalendarDao( (CalendarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CalendarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CalendarRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CalendarRow}オブジェクト 
    */
    protected CalendarDao( CalendarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CalendarRow}オブジェクトを取得します。
   */
    public CalendarRow getCalendarRow() {
        return row;
    }


  /** 
   * 指定の{@@link CalendarRow}オブジェクトから{@@link CalendarDao}オブジェクトを作成します。 
   * これは実際の{@@link CalendarRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CalendarDao}取得のために指定の{@@link CalendarRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CalendarDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CalendarDao forRow( CalendarRow row ) throws java.lang.IllegalArgumentException {
        return (CalendarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CalendarRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CalendarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CalendarPK}やデータベースレコードとして挿入される{@@link CalendarParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CalendarRow.TYPE );
    }


  /** 
   * {@@link CalendarRow}を一意に特定する{@@link CalendarPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CalendarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CalendarParams}オブジェクトの主キーとして利用可能な{@@link CalendarPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CalendarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CalendarRow}オブジェクトを検索します。 
   * 
   * @@param p_holiday 検索対象であるp_holidayフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CalendarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CalendarRow findRowByPk( java.sql.Timestamp p_holiday ) throws DataFindException, DataQueryException, DataNetworkException {
        CalendarPK pk = new CalendarPK( p_holiday );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCalendarPKオブジェクトから{@@link CalendarRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCalendarPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CalendarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CalendarRow findRowByPk( CalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CalendarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(java.sql.Timestamp)}および{@@link #forRow(CalendarRow)}を使用してください。 
   */
    public static CalendarDao findDaoByPk( java.sql.Timestamp p_holiday ) throws DataFindException, DataQueryException, DataNetworkException {
        CalendarPK pk = new CalendarPK( p_holiday );
        CalendarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CalendarPK)}および{@@link #forRow(CalendarRow)}を使用してください。 
   */
    public static CalendarDao findDaoByPk( CalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CalendarRow row = findRowByPk( pk );
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
   * p_holiday, and にて指定の値に一致する{@@link CalendarRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_holiday 検索対象であるp_holidayフィールドの値
   * 
   * @@return 引数指定のp_holiday, and の値と一致する{@@link CalendarRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByHoliday( java.sql.Timestamp p_holiday ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CalendarRow.TYPE,
            "holiday=?",
            null,
            new Object[] { p_holiday } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByHoliday(java.sql.Timestamp)}および{@@link #forRow(CalendarRow)}を使用してください。 
   */
    public static List findDaosByHoliday( java.sql.Timestamp p_holiday ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByHoliday( p_holiday ) );
    }

}
@
