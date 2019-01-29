head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブルデータマネージャ(WEB3AdminDirSecHostTableDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 呉艶飛 (中訊) 新規作成
*/

package webbroker3.dirsec;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CreatedTimestampDivDef;
import webbroker3.common.define.WEB3OrderRequestNumberDivDef;
import webbroker3.dirsec.data.HostManagementDao;
import webbroker3.dirsec.data.HostManagementParams;
import webbroker3.dirsec.data.HostManagementRow;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (キューテーブルデータマネージャ)<BR>
 * キューテーブルのレコード管理をおこなうクラス。<BR>
 * <BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableDataManager 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableDataManager.class);
    
    /**
     * @@roseuid 442A1C840196
     */
    public WEB3AdminDirSecHostTableDataManager() 
    {
     
    }
    
    /**
     * (createキューテーブル一覧情報)<BR>
     * キューテーブル管理Paramsより、キューテーブル一覧情報を<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成。<BR>
     * <BR>
     * ２）　@引数:キューテーブル一覧Listの要素分、Loop処理をおこなう。<BR>
     * <BR>
     * 　@２－１）　@キューテーブル一覧クラスのオブジェクトを生成する。<BR>
     * <BR>
     * 　@２－２）　@２－１）で生成したオブジェクトに以下の内容をセットする。<BR>
     * <BR>
     * 　@　@　@　@　@・　@キューテーブル一覧オブジェクト.テーブル名<BR>
     *　@　@　@　@　@　@　@　@= 引数:キューテーブル一覧List.get( i ).getテーブル名()。<BR>
     * <BR>
     * 　@　@　@　@　@・　@キューテーブル一覧オブジェクト.テーブル物理名<BR>
     *　@　@　@　@　@　@　@　@= 引数:キューテーブル一覧List.get( i ).getテーブル物理名()。<BR>
     * <BR>
     * 　@２－３）　@１）で生成したArrayListオブジェクトにキューテーブル一覧<BR>
     *　@　@　@　@　@　@　@　@　@オブジェクトをadd()する。<BR>
     * <BR>
     * ３）　@キューテーブル一覧クラス型の配列オブジェクトをArrayListオブジェクト<BR>
     *　@　@　@　@のサイズで生成する。<BR>
     * <BR>
     * ４）　@toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。 <BR>
     * <BR>
     * 　@　@ArrayListオブジェクト.toArray(キューテーブル一覧クラス型の配列オブジェクト); <BR>
     * <BR>
     * ５）　@変換した配列オブジェクトを返却する。 <BR>
     * @@param l_lisHostTableList - (キューテーブル一覧List)<BR>
     * キューテーブル一覧List。<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableUnit[]
     * @@roseuid 44190A94027D
     */
    public WEB3AdminDirSecHostTableUnit[] createHostTableListInfo(List l_lisHostTableList) 
    {
        final String STR_METHOD_NAME = " createHostTableListInfo(List) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListオブジェクトの生成。
        List l_lisHostTableUnit = new ArrayList();
        
        //２）　@引数:キューテーブル一覧Listの要素分、Loop処理をおこなう。
        int l_intTableListLength = 0;
        if (l_lisHostTableList != null)
        {
            l_intTableListLength = l_lisHostTableList.size();
        }
        for (int i = 0; i < l_intTableListLength; i++)
        {
            //２－１）　@キューテーブル一覧クラスのオブジェクトを生成する。
            WEB3AdminDirSecHostTableUnit l_hostTableUnit = new WEB3AdminDirSecHostTableUnit();
            HostManagementParams l_params = (HostManagementParams)l_lisHostTableList.get(i);
            
            //キューテーブル一覧オブジェクト.テーブル名
            //  = 引数:キューテーブル一覧List.get( i ).getテーブル名()。
            l_hostTableUnit.tableJpnName = l_params.getHostTableName();
            
            //キューテーブル一覧オブジェクト.テーブル物理名
            //  = 引数:キューテーブル一覧List.get( i ).getテーブル物理名()。
            l_hostTableUnit.tableName = l_params.getHostTablePhysicsName();
            
            //２－３）　@１）で生成したArrayListオブジェクトにキューテーブル一覧
            // オブジェクトをadd()する。
            l_lisHostTableUnit.add(l_hostTableUnit);
            
        }
        
        //３）　@キューテーブル一覧クラス型の配列オブジェクトをArrayListオブジェクト
        //  のサイズで生成する。
        WEB3AdminDirSecHostTableUnit[] l_tableUnits = 
            new WEB3AdminDirSecHostTableUnit[l_lisHostTableUnit.size()];
        
        //４）　@toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。
        l_lisHostTableUnit.toArray(l_tableUnits);
        
        //５）　@変換した配列オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_tableUnits;
    }
    
    /**
     * (createキューテーブルレコード詳細)<BR>
     * キューテーブルレコードListより、キューテーブルレコード詳細型配列を作成する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成。<BR>
     * <BR>
     * ２）　@引数:キューテーブルレコードListの要素分、Loop処理をおこなう。<BR>
     * <BR>
     * 　@２－１）　@キューテーブルレコード詳細クラスのオブジェクトを生成する。<BR>
     * <BR>
     * 　@２－２）　@２－１）で生成したオブジェクトに以下の内容をセットする。<BR>
     * <BR>
     *  　@２－２－１）　@キューテーブルレコード詳細オブジェクト.証券会社コード = <BR>
     *　@　@　@　@　@　@引数:キューテーブルレコードList.get( i ).getColumn("institution_code")。<BR>
     * <BR>
     *  　@２－２－２）　@キューテーブルレコード詳細オブジェクト.部店コード = <BR>
     *　@　@　@　@　@　@引数:キューテーブルレコードList.get( i ).getColumn("branch_code")。<BR>
     * <BR>
     *  　@２－２－３）　@引数:識別コード有無フラグ == 1(有り)の場合、<BR>
     *  <BR>
     * 　@　@・　@キューテーブルレコード詳細オブジェクト.識別コード = <BR>
     *　@　@　@　@　@　@引数:キューテーブルレコードList.get（ i ）.getColumn("order_request_number")。<BR>
     * <BR>
     *  　@２－２－４）　@キューテーブルレコード詳細オブジェクト.更新前ステータス = <BR>
     *　@　@　@　@　@　@引数:キューテーブルレコードList.get( i ).getColumn("status")。<BR>
     * <BR>
     *  　@２－２－５）　@引数:作成日付有無フラグ == 1(有り)の場合、<BR>
     * <BR>　@
     * 　@  ・　@キューテーブルレコード詳細オブジェクト.作成日付 = <BR>
     *　@　@　@　@　@　@引数:キューテーブルレコードList.get( i ).getColumn("created_timestamp")。<BR>
     * <BR>
     * 　@２－３）　@１）で生成したArrayListオブジェクトにキューテーブルレコード<BR>
     *　@　@　@　@　@　@　@　@詳細オブジェクトをadd()する。<BR>
     * <BR>
     * ３）　@キューテーブルレコード詳細クラス型の配列オブジェクトをArrayListオブジェクト<BR>
     *　@　@　@　@のサイズで生成する。<BR>
     * <BR>
     * ４）　@toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。 <BR>
     * <BR>
     * 　@　@ArrayListオブジェクト.toArray(キューテーブルレコード詳細クラス型の配列オブジェクト); <BR>
     * <BR>
     * ５）　@変換した配列オブジェクトを返却する。 <BR>
     * <BR>
     * @@param l_lisHostTableList - (キューテーブルレコードList)<BR>
     * キューテーブルレコードList。<BR>
     * @@param l_strIdentityCodeFlag - (識別コード有無フラグ)<BR>
     * 識別コード有無フラグ。<BR>
     * <BR>
     * 0：無<BR>
     * 1：有り<BR>
     * @@param l_strCreateDateFlag - (作成日付有無フラグ)<BR>
     * 作成日付有無フラグ。<BR>
     * <BR>
     * 0：無<BR>
     * 1：有り<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableDetail[]
     * @@roseuid 442153AD0254
     */
    public WEB3AdminDirSecHostTableDetail[] createHostTableDetails(
        List l_lisHostTableList, 
        String l_strIdentityCodeFlag, 
        String l_strCreateDateFlag) 
    {
        final String STR_METHOD_NAME = " createHostTableDetails(List, String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListオブジェクトの生成。
        List l_lisHostTableDetail = new ArrayList();
        
        //２）　@引数:キューテーブルレコードListの要素分、Loop処理をおこなう。
        //２）　@引数:キューテーブル一覧Listの要素分、Loop処理をおこなう。
        int l_intTableListLength = 0;
        if (l_lisHostTableList != null)
        {
            l_intTableListLength = l_lisHostTableList.size();
        }
        for (int i = 0; i < l_intTableListLength; i++)
        {
            //２－１）　@キューテーブルレコード詳細クラスのオブジェクトを生成する。
            WEB3AdminDirSecHostTableDetail l_hostTableDetail = new WEB3AdminDirSecHostTableDetail();
            Row l_row = (Row)l_lisHostTableList.get(i);
            
            //２－２）　@２－１）で生成したオブジェクトに以下の内容をセットする。            
            //２－２－１）　@キューテーブルレコード詳細オブジェクト.証券会社コード = 
            //　@引数:キューテーブルレコードList.get( i ).getColumn("institution_code")。
            if(l_row.getColumn("institution_code") !=null){
            	l_hostTableDetail.institutionCode = 
            	l_row.getColumn("institution_code").toString();
            }
            else{
             	l_hostTableDetail.institutionCode = null;
            }
            
            //２－２－２）　@キューテーブルレコード詳細オブジェクト.部店コード = 
            //  引数:キューテーブルレコードList.get( i ).getColumn("branch_code")。
            if(l_row.getColumn("branch_code") != null){
            	l_hostTableDetail.branchCode = l_row.getColumn("branch_code").toString();
            }
            else{
            	l_hostTableDetail.branchCode = null;
            }
            
            //２－２－３）　@引数:識別コード有無フラグ == 1(有り)の場合、
            if (WEB3OrderRequestNumberDivDef.EXISTENCE.equals(l_strIdentityCodeFlag))
            {
                //・　@キューテーブルレコード詳細オブジェクト.識別コード = 
                //　@引数:キューテーブルレコードList.get（ i ）.getColumn("order_request_number")。
                if(l_row.getColumn("order_request_number") != null) {
                	l_hostTableDetail.identityCode = 
                	l_row.getColumn("order_request_number").toString();
                }
                else{
                	l_hostTableDetail.identityCode = null;
                }
            }
            
            //２－２－４）　@キューテーブルレコード詳細オブジェクト.更新前ステータス = 
            //　@引数:キューテーブルレコードList.get( i ).getColumn("status")。
            if(l_row.getColumn("status") != null){
            	l_hostTableDetail.beforeStatus = l_row.getColumn("status").toString();
            }
            else{
            	l_hostTableDetail.beforeStatus = null;
            }
            
            //２－２－５）　@引数:作成日付有無フラグ == 1(有り)の場合、
            //キューテーブルレコード詳細オブジェクト.作成日付 = 
            //　@引数:キューテーブルレコードList.get( i ).getColumn("created_timestamp")。
            if (WEB3CreatedTimestampDivDef.EXISTENCE.equals(l_strCreateDateFlag))
            {
                l_hostTableDetail.createDate = (Timestamp)l_row.getColumn("created_timestamp");
//                WEB3DateUtility.getDate((String) l_row.getColumn("created_timestamp"),"yyyyMMddhh24MI");
            }
            
            //２－３）　@１）で生成したArrayListオブジェクトにキューテーブルレコード
            //  詳細オブジェクトをadd()する。
            l_lisHostTableDetail.add(l_hostTableDetail);            
        }        
        //３）　@キューテーブルレコード詳細クラス型の配列オブジェクトをArrayListオブジェクト
        //　@のサイズで生成する。
        WEB3AdminDirSecHostTableDetail[] l_tableDetails = 
            new WEB3AdminDirSecHostTableDetail[l_lisHostTableDetail.size()];
        
        //４）　@toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。
        l_lisHostTableDetail.toArray(l_tableDetails);
        
        //５）　@変換した配列オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_tableDetails;
    }
    
    /**
     * (getステータスMap)<BR>
     * 更新するステータス値をMapオブジェクトに格納して返却する。<BR>
     * <BR>
     * １）　@Mapオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@引数:ステータスをMapオブジェクトに格納する。<BR>
     * 　@　@put("status" , 引数:ステータス)<BR>
     * <BR>
     * ３）　@Mapオブジェクトを返却する。<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス。<BR>
     * @@return Map
     * @@roseuid 4423453B02F0
     */
    public Map getStatusMap(String l_strStatus) 
    {
        final String STR_METHOD_NAME = " getStatusMap(String) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@Mapオブジェクトを生成する。
        Map l_mapStatus = new HashMap();
        
        //２）　@引数:ステータスをMapオブジェクトに格納する。
        //　@put("status" , 引数:ステータス)
        l_mapStatus.put("status", l_strStatus);
        
        log.exiting(STR_METHOD_NAME);
        return l_mapStatus;
    }
    
    /**
     * 指定されたテーブルのRowTypeを返却する。<BR>
     * <BR>
     * <BR>
     * １）　@new RowType(引数:テーブル物理名 , 引数:クエリプロセッサ名)を返却する。<BR>
     * @@param l_strTablePhysicsName - (テーブル物理名)<BR>
     * テーブル物理名。<BR>
     * @@param l_strQueryProcessorName - (クエリプロセッサ名)<BR>
     * クエリプロセッサ名。<BR>
     * @@return RowType
     * @@roseuid 44213F0B0060
     */
    public RowType getRowType(
        String l_strTablePhysicsName, 
        String l_strQueryProcessorName) 
    {
        final String STR_METHOD_NAME = " getRowType(String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@new RowType(引数:テーブル物理名 , 引数:クエリプロセッサ名)を返却する。
        log.exiting(STR_METHOD_NAME);
        return new RowType(l_strTablePhysicsName, l_strQueryProcessorName);
        
    }
    
    /**
     * (getキューテーブル管理)<BR>
     * 指定したキューテーブル名に該当する行をキューテーブル管理より検索する。 <BR>
     * <BR>
     * １）キューテーブル管理Dao.findRowByPk(引数:キューテーブル名)により、<BR>
     * 　@　@　@該当する行を取得する。<BR>
     * <BR>
     * ２）　@１）で取得した値を返却する。<BR>
     * @@param l_strHostTableName - (キューテーブル名)<BR>
     * キューテーブル名。<BR>
     * @@return HostManagementRow
     * @@throws WEB3BaseException
     * @@roseuid 4420FA110389
     */
    public HostManagementRow getHostTableManagement(String l_strHostTableName) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHostTableManagement(String) ";
        log.entering(STR_METHOD_NAME);        
        
        //１）キューテーブル管理Dao.findRowByPk(引数:キューテーブル名)により、
        //　@該当する行を取得する。
        try
        {
            HostManagementRow l_managementRow = 
                (HostManagementRow)HostManagementDao.findRowByPk(l_strHostTableName);
            
            //２）　@１）で取得した値を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_managementRow;
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (getキューテーブル一覧)<BR>
     * キューテーブル管理Paramsの一覧を返却する。 <BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ] <BR>
     * 　@　@arg0：　@キューテーブル管理のRowTypeオブジェクト。<BR>
     * 　@　@arg1：　@引数：検索条件<BR>
     * 　@　@arg2：　@引数：ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@引数：検索条件データ<BR>
     * <BR>
     * <BR>
     * ２）　@検索結果が0件の場合、エラーを返却する。<BR>
     * 　@　@　@　@エラーメッセージ「条件に該当するデータが存在しない。」<BR>
     * 　@　@　@　@（BUSINESS_ERROR_01037）<BR>
     * <BR>
     * ３） １）の検索結果を返却する。 <BR>
     * @@param l_strQueryCondition - (検索条件)<BR>
     * 検索条件文字列。<BR>
     * @@param l_strSortCondition - (ソート条件)<BR>
     * ソート条件文字列。<BR>
     * @@param l_objQueryCondDatas - (検索条件データ)<BR>
     * 検索条件データコンテナ。<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4418E5F90338
     */
    public List getHostTableList(
        String l_strQueryCondition, 
        String l_strSortCondition, 
        Object[] l_objQueryCondDatas 
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getHostTableList(String, String, Object[]) ";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords = new ArrayList();
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        // [doFindAllQuery()にセットするパラメータ] 
        // 　@　@arg0：　@キューテーブル管理のRowTypeオブジェクト。
        // 　@　@arg1：　@引数：検索条件
        // 　@　@arg2：　@引数：ソート条件
        // 　@　@arg3：　@null
        // 　@　@arg4：　@引数：検索条件データ
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostManagementRow.TYPE,
                l_strQueryCondition,
                l_strSortCondition,
                null,
                l_objQueryCondDatas
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@検索結果が0件の場合、エラーを返却する。
        // 　@　@　@　@エラーメッセージ「条件に該当するデータが存在しない。」
        //　@　@　@　@（BUSINESS_ERROR_01037）
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }
        
        //３） １）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }
    
    /**
     * (getキューテーブルレコード)<BR>
     * 指定したキューテーブルのレコードを検索条件をもとに取得する。<BR>
     * <BR>
     * <BR>
     * １）　@QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ] <BR>
     * 　@　@arg0：　@引数：キューテーブルRowType<BR>
     * 　@　@arg1：　@引数：検索条件<BR>
     * 　@　@arg2：　@引数：ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@引数：検索条件データ<BR>
     * <BR>
     * ２）　@検索結果が0件の場合、エラーを返却する。<BR>
     * 　@　@　@　@エラーメッセージ「条件に該当するデータが存在しない。」<BR>
     * 　@　@　@　@（BUSINESS_ERROR_01037）<BR>
     * <BR>
     * ３）　@１）で取得した値を返却する。<BR>
     * @@param l_strQueryCondition - (検索条件)<BR>
     * 検索条件。<BR>
     * @@param l_objQueryCondDatas - (検索条件データ)<BR>
     * 検索条件データコンテナ。<BR>
     * @@param l_hostTableRowType - (キューテーブルRowType)<BR>
     * キューテーブルRowType。<BR>
     * @@param l_strSortCondition - (ソート条件)<BR>
     * キューテーブルソート条件<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4422707E004E
     */
    public List getHostTableRecord(
        String l_strQueryCondition,
        Object[] l_objQueryCondDatas,
        RowType l_hostTableRowType,
        String l_strSortCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHostTableRecord(String, Object[], RowType, String) ";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords = new ArrayList();
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        // [doFindAllQuery()にセットするパラメータ] 
        // 　@　@arg0：　@引数：キューテーブルRowType
        // 　@　@arg1：　@引数：検索条件
        // 　@　@arg2：　@引数：ソート条件
        // 　@　@arg3：　@null
        // 　@　@arg4：　@引数：検索条件データ
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                l_hostTableRowType,
                l_strQueryCondition,
                l_strSortCondition,
                null,
                l_objQueryCondDatas
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@検索結果が0件の場合、エラーを返却する。
        // 　@　@　@　@エラーメッセージ「条件に該当するデータが存在しない。」
        //　@　@　@　@（BUSINESS_ERROR_01037）
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }
        
        //３） １）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }
    
    /**
     * (updateキューテーブルレコード)<BR>
     * 指定したキューテーブルの更新処理をおこなう。<BR>
     * <BR>
     * １）　@QueryProcessor.doUpdateAllQuery()メソッドをコールする。 <BR>
     * <BR>
     * [doUpdateAllQuery()にセットするパラメータ] <BR>
     * 　@　@arg0：　@引数：キューテーブルRowType<BR>
     * 　@　@arg1：　@引数：検索条件<BR>
     * 　@　@arg2：　@引数：検索条件データ<BR>
     * 　@　@arg3：　@引数：更新ステータス<BR>
     * <BR>
     * @@param l_strQueryCondition - (検索条件)<BR>
     * 検索条件文字列。<BR>
     * @@param l_objQueryCondDatas - (検索条件データ)<BR>
     * 検索条件データコンテナ。<BR>
     * @@param l_hostTableRowType - (キューテーブルRowType)<BR>
     * キューテーブルRowType。<BR>
     * @@param l_mapUpdateStatus - (更新ステータス)<BR>
     * 更新ステータスMapオブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44234A4301E6
     */
    public void updateHostTableRecord(
        String l_strQueryCondition, 
        Object[] l_objQueryCondDatas, 
        RowType l_hostTableRowType, 
        Map l_mapUpdateStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateHostTableRecord(String, Object[], RowType, Map) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@QueryProcessor.doUpdateAllQuery()メソッドをコールする。 
        // [doUpdateAllQuery()にセットするパラメータ] 
        // 　@　@arg0：　@引数：キューテーブルRowType
        // 　@　@arg1：　@引数：検索条件
        // 　@　@arg2：　@引数：検索条件データ
        // 　@　@arg3：　@引数：更新ステータス
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                l_hostTableRowType,
                l_strQueryCondition,
                l_objQueryCondDatas,
                l_mapUpdateStatus
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
