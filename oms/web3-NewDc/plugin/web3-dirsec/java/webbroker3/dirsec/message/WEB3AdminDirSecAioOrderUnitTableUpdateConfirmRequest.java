head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�m�F���N�G�X�g(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ����� (���u) �V�K�쐬
				   2006/08/11  ����� (���u) ����̍X�E���f��014
Revesion History : 2007/10/31  �Ӑ� (���u) ���f��No.113
Revesion History : 2008/07/15  ���� (���u) ���f��No.130
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�m�F���N�G�X�g�N���X�B<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest extends WEB3GenRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_update_confirm";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * ���O���[�e�B���e�B<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.class);

	/**
	 * (�X�V_�������)<BR>
	 * �X�V���钍�����<BR>
	 */
	public String updateOrderStatus;

	/**
	 * (�X�V_�����L�����)<BR>
	 * �X�V���钍���L�����<BR>
	 */
	public String updateOrderOpenStatus;

    /**
     * (�X�V_�����)<BR>
     * �X�V_�����
     */
    public String updateExecStatus;

    /**
     * (�X�V_������)<BR>
     * �X�V���锭����<BR>
     */
    public String updateOrderBizDate;

	/**
	 * @@roseuid 44BE20BF037A
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest()
	{

	}

	/**
	 * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
	 * <BR>
	 * �P�j������ԃ`�F�b�N<BR>
	 * �P�|�P�j WEB3StringTypeUtility.isEmpty(this.�X�V_�������) == false �̏ꍇ�A<BR>
	 * �ȉ��̃`�F�b�N���s���B<BR>
	 * �P�|�P�|�P�j this.�X�V_������Ԃ������ȊO�̒l�ł������ꍇ�A<BR>
	 * �u������Ԃ������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02508<BR>
	 * <BR>
	 * �Q�j�����L����ԃ`�F�b�N <BR>
	 * �Q�|�P�j WEB3StringTypeUtility.isEmpty(this.�X�V_�����L�����) == false �̏ꍇ�A<BR>
	 * �ȉ��̃`�F�b�N���s���B<BR> 
	 * �Q�|�P�|�P�jthis.�X�V_�����L����Ԃ������ȊO�̒l�ł������ꍇ�A<BR>
	 * �u�����L����Ԃ������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR> 
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02509<BR>
	 * <BR>
     * �R�j����ԃ`�F�b�N<BR>
     * �R�|�P�j�@@WEB3StringTypeUtility.isEmpty(this.�X�V_�����) == false�@@�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̃`�F�b�N���s���B<BR>
     * �R�|�P�|�P�jthis.�X�V_����Ԃ������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u����Ԃ������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02951<BR>
     * <BR>
     * �S�j�������`�F�b�N <BR>
�@@   * �S�|�P�j�@@WEB3StringTypeUtility.isEmpty(this.�X�V_������) == false�@@�̏ꍇ�A <BR>
�@@�@@ * �@@�@@�ȉ��̃`�F�b�N���s���B <BR>
�@@�@@ * �S�|�P�|�P�jthis.�X�V_�������̌��� != 8 �ł������ꍇ�A <BR>
�@@�@@ * �@@�@@�@@�@@�@@�u�������̌������s���ł��B�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02160<BR>
     * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44475D860117
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);

		// �P�j������ԃ`�F�b�N
		// �P�|�P�j WEB3StringTypeUtility.isEmpty(this.�X�V_�������) == false �̏ꍇ�A
		// �ȉ��̃`�F�b�N���s���B
		// �P�|�P�|�P�jthis.�X�V_������ԃ`�F�b�N�������ȊO�̒l�ł������ꍇ�A
		// �u������Ԃ������ȊO�̒l�ł��B�v�̗�O���X���[����B
		if (!WEB3StringTypeUtility.isEmpty(this.updateOrderStatus))
		{
			if (!WEB3StringTypeUtility.isDigit(this.updateOrderStatus))
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02508,
					this.getClass().getName() + STR_METHOD_NAME,
					"������Ԃ������ȊO�̒l�ł��B");
			}
		}
		
		// �Q�j�����L����ԃ`�F�b�N
		// �Q�|�P�j WEB3StringTypeUtility.isEmpty(this.�X�V_�����L�����) == false �̏ꍇ�A
		// �ȉ��̃`�F�b�N���s���B
		// �Q�|�P�|�P�jthis.�X�V_�����L����ԃ`�F�b�N�������ȊO�̒l�ł������ꍇ�A
		// �u�����L����Ԃ������ȊO�̒l�ł��B�v�̗�O���X���[����B
		if (!WEB3StringTypeUtility.isEmpty(this.updateOrderOpenStatus))
		{
			if (!WEB3StringTypeUtility.isDigit(this.updateOrderOpenStatus))
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02509,
					this.getClass().getName() + STR_METHOD_NAME, 
					"�����L����Ԃ������ȊO�̒l�ł��B");
			}
		}

        //�R�j����ԃ`�F�b�N
        //�R�|�P�j�@@WEB3StringTypeUtility.isEmpty(this.�X�V_�����) == false�@@�̏ꍇ�A
        //�ȉ��̃`�F�b�N���s���B
        if (!WEB3StringTypeUtility.isEmpty(this.updateExecStatus))
        {
            //�R�|�P�|�P�jthis.�X�V_����Ԃ������ȊO�̒l�ł������ꍇ�A
            //�u����Ԃ������ȊO�̒l�ł��B�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.updateExecStatus))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02951,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����Ԃ������ȊO�̒l�ł��B");
            }
        }

        //�S�j�������`�F�b�N
        //�S�|�P�j�@@WEB3StringTypeUtility.isEmpty(this.�X�V_������) == false�@@�̏ꍇ�A
        //�ȉ��̃`�F�b�N���s���B
        if (!WEB3StringTypeUtility.isEmpty(this.updateOrderBizDate))
        {
            //�S�|�P�|�P�jthis.�X�V_�������̌��� != 8 �ł������ꍇ�A
            //�u�������̌������s���ł��B�v�̗�O���X���[����B
            if (this.updateOrderBizDate.length() != 8)
            {
                log.debug("�������̌������s���ł��B" + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02160,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�������G���[�B");
            }
        }
	}

	/**
	 * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * 
	 * @@return ���X�|���X�I�u�W�F�N�g
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse(this);
	}
}
@
