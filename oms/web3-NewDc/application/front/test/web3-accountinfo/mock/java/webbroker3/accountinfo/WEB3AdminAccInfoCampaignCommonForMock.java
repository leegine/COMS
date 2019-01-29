head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.37.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignCommonForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[�����ʃN���XForMock(WEB3AdminAccInfoCampaignCommonForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/07 ���G�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo;

import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �萔�������L�����y�[�����ʃN���XForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignCommonForMock extends WEB3AdminAccInfoCampaignCommon
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoCampaignCommonForMock.class);

    /**
     * (delete�L�����y�[������(Mock))<BR>
     * �萔�������L�����y�[�������}�X�^���R�[�h�̍폜���s���B<BR>
     * @@param l_strCampaignConditionId - �萔�������L�����y�[������ID<BR>
     * <BR>
     * @@param l_strUpdaterCode - �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException<BR>
     */
    public void deleteCampaignCondition(String l_strCampaignConditionId, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteCampaignCondition(String, String)-->ForMock";
        log.entering(STR_METHOD_NAME);
        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "deleteCampaignCondition",
            new Class[] {String.class, String.class},
            new Object[]{l_strCampaignConditionId, l_strUpdaterCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "deleteCampaignCondition",  new Class[] {String.class, String.class}))
        {
            //2�jMockFor --�r WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "deleteCampaignCondition",
                new Class[] {String.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "deleteCampaignCondition",
                new Class[] {String.class, String.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.deleteCampaignCondition(l_strCampaignConditionId, l_strUpdaterCode);
    }

    /**
     * (get�L�����y�[���ꗗ(Mock))<BR>
     * �萔�������L�����y�[�����R�[�h�̎擾���s���B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strRegistTypes - (�o�^�^�C�v)<BR>
     * �o�^�^�C�v�̔z��<BR>
     * <BR>
     * 0�F �����J�ݏ����w��<BR>
     * 1�F�ʌڋq�w��<BR>
     * 2�F�����ʌڋq�w��<BR>
     * @@return WEB3AccInfoCampainInfo[]
     */
    public WEB3AccInfoCampaignInfo[] getCampaignList(WEB3GenRequest l_request, String l_strInstitutionCode,
            String[] l_strRegistTypes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignList(WEB3GenRequest, String, String[])-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignList",
            new Class[] {WEB3GenRequest.class, String.class, String[].class},
            new Object[]{l_request, l_strInstitutionCode, l_strRegistTypes});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignList",
            new Class[] {WEB3GenRequest.class, String.class, String[].class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignList",
                new Class[] {WEB3GenRequest.class, String.class, String[].class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3AccInfoCampaignInfo[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignList",
                new Class[] {WEB3GenRequest.class, String.class, String[].class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getCampaignList(l_request, l_strInstitutionCode, l_strRegistTypes);
    }

    /**
     * (get�L�����y�[������(Mock))<BR>
     * @@param l_strCampaignId - �萔�������L�����y�[������ID<BR>
     * <BR>
     * @@return WEB3AccInfoCampainInfo
     */
    public WEB3AccInfoCampaignInfo getCampaignCondition(String l_strCampaignId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignItemMasterList(List)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignCondition",
            new Class[] {String.class},
            new Object[]{l_strCampaignId});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignCondition",
            new Class[] {String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3AccInfoCampaignInfo)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getCampaignCondition(l_strCampaignId);
    }

    /**
     * (get�d���L�����y�[������(Mock))<BR>
     * �萔�������L�����y�[�����R�[�h�̎擾���s���B<BR>
     * @@param l_sameSearchCondition - �d����������<BR>
     * @@return WEB3AccInfoCampainInfo[]
     */
    public WEB3AccInfoCampaignInfo[] getSameCampaignCondition(
            WEB3AdminAccInfoCampaignSearchCondition l_sameSearchCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getSameCampaignCondition",
            new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
            new Object[]{l_sameSearchCondition});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getSameCampaignCondition",
            new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3AccInfoCampaignInfo[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getSameCampaignCondition(l_sameSearchCondition);
    }

    /**
     * (get�����R�[�h����(Mock))<BR>
     * �����R�[�h�����l��Ԃ��B<BR>
     * @@param l_campaignSearchCondition - (�L�����y�[����������)<BR>
     * �萔�������L�����y�[�����������I�u�W�F�N�g<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strRegistTypes - �o�^�^�C�v<BR>
     * @@return int
     */
    public int getAllRecordCount(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strInstitutionCode, String[] l_strRegistTypes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "getAllRecordCount(WEB3AccInfoCampaignSearchCondition, String, String[])-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getAllRecordCount",
            new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class},
            new Object[]{l_campaignSearchCondition, l_strInstitutionCode, l_strRegistTypes});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getAllRecordCount",
            new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getAllRecordCount",
                new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class}).asWEB3BaseException();

            //3)MockFor --�r asInt
            log.exiting(STR_METHOD_NAME);
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getAllRecordCount",
                new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class}).asInt();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getAllRecordCount(l_campaignSearchCondition, l_strInstitutionCode, l_strRegistTypes);
    }

    /**
     * (insert�L�����y�[������(Mock))<BR>
     * @@param l_registInfo - (�o�^���)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strUpdaterCode - �X�V�҃R�[�h<BR>
     */
    public void insertCampaignCondition(WEB3AccInfoCampaignInfo l_registInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "insertCampaignCondition(WEB3AccInfoCampaignInfo, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "insertCampaignCondition",
            new Class[] {WEB3AccInfoCampaignInfo.class, String.class},
            new Object[]{l_registInfo, l_strUpdaterCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "insertCampaignCondition",
            new Class[] {WEB3AccInfoCampaignInfo.class, String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "insertCampaignCondition",
                new Class[] {WEB3AccInfoCampaignInfo.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "insertCampaignCondition",
                new Class[] {WEB3AccInfoCampaignInfo.class, String.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.insertCampaignCondition(l_registInfo, l_strUpdaterCode);
    }

    /**
     * (is�ύX���(Mock))<BR>
     * @@param l_changeAfterInfo - (�ύX����)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * @@return Boolean
     */
    public boolean isChangeInfo(WEB3AccInfoCampaignInfo l_changeAfterInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeInfo(WEB3AccInfoCampaignInfo)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "isChangeInfo",
            new Class[] {WEB3AccInfoCampaignInfo.class},
            new Object[]{l_changeAfterInfo});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "isChangeInfo",
            new Class[] {WEB3AccInfoCampaignInfo.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class}).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            log.exiting(STR_METHOD_NAME);
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class}).asBoolean();

        }

        log.exiting(STR_METHOD_NAME);
        return super.isChangeInfo(l_changeAfterInfo);
    }

    /**
     * (update�L�����y�[������)<BR>
     * @@param l_changeAfterInfo - (�ύX����)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strUpdaterCode - �X�V�҃R�[�h<BR>
     */
    public void updateCampaignCondition(WEB3AccInfoCampaignInfo l_changeAfterInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateCampaignCondition(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "updateCampaignCondition",
            new Class[] {WEB3AccInfoCampaignInfo.class, String.class},
            new Object[]{l_changeAfterInfo, l_strUpdaterCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "updateCampaignCondition",
            new Class[] {WEB3AccInfoCampaignInfo.class, String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "updateCampaignCondition",
                new Class[] {WEB3AccInfoCampaignInfo.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "updateCampaignCondition",
                new Class[] {WEB3AccInfoCampaignInfo.class, String.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.updateCampaignCondition(l_changeAfterInfo, l_strUpdaterCode);
    }

    /**
     * (validate�Ώۊ���(Mock))<BR>
     * �C���Ώۂ̃L�����y�[�������݁A�L�����y�[�����Ԓ����ǂ����`�F�b�N���s���B<BR>
     * @@param l_changeAfterInfo - (�ύX����)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * @@return String
     */
    public String validateTargetPeriod(WEB3AccInfoCampaignInfo l_changeAfterInfo,String updateFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTargetPeriod(WEB3AccInfoCampaignInfo)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "validateTargetPeriod",
            new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
            new Object[]{l_changeAfterInfo,updateFlag});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "validateTargetPeriod",
            new Class[] {WEB3AccInfoCampaignInfo.class,String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class,String.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class,String.class}).asObject();

        }

        log.exiting(STR_METHOD_NAME);
        return super.validateTargetPeriod(l_changeAfterInfo,updateFlag);
    }

    /**
     * �i�X�[�p�[�N���X�Ɏ��g�̃C���X�^���X��o�^����B�j�B<BR>
     * <BR>
     * �i�v���O�C�����������ɃR�[�������j<BR>
     * <BR>
     * ---<BR>
     * super.setInstance(this);<BR>
     * ---
     */
    public void register()
    {
        log.debug("�v���O�C�����������ɃR�[�������register");
        super.setInstance(this);
    }
}
@
