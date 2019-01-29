head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.37.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoCommissionCourseRegistForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.util.WEB3DateUtility;

public class WEB3AccInfoCommissionCourseRegistForMock extends WEB3AccInfoCommissionCourseRegist
{
    public WEB3AccInfoCommissionCourseRegistForMock(long l_lngCommissionCourseRegistID) throws WEB3BaseException
    {
        super(l_lngCommissionCourseRegistID);
    }

    public static void mockGetCommissionCourseRegist(
            WEB3GentradeMainAccount l_mainAccount, String l_strCommissionProductCode)
        throws WEB3BaseException
    {
        //MainAccountParams
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);
        l_mainAccountParams.setInstitutionCode("0D");
        TestDBUtility.insertWithDel(l_mainAccountParams);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("33");
        TestDBUtility.insertWithDel(l_institutionParams);

        //BranchPreferencesParams
        BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
        l_branchPreferencesParams.setBranchId(33381);
        l_branchPreferencesParams.setName("gentrade.bizlogicprovider.div");
        l_branchPreferencesParams.setNameSerialNo(1);
        TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
        TestDBUtility.insertWithDel(l_branchPreferencesParams);

        //MobileOfficeInfoRegistParams
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = TestDBUtility.getMobileOfficeInfoRegistRow();
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);

        //CommissionCourseRegistParams
        CommissionCourseRegistParams l_commissionCourseRegistParams = new CommissionCourseRegistParams();
        l_commissionCourseRegistParams.setCommissionCourseRegistId(123456789L);
        l_commissionCourseRegistParams.setInstitutionCode("0D");
        l_commissionCourseRegistParams.setBranchId(33381L);
        l_commissionCourseRegistParams.setAccountId(333812512203L);
        l_commissionCourseRegistParams.setCommProductCode("10");
        l_commissionCourseRegistParams.setAppliStartDatetime(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setAppliEndDatetime(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setCommissionCourseDiv("12");
        l_commissionCourseRegistParams.setRegistTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setRegistEndTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setDownloadFlag(BooleanEnum.TRUE);
        l_commissionCourseRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_commissionCourseRegistParams.setLastUpdater("abcd");
        l_commissionCourseRegistParams.setCreatedTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));

        TestDBUtility.deleteAll(l_mobileOfficeInfoRegistParams.getRowType());
        TestDBUtility.insertWithDel(l_mobileOfficeInfoRegistParams);

        TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
        TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());

        TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

        TestDBUtility.deleteAll(l_commissionCourseRegistParams.getRowType());
        TestDBUtility.insertWithDel(l_commissionCourseRegistParams);

    }
}
@
