head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.30.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EraDao.java;


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
 * {@@link EraDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EraRow}インスタンスへ関連付けることができます。 
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
 * @@see EraPK 
 * @@see EraRow 
 */
public class EraDao extends DataAccessObject {


  /** 
   * この{@@link EraDao}に関連する型指定のRowオブジェクト 
   */
    private EraRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EraRow}と仮定される{@@link DataAccessObject}から新たに{@@link EraDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EraDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EraRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EraRow )
                return new EraDao( (EraRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EraRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EraRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EraRow}オブジェクト 
    */
    protected EraDao( EraRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EraRow}オブジェクトを取得します。
   */
    public EraRow getEraRow() {
        return row;
    }


  /** 
   * 指定の{@@link EraRow}オブジェクトから{@@link EraDao}オブジェクトを作成します。 
   * これは実際の{@@link EraRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EraDao}取得のために指定の{@@link EraRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EraDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EraDao forRow( EraRow row ) throws java.lang.IllegalArgumentException {
        return (EraDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EraRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EraRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EraPK}やデータベースレコードとして挿入される{@@link EraParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EraRow.TYPE );
    }


  /** 
   * {@@link EraRow}を一意に特定する{@@link EraPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EraRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EraParams}オブジェクトの主キーとして利用可能な{@@link EraPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EraPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EraRow}オブジェクトを検索します。 
   * 
   * @@param p_japaneseEraDiv 検索対象であるp_japaneseEraDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EraRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EraRow findRowByPk( int p_japaneseEraDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        EraPK pk = new EraPK( p_japaneseEraDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEraPKオブジェクトから{@@link EraRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEraPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EraRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EraRow findRowByPk( EraPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EraRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(int)}および{@@link #forRow(EraRow)}を使用してください。 
   */
    public static EraDao findDaoByPk( int p_japaneseEraDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        EraPK pk = new EraPK( p_japaneseEraDiv );
        EraRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EraPK)}および{@@link #forRow(EraRow)}を使用してください。 
   */
    public static EraDao findDaoByPk( EraPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EraRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_japaneseEraDiv, and にて指定の値に一致する{@@link EraRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_japaneseEraDiv 検索対象であるp_japaneseEraDivフィールドの値
   * 
   * @@return 引数指定のp_japaneseEraDiv, and の値と一致する{@@link EraRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByJapaneseEraDiv( int p_japaneseEraDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EraRow.TYPE,
            "japanese_era_div=?",
            null,
            new Object[] { new Integer(p_japaneseEraDiv) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByJapaneseEraDiv(int)}および{@@link #forRow(EraRow)}を使用してください。 
   */
    public static List findDaosByJapaneseEraDiv( int p_japaneseEraDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByJapaneseEraDiv( p_japaneseEraDiv ) );
    }

}
@
