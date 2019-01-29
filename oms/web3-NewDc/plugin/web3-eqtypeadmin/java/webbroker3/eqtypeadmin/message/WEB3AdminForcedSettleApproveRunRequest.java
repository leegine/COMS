head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleApproveRunRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g(WEB3AdminForcedSettleApproveLapseRunRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ��іQ (���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleApproveRunRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleApproveRunRequest.class);

    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_approve_run";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * ����ID�̔z��<BR>
     */
    public String[] id;

    /**
     * (���F�敪)<BR>
     * ���F�敪<BR>
     * <BR>
     * 0�F�@@���F<BR>
     * 1�F�@@�񏳔F<BR>
     */
    public String approveType;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * @@roseuid 462CA42702B4
     */
    public WEB3AdminForcedSettleApproveRunRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@ID�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.ID == null�̏ꍇ�A<BR>
     * �@@�@@�uID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00600       <BR>
     * <BR>
     * �Q�j�@@���F�敪�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.���F�敪 == null�̏ꍇ�A<BR>
     * �@@�@@�u���F�敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02761       <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.���F�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u���F�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"���F"<BR>
     * �@@�@@�@@�E"�񏳔F"<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02760       <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46075536025B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

		//  �P�j�@@ID�`�F�b�N
		// �@@�P�|�P�j�@@this.ID == null�̏ꍇ�A
		//�@@�@@�uID��null�v�̗�O���X���[����B
    	if (this.id == null)
    	{
    		log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
    	}

        //�Q�j�@@���F�敪�`�F�b�N<BR>
        // �@@�Q�|�P�j�@@this.���F�敪 == null�̏ꍇ�A<BR>
        // �@@�@@�u���F�敪��null�v�̗�O���X���[����B<BR>
        if (this.approveType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02761,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���F�敪��null�B");
        }

        //   �Q�|�Q�j�@@this.���F�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�u���F�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�E"���F"
        //�@@�@@�@@�E"�񏳔F"
        if (!WEB3AdminEquityApproveTypeDef.APPROVE.equals(this.approveType)
            && !WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(this.approveType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02760,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���F�敪������`�̒l�B");
        }
         log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminForcedSettleApproveRunResponse(this);
    }
}
@
