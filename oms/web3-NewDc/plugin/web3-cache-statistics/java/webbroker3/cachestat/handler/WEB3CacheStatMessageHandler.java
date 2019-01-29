head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatMessageHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3CacheStatMessageHandlerクラス(WEB3CacheStatMessageHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/16 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import webbroker3.cachestat.define.WEB3CacheStatType;
import webbroker3.cachestat.message.WEB3CacheStatClearRequest;
import webbroker3.cachestat.message.WEB3CacheStatGetStatisticsRequest;
import webbroker3.cachestat.message.WEB3CacheStatGetStatisticsResponse;
import webbroker3.cachestat.message.WEB3CacheStatHitRateInfo;
import webbroker3.cachestat.message.WEB3CacheStatInvInfo;
import webbroker3.cachestat.message.WEB3CacheStatSizeInfo;
import webbroker3.cachestat.message.WEB3CacheStatSpecialCase;
import webbroker3.cachestat.message.WEB3CacheStatStartCollectingRequest;
import webbroker3.cachestat.message.WEB3CacheStatStopCollectingRequest;
import webbroker3.cachestat.message.WEB3CacheStatTimingInfo;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.impl.CacheStatistics;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.Response;

/**
 * キャッシュ統計メッセージハンドラ
 * 
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatMessageHandler implements MessageHandler
{

    /** ログユーティリティ */
    private static final WEB3LogUtility LOG = WEB3LogUtility
        .getInstance(WEB3CacheStatMessageHandler.class);
    
    /** デバック出力フラグ */
    private static final boolean DBG = LOG.ison();

    /** 数値フォーマットパターン */
    private static final String NF0 = "0";

    /** 数値フォーマットパターン */
    private static final String NF1 = "0.0";

    /** 数値フォーマットパターン */
    private static final String NF2 = "0.00";

    /** 数値フォーマットパターン */
    private static final String NF3 = "0.000";

    /** ヘルパークラス */
    private final WEB3CacheStatHelper helper = new WEB3CacheStatHelper();

    /**
     * キャッシュ統計の収集を開始する。
     * 
     * @@param l_request キャッシュ統計収集開始リクエスト
     */
    public Response startCollecting(
        WEB3CacheStatStartCollectingRequest l_request)
    {
        
        if (CacheStatistics.isCollecting())
        {
            
            LOG.warn("CacheStatistics already started.");
            
        } else
        {
            
            if (l_request.clearStat)
            {
                
                CacheStatistics.clear();
                
                LOG.info("CacheStatistics cleared.");
                
            }

            CacheStatistics.setCollecting(true);
            
            LOG.info("CacheStatistics start collecting.");
            
        }
        
        return new Response();

    }

    /**
     * キャッシュ統計の収集を終了する。
     * 
     * @@param l_request キャッシュ統計収集停止リクエスト
     */
    public Response stopCollecting(WEB3CacheStatStopCollectingRequest l_request)
    {
        
        if (!CacheStatistics.isCollecting())
        {
            
            LOG.warn("CacheStatistics is not started.");
            
        } else
        {
            
            CacheStatistics.setCollecting(false);
            
            LOG.info("CacheStatistics stop collecting.");
            
            if (l_request.clearStat)
            {
                
                CacheStatistics.clear();
                
                LOG.info("CacheStatistics cleared.");
                
            }

        }

        return new Response();

    }
    
    /**
     * キャッシュ統計をクリアする。
     * 
     * @@param l_request キャッシュ統計クリアリクエストメッセージ
     */
    public Response clear(WEB3CacheStatClearRequest l_request)
    {
        
        CacheStatistics.clear();
        
        LOG.info("CacheStatistics cleared.");
        
        return new Response();
        
    }

    /**
     * キャッシュ統計を取得する。
     * 
     * @@param l_request キャッシュ統計取得リクエストメッセージ
     * @@return キャッシュ統計取得レスポンスメッセージ
     */
    public Response getStastics(WEB3CacheStatGetStatisticsRequest l_request)
    {
        
        int l_intStatType = l_request.statType;
        String l_strTargetTableName = l_request.tableName;
        
        WEB3CacheStatSizeInfo[] l_sizeInfo = null;
        WEB3CacheStatHitRateInfo[] l_hitRateInfo = null;
        WEB3CacheStatInvInfo[] l_invInfo = null;
        WEB3CacheStatTimingInfo[] l_timingInfo = null;
        
        switch (l_intStatType)
        {
            case (WEB3CacheStatType.SIZE):
            {
                l_sizeInfo = getSizeInfo(l_strTargetTableName);
                break;
            }
            case (WEB3CacheStatType.HIT_RATE):
            {
                l_hitRateInfo = getHitRateInfo(l_strTargetTableName);
                break;
            }
            case (WEB3CacheStatType.INVALIDATION):
            {
                l_invInfo = getInvInfo(l_strTargetTableName);
                break;
            }
            case (WEB3CacheStatType.TIMING):
            {
                l_timingInfo = getTimingInfo(l_strTargetTableName);
                break;
            }
            default:
            {
                l_hitRateInfo = getHitRateInfo(l_strTargetTableName);
                l_sizeInfo = getSizeInfo(l_strTargetTableName);
                l_invInfo = getInvInfo(l_strTargetTableName);
                l_timingInfo = getTimingInfo(l_strTargetTableName);
            }
        }
        
        WEB3CacheStatGetStatisticsResponse l_response = 
            new WEB3CacheStatGetStatisticsResponse();
        l_response.statType = l_intStatType;
        l_response.tableName = l_strTargetTableName;
        l_response.sizeInfo = l_sizeInfo;
        l_response.hitRateInfo = l_hitRateInfo;
        l_response.invInfo = l_invInfo;
        l_response.timingInfo = l_timingInfo;
        
        return l_response;
        
    }

    /**
     * キャッシュ統計のサイズ情報を取得する。
     * 
     * 特定のテーブルの情報のみ取得する場合は、
     * パラメータ.l_strTargetTableNameに対象となるテーブルのテーブル名を
     * 設定する。
     * テーブル名が指定されない場合全テーブルの情報を取得する。
     * 
     * @@param l_strTargetTableName 取得対象テーブルのテーブル名
     * @@return サイズ情報
     */
    protected WEB3CacheStatSizeInfo[] getSizeInfo(String l_strTargetTableName)
    {

        WEB3CacheStatSizeInfo[] l_results = null;
        List l_sizeInfoList = new ArrayList();
        Map l_sizeMapMap = CacheStatistics.getSizeInfoMapMap();
        
        parseStatInfoMap(l_sizeInfoList, l_sizeMapMap, l_strTargetTableName);

        if (l_sizeInfoList.size() > 0)
        {
            l_results = 
                (WEB3CacheStatSizeInfo[]) l_sizeInfoList.toArray(
                    new WEB3CacheStatSizeInfo[l_sizeInfoList.size()]);
        }

        return l_results;

    }
    
    /**
     * キャッシュ統計のヒット率情報を取得する。
     * 
     * 特定のテーブルの情報のみ取得する場合は、
     * パラメータ.l_strTargetTableNameに対象となるテーブルのテーブル名を
     * 設定する。
     * テーブル名が指定されない場合全テーブルの情報を取得する。
     * 
     * @@param l_strTargetTableName 取得対象テーブルのテーブル名
     * @@return ヒット率情報
     */
    protected WEB3CacheStatHitRateInfo[] getHitRateInfo(String l_strTargetTableName)
    {

        WEB3CacheStatHitRateInfo[] l_results = null;
        List l_hitRateInfoList = new ArrayList();
        Map l_hitRateMapMap = CacheStatistics.getHitRatioMapMap();
        
        parseStatInfoMap(l_hitRateInfoList, l_hitRateMapMap, l_strTargetTableName);

        if (l_hitRateInfoList.size() > 0)
        {
            l_results = 
                (WEB3CacheStatHitRateInfo[]) l_hitRateInfoList.toArray(
                    new WEB3CacheStatHitRateInfo[l_hitRateInfoList.size()]);
        }

        return l_results;

    }
    
    /**
     * キャッシュ統計のインバリデーション情報を取得する。
     * 
     * 特定のテーブルの情報のみ取得する場合は、
     * パラメータ.l_strTargetTableNameに対象となるテーブルのテーブル名を
     * 設定する。
     * テーブル名が指定されない場合全テーブルの情報を取得する。
     * 
     * @@param l_strTargetTableName 取得対象テーブルのテーブル名
     * @@return インバリデーション率情報
     */
    protected WEB3CacheStatInvInfo[] getInvInfo(
        String l_strTargetTableName)
    {

        WEB3CacheStatInvInfo[] l_results = null;
        List l_invInfoList = new ArrayList();
        Map l_invMapMap = CacheStatistics.getInvalidationMapMap();
        
        parseStatInfoMap(l_invInfoList, l_invMapMap, l_strTargetTableName);
        
        if (l_invInfoList.size() > 0)
        {
            l_results = 
                (WEB3CacheStatInvInfo[]) l_invInfoList.toArray(
                    new WEB3CacheStatInvInfo[l_invInfoList.size()]);
        }

        return l_results;

    }
    
    /**
     * キャッシュ統計のタイミング情報を取得する。
     * 
     * 特定のテーブルの情報のみ取得する場合は、
     * パラメータ.l_strTargetTableNameに対象となるテーブルのテーブル名を
     * 設定する。
     * テーブル名が指定されない場合全テーブルの情報を取得する。
     * 
     * @@param l_strTargetTableName 取得対象テーブルのテーブル名
     * @@return タイミング情報
     */
    protected WEB3CacheStatTimingInfo[] getTimingInfo(
        String l_strTargetTableName)
    {
        
        WEB3CacheStatTimingInfo[] l_results = null;
        List l_timingInfoList = new ArrayList();
        Map l_timingMapMapMapMap = CacheStatistics.getTimingMapMapMapMap();
        
        parseStatInfoMap(l_timingInfoList, l_timingMapMapMapMap, l_strTargetTableName);
        
        if (l_timingInfoList.size() > 0)
        {
            l_results = 
                (WEB3CacheStatTimingInfo[]) l_timingInfoList.toArray(
                    new WEB3CacheStatTimingInfo[l_timingInfoList.size()]);
        }

        return l_results;

    }

    /**
     * キャッシュ統計情報を保持する<code>Map</code>をパースして、
     * 統計情報を取得する。
     * 
     * @@param l_results 取得した統計情報を保持する<code>List</code>
     * @@param l_statInfoMap キャッシュ統計情報を保持する<code>Map</code>
     * @@param l_strTargetTableName 対象テーブルのテーブル名
     */
    protected void parseStatInfoMap(List l_results, Map l_statInfoMap,
        String l_strTargetTableName)
    {

        if (l_statInfoMap == null || l_statInfoMap.isEmpty())
        {
            return;
        }

        LinkedList l_keys = new LinkedList();
        Map l_subStatInfoMap = getSubStatInfoMap(
            l_statInfoMap,
            l_strTargetTableName);
        
        if (l_strTargetTableName != null && l_subStatInfoMap == null)
        {
            return;
        }

        if (l_subStatInfoMap != null)
        {
            l_keys.add(l_strTargetTableName);
            l_statInfoMap = l_subStatInfoMap;
        }
        
        parseStatInfoMap(l_results, l_statInfoMap, l_keys);

    }
    
    /**
     * キャッシュ統計情報を保持する<code>Map</code>から
     * 指定したテーブル名の統計情報を取得する。
     * 
     * 指定したテーブル名の統計情報が存在しない場合、<code>null</code>を返す。
     * 
     * @@param l_statInfoMap キャッシュ統計情報を保持する<code>Map</code>
     * @@param l_strTargetTableName 対象テーブルのテーブル名
     * @@return 指定したテーブル名の統計情報を保持するマップ
     */
    private Map getSubStatInfoMap(Map l_statInfoMap, String l_strTargetTableName)
    {

        if (l_strTargetTableName == null)
        {
            return null;
        }

        Map l_subMap = null;
        Object l_objValue = null;

        l_objValue = l_statInfoMap.get(l_strTargetTableName);

        if (l_objValue == null)
        {
            l_objValue = l_statInfoMap.get(l_strTargetTableName.toLowerCase());
        }

        if (l_objValue == null)
        {
            l_objValue = l_statInfoMap.get(l_strTargetTableName.toUpperCase());
        }

        if (l_objValue != null && (l_objValue instanceof Map))
        {
            l_subMap = (Map) l_objValue;
        }

        return l_subMap;

    }
    
    /**
     * parseStatInfoMap(List, Map, LinkedList)を再帰的に呼び出して、
     * 統計情報を取得する。
     * 
     * @@param l_results 取得した統計情報を保持する<code>List</code>
     * @@param l_statInfoMap 対象テーブルのテーブル名
     * @@param l_keys 統計情報を保持する<code>Map</code>のキーを保持する<code>List</code>
     */
    private void parseStatInfoMap(List l_results, Map l_statInfoMap,
        LinkedList l_keys)
    {

        Iterator l_it = l_statInfoMap.entrySet().iterator();
        while (l_it.hasNext())
        {

            // 統計情報を保持するMapの要素を取得する。
            Map.Entry l_e = (Map.Entry) l_it.next();
            Object l_objKey = l_e.getKey();
            Object l_objValue = l_e.getValue();
            
            if (DBG)
            {
                LOG.debug("key=" + l_objKey);
            }
            
            // キーを保持するListに取得した要素のキーを設定する。
            l_keys.addLast(l_objKey);
            
            // 取得した要素から統計情報を作成する。
            Object l_objStatInfo = processStatInfo(l_keys, l_objValue);
            
            // 統計情報が作成された場合は、結果Listに統計情報を追加する。
            if (l_objStatInfo != null)
            {
                l_results.add(l_objStatInfo);
            } else
            {
                // 取得した要素がMapの場合は、再帰処理
                if (l_objValue instanceof Map)
                {
                    parseStatInfoMap(l_results, (Map) l_objValue, l_keys);
                }
            }
            
            // キーを保持するListから最後に追加した要素を削除する。
            l_keys.removeLast();

        }
        
    }
    
    /**
     * 統計情報を作成する。
     * 統計情報を作成できない場合は、<code>null</code>を返す。
     * 
     * @@param l_keys 統計情報を保持する<code>Map</code>のキーの<code>List</code>
     * @@param l_objValue 統計情報を保持する<code>Map</code>に設定されている値
     * @@return　@作成した統計情報
     */
    private Object processStatInfo(LinkedList l_keys, Object l_objValue)
    {

        Object l_objResult = null;

        if (l_objValue instanceof CacheStatistics.SizeInfo)
        {
            CacheStatistics.SizeInfo l_source = (CacheStatistics.SizeInfo) l_objValue;
            l_objResult = toSizeInfo(l_keys, l_source);
        } else if (l_objValue instanceof CacheStatistics.Tally)
        {
            CacheStatistics.Tally l_source = (CacheStatistics.Tally) l_objValue;
            l_objResult = toHitRateInfo(l_keys, l_source);
        } else if (l_objValue instanceof CacheStatistics.InvInfo)
        {
            CacheStatistics.InvInfo l_source = (CacheStatistics.InvInfo) l_objValue;
            l_objResult = toInvInfo(l_keys, l_source);
        } else if (l_objValue instanceof CacheStatistics.Summary)
        {
            CacheStatistics.Summary l_source = (CacheStatistics.Summary) l_objValue;
            l_objResult = toTimingInfo(l_keys, l_source);
        }

        if (DBG)
        {
            LOG.debug("value=" + l_objValue + ", result=" + l_objResult);
        }

        return l_objResult;

    }
    
    /**
     * サイズ情報を作成する。
     */
    private WEB3CacheStatSizeInfo toSizeInfo(List l_keys, CacheStatistics.SizeInfo l_source)
    {
        String l_strTableName = (String) l_keys.get(0);
        String l_strCacheType = (String) l_keys.get(1);
        return toSizeInfo(l_strTableName, l_strCacheType, l_source);
    }

    /**
     * サイズ情報を作成する。
     */
    private WEB3CacheStatSizeInfo toSizeInfo(String l_strTableName,
        String l_strCacheType, CacheStatistics.SizeInfo l_source)
    {

        long l_lngCapacity = l_source.getCapacity();
        double l_dblAvgCnt = l_source.getMean();
        double l_dblMaxCnt = l_source.getMaximum();
        double l_dblStdDev = l_source.getStandardDeviation();
        double l_dblUseRatio = l_dblAvgCnt / l_lngCapacity * 100.0;

        WEB3CacheStatSizeInfo l_sizeInfo = new WEB3CacheStatSizeInfo();
        l_sizeInfo.tableName = l_strTableName;
        l_sizeInfo.cacheType = l_strCacheType;
        l_sizeInfo.capacity = helper.format(l_lngCapacity, NF0);
        l_sizeInfo.averageCount = helper.format(l_dblAvgCnt, NF1);
        l_sizeInfo.maxCount = helper.format(l_dblMaxCnt, NF0);
        l_sizeInfo.standardDeviation = helper.format(l_dblStdDev, NF2);
        l_sizeInfo.useRate = helper.format(l_dblUseRatio, NF0);

        return l_sizeInfo;
        
    }
    
    /**
     * ヒット率情報を作成する。
     */
    private WEB3CacheStatHitRateInfo toHitRateInfo(List l_keys,
        CacheStatistics.Tally l_source)
    {
        String l_strTableName = (String) l_keys.get(0);
        String l_strCacheType = (String) l_keys.get(1);
        return toHitRateInfo(l_strTableName, l_strCacheType, l_source);
    }
    
    /**
     * ヒット率情報を作成する。
     */
    private WEB3CacheStatHitRateInfo toHitRateInfo(String l_strTableName,
        String l_strCacheType, CacheStatistics.Tally l_source)
    {

        long l_lngTryCnt = l_source.getAccessCount();
        long l_lngHitCnt = l_source.getHitCount();
        long l_lngMissCnt = l_source.getMissCount();
        double l_dblHitRate = l_source.getHitRate() * 100;
        double l_dblMissRate = l_source.getMissRate() * 100;
        WEB3CacheStatSpecialCase[] l_specialCases = 
            toSpecialCases(l_source.getSpecialCases());

        WEB3CacheStatHitRateInfo l_hitRateInfo = new WEB3CacheStatHitRateInfo();
        l_hitRateInfo.tableName = l_strTableName;
        l_hitRateInfo.cacheType = l_strCacheType;
        l_hitRateInfo.tryCount = helper.format(l_lngTryCnt, NF0);
        l_hitRateInfo.hitCount = helper.format(l_lngHitCnt, NF0);
        l_hitRateInfo.missCount = helper.format(l_lngMissCnt, NF0);
        l_hitRateInfo.hitRate = helper.format(l_dblHitRate, NF1);
        l_hitRateInfo.missRate = helper.format(l_dblMissRate, NF1);
        l_hitRateInfo.specialCases = l_specialCases;

        return l_hitRateInfo;
        
    }
    
    /**
     * ヒット率情報の特殊条件を作成する。
     */
    private WEB3CacheStatSpecialCase[] toSpecialCases(Map l_source)
    {

        if (l_source == null || l_source.isEmpty())
        {
            return null;
        }

        WEB3CacheStatSpecialCase[] l_result = null;
        List l_tempList = new ArrayList();
        
        for (Iterator l_it = l_source.entrySet().iterator(); l_it.hasNext();)
        {

            Map.Entry l_e = (Map.Entry) l_it.next();
            Object l_objKey = l_e.getKey();
            Object l_objValue = l_e.getValue();

            if (l_objKey != null && l_objValue != null)
            {
                WEB3CacheStatSpecialCase l_specialCase = new WEB3CacheStatSpecialCase();
                l_specialCase.name = l_objKey.toString();
                l_specialCase.value = l_objValue.toString();
                l_tempList.add(l_specialCase);
            }

        }

        if (l_tempList.size() > 0)
        {
            l_result = new WEB3CacheStatSpecialCase[l_tempList.size()];
            l_tempList.toArray(l_result);
        }

        return l_result;

    }
    
    /**
     * インバリデーション情報を作成する。
     */
    private WEB3CacheStatInvInfo toInvInfo(List l_keys,
        CacheStatistics.InvInfo l_source)
    {
        String l_strTableName = (String) l_keys.get(0);
        String l_strMutateType = (String) l_keys.get(1);
        return toInvInfo(l_strTableName, l_strMutateType, l_source);
    }

    /**
     * インバリデーション情報を作成する。
     */
    private WEB3CacheStatInvInfo toInvInfo(
        String l_strTableName, String l_strMutateType,
        CacheStatistics.InvInfo l_source)
    {

        WEB3CacheStatInvInfo l_invInfo = new WEB3CacheStatInvInfo();

        long l_lngCount = l_source.getCount();
        double l_dblAvgTime = l_source.getMean() / 1000;
        double l_dblMaxTime = ((double) l_source.getMaximum()) / 1000;
        double l_dblStdDev = l_source.getStandardDeviation() / 1000;
        double l_dblTotalTime = l_source.getCumulativeTotal() / 1000;

        l_invInfo.tableName = l_strTableName;
        l_invInfo.mutatorType = l_strMutateType;
        l_invInfo.count = helper.format(l_lngCount, NF0);
        l_invInfo.averageTime = helper.format(l_dblAvgTime, NF3);
        l_invInfo.maxTime = helper.format(l_dblMaxTime, NF3);
        l_invInfo.standardDeviation = helper.format(l_dblStdDev, NF3);
        l_invInfo.totalTime = helper.format(l_dblTotalTime, NF3);

        return l_invInfo;

    }
    
    /**
     * タイミング情報を作成する。
     */
    private WEB3CacheStatTimingInfo toTimingInfo(List l_keys, CacheStatistics.Summary l_source)
    {
        String l_strTableName = (String) l_keys.get(0);
        String l_strCacheType = (String) l_keys.get(1);
        String l_strCondition = (String) l_keys.get(2);
        String l_strDetail = (String) l_keys.get(3);
        return toTimingInfo(
            l_strTableName,
            l_strCacheType,
            l_strCondition,
            l_strDetail,
            l_source);
    }
    
    /**
     * タイミング情報を作成する。
     */
    private WEB3CacheStatTimingInfo toTimingInfo(String l_strTableName,
        String l_strCacheType, String l_strCondition, String l_strDetail,
        CacheStatistics.Summary l_source)
    {
        
        WEB3CacheStatTimingInfo l_timingInfo = new WEB3CacheStatTimingInfo();
        
        long l_lngCount = l_source.getCount();
        double l_dblAvgTime = l_source.getMean() / 1000;
        double l_dblMaxTime = ((double) l_source.getMaximum()) / 1000;
        double l_dblStdDev = l_source.getStandardDeviation() / 1000;
        double l_dblTotalTime = l_source.getCumulativeTotal() / 1000;
        
        l_timingInfo.tableName = l_strTableName;
        l_timingInfo.cacheType = l_strCacheType;
        l_timingInfo.condition = l_strCondition;
        l_timingInfo.detail = l_strDetail;
        l_timingInfo.count = helper.format(l_lngCount, NF0);
        l_timingInfo.averageTime = helper.format(l_dblAvgTime, NF3);
        l_timingInfo.maxTime = helper.format(l_dblMaxTime, NF3);
        l_timingInfo.standardDeviation = helper.format(l_dblStdDev, NF3);
        l_timingInfo.totalTime = helper.format(l_dblTotalTime, NF1);
        
        return l_timingInfo;

    }

}@
