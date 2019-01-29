head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSTradingTimeManagementForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �iWEB3EquityPTSTradingTimeManagementForTest.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/25 �g�E�N�| (���u) �V�K�쐬
*/
package webbroker3.equity;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSTradingTimeManagementForTest extends WEB3EquityPTSTradingTimeManagement
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranchMarketPTSDealtCond.class);
    
    private static String caseNum = "0";
    
    public void setCaseNum(String caseNum)
    {
        this.caseNum = caseNum;
    }
    
    public static Date getOrderBizDate() throws WEB3SystemLayerException
    {
        if ("0".equals(caseNum))
        {
            return WEB3DateUtility.getDate("20071225", "yyyyMMdd");
        }
        else
        {
            return null;
        }
        
    }

    public static Date getOrderBizDate(Date l_datConfirmBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(Date)";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = getOrderBizDate();
        if (l_datBizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datConfirmBizDate;
        }

        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datConfirmBizDate) != 0)
        {
            log.debug("���������ς��܂����B���萔�ł����A������x���͂������Ă��������B");
            log.exiting(STR_METHOD_NAME);
            //�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���Η�O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
               "���������ς��܂����B���萔�ł����A������x���͂������Ă��������B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_datConfirmBizDate;
    }
    
}
@
