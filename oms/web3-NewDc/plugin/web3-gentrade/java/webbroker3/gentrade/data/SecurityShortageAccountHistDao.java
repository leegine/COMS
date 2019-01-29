head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SecurityShortageAccountHistDao.java;


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
 * {@@link SecurityShortageAccountHistDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SecurityShortageAccountHistRow}インスタンスへ関連付けることができます。 
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
 * @@see SecurityShortageAccountHistPK 
 * @@see SecurityShortageAccountHistRow 
 */
public class SecurityShortageAccountHistDao extends DataAccessObject {


  /** 
   * この{@@link SecurityShortageAccountHistDao}に関連する型指定のRowオブジェクト 
   */
    private SecurityShortageAccountHistRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SecurityShortageAccountHistRow}と仮定される{@@link DataAccessObject}から新たに{@@link SecurityShortageAccountHistDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SecurityShortageAccountHistDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SecurityShortageAccountHistRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SecurityShortageAccountHistRow )
                return new SecurityShortageAccountHistDao( (SecurityShortageAccountHistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SecurityShortageAccountHistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SecurityShortageAccountHistRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SecurityShortageAccountHistRow}オブジェクト 
    */
    protected SecurityShortageAccountHistDao( SecurityShortageAccountHistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SecurityShortageAccountHistRow}オブジェクトを取得します。
   */
    public SecurityShortageAccountHistRow getSecurityShortageAccountHistRow() {
        return row;
    }


  /** 
   * 指定の{@@link SecurityShortageAccountHistRow}オブジェクトから{@@link SecurityShortageAccountHistDao}オブジェクトを作成します。 
   * これは実際の{@@link SecurityShortageAccountHistRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SecurityShortageAccountHistDao}取得のために指定の{@@link SecurityShortageAccountHistRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SecurityShortageAccountHistDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SecurityShortageAccountHistDao forRow( SecurityShortageAccountHistRow row ) throws java.lang.IllegalArgumentException {
        return (SecurityShortageAccountHistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SecurityShortageAccountHistRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SecurityShortageAccountHistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SecurityShortageAccountHistPK}やデータベースレコードとして挿入される{@@link SecurityShortageAccountHistParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SecurityShortageAccountHistRow.TYPE );
    }


  /** 
   * {@@link SecurityShortageAccountHistRow}を一意に特定する{@@link SecurityShortageAccountHistPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SecurityShortageAccountHistRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SecurityShortageAccountHistParams}オブジェクトの主キーとして利用可能な{@@link SecurityShortageAccountHistPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SecurityShortageAccountHistPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SecurityShortageAccountHistRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_procDate 検索対象であるp_procDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SecurityShortageAccountHistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SecurityShortageAccountHistRow findRowByPk( long p_accountId, String p_procDate ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountHistPK pk = new SecurityShortageAccountHistPK( p_accountId, p_procDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSecurityShortageAccountHistPKオブジェクトから{@@link SecurityShortageAccountHistRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSecurityShortageAccountHistPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SecurityShortageAccountHistRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SecurityShortageAccountHistRow findRowByPk( SecurityShortageAccountHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SecurityShortageAccountHistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(SecurityShortageAccountHistRow)}を使用してください。 
   */
    public static SecurityShortageAccountHistDao findDaoByPk( long p_accountId, String p_procDate ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountHistPK pk = new SecurityShortageAccountHistPK( p_accountId, p_procDate );
        SecurityShortageAccountHistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SecurityShortageAccountHistPK)}および{@@link #forRow(SecurityShortageAccountHistRow)}を使用してください。 
   */
    public static SecurityShortageAccountHistDao findDaoByPk( SecurityShortageAccountHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountHistRow row = findRowByPk( pk );
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
   * p_accountId, p_procDate, and にて指定の値から一意の{@@link SecurityShortageAccountHistRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_procDate 検索対象であるp_procDateフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_procDate, and の値と一致する{@@link SecurityShortageAccountHistRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SecurityShortageAccountHistRow findRowByAccountIdProcDate( long p_accountId, String p_procDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SecurityShortageAccountHistRow.TYPE,
            "account_id=? and proc_date=?",
            null,
            new Object[] { new Long(p_accountId), p_procDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SecurityShortageAccountHistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SecurityShortageAccountHistDao.findRowsByAccountIdProcDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdProcDate(long, String)}および{@@link #forRow(SecurityShortageAccountHistRow)}を使用してください。 
   */
    public static SecurityShortageAccountHistDao findDaoByAccountIdProcDate( long p_accountId, String p_procDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdProcDate( p_accountId, p_procDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
