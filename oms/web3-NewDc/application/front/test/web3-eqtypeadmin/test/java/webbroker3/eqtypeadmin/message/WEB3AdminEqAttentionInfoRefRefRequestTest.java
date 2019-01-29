head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEqAttentionInfoRefRefRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ӏ��Ɖ�N�G�X�g(WEB3AdminEqAttentionInfoRefRefRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �И��� (���u) �V�K�쐬 ���f��No.217,���f��No.221-224
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEqAttentionInfoRefRefRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEqAttentionInfoRefRefRequestTest.class);
    
    public WEB3AdminEqAttentionInfoRefRefRequestTest(String name)
    {
        super(name);
    }

    /**
     * this.���ӏ���� != null�̏ꍇ
     * �u���ӏ���ʂ�����`�̒l�v�̗�O���X���[����B 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "0";
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03147, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.���ӏ���� != null�̏ꍇ
     * �E"������" 
     * this.���ӏ��敪�R�[�h != null�̏ꍇ
     * �u���ӏ��敪�R�[�h������`�̒l�v�̗�O���X���[����B 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A000";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03149, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.���ӏ���� != null�@@���� 
     * this.���ӏ��敪�R�[�h != null �̏ꍇ
     * this.���ӏ���� �� "�t���[�t�H�[�}�b�g" ���� 
     * this.���ӏ��敪�R�[�h �� "�t���[�t�H�[�}�b�g"�̏ꍇ�A 
     * �u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B 
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "3";
        l_request.attentionInfoDivCode = "A001";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.���ӏ���� != null�@@���� 
     * this.���ӏ��敪�R�[�h != null �̏ꍇ
     * this.���ӏ���� �� "�l���������" ����
     * this.���ӏ��敪�R�[�h �� "�V�K�������̏��l���t�����ꍇ"�̏ꍇ�A
     * �u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B 
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "2";
        l_request.attentionInfoDivCode = "A002";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.���ӏ���� �� "������" �̏ꍇ�A 
     * this.���ӏ��敪�R�[�h �� "�t���[�t�H�[�}�b�g"�@@ 
     * ���邢�́Athis.���ӏ��敪�R�[�h �� "�V�K�������̏��l���t�����ꍇ"�@@ 
     * �u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B 
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A081";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.���ӏ���� �� "������" �̏ꍇ�A 
     * this.���ӏ��敪�R�[�h �� "�t���[�t�H�[�}�b�g"�@@ 
     * ���邢�́Athis.���ӏ��敪�R�[�h �� "�V�K�������̏��l���t�����ꍇ"�@@ 
     * �u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B 
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A031";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h != null�̏ꍇ 
     * �u�s��R�[�h������`�̒l�v�̗�O���X���[����B 
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A003";
        l_request.marketCode = "0";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 1
     * this.�����R�[�h != null�̏ꍇ 
     * �Ethis.�����R�[�h != ���� 
     * �u�����R�[�h�G���[�v�̗�O���X���[����B 
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A004";
        l_request.marketCode = "1";
        l_request.productCode = "aaa";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 2
     * this.�����R�[�h != null�̏ꍇ 
     * �Ethis.�����R�[�h.length != 5 
     * �u�����R�[�h�G���[�v�̗�O���X���[����B 
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A005";
        l_request.marketCode = "2";
        l_request.productCode = "111111";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 3
     * this.�����R�[�h = 11111
     * this.�L���� != null�̏ꍇ
     * �u�L�����G���[�v�̗�O���X���[����B 
     */
    public void test_validate_0010()
    {
        String STR_METHOD_NAME = "test_validate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A006";
        l_request.marketCode = "3";
        l_request.productCode = "11111";
        l_request.validDate = "20022222";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03150, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 6
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From != null�̏ꍇ
     * �u��񔭐�����From�G���[�v�̗�O���X���[����B
     */
    public void test_validate_0011()
    {
        String STR_METHOD_NAME = "test_validate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A007";
        l_request.marketCode = "6";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "2009010915:00:00";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03151, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 8
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To != null�̏ꍇ
     * �u��񔭐�����To�G���[�v�̗�O���X���[����B
     */
    public void test_validate_0012()
    {
        String STR_METHOD_NAME = "test_validate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A008";
        l_request.marketCode = "8";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "2009010915:00:00";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03152, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 6
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From != null�@@���@@this.��񔭐�����To != null�̏ꍇ
     * this.��񔭐�����From > this.��񔭐�����To�̏ꍇ
     * �u���͎��Ԑ������G���[�v�̗�O���X���[����B 
     */
    public void test_validate_0013()
    {
        String STR_METHOD_NAME = "test_validate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A011";
        l_request.marketCode = "6";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090110150000";
        l_request.infoOccuredDateTo = "20090109150000";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01481, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 1
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ == null�ł������ꍇ�A
     * �u�\�[�g�L�[��null�v�̗�O���X���[����B 
     */
    public void test_validate_0014()
    {
        String STR_METHOD_NAME = "test_validate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "9";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = null;
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 10
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * �\�[�g�L�[.validate()���R�[������B 
     */
    public void test_validate_0015()
    {
        String STR_METHOD_NAME = "test_validate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "10";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{l_sortKey};
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ� == null�ł������ꍇ�A 
     * �u�\���y�[�W�ԍ���null�v�̗�O���X���[����B 
     */
    public void test_validate_0016()
    {
        String STR_METHOD_NAME = "test_validate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = null;
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A 
     * �u�\���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
     */
    public void test_validate_0017()
    {
        String STR_METHOD_NAME = "test_validate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "1";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "a";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A 
     * �u�\���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
     */
    public void test_validate_0018()
    {
        String STR_METHOD_NAME = "test_validate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "-1";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A 
     * �u�\���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
     */
    public void test_validate_0019()
    {
        String STR_METHOD_NAME = "test_validate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "0";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ� = 1
     * this.�y�[�W���\���s�� == null�ł������ꍇ�A 
     * �u�y�[�W���\���s����null�v�̗�O���X���[����B
     */
    public void test_validate_0020()
    {
        String STR_METHOD_NAME = "test_validate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = null;
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ� = 1
     * this.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A 
     * �u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
     */
    public void test_validate_0021()
    {
        String STR_METHOD_NAME = "test_validate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = "A";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ� = 1
     * this.�y�[�W���\���s�� <= 0�ł������ꍇ�A  
     * �u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B 
     */
    public void test_validate_0022()
    {
        String STR_METHOD_NAME = "test_validate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = "-1";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ� = 1
     * this.�y�[�W���\���s�� <= 0�ł������ꍇ�A  
     * �u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B 
     */
    public void test_validate_0023()
    {
        String STR_METHOD_NAME = "test_validate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = "0";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�s��R�[�h = 11
     * this.�����R�[�h = 11111
     * this.�L���� = 20020202
     * this.��񔭐�����From = 20090109150000
     * this.��񔭐�����To = 20090109150000
     * this.�\�[�g�L�[ != null�ł������ꍇ�A
     * this.�\���y�[�W�ԍ� = 1
     * this.�y�[�W���\���s�� = 1 
     */
    public void test_validate_0024()
    {
        String STR_METHOD_NAME = "test_validate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        try
        {
            l_request.validate();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
