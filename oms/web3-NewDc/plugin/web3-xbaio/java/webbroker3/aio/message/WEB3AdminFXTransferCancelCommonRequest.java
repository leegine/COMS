head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferCancelCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�U�֎�����ʃ��N�G�X�g(WEB3AdminFXTransferCancelCommonRequest)
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
 * (�Ǘ��ҁEFX�U�֎�����ʃ��N�G�X�g) <BR>
 * �Ǘ��ҁEFX�U�֎�����ʃ��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXTransferCancelCommonRequest extends WEB3GenRequest
{
    /**
     * (FX���������ꗗ) <BR>
     * FX���������̈ꗗ
     */
    public WEB3FXSearchConditionUnit[] fxSearchConditionList;

    /**
     * @@roseuid 41E7901F037A
     */
    public WEB3AdminFXTransferCancelCommonRequest()
    {
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferCancelCommonRequest.class);
        
    /**
     * (validate) <BR>
     * ���N�G�X�g�f�[�^�̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�jFX���������ꗗ <BR>
     * <BR>
     * �P�|�P�j <BR>
     * this.FX���������ꗗ == null or <BR>
     * this.FX���������ꗗ.length() == 0 <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01774 <BR>
     * <BR>
     * �P�|�Q�jFX���������ꗗ�̊e�v�f�iFX���������j�ɂ��� <BR>
     * <BR>
     * FX��������.validate()���\�b�h���R�[������B
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�jFX���������ꗗ 
        // �P�|�P�j 
        //   this.FX���������ꗗ == null or 
        //   this.FX���������ꗗ.length() == 0 
        //   �̏ꍇ�A��O���X���[����B 
        if (this.fxSearchConditionList == null 
            || this.fxSearchConditionList.length == 0)
        {
            log.debug("FX���������ꗗ�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01774,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX���������ꗗ�����w��ł��B");
        }
        
        //  �P�|�Q�jFX���������ꗗ�̊e�v�f�iFX���������j�ɂ��� 
        //   FX��������.validate()���\�b�h���R�[������B 
        for (int i = 0; i < this.fxSearchConditionList.length; i++)
        {
            WEB3FXSearchConditionUnit l_fxSearchConditionUnit = 
                this.fxSearchConditionList[i];
            l_fxSearchConditionUnit.validate();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁEFX�U�֎�����ʃ��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E790200000
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferCancelCommonResponse(this);
    }
}@
