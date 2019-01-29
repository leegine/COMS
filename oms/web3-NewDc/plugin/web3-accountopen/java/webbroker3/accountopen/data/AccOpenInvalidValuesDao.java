head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.16.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenInvalidValuesDao.java;


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
 * {@@link AccOpenInvalidValuesDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenInvalidValuesRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenInvalidValuesPK 
 * @@see AccOpenInvalidValuesRow 
 */
public class AccOpenInvalidValuesDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenInvalidValuesDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenInvalidValuesRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenInvalidValuesRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenInvalidValuesDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenInvalidValuesDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenInvalidValuesRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenInvalidValuesRow )
                return new AccOpenInvalidValuesDao( (AccOpenInvalidValuesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenInvalidValuesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenInvalidValuesRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenInvalidValuesRow}オブジェクト 
    */
    protected AccOpenInvalidValuesDao( AccOpenInvalidValuesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenInvalidValuesRow}オブジェクトを取得します。
   */
    public AccOpenInvalidValuesRow getAccOpenInvalidValuesRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenInvalidValuesRow}オブジェクトから{@@link AccOpenInvalidValuesDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenInvalidValuesRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenInvalidValuesDao}取得のために指定の{@@link AccOpenInvalidValuesRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenInvalidValuesDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenInvalidValuesDao forRow( AccOpenInvalidValuesRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenInvalidValuesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenInvalidValuesRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenInvalidValuesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenInvalidValuesPK}やデータベースレコードとして挿入される{@@link AccOpenInvalidValuesParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenInvalidValuesRow.TYPE );
    }


  /** 
   * {@@link AccOpenInvalidValuesRow}を一意に特定する{@@link AccOpenInvalidValuesPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenInvalidValuesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenInvalidValuesParams}オブジェクトの主キーとして利用可能な{@@link AccOpenInvalidValuesPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenInvalidValuesPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenInvalidValuesRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenInvalidValuesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenInvalidValuesRow findRowByPk( String p_institutionCode, String p_accOpenRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenInvalidValuesPK pk = new AccOpenInvalidValuesPK( p_institutionCode, p_accOpenRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenInvalidValuesPKオブジェクトから{@@link AccOpenInvalidValuesRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenInvalidValuesPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenInvalidValuesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenInvalidValuesRow findRowByPk( AccOpenInvalidValuesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenInvalidValuesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(AccOpenInvalidValuesRow)}を使用してください。 
   */
    public static AccOpenInvalidValuesDao findDaoByPk( String p_institutionCode, String p_accOpenRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenInvalidValuesPK pk = new AccOpenInvalidValuesPK( p_institutionCode, p_accOpenRequestNumber );
        AccOpenInvalidValuesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenInvalidValuesPK)}および{@@link #forRow(AccOpenInvalidValuesRow)}を使用してください。 
   */
    public static AccOpenInvalidValuesDao findDaoByPk( AccOpenInvalidValuesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenInvalidValuesRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accOpenRequestNumber, and にて指定の値から一意の{@@link AccOpenInvalidValuesRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_accOpenRequestNumber, and の値と一致する{@@link AccOpenInvalidValuesRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenInvalidValuesRow findRowByInstitutionCodeAccOpenRequestNumber( String p_institutionCode, String p_accOpenRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenInvalidValuesRow.TYPE,
            "institution_code=? and acc_open_request_number=?",
            null,
            new Object[] { p_institutionCode, p_accOpenRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenInvalidValuesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenInvalidValuesDao.findRowsByInstitutionCodeAccOpenRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAccOpenRequestNumber(String, String)}および{@@link #forRow(AccOpenInvalidValuesRow)}を使用してください。 
   */
    public static AccOpenInvalidValuesDao findDaoByInstitutionCodeAccOpenRequestNumber( String p_institutionCode, String p_accOpenRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccOpenRequestNumber( p_institutionCode, p_accOpenRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
