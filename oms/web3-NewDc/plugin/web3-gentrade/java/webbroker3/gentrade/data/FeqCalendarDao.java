head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FeqCalendarDao.java;


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
 * {@@link FeqCalendarDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqCalendarRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqCalendarPK 
 * @@see FeqCalendarRow 
 */
public class FeqCalendarDao extends DataAccessObject {


  /** 
   * この{@@link FeqCalendarDao}に関連する型指定のRowオブジェクト 
   */
    private FeqCalendarRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqCalendarRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqCalendarDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqCalendarDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqCalendarRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqCalendarRow )
                return new FeqCalendarDao( (FeqCalendarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqCalendarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqCalendarRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqCalendarRow}オブジェクト 
    */
    protected FeqCalendarDao( FeqCalendarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqCalendarRow}オブジェクトを取得します。
   */
    public FeqCalendarRow getFeqCalendarRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqCalendarRow}オブジェクトから{@@link FeqCalendarDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqCalendarRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqCalendarDao}取得のために指定の{@@link FeqCalendarRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqCalendarDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqCalendarDao forRow( FeqCalendarRow row ) throws java.lang.IllegalArgumentException {
        return (FeqCalendarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqCalendarRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqCalendarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqCalendarPK}やデータベースレコードとして挿入される{@@link FeqCalendarParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqCalendarRow.TYPE );
    }


  /** 
   * {@@link FeqCalendarRow}を一意に特定する{@@link FeqCalendarPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqCalendarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqCalendarParams}オブジェクトの主キーとして利用可能な{@@link FeqCalendarPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqCalendarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqCalendarRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqCalendarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqCalendarRow findRowByPk( String p_institutionCode, String p_marketCode, java.sql.Timestamp p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqCalendarPK pk = new FeqCalendarPK( p_institutionCode, p_marketCode, p_bizDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqCalendarPKオブジェクトから{@@link FeqCalendarRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqCalendarPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqCalendarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqCalendarRow findRowByPk( FeqCalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqCalendarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,java.sql.Timestamp)}および{@@link #forRow(FeqCalendarRow)}を使用してください。 
   */
    public static FeqCalendarDao findDaoByPk( String p_institutionCode, String p_marketCode, java.sql.Timestamp p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqCalendarPK pk = new FeqCalendarPK( p_institutionCode, p_marketCode, p_bizDate );
        FeqCalendarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqCalendarPK)}および{@@link #forRow(FeqCalendarRow)}を使用してください。 
   */
    public static FeqCalendarDao findDaoByPk( FeqCalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqCalendarRow row = findRowByPk( pk );
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
   * p_institutionCode, p_marketCode, p_bizDate, and にて指定の値から一意の{@@link FeqCalendarRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_marketCode, p_bizDate, and の値と一致する{@@link FeqCalendarRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqCalendarRow findRowByInstitutionCodeMarketCodeBizDate( String p_institutionCode, String p_marketCode, java.sql.Timestamp p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqCalendarRow.TYPE,
            "institution_code=? and market_code=? and biz_date=?",
            null,
            new Object[] { p_institutionCode, p_marketCode, p_bizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqCalendarRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqCalendarDao.findRowsByInstitutionCodeMarketCodeBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeMarketCodeBizDate(String, String, java.sql.Timestamp)}および{@@link #forRow(FeqCalendarRow)}を使用してください。 
   */
    public static FeqCalendarDao findDaoByInstitutionCodeMarketCodeBizDate( String p_institutionCode, String p_marketCode, java.sql.Timestamp p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCodeBizDate( p_institutionCode, p_marketCode, p_bizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
