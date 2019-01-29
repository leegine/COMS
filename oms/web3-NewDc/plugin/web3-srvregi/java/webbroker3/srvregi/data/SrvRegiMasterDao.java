head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.41.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiMasterDao.java;


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
 * {@@link SrvRegiMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiMasterPK 
 * @@see SrvRegiMasterRow 
 */
public class SrvRegiMasterDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiMasterDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiMasterRow )
                return new SrvRegiMasterDao( (SrvRegiMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiMasterRow}オブジェクト 
    */
    protected SrvRegiMasterDao( SrvRegiMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiMasterRow}オブジェクトを取得します。
   */
    public SrvRegiMasterRow getSrvRegiMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiMasterRow}オブジェクトから{@@link SrvRegiMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiMasterDao}取得のために指定の{@@link SrvRegiMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiMasterDao forRow( SrvRegiMasterRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiMasterPK}やデータベースレコードとして挿入される{@@link SrvRegiMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiMasterRow.TYPE );
    }


  /** 
   * {@@link SrvRegiMasterRow}を一意に特定する{@@link SrvRegiMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiMasterParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiMasterRow findRowByPk( String p_institutionCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiMasterPK pk = new SrvRegiMasterPK( p_institutionCode, p_srvDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiMasterPKオブジェクトから{@@link SrvRegiMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiMasterRow findRowByPk( SrvRegiMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(SrvRegiMasterRow)}を使用してください。 
   */
    public static SrvRegiMasterDao findDaoByPk( String p_institutionCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiMasterPK pk = new SrvRegiMasterPK( p_institutionCode, p_srvDiv );
        SrvRegiMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiMasterPK)}および{@@link #forRow(SrvRegiMasterRow)}を使用してください。 
   */
    public static SrvRegiMasterDao findDaoByPk( SrvRegiMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_srvDiv, and にて指定の値から一意の{@@link SrvRegiMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_srvDiv, and の値と一致する{@@link SrvRegiMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiMasterRow findRowByInstitutionCodeSrvDiv( String p_institutionCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiMasterRow.TYPE,
            "institution_code=? and srv_div=?",
            null,
            new Object[] { p_institutionCode, p_srvDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiMasterDao.findRowsByInstitutionCodeSrvDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeSrvDiv(String, String)}および{@@link #forRow(SrvRegiMasterRow)}を使用してください。 
   */
    public static SrvRegiMasterDao findDaoByInstitutionCodeSrvDiv( String p_institutionCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSrvDiv( p_institutionCode, p_srvDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
