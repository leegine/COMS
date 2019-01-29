head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	InstitutionPreferencesDao.java;


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
 * {@@link InstitutionPreferencesDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link InstitutionPreferencesRow}インスタンスへ関連付けることができます。 
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
 * @@see InstitutionPreferencesPK 
 * @@see InstitutionPreferencesRow 
 */
public class InstitutionPreferencesDao extends DataAccessObject {


  /** 
   * この{@@link InstitutionPreferencesDao}に関連する型指定のRowオブジェクト 
   */
    private InstitutionPreferencesRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link InstitutionPreferencesRow}と仮定される{@@link DataAccessObject}から新たに{@@link InstitutionPreferencesDao}を返します。 
         * @@return 指定のRowに結びつく{@@link InstitutionPreferencesDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link InstitutionPreferencesRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InstitutionPreferencesRow )
                return new InstitutionPreferencesDao( (InstitutionPreferencesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InstitutionPreferencesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InstitutionPreferencesRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link InstitutionPreferencesRow}オブジェクト 
    */
    protected InstitutionPreferencesDao( InstitutionPreferencesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link InstitutionPreferencesRow}オブジェクトを取得します。
   */
    public InstitutionPreferencesRow getInstitutionPreferencesRow() {
        return row;
    }


  /** 
   * 指定の{@@link InstitutionPreferencesRow}オブジェクトから{@@link InstitutionPreferencesDao}オブジェクトを作成します。 
   * これは実際の{@@link InstitutionPreferencesRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link InstitutionPreferencesDao}取得のために指定の{@@link InstitutionPreferencesRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link InstitutionPreferencesDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static InstitutionPreferencesDao forRow( InstitutionPreferencesRow row ) throws java.lang.IllegalArgumentException {
        return (InstitutionPreferencesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InstitutionPreferencesRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link InstitutionPreferencesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link InstitutionPreferencesPK}やデータベースレコードとして挿入される{@@link InstitutionPreferencesParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InstitutionPreferencesRow.TYPE );
    }


  /** 
   * {@@link InstitutionPreferencesRow}を一意に特定する{@@link InstitutionPreferencesPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link InstitutionPreferencesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link InstitutionPreferencesParams}オブジェクトの主キーとして利用可能な{@@link InstitutionPreferencesPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static InstitutionPreferencesPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link InstitutionPreferencesRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * @@param p_nameSerialNo 検索対象であるp_nameSerialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InstitutionPreferencesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InstitutionPreferencesRow findRowByPk( long p_institutionId, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPreferencesPK pk = new InstitutionPreferencesPK( p_institutionId, p_name, p_nameSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のInstitutionPreferencesPKオブジェクトから{@@link InstitutionPreferencesRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するInstitutionPreferencesPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InstitutionPreferencesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InstitutionPreferencesRow findRowByPk( InstitutionPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InstitutionPreferencesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,int)}および{@@link #forRow(InstitutionPreferencesRow)}を使用してください。 
   */
    public static InstitutionPreferencesDao findDaoByPk( long p_institutionId, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPreferencesPK pk = new InstitutionPreferencesPK( p_institutionId, p_name, p_nameSerialNo );
        InstitutionPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(InstitutionPreferencesPK)}および{@@link #forRow(InstitutionPreferencesRow)}を使用してください。 
   */
    public static InstitutionPreferencesDao findDaoByPk( InstitutionPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link InstitutionPreferencesDao}に紐付く{@@link InstitutionPreferencesRow}内で外部キーの関係をもつ{@@link InstitutionRow}を検索します。 
   * 
   * @@return {@@link InstitutionPreferencesDao}と外部キーの関係にある{@@link InstitutionRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public InstitutionRow fetchInstitutionRowViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        Row row = InstitutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof InstitutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (InstitutionRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchInstitutionRowViaInstitutionId()}および{@@link #forRow(InstitutionPreferencesRow)}を使用してください。 
   */
    public InstitutionDao fetchInstitutionDaoViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        DataAccessObject dao = InstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof InstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (InstitutionDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Institution
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByInstitutionId(InstitutionRow)}を使用してください。 
   */
    public static List findRowsByInstitutionId( InstitutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( dao.getInstitutionRow() );
    }


  /** 
   * {@@link InstitutionRow}と外部キーの関係にある{@@link InstitutionPreferencesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link InstitutionRow}オブジェクト 
   * @@return 指定の{@@link InstitutionRow}に外部キーを持つ{@@link InstitutionPreferencesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( row.getInstitutionId() );
    }


  /** 
   * {@@link InstitutionPK}と外部キーの関係にある{@@link InstitutionPreferencesRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link InstitutionPK}オブジェクト 
   * @@return {@@link InstitutionPK}と外部キーが一致する値を持つ{@@link InstitutionPreferencesRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( pk.institution_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link InstitutionPreferencesRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link InstitutionPreferencesRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( long p_institutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            InstitutionPreferencesRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Institution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(InstitutionPreferencesRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(InstitutionPreferencesRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionPK)}および{@@link #forRow(InstitutionPreferencesRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( pk.institution_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(long)}および{@@link #forRow(InstitutionPreferencesRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( long p_institutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( p_institutionId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionId, p_name, p_nameSerialNo, and にて指定の値から一意の{@@link InstitutionPreferencesRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * @@param p_name 検索対象であるp_nameフィールドの値
   * @@param p_nameSerialNo 検索対象であるp_nameSerialNoフィールドの値
   * 
   * @@return 引数指定のp_institutionId, p_name, p_nameSerialNo, and の値と一致する{@@link InstitutionPreferencesRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static InstitutionPreferencesRow findRowByInstitutionIdNameNameSerialNo( long p_institutionId, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InstitutionPreferencesRow.TYPE,
            "institution_id=? and name=? and name_serial_no=?",
            null,
            new Object[] { new Long(p_institutionId), p_name, new Integer(p_nameSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InstitutionPreferencesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InstitutionPreferencesDao.findRowsByInstitutionIdNameNameSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionIdNameNameSerialNo(long, String, int)}および{@@link #forRow(InstitutionPreferencesRow)}を使用してください。 
   */
    public static InstitutionPreferencesDao findDaoByInstitutionIdNameNameSerialNo( long p_institutionId, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionIdNameNameSerialNo( p_institutionId, p_name, p_nameSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
