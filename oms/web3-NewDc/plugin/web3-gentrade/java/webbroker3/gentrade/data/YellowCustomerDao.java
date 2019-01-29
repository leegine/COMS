head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	YellowCustomerDao.java;


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
 * {@@link YellowCustomerDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link YellowCustomerRow}インスタンスへ関連付けることができます。 
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
 * @@see YellowCustomerPK 
 * @@see YellowCustomerRow 
 */
public class YellowCustomerDao extends DataAccessObject {


  /** 
   * この{@@link YellowCustomerDao}に関連する型指定のRowオブジェクト 
   */
    private YellowCustomerRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link YellowCustomerRow}と仮定される{@@link DataAccessObject}から新たに{@@link YellowCustomerDao}を返します。 
         * @@return 指定のRowに結びつく{@@link YellowCustomerDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link YellowCustomerRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof YellowCustomerRow )
                return new YellowCustomerDao( (YellowCustomerRow) row );
            throw new java.lang.IllegalArgumentException( "Not a YellowCustomerRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link YellowCustomerRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link YellowCustomerRow}オブジェクト 
    */
    protected YellowCustomerDao( YellowCustomerRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link YellowCustomerRow}オブジェクトを取得します。
   */
    public YellowCustomerRow getYellowCustomerRow() {
        return row;
    }


  /** 
   * 指定の{@@link YellowCustomerRow}オブジェクトから{@@link YellowCustomerDao}オブジェクトを作成します。 
   * これは実際の{@@link YellowCustomerRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link YellowCustomerDao}取得のために指定の{@@link YellowCustomerRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link YellowCustomerDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static YellowCustomerDao forRow( YellowCustomerRow row ) throws java.lang.IllegalArgumentException {
        return (YellowCustomerDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link YellowCustomerRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link YellowCustomerRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link YellowCustomerPK}やデータベースレコードとして挿入される{@@link YellowCustomerParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( YellowCustomerRow.TYPE );
    }


  /** 
   * {@@link YellowCustomerRow}を一意に特定する{@@link YellowCustomerPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link YellowCustomerRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link YellowCustomerParams}オブジェクトの主キーとして利用可能な{@@link YellowCustomerPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static YellowCustomerPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link YellowCustomerRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_eraBorn 検索対象であるp_eraBornフィールドの値
   * @@param p_bornDate 検索対象であるp_bornDateフィールドの値
   * @@param p_nameKana 検索対象であるp_nameKanaフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link YellowCustomerRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static YellowCustomerRow findRowByPk( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataFindException, DataQueryException, DataNetworkException {
        YellowCustomerPK pk = new YellowCustomerPK( p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name );
        return findRowByPk( pk );
    }


  /** 
   * 指定のYellowCustomerPKオブジェクトから{@@link YellowCustomerRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するYellowCustomerPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link YellowCustomerRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static YellowCustomerRow findRowByPk( YellowCustomerPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (YellowCustomerRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(YellowCustomerRow)}を使用してください。 
   */
    public static YellowCustomerDao findDaoByPk( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataFindException, DataQueryException, DataNetworkException {
        YellowCustomerPK pk = new YellowCustomerPK( p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name );
        YellowCustomerRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(YellowCustomerPK)}および{@@link #forRow(YellowCustomerRow)}を使用してください。 
   */
    public static YellowCustomerDao findDaoByPk( YellowCustomerPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        YellowCustomerRow row = findRowByPk( pk );
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
   * p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name, and にて指定の値から一意の{@@link YellowCustomerRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_eraBorn 検索対象であるp_eraBornフィールドの値
   * @@param p_bornDate 検索対象であるp_bornDateフィールドの値
   * @@param p_nameKana 検索対象であるp_nameKanaフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name, and の値と一致する{@@link YellowCustomerRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static YellowCustomerRow findRowByInstitutionCodeEraBornBornDateNameKanaName( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            YellowCustomerRow.TYPE,
            "institution_code=? and era_born=? and born_date=? and name_kana=? and name=?",
            null,
            new Object[] { p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (YellowCustomerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query YellowCustomerDao.findRowsByInstitutionCodeEraBornBornDateNameKanaName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeEraBornBornDateNameKanaName(String, String, String, String, String)}および{@@link #forRow(YellowCustomerRow)}を使用してください。 
   */
    public static YellowCustomerDao findDaoByInstitutionCodeEraBornBornDateNameKanaName( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeEraBornBornDateNameKanaName( p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
