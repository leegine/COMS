head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FaqDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.faq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.faq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FaqDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FaqRow}インスタンスへ関連付けることができます。 
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
 * @@see FaqPK 
 * @@see FaqRow 
 */
public class FaqDao extends DataAccessObject {


  /** 
   * この{@@link FaqDao}に関連する型指定のRowオブジェクト 
   */
    private FaqRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FaqRow}と仮定される{@@link DataAccessObject}から新たに{@@link FaqDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FaqDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FaqRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FaqRow )
                return new FaqDao( (FaqRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FaqRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FaqRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FaqRow}オブジェクト 
    */
    protected FaqDao( FaqRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FaqRow}オブジェクトを取得します。
   */
    public FaqRow getFaqRow() {
        return row;
    }


  /** 
   * 指定の{@@link FaqRow}オブジェクトから{@@link FaqDao}オブジェクトを作成します。 
   * これは実際の{@@link FaqRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FaqDao}取得のために指定の{@@link FaqRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FaqDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FaqDao forRow( FaqRow row ) throws java.lang.IllegalArgumentException {
        return (FaqDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FaqRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FaqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FaqPK}やデータベースレコードとして挿入される{@@link FaqParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FaqRow.TYPE );
    }


  /** 
   * {@@link FaqRow}を一意に特定する{@@link FaqPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FaqRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FaqParams}オブジェクトの主キーとして利用可能な{@@link FaqPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FaqPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FaqRow}オブジェクトを検索します。 
   * 
   * @@param p_faqNumber 検索対象であるp_faqNumberフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FaqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FaqRow findRowByPk( String p_faqNumber, String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FaqPK pk = new FaqPK( p_faqNumber, p_institutionCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFaqPKオブジェクトから{@@link FaqRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFaqPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FaqRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FaqRow findRowByPk( FaqPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FaqRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(FaqRow)}を使用してください。 
   */
    public static FaqDao findDaoByPk( String p_faqNumber, String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FaqPK pk = new FaqPK( p_faqNumber, p_institutionCode );
        FaqRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FaqPK)}および{@@link #forRow(FaqRow)}を使用してください。 
   */
    public static FaqDao findDaoByPk( FaqPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FaqRow row = findRowByPk( pk );
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
   * p_faqNumber, p_institutionCode, and にて指定の値から一意の{@@link FaqRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_faqNumber 検索対象であるp_faqNumberフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_faqNumber, p_institutionCode, and の値と一致する{@@link FaqRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FaqRow findRowByFaqNumberInstitutionCode( String p_faqNumber, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FaqRow.TYPE,
            "faq_number=? and institution_code=?",
            null,
            new Object[] { p_faqNumber, p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FaqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FaqDao.findRowsByFaqNumberInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFaqNumberInstitutionCode(String, String)}および{@@link #forRow(FaqRow)}を使用してください。 
   */
    public static FaqDao findDaoByFaqNumberInstitutionCode( String p_faqNumber, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFaqNumberInstitutionCode( p_faqNumber, p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
