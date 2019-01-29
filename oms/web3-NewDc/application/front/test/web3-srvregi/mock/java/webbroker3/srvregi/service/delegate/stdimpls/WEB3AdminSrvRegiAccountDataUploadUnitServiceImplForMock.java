head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/18 金傑 (中訊) 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock extends WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiRegistServiceImplForMock.class);
    
    public void insertSrvApplyAttribute(String l_strInstitutionCode, String l_strBranchCode,
            String l_strMainAccountCode, String l_strServiceDiv, String l_strAppliLotDiv, Timestamp l_tsAppliStartDate,
            Timestamp l_tsAppliEndDate) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                "insertSrvApplyAttribute", new Class[]
                { String.class, String.class, String.class, String.class, String.class, Timestamp.class,
                        Timestamp.class }, new Object[]
                { l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode, l_strServiceDiv, l_strAppliLotDiv,
                        l_tsAppliStartDate, l_tsAppliEndDate });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                "insertSrvApplyAttribute", new Class[]
                { String.class, String.class, String.class, String.class, String.class, Timestamp.class,
                        Timestamp.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock.insertSrvApplyAttribute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                    "insertSrvApplyAttribute",
                    new Class[]
                    { String.class, String.class, String.class, String.class, String.class, Timestamp.class,
                            Timestamp.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                    "insertSrvApplyAttribute",
                    new Class[]
                    { String.class, String.class, String.class, String.class, String.class, Timestamp.class,
                            Timestamp.class }).asVoid();
            return;
        }
        super.insertSrvApplyAttribute(l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode, l_strServiceDiv,
                l_strAppliLotDiv, l_tsAppliStartDate, l_tsAppliEndDate);
    }
    
    public void updateSrvApplyAttribute(String l_strInstitutionCode, String l_strBranchCode,
            String l_strMainAccountCode, String l_strServiceDiv, String l_strAppliLotDiv, Timestamp l_tsAppliStartDate,
            Timestamp l_tsAppliEndDate) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                "updateSrvApplyAttribute", new Class[]
                { String.class, String.class, String.class, String.class, String.class, Timestamp.class,
                        Timestamp.class }, new Object[]
                { l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode, l_strServiceDiv, l_strAppliLotDiv,
                        l_tsAppliStartDate, l_tsAppliEndDate });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                "updateSrvApplyAttribute", new Class[]
                { String.class, String.class, String.class, String.class, String.class, Timestamp.class,
                        Timestamp.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock.updateSrvApplyAttribute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                    "updateSrvApplyAttribute",
                    new Class[]
                    { String.class, String.class, String.class, String.class, String.class, Timestamp.class,
                            Timestamp.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                    "updateSrvApplyAttribute",
                    new Class[]
                    { String.class, String.class, String.class, String.class, String.class, Timestamp.class,
                            Timestamp.class }).asVoid();
            return;
        }
        super.updateSrvApplyAttribute(l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode, l_strServiceDiv,
                l_strAppliLotDiv, l_tsAppliStartDate, l_tsAppliEndDate);
    }
    
    public void updateAppliRegist(WEB3GentradeSubAccount l_subAccount, String l_strUploadDiv, Long l_registId,
            String l_strInstitutionCode, String l_strSrvDiv, String l_strBranchCode, String l_strAccountCode,
            Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate, Timestamp l_tsAppliDate, String l_strAppliLotDiv,
            String l_strPaymentDiv, Double l_dblUseAmt, Timestamp l_tsPaymentDate, String l_strPassword)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                "updateAppliRegist", new Class[]
                { WEB3GentradeSubAccount.class, String.class, Long.class, String.class, String.class, String.class,
                        String.class, Timestamp.class, Timestamp.class, Timestamp.class, String.class, String.class,
                        Double.class, Timestamp.class, String.class }, new Object[]
                { l_subAccount, l_strUploadDiv, l_registId, l_strInstitutionCode, l_strSrvDiv, l_strBranchCode,
                        l_strAccountCode, l_tsAppliStartDate, l_tsAppliEndDate, l_tsAppliDate, l_strAppliLotDiv,
                        l_strPaymentDiv, l_dblUseAmt, l_tsPaymentDate, l_strPassword });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                "updateAppliRegist", new Class[]
                { WEB3GentradeSubAccount.class, String.class, Long.class, String.class, String.class, String.class,
                        String.class, Timestamp.class, Timestamp.class, Timestamp.class, String.class, String.class,
                        Double.class, Timestamp.class, String.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock.updateAppliRegist()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                    "updateAppliRegist",
                    new Class[]
                    { WEB3GentradeSubAccount.class, String.class, Long.class, String.class, String.class, String.class,
                            String.class, Timestamp.class, Timestamp.class, Timestamp.class, String.class,
                            String.class, Double.class, Timestamp.class, String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                    "updateAppliRegist",
                    new Class[]
                    { WEB3GentradeSubAccount.class, String.class, Long.class, String.class, String.class, String.class,
                            String.class, Timestamp.class, Timestamp.class, Timestamp.class, String.class,
                            String.class, Double.class, Timestamp.class, String.class }).asVoid();
            return;
        }
        super.updateAppliRegist(l_subAccount, l_strUploadDiv, l_registId, l_strInstitutionCode, l_strSrvDiv,
                l_strBranchCode, l_strAccountCode, l_tsAppliStartDate, l_tsAppliEndDate, l_tsAppliDate,
                l_strAppliLotDiv, l_strPaymentDiv, l_dblUseAmt, l_tsPaymentDate, l_strPassword);
    }
}
@
