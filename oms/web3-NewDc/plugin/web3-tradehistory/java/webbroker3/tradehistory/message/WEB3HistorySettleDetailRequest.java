head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : ���ϖ��׃��N�G�X�g(WEB3HistorySettleDetailRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 ���q (���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (���ϖ��׃��N�G�X�g)<BR>
 * ���ϖ��׃��N�G�X�g�N���X
 * @@author ���q
 * @@version 1.0
 */
public class WEB3HistorySettleDetailRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistorySettleDetailRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_settleDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221704L;

    /**
     * (���׊Ǘ��ԍ�)<BR>
     * ���׊Ǘ��ԍ�<BR>
     */
    public String detailsManageNo;

    /**
     * (�|��E�v��)<BR>
     * �|��E�v��<BR>
     */
    public String remarkName;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public String quantity;

    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;

    /**
     * @@roseuid 41789C49008C
     */
    public WEB3HistorySettleDetailRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���׊Ǘ��ԍ��`�F�b�N<BR>
     * �@@�@@�@@this.���׊Ǘ��ԍ� == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�u���׊Ǘ��ԍ���null�v�̗�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :   BUSINESS_ERROR_01060             <BR>
     * <BR>
     * �Q�j�@@�|��E�v���`�F�b�N<BR>
     * �@@�@@�@@this.�|��E�v�� == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�u�|��E�v����null�v�̗�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_01061              <BR>
     * <BR>
     * �R�j�@@�������`�F�b�N<BR>
     * �@@�@@�@@this.������ == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�u��������null�v�̗�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_01062              <BR>
     * <BR>
     * �S�j�@@���ʃ`�F�b�N<BR>
     * �@@�@@�@@this.���� == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�u���ʂ�null�v�̗�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_01071           <BR>
     * <BR>
     * �T�j�@@��n���`�F�b�N<BR>
     * �@@�@@�@@this.��n�� == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�u��n����null�v�̗�O���X���[����B<BR>
     *        class         :  WEB3BusinessLayerException           <BR>
     *        tag            :  BUSINESS_ERROR_01079              <BR>
     * <BR>
     * @@roseuid 413419B0034F
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �@@���׊Ǘ��ԍ��`�F�b�N
        //   �u���׊Ǘ��ԍ���null�v�̗�O���X���[����
        if (this.detailsManageNo == null)
        {
            //��O
            log.error("�u���׊Ǘ��ԍ���null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01060,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        // �@@�|��E�v���`�F�b�N
        //   �u�|��E�v����null�v�̗�O���X���[����
        if (this.remarkName == null)
        {
            //��O
            log.error("�u�|��E�v����null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01061,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //   �������`�F�b�N
        //   �u��������null�v�̗�O���X���[����
        if (this.productName == null)
        {
            //��O
            log.error("�u��������null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01062,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //   ���ʃ`�F�b�N
        //   �u���ʂ�null�v�̗�O���X���[����
        if (this.quantity == null)
        {
            //��O
            log.error("�u���ʂ�null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01071,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //   ��n���`�F�b�N
        //   �u��n����null�v�̗�O���X���[����
        if (this.deliveryDate == null)
        {
            //��O
            log.error("�u��n����null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 41789C490138
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3HistorySettleDetailResponse(this);
    }
}
@
