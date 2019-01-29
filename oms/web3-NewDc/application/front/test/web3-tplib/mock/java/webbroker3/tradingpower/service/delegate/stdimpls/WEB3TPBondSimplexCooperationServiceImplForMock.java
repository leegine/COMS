head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPBondSimplexCooperationServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3TPBondSimplexCooperationServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/06 ïêîg (íÜêu) êVãKçÏê¨
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author ïêîg(íÜêu)
 * @@version 1.0
 */
public class WEB3TPBondSimplexCooperationServiceImplForMock
    extends WEB3TPBondSimplexCooperationServiceImpl
{

    public void saveBondBuyAmount(long l_lngAccountId, double l_dblRestraint,
        Date l_datFinTransactionDate, Date l_datDeliveryDate, String l_strOrderNo)
        throws WEB3BaseException
    {

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl",
            "saveBondBuyAmount", new Class[]
            { long.class, double.class,Date.class,Date.class,String.class }))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl",
                    "saveBondBuyAmount", new Class[]
                   { long.class, double.class,Date.class,Date.class,String.class }).asObject();
            return;
        }
        super.saveBondBuyAmount(l_lngAccountId, l_dblRestraint, l_datFinTransactionDate, l_datDeliveryDate, l_strOrderNo);
    }

    public void cancelBondBuyAmount(String l_strOrderNo) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl",
                "cancelBondBuyAmount", new Class[]
                { String.class }))
            {
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl",
                        "cancelBondBuyAmount", new Class[]
                       { String.class }).asObject();
                return;
            }
        super.cancelBondBuyAmount(l_strOrderNo);
    }
}

@
