head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.17.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenDlFormMasterDao.java;


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
 * {@@link AccOpenDlFormMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenDlFormMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenDlFormMasterPK 
 * @@see AccOpenDlFormMasterRow 
 */
public class AccOpenDlFormMasterDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenDlFormMasterDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenDlFormMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenDlFormMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenDlFormMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenDlFormMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenDlFormMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenDlFormMasterRow )
                return new AccOpenDlFormMasterDao( (AccOpenDlFormMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenDlFormMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenDlFormMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenDlFormMasterRow}オブジェクト 
    */
    protected AccOpenDlFormMasterDao( AccOpenDlFormMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenDlFormMasterRow}オブジェクトを取得します。
   */
    public AccOpenDlFormMasterRow getAccOpenDlFormMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenDlFormMasterRow}オブジェクトから{@@link AccOpenDlFormMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenDlFormMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenDlFormMasterDao}取得のために指定の{@@link AccOpenDlFormMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenDlFormMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenDlFormMasterDao forRow( AccOpenDlFormMasterRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenDlFormMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenDlFormMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenDlFormMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenDlFormMasterPK}やデータベースレコードとして挿入される{@@link AccOpenDlFormMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenDlFormMasterRow.TYPE );
    }


  /** 
   * {@@link AccOpenDlFormMasterRow}を一意に特定する{@@link AccOpenDlFormMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenDlFormMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenDlFormMasterParams}オブジェクトの主キーとして利用可能な{@@link AccOpenDlFormMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenDlFormMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenDlFormMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_columnNumber 検索対象であるp_columnNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenDlFormMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenDlFormMasterRow findRowByPk( String p_institutionCode, String p_accountDiv, int p_columnNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenDlFormMasterPK pk = new AccOpenDlFormMasterPK( p_institutionCode, p_accountDiv, p_columnNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenDlFormMasterPKオブジェクトから{@@link AccOpenDlFormMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenDlFormMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenDlFormMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenDlFormMasterRow findRowByPk( AccOpenDlFormMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenDlFormMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,int)}および{@@link #forRow(AccOpenDlFormMasterRow)}を使用してください。 
   */
    public static AccOpenDlFormMasterDao findDaoByPk( String p_institutionCode, String p_accountDiv, int p_columnNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenDlFormMasterPK pk = new AccOpenDlFormMasterPK( p_institutionCode, p_accountDiv, p_columnNumber );
        AccOpenDlFormMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenDlFormMasterPK)}および{@@link #forRow(AccOpenDlFormMasterRow)}を使用してください。 
   */
    public static AccOpenDlFormMasterDao findDaoByPk( AccOpenDlFormMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenDlFormMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accountDiv, p_columnNumber, and にて指定の値から一意の{@@link AccOpenDlFormMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * @@param p_columnNumber 検索対象であるp_columnNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_accountDiv, p_columnNumber, and の値と一致する{@@link AccOpenDlFormMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenDlFormMasterRow findRowByInstitutionCodeAccountDivColumnNumber( String p_institutionCode, String p_accountDiv, int p_columnNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenDlFormMasterRow.TYPE,
            "institution_code=? and account_div=? and column_number=?",
            null,
            new Object[] { p_institutionCode, p_accountDiv, new Integer(p_columnNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenDlFormMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenDlFormMasterDao.findRowsByInstitutionCodeAccountDivColumnNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAccountDivColumnNumber(String, String, int)}および{@@link #forRow(AccOpenDlFormMasterRow)}を使用してください。 
   */
    public static AccOpenDlFormMasterDao findDaoByInstitutionCodeAccountDivColumnNumber( String p_institutionCode, String p_accountDiv, int p_columnNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccountDivColumnNumber( p_institutionCode, p_accountDiv, p_columnNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
