head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MailAddressRegiDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MailAddressRegiDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MailAddressRegiRow}インスタンスへ関連付けることができます。 
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
 * @@see MailAddressRegiPK 
 * @@see MailAddressRegiRow 
 */
public class MailAddressRegiDao extends DataAccessObject {


  /** 
   * この{@@link MailAddressRegiDao}に関連する型指定のRowオブジェクト 
   */
    private MailAddressRegiRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MailAddressRegiRow}と仮定される{@@link DataAccessObject}から新たに{@@link MailAddressRegiDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MailAddressRegiDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MailAddressRegiRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MailAddressRegiRow )
                return new MailAddressRegiDao( (MailAddressRegiRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MailAddressRegiRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MailAddressRegiRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MailAddressRegiRow}オブジェクト 
    */
    protected MailAddressRegiDao( MailAddressRegiRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MailAddressRegiRow}オブジェクトを取得します。
   */
    public MailAddressRegiRow getMailAddressRegiRow() {
        return row;
    }


  /** 
   * 指定の{@@link MailAddressRegiRow}オブジェクトから{@@link MailAddressRegiDao}オブジェクトを作成します。 
   * これは実際の{@@link MailAddressRegiRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MailAddressRegiDao}取得のために指定の{@@link MailAddressRegiRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MailAddressRegiDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MailAddressRegiDao forRow( MailAddressRegiRow row ) throws java.lang.IllegalArgumentException {
        return (MailAddressRegiDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MailAddressRegiRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MailAddressRegiRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MailAddressRegiPK}やデータベースレコードとして挿入される{@@link MailAddressRegiParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MailAddressRegiRow.TYPE );
    }


  /** 
   * {@@link MailAddressRegiRow}を一意に特定する{@@link MailAddressRegiPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MailAddressRegiRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MailAddressRegiParams}オブジェクトの主キーとして利用可能な{@@link MailAddressRegiPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MailAddressRegiPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new MailAddressRegiPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MailAddressRegiRow}オブジェクトを検索します。 
   * 
   * @@param p_mailAddressRegiId 検索対象であるp_mailAddressRegiIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailAddressRegiRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailAddressRegiRow findRowByPk( long p_mailAddressRegiId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailAddressRegiPK pk = new MailAddressRegiPK( p_mailAddressRegiId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMailAddressRegiPKオブジェクトから{@@link MailAddressRegiRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMailAddressRegiPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailAddressRegiRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailAddressRegiRow findRowByPk( MailAddressRegiPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MailAddressRegiRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(MailAddressRegiRow)}を使用してください。 
   */
    public static MailAddressRegiDao findDaoByPk( long p_mailAddressRegiId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailAddressRegiPK pk = new MailAddressRegiPK( p_mailAddressRegiId );
        MailAddressRegiRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MailAddressRegiPK)}および{@@link #forRow(MailAddressRegiRow)}を使用してください。 
   */
    public static MailAddressRegiDao findDaoByPk( MailAddressRegiPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MailAddressRegiRow row = findRowByPk( pk );
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
   * p_mailAddressRegiId, and にて指定の値から一意の{@@link MailAddressRegiRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_mailAddressRegiId 検索対象であるp_mailAddressRegiIdフィールドの値
   * 
   * @@return 引数指定のp_mailAddressRegiId, and の値と一致する{@@link MailAddressRegiRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MailAddressRegiRow findRowByMailAddressRegiId( long p_mailAddressRegiId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MailAddressRegiRow.TYPE,
            "mail_address_regi_id=?",
            null,
            new Object[] { new Long(p_mailAddressRegiId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MailAddressRegiRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MailAddressRegiDao.findRowsByMailAddressRegiId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMailAddressRegiId(long)}および{@@link #forRow(MailAddressRegiRow)}を使用してください。 
   */
    public static MailAddressRegiDao findDaoByMailAddressRegiId( long p_mailAddressRegiId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMailAddressRegiId( p_mailAddressRegiId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
