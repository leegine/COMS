head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX����ظ��ăe�X�g(WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/6  ꎉ�(���u) �V�K�쐬
Revision History : 2007/03/20  �g��i(���u) �C��
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestSpecialClassUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest extends
    TestBaseForMock
{
    WEB3AdminAccInfoCampaignAccOpenCompleteRequest l_request = null;
    
    public final String CompaignName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    public final String validCompaignName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest.class);
    
    public WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_request = new WEB3AdminAccInfoCampaignAccOpenCompleteRequest();
        l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        super.setUp();

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testValidate301()
    {
        final String STR_METHOD_NAME = "testValidate301()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "3";
            l_request.validate(); 
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02710,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate301>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate302()
    {
        final String STR_METHOD_NAME = "testValidate302()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02718,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate302>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
 
    /**
     * �L�����y�[�����̂̃`�F�b�N
     * �L�����y�[�����̂������͂̏ꍇ  �A�w�L�����y�[�����̖����̓G���[�x��O���X���[����
     *
     */
    public void testValidate303()
    {
        final String STR_METHOD_NAME = "testValidate303()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "1";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02712,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate303>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * �L�����y�[�����̂̃`�F�b�N
     * �L�����y�[�����̂������͂̏ꍇ(="")  �A�w�L�����y�[�����̖����̓G���[�x��O���X���[����
     * 
    */
    public void testValidate325()
    {
        final String STR_METHOD_NAME = "testValidate325()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "1";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = "";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02712,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate303>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate304()
    {
        final String STR_METHOD_NAME = "testValidate304()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.CompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02709,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate304>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate305()
    {
        final String STR_METHOD_NAME = "testValidate305()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "12";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate305>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate306()
    {
        final String STR_METHOD_NAME = "testValidate306()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01912,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate306>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate307()
    {
        final String STR_METHOD_NAME = "testValidate307()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.targetPeriodFrom = null;
            l_request.commissionCampaignInfo.targetPeriodTo = null;
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02713,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate307>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �����J�ݓ��w�肪�I�����ꂽ�ꍇ�i�����J�݌o�ߊ��ԁi���j!=null && �����J�݌o�ߊ��ԁi���j!=null �j
     * �����J�݌o�ߊ��ԁi���j���O�`�P�Q�̐����ȊO�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B
     *
     */
    public void testValidate3081()
    {
        final String STR_METHOD_NAME = "testValidate3081()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            //�����J�݌o�ߊ��ԁi���j���O�`�P�Q�̐����ȊO�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "13";
            l_request.commissionCampaignInfo.accopenPassPeriodDay="20";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate3081>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate3082()
    {
        final String STR_METHOD_NAME = "testValidate3082()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "-1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02080,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate3082>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate3091()
    {
        final String STR_METHOD_NAME = "testValidate3091()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "32";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate3091>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate3092()
    {
        final String STR_METHOD_NAME = "testValidate3092()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "32";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate3092>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate310()
    {
        final String STR_METHOD_NAME = "testValidate310()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02713,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate310>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ��萑����̍X�C���S���L�������������B
     *�Q-�T-�S�j �����J�ݓ�To!=null�̏ꍇ�A
     *�����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j
     *�����Z�������t���ߋ����t�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B
     *���� �����J�݌o�ߊ��ԁi���j==�O && �����J�݌o�ߊ��ԁi���j==�O �̏ꍇ�A
     */
    public void testValidate311()
    {
        final String STR_METHOD_NAME = "testValidate311()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "0";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "0";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2005/02/05");
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "0";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate311>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate312()
    {
        final String STR_METHOD_NAME = "testValidate312()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/05");
            
            l_request.commissionCampaignInfo.targetPeriodFrom = null;
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02713,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate312>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate313()
    {
        final String STR_METHOD_NAME = "testValidate313()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/05");
            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/05");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/04");
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate313>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �Q-�U-�R�j �Ώۊ���To�̓��t���ߋ����t�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B<BR>
     * �������S�s���ݗ��B
     * ������Case�C���������J�݋敪�̃`�F�b�N
     * 
     * �Q-�U-�Q-�P�j �����J�݋敪�� 1:���������@@2:�M�p���� 3:�敨OP�����@@4:FX�����@@5:�����������@@null �ȊO�̏ꍇ
     * �w�����J�݋敪�G���[�x��O���X���[����B<BR>
     *
     */
    public void testValidate314()
    {
        final String STR_METHOD_NAME = "testValidate314()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/05");
            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "1";
            
            l_request.commissionCampaignInfo.targetPeriodFrom = null;;
            l_request.commissionCampaignInfo.targetPeriodTo = null;
            l_request.commissionCampaignInfo.accountOpenDiv = "6";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02719,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate314>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate315()
    {
        final String STR_METHOD_NAME = "testValidate315()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/04");
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            int l_intMonth = l_datBizDate.getMonth() + 1;
            l_datBizDate.setMonth(l_intMonth);
            int l_intYear = l_datBizDate.getYear() + 1;
            l_datBizDate.setYear(l_intYear);
            l_request.commissionCampaignInfo.targetPeriodTo = l_datBizDate;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02080,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate315>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate316()
    {
        final String STR_METHOD_NAME = "testValidate316()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            int l_intMonth = l_datBizDate.getMonth() + 1;
            l_datBizDate.setMonth(l_intMonth);
            int l_intYear = l_datBizDate.getYear() + 1;
            l_datBizDate.setYear(l_intYear);
            l_request.commissionCampaignInfo.targetPeriodTo = l_datBizDate;

            l_request.commissionCampaignInfo.collectRate = "110";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02082,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate316>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �����C���C���@@�����Q-�U-�Q�j �����J�݋敪�̃`�F�b�N
     *  �����J�݋敪�� 1:���������@@2:�M�p���� 3:�敨OP�����@@4:FX�����@@5:�����������@@null �ȊO�̏ꍇ�A
     *  �w�����J�݋敪�G���[�x��O���X���[����B
     *
     */
//    public void testValidate317()
//    {
//        final String STR_METHOD_NAME = "testValidate317()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            l_request.updateFlag = "0";
//            String[] l_strs = new String[1];
//            l_strs[0] = "01";
//            l_request.commissionCampaignInfo.itemCode = l_strs;
//            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
//            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
//                l_request.commissionCampaignInfo.campaignName.length());
//            
//            l_request.commissionCampaignInfo.branchCode = "123";
//            l_request.commissionCampaignInfo.traderCode = "12345";
//            
//            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
//            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
//            
//            
//            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
//            
//
//            
//            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
//            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
//            
//            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
//            int l_intMonth = l_datBizDate.getMonth() + 1;
//            l_datBizDate.setMonth(l_intMonth);
//            int l_intYear = l_datBizDate.getYear() + 1;
//            l_datBizDate.setYear(l_intYear);
//            l_request.commissionCampaignInfo.targetPeriodTo = l_datBizDate;
//
//            l_request.commissionCampaignInfo.collectRate = "100";
//            l_request.commissionCampaignInfo.accountOpenDiv = "6";
//            l_request.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02719,l_ex.getErrorInfo());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate317>>>>>>>>>>>>>>>test pass !!");
//        } 
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    
    public void testValidate318()
    {
        final String STR_METHOD_NAME = "testValidate318()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            int l_intMonth = l_datBizDate.getMonth() + 1;
            l_datBizDate.setMonth(l_intMonth);
            int l_intYear = l_datBizDate.getYear() + 1;
            l_datBizDate.setYear(l_intYear);
            l_request.commissionCampaignInfo.targetPeriodTo = l_datBizDate;

            l_request.commissionCampaignInfo.collectRate = "10";
            l_request.commissionCampaignInfo.accountOpenDiv = "3";
            
            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/02/07");
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02720,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate318>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate319()
    {
        final String STR_METHOD_NAME = "testValidate319()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            

            
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/06");
            l_request.commissionCampaignInfo.collectRate = "10";
            l_request.commissionCampaignInfo.accountOpenDiv = "3";
            
            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/02/07");
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02720,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate319>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
 
    /**
     * �����g�����J�ݓ�To���ߋ����t�̏ꍇ�A�����J�ݓ��G���[�x��O���X���[����B�g
     * ���������C��case����B
     *
     */
//    public void testValidate320()
//    {
//        final String STR_METHOD_NAME = "testValidate320()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            l_request.updateFlag = "0";
//            String[] l_strs = new String[1];
//            l_strs[0] = "01";
//            l_request.commissionCampaignInfo.itemCode = l_strs;
//            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
//            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
//                l_request.commissionCampaignInfo.campaignName.length());
//            
//            l_request.commissionCampaignInfo.branchCode = "123";
//            l_request.commissionCampaignInfo.traderCode = "12345";
//            
//            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
//            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
//            
//
//            
//            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/06");
//            l_request.commissionCampaignInfo.collectRate = "10";
//            l_request.commissionCampaignInfo.accountOpenDiv = "3";
//            
//            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/01/06");
//            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/01/06");
//            l_request.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02720,l_ex.getErrorInfo());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate320>>>>>>>>>>>>>>>test pass !!");
//        } 
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    public void testValidate321()
    {
        final String STR_METHOD_NAME = "testValidate321()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "2";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            l_request.commissionCampaignInfo.campaignId = null;
            l_request.commissionCampaignInfo.registType = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02716,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate321>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidate322()
    {
        final String STR_METHOD_NAME = "testValidate322()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "1";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            l_request.commissionCampaignInfo.campaignId = "021";
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/06");
            l_request.commissionCampaignInfo.collectRate = "10";
            l_request.commissionCampaignInfo.accountOpenDiv = "3";
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/05");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/08");
            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/02/07");
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/22");
            
            
            l_request.commissionCampaignInfo.registType = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02722,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate322>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate323()
    {
        final String STR_METHOD_NAME = "testValidate323()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "2";
            l_request.commissionCampaignInfo.campaignId = "012";
            l_request.commissionCampaignInfo.registType = "1";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            

            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/05");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/06");
            l_request.commissionCampaignInfo.collectRate = "10";
            l_request.commissionCampaignInfo.accountOpenDiv = "3";
            
            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/02/01");
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/14");
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate323>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate323>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate324()
    {
        final String STR_METHOD_NAME = "testValidate324()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestSpecialClassUtility.testCreateResponse(WEB3AdminAccInfoCampaignAccOpenCompleteRequest.class);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
    
@
