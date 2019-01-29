head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.12.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ExpAccountOpenTempDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link ExpAccountOpenTempDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ExpAccountOpenTempRow}インスタンスへ関連付けることができます。 
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
 * @@see ExpAccountOpenTempPK 
 * @@see ExpAccountOpenTempRow 
 */
public class ExpAccountOpenTempDao extends DataAccessObject {


  /** 
   * この{@@link ExpAccountOpenTempDao}に関連する型指定のRowオブジェクト 
   */
    private ExpAccountOpenTempRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ExpAccountOpenTempRow}と仮定される{@@link DataAccessObject}から新たに{@@link ExpAccountOpenTempDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ExpAccountOpenTempDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ExpAccountOpenTempRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExpAccountOpenTempRow )
                return new ExpAccountOpenTempDao( (ExpAccountOpenTempRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExpAccountOpenTempRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExpAccountOpenTempRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ExpAccountOpenTempRow}オブジェクト 
    */
    protected ExpAccountOpenTempDao( ExpAccountOpenTempRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ExpAccountOpenTempRow}オブジェクトを取得します。
   */
    public ExpAccountOpenTempRow getExpAccountOpenTempRow() {
        return row;
    }


  /** 
   * 指定の{@@link ExpAccountOpenTempRow}オブジェクトから{@@link ExpAccountOpenTempDao}オブジェクトを作成します。 
   * これは実際の{@@link ExpAccountOpenTempRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ExpAccountOpenTempDao}取得のために指定の{@@link ExpAccountOpenTempRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ExpAccountOpenTempDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ExpAccountOpenTempDao forRow( ExpAccountOpenTempRow row ) throws java.lang.IllegalArgumentException {
        return (ExpAccountOpenTempDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExpAccountOpenTempRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ExpAccountOpenTempRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ExpAccountOpenTempPK}やデータベースレコードとして挿入される{@@link ExpAccountOpenTempParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExpAccountOpenTempRow.TYPE );
    }


  /** 
   * {@@link ExpAccountOpenTempRow}を一意に特定する{@@link ExpAccountOpenTempPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ExpAccountOpenTempRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ExpAccountOpenTempParams}オブジェクトの主キーとして利用可能な{@@link ExpAccountOpenTempPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ExpAccountOpenTempPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ExpAccountOpenTempRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExpAccountOpenTempRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExpAccountOpenTempRow findRowByPk( String p_institutionCode, String p_accOpenRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenTempPK pk = new ExpAccountOpenTempPK( p_institutionCode, p_accOpenRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のExpAccountOpenTempPKオブジェクトから{@@link ExpAccountOpenTempRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するExpAccountOpenTempPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExpAccountOpenTempRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExpAccountOpenTempRow findRowByPk( ExpAccountOpenTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExpAccountOpenTempRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(ExpAccountOpenTempRow)}を使用してください。 
   */
    public static ExpAccountOpenTempDao findDaoByPk( String p_institutionCode, String p_accOpenRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenTempPK pk = new ExpAccountOpenTempPK( p_institutionCode, p_accOpenRequestNumber );
        ExpAccountOpenTempRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ExpAccountOpenTempPK)}および{@@link #forRow(ExpAccountOpenTempRow)}を使用してください。 
   */
    public static ExpAccountOpenTempDao findDaoByPk( ExpAccountOpenTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenTempRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accOpenRequestNumber, and にて指定の値から一意の{@@link ExpAccountOpenTempRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_accOpenRequestNumber, and の値と一致する{@@link ExpAccountOpenTempRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ExpAccountOpenTempRow findRowByInstitutionCodeAccOpenRequestNumber( String p_institutionCode, String p_accOpenRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExpAccountOpenTempRow.TYPE,
            "institution_code=? and acc_open_request_number=?",
            null,
            new Object[] { p_institutionCode, p_accOpenRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExpAccountOpenTempRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExpAccountOpenTempDao.findRowsByInstitutionCodeAccOpenRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAccOpenRequestNumber(String, String)}および{@@link #forRow(ExpAccountOpenTempRow)}を使用してください。 
   */
    public static ExpAccountOpenTempDao findDaoByInstitutionCodeAccOpenRequestNumber( String p_institutionCode, String p_accOpenRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccOpenRequestNumber( p_institutionCode, p_accOpenRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_status, and にて指定の値に一致する{@@link ExpAccountOpenTempRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_status, and の値と一致する{@@link ExpAccountOpenTempRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeStatus( String p_institutionCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenTempRow.TYPE,
            "institution_code=? and status=?",
            null,
            new Object[] { p_institutionCode, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeStatus(String, String)}および{@@link #forRow(ExpAccountOpenTempRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeStatus( String p_institutionCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeStatus( p_institutionCode, p_status ) );
    }

}
@
