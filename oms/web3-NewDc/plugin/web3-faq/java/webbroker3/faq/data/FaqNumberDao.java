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
filename	FaqNumberDao.java;


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
 * {@@link FaqNumberDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FaqNumberRow}インスタンスへ関連付けることができます。 
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
 * @@see FaqNumberPK 
 * @@see FaqNumberRow 
 */
public class FaqNumberDao extends DataAccessObject {


  /** 
   * この{@@link FaqNumberDao}に関連する型指定のRowオブジェクト 
   */
    private FaqNumberRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FaqNumberRow}と仮定される{@@link DataAccessObject}から新たに{@@link FaqNumberDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FaqNumberDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FaqNumberRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FaqNumberRow )
                return new FaqNumberDao( (FaqNumberRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FaqNumberRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FaqNumberRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FaqNumberRow}オブジェクト 
    */
    protected FaqNumberDao( FaqNumberRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FaqNumberRow}オブジェクトを取得します。
   */
    public FaqNumberRow getFaqNumberRow() {
        return row;
    }


  /** 
   * 指定の{@@link FaqNumberRow}オブジェクトから{@@link FaqNumberDao}オブジェクトを作成します。 
   * これは実際の{@@link FaqNumberRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FaqNumberDao}取得のために指定の{@@link FaqNumberRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FaqNumberDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FaqNumberDao forRow( FaqNumberRow row ) throws java.lang.IllegalArgumentException {
        return (FaqNumberDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FaqNumberRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FaqNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FaqNumberPK}やデータベースレコードとして挿入される{@@link FaqNumberParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FaqNumberRow.TYPE );
    }


  /** 
   * {@@link FaqNumberRow}を一意に特定する{@@link FaqNumberPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FaqNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FaqNumberParams}オブジェクトの主キーとして利用可能な{@@link FaqNumberPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FaqNumberPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FaqNumberRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FaqNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FaqNumberRow findRowByPk( String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FaqNumberPK pk = new FaqNumberPK( p_institutionCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFaqNumberPKオブジェクトから{@@link FaqNumberRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFaqNumberPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FaqNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FaqNumberRow findRowByPk( FaqNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FaqNumberRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(FaqNumberRow)}を使用してください。 
   */
    public static FaqNumberDao findDaoByPk( String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FaqNumberPK pk = new FaqNumberPK( p_institutionCode );
        FaqNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FaqNumberPK)}および{@@link #forRow(FaqNumberRow)}を使用してください。 
   */
    public static FaqNumberDao findDaoByPk( FaqNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FaqNumberRow row = findRowByPk( pk );
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
   * p_institutionCode, and にて指定の値から一意の{@@link FaqNumberRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, and の値と一致する{@@link FaqNumberRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FaqNumberRow findRowByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FaqNumberRow.TYPE,
            "institution_code=?",
            null,
            new Object[] { p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FaqNumberRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FaqNumberDao.findRowsByInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCode(String)}および{@@link #forRow(FaqNumberRow)}を使用してください。 
   */
    public static FaqNumberDao findDaoByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCode( p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
