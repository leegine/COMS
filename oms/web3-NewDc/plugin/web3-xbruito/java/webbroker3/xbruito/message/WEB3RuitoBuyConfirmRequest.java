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
filename	WEB3RuitoBuyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������t�����m�F���N�G�X�g�N���X(WEB3RuitoBuyConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
                   2004/12/01 ��O�� (���u) �c�Ή�
Revesion History : 2007/10/25 ��іQ (���u) ���f��NO.093
*/
package webbroker3.xbruito.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �ݐϓ������t�����m�F���N�G�X�g�N���X<BR>
 */
public class WEB3RuitoBuyConfirmRequest extends WEB3RuitoCommonRequest
{
	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */    
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoBuyConfirmRequest.class);
			
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_buy_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * �w����@@<BR>
     * 3�F���z�A4�F����<BR>
     */
    public String specifyDiv;

    /**
     * (�d�q���`�F�b�N�t���O)<BR>
     * �d�q���`�F�b�N�t���O<BR>
     * <BR>
     * true�F�`�F�b�N�v<BR>
     * false�F�`�F�b�N�s�v<BR>
     */
    public boolean batoCheckFlag;
    
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR>
     */
    public String typeCode;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922CAA03B9
     */
    public WEB3RuitoBuyConfirmRequest()
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
     * �@@�Q�|�R�jthis.�������� �� 0�@@�ł���ꍇ�A��O���X���[����B <BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00076<BR>
     * �@@�Q�|�S�jthis.�������� �� 11�� �ł���ꍇ�A��O���X���[����B<BR>            
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00077<BR>
     * <BR>
     * �R�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@ this.�����R�[�h��null�̒l�ł���Η�O���X���[����B<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4073679802CB
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

        //�P�jthis.�w����@@�̒l���A"���z" "����"�ȊO�̒l�ł���ꍇ�A��O���X���[����B
        if (!(WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("�w����@@�̒l���A�u���z�v�u�����v�ȊO�̒l�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�w����@@�̒l���A�u���z�v�u�����v�ȊO�̒l�ł���B");
        }

        //�Q�j�������ʃ`�F�b�N
        //�Q�|�P�jthis.�������� �� null �ł���ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.ruitoOrderQuantity))
        {
            log.debug("�������ʂ����͂���Ă��܂���B");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�������ʂ����͂���Ă��܂���");

        }
        //�Q�|�Q�jthis.�������� �� ���� �ł���ꍇ�A��O���X���[����B
		try
		{
            //this.�������� �� ���� �ł���ꍇ�A��O���X���[����B
			double l_dbTemp = Double.parseDouble(this.ruitoOrderQuantity);
		}
		catch(NumberFormatException l_ex)
		{
            log.debug("�������ʂ������ȊO�̒l�ł���B");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�������ʂ������ȊO�̒l�ł���B");
		}

        //�Q�|�R�jthis.�������� �� 0 �ł���ꍇ�A��O���X���[����B
        if (Double.valueOf(this.ruitoOrderQuantity).doubleValue() <= 0)
        {            
            log.debug("�������ʂ�0�ȉ��̒l�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�������ʂ�0�ȉ��̒l�ł���B");
        }
        
		//�Q�|�S�jthis.�������� �� 11�� �ł���ꍇ�A��O���X���[����B
        if (this.ruitoOrderQuantity.length() > 11)
        {            
            log.debug("�������ʂ�����l�𒴂��Ă��܂��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�������ʂ�����l�𒴂��Ă��܂��B");
        }

        //�R�j�@@�����R�[�h�`�F�b�N 
        //�����R�[�h��null�̒l�ł���Η�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.ruitoProductCode))
        {
            log.debug("�����R�[�h�����͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�����R�[�h�����͂���Ă��܂���B");
        }
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ����t�����m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407297A702FB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoBuyConfirmResponse(this);
    }

}
@
