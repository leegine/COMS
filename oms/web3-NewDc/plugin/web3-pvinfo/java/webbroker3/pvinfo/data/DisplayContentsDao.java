head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	DisplayContentsDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.pvinfo.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link DisplayContentsDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DisplayContentsRow}インスタンスへ関連付けることができます。 
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
 * @@see DisplayContentsPK 
 * @@see DisplayContentsRow 
 */
public class DisplayContentsDao extends DataAccessObject {


  /** 
   * この{@@link DisplayContentsDao}に関連する型指定のRowオブジェクト 
   */
    private DisplayContentsRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DisplayContentsRow}と仮定される{@@link DataAccessObject}から新たに{@@link DisplayContentsDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DisplayContentsDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DisplayContentsRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DisplayContentsRow )
                return new DisplayContentsDao( (DisplayContentsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DisplayContentsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DisplayContentsRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DisplayContentsRow}オブジェクト 
    */
    protected DisplayContentsDao( DisplayContentsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DisplayContentsRow}オブジェクトを取得します。
   */
    public DisplayContentsRow getDisplayContentsRow() {
        return row;
    }


  /** 
   * 指定の{@@link DisplayContentsRow}オブジェクトから{@@link DisplayContentsDao}オブジェクトを作成します。 
   * これは実際の{@@link DisplayContentsRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DisplayContentsDao}取得のために指定の{@@link DisplayContentsRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DisplayContentsDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DisplayContentsDao forRow( DisplayContentsRow row ) throws java.lang.IllegalArgumentException {
        return (DisplayContentsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DisplayContentsRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DisplayContentsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DisplayContentsPK}やデータベースレコードとして挿入される{@@link DisplayContentsParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DisplayContentsRow.TYPE );
    }


  /** 
   * {@@link DisplayContentsRow}を一意に特定する{@@link DisplayContentsPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DisplayContentsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DisplayContentsParams}オブジェクトの主キーとして利用可能な{@@link DisplayContentsPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DisplayContentsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new DisplayContentsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DisplayContentsRow}オブジェクトを検索します。 
   * 
   * @@param p_displayContentsId 検索対象であるp_displayContentsIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DisplayContentsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DisplayContentsRow findRowByPk( long p_displayContentsId ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayContentsPK pk = new DisplayContentsPK( p_displayContentsId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDisplayContentsPKオブジェクトから{@@link DisplayContentsRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDisplayContentsPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DisplayContentsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DisplayContentsRow findRowByPk( DisplayContentsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DisplayContentsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(DisplayContentsRow)}を使用してください。 
   */
    public static DisplayContentsDao findDaoByPk( long p_displayContentsId ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayContentsPK pk = new DisplayContentsPK( p_displayContentsId );
        DisplayContentsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DisplayContentsPK)}および{@@link #forRow(DisplayContentsRow)}を使用してください。 
   */
    public static DisplayContentsDao findDaoByPk( DisplayContentsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayContentsRow row = findRowByPk( pk );
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
   * p_displayContentsId, and にて指定の値から一意の{@@link DisplayContentsRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_displayContentsId 検索対象であるp_displayContentsIdフィールドの値
   * 
   * @@return 引数指定のp_displayContentsId, and の値と一致する{@@link DisplayContentsRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DisplayContentsRow findRowByDisplayContentsId( long p_displayContentsId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DisplayContentsRow.TYPE,
            "display_contents_id=?",
            null,
            new Object[] { new Long(p_displayContentsId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DisplayContentsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DisplayContentsDao.findRowsByDisplayContentsId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByDisplayContentsId(long)}および{@@link #forRow(DisplayContentsRow)}を使用してください。 
   */
    public static DisplayContentsDao findDaoByDisplayContentsId( long p_displayContentsId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByDisplayContentsId( p_displayContentsId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_conditionNo, and にて指定の値に一致する{@@link DisplayContentsRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_conditionNo 検索対象であるp_conditionNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_conditionNo, and の値と一致する{@@link DisplayContentsRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeConditionNo( String p_institutionCode, String p_conditionNo ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DisplayContentsRow.TYPE,
            "institution_code=? and condition_no=?",
            null,
            new Object[] { p_institutionCode, p_conditionNo } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeConditionNo(String, String)}および{@@link #forRow(DisplayContentsRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeConditionNo( String p_institutionCode, String p_conditionNo ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeConditionNo( p_institutionCode, p_conditionNo ) );
    }

}
@
