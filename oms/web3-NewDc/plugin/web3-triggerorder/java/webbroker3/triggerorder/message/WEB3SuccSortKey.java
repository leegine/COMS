head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������\�[�g�L�[(WEB3SuccSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�A�������\�[�g�L�[)<BR>
 * �A�������\�[�g�L�[<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3SuccSortKey extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccSortKey.class);
    
    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X��<BR>
     * �̃V���{�������L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;
    
    /**
     * (�A�������\�[�g�L�[)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 431F84A10014
     */
    public WEB3SuccSortKey() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * )<BR>
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A)<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00085<BR>
     * �Q�jthis.�����^�~����null�̏ꍇ�A)<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00087<BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A)<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00088<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 431BECCD02B5
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�L�[���ڃ`�F�b�N
        //�P�|�P�j�@@this.�L�[���� == null�̏ꍇ�A
        //�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B" + this.keyItem);
        }
        
        //�Q�j�@@this.�����^�~����null�̏ꍇ�A)
        //�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
        if (this.ascDesc == null)
        {
            log.debug("�����^�~�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�������w��ł��B" + this.ascDesc);
        }
        
        //�R�j�@@this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A)
        //�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
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
