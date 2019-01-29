head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3CacheStatisticsUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3CacheStatisticsUtilsクラス(WEB3CacheStatisticsUtils.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/24 山田　@卓司 (FLJ) 新規作成
                  : 2005/04/19 山田　@卓司 (FLJ) キャッシュ統計・タイミング詳細出力メソッドを追加
 */
package webbroker3.preloader;

import java.util.Iterator;
import java.util.Map;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.impl.CacheStatistics;

/**
 * キャッシュ統計操作用ユーティリティクラス
 * 
 * @@author Takuji Yamada (FLJ)
 */
class WEB3CacheStatisticsUtils
{
	
    private static final WEB3LogUtility LOG =
        WEB3LogUtility.getInstance(WEB3CacheStatisticsUtils.class);
    
    private WEB3CacheStatisticsUtils()
    {
    }

    /**
     * 統計情報収集を開始する。
     * 
     * @@return 既に開始されている場合<code>true</code>、今回開始した場合<code>false</code>
     */
    synchronized static boolean startCollecting()
    {
        boolean l_hasAlreadyStarted = CacheStatistics.isCollecting();
        if (!l_hasAlreadyStarted)
        {
            CacheStatistics.setCollecting(true);
            LOG.debug("Collecting cache statistics was started.");
        } else {
            LOG.debug("Collecting cache statistics has already started.");
        }
        return l_hasAlreadyStarted;
    }

    /**
     * 統計情報収集を停止する。
     */
    synchronized static void stopCollecting()
    {
        CacheStatistics.setCollecting(false);
        CacheStatistics.clear();
        LOG.debug("Collecting cache statistics was stoped.");
    }

    /**
     * 統計情報収集を停止する。
     * パラメータ.l_hasAlreadyStartedには、WEB3CacheStatisticsUtils#startCollecting()の戻り値を設定する。
     * 
     * @@param l_hasAlreadyStarted <code>true</code>の時は停止しない、<code>false</code>の時は停止する。
     */
    synchronized static void stopCollecting(boolean l_hasAlreadyStarted)
    {
        if (!l_hasAlreadyStarted)
        {
            stopCollecting();
        }
    }

    /**
     * キャッシュされているオブジェクトの数を取得する。
     * 
     * @@param l_strTableName テーブル名
     * @@param l_strCacheType キャッシュタイプ（"row" または "enum"）
     * @@return キャッシュされているオブジェクトの数
     */
    static long getCachedItemCount(
        String l_strTableName,
        String l_strCacheType)
    {
        long l_lngCachedItemCount = 0L;
        Map l_sizeInfoMapMap = CacheStatistics.getSizeInfoMapMap();
        if (l_sizeInfoMapMap != null)
        {
            Map l_sizeInfoMap = (Map) l_sizeInfoMapMap.get(l_strTableName);
            if (l_sizeInfoMap != null)
            {
                CacheStatistics.SizeInfo l_sizeInfo =
                    (CacheStatistics.SizeInfo) l_sizeInfoMap.get(
                        l_strCacheType);
                if (l_sizeInfo != null)
                {
                    l_lngCachedItemCount = l_sizeInfo.getCount();
                }
            }
        }
        return l_lngCachedItemCount;
    }

    static void printCachedItemCountSummary()
    {
    	
    	LOG.info("--------------------------------------------------");
    	
        Map l_sizeInfoMapMap = CacheStatistics.getSizeInfoMapMap();
        for (Iterator l_it = l_sizeInfoMapMap.entrySet().iterator();
            l_it.hasNext();
            )
        {
            Map.Entry l_itEntry = (Map.Entry) l_it.next();
            String l_strTableName = (String) l_itEntry.getKey();
            Map l_sizeInfoMap = (Map) l_itEntry.getValue();
            for (Iterator l_jt = l_sizeInfoMap.entrySet().iterator();
                l_jt.hasNext();
                )
            {
                Map.Entry l_jtEntry = (Map.Entry) l_jt.next();
                String l_strCacheType = (String) l_jtEntry.getKey();
                CacheStatistics.SizeInfo l_sizeInfo =
                    (CacheStatistics.SizeInfo) l_jtEntry.getValue();
                
                StringBuffer l_sb = new StringBuffer();
                l_sb.append("TableName=").append(l_strTableName);
                l_sb.append(",CacheType=").append(l_strCacheType);
                l_sb.append(",CachedItemCount=").append(l_sizeInfo.getCount());
                
                LOG.info(l_sb.toString());
                
            }
        }
        
		LOG.info("--------------------------------------------------");
		
    }
    
    static void printTimingInfoSummary()
    {
        
        LOG.info("--------------------------------------------------");
        
        Map l_timingMapMapMapMap = CacheStatistics.getTimingMapMapMapMap();
        for (Iterator l_i1 = l_timingMapMapMapMap.entrySet().iterator(); l_i1.hasNext();)
        {
            Map.Entry l_e1 = (Map.Entry) l_i1.next();
            String l_strTableName = (String) l_e1.getKey();
            Map l_timingMapMapMap = (Map) l_e1.getValue();
            
            for (Iterator l_i2 = l_timingMapMapMap.entrySet().iterator(); l_i2.hasNext();)
            {
                Map.Entry l_e2 = (Map.Entry) l_i2.next();
                String l_strCacheType = (String) l_e2.getKey();
                Map l_timingMapMap = (Map) l_e2.getValue();
                
                for (Iterator l_i3 = l_timingMapMap.entrySet().iterator(); l_i3.hasNext();)
                {
                    Map.Entry l_e3 = (Map.Entry) l_i3.next();
                    String l_strSql = (String) l_e3.getKey();
                    Map l_timingMap = (Map) l_e3.getValue();
                    
                    for (Iterator l_i4 = l_timingMap.entrySet().iterator(); l_i4.hasNext();)
                    {
                        Map.Entry l_e4 = (Map.Entry) l_i4.next();
                        String l_strDetail = (String) l_e4.getKey();
                        CacheStatistics.Summary timing = (CacheStatistics.Summary) l_e4.getValue();
                        
                        StringBuffer l_sb = new StringBuffer();
                        l_sb.append("TableName=").append(l_strTableName);
                        l_sb.append(",CacheType=").append(l_strCacheType);
                        l_sb.append(",Sql=").append(l_strSql);
                        l_sb.append(",Detail=").append(l_strDetail);
                        l_sb.append(",Count=").append(timing.getCount());
                        LOG.info(l_sb.toString());
                        
                    }
                    
                }
            }
            
        }
        
        LOG.info("--------------------------------------------------");
    }

}
@
