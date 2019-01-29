head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.31.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoMailAddressDownloadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

import webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo;
import webbroker3.gentrade.data.AccountMailAddressParams;
import webbroker3.gentrade.data.MailAssortmentParams;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoMailAddressDownloadServiceImplTest extends JunitTestBase
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressDownloadServiceImplTest.class);

    public WEB3AdminAccInfoMailAddressDownloadServiceImplTest(String arg0)
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

    public void testGetDownloadData_C0001()
    {
        final String STR_METHOD_NAME = "testGetDownloadData_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1000051");
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setFamilyName("��");
            l_mainAccountParams.setEmailLastUpdater("1000051");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            String[] l_strBranchCodes = new String[]{"381", "382", "383"};
            
            WEB3AdminAccInfoMailAddressDownloadServiceImpl l_impl = new WEB3AdminAccInfoMailAddressDownloadServiceImpl();
            WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
                l_impl.getDownloadData("0D", l_strBranchCodes, "100000", "100006", "1", "1");//�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ
            WEB3AccInfoAccountMailAddressInfo l_accountMailAddressInfo = new WEB3AccInfoAccountMailAddressInfo();
            l_accountMailAddressInfo = l_mailAddressInfos[0];
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq�}�X�^�s.�،���ЃR�[�h
            assertEquals("100005", l_accountMailAddressInfo.accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��", l_accountMailAddressInfo.accountName);
            //�ڋq���[���A�h���X���.�X�V�҃R�[�h = (*1)
            //(*1)�ڋq�}�X�^�s.email�A�h���X�X�V�҃R�[�h == �ڋq�}�X�^�s.�����R�[�h�j�̏ꍇ�A�����R�[�h�̍�6byte�B
            assertEquals("100005", l_accountMailAddressInfo.updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�ڋq�}�X�^�s.�ē����[�����M�t���O = �v �̏ꍇ 1 
            assertEquals("1", l_accountMailAddressInfo.sendFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //
    public void testGetDownloadData_C0002()
    {
        final String STR_METHOD_NAME = "testGetDownloadData_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1000051");
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);//.�ē����[�����M�t���O
            l_mainAccountParams.setFamilyName("��");
            l_mainAccountParams.setEmailLastUpdater("100009");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            String[] l_strBranchCodes = new String[]{"381", "382", "383"};
            
            WEB3AdminAccInfoMailAddressDownloadServiceImpl l_impl = new WEB3AdminAccInfoMailAddressDownloadServiceImpl();
            WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
                l_impl.getDownloadData("0D", l_strBranchCodes, "100000", "100006", "0", "1");//�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ
            WEB3AccInfoAccountMailAddressInfo l_accountMailAddressInfo = new WEB3AccInfoAccountMailAddressInfo();
            l_accountMailAddressInfo = l_mailAddressInfos[0];
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq�}�X�^�s.�،���ЃR�[�h
            assertEquals("100005", l_accountMailAddressInfo.accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��", l_accountMailAddressInfo.accountName);
            //�ڋq���[���A�h���X���.�X�V�҃R�[�h = (*1)
            //(*1)�ڋq�}�X�^�s.email�A�h���X�X�V�҃R�[�h == �ڋq�}�X�^�s.�����R�[�h�j�̏ꍇ�A�����R�[�h�̍�6byte�B
            assertEquals("100009", l_accountMailAddressInfo.updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�ڋq�}�X�^�s.�ē����[�����M�t���O = �v �̏ꍇ 1
            assertEquals("0", l_accountMailAddressInfo.sendFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadData_C0003()
    {
        final String STR_METHOD_NAME = "testGetDownloadData_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1000051");
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);//.�ē����[�����M�t���O
            l_mainAccountParams.setFamilyName("��");
            l_mainAccountParams.setEmailLastUpdater("100009");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            String[] l_strBranchCodes = new String[]{"381", "382", "383"};
            
            WEB3AdminAccInfoMailAddressDownloadServiceImpl l_impl = new WEB3AdminAccInfoMailAddressDownloadServiceImpl();
            WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
                l_impl.getDownloadData("0D", l_strBranchCodes, "100000", "100006", null, "1");//�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ
            WEB3AccInfoAccountMailAddressInfo l_accountMailAddressInfo = new WEB3AccInfoAccountMailAddressInfo();
            l_accountMailAddressInfo = l_mailAddressInfos[0];
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq�}�X�^�s.�،���ЃR�[�h
            assertEquals("100005", l_accountMailAddressInfo.accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��", l_accountMailAddressInfo.accountName);
            //�ڋq���[���A�h���X���.�X�V�҃R�[�h = (*1)
            //(*1)�ڋq�}�X�^�s.email�A�h���X�X�V�҃R�[�h == �ڋq�}�X�^�s.�����R�[�h�j�̏ꍇ�A�����R�[�h�̍�6byte�B
            assertEquals("100009", l_accountMailAddressInfo.updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�ڋq�}�X�^�s.�ē����[�����M�t���O = �v �̏ꍇ 1
            assertEquals("0", l_accountMailAddressInfo.sendFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadData_C0004()
    {
        final String STR_METHOD_NAME = "testGetDownloadData_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            

            
            String[] l_strBranchCodes = new String[]{"381", "382", "383"};
            
            WEB3AdminAccInfoMailAddressDownloadServiceImpl l_impl = new WEB3AdminAccInfoMailAddressDownloadServiceImpl();
            WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
                l_impl.getDownloadData("0D", l_strBranchCodes, "100000", "100006", "1", "2");//�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100001",  l_mailAddressInfos[0].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[0].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("001",  l_mailAddressInfos[0].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("1",  l_mailAddressInfos[0].sendFlag);
            
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100002",  l_mailAddressInfos[1].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[1].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("002",  l_mailAddressInfos[1].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("1",  l_mailAddressInfos[1].sendFlag);
            
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100005",  l_mailAddressInfos[2].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[2].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("005",  l_mailAddressInfos[2].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("1",  l_mailAddressInfos[2].sendFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadData_C0005()
    {
        final String STR_METHOD_NAME = "testGetDownloadData_C0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            
            String[] l_strBranchCodes = new String[]{"381", "382", "383"};
            
            WEB3AdminAccInfoMailAddressDownloadServiceImpl l_impl = new WEB3AdminAccInfoMailAddressDownloadServiceImpl();
            WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
                l_impl.getDownloadData("0D", l_strBranchCodes, "100000", "100006", "0", "2");//�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100003",  l_mailAddressInfos[0].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[0].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("003",  l_mailAddressInfos[0].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("0",  l_mailAddressInfos[0].sendFlag);
            
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100004",  l_mailAddressInfos[1].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[1].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("004",  l_mailAddressInfos[1].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("0",  l_mailAddressInfos[1].sendFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadData_C0006()
    {
        final String STR_METHOD_NAME = "testGetDownloadData_C0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            
            String[] l_strBranchCodes = new String[]{"381", "382", "383"};
            
            WEB3AdminAccInfoMailAddressDownloadServiceImpl l_impl = new WEB3AdminAccInfoMailAddressDownloadServiceImpl();
            WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
                l_impl.getDownloadData("0D", l_strBranchCodes, "100000", "100006", null, "2");//�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100001",  l_mailAddressInfos[0].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[0].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("001",  l_mailAddressInfos[0].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("1",  l_mailAddressInfos[0].sendFlag);
            
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100002",  l_mailAddressInfos[1].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[1].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("002",  l_mailAddressInfos[1].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("1",  l_mailAddressInfos[1].sendFlag);
            
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100003",  l_mailAddressInfos[2].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[2].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("003",  l_mailAddressInfos[2].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("0",  l_mailAddressInfos[2].sendFlag);
            
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100004",  l_mailAddressInfos[3].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[3].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("004",  l_mailAddressInfos[3].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("0",  l_mailAddressInfos[3].sendFlag);
            
            //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
            assertEquals("100005",  l_mailAddressInfos[4].accountCode);
            //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p
            assertEquals("��",  l_mailAddressInfos[4].accountName);
            //(�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
            assertEquals("005",  l_mailAddressInfos[4].updaterCode);
            //�ڋq���[���A�h���X���.���M�t���O = �i*2�j
            //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�y
            //�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
            //���R�[�h������ �̏ꍇ1
            assertEquals("1",  l_mailAddressInfos[4].sendFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void initData()
    {
        try
        {
            //MailAssortmentRow
            TestDBUtility.deleteAll(MailAssortmentParams.TYPE);
            MailAssortmentParams l_MailAssortmentParams1 = new MailAssortmentParams();
            l_MailAssortmentParams1.setInstitutionCode("0D");
            l_MailAssortmentParams1.setBranchCode("381");
            l_MailAssortmentParams1.setAccountCode("1000011");
            l_MailAssortmentParams1.setEmailAddressNumber(1);
            l_MailAssortmentParams1.setMailAssortmentDiv("6");
            l_MailAssortmentParams1.setLastUpdater("111");
            l_MailAssortmentParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_MailAssortmentParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_MailAssortmentParams1);
            MailAssortmentParams l_MailAssortmentParams2 = new MailAssortmentParams();
            l_MailAssortmentParams2.setInstitutionCode("0D");
            l_MailAssortmentParams2.setBranchCode("381");
            l_MailAssortmentParams2.setAccountCode("1000021");
            l_MailAssortmentParams2.setEmailAddressNumber(1);
            l_MailAssortmentParams2.setMailAssortmentDiv("6");
            l_MailAssortmentParams2.setLastUpdater("111");
            l_MailAssortmentParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_MailAssortmentParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_MailAssortmentParams2);
            MailAssortmentParams l_MailAssortmentParams3 = new MailAssortmentParams();
            l_MailAssortmentParams3.setInstitutionCode("0D");
            l_MailAssortmentParams3.setBranchCode("381");
            l_MailAssortmentParams3.setAccountCode("1000031");
            l_MailAssortmentParams3.setEmailAddressNumber(1);
            l_MailAssortmentParams3.setMailAssortmentDiv("5");
            l_MailAssortmentParams3.setLastUpdater("111");
            l_MailAssortmentParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_MailAssortmentParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_MailAssortmentParams3);
            MailAssortmentParams l_MailAssortmentParams4 = new MailAssortmentParams();
            l_MailAssortmentParams4.setInstitutionCode("0D");
            l_MailAssortmentParams4.setBranchCode("381");
            l_MailAssortmentParams4.setAccountCode("1000041");
            l_MailAssortmentParams4.setEmailAddressNumber(1);
            l_MailAssortmentParams4.setMailAssortmentDiv("5");
            l_MailAssortmentParams4.setLastUpdater("111");
            l_MailAssortmentParams4.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_MailAssortmentParams4.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_MailAssortmentParams4);
            MailAssortmentParams l_MailAssortmentParams5 = new MailAssortmentParams();
            l_MailAssortmentParams5.setInstitutionCode("0D");
            l_MailAssortmentParams5.setBranchCode("381");
            l_MailAssortmentParams5.setAccountCode("1000051");
            l_MailAssortmentParams5.setEmailAddressNumber(1);
            l_MailAssortmentParams5.setMailAssortmentDiv("6");
            l_MailAssortmentParams5.setLastUpdater("111");
            l_MailAssortmentParams5.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_MailAssortmentParams5.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_MailAssortmentParams5);
            MailAssortmentParams l_MailAssortmentParams6 = new MailAssortmentParams();
            l_MailAssortmentParams6.setInstitutionCode("0D");
            l_MailAssortmentParams6.setBranchCode("381");
            l_MailAssortmentParams6.setAccountCode("1000081");
            l_MailAssortmentParams6.setEmailAddressNumber(1);
            l_MailAssortmentParams6.setMailAssortmentDiv("6");
            l_MailAssortmentParams6.setLastUpdater("111");
            l_MailAssortmentParams6.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_MailAssortmentParams6.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_MailAssortmentParams6);
            
            TestDBUtility.deleteAll(AccountMailAddressParams.TYPE);
            AccountMailAddressParams l_AccountMailAddressParams1 = new AccountMailAddressParams();
            l_AccountMailAddressParams1.setInstitutionCode("0D");
            l_AccountMailAddressParams1.setBranchCode("381");   
            l_AccountMailAddressParams1.setAccountCode("1000011");
            l_AccountMailAddressParams1.setEmailAddressNumber(1);
            l_AccountMailAddressParams1.setAddressDiv("1");
            l_AccountMailAddressParams1.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams1.setEmailLastUpdater("001");
            l_AccountMailAddressParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams1);
            AccountMailAddressParams l_AccountMailAddressParams2 = new AccountMailAddressParams();
            l_AccountMailAddressParams2.setInstitutionCode("0D");
            l_AccountMailAddressParams2.setBranchCode("381");   
            l_AccountMailAddressParams2.setAccountCode("1000021");
            l_AccountMailAddressParams2.setEmailAddressNumber(1);
            l_AccountMailAddressParams2.setAddressDiv("1");
            l_AccountMailAddressParams2.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams2.setEmailLastUpdater("002");
            l_AccountMailAddressParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams2);
            AccountMailAddressParams l_AccountMailAddressParams3 = new AccountMailAddressParams();
            l_AccountMailAddressParams3.setInstitutionCode("0D");
            l_AccountMailAddressParams3.setBranchCode("381");   
            l_AccountMailAddressParams3.setAccountCode("1000031");
            l_AccountMailAddressParams3.setEmailAddressNumber(1);
            l_AccountMailAddressParams3.setAddressDiv("1");
            l_AccountMailAddressParams3.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams3.setEmailLastUpdater("003");
            l_AccountMailAddressParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams3);
            AccountMailAddressParams l_AccountMailAddressParams4 = new AccountMailAddressParams();
            l_AccountMailAddressParams4.setInstitutionCode("0D");
            l_AccountMailAddressParams4.setBranchCode("381");   
            l_AccountMailAddressParams4.setAccountCode("1000041");
            l_AccountMailAddressParams4.setEmailAddressNumber(1);
            l_AccountMailAddressParams4.setAddressDiv("1");
            l_AccountMailAddressParams4.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams4.setEmailLastUpdater("004");
            l_AccountMailAddressParams4.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams4.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams4);
            AccountMailAddressParams l_AccountMailAddressParams5 = new AccountMailAddressParams();
            l_AccountMailAddressParams5.setInstitutionCode("0D");
            l_AccountMailAddressParams5.setBranchCode("381");   
            l_AccountMailAddressParams5.setAccountCode("1000051");
            l_AccountMailAddressParams5.setEmailAddressNumber(1);
            l_AccountMailAddressParams5.setAddressDiv("1");
            l_AccountMailAddressParams5.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams5.setEmailLastUpdater("005");
            l_AccountMailAddressParams5.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams5.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams5);
            AccountMailAddressParams l_AccountMailAddressParams6 = new AccountMailAddressParams();
            l_AccountMailAddressParams6.setInstitutionCode("0D");
            l_AccountMailAddressParams6.setBranchCode("381");   
            l_AccountMailAddressParams6.setAccountCode("1000081");
            l_AccountMailAddressParams6.setEmailAddressNumber(1);
            l_AccountMailAddressParams6.setAddressDiv("1");
            l_AccountMailAddressParams6.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams6.setEmailLastUpdater("006");
            l_AccountMailAddressParams6.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams6.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams6);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(333812512241L);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setBranchCode("381");
            l_mainAccountParams1.setAccountCode("1000011");
            l_mainAccountParams1.setInformationMailFlag(BooleanEnum.FALSE);//.�ē����[�����M�t���O
            l_mainAccountParams1.setFamilyName("��");
            l_mainAccountParams1.setEmailLastUpdater("100009");
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(333812512242L);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setBranchCode("381");
            l_mainAccountParams2.setAccountCode("1000021");
            l_mainAccountParams2.setInformationMailFlag(BooleanEnum.FALSE);//.�ē����[�����M�t���O
            l_mainAccountParams2.setFamilyName("��");
            l_mainAccountParams2.setEmailLastUpdater("100009");
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            MainAccountParams l_mainAccountParams3 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams3.setAccountId(333812512243L);
            l_mainAccountParams3.setInstitutionCode("0D");
            l_mainAccountParams3.setBranchCode("381");
            l_mainAccountParams3.setAccountCode("1000031");
            l_mainAccountParams3.setInformationMailFlag(BooleanEnum.FALSE);//.�ē����[�����M�t���O
            l_mainAccountParams3.setFamilyName("��");
            l_mainAccountParams3.setEmailLastUpdater("100009");
            TestDBUtility.insertWithDel(l_mainAccountParams3);
            MainAccountParams l_mainAccountParams4 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams4.setAccountId(333812512244L);
            l_mainAccountParams4.setInstitutionCode("0D");
            l_mainAccountParams4.setBranchCode("381");
            l_mainAccountParams4.setAccountCode("1000041");
            l_mainAccountParams4.setInformationMailFlag(BooleanEnum.FALSE);//.�ē����[�����M�t���O
            l_mainAccountParams4.setFamilyName("��");
            l_mainAccountParams4.setEmailLastUpdater("100009");
            TestDBUtility.insertWithDel(l_mainAccountParams4);
            MainAccountParams l_mainAccountParams5 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams5.setAccountId(333812512245L);
            l_mainAccountParams5.setInstitutionCode("0D");
            l_mainAccountParams5.setBranchCode("381");
            l_mainAccountParams5.setAccountCode("1000051");
            l_mainAccountParams5.setInformationMailFlag(BooleanEnum.FALSE);//.�ē����[�����M�t���O
            l_mainAccountParams5.setFamilyName("��");
            l_mainAccountParams5.setEmailLastUpdater("100009");
            TestDBUtility.insertWithDel(l_mainAccountParams5);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
