head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AcceptLoginServiceImplTest_xhw.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3AcceptLoginServiceImplTest_xhw.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/12 ���G�� (���u) �V�K�쐬
*/
package webbroker3.login.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.LoginRejectIpParams;
import webbroker3.gentrade.data.LoginRejectIpRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AcceptLoginServiceImplTest_xhw extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AcceptLoginServiceImplTest_xhw.class);

    WEB3AcceptLoginServiceImpl l_imple = new WEB3AcceptLoginServiceImpl();

    public WEB3AcceptLoginServiceImplTest_xhw(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * "���X�v���t�@@�����X�e�[�u�����A�ȉ��̑����̃��R�[�h���擾����
     * [�擾����]
     * ���XID�F����.���XID
     * �������F""login.rejectip.check.""�{����.�����o�H�敪
     * �A�ԁF1
     * ���R�[�h�Ȃ�
     * ���@@�ԉ�true"
     * "1)����.���XID = 62421
     * 2)����.�����o�H�敪=1
     * 3)���󕔓X�v���t�@@�����X�e�[�u��"  ���@@�ԉ�true
     */
    public void testIsRejectIp_case001()
    {
        final String STR_METHOD_NAME = " testIsRejectIp_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_lngBranchId = 62421;
        String l_strInstitutionCode = "123";
        String l_strRootDiv = "1";
        String l_strIpAddress = "xuhongwei@@sionocom.cn";
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            boolean l_blnReturn =
                l_imple.isRejectIp(l_lngBranchId, l_strInstitutionCode, l_strRootDiv, l_strIpAddress);
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "���X�v���t�@@�����X�e�[�u�����A�ȉ��̑����̃��R�[�h���擾����
     * [�擾����]
     * ���XID�F����.���XID
     * �������F""login.rejectip.check.""�{����.�����o�H�敪
     * �A�ԁF1
     * ���R�[�h�̒l���u�`�F�b�N�Ȃ��v�̏ꍇ
     * "1)����.���XID = 62421
     * 2)����.�����o�H�敪=1
     * 4)�����X�v���t�@@�����X�e�[�u�����������ɝ�,���e�@@���F
     * ���XID=62421
     * ������=""login.rejectip.check.1""
     * �A�ԁF1
     * �l=""0""
     * ���@@�ԉ�true
     */
    public void testIsRejectIp_case002()
    {
        final String STR_METHOD_NAME = " testIsRejectIp_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_lngBranchId = 62421;
        String l_strInstitutionCode = "123";
        String l_strName = "login.rejectip.check.1";
        String l_strValue = "1";
        String l_strRootDiv = "1";
        String l_strIpAddress = "xuhongwei@@sionocom.cn";

        try
        {
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_lngBranchId);
            l_branchPreferencesParams.setName(l_strName);
            l_branchPreferencesParams.setValue(l_strValue);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            boolean l_blnReturn =
                l_imple.isRejectIp(l_lngBranchId, l_strInstitutionCode, l_strRootDiv, l_strIpAddress);
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "�擾�������R�[�h�̒l���u�`�F�b�N����v
     * ���O�C������IP tbl���������A�Y��rec����ꍇ��true
     * "1)����.���XID = 62421
     * 2)����.�����o�H�敪=1
     * 3�j����.IP�A�h���X=""xuhongwei@@sinocom.cn
     * 4)�����X�v���t�@@�����X�e�[�u�����������ɝ�,���e�@@���F
     * ���XID=62421
     * ������=""login.rejectip.check.1""
     * �A�ԁF1
     * �l=""1""
     * 5)�����O�C������IP�\�������꞊�ɝ�,���e�@@���F
     * �،���ЃR�[�h�F62421
     * IP�A�h���X�F""xuhongwei@@sinocom.cn
     * �X�e�[�^�X�F0" ���@@�ԉ�true
     */
    public void testIsRejectIp_case003()
    {
        final String STR_METHOD_NAME = " testIsRejectIp_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_lngBranchId = 62421;
        String l_strInstitutionCode = "0D";
        String l_strName = "login.rejectip.check.1";
        String l_strValue = "1";
        String l_strRootDiv = "1";
        String l_strIpAddress = "127.0.0.1";

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_lngBranchId);
            l_branchPreferencesParams.setName(l_strName);
            l_branchPreferencesParams.setValue(l_strValue);
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            LoginRejectIpParams l_params = new LoginRejectIpParams();
            l_params.setLoginRejectId(1001);
            //1 �،���ЃR�[�hinstitution_codeVARCHAR22NOT
            l_params.setInstitutionCode("0D");
            //2 IP�A�h���Xip_addressVARCHAR215NOT
            l_params.setIpAddress("127.0.0.1");
            //3 �X�e�[�^�XstatusVARCHAR21NOT
            l_params.setStatus("0");
            
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(GtlUtils.getSystemTimestamp());
            calendar.add(Calendar.DAY_OF_MONTH, -1 );            
            //4 �K�p�J�n����appli_start_timestampDATENOT
            l_params.setAppliStartTimestamp(calendar.getTime());            
            calendar.add(Calendar.DAY_OF_MONTH, 2 );    
            //5 �K�p�I������appli_end_timestampDATENOT
            l_params.setAppliEndTimestamp(calendar.getTime());
            
            l_params.setRegistDiv("1");
            l_params.setUpdatedDiv("1");
            
            //6 �X�V�҃R�[�hlast_updaterVARCHAR220NOT
            l_params.setLastUpdater("12");
            //7 �쐬���tcreated_timestampDATENOT
            l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //8 �X�V���tlast_updated_timestampDATENOT
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_params);
            boolean l_blnReturn =
                l_imple.isRejectIp(l_lngBranchId, l_strInstitutionCode, l_strRootDiv, l_strIpAddress);
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "�擾�������R�[�h�̒l���u�`�F�b�N����v
     * ���O�C������IP tbl���������A�Y��rec�s���ݏꍇ��false
     * "1)����.���XID = 62421
     * 2)����.�����o�H�敪=1
     * 3�j����.IP�A�h���X=""xuhongwei@@sinocom.cn""
     * 4)�����X�v���t�@@�����X�e�[�u�����������ɝ�,���e�@@���F
     * ���XID=62421
     * ������=""login.rejectip.check.1""
     * �A�ԁF1
     * �l=""1""
     * 5)���󃍃O�C������IP�\
     * ���@@�ԉ�false
     */
    public void testIsRejectIp_case004()
    {
        final String STR_METHOD_NAME = " testIsRejectIp_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_lngBranchId = 62421;
        String l_strInstitutionCode = "123";
        String l_strName = "login.rejectip.check.1";
        String l_strValue = "1";
        String l_strRootDiv = "1";
        String l_strIpAddress = "xuhongwei@@sionocom.cn";

        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_lngBranchId);
            l_branchPreferencesParams.setName(l_strName);
            l_branchPreferencesParams.setValue(l_strValue);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            boolean l_blnReturn =
                l_imple.isRejectIp(l_lngBranchId, l_strInstitutionCode, l_strRootDiv, l_strIpAddress);
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
