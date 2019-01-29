head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CommSerialNumbersDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommSerialNumbersDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CommSerialNumbersRow}インスタンスへ関連付けることができます。 
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
 * @@see CommSerialNumbersPK 
 * @@see CommSerialNumbersRow 
 */
public class CommSerialNumbersDao extends DataAccessObject {


  /** 
   * この{@@link CommSerialNumbersDao}に関連する型指定のRowオブジェクト 
   */
    private CommSerialNumbersRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CommSerialNumbersRow}と仮定される{@@link DataAccessObject}から新たに{@@link CommSerialNumbersDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CommSerialNumbersDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CommSerialNumbersRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommSerialNumbersRow )
                return new CommSerialNumbersDao( (CommSerialNumbersRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommSerialNumbersRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommSerialNumbersRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CommSerialNumbersRow}オブジェクト 
    */
    protected CommSerialNumbersDao( CommSerialNumbersRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CommSerialNumbersRow}オブジェクトを取得します。
   */
    public CommSerialNumbersRow getCommSerialNumbersRow() {
        return row;
    }


  /** 
   * 指定の{@@link CommSerialNumbersRow}オブジェクトから{@@link CommSerialNumbersDao}オブジェクトを作成します。 
   * これは実際の{@@link CommSerialNumbersRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CommSerialNumbersDao}取得のために指定の{@@link CommSerialNumbersRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CommSerialNumbersDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CommSerialNumbersDao forRow( CommSerialNumbersRow row ) throws java.lang.IllegalArgumentException {
        return (CommSerialNumbersDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommSerialNumbersRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CommSerialNumbersRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CommSerialNumbersPK}やデータベースレコードとして挿入される{@@link CommSerialNumbersParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommSerialNumbersRow.TYPE );
    }


  /** 
   * {@@link CommSerialNumbersRow}を一意に特定する{@@link CommSerialNumbersPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CommSerialNumbersRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CommSerialNumbersParams}オブジェクトの主キーとして利用可能な{@@link CommSerialNumbersPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CommSerialNumbersPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CommSerialNumbersRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_serialNumberName 検索対象であるp_serialNumberNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommSerialNumbersRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommSerialNumbersRow findRowByPk( String p_institutionCode, String p_serialNumberName ) throws DataFindException, DataQueryException, DataNetworkException {
        CommSerialNumbersPK pk = new CommSerialNumbersPK( p_institutionCode, p_serialNumberName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCommSerialNumbersPKオブジェクトから{@@link CommSerialNumbersRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCommSerialNumbersPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommSerialNumbersRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommSerialNumbersRow findRowByPk( CommSerialNumbersPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommSerialNumbersRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(CommSerialNumbersRow)}を使用してください。 
   */
    public static CommSerialNumbersDao findDaoByPk( String p_institutionCode, String p_serialNumberName ) throws DataFindException, DataQueryException, DataNetworkException {
        CommSerialNumbersPK pk = new CommSerialNumbersPK( p_institutionCode, p_serialNumberName );
        CommSerialNumbersRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CommSerialNumbersPK)}および{@@link #forRow(CommSerialNumbersRow)}を使用してください。 
   */
    public static CommSerialNumbersDao findDaoByPk( CommSerialNumbersPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommSerialNumbersRow row = findRowByPk( pk );
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
   * p_institutionCode, p_serialNumberName, and にて指定の値から一意の{@@link CommSerialNumbersRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_serialNumberName 検索対象であるp_serialNumberNameフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_serialNumberName, and の値と一致する{@@link CommSerialNumbersRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CommSerialNumbersRow findRowByInstitutionCodeSerialNumberName( String p_institutionCode, String p_serialNumberName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommSerialNumbersRow.TYPE,
            "institution_code=? and serial_number_name=?",
            null,
            new Object[] { p_institutionCode, p_serialNumberName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommSerialNumbersRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommSerialNumbersDao.findRowsByInstitutionCodeSerialNumberName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeSerialNumberName(String, String)}および{@@link #forRow(CommSerialNumbersRow)}を使用してください。 
   */
    public static CommSerialNumbersDao findDaoByInstitutionCodeSerialNumberName( String p_institutionCode, String p_serialNumberName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSerialNumberName( p_institutionCode, p_serialNumberName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
