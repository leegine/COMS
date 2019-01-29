head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheMonitorServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@(WEB3CacheMonitorServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.cachemonitor.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import webbroker3.cachemonitor.data.*;
import webbroker3.cachemonitor.message.*;
import webbroker3.cachemonitor.service.delegate.*;
import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.gentrade.data.*;
import webbroker3.util.*;

public class WEB3CacheMonitorServiceImpl
    implements WEB3CacheMonitorService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3CacheMonitorServiceImpl.class);

    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        boolean isDirty = false;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_list = l_qp.doFindAllQuery(CacheMonitorOrdAccStatusRow.TYPE);
            log.info("diff start...");
            for (int i = 0; i < l_list.size(); i++)
            {
                CacheMonitorOrdAccStatusRow l_dbRow = (CacheMonitorOrdAccStatusRow)
                    l_list.get(i);
                log.info("CacheMonitorOrdAccStatusRow[" + i + "]=" + l_dbRow);

                OrderAcceptStatusRow l_cacheRow = OrderAcceptStatusDao.
                    findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(
                    l_dbRow.getInstitutionCode(), l_dbRow.getBranchCode(),
                    l_dbRow.getOrderAccProduct(), l_dbRow.getOrderAccTransaction());

                log.info("       OrderAcceptStatusRow[" + i + "]=" + l_cacheRow);

                if (fieldsEqual(l_cacheRow, l_dbRow) == false)
                {
                    isDirty = true;
                    log.info("cache is dirty!.");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            log.info("cache may be dirty!.");
            isDirty = true;
        }

        log.info("diff end.");
        WEB3CacheMonitorResponse l_resp = new WEB3CacheMonitorResponse(l_request);
        l_resp.is_dirty = isDirty;
        if (isDirty == true)
        {
            l_resp.dirty_table_name = OrderAcceptStatusRow.TYPE.toString();
        }
        return l_resp;
    }

    public boolean fieldsEqual(OrderAcceptStatusRow l_cacheRow,
                               CacheMonitorOrdAccStatusRow l_dbRow)
    {
        if (l_cacheRow.getInstitutionCode() == null)
        {
            if (l_dbRow.getInstitutionCode() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getInstitutionCode().equals(l_dbRow.getInstitutionCode()))
        {
            return false;
        }

        if (l_cacheRow.getBranchCode() == null)
        {
            if (l_dbRow.getBranchCode() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getBranchCode().equals(l_dbRow.getBranchCode()))
        {
            return false;
        }

        if (l_cacheRow.getOrderAccProduct() == null)
        {
            if (l_dbRow.getOrderAccProduct() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getOrderAccProduct().equals(l_dbRow.getOrderAccProduct()))
        {
            return false;
        }

        if (l_cacheRow.getOrderAccTransaction() == null)
        {
            if (l_dbRow.getOrderAccTransaction() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getOrderAccTransaction().equals(l_dbRow.
            getOrderAccTransaction()))
        {
            return false;
        }

        if (l_cacheRow.getOrderAcceptStatus() == null)
        {
            if (l_dbRow.getOrderAcceptStatus() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getOrderAcceptStatus().equals(l_dbRow.getOrderAcceptStatus()))
        {
            return false;
        }

        if (l_cacheRow.getOrderAcceptStatusBefore() == null)
        {
            if (l_dbRow.getOrderAcceptStatusBefore() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getOrderAcceptStatusBefore().equals(l_dbRow.
            getOrderAcceptStatusBefore()))
        {
            return false;
        }

        if (l_cacheRow.getLastUpdater() == null)
        {
            if (l_dbRow.getLastUpdater() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getLastUpdater().equals(l_dbRow.getLastUpdater()))
        {
            return false;
        }

        if (l_cacheRow.getCreatedTimestamp() == null)
        {
            if (l_dbRow.getCreatedTimestamp() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getCreatedTimestamp().equals(l_dbRow.getCreatedTimestamp()))
        {
            return false;
        }

        if (l_cacheRow.getLastUpdatedTimestamp() == null)
        {
            if (l_dbRow.getLastUpdatedTimestamp() != null)
            {
                return false;
            }
        }
        else if (!l_cacheRow.getLastUpdatedTimestamp().equals(l_dbRow.
            getLastUpdatedTimestamp()))
        {
            return false;
        }
        return true;
    }

}
@
