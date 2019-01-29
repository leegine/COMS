head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqProductForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O����������(WEB3FeqProductForMock)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  ���Ō�(���u) �V�K�쐬
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O����������) <BR>
 * �O����������
 * 
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3FeqProductForMock extends WEB3FeqProduct
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqProductForMock.class);
    
    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FeqProductForMock(ProductRow l_prow)
        throws DataQueryException, DataNetworkException
    {
        super(l_prow);
    }

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FeqProductForMock(FeqProductRow l_row)
        throws DataQueryException, DataNetworkException
    {
        super(l_row);
    }   
    
    /**
     * (get�������) <BR>
     * �igetFeqTradedProduct�j <BR>
     * ����������擾����B <BR>
     *  <BR>
     * �P�j�@@�s��R�[�h���Z�b�g <BR>
     * �@@������ԊǗ�.reset�s��R�[�h()�ɂāA�s����Z�b�g����B <BR>
     *  <BR>
     * �@@[reset�s��R�[�h()�Ɏw�肷�����] <BR>
     * �@@�s��R�[�h�F�@@this.get�s��R�[�h() <BR>
     *  <BR>
     * �Q�j�@@��������擾 <BR>
     * �@@�O�������v���_�N�g�}�l�[�W��.getFeqTradedProduct()�ɂ� <BR>
     * �@@�O����������������擾���ԋp����B <BR>
     *  <BR>
     * �@@[getFeqTradedProduct()�Ɏw�肷�����] <BR>
     * �@@�،���ЁF�@@this.getInstitution() <BR>
     * �@@�����R�[�h�F�@@this.getProductCode() <BR>
     * �@@�s��R�[�h�F�@@this.get�s��R�[�h() <BR>
     * @@return WEB3FeqTradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 4282C53802E1
     */
    public WEB3FeqTradedProduct getFeqTradedProduct() throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqProduct",
            "getFeqTradedProduct",
            new Class[] {}))
        {
        	log.debug("webbroker3.feq.WEB3FeqProductForMock.getFeqTradedProduct()");
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProduct",
                    "getFeqTradedProduct",
                    new Class[] {}).asWEB3BaseException();
        	
        	return (WEB3FeqTradedProduct)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqProduct",
                "getFeqTradedProduct",
                new Class[] {}).asObject();       
        }
        return super.getFeqTradedProduct();
    }
    
    /**
     * (get�ʉ�) <BR>
     * �ʉ݂��擾����B <BR>
     *  <BR>
     * �ʉ݃I�u�W�F�N�g�𐶐����ԋp����B <BR>
     *  <BR>
     * [�R���X�g���N�^�Ɏw�肷�����] <BR>
     * �،���ЃR�[�h�F�@@getInstitution().getInstitutionCode() <BR>
     * �ʉ݃R�[�h�F�@@get�ʉ݃R�[�h() <BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 42898A9001D4
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqProduct",
            "getCurrency",
            new Class[] {}))
        {
        	log.debug("webbroker3.feq.WEB3FeqProductForMock.getCurrency()");
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProduct",
                    "getCurrency",
                    new Class[] {}).asWEB3BaseException();
        	
        	return (WEB3GentradeCurrency)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqProduct",
                "getCurrency",
                new Class[] {}).asObject();            
        }
        return super.getCurrency();
    }
    public String getStandardName()
    {
        throw new UnsupportedOperationException("getStandardName�͗��p�ł��Ȃ��I�`�[�����[�_�֘A�����Ă��������I");
    }
}
@
