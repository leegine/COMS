head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.39.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	SystemPreferencesDao.java;


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
 * {@@link SystemPreferencesDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SystemPreferencesRow}インスタンスへ関連付けることができます。 
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
 * @@see SystemPreferencesPK 
 * @@see SystemPreferencesRow 
 */
public class SystemPreferencesDao extends DataAccessObject {


  /** 
   * この{@@link SystemPreferencesDao}に関連する型指定のRowオブジェクト 
   */
    private SystemPreferencesRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SystemPreferencesRow}と仮定される{@@link DataAccessObject}から新たに{@@link SystemPreferencesDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SystemPreferencesDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SystemPreferencesRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SystemPreferencesRow )
                return new SystemPreferencesDao( (SystemPreferencesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SystemPreferencesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SystemPreferencesRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SystemPreferencesRow}オブジェクト 
    */
    protected SystemPreferencesDao( SystemPreferencesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SystemPreferencesRow}オブジェクトを取得します。
   */
    public SystemPreferencesRow getSystemPreferencesRow() {
        return row;
    }


  /** 
   * 指定の{@@link SystemPreferencesRow}オブジェクトから{@@link SystemPreferencesDao}オブジェクトを作成します。 
   * これは実際の{@@link SystemPreferencesRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SystemPreferencesDao}取得のために指定の{@@link SystemPreferencesRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SystemPreferencesDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SystemPreferencesDao forRow( SystemPreferencesRow row ) throws java.lang.IllegalArgumentException {
        return (SystemPreferencesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SystemPreferencesRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SystemPreferencesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SystemPreferencesPK}やデータベースレコードとして挿入される{@@link SystemPreferencesParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SystemPreferencesRow.TYPE );
    }


  /** 
   * {@@link SystemPreferencesRow}を一意に特定する{@@link SystemPreferencesPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SystemPreferencesRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SystemPreferencesParams}オブジェクトの主キーとして利用可能な{@@link SystemPreferencesPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SystemPreferencesPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SystemPreferencesRow}オブジェクトを検索します。 
   * 
   * @@param p_name 検索対象であるp_nameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SystemPreferencesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SystemPreferencesRow findRowByPk( String p_name ) throws DataFindException, DataQueryException, DataNetworkException {
        SystemPreferencesPK pk = new SystemPreferencesPK( p_name );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSystemPreferencesPKオブジェクトから{@@link SystemPreferencesRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSystemPreferencesPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SystemPreferencesRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SystemPreferencesRow findRowByPk( SystemPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SystemPreferencesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(SystemPreferencesRow)}を使用してください。 
   */
    public static SystemPreferencesDao findDaoByPk( String p_name ) throws DataFindException, DataQueryException, DataNetworkException {
        SystemPreferencesPK pk = new SystemPreferencesPK( p_name );
        SystemPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SystemPreferencesPK)}および{@@link #forRow(SystemPreferencesRow)}を使用してください。 
   */
    public static SystemPreferencesDao findDaoByPk( SystemPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SystemPreferencesRow row = findRowByPk( pk );
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
   * p_name, and にて指定の値から一意の{@@link SystemPreferencesRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_name 検索対象であるp_nameフィールドの値
   * 
   * @@return 引数指定のp_name, and の値と一致する{@@link SystemPreferencesRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SystemPreferencesRow findRowByName( String p_name ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SystemPreferencesRow.TYPE,
            "name=?",
            null,
            new Object[] { p_name } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SystemPreferencesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SystemPreferencesDao.findRowsByName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByName(String)}および{@@link #forRow(SystemPreferencesRow)}を使用してください。 
   */
    public static SystemPreferencesDao findDaoByName( String p_name ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByName( p_name ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
