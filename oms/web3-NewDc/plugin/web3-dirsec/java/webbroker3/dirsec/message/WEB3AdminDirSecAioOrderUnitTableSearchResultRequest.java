head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����P�ʃe�[�u���������ʃ��N�G�X�g(WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ����� (���u) �V�K�쐬
                 : 2007/01/08�@@����(���u) �d�l�ύX���f��No.022
Revesion History : 2007/10/31  �Ӑ� (���u) ���f��No.113
Revesion History : 2008/07/15  ���� (���u) ���f��No.130
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminDirOrderUnitTblKbnDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�����P�ʃe�[�u���������ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE�����P�ʃe�[�u���������ʃ��N�G�X�g�N���X�B<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableSearchResultRequest extends WEB3GenRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_search_result";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * ���O���[�e�B���e�B<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.class);

    /**
     * (�����P�ʃe�[�u���敪)<BR>
     * �����P�ʃe�[�u���敪�B <BR>
     * <BR>
     * 0�F�@@�O��<BR>
     * 1�F�@@���o��<BR>
     * 2�F�@@���M<BR>
     * 3�F  ����<BR>
     * 4�F  �敨OP<BR>
     */
    public String orderUnitTblKbn;

	/**
	 * (�����P��ID)<BR>
	 * �����P��ID�B<BR>
	 */
	public String orderUnitId;

	/**
	 * @@roseuid 44BE20BF02CE
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchResultRequest()
	{

	}

	/**
	 * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
	 * <BR>
	 * �P�j�����P��ID�`�F�b�N <BR>
	 * �P�|�P�jthis.�����P��ID == null �̏ꍇ�A <BR>
	 * �u�����P��ID�������͂ł��B�v�̗�O���X���[����B<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02505<BR>
	 * <BR>
	 * �P�|�Q�jthis.�����P��ID�̌��� > 18 �̏ꍇ�A <BR>
	 * �u�����P��ID�̌������s���ł��B�v�̗�O���X���[����B<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02506<BR>
	 * <BR>
	 * �P�|�R�jthis.�����P��ID�������ȊO�̒l�ł������ꍇ�A <BR>
	 * �u�����P��ID�������ȊO�̒l�ł��B�v�̗�O���X���[����B <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02507<BR>
     * <BR>
     * �Q�j�����P�ʃe�[�u���敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�����P�ʃe�[�u���敪 == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����P�ʃe�[�u�������I���ł��B�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02704<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�����P�ʃe�[�u���敪 !=�i0�A1�A2�A3�A4�j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����P�ʃe�[�u�������݂��Ȃ��敪�ł��B�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02705<BR>
     * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44B6F995004B
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);

		// �P�j�����P��ID�`�F�b�N
		// �P�|�P�jthis.�����P��ID == null �̏ꍇ�A
		// �u�����P��ID�������͂ł��B�v�̗�O���X���[����B
		if (this.orderUnitId == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02505,
				this.getClass().getName() + STR_METHOD_NAME, 
				"�����P��ID�������͂ł��B");
		}

		// �P�|�Q�jthis.�����P��ID�̌��� > 18 �̏ꍇ�A
		// �u�����P��ID�̌������s���ł��B�v�̗�O���X���[����B
		if (this.orderUnitId.length() > 18)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02506, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�����P��ID�̌������s���ł��B");
		}

		// �P�|�R�j this.�����P��ID�������ȊO�̒l�ł������ꍇ�A
		// �u�����P��ID�������ȊO�̒l�ł��B�v�̗�O���X���[����B
		if (!WEB3StringTypeUtility.isDigit(this.orderUnitId))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02507, 
				this.getClass().getName() + STR_METHOD_NAME,
				"�����P��ID�������ȊO�̒l�ł��B");
		}

        //�Q�j�����P�ʃe�[�u���敪�`�F�b�N
        //�@@�Q�|�P�jthis.�����P�ʃe�[�u���敪 == null �̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�����P�ʃe�[�u�������I���ł��B�v�̗�O���X���[����B
        if (this.orderUnitTblKbn == null)
        {
            log.debug("�����P�ʃe�[�u�������I���ł��B" + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02704,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����P�ʃe�[�u�������I���ł��B");
        }

        //�Q�|�Q�jthis.�����P�ʃe�[�u���敪 !=�i0�A1�A2�A3�A4�j �̏ꍇ�A
        //   �@@�@@�u�����P�ʃe�[�u�������݂��Ȃ��敪�ł��B�v�̗�O���X���[����B
        if (!WEB3AdminDirOrderUnitTblKbnDef.FEQ.equals(this.orderUnitTblKbn)
            && !WEB3AdminDirOrderUnitTblKbnDef.AIO.equals(this.orderUnitTblKbn)
            && !WEB3AdminDirOrderUnitTblKbnDef.MUTUAL.equals(this.orderUnitTblKbn)
            && !WEB3AdminDirOrderUnitTblKbnDef.EQ.equals(this.orderUnitTblKbn)
            && !WEB3AdminDirOrderUnitTblKbnDef.IFO.equals(this.orderUnitTblKbn))
        {
            log.debug("�����P�ʃe�[�u�������݂��Ȃ��敪�ł��B" + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02705,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����P�ʃe�[�u�������݂��Ȃ��敪�ł��B");
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
		return new WEB3AdminDirSecAioOrderUnitTableSearchResultResponse(this);
	}
}
@
