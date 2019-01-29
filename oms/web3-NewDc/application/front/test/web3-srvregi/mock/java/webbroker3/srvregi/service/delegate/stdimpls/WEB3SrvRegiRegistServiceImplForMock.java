head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiRegistServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : //TODO(WEB3SrvRegiRegistServiceImplForMock.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/04/09 徐宏偉 (中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3SrvRegiRegistServiceImplForMock extends WEB3SrvRegiRegistServiceImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiRegistServiceImplForMock.class);

    /**
     * (getサービス申込登録一覧)<BR>
     * 指定された条件に合致するサービス申込登録一覧を検索し、<BR>
     * その結果をサービス申込登録Paramsオブジェクトの配列にして返却する。<BR>
     * 
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String[] l_strBranchCodes,
            String l_strSrvDiv, String l_strAccountCode, String l_strPaymentDiv, String l_strAppliLotDiv,
            Timestamp l_tsAppliStartDateFrom, Timestamp l_tsAppliStartDateTo, Timestamp l_tsAppliDateFrom,
            Timestamp l_tsAppliDateTo, WEB3SrvRegiSortKey[] l_sortConds) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceRegistLists(String, String[], String, String, String, String, Timestamp, Timestamp, Timestamp, Timestamp, WEB3SrvRegiSortKey[])";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER
                .setMethodParamsValue("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                        "getServiceRegistLists", new Class[]
                        { String.class, String[].class, String.class, String.class, String.class, String.class,
                                Timestamp.class, Timestamp.class, Timestamp.class, Timestamp.class,
                                WEB3SrvRegiSortKey[].class }, new Object[]
                        { l_strInstitutionCode, l_strBranchCodes, l_strSrvDiv, l_strAccountCode, l_strPaymentDiv,
                                l_strAppliLotDiv, l_tsAppliStartDateFrom, l_tsAppliStartDateTo, l_tsAppliDateFrom,
                                l_tsAppliDateTo, l_sortConds });

        if (TestBaseForMock.MOCK_MANAGER
                .isMockUsed("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                        "getServiceRegistLists", new Class[]
                        { String.class, String[].class, String.class, String.class, String.class, String.class,
                                Timestamp.class, Timestamp.class, Timestamp.class, Timestamp.class,
                                WEB3SrvRegiSortKey[].class }))
        {
            // 2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "getServiceRegistLists",
                    new Class[]
                    { String.class, String[].class, String.class, String.class, String.class, String.class,
                            Timestamp.class, Timestamp.class, Timestamp.class, Timestamp.class,
                            WEB3SrvRegiSortKey[].class }).asWEB3BaseException();

            // 3)MockFor --〉 asVoid
            return (SrvRegiApplicationParams[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "getServiceRegistLists",
                    new Class[]
                    { String.class, String[].class, String.class, String.class, String.class, String.class,
                            Timestamp.class, Timestamp.class, Timestamp.class, Timestamp.class,
                            WEB3SrvRegiSortKey[].class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getServiceRegistLists(l_strInstitutionCode, l_strBranchCodes, l_strSrvDiv, l_strAccountCode,
                l_strPaymentDiv, l_strAppliLotDiv, l_tsAppliStartDateFrom, l_tsAppliStartDateTo, l_tsAppliDateFrom,
                l_tsAppliDateTo, l_sortConds);
    }

    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode,
            String l_strSrvDiv, String l_strAccountCode, String l_strCancelDiv, String l_strEffectiveDiv,
            boolean l_blnIsRowLock) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "getServiceRegist",
                new Class[]
                { String.class, String.class, String.class, String.class, String.class, String.class, boolean.class },
                new Object[]
                { l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, l_strCancelDiv,
                        l_strEffectiveDiv, new Boolean(l_blnIsRowLock) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "getServiceRegist",
                new Class[]
                { String.class, String.class, String.class, String.class, String.class, String.class, boolean.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplForMock.getServiceRegist()");
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                            "getServiceRegist",
                            new Class[]
                            { String.class, String.class, String.class, String.class, String.class, String.class,
                                    boolean.class }).asWEB3BaseException();
            return (WEB3GentradeSrvRegiApplication) TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                            "getServiceRegist",
                            new Class[]
                            { String.class, String.class, String.class, String.class, String.class, String.class,
                                    boolean.class }).asObject();
        }
        return super.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                l_strCancelDiv, l_strEffectiveDiv, l_blnIsRowLock);
    }

    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode,
            String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId, boolean l_blnIsRowLock)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "getServiceRegist",
                new Class[]
                { String.class, String.class, String.class, String.class, long.class, boolean.class }, new Object[]
                { l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, new Long(l_lngRegistId),
                        new Boolean(l_blnIsRowLock) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "getServiceRegist",
                new Class[]
                { String.class, String.class, String.class, String.class, long.class, boolean.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplForMock.getServiceRegist()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "getServiceRegist",
                    new Class[]
                    { String.class, String.class, String.class, String.class, long.class, boolean.class })
                    .asWEB3BaseException();
            return (WEB3GentradeSrvRegiApplication) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "getServiceRegist",
                    new Class[]
                    { String.class, String.class, String.class, String.class, long.class, boolean.class }).asObject();
        }
        return super.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                l_lngRegistId, l_blnIsRowLock);
    }

    public String getInitializeAppliDiv(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv,
            String l_strAccountCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "getInitializeAppliDiv",
                new Class[]
                { String.class, String.class, String.class, String.class }, new Object[]
                { l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "getInitializeAppliDiv",
                new Class[]
                { String.class, String.class, String.class, String.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplForMock.getInitializeAppliDiv()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "getInitializeAppliDiv", new Class[]
                    { String.class, String.class, String.class, String.class }).asWEB3BaseException();
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "getInitializeAppliDiv", new Class[]
                    { String.class, String.class, String.class, String.class }).asObject();
        }
        return super.getInitializeAppliDiv(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);
    }

    // public Timestamp calcAppliEndDate(String l_strInstitutionCode, String
    // l_strBranchCode, String l_strSrvDiv,
    // String l_strAccountCode, Timestamp l_tsAppliStartDate, long
    // l_lngSrvUsePeriodId, String l_strSpecialDiv)
    // throws WEB3BaseException
    // {
    // TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
    // "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
    // "calcAppliEndDate",
    // new Class[]{
    // String.class, String.class, String.class, String.class, Timestamp.class,
    // long.class, String.class},
    // new Object[]{
    // l_strInstitutionCode, l_strBranchCode, l_strSrvDiv,
    // l_strAccountCode, l_tsAppliStartDate, new Long(l_lngSrvUsePeriodId),
    // l_strSpecialDiv
    // }
    // );
    // if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
    // "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
    // "calcAppliEndDate",
    // new Class[]
    // {
    // String.class, String.class, String.class, String.class, Timestamp.class,
    // long.class, String.class}))
    // {
    // log
    // .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplForMock.calcAppliEndDate()");
    // TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
    // "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
    // "calcAppliEndDate", new Class[]{
    // String.class, String.class, String.class, String.class, Timestamp.class,
    // long.class, String.class}).asWEB3BaseException();
    // return (Timestamp) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
    // "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
    // "calcAppliEndDate", new Class[]{
    // String.class, String.class, String.class, String.class, Timestamp.class,
    // long.class, String.class}).asObject();
    // }
    // return super.calcAppliEndDate(l_strInstitutionCode, l_strBranchCode,
    // l_strSrvDiv,
    // l_strAccountCode, l_tsAppliStartDate, l_lngSrvUsePeriodId,
    // l_strSpecialDiv);
    // }

    public void validateAppliPeriod(String l_strInstitutionCode, String l_strSrvDiv, String l_strBranchCode,
            String l_strMainAccountCode, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate, Long l_lngRegistId)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER
                .setMethodParamsValue("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                        "validateAppliPeriod", new Class[]
                        { String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class,
                                Long.class }, new Object[]
                        { l_strInstitutionCode, l_strSrvDiv, l_strBranchCode, l_strMainAccountCode, l_tsAppliStartDate,
                                l_tsAppliEndDate, l_lngRegistId });
        if (TestBaseForMock.MOCK_MANAGER
                .isMockUsed("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                        "validateAppliPeriod", new Class[]
                        { String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class,
                                Long.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplForMock.validateAppliPeriod()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "validateAppliPeriod",
                    new Class[]
                    { String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class,
                            Long.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "validateAppliPeriod",
                    new Class[]
                    { String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class,
                            Long.class }).asVoid();
        }
        super.validateAppliPeriod(l_strInstitutionCode, l_strSrvDiv, l_strBranchCode, l_strMainAccountCode,
                l_tsAppliStartDate, l_tsAppliEndDate, l_lngRegistId);
    }

    public SrvAppliAttributeParams[] getServiceAttributeLists(String l_strInstitutionCode, String[] l_strBranchCodes,
            String l_strSrvDiv, String l_strAccountCode, String l_strAppliLotDiv, Timestamp l_tsAppDate,
            WEB3SrvRegiSortKey[] l_sortConditions) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceAttributeLists", new Class[]
                { String.class, String[].class, String.class, String.class, String.class, Timestamp.class,
                        WEB3SrvRegiSortKey[].class }, new Object[]
                { l_strInstitutionCode, l_strBranchCodes, l_strSrvDiv, l_strAccountCode, l_strAppliLotDiv, l_tsAppDate,
                        l_sortConditions });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceAttributeLists", new Class[]
                { String.class, String[].class, String.class, String.class, String.class, Timestamp.class,
                        WEB3SrvRegiSortKey[].class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplForMock.getServiceAttributeLists()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "getServiceAttributeLists",
                    new Class[]
                    { String.class, String[].class, String.class, String.class, String.class, Timestamp.class,
                            WEB3SrvRegiSortKey[].class }).asWEB3BaseException();
            return (SrvAppliAttributeParams[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "getServiceAttributeLists",
                    new Class[]
                    { String.class, String[].class, String.class, String.class, String.class, Timestamp.class,
                            WEB3SrvRegiSortKey[].class }).asObject();
        }
        return super.getServiceAttributeLists(l_strInstitutionCode, l_strBranchCodes, l_strSrvDiv, l_strAccountCode,
                l_strAppliLotDiv, l_tsAppDate, l_sortConditions);
    }

    public Timestamp calcAppliEndDate(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv,
            String l_strAccountCode, Timestamp l_tsAppliStartDate, long l_lngSrvUsePeriodId, String l_strSpecialDiv,
            String l_strFreeAttributeApplyDiv) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "calcAppliEndDate",
                new Class[]
                { String.class, String.class, String.class, String.class, Timestamp.class, long.class, String.class,
                        String.class }, new Object[]
                { l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, l_tsAppliStartDate,
                        new Long(l_lngSrvUsePeriodId), l_strSpecialDiv, l_strFreeAttributeApplyDiv });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "calcAppliEndDate",
                new Class[]
                { String.class, String.class, String.class, String.class, Timestamp.class, long.class, String.class,
                        String.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplForMock.calcAppliEndDate()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "calcAppliEndDate",
                    new Class[]
                    { String.class, String.class, String.class, String.class, Timestamp.class, long.class,
                            String.class, String.class }).asWEB3BaseException();
            return (Timestamp) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "calcAppliEndDate",
                    new Class[]
                    { String.class, String.class, String.class, String.class, Timestamp.class, long.class,
                            String.class, String.class }).asObject();
        }
        return super.calcAppliEndDate(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                l_tsAppliStartDate, l_lngSrvUsePeriodId, l_strSpecialDiv, l_strFreeAttributeApplyDiv);
    }

    public void submitServiceRegist(WEB3SrvRegiNewAppliSpec l_newAppliSpec, Long l_orderId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "submitServiceRegist",
                new Class[]
                { WEB3SrvRegiNewAppliSpec.class, Long.class }, new Object[]
                { l_newAppliSpec, l_orderId });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "submitServiceRegist",
                new Class[]
                { WEB3SrvRegiNewAppliSpec.class, Long.class }))
        {
            log
                    .debug("webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplForMock.submitServiceRegist()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "submitServiceRegist",
                    new Class[]
                    { WEB3SrvRegiNewAppliSpec.class, Long.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl", "submitServiceRegist",
                    new Class[]
                    { WEB3SrvRegiNewAppliSpec.class, Long.class }).asVoid();
            return;
        }
        super.submitServiceRegist(l_newAppliSpec, l_orderId);
    }
}
@
