head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.20.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	YCustomerDao.java;


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
 * {@@link YCustomerDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link YCustomerRow}インスタンスへ関連付けることができます。 
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
 * @@see YCustomerPK 
 * @@see YCustomerRow 
 */
public class YCustomerDao extends DataAccessObject {


  /** 
   * この{@@link YCustomerDao}に関連する型指定のRowオブジェクト 
   */
    private YCustomerRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link YCustomerRow}と仮定される{@@link DataAccessObject}から新たに{@@link YCustomerDao}を返します。 
         * @@return 指定のRowに結びつく{@@link YCustomerDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link YCustomerRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof YCustomerRow )
                return new YCustomerDao( (YCustomerRow) row );
            throw new java.lang.IllegalArgumentException( "Not a YCustomerRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link YCustomerRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link YCustomerRow}オブジェクト 
    */
    protected YCustomerDao( YCustomerRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link YCustomerRow}オブジェクトを取得します。
   */
    public YCustomerRow getYCustomerRow() {
        return row;
    }


  /** 
   * 指定の{@@link YCustomerRow}オブジェクトから{@@link YCustomerDao}オブジェクトを作成します。 
   * これは実際の{@@link YCustomerRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link YCustomerDao}取得のために指定の{@@link YCustomerRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link YCustomerDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static YCustomerDao forRow( YCustomerRow row ) throws java.lang.IllegalArgumentException {
        return (YCustomerDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link YCustomerRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link YCustomerRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link YCustomerPK}やデータベースレコードとして挿入される{@@link YCustomerParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( YCustomerRow.TYPE );
    }


  /** 
   * {@@link YCustomerRow}を一意に特定する{@@link YCustomerPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link YCustomerRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link YCustomerParams}オブジェクトの主キーとして利用可能な{@@link YCustomerPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static YCustomerPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new YCustomerPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link YCustomerRow}オブジェクトを検索します。 
   * 
   * @@param p_yCustomerId 検索対象であるp_yCustomerIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link YCustomerRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static YCustomerRow findRowByPk( long p_yCustomerId ) throws DataFindException, DataQueryException, DataNetworkException {
        YCustomerPK pk = new YCustomerPK( p_yCustomerId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のYCustomerPKオブジェクトから{@@link YCustomerRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するYCustomerPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link YCustomerRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static YCustomerRow findRowByPk( YCustomerPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (YCustomerRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(YCustomerRow)}を使用してください。 
   */
    public static YCustomerDao findDaoByPk( long p_yCustomerId ) throws DataFindException, DataQueryException, DataNetworkException {
        YCustomerPK pk = new YCustomerPK( p_yCustomerId );
        YCustomerRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(YCustomerPK)}および{@@link #forRow(YCustomerRow)}を使用してください。 
   */
    public static YCustomerDao findDaoByPk( YCustomerPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        YCustomerRow row = findRowByPk( pk );
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
   * p_yCustomerId, and にて指定の値から一意の{@@link YCustomerRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_yCustomerId 検索対象であるp_yCustomerIdフィールドの値
   * 
   * @@return 引数指定のp_yCustomerId, and の値と一致する{@@link YCustomerRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static YCustomerRow findRowByYCustomerId( long p_yCustomerId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            YCustomerRow.TYPE,
            "y_customer_id=?",
            null,
            new Object[] { new Long(p_yCustomerId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (YCustomerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query YCustomerDao.findRowsByYCustomerId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByYCustomerId(long)}および{@@link #forRow(YCustomerRow)}を使用してください。 
   */
    public static YCustomerDao findDaoByYCustomerId( long p_yCustomerId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByYCustomerId( p_yCustomerId ) );
    }


  /** 
   * p_controlBranchCode, p_operationDiv, p_controlNumber, p_institutionCode, and にて指定の値から一意の{@@link YCustomerRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_controlBranchCode 検索対象であるp_controlBranchCodeフィールドの値
   * @@param p_operationDiv 検索対象であるp_operationDivフィールドの値
   * @@param p_controlNumber 検索対象であるp_controlNumberフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_controlBranchCode, p_operationDiv, p_controlNumber, p_institutionCode, and の値と一致する{@@link YCustomerRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static YCustomerRow findRowByControlBranchCodeOperationDivControlNumberInstitutionCode( String p_controlBranchCode, String p_operationDiv, int p_controlNumber, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            YCustomerRow.TYPE,
            "control_branch_code=? and operation_div=? and control_number=? and institution_code=?",
            null,
            new Object[] { p_controlBranchCode, p_operationDiv, new Integer(p_controlNumber), p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (YCustomerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query YCustomerDao.findRowsByControlBranchCodeOperationDivControlNumberInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByControlBranchCodeOperationDivControlNumberInstitutionCode(String, String, int, String)}および{@@link #forRow(YCustomerRow)}を使用してください。 
   */
    public static YCustomerDao findDaoByControlBranchCodeOperationDivControlNumberInstitutionCode( String p_controlBranchCode, String p_operationDiv, int p_controlNumber, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByControlBranchCodeOperationDivControlNumberInstitutionCode( p_controlBranchCode, p_operationDiv, p_controlNumber, p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name, and にて指定の値に一致する{@@link YCustomerRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_eraBorn 検索対象であるp_eraBornフィールドの値
   * @@param p_bornDate 検索対象であるp_bornDateフィールドの値
   * @@param p_nameKana 検索対象であるp_nameKanaフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name, and の値と一致する{@@link YCustomerRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeEraBornBornDateNameKanaName( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            YCustomerRow.TYPE,
            "institution_code=? and era_born=? and born_date=? and name_kana=? and name=?",
            null,
            new Object[] { p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeEraBornBornDateNameKanaName(String, String, String, String, String)}および{@@link #forRow(YCustomerRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeEraBornBornDateNameKanaName( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeEraBornBornDateNameKanaName( p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name ) );
    }

}
@
