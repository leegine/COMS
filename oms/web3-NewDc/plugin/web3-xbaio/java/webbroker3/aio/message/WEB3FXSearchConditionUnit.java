head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSearchConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX��������(WEB3FXSearchConditionUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX��������) <BR>
 *  FX��������  <BR>
 * (�ꗗ�\���@@�\�ɂĈ�ӂƂȂ錟��������\��)
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FXSearchConditionUnit extends Message
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FXSearchConditionUnit.class);
        
    /**
     * (���X�R�[�h) <BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (���ʃR�[�h) <BR>
     * ���ʃR�[�h
     */
    public String requestNumber;

    /**
     * (FX��������) <BR>
     * �R���X�g���N�^�B
     * 
     * @@roseuid 41B5B5050321
     */
    public WEB3FXSearchConditionUnit()
    {
    }

    /**
     * (validate) <BR>
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j ���X�R�[�h�̃`�F�b�N <BR>
     * �P�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * <BR>
     * �Q�j ���ʃR�[�h�̃`�F�b�N <BR>
     * �Q�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00829 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41BE4FC000D7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N 
        // �P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("���X�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        
        //�Q�j�@@���ʃR�[�h�̃`�F�b�N 
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.requestNumber))
        {
            log.debug("���ʃR�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʃR�[�h�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}@
