head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.43.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiSetupDao.java;


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
 * {@@link SrvRegiSetupDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiSetupRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiSetupPK 
 * @@see SrvRegiSetupRow 
 */
public class SrvRegiSetupDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiSetupDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiSetupRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiSetupRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiSetupDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiSetupDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiSetupRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiSetupRow )
                return new SrvRegiSetupDao( (SrvRegiSetupRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiSetupRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiSetupRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiSetupRow}オブジェクト 
    */
    protected SrvRegiSetupDao( SrvRegiSetupRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiSetupRow}オブジェクトを取得します。
   */
    public SrvRegiSetupRow getSrvRegiSetupRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiSetupRow}オブジェクトから{@@link SrvRegiSetupDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiSetupRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiSetupDao}取得のために指定の{@@link SrvRegiSetupRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiSetupDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiSetupDao forRow( SrvRegiSetupRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiSetupDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiSetupRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiSetupRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiSetupPK}やデータベースレコードとして挿入される{@@link SrvRegiSetupParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiSetupRow.TYPE );
    }


  /** 
   * {@@link SrvRegiSetupRow}を一意に特定する{@@link SrvRegiSetupPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiSetupRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiSetupParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiSetupPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiSetupPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiSetupRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiSetupRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiSetupRow findRowByPk( String p_institutionCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiSetupPK pk = new SrvRegiSetupPK( p_institutionCode, p_srvDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiSetupPKオブジェクトから{@@link SrvRegiSetupRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiSetupPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiSetupRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiSetupRow findRowByPk( SrvRegiSetupPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiSetupRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(SrvRegiSetupRow)}を使用してください。 
   */
    public static SrvRegiSetupDao findDaoByPk( String p_institutionCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiSetupPK pk = new SrvRegiSetupPK( p_institutionCode, p_srvDiv );
        SrvRegiSetupRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiSetupPK)}および{@@link #forRow(SrvRegiSetupRow)}を使用してください。 
   */
    public static SrvRegiSetupDao findDaoByPk( SrvRegiSetupPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiSetupRow row = findRowByPk( pk );
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
   * p_institutionCode, p_srvDiv, and にて指定の値から一意の{@@link SrvRegiSetupRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_srvDiv, and の値と一致する{@@link SrvRegiSetupRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiSetupRow findRowByInstitutionCodeSrvDiv( String p_institutionCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiSetupRow.TYPE,
            "institution_code=? and srv_div=?",
            null,
            new Object[] { p_institutionCode, p_srvDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiSetupRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiSetupDao.findRowsByInstitutionCodeSrvDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeSrvDiv(String, String)}および{@@link #forRow(SrvRegiSetupRow)}を使用してください。 
   */
    public static SrvRegiSetupDao findDaoByInstitutionCodeSrvDiv( String p_institutionCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSrvDiv( p_institutionCode, p_srvDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
