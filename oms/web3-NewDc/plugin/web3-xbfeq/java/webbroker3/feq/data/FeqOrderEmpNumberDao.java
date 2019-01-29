head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqOrderEmpNumberDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;

/** 
 * {@@link FeqOrderEmpNumberDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqOrderEmpNumberRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqOrderEmpNumberPK 
 * @@see FeqOrderEmpNumberRow 
 */
public class FeqOrderEmpNumberDao extends DataAccessObject {


  /** 
   * この{@@link FeqOrderEmpNumberDao}に関連する型指定のRowオブジェクト 
   */
    private FeqOrderEmpNumberRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqOrderEmpNumberRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqOrderEmpNumberDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqOrderEmpNumberDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqOrderEmpNumberRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqOrderEmpNumberRow )
                return new FeqOrderEmpNumberDao( (FeqOrderEmpNumberRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqOrderEmpNumberRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqOrderEmpNumberRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqOrderEmpNumberRow}オブジェクト 
    */
    protected FeqOrderEmpNumberDao( FeqOrderEmpNumberRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqOrderEmpNumberRow}オブジェクトを取得します。
   */
    public FeqOrderEmpNumberRow getFeqOrderEmpNumberRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqOrderEmpNumberRow}オブジェクトから{@@link FeqOrderEmpNumberDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqOrderEmpNumberRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqOrderEmpNumberDao}取得のために指定の{@@link FeqOrderEmpNumberRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqOrderEmpNumberDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqOrderEmpNumberDao forRow( FeqOrderEmpNumberRow row ) throws java.lang.IllegalArgumentException {
        return (FeqOrderEmpNumberDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqOrderEmpNumberRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqOrderEmpNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqOrderEmpNumberPK}やデータベースレコードとして挿入される{@@link FeqOrderEmpNumberParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqOrderEmpNumberRow.TYPE );
    }


  /** 
   * {@@link FeqOrderEmpNumberRow}を一意に特定する{@@link FeqOrderEmpNumberPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqOrderEmpNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqOrderEmpNumberParams}オブジェクトの主キーとして利用可能な{@@link FeqOrderEmpNumberPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqOrderEmpNumberPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqOrderEmpNumberRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_feqOrderEmpDiv 検索対象であるp_feqOrderEmpDivフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqOrderEmpNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqOrderEmpNumberRow findRowByPk( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderEmpNumberPK pk = new FeqOrderEmpNumberPK( p_institutionCode, p_feqOrderEmpDiv, p_bizDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqOrderEmpNumberPKオブジェクトから{@@link FeqOrderEmpNumberRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqOrderEmpNumberPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqOrderEmpNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqOrderEmpNumberRow findRowByPk( FeqOrderEmpNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqOrderEmpNumberRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,java.sql.Timestamp)}および{@@link #forRow(FeqOrderEmpNumberRow)}を使用してください。 
   */
    public static FeqOrderEmpNumberDao findDaoByPk( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderEmpNumberPK pk = new FeqOrderEmpNumberPK( p_institutionCode, p_feqOrderEmpDiv, p_bizDate );
        FeqOrderEmpNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqOrderEmpNumberPK)}および{@@link #forRow(FeqOrderEmpNumberRow)}を使用してください。 
   */
    public static FeqOrderEmpNumberDao findDaoByPk( FeqOrderEmpNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderEmpNumberRow row = findRowByPk( pk );
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
   * p_institutionCode, p_feqOrderEmpDiv, p_bizDate, and にて指定の値から一意の{@@link FeqOrderEmpNumberRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_feqOrderEmpDiv 検索対象であるp_feqOrderEmpDivフィールドの値
   * @@param p_bizDate 検索対象であるp_bizDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_feqOrderEmpDiv, p_bizDate, and の値と一致する{@@link FeqOrderEmpNumberRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqOrderEmpNumberRow findRowByInstitutionCodeFeqOrderEmpDivBizDate( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderEmpNumberRow.TYPE,
            "institution_code=? and feq_order_emp_div=? and biz_date=?",
            null,
            new Object[] { p_institutionCode, p_feqOrderEmpDiv, p_bizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderEmpNumberRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderEmpNumberDao.findRowsByInstitutionCodeFeqOrderEmpDivBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeFeqOrderEmpDivBizDate(String, String, java.sql.Timestamp)}および{@@link #forRow(FeqOrderEmpNumberRow)}を使用してください。 
   */
    public static FeqOrderEmpNumberDao findDaoByInstitutionCodeFeqOrderEmpDivBizDate( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeFeqOrderEmpDivBizDate( p_institutionCode, p_feqOrderEmpDiv, p_bizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
