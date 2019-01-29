head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiConsDocDao.java;


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
 * {@@link SrvRegiConsDocDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiConsDocRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiConsDocPK 
 * @@see SrvRegiConsDocRow 
 */
public class SrvRegiConsDocDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiConsDocDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiConsDocRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiConsDocRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiConsDocDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiConsDocDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiConsDocRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiConsDocRow )
                return new SrvRegiConsDocDao( (SrvRegiConsDocRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiConsDocRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiConsDocRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiConsDocRow}オブジェクト 
    */
    protected SrvRegiConsDocDao( SrvRegiConsDocRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiConsDocRow}オブジェクトを取得します。
   */
    public SrvRegiConsDocRow getSrvRegiConsDocRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiConsDocRow}オブジェクトから{@@link SrvRegiConsDocDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiConsDocRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiConsDocDao}取得のために指定の{@@link SrvRegiConsDocRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiConsDocDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiConsDocDao forRow( SrvRegiConsDocRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiConsDocDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiConsDocRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiConsDocRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiConsDocPK}やデータベースレコードとして挿入される{@@link SrvRegiConsDocParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiConsDocRow.TYPE );
    }


  /** 
   * {@@link SrvRegiConsDocRow}を一意に特定する{@@link SrvRegiConsDocPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiConsDocRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiConsDocParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiConsDocPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiConsDocPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiConsDocRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_lineNumber 検索対象であるp_lineNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiConsDocRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiConsDocRow findRowByPk( String p_institutionCode, String p_srvDiv, long p_lineNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiConsDocPK pk = new SrvRegiConsDocPK( p_institutionCode, p_srvDiv, p_lineNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiConsDocPKオブジェクトから{@@link SrvRegiConsDocRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiConsDocPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiConsDocRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiConsDocRow findRowByPk( SrvRegiConsDocPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiConsDocRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,long)}および{@@link #forRow(SrvRegiConsDocRow)}を使用してください。 
   */
    public static SrvRegiConsDocDao findDaoByPk( String p_institutionCode, String p_srvDiv, long p_lineNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiConsDocPK pk = new SrvRegiConsDocPK( p_institutionCode, p_srvDiv, p_lineNumber );
        SrvRegiConsDocRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiConsDocPK)}および{@@link #forRow(SrvRegiConsDocRow)}を使用してください。 
   */
    public static SrvRegiConsDocDao findDaoByPk( SrvRegiConsDocPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiConsDocRow row = findRowByPk( pk );
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
   * p_institutionCode, p_srvDiv, p_lineNumber, and にて指定の値から一意の{@@link SrvRegiConsDocRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_lineNumber 検索対象であるp_lineNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_srvDiv, p_lineNumber, and の値と一致する{@@link SrvRegiConsDocRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiConsDocRow findRowByInstitutionCodeSrvDivLineNumber( String p_institutionCode, String p_srvDiv, long p_lineNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiConsDocRow.TYPE,
            "institution_code=? and srv_div=? and line_number=?",
            null,
            new Object[] { p_institutionCode, p_srvDiv, new Long(p_lineNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiConsDocRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiConsDocDao.findRowsByInstitutionCodeSrvDivLineNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeSrvDivLineNumber(String, String, long)}および{@@link #forRow(SrvRegiConsDocRow)}を使用してください。 
   */
    public static SrvRegiConsDocDao findDaoByInstitutionCodeSrvDivLineNumber( String p_institutionCode, String p_srvDiv, long p_lineNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSrvDivLineNumber( p_institutionCode, p_srvDiv, p_lineNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
