head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	DisplayConditionDao.java;


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
 * {@@link DisplayConditionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DisplayConditionRow}インスタンスへ関連付けることができます。 
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
 * @@see DisplayConditionPK 
 * @@see DisplayConditionRow 
 */
public class DisplayConditionDao extends DataAccessObject {


  /** 
   * この{@@link DisplayConditionDao}に関連する型指定のRowオブジェクト 
   */
    private DisplayConditionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DisplayConditionRow}と仮定される{@@link DataAccessObject}から新たに{@@link DisplayConditionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DisplayConditionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DisplayConditionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DisplayConditionRow )
                return new DisplayConditionDao( (DisplayConditionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DisplayConditionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DisplayConditionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DisplayConditionRow}オブジェクト 
    */
    protected DisplayConditionDao( DisplayConditionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DisplayConditionRow}オブジェクトを取得します。
   */
    public DisplayConditionRow getDisplayConditionRow() {
        return row;
    }


  /** 
   * 指定の{@@link DisplayConditionRow}オブジェクトから{@@link DisplayConditionDao}オブジェクトを作成します。 
   * これは実際の{@@link DisplayConditionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DisplayConditionDao}取得のために指定の{@@link DisplayConditionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DisplayConditionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DisplayConditionDao forRow( DisplayConditionRow row ) throws java.lang.IllegalArgumentException {
        return (DisplayConditionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DisplayConditionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DisplayConditionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DisplayConditionPK}やデータベースレコードとして挿入される{@@link DisplayConditionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DisplayConditionRow.TYPE );
    }


  /** 
   * {@@link DisplayConditionRow}を一意に特定する{@@link DisplayConditionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DisplayConditionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DisplayConditionParams}オブジェクトの主キーとして利用可能な{@@link DisplayConditionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DisplayConditionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new DisplayConditionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DisplayConditionRow}オブジェクトを検索します。 
   * 
   * @@param p_displayConditionId 検索対象であるp_displayConditionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DisplayConditionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DisplayConditionRow findRowByPk( long p_displayConditionId ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayConditionPK pk = new DisplayConditionPK( p_displayConditionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDisplayConditionPKオブジェクトから{@@link DisplayConditionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDisplayConditionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DisplayConditionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DisplayConditionRow findRowByPk( DisplayConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DisplayConditionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(DisplayConditionRow)}を使用してください。 
   */
    public static DisplayConditionDao findDaoByPk( long p_displayConditionId ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayConditionPK pk = new DisplayConditionPK( p_displayConditionId );
        DisplayConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DisplayConditionPK)}および{@@link #forRow(DisplayConditionRow)}を使用してください。 
   */
    public static DisplayConditionDao findDaoByPk( DisplayConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayConditionRow row = findRowByPk( pk );
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
   * p_displayConditionId, and にて指定の値から一意の{@@link DisplayConditionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_displayConditionId 検索対象であるp_displayConditionIdフィールドの値
   * 
   * @@return 引数指定のp_displayConditionId, and の値と一致する{@@link DisplayConditionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DisplayConditionRow findRowByDisplayConditionId( long p_displayConditionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DisplayConditionRow.TYPE,
            "display_condition_id=?",
            null,
            new Object[] { new Long(p_displayConditionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DisplayConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DisplayConditionDao.findRowsByDisplayConditionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByDisplayConditionId(long)}および{@@link #forRow(DisplayConditionRow)}を使用してください。 
   */
    public static DisplayConditionDao findDaoByDisplayConditionId( long p_displayConditionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByDisplayConditionId( p_displayConditionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_conditionNo, and にて指定の値に一致する{@@link DisplayConditionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_conditionNo 検索対象であるp_conditionNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_conditionNo, and の値と一致する{@@link DisplayConditionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeConditionNo( String p_institutionCode, String p_conditionNo ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DisplayConditionRow.TYPE,
            "institution_code=? and condition_no=?",
            null,
            new Object[] { p_institutionCode, p_conditionNo } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeConditionNo(String, String)}および{@@link #forRow(DisplayConditionRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeConditionNo( String p_institutionCode, String p_conditionNo ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeConditionNo( p_institutionCode, p_conditionNo ) );
    }

}
@
