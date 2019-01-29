head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEqAttentionInfoRefSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��Ɖ�\�[�g�L�[(WEB3AdminEqAttentionInfoRefSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �И��� (���u) �V�K�쐬 ���f��No.217
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEqAttentionInfoRefSortKeyTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEqAttentionInfoRefSortKeyTest.class);
    
    public WEB3AdminEqAttentionInfoRefSortKeyTest(String name)
    {
        super(name);
    }

    /**
     * this.�L�[���� == null�̏ꍇ
     * �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKey.keyItem = null;
        try
        {
            l_sortKey.validate();
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
     * this.�L�[���� != null�̏ꍇ
     * �ݒ肳��Ă�����A�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKey.keyItem = "attentionInfoType11";
        try
        {
            l_sortKey.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�L�[���� = attentionInfoType
     * this.�����^�~����null�̏ꍇ�A
     * �u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKey.keyItem = "attentionInfoType";
        l_sortKey.ascDesc = null;
        try
        {
            l_sortKey.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�L�[���� = attentionInfoDivCode
     * this.�����^�~��!��null�̏ꍇ�A
     * �@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B 
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKey.keyItem = "attentionInfoDivCode";
        l_sortKey.ascDesc = "C";
        try
        {
            l_sortKey.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�L�[���� = infoOccuredDate
     * this.�����^�~�� = A
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKey.keyItem = "infoOccuredDate";
        l_sortKey.ascDesc = "A";
        try
        {
            l_sortKey.validate();
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
    
    /**
     * this.�L�[���� = productCode
     * this.�����^�~�� = D
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKey.keyItem = "productCode";
        l_sortKey.ascDesc = "D";
        try
        {
            l_sortKey.validate();
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
    
    /**
     * this.�L�[���� = marketCode
     * this.�����^�~�� = D
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKey.keyItem = "marketCode";
        l_sortKey.ascDesc = "D";
        try
        {
            l_sortKey.validate();
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
