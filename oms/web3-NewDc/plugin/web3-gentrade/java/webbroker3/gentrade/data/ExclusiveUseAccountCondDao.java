head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ExclusiveUseAccountCondDao.java;


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
 * {@@link ExclusiveUseAccountCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ExclusiveUseAccountCondRow}インスタンスへ関連付けることができます。 
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
 * @@see ExclusiveUseAccountCondPK 
 * @@see ExclusiveUseAccountCondRow 
 */
public class ExclusiveUseAccountCondDao extends DataAccessObject {


  /** 
   * この{@@link ExclusiveUseAccountCondDao}に関連する型指定のRowオブジェクト 
   */
    private ExclusiveUseAccountCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ExclusiveUseAccountCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link ExclusiveUseAccountCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ExclusiveUseAccountCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ExclusiveUseAccountCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExclusiveUseAccountCondRow )
                return new ExclusiveUseAccountCondDao( (ExclusiveUseAccountCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExclusiveUseAccountCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExclusiveUseAccountCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ExclusiveUseAccountCondRow}オブジェクト 
    */
    protected ExclusiveUseAccountCondDao( ExclusiveUseAccountCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ExclusiveUseAccountCondRow}オブジェクトを取得します。
   */
    public ExclusiveUseAccountCondRow getExclusiveUseAccountCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link ExclusiveUseAccountCondRow}オブジェクトから{@@link ExclusiveUseAccountCondDao}オブジェクトを作成します。 
   * これは実際の{@@link ExclusiveUseAccountCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ExclusiveUseAccountCondDao}取得のために指定の{@@link ExclusiveUseAccountCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ExclusiveUseAccountCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ExclusiveUseAccountCondDao forRow( ExclusiveUseAccountCondRow row ) throws java.lang.IllegalArgumentException {
        return (ExclusiveUseAccountCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExclusiveUseAccountCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ExclusiveUseAccountCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ExclusiveUseAccountCondPK}やデータベースレコードとして挿入される{@@link ExclusiveUseAccountCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExclusiveUseAccountCondRow.TYPE );
    }


  /** 
   * {@@link ExclusiveUseAccountCondRow}を一意に特定する{@@link ExclusiveUseAccountCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ExclusiveUseAccountCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ExclusiveUseAccountCondParams}オブジェクトの主キーとして利用可能な{@@link ExclusiveUseAccountCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ExclusiveUseAccountCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ExclusiveUseAccountCondRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * @@param p_finBranchCode 検索対象であるp_finBranchCodeフィールドの値
   * @@param p_finAccountNo 検索対象であるp_finAccountNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExclusiveUseAccountCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExclusiveUseAccountCondRow findRowByPk( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountCondPK pk = new ExclusiveUseAccountCondPK( p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のExclusiveUseAccountCondPKオブジェクトから{@@link ExclusiveUseAccountCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するExclusiveUseAccountCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExclusiveUseAccountCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExclusiveUseAccountCondRow findRowByPk( ExclusiveUseAccountCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExclusiveUseAccountCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(ExclusiveUseAccountCondRow)}を使用してください。 
   */
    public static ExclusiveUseAccountCondDao findDaoByPk( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountCondPK pk = new ExclusiveUseAccountCondPK( p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo );
        ExclusiveUseAccountCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ExclusiveUseAccountCondPK)}および{@@link #forRow(ExclusiveUseAccountCondRow)}を使用してください。 
   */
    public static ExclusiveUseAccountCondDao findDaoByPk( ExclusiveUseAccountCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountCondRow row = findRowByPk( pk );
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
   * p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo, and にて指定の値から一意の{@@link ExclusiveUseAccountCondRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * @@param p_finBranchCode 検索対象であるp_finBranchCodeフィールドの値
   * @@param p_finAccountNo 検索対象であるp_finAccountNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo, and の値と一致する{@@link ExclusiveUseAccountCondRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ExclusiveUseAccountCondRow findRowByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExclusiveUseAccountCondRow.TYPE,
            "institution_code=? and fin_institution_code=? and fin_branch_code=? and fin_account_no=?",
            null,
            new Object[] { p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExclusiveUseAccountCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExclusiveUseAccountCondDao.findRowsByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo(String, String, String, String)}および{@@link #forRow(ExclusiveUseAccountCondRow)}を使用してください。 
   */
    public static ExclusiveUseAccountCondDao findDaoByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
