head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	BankDepositErrorHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link BankDepositErrorHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BankDepositErrorHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see BankDepositErrorHistoryPK 
 * @@see BankDepositErrorHistoryRow 
 */
public class BankDepositErrorHistoryDao extends DataAccessObject {


  /** 
   * この{@@link BankDepositErrorHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private BankDepositErrorHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BankDepositErrorHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link BankDepositErrorHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BankDepositErrorHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BankDepositErrorHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankDepositErrorHistoryRow )
                return new BankDepositErrorHistoryDao( (BankDepositErrorHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankDepositErrorHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankDepositErrorHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BankDepositErrorHistoryRow}オブジェクト 
    */
    protected BankDepositErrorHistoryDao( BankDepositErrorHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BankDepositErrorHistoryRow}オブジェクトを取得します。
   */
    public BankDepositErrorHistoryRow getBankDepositErrorHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link BankDepositErrorHistoryRow}オブジェクトから{@@link BankDepositErrorHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link BankDepositErrorHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BankDepositErrorHistoryDao}取得のために指定の{@@link BankDepositErrorHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BankDepositErrorHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BankDepositErrorHistoryDao forRow( BankDepositErrorHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (BankDepositErrorHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankDepositErrorHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BankDepositErrorHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BankDepositErrorHistoryPK}やデータベースレコードとして挿入される{@@link BankDepositErrorHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankDepositErrorHistoryRow.TYPE );
    }


  /** 
   * {@@link BankDepositErrorHistoryRow}を一意に特定する{@@link BankDepositErrorHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BankDepositErrorHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BankDepositErrorHistoryParams}オブジェクトの主キーとして利用可能な{@@link BankDepositErrorHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BankDepositErrorHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BankDepositErrorHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BankDepositErrorHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_bankDepositErrorHistoryId 検索対象であるp_bankDepositErrorHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankDepositErrorHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankDepositErrorHistoryRow findRowByPk( long p_bankDepositErrorHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositErrorHistoryPK pk = new BankDepositErrorHistoryPK( p_bankDepositErrorHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBankDepositErrorHistoryPKオブジェクトから{@@link BankDepositErrorHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBankDepositErrorHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankDepositErrorHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankDepositErrorHistoryRow findRowByPk( BankDepositErrorHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankDepositErrorHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public static BankDepositErrorHistoryDao findDaoByPk( long p_bankDepositErrorHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositErrorHistoryPK pk = new BankDepositErrorHistoryPK( p_bankDepositErrorHistoryId );
        BankDepositErrorHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BankDepositErrorHistoryPK)}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public static BankDepositErrorHistoryDao findDaoByPk( BankDepositErrorHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositErrorHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link BankDepositErrorHistoryDao}に紐付く{@@link BankDepositErrorHistoryRow}内で外部キーの関係をもつ{@@link BankDepositNotifyRow}を検索します。 
   * 
   * @@return {@@link BankDepositErrorHistoryDao}と外部キーの関係にある{@@link BankDepositNotifyRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public BankDepositNotifyRow fetchBankDepositNotifyRowViaBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataFindException, DataQueryException  {
        BankDepositNotifyPK pk = new BankDepositNotifyPK( row.getBankDepositNotifyId(), row.getInstitutionCode(), row.getDataLoadDiv() );
        Row row = BankDepositNotifyDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BankDepositNotifyRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BankDepositNotifyRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBankDepositNotifyRowViaBankDepositNotifyIdInstitutionCodeDataLoadDiv()}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public BankDepositNotifyDao fetchBankDepositNotifyDaoViaBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataFindException, DataQueryException  {
        BankDepositNotifyPK pk = new BankDepositNotifyPK( row.getBankDepositNotifyId(), row.getInstitutionCode(), row.getDataLoadDiv() );
        DataAccessObject dao = BankDepositNotifyDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BankDepositNotifyDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BankDepositNotifyDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for BankDepositNotify
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(BankDepositNotifyRow)}を使用してください。 
   */
    public static List findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( dao.getBankDepositNotifyRow() );
    }


  /** 
   * {@@link BankDepositNotifyRow}と外部キーの関係にある{@@link BankDepositErrorHistoryRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BankDepositNotifyRow}オブジェクト 
   * @@return 指定の{@@link BankDepositNotifyRow}に外部キーを持つ{@@link BankDepositErrorHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( row.getBankDepositNotifyId(), row.getInstitutionCode(), row.getDataLoadDiv() );
    }


  /** 
   * {@@link BankDepositNotifyPK}と外部キーの関係にある{@@link BankDepositErrorHistoryRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BankDepositNotifyPK}オブジェクト 
   * @@return {@@link BankDepositNotifyPK}と外部キーが一致する値を持つ{@@link BankDepositErrorHistoryRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( pk.bank_deposit_notify_id, pk.institution_code, pk.data_load_div );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BankDepositErrorHistoryRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_bankDepositNotifyId 検索対象であるp_bankDepositNotifyIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_dataLoadDiv 検索対象であるp_dataLoadDivフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BankDepositErrorHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BankDepositErrorHistoryRow.TYPE,
            "bank_deposit_notify_id=? and institution_code=? and data_load_div=?",
            null,
            new Object[] { new Long(p_bankDepositNotifyId), p_institutionCode, p_dataLoadDiv } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BankDepositNotify
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(BankDepositNotifyRow)}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public static List findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(BankDepositNotifyRow)}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public static List findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(BankDepositNotifyPK)}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public static List findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( pk.bank_deposit_notify_id, pk.institution_code, pk.data_load_div ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(long, String, String)}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public static List findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv ) );
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
   * p_bankDepositErrorHistoryId, and にて指定の値から一意の{@@link BankDepositErrorHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_bankDepositErrorHistoryId 検索対象であるp_bankDepositErrorHistoryIdフィールドの値
   * 
   * @@return 引数指定のp_bankDepositErrorHistoryId, and の値と一致する{@@link BankDepositErrorHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BankDepositErrorHistoryRow findRowByBankDepositErrorHistoryId( long p_bankDepositErrorHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankDepositErrorHistoryRow.TYPE,
            "bank_deposit_error_history_id=?",
            null,
            new Object[] { new Long(p_bankDepositErrorHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankDepositErrorHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankDepositErrorHistoryDao.findRowsByBankDepositErrorHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBankDepositErrorHistoryId(long)}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public static BankDepositErrorHistoryDao findDaoByBankDepositErrorHistoryId( long p_bankDepositErrorHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBankDepositErrorHistoryId( p_bankDepositErrorHistoryId ) );
    }


  /** 
   * p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, p_serialNo, and にて指定の値から一意の{@@link BankDepositErrorHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_bankDepositNotifyId 検索対象であるp_bankDepositNotifyIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_dataLoadDiv 検索対象であるp_dataLoadDivフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のp_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, p_serialNo, and の値と一致する{@@link BankDepositErrorHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BankDepositErrorHistoryRow findRowByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv, int p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankDepositErrorHistoryRow.TYPE,
            "bank_deposit_notify_id=? and institution_code=? and data_load_div=? and serial_no=?",
            null,
            new Object[] { new Long(p_bankDepositNotifyId), p_institutionCode, p_dataLoadDiv, new Integer(p_serialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankDepositErrorHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankDepositErrorHistoryDao.findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo(long, String, String, int)}および{@@link #forRow(BankDepositErrorHistoryRow)}を使用してください。 
   */
    public static BankDepositErrorHistoryDao findDaoByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv, int p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, p_serialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
