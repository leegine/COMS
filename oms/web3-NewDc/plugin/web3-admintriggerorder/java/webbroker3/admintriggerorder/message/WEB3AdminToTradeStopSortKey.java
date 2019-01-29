head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �戵��~�\�[�g�L�[(WEB3AdminToTradeStopSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���@@�F(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�戵��~�\�[�g�L�[)<BR>
 * �戵��~�\�[�g�L�[�N���X<BR>
 * 
 * @@author ���@@�F
 * @@version 1.0
 */
public class WEB3AdminToTradeStopSortKey extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopSortKey.class);
    
    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{�������L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 4430D2C00177
     */
    public WEB3AdminToTradeStopSortKey() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@this.�L�[���ځ�null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B <BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j�@@this.�����^�~����null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B <BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * �R�j�@@this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E�hA�F�����h <BR>
     * �@@�@@�@@�E�hD�F�~���h <BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 441110220194
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�jthis.�L�[���ځ�null�̏ꍇ�A 
        // �@@ �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B 
        //     class : WEB3BusinessLayerException
        //     tag : BUSINESS_ERROR_00085
        if (WEB3StringTypeUtility.isEmpty(this.keyItem))
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B"); 
        }
        
        // �Q�jthis.�����^�~����null�̏ꍇ�A 
        //�@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
        //     class : WEB3BusinessLayerException
        //     tag : BUSINESS_ERROR_00087
        if (WEB3StringTypeUtility.isEmpty(this.ascDesc))
        {
            log.debug("�����^�~�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�~�������w��ł��B"); 
        }
        
        // �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A 
        //�@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B 
        // �@@�@@�@@�E�hA�F�����h 
        // �@@�@@�@@�E�hD�F�~���h 
        //     class : WEB3BusinessLayerException
        //     tag : BUSINESS_ERROR_00088
        if (!(WEB3AscDescDef.ASC.equals(this.ascDesc) || WEB3AscDescDef.DESC.equals(this.ascDesc)))
        {
            log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
