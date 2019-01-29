head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�������N�G�X�g(WEB3AdminMutualConditionsInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/21 ��O��  (���u) �V�K�쐬
                 : 2006/8/23 �Ԑi(���u) �d�l�ύX ���f�� 614
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����ʒm�������N�G�X�g<BR>
 *
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AdminAioCashinNoticeSearchRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_aio_cashin_notice_search";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211056L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�����)<BR>
     * �����<BR>
     */
    public Date settlementDate;

    /**
     * (�U���˗��l�R�[�h)<BR>
     * �U���˗��l�R�[�h<BR>
     */
    public String clientCode;

    /**
     * (��������(FROM))<BR>
     * ��������(FROM)<BR>
     */
    public Date transactionDateFrom;

    /**
     * (��������(TO))<BR>
     * ��������(TO)<BR>
     */
    public Date transactionDateTo;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     */
    public String transactionDiv;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3AioCashinNoticeSortKey[] sortKeys;

    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeSearchRequest.class);

    /**
     * (�����ʒm�������N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40DF7B2003DF
     */
    public WEB3AdminAioCashinNoticeSearchRequest()
    {
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �����ʒm�������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF7B460371
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAioCashinNoticeSearchResponse(this);
    }

    /**
     * �P�j���X�R�[�h�̔z��Ɨv�f��null�łȂ����Ƃ��`�F�b�N
     * null�̏ꍇ�u���X�R�[�h�����w��v�G���[���X���[����B
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00833 <BR>
     * �Q�j�����ʒm�\�[�g�L�[�̔z�񌏐���LOOP��
     * �����ʒm�\�[�g�L�[.validate()���ĂԁB<BR>

     * @@roseuid 40DF7B460381
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���X�R�[�h�̔z��Ɨv�f��null�łȂ����Ƃ��`�F�b�N
        //null�̏ꍇ�u���X�R�[�h�����w��v�G���[���X���[����B
        if (this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        else
        {
            for (int i = 0; i < this.branchCode.length; i++)
            {
                if (WEB3StringTypeUtility.isEmpty(this.branchCode[i]))
                {
                log.debug("���X�R�[�h�����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����w��ł��B");
                }
            }
        }

        //�Q�j�������null�łȂ����Ƃ��`�F�b�N
        //null�̏ꍇ�u���t�����͓��t�敪�`�F�b�N�v�G���[���X���[����B
        //(�G���[���b�Z�[�W�̂݊�����ł��邱�Ƃ𕪂���₷���ύX)
        if (this.settlementDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02157,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������w��ł��B");
        }



        //�R�j�����ʒm�\�[�g�L�[�̔z�񌏐���LOOP��
        //�����ʒm�\�[�g�L�[.validate()���ĂԁB
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            sortKeys[i].validate();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
