head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	TriggerOrderStopDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.triggerorder.base.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link TriggerOrderStopDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TriggerOrderStopRow}インスタンスへ関連付けることができます。 
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
 * @@see TriggerOrderStopPK 
 * @@see TriggerOrderStopRow 
 */
public class TriggerOrderStopDao extends DataAccessObject {


  /** 
   * この{@@link TriggerOrderStopDao}に関連する型指定のRowオブジェクト 
   */
    private TriggerOrderStopRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TriggerOrderStopRow}と仮定される{@@link DataAccessObject}から新たに{@@link TriggerOrderStopDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TriggerOrderStopDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TriggerOrderStopRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TriggerOrderStopRow )
                return new TriggerOrderStopDao( (TriggerOrderStopRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TriggerOrderStopRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TriggerOrderStopRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TriggerOrderStopRow}オブジェクト 
    */
    protected TriggerOrderStopDao( TriggerOrderStopRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TriggerOrderStopRow}オブジェクトを取得します。
   */
    public TriggerOrderStopRow getTriggerOrderStopRow() {
        return row;
    }


  /** 
   * 指定の{@@link TriggerOrderStopRow}オブジェクトから{@@link TriggerOrderStopDao}オブジェクトを作成します。 
   * これは実際の{@@link TriggerOrderStopRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TriggerOrderStopDao}取得のために指定の{@@link TriggerOrderStopRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TriggerOrderStopDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TriggerOrderStopDao forRow( TriggerOrderStopRow row ) throws java.lang.IllegalArgumentException {
        return (TriggerOrderStopDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TriggerOrderStopRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TriggerOrderStopRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TriggerOrderStopPK}やデータベースレコードとして挿入される{@@link TriggerOrderStopParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TriggerOrderStopRow.TYPE );
    }


  /** 
   * {@@link TriggerOrderStopRow}を一意に特定する{@@link TriggerOrderStopPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TriggerOrderStopRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TriggerOrderStopParams}オブジェクトの主キーとして利用可能な{@@link TriggerOrderStopPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TriggerOrderStopPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TriggerOrderStopPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TriggerOrderStopRow}オブジェクトを検索します。 
   * 
   * @@param p_triggerOrderStopId 検索対象であるp_triggerOrderStopIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TriggerOrderStopRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TriggerOrderStopRow findRowByPk( long p_triggerOrderStopId ) throws DataFindException, DataQueryException, DataNetworkException {
        TriggerOrderStopPK pk = new TriggerOrderStopPK( p_triggerOrderStopId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTriggerOrderStopPKオブジェクトから{@@link TriggerOrderStopRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTriggerOrderStopPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TriggerOrderStopRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TriggerOrderStopRow findRowByPk( TriggerOrderStopPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TriggerOrderStopRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TriggerOrderStopRow)}を使用してください。 
   */
    public static TriggerOrderStopDao findDaoByPk( long p_triggerOrderStopId ) throws DataFindException, DataQueryException, DataNetworkException {
        TriggerOrderStopPK pk = new TriggerOrderStopPK( p_triggerOrderStopId );
        TriggerOrderStopRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TriggerOrderStopPK)}および{@@link #forRow(TriggerOrderStopRow)}を使用してください。 
   */
    public static TriggerOrderStopDao findDaoByPk( TriggerOrderStopPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TriggerOrderStopRow row = findRowByPk( pk );
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
   * p_triggerOrderStopId, and にて指定の値から一意の{@@link TriggerOrderStopRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_triggerOrderStopId 検索対象であるp_triggerOrderStopIdフィールドの値
   * 
   * @@return 引数指定のp_triggerOrderStopId, and の値と一致する{@@link TriggerOrderStopRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TriggerOrderStopRow findRowByTriggerOrderStopId( long p_triggerOrderStopId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TriggerOrderStopRow.TYPE,
            "trigger_order_stop_id=?",
            null,
            new Object[] { new Long(p_triggerOrderStopId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TriggerOrderStopRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TriggerOrderStopDao.findRowsByTriggerOrderStopId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTriggerOrderStopId(long)}および{@@link #forRow(TriggerOrderStopRow)}を使用してください。 
   */
    public static TriggerOrderStopDao findDaoByTriggerOrderStopId( long p_triggerOrderStopId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTriggerOrderStopId( p_triggerOrderStopId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_targetType, p_key, and にて指定の値に一致する{@@link TriggerOrderStopRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_targetType 検索対象であるp_targetTypeフィールドの値
   * @@param p_key 検索対象であるp_keyフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_targetType, p_key, and の値と一致する{@@link TriggerOrderStopRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeTargetTypeKey( String p_institutionCode, String p_branchCode, String p_targetType, String p_key ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TriggerOrderStopRow.TYPE,
            "institution_code=? and branch_code=? and target_type=? and key=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_targetType, p_key } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeTargetTypeKey(String, String, String, String)}および{@@link #forRow(TriggerOrderStopRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeTargetTypeKey( String p_institutionCode, String p_branchCode, String p_targetType, String p_key ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeTargetTypeKey( p_institutionCode, p_branchCode, p_targetType, p_key ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_targetType, p_deleteFlag, and にて指定の値に一致する{@@link TriggerOrderStopRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_targetType 検索対象であるp_targetTypeフィールドの値
   * @@param p_deleteFlag 検索対象であるp_deleteFlagフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_targetType, p_deleteFlag, and の値と一致する{@@link TriggerOrderStopRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeTargetTypeDeleteFlag( String p_institutionCode, String p_branchCode, String p_targetType, int p_deleteFlag ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TriggerOrderStopRow.TYPE,
            "institution_code=? and branch_code=? and target_type=? and delete_flag=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_targetType, new Integer(p_deleteFlag) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeTargetTypeDeleteFlag(String, String, String, int)}および{@@link #forRow(TriggerOrderStopRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeTargetTypeDeleteFlag( String p_institutionCode, String p_branchCode, String p_targetType, int p_deleteFlag ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeTargetTypeDeleteFlag( p_institutionCode, p_branchCode, p_targetType, p_deleteFlag ) );
    }

}
@
