head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadTempDao.java;


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
 * {@@link AdministratorUploadTempDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AdministratorUploadTempRow}インスタンスへ関連付けることができます。 
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
 * @@see AdministratorUploadTempPK 
 * @@see AdministratorUploadTempRow 
 */
public class AdministratorUploadTempDao extends DataAccessObject {


  /** 
   * この{@@link AdministratorUploadTempDao}に関連する型指定のRowオブジェクト 
   */
    private AdministratorUploadTempRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AdministratorUploadTempRow}と仮定される{@@link DataAccessObject}から新たに{@@link AdministratorUploadTempDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AdministratorUploadTempDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AdministratorUploadTempRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdministratorUploadTempRow )
                return new AdministratorUploadTempDao( (AdministratorUploadTempRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdministratorUploadTempRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdministratorUploadTempRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AdministratorUploadTempRow}オブジェクト 
    */
    protected AdministratorUploadTempDao( AdministratorUploadTempRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AdministratorUploadTempRow}オブジェクトを取得します。
   */
    public AdministratorUploadTempRow getAdministratorUploadTempRow() {
        return row;
    }


  /** 
   * 指定の{@@link AdministratorUploadTempRow}オブジェクトから{@@link AdministratorUploadTempDao}オブジェクトを作成します。 
   * これは実際の{@@link AdministratorUploadTempRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AdministratorUploadTempDao}取得のために指定の{@@link AdministratorUploadTempRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AdministratorUploadTempDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AdministratorUploadTempDao forRow( AdministratorUploadTempRow row ) throws java.lang.IllegalArgumentException {
        return (AdministratorUploadTempDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdministratorUploadTempRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AdministratorUploadTempRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AdministratorUploadTempPK}やデータベースレコードとして挿入される{@@link AdministratorUploadTempParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdministratorUploadTempRow.TYPE );
    }


  /** 
   * {@@link AdministratorUploadTempRow}を一意に特定する{@@link AdministratorUploadTempPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AdministratorUploadTempRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AdministratorUploadTempParams}オブジェクトの主キーとして利用可能な{@@link AdministratorUploadTempPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AdministratorUploadTempPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AdministratorUploadTempRow}オブジェクトを検索します。 
   * 
   * @@param p_administratorUploadId 検索対象であるp_administratorUploadIdフィールドの値
   * @@param p_lineNumber 検索対象であるp_lineNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdministratorUploadTempRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdministratorUploadTempRow findRowByPk( long p_administratorUploadId, int p_lineNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadTempPK pk = new AdministratorUploadTempPK( p_administratorUploadId, p_lineNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAdministratorUploadTempPKオブジェクトから{@@link AdministratorUploadTempRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAdministratorUploadTempPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdministratorUploadTempRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdministratorUploadTempRow findRowByPk( AdministratorUploadTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdministratorUploadTempRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,int)}および{@@link #forRow(AdministratorUploadTempRow)}を使用してください。 
   */
    public static AdministratorUploadTempDao findDaoByPk( long p_administratorUploadId, int p_lineNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadTempPK pk = new AdministratorUploadTempPK( p_administratorUploadId, p_lineNumber );
        AdministratorUploadTempRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AdministratorUploadTempPK)}および{@@link #forRow(AdministratorUploadTempRow)}を使用してください。 
   */
    public static AdministratorUploadTempDao findDaoByPk( AdministratorUploadTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadTempRow row = findRowByPk( pk );
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
   * p_administratorUploadId, p_lineNumber, and にて指定の値から一意の{@@link AdministratorUploadTempRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_administratorUploadId 検索対象であるp_administratorUploadIdフィールドの値
   * @@param p_lineNumber 検索対象であるp_lineNumberフィールドの値
   * 
   * @@return 引数指定のp_administratorUploadId, p_lineNumber, and の値と一致する{@@link AdministratorUploadTempRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AdministratorUploadTempRow findRowByAdministratorUploadIdLineNumber( long p_administratorUploadId, int p_lineNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorUploadTempRow.TYPE,
            "administrator_upload_id=? and line_number=?",
            null,
            new Object[] { new Long(p_administratorUploadId), new Integer(p_lineNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorUploadTempRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorUploadTempDao.findRowsByAdministratorUploadIdLineNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAdministratorUploadIdLineNumber(long, int)}および{@@link #forRow(AdministratorUploadTempRow)}を使用してください。 
   */
    public static AdministratorUploadTempDao findDaoByAdministratorUploadIdLineNumber( long p_administratorUploadId, int p_lineNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAdministratorUploadIdLineNumber( p_administratorUploadId, p_lineNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
