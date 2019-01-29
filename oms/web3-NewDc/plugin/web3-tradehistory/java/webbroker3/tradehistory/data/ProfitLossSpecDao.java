head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	ProfitLossSpecDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradehistory.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link ProfitLossSpecDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ProfitLossSpecRow}インスタンスへ関連付けることができます。 
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
 * @@see ProfitLossSpecPK 
 * @@see ProfitLossSpecRow 
 */
public class ProfitLossSpecDao extends DataAccessObject {


  /** 
   * この{@@link ProfitLossSpecDao}に関連する型指定のRowオブジェクト 
   */
    private ProfitLossSpecRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ProfitLossSpecRow}と仮定される{@@link DataAccessObject}から新たに{@@link ProfitLossSpecDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ProfitLossSpecDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ProfitLossSpecRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ProfitLossSpecRow )
                return new ProfitLossSpecDao( (ProfitLossSpecRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ProfitLossSpecRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ProfitLossSpecRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ProfitLossSpecRow}オブジェクト 
    */
    protected ProfitLossSpecDao( ProfitLossSpecRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ProfitLossSpecRow}オブジェクトを取得します。
   */
    public ProfitLossSpecRow getProfitLossSpecRow() {
        return row;
    }


  /** 
   * 指定の{@@link ProfitLossSpecRow}オブジェクトから{@@link ProfitLossSpecDao}オブジェクトを作成します。 
   * これは実際の{@@link ProfitLossSpecRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ProfitLossSpecDao}取得のために指定の{@@link ProfitLossSpecRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ProfitLossSpecDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ProfitLossSpecDao forRow( ProfitLossSpecRow row ) throws java.lang.IllegalArgumentException {
        return (ProfitLossSpecDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ProfitLossSpecRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ProfitLossSpecRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ProfitLossSpecPK}やデータベースレコードとして挿入される{@@link ProfitLossSpecParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ProfitLossSpecRow.TYPE );
    }


  /** 
   * {@@link ProfitLossSpecRow}を一意に特定する{@@link ProfitLossSpecPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ProfitLossSpecRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ProfitLossSpecParams}オブジェクトの主キーとして利用可能な{@@link ProfitLossSpecPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ProfitLossSpecPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new ProfitLossSpecPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ProfitLossSpecRow}オブジェクトを検索します。 
   * 
   * @@param p_profitLossSpecId 検索対象であるp_profitLossSpecIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ProfitLossSpecRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ProfitLossSpecRow findRowByPk( long p_profitLossSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        ProfitLossSpecPK pk = new ProfitLossSpecPK( p_profitLossSpecId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のProfitLossSpecPKオブジェクトから{@@link ProfitLossSpecRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するProfitLossSpecPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ProfitLossSpecRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ProfitLossSpecRow findRowByPk( ProfitLossSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ProfitLossSpecRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(ProfitLossSpecRow)}を使用してください。 
   */
    public static ProfitLossSpecDao findDaoByPk( long p_profitLossSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        ProfitLossSpecPK pk = new ProfitLossSpecPK( p_profitLossSpecId );
        ProfitLossSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ProfitLossSpecPK)}および{@@link #forRow(ProfitLossSpecRow)}を使用してください。 
   */
    public static ProfitLossSpecDao findDaoByPk( ProfitLossSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ProfitLossSpecRow row = findRowByPk( pk );
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
   * p_profitLossSpecId, and にて指定の値から一意の{@@link ProfitLossSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_profitLossSpecId 検索対象であるp_profitLossSpecIdフィールドの値
   * 
   * @@return 引数指定のp_profitLossSpecId, and の値と一致する{@@link ProfitLossSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ProfitLossSpecRow findRowByProfitLossSpecId( long p_profitLossSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ProfitLossSpecRow.TYPE,
            "profit_loss_spec_id=?",
            null,
            new Object[] { new Long(p_profitLossSpecId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ProfitLossSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ProfitLossSpecDao.findRowsByProfitLossSpecId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProfitLossSpecId(long)}および{@@link #forRow(ProfitLossSpecRow)}を使用してください。 
   */
    public static ProfitLossSpecDao findDaoByProfitLossSpecId( long p_profitLossSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProfitLossSpecId( p_profitLossSpecId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_recDiv, and にて指定の値に一致する{@@link ProfitLossSpecRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_recDiv 検索対象であるp_recDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_recDiv, and の値と一致する{@@link ProfitLossSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeRecDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_recDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ProfitLossSpecRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and rec_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_recDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeRecDiv(String, String, String, String)}および{@@link #forRow(ProfitLossSpecRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeRecDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_recDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeRecDiv( p_institutionCode, p_branchCode, p_accountCode, p_recDiv ) );
    }

}
@
