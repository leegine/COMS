head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityLapseTargetOrderConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3AdminEquityLapseTargetOrderConditionTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/12/27�@@��іQ(���u)
*/
package webbroker3.eqtypeadmin.message;


import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * ���������Ώے��������̃e�X�g<BR>
 * @@author ��іQ(���u)
 * @@version 1.0
 */
public class WEB3AdminEquityLapseTargetOrderConditionTest extends TestBaseForMock
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityLapseTargetOrderConditionTest.class);

    public WEB3AdminEquityLapseTargetOrderConditionTest(String name) 
    {
        super(name);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N<BR>
     * �s��R�[�h�ꗗ�`�F�b�N<BR>
     *�R�|�Q�j�@@this.�s��R�[�h�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E"����" <BR>
     * �@@�@@�@@�E"���" <BR>
     * �@@�@@�@@�E"���É�" <BR>
     * �@@�@@�@@�E"����" <BR>
     * �@@�@@�@@�E"�D�y" <BR>
     * �@@�@@�@@�E"NNM" <BR>
     * �@@�@@�@@�E"JASDAQ" <BR>
     * �@@�@@�@@�E"JNX-PTS" <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     */
    public void test_validate_0001()
    {
    	//log
        final String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    	
    	WEB3AdminEquityLapseTargetOrderCondition l_adminEquityLapseTargetOrderCondition = new WEB3AdminEquityLapseTargetOrderCondition();

    	//���X�R�[�h
    	l_adminEquityLapseTargetOrderCondition.branchCode = new String[]{"381"};

    	//�����R�[�h
    	l_adminEquityLapseTargetOrderCondition.productCode = "12345";
    	
    	//�s��R�[�h
    	l_adminEquityLapseTargetOrderCondition.marketList = new String[]{"JNX-PTS"};

        try
        {
        	l_adminEquityLapseTargetOrderCondition.validate();
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,e.getErrorInfo());
        }
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N<BR>
     * �s��R�[�h�ꗗ�`�F�b�N<BR>
     *�R�|�Q�j�@@this.�s��R�[�h�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E"����" <BR>
     * �@@�@@�@@�E"���" <BR>
     * �@@�@@�@@�E"���É�" <BR>
     * �@@�@@�@@�E"����" <BR>
     * �@@�@@�@@�E"�D�y" <BR>
     * �@@�@@�@@�E"NNM" <BR>
     * �@@�@@�@@�E"JASDAQ" <BR>
     * �@@�@@�@@�E"JNX-PTS" <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     */
    public void test_validate_0002()
    {
    	//log
        final String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    	
    	WEB3AdminEquityLapseTargetOrderCondition l_adminEquityLapseTargetOrderCondition = new WEB3AdminEquityLapseTargetOrderCondition();

    	//���X�R�[�h
    	l_adminEquityLapseTargetOrderCondition.branchCode = new String[]{"381"};
    	
    	//�����R�[�h
    	l_adminEquityLapseTargetOrderCondition.productCode = "12345";
    	
    	//�s��R�[�h
    	l_adminEquityLapseTargetOrderCondition.marketList = new String[]{"11"};
    	
    	//����敪
    	l_adminEquityLapseTargetOrderCondition.tradingTypeList = new String[]{"1"};
    	
    	//�ٍϋ敪
    	l_adminEquityLapseTargetOrderCondition.repaymentList = new String[]{ "EQUITY_BUY"};

        try
        {
        	l_adminEquityLapseTargetOrderCondition.validate();
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02445,e.getErrorInfo());
        }
    }

}
@
