head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	GenFinTransactionDao.java;


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
 * {@@link GenFinTransactionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link GenFinTransactionRow}インスタンスへ関連付けることができます。 
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
 * @@see GenFinTransactionPK 
 * @@see GenFinTransactionRow 
 */
public class GenFinTransactionDao extends DataAccessObject {


  /** 
   * この{@@link GenFinTransactionDao}に関連する型指定のRowオブジェクト 
   */
    private GenFinTransactionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link GenFinTransactionRow}と仮定される{@@link DataAccessObject}から新たに{@@link GenFinTransactionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link GenFinTransactionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link GenFinTransactionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof GenFinTransactionRow )
                return new GenFinTransactionDao( (GenFinTransactionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a GenFinTransactionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link GenFinTransactionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link GenFinTransactionRow}オブジェクト 
    */
    protected GenFinTransactionDao( GenFinTransactionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link GenFinTransactionRow}オブジェクトを取得します。
   */
    public GenFinTransactionRow getGenFinTransactionRow() {
        return row;
    }


  /** 
   * 指定の{@@link GenFinTransactionRow}オブジェクトから{@@link GenFinTransactionDao}オブジェクトを作成します。 
   * これは実際の{@@link GenFinTransactionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link GenFinTransactionDao}取得のために指定の{@@link GenFinTransactionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link GenFinTransactionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static GenFinTransactionDao forRow( GenFinTransactionRow row ) throws java.lang.IllegalArgumentException {
        return (GenFinTransactionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link GenFinTransactionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link GenFinTransactionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link GenFinTransactionPK}やデータベースレコードとして挿入される{@@link GenFinTransactionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( GenFinTransactionRow.TYPE );
    }


  /** 
   * {@@link GenFinTransactionRow}を一意に特定する{@@link GenFinTransactionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link GenFinTransactionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link GenFinTransactionParams}オブジェクトの主キーとして利用可能な{@@link GenFinTransactionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static GenFinTransactionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new GenFinTransactionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link GenFinTransactionRow}オブジェクトを検索します。 
   * 
   * @@param p_transactionId 検索対象であるp_transactionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link GenFinTransactionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static GenFinTransactionRow findRowByPk( long p_transactionId ) throws DataFindException, DataQueryException, DataNetworkException {
        GenFinTransactionPK pk = new GenFinTransactionPK( p_transactionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のGenFinTransactionPKオブジェクトから{@@link GenFinTransactionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するGenFinTransactionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link GenFinTransactionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static GenFinTransactionRow findRowByPk( GenFinTransactionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (GenFinTransactionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public static GenFinTransactionDao findDaoByPk( long p_transactionId ) throws DataFindException, DataQueryException, DataNetworkException {
        GenFinTransactionPK pk = new GenFinTransactionPK( p_transactionId );
        GenFinTransactionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(GenFinTransactionPK)}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public static GenFinTransactionDao findDaoByPk( GenFinTransactionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        GenFinTransactionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link GenFinTransactionDao}に紐付く{@@link GenFinTransactionRow}内で外部キーの関係をもつ{@@link SubAccountRow}を検索します。 
   * 
   * @@return {@@link GenFinTransactionDao}と外部キーの関係にある{@@link SubAccountRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public SubAccountRow fetchSubAccountRowViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        Row row = SubAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof SubAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (SubAccountRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for SubAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}を使用してください。 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( dao.getSubAccountRow() );
    }


  /** 
   * {@@link SubAccountRow}と外部キーの関係にある{@@link GenFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link SubAccountRow}オブジェクト 
   * @@return 指定の{@@link SubAccountRow}に外部キーを持つ{@@link GenFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}と外部キーの関係にある{@@link GenFinTransactionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link SubAccountPK}オブジェクト 
   * @@return {@@link SubAccountPK}と外部キーが一致する値を持つ{@@link GenFinTransactionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link GenFinTransactionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link GenFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            GenFinTransactionRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdSubAccountId(long, long)}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * p_transactionId, and にて指定の値から一意の{@@link GenFinTransactionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_transactionId 検索対象であるp_transactionIdフィールドの値
   * 
   * @@return 引数指定のp_transactionId, and の値と一致する{@@link GenFinTransactionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static GenFinTransactionRow findRowByTransactionId( long p_transactionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            GenFinTransactionRow.TYPE,
            "transaction_id=?",
            null,
            new Object[] { new Long(p_transactionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (GenFinTransactionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query GenFinTransactionDao.findRowsByTransactionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTransactionId(long)}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public static GenFinTransactionDao findDaoByTransactionId( long p_transactionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTransactionId( p_transactionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_transactionId, p_deliveryDate, p_accountId, p_subAccountId, and にて指定の値に一致する{@@link GenFinTransactionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_transactionId 検索対象であるp_transactionIdフィールドの値
   * @@param p_deliveryDate 検索対象であるp_deliveryDateフィールドの値
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_subAccountId 検索対象であるp_subAccountIdフィールドの値
   * 
   * @@return 引数指定のp_transactionId, p_deliveryDate, p_accountId, p_subAccountId, and の値と一致する{@@link GenFinTransactionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTransactionIdDeliveryDateAccountIdSubAccountId( long p_transactionId, java.sql.Timestamp p_deliveryDate, long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            GenFinTransactionRow.TYPE,
            "transaction_id=? and delivery_date=? and account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_transactionId), p_deliveryDate, new Long(p_accountId), new Long(p_subAccountId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTransactionIdDeliveryDateAccountIdSubAccountId(long, java.sql.Timestamp, long, long)}および{@@link #forRow(GenFinTransactionRow)}を使用してください。 
   */
    public static List findDaosByTransactionIdDeliveryDateAccountIdSubAccountId( long p_transactionId, java.sql.Timestamp p_deliveryDate, long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTransactionIdDeliveryDateAccountIdSubAccountId( p_transactionId, p_deliveryDate, p_accountId, p_subAccountId ) );
    }

}
@
