head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferListConditionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�U�ֈꗗ�������̓��N�G�X�g(WEB3AdminFXTransferListConditionRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁEFX�U�ֈꗗ�������̓��N�G�X�g) <BR>
 * �Ǘ��ҁEFX�U�ֈꗗ�������̓��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXTransferListConditionRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_transfer_list_condition";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (���X�R�[�h�ꗗ) <BR>
     * ���X�R�[�h�̈ꗗ
     */
    public String[] branchCodeList;

    /**
     * @@roseuid 41E7904B0213
     */
    public WEB3AdminFXTransferListConditionRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferListConditionRequest.class);

    /**
     * (validate) <BR>
     * ���N�G�X�g�f�[�^�̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j���X�R�[�h <BR>
     * <BR>
     * this.���X�R�[�h == null or <BR>
     * this.���X�R�[�h.length() == 0 <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01757 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C2667E01F3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h 
        //this.���X�R�[�h == null or 
        //this.���X�R�[�h.length() == 0 
        //�̏ꍇ�A��O���X���[����B 
        if (this.branchCodeList == null)
        {
            log.debug("���X�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        if (this.branchCodeList.length == 0)
        {
            log.debug("���X�R�[�h�̗v�f�����O�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f�����O�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁEFX�U�ֈꗗ�������̓��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904B029F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferListConditionResponse(this);
    }
}@
