head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��Җ����\�������o�^�������N�G�X�g(WEB3AdminMutualDisplayOrderCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M�Ǘ��Җ����\�������o�^�������N�G�X�g)<BR>
 * �����M���Ǘ��Җ����\�������o�^�������N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_display_order_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031047L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualDisplayOrderCompleteRequest.class);
    
    /**
     * (�����\�������X�V�l�ꗗ)<BR>
     * ���M�Ǘ��Җ����\�������X�V�l�I�u�W�F�N�g�̔z��
     */
    public WEB3MutualDisplayOrderChangeUnit[] displayOrderChangeList;
    
    /**
     * (�Ïؔԍ�)<BR>
     *  �Ïؔԍ�
     */
    public String password;
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�Ǘ��Җ����\�������o�^�������X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4157AA530153
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualDisplayOrderCompleteResponse(this);
    }
    
    /**
     * (validate)<BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�����\�������X�V�l�ꗗ�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.�����\�������X�V�l�ꗗ��null�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01273<BR>
     * �@@�P�|�Q�jthis.�����\�������X�V�l�ꗗ�̗v�f����0�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01274<BR>
     * �@@�P�|�R�jthis.�����\�������X�V�l�ꗗ�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     * �@@�@@�P�|�R�|�P�j�����\�������X�V�l.�\������null�̏ꍇ�A��O���X���[����B<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_01275<BR>
     * �@@�@@�P�|�R�|�Q�j�����\�������X�V�l.�\���������l�ȊO�̏ꍇ�A<BR>
     *                ��O���X���[����B<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_01276<BR>
     * �@@�@@�P�|�R�|�R�j�����\�������X�V�l.�����R�[�h��null�̏ꍇ�A<BR>
     *                ��O���X���[����B<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_01277
     * @@throws WEB3BaseException
     * @@roseuid 4153BB7403A2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�����\�������X�V�l�ꗗ�̃`�F�b�N 
        //  �P�|�P�jthis.�����\�������X�V�l�ꗗ��null�̏ꍇ�A��O���X���[����B 
        if (this.displayOrderChangeList == null)
        {
            log.debug("�����\�������X�V�l�ꗗ�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01273,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����\�������X�V�l�ꗗ�����w��ł��B");
        }
        
        //�P�|�Q�jthis.�����\�������X�V�l�ꗗ�̗v�f����0�̏ꍇ�A��O���X���[����B 
        if (this.displayOrderChangeList.length == 0)
        {
            log.debug("�����\�������X�V�l�ꗗ�̗v�f�����O�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01274,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����\�������X�V�l�ꗗ�̗v�f�����O�ł���B");
        }
        
        //�P�|�R�jthis.�����\�������X�V�l�ꗗ�̗v�f�����A�ȉ����J��Ԃ��B 
        for (int i = 0; i < this.displayOrderChangeList.length; i++)
        {
            //�P�|�R�|�P�j�����\�������X�V�l.�\����!=null�ł���A�����l�ȊO�̏ꍇ�A��O���X���[����B 
            if (!WEB3StringTypeUtility.isEmpty(this.displayOrderChangeList[i].displayOrder) &&
                !WEB3StringTypeUtility.isNumber(this.displayOrderChangeList[i].displayOrder))
            {
                log.debug("�����\�������X�V�l�̕\���������l�ȊO�̒l�ł���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01276,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����\�������X�V�l�̕\���������l�ȊO�̒l�ł���B");
            }
            
            //�P�|�R�|�Q�j�����\�������X�V�l.�����R�[�h��null�̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.displayOrderChangeList[i].mutualProductCode))
            {
                log.debug("�����\�������X�V�l�̖����R�[�h�����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01277,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����\�������X�V�l�̖����R�[�h�����w��ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
