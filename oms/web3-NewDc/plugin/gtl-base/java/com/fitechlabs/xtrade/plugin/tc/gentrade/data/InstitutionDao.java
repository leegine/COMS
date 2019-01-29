head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.37.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	InstitutionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link InstitutionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link InstitutionRow}インスタンスへ関連付けることができます。 
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
 * @@see InstitutionPK 
 * @@see InstitutionRow 
 */
public class InstitutionDao extends DataAccessObject {


  /** 
   * この{@@link InstitutionDao}に関連する型指定のRowオブジェクト 
   */
    private InstitutionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link InstitutionRow}と仮定される{@@link DataAccessObject}から新たに{@@link InstitutionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link InstitutionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link InstitutionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InstitutionRow )
                return new InstitutionDao( (InstitutionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InstitutionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InstitutionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link InstitutionRow}オブジェクト 
    */
    protected InstitutionDao( InstitutionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link InstitutionRow}オブジェクトを取得します。
   */
    public InstitutionRow getInstitutionRow() {
        return row;
    }


  /** 
   * 指定の{@@link InstitutionRow}オブジェクトから{@@link InstitutionDao}オブジェクトを作成します。 
   * これは実際の{@@link InstitutionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link InstitutionDao}取得のために指定の{@@link InstitutionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link InstitutionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static InstitutionDao forRow( InstitutionRow row ) throws java.lang.IllegalArgumentException {
        return (InstitutionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InstitutionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link InstitutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link InstitutionPK}やデータベースレコードとして挿入される{@@link InstitutionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InstitutionRow.TYPE );
    }


  /** 
   * {@@link InstitutionRow}を一意に特定する{@@link InstitutionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link InstitutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link InstitutionParams}オブジェクトの主キーとして利用可能な{@@link InstitutionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static InstitutionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new InstitutionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link InstitutionRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InstitutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InstitutionRow findRowByPk( long p_institutionId ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPK pk = new InstitutionPK( p_institutionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のInstitutionPKオブジェクトから{@@link InstitutionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するInstitutionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InstitutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InstitutionRow findRowByPk( InstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InstitutionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public static InstitutionDao findDaoByPk( long p_institutionId ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPK pk = new InstitutionPK( p_institutionId );
        InstitutionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(InstitutionPK)}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public static InstitutionDao findDaoByPk( InstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link InstitutionDao}に関連する{@@link InstitutionRow}の外部キーがある{@@link BranchRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link InstitutionDao}に関連する{@@link InstitutionRow}の外部キーがある{@@link BranchRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchBranchRowsByInstitutionId() throws DataNetworkException, DataQueryException  {
        return BranchDao.findRowsByInstitutionId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBranchRowsByInstitutionId()}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public List fetchBranchDaosByInstitutionId() throws DataNetworkException, DataQueryException  {
        return BranchDao.findDaosByInstitutionId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBranchRowsByInstitutionId()}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public List fetchBranchDaosInstitutionId() throws DataNetworkException, DataQueryException  {
        return fetchBranchDaosByInstitutionId();
    }


  /** 
   * この{@@link InstitutionDao}に関連する{@@link InstitutionRow}の外部キーがある{@@link MainAccountRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link InstitutionDao}に関連する{@@link InstitutionRow}の外部キーがある{@@link MainAccountRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchMainAccountRowsByInstitutionId() throws DataNetworkException, DataQueryException  {
        return MainAccountDao.findRowsByInstitutionId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMainAccountRowsByInstitutionId()}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public List fetchMainAccountDaosByInstitutionId() throws DataNetworkException, DataQueryException  {
        return MainAccountDao.findDaosByInstitutionId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMainAccountRowsByInstitutionId()}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public List fetchMainAccountDaosInstitutionId() throws DataNetworkException, DataQueryException  {
        return fetchMainAccountDaosByInstitutionId();
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
   * p_institutionId, and にて指定の値から一意の{@@link InstitutionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * 
   * @@return 引数指定のp_institutionId, and の値と一致する{@@link InstitutionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static InstitutionRow findRowByInstitutionId( long p_institutionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InstitutionRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InstitutionDao.findRowsByInstitutionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionId(long)}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public static InstitutionDao findDaoByInstitutionId( long p_institutionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionId( p_institutionId ) );
    }


  /** 
   * p_institutionCode, and にて指定の値から一意の{@@link InstitutionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, and の値と一致する{@@link InstitutionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static InstitutionRow findRowByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InstitutionRow.TYPE,
            "institution_code=?",
            null,
            new Object[] { p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InstitutionDao.findRowsByInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCode(String)}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public static InstitutionDao findDaoByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCode( p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionId, p_institutionCode, and にて指定の値に一致する{@@link InstitutionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionId, p_institutionCode, and の値と一致する{@@link InstitutionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionIdInstitutionCode( long p_institutionId, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            InstitutionRow.TYPE,
            "institution_id=? and institution_code=?",
            null,
            new Object[] { new Long(p_institutionId), p_institutionCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionIdInstitutionCode(long, String)}および{@@link #forRow(InstitutionRow)}を使用してください。 
   */
    public static List findDaosByInstitutionIdInstitutionCode( long p_institutionId, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionIdInstitutionCode( p_institutionId, p_institutionCode ) );
    }

}
@
