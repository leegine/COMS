head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��Җ����\�������o�^���͉�ʃ��N�G�X�g(WEB3AdminMutualDisplayOrderInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
Revesion History : 2007/02/25 ������ (���u) ���f�� No.543
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M�Ǘ��Җ����\�������o�^���͉�ʃ��N�G�X�g)<BR>
 * �����M���Ǘ��Җ����\�������o�^���͉�ʃ��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderInputRequest extends WEB3GenRequest 
{
   
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_display_order_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031015L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualDisplayOrderInputRequest.class);    
  
    /**
     * (���M�\�[�g�L�[)<BR>
     * <BR>
     * �Ώۍ���:
     *     �h���ݕ\�����h�A
     *     �h�����R�[�h�h�A<BR>
     * �@@  �h���M��������R�[�h�h�A<BR>
     * �@@  �h���M�����J�e�S���[�R�[�h�P�h�A<BR>
     * �@@  �h���M�����J�e�S���[�R�[�h�Q�h�A<BR>
     * �@@  �h���M�����J�e�S���[�R�[�h�R�h
     */
    public WEB3MutualSortKey[] sortKeys;

    /**
     * (���M�E�O��MMF�\���敪)<BR>
     * ���M�E�O��MMF�\���敪<BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪<BR>
     * <BR>
     * 0:���M�̂�<BR>
     * 1:�O��MMF�̂�<BR>
     * 2:����<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�Ǘ��Җ����\�������o�^���͉�ʃ��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4153BB3D0037
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualDisplayOrderInputResponse(this);
    }
   
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@���M�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�P�|�P)�@@this.���M�\�[�g�L�[��null�̏ꍇ�A��O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:BUSINESS_ERROR_00231<BR>
     * �@@�P�|�Q)�@@this.���M�\�[�g�L�[�̗v�f����0�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:BUSINESS_ERROR_00232<BR>
     * �@@�P�|�R)�@@this.���M�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�P�|�R�|�P�j�L�[���ځ�null�̏ꍇ�A��O���X���[����B<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_00085<BR>
     * �@@�@@�P�|�R�|�Q�j�L�[���ڂ��ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A<BR>
     *                ��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  �E�h���ݕ\�����h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  �E�h�����R�[�h�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  �E�h���M��������R�[�h�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  �E�h���M�����J�e�S���[�R�[�h�P�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  �E�h���M�����J�e�S���[�R�[�h�Q�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  �E�h���M�����J�e�S���[�R�[�h�R�h<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_00086<BR>
     * �@@�@@�P�|�R�|�R�j�����^�~����null�̏ꍇ�A��O���X���[����B<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_00087<BR>
     * �@@�@@�P�|�R�|�S�j�����^�~�����ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A<BR>
     *                ��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  �E�h�����h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  �E�h�~���h<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_00088
     * @@roseuid 4153C2F30334
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@���M�\�[�g�L�[�̃`�F�b�N 
        //  �P�|�P)�@@this.���M�\�[�g�L�[��null�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        //�P�|�Q)�@@this.���M�\�[�g�L�[�̗v�f����0�ł���Η�O���X���[����B 
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�P�|�R)�@@this.���M�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            //�P�|�R�|�P�j�L�[���ځ�null�̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].keyItem))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            }
            
            //�P�|�R�|�Q�j�L�[���ڂ��ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
            //�@@�E�h���ݕ\�����h 
            //�@@�E�h�����R�[�h�h 
            //�@@�E�h���M��������R�[�h�h 
            //�@@�E�h���M�����J�e�S���[�R�[�h�P�h 
            //�@@�E�h���M�����J�e�S���[�R�[�h�Q�h 
            //�@@�E�h���M�����J�e�S���[�R�[�h�R�h
            if (!(WEB3MFSortkeyItemDef.DISPLAY_ORDER.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.PRODUCT_CODE.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.MUTUAL_ASSOC_PRODUCT_CODE.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_2.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_3.equals(
                    this.sortKeys[i].keyItem)))
            {
                log.debug("�L�[���ڂɖ������A�����敪�A�������Ԃ̍��ږ��ȊO�̒l�����݂��Ă��܂��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "[���ݕ\����]�A[�����R�[�h]�A[���M��������R�[�h]�A" +
                    "[���M�����J�e�S���[�R�[�h�P]�A[���M�����J�e�S���[�R�[�h�Q]�A" +
                    "[���M�����J�e�S���[�R�[�h�R]");
            }
            
            //�P�|�R�|�R�j�����^�~����null�̏ꍇ�A��O���X���[����B 
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].ascDesc))
            {
                log.debug("�����^�~�������w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�������w��ł��B");
            }
            
            //�P�|�R�|�S�j�����^�~�����ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
            //�@@�E�h�����h 
            //�@@�E�h�~���h
            if (!(WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc)
                || WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc)))
            {
                log.debug("�����^�~���́hA�F�����h�A�hD�F�~���h�̒l�ȊO�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~���́hA�F�����h�A�hD�F�~���h�̒l�ȊO�ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
