head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3ForeignSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������\�[�g�L�[(WEB3ForeignSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������\�[�g�L�[)<BR>
 * �O�������\�[�g�L�[�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3ForeignSortKey extends Message 
{
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ForeignSortKey.class);
        
    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{������<BR>
     * �L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A�F����<BR>
     * D�F�~��<BR>
     */
    public String ascDesc;
    
    /**
     * (�O�������\�[�g�L�[)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4200CCE900C6
     */
    public WEB3ForeignSortKey() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�L�[���ڃ`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�L�[���� == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j�@@�����^�~���`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�����^�~�� == null�̏ꍇ�A ��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00087<BR>
     * �@@�Q�|�Q�j�@@this.�����^�~�����ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�E"�~��"<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00088<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 429ECBE50069
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�L�[���ڃ`�F�b�N
        //�P�|�P�j�@@this.�L�[���� == null�̏ꍇ�A��O���X���[����B
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B" + this.keyItem);
        }
        
        //�Q�j�@@�����^�~���`�F�b�N
        //�Q�|�P�j�@@this.�����^�~�� == null�̏ꍇ�A ��O���X���[����B
        if (this.ascDesc == null)
        {
            log.debug("�����^�~�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�������w��ł��B" + this.ascDesc);
        }
        
        //�Q�|�Q�j�@@this.�����^�~�����ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�E"����"�E"�~��"
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && 
            !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B" 
                + this.ascDesc);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
