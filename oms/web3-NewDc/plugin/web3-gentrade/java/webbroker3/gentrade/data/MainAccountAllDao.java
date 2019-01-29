head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MainAccountAllDao.java;


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
 * {@@link MainAccountAllDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MainAccountAllRow}インスタンスへ関連付けることができます。 
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
 * @@see MainAccountAllPK 
 * @@see MainAccountAllRow 
 */
public class MainAccountAllDao extends DataAccessObject {


  /** 
   * この{@@link MainAccountAllDao}に関連する型指定のRowオブジェクト 
   */
    private MainAccountAllRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MainAccountAllRow}と仮定される{@@link DataAccessObject}から新たに{@@link MainAccountAllDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MainAccountAllDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MainAccountAllRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MainAccountAllRow )
                return new MainAccountAllDao( (MainAccountAllRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MainAccountAllRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MainAccountAllRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MainAccountAllRow}オブジェクト 
    */
    protected MainAccountAllDao( MainAccountAllRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MainAccountAllRow}オブジェクトを取得します。
   */
    public MainAccountAllRow getMainAccountAllRow() {
        return row;
    }


  /** 
   * 指定の{@@link MainAccountAllRow}オブジェクトから{@@link MainAccountAllDao}オブジェクトを作成します。 
   * これは実際の{@@link MainAccountAllRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MainAccountAllDao}取得のために指定の{@@link MainAccountAllRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MainAccountAllDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MainAccountAllDao forRow( MainAccountAllRow row ) throws java.lang.IllegalArgumentException {
        return (MainAccountAllDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MainAccountAllRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MainAccountAllRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MainAccountAllPK}やデータベースレコードとして挿入される{@@link MainAccountAllParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MainAccountAllRow.TYPE );
    }


  /** 
   * {@@link MainAccountAllRow}を一意に特定する{@@link MainAccountAllPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MainAccountAllRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MainAccountAllParams}オブジェクトの主キーとして利用可能な{@@link MainAccountAllPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MainAccountAllPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MainAccountAllRow}オブジェクトを検索します。 
   * 
   * @@param p_compCode 検索対象であるp_compCodeフィールドの値
   * @@param p_brCode 検索対象であるp_brCodeフィールドの値
   * @@param p_custCode 検索対象であるp_custCodeフィールドの値
   * @@param p_custCodeCd 検索対象であるp_custCodeCdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MainAccountAllRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MainAccountAllRow findRowByPk( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountAllPK pk = new MainAccountAllPK( p_compCode, p_brCode, p_custCode, p_custCodeCd );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMainAccountAllPKオブジェクトから{@@link MainAccountAllRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMainAccountAllPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MainAccountAllRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MainAccountAllRow findRowByPk( MainAccountAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MainAccountAllRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(MainAccountAllRow)}を使用してください。 
   */
    public static MainAccountAllDao findDaoByPk( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountAllPK pk = new MainAccountAllPK( p_compCode, p_brCode, p_custCode, p_custCodeCd );
        MainAccountAllRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MainAccountAllPK)}および{@@link #forRow(MainAccountAllRow)}を使用してください。 
   */
    public static MainAccountAllDao findDaoByPk( MainAccountAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountAllRow row = findRowByPk( pk );
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
   * p_compCode, p_brCode, p_custCode, p_custCodeCd, and にて指定の値から一意の{@@link MainAccountAllRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_compCode 検索対象であるp_compCodeフィールドの値
   * @@param p_brCode 検索対象であるp_brCodeフィールドの値
   * @@param p_custCode 検索対象であるp_custCodeフィールドの値
   * @@param p_custCodeCd 検索対象であるp_custCodeCdフィールドの値
   * 
   * @@return 引数指定のp_compCode, p_brCode, p_custCode, p_custCodeCd, and の値と一致する{@@link MainAccountAllRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MainAccountAllRow findRowByCompCodeBrCodeCustCodeCustCodeCd( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MainAccountAllRow.TYPE,
            "comp_code=? and br_code=? and cust_code=? and cust_code_cd=?",
            null,
            new Object[] { p_compCode, p_brCode, p_custCode, p_custCodeCd } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MainAccountAllRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MainAccountAllDao.findRowsByCompCodeBrCodeCustCodeCustCodeCd(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByCompCodeBrCodeCustCodeCustCodeCd(String, String, String, String)}および{@@link #forRow(MainAccountAllRow)}を使用してください。 
   */
    public static MainAccountAllDao findDaoByCompCodeBrCodeCustCodeCustCodeCd( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCompCodeBrCodeCustCodeCustCodeCd( p_compCode, p_brCode, p_custCode, p_custCodeCd ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_eraBorn, p_bornDate, and にて指定の値に一致する{@@link MainAccountAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_eraBorn 検索対象であるp_eraBornフィールドの値
   * @@param p_bornDate 検索対象であるp_bornDateフィールドの値
   * 
   * @@return 引数指定のp_eraBorn, p_bornDate, and の値と一致する{@@link MainAccountAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByEraBornBornDate( String p_eraBorn, String p_bornDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountAllRow.TYPE,
            "era_born=? and born_date=?",
            null,
            new Object[] { p_eraBorn, p_bornDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByEraBornBornDate(String, String)}および{@@link #forRow(MainAccountAllRow)}を使用してください。 
   */
    public static List findDaosByEraBornBornDate( String p_eraBorn, String p_bornDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByEraBornBornDate( p_eraBorn, p_bornDate ) );
    }


  /** 
   * p_telephone, and にて指定の値に一致する{@@link MainAccountAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_telephone 検索対象であるp_telephoneフィールドの値
   * 
   * @@return 引数指定のp_telephone, and の値と一致する{@@link MainAccountAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTelephone( String p_telephone ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountAllRow.TYPE,
            "telephone=?",
            null,
            new Object[] { p_telephone } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTelephone(String)}および{@@link #forRow(MainAccountAllRow)}を使用してください。 
   */
    public static List findDaosByTelephone( String p_telephone ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTelephone( p_telephone ) );
    }


  /** 
   * p_contactAddressTelephone, and にて指定の値に一致する{@@link MainAccountAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_contactAddressTelephone 検索対象であるp_contactAddressTelephoneフィールドの値
   * 
   * @@return 引数指定のp_contactAddressTelephone, and の値と一致する{@@link MainAccountAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByContactAddressTelephone( String p_contactAddressTelephone ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountAllRow.TYPE,
            "contact_address_telephone=?",
            null,
            new Object[] { p_contactAddressTelephone } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByContactAddressTelephone(String)}および{@@link #forRow(MainAccountAllRow)}を使用してください。 
   */
    public static List findDaosByContactAddressTelephone( String p_contactAddressTelephone ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByContactAddressTelephone( p_contactAddressTelephone ) );
    }

}
@
