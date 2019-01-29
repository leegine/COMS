head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������t�����������N�G�X�g�N���X(WEB3RuitoBuyCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
                   2004/12/03 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * �ݐϓ������t�����������N�G�X�g�N���X<BR>
 */
public class WEB3RuitoBuyCompleteRequest extends WEB3RuitoCommonRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_buy_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200400031539L;    
    
	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */    
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoBuyCompleteRequest.class);
		
    /**
     * �m�F��������<BR>
     * <BR>
     * �m�F���X�|���X�̏����Ŏg�p�����l���i�[����B<BR>
     */
    public Date checkDate;

    /**
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * �w����@@<BR>
     * 3�F���z�A4�F����<BR>
     */
    public String specifyDiv;

    /**
     * ����ID<BR>
     */
    public String orderId;
    
    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922CC0033C
     */
    public WEB3RuitoBuyCompleteRequest()
    {
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@this.�w����@@�̒l���A"���z" "����"�ȊO�̒l�ł���ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *       class    : WEB3BusinessLayerException<BR>
     *       tag      : BUSINESS_ERROR_00073<BR>
     * <BR>
     * �Q�j�@@�������ʃ`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�������� �� null �ł���ꍇ�A��O���X���[����B<BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00074<BR>
     * �@@�Q�|�Q�jthis.�������� �� ���� �ł���ꍇ�A��O���X���[����B<BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00075<BR>
     * �@@�Q�|�R�jthis.�������� �� 0 �ł���ꍇ�A��O���X���[����B<BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00076<BR>
     * �@@�Q�|�S�jthis.�������� �� 11�� �ł���ꍇ�A��O���X���[����B<BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00077<BR>
     * <BR>
     * �R�j�@@�m�F���������`�F�b�N<BR>
     * �@@�@@ this.�m�F����������null�̒l�ł���ꍇ�A��O���X���[����B<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00078<BR>
     * <BR>
     * �S�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@ this.�����R�[�h��null�̒l�ł���Η�O���X���[����B<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     * �T�j�@@����ID�`�F�b�N <BR>
     *      this.����ID �� null�ł���ꍇ�A��O���X���[����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407367B30088
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

        //this.�w����@@�̒l���A"���z" "����"�ȊO�̒l�ł���ꍇ�A��O���X���[����
        if (!(WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("this.�w����@@�̒l���A'���z' '����'�ȊO�̒l�ł���ꍇ�A��O���X���[����");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@�̒l���A'���z' '����'�ȊO�̒l�ł���ꍇ");
        }

        //�Q�j �������ʃ`�F�b�N
        //�Q�|�P�jthis.�������� �� null �ł���ꍇ��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.ruitoOrderQuantity))
        {
            log.debug("�������ʂ����͂���Ă��܂���B");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�������ʂ����͂���Ă��܂���B");

        }
        //�Q�|�Q�jthis.�������� �� ���� �ł���ꍇ�A��O���X���[����B
		try
	    {
	 		double l_dblTemp = Double.parseDouble(this.ruitoOrderQuantity);
	    }
	    catch (NumberFormatException l_ex)
	    {
            log.debug("this.�������� �� ���� �ł���ꍇ�A��O���X���[����B");
		    throw new WEB3BusinessLayerException(
				   WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                    getClass().getName() + "." + STR_METHOD_NAME, 
                    "�������� �� ���� �ł���ꍇ");
	    }        

		//�Q�|�R�jthis.�������� �� 0 �ł���ꍇ�A��O���X���[����B
	    if (Double.valueOf(this.ruitoOrderQuantity).doubleValue() <= 0)
        {            
            log.debug("this.�������� �� 0 �ł���ꍇ�A��O���X���[����B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�������� �� 0 �ł���ꍇ");
        }

		//�Q�|�S�jthis.�������� �� 11�� �ł���ꍇ�A��O���X���[����B
        if (this.ruitoOrderQuantity.length() > 11)
        {          
            log.debug("this.�������� �� 11�� �ł���ꍇ�A��O���X���[����B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�������� �� 11�� �ł���ꍇ");
        }

        //�R�j�@@�m�F���������`�F�b�N 
        //�m�F����������null�̒l�ł���ꍇ�A��O���X���[����
        if (this.checkDate == null)
        {
            log.debug("�m�F�������������͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�m�F�������������͂���Ă��܂���B");
        }
        
        //�S�j�@@�����R�[�h�`�F�b�N 
        //this.�����R�[�h �� null�ł���ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.ruitoProductCode))
        {
            log.debug("�����R�[�h�����͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�����R�[�h�����͂���Ă��܂���B");
        }
        //�T�j�@@����ID�`�F�b�N
        //this.����ID �� null�ł���ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            log.debug("����ID�����͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "����ID�����͂���Ă��܂���");
        }
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ����t�����������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407297EF005C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoBuyCompleteResponse(this);
    }
}
@
