head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.41.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	OtherOrgInfoAdminDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.srvregi.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OtherOrgInfoAdminDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OtherOrgInfoAdminRow}インスタンスへ関連付けることができます。 
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
 * @@see OtherOrgInfoAdminPK 
 * @@see OtherOrgInfoAdminRow 
 */
public class OtherOrgInfoAdminDao extends DataAccessObject {


  /** 
   * この{@@link OtherOrgInfoAdminDao}に関連する型指定のRowオブジェクト 
   */
    private OtherOrgInfoAdminRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OtherOrgInfoAdminRow}と仮定される{@@link DataAccessObject}から新たに{@@link OtherOrgInfoAdminDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OtherOrgInfoAdminDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OtherOrgInfoAdminRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OtherOrgInfoAdminRow )
                return new OtherOrgInfoAdminDao( (OtherOrgInfoAdminRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OtherOrgInfoAdminRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OtherOrgInfoAdminRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OtherOrgInfoAdminRow}オブジェクト 
    */
    protected OtherOrgInfoAdminDao( OtherOrgInfoAdminRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OtherOrgInfoAdminRow}オブジェクトを取得します。
   */
    public OtherOrgInfoAdminRow getOtherOrgInfoAdminRow() {
        return row;
    }


  /** 
   * 指定の{@@link OtherOrgInfoAdminRow}オブジェクトから{@@link OtherOrgInfoAdminDao}オブジェクトを作成します。 
   * これは実際の{@@link OtherOrgInfoAdminRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OtherOrgInfoAdminDao}取得のために指定の{@@link OtherOrgInfoAdminRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OtherOrgInfoAdminDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OtherOrgInfoAdminDao forRow( OtherOrgInfoAdminRow row ) throws java.lang.IllegalArgumentException {
        return (OtherOrgInfoAdminDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OtherOrgInfoAdminRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OtherOrgInfoAdminRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OtherOrgInfoAdminPK}やデータベースレコードとして挿入される{@@link OtherOrgInfoAdminParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OtherOrgInfoAdminRow.TYPE );
    }


  /** 
   * {@@link OtherOrgInfoAdminRow}を一意に特定する{@@link OtherOrgInfoAdminPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OtherOrgInfoAdminRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OtherOrgInfoAdminParams}オブジェクトの主キーとして利用可能な{@@link OtherOrgInfoAdminPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OtherOrgInfoAdminPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OtherOrgInfoAdminRow}オブジェクトを検索します。 
   * 
   * @@param p_sequenceNumber 検索対象であるp_sequenceNumberフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrgInfoAdminRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrgInfoAdminRow findRowByPk( long p_sequenceNumber, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgInfoAdminPK pk = new OtherOrgInfoAdminPK( p_sequenceNumber, p_srvDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOtherOrgInfoAdminPKオブジェクトから{@@link OtherOrgInfoAdminRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOtherOrgInfoAdminPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OtherOrgInfoAdminRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OtherOrgInfoAdminRow findRowByPk( OtherOrgInfoAdminPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OtherOrgInfoAdminRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(OtherOrgInfoAdminRow)}を使用してください。 
   */
    public static OtherOrgInfoAdminDao findDaoByPk( long p_sequenceNumber, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgInfoAdminPK pk = new OtherOrgInfoAdminPK( p_sequenceNumber, p_srvDiv );
        OtherOrgInfoAdminRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OtherOrgInfoAdminPK)}および{@@link #forRow(OtherOrgInfoAdminRow)}を使用してください。 
   */
    public static OtherOrgInfoAdminDao findDaoByPk( OtherOrgInfoAdminPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgInfoAdminRow row = findRowByPk( pk );
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
   * p_sequenceNumber, p_srvDiv, and にて指定の値から一意の{@@link OtherOrgInfoAdminRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_sequenceNumber 検索対象であるp_sequenceNumberフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * 
   * @@return 引数指定のp_sequenceNumber, p_srvDiv, and の値と一致する{@@link OtherOrgInfoAdminRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OtherOrgInfoAdminRow findRowBySequenceNumberSrvDiv( long p_sequenceNumber, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OtherOrgInfoAdminRow.TYPE,
            "sequence_number=? and srv_div=?",
            null,
            new Object[] { new Long(p_sequenceNumber), p_srvDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OtherOrgInfoAdminRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OtherOrgInfoAdminDao.findRowsBySequenceNumberSrvDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowBySequenceNumberSrvDiv(long, String)}および{@@link #forRow(OtherOrgInfoAdminRow)}を使用してください。 
   */
    public static OtherOrgInfoAdminDao findDaoBySequenceNumberSrvDiv( long p_sequenceNumber, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySequenceNumberSrvDiv( p_sequenceNumber, p_srvDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_srvDiv, p_status, p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link OtherOrgInfoAdminRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_srvDiv, p_status, p_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link OtherOrgInfoAdminRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsBySrvDivStatusInstitutionCodeBranchCodeAccountCode( String p_srvDiv, String p_status, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            OtherOrgInfoAdminRow.TYPE,
            "srv_div=? and status=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_srvDiv, p_status, p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsBySrvDivStatusInstitutionCodeBranchCodeAccountCode(String, String, String, String, String)}および{@@link #forRow(OtherOrgInfoAdminRow)}を使用してください。 
   */
    public static List findDaosBySrvDivStatusInstitutionCodeBranchCodeAccountCode( String p_srvDiv, String p_status, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsBySrvDivStatusInstitutionCodeBranchCodeAccountCode( p_srvDiv, p_status, p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
