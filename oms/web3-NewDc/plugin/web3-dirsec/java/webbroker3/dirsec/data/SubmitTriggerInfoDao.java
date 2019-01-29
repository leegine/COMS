head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SubmitTriggerInfoDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.dirsec.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link SubmitTriggerInfoDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SubmitTriggerInfoRow}インスタンスへ関連付けることができます。 
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
 * @@see SubmitTriggerInfoPK 
 * @@see SubmitTriggerInfoRow 
 */
public class SubmitTriggerInfoDao extends DataAccessObject {


  /** 
   * この{@@link SubmitTriggerInfoDao}に関連する型指定のRowオブジェクト 
   */
    private SubmitTriggerInfoRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SubmitTriggerInfoRow}と仮定される{@@link DataAccessObject}から新たに{@@link SubmitTriggerInfoDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SubmitTriggerInfoDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SubmitTriggerInfoRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SubmitTriggerInfoRow )
                return new SubmitTriggerInfoDao( (SubmitTriggerInfoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SubmitTriggerInfoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SubmitTriggerInfoRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SubmitTriggerInfoRow}オブジェクト 
    */
    protected SubmitTriggerInfoDao( SubmitTriggerInfoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SubmitTriggerInfoRow}オブジェクトを取得します。
   */
    public SubmitTriggerInfoRow getSubmitTriggerInfoRow() {
        return row;
    }


  /** 
   * 指定の{@@link SubmitTriggerInfoRow}オブジェクトから{@@link SubmitTriggerInfoDao}オブジェクトを作成します。 
   * これは実際の{@@link SubmitTriggerInfoRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SubmitTriggerInfoDao}取得のために指定の{@@link SubmitTriggerInfoRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SubmitTriggerInfoDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SubmitTriggerInfoDao forRow( SubmitTriggerInfoRow row ) throws java.lang.IllegalArgumentException {
        return (SubmitTriggerInfoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SubmitTriggerInfoRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SubmitTriggerInfoRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SubmitTriggerInfoPK}やデータベースレコードとして挿入される{@@link SubmitTriggerInfoParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SubmitTriggerInfoRow.TYPE );
    }


  /** 
   * {@@link SubmitTriggerInfoRow}を一意に特定する{@@link SubmitTriggerInfoPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SubmitTriggerInfoRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SubmitTriggerInfoParams}オブジェクトの主キーとして利用可能な{@@link SubmitTriggerInfoPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SubmitTriggerInfoPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SubmitTriggerInfoRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_jobId 検索対象であるp_jobIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SubmitTriggerInfoRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SubmitTriggerInfoRow findRowByPk( String p_institutionCode, String p_requestCode, String p_jobId ) throws DataFindException, DataQueryException, DataNetworkException {
        SubmitTriggerInfoPK pk = new SubmitTriggerInfoPK( p_institutionCode, p_requestCode, p_jobId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSubmitTriggerInfoPKオブジェクトから{@@link SubmitTriggerInfoRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSubmitTriggerInfoPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SubmitTriggerInfoRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SubmitTriggerInfoRow findRowByPk( SubmitTriggerInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SubmitTriggerInfoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(SubmitTriggerInfoRow)}を使用してください。 
   */
    public static SubmitTriggerInfoDao findDaoByPk( String p_institutionCode, String p_requestCode, String p_jobId ) throws DataFindException, DataQueryException, DataNetworkException {
        SubmitTriggerInfoPK pk = new SubmitTriggerInfoPK( p_institutionCode, p_requestCode, p_jobId );
        SubmitTriggerInfoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SubmitTriggerInfoPK)}および{@@link #forRow(SubmitTriggerInfoRow)}を使用してください。 
   */
    public static SubmitTriggerInfoDao findDaoByPk( SubmitTriggerInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SubmitTriggerInfoRow row = findRowByPk( pk );
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
   * p_institutionCode, p_requestCode, p_jobId, and にて指定の値から一意の{@@link SubmitTriggerInfoRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_jobId 検索対象であるp_jobIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_requestCode, p_jobId, and の値と一致する{@@link SubmitTriggerInfoRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SubmitTriggerInfoRow findRowByInstitutionCodeRequestCodeJobId( String p_institutionCode, String p_requestCode, String p_jobId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SubmitTriggerInfoRow.TYPE,
            "institution_code=? and request_code=? and job_id=?",
            null,
            new Object[] { p_institutionCode, p_requestCode, p_jobId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SubmitTriggerInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SubmitTriggerInfoDao.findRowsByInstitutionCodeRequestCodeJobId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeRequestCodeJobId(String, String, String)}および{@@link #forRow(SubmitTriggerInfoRow)}を使用してください。 
   */
    public static SubmitTriggerInfoDao findDaoByInstitutionCodeRequestCodeJobId( String p_institutionCode, String p_requestCode, String p_jobId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeRequestCodeJobId( p_institutionCode, p_requestCode, p_jobId ) );
    }


  /** 
   * p_institutionCode, p_jobId, p_idNo, and にて指定の値から一意の{@@link SubmitTriggerInfoRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_jobId 検索対象であるp_jobIdフィールドの値
   * @@param p_idNo 検索対象であるp_idNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_jobId, p_idNo, and の値と一致する{@@link SubmitTriggerInfoRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SubmitTriggerInfoRow findRowByInstitutionCodeJobIdIdNo( String p_institutionCode, String p_jobId, String p_idNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SubmitTriggerInfoRow.TYPE,
            "institution_code=? and job_id=? and id_no=?",
            null,
            new Object[] { p_institutionCode, p_jobId, p_idNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SubmitTriggerInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SubmitTriggerInfoDao.findRowsByInstitutionCodeJobIdIdNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeJobIdIdNo(String, String, String)}および{@@link #forRow(SubmitTriggerInfoRow)}を使用してください。 
   */
    public static SubmitTriggerInfoDao findDaoByInstitutionCodeJobIdIdNo( String p_institutionCode, String p_jobId, String p_idNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeJobIdIdNo( p_institutionCode, p_jobId, p_idNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
