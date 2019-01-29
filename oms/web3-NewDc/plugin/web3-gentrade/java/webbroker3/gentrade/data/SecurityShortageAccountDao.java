head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.24.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SecurityShortageAccountDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SecurityShortageAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SecurityShortageAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see SecurityShortageAccountPK 
 * @@see SecurityShortageAccountRow 
 */
public class SecurityShortageAccountDao extends DataAccessObject {


  /** 
   * この{@@link SecurityShortageAccountDao}に関連する型指定のRowオブジェクト 
   */
    private SecurityShortageAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SecurityShortageAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link SecurityShortageAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SecurityShortageAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SecurityShortageAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SecurityShortageAccountRow )
                return new SecurityShortageAccountDao( (SecurityShortageAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SecurityShortageAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SecurityShortageAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SecurityShortageAccountRow}オブジェクト 
    */
    protected SecurityShortageAccountDao( SecurityShortageAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SecurityShortageAccountRow}オブジェクトを取得します。
   */
    public SecurityShortageAccountRow getSecurityShortageAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link SecurityShortageAccountRow}オブジェクトから{@@link SecurityShortageAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link SecurityShortageAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SecurityShortageAccountDao}取得のために指定の{@@link SecurityShortageAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SecurityShortageAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SecurityShortageAccountDao forRow( SecurityShortageAccountRow row ) throws java.lang.IllegalArgumentException {
        return (SecurityShortageAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SecurityShortageAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SecurityShortageAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SecurityShortageAccountPK}やデータベースレコードとして挿入される{@@link SecurityShortageAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SecurityShortageAccountRow.TYPE );
    }


  /** 
   * {@@link SecurityShortageAccountRow}を一意に特定する{@@link SecurityShortageAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SecurityShortageAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SecurityShortageAccountParams}オブジェクトの主キーとして利用可能な{@@link SecurityShortageAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SecurityShortageAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SecurityShortageAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SecurityShortageAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SecurityShortageAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SecurityShortageAccountRow findRowByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountPK pk = new SecurityShortageAccountPK( p_accountId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSecurityShortageAccountPKオブジェクトから{@@link SecurityShortageAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSecurityShortageAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SecurityShortageAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SecurityShortageAccountRow findRowByPk( SecurityShortageAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SecurityShortageAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(SecurityShortageAccountRow)}を使用してください。 
   */
    public static SecurityShortageAccountDao findDaoByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountPK pk = new SecurityShortageAccountPK( p_accountId );
        SecurityShortageAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SecurityShortageAccountPK)}および{@@link #forRow(SecurityShortageAccountRow)}を使用してください。 
   */
    public static SecurityShortageAccountDao findDaoByPk( SecurityShortageAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountRow row = findRowByPk( pk );
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
   * p_accountId, and にて指定の値から一意の{@@link SecurityShortageAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, and の値と一致する{@@link SecurityShortageAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SecurityShortageAccountRow findRowByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SecurityShortageAccountRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SecurityShortageAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SecurityShortageAccountDao.findRowsByAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountId(long)}および{@@link #forRow(SecurityShortageAccountRow)}を使用してください。 
   */
    public static SecurityShortageAccountDao findDaoByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountId( p_accountId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
